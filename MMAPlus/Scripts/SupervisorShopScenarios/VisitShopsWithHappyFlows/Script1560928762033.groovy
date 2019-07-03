import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.ct.qa.constants.ProjectConstants as ProjectConstants
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import internal.GlobalVariable as GlobalVariable

Mobile.verifyElementVisible(findTestObject('WorkActions/validate_WorkActionsScreen', [('package') : ProjectConstants.PACKAGENAME]), 
    100)

Mobile.verifyElementText(findTestObject('WorkActions/validate_WorkActionsScreen', [('package') : ProjectConstants.PACKAGENAME]), 
    'Select')

CustomKeywords.'com.ct.qa.keywords.WorkActionsKeywords.selectAction'('WW')

Mobile.verifyElementText(findTestObject('WorkActions/Validate_SubWorkActionsScreen', [('package') : ProjectConstants.PACKAGENAME]), 
    'Select')

CustomKeywords.'com.ct.qa.keywords.WorkActionsKeywords.selectSubAction'('Route')

Mobile.verifyElementVisible(findTestObject('WorkActions/Validate_RouteListScreen', [('package') : ProjectConstants.PACKAGENAME]), 
    100)

Mobile.verifyElementText(findTestObject('WorkActions/Validate_RouteListScreen', [('package') : ProjectConstants.PACKAGENAME]), 
    'Route LIST')

CustomKeywords.'com.ct.qa.keywords.WorkActionsKeywords.selectRoute'()

'Validate shops list screen appearance'
Mobile.verifyElementVisible(findTestObject('Validate_ShopListScreen', [('package') : ProjectConstants.PACKAGENAME]), 100)

'Validate shops list screen appearance'
Mobile.verifyElementText(findTestObject('Validate_ShopListScreen', [('package') : ProjectConstants.PACKAGENAME]), 'Shops on Route')

'select shops from shops list for data verification'
CustomKeywords.'com.ct.qa.keywords.ShopVisitingScenariosKeywords.visitShopWith_HappyFlow'()

Mobile.pressBack()

not_run: Mobile.verifyElementText(findTestObject('WorkActions/Validate_RouteListScreen', [('package') : ProjectConstants.PACKAGENAME]), 
    'Route LIST')

not_run: Mobile.pressBack()

not_run: Mobile.verifyElementText(findTestObject('WorkActions/Validate_SubWorkActionsScreen', [('package') : ProjectConstants.PACKAGENAME]), 
    'Select')

not_run: CustomKeywords.'com.ct.qa.keywords.WorkActionsKeywords.selectAction'('Un-Captured')

not_run: Mobile.verifyElementExist(findTestObject('WorkActions/Validate_UncapturedVisit_Popup', [('package') : ProjectConstants.PACKAGENAME]), 
    0)

not_run: Mobile.tap(findTestObject('WorkActions/Uncapturedvisit_OkButton', [('package') : ProjectConstants.PACKAGENAME]), 
    0)

not_run: CustomKeywords.'com.ct.qa.keywords.WorkActionsKeywords.validateShopListScreenForUnCapturedShops'()

not_run: Mobile.pressBack()

not_run: Mobile.verifyElementText(findTestObject('WorkActions/validate_WorkActionsScreen', [('package') : ProjectConstants.PACKAGENAME]), 
    'Select')

not_run: CustomKeywords.'com.ct.qa.keywords.WorkActionsKeywords.selectAction'('WR')

not_run: Mobile.verifyElementText(findTestObject('WorkActions/Validate_SubWorkActionsScreen', [('package') : ProjectConstants.PACKAGENAME]), 
    'Select')

not_run: CustomKeywords.'com.ct.qa.keywords.WorkActionsKeywords.selectSubAction'('Route')

not_run: Mobile.verifyElementVisible(findTestObject('WorkActions/Validate_RouteListScreen', [('package') : ProjectConstants.PACKAGENAME]), 
    100)

not_run: Mobile.verifyElementText(findTestObject('WorkActions/Validate_RouteListScreen', [('package') : ProjectConstants.PACKAGENAME]), 
    'Route LIST')

not_run: CustomKeywords.'com.ct.qa.keywords.WorkActionsKeywords.selectSameRouteForDifferentWorkActions'(ProjectConstants.CURRENTVISITING_ROUTE)

'Validate shops list screen appearance'
not_run: Mobile.verifyElementVisible(findTestObject('Validate_ShopListScreen', [('package') : ProjectConstants.PACKAGENAME]), 
    100)

'Validate shops list screen appearance'
not_run: Mobile.verifyElementText(findTestObject('Validate_ShopListScreen', [('package') : ProjectConstants.PACKAGENAME]), 
    'Shops on Route')

'select shops from shops list for data verification'
not_run: CustomKeywords.'com.ct.qa.keywords.ShopVisitingScenariosKeywords.visitShopWith_HappyFlow'()

not_run: Mobile.pressBack()

not_run: Mobile.verifyElementText(findTestObject('WorkActions/Validate_RouteListScreen', [('package') : ProjectConstants.PACKAGENAME]), 
    'Route LIST')

not_run: Mobile.pressBack()

not_run: Mobile.verifyElementText(findTestObject('WorkActions/Validate_SubWorkActionsScreen', [('package') : ProjectConstants.PACKAGENAME]), 
    'Select')

not_run: CustomKeywords.'com.ct.qa.keywords.WorkActionsKeywords.selectSubAction'('Un-Captured')

not_run: Mobile.verifyElementExist(findTestObject('WorkActions/Validate_UncapturedVisit_Popup', [('package') : ProjectConstants.PACKAGENAME]), 
    0)

not_run: Mobile.tap(findTestObject('WorkActions/Uncapturedvisit_OkButton', [('package') : ProjectConstants.PACKAGENAME]), 
    0)

not_run: CustomKeywords.'com.ct.qa.keywords.WorkActionsKeywords.validateShopListScreenForUnCapturedShops'()

not_run: Mobile.pressBack()

not_run: Mobile.verifyElementText(findTestObject('WorkActions/validate_WorkActionsScreen', [('package') : ProjectConstants.PACKAGENAME]), 
    'Select')

not_run: CustomKeywords.'com.ct.qa.keywords.WorkActions.selectAction'('Merchandising')

not_run: Mobile.verifyElementVisible(findTestObject('WorkActions/Validate_RouteListScreen', [('package') : ProjectConstants.PACKAGENAME]), 
    100)

not_run: Mobile.verifyElementText(findTestObject('WorkActions/Validate_RouteListScreen', [('package') : ProjectConstants.PACKAGENAME]), 
    'Route LIST')

not_run: CustomKeywords.'com.ct.qa.keywords.WorkActionsKeywords.selectSameRouteForDifferentWorkActions'(ProjectConstants.CURRENTVISITING_ROUTE)

'Validate shops list screen appearance'
not_run: Mobile.verifyElementVisible(findTestObject('Validate_ShopListScreen', [('package') : ProjectConstants.PACKAGENAME]), 
    100)

'Validate shops list screen appearance'
not_run: Mobile.verifyElementText(findTestObject('Validate_ShopListScreen', [('package') : ProjectConstants.PACKAGENAME]), 
    'Shops on Route')

'select shops from shops list for data verification'
not_run: CustomKeywords.'com.ct.qa.keywords.ShopVisitingScenariosKeywords.visitShopWith_HappyFlow'()

not_run: Mobile.pressBack()

not_run: Mobile.verifyElementText(findTestObject('WorkActions/Validate_RouteListScreen', [('package') : ProjectConstants.PACKAGENAME]), 
    'Route LIST')

not_run: Mobile.pressBack()

not_run: Mobile.verifyElementText(findTestObject('WorkActions/validate_WorkActionsScreen', [('package') : ProjectConstants.PACKAGENAME]), 
    'Select')

CustomKeywords.'com.ct.qa.keywords.DisplayReportKeywords.displayDataInReport'()

