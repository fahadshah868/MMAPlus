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

'validate chiller products categories detail screen appearance'
Mobile.verifyElementText(findTestObject('ShopOpen/ChillerUtilization/ChillerNotAvailable/Validate_ProductCategoryAssetScreen' , [('package') : ProjectConstants.PACKAGENAME]), 
    'Facing')

'tap on "Facing"'
Mobile.tap(findTestObject('ShopOpen/ChillerUtilization/ChillerNotAvailable/Facing' , [('package') : ProjectConstants.PACKAGENAME]), 0)

'validate facing detail screen appearance'
MobileBuiltInKeywords.verifyElementText(findTestObject('ShopOpen/ChillerUtilization/ChillerNotAvailable/Validate_FacingScreen' , [('package') : ProjectConstants.PACKAGENAME]), 
    'Facing')

'overwrite chiller products with facing'
CustomKeywords.'com.ct.qa.keywords.ChillerProductsDataKeywords.VisitChillerNotAvailableProductsData'(ProjectConstants.CHANNEL_CNA_OVERWRITEFACING, 
    'Facing')

'tap on submit button'
MobileBuiltInKeywords.tap(findTestObject('ShopOpen/ChillerUtilization/ChillerNotAvailable/SubmitButton' , [('package') : ProjectConstants.PACKAGENAME]), 0)

'validate chiller products categories detail screen appearance'
Mobile.verifyElementText(findTestObject('ShopOpen/ChillerUtilization/ChillerNotAvailable/Validate_ProductCategoryAssetScreen' , [('package') : ProjectConstants.PACKAGENAME]), 
    'Facing')

'tap on "Stock Taking"'
Mobile.tap(findTestObject('ShopOpen/ChillerUtilization/ChillerNotAvailable/StockTaking' , [('package') : ProjectConstants.PACKAGENAME]), 0)

'validate stock taking detail screen appearance'
MobileBuiltInKeywords.verifyElementText(findTestObject('ShopOpen/ChillerUtilization/ChillerNotAvailable/Validate_StockTakingScreen' , [('package') : ProjectConstants.PACKAGENAME]), 
    'Stock Taking')

'overwrite chiller products with stock taking'
CustomKeywords.'com.ct.qa.keywords.ChillerProductsDataKeywords.VisitChillerNotAvailableProductsData'(ProjectConstants.CHANNEL_CNA_OVERWRITESTOCKTAKING, 
    'Stock Taking')

'tap on submit button'
MobileBuiltInKeywords.tap(findTestObject('ShopOpen/ChillerUtilization/ChillerNotAvailable/SubmitButton' , [('package') : ProjectConstants.PACKAGENAME]), 0)

'validate chiller products categories detail screen appearance'
Mobile.verifyElementText(findTestObject('ShopOpen/ChillerUtilization/ChillerNotAvailable/Validate_ProductCategoryAssetScreen' , [('package') : ProjectConstants.PACKAGENAME]), 
    'Facing')

'tap on back button'
MobileBuiltInKeywords.tap(findTestObject('ShopOpen/ChillerUtilization/ChillerNotAvailable/ProductCategory_BackButton' , [('package') : ProjectConstants.PACKAGENAME]), 
    0)

'validate "Chiller Utilization" detail screen appearance'
MobileBuiltInKeywords.verifyElementText(findTestObject('ShopOpen/ChillerUtilization/ChillerNotAvailable/Validate_ChillerNotAvailableScreen' , [('package') : ProjectConstants.PACKAGENAME]), 
    'Category:Chiller Utilization')

