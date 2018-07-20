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

MobileBuiltInKeywords.verifyElementText(findTestObject('ShopOpen/ChillerUtilization/ChillerNotAvailable/Validate_ChillerNotAvailableScreen'), 
    'Category:Chiller Utilization')

CustomKeywords.'com.ct.qa.keywords.ChillerKeywords.visitChillerNotAvailableProductCategories'(2)

MobileBuiltInKeywords.tap(findTestObject('ShopOpen/ChillerUtilization/ChillerNotAvailable/Planogram_ImageView'), 0)

CustomKeywords.'com.ct.qa.keywords.CommonKeywords.checkPlanogramAvailability'()

CustomKeywords.'com.ct.qa.keywords.CommonKeywords.closePlanogram'()

MobileBuiltInKeywords.verifyElementText(findTestObject('ShopOpen/ChillerUtilization/ChillerNotAvailable/Validate_ChillerNotAvailableScreen'), 
    'Category:Chiller Utilization')

CustomKeywords.'com.ct.qa.keywords.CommonKeywords.findPictureImageView'()

MobileBuiltInKeywords.tap(findTestObject('ShopOpen/ChillerUtilization/ChillerNotAvailable/ChillerNotAvailable_BackButton'), 
    0)

MobileBuiltInKeywords.verifyElementText(findTestObject('ShopOpen/ChillerUtilization/Validate_ChillerUtilizationScreen'), 
    'Shops on Route')

not_run: MobileBuiltInKeywords.tap(findTestObject('ShopOpen/ChillerUtilization/ChillerUtilization_backButton'), 0)

not_run: MobileBuiltInKeywords.verifyElementExist(findTestObject('ShopOpen/Validate_ShopCategoriesListScreen'), 0)

not_run: CustomKeywords.'com.ct.qa.keywords.MainKeywords.findShopCategory'(ProjectConstants.currentvisitingmaincategory)

not_run: MobileBuiltInKeywords.verifyElementText(findTestObject('ShopOpen/ChillerUtilization/Validate_ChillerUtilizationScreen'), 
    'Shops on Route')

CustomKeywords.'com.ct.qa.keywords.ChillerKeywords.findTaggedVisitingChiller'()

MobileBuiltInKeywords.verifyElementText(findTestObject('ShopOpen/ChillerUtilization/Validate_ChillerRemarksScreen'), 'KPI: Chiller Utilization')
