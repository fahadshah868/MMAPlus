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

MobileBuiltInKeywords.verifyElementExist(findTestObject('ShopOpen/ChillerUtilization/ChillerAvailable/Validate_ChillerAvailableScreen'), 
    0)

MobileBuiltInKeywords.tap(findTestObject('ShopOpen/ChillerUtilization/ChillerAvailable/Planogram_ImageView'), 
    0)

CustomKeywords.'com.ct.qa.keywords.KeywordsCollection.checkPlanogramAvailability'()

MobileBuiltInKeywords.tap(findTestObject('ShopOpen/ChillerUtilization/ChillerAvailable/Planogram_CloseButton'), 
    0)

MobileBuiltInKeywords.verifyElementExist(findTestObject('ShopOpen/ChillerUtilization/ChillerAvailable/Validate_ChillerAvailableScreen'), 
    0)

CustomKeywords.'com.ct.qa.keywords.KeywordsCollection.findPictureImageView'()

MobileBuiltInKeywords.verifyElementExist(findTestObject('ShopOpen/ChillerUtilization/ChillerAvailable/Validate_ChillerAvailableScreen'), 
    0)

MobileBuiltInKeywords.tap(findTestObject('ShopOpen/ChillerUtilization/ChillerAvailable/Depth1_TakePicture'), 
    0)

MobileBuiltInKeywords.verifyElementExist(findTestObject('CommonScreenElements/Validate_CameraScreen'), 0)

MobileBuiltInKeywords.tap(findTestObject('CommonScreenElements/TakePictureButton'), 0)

MobileBuiltInKeywords.delay(5)

MobileBuiltInKeywords.tap(findTestObject('CommonScreenElements/DoneButton'), 0)

MobileBuiltInKeywords.verifyElementExist(findTestObject('ShopOpen/ChillerUtilization/ChillerAvailable/Validate_ChillerAvailableScreen'), 
    0)

MobileBuiltInKeywords.tap(findTestObject('ShopOpen/ChillerUtilization/ChillerAvailable/Depth2TakePicture'), 
    0)

MobileBuiltInKeywords.verifyElementExist(findTestObject('CommonScreenElements/Validate_CameraScreen'), 0)

MobileBuiltInKeywords.tap(findTestObject('CommonScreenElements/TakePictureButton'), 0)

MobileBuiltInKeywords.delay(5)

MobileBuiltInKeywords.tap(findTestObject('CommonScreenElements/DoneButton'), 0)

MobileBuiltInKeywords.verifyElementExist(findTestObject('ShopOpen/ChillerUtilization/ChillerAvailable/Validate_ChillerAvailableScreen'), 
    0)

MobileBuiltInKeywords.tap(findTestObject('ShopOpen/ChillerUtilization/ChillerAvailable/NextButton'), 
    0)

MobileBuiltInKeywords.verifyElementText(findTestObject('ShopOpen/ChillerUtilization/ChillerAvailable/Validate_ChillerProductsCategoriesScreen'), 
    'Category:Chiller Utilization')

CustomKeywords.'com.ct.qa.keywords.KeywordsCollection.overwriteChillerAvailableProductsCategories'()

MobileBuiltInKeywords.tap(findTestObject('ShopOpen/ChillerUtilization/ChillerAvailable/ChillerProductsCategories_Planogram_ImageView'), 
    0)

CustomKeywords.'com.ct.qa.keywords.KeywordsCollection.checkPlanogramAvailability'()

MobileBuiltInKeywords.tap(findTestObject('ShopOpen/ChillerUtilization/ChillerAvailable/Planogram_CloseButton'), 
    0)

MobileBuiltInKeywords.verifyElementText(findTestObject('ShopOpen/ChillerUtilization/ChillerAvailable/Validate_ChillerProductsCategoriesScreen'), 
    'Category:Chiller Utilization')

CustomKeywords.'com.ct.qa.keywords.KeywordsCollection.findPictureImageView'()

MobileBuiltInKeywords.verifyElementText(findTestObject('ShopOpen/ChillerUtilization/ChillerAvailable/Validate_ChillerProductsCategoriesScreen'), 
    'Category:Chiller Utilization')

MobileBuiltInKeywords.tap(findTestObject('ShopOpen/ChillerUtilization/ChillerAvailable/ChillerProductsCategories_BackButton'), 
    0)

MobileBuiltInKeywords.verifyElementText(findTestObject('ShopOpen/ChillerUtilization/Validate_ChillerRemarksScreen'), 
    'KPI: Chiller Utilization')

MobileBuiltInKeywords.tap(findTestObject('ShopOpen/ChillerUtilization/ChillerActions_BackButton'), 
    0)

MobileBuiltInKeywords.verifyElementText(findTestObject('ShopOpen/ChillerUtilization/Validate_ChillerUtilizationScreen'), 
    'Shops on Route')

MobileBuiltInKeywords.tap(findTestObject('ShopOpen/ChillerUtilization/ChillerUtilization_backButton'), 
    0)

MobileBuiltInKeywords.verifyElementText(findTestObject('ShopOpen/ShopChannel'), 
    'Channel: Small Kiryana')

