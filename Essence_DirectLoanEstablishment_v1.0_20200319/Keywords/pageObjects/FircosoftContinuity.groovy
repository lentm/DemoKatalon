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
import commons.AbstractPage

public class FircosoftContinuity {

	AbstractPage abstractPage = new AbstractPage()
	int shorttime = 2
	int medtime = 5
	int longtime = 10

	private void loginContinuity(String url){
		try{
			WebUI.openBrowser(url)
			WebUI.maximizeWindow()
		}
		catch (Exception e){
			println(e)
		}
	}

	private void loginContinuity( String username, String password){
		TestObject txt_Username = findTestObject('Object Repository/Fircosft_Continuity/Login/txt_Username')
		TestObject txt_Password = findTestObject('Object Repository/Fircosft_Continuity/Login/txt_Password')
		TestObject btn_Login =findTestObject('Object Repository/Fircosft_Continuity/Login/btn_Login')
		try{
			abstractPage.waitForElementVisibled(txt_Username)
			abstractPage.sendkeyToElement(txt_Username, username)

			abstractPage.waitForElementVisibled(txt_Password)
			abstractPage.sendkeyToElement(txt_Password, password)

			abstractPage.waitForElementVisibled(btn_Login)
			abstractPage.clickToElement(btn_Login)
		}
		catch(Exception e){
			println(e)
		}
	}

	private void clickLiveMessage(){
		TestObject to = findTestObject('Object Repository/Fircosft_Continuity/Page_Welcome to Firco Continuity/link_LiveMessages')
		try{
			abstractPage.waitForElementVisibled(to)
			abstractPage.clickToElement(to)
		}
		catch(Exception e){
			println(e)
		}
	}

	private void clickColumns(){
		TestObject link_Column = findTestObject('Object Repository/Fircosft_Continuity/Page_Live Messages/btn_Columns_Arrow')
		TestObject item_Info = findTestObject('Object Repository/Fircosft_Continuity/Page_Live Messages/item_Info')
		try{
			abstractPage.waitForElementVisibled(link_Column)
			abstractPage.clickToElement(link_Column)
			WebUI.delay(medtime)
			abstractPage.waitForElementVisibled(item_Info)
			abstractPage.clickToElement(item_Info)
			//abstractPage.clickToElement(link_filterDate)
		}
		catch(Exception e){
			println(e)
		}
	}

	private void clickFilteredForSorting(){
		TestObject link_filterDate = findTestObject('Object Repository/Fircosft_Continuity/Page_Live Messages/btn_FilteredDate_Arrow')
		TestObject sortDescending = findTestObject('Object Repository/Fircosft_Continuity/Page_Live Messages/item_SortDescending')

		try{
			abstractPage.waitForElementPresent(link_filterDate)
			abstractPage.clickToElement(link_filterDate)
			WebUI.delay(shorttime)
			abstractPage.waitForElementVisibled(sortDescending)
			abstractPage.clickToElement(sortDescending)
		}
		catch(Exception e){
			println(e)
		}
	}

	private void clickFilterInfo(String customerName){
		TestObject link_filterDate = findTestObject('Object Repository/Fircosft_Continuity/Page_Live Messages/btn_FilteredDate_Arrow')
		TestObject link_Info = findTestObject('Object Repository/Fircosft_Continuity/Page_Live Messages/link_Info')
		TestObject txt_quickSearch = findTestObject('Object Repository/Fircosft_Continuity/Page_Live Messages/txt_inputSearch')
		try{
			abstractPage.clickToElement(link_filterDate)
			WebUI.delay(medtime)
			abstractPage.waitForElementPresent(link_Info)
			abstractPage.clickToElement(link_Info)
			WebUI.delay(shorttime)
			abstractPage.waitForElementVisibled(txt_quickSearch)
			//abstractPage.sendkeyToElement(txt_quickSearch, customerName + Keys.chord(Keys.ENTER))
			WebUI.sendKeys(txt_quickSearch, customerName + Keys.chord(Keys.ENTER))
		}
		catch(Exception e){
			println(e)
		}
	}

	private void clickInfo(String customerName){
		TestObject itemDetails = findTestObject('Object Repository/Fircosft_Continuity/Page_Live Messages/td_InfoDetails',['name':customerName])
		try{
			abstractPage.waitForElementVisibled(itemDetails)
			abstractPage.clickToElement(itemDetails)
			println("customerName: " + customerName)
		}
		catch(Exception e){
			println(e)
		}
	}

	private void clickItems(String customerName){
		TestObject link_filterDate = findTestObject('Object Repository/Fircosft_Continuity/Page_Live Messages/btn_FilteredDate_Arrow')
		TestObject itemDetails = findTestObject('Object Repository/Fircosft_Continuity/Page_Live Messages/td_InfoDetails',['name':customerName])
		try{
			abstractPage.waitForElementVisibled(itemDetails)
			abstractPage.clickToElement(itemDetails)
		}
		catch(Exception e){
			println(e)
		}
	}

	private void clickPassed(){
		TestObject btn_Passed = findTestObject('Object Repository/Fircosft_Continuity/btn_Passed')
		TestObject item_OK = findTestObject('Object Repository/Fircosft_Continuity/ddl_item_Ok')
		TestObject ddl_select = findTestObject('Object Repository/Fircosft_Continuity/ddl_select')
		try{
			abstractPage.waitForElementVisibled(btn_Passed)
			abstractPage.clickToElement(btn_Passed)

			abstractPage.waitForElementVisibled(ddl_select)
			abstractPage.clickToElement(ddl_select)
			WebUI.delay(medtime)
			abstractPage.waitForElementVisibled(item_OK)
			abstractPage.highlightElement(item_OK)
			abstractPage.clickToElement(item_OK)
			abstractPage.clickToElementByJavascript(item_OK)
		}
		catch(Exception e){
			println(e)
		}
	}

	private void inputReasoncode(){
	}

	private void clickSubmit(String customerName){
		TestObject btn_Submit = findTestObject('Object Repository/Fircosft_Continuity/btn_Submit')
		TestObject btn_SearchIcon = findTestObject('Object Repository/Fircosft_Continuity/btn_SearchIcon')
		TestObject lbl_NoData = findTestObject('Object Repository/Fircosft_Continuity/lbl_NoData')
		try{
			abstractPage.waitForElementVisibled(btn_Submit)
			abstractPage.clickToElement(btn_Submit)
			
			//click Live Message
			clickLiveMessage()
			//clear fillter
			abstractPage.waitForElementVisibled(btn_SearchIcon)
			abstractPage.clickToElement(btn_SearchIcon)
			//fillter the customer again
			clickFilterInfo(customerName)
			if(WebUI.waitForElementVisible(lbl_NoData, shorttime, FailureHandling.OPTIONAL)){
				WebUI.delay(shorttime)
				clickItems(customerName)
				clickPassed()
				clickSubmit(customerName)
			}
			
		}
		catch(Exception e){
			println(e)
		}
	}
	
	private void clickLogout(){
		TestObject to= findTestObject('Object Repository/Fircosft_Continuity/btn_Logout')
		try{
			abstractPage.waitForElementVisibled(to)
			abstractPage.clickToElement(to)
		}
		catch(Exception e){
			println(e)
		}
	}
}
