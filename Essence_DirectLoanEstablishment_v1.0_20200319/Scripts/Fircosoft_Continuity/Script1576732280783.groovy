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
import pageObjects.FircosoftContinuity
import org.openqa.selenium.WebDriver as WebDriver
import org.openqa.selenium.firefox.FirefoxDriver as FirefoxDriver
import com.kms.katalon.core.webui.driver.DriverFactory as DriverFactory


FircosoftContinuity ficosoftContinuity = new FircosoftContinuity()
int shorttime = 2
//Login
System.setProperty("webdriver.gecko.driver", "D:\\Auto\\Katalon_Tools\\Katalon_Studio_Windows_64-6.1.2\\configuration\\resources\\drivers\\firefox_win64\\geckodriver.exe")
WebDriver firefoxDriver = new FirefoxDriver()

DriverFactory.changeWebDriver(firefoxDriver)
WebUI.navigateToUrl(url)
ficosoftContinuity.loginContinuity(username,password)

//click Live Message to open the information
ficosoftContinuity.clickLiveMessage()
WebUI.delay(shorttime)
ficosoftContinuity.clickColumns() //add the Info column
ficosoftContinuity.clickFilterInfo(customerName) //filter by customer name
ficosoftContinuity.clickInfo(customerName)
WebUI.delay(shorttime)
//ficosoftContinuity.clickItems()
ficosoftContinuity.clickPassed()
//ficosoftContinuity.inputReasoncode()
ficosoftContinuity.clickSubmit(customerName)
ficosoftContinuity.clickLogout()
//