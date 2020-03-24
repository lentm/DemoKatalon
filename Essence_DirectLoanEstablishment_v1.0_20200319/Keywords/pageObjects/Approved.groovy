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
import org.openqa.selenium.Keys
import internal.GlobalVariable
import commons.AbstractPage as AbstractPage

public class Approved {
	AbstractPage abs = new AbstractPage()
	int timeout= 5
	private FailureHandling failureHandling = FailureHandling.OPTIONAL
	public void clicknotifications() {
		abs.waitForElementVisibled(findTestObject('Object Repository/EnterpriseCommitment/Approved/icon_userapprove'))
		abs.clickToElement(findTestObject('Object Repository/EnterpriseCommitment/Approved/icon_userapprove'))
	}
	public void inputbusinessprocesstype (String businessprocesstype){
		abs.waitForElementVisibled(findTestObject('Object Repository/EnterpriseCommitment/Approved/input_BusinessProcessType'))
		abs.sendkeyToElement(findTestObject('Object Repository/EnterpriseCommitment/Approved/input_BusinessProcessType'), businessprocesstype)
		abs.sendkeyToElement(findTestObject('Object Repository/EnterpriseCommitment/Approved/input_BusinessProcessType'), Keys.chord(Keys.ENTER))
	}
	public void checkdivworkflowgeneral(String description) {
		try{
			abs.waitForElementVisibled(findTestObject('Object Repository/EnterpriseCommitment/Approved/check_workflowgeneral',['description':description]))
			WebUI.delay(2)
			abs.clickToElement(findTestObject('Object Repository/EnterpriseCommitment/Approved/check_workflowgeneral',['description':description]))
		}
		catch(Exception e){
			println (e)
		}
	}
	public void clicknext() {
		try {
			abs.waitForElementVisibled(findTestObject('Object Repository/EnterpriseCommitment/Approved/btn_Next'))
			WebUI.delay(2)
			abs.clickToElement(findTestObject('Object Repository/EnterpriseCommitment/Approved/btn_Next'))
		}
		catch (Exception e) {
			println (e)
		}
	}
	/*public void checkready (String ready) {
	 try{
	 abs.waitForElementVisibled(findTestObject('Object Repository/EnterpriseCommitment/Approved/check_ready',['ready':ready]))
	 abs.checkTheCheckbox(findTestObject('Object Repository/EnterpriseCommitment/Approved/check_ready',['ready': ready]))
	 }
	 catch(Exception e){
	 println (e)
	 }
	 }*/
	public void clickexecute() {
		abs.waitForElementVisibled(findTestObject('Object Repository/EnterpriseCommitment/Approved/btn_Execute'))
		abs.clickToElement(findTestObject('Object Repository/EnterpriseCommitment/Approved/btn_Execute'))
	}
	private void clickCloseTab(){
		try{
			TestObject to = findTestObject('Object Repository/EnterpriseCommitment/Approved/btn_CloseTab')
			TestObject tabName = findTestObject('Object Repository/EnterpriseCommitment/Approved/span_TabName')
			abs.waitForElementVisibled(tabName)
			abs.moveMouseToElement(tabName)
			abs.waitForElementPresent(to)
			abs.clickToElementByJavascript(to)
		}
		catch(Exception e){
			println (e)
		}
	}
	public void checkapprove() {
		abs.waitForElementVisibled(findTestObject('Object Repository/EnterpriseCommitment/Approved/td_Approve'))
		abs.clickToElement(findTestObject('Object Repository/EnterpriseCommitment/Approved/td_Approve'))
	}
	public void inputcustomerresponse (String customerresponse) {
		abs.waitForElementVisibled(findTestObject('Object Repository/EnterpriseCommitment/Approved/input_CustomerResponse'))
		WebUI.delay(2)
		abs.sendkeyToElement(findTestObject('Object Repository/EnterpriseCommitment/Approved/input_CustomerResponse'), customerresponse)
	}
	public void inputdecisiondate (String decisiondate) {
		abs.waitForElementVisibled(findTestObject('Object Repository/EnterpriseCommitment/Approved/input_CustomerResponse'))
		WebUI.delay(1)
		abs.sendkeyToElement(findTestObject('Object Repository/EnterpriseCommitment/Approved/input_CustomerResponse'), decisiondate)
	}
	public void inputfundingaccount (String fundingaccount) {
		abs.waitForElementVisibled(findTestObject('Object Repository/EnterpriseCommitment/Approved/input_FundingAccount'))
		WebUI.delay(1)
		abs.sendkeyToElement(findTestObject('Object Repository/EnterpriseCommitment/Approved/input_FundingAccount'), fundingaccount)
	}
	public void inputindustrycode (String industrycode) {
		abs.waitForElementVisibled(findTestObject('Object Repository/EnterpriseCommitment/Approved/input_IndustryCode'))
		WebUI.delay(1)
		abs.sendkeyToElement(findTestObject('Object Repository/EnterpriseCommitment/Approved/input_IndustryCode'), industrycode)
	}
	public boolean isElementReady(TestObject to){
		boolean isVisible = false
		isVisible = WebUI.verifyElementVisible(to, FailureHandling.OPTIONAL)
		println "co ton tai khong" +isVisible
		return isVisible
	}
	public void excuteLOApplication(String ready){
		//TestObject to = findTestObject('Object Repository/EnterpriseCommitment/Approved/check_ready',['ready':ready])
		boolean isReady = isElementReady(findTestObject('Object Repository/EnterpriseCommitment/Approved/check_ready',['ready':ready]))
		switch (isReady) {
			case true:
				abs.waitForElementVisibled(findTestObject('Object Repository/EnterpriseCommitment/Approved/check_ready',['ready':ready]))
				abs.clickToElement(findTestObject('Object Repository/EnterpriseCommitment/Approved/check_ready',['ready':ready]))
				break;
			default:
				clickCloseTab()
				break;
		}
	}
	public void maketransaction (String ready, String customerresponse, String fundingaccount, String industrycode){
		clicknotifications()
		inputbusinessprocesstype("Enterprise Commitment")
		checkdivworkflowgeneral("WORKFLOW_GENERAL")
		clicknext()
		//isElementReady()
		boolean isReady = false
		isReady = isElementReady(findTestObject('Object Repository/EnterpriseCommitment/Approved/check_ready',['ready':ready]))
		if (isReady) {
			excuteLOApplication(ready)
			clickexecute()
			clickCloseTab()
			checkapprove()
			clicknext()
			clicknext()
			inputcustomerresponse(customerresponse)
			clicknext()
			inputfundingaccount(fundingaccount)
			inputindustrycode(industrycode)
			clicknext()
			getInformationConfirmationPage()
			clicknext()
			maketransaction(ready, customerresponse, fundingaccount, industrycode)
		}
		else {
			clickCloseTab()
		}
	}
	private void getInformationConfirmationPage() {
		String filename = 'Results_Data\\EnterpriseCommitment.xlsx'
		String sheetname = 'EnterpriseCommitment'
		TestObject to_applicationid = findTestObject('Object Repository/EnterpriseCommitment/Approved/InformationConfirmation/lbl_applicationid')
		TestObject to_product = findTestObject('Object Repository/EnterpriseCommitment/Approved/InformationConfirmation/lbl_product')
		TestObject to_commitmentreference = findTestObject('Object Repository/EnterpriseCommitment/Approved/InformationConfirmation/lbl_commitmentreference')
		String strHeader, strValues
		try{
			strHeader = "Application ID"
			strValues = abs.getAttributeValue(to_applicationid, "value")
			strHeader += " | " + "Product"
			strValues += " | " + abs.getAttributeValue(to_product, "value")
			strHeader += " | " + "Commitment Reference"
			strValues += " | " + abs.getAttributeValue(to_commitmentreference, "value")
			println "strHeader: "+strHeader +" strValues: "+strValues
			excelFiles.writeExcelfilesMulitpleCol(filename, sheetname, strHeader, strValues)
		}
		catch(Exception e) {
			println (e)
		}
	}
}
