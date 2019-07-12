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
import qa.constants.ProjectConstants as ProjectConstants

Mobile.verifyElementText(findTestObject('ExpiryIssue/ExpiredProduct/Validate_ExpiredProductScreen', [('package') : ProjectConstants.PACKAGENAME]), 
    'Expire Product List')

CustomKeywords.'qa.keywords.ExpiryIssue.validateProducts'()

Mobile.swipe(0, 200, 0, 500)

Mobile.swipe(0, 200, 0, 500)

CustomKeywords.'qa.keywords.ExpiryIssue.visitExpiredProducts'(ProjectConstants.CHANNEL_EXPIREDPRODUCT)

