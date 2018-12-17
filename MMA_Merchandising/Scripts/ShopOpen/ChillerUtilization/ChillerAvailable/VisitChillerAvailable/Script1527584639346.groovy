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

'validate "Chiller Available" detail screen appearance'
MobileBuiltInKeywords.verifyElementExist(findTestObject('ShopOpen/ChillerUtilization/ChillerAvailable/Validate_ChillerAvailableScreen', 
        [('package') : ProjectConstants.PACKAGENAME]), 0)

'tap on "Planogram" imageview'
MobileBuiltInKeywords.tap(findTestObject('ShopOpen/ChillerUtilization/ChillerAvailable/Planogram_ImageView', [('package') : ProjectConstants.PACKAGENAME]), 
    0)

'find planogram availability'
CustomKeywords.'com.ct.qa.keywords.CommonKeywords.checkPlanogramAvailability'()

'tap on planogram close button'
CustomKeywords.'com.ct.qa.keywords.CommonKeywords.closePlanogram'()

'validate "Chiller Available" detail screen appearance'
MobileBuiltInKeywords.verifyElementExist(findTestObject('ShopOpen/ChillerUtilization/ChillerAvailable/Validate_ChillerAvailableScreen', 
        [('package') : ProjectConstants.PACKAGENAME]), 0)

'tap on take picture for depth 1'
MobileBuiltInKeywords.tap(findTestObject('ShopOpen/ChillerUtilization/ChillerAvailable/Depth1_TakePicture', [('package') : ProjectConstants.PACKAGENAME]), 
    0)

'validate camera screen appearance'
MobileBuiltInKeywords.verifyElementExist(findTestObject('CommonScreenElements/Validate_CameraScreen', [('package') : ProjectConstants.PACKAGENAME]), 
    0)

'tap on tak picture button'
MobileBuiltInKeywords.tap(findTestObject('CommonScreenElements/TakePictureButton', [('package') : ProjectConstants.PACKAGENAME]), 
    0)

'delay of 5 seconds while taking picture'
MobileBuiltInKeywords.delay(5)

'tap on done button'
MobileBuiltInKeywords.tap(findTestObject('CommonScreenElements/DoneButton', [('package') : ProjectConstants.PACKAGENAME]), 
    0)

'validate "Chiller Available" detail screen appearance'
MobileBuiltInKeywords.verifyElementExist(findTestObject('ShopOpen/ChillerUtilization/ChillerAvailable/Validate_ChillerAvailableScreen', 
        [('package') : ProjectConstants.PACKAGENAME]), 0)

'tap on take picture button fot depth 2'
MobileBuiltInKeywords.tap(findTestObject('ShopOpen/ChillerUtilization/ChillerAvailable/Depth2TakePicture', [('package') : ProjectConstants.PACKAGENAME]), 
    0)

'validate camera screen appearance'
MobileBuiltInKeywords.verifyElementExist(findTestObject('CommonScreenElements/Validate_CameraScreen', [('package') : ProjectConstants.PACKAGENAME]), 
    0)

'tap on take picture button'
MobileBuiltInKeywords.tap(findTestObject('CommonScreenElements/TakePictureButton', [('package') : ProjectConstants.PACKAGENAME]), 
    0)

'delay of 5 seconds while taking picture'
MobileBuiltInKeywords.delay(5)

'tap on done button'
MobileBuiltInKeywords.tap(findTestObject('CommonScreenElements/DoneButton', [('package') : ProjectConstants.PACKAGENAME]), 
    0)

'validate "Chiller Available" detail screen appearance'
MobileBuiltInKeywords.verifyElementExist(findTestObject('ShopOpen/ChillerUtilization/ChillerAvailable/Validate_ChillerAvailableScreen', 
        [('package') : ProjectConstants.PACKAGENAME]), 0)

'tap on next button to further proceed'
MobileBuiltInKeywords.tap(findTestObject('ShopOpen/ChillerUtilization/ChillerAvailable/NextButton', [('package') : ProjectConstants.PACKAGENAME]), 
    0)

'validate products categories screen appearance'
MobileBuiltInKeywords.verifyElementText(findTestObject('ShopOpen/ChillerUtilization/ChillerAvailable/Validate_ChillerProductsCategoriesScreen', 
        [('package') : ProjectConstants.PACKAGENAME]), 'Category:Chiller Utilization')

'visit chiller available products categories'
CustomKeywords.'com.ct.qa.keywords.ChillerProductsDataKeywords.visitChillerAvailableProductCategories'(1)

'tap on picture imageview and take picture for category'
CustomKeywords.'com.ct.qa.keywords.CommonKeywords.visitPlanogramImageViewButton'()

'validate products categories screen appearance'
MobileBuiltInKeywords.verifyElementText(findTestObject('ShopOpen/ChillerUtilization/ChillerAvailable/Validate_ChillerProductsCategoriesScreen', 
        [('package') : ProjectConstants.PACKAGENAME]), 'Category:Chiller Utilization')

'tap on picture imageview and take picture for category'
CustomKeywords.'com.ct.qa.keywords.CommonKeywords.visitPictureImageViewButton'()

'validate products categories screen appearance'
MobileBuiltInKeywords.verifyElementText(findTestObject('ShopOpen/ChillerUtilization/ChillerAvailable/Validate_ChillerProductsCategoriesScreen', 
        [('package') : ProjectConstants.PACKAGENAME]), 'Category:Chiller Utilization')

'tap on picture imageview and take picture for category'
CustomKeywords.'com.ct.qa.keywords.CommonKeywords.visitBackImageViewButton'()

'validate "Chiller Utilization" detail screen appearance'
MobileBuiltInKeywords.verifyElementText(findTestObject('ShopOpen/ChillerUtilization/Validate_ChillerUtilizationScreen', 
        [('package') : ProjectConstants.PACKAGENAME]), 'Shop Chillers')

