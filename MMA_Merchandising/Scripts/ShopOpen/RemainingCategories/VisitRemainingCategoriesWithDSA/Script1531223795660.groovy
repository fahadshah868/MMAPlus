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
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW

'Validate display space available detail screen appearance'
MobileBuiltInKeywords.verifyElementText(findTestObject('ShopOpen/RemainingCategories/Validate_ProductCategoryAssetScreen', 
        [('package') : ProjectConstants.PACKAGENAME]), 'Facing')

'tap on "Facing"'
MobileBuiltInKeywords.tap(findTestObject('ShopOpen/RemainingCategories/Facing', [('package') : ProjectConstants.PACKAGENAME]), 
    0)

'validate facing detail screen appearance'
MobileBuiltInKeywords.verifyElementText(findTestObject('ShopOpen/RemainingCategories/Validate_FacingScreen', [('package') : ProjectConstants.PACKAGENAME]), 
    'Facing')

'visit products with facing'
CustomKeywords.'com.ct.qa.keywords.ChannelProductsDataKeywords.visitChannelWiseProductsData'(ProjectConstants.CHANNEL_DSA_FACING, 
    'Facing')

'tap on submit button'
MobileBuiltInKeywords.tap(findTestObject('ShopOpen/RemainingCategories/SubmitButton', [('package') : ProjectConstants.PACKAGENAME]), 
    0)

'Validate display space available detail screen appearance'
MobileBuiltInKeywords.verifyElementText(findTestObject('ShopOpen/RemainingCategories/Validate_ProductCategoryAssetScreen', 
        [('package') : ProjectConstants.PACKAGENAME]), 'Facing')

'tap on"StockTaking"'
MobileBuiltInKeywords.tap(findTestObject('ShopOpen/RemainingCategories/StockTaking', [('package') : ProjectConstants.PACKAGENAME]), 
    0)

'validate stock taking detail screen'
MobileBuiltInKeywords.verifyElementText(findTestObject('ShopOpen/RemainingCategories/Validate_StockTakingScreen', [('package') : ProjectConstants.PACKAGENAME]), 
    'Stock Taking')

'visit products with stock taking'
CustomKeywords.'com.ct.qa.keywords.ChannelProductsDataKeywords.visitChannelWiseProductsData'(ProjectConstants.CHANNEL_DSA_STOCKTAKING, 
    'Stock Taking')

'tap on submit button'
MobileBuiltInKeywords.tap(findTestObject('ShopOpen/RemainingCategories/SubmitButton', [('package') : ProjectConstants.PACKAGENAME]), 
    0)

'Validate display space available detail screen appearance'
MobileBuiltInKeywords.verifyElementText(findTestObject('ShopOpen/RemainingCategories/Validate_ProductCategoryAssetScreen', 
        [('package') : ProjectConstants.PACKAGENAME]), 'Facing')

CustomKeywords.'com.ct.qa.keywords.CommonKeywords.visitPlanogramImageViewButton'()

'Validate display space available detail screen appearance'
MobileBuiltInKeywords.verifyElementText(findTestObject('ShopOpen/RemainingCategories/Validate_ProductCategoryAssetScreen', 
        [('package') : ProjectConstants.PACKAGENAME]), 'Facing')

CustomKeywords.'com.ct.qa.keywords.CommonKeywords.visitPictureImageViewButton'()

'Validate display space available detail screen appearance'
MobileBuiltInKeywords.verifyElementText(findTestObject('ShopOpen/RemainingCategories/Validate_ProductCategoryAssetScreen', 
        [('package') : ProjectConstants.PACKAGENAME]), 'Facing')

CustomKeywords.'com.ct.qa.keywords.CommonKeywords.visitBackImageViewButton'()

'validate shop\'s categories screen appearance'
MobileBuiltInKeywords.verifyElementExist(findTestObject('ShopOpen/Validate_ShopCategoriesListScreen', [('package') : ProjectConstants.PACKAGENAME]), 
    0)

