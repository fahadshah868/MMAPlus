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

'validate chiller product categories detail screen appearance'
MobileBuiltInKeywords.verifyElementText(findTestObject('ShopOpen/ChillerUtilization/ChillerAvailable/Validate_ProductCategoryAssetScreen' , [('package') : ProjectConstants.PACKAGENAME]), 
    'Facing')

'tap on "Facing"'
Mobile.tap(findTestObject('ShopOpen/ChillerUtilization/ChillerAvailable/Facing' , [('package') : ProjectConstants.PACKAGENAME]), 0)

'validate facing detail screen appearance'
MobileBuiltInKeywords.verifyElementText(findTestObject('ShopOpen/ChillerUtilization/ChillerAvailable/Validate_FacingScreen' , [('package') : ProjectConstants.PACKAGENAME]), 
    'Facing')

'overwrite chiller products with facing'
CustomKeywords.'com.ct.qa.keywords.ChillerProductsDataKeywords.visitChillerAvailableProductsData'(ProjectConstants.CHILLER_OVERWRITEFACING, 
    'Facing')

'tap on submit button'
MobileBuiltInKeywords.tap(findTestObject('ShopOpen/ChillerUtilization/ChillerAvailable/SubmitButton' , [('package') : ProjectConstants.PACKAGENAME]), 0)

'validate chiller product categories detail screen appearance'
MobileBuiltInKeywords.verifyElementText(findTestObject('ShopOpen/ChillerUtilization/ChillerAvailable/Validate_ProductCategoryAssetScreen' , [('package') : ProjectConstants.PACKAGENAME]), 
    'Facing')

'tap on "Depth"'
MobileBuiltInKeywords.tap(findTestObject('ShopOpen/ChillerUtilization/ChillerAvailable/Depth' , [('package') : ProjectConstants.PACKAGENAME]), 0)

'validate depth detail screen appearance'
MobileBuiltInKeywords.verifyElementText(findTestObject('ShopOpen/ChillerUtilization/ChillerAvailable/Validate_DepthScreen' , [('package') : ProjectConstants.PACKAGENAME]), 
    'Depth')

'overwrite chiller products with depth'
CustomKeywords.'com.ct.qa.keywords.ChillerProductsDataKeywords.visitChillerAvailableProductsData'(ProjectConstants.CHILLER_OVERWRITEDEPTH, 
    'Depth')

'tap on submit button'
MobileBuiltInKeywords.tap(findTestObject('ShopOpen/ChillerUtilization/ChillerAvailable/SubmitButton' , [('package') : ProjectConstants.PACKAGENAME]), 0)

'validate chiller product categories detail screen appearance'
MobileBuiltInKeywords.verifyElementText(findTestObject('ShopOpen/ChillerUtilization/ChillerAvailable/Validate_ProductCategoryAssetScreen' , [('package') : ProjectConstants.PACKAGENAME]), 
    'Facing')

'tap on "Stock Count"'
MobileBuiltInKeywords.tap(findTestObject('ShopOpen/ChillerUtilization/ChillerAvailable/StockCount' , [('package') : ProjectConstants.PACKAGENAME]), 0)

'validate stock count detail sceen appearance'
MobileBuiltInKeywords.verifyElementText(findTestObject('ShopOpen/ChillerUtilization/ChillerAvailable/Validate_StockCountScreen' , [('package') : ProjectConstants.PACKAGENAME]), 
    'Stock Count')

'overwrite chiller products with stock count'
CustomKeywords.'com.ct.qa.keywords.ChillerProductsDataKeywords.visitChillerAvailableProductsData'(ProjectConstants.CHILLER_OVERWRITESTOCKCOUNT, 
    'Stock Count')

'tap on submit button'
MobileBuiltInKeywords.tap(findTestObject('ShopOpen/ChillerUtilization/ChillerAvailable/SubmitButton' , [('package') : ProjectConstants.PACKAGENAME]), 0)

'validate chiller product categories detail screen appearance'
MobileBuiltInKeywords.verifyElementText(findTestObject('ShopOpen/ChillerUtilization/ChillerAvailable/Validate_ProductCategoryAssetScreen' , [('package') : ProjectConstants.PACKAGENAME]), 
    'Facing')

'tap on back button'
MobileBuiltInKeywords.tap(findTestObject('ShopOpen/ChillerUtilization/ChillerAvailable/PorductCategoryActions_BackButton' , [('package') : ProjectConstants.PACKAGENAME]), 
    0)

'validate "Chiller Utiilization" screen appearance'
MobileBuiltInKeywords.verifyElementText(findTestObject('ShopOpen/ChillerUtilization/ChillerAvailable/Validate_ChillerProductsCategoriesScreen' , [('package') : ProjectConstants.PACKAGENAME]), 
    'Category:Chiller Utilization')

