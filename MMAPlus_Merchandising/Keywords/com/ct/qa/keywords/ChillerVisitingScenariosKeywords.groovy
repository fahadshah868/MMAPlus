package com.ct.qa.keywords

import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import com.ct.qa.constants.ProjectConstants
import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.checkpoint.Checkpoint
import com.kms.katalon.core.checkpoint.CheckpointFactory
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testcase.TestCase
import com.kms.katalon.core.testcase.TestCaseFactory
import com.kms.katalon.core.testdata.TestData
import com.kms.katalon.core.testdata.TestDataFactory
import com.kms.katalon.core.testobject.ObjectRepository
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords

import internal.GlobalVariable
import io.appium.java_client.MobileElement
import MobileBuiltInKeywords as Mobile
import WSBuiltInKeywords as WS
import WebUiBuiltInKeywords as WebUI

public class ChillerVisitingScenariosKeywords {
	@Keyword
	def visitChillersTaggedInChillerUtilizationWithDataVerification(){
		int totalchillers = ProjectConstants.DRIVER.findElementsByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ListView[1]/*").size()
		if(totalchillers == 7){
			for(int i=1; i<=totalchillers; i++){
				MobileElement chiller = ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout["+i+"]/android.widget.TextView[1]")
				ProjectConstants.CURRENTVISITING_CHILLERTYPE = chiller.getText()
				ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout["+i+"]").click()
				Mobile.verifyElementExist(findTestObject("Object Repository/CommonScreenElements/Validate_CameraScreen"), 0)
				Mobile.tap(findTestObject("Object Repository/CommonScreenElements/TakePictureButton"), 0)
				Mobile.delay(5)
				Mobile.tap(findTestObject("Object Repository/CommonScreenElements/DoneButton"),0)
				findChillerRemark("Chiller Available")
				Mobile.callTestCase(findTestCase("Test Cases/ShopOpen/ChillerUtilization/ChillerAvailable/VisitChillerAvailable"), null)
			}
		}
		else{
			ProjectConstants.VISITED_CHILLERREMARKS = ProjectConstants.VISITED_CHILLERREMARKS + 1
			for(int i=1; i<=totalchillers; i++){
				ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout[1]").click()
				Mobile.verifyElementExist(findTestObject("Object Repository/CommonScreenElements/Validate_CameraScreen"), 0)
				Mobile.tap(findTestObject("Object Repository/CommonScreenElements/TakePictureButton"), 0)
				Mobile.delay(5)
				Mobile.tap(findTestObject("Object Repository/CommonScreenElements/DoneButton"),0)
				if(ProjectConstants.VISITED_CHILLERREMARKS == 2){
					findChillerRemark("Chiller not Available")
					Mobile.callTestCase(findTestCase("Test Cases/ShopOpen/ChillerUtilization/ChillerNotAvailable/VisitChillerNotAvailable"), null)
				}
				else if(ProjectConstants.VISITED_CHILLERREMARKS == 3){
					findChillerRemark("Chiller removed for maintenance")
					Mobile.callTestCase(findTestCase("Test Cases/ShopOpen/ChillerUtilization/ChillerRemovedForMaintenance/VisitChillerRemovedForMaintenance"), null)
				}
				else if(ProjectConstants.VISITED_CHILLERREMARKS == 4){
					findChillerRemark("Chiller need maintenance")
					Mobile.callTestCase(findTestCase("Test Cases/ShopOpen/ChillerUtilization/ChillerNeedMaintenance/VisitChillerNeedMaintenance"), null)
				}
				else if(ProjectConstants.VISITED_CHILLERREMARKS == 5){
					findChillerRemark("Chiller not in access")
					Mobile.callTestCase(findTestCase("Test Cases/ShopOpen/ChillerUtilization/ChillerNotInAccess/VisitChillerNotInAccess"), null)
				}
				else if(ProjectConstants.VISITED_CHILLERREMARKS == 6){
					findChillerRemark("Shopkeeper did not allow")
					Mobile.callTestCase(findTestCase("Test Cases/ShopOpen/ChillerUtilization/ShopKeeperDidNotAllow/VisitShopKeeperDidNotAllow"), null)
				}
				else if(ProjectConstants.VISITED_CHILLERREMARKS == 7){
					findChillerRemark("Chiller Type not Available")
					Mobile.callTestCase(findTestCase("Test Cases/ShopOpen/ChillerUtilization/ChillerTypeNotAvailable/VisitChillerTypeNotAvailable"), null)
				}
				else{
					findChillerRemark("Shopkeeper did not allow")
					Mobile.callTestCase(findTestCase("Test Cases/ShopOpen/ChillerUtilization/ShopKeeperDidNotAllow/VisitShopKeeperDidNotAllow"), null)
				}
			}
		}
	}
	@Keyword
	def findChillerRemark(String _chillerremark){
		int totalremarks = ProjectConstants.DRIVER.findElementsByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ListView[1]/*").size()
		for(int i=1; i<=totalremarks; i++){
			MobileElement chillerremark = ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout["+i+"]/android.widget.TextView[1]")
			String chillerremarkname = chillerremark.getText()
			if(chillerremarkname.equalsIgnoreCase(_chillerremark)){
				ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout["+i+"]").click()
				break
			}
		}
	}
}
