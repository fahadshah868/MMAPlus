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

'validate dashboard screen appearance'
MobileBuiltInKeywords.verifyElementText(findTestObject('DashboardScreenElements/Validate_DashboardScreen'), 'Dashboard')

'tap on days drop down menu to select the day'
MobileBuiltInKeywords.tap(findTestObject('DashboardScreenElements/DaysDropdownMenu'), 0)

'select the current day for further proceed'
CustomKeywords.'com.ct.qa.keywords.ab.selectday'()

'tap on start your day button'
MobileBuiltInKeywords.tap(findTestObject('DashboardScreenElements/StartYourDay'), 0)

'delay of 15 seconds for check in through map screen'
Mobile.delay(15, FailureHandling.STOP_ON_FAILURE)

'validate map screen appearance'
MobileBuiltInKeywords.verifyElementExist(findTestObject('CommonScreenElements/Validate_MapScreen'), 0)

'tap on check in button through map'
MobileBuiltInKeywords.tap(findTestObject('CommonScreenElements/Location_CheckIn'), 0)

'validate confirm popup appearance'
Mobile.verifyElementExist(findTestObject('CommonScreenElements/Validate_InfoPopUP'), 0)

'tap on confirm popup no button'
MobileBuiltInKeywords.tap(findTestObject('CommonScreenElements/InfoPopUp_NoButton'), 0)

'validate channel summary detail screen appearance'
MobileBuiltInKeywords.verifyElementText(findTestObject('DistributionPoint/Validate_RouteInfoScreen'), 'Route Info')

'tap on next button to further proceed'
MobileBuiltInKeywords.tap(findTestObject('DistributionPoint/RouteInfoNextButton'), 0)

'validate distribution point screen appearance'
MobileBuiltInKeywords.verifyElementText(findTestObject('DistributionPoint/Validate_DistributionPointScreen'), 'Poster Quantity:')

'enter quantity of products on distribution point'
CustomKeywords.'com.ct.qa.keywords.DistributionPointKeywords.visitdistributionPoint'()

'tap on submit button after enter the quantity of products on distribution point'
MobileBuiltInKeywords.tap(findTestObject('DistributionPoint/DistributionPoint_SubmitButton'), 0)

