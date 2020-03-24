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
import commons.AbstractPage as AbstractPage
import pageObjects.NotificationPage as NotificationPage
AbstractPage abstractPage = new AbstractPage()

NotificationPage notification = new NotificationPage()
DirectLoanEstablishment directLoanEstablishment = new DirectLoanEstablishment()

//Scripting for
//Enroll loan account - Log-in with Teller ->  Ask for Approval -> Log-out from system
GlobalVariable.testCaseID = testCaseNumber
GlobalVariable.sequence = sequence
GlobalVariable.results = ''
GlobalVariable.step = '1'


//Open Direct Loan Establishment
directLoanEstablishment.openDirectLoanEstablishment()
WebUI.delay(2)
//Direct Loan Establishment is displayed
GlobalVariable.results += GlobalVariable.step + ',' + directLoanEstablishment.isDisplayedDirectLoanEstablishment(testCaseNumber, GlobalVariable.step, sequence) + '|'
println GlobalVariable.results
WebUI.delay(2)

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
GlobalVariable.results += GlobalVariable.step + ', PASSED|'
GlobalVariable.step = '3'
WebUI.delay(2)
directLoanEstablishment.clickNext()
WebUI.delay(1)
directLoanEstablishment.clickProceed()

if(directLoanEstablishment.isAskForApproval()){
	directLoanEstablishment.clickAskForApproval(username_teller,password_teller, username_supervisor, password_supervisor)
	notification.clickConfirmed()
}
WebUI.delay(5)
GlobalVariable.results += GlobalVariable.step + ',' + directLoanEstablishment.isScreenDisplayed(testCaseNumber, GlobalVariable.step, 'Disbursement Details', sequence) + '|'

WebUI.delay(2)

GlobalVariable.step = '4'

directLoanEstablishment.inputDisbursementDetails(DisbursDate, DisbursAmount,disbursementMode,disbursementType,disbursTo,DisbursAccountID )
GlobalVariable.results += GlobalVariable.step + ', PASSED|'

GlobalVariable.step = '5'

WebUI.delay(2)
directLoanEstablishment.clickNext()
GlobalVariable.results += GlobalVariable.step + ',' + directLoanEstablishment.isScreenDisplayed(testCaseNumber, GlobalVariable.step, 'Charge Details', sequence) +'|'

WebUI.delay(2)
directLoanEstablishment.clickNext()
GlobalVariable.step = '6'
GlobalVariable.results += (((GlobalVariable.step + ',') + directLoanEstablishment.isScreenDisplayed(testCaseNumber, GlobalVariable.step, 'Loan Repayment Schedule', sequence)) + '|')

WebUI.delay(2)

directLoanEstablishment.clickNext()
GlobalVariable.step = '7'
double prinAmt = directLoanEstablishment.calculateLoanAmount(totalCost,customerContributionPercentage, customerContributionAmount)
GlobalVariable.results += (((GlobalVariable.step + ',') + directLoanEstablishment.verifYLoanInformation(testCaseNumber, GlobalVariable.step, sequence,prinAmt,termOfLoan)) + '|')

//principalAmount = directLoanEstablishment.getPrincipalAmount()

String loanReference = directLoanEstablishment.getLoanReference()
WebUI.delay(3)

//results = ((step + ',') + directLoanEstablishment.verifyDisplayedValue(testCaseNumber, step, temrOfLoan, principalAmount, sequence))


directLoanEstablishment.clickNext()

WebUI.delay(2)
directLoanEstablishment.clickAskForApproval(username_teller,password_teller, username_supervisor, password_supervisor)

WebUI.delay(5)
GlobalVariable.step = '8'

notification.clickConfirmed( testCaseNumber,  GlobalVariable.results,  sequence,  GlobalVariable.step,loanReference,  null)
WebUI.delay(3)
