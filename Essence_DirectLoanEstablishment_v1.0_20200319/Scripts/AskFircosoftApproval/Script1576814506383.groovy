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
import pageObjects.NotificationPage as NotificationPage

NotificationPage notificationPage = new NotificationPage()

WebUI.callTestCase(findTestCase('Login'), [('url') : tel_url, ('username') : tel_username, ('password') : tel_password, ('active') : 'Yes'], FailureHandling.STOP_ON_FAILURE)
notificationPage.clickNotificationIcon()
notificationPage.clickDescription("Approval")
notificationPage.clickItemBySupervisorUser(supervisorUsername)
//ask for fircosoft approval
notificationPage.clickAskForApproval()
WebUI.callTestCase(findTestCase('LogOutFromSystem'), [:], FailureHandling.STOP_ON_FAILURE)

