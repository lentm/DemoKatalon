package pageObjects

import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.checkpoint.Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testcase.TestCase
import com.kms.katalon.core.testdata.TestData
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import commons.AbstractPage
import org.openqa.selenium.Keys
import java.text.SimpleDateFormat
import org.openqa.selenium.WebElement
import org.testng.Assert
import com.kms.katalon.core.webui.common.WebUiCommonHelper
import org.openqa.selenium.StaleElementReferenceException
import com.kms.katalon.core.webui.driver.DriverFactory
import java.sql.*
import javax.money.*

import internal.GlobalVariable
import jcifs.dcerpc.msrpc.samr

public class DirectLoanEstablishment {

	AbstractPage abstractPage = new AbstractPage()
	int shorttime = 2
	int medtime = 5
	int logtime=7
	Statement stmt
	//	String userDir = System.getProperty("user.dir");

	public openDirectLoanEstablishment(){
		abstractPage.inputOpenScreenMenuSearch("Direct Loan Establishment")
	}
	public void inputCustomerNumber (String customernumber){
		TestObject to = findTestObject('Object Repository/DirectLoanEstablishment/LoanEstablishment/input_CustomerNumber')
		abstractPage.waitForElementVisibled(to )
		abstractPage.sendkeyToElement(to, customernumber + Keys.chord(Keys.TAB))
	}
	public void inputDealerID (String dealerID){
		TestObject to = findTestObject('Object Repository/DirectLoanEstablishment/LoanEstablishment/input_DealerID')
		if(!dealerID.trim().equals("")){
			abstractPage.waitForElementVisibled(to)
			abstractPage.sendkeyToElement(to, dealerID + Keys.chord(Keys.TAB))
		}

	}
	public void inputLoanPurpose (String loanPurpose) {
		TestObject to = findTestObject('Object Repository/DirectLoanEstablishment/LoanEstablishment/input_LoanPurpose')
		abstractPage.waitForElementVisibled(to)
		abstractPage.sendkeyToElement(to, loanPurpose + Keys.chord(Keys.ENTER))
		WebUI.delay(shorttime)
	}
	public void inputCurrency (String currency){
		TestObject to = findTestObject('Object Repository/DirectLoanEstablishment/LoanEstablishment/input_Currency')

		abstractPage.waitForElementVisibled(to)
		abstractPage.clickToElement(to)
		WebUI.delay(shorttime)
		abstractPage.sendkeyToElement(to, currency + Keys.chord(Keys.ENTER))
		WebUI.delay(shorttime)
	}
	public void inputSubproduct (String subproduct){
		TestObject to = findTestObject('Object Repository/DirectLoanEstablishment/LoanEstablishment/input_Sub-Product')

		abstractPage.waitForElementVisibled(to)
		abstractPage.clickToElement(to)
		abstractPage.sendkeyToElement(to, subproduct)
		WebUI.delay(shorttime)
	}
	public void checkRepaymentOnLastMonth(String repaymentOnLastMonth) {
		TestObject to = findTestObject('Object Repository/DirectLoanEstablishment/LoanEstablishment/tick_RepaymentonLastDayofMonth')

		if (repaymentOnLastMonth.toUpperCase().trim().equals('YES')){
			abstractPage.waitForElementVisibled(to)
			abstractPage.checkTheCheckbox(to)
		}
		else {
			abstractPage.uncheckTheCheckbox(to)
		}
	}
	public void inputTotalCost (String totalcost){
		TestObject to = findTestObject('Object Repository/DirectLoanEstablishment/LoanEstablishment/input_TotalCost')
		abstractPage.waitForElementVisibled(to)
		abstractPage.sendkeyToElement(to, Keys.chord(Keys.ARROW_UP) + Keys.chord(Keys.ARROW_RIGHT) + totalcost)
		WebUI.delay(shorttime)

	}

	public void inputCustomerContribution (String contributionPercentage, String contributionAmount) {
		//process for Customer Contribution % and Customer Contribution fields, if percentage and amount entered, percentage will be priority
		TestObject cusContributionPercentage = findTestObject('DirectLoanEstablishment/LoanEstablishment/input_CustomerContributionPercentage')
		TestObject cusContributionAmount = findTestObject('Object Repository/DirectLoanEstablishment/LoanEstablishment/input_CustomerContributionAmount')
		if(!contributionPercentage.equals("") && !contributionAmount.equals("")){
			//Percentage is priority
			abstractPage.waitForElementVisibled(cusContributionPercentage)
			abstractPage.clearText(cusContributionPercentage)
			abstractPage.sendkeyToElement(cusContributionPercentage , contributionPercentage + Keys.chord(Keys.TAB))
		}
		else {
			if(!contributionAmount.equals("")){
				//Amount
				abstractPage.waitForElementVisibled(cusContributionAmount)
				abstractPage.clearText(cusContributionAmount)
				abstractPage.sendkeyToElement(cusContributionAmount, contributionAmount + Keys.chord(Keys.TAB))
			}else {
				//Percentage
				abstractPage.waitForElementVisibled(cusContributionPercentage)
				abstractPage.clearText(cusContributionPercentage)
				abstractPage.sendkeyToElement(cusContributionPercentage, contributionPercentage + Keys.chord(Keys.TAB))
			}
		}

	}

	public void inputContractDate (String contractDate){
		//			println "contractdate"+contractDate
		//			Date date2 = new SimpleDateFormat("MM/dd/yyyy").parse(contractDate);
		//			SimpleDateFormat fm = new SimpleDateFormat("yyyy-MM-dd");
		//			contractDate = fm.format(date2);
		TestObject to =findTestObject('Object Repository/DirectLoanEstablishment/LoanEstablishment/input_ContractDate')
		if(!contractDate.equals("")){
			abstractPage.waitForElementVisibled(to)
			abstractPage.clearText(to)
			abstractPage.sendkeyToElement(to, contractDate)
		}
	}
	public void inputTermOfLoan (String termOfLoan) {
		TestObject to = findTestObject('Object Repository/DirectLoanEstablishment/LoanEstablishment/input_TermofLoan')

		abstractPage.waitForElementVisibled(to)
		abstractPage.clearText(to)
		abstractPage.sendkeyToElement(to, termOfLoan)

	}

	public void inputTermOfLoanFrequency (String termOfLoanFrequency) {
		TestObject to = findTestObject('Object Repository/DirectLoanEstablishment/LoanEstablishment/input_TermofLoan(choose)')
		abstractPage.waitForElementPresent(to)
		abstractPage.sendkeyToElement(to, termOfLoanFrequency.trim() +Keys.chord(Keys.ENTER))

	}

	public void inputMaturityDateCalculation (String maturitydatecalculation) {
		TestObject to = findTestObject('Object Repository/DirectLoanEstablishment/LoanEstablishment/input_MaturityDateCalculation')
		if (!maturitydatecalculation.trim().equals('')) {
			abstractPage.waitForElementPresent(to)
			abstractPage.sendkeyToElement(to, maturitydatecalculation +Keys.chord(Keys.ENTER))
		}
	}
	public void inputmaturitydate (String maturitydate, String maturitydatecalculation) {
		if (maturitydatecalculation.trim().equals('Manual') ) {
			println "maturitydate"+maturitydate
			Date date = new SimpleDateFormat("MM/dd/yy").parse(maturitydate);
			SimpleDateFormat fm = new SimpleDateFormat("yyyy-MM-dd");
			maturitydate = fm.format(date);
			println date
			abstractPage.waitForElementVisibled(findTestObject('Object Repository/DirectLoanEstablishment/LoanEstablishment/input_maturitydate'))
			WebUI.setText(findTestObject('Object Repository/DirectLoanEstablishment/LoanEstablishment/input_maturitydate'), maturitydate)
		}
		else {
			abstractPage.waitForElementPresent(findTestObject('Object Repository/DirectLoanEstablishment/LoanEstablishment/input_maturitydate'))
		}
	}
	//	public void inputeffectiveinterestrate (String effectiveinterestrate) {
	//		abstractPage.waitForElementVisibled(findTestObject('Object Repository/DirectLoanEstablishment/LoanEstablishment/input_EffectiveInterestRateType'))
	//		abstractPage.sendkeyToElement(findTestObject('Object Repository/DirectLoanEstablishment/LoanEstablishment/input_EffectiveInterestRateType'), effectiveinterestrate)
	//		abstractPage.sendkeyToElement(findTestObject('Object Repository/DirectLoanEstablishment/LoanEstablishment/input_EffectiveInterestRateType'), Keys.chord(Keys.ENTER)+ Keys.chord(Keys.TAB))
	//	}
	//	public void inputeffectiveinterestrate1 (String effectiveinterestrate1) {
	//		abstractPage.waitForElementVisibled(findTestObject('DirectLoanEstablishment/LoanEstablishment/input_EffectiveInterestRateTypeDetails'))
	//		abstractPage.sendkeyToElement(findTestObject('DirectLoanEstablishment/LoanEstablishment/input_EffectiveInterestRateTypeDetails'), effectiveinterestrate1)
	//	}
	//	public void inputcustomermargin (String customermargin) {
	//		abstractPage.waitForElementVisibled(findTestObject('Object Repository/DirectLoanEstablishment/LoanEstablishment/input_CustomerMargin'))
	//		abstractPage.clearText(findTestObject('Object Repository/DirectLoanEstablishment/LoanEstablishment/input_CustomerMargin'))
	//		abstractPage.sendkeyToElement(findTestObject('Object Repository/DirectLoanEstablishment/LoanEstablishment/input_CustomerMargin'), customermargin)
	//	}
	//	public void inputresetfrequency (String resetfrequency) {
	//		abstractPage.waitForElementVisibled(findTestObject('Object Repository/DirectLoanEstablishment/LoanEstablishment/input_ResetFrequency'))
	//		abstractPage.sendkeyToElement(findTestObject('Object Repository/DirectLoanEstablishment/LoanEstablishment/input_ResetFrequency'), resetfrequency)
	//	}
	//	public void inputresetdate (String resetdate) {
	//		abstractPage.waitForElementVisibled(findTestObject('Object Repository/DirectLoanEstablishment/LoanEstablishment/input_ResetDate'))
	//		abstractPage.sendkeyToElement(findTestObject('Object Repository/DirectLoanEstablishment/LoanEstablishment/input_ResetDate'), resetdate)
	//	}
	//	public void inputsettlementtype (String settlementtype) {
	//		abstractPage.waitForElementVisibled(findTestObject('Object Repository/DirectLoanEstablishment/LoanEstablishment/input_SettlementType'))
	//		abstractPage.sendkeyToElement(findTestObject('Object Repository/DirectLoanEstablishment/LoanEstablishment/input_SettlementType'), settlementtype +Keys.chord(Keys.ENTER))
	//	}
	public void inputChargeFundingAccount (String chargefundingaccount) {
		TestObject to = findTestObject('Object Repository/DirectLoanEstablishment/LoanEstablishment/input_ChargeFundingAccount')
		abstractPage.waitForElementVisibled(to)
		abstractPage.clearText(to)
		abstractPage.sendkeyToElement(to, chargefundingaccount)
	}

	public void clickEnterDisbursementDetails (String isDisbursementDetails) {
		TestObject to = findTestObject('Object Repository/DirectLoanEstablishment/LoanEstablishment/chk_EnterDisbursementDetails')
		if(isDisbursementDetails.trim().toUpperCase().equals("TRUE")){
			abstractPage
			abstractPage.checkTheCheckbox(to)
		}else
		{
			abstractPage.uncheckTheCheckbox(to)
		}

	}


	//	public void inputindustry (String industry) {
	//		abstractPage.waitForElementVisibled(findTestObject('Object Repository/DirectLoanEstablishment/LoanEstablishment/input_Industry'))
	//		abstractPage.sendkeyToElement(findTestObject('Object Repository/DirectLoanEstablishment/LoanEstablishment/input_Industry'), industry +Keys.chord(Keys.ENTER))
	//	}
	public void inputRepaymentFrequency (String repaymentFrequency) {
		TestObject to = findTestObject('Object Repository/DirectLoanEstablishment/LoanEstablishment/input_RepaymentFrequency')
		abstractPage.waitForElementVisibled(to)
		abstractPage.sendkeyToElement(to, repaymentFrequency +Keys.chord(Keys.TAB))

	}
	public void inputRepaymentStartDate (String repaymentstartdate) {
		TestObject to = findTestObject('Object Repository/DirectLoanEstablishment/LoanEstablishment/input_RepaymentStartDate')
		if (!repaymentstartdate.trim().equals('')){
			//			println "repaymentstartdate"+repaymentstartdate
			//			Date date1 = new SimpleDateFormat("MM/dd/yy").parse(repaymentstartdate);
			//			SimpleDateFormat fm = new SimpleDateFormat("yyyy-MM-dd");
			//			repaymentstartdate = fm.format(date1);
			//			println date1
			abstractPage.waitForElementVisibled(to)
			abstractPage.sendkeyToElement(to, repaymentstartdate)
		}
	}

	public String getIsInterestRepaymentFrequencyFromLoanVariation(String subProduct){
		Connection con = abstractPage.getSQLServerconnection("TestData_UATA")
		try{
			stmt = con.createStatement()
			String sql = "SELECT * FROM OPENQUERY([UATA], 'SELECT ISINTERESTNREPAYFREQ FROM WASADMIN.LOANVARIATIONS where UBTYPE=''" + subProduct.trim() +  "''')"
			ResultSet rs = stmt.executeQuery(sql)
			String isInterestRepayFreq = rs.getString("ISINTERESTNREPAYFREQ")// get the interest repayment frequency
			println "isInterestRepayFreq: " + isInterestRepayFreq
			return isInterestRepayFreq
		}
		catch (Exception e){
			println (e)
		}
	}

	public void inputInterestFrequency (String interestFrequency, String subProduct) {
		TestObject to = findTestObject('Object Repository/DirectLoanEstablishment/LoanEstablishment/input_Interestfrequency')
		if(getIsInterestRepaymentFrequencyFromLoanVariation(subProduct).equals("N")){
			abstractPage.waitForElementVisibled(to)
			abstractPage.sendkeyToElement(to, interestFrequency)
		}
	}

	public void inputInterestRepaymentStartDate(String interestRepaymentStartDate, String subProduct){
		TestObject to =findTestObject('Object Repository/DirectLoanEstablishment/LoanEstablishment/date_InterestRepaymentStartDate')

		if(getIsInterestRepaymentFrequencyFromLoanVariation(subProduct).equals("N")){
			abstractPage.waitForElementVisibled(to)
			abstractPage.sendkeyToElement(to, interestRepaymentStartDate)
		}
	}

	public void inputMoratoriumType (String moratoriumType) {
		TestObject to = findTestObject('Object Repository/DirectLoanEstablishment/LoanEstablishment/input_MoratoriumType')
		if(!moratoriumType.trim().equals("")){
			abstractPage.waitForElementVisibled(to)
			abstractPage.sendkeyToElement(to, moratoriumType + Keys.chord(Keys.TAB))
		}
	}
	public void inputMoratoriumPeriod (String moratoriumPeriod) {
		TestObject to = findTestObject('Object Repository/DirectLoanEstablishment/LoanEstablishment/input_MoratoriumPeriod')
		if (!moratoriumPeriod.trim().equals("")) {
			abstractPage.waitForElementVisibled(to)
			abstractPage.sendkeyToElement(to, moratoriumPeriod + Keys.chord(Keys.TAB))
		}

	}
	//	public void inputCollectionOfMoratorium (String collectionofmoratorium) {
	//		TestObject to = findTestObject('Object Repository/DirectLoanEstablishment/LoanEstablishment/input_CollectionofMoratoriumPeriodInterest')
	//		try {
	//			if (!collectionofmoratorium.trim().equals('')) {
	//				abstractPage.waitForElementVisibled(to)
	//				abstractPage.sendkeyToElement(to, collectionofmoratorium)
	//			}
	//		}
	//		catch(Exception e){
	//			println(e)
	//		}
	//	}

	public void inputEffectiveInterestRateType(String interetsRateType, String interesDetails){
		TestObject to_interestRateType = findTestObject('Object Repository/DirectLoanEstablishment/LoanEstablishment/input_EffectiveInterestRateType')
		TestObject to_interestRateTypeDetails =findTestObject('Object Repository/DirectLoanEstablishment/LoanEstablishment/input_EffectiveInterestRateTypeDetails')
		abstractPage.waitForElementVisibled(to_interestRateType)
		abstractPage.sendkeyToElement(to_interestRateType, interetsRateType + Keys.chord(Keys.TAB))
		abstractPage.waitForElementVisibled(to_interestRateTypeDetails)
		abstractPage.sendkeyToElement(to_interestRateTypeDetails, interesDetails  + Keys.chord(Keys.TAB))
	}

	public void inputSettlementType(String settlementType){
		TestObject to = findTestObject('Object Repository/DirectLoanEstablishment/LoanEstablishment/input_SettlementType')
		abstractPage.waitForElementVisibled(to)
		abstractPage.sendkeyToElement(to, settlementType + Keys.chord(Keys.TAB))

	}

	public void inputSettlementAccount (String settlementaccount, String settlementtype) {
		if (settlementtype.trim().equals('Automatic Debit')) {
			abstractPage.waitForElementVisibled(findTestObject('Object Repository/DirectLoanEstablishment/LoanEstablishment/input_SettlementAccount'))
			WebUI.setText(findTestObject('Object Repository/DirectLoanEstablishment/LoanEstablishment/input_SettlementAccount'), settlementaccount)
		}
		//		else {
		//			abstractPage.waitForElementPresent(findTestObject('Object Repository/DirectLoanEstablishment/LoanEstablishment/input_SettlementAccount'))
		//		}
	}

	//	public void inputnote (String note) {
	//		abstractPage.waitForElementVisibled(findTestObject('Object Repository/DirectLoanEstablishment/LoanEstablishment/input_Notes'))
	//		abstractPage.sendkeyToElement(findTestObject('Object Repository/DirectLoanEstablishment/LoanEstablishment/input_Notes'), note)
	//	}
	//	public void tickinterest() {
	//		abstractPage.waitForElementVisibled(findTestObject('Object Repository/DirectLoanEstablishment/LoanEstablishment/tick_Interest'))
	//		abstractPage.clickToElement(findTestObject('Object Repository/DirectLoanEstablishment/LoanEstablishment/tick_Interest'))
	//	}
	public void clickNext() {
		abstractPage.clickNext()
	}
	//	public void checkotherbank(String otherbank, String settlementtype){
	//		if (settlementtype.trim().equals('Automatic Debit') && (otherbank.trim().equals('yes'))){
	//			abstractPage.waitForElementVisibled(findTestObject('Object Repository/DirectLoanEstablishment/LoanEstablishment/tick_OtherBank'))
	//			abstractPage.checkTheCheckbox(findTestObject('Object Repository/DirectLoanEstablishment/LoanEstablishment/tick_OtherBank'))
	//		}
	//		else if (otherbank.trim().equals('no')) {
	//			abstractPage.uncheckTheCheckbox(findTestObject('Object Repository/DirectLoanEstablishment/LoanEstablishment/tick_OtherBank'))
	//		}
	//	}

	//	public void clickproceed(){
	//		abstractPage.waitForElementVisibled(findTestObject('Object Repository/DirectLoanEstablishment/LoanEstablishment/btn_Proceed'))
	//		abstractPage.clickToElement(findTestObject('Object Repository/DirectLoanEstablishment/LoanEstablishment/btn_Proceed'))
	//	}
	//	public void clickrelateditems(){
	//		try {
	//			abstractPage.waitForElementVisibled(findTestObject('Object Repository/DirectLoanEstablishment/LoanEstablishment/click_RelatedItems'))
	//			abstractPage.clickToElement(findTestObject('Object Repository/DirectLoanEstablishment/LoanEstablishment/click_RelatedItems'))
	//		}
	//		catch(Exception e){
	//			println(e)
	//		}
	//	}
	/**
	 * Disbursement----Begin*/
	public void inputdisbursementdate (String disbursementdate) {
		println "disbursementdate"+disbursementdate
		Date date2 = new SimpleDateFormat("MM/dd/yy").parse(disbursementdate);
		SimpleDateFormat fm = new SimpleDateFormat("yyyy-MM-dd");
		disbursementdate = fm.format(date2);
		println date2
		WebUI.delay(shorttime)
		abstractPage.waitForElementPresent(findTestObject('Object Repository/DirectLoanEstablishment/DisbursementDetails/input_DisbursementDate'))
		//abstractPage.clearText(findTestObject('Object Repository/DirectLoanEstablishment/DisbursementDetails/input_DisbursementDate'))
		//abstractPage.setAttributeInDOM(findTestObject('Object Repository/DirectLoanEstablishment/DisbursementDetails/input_DisbursementDate'),'value', disbursementdate)
		//WebUI.setText(findTestObject('Object Repository/DirectLoanEstablishment/DisbursementDetails/input_DisbursementDate'), disbursementdate)
		WebUI.delay(shorttime)
		WebUI.sendKeys(findTestObject('Object Repository/DirectLoanEstablishment/DisbursementDetails/input_DisbursementDate'), disbursementdate)
	}

	public void inputDisbursementDetails (String disbursementDate, String disbursementAmount, String disbursementMode, String disbursementType, String disbursementTo, String disburseAccountID) {
		TestObject to_disbursementMode = findTestObject('Object Repository/DirectLoanEstablishment/DisbursementDetails/input_DisbursementMode')
		TestObject to_disbursementType = findTestObject('Object Repository/DirectLoanEstablishment/DisbursementDetails/input_DisbursementType')
		TestObject to_disbursementAmount = findTestObject('Object Repository/DirectLoanEstablishment/DisbursementDetails/txt_DisbursementAmount')
		TestObject to_disbursementDate = findTestObject('Object Repository/DirectLoanEstablishment/DisbursementDetails/txt_DisbursementDate')
		TestObject to_disbursementTo = findTestObject('Object Repository/DirectLoanEstablishment/DisbursementDetails/txt_DisbursementTo')
		TestObject to_ScreenName = findTestObject('Object Repository/DirectLoanEstablishment/DisbursementDetails/lbl_CustomerID')
		TestObject to_disburseAccountId = findTestObject('Object Repository/DirectLoanEstablishment/DisbursementDetails/input_DisburseAccountId')
		String [] listDisbursementMode = abstractPage.splitString(disbursementMode)
		for(int i =0; i< listDisbursementMode.length ; i++){
			String str_disbursementDate = abstractPage.replaceArrayIndexOutOfBoundsException(disbursementDate, i,"")
			String str_disbursementType = abstractPage.replaceArrayIndexOutOfBoundsException(disbursementType, i,"")
			String str_disbursementTo = abstractPage.replaceArrayIndexOutOfBoundsException(disbursementTo, i,"")
			String str_disbursementAmount = abstractPage.replaceArrayIndexOutOfBoundsException(disbursementAmount, i,"")
			String str_disbursementAccountID = abstractPage.replaceArrayIndexOutOfBoundsException(disburseAccountID, i,"")

			//disbursement date
			abstractPage.waitForElementVisibled(to_disbursementDate)
			if(!str_disbursementDate.equals("")){
				abstractPage.scrollDownDropDownList(to_ScreenName)
				abstractPage.clearText(to_disbursementDate)
				abstractPage.sendkeyToElement(to_disbursementDate, str_disbursementDate +Keys.chord(Keys.TAB))
			}else{
				abstractPage.scrollDownDropDownList(to_ScreenName)
				abstractPage.clickToElement(to_disbursementDate)
				abstractPage.sendkeyToElement(to_disbursementDate, Keys.chord(Keys.TAB))
			}

			WebUI.delay(shorttime)
			//disbursement amount - apply one time and multiple
			if(WebUI.waitForElementVisible(to_disbursementAmount, i, FailureHandling.OPTIONAL)){
				if(!str_disbursementAmount.trim().equals("")){
					abstractPage.clearText(to_disbursementAmount)
					//abstractPage.clickToElement(to_disbursementAmount)
					println("str_disbursementAmount: "+str_disbursementAmount)
					//abstractPage.sendkeyToElement(to_disbursementAmount,"0")
					abstractPage.sendkeyToElement(to_disbursementAmount,Keys.chord(Keys.ARROW_UP) + Keys.chord(Keys.ARROW_RIGHT)+ str_disbursementAmount +Keys.chord(Keys.TAB))
				}
			}

			//disbursement mode
			abstractPage.waitForElementVisibled(to_disbursementMode)
			abstractPage.clearText(to_disbursementMode)
			abstractPage.sendkeyToElement(to_disbursementMode, listDisbursementMode[i].toString().trim() +Keys.chord(Keys.TAB))

			//disbursement type
			abstractPage.waitForElementVisibled(to_disbursementType)
			abstractPage.clearText(to_disbursementType)
			abstractPage.sendkeyToElement(to_disbursementType, str_disbursementType +Keys.chord(Keys.TAB))

			//disbursement to
			if(!str_disbursementTo.trim().equals("")){
				abstractPage.waitForElementVisibled(to_disbursementTo)
				abstractPage.clearText(to_disbursementTo)
				abstractPage.sendkeyToElement(to_disbursementTo, str_disbursementTo +Keys.chord(Keys.TAB))
			}
			//disbursement account id
			if(!str_disbursementAccountID.trim().equals("")){
				abstractPage.waitForElementVisibled(to_disburseAccountId)
				abstractPage.clearText(to_disburseAccountId)
				abstractPage.sendkeyToElement(to_disburseAccountId, str_disbursementAccountID +Keys.chord(Keys.TAB))
			}
			
			clickAdd()
		}

	}

	//	public void inputDisbursementMode (String disbursementmode) {
	//		TestObject to = findTestObject('Object Repository/DirectLoanEstablishment/DisbursementDetails/input_DisbursementMode')
	//		try{
	//			abstractPage.waitForElementVisibled(to)
	//			abstractPage.sendkeyToElement(to, disbursementmode)
	//		}
	//		catch(Exception e){
	//			println(e)
	//		}
	//	}
	//	public void inputDisbursementType (String disbursementtype) {
	//		TestObject to = findTestObject('Object Repository/DirectLoanEstablishment/DisbursementDetails/input_DisbursementType')
	//		try{
	//			abstractPage.waitForElementVisibled(to)
	//			abstractPage.sendkeyToElement(to, disbursementtype)
	//		}
	//		catch(Exception e){
	//			println(e)
	//		}
	//	}

	public void inputPaymenttype (String paymenttype, String disbursementmode) {
		if (disbursementmode.trim().equals('Direct Credit')) {
			abstractPage.waitForElementVisibled(findTestObject('Object Repository/DirectLoanEstablishment/DisbursementDetails/input_Paymenttype'))
			abstractPage.sendkeyToElement(findTestObject('Object Repository/DirectLoanEstablishment/DisbursementDetails/input_Paymenttype'), paymenttype)
		}
		else if (!disbursementmode.trim().equals('Direct Credit')) {
			abstractPage.waitForElementPresent(findTestObject('Object Repository/DirectLoanEstablishment/DisbursementDetails/input_Paymenttype'))
		}
	}
	//	public void inputPaymentsystem (String paymentsystem, String disbursementmode) {
	//		if (disbursementmode.trim().equals('Direct Credit')){
	//			abstractPage.waitForElementVisibled(findTestObject('Object Repository/DirectLoanEstablishment/DisbursementDetails/input_Paymentsystem'))
	//			abstractPage.sendkeyToElement(findTestObject('Object Repository/DirectLoanEstablishment/DisbursementDetails/input_Paymentsystem'), paymentsystem)
	//		}
	//		else if (!disbursementmode.trim().equals('Direct Credit')) {
	//			abstractPage.waitForElementPresent(findTestObject('Object Repository/DirectLoanEstablishment/DisbursementDetails/input_Paymentsystem'))
	//		}
	//	}
	//	public void inputPaymentinternalreference (String paymentinternalreferenc, String disbursementmode) {
	//		if (disbursementmode.trim().equals('Direct Credit')) {
	//			abstractPage.waitForElementVisibled(findTestObject('Object Repository/DirectLoanEstablishment/DisbursementDetails/input_Paymentinternalreference'))
	//			abstractPage.sendkeyToElement(findTestObject('Object Repository/DirectLoanEstablishment/DisbursementDetails/input_Paymentinternalreference'), paymentinternalreferenc)
	//		}
	//		else if (!disbursementmode.trim().equals('Direct Credit')) {
	//			abstractPage.waitForElementPresent(findTestObject('Object Repository/DirectLoanEstablishment/DisbursementDetails/input_Paymentinternalreference'))
	//		}
	//	}
	//	public void inputSTIpaymentmethod (String STIpaymentmethod) {
	//		abstractPage.waitForElementVisibled(findTestObject('Object Repository/DirectLoanEstablishment/DisbursementDetails/input_STIPaymentMethod'))
	//		abstractPage.sendkeyToElement(findTestObject('Object Repository/DirectLoanEstablishment/DisbursementDetails/input_STIPaymentMethod'), STIpaymentmethod)
	//	}
	//	public void inputSTIamount (String STIamount) {
	//		abstractPage.waitForElementVisibled(findTestObject('Object Repository/DirectLoanEstablishment/DisbursementDetails/input_STIAmount'))
	//		abstractPage.clearText(findTestObject('Object Repository/DirectLoanEstablishment/DisbursementDetails/input_STIAmount'))
	//		abstractPage.sendkeyToElement(findTestObject('Object Repository/DirectLoanEstablishment/DisbursementDetails/input_STIAmount'), STIamount)
	//		abstractPage.sendkeyToElement(findTestObject('Object Repository/DirectLoanEstablishment/DisbursementDetails/input_STIAmount'), Keys.chord(Keys.ARROW_UP)+ Keys.chord(Keys.ARROW_RIGHT))
	//	}
	//	public void inputASTIpaymentmethod (String ASTIpaymentmethod) {
	//		abstractPage.waitForElementVisibled(findTestObject('Object Repository/DirectLoanEstablishment/DisbursementDetails/input_ASTIPaymentMethod'))
	//		abstractPage.sendkeyToElement(findTestObject('Object Repository/DirectLoanEstablishment/DisbursementDetails/input_ASTIPaymentMethod'), ASTIpaymentmethod)
	//	}
	//	public void inputASTIamount (String ASTIamount) {
	//		abstractPage.waitForElementVisibled(findTestObject('Object Repository/DirectLoanEstablishment/DisbursementDetails/input_ASTIAmount'))
	//		abstractPage.clearText(findTestObject('Object Repository/DirectLoanEstablishment/DisbursementDetails/input_ASTIAmount'))
	//		abstractPage.sendkeyToElement(findTestObject('Object Repository/DirectLoanEstablishment/DisbursementDetails/input_ASTIAmount'), ASTIamount)
	//		abstractPage.sendkeyToElement(findTestObject('Object Repository/DirectLoanEstablishment/DisbursementDetails/input_ASTIAmount'), Keys.chord(Keys.ARROW_UP)+ Keys.chord(Keys.ARROW_RIGHT))
	//	}
	//	public void checkLTIonoutstandingbalance (String onoutstandingbalance) {
	//		if (onoutstandingbalance.trim().equals('yes')){
	//			abstractPage.waitForElementVisibled(findTestObject('Object Repository/DirectLoanEstablishment/DisbursementDetails/check_LTIonOutstandingBalance'))
	//			abstractPage.checkTheCheckbox(findTestObject('Object Repository/DirectLoanEstablishment/DisbursementDetails/check_LTIonOutstandingBalance'))
	//		}
	//		else if (onoutstandingbalance.trim().equals('no')) {
	//			abstractPage.waitForElementVisibled(findTestObject('Object Repository/DirectLoanEstablishment/DisbursementDetails/check_LTIonOutstandingBalance'))
	//			abstractPage.uncheckTheCheckbox(findTestObject('Object Repository/DirectLoanEstablishment/DisbursementDetails/check_LTIonOutstandingBalance'))
	//		}
	//	}
	//	public void inputLTIamount (String LTIamount, String onoutstandingbalance) {
	//		if (onoutstandingbalance.trim().equals('yes')){
	//			abstractPage.waitForElementPresent(findTestObject('Object Repository/DirectLoanEstablishment/DisbursementDetails/input_LTIAmount'))
	//		}
	//		else {
	//			abstractPage.waitForElementVisibled(findTestObject('Object Repository/DirectLoanEstablishment/DisbursementDetails/input_LTIAmount'))
	//			abstractPage.clearText(findTestObject('Object Repository/DirectLoanEstablishment/DisbursementDetails/input_LTIAmount'))
	//			abstractPage.sendkeyToElement(findTestObject('Object Repository/DirectLoanEstablishment/DisbursementDetails/input_LTIAmount'), LTIamount)
	//			abstractPage.sendkeyToElement(findTestObject('Object Repository/DirectLoanEstablishment/DisbursementDetails/input_LTIAmount'), Keys.chord(Keys.ARROW_UP)+ Keys.chord(Keys.ARROW_RIGHT))
	//		}
	//	}
	public void clickAdd() {
		TestObject btn_Add = findTestObject('Object Repository/DirectLoanEstablishment/DisbursementDetails/btn_Add')
		TestObject title_Errors = findTestObject('Object Repository/DirectLoanEstablishment/DisbursementDetails/Errors/title_Errors')
		TestObject context_Errors = findTestObject('Object Repository/DirectLoanEstablishment/DisbursementDetails/Errors/context_Errors')
		abstractPage.waitForElementVisibled(btn_Add)
		abstractPage.clickToElement(btn_Add)
		if(WebUI.verifyElementPresent(title_Errors, medtime, FailureHandling.OPTIONAL)){
			println "clickAdd: "+ abstractPage.getAttributeValue(context_Errors, "value")
		}
	}

	//	public void clickAmend() {
	//		TestObject to = findTestObject('Object Repository/DirectLoanEstablishment/DisbursementDetails/btn_Amend')
	//			abstractPage.waitForElementVisibled(to)
	//			abstractPage.clickToElement(to)
	//
	//	}

	//	public void clickDelete() {
	//		TestObject to = findTestObject('Object Repository/DirectLoanEstablishment/DisbursementDetails/btn_Delete')
	//			abstractPage.waitForElementVisibled(to)
	//			abstractPage.clickToElement(to)
	//	}

	/*public void inputautopopulationmethod (String autopopulationmethod) {
	 abstractPage.waitForElementVisibled(findTestObject('Object Repository/DirectLoanEstablishment/input_AutoPopulationMethod'))
	 WebUI.delay(shorttime)
	 abstractPage.sendkeyToElement(findTestObject('Object Repository/DirectLoanEstablishment/input_AutoPopulationMethod'), autopopulationmethod)
	 }*/
	public void clickAskForApproval() {
		TestObject to = findTestObject('NotificationPage/btn_askforapproval')
		abstractPage.waitForElementVisibled(findTestObject('NotificationPage/btn_askforapproval'))
		abstractPage.clickToElement(findTestObject('NotificationPage/btn_askforapproval'))
	}

	/**
	 * Disbursement----End*/

	/*Capture Manual Schedule for Manual Schedule only - Begin*/
	public void inputAutoPopilationMethod(String autoPopulationMethod){
		TestObject to =findTestObject('DirectLoanEstablishment/CaptureSchedule/input_AutoPopulationMethod')
		abstractPage.waitForElementVisibled(to)
		abstractPage.sendkeyToElement(to, autoPopulationMethod)
	}

	public void clickAutoPopulateSchedule(){
		TestObject to =findTestObject('Object Repository/DirectLoanEstablishment/CaptureSchedule/btn_AutoPopulate')
		abstractPage.waitForElementVisibled(to)
		abstractPage.clickToElement(to)

	}

	public void editPrincipal(String paymentNo, String principalAmt){
		TestObject rdo_payment_no
		TestObject txt_principalAmt = findTestObject('DirectLoanEstablishment/CaptureSchedule/txt_PrincipalAmt')
		TestObject btn_SavePayment = findTestObject('Object Repository/DirectLoanEstablishment/CaptureSchedule/btn_SavePayment')

		if(!paymentNo.trim().equals("")){
			String [] ar_paymentNo = abstractPage.splitString(paymentNo)
			for(int i = 0; i <ar_paymentNo.length ; i++){
				WebUI.delay(2)
				rdo_payment_no = findTestObject('Object Repository/DirectLoanEstablishment/CaptureSchedule/rdo_PaymentNo',['No':abstractPage.replaceArrayIndexOutOfBoundsException(paymentNo, i, "")])
				abstractPage.waitForElementVisibled(rdo_payment_no)
				abstractPage.clickToElement(rdo_payment_no)
				abstractPage.waitForElementVisibled(txt_principalAmt)
				abstractPage.clearText(txt_principalAmt)
				abstractPage.clickToElement(rdo_payment_no)
				abstractPage.sendkeyToElement(txt_principalAmt, Keys.chord(Keys.ARROW_UP) + Keys.chord(Keys.ARROW_RIGHT) + abstractPage.replaceArrayIndexOutOfBoundsException(principalAmt, i, ""))
				abstractPage.waitForElementVisibled(btn_SavePayment)
				abstractPage.clickToElement(btn_SavePayment)
			}
		}
		//clickNext()

	}

	/*Capture Manual Schedule for Manual Schedule only - End*/

	/*--Verify----*/
	/*Highlight the element*/   
	public highlightObject(TestObject to){
		abstractPage.highlightElement(to)
	}

	/*Step 1 - Verify Direct Loan Establistment is displayed*/
	public String isDisplayedDirectLoanEstablishment(String testCaseNumber, String step, String sequence){
		TestObject to = findTestObject('Object Repository/DirectLoanEstablishment/title_DirectLoanEstablishment')
		String resutls = ""
		try{
			if(WebUI.verifyElementVisible(to, FailureHandling.OPTIONAL)){
				resutls = "PASSED"
				highlightObject(to)
			}
			else {
				resutls = "FAILED"
			}
			abstractPage.takeScreenshotGetCurrenDate(testCaseNumber, step, resutls, sequence)
		}
		catch(Exception e){
			resutls = "ERROR"
			println(e)
		}
		return resutls
	}

	/* Verify Disbursement Details is displayed*/
	public String isScreenDisplayed(String testCaseNumber, String step, String screenName, String sequence){
		TestObject to = findTestObject('DirectLoanEstablishment/Verification/lbl_ScreenName',['screenName':screenName])
		String resutls = ""
		try{
			if(WebUI.verifyElementVisible(to, FailureHandling.OPTIONAL)){
				resutls = "PASSED"
				highlightObject(to)
			}
			else {
				resutls = "FAILED"
			}
			abstractPage.takeScreenshotGetCurrenDate(testCaseNumber, step, resutls, sequence)
		}
		catch(Exception e){
			resutls = "ERROR"
			println(e)
		}
		return resutls
	}

	//	public String verifyDisplayedValue(String testCaseNumber, String step, String loanTerm, String principalAmount, String sequence){
	//		TestObject lbl_LoanTerm = findTestObject('DirectLoanEstablishment/Verification/lbl_LoanTerm')
	//		TestObject lbl_PrincipalAmount = findTestObject('DirectLoanEstablishment/Verification/lbl_PrincipalAmount')
	//		String resutls = ""
	//		try{
	//			if(abstractPage.getAttributeValue(lbl_LoanTerm, "value").equals(loanTerm) &&  abstractPage.getAttributeValue(lbl_PrincipalAmount, "value").equals(principalAmount)){
	//				resutls = "PASSED"
	//				highlightObject(lbl_LoanTerm)
	//				highlightObject(lbl_PrincipalAmount)
	//			}
	//			else {
	//				resutls = "FAILED"
	//			}
	//			abstractPage.takeScreenshotGetCurrenDate(testCaseNumber, step, resutls, sequence)
	//
	//		}
	//		catch(Exception e){
	//			resutls = "ERROR"
	//			println(e)
	//		}
	//		return resutls
	//	}

	/*Step 2 - Verify Customer Name*/
	//	public String isDisplayedCustomerName(String customerName){
	//		TestObject to = findTestObject('Object Repository/DirectLoanEstablishment/Verify/lbl_CustomerName')
	//		String resutls = ""
	//		try{
	//			if(WebUI.verifyElementPresent(to, 5, FailureHandling.OPTIONAL)){
	//				resutls = "PASSED"
	//			}
	//			else {
	//				resutls = "FAILED"
	//			}
	//		}
	//		catch(Exception e){
	//			resutls = "ERROR"
	//			println(e)
	//		}
	//		return resutls
	//	}

	/*Step 5 - Verify Co-Applicants*/
	//	public String isDisplayedCoApplicants(){
	//		TestObject to = findTestObject('Object Repository/DirectLoanEstablishment/Verify/lbt_Co-Applicants')
	//		String resutls = ""
	//		try{
	//			if(WebUI.verifyElementPresent(to, 5, FailureHandling.OPTIONAL)){
	//				resutls = "PASSED"
	//			}
	//			else {
	//				resutls = "FAILED"
	//			}
	//		}
	//		catch(Exception e){
	//			resutls = "ERROR"
	//			println(e)
	//		}
	//		return resutls
	//	}
	//	public void clickcoapplicant(){
	//		try {
	//			abstractPage.waitForElementVisibled(findTestObject('Object Repository/DirectLoanEstablishment/Verify/lbt_Co-Applicants'))
	//			abstractPage.clickToElement(findTestObject('Object Repository/DirectLoanEstablishment/Verify/lbt_Co-Applicants'))
	//		}
	//		catch(Exception e){
	//			println(e)
	//		}
	//	}

	/**
	 * Total Charge and Taxt: 40.000 MMK*/
	public String verifyTotalChargeAmount(String testCaseNumber, String step, String sequence, String chargeAmount){
		TestObject to = findTestObject('Object Repository/DirectLoanEstablishment/Verification/ChargePage/lbl_TotalChargeAndTax')
		String resutls = ""
		try{
			abstractPage.waitForElementPresent(to)
			abstractPage.highlightElement(to)
			String tmp = abstractPage.getAttributeValue(to, "value").replace(",","")
			println("charge amount: "+ tmp)
			Float expectedChargeAmount  =Float.parseFloat(chargeAmount)
			Float acctualChargeAmount  =Float.parseFloat(tmp)
			println "ex: " + expectedChargeAmount + " acctual: " + acctualChargeAmount
			if(expectedChargeAmount == acctualChargeAmount ){
				resutls = "PASSED"
			}
			else {
				resutls = "FAILED"
			}
			abstractPage.takeScreenshotGetCurrenDate(testCaseNumber, step, resutls, sequence)
		}
		catch(Exception e){
			resutls = "ERROR"
			println(e)
		}
		return resutls
	}

	/**
	 * Step05: - 424 only apply for disburse one time
	 * 1. Schedule gird is displayed
	 * 2. The first row is displayed: Disbursement Date and Disbursement Amount*/
	public String verifyCaptureManualSchedule(String testCaseNumber, String step, String sequence, String disbursementAmount){
		TestObject to_disbursementAmount = findTestObject('DirectLoanEstablishment/Verification/CaptureManualSchedule/lbl_TotalDisbursementAmount')
		String resutl = "", tmp_disbusementAmt = 0
		try{
			abstractPage.waitForElementPresent(to_disbursementAmount)
			tmp_disbusementAmt = abstractPage.getAttributeValue(to_disbursementAmount, "value").replace(",", "")
			BigDecimal bgDec_acctual_disbursementAmount = abstractPage.convertStringToBigDecimal(tmp_disbusementAmt)
			BigDecimal bgDec_expected_disbursementAmount = new BigDecimal(disbursementAmount)

			println("bgDec_acctual_disbursementAmount: "+ bgDec_acctual_disbursementAmount + " bgDec_expected_disbursementAmount: " + bgDec_expected_disbursementAmount)
			if(bgDec_acctual_disbursementAmount == bgDec_expected_disbursementAmount){
				abstractPage.highlightElementByColor(to_disbursementAmount, "blu")
				resutl = "PASSED"
			}
			else {
				abstractPage.highlightElementByColor(to_disbursementAmount, "red")
				resutl = "FAILED"
			}

			abstractPage.takeScreenshotGetCurrenDate(testCaseNumber, step, resutl, sequence)
		}
		catch(Exception e){
			resutl = "ERROR"
			println(e)
		}
		return resutl
	}


	public void closePage(String pageName){
		abstractPage.closePage(pageName)
	}

	public ResultSet getResultTestDataFromDB(String testCaseNumber){
		String sql = ""
		ResultSet rs = null
		try{
			sql ="select * from [dbo].[TD_EducationLoan_Amort_MMK] where testcaseNumber = '"+ testCaseNumber.trim() + "'"
			return rs = abstractPage.getResultTestDataFromDB(sql, "TestData_UATA")
		}
		catch(Exception e){
			println(e)
			println ("Error getResultTestDataFromDB")
		}

	}

	/*---------------------Write the results------------*/

	public void writeResult(String testcaseID, String stepStatus, String stepScreenshort, String testDate,String sequence, String databaseName){
		try{
			abstractPage.writeResultToDatabase(testcaseID, stepStatus, stepScreenshort, testDate, sequence, databaseName)
		}
		catch(Exception e){
			println(e)
		}
	}

	public String getLoanReference(){
		TestObject to_rf= findTestObject('Object Repository/DirectLoanEstablishment/Verification/LoanReview/lbl_LoanReference')
		String loanAmt = ""
		int count =0
		try{
			abstractPage.highlightElement(to_rf)
			loanAmt = abstractPage.getAttributeValue(to_rf, 'value')
		}
		catch(StaleElementReferenceException){
			if(count<3){
				//chrome 80 has got error time out
				WebUI.delay(2)
				getLoanReference()
				count++
			}
		}
		return loanAmt
	}

	public String getTestDate(){
		abstractPage.getTestDate()
	}

	/*Thuong*/
	public String getLoanReferenceFromLoanDetails(String accountID){
		Connection con = abstractPage.getSQLServerconnection("TestData_UATA")
		stmt = con.createStatement()
		String sql = "SELECT * FROM OPENQUERY([UATA], 'select LOANREFERENCE from WASADMIN.LOANDETAILS where accountid = ''"+accountID+"''')"
		//String sql ="SELECT *  FROM OPENQUERY([UATA], 'SELECT * FROM WASADMIN.LOANDETAILS WHERE ROWNUM<100')"
		ResultSet rs = stmt.executeQuery(sql)
		if(rs.next()){
			String loanReference = rs.getString("LOANREFERENCE")// get the loanReference
			println "LOANREFERENCE: " + loanReference
			return loanReference
		}else{
			println 'data not found'
		}
	}

	public String verifyChargeValue(String testCaseNumber, String step, String chargeamt, String sequence){
		TestObject lblChargeValue = findTestObject('Object Repository/DirectLoanEstablishment/Verification/ChargePage/lbl_TotalChargeAndTax')
		String resutls = ""
		try{
			if(abstractPage.getAttributeValue(lblChargeValue, "value").equals(chargeamt) ){
				resutls = "PASSED"
				highlightObject(lblChargeValue)
			}
			else {
				resutls = "FAILED"
			}
			abstractPage.takeScreenshotGetCurrenDate(testCaseNumber, step, resutls, sequence)
			return resutls
		}
		catch(Exception e){
			resutls = "ERROR"
			println(e)
		}
	}

	public void clickAskForApproval(String username_teller, String password_teller, String username_supervisor, String password_supervisor) {
		TestObject btn_askForApproval = findTestObject('NotificationPage/btn_AskForApproval')
		try{
			if(WebUI.verifyElementPresent(btn_askForApproval, 5, FailureHandling.OPTIONAL)){
				abstractPage.waitForElementPresent(btn_askForApproval)
				abstractPage.clickToElement(btn_askForApproval)

				WebUI.callTestCase(findTestCase('AfterClickAskForApproval'), [('username_teller') : username_teller
					, ('password_teller') : password_teller, ('active') : 'Yes', ('username_supervisor') : username_supervisor, ('password_supervisor') : password_supervisor],
				FailureHandling.CONTINUE_ON_FAILURE)

				WebUI.delay(5)
			}

		}
		catch(Exception e){
			println(e)
		}
	}

	public boolean isAskForApproval() {
		TestObject btn_askForApproval = findTestObject('NotificationPage/btn_AskForApproval')
		boolean isAsk = false
		try{
			if(WebUI.verifyElementPresent(btn_askForApproval, 5, FailureHandling.OPTIONAL)){
				isAsk = true
			}

		}
		catch(Exception e){
			println(e)
		}
		return isAsk
	}

	public double calculateLoanAmount(String totalCost, String contributionPercentage, String contributionAmount){
		double prinAmount = 0
		totalCost = totalCost.replace(",", "")
		contributionPercentage = contributionPercentage.replace(",", "")
		contributionAmount = contributionAmount.replace(",", "")
		if(!contributionPercentage.equals("")){
			prinAmount = Double.parseDouble(totalCost)*(100-Double.parseDouble(contributionPercentage))/100

		}else{
			prinAmount = Double.parseDouble(totalCost) - Double.parseDouble(contributionAmount)
		}
		prinAmount = Math.round(prinAmount*100)/100
		println prinAmount
		return prinAmount
	}

	public String getPrincipalAmount(){
		TestObject to_amt = findTestObject('Object Repository/DirectLoanEstablishment/Verification/Loan Review/lbl_SanctionedloanAmount')
		TestObject to_term = findTestObject('Object Repository/DirectLoanEstablishment/Verification/Loan Review/lbl_LoanTerm')
		abstractPage.highlightElement(to_amt)
		abstractPage.highlightElement(to_term)
		String loanAmt = abstractPage.getAttributeValue(to_amt, 'value')
		return loanAmt
	}

	public String verifYLoanInformation(String testcaseNumber, String step, String sequence, double principalAmt, String term){

		TestObject to_amt = findTestObject('Object Repository/DirectLoanEstablishment/Verification/LoanReview/lbl_SanctionedloanAmount')
		TestObject to_term = findTestObject('Object Repository/DirectLoanEstablishment/Verification/LoanReview/lbl_LoanTerm')
		String rs = 'PASSED'
		try{
			abstractPage.highlightElement(to_amt)
			abstractPage.highlightElement(to_term)
			String loanAmt = abstractPage.getAttributeValue(to_amt, 'value')
			String termNo = abstractPage.getAttributeValue(to_term, 'value')
			if(loanAmt != null && termNo != null ){
				loanAmt = loanAmt.replace(",", "")

				if(Double.parseDouble(loanAmt) != principalAmt || !termNo.equals(term)){
					rs = 'FAILED'
				}
			}

		}catch(Exception e){
			rs = 'FAILED'
			println(e)
		}
		abstractPage.takeScreenshotGetCurrenDate(testcaseNumber, step, rs, sequence)
		return rs
	}

	public String[] getAccountNumber(String testcaseNumber, String step, String sequence, String principalAmt){
		TestObject to_acc = findTestObject('Object Repository/DirectLoanEstablishment/Verification/AfterApproved/lbl_AccountNumber')
		TestObject to_amt = findTestObject('Object Repository/DirectLoanEstablishment/Verification/AfterApproved/lbl_LoanAmount')
		String accountNumber = abstractPage.getAttributeValue(to_acc, 'value')
		String loanAmt = abstractPage.getAttributeValue(to_amt, 'value')
		abstractPage.highlightElement(to_acc)
		abstractPage.highlightElement(to_amt)
		String result = 'PASSED'
		if(principalAmt != null){
			if(loanAmt.equals(principalAmt)){
				result = 'PASSED'
			}
			else{
				result = 'FAILED'
			}
		}
		abstractPage.takeScreenshotGetCurrenDate(testcaseNumber, step, result, sequence)
		String[] rs = new String[2]
		rs[0] = accountNumber
		rs[1] = result
		return rs
	}

	public void clickProceed(){
		TestObject to = findTestObject('commons/btn_Proceed_Offer')
		if(WebUI.verifyElementPresent(to, 5, FailureHandling.OPTIONAL)){
			abstractPage.waitForElementPresent(to)
			abstractPage.clickToElement(to)
			clickProceed()
		}
	}

	public void clickClosePage(){
		abstractPage.waitForElementPresent(findTestObject('commons/btn_ClosePage',['pageName':'Direct Loan Establishment']))
		abstractPage.clickToElement(findTestObject('commons/btn_ClosePage',['pageName':'Direct Loan Establishment']))
	}
}