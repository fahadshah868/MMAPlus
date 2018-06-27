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

Mobile.swipe(0, 200, 0, 600)

Mobile.swipe(0, 200, 0, 600)

MobileBuiltInKeywords.verifyElementText(findTestObject('Validate_ShopListScreen'), 'Shops on Route')

CustomKeywords.'customkeywords.CustomKeywordsCollection.selectShopWithChillerUtilization'()

MobileBuiltInKeywords.verifyElementText(findTestObject('Validate_ShopOptionsScreen'), 'Options')

MobileBuiltInKeywords.tap(findTestObject('StartWorking'), 0)

MobileBuiltInKeywords.verifyElementExist(findTestObject('CommonScreenElements/Validate_InfoPopUP'), 0)

MobileBuiltInKeywords.tap(findTestObject('CommonScreenElements/InfoPopUp_YesButton'), 0)

Mobile.waitForElementPresent(findTestObject('CommonScreenElements/Validate_MapScreen'), 60)

MobileBuiltInKeywords.tap(findTestObject('CommonScreenElements/Location_CheckIn'), 0)

Mobile.verifyElementExist(findTestObject('CommonScreenElements/Validate_InfoPopUP'), 0)

MobileBuiltInKeywords.tap(findTestObject('CommonScreenElements/InfoPopUp_NoButton'), 0)

MobileBuiltInKeywords.verifyElementExist(findTestObject('ShopActions_List'), 0)

