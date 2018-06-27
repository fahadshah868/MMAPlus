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

MobileBuiltInKeywords.verifyElementText(findTestObject('ShopOpen/Sachets/Validate_SachetsScreen'), 
    'KPI: Sachets')

MobileBuiltInKeywords.tap(findTestObject('ShopOpen/Sachets/ShopKeeperDidNotAllow'), 0)

MobileBuiltInKeywords.verifyElementText(findTestObject('ShopOpen/Sachets/Validate_CategoryRemarksScreen'), 
    'CATEGORY REMARKS')

MobileBuiltInKeywords.tap(findTestObject('ShopOpen/Sachets/CategoryRemarks_Others'), 0)

MobileBuiltInKeywords.tap(findTestObject('ShopOpen/Sachets/CategoryRemarks_BackButton'), 
    0)

MobileBuiltInKeywords.verifyElementText(findTestObject('ShopOpen/Sachets/Validate_SachetsScreen'), 
    'KPI: Sachets')

MobileBuiltInKeywords.tap(findTestObject('ShopOpen/Sachets/DisplaySpaceAvailable'), 0)

MobileBuiltInKeywords.verifyElementText(findTestObject('ShopOpen/Sachets/Validate_ProductCategoryScreen'), 
    'Category:Sachets')

MobileBuiltInKeywords.tap(findTestObject('ShopOpen/Sachets/Facing'), 0)

MobileBuiltInKeywords.verifyElementText(findTestObject('ShopOpen/Sachets/Validate_FacingScreen'), 
    'Facing')

CustomKeywords.'customkeywords.CustomKeywordsCollection.overwriteSachetsProducts'()

MobileBuiltInKeywords.tap(findTestObject('ShopOpen/Sachets/SubmitButton'), 0)

MobileBuiltInKeywords.verifyElementText(findTestObject('ShopOpen/Sachets/Validate_ProductCategoryScreen'), 
    'Category:Sachets')

MobileBuiltInKeywords.tap(findTestObject('ShopOpen/Sachets/StockTaking'), 0)

MobileBuiltInKeywords.verifyElementText(findTestObject('ShopOpen/Sachets/Validate_StockTakingScreen'), 
    'Stock Taking')

CustomKeywords.'customkeywords.CustomKeywordsCollection.overwriteSachetsProducts'()

MobileBuiltInKeywords.tap(findTestObject('ShopOpen/Sachets/SubmitButton'), 0)

MobileBuiltInKeywords.verifyElementText(findTestObject('ShopOpen/Sachets/Validate_ProductCategoryScreen'), 
    'Category:Sachets')

MobileBuiltInKeywords.tap(findTestObject('ShopOpen/Sachets/planogram_ImageView'), 0)

CustomKeywords.'customkeywords.CustomKeywordsCollection.checkPlanogramAvailability'()

MobileBuiltInKeywords.tap(findTestObject('ShopOpen/Sachets/Planogram_CloseButton'), 0)

MobileBuiltInKeywords.verifyElementText(findTestObject('ShopOpen/Sachets/Validate_ProductCategoryScreen'), 
    'Category:Sachets')

MobileBuiltInKeywords.tap(findTestObject('ShopOpen/Sachets/Picture_ImageView'), 0)

MobileBuiltInKeywords.verifyElementExist(findTestObject('CommonScreenElements/Validate_CameraScreen'), 0)

MobileBuiltInKeywords.tap(findTestObject('CommonScreenElements/TakePictureButton'), 0)

MobileBuiltInKeywords.tap(findTestObject('CommonScreenElements/DoneButton'), 0)

MobileBuiltInKeywords.verifyElementText(findTestObject('ShopOpen/Sachets/Validate_ProductCategoryScreen'), 
    'Category:Sachets')

MobileBuiltInKeywords.tap(findTestObject('ShopOpen/Sachets/ProductCategory_BackButton'), 
    0)

MobileBuiltInKeywords.verifyElementText(findTestObject('ShopOpen/Validate_ShopModulesScreen'), 
    'Channel: Small Kiryana')

