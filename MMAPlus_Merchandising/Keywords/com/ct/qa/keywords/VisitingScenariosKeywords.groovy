package com.ct.qa.keywords

import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

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
import com.ct.qa.constants.ProjectConstants
import com.ct.qa.struct.ProductsData

public class VisitingScenariosKeywords {
	@Keyword
	def visitShop(){
		int index = 0
		ArrayList<MobileElement> shops = ProjectConstants.driver.findElementsByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.ListView[1]/*")
		for(int i=4; i<=shops.size(); i++){
			MobileElement shop = ProjectConstants.driver.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout["+i+"]/android.widget.TextView[1]")
			ProjectConstants.currentvisitingshopname = shop.getText()
			ProjectConstants.driver.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout["+i+"]").click()
			MobileBuiltInKeywords.verifyElementText(findTestObject("Object Repository/Validate_ShopOptionsScreen"), "Options")
			MobileBuiltInKeywords.tap(findTestObject("Object Repository/StartWorking"), 0)
			Mobile.delay(15)
			Mobile.verifyElementExist(findTestObject("Object Repository/CommonScreenElements/Validate_MapScreen"), 60)
			MobileBuiltInKeywords.tap(findTestObject("Object Repository/CommonScreenElements/Location_CheckIn"), 0)
			Mobile.verifyElementExist(findTestObject("Object Repository/CommonScreenElements/Validate_InfoPopUP"), 0)
			MobileBuiltInKeywords.tap(findTestObject("Object Repository/CommonScreenElements/InfoPopUp_NoButton"), 0)
			Mobile.callTestCase(findTestCase("Test Cases/ShopOpen/VisitShopOpen"), null)
			Mobile.callTestCase(findTestCase("Test Cases/ShopOpen/VisitShopCategories"), null)
			//todo
		}
		while(true){
			index = ProjectConstants.driver.findElementsByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.ListView[1]/*").size()
			MobileElement lastitembeforeswipe  = ProjectConstants.driver.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout["+index+"]/android.widget.TextView[1]")
			String lastitemnamebeforeswipe = lastitembeforeswipe.getText()
			Mobile.swipe(0, 292, 0, 200)
			index = ProjectConstants.driver.findElementsByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.ListView[1]/*").size()
			MobileElement lastitemafterswipe = ProjectConstants.driver.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout["+index+"]/android.widget.TextView[1]")
			String lastitemnameafterswipe = lastitemafterswipe.getText()
			if(lastitemnamebeforeswipe.equals(lastitemnameafterswipe)){
				break
			}
			else{
				MobileElement shop = ProjectConstants.driver.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout["+index+"]/android.widget.TextView[1]")
				ProjectConstants.driver.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout["+index+"]").click()
				Mobile.callTestCase(findTestCase("Test Cases/ShopOpen/VisitShopCategories"), null)
			}
		}
	}
	@Keyword
	def visitShopCategories(){
		MobileElement channel = ProjectConstants.driver.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.RelativeLayout[1]/android.widget.TextView[1]")
		ProjectConstants.currentvisitingshopchannel = channel.getText()
		int index = 0
		String lastvisitedcategory = ""
		ArrayList<MobileElement> products = ProjectConstants.driver.findElementsByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ListView[1]/*")
		for(int i=1; i<=products.size(); i++){
			MobileElement product = ProjectConstants.driver.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout["+i+"]/android.widget.TextView[1]")
			String productname = product.getText()
			if(productname.equals("Chiller")){
				MobileElement maincategory = ProjectConstants.driver.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout["+i+"]/android.widget.TextView[1]")
				ProjectConstants.currentvisitingmaincategory = maincategory.getText()
				ProjectConstants.driver.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout["+i+"]").click()
				Mobile.callTestCase(findTestCase("ShopOpen/Chiller/VisitChillerWithChillerNotAllocated"), null)
			}
			else if(productname.equals("Chiller Utilization")){
				MobileElement maincategory = ProjectConstants.driver.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout["+i+"]/android.widget.TextView[1]")
				ProjectConstants.currentvisitingmaincategory = maincategory.getText()
				ProjectConstants.driver.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout["+i+"]").click()
				Mobile.callTestCase(findTestCase("Test Cases/ShopOpen/ChillerUtilization/VisitChillerUtilization"), null)
			}
			else if(productname.equals("Additional Picture")){
				ProjectConstants.driver.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout["+i+"]").click()
				Mobile.callTestCase(findTestCase("Test Cases/ShopOpen/AdditionalPicture/VisitAdditionalPicture"), null)
			}
			else if(productname.equals("Competition Tracking")){
				ProjectConstants.driver.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout["+i+"]").click()
				Mobile.callTestCase(findTestCase("Test Cases/ShopOpen/CompetitionTracking/VisitCompetitionTracking"), null)
			}
			else if(productname.equals("Retailer Remarks")){
				ProjectConstants.driver.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout["+i+"]").click()
				Mobile.callTestCase(findTestCase("Test Cases/ShopOpen/RetailerRemarks/VisitRetailerRemarks"), null)
			}
			else if(productname.equals("RTM -Visit Frequency")){
				ProjectConstants.driver.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout["+i+"]").click()
				Mobile.callTestCase(findTestCase("Test Cases/ShopOpen/RTMVisitFrequency/VisitRTMVisitFrequency"), null)
			}
			else if(productname.equals("Hanger Availibility")){
				ProjectConstants.driver.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout["+i+"]").click()
				Mobile.callTestCase(findTestCase("Test Cases/ShopOpen/HangerAvailability/VisitHangerAvailability"), null)
			}
			else{
				MobileElement maincategory = ProjectConstants.driver.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout["+i+"]/android.widget.TextView[1]")
				ProjectConstants.currentvisitingmaincategory = maincategory.getText()
				ProjectConstants.currentvisitingproductcategory = maincategory.getText()
				ProjectConstants.driver.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout["+i+"]").click()
				Mobile.callTestCase(findTestCase("ShopOpen/RemainingCategories/VisitRemainingCategoriesWithNSFD"), null)
			}
		}
		while(true){
			index = ProjectConstants.driver.findElementsByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ListView[1]/*").size()
			MobileElement lastitembeforeswipe = ProjectConstants.driver.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout["+index+"]/android.widget.TextView[1]")
			String lastitemnamebeforeswipe = lastitembeforeswipe.getText()
			Mobile.swipe(0, 293, 0, 200)
			index = ProjectConstants.driver.findElementsByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ListView[1]/*").size()
			MobileElement lastitemafterswipe = ProjectConstants.driver.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout["+index+"]/android.widget.TextView[1]")
			String lastitemnameafterswipe = lastitemafterswipe.getText()
			if(lastitemnamebeforeswipe.equals(lastitemnameafterswipe)){
				break
			}
			else if(lastitemnameafterswipe.equals("Chiller")){
				MobileElement maincategory = ProjectConstants.driver.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout["+index+"]/android.widget.TextView[1]")
				lastvisitedcategory = maincategory.getText()
				ProjectConstants.currentvisitingmaincategory = maincategory.getText()
				ProjectConstants.driver.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout["+index+"]").click()
				Mobile.callTestCase(findTestCase("ShopOpen/Chiller/VisitChillerWithChillerNotAllocated"), null)
			}
			else if(lastitemnameafterswipe.equals("Chiller Utilization")){
				MobileElement maincategory = ProjectConstants.driver.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout["+index+"]/android.widget.TextView[1]")
				lastvisitedcategory = maincategory.getText()
				ProjectConstants.currentvisitingmaincategory = maincategory.getText()
				ProjectConstants.driver.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout["+index+"]").click()
				Mobile.callTestCase(findTestCase("Test Cases/ShopOpen/ChillerUtilization/VisitChillerUtilization"), null)
			}
			else if(lastitemnameafterswipe.equals("Additional Picture")){
				MobileElement maincategory = ProjectConstants.driver.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout["+index+"]/android.widget.TextView[1]")
				Mobile.callTestCase(findTestCase("Test Cases/ShopOpen/AdditionalPicture/VisitAdditionalPicture"), null)
			}
			else if(lastitemnameafterswipe.equals("Competition Tracking")){
				MobileElement maincategory = ProjectConstants.driver.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout["+index+"]/android.widget.TextView[1]")
				Mobile.callTestCase(findTestCase("Test Cases/ShopOpen/CompetitionTracking/VisitCompetitionTracking"), null)
			}
			else if(lastitemnameafterswipe.equals("Retailer Remarks")){
				MobileElement maincategory = ProjectConstants.driver.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout["+index+"]/android.widget.TextView[1]")
				Mobile.callTestCase(findTestCase("Test Cases/ShopOpen/RetailerRemarks/VisitRetailerRemarks"), null)
			}
			else if(lastitemnameafterswipe.equals("RTM -Visit Frequency")){
				MobileElement maincategory = ProjectConstants.driver.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout["+index+"]/android.widget.TextView[1]")
				Mobile.callTestCase(findTestCase("Test Cases/ShopOpen/RTMVisitFrequency/VisitRTMVisitFrequency"), null)
			}
			else if(lastitemnameafterswipe.equals("Hanger Availibility")){
				MobileElement maincategory = ProjectConstants.driver.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout["+index+"]/android.widget.TextView[1]")
				Mobile.callTestCase(findTestCase("Test Cases/ShopOpen/HangerAvailability/VisitHangerAvailability"), null)
			}
			else{
				MobileElement maincategory = ProjectConstants.driver.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout["+index+"]/android.widget.TextView[1]")
				lastvisitedcategory = maincategory.getText()
				ProjectConstants.currentvisitingmaincategory = maincategory.getText()
				ProjectConstants.currentvisitingproductcategory = maincategory.getText()
				ProjectConstants.driver.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout["+index+"]").click()
				Mobile.callTestCase(findTestCase("ShopOpen/RemainingCategories/VisitRemainingCategoriesWithNSFD"), null)
			}
			while(true){
				Mobile.swipe(0, 293, 0, 200)
				index = ProjectConstants.driver.findElementsByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ListView[1]/*").size()
				MobileElement product = ProjectConstants.driver.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout["+index+"]/android.widget.TextView[1]")
				String productname = product.getText()
				if(productname.equals(lastvisitedcategory)){
					break
				}
			}
		}
	}
	@Keyword
	def overwriteShopCategories(){
		int index = 0
		String lastvisitedcategory = ""
		ArrayList<MobileElement> products = ProjectConstants.driver.findElementsByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ListView[1]/*")
		for(int i=1; i<=products.size(); i++){
			MobileElement product = ProjectConstants.driver.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout["+i+"]/android.widget.TextView[1]")
			String productname = product.getText()
			if(productname.equals("Chiller")){
				ProjectConstants.driver.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout["+i+"]").click()
				Mobile.verifyElementExist(findTestObject("Object Repository/CommonScreenElements/Validate_InfoPopUP"), 0)
				Mobile.tap(findTestObject("Object Repository/CommonScreenElements/InfoPopUp_YesButton"), 0)
				Mobile.callTestCase(findTestCase("ShopOpen/Chiller/VisitChillerWithSKDNA"), null)
			}
			else if(productname.equals("Chiller Utilization")){
				ProjectConstants.driver.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout["+i+"]").click()
				Mobile.verifyElementExist(findTestObject("Object Repository/CommonScreenElements/Validate_InfoPopUP"), 0)
				Mobile.tap(findTestObject("Object Repository/CommonScreenElements/InfoPopUp_YesButton"), 0)
				Mobile.callTestCase(findTestCase("Test Cases/ShopOpen/ChillerUtilization/OverwriteChillerUtilization"), null)
			}
			else if(productname.equals("Additional Picture")){
				ProjectConstants.driver.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout["+i+"]").click()
				Mobile.callTestCase(findTestCase("Test Cases/ShopOpen/AdditionalPicture/OverwriteAdditionalPicture"), null)
			}
			else if(productname.equals("Competition Tracking")){
				ProjectConstants.driver.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout["+i+"]").click()
				Mobile.verifyElementExist(findTestObject("Object Repository/CommonScreenElements/Validate_InfoPopUP"), 0)
				Mobile.tap(findTestObject("Object Repository/CommonScreenElements/InfoPopUp_YesButton"), 0)
				Mobile.callTestCase(findTestCase("Test Cases/ShopOpen/CompetitionTracking/OverwriteCompetitionTracking"), null)
			}
			else if(productname.equals("Retailer Remarks")){
				ProjectConstants.driver.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout["+i+"]").click()
				Mobile.verifyElementExist(findTestObject("Object Repository/CommonScreenElements/Validate_InfoPopUP"), 0)
				Mobile.tap(findTestObject("Object Repository/CommonScreenElements/InfoPopUp_YesButton"), 0)
				Mobile.callTestCase(findTestCase("Test Cases/ShopOpen/RetailerRemarks/OverwriteRetailerRemarks"), null)
			}
			else if(productname.equals("RTM -Visit Frequency")){
				ProjectConstants.driver.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout["+i+"]").click()
				Mobile.verifyElementExist(findTestObject("Object Repository/CommonScreenElements/Validate_InfoPopUP"), 0)
				Mobile.tap(findTestObject("Object Repository/CommonScreenElements/InfoPopUp_YesButton"), 0)
				Mobile.callTestCase(findTestCase("Test Cases/ShopOpen/RTMVisitFrequency/OverwriteRTMVisitFrequency"), null)
			}
			else if(productname.equals("Hanger Availibility")){
				ProjectConstants.driver.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout["+i+"]").click()
				Mobile.callTestCase(findTestCase("Test Cases/ShopOpen/HangerAvailability/OverwriteHangerAvailability"), null)
			}
			else{
				ProjectConstants.driver.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout["+i+"]").click()
				Mobile.verifyElementExist(findTestObject("Object Repository/CommonScreenElements/Validate_InfoPopUP"), 0)
				Mobile.tap(findTestObject("Object Repository/CommonScreenElements/InfoPopUp_YesButton"), 0)
				Mobile.callTestCase(findTestCase("ShopOpen/RemainingCategories/VisitRemainingCategoriesWithSKDNA"), null)
			}
		}
		while(true){
			index = ProjectConstants.driver.findElementsByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ListView[1]/*").size()
			MobileElement lastitembeforeswipe = ProjectConstants.driver.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout["+index+"]/android.widget.TextView[1]")
			String lastitemnamebeforeswipe = lastitembeforeswipe.getText()
			Mobile.swipe(0, 293, 0, 200)
			index = ProjectConstants.driver.findElementsByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ListView[1]/*").size()
			MobileElement lastitemafterswipe = ProjectConstants.driver.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout["+index+"]/android.widget.TextView[1]")
			String lastitemnameafterswipe = lastitemafterswipe.getText()
			if(lastitemnamebeforeswipe.equals(lastitemnameafterswipe)){
				break
			}
			else if(lastitemnameafterswipe.equals("Chiller")){
				MobileElement shop = ProjectConstants.driver.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout["+index+"]/android.widget.TextView[1]")
				lastvisitedcategory = shop.getText()
				ProjectConstants.driver.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout["+index+"]").click()
				Mobile.verifyElementExist(findTestObject("Object Repository/CommonScreenElements/Validate_InfoPopUP"), 0)
				Mobile.tap(findTestObject("Object Repository/CommonScreenElements/InfoPopUp_YesButton"), 0)
				Mobile.callTestCase(findTestCase("ShopOpen/Chiller/VisitChillerWithSKDNA"), null)
			}
			else if(lastitemnameafterswipe.equals("Chiller Utilization")){
				MobileElement shop = ProjectConstants.driver.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout["+index+"]/android.widget.TextView[1]")
				lastvisitedcategory = shop.getText()
				ProjectConstants.driver.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout["+index+"]").click()
				Mobile.verifyElementExist(findTestObject("Object Repository/CommonScreenElements/Validate_InfoPopUP"), 0)
				Mobile.tap(findTestObject("Object Repository/CommonScreenElements/InfoPopUp_YesButton"), 0)
				Mobile.callTestCase(findTestCase("Test Cases/ShopOpen/ChillerUtilization/OverwriteChillerUtilization"), null)
			}
			else if(lastitemnameafterswipe.equals("Additional Picture")){
				MobileElement shop = ProjectConstants.driver.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout["+index+"]/android.widget.TextView[1]")
				lastvisitedcategory = shop.getText()
				ProjectConstants.driver.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout["+index+"]").click()
				Mobile.callTestCase(findTestCase("Test Cases/ShopOpen/AdditionalPicture/OverwriteAdditionalPicture"), null)
			}
			else if(lastitemnameafterswipe.equals("Competition Tracking")){
				MobileElement shop = ProjectConstants.driver.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout["+index+"]/android.widget.TextView[1]")
				lastvisitedcategory = shop.getText()
				ProjectConstants.driver.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout["+index+"]").click()
				Mobile.verifyElementExist(findTestObject("Object Repository/CommonScreenElements/Validate_InfoPopUP"), 0)
				Mobile.tap(findTestObject("Object Repository/CommonScreenElements/InfoPopUp_YesButton"), 0)
				Mobile.callTestCase(findTestCase("Test Cases/ShopOpen/CompetitionTracking/OverwriteCompetitionTracking"), null)
			}
			else if(lastitemnameafterswipe.equals("Retailer Remarks")){
				MobileElement shop = ProjectConstants.driver.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout["+index+"]/android.widget.TextView[1]")
				lastvisitedcategory = shop.getText()
				ProjectConstants.driver.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout["+index+"]").click()
				Mobile.verifyElementExist(findTestObject("Object Repository/CommonScreenElements/Validate_InfoPopUP"), 0)
				Mobile.tap(findTestObject("Object Repository/CommonScreenElements/InfoPopUp_YesButton"), 0)
				Mobile.callTestCase(findTestCase("Test Cases/ShopOpen/RetailerRemarks/OverwriteRetailerRemarks"), null)
			}
			else if(lastitemnameafterswipe.equals("RTM -Visit Frequency")){
				MobileElement shop = ProjectConstants.driver.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout["+index+"]/android.widget.TextView[1]")
				lastvisitedcategory = shop.getText()
				ProjectConstants.driver.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout["+index+"]").click()
				Mobile.verifyElementExist(findTestObject("Object Repository/CommonScreenElements/Validate_InfoPopUP"), 0)
				Mobile.tap(findTestObject("Object Repository/CommonScreenElements/InfoPopUp_YesButton"), 0)
				Mobile.callTestCase(findTestCase("Test Cases/ShopOpen/RTMVisitFrequency/OverwriteRTMVisitFrequency"), null)
			}
			else if(lastitemnameafterswipe.equals("Hanger Availibility")){
				MobileElement shop = ProjectConstants.driver.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout["+index+"]/android.widget.TextView[1]")
				lastvisitedcategory = shop.getText()
				ProjectConstants.driver.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout["+index+"]").click()
				Mobile.callTestCase(findTestCase("Test Cases/ShopOpen/HangerAvailability/OverwriteHangerAvailability"), null)
			}
			else{
				MobileElement shop = ProjectConstants.driver.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout["+index+"]/android.widget.TextView[1]")
				lastvisitedcategory = shop.getText()
				ProjectConstants.driver.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout["+index+"]").click()
				Mobile.verifyElementExist(findTestObject("Object Repository/CommonScreenElements/Validate_InfoPopUP"), 0)
				Mobile.tap(findTestObject("Object Repository/CommonScreenElements/InfoPopUp_YesButton"), 0)
				Mobile.callTestCase(findTestCase("Test Cases/ShopOpen/RTMVisitFrequency/OverwriteRTMVisitFrequency"), null)
			}
			while(true){
				Mobile.swipe(0, 293, 0, 200)
				index = ProjectConstants.driver.findElementsByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ListView[1]/*").size()
				MobileElement product = ProjectConstants.driver.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout["+index+"]/android.widget.TextView[1]")
				String productname = product.getText()
				if(productname.equals(lastvisitedcategory)){
					break
				}
			}
		}
	}
}
