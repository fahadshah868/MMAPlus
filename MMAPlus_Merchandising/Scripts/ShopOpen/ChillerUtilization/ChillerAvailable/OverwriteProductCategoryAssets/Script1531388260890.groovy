import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.ct.qa.constants.ProjectConstants as ProjectConstants
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

MobileBuiltInKeywords.verifyElementText(findTestObject('ShopOpen/ChillerUtilization/ChillerAvailable/Validate_ProductCategoryAssetScreen'), 
    'Facing')

Mobile.tap(findTestObject('ShopOpen/ChillerUtilization/ChillerAvailable/Facing'), 0)

MobileBuiltInKeywords.verifyElementText(findTestObject('ShopOpen/ChillerUtilization/ChillerAvailable/Validate_FacingScreen'), 
    'Facing')

CustomKeywords.'com.ct.qa.keywords.ChillerKeywords.visitChillerAvailableProductsData'(ProjectConstants.CHILLER_OVERWRITEFACING)

MobileBuiltInKeywords.tap(findTestObject('ShopOpen/ChillerUtilization/ChillerAvailable/SubmitButton'), 0)

MobileBuiltInKeywords.verifyElementText(findTestObject('ShopOpen/ChillerUtilization/ChillerAvailable/Validate_ProductCategoryAssetScreen'), 
    'Facing')

MobileBuiltInKeywords.tap(findTestObject('ShopOpen/ChillerUtilization/ChillerAvailable/Depth'), 0)

MobileBuiltInKeywords.verifyElementText(findTestObject('ShopOpen/ChillerUtilization/ChillerAvailable/Validate_DepthScreen'), 
    'Depth')

CustomKeywords.'com.ct.qa.keywords.ChillerKeywords.visitChillerAvailableProductsData'(ProjectConstants.CHILLER_OVERWRITEDEPTH)

MobileBuiltInKeywords.tap(findTestObject('ShopOpen/ChillerUtilization/ChillerAvailable/SubmitButton'), 0)

MobileBuiltInKeywords.verifyElementText(findTestObject('ShopOpen/ChillerUtilization/ChillerAvailable/Validate_ProductCategoryAssetScreen'), 
    'Facing')

MobileBuiltInKeywords.tap(findTestObject('ShopOpen/ChillerUtilization/ChillerAvailable/StockCount'), 0)

MobileBuiltInKeywords.verifyElementText(findTestObject('ShopOpen/ChillerUtilization/ChillerAvailable/Validate_StockCountScreen'), 
    'Stock Count')

CustomKeywords.'com.ct.qa.keywords.ChillerKeywords.visitChillerAvailableProductsData'(ProjectConstants.CHILLER_OVERWRITESTOCKCOUNT)

MobileBuiltInKeywords.tap(findTestObject('ShopOpen/ChillerUtilization/ChillerAvailable/SubmitButton'), 0)

MobileBuiltInKeywords.verifyElementText(findTestObject('ShopOpen/ChillerUtilization/ChillerAvailable/Validate_ProductCategoryAssetScreen'), 
    'Facing')

MobileBuiltInKeywords.tap(findTestObject('ShopOpen/ChillerUtilization/ChillerAvailable/PorductCategoryActions_BackButton'), 
    0)

MobileBuiltInKeywords.verifyElementText(findTestObject('ShopOpen/ChillerUtilization/ChillerAvailable/Validate_ChillerProductsCategoriesScreen'), 
    'Category:Chiller Utilization')

