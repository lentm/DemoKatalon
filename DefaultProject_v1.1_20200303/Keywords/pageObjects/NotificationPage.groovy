package pageObjects
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import org.openqa.selenium.WebElement

import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import java.lang.RuntimeException
import org.openqa.selenium.WebDriverException
import org.openqa.selenium.NotFoundException
import org.openqa.selenium.StaleElementReferenceException

import commons.AbstractPage as AbstractPage
import report.ExcelFiles as ExcelFiles
import org.openqa.selenium.ElementClickInterceptedException


public class NotificationPage {
	AbstractPage abstractPage = new AbstractPage()
	ExcelFiles excelFiles = new ExcelFiles ()
	private int longTimeout =30
	private int shortTimeout = 5
	private FailureHandling failureHandling = FailureHandling.OPTIONAL

	/**
	 * Select Referral, Information*/
	private void clickElement(String descOfElement){
		try{
			//TestObject element = notificationPageUI.rdo_NotificationTypes(descOfElement)
			abstractPage.waitForElementVisibled(findTestObject('Object Repository/NotificationPage/NotificationTypes_rdo',['descElement':descOfElement]))
			abstractPage.checkTheCheckbox(findTestObject('Object Repository/NotificationPage/NotificationTypes_rdo',['descElement':descOfElement]))
		}
		catch (Exception e){
			println(e)
		}
	}

	private void clickNextButton(){
		try{
			abstractPage.waitForElementVisibled(findTestObject('commons/btn_Next'))
			abstractPage.clickToElementByJavascript(findTestObject('commons/btn_Next'))
		}
		catch (Exception e){
			println(e)
		}
	}

	private void clickApproved(String taskDescription){
		try{
			WebUI.delay(1)

			if(WebUI.verifyElementPresent(findTestObject('NotificationPage/ListTaskID_rdo',['description':'Referral']),5 , FailureHandling.OPTIONAL)){
				abstractPage.waitForElementPresent(findTestObject('NotificationPage/ListTaskID_rdo',['description':'Referral']))

				abstractPage.clickToElement(findTestObject('NotificationPage/ListTaskID_rdo',['description':'Referral']))
				WebUI.delay(1)

				abstractPage.waitForElementVisibled(findTestObject('NotificationPage/btn_ApproveButton'))
				abstractPage.clickToElement(findTestObject('NotificationPage/btn_ApproveButton'))
				clickApproved(taskDescription)
			}
		}
		catch (Exception e){
			println(e)
		}
	}

	private void clickConfirmed(String taskDescription, String description){
		WebElement listitems
		WebElement listitems_rdo
		String temp, path, sheetname
		String module =""
		int count
		try{
			WebUI.delay(1)
			abstractPage.waitForElementVisibled(findTestObject('NotificationPage/ListTaskID_rdo',['description':'Approval']))
			listitems_rdo = WebUI.findWebElement(findTestObject('NotificationPage/ListTaskID_rdo',['description':'Approval']),longTimeout )

			//will be loop if the gird has row greater 0
			while(WebUI.verifyElementPresent(findTestObject('NotificationPage/ListTaskID_rdo',['description':'Approval']),longTimeout , FailureHandling.OPTIONAL)){
				abstractPage.waitForElementVisibled(findTestObject('NotificationPage/ListTaskID_rdo',['description':'Approval']))
				//WebUI.waitForElementClickable(findTestObject('NotificationPage/ListTaskID_rdo',['description':'Approval']), longTimeout, FailureHandling.STOP_ON_FAILURE)
				listitems_rdo = WebUI.findWebElement(findTestObject('NotificationPage/ListTaskID_rdo',['description':'Approval']),longTimeout )
				//WebUI.delay(n)
				listitems_rdo.click()
				clickNextButton()

				/*Asking approval from Fricosoft*/
				/*IDT environment asks to approve from FricoSoft - UAT doesn't ask - Begin*/
				//if(WebUI.waitForElementVisible(findTestObject('NotificationPage/lbl_MessageAskingFricosoft (1)'), 5, FailureHandling.OPTIONAL)){
				if(WebUI.waitForElementVisible(findTestObject('Object Repository/ProcessApprovalConfirmation/OpenAccountCASA/lbl_ApprovalRequired'), 5, FailureHandling.OPTIONAL)){
					/*IDT environment asks to approve from FricoSoft - UAT doesn't ask - End*/
					abstractPage.clickToElement(findTestObject('NotificationPage/btn_AskForApprovalFricoSoft'))
					clickNotificationIcon()
					println "clickNotificationIcon()"
					WebUI.delay(3)
					clickDescription('Approval')
					println "clickDescription()"
				}else{

					/*Checking which module is approving*/
					if(WebUI.waitForElementPresent(findTestObject('NotificationPage/lbl_TitlePage',['pageName':'Party Onboarding']), shortTimeout, FailureHandling.OPTIONAL) || WebUI.waitForElementPresent(findTestObject('NotificationPage/lbl_TitlePage',['pageName':'Quick Party Onboarding']), shortTimeout, FailureHandling.OPTIONAL)){
						//Party Onboarding
						module ='Party'
					}
					else {
						if(WebUI.waitForElementPresent(findTestObject('NotificationPage/lbl_TitlePage',['pageName':'Open Account']), shortTimeout, FailureHandling.OPTIONAL)){
							//check Account CASA
							module ='Account CASA'
						}
						else {
							if(WebUI.waitForElementPresent(findTestObject('NotificationPage/lbl_TitlePage',['pageName':'Direct Loan Establishment']), shortTimeout, FailureHandling.OPTIONAL)){
								//Direct Loan Establishment
								module = "Direct Loan Establishment"
							}else {
								module = ""}

						}
					}
					println "module: " + module
					switch(module){
						case "Party":
							clickDenoteCompliance()
							if(WebUI.waitForElementVisible(findTestObject('NotificationPage/lbl_TitleEnterprise',['description':'Enterprise Related Party Status']), shortTimeout, FailureHandling.OPTIONAL)){
								/**enterprise*/
								WebUI.delay(1)
								//click Accept radio
								abstractPage.waitForElementPresent(findTestObject('Object Repository/ProcessApprovalConfirmation/Enterprise/rdo_Accept'))
								abstractPage.clickToElementByJavascript(findTestObject('Object Repository/ProcessApprovalConfirmation/Enterprise/rdo_Accept'))
								clickNextButton()
								count = 0
							}
							path ="Results_Data\\PartyID.xlsx"
							sheetname = "PartyID"
							break
						case "Account CASA":
							path ="Results_Data\\AccountNumber.xlsx"
							sheetname = "AccountNumber"
							break
						default:
							path ="Results_Data\\AccountNumber_Error.xlsx"
							sheetname = "AccountNumber"
							break
					}
					println "path: "+ path
					getInformationConfirmationPage(path,sheetname,module)
					clickDescription(description)
				}
			}
		}
		catch(StaleElementReferenceException e){
			count += 1
			if(count <3){
				println "Encounter StaleElementReferenceException"
				clickConfirmed(taskDescription, description)
			}
		}
		catch (Exception e){
			println(e)
		}
	}


	private void clickNotificationIcon(){
		try{
			//WebUI.delay(n)
			abstractPage.waitForElementVisibled(findTestObject('NotificationPage/btn_NotificationIcon'))
			abstractPage.clickToElementByJavascript(findTestObject('NotificationPage/btn_NotificationIcon'))
		}
		catch(Exception e) {
			println (e)
		}
	}

	private void clickDescription(String strDescription){
		//TestObject to = notificationPageUI.rdo_NotificationTypes("Referral")
		TestObject to = findTestObject('Object Repository/NotificationPage/NotificationTypes_rdo',['descElement':strDescription])
		try{
			if(WebUI.waitForElementVisible(to, shortTimeout, FailureHandling.OPTIONAL)){
				//WebUI.delay(n)
				abstractPage.waitForElementVisibled(to)
				abstractPage.clickToElementByJavascript(to)
				WebUI.delay(2)
				clickNextButton()
			}
		}
		catch(Exception e) {
			println (e)
		}
	}

	private void clickDenoteCompliance(){
		//TestObject to = notificationPageUI.rdo_NotificationTypes("Referral")
		TestObject to = findTestObject('NotificationPage/chk_TickToDenoteCompliance')
		try{
			//WebUI.delay(n)
			abstractPage.waitForElementPresent(to)
			WebUI.delay(1)
			abstractPage.clickToElementByJavascript(to)
			WebUI.delay(1)
			clickNextButton()
		}
		catch(Exception e) {
			println (e)
		}
	}

	private void getInformationConfirmationPage(String filename, String sheetname, String module){
		//enterprise and personal are using same xpath, should be open one by one
		TestObject to_personalPartyType = findTestObject('Object Repository/ProcessApprovalConfirmation/PartyID/lbl_PartyType')
		TestObject to_personalPartyID = findTestObject('Object Repository/ProcessApprovalConfirmation/PartyID/lbl_PartyID')
		TestObject to_personalPartyName = findTestObject('Object Repository/ProcessApprovalConfirmation/PartyID/lbl_PartyName')
		TestObject to_personalPartyNameEnterprise = findTestObject('Object Repository/ProcessApprovalConfirmation/PartyID/lbl_PartyNameEnterprise')
		TestObject to_accountNumber = findTestObject('Object Repository/ProcessApprovalConfirmation/OpenAccountCASA/lbl_AccountNumber')
		TestObject to_customerID = findTestObject('Object Repository/ProcessApprovalConfirmation/OpenAccountCASA/lbl_CustomerID')
		TestObject to_customerName = findTestObject('Object Repository/ProcessApprovalConfirmation/OpenAccountCASA/lbl_CustomerName')
		TestObject to_productName = findTestObject('Object Repository/ProcessApprovalConfirmation/OpenAccountCASA/lbl_ProductName')
		TestObject to_jointAccount = findTestObject('Object Repository/ProcessApprovalConfirmation/OpenAccountCASA/lbl_JointAccount')
		TestObject to_openingDate = findTestObject('Object Repository/ProcessApprovalConfirmation/OpenAccountCASA/lbl_OpeningDate')
		TestObject to_status = findTestObject('Object Repository/ProcessApprovalConfirmation/OpenAccountCASA/lbl_Status')
		String strHeader, strValues
		try{
			switch (module){
				case "Party":
					strHeader = "Party Type"
					strValues = abstractPage.getAttributeValue(to_personalPartyType, "value")
					abstractPage.waitForElementVisibled(to_personalPartyID)
					strHeader += " | " + "Party ID"
					strValues += " | " + abstractPage.getAttributeValue(to_personalPartyID, "value")
					strHeader += " | " + "Party Name"
					strValues += " | " + abstractPage.getAttributeValue(to_personalPartyName, "value")
					println "strHeader: "+strHeader +" strValues: "+strValues
					excelFiles.writeExcelfilesMulitpleCol(filename, sheetname, strHeader, strValues)
					WebUI.waitForPageLoad(3)
				//waiting page load, close the page just opened
					if(WebUI.waitForElementPresent(findTestObject('commons/btn_ClosePage',['pageName':'Resume Party Onboarding']), shortTimeout, FailureHandling.OPTIONAL)){
						//personal
						abstractPage.clickToElement(findTestObject('commons/btn_ClosePage',['pageName':'Resume Party Onboarding']))
					}else{
						//enterprise
						WebUI.delay(2)
						abstractPage.waitForElementVisibled(findTestObject('commons/btn_ClosePage',['pageName':'Party Onboarding']))
						abstractPage.clickToElement(findTestObject('commons/btn_ClosePage',['pageName':'Party Onboarding']))
					}
					break
				case "Party Enterprise":
					strHeader = "Party Type"
					strValues = abstractPage.getAttributeValue(to_personalPartyType, "value")
					abstractPage.waitForElementVisibled(to_personalPartyID)
					strHeader += " | " + "Party ID"
					strValues += " | " + abstractPage.getAttributeValue(to_personalPartyID, "value")
					strHeader += " | " + "Party Name"
					strValues += " | " + abstractPage.getAttributeValue(to_personalPartyNameEnterprise, "value")
					println "strHeader: "+strHeader +" strValues: "+strValues
					excelFiles.writeExcelfilesMulitpleCol(filename, sheetname, strHeader, strValues)
					WebUI.waitForPageLoad(3)
				//waiting page load, close the page just opened
					if(WebUI.waitForElementPresent(findTestObject('commons/btn_ClosePage',['pageName':'Resume Party Onboarding']), shortTimeout, FailureHandling.OPTIONAL)){
						//personal
						abstractPage.clickToElement(findTestObject('commons/btn_ClosePage',['pageName':'Resume Party Onboarding']))
					}else{
						//enterprise
						WebUI.delay(2)
						abstractPage.waitForElementVisibled(findTestObject('commons/btn_ClosePage',['pageName':'Party Onboarding']))
						abstractPage.clickToElement(findTestObject('commons/btn_ClosePage',['pageName':'Party Onboarding']))
					}
					break
				case "Quick Party Onboarding - Personal":
					strHeader = "Party Type"
					strValues = abstractPage.getAttributeValue(to_personalPartyType, "value")
					abstractPage.waitForElementVisibled(to_personalPartyID)
					strHeader += " | " + "Party ID"
					strValues += " | " + abstractPage.getAttributeValue(to_personalPartyID, "value")
					strHeader += " | " + "Party Name"
					strValues += " | " + abstractPage.getAttributeValue(to_personalPartyName, "value")
					println "strHeader: "+strHeader +" strValues: "+strValues
					excelFiles.writeExcelfilesMulitpleCol(filename, sheetname, strHeader, strValues)
					WebUI.waitForPageLoad(3)
					WebUI.delay(2)
					abstractPage.waitForElementVisibled(findTestObject('commons/btn_ClosePage',['pageName':'Quick Party Onboarding']))
					abstractPage.clickToElement(findTestObject('commons/btn_ClosePage',['pageName':'Quick Party Onboarding']))
					break
				case "Quick Party Onboarding - Enterprise":
					strHeader = "Party Type"
					strValues = abstractPage.getAttributeValue(to_personalPartyType, "value")
					abstractPage.waitForElementVisibled(to_personalPartyID)
					strHeader += " | " + "Party ID"
					strValues += " | " + abstractPage.getAttributeValue(to_personalPartyID, "value")
					strHeader += " | " + "Party Name"
					strValues += " | " + abstractPage.getAttributeValue(to_personalPartyNameEnterprise, "value")
					println "strHeader: "+strHeader +" strValues: "+strValues
					excelFiles.writeExcelfilesMulitpleCol(filename, sheetname, strHeader, strValues)
					WebUI.waitForPageLoad(3)
					WebUI.delay(2)
					abstractPage.waitForElementVisibled(findTestObject('commons/btn_ClosePage',['pageName':'Quick Party Onboarding']))
					abstractPage.clickToElement(findTestObject('commons/btn_ClosePage',['pageName':'Quick Party Onboarding']))
					break
				case "Account CASA":
				//Account Number
					strHeader = "Account Number"
					abstractPage.waitForElementPresent(to_accountNumber)
					strValues = abstractPage.getAttributeValue(to_accountNumber, "value")
				//Customer
					strHeader += " | " + "Customer"
					abstractPage.waitForElementPresent(to_customerID)
					strValues += " | " + abstractPage.getAttributeValue(to_customerID, "value")
				//Customer Name
					strHeader += " | " + "Customer Name"
					abstractPage.waitForElementPresent(to_customerID)
					strValues += " | " + abstractPage.getAttributeValue(to_customerName, "value")
				//Opening Date
					strHeader += " | " + "Opening Date"
					abstractPage.waitForElementPresent(to_openingDate)
					strValues += " | " + abstractPage.getAttributeValue(to_openingDate, "value")
				//Product Name
					strHeader += " | " + "Product Name"
					abstractPage.waitForElementPresent(to_productName)
					strValues += " | " + abstractPage.getAttributeValue(to_productName, "value")
				//Joint Account
					strHeader += " | " + "Joint Account"
					abstractPage.waitForElementPresent(to_jointAccount)
					strValues += " | " + abstractPage.getAttributeValue(to_jointAccount, "value")
				//Account Status
					strHeader += " | " + "Status"
					abstractPage.waitForElementPresent(to_status)
					strValues += " | " + abstractPage.getAttributeValue(to_status, "value")
					println "strHeader: "+strHeader +" strValues: "+strValues
				//write values
					excelFiles.writeExcelfilesMulitpleCol(filename, sheetname, strHeader, strValues)
				//WebUI.waitForPageLoad(3)
					abstractPage.waitForElementVisibled(findTestObject('commons/btn_ClosePage',['pageName':'Open Account']))
					abstractPage.clickToElement(findTestObject('commons/btn_ClosePage',['pageName':'Open Account']))

					break
			}


		}
		catch(Exception e) {
			println (e)
		}
	}

	private void clickConfirmedQuickPartyOboarding_Personal(String taskDescription, String description){
		WebElement listitems
		WebElement listitems_rdo
		String temp, path, sheetname
		String module =""
		int count
		try{
			WebUI.delay(1)
			abstractPage.waitForElementVisibled(findTestObject('NotificationPage/ListTaskID_rdo',['description':'Approval']))
			listitems_rdo = WebUI.findWebElement(findTestObject('NotificationPage/ListTaskID_rdo',['description':'Approval']),longTimeout )

			//will be loop if the gird has row greater 0
			while(WebUI.verifyElementPresent(findTestObject('NotificationPage/ListTaskID_rdo',['description':'Approval']),longTimeout , FailureHandling.OPTIONAL)){
				abstractPage.waitForElementVisibled(findTestObject('NotificationPage/ListTaskID_rdo',['description':'Approval']))
				//WebUI.waitForElementClickable(findTestObject('NotificationPage/ListTaskID_rdo',['description':'Approval']), longTimeout, FailureHandling.STOP_ON_FAILURE)
				listitems_rdo = WebUI.findWebElement(findTestObject('NotificationPage/ListTaskID_rdo',['description':'Approval']),longTimeout )
				//WebUI.delay(n)
				listitems_rdo.click()
				clickNextButton()
				if(WebUI.verifyElementPresent(findTestObject('QuickPartyOnboarding/Enterprise/Error/btn_GoBack_ErrorMessage'), 5, FailureHandling.OPTIONAL)){
					abstractPage.clickToElementByJavascript(findTestObject('QuickPartyOnboarding/Enterprise/Error/btn_GoBack_ErrorMessage'))
					abstractPage.waitForElementVisibled(findTestObject('commons/btn_ClosePage',['pageName':'Quick Party Onboarding']))
					abstractPage.clickToElementByJavascript(findTestObject('commons/btn_ClosePage',['pageName':'Quick Party Onboarding']))
					clickDescription(description)
				}else{

					module = "Quick Party Onboarding - Personal"
					path ="Results_Data\\PartyID.xlsx"
					sheetname = "PartyID"
					clickDenoteCompliance()
					clickNextButton()
					getInformationConfirmationPage(path,sheetname,module)
					clickDescription(description)
				}
			}
		}
		catch(StaleElementReferenceException e){
			count += 1
			if(count <3){
				println "Encounter StaleElementReferenceException"
				clickConfirmed(taskDescription, description)
			}
		}
		catch (Exception e){
			println(e)
		}
	}

	private void clickConfirmedQuickPartyOboarding_Enterprise(String taskDescription, String description){
		WebElement listitems
		WebElement listitems_rdo
		String temp, path, sheetname
		String module =""
		int count
		try{
			WebUI.delay(1)
			abstractPage.waitForElementVisibled(findTestObject('NotificationPage/ListTaskID_rdo',['description':'Approval']))
			listitems_rdo = WebUI.findWebElement(findTestObject('NotificationPage/ListTaskID_rdo',['description':'Approval']),longTimeout )

			//will be loop if the gird has row greater 0
			while(WebUI.verifyElementPresent(findTestObject('NotificationPage/ListTaskID_rdo',['description':'Approval']),longTimeout , FailureHandling.OPTIONAL)){
				abstractPage.waitForElementVisibled(findTestObject('NotificationPage/ListTaskID_rdo',['description':'Approval']))
				//WebUI.waitForElementClickable(findTestObject('NotificationPage/ListTaskID_rdo',['description':'Approval']), longTimeout, FailureHandling.STOP_ON_FAILURE)
				listitems_rdo = WebUI.findWebElement(findTestObject('NotificationPage/ListTaskID_rdo',['description':'Approval']),longTimeout )
				//WebUI.delay(n)
				listitems_rdo.click()
				clickNextButton()
				/*fixing the error from Finastra Begin*/
				//if((WebUI.waitForElementVisible(findTestObject('QuickPartyOnboarding/Enterprise/Error/btn_GoBack_ErrorMessage'), 5, FailureHandling.OPTIONAL)){
				//abstractPage.waitForElementVisibled(findTestObject('QuickPartyOnboarding/Enterprise/Error/btn_GoBack_ErrorMessage'))
				if(WebUI.verifyElementPresent(findTestObject('QuickPartyOnboarding/Enterprise/Error/btn_GoBack_ErrorMessage'), 5, FailureHandling.OPTIONAL)){
					abstractPage.clickToElementByJavascript(findTestObject('QuickPartyOnboarding/Enterprise/Error/btn_GoBack_ErrorMessage'))
					abstractPage.waitForElementVisibled(findTestObject('commons/btn_ClosePage',['pageName':'Quick Party Onboarding']))
					abstractPage.clickToElementByJavascript(findTestObject('commons/btn_ClosePage',['pageName':'Quick Party Onboarding']))
				}
				else{

					//				/*fixing the error from Finastra End*/
					module = "Quick Party Onboarding - Enterprise"
					path ="Results_Data\\PartyID.xlsx"
					sheetname = "PartyID"
					clickDenoteCompliance()
					abstractPage.waitForElementPresent(findTestObject('Object Repository/ProcessApprovalConfirmation/Enterprise/rdo_Accept'))
					abstractPage.clickToElementByJavascript(findTestObject('Object Repository/ProcessApprovalConfirmation/Enterprise/rdo_Accept'))
					clickNextButton()
					//				WebUI.delay(2)
					//				clickNextButton()
					getInformationConfirmationPage(path,sheetname,module)
				}
				clickDescription(description)
			}
		}
		catch(StaleElementReferenceException e){
			count += 1
			if(count <3){
				println "Encounter StaleElementReferenceException"
				clickConfirmed(taskDescription, description)
			}
		}
		catch (Exception e){
			println(e)
		}
	}

	private void clickConfirmedPartyOboarding_Enterprise(){
		WebElement listitems
		WebElement listitems_rdo
		String temp, path, sheetname
		String module =""
		int count
		try{
			WebUI.delay(1)
			abstractPage.waitForElementVisibled(findTestObject('NotificationPage/ListTaskID_rdo',['description':'Approval']))
			listitems_rdo = WebUI.findWebElement(findTestObject('NotificationPage/ListTaskID_rdo',['description':'Approval']),longTimeout )

			//will be loop if the gird has row greater 0
			if(WebUI.verifyElementPresent(findTestObject('NotificationPage/ListTaskID_rdo',['description':'Approval']),longTimeout , FailureHandling.OPTIONAL)){
				abstractPage.waitForElementVisibled(findTestObject('NotificationPage/ListTaskID_rdo',['description':'Approval']))
				listitems_rdo = WebUI.findWebElement(findTestObject('NotificationPage/ListTaskID_rdo',['description':'Approval']),longTimeout )
				listitems_rdo.click()
				clickNextButton()
				if(WebUI.verifyElementPresent(findTestObject('NotificationPage/lbl_MessageAskingFricosoft'), 5, FailureHandling.OPTIONAL)){
					abstractPage.clickToElement(findTestObject('NotificationPage/btn_AskForApprovalFricoSoft'))
					println "ask firco roi ne - wait 60s"
					WebUI.delay(60)

					clickNotificationIcon()
					println "clickNotificationIcon()"
					clickDescription('Approval')
					println "clickDescription()"
				}
				else{
					clickDenoteCompliance()
					WebUI.delay(10)
					abstractPage.waitForElementPresent(findTestObject('Object Repository/ProcessApprovalConfirmation/Enterprise/rdo_Accept'))
					abstractPage.clickToElementByJavascript(findTestObject('Object Repository/ProcessApprovalConfirmation/Enterprise/rdo_Accept'))
					clickNextButton()
				}
			}
			clickClosePage()
		}
		catch(StaleElementReferenceException e){
			println(e)
		}
		catch (Exception e){
			println(e)
		}
	}

	private void clickConfirmedPartyOnboardingPersonal(String taskDescription, String description){
		WebElement listitems
		WebElement listitems_rdo
		String temp, path, sheetname
		String module =""
		int count
		try{
			WebUI.delay(1)
			abstractPage.waitForElementVisibled(findTestObject('NotificationPage/ListTaskID_rdo',['description':'Approval']))
			listitems_rdo = WebUI.findWebElement(findTestObject('NotificationPage/ListTaskID_rdo',['description':'Approval']),longTimeout )

			//will be loop if the gird has row greater 0
			while(WebUI.verifyElementPresent(findTestObject('NotificationPage/ListTaskID_rdo',['description':'Approval']),longTimeout , FailureHandling.OPTIONAL)){
				abstractPage.waitForElementVisibled(findTestObject('NotificationPage/ListTaskID_rdo',['description':'Approval']))
				//WebUI.waitForElementClickable(findTestObject('NotificationPage/ListTaskID_rdo',['description':'Approval']), longTimeout, FailureHandling.STOP_ON_FAILURE)
				listitems_rdo = WebUI.findWebElement(findTestObject('NotificationPage/ListTaskID_rdo',['description':'Approval']),longTimeout )
				//WebUI.delay(n)
				listitems_rdo.click()
				clickNextButton()


				module ='Party'
				path ="Results_Data\\PartyID.xlsx"
				sheetname = "PartyID"
				clickDenoteCompliance()
				println "module: " + module

				println "path: "+ path
				getInformationConfirmationPage(path,sheetname,module)
				clickDescription(description)
			}
			//			}
		}
		catch(StaleElementReferenceException e){
			count += 1
			if(count <3){
				println "Encounter StaleElementReferenceException"
				clickConfirmedPartyOnboardingPersonal(taskDescription, description)
			}
		}
		catch (Exception e){
			println(e)
		}
	}

	private void clickConfirmedPartyOnboarding(){
		WebElement listitems
		WebElement listitems_rdo
		String temp, path, sheetname
		String module =""
		int count
		try{
			WebUI.delay(1)
			abstractPage.waitForElementVisibled(findTestObject('NotificationPage/ListTaskID_rdo',['description':'Approval']))
			listitems_rdo = WebUI.findWebElement(findTestObject('NotificationPage/ListTaskID_rdo',['description':'Approval']),shortTimeout )

			//will be loop if the gird has row greater 0
			if(WebUI.verifyElementPresent(findTestObject('NotificationPage/ListTaskID_rdo',['description':'Approval']),shortTimeout , FailureHandling.OPTIONAL)){
				abstractPage.waitForElementVisibled(findTestObject('NotificationPage/ListTaskID_rdo',['description':'Approval']))
				listitems_rdo = WebUI.findWebElement(findTestObject('NotificationPage/ListTaskID_rdo',['description':'Approval']),shortTimeout )
				//WebUI.delay(n)
				listitems_rdo.click()
				clickNextButton()

				if(WebUI.verifyElementPresent(findTestObject('NotificationPage/lbl_MessageAskingFricosoft'), 10, FailureHandling.OPTIONAL)){
					abstractPage.clickToElement(findTestObject('NotificationPage/btn_AskForApprovalFricoSoft'))
					println "ask firco roi ne - wait 60s"
					WebUI.delay(60)

					clickNotificationIcon()
					println "clickNotificationIcon()"
					clickDescription('Approval')
					println "clickDescription()"
				}else{
					clickDenoteCompliance()
					WebUI.delay(10)
				}

			}
			clickClosePage()
		}
		catch (Exception e){
			println(e)
		}
	}

	private void clickConfirmedPartyOnboardingEnterprise(String taskDescription, String description){
		WebElement listitems
		WebElement listitems_rdo
		String temp, path, sheetname
		String module =""
		int count
		try{
			WebUI.delay(1)
			abstractPage.waitForElementVisibled(findTestObject('NotificationPage/ListTaskID_rdo',['description':'Approval']))
			listitems_rdo = WebUI.findWebElement(findTestObject('NotificationPage/ListTaskID_rdo',['description':'Approval']),shortTimeout )

			//will be loop if the gird has row greater 0
			while(WebUI.verifyElementPresent(findTestObject('NotificationPage/ListTaskID_rdo',['description':'Approval']),shortTimeout , FailureHandling.OPTIONAL)){
				abstractPage.waitForElementVisibled(findTestObject('NotificationPage/ListTaskID_rdo',['description':'Approval']))
				listitems_rdo = WebUI.findWebElement(findTestObject('NotificationPage/ListTaskID_rdo',['description':'Approval']),shortTimeout )
				//WebUI.delay(n)
				//listitems_rdo.click()
				clickNextButton()

				/*Checking which module is approving*/
				if(WebUI.waitForElementPresent(findTestObject('NotificationPage/lbl_TitlePage',['pageName':'Party Onboarding']), shortTimeout, FailureHandling.OPTIONAL) || WebUI.waitForElementPresent(findTestObject('NotificationPage/lbl_TitlePage',['pageName':'Quick Party Onboarding']), shortTimeout, FailureHandling.OPTIONAL)){
					//Party Onboarding
					module ='Party Enterprise'

					println "module: " + module
					clickDenoteCompliance()
					WebUI.delay(1)
					abstractPage.waitForElementPresent(findTestObject('Object Repository/ProcessApprovalConfirmation/Enterprise/rdo_Accept'))
					abstractPage.clickToElementByJavascript(findTestObject('Object Repository/ProcessApprovalConfirmation/Enterprise/rdo_Accept'))
					clickNextButton()
					path ="Results_Data\\PartyID.xlsx"
					sheetname = "PartyID"
					println "path: "+ path
					getInformationConfirmationPage(path,sheetname,module)
					clickDescription(description)
				}
			}
		}
		catch(StaleElementReferenceException e){
			count += 1
			if(count <3){
				println "Encounter StaleElementReferenceException"
				clickConfirmedPartyOnboardingEnterprise(taskDescription, description)
			}
		}
		catch (Exception e){
			println(e)
		}
	}
	private void clickClosePage(){
		try{
			abstractPage.waitForElementPresent(findTestObject('commons/btn_ClosePage',['pageName':'Process Notifications']))
			abstractPage.clickToElement(findTestObject('commons/btn_ClosePage',['pageName':'Process Notifications']))
		}
		catch (Exception e){
			println(e)
		}

	}
	private void clickMySavedTaskList(){
		try{


			if(WebUI.verifyElementPresent(findTestObject('NotificationPage/ListTaskID_rdo',['description':'MySavedTaskList']),shortTimeout , FailureHandling.OPTIONAL)){
				abstractPage.waitForElementVisibled(findTestObject('NotificationPage/ListTaskID_rdo',['description':'MySavedTaskList']))
				abstractPage.clickToElement(findTestObject('NotificationPage/ListTaskID_rdo',['description':'MySavedTaskList']))
				WebUI.delay(1)
				clickNextButton()
				WebUI.delay(5)
			}
			clickClosePage()
		}
		catch (Exception e){
			println(e)
		}
	}
}