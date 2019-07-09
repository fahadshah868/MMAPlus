import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import qa.constants.ProjectConstants as ProjectConstants
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

Mobile.verifyElementText(findTestObject('DashboardScreenElements/Validate_DashboardScreen', [('package') : ProjectConstants.PACKAGENAME]), 
    'Dashboard')

Mobile.swipe(2, 250, 400, 250)

Mobile.verifyElementExist(findTestObject('SliderOptions/Validate_SliderItemsScreen', [('package') : ProjectConstants.PACKAGENAME]), 
    0)

CustomKeywords.'qa.keywords.SliderOptionsKeywords.valideSliderOptions'()

CustomKeywords.'qa.keywords.SliderOptionsKeywords.findSliderOption'('Update Profile')

CustomKeywords.'qa.keywords.SliderOptionsKeywords.findSliderOption'('Out of Route')

not_run: CustomKeywords.'qa.keywords.SliderOptionsKeywords.findSliderOption'('Abnormal Shops')

CustomKeywords.'qa.keywords.SliderOptionsKeywords.findSliderOption'('Info')

not_run: CustomKeywords.'qa.keywords.SliderOptionsKeywords.findSliderOption'('RedFlag Shops')

Mobile.swipe(320, 5, 2, 5)

CustomKeywords.'qa.keywords.SliderOptionsKeywords.displayMissingSliderOptionsInReport'()

