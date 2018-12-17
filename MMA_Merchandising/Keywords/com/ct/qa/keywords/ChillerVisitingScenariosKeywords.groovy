package com.ct.qa.keywords

import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import com.ct.qa.constants.ProjectConstants
import com.ct.qa.struct.TaggedChillersRemark
import com.ct.qa.struct.VisitedCategoryData
import com.ct.qa.struct.VisitedShopDataInfo
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
		ArrayList<TaggedChillersRemark> taggedchillersremark = new ArrayList<TaggedChillersRemark>()
		int index = 0
		int totalchillers = ProjectConstants.DRIVER.findElementsByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ListView[1]/*").size()
		if(totalchillers >= 2){
			for(int i=1; i<=totalchillers; i++){
				MobileElement chiller = ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout["+i+"]/android.widget.TextView[1]")
				ProjectConstants.CURRENTVISITING_CHILLERTYPE = chiller.getText()
				ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout["+i+"]").click()
				Mobile.verifyElementExist(findTestObject("Object Repository/CommonScreenElements/Validate_CameraScreen" , [('package') : ProjectConstants.PACKAGENAME]), 0)
				Mobile.tap(findTestObject("Object Repository/CommonScreenElements/TakePictureButton" , [('package') : ProjectConstants.PACKAGENAME]), 0)
				Mobile.delay(5)
				Mobile.tap(findTestObject("Object Repository/CommonScreenElements/DoneButton" , [('package') : ProjectConstants.PACKAGENAME]),0)
				findChillerRemark("Chiller Available")
				Mobile.callTestCase(findTestCase("Test Cases/ShopOpen/ChillerUtilization/ChillerAvailable/VisitChillerAvailable"), null)
			}
			while(true){
				index = ProjectConstants.DRIVER.findElementsByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ListView[1]/*").size()
				MobileElement lastchillerbeforeswipe = ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout["+index+"]/android.widget.TextView[1]")
				String lastchillernamebeforeswipe = lastchillerbeforeswipe.getText()
				Mobile.swipe(0, 292, 0, 200)
				index = ProjectConstants.DRIVER.findElementsByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ListView[1]/*").size()
				MobileElement lastchillerafterswipe = ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout["+index+"]/android.widget.TextView[1]")
				String lastchillernameafterswipe = lastchillerafterswipe.getText()
				if(lastchillernamebeforeswipe.equalsIgnoreCase(lastchillernameafterswipe)){
					break
				}
				else{
					ProjectConstants.CURRENTVISITING_CHILLERTYPE = lastchillernameafterswipe
					ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout["+index+"]").click()
					Mobile.verifyElementExist(findTestObject("Object Repository/CommonScreenElements/Validate_CameraScreen" , [('package') : ProjectConstants.PACKAGENAME]), 0)
					Mobile.tap(findTestObject("Object Repository/CommonScreenElements/TakePictureButton" , [('package') : ProjectConstants.PACKAGENAME]), 0)
					Mobile.delay(5)
					Mobile.tap(findTestObject("Object Repository/CommonScreenElements/DoneButton" , [('package') : ProjectConstants.PACKAGENAME]),0)
					findChillerRemark("Chiller Available")
					Mobile.callTestCase(findTestCase("Test Cases/ShopOpen/ChillerUtilization/ChillerAvailable/VisitChillerAvailable"), null)
				}
			}
		}
		else{
			ProjectConstants.VISITED_CHILLERREMARKS = ProjectConstants.VISITED_CHILLERREMARKS + 1
			for(int i=1; i<=totalchillers; i++){
				MobileElement chiller = ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout["+i+"]/android.widget.TextView[1]")
				ProjectConstants.CURRENTVISITING_CHILLERTYPE = chiller.getText()
				ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout[1]").click()
				Mobile.verifyElementExist(findTestObject("Object Repository/CommonScreenElements/Validate_CameraScreen" , [('package') : ProjectConstants.PACKAGENAME]), 0)
				Mobile.tap(findTestObject("Object Repository/CommonScreenElements/TakePictureButton" , [('package') : ProjectConstants.PACKAGENAME]), 0)
				Mobile.delay(5)
				Mobile.tap(findTestObject("Object Repository/CommonScreenElements/DoneButton" , [('package') : ProjectConstants.PACKAGENAME]),0)
				if(ProjectConstants.VISITED_CHILLERREMARKS == 1){
					findChillerRemark("Chiller Available")
					Mobile.callTestCase(findTestCase("Test Cases/ShopOpen/ChillerUtilization/ChillerAvailable/VisitChillerAvailable"), null)
				}
				else if(ProjectConstants.VISITED_CHILLERREMARKS == 2){
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
					findChillerRemark("Chiller Available")
					Mobile.callTestCase(findTestCase("Test Cases/ShopOpen/ChillerUtilization/ChillerAvailable/VisitChillerAvailable"), null)
				}
			}
		}
	}
	@Keyword
	def visitChillersTaggedinChillerUtilizationWithSingleRemark(String chillerremark){
		int totalchillers = ProjectConstants.DRIVER.findElementsByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ListView[1]/*").size()
		for(int i=1; i<=totalchillers; i++){
			MobileElement chiller = ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout["+i+"]/android.widget.TextView[1]")
			ProjectConstants.CURRENTVISITING_CHILLERTYPE = chiller.getText()
			ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout["+i+"]").click()
			Mobile.verifyElementExist(findTestObject("Object Repository/CommonScreenElements/Validate_CameraScreen" , [('package') : ProjectConstants.PACKAGENAME]), 0)
			Mobile.tap(findTestObject("Object Repository/CommonScreenElements/TakePictureButton" , [('package') : ProjectConstants.PACKAGENAME]), 0)
			Mobile.delay(5)
			Mobile.tap(findTestObject("Object Repository/CommonScreenElements/DoneButton" , [('package') : ProjectConstants.PACKAGENAME]),0)
			findChillerRemark(chillerremark)
			if(chillerremark.equalsIgnoreCase("Chiller Available")){
				Mobile.callTestCase(findTestCase("Test Cases/ShopOpen/ChillerUtilization/ChillerAvailable/VisitChillerAvailable"), null)
			}
			else if(chillerremark.equalsIgnoreCase("Chiller not Available")){
				Mobile.callTestCase(findTestCase("Test Cases/ShopOpen/ChillerUtilization/ChillerNotAvailable/VisitChillerNotAvailable"), null)
			}
			else if(chillerremark.equalsIgnoreCase("Chiller need maintenance")){
				Mobile.callTestCase(findTestCase("Test Cases/ShopOpen/ChillerUtilization/ChillerNeedMaintenance/VisitChillerNeedMaintenance"), null)
			}
			else if(chillerremark.equalsIgnoreCase("Chiller not in access")){
				Mobile.callTestCase(findTestCase("Test Cases/ShopOpen/ChillerUtilization/ChillerNotInAccess/VisitChillerNotInAccess"), null)
			}
			else if(chillerremark.equalsIgnoreCase("Chiller removed for maintenance")){
				Mobile.callTestCase(findTestCase("Test Cases/ShopOpen/ChillerUtilization/ChillerRemovedForMaintenance/VisitChillerRemovedForMaintenance"), null)
			}
			else if(chillerremark.equalsIgnoreCase("Chiller Type not Available")){
				Mobile.callTestCase(findTestCase("Test Cases/ShopOpen/ChillerUtilization/ChillerTypeNotAvailable/VisitChillerTypeNotAvailable"), null)
			}
			else if(chillerremark.equalsIgnoreCase("Shopkeeper did not Allow")){
				Mobile.callTestCase(findTestCase("Test Cases/ShopOpen/ChillerUtilization/ShopKeeperDidNotAllow/VisitShopKeeperDidNotAllow"), null)
			}
			else{
			}
		}
	}
	@Keyword
	def overwriteChillersTaggedinChillerUtilizationWithSingleRemark(String chillerremark){
		int totalchillers = ProjectConstants.DRIVER.findElementsByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ListView[1]/*").size()
		for(int i=1; i<=totalchillers; i++){
			MobileElement chiller = ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout["+i+"]/android.widget.TextView[1]")
			ProjectConstants.CURRENTVISITING_CHILLERTYPE = chiller.getText()
			ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout["+i+"]").click()
			Mobile.verifyElementExist(findTestObject("Object Repository/CommonScreenElements/Validate_CameraScreen" , [('package') : ProjectConstants.PACKAGENAME]), 0)
			Mobile.tap(findTestObject("Object Repository/CommonScreenElements/TakePictureButton" , [('package') : ProjectConstants.PACKAGENAME]), 0)
			Mobile.delay(5)
			Mobile.tap(findTestObject("Object Repository/CommonScreenElements/DoneButton" , [('package') : ProjectConstants.PACKAGENAME]),0)
			findChillerRemark(chillerremark)
			if(chillerremark.equalsIgnoreCase("Chiller Available")){
				Mobile.callTestCase(findTestCase("Test Cases/ShopOpen/ChillerUtilization/ChillerAvailable/OverwriteChillerAvailable"), null)
			}
			else if(chillerremark.equalsIgnoreCase("Chiller not Available")){
				Mobile.callTestCase(findTestCase("Test Cases/ShopOpen/ChillerUtilization/ChillerNotAvailable/OverwriteChillerNotAvailable"), null)
			}
			else if(chillerremark.equalsIgnoreCase("Chiller need maintenance")){
				Mobile.callTestCase(findTestCase("Test Cases/ShopOpen/ChillerUtilization/ChillerNeedMaintenance/OverwriteChillerNeedMaintenance"), null)
			}
			else if(chillerremark.equalsIgnoreCase("Chiller not in access")){
				Mobile.callTestCase(findTestCase("Test Cases/ShopOpen/ChillerUtilization/ChillerNotInAccess/OverwriteChillerNotInAccess"), null)
			}
			else if(chillerremark.equalsIgnoreCase("Chiller removed for maintenance")){
				Mobile.callTestCase(findTestCase("Test Cases/ShopOpen/ChillerUtilization/ChillerRemovedForMaintenance/OverwriteChillerRemovedForMaintenance"), null)
			}
			else if(chillerremark.equalsIgnoreCase("Chiller Type not Available")){
				Mobile.callTestCase(findTestCase("Test Cases/ShopOpen/ChillerUtilization/ChillerTypeNotAvailable/OverwriteChillerTypeNotAvailable"), null)
			}
			else if(chillerremark.equalsIgnoreCase("Shopkeeper did not Allow")){
				Mobile.callTestCase(findTestCase("Test Cases/ShopOpen/ChillerUtilization/ShopKeeperDidNotAllow/OverwriteShopKeeperDidNotAllow"), null)
			}
			else{
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
				ProjectConstants.CURRENTVISITING_CHILLERREMARK = chillerremarkname
				ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout["+i+"]").click()
				break
			}
		}
	}
	@Keyword
	def selectChillerType(){
		int totalchillertypes = ProjectConstants.DRIVER.findElementsByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.ListView[1]/*").size()
		if(ProjectConstants.CURRENTVISITING_CHILLERTYPE.contains("Without Yogurt")){
			for(int i=1; i<=totalchillertypes-1; i++){
				MobileElement chiller = ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout["+i+"]/android.widget.TextView[1]")
				String chillername = chiller.getText()
				if(chillername.contains("With Yogurt")){
					ProjectConstants.CURRENTVISITING_CHILLERTYPE = chiller.getText()
					ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout["+i+"]").click()
					break
				}
			}
		}
		else{
			for(int i=1; i<=totalchillertypes-1; i++){
				MobileElement chiller = ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout["+i+"]/android.widget.TextView[1]")
				String chillername = chiller.getText()
				if(chillername.contains("Without Yogurt")){
					ProjectConstants.CURRENTVISITING_CHILLERTYPE = chiller.getText()
					ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout["+i+"]").click()
					break
				}
			}
		}
	}
	@Keyword
	def selectDifferentChillerType(){
		int totalchillertypes = ProjectConstants.DRIVER.findElementsByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.ListView[1]/*").size()
		if(ProjectConstants.CURRENTVISITING_CHILLERTYPE.contains("With Yogurt")){
			for(int i=1; i<=totalchillertypes-1; i++){
				MobileElement chiller = ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout["+i+"]/android.widget.TextView[1]")
				String chillername = chiller.getText()
				if(chillername.contains("Without Yogurt")){
					ProjectConstants.CURRENTVISITING_CHILLERTYPE = chiller.getText()
					ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout["+i+"]").click()
					break
				}
			}
		}
		else{
			for(int i=1; i<=totalchillertypes-1; i++){
				MobileElement chiller = ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout["+i+"]/android.widget.TextView[1]")
				String chillername = chiller.getText()
				if(chillername.contains("With Yogurt")){
					ProjectConstants.CURRENTVISITING_CHILLERTYPE = chiller.getText()
					ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout["+i+"]").click()
					break
				}
			}
		}
	}
}
