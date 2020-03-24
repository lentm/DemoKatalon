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

DirectLoanEstablishment directLoanEstablishment = new DirectLoanEstablishment()
//Scripting for AUT-480, AUT-481, AUT-482 and AUT-483

GlobalVariable.sequence = sequence
GlobalVariable.results = ""
GlobalVariable.testCaseID = testCaseNumber
GlobalVariable.no = no

//WebUI.callTestCase(findTestCase('Login'), [('url') : tel_url, ('username') : tel_username, ('password') : tel_password, ('active') : 'Yes'], FailureHandling.STOP_ON_FAILURE)
GlobalVariable.step = '1'
//ResultSet rs = directLoanEstablishment.getResultTestDataFromDB(GlobalVariable.testCaseID)
//rs.next()

//Open Direct Loan Establishment
directLoanEstablishment.openDirectLoanEstablishment()

//Direct Loan Establishment is displayed
GlobalVariable.results = GlobalVariable.step + ',' + directLoanEstablishment.isDisplayedDirectLoanEstablishment(GlobalVariable.testCaseID,GlobalVariable.step, GlobalVariable.sequence) + '|'
WebUI.delay(5)
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
directLoanEstablishment.clickEnterDisbursementDetails(isDisbursementDetails)
directLoanEstablishment.clickNext()
/*Asking for limit---begin
directLoanEstablishment.clickAskForApproval()
WebUI.callTestCase(findTestCase('LogOutFromSystem'), [:], FailureHandling.STOP_ON_FAILURE)
WebUI.callTestCase(findTestCase('ApprovalProcess'), [('description') : 'Referral', ('sup_url') : app_url, ('sup_username') : app_username
	, ('sup_password') : app_password], FailureHandling.STOP_ON_FAILURE)
confirm and continue process
WebUI.callTestCase(findTestCase('Confirm_Limit'), [('description') : 'Approval', ('tel_url') : 'http://172.24.197.11:9082/uxp/rt/html/login.html?locale=en-gb'
	, ('tel_username') : 'BR3USER048', ('tel_password') : '@dmin@123', ('testCaseNumber') : '', ('results') : '', ('loanRef') : 'None'
	, ('step') : ''], FailureHandling.STOP_ON_FAILURE)

Asking for limit---end*/
GlobalVariable.results += GlobalVariable.step + ", PASSED|"

GlobalVariable.step="3"
/**Disbursement Details is displayed
 * Enter Disbursement Details
 * 424: one time
 */
//verify Disbursement Details is displayed
GlobalVariable.results += GlobalVariable.step + "," +directLoanEstablishment.isScreenDisplayed(GlobalVariable.testCaseID,GlobalVariable.step,"Disbursement Details", GlobalVariable.sequence) +"|"
//Enter Disbursement Details
directLoanEstablishment.inputDisbursementDetails(disbursementDate, disbursementAmount, disbursementMode, disbursementType, disbursementTo, disburseAccount)
directLoanEstablishment.clickNext()
WebUI.delay(5)

GlobalVariable.step="4"
/**Verify Total Charge and Tax: 40.000MMK
 * */
GlobalVariable.results += GlobalVariable.step + "," + directLoanEstablishment.verifyTotalChargeAmount(GlobalVariable.testCaseID, GlobalVariable.step, GlobalVariable.sequence, chargeAmount)+ "|"

GlobalVariable.step="5"
/**
 * 1. Schedule gird is displayed
 * 2. The first row is displayed: Disbursement Date and Disbursement Amount
 * 3. Loan Repayment is displayed
 * */
WebUI.delay(2)
directLoanEstablishment.clickNext()
WebUI.delay(5)

GlobalVariable.step="6"
/**
 * 1. Loan Status: Normal
 * 2. Account Number is displayed
 * 3. Sub-Product
 * 4. Loan Amount
 * 5. Loan Term
 * 6. Rate of Interest*/
WebUI.delay(2)
//go to Loan Review page
directLoanEstablishment.clickNext()
//get the Loan reference for inserting into DB
String loanRef = directLoanEstablishment.getLoanReference()
//asking for approval
WebUI.delay(2)
directLoanEstablishment.clickNext()
directLoanEstablishment.clickAskForApproval()

//log out teller user
WebUI.delay(2)
WebUI.callTestCase(findTestCase('LogOutFromSystem'), [:], FailureHandling.STOP_ON_FAILURE)

//Supervisor approve - Log-in with Supervisor -> Approve -> Log-out from system
WebUI.callTestCase(findTestCase('ApprovalProcess'), [('description') : 'Referral', ('sup_url') : app_url, ('sup_username') : app_username
	, ('sup_password') : app_password], FailureHandling.STOP_ON_FAILURE)

//Teller ask for fircosoft approval - Login with Teller -> asking for fircosoft approval -> Logout
WebUI.callTestCase(findTestCase('ConfirmProcess'), [('description') : 'Approval', ('tel_url') : tel_url
        , ('tel_username') : tel_username, ('tel_password') : tel_password, ('testCaseNumber') : GlobalVariable.testCaseID, ('results') : results, ('sequence') : GlobalVariable.sequence
        , ('loanRef') : loanRef, ('loanAmount') : loanAmount, ('loanStatus') : loanStatus, ('rateInterest') :rateInterest, ('subProduct') : subProduct, ('loanTerm') : termOfLoan
        , ('loanFrequence') : termOfLoanFrequency, ('step') :GlobalVariable.step], FailureHandling.STOP_ON_FAILURE)
//WebUI.callTestCase(findTestCase('LogOutFromSystem'), [:], FailureHandling.STOP_ON_FAILURE)

directLoanEstablishment.closePage("Process Notifications")
WebUI.delay(3)





