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
import com.ct.qa.constants.ProjectConstants as ProjectConstants

'validate channel summary detail screen appearance'
MobileBuiltInKeywords.verifyElementText(findTestObject('DistributionPoint/Validate_RouteInfoScreen', [('package') : ProjectConstants.PACKAGENAME]), 
    'Route Info')

'tap on next button to further proceed'
MobileBuiltInKeywords.tap(findTestObject('DistributionPoint/RouteInfoNextButton', [('package') : ProjectConstants.PACKAGENAME]), 
    0)

'validate distribution point screen appearance'
MobileBuiltInKeywords.verifyElementText(findTestObject('DistributionPoint/Validate_DistributionPointScreen', [('package') : ProjectConstants.PACKAGENAME]), 
    'Poster Quantity:')

'enter quantity of products on distribution point'
CustomKeywords.'com.ct.qa.keywords.DistributionPointKeywords.visitdistributionPoint'()

'tap on submit button after enter the quantity of products on distribution point'
MobileBuiltInKeywords.tap(findTestObject('DistributionPoint/DistributionPoint_SubmitButton', [('package') : ProjectConstants.PACKAGENAME]), 
    0)

'validate dashboard screen appearance'
Mobile.verifyElementText(findTestObject('DashboardScreenElements/Validate_DashboardScreen', [('package') : ProjectConstants.PACKAGENAME]), 
    'Dashboard')

