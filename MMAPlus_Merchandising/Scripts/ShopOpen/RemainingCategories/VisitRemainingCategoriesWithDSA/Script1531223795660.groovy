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

MobileBuiltInKeywords.verifyElementText(findTestObject('ShopOpen/RemainingCategories/Validate_RemainingCategoriesDetailScreen'), 
    'Display Space Available')

MobileBuiltInKeywords.tap(findTestObject('ShopOpen/RemainingCategories/DisplaySpaceAvailable'), 0)

MobileBuiltInKeywords.verifyElementText(findTestObject('ShopOpen/RemainingCategories/Validate_ProductCategoryAssetScreen'), 
    'Facing')

MobileBuiltInKeywords.tap(findTestObject('ShopOpen/RemainingCategories/Facing'), 0)

MobileBuiltInKeywords.verifyElementText(findTestObject('ShopOpen/RemainingCategories/Validate_FacingScreen'), 'Facing')

CustomKeywords.'com.ct.qa.keywords.KeywordsCollection.visitDisplaySpaceAvailableFacing'()

MobileBuiltInKeywords.tap(findTestObject('ShopOpen/RemainingCategories/SubmitButton'), 0)

MobileBuiltInKeywords.verifyElementText(findTestObject('ShopOpen/RemainingCategories/Validate_ProductCategoryAssetScreen'), 
    'Facing')

MobileBuiltInKeywords.tap(findTestObject('ShopOpen/RemainingCategories/StockTaking'), 0)

MobileBuiltInKeywords.verifyElementText(findTestObject('ShopOpen/RemainingCategories/Validate_StockTakingScreen'), 'Stock Taking')

CustomKeywords.'com.ct.qa.keywords.KeywordsCollection.visitDisplaySpaceAvailableStockTaking'()

MobileBuiltInKeywords.tap(findTestObject('ShopOpen/RemainingCategories/SubmitButton'), 0)

MobileBuiltInKeywords.verifyElementText(findTestObject('ShopOpen/RemainingCategories/Validate_ProductCategoryAssetScreen'), 
    'Facing')

MobileBuiltInKeywords.tap(findTestObject('ShopOpen/RemainingCategories/planogram_ImageView'), 0)

CustomKeywords.'com.ct.qa.keywords.KeywordsCollection.checkPlanogramAvailability'()

MobileBuiltInKeywords.tap(findTestObject('ShopOpen/RemainingCategories/Planogram_CloseButton'), 0)

MobileBuiltInKeywords.verifyElementText(findTestObject('ShopOpen/RemainingCategories/Validate_ProductCategoryAssetScreen'), 
    'Facing')

MobileBuiltInKeywords.tap(findTestObject('ShopOpen/RemainingCategories/Picture_ImageView'), 0)

MobileBuiltInKeywords.verifyElementExist(findTestObject('CommonScreenElements/Validate_CameraScreen'), 0)

MobileBuiltInKeywords.tap(findTestObject('CommonScreenElements/TakePictureButton'), 0)

MobileBuiltInKeywords.tap(findTestObject('CommonScreenElements/DoneButton'), 0)

MobileBuiltInKeywords.verifyElementText(findTestObject('ShopOpen/RemainingCategories/Validate_ProductCategoryAssetScreen'), 
    'Facing')

MobileBuiltInKeywords.tap(findTestObject('ShopOpen/RemainingCategories/ProductCategoryAsset_BackButton'), 0)

MobileBuiltInKeywords.verifyElementExist(findTestObject('ShopOpen/Validate_ShopCategoriesListScreen'), 0)

