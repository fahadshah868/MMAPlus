import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.checkpoint.CheckpointFactory as CheckpointFactory
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as MobileBuiltInKeywords
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testcase.TestCaseFactory as TestCaseFactory
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testdata.TestDataFactory as TestDataFactory
import com.kms.katalon.core.testobject.ObjectRepository as ObjectRepository
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WSBuiltInKeywords
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUiBuiltInKeywords
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import internal.GlobalVariable as GlobalVariable

MobileBuiltInKeywords.verifyElementText(findTestObject('DashboardScreenElements/Validate_DashboardScreen'), 'Dashboard')

MobileBuiltInKeywords.tap(findTestObject('DashboardScreenElements/DaysDropdownMenu'), 0)

CustomKeywords.'com.ct.qa.keywords.KeywordsCollection.selectday'()

MobileBuiltInKeywords.tap(findTestObject('DashboardScreenElements/StartYourDay'), 0)

Mobile.delay(15, FailureHandling.STOP_ON_FAILURE)

MobileBuiltInKeywords.verifyElementExist(findTestObject('CommonScreenElements/Validate_MapScreen'), 0)

MobileBuiltInKeywords.tap(findTestObject('CommonScreenElements/Location_CheckIn'), 0)

Mobile.verifyElementExist(findTestObject('CommonScreenElements/Validate_InfoPopUP'), 0)

MobileBuiltInKeywords.tap(findTestObject('CommonScreenElements/InfoPopUp_NoButton'), 0)

MobileBuiltInKeywords.verifyElementText(findTestObject('DistributionPoint/Validate_RouteInfoScreen'), 'Route Info')

MobileBuiltInKeywords.tap(findTestObject('DistributionPoint/RouteInfoNextButton'), 0)

MobileBuiltInKeywords.verifyElementText(findTestObject('DistributionPoint/Validate_DistributionPointScreen'), 'Poster Quantity:')

CustomKeywords.'com.ct.qa.keywords.KeywordsCollection.visitdistributionPoint'()

MobileBuiltInKeywords.tap(findTestObject('DistributionPoint/DistributionPoint_SubmitButton'), 0)

MobileBuiltInKeywords.verifyElementText(findTestObject('DashboardScreenElements/Validate_DashboardScreen'), 'Dashboard')

