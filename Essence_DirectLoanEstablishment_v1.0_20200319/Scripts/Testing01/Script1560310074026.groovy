import java.text.DateFormat as DateFormat
import java.text.SimpleDateFormat as SimpleDateFormat
import org.apache.poi.ss.usermodel.Cell as Cell
import org.apache.poi.xssf.usermodel.XSSFRow as XSSFRow
import org.apache.poi.xssf.usermodel.XSSFSheet as XSSFSheet
import org.apache.poi.xssf.usermodel.XSSFWorkbook as XSSFWorkbook
import java.lang.Integer as Integer
import java.lang.String as String
import commons.AbstractPage as AbstractPage
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import internal.GlobalVariable as GlobalVariable
import java.lang.reflect.Method
import pageObjects.DirectLoanEstablishment as FDReuse

FDReuse openFD= new FDReuse()

//AbstractPage abd = new AbstractPage()
//
//String colname = '5000000 | 3000000'
//
//String kkkk = 'Personal | A000000001'
//
//String[] kb = abd.splitString(colname)
//
//println('kb: ' + kb.length)
//
//for (int i = 0; i < kb.length; i++) {
//    println(('kb: ' + ' i ') + (kb[i]))
//}
//
//WebUI.callTestCase(findTestCase('Login'), [('url') : 'http://172.24.192.22:9080/uxp/rt/html/login.html?locale=en-gb', ('username') : 'user31'
//        , ('password') : 'user31', ('active') : 'Yes'], FailureHandling.STOP_ON_FAILURE)
//
//WebUI.callTestCase(findTestCase('LogOutFromSystem'), [:], FailureHandling.STOP_ON_FAILURE)
//
//WebUI.callTestCase(findTestCase('ApprovalDataPreparation'), [('description') : ''], FailureHandling.STOP_ON_FAILURE)
//
//WebUI.callTestCase(findTestCase('ConfirmDataPreparation_OpenFD'), [('description') : '', ('module') : ''], FailureHandling.STOP_ON_FAILURE)


Class classObject = openFD.class
Method[] methods = classObject.getMethods()
String mehodName = ""
for(Method method: methods){
	mehodName += method.getName() + "\n"
}
println "methodName: " + mehodName



