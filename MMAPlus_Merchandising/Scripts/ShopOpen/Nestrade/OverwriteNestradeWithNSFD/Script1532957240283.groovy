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

'Validate shop\'s category detail screen appearance'
MobileBuiltInKeywords.verifyElementText(findTestObject('ShopOpen/RemainingCategories/Validate_RemainingCategoriesDetailScreen'), 
    'Display Space Available')

'tap on "Display Space Available"'
MobileBuiltInKeywords.tap(findTestObject('ShopOpen/RemainingCategories/DisplaySpaceAvailable'), 0)

'validate display space available detail screen appearance'
MobileBuiltInKeywords.verifyElementText(findTestObject('ShopOpen/Nestrade/Validate_NestradePorductCategoryScreen'), 'Category:Nestrade')

'overwrite products categories'
CustomKeywords.'com.ct.qa.keywords.ChannelProductsDataKeywords.visitNestradeProductsCategoriesWithNSFD'(2)

'tap on "Planogram" imageview'
MobileBuiltInKeywords.tap(findTestObject('ShopOpen/RemainingCategories/planogram_ImageView'), 0)

'find planogram availability'
CustomKeywords.'com.ct.qa.keywords.CommonKeywords.checkPlanogramAvailability'()

'tap on planogram close button'
CustomKeywords.'com.ct.qa.keywords.CommonKeywords.closePlanogram'()

'validate display space available detail screen appearance'
MobileBuiltInKeywords.verifyElementText(findTestObject('ShopOpen/Nestrade/Validate_NestradePorductCategoryScreen'), 'Category:Nestrade')

'tap on picture imageview and take picture for category'
CustomKeywords.'com.ct.qa.keywords.CommonKeywords.findPictureImageView'()

'validate display space available detail screen appearance'
MobileBuiltInKeywords.verifyElementText(findTestObject('ShopOpen/Nestrade/Validate_NestradePorductCategoryScreen'), 'Category:Nestrade')

'tap on back button'
MobileBuiltInKeywords.tap(findTestObject('ShopOpen/RemainingCategories/ProductCategoryAsset_BackButton'), 0)

'validate shop\'s category screen appearance'
MobileBuiltInKeywords.verifyElementExist(findTestObject('ShopOpen/Validate_ShopCategoriesListScreen'), 0)

