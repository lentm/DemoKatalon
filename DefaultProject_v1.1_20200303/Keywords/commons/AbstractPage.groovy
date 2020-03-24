package commons
import java.sql.Connection
import java.sql.SQLException
import java.text.SimpleDateFormat
import java.util.Date

import javax.xml.bind.annotation.XmlElementDecl.GLOBAL

import org.openqa.selenium.By
import org.openqa.selenium.JavascriptExecutor as JavascriptExecutor
import org.openqa.selenium.Keys
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.util.KeywordUtil
import com.kms.katalon.core.webui.common.WebUiCommonHelper
import com.kms.katalon.core.webui.driver.DriverFactory
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.testcase.TestCase
import com.kms.katalon.core.testdata.TestData
import internal.GlobalVariable
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import org.openqa.selenium.ElementClickInterceptedException
import jcifs.smb.NtlmPasswordAuthentication
import jcifs.smb.SmbException
import jcifs.smb.SmbFile
import jcifs.smb.SmbFileInputStream
import jcifs.smb.SmbFileOutputStream
import java.sql.*
import java.io.File
import java.text.DateFormat
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.lang.String as String
import java.awt.Robot
import java.awt.Toolkit
import java.awt.datatransfer.StringSelection
import java.awt.event.KeyEvent

public class AbstractPage {
	private int longTimeout =20;
	private int mediumTimeout =10;
	private int minTimeout = 5
	private FailureHandling failureHandling = FailureHandling.STOP_ON_FAILURE
	//private WebDriver driver = DriverFactory.getWebDriver()
	private String userDir = System.getProperty("user.dir");
	private String filePath = userDir +  "\\PreData\\";
	Statement stmt

	private void inputOpenScreenMenuSearch(String screenName){
		//TestObject menuSearch_txt = findTestObject('Object Repository/commons/Menu_Search')
		TestObject menuSearch_txt = findTestObject('Object Repository/commons/Menu_Search')
		waitForElementVisibled(menuSearch_txt)
		highlightElement(menuSearch_txt)
		sendkeyToElement(menuSearch_txt,screenName + Keys.chord(Keys.ENTER))
	}

	public void waitForElementVisibled(TestObject to){
		//		try{
		WebUI.waitForElementVisible(to, longTimeout, failureHandling)
		//		}
		//		catch(Exception e){
		//			println(e)
		//			excutedFailedCase("AbstractPage.sendkeyToElement")
		//		}
	}
	public void waitForElementPresent(TestObject to){
		WebUI.waitForElementPresent(to, longTimeout, failureHandling)
	}
	public void waitForElementClickable(TestObject to){

		WebUI.waitForElementPresent(to, longTimeout, failureHandling)
	}
	public void waitForElementInvisible(TestObject to){

		WebUI.waitForElementPresent(to, longTimeout, failureHandling)

	}
	public void waitForAlertPresence(TestObject to){

		WebUI.waitForAlert(longTimeout, failureHandling)

	}


	/*Web Element*/   	
	public void sendkeyToElement(TestObject to, String values){
		int count = 0
		String invalidvalue =""
		try {
			highlightElement(to)
			WebUI.sendKeys(to, values, failureHandling)
			invalidvalue = getAttributeValue(to, "aria-invalid")
			//println "invalidvalue: " + invalidvalue
			if(invalidvalue.equals("true")){
				clearText(to)
				WebUI.sendKeys(to, values, failureHandling)
			}
		}catch(Exception e){
			println(e)
			excutedFailedCase("AbstractPage.sendkeyToElement")
		}

	}

	private void clickToElement(TestObject to){
		int count = 0
		try{
			if(WebUI.click(to, failureHandling)){
				count=0
			}
		}
		catch(ElementClickInterceptedException e){
			if(count <3){
				clickToElement(to)
				count =+1
			}
		}
		catch(Exception e){
			println(e)
			excutedFailedCase("AbstractPage.clickToElement")
		}
	}
	private String getAttributeValue(TestObject to, String attributename){
		String values
		try{
			highlightElement(to)
			values = WebUI.getAttribute(to, attributename, failureHandling)
		}
		catch(Exception e){
			println(e)
			excutedFailedCase("AbstractPage.getAttributeValue")
		}

		//println "tracking values: " + values
		return values
	}

	private String getTextElement(TestObject to){
		String values=""
		try{
			highlightElement(to)
			values = WebUI.getText(to, failureHandling)
		}
		catch(Exception e){
			println(e)
			excutedFailedCase("AbstractPage.getTextElement")
		}
		return values
	}

	private int countElementNumberOfTheList(TestObject to, String locator){
		String values = ""
		try{
			WebDriver driver = DriverFactory.getWebDriver()
			List <WebElement> listElements = driver.findElements(By.xpath(locator))
			values = listElements.size()
		}
		catch(Exception e){
			println(e)
			excutedFailedCase("AbstractPage.countElementNumberOfTheList")
		}
		return values
	}

	private void checkTheCheckbox(TestObject to){
		String valuesattribute = WebUI.getAttribute(to, "aria-checked")
		try{
			highlightElement(to)
			if(valuesattribute.equals("false")){
				WebUI.check(to, failureHandling)
			}
		}
		catch(Exception e){
			println(e)
			excutedFailedCase("AbstractPage.checkTheCheckbox")
		}

	}

	private void clickElementBySelenium(WebElement element){
		try{
			if(!element.isSelected()){
				element.click()
			}
		}
		catch(Exception e){
			println(e)
			excutedFailedCase("AbstractPage.clickElementBySelenium")
		}
	}

	private void unclickElementBySelenium(WebElement element){

		if(element.isSelected()){
			element.click()
		}
	}

	private void uncheckTheCheckbox(TestObject to){
		String valuesattribute = WebUI.getAttribute(to, "aria-checked")
		try{
			highlightElement(to)
			//println "value: " + valuesattribute
			if(valuesattribute.equals("true")){
				WebUI.uncheck(to, failureHandling)
				WebUI.click(to, failureHandling)
			}
		}
		catch(Exception e){
			println(e)
			excutedFailedCase("AbstractPage.uncheckTheCheckbox")
		}

	}

	private void switchToIframe(TestObject to){
		WebUI.switchToFrame(to, longTimeout, failureHandling)
	}
	private void doubleClickToElement(TestObject to){
		WebUI.doubleClick(to, failureHandling)
	}
	private void moveMouseToElement(TestObject to){
		WebUI.mouseOver(to, failureHandling)
	}
	private void rightClick(TestObject to){
		WebUI.rightClick(to, failureHandling)
	}
	private void dragAndDropSourceTarget(TestObject from, TestObject to){
		WebUI.dragAndDropToObject(from, to,failureHandling)
	}

	// JavascriptExecutor
	private void excuteJavascriptToBrowser(String scripts){
		WebUI.executeJavaScript(scripts, null, FailureHandling.STOP_ON_FAILURE)
	}

	private void scrollDownDropDownList(TestObject to){
		WebElement element = WebUiCommonHelper.findWebElement(to, longTimeout)
		WebUI.executeJavaScript("arguments[0].scrollIntoView(true);", Arrays.asList(element))
	}

	private void clickToElementByJavascript(TestObject to){
		try{
			WebElement element = WebUiCommonHelper.findWebElement(to, longTimeout)
			WebUI.executeJavaScript("arguments[0].click();", Arrays.asList(element))
		}
		catch(Exception e){
			println(e)
			excutedFailedCase("AbstractPage.clickToElementByJavascript")
		}
	}
	private void sendKeyToElementByJavascript(TestObject to, String values){
		try{
			WebElement element = WebUiCommonHelper.findWebElement(to, longTimeout)
			WebUI.executeJavaScript("arguments[0].setAttribute('value', '" + values + "')", Arrays.asList(element))
		}
		catch(Exception e){
			println(e)
			excutedFailedCase("AbstractPage.sendKeyToElementByJavascript")
		}

	}
	private void removeAttributeInDOM(TestObject to, String attributeName){

		WebElement element = WebUiCommonHelper.findWebElement(to, longTimeout)
		WebUI.executeJavaScript("arguments[0].removeAttribute('" + attributeName + "'", Arrays.asList(element))

	}
	private void setAttributeInDOM(TestObject to, String attributeName, String values){

		WebElement element = WebUiCommonHelper.findWebElement(to, longTimeout)
		WebUI.executeJavaScript("arguments[0].setAttribute('" + attributeName + "','" + values + "'", Arrays.asList(element))

	}
	private void navigateToUrlByJS(TestObject to, String url){
		WebElement element = WebUiCommonHelper.findWebElement(to, longTimeout)
		WebUI.executeJavaScript("windown.location = '" + url + "'")

	}
	private void highlightElement (TestObject objectto) {
		WebDriver driver = DriverFactory.getWebDriver()
		WebElement element = WebUiCommonHelper.findWebElement(objectto, 20)
		try{
		JavascriptExecutor js = (JavascriptExecutor) driver
		js.executeScript("arguments[0].style.border='2px groove red'",element)
		}
		catch(Exception e){
			println(e)
			excutedFailedCase("AbstractPage.highlightElement")
		}
	}

	/**
	 * color: red, yel (yellow), blu(blue)*/
	private void highlightElementByColor (TestObject objectto, String color) {
		WebDriver driver = DriverFactory.getWebDriver()
		WebElement element = WebUiCommonHelper.findWebElement(objectto, 20);
		JavascriptExecutor js = (JavascriptExecutor) driver
		switch (color){
			case "red":
				js.executeScript("arguments[0].style.border='2px groove red'",element)
				break
			case "yel":
				js.executeScript("arguments[0].style.border='2px solid yellow'",element)
				break
			case "blu":
				js.executeScript("arguments[0].style.border='2px solid blue'",element)
				break
		}
	}

	private String[] splitString (String valueString) {
		String[] ar_String =  valueString.split("\\|");
		return ar_String;
	}

	private void clearText(TestObject to){
		try{
			WebUI.waitForElementVisible(to, longTimeout)
			WebUI.clearText(to, failureHandling)
		}
		catch(Exception e){
			println(e)
			excutedFailedCase("AbstractPage.clearText")
		}
	}

	private String[][] getStepAndResultsFromString(String str){
		String[] original_list = str.split('\\|')
		String [] temp
		int number = original_list.length
		String[][] row_results = new String[number][2]

		for (int i = 0; i < row_results.length; i++) {
			for (int j = 0; j < row_results[i].length; j++) {
				temp = original_list[i].split(",")
				row_results[i][j] = temp[j]
			}
		}
		return row_results
	}

	private boolean isVerifyCharacterInTheString(String originalString, String findString){
		boolean values =true
		if(originalString.contains(findString)){
			values = true
		}
		else {values =false}
		return values
	}

	private String getAllTextInThePage (){
		String values = WebUI.executeJavaScript("return document.documentElement.innerText;",null).toString()
		return values
	}

	private String replaceArrayIndexOutOfBoundsException(String s_variable, int i, String _replace){
		String[] ar_variable = s_variable.split("\\|");
		String temp = ''
		try{
			temp =  ar_variable[i].trim()
		}catch(ArrayIndexOutOfBoundsException e){
			temp = _replace
		}
		return temp
	}

	private String changeFormatDate(String strDate, String patternFrom, String patternTo){
		Date date1
		String date2 =""

		date1 = new SimpleDateFormat(patternFrom).parse(strDate)
		//println "date1: " + date1
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(patternTo)
		date2 = simpleDateFormat.format(date1)
		return date2
	}

	private String getAlphaNumericString(int n)
	{
		StringBuilder sb = new StringBuilder(n);
		// chose a Character random from this String
		String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"+
				"0123456789"+
				"abcdefghijklmnopqrstuvxyz";
		// create StringBuffer size of AlphaNumericString
		for (int i = 0; i < n; i++) {
			// generate a random number between
			// 0 to AlphaNumericString variable length
			//random() returns a random number from 0.0 to 0.9, so we multiply it by the length of string, (int) get the integer
			int index = (int)(AlphaNumericString.length()* Math.random());
			// add Character one by one in end of sb
			sb.append(AlphaNumericString.charAt(index));
			println "sb: " + sb.toString()
		}
		return sb.toString()
	}

	private String getcurrentDate(){
		Date date = new Date()
		println date
		SimpleDateFormat fm = new SimpleDateFormat('yyyyMMdd_hhmmss');
		return fm.format(date)
	}
	private void createFolder(String filepath){
		File file = new File(filepath)
		if(!file.exists()){
			file.mkdir()
		}
	}

	private void closePage(String pageName){
		TestObject to = findTestObject('Object Repository/commons/btn_ClosePage',['pageName':pageName])
		waitForElementVisibled(to)
		clickToElement(to)
	}

	private void takeScreenshotGetCurrenDate(String testCaseNumber, String step, String results, String sequence){
		String userDir = System.getProperty("user.dir");
		String filepath = userDir + "\\Results\\"+testCaseNumber
		createFolder(filepath)
		String fileName = sequence+ "_"+testCaseNumber +"_" + step + "_" + results + "_"+ getcurrentDate()+".png"
		//2_AUT-2_2_PASSED_10291127_120102.png
		String screenshotName = filepath + "\\"+fileName
		println "screenshotName: " + screenshotName
		WebUI.takeScreenshot(screenshotName)
	}

	/*UploadFile using Robot 20191128_v2.0*/
	private void uploadFile(TestObject to, String filePath){
		clickToElement(to)
		WebUI.delay(3)
		StringSelection ss = new StringSelection(filePath)
		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(ss, null)
		WebUI.delay(1)
		Robot robot = new Robot()
		robot.keyPress(KeyEvent.VK_ENTER)
		robot.keyRelease(KeyEvent.VK_ENTER)
		robot.keyPress(KeyEvent.VK_CONTROL)
		robot.keyPress(KeyEvent.VK_V)
		robot.keyRelease(KeyEvent.VK_V)
		robot.keyRelease(KeyEvent.VK_CONTROL)
		robot.keyPress(KeyEvent.VK_ENTER)
		robot.keyRelease(KeyEvent.VK_ENTER)
	}

	private String moveScreenShortToServer(String testCaseID,String sequence, String fromTime, String toTime ){
		String userDir = System.getProperty("user.dir");
		String filePath = userDir + "\\Results\\"+testCaseID
		String listFileName =""
		SmbFileOutputStream sfos = null;
		BufferedInputStream bis = null;
		String username = "HP"
		String pw = "123456"
		String shareFolder = "172.24.175.11/Screenshots/"
		String targetPath = filePath
		try {
			NtlmPasswordAuthentication auth = null;
			if(!username.equals("") && !pw.equals("")){
				auth = new NtlmPasswordAuthentication(username + ":" + pw);
			}
			if (!shareFolder.endsWith("/")) {
				shareFolder = shareFolder + "/";
			}
			if (!targetPath.endsWith("/")) {
				targetPath = targetPath + "/";
			}
			String sourcePath = shareFolder + testCaseID;
			SmbFile sourceLocation = null;
			if(auth == null){
				sourceLocation = new SmbFile("smb://" + sourcePath);
			}else{
				sourceLocation = new SmbFile("smb://" + sourcePath, auth);
			}
			if (!sourceLocation.exists()) {
				sourceLocation.mkdir();
			}

			File folder = new File(targetPath)
			println targetPath + "++++++"+folder.getAbsolutePath()
			File[] listOfFiles = folder.listFiles()
			for (int i = 0; i < listOfFiles.length; i++) {
				if (listOfFiles[i].isFile()) {
					String[] fileName = listOfFiles[i].getName().split('\\_')
					if(fileName[0]==sequence && fileName[1]==testCaseID) {
						listFileName+= listOfFiles[i].getName()+"|"
						println listFileName
						try {
							File sourceFile = new File(targetPath+listOfFiles[i].getName())
							bis = new BufferedInputStream(new FileInputStream(sourceFile))
							byte[] fileBytes = new byte[(int) sourceFile.length()]
							bis.read(fileBytes, 0, fileBytes.length)

							String sourcePathFile = sourcePath +"/" + listOfFiles[i].getName();
							if(auth == null){
								sourceLocation = new SmbFile("smb://" + sourcePathFile);
							}else{
								sourceLocation = new SmbFile("smb://" + sourcePathFile, auth);
							}

							sfos = new SmbFileOutputStream(sourceLocation)
							sfos.write(fileBytes)
							sfos.flush()
						}catch (SmbException e) {
							e.printStackTrace()
						}finally {
							bis.close()
							sfos.close()
						}
					}
				}
			}

		} catch (MalformedURLException ex) {
			System.out.println(ex.getMessage());
		} catch (SmbException ex) {
			System.out.println(ex.getMessage());
		} catch (IOException ex) {
			System.out.println(ex.getMessage());
		}
		return listFileName
	}

	private String getTestDate() {
		Date date = new Date()
		SimpleDateFormat sdf = new SimpleDateFormat('yyyy-MM-dd HH:mm:ss')
		return sdf.format(date)
	}

	public void clickNext(){
		TestObject to = findTestObject('Object Repository/commons/btn_Next')
		waitForElementVisibled(to)
		clickToElement(to)
	}

	public void clickPrevious(){
		TestObject to = findTestObject('Object Repository/commons/btn_Next')
		waitForElementVisibled(to)
		clickToElement(to)
	}

	private String getCurrentDateByFormat(String format) {
		Date date
		SimpleDateFormat sdf
		date = new Date()
		sdf = new SimpleDateFormat(format)
		return sdf.format(date)
	}

	private void highlightElementCorrectly(TestObject to, String result) {

		switch(result.toUpperCase()){
			case "TRUE":
				highlightElementByColor(to, "blu")
				break
			case "FALSE":
				highlightElementByColor(to, "red")
				break
		}

	}

	/**
	 * This method allows to get amount from element by gettext and convert that amount to BigDecimal. */
	private BigDecimal convertAmountStringToBigDecimalByAttribute(TestObject to) {
		BigDecimal big_amount = null

		String str_amount = getTextElement(to).replace(",","")
		if(!str_amount.trim().equals("")){
			if(str_amount.isNumber()){
				big_amount = new BigDecimal(str_amount)
			}else{
				big_amount = 0
			}
		}else{
			big_amount = -1
		}
		return big_amount
	}

	private BigDecimal convertStringToBigDecimal(String str) {
		BigDecimal big_amount = null

		if(str.trim().isNumber()){
			big_amount = new BigDecimal(str)
		}else{
			big_amount = 0
			println "The input value is not number"
		}
		return big_amount
	}
	/*-----------Database -------------*/

	public ResultSet getResultFromDB(String remark, String databaseName){
		Connection sqlConn = this.getSQLServerconnection(databaseName)
		Statement statement = sqlConn.createStatement()
		ResultSet rs = null
		if(remark != null || !remark.equals('')){
			String sql = "select * from dbo.TD_TESTCASERESULT where Remark = '"+remark+"'"
			//String sql = "insert into dbo.TD_TESTCASERESULT values('AUT_2','1,PASS|2,PASS','',SYSDATETIME(),3)"
			println sql
			rs = statement.executeQuery(sql)
		}

		return rs
	}

	private String getLoanReferenceFromLoanDetails(String accountID){
		Connection con = getSQLServerconnection("TestData_UATA")
		stmt = con.createStatement()
		String sql = "SELECT * FROM OPENQUERY([UATA], 'select LOANREFERENCE from WASADMIN.LOANDETAILS where accountid = ''"+accountID+"''')"
		ResultSet rs = stmt.executeQuery(sql)
		if(rs.next()){
			String loanReference = rs.getString("LOANREFERENCE")// get the loanReference
			println "LOANREFERENCE: " + loanReference
			return loanReference
		}else{
			println 'data not found'
		}
	}

	public Connection getSQLServerconnection(String database) {
		String Url = "jdbc:sqlserver://172.24.175.11;DatabaseName="+ database +";user=sa;Password=@dmin@123";
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver")
			Connection connection = DriverManager.getConnection(Url)
			return connection
		}
		catch(Exception e){
			println(e)
		}
	}

	public int writeResultToDatabase(String testcaseID, String stepStatus, String stepScreenshort, String testDate,String sequence, String remark, String databaseName){
		Connection sqlConn = this.getSQLServerconnection(databaseName)
		println sqlConn
		Statement statement = sqlConn.createStatement()
		if(testcaseID==null || testcaseID.equals("")) {
			println"customerId is null"
		}
		else {
			String sql = "Insert into dbo.TD_TESTCASERESULT values ('"+testcaseID+"','"+stepStatus+"','"+stepScreenshort+"','"+testDate+"','"+ sequence +"','"+remark+"')"
			int rowCount = statement.executeUpdate(sql)
			return rowCount;
		}
		return 0;
	}

	public int updateResultToDatabase(String testcaseID, String stepStatus, String stepScreenshort,String sequence,String remark, String databaseName){
		Connection sqlConn = this.getSQLServerconnection(databaseName)
		Statement statement = sqlConn.createStatement()
		if(testcaseID==null || testcaseID.equals("")) {
			println"testcaseID is null"
		}
		else {

			String sql = "UPDATE TestData_UATA.dbo.TD_TESTCASERESULT SET StepStatus = N'"+stepStatus+"', StepScreenShort = N'"+
					stepScreenshort+"', Remark = N'"+remark+"' WHERE TestCaseId = '"+testcaseID+"' and Sequence = "+sequence
			int rowCount = statement.executeUpdate(sql)
			return rowCount
		}
		return 0
	}

	public ResultSet getResultTestDataFromDB(String sql, String databaseName){
		Connection sqlConn = this.getSQLServerconnection(databaseName)
		Statement statement = sqlConn.createStatement()
		ResultSet rs = null
		rs = statement.executeQuery(sql)

		return rs
	}

	//add year/month/day
	/**
	 ** parameter number: the number will be added 
	 * parameter frequence: [D]day, [M]month or [Y]year 
	 * parameter formateDate: e.g ddMMyyyy HH:mm:ss 17012020 04:14:23
	 * parameter checkWeekend: 1 allows weekends, 2 doesn't allow weekend
	 * note: applicable for java 1.8*/
	private String addDaysToDate(int number, String frequency, String formatDate, int checkWeekend){
		LocalDateTime today =  LocalDateTime.now() //get date today
		LocalDateTime addDate = null
		String formatDateTime = ""
		switch(frequency){
			case "D":
				addDate = today.plusDays(number)
				println "D :" + addDate
				break
			case "M":
				addDate = today.plusMonths(number)
				println "M :" + addDate
				break
			case "Y":
				addDate = today.plusYears(number)
				println "Y :" + addDate
				break
		}
		String dayOfWeek = addDate.getDayOfWeek()
		//println "dayOfWeek: " + dayOfWeek
		if(checkWeekend==2){
			if(dayOfWeek.equals("SATURDAY")){
				//Sat
				addDate = addDate.plusDays(2)
			}else{
				if(dayOfWeek.equals("SUNDAY")){
					//Sun
					addDate = addDate.plusDays(1)
				}
			}
		}
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(formatDate)
		formatDateTime = addDate.format(formatter)

		return formatDateTime
	}

	private String returnStepResult(boolean inputResult, String inputStep){
		String outputResults =""
		if(inputResult){
			outputResults += inputStep + ",PASSED|"
		}else{
			outputResults += inputStep + ",FAILED|"
		}
		println "result: " + outputResults
		return outputResults
	}

	private String convertResultFromBooleanToString(boolean result){
		String str_result = ""
		if(result){
			str_result = "PASSED"
		}else{
			str_result = "FAILED"
		}
		return str_result
	}

	private void excutedFailedCase(String remark){
		//take screenshot
		String failedStep = "FAILED"
		takeScreenshotGetCurrenDate(GlobalVariable.testCaseID, GlobalVariable.step,failedStep, GlobalVariable.sequence)
		//get all screenshots
		String screenshots = moveScreenShortToServer(GlobalVariable.testCaseID, GlobalVariable.sequence, "", "")
		//write all results into DB
		if(GlobalVariable.results.toString().endsWith("|")){
			GlobalVariable.results += GlobalVariable.step+","+failedStep
		}else{
			GlobalVariable.results += "|"+GlobalVariable.step+","+failedStep
		}
		writeResultToDatabase(GlobalVariable.testCaseID, GlobalVariable.results, screenshots, getTestDate() ,GlobalVariable.sequence, remark, "TestData_UATA")
		KeywordUtil.markFailedAndStop(remark)
	}

}

