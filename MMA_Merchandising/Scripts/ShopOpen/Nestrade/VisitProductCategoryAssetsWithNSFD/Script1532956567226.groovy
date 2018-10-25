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

'validate product category detail screen appearance'
MobileBuiltInKeywords.verifyElementText(findTestObject('ShopOpen/RemainingCategories/Validate_ProductCategoryAssetScreen' , [('package') : ProjectConstants.PACKAGENAME]), 
    'Facing')

'tap on "Facing"'
MobileBuiltInKeywords.tap(findTestObject('ShopOpen/RemainingCategories/Facing' , [('package') : ProjectConstants.PACKAGENAME]), 0)

'validate facing screen appearance'
MobileBuiltInKeywords.verifyElementText(findTestObject('ShopOpen/RemainingCategories/Validate_FacingScreen' , [('package') : ProjectConstants.PACKAGENAME]), 'Facing')

'overwrite products with facing'
CustomKeywords.'com.ct.qa.keywords.ChannelProductsDataKeywords.visitChannelWiseProductsData'(ProjectConstants.CHANNEL_NSFD_FACING, 
    'Facing')

'tap on submit button'
MobileBuiltInKeywords.tap(findTestObject('ShopOpen/RemainingCategories/SubmitButton' , [('package') : ProjectConstants.PACKAGENAME]), 0)

'validate product category detail screen appearance'
MobileBuiltInKeywords.verifyElementText(findTestObject('ShopOpen/RemainingCategories/Validate_ProductCategoryAssetScreen' , [('package') : ProjectConstants.PACKAGENAME]), 
    'Facing')

'tap on "Stock Taking"'
MobileBuiltInKeywords.tap(findTestObject('ShopOpen/RemainingCategories/StockTaking' , [('package') : ProjectConstants.PACKAGENAME]), 0)

'validate stock taking screen appearance'
MobileBuiltInKeywords.verifyElementText(findTestObject('ShopOpen/RemainingCategories/Validate_StockTakingScreen' , [('package') : ProjectConstants.PACKAGENAME]), 'Stock Taking')

'overwrite products with stock taking'
CustomKeywords.'com.ct.qa.keywords.ChannelProductsDataKeywords.visitChannelWiseProductsData'(ProjectConstants.CHANNEL_NSFD_STOCKTAKING, 
    'Stock Taking')

'tap on submit button'
MobileBuiltInKeywords.tap(findTestObject('ShopOpen/RemainingCategories/SubmitButton' , [('package') : ProjectConstants.PACKAGENAME]), 0)

'validate shop\'s categories screen appearance'
MobileBuiltInKeywords.verifyElementText(findTestObject('ShopOpen/RemainingCategories/Validate_ProductCategoryAssetScreen' , [('package') : ProjectConstants.PACKAGENAME]), 
    'Facing')

'tap on back button'
Mobile.tap(findTestObject('ShopOpen/RemainingCategories/ProductCategoryAsset_BackButton' , [('package') : ProjectConstants.PACKAGENAME]), 0)

'validate display space available detail screen appearance'
MobileBuiltInKeywords.verifyElementText(findTestObject('ShopOpen/Nestrade/Validate_NestradePorductCategoryScreen' , [('package') : ProjectConstants.PACKAGENAME]), 'Category:Nestrade')

