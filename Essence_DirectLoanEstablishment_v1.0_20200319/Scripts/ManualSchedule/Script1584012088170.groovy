import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import internal.GlobalVariable as GlobalVariable
import pageObjects.DirectLoanEstablishment as DirectLoanEstablishment
import java.sql.ResultSet

int shortTimeOut =2
int mediumTimeOut =4
int longTimeOut =10
DirectLoanEstablishment directLoanEstablishment = new DirectLoanEstablishment()
//Scripting for UAT-424, UAT-426, UAT-428, UAT-484, AUT-491, AUT-493, AUT-503 and AUT-505
//Enroll loan account - Log-in with Teller ->  Ask for Approval -> Log-out from system
//WebUI.callTestCase(findTestCase('Login'), [('url') : tel_url, ('username') : tel_username, ('password') : tel_password, ('active') : 'Yes'], FailureHandling.STOP_ON_FAILURE)
GlobalVariable.sequence = "1"
GlobalVariable.results = ""
GlobalVariable.testCaseID = testCaseNumber
GlobalVariable.step = '1'
GlobalVariable.no = no
//ResultSet rs = directLoanEstablishment.getResultTestDataFromDB(GlobalVariable.testCaseID)
//rs.next()

//Open Direct Loan Establishment
directLoanEstablishment.openDirectLoanEstablishment()

//Direct Loan Establishment is displayed
GlobalVariable.results += GlobalVariable.step + ',' + directLoanEstablishment.isDisplayedDirectLoanEstablishment(GlobalVariable.testCaseID,GlobalVariable.step, GlobalVariable.sequence)  + '|'
WebUI.delay(mediumTimeOut)
GlobalVariable.step = '2'
//enter the required fields
directLoanEstablishment.inputCustomerNumber(customerNumber)
directLoanEstablishment.inputDealerID(dealerID)
directLoanEstablishment.inputLoanPurpose(loanPurpose)
directLoanEstablishment.inputCurrency(currency)
directLoanEstablishment.inputSubproduct(subProduct)
directLoanEstablishment.inputTotalCost(totalCost)
directLoanEstablishment.inputCustomerContribution(customerContributionPercentage, customerContributionAmount)
directLoanEstablishment.inputContractDate(contractDate)
directLoanEstablishment.inputTermOfLoan(termOfLoan)
directLoanEstablishment.inputTermOfLoanFrequency(termOfLoanFrequency)
directLoanEstablishment.checkRepaymentOnLastMonth(repaymentOnLastMonth)
directLoanEstablishment.inputRepaymentFrequency(repaymentFrequency)
directLoanEstablishment.inputRepaymentStartDate(repaymentStartDate)
directLoanEstablishment.inputEffectiveInterestRateType(effectiveInteretsRateType, interestDetails)
directLoanEstablishment.inputInterestFrequency(interestFrequency, subProduct)
directLoanEstablishment.inputInterestRepaymentStartDate(interestRepaymentStartDate, subProduct)
directLoanEstablishment.inputMoratoriumType(moratoriumType)
directLoanEstablishment.inputMoratoriumPeriod(moratoriumPeriod)
directLoanEstablishment.inputSettlementType(settlementType)
directLoanEstablishment.inputSettlementAccount(settlementAccount, settlementType)
directLoanEstablishment.inputChargeFundingAccount(chargeFundingAccount)
//no checking
GlobalVariable.results += GlobalVariable.step + ", PASSED|"

GlobalVariable.step="3"
//Disbursement Details page will be displayed
directLoanEstablishment.clickNext()
GlobalVariable.results += GlobalVariable.step + "," +directLoanEstablishment.isScreenDisplayed(GlobalVariable.testCaseID,GlobalVariable.step,"Disbursement Details", GlobalVariable.sequence) +"|"

GlobalVariable.step="4"
directLoanEstablishment.inputDisbursementDetails(disbursementDate, disbursementAmount, disbursementMode, disbursementType, disbursementTo, disburseAccountID)
//directLoanEstablishment.clickAdd()
WebUI.delay(shortTimeOut)
directLoanEstablishment.clickNext()
WebUI.delay(shortTimeOut)
//Total Charge and Taxt: 40.000 MMK
GlobalVariable.results += GlobalVariable.step +","+ directLoanEstablishment.verifyTotalChargeAmount(GlobalVariable.testCaseID, GlobalVariable.step, GlobalVariable.sequence, chargeAmount)

GlobalVariable.step="5"
WebUI.delay(shortTimeOut)
directLoanEstablishment.clickNext()
/**
 * 1. Schedule grid will be displayed 
 * 2. first row: Disbursement Dat: Current Date, Disbursement Amount: 7.000.000MMK
 * 3. after click Next the Loan Repayment Schedule will be displayed
 * */
directLoanEstablishment.inputAutoPopilationMethod(autoPopulationMethod)
directLoanEstablishment.clickAutoPopulateSchedule()
directLoanEstablishment.editPrincipal(paymentNo, principalAmt)
WebUI.delay(shortTimeOut)
GlobalVariable.results += GlobalVariable.step + "," + directLoanEstablishment.verifyCaptureManualSchedule(GlobalVariable.testCaseID,GlobalVariable.step, GlobalVariable.sequence,disbursementAmount)+"|"

GlobalVariable.step="6"
directLoanEstablishment.clickNext()
//results += step + "," +directLoanEstablishment.isScreenDisplayed(testCaseNumber,step,"Loan Repayment Schedule", sequence) +"|"
WebUI.delay(mediumTimeOut)
/**
 * Account Number is displayed
 * Sub-product
 * Loan Amount: 7.000.000
 * Loan term: 36 months
 * Rate of Interest: 13%
 * */
WebUI.delay(shortTimeOut)
directLoanEstablishment.clickNext()
String loanRef = directLoanEstablishment.getLoanReference()
WebUI.delay(shortTimeOut)
directLoanEstablishment.clickNext()
directLoanEstablishment.clickAskForApproval()
//log out teller user
WebUI.callTestCase(findTestCase('LogOutFromSystem'), [:], FailureHandling.STOP_ON_FAILURE)
WebUI.delay(2)
//Supervisor approve - Log-in with Supervisor -> Approve -> Log-out from system
WebUI.callTestCase(findTestCase('ApprovalProcess'), [('description') : 'Referral', ('sup_url') : "", ('sup_username') : app_username
	, ('sup_password') : app_password], FailureHandling.STOP_ON_FAILURE)
WebUI.delay(2)
//Teller ask for fircosoft approval - Login with Teller -> asking for fircosoft approval -> Logout
WebUI.callTestCase(findTestCase('ConfirmProcess'), [('description') : 'Approval', ('tel_url') : ""
        , ('tel_username') : tel_username, ('tel_password') : tel_password, ('testCaseNumber') : GlobalVariable.testCaseID, ('results') : GlobalVariable.results, ('sequence') : GlobalVariable.sequence
        , ('loanRef') : loanRef, ('loanAmount') : loanAmount, ('loanStatus') : loanStatus, ('rateInterest') : rateInterest, ('subProduct') : subProduct, ('loanTerm') : termOfLoan
        , ('loanFrequence') : termOfLoanFrequency, ('step') :GlobalVariable.step], FailureHandling.STOP_ON_FAILURE)
//WebUI.callTestCase(findTestCase('LogOutFromSystem'), [:], FailureHandling.STOP_ON_FAILURE)
//directLoanEstablishment.closePage("Direct Loan Establishment")
directLoanEstablishment.closePage("Process Notifications")
WebUI.delay(3)






