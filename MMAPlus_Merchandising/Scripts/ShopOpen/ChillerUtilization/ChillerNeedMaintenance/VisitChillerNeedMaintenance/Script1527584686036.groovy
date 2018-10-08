import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import com.ct.qa.constants.ProjectConstants
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

'validate category remarks screen appearance'
MobileBuiltInKeywords.verifyElementText(findTestObject('ShopOpen/ChillerUtilization/ChillerNeedMaintenance/Validate_CategoryRemarksScreen' , [('package') : ProjectConstants.PACKAGENAME]), 
    'CATEGORY REMARKS')

'tap on category remark'
MobileBuiltInKeywords.tap(findTestObject('ShopOpen/ChillerUtilization/ChillerNeedMaintenance/Others' , [('package') : ProjectConstants.PACKAGENAME]), 0)

'tap on back button'
MobileBuiltInKeywords.tap(findTestObject('ShopOpen/ChillerUtilization/ChillerNeedMaintenance/CategoryRemarks_BackButton' , [('package') : ProjectConstants.PACKAGENAME]), 
    0)

'visit "Chiller Available" flow for "Chiller Nees Maintenance"'
WebUI.callTestCase(findTestCase('ShopOpen/ChillerUtilization/ChillerAvailable/VisitChillerAvailable'), [:], FailureHandling.STOP_ON_FAILURE)

