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

import internal.GlobalVariable
import commons.AbstractPage as AbstractPage

public class LoginPage {

	AbstractPage abstractPage = new AbstractPage()

	private void openBrowser(String url){
		if(!url.trim().equals("")){
			WebUI.openBrowser(url)
			WebUI.maximizeWindow()
		}	
	}
	private void inputUsername(String username){
		abstractPage.waitForElementPresent(findTestObject('LogIn/input_Username_user'))
		abstractPage.sendkeyToElement(findTestObject('LogIn/input_Username_user'), username)
	}

	private void inputPassword(String password){
		abstractPage.waitForElementPresent(findTestObject('LogIn/input_Password_pwd'))
		abstractPage.sendkeyToElement(findTestObject('LogIn/input_Password_pwd'), password)
	}

	private void clickSingin(){
		abstractPage.waitForElementPresent(findTestObject('LogIn/input_Password_login'))
		abstractPage.clickToElement(findTestObject('LogIn/input_Password_login'))
	}

	private void waitLoadPage(){
		WebUI.waitForPageLoad(5, FailureHandling.STOP_ON_FAILURE)
	}

	private void isLoginSuccess(String username){
		TestObject to = findTestObject('Object Repository/LogIn/CheckValidUsers/ErrorMes_Contains')
		String contains = abstractPage.getTextElement(to)
		println "contains: " + contains
		if(WebUI.waitForElementVisible(findTestObject('Object Repository/LogIn/Error'), 5, FailureHandling.OPTIONAL)){
			excelFields.writeExcelfilesColNameRowName("userlist_20190903.xlsx", "User List for (UAT)", "Results",username, "F " + contains)
		}
		else{
			excelFields.writeExcelfilesColNameRowName("userlist_20190903.xlsx", "User List for (UAT)", "Results",username, "T")
			WebUI.callTestCase(findTestCase('LogOutFromSystem'), [:], FailureHandling.STOP_ON_FAILURE)
		}
	}
}
