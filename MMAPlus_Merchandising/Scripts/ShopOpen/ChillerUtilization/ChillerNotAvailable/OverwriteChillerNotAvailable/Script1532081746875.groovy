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

'validate "Chiller Not Available" detail screen appearance'
MobileBuiltInKeywords.verifyElementText(findTestObject('ShopOpen/ChillerUtilization/ChillerNotAvailable/Validate_ChillerNotAvailableScreen'), 
    'Category:Chiller Utilization')

'overwrite chiller not available products categories'
CustomKeywords.'com.ct.qa.keywords.ChillerProductsDataKeywords.visitChillerNotAvailableProductCategories'(2)

'tap on "Planogram" imageview'
MobileBuiltInKeywords.tap(findTestObject('ShopOpen/ChillerUtilization/ChillerNotAvailable/Planogram_ImageView'), 0)

'find planogram availability'
CustomKeywords.'com.ct.qa.keywords.CommonKeywords.checkPlanogramAvailability'()

'tap on planogram close button'
CustomKeywords.'com.ct.qa.keywords.CommonKeywords.closePlanogram'()

'validate "Chiller Not Available" detail screen appearance'
MobileBuiltInKeywords.verifyElementText(findTestObject('ShopOpen/ChillerUtilization/ChillerNotAvailable/Validate_ChillerNotAvailableScreen'), 
    'Category:Chiller Utilization')

'find "Picture" imageview and take picture for chiller not available'
CustomKeywords.'com.ct.qa.keywords.CommonKeywords.findPictureImageView'()

'tap on back button'
MobileBuiltInKeywords.tap(findTestObject('ShopOpen/ChillerUtilization/ChillerNotAvailable/ChillerNotAvailable_BackButton'), 
    0)

'validate "Chiller Utilization" detail screen appearance'
MobileBuiltInKeywords.verifyElementText(findTestObject('ShopOpen/ChillerUtilization/Validate_ChillerUtilizationScreen'), 
    'Shops on Route')

