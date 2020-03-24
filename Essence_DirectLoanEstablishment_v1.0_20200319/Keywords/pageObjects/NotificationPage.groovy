package pageObjects
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import org.openqa.selenium.WebElement

import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testdata.TestData
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import java.lang.RuntimeException
import org.openqa.selenium.WebDriverException
import org.openqa.selenium.NotFoundException
import org.openqa.selenium.StaleElementReferenceException

import commons.AbstractPage as AbstractPage
import internal.GlobalVariable
import pageObjects.DirectLoanEstablishment

import java.sql.ResultSet

public class NotificationPage {
	AbstractPage abstractPage = new AbstractPage()
	DirectLoanEstablishment directLoanEstablishment = new DirectLoanEstablishment()
	TestData test = findTestData("DirectLoanEstablishment")
	private int longTimeout_01 =50
	private int longTimeout =30
	private int medTimeout = 10
	private int shortTimeout = 5
	private FailureHandling failureHandling = FailureHandling.OPTIONAL


	public void clickNextButton(){
		TestObject btn_Next = findTestObject('Object Repository/NotificationPage/btn_Next')
		try{
			abstractPage.waitForElementVisibled(btn_Next)
			abstractPage.clickToElement(btn_Next)
		}
		catch(Exception e){
		}
	}

	public void clickApproved(){
		WebUI.delay(1)

		if(WebUI.verifyElementPresent(findTestObject('NotificationPage/ListTaskID_rdo',['description':'Referral']),5 , FailureHandling.OPTIONAL)){
			abstractPage.waitForElementPresent(findTestObject('NotificationPage/ListTaskID_rdo',['description':'Referral']))

			abstractPage.clickToElement(findTestObject('NotificationPage/ListTaskID_rdo',['description':'Referral']))
			WebUI.delay(1)

			abstractPage.waitForElementVisibled(findTestObject('NotificationPage/Approve_btn'))
			abstractPage.clickToElement(findTestObject('NotificationPage/Approve_btn'))
			clickApproved()
		}
	}

	public void clickApproved(String taskDescription){
		String temp
		int count = 0
		TestObject rdo_commentItem= findTestObject('Object Repository/NotificationPage/rdo_commentItem',['description':taskDescription])
		TestObject btn_Approve = findTestObject('Object Repository/NotificationPage/Approve_btn')
		try{
			WebUI.delay(3)
			abstractPage.waitForElementVisibled(rdo_commentItem)
			while(WebUI.verifyElementPresent(rdo_commentItem,shortTimeout , FailureHandling.OPTIONAL)){
				abstractPage.waitForElementPresent(rdo_commentItem)
				WebUI.delay(2)
				abstractPage.clickToElement(rdo_commentItem)
				count +=1
				abstractPage.waitForElementVisibled(btn_Approve)
				abstractPage.clickToElement(btn_Approve)
				println "after click: " + count
			}
		}
		catch (StaleElementReferenceException e){
			println "count: " + count
			count +=1
			if(count<3){
				clickApproved(taskDescription)
			}
		}
		catch (Exception e){
			println(e)
		}
	}

	public void clickApprovedByNumber(String taskDescription, int totalNumber){
		String temp
		int count = 0
		TestObject rdo_commentItem= findTestObject('Object Repository/NotificationPage/rdo_commentItem',['description':taskDescription])
		TestObject btn_Approve = findTestObject('Object Repository/NotificationPage/Approve_btn')
		try{
			WebUI.delay(3)
			abstractPage.waitForElementVisibled(rdo_commentItem)

			while(totalNumber>0){
				abstractPage.waitForElementPresent(rdo_commentItem)
				WebUI.delay(2)
				abstractPage.clickToElement(rdo_commentItem)
				count +=1
				abstractPage.waitForElementVisibled(btn_Approve)
				abstractPage.clickToElement(btn_Approve)
				println "after click: " + count
				totalNumber--
				println("totalNumber-- :" +totalNumber)
			}
		}
		catch (StaleElementReferenceException e){
			println "[catch] count : " + count
			totalNumber ++
			if(count<4){
				clickApprovedByNumber(taskDescription)
			}
		}
		catch (Exception e){
			println(e)
		}
	}

	public void clickConfirmed(){
		TestObject to = findTestObject('NotificationPage/ListTaskID_rdo',['description':'Approval'])
		if(WebUI.verifyElementPresent(to,5 , FailureHandling.OPTIONAL)){
			abstractPage.waitForElementVisibled(to)
			abstractPage.clickToElement(to)
			clickNextButton()
			WebUI.delay(7)
			clickClosePage()
		}
	}

	public void clickClosePage(){
		abstractPage.waitForElementPresent(findTestObject('commons/btn_ClosePage',['pageName':'Process Notifications']))
		abstractPage.clickToElement(findTestObject('commons/btn_ClosePage',['pageName':'Process Notifications']))
	}

	public void clickConfirmed(String testCaseNumber, String result, String sequence, String step,String loanReference, String principalAmt){
		TestObject to = findTestObject('NotificationPage/ListTaskID_rdo',['description':'Approval'])
		if(WebUI.verifyElementPresent(to,5 , FailureHandling.OPTIONAL)){
			abstractPage.waitForElementVisibled(to)
			abstractPage.clickToElement(to)
			WebUI.delay(2)
			clickNextButton()
			println 'delay 25s wait for page load'
			WebUI.delay(25)
			println 'delay 25s done'
			if(WebUI.verifyElementPresent(findTestObject('Object Repository/NotificationPage/btn_AskForApproval'), 10,FailureHandling.OPTIONAL)){
				abstractPage.clickToElement(findTestObject('Object Repository/NotificationPage/btn_AskForApproval'))
				WebUI.delay(2)
				println 'ask firco + new row: ' + testCaseNumber
				abstractPage.writeResultToDatabase(testCaseNumber, result, '', abstractPage.getTestDate(), sequence,loanReference, 'TestData_UATA')
				clickNotificationIcon()
				println 'click notification'
				clickDescription("Approval")
				println 'click Approval'

				clickConfirmed(testCaseNumber,result,sequence,step,loanReference,principalAmt)
			}else{

				if (!WebUI.verifyElementPresent(findTestObject('commons/lbl_ScreenName',['screenName':'Disbursement Details']),10,FailureHandling.OPTIONAL)){
					if(WebUI.verifyElementPresent(findTestObject('commons/lbl_ScreenName',['screenName':'Direct Loan Establishment']),10,FailureHandling.OPTIONAL)){
						clickClosePage()
						directLoanEstablishment.highlightObject(findTestObject('commons/lbl_ScreenName',['screenName':'Direct Loan Establishment']))
						String[] accountRs = directLoanEstablishment.getAccountNumber(testCaseNumber, step, sequence, principalAmt)
						println 'accountRs: '+accountRs
						String loanRf = directLoanEstablishment.getLoanReferenceFromLoanDetails(accountRs[0])
						//list of screenshort

						String screenshots

						ResultSet resultSet = abstractPage.getResultFromDB(loanRf, "TestData_UATA")
						if(resultSet == null || !resultSet.next()){
							println 'data null + new row: ' + testCaseNumber
							String result_temp =result+ step+","+accountRs[1]
							screenshots = abstractPage.moveScreenShortToServer(testCaseNumber, sequence, "", "")
							abstractPage.writeResultToDatabase(testCaseNumber, result_temp, screenshots, abstractPage.getTestDate(), sequence, accountRs[0],'TestData_UATA')
							println 'rs: '+result_temp
						}else{
							String stepRs = resultSet.getString(3)

							if(!stepRs.endsWith("|")){
								stepRs+= "|"
							}
							stepRs = stepRs + step+","+accountRs[1]
							println resultSet.getString("TestCaseId") + ' rs: '+stepRs
							screenshots = abstractPage.moveScreenShortToServer(resultSet.getString("TestCaseId"),  resultSet.getString("Sequence"), "", "")
							abstractPage.updateResultToDatabase(resultSet.getString("TestCaseId"), stepRs, screenshots, resultSet.getString("Sequence"), accountRs[0], 'TestData_UATA')
						}
						directLoanEstablishment.clickClosePage()

					}
					clickNotificationIcon()
					println 'click notification'
					clickDescription("Approval")
					println 'click Approval'
					clickConfirmed(testCaseNumber,result,sequence,step,loanReference,principalAmt)
				}
				else {
					println 'Continue'
					clickClosePage()
				}
			}
		}
		else{
			clickClosePage()
		}
	}


	/*System asks to approve - created at 11/02/2020 - ML*/
	public void clickConfirmedLimit_DirectLoanEstablishment(){
		String temp, path, sheetname
		TestObject rdo_listTaskID = findTestObject('Object Repository/NotificationPage/ListTaskID_rdo',['description':'Approval'])
		String module =""
		int count
		try{
			WebUI.delay(1)
			//will be loop if the gird has row greater 0
			while(WebUI.verifyElementPresent(rdo_listTaskID,longTimeout , FailureHandling.OPTIONAL)){
				abstractPage.waitForElementPresent(rdo_listTaskID)
				abstractPage.clickToElement(rdo_listTaskID)
				//WebUI.delay(n)
				clickNextButton()
				abstractPage.closePage("Process Notifications")
				//clickDescription(description)
			}
		}
		catch(StaleElementReferenceException e){
			count += 1
			if(count <3){
				println "Encounter StaleElementReferenceException"
				clickConfirmedLimit_DirectLoanEstablishment()
			}
		}
		catch (Exception e){
			println(e)
		}
	}

	public void clickNotificationIcon(){
		TestObject to = findTestObject('Object Repository/NotificationPage/btn_NotificationIcon')

		try{
			//WebUI.delay(n)
			abstractPage.waitForElementVisibled(to)
			abstractPage.clickToElementByJavascript(to)
		}
		catch(Exception e) {
			println (e)
		}
	}

	public int clickDescription(String strDescription){
		TestObject to = findTestObject('NotificationPage/rdo_descriptionItem',['descElement':strDescription])
		TestObject btn_Next = findTestObject('Object Repository/NotificationPage/btn_Next')
		TestObject lbl_Number= findTestObject('Object Repository/NotificationPage/lbl_TotalNumber',['description':strDescription])
		int i = 0
		try{
			if(WebUI.waitForElementPresent(to, shortTimeout, FailureHandling.OPTIONAL)){
				println "lbl_TotalNumber: "+ abstractPage.getTextElement(lbl_Number)
				if(!abstractPage.getTextElement(lbl_Number).trim().equals("")){
					i = Integer.parseInt(abstractPage.getTextElement(lbl_Number).toString())
				}else {i=0}

				//WebUI.delay(n)
				abstractPage.waitForElementPresent(to)
				abstractPage.clickToElementByJavascript(to)
				//WebUI.delay(n)
				abstractPage.waitForElementPresent(btn_Next)
				abstractPage.clickToElementByJavascript(btn_Next)
			}
			return i
		}
		catch(Exception e) {
			println (e)
		}
	}

	public void clickItemBySupervisorUser(String strSupervisorUsername){
		TestObject to = findTestObject('NotificationPage/rdo_descriptionItem',['descElement':strSupervisorUsername])
		TestObject btn_Next = findTestObject('Object Repository/NotificationPage/btn_Next')
		try{
			if(WebUI.waitForElementPresent(to, shortTimeout, FailureHandling.OPTIONAL)){
				//WebUI.delay(n)
				abstractPage.waitForElementPresent(to)
				abstractPage.clickToElementByJavascript(to)
				//WebUI.delay(n)
				abstractPage.waitForElementPresent(btn_Next)
				abstractPage.clickToElementByJavascript(btn_Next)
			}
		}
		catch(Exception e) {
			println (e)
		}
	}


	/**
	 * apply for party onboarding: Personal and Enterprise*/
	public void clickDenoteCompliance(){
		//TestObject to = notificationPageUI.rdo_NotificationTypes("Referral")
		TestObject to = findTestObject('Object Repository/ProcessApprovalConfirmation/chk_TickToDenoteCompliance')
		try{
			//WebUI.delay(n)
			abstractPage.waitForElementPresent(to)
			abstractPage.clickToElementByJavascript(to)
			WebUI.delay(1)
			abstractPage.waitForElementVisibled(findTestObject('PartyOnboarding/btn_Next'))
			abstractPage.clickToElementByJavascript(findTestObject('PartyOnboarding/btn_Next'))
		}
		catch(Exception e) {
			println (e)
		}
	}

	public void clickClosePage(String pageName){
		abstractPage.closePage(pageName)
	}

	public void confirmDirectLoanEstablishment(String testCaseNumber, String results, String sequence, String loanRef, String step){
		TestObject rdo_commentItem= findTestObject('Object Repository/NotificationPage/rdo_commentItem',['description':"Approval"])
		TestObject btn_Next = findTestObject('Object Repository/NotificationPage/btn_Next')
		TestObject lbl_TotalItemsApproval = findTestObject('Object Repository/NotificationPage/lbl_TotalItemsApproval')
		TestObject askForApproval = findTestObject('Object Repository/NotificationPage/btn_askforapproval')
		TestObject lbl_AccountNumber = findTestObject('Object Repository/DirectLoanEstablishment/Verification/AfterApproved/lbl_AccountNumber')

		try{
			abstractPage.waitForElementPresent(rdo_commentItem)
			abstractPage.clickToElement(rdo_commentItem)
			abstractPage.waitForElementVisibled(btn_Next)
			abstractPage.clickToElement(btn_Next)

			/*Checking ask firosoft approval*/
			if(WebUI.waitForElementVisible(askForApproval, medTimeout, FailureHandling.OPTIONAL)){
				//asking fircosoft
				//WebUI.delay(shortTimeout)
				abstractPage.waitForElementVisibled(askForApproval)
				abstractPage.clickToElement(askForApproval)
				//insert results tmp into DB, will process after
				abstractPage.writeResultToDatabase(testCaseNumber, results, " ", abstractPage.getTestDate(), sequence,loanRef, 'TestData_UATA')
			}else{
				confirmDirectLoanEstablishmentWithoutAskingFircosoftApproval(loanRef, testCaseNumber, step, results, sequence)
			}
		}
		catch(Exception e){
			println(e)
		}
	}

	public void confirmDirectLoanEstablishmentWithoutAskingFircosoftApproval(String loanRef, String testCaseNumber, String step, String results, String sequence){
		TestObject rdo_commentItem= findTestObject('Object Repository/NotificationPage/rdo_commentItem',['description':'Approval'])
		TestObject btn_Next = findTestObject('Object Repository/NotificationPage/btn_Next')
		TestObject askForApproval = findTestObject('Object Repository/NotificationPage/btn_askforapproval')
		TestObject lbl_AccountNumber = findTestObject('Object Repository/DirectLoanEstablishment/Verification/AfterApproved/lbl_AccountNumber')
		String result ="", screenshots =""
		//get the results based on Loan Reference number
		ResultSet resultSet
		try{
			result = verifyLoanConfirmation( testCaseNumber, step, results, sequence)
			println "confirmDirectLoanEstablishmentWithoutAskingFircosoftApproval: result - " + result

			switch (loanRef) {
				case "None":
				//Fircosoft approved
					loanRef = abstractPage.getLoanReferenceFromLoanDetails(abstractPage.getAttributeValue(lbl_AccountNumber, "value"))
					resultSet = abstractPage.getResultFromDB(loanRef, "TestData_UATA")

					if(resultSet != null && !resultSet.next()){
						println("No data")
					}else{
						String stepRs = resultSet.getString(3)
						if(!stepRs.endsWith("|")){
							stepRs+= "|"
						}
						results = stepRs + step+","+result
					}

					screenshots = abstractPage.moveScreenShortToServer(resultSet.getString("TestCaseId"),  resultSet.getString("Sequence"), "", "")
				//remark is loanRefrence
					abstractPage.updateResultToDatabase(resultSet.getString("TestCaseId"), results, screenshots, resultSet.getString("Sequence"), abstractPage.getAttributeValue(lbl_AccountNumber, "value"), 'TestData_UATA')
					clickClosePage("Direct Loan Establishment")
					clickNotificationIcon()
					if(clickDescription("Approval")==0){
						break
					}
					break
				default:
				//Not asked fircosoft approval
					results += step+","+result
					screenshots = abstractPage.moveScreenShortToServer(testCaseNumber,  sequence, "", "")
				//remark is account number
					abstractPage.writeResultToDatabase(testCaseNumber, results, screenshots, abstractPage.getTestDate(), sequence, abstractPage.getAttributeValue(lbl_AccountNumber, "value"),'TestData_UATA')
					clickClosePage("Direct Loan Establishment")
					clickNotificationIcon()
					if(clickDescription("Approval")==0){
						break
					}
					break
			}
		}
		catch(Exception e){
			println (e)
		}
	}

	/** 2. Verify
	 * 2.1 Loan Status: Normal
	 * 2.2 Account Number is displayed
	 * 2.3 Sub-Product
	 * 2.4 Loan Amount
	 * 2.5 Loan Term
	 * 2.6 Rate of Interest*/
	public String verifyLoanConfirmation(String testCaseNumber, String step, String results, String sequence){
		TestObject askForApproval = findTestObject('Object Repository/NotificationPage/btn_askforapproval')
		TestObject lbl_AccountNumber = findTestObject('Object Repository/DirectLoanEstablishment/Verification/AfterApproved/lbl_AccountNumber')
		TestObject lbl_LoanStatus = findTestObject('Object Repository/DirectLoanEstablishment/Verification/AfterApproved/lbl_LoanStatus')
		TestObject lbl_RateOfInterest = findTestObject('Object Repository/DirectLoanEstablishment/Verification/AfterApproved/lbl_RateOfInterest')
		TestObject lbl_SubProduct = findTestObject('Object Repository/DirectLoanEstablishment/Verification/AfterApproved/lbl_SubProduct')
		TestObject lbl_Term = findTestObject('Object Repository/DirectLoanEstablishment/Verification/AfterApproved/lbl_Term')
		TestObject lbl_TermFrequence = findTestObject('Object Repository/DirectLoanEstablishment/Verification/AfterApproved/lbl_TermFrequency')
		TestObject lbl_LoanAmount = findTestObject('Object Repository/DirectLoanEstablishment/Verification/AfterApproved/lbl_LoanAmount')
		String result = ""
		int no = Integer.parseInt(GlobalVariable.no)
		//		ResultSet verifyData = null
		//
		//		verifyData = getResultTestDataFromDB(testCaseNumber)
		try{
			//			if(verifyData!= null && !verifyData.next()){
			//				println("No data")
			//			}else{
			if(WebUI.verifyElementPresent(findTestObject('DirectLoanEstablishment/Verification/lbl_ScreenName',['screenName':'Direct Loan Establishment']),longTimeout_01,FailureHandling.OPTIONAL)){
				abstractPage.waitForElementPresent(lbl_AccountNumber)
				abstractPage.waitForElementPresent(lbl_LoanStatus)
				abstractPage.waitForElementPresent(lbl_RateOfInterest)
				abstractPage.waitForElementPresent(lbl_SubProduct)
				abstractPage.waitForElementPresent(lbl_Term)
				abstractPage.waitForElementPresent(lbl_TermFrequence)
				abstractPage.waitForElementPresent(lbl_LoanAmount)

				String tmp_loanAmount = abstractPage.getAttributeValue(lbl_LoanAmount, "value").replace(",","")
				String tmp_loanStatus = abstractPage.getAttributeValue(lbl_LoanStatus, "value")
				String tmp_RateOfInterest = abstractPage.getAttributeValue(lbl_RateOfInterest, "value")
				String tmp_SubProduct = abstractPage.getAttributeValue(lbl_SubProduct, "value")
				String tmp_Term = abstractPage.getAttributeValue(lbl_Term, "value")
				String tmp_TermFrequence = abstractPage.getAttributeValue(lbl_TermFrequence, "value")

				String expected_loanAmount = test.getValue("loanAmount", no).trim()
				String expected_loanStatus = test.getValue("loanStatus", no).trim()
				String expected_RateOfInterest = test.getValue("rateInterest",no).trim()
				String expected_SubProduct = test.getValue("subProduct", no).trim()
				String expected_Term = test.getValue("termOfLoan", no).trim()
				String expected_TermFrequence = test.getValue("termOfLoanFrequency", no).trim()

				println ("tmp_loanAmount: " + tmp_loanAmount +  " tmp_loanStatus: " + tmp_loanStatus+ " tmp_RateOfInterest: "+ tmp_RateOfInterest + " tmp_SubProduct: "
						+tmp_SubProduct + " tmp_Term: " + tmp_Term + " tmp_TermFrequence: " + tmp_TermFrequence)

				BigDecimal bigAcctual_LoanAmount = new BigDecimal(tmp_loanAmount.trim())
				BigDecimal bigExpected_LoanAmount = new BigDecimal(expected_loanAmount)

				println ("[data]loanAmount: " +bigExpected_LoanAmount +  " |loanStatus: " + expected_loanStatus+ " |RateOfInterest: "+ expected_RateOfInterest
						+ " |SubProduct: "+expected_SubProduct + " |Term: " + expected_Term + " |TermFrequence: " + expected_TermFrequence)

				if((bigAcctual_LoanAmount == bigExpected_LoanAmount)  && tmp_loanStatus.trim().equals(expected_loanStatus) && tmp_RateOfInterest.trim().equals(expected_RateOfInterest)
				&& tmp_SubProduct.trim().equals(expected_SubProduct.trim()) && tmp_Term.trim().equals(expected_Term.trim()) && tmp_TermFrequence.trim().equals(expected_TermFrequence.trim())){
					result = "PASSED"
				}
				else {
					result = "FAILED"
				}
				abstractPage.takeScreenshotGetCurrenDate(testCaseNumber, step, result, sequence)
			}
			//			}
		}
		catch(Exception e){
			println(e)
		}
		return result
	}
	public void clickAskForApproval(String description){
		TestObject to= findTestObject('Object Repository/NotificationPage/btn_askforapproval')
		try{
			abstractPage.waitForElementVisibled(to)
			abstractPage.clickToElement(to)
		}
		catch(Exception e){
			println(e)
		}
	}

}