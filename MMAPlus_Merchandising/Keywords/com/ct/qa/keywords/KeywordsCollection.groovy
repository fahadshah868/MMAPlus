package com.ct.qa.keywords


import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import java.awt.Image
import java.awt.Toolkit
import java.awt.image.BufferedImage
import java.awt.image.DataBuffer
import java.awt.image.PixelGrabber
import java.awt.image.Raster
import java.text.SimpleDateFormat

import java.time.DayOfWeek

import org.apache.poi.ss.usermodel.DataFormatter
import org.apache.poi.ss.usermodel.Row
import org.apache.poi.xssf.usermodel.XSSFSheet
import org.apache.poi.xssf.usermodel.XSSFWorkbook

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.checkpoint.Checkpoint
import com.kms.katalon.core.checkpoint.CheckpointFactory
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords
import com.kms.katalon.core.mobile.keyword.internal.MobileDriverFactory
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testcase.TestCase
import com.kms.katalon.core.testcase.TestCaseFactory
import com.kms.katalon.core.testdata.TestData
import com.kms.katalon.core.testdata.TestDataFactory
import com.kms.katalon.core.testobject.ObjectRepository
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords
import com.kms.katalon.core.webui.driver.DriverFactory
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords

import io.appium.java_client.AppiumDriver
import io.appium.java_client.MobileElement
import io.appium.java_client.ScreenshotState
import MobileBuiltInKeywords as Mobile
import WSBuiltInKeywords as WS
import WebUiBuiltInKeywords as WebUI
import com.ct.qa.constants.ProjectConstants
import com.ct.qa.struct.ChannelProducts


public class KeywordsCollection {

	@Keyword
	def selectday(){
		Calendar calendar = Calendar.getInstance();
		int day = calendar.get(Calendar.DAY_OF_WEEK);
		ArrayList<MobileElement> days = ProjectConstants.driver.findElementsByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.LinearLayout[1]/android.widget.ListView[1]/*")
		if(day == 1){
			ProjectConstants.driver.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.LinearLayout[1]/android.widget.ListView[1]/android.widget.TextView[7]").click()
		}
		else if(day == 2){
			ProjectConstants.driver.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.LinearLayout[1]/android.widget.ListView[1]/android.widget.TextView[1]").click()
		}
		else if(day == 3){
			ProjectConstants.driver.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.LinearLayout[1]/android.widget.ListView[1]/android.widget.TextView[2]").click()
		}
		else if(day == 4){
			ProjectConstants.driver.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.LinearLayout[1]/android.widget.ListView[1]/android.widget.TextView[3]").click()
		}
		else if(day == 5){
			ProjectConstants.driver.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.LinearLayout[1]/android.widget.ListView[1]/android.widget.TextView[4]").click()
		}
		else if(day == 6){
			ProjectConstants.driver.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.LinearLayout[1]/android.widget.ListView[1]/android.widget.TextView[5]").click()
		}
		else if(day == 7){
			ProjectConstants.driver.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.LinearLayout[1]/android.widget.ListView[1]/android.widget.TextView[6]").click()
		}
	}
	@Keyword
	def visitdistributionPoint(){
		DataFormatter dataFormatter = new DataFormatter()
		XSSFSheet sheet = ProjectConstants.loadDistributionPointSheet()
		int totalrows = sheet.getLastRowNum()
		ArrayList<MobileElement> distributionitems = ProjectConstants.driver.findElementsByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/*")
		for(int i=1; i<=(distributionitems.size()-2); i++){
			MobileElement distributionitem = ProjectConstants.driver.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.LinearLayout["+i+"]/android.widget.TextView[1]")
			String distributionitemname = distributionitem.getText()
			for(int j=1; j<=totalrows; j++){
				Row row = sheet.getRow(j)
				String distributionpoint = row.getCell(0)
				String distributionpointvalue = dataFormatter.formatCellValue(row.getCell(1))
				if(distributionitemname.contains(distributionpoint)){
					MobileElement distributionitemtextfield = ProjectConstants.driver.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.LinearLayout["+i+"]/android.widget.EditText[1]")
					distributionitemtextfield.setValue(distributionpointvalue)
					Mobile.hideKeyboard()
					break
				}
			}
		}
	}
	@Keyword
	def findPictureImageView(){
		try{
			ProjectConstants.driver.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.LinearLayout[1]/android.widget.LinearLayout[2]/android.widget.ImageButton[1]").isDisplayed()
			Mobile.tap(findTestObject("Object Repository/CommonScreenElements/Picture_ImageView"), 0)
			Mobile.verifyElementExist(findTestObject("Object Repository/CommonScreenElements/Validate_CameraScreen"), 0)
			Mobile.tap(findTestObject("Object Repository/CommonScreenElements/TakePictureButton"), 0)
			Mobile.delay(5)
			Mobile.tap(findTestObject("Object Repository/CommonScreenElements/DoneButton"), 0)
		}
		catch(Exception ex){
		}
	}
	@Keyword
	def selectShop(){
		int index = 0
		ArrayList<MobileElement> shops = ProjectConstants.driver.findElementsByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.ListView[1]/*")
		for(int i=2; i<=shops.size(); i++){
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
		for(int i=2; i<=products.size(); i++){
			MobileElement product = ProjectConstants.driver.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout["+i+"]/android.widget.TextView[1]")
			String productname = product.getText()
			if(productname.equals("Chiller")){
				MobileElement maincategory = ProjectConstants.driver.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout["+i+"]/android.widget.TextView[1]")
				ProjectConstants.currentvisitingmaincategory = maincategory.getText()
				ProjectConstants.driver.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout["+i+"]").click()
				Mobile.callTestCase(findTestCase("Test Cases/ShopOpen/Chiller/VisitChiller"), null)
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
				Mobile.callTestCase(findTestCase("ShopOpen/RemainingCategories/VisitRemainingCategory"), null)
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
				Mobile.callTestCase(findTestCase("Test Cases/ShopOpen/Chiller/VisitChiller"), null)
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
				Mobile.callTestCase(findTestCase("ShopOpen/RemainingCategories/VisitRemainingCategory"), null)
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
				Mobile.callTestCase(findTestCase("Test Cases/ShopOpen/Chiller/OverwriteChiller"), null)
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
				Mobile.callTestCase(findTestCase("ShopOpen/RemainingCategories/OverwriteRemainingCategory"), null)
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
				Mobile.callTestCase(findTestCase("Test Cases/ShopOpen/Chiller/OverwriteChiller"), null)
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
	@Keyword
	def checkPlanogramAvailability(){
		try{
			ProjectConstants.driver.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]").isDisplayed()
			Mobile.tap(findTestObject("Object Repository/CommonScreenElements/InfoPopUp_OKButton"), 0)
		}
		catch(Exception ex){
		}
	}
	@Keyword
	def visitChillerNotAllocatedProductCategories(){
		ArrayList<MobileElement> productcategories = ProjectConstants.driver.findElementsByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.ListView[1]/*")
		for(int i=1; i<=productcategories.size(); i++){
			MobileElement productcategory = ProjectConstants.driver.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout["+i+"]/android.widget.TextView[1]")
			ProjectConstants.currentvisitingproductcategory = productcategory.getText()
			ProjectConstants.driver.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout["+i+"]").click()
			Mobile.callTestCase(findTestCase("Test Cases/ShopOpen/Chiller/VisitProductCategoryAssets"), null)
		}
	}
	def loadProductsList(XSSFSheet sheet, int column){
		DataFormatter dataformatter = new DataFormatter()
		ArrayList<ChannelProducts> channelproducts = new ArrayList<ChannelProducts>()
		int totalrows = sheet.getLastRowNum()
		for(int i=1; i<=totalrows; i++){
			Row row = sheet.getRow(i)
			String channel = dataformatter.formatCellValue(row.getCell(ProjectConstants.channel))
			String maincategory = dataformatter.formatCellValue(row.getCell(ProjectConstants.channel_maincategory))
			String productcategory = dataformatter.formatCellValue(row.getCell(ProjectConstants.channel_productcategory))
			if(ProjectConstants.currentvisitingshopchannel.contains(channel) && ProjectConstants.currentvisitingmaincategory.equals(maincategory) && ProjectConstants.currentvisitingproductcategory.equals(productcategory)){
				ChannelProducts channelproduct = new ChannelProducts()
				String product = dataformatter.formatCellValue(row.getCell(ProjectConstants.channel_product))
				String columndata = dataformatter.formatCellValue(row.getCell(column))
				channelproduct.setProduct(product)
				channelproduct.setProduct_data(columndata)
				channelproducts.add(channelproduct)
			}
		}
		return channelproducts
	}
	@Keyword
	def visitChillerNotAllocatedProductCategoryFacing(){
		int index = 0
		XSSFSheet channelproductssheet = ProjectConstants.loadChannelProductsSheet()
		ArrayList<ChannelProducts> channelproducts = loadProductsList(channelproductssheet,ProjectConstants.channel_chiller_facing)
		ArrayList<MobileElement> products = ProjectConstants.driver.findElementsByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ScrollView[1]/android.widget.LinearLayout[1]/*")
		for(int i=2; i<products.size(); i=i+3){
			index = index + 1
			MobileElement selectedproduct = ProjectConstants.driver.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ScrollView[1]/android.widget.LinearLayout[1]/android.widget.TextView["+index+"]")
			String selectedproductname = selectedproduct.getText()
			for(int j=0; j<channelproducts.size(); j++){
				ChannelProducts channelproduct = channelproducts.get(j)
				String productname = channelproduct.getProduct()
				if(selectedproductname.equals(productname)){
					String productquantity = channelproduct.getProduct_data()
					MobileElement selectedproducttextfield = ProjectConstants.driver.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ScrollView[1]/android.widget.LinearLayout[1]/android.widget.LinearLayout["+index+"]/android.widget.EditText[1]")
					selectedproducttextfield.setValue(productquantity)
					Mobile.hideKeyboard()
				}
			}
		}
		//todo
	}
	@Keyword
	def visitChillerNotAllocatedProductCategoryStockTaking(){
		int index = 0
		XSSFSheet channelproductssheet = ProjectConstants.loadChannelProductsSheet()
		ArrayList<ChannelProducts> channelproducts = loadProductsList(channelproductssheet,ProjectConstants.channel_chiller_stocktaking)
		ArrayList<MobileElement> products = ProjectConstants.driver.findElementsByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ScrollView[1]/android.widget.LinearLayout[1]/*")
		for(int i=2; i<products.size(); i=i+3){
			index = index + 1
			MobileElement selectedproduct = ProjectConstants.driver.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ScrollView[1]/android.widget.LinearLayout[1]/android.widget.TextView["+index+"]")
			String selectedproductname = selectedproduct.getText()
			for(int j=0; j<channelproducts.size(); j++){
				ChannelProducts channelproduct = channelproducts.get(j)
				String productname = channelproduct.getProduct()
				if(selectedproductname.equals(productname)){
					String productquantity = channelproduct.getProduct_data()
					MobileElement selectedproducttextfield = ProjectConstants.driver.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ScrollView[1]/android.widget.LinearLayout[1]/android.widget.LinearLayout["+index+"]/android.widget.EditText[1]")
					selectedproducttextfield.setValue(productquantity)
					Mobile.hideKeyboard()
				}
			}
		}
		//todo
	}
	@Keyword
	def visitDisplaySpaceAvailableFacing(){
		int index = 0
		XSSFSheet channelproductssheet = ProjectConstants.loadChannelProductsSheet()
		ArrayList<ChannelProducts> channelproducts = loadProductsList(channelproductssheet,ProjectConstants.channel_dsa_facing)
		ArrayList<MobileElement> products = ProjectConstants.driver.findElementsByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ScrollView[1]/android.widget.LinearLayout[1]/*")
		for(int i=2; i<products.size(); i=i+3){
			index = index + 1
			MobileElement selectedproduct = ProjectConstants.driver.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ScrollView[1]/android.widget.LinearLayout[1]/android.widget.TextView["+index+"]")
			String selectedproductname = selectedproduct.getText()
			for(int j=0; j<channelproducts.size(); j++){
				ChannelProducts channelproduct = channelproducts.get(j)
				String productname = channelproduct.getProduct()
				if(selectedproductname.equals(productname)){
					String productquantity = channelproduct.getProduct_data()
					MobileElement selectedproducttextfield = ProjectConstants.driver.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ScrollView[1]/android.widget.LinearLayout[1]/android.widget.LinearLayout["+index+"]/android.widget.EditText[1]")
					selectedproducttextfield.setValue(productquantity)
					Mobile.hideKeyboard()
				}
			}
		}
	}
	@Keyword
	def visitDisplaySpaceAvailableStockTaking(){
		int index = 0
		XSSFSheet channelproductssheet = ProjectConstants.loadChannelProductsSheet()
		ArrayList<ChannelProducts> channelproducts = loadProductsList(channelproductssheet,ProjectConstants.channel_dsa_stocktaking)
		ArrayList<MobileElement> products = ProjectConstants.driver.findElementsByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ScrollView[1]/android.widget.LinearLayout[1]/*")
		for(int i=2; i<products.size(); i=i+3){
			index = index + 1
			MobileElement selectedproduct = ProjectConstants.driver.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ScrollView[1]/android.widget.LinearLayout[1]/android.widget.TextView["+index+"]")
			String selectedproductname = selectedproduct.getText()
			for(int j=0; j<channelproducts.size(); j++){
				ChannelProducts channelproduct = channelproducts.get(j)
				String productname = channelproduct.getProduct()
				if(selectedproductname.equals(productname)){
					String productquantity = channelproduct.getProduct_data()
					MobileElement selectedproducttextfield = ProjectConstants.driver.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ScrollView[1]/android.widget.LinearLayout[1]/android.widget.LinearLayout["+index+"]/android.widget.EditText[1]")
					selectedproducttextfield.setValue(productquantity)
					Mobile.hideKeyboard()
				}
			}
		}
	}
	@Keyword
	def visitNoSpaceForDisplayFacing(){
		int index = 0
		XSSFSheet channelproductssheet = ProjectConstants.loadChannelProductsSheet()
		ArrayList<ChannelProducts> channelproducts = loadProductsList(channelproductssheet,ProjectConstants.channel_nsfd_facing)
		ArrayList<MobileElement> products = ProjectConstants.driver.findElementsByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ScrollView[1]/android.widget.LinearLayout[1]/*")
		for(int i=2; i<products.size(); i=i+3){
			index = index + 1
			MobileElement selectedproduct = ProjectConstants.driver.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ScrollView[1]/android.widget.LinearLayout[1]/android.widget.TextView["+index+"]")
			String selectedproductname = selectedproduct.getText()
			for(int j=0; j<channelproducts.size(); j++){
				ChannelProducts channelproduct = channelproducts.get(j)
				String productname = channelproduct.getProduct()
				if(selectedproductname.equals(productname)){
					String productquantity = channelproduct.getProduct_data()
					MobileElement selectedproducttextfield = ProjectConstants.driver.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ScrollView[1]/android.widget.LinearLayout[1]/android.widget.LinearLayout["+index+"]/android.widget.EditText[1]")
					selectedproducttextfield.setValue(productquantity)
					Mobile.hideKeyboard()
				}
			}
		}
	}
	@Keyword
	def visitNoSpaceForDisplayStockTaking(){
		int index = 0
		XSSFSheet channelproductssheet = ProjectConstants.loadChannelProductsSheet()
		ArrayList<ChannelProducts> channelproducts = loadProductsList(channelproductssheet,ProjectConstants.channel_nsfd_stocktaking)
		ArrayList<MobileElement> products = ProjectConstants.driver.findElementsByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ScrollView[1]/android.widget.LinearLayout[1]/*")
		for(int i=2; i<products.size(); i=i+3){
			index = index + 1
			MobileElement selectedproduct = ProjectConstants.driver.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ScrollView[1]/android.widget.LinearLayout[1]/android.widget.TextView["+index+"]")
			String selectedproductname = selectedproduct.getText()
			for(int j=0; j<channelproducts.size(); j++){
				ChannelProducts channelproduct = channelproducts.get(j)
				String productname = channelproduct.getProduct()
				if(selectedproductname.equals(productname)){
					String productquantity = channelproduct.getProduct_data()
					MobileElement selectedproducttextfield = ProjectConstants.driver.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ScrollView[1]/android.widget.LinearLayout[1]/android.widget.LinearLayout["+index+"]/android.widget.EditText[1]")
					selectedproducttextfield.setValue(productquantity)
					Mobile.hideKeyboard()
				}
			}
		}
	}
	//	@Keyword
	//	def overwriteChillerJuices(){
	//		int index = 0
	//		ArrayList<MobileElement> juices = ProjectConstants.driver.findElementsByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ScrollView[1]/android.widget.LinearLayout[1]/*")
	//		for(int i=2; i<=juices.size(); i=i+3){
	//			index = index+1
	//			MobileElement juice = ProjectConstants.driver.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ScrollView[1]/android.widget.LinearLayout[1]/android.widget.TextView["+index+"]")
	//			String juicename = juice.getText()
	//			MobileElement textfield = ProjectConstants.driver.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ScrollView[1]/android.widget.LinearLayout[1]/android.widget.LinearLayout["+index+"]/android.widget.EditText[1]")
	//			if(juicename.equals("NESFRUTA MANGO 200ML")){
	//				textfield.setValue(dataforoverwritechillerproducts.getObjectValue("Nesfruta Mango 200ml", 1))
	//				Mobile.hideKeyboard()
	//			}
	//			else if(juicename.equals("FRUITA VITALS CHAUNSA 200ML")){
	//				textfield.setValue(dataforoverwritechillerproducts.getObjectValue("Fruita Vitals Chaunsa 200ml", 1))
	//				Mobile.hideKeyboard()
	//			}
	//			else if(juicename.equals("FRUITA VITALS RED GRAPES 200ML")){
	//				textfield.setValue(dataforoverwritechillerproducts.getObjectValue("Fruita Vitals Red Grapes 200ml", 1))
	//				Mobile.hideKeyboard()
	//			}
	//			else if(juicename.equals("FRUITA VITALS APPLE 200ML")){
	//				textfield.setValue(dataforoverwritechillerproducts.getObjectValue("Fruita Vitals Apple 200ml", 1))
	//				Mobile.hideKeyboard()
	//			}
	//			juices = ProjectConstants.driver.findElementsByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ScrollView[1]/android.widget.LinearLayout[1]/*")
	//		}
	//	}
	//	@Keyword
	//	def findShopProduct(String _productname){
	//		boolean flag = false
	//		int index = 0
	//		ArrayList<MobileElement> products = ProjectConstants.driver.findElementsByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ListView[1]/*")
	//		for(int i=1; i<=products.size(); i++){
	//			MobileElement product = ProjectConstants.driver.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout["+i+"]/android.widget.TextView[1]")
	//			String productname = product.getText()
	//			if(productname.equals(_productname)){
	//				flag = true
	//				ProjectConstants.driver.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout["+i+"]").click()
	//				Mobile.verifyElementExist(findTestObject("Object Repository/CommonScreenElements/Validate_InfoPopUP"), 0)
	//				Mobile.tap(findTestObject("Object Repository/CommonScreenElements/InfoPopUp_YesButton"), 0)
	//				break
	//			}
	//			products = ProjectConstants.driver.findElementsByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ListView[1]/*")
	//		}
	//		if(flag == false){
	//			while(true){
	//				index = ProjectConstants.driver.findElementsByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ListView[1]/*").size()
	//				MobileElement lastitembeforeswipe = ProjectConstants.driver.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout["+index+"]/android.widget.TextView[1]")
	//				String lastitemnamebeforeswipe = lastitembeforeswipe.getText()
	//				Mobile.swipe(0, 293, 0, 200)
	//				index = ProjectConstants.driver.findElementsByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ListView[1]/*").size()
	//				MobileElement lastitemafterswipe = ProjectConstants.driver.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout["+index+"]/android.widget.TextView[1]")
	//				String lastitemnameafterswipe = lastitemafterswipe.getText()
	//				if(lastitemnamebeforeswipe.equals(lastitemnameafterswipe)){
	//					break
	//				}
	//				else if(lastitemnameafterswipe.equals(_productname)){
	//					ProjectConstants.driver.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout["+index+"]").click()
	//					Mobile.verifyElementExist(findTestObject("Object Repository/CommonScreenElements/Validate_InfoPopUP"), 0)
	//					Mobile.tap(findTestObject("Object Repository/CommonScreenElements/InfoPopUp_YesButton"), 0)
	//					break
	//				}
	//			}
	//		}
	//	}
	//	@Keyword
	//	def visitSachetsProducts(){
	//		int index = 0
	//		ArrayList<MobileElement> products = ProjectConstants.driver.findElementsByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ScrollView[1]/android.widget.LinearLayout[1]/*")
	//		for(int i=2;i<=products.size(); i=i+3){
	//			index = index+1
	//			MobileElement product = ProjectConstants.driver.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ScrollView[1]/android.widget.LinearLayout[1]/android.widget.TextView["+index+"]")
	//			String productname = product.getText()
	//			MobileElement textfield = ProjectConstants.driver.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ScrollView[1]/android.widget.LinearLayout[1]/android.widget.LinearLayout["+index+"]/android.widget.EditText[1]")
	//			if(productname.equals("EVERYDAY 16.8GM")){
	//				textfield.setValue(dataforvisitsachetproducts.getObjectValue("Everyday 16.8gm", 1))
	//				Mobile.hideKeyboard()
	//			}
	//			else if(productname.equals("CERELAC 3 FRUIT 25GM")){
	//				textfield.setValue(dataforvisitsachetproducts.getObjectValue("Cerelac 3 Fruit 25gm", 1))
	//				Mobile.hideKeyboard()
	//			}
	//			else if(productname.equals("NESTLE BUNYAD 26GM")){
	//				textfield.setValue(dataforvisitsachetproducts.getObjectValue("Nestle Bunyad 26gm", 1))
	//				Mobile.hideKeyboard()
	//			}
	//			else if(productname.equals("CERELAC RICE 25GM")){
	//				textfield.setValue(dataforvisitsachetproducts.getObjectValue("Cerelac Rice 25gm", 1))
	//				Mobile.hideKeyboard()
	//			}
	//			else if(productname.equals("EVERYDAY 34GM")){
	//				textfield.setValue(dataforvisitsachetproducts.getObjectValue("Everyday 34gm", 1))
	//				Mobile.hideKeyboard()
	//			}
	//			else if(productname.equals("CERELAC Wheat 25GM")){
	//				textfield.setValue(dataforvisitsachetproducts.getObjectValue("Cerelac Wheat 25gm", 1))
	//				Mobile.hideKeyboard()
	//			}
	//			products = ProjectConstants.driver.findElementsByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ScrollView[1]/android.widget.LinearLayout[1]/*")
	//		}
	//	}
	//	@Keyword
	//	def overwriteSachetsProducts(){
	//		int index = 0
	//		ArrayList<MobileElement> products = ProjectConstants.driver.findElementsByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ScrollView[1]/android.widget.LinearLayout[1]/*")
	//		for(int i=2;i<=products.size(); i=i+3){
	//			index = index+1
	//			MobileElement product = ProjectConstants.driver.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ScrollView[1]/android.widget.LinearLayout[1]/android.widget.TextView["+index+"]")
	//			String productname = product.getText()
	//			MobileElement textfield = ProjectConstants.driver.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ScrollView[1]/android.widget.LinearLayout[1]/android.widget.LinearLayout["+index+"]/android.widget.EditText[1]")
	//			if(productname.equals("EVERYDAY 16.8GM")){
	//				textfield.setValue(dataforoverwritesachetproducts.getObjectValue("Everyday 16.8gm", 1))
	//				Mobile.hideKeyboard()
	//			}
	//			else if(productname.equals("CERELAC 3 FRUIT 25GM")){
	//				textfield.setValue(dataforoverwritesachetproducts.getObjectValue("Cerelac 3 Fruit 25gm", 1))
	//				Mobile.hideKeyboard()
	//			}
	//			else if(productname.equals("NESTLE BUNYAD 26GM")){
	//				textfield.setValue(dataforoverwritesachetproducts.getObjectValue("Nestle Bunyad 26gm", 1))
	//				Mobile.hideKeyboard()
	//			}
	//			else if(productname.equals("CERELAC RICE 25GM")){
	//				textfield.setValue(dataforoverwritesachetproducts.getObjectValue("Cerelac Rice 25gm", 1))
	//				Mobile.hideKeyboard()
	//			}
	//			else if(productname.equals("EVERYDAY 34GM")){
	//				textfield.setValue(dataforoverwritesachetproducts.getObjectValue("Everyday 34gm", 1))
	//				Mobile.hideKeyboard()
	//			}
	//			else if(productname.equals("CERELAC Wheat 25GM")){
	//				textfield.setValue(dataforoverwritesachetproducts.getObjectValue("Cerelac Wheat 25gm", 1))
	//				Mobile.hideKeyboard()
	//			}
	//			products = ProjectConstants.driver.findElementsByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ScrollView[1]/android.widget.LinearLayout[1]/*")
	//		}
	//	}
	//	@Keyword
	//	def visitCompetitiontrackingProducts(){
	//		int index = 0
	//		int val = 10
	//		ArrayList<MobileElement> products = ProjectConstants.driver.findElementsByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ScrollView[1]/android.widget.LinearLayout[1]/*")
	//		for(int i=2; i<=products.size(); i=i+3){
	//			index = index+1
	//			val = val+1
	//			MobileElement textfield = ProjectConstants.driver.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ScrollView[1]/android.widget.LinearLayout[1]/android.widget.LinearLayout["+index+"]/android.widget.EditText[1]")
	//			textfield.setValue(val.toString())
	//			Mobile.hideKeyboard()
	//			products = ProjectConstants.driver.findElementsByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ScrollView[1]/android.widget.LinearLayout[1]/*")
	//		}
	//	}
	//	@Keyword
	//	def overwriteCompetitiontrackingProducts(){
	//		int index = 0
	//		int val = 20
	//		ArrayList<MobileElement> products = ProjectConstants.driver.findElementsByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ScrollView[1]/android.widget.LinearLayout[1]/*")
	//		for(int i=2; i<=products.size(); i=i+3){
	//			index = index+1
	//			val = val+1
	//			MobileElement textfield = ProjectConstants.driver.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ScrollView[1]/android.widget.LinearLayout[1]/android.widget.LinearLayout["+index+"]/android.widget.EditText[1]")
	//			textfield.setValue(val.toString())
	//			Mobile.hideKeyboard()
	//			products = ProjectConstants.driver.findElementsByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ScrollView[1]/android.widget.LinearLayout[1]/*")
	//		}
	//	}
	//	@Keyword
	//	def visitRTMVisitFrequency(){
	//		ArrayList<MobileElement> products = ProjectConstants.driver.findElementsByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ListView[1]/*")
	//		for(int i=1; i<=products.size(); i++){
	//			ProjectConstants.driver.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout["+i+"]").click()
	//			Mobile.verifyElementExist(findTestObject("Object Repository/ShopOpen/RTMVisitFrequency/Validate_RTMVisitFrequencyRemarks"), 0)
	//			Mobile.tap(findTestObject("Object Repository/ShopOpen/RTMVisitFrequency/OnceAWeek"), 0)
	//			products = ProjectConstants.driver.findElementsByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ListView[1]/*")
	//		}
	//	}
	//	@Keyword
	//	def overwriteRTMVisitFrequency(){
	//		ArrayList<MobileElement> products = ProjectConstants.driver.findElementsByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ListView[1]/*")
	//		for(int i=1; i<=products.size(); i++){
	//			ProjectConstants.driver.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout["+i+"]").click()
	//			Mobile.verifyElementExist(findTestObject("Object Repository/ShopOpen/RTMVisitFrequency/Validate_RTMVisitFrequencyRemarks"), 0)
	//			Mobile.tap(findTestObject("Object Repository/ShopOpen/RTMVisitFrequency/TwiceAWeek"), 0)
	//			products = ProjectConstants.driver.findElementsByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ListView[1]/*")
	//		}
	//	}
	//	@Keyword
	//	def visitRetailerRemarks(){
	//		ArrayList<MobileElement> products = ProjectConstants.driver.findElementsByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ListView[1]/*")
	//		for(int i=1; i<=products.size(); i++){
	//			ProjectConstants.driver.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout["+i+"]").click()
	//			Mobile.verifyElementText(findTestObject("Object Repository/ShopOpen/RetailerRemarks/Validate_RetailerRemarksDetailScreen"), "Channel:Small Kiryana")
	//			Mobile.tap(findTestObject("Object Repository/ShopOpen/RetailerRemarks/OBNotVisiting"), 0)
	//			Mobile.pressBack()
	//			Mobile.verifyElementText(findTestObject("Object Repository/ShopOpen/RetailerRemarks/Validate_RetailerRemarksScreen"), "KPI: Retailer Remarks")
	//			products = ProjectConstants.driver.findElementsByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ListView[1]/*")
	//		}
	//	}
	//	@Keyword
	//	def overwriteRetailerRemarks(){
	//		ArrayList<MobileElement> products = ProjectConstants.driver.findElementsByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ListView[1]/*")
	//		for(int i=1; i<=products.size(); i++){
	//			ProjectConstants.driver.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout["+i+"]").click()
	//			Mobile.verifyElementText(findTestObject("Object Repository/ShopOpen/RetailerRemarks/Validate_RetailerRemarksDetailScreen"), "Channel:Small Kiryana")
	//			Mobile.tap(findTestObject("Object Repository/ShopOpen/RetailerRemarks/SMNotVisiting"), 0)
	//			Mobile.pressBack()
	//			Mobile.verifyElementText(findTestObject("Object Repository/ShopOpen/RetailerRemarks/Validate_RetailerRemarksScreen"), "KPI: Retailer Remarks")
	//			products = ProjectConstants.driver.findElementsByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ListView[1]/*")
	//		}
	//	}
	//	@Keyword
	//	def visitChillerUtilization(){
	//		ArrayList<MobileElement> actions = ProjectConstants.driver.findElementsByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ListView[1]/*")
	//		for(int i=1; i<=actions.size(); i++){
	//			MobileElement action = ProjectConstants.driver.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout["+i+"]/android.widget.TextView[1]")
	//			String actionname = action.getText()
	//			if(actionname.equals("Chiller Available")){
	//				ProjectConstants.driver.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout["+i+"]").click()
	//				Mobile.callTestCase(findTestCase("Test Cases/ShopOpen/ChillerUtilization/ChillerAvailable/VisitChillerAvailable"), null)
	//				actions = ProjectConstants.driver.findElementsByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ListView[1]/*")
	//			}
	//			else if(actionname.equals("Chiller not Available")){
	//				ProjectConstants.driver.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout["+i+"]").click()
	//				Mobile.callTestCase(findTestCase("Test Cases/ShopOpen/ChillerUtilization/ChillerNotAvailable/VisitChillerNotAvailable"), null)
	//				actions = ProjectConstants.driver.findElementsByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ListView[1]/*")
	//			}
	//			else if(actionname.equals("Chiller need maintenance")){
	//				ProjectConstants.driver.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout["+i+"]").click()
	//				Mobile.callTestCase(findTestCase("Test Cases/ShopOpen/ChillerUtilization/ChillerNeedMaintenance/VisitChillerNeedMaintenance"), null)
	//				actions = ProjectConstants.driver.findElementsByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ListView[1]/*")
	//			}
	//			else if(actionname.equals("Chiller removed for maintenance")){
	//				ProjectConstants.driver.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout["+i+"]").click()
	//				Mobile.callTestCase(findTestCase("Test Cases/ShopOpen/ChillerUtilization/ChillerRemovedForMaintenance/VisitChillerRemovedForMaintenance"), null)
	//				actions = ProjectConstants.driver.findElementsByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ListView[1]/*")
	//			}
	//			else if(actionname.equals("Chiller not in access")){
	//				ProjectConstants.driver.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout["+i+"]").click()
	//				Mobile.callTestCase(findTestCase("Test Cases/ShopOpen/ChillerUtilization/ChillerNotInAccess/VisitChillerNotInAccess"), null)
	//				actions = ProjectConstants.driver.findElementsByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ListView[1]/*")
	//			}
	//			else if(actionname.equals("Shopkeeper did not allow")){
	//				ProjectConstants.driver.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout["+i+"]").click()
	//				Mobile.callTestCase(findTestCase("Test Cases/ShopOpen/ChillerUtilization/ShopKeeperDidNotAllow/VisitShopKeeperDidNotAllow"), null)
	//				actions = ProjectConstants.driver.findElementsByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ListView[1]/*")
	//			}
	//			else if(actionname.equals("Chiller Type not Available")){
	//				ProjectConstants.driver.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout["+i+"]").click()
	//				Mobile.callTestCase(findTestCase("Test Cases/ShopOpen/ChillerUtilization/ChillerTypeNotAvailable/VisitChillerTypeNotAvailable"), null)
	//			}
	//		}
	//	}
	//	@Keyword
	//	def overwriteChillerUtilization(){
	//		ArrayList<MobileElement> actions = ProjectConstants.driver.findElementsByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ListView[1]/*")
	//		for(int i=1; i<=actions.size(); i++){
	//			MobileElement action = ProjectConstants.driver.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout["+i+"]/android.widget.TextView[1]")
	//			String actionname = action.getText()
	//			if(actionname.equals("Chiller Type not Available")){
	//				ProjectConstants.driver.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout["+i+"]").click()
	//				Mobile.callTestCase(findTestCase("Test Cases/ShopOpen/ChillerUtilization/ChillerTypeNotAvailable/overwriteChillerTypeNotAvailable"), null)
	//			}
	//		}
	//	}
	//	@Keyword
	//	def visitChillerAvailableProductsCategories(){
	//		ArrayList<MobileElement> categories = ProjectConstants.driver.findElementsByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.ListView[1]/*")
	//		for(int i=1; i<=categories.size(); i++){
	//			MobileElement category = ProjectConstants.driver.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout["+i+"]/android.widget.TextView[1]")
	//			String categoryname = category.getText()
	//			if(categoryname.equals("Flavoured Milk")){
	//				ProjectConstants.driver.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout["+i+"]").click()
	//				Mobile.callTestCase(findTestCase("Test Cases/ShopOpen/ChillerUtilization/ChillerAvailableProducts/VisitFlavouredMilkProducts"), null)
	//			}
	//			else if(categoryname.equals("Juices - 1000ML")){
	//				ProjectConstants.driver.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout["+i+"]").click()
	//				Mobile.callTestCase(findTestCase("Test Cases/ShopOpen/ChillerUtilization/ChillerAvailableProducts/VisitJuices1000mlProducts"), null)
	//			}
	//			else if(categoryname.equals("Juices - 200ML")){
	//				ProjectConstants.driver.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout["+i+"]").click()
	//				Mobile.callTestCase(findTestCase("Test Cases/ShopOpen/ChillerUtilization/ChillerAvailableProducts/VisitJuices200mlProducts"), null)
	//			}
	//			else if(categoryname.equals("Water")){
	//				ProjectConstants.driver.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout["+i+"]").click()
	//				Mobile.callTestCase(findTestCase("Test Cases/ShopOpen/ChillerUtilization/ChillerAvailableProducts/VisitWaterProducts"), null)
	//			}
	//			else if(categoryname.equals("Yogurt")){
	//				ProjectConstants.driver.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout["+i+"]").click()
	//				Mobile.callTestCase(findTestCase("Test Cases/ShopOpen/ChillerUtilization/ChillerAvailableProducts/VisitYogurtProducts"), null)
	//			}
	//			categories = ProjectConstants.driver.findElementsByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.ListView[1]/*")
	//		}
	//	}
	//
	//	@Keyword
	//	def overwriteChillerAvailableProductsCategories(){
	//		ArrayList<MobileElement> categories = ProjectConstants.driver.findElementsByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.ListView[1]/*")
	//		for(int i=1; i<=categories.size(); i++){
	//			MobileElement category = ProjectConstants.driver.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout["+i+"]/android.widget.TextView[1]")
	//			String categoryname = category.getText()
	//			if(categoryname.equals("Flavoured Milk")){
	//				ProjectConstants.driver.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout["+i+"]").click()
	//				Mobile.callTestCase(findTestCase("Test Cases/ShopOpen/ChillerUtilization/ChillerAvailableProducts/OverwriteFlavouredMilkProducts"), null)
	//			}
	//			else if(categoryname.equals("Juices - 1000ML")){
	//				ProjectConstants.driver.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout["+i+"]").click()
	//				Mobile.callTestCase(findTestCase("Test Cases/ShopOpen/ChillerUtilization/ChillerAvailableProducts/OverwriteJuices1000mlProducts"), null)
	//			}
	//			else if(categoryname.equals("Juices - 200ML")){
	//				ProjectConstants.driver.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout["+i+"]").click()
	//				Mobile.callTestCase(findTestCase("Test Cases/ShopOpen/ChillerUtilization/ChillerAvailableProducts/OverwriteJuices200mlProducts"), null)
	//			}
	//			else if(categoryname.equals("Water")){
	//				ProjectConstants.driver.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout["+i+"]").click()
	//				Mobile.callTestCase(findTestCase("Test Cases/ShopOpen/ChillerUtilization/ChillerAvailableProducts/OverwriteWaterProducts"), null)
	//			}
	//			else if(categoryname.equals("Yogurt")){
	//				ProjectConstants.driver.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout["+i+"]").click()
	//				Mobile.callTestCase(findTestCase("Test Cases/ShopOpen/ChillerUtilization/ChillerAvailableProducts/OverwriteYogurtProducts"), null)
	//			}
	//			categories = ProjectConstants.driver.findElementsByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.ListView[1]/*")
	//		}
	//	}
	//	@Keyword
	//	def overwriteChillerAvailableFlavouredMilkProducts(){
	//		int index = 0
	//		ArrayList<MobileElement> products = ProjectConstants.driver.findElementsByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ScrollView[1]/android.widget.LinearLayout[1]/*")
	//		for(int i=2; i<=products.size(); i=i+3){
	//			index = index + 1
	//			MobileElement product = ProjectConstants.driver.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ScrollView[1]/android.widget.LinearLayout[1]/android.widget.TextView["+index+"]")
	//			String productname = product.getText()
	//			MobileElement textfield = ProjectConstants.driver.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ScrollView[1]/android.widget.LinearLayout[1]/android.widget.LinearLayout["+index+"]/android.widget.EditText[1]")
	//			if(productname.equals("MILO RTD 200ML")){
	//				textfield.setValue(dataforoverwritechilleravailableproducts.getObjectValue("Milo RTD 200ml", 1))
	//				Mobile.hideKeyboard()
	//			}
	//		}
	//	}
	//	@Keyword
	//	def overwriteChillerAvailableJuices1000mlProducts(){
	//		int index = 0
	//		ArrayList<MobileElement> products = ProjectConstants.driver.findElementsByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ScrollView[1]/android.widget.LinearLayout[1]/*")
	//		for(int i=2; i<=products.size(); i=i+3){
	//			index = index + 1
	//			MobileElement product = ProjectConstants.driver.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ScrollView[1]/android.widget.LinearLayout[1]/android.widget.TextView["+index+"]")
	//			String productname = product.getText()
	//			MobileElement textfield = ProjectConstants.driver.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ScrollView[1]/android.widget.LinearLayout[1]/android.widget.LinearLayout["+index+"]/android.widget.EditText[1]")
	//			if(productname.equals("FRUITA VITALS APPLE 1000ML")){
	//				textfield.setValue(dataforoverwritechilleravailableproducts.getObjectValue("Fruita Vitals Apple 1000ml", 1))
	//				Mobile.hideKeyboard()
	//			}
	//			else if(productname.equals("FRUITA VITALS CHAUNSA 1000ML")){
	//				textfield.setValue(dataforoverwritechilleravailableproducts.getObjectValue("Fruita Vitals Chaunsa 1000ml", 1))
	//				Mobile.hideKeyboard()
	//			}
	//			else if(productname.equals("FRUITA VITALS RED GRAPES 1000ML")){
	//				textfield.setValue(dataforoverwritechilleravailableproducts.getObjectValue("Fruita Vitals Red Grapes 1000ml", 1))
	//				Mobile.hideKeyboard()
	//			}
	//		}
	//	}
	//	@Keyword
	//	def overwriteChillerAvailableJuices200mlProducts(){
	//		int index = 0
	//		ArrayList<MobileElement> products = ProjectConstants.driver.findElementsByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ScrollView[1]/android.widget.LinearLayout[1]/*")
	//		for(int i=2; i<=products.size(); i=i+3){
	//			index = index + 1
	//			MobileElement product = ProjectConstants.driver.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ScrollView[1]/android.widget.LinearLayout[1]/android.widget.TextView["+index+"]")
	//			String productname = product.getText()
	//			MobileElement textfield = ProjectConstants.driver.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ScrollView[1]/android.widget.LinearLayout[1]/android.widget.LinearLayout["+index+"]/android.widget.EditText[1]")
	//			if(productname.equals("NESFRUTA MANGO 200ML")){
	//				textfield.setValue(dataforoverwritechilleravailableproducts.getObjectValue("Nesfruta Mango 200ml", 1))
	//				Mobile.hideKeyboard()
	//			}
	//			else if(productname.equals("FRUITA VITALS APPLE 200ML")){
	//				textfield.setValue(dataforoverwritechilleravailableproducts.getObjectValue("Fruita Vitals Apple 200ml", 1))
	//				Mobile.hideKeyboard()
	//			}
	//			else if(productname.equals("FRUITA VITALS PINEAPPLE 200ML")){
	//				textfield.setValue(dataforoverwritechilleravailableproducts.getObjectValue("Fruita Vitals PineApple 200ml", 1))
	//				Mobile.hideKeyboard()
	//			}
	//			else if(productname.equals("FRUITA VITALS CHAUNSA 200ML")){
	//				textfield.setValue(dataforoverwritechilleravailableproducts.getObjectValue("Fruita Vitals Chaunsa 200ml", 1))
	//				Mobile.hideKeyboard()
	//			}
	//			else if(productname.equals("NESFRUTA APPLE 200ML")){
	//				textfield.setValue(dataforoverwritechilleravailableproducts.getObjectValue("Nesfruta Apple 200ml", 1))
	//				Mobile.hideKeyboard()
	//			}
	//			else if(productname.equals("FRUITA VITALS KINOW 200ML")){
	//				textfield.setValue(dataforoverwritechilleravailableproducts.getObjectValue("Fruita Vitals Kinow 200ml", 1))
	//				Mobile.hideKeyboard()
	//			}
	//			else if(productname.equals("FRUITA VITALS GUAVA 200ML")){
	//				textfield.setValue(dataforoverwritechilleravailableproducts.getObjectValue("Fruita Vitals Guava 200ml", 1))
	//				Mobile.hideKeyboard()
	//			}
	//			else if(productname.equals("FRUITA VITALS RED GRAPES 200ML")){
	//				textfield.setValue(dataforoverwritechilleravailableproducts.getObjectValue("Fruita Vitals Red Grapes 200ml", 1))
	//				Mobile.hideKeyboard()
	//			}
	//			else if(productname.equals("FRUITA VITALS PEACH 200ML")){
	//				textfield.setValue(dataforoverwritechilleravailableproducts.getObjectValue("Fruita Vitals Peach 200ml", 1))
	//				Mobile.hideKeyboard()
	//			}
	//			products = ProjectConstants.driver.findElementsByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ScrollView[1]/android.widget.LinearLayout[1]/*")
	//		}
	//		while(true){
	//			MobileElement lastitembeforeswipe = ProjectConstants.driver.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ScrollView[1]/android.widget.LinearLayout[1]/android.widget.TextView[5]")
	//			String lastitemnamebeforeswipe = lastitembeforeswipe.getText()
	//			Mobile.swipe(0, 259, 0, 150)
	//			MobileElement lastitemafterswipe = ProjectConstants.driver.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ScrollView[1]/android.widget.LinearLayout[1]/android.widget.TextView[5]")
	//			String lastitemnameafterswipe = lastitemafterswipe.getText()
	//			MobileElement textfield = ProjectConstants.driver.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ScrollView[1]/android.widget.LinearLayout[1]/android.widget.LinearLayout[6]/android.widget.EditText[1]")
	//			if(lastitemnamebeforeswipe.equals(lastitemnameafterswipe)){
	//				break
	//			}
	//			else if(lastitemnameafterswipe.equals("NESFRUTA MANGO 200ML")){
	//				textfield.setValue(dataforoverwritechilleravailableproducts.getObjectValue("Nesfruta Mango 200ml", 1))
	//				Mobile.hideKeyboard()
	//			}
	//			else if(lastitemnameafterswipe.equals("FRUITA VITALS APPLE 200ML")){
	//				textfield.setValue(dataforoverwritechilleravailableproducts.getObjectValue("Fruita Vitals Apple 200ml", 1))
	//				Mobile.hideKeyboard()
	//			}
	//			else if(lastitemnameafterswipe.equals("FRUITA VITALS PINEAPPLE 200ML")){
	//				textfield.setValue(dataforoverwritechilleravailableproducts.getObjectValue("Fruita Vitals PineApple 200ml", 1))
	//				Mobile.hideKeyboard()
	//			}
	//			else if(lastitemnameafterswipe.equals("FRUITA VITALS CHAUNSA 200ML")){
	//				textfield.setValue(dataforoverwritechilleravailableproducts.getObjectValue("Fruita Vitals Chaunsa 200ml", 1))
	//				Mobile.hideKeyboard()
	//			}
	//			else if(lastitemnameafterswipe.equals("NESFRUTA APPLE 200ML")){
	//				textfield.setValue(dataforoverwritechilleravailableproducts.getObjectValue("Nesfruta Apple 200ml", 1))
	//				Mobile.hideKeyboard()
	//			}
	//			else if(lastitemnameafterswipe.equals("FRUITA VITALS KINOW 200ML")){
	//				textfield.setValue(dataforoverwritechilleravailableproducts.getObjectValue("Fruita Vitals Kinow 200ml", 1))
	//				Mobile.hideKeyboard()
	//			}
	//			else if(lastitemnameafterswipe.equals("FRUITA VITALS GUAVA 200ML")){
	//				textfield.setValue(dataforoverwritechilleravailableproducts.getObjectValue("Fruita Vitals Guava 200ml", 1))
	//				Mobile.hideKeyboard()
	//			}
	//			else if(lastitemnameafterswipe.equals("FRUITA VITALS RED GRAPES 200ML")){
	//				textfield.setValue(dataforoverwritechilleravailableproducts.getObjectValue("Fruita Vitals Red Grapes 200ml", 1))
	//				Mobile.hideKeyboard()
	//			}
	//			else if(lastitemnameafterswipe.equals("FRUITA VITALS PEACH 200ML")){
	//				textfield.setValue(dataforoverwritechilleravailableproducts.getObjectValue("Fruita Vitals Peach 200ml", 1))
	//				Mobile.hideKeyboard()
	//			}
	//		}
	//	}
	//	@Keyword
	//	def overwriteChillerAvailableWaterProducts(){
	//		int index = 0
	//		ArrayList<MobileElement> products = ProjectConstants.driver.findElementsByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ScrollView[1]/android.widget.LinearLayout[1]/*")
	//		for(int i=2; i<=products.size(); i=i+3){
	//			index = index + 1
	//			MobileElement product = ProjectConstants.driver.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ScrollView[1]/android.widget.LinearLayout[1]/android.widget.TextView["+index+"]")
	//			String productname = product.getText()
	//			MobileElement textfield = ProjectConstants.driver.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ScrollView[1]/android.widget.LinearLayout[1]/android.widget.LinearLayout["+index+"]/android.widget.EditText[1]")
	//			if(productname.equals("Water Npl 500ML")){
	//				textfield.setValue(dataforoverwritechilleravailableproducts.getObjectValue("Water Npl 500ml", 1))
	//				Mobile.hideKeyboard()
	//			}
	//			else if(productname.equals("Water Npl 1500ML")){
	//				textfield.setValue(dataforoverwritechilleravailableproducts.getObjectValue("Water Npl 1500ml", 1))
	//				Mobile.hideKeyboard()
	//			}
	//		}
	//	}
	//	@Keyword
	//	def overwriteChillerAvailableYogurtProducts(){
	//		int index = 0
	//		ArrayList<MobileElement> products = ProjectConstants.driver.findElementsByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ScrollView[1]/android.widget.LinearLayout[1]/*")
	//		for(int i=2; i<=products.size(); i=i+3){
	//			index = index + 1
	//			MobileElement product = ProjectConstants.driver.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ScrollView[1]/android.widget.LinearLayout[1]/android.widget.TextView["+index+"]")
	//			String productname = product.getText()
	//			MobileElement textfield = ProjectConstants.driver.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ScrollView[1]/android.widget.LinearLayout[1]/android.widget.LinearLayout["+index+"]/android.widget.EditText[1]")
	//			if(productname.equals("MILKPAK YOGURT POUCH 500GM")){
	//				textfield.setValue(dataforoverwritechilleravailableproducts.getObjectValue("Milkpak Yogurt Pouch 500gm", 1))
	//				Mobile.hideKeyboard()
	//			}
	//			else if(productname.equals("Sweet & Tasty 400GM")){
	//				textfield.setValue(dataforoverwritechilleravailableproducts.getObjectValue("Sweet & Tasty 400gm", 1))
	//				Mobile.hideKeyboard()
	//			}
	//			else if(productname.equals("Nestle Nesvita YOGURT 400GM")){
	//				textfield.setValue(dataforoverwritechilleravailableproducts.getObjectValue("Nestle Nesvita Yogurt 400gm", 1))
	//				Mobile.hideKeyboard()
	//			}
	//			else if(productname.equals("Rawaity Maza 400GM")){
	//				textfield.setValue(dataforoverwritechilleravailableproducts.getObjectValue("Rawaity Maza 400gm", 1))
	//				Mobile.hideKeyboard()
	//			}
	//			else if(productname.equals("NESTLE ZEERA RAITA 250GM")){
	//				textfield.setValue(dataforoverwritechilleravailableproducts.getObjectValue("Nestle Zeera Raita 250gm", 1))
	//				Mobile.hideKeyboard()
	//			}
	//			else if(productname.equals("Mint Raita 250GM")){
	//				textfield.setValue(dataforoverwritechilleravailableproducts.getObjectValue("Main Raita 250gm", 1))
	//				Mobile.hideKeyboard()
	//			}
	//		}
	//	}
	//	@Keyword
	//	def visitChillerAvailableFlavouredMilkProducts(){
	//		int index = 0
	//		ArrayList<MobileElement> products = ProjectConstants.driver.findElementsByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ScrollView[1]/android.widget.LinearLayout[1]/*")
	//		for(int i=2; i<=products.size(); i=i+3){
	//			index = index + 1
	//			MobileElement product = ProjectConstants.driver.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ScrollView[1]/android.widget.LinearLayout[1]/android.widget.TextView["+index+"]")
	//			String productname = product.getText()
	//			MobileElement textfield = ProjectConstants.driver.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ScrollView[1]/android.widget.LinearLayout[1]/android.widget.LinearLayout["+index+"]/android.widget.EditText[1]")
	//			if(productname.equals("MILO RTD 200ML")){
	//				textfield.setValue(dataforvisitchilleravailableproducts.getObjectValue("Milo RTD 200ml", 1))
	//				Mobile.hideKeyboard()
	//			}
	//		}
	//	}
	//	@Keyword
	//	def visitChillerAvailableJuices1000mlProducts(){
	//		int index = 0
	//		ArrayList<MobileElement> products = ProjectConstants.driver.findElementsByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ScrollView[1]/android.widget.LinearLayout[1]/*")
	//		for(int i=2; i<=products.size(); i=i+3){
	//			index = index + 1
	//			MobileElement product = ProjectConstants.driver.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ScrollView[1]/android.widget.LinearLayout[1]/android.widget.TextView["+index+"]")
	//			String productname = product.getText()
	//			MobileElement textfield = ProjectConstants.driver.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ScrollView[1]/android.widget.LinearLayout[1]/android.widget.LinearLayout["+index+"]/android.widget.EditText[1]")
	//			if(productname.equals("FRUITA VITALS APPLE 1000ML")){
	//				textfield.setValue(dataforvisitchilleravailableproducts.getObjectValue("Fruita Vitals Apple 1000ml", 1))
	//				Mobile.hideKeyboard()
	//			}
	//			else if(productname.equals("FRUITA VITALS CHAUNSA 1000ML")){
	//				textfield.setValue(dataforvisitchilleravailableproducts.getObjectValue("Fruita Vitals Chaunsa 1000ml", 1))
	//				Mobile.hideKeyboard()
	//			}
	//			else if(productname.equals("FRUITA VITALS RED GRAPES 1000ML")){
	//				textfield.setValue(dataforvisitchilleravailableproducts.getObjectValue("Fruita Vitals Red Grapes 1000ml", 1))
	//				Mobile.hideKeyboard()
	//			}
	//		}
	//	}
	//	@Keyword
	//	def visitChillerAvailableJuices200mlProducts(){
	//		int index = 0
	//		ArrayList<MobileElement> products = ProjectConstants.driver.findElementsByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ScrollView[1]/android.widget.LinearLayout[1]/*")
	//		for(int i=2; i<=products.size(); i=i+3){
	//			index = index + 1
	//			MobileElement product = ProjectConstants.driver.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ScrollView[1]/android.widget.LinearLayout[1]/android.widget.TextView["+index+"]")
	//			String productname = product.getText()
	//			MobileElement textfield = ProjectConstants.driver.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ScrollView[1]/android.widget.LinearLayout[1]/android.widget.LinearLayout["+index+"]/android.widget.EditText[1]")
	//			if(productname.equals("NESFRUTA MANGO 200ML")){
	//				textfield.setValue(dataforvisitchilleravailableproducts.getObjectValue("Nesfruta Mango 200ml", 1))
	//				Mobile.hideKeyboard()
	//			}
	//			else if(productname.equals("FRUITA VITALS APPLE 200ML")){
	//				textfield.setValue(dataforvisitchilleravailableproducts.getObjectValue("Fruita Vitals Apple 200ml", 1))
	//				Mobile.hideKeyboard()
	//			}
	//			else if(productname.equals("FRUITA VITALS PINEAPPLE 200ML")){
	//				textfield.setValue(dataforvisitchilleravailableproducts.getObjectValue("Fruita Vitals PineApple 200ml", 1))
	//				Mobile.hideKeyboard()
	//			}
	//			else if(productname.equals("FRUITA VITALS CHAUNSA 200ML")){
	//				textfield.setValue(dataforvisitchilleravailableproducts.getObjectValue("Fruita Vitals Chaunsa 200ml", 1))
	//				Mobile.hideKeyboard()
	//			}
	//			else if(productname.equals("NESFRUTA APPLE 200ML")){
	//				textfield.setValue(dataforvisitchilleravailableproducts.getObjectValue("Nesfruta Apple 200ml", 1))
	//				Mobile.hideKeyboard()
	//			}
	//			else if(productname.equals("FRUITA VITALS KINOW 200ML")){
	//				textfield.setValue(dataforvisitchilleravailableproducts.getObjectValue("Fruita Vitals Kinow 200ml", 1))
	//				Mobile.hideKeyboard()
	//			}
	//			else if(productname.equals("FRUITA VITALS GUAVA 200ML")){
	//				textfield.setValue(dataforvisitchilleravailableproducts.getObjectValue("Fruita Vitals Guava 200ml", 1))
	//				Mobile.hideKeyboard()
	//			}
	//			else if(productname.equals("FRUITA VITALS RED GRAPES 200ML")){
	//				textfield.setValue(dataforvisitchilleravailableproducts.getObjectValue("Fruita Vitals Red Grapes 200ml", 1))
	//				Mobile.hideKeyboard()
	//			}
	//			else if(productname.equals("FRUITA VITALS PEACH 200ML")){
	//				textfield.setValue(dataforvisitchilleravailableproducts.getObjectValue("Fruita Vitals Peach 200ml", 1))
	//				Mobile.hideKeyboard()
	//			}
	//			products = ProjectConstants.driver.findElementsByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ScrollView[1]/android.widget.LinearLayout[1]/*")
	//		}
	//		while(true){
	//			MobileElement lastitembeforeswipe = ProjectConstants.driver.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ScrollView[1]/android.widget.LinearLayout[1]/android.widget.TextView[5]")
	//			String lastitemnamebeforeswipe = lastitembeforeswipe.getText()
	//			Mobile.swipe(0, 259, 0, 150)
	//			MobileElement lastitemafterswipe = ProjectConstants.driver.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ScrollView[1]/android.widget.LinearLayout[1]/android.widget.TextView[5]")
	//			String lastitemnameafterswipe = lastitemafterswipe.getText()
	//			MobileElement textfield = ProjectConstants.driver.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ScrollView[1]/android.widget.LinearLayout[1]/android.widget.LinearLayout[6]/android.widget.EditText[1]")
	//			if(lastitemnamebeforeswipe.equals(lastitemnameafterswipe)){
	//				break
	//			}
	//			else if(lastitemnameafterswipe.equals("NESFRUTA MANGO 200ML")){
	//				textfield.setValue(dataforvisitchilleravailableproducts.getObjectValue("Nesfruta Mango 200ml", 1))
	//				Mobile.hideKeyboard()
	//			}
	//			else if(lastitemnameafterswipe.equals("FRUITA VITALS APPLE 200ML")){
	//				textfield.setValue(dataforvisitchilleravailableproducts.getObjectValue("Fruita Vitals Apple 200ml", 1))
	//				Mobile.hideKeyboard()
	//			}
	//			else if(lastitemnameafterswipe.equals("FRUITA VITALS PINEAPPLE 200ML")){
	//				textfield.setValue(dataforvisitchilleravailableproducts.getObjectValue("Fruita Vitals PineApple 200ml", 1))
	//				Mobile.hideKeyboard()
	//			}
	//			else if(lastitemnameafterswipe.equals("FRUITA VITALS CHAUNSA 200ML")){
	//				textfield.setValue(dataforvisitchilleravailableproducts.getObjectValue("Fruita Vitals Chaunsa 200ml", 1))
	//				Mobile.hideKeyboard()
	//			}
	//			else if(lastitemnameafterswipe.equals("NESFRUTA APPLE 200ML")){
	//				textfield.setValue(dataforvisitchilleravailableproducts.getObjectValue("Nesfruta Apple 200ml", 1))
	//				Mobile.hideKeyboard()
	//			}
	//			else if(lastitemnameafterswipe.equals("FRUITA VITALS KINOW 200ML")){
	//				textfield.setValue(dataforvisitchilleravailableproducts.getObjectValue("Fruita Vitals Kinow 200ml", 1))
	//				Mobile.hideKeyboard()
	//			}
	//			else if(lastitemnameafterswipe.equals("FRUITA VITALS GUAVA 200ML")){
	//				textfield.setValue(dataforvisitchilleravailableproducts.getObjectValue("Fruita Vitals Guava 200ml", 1))
	//				Mobile.hideKeyboard()
	//			}
	//			else if(lastitemnameafterswipe.equals("FRUITA VITALS RED GRAPES 200ML")){
	//				textfield.setValue(dataforvisitchilleravailableproducts.getObjectValue("Fruita Vitals Red Grapes 200ml", 1))
	//				Mobile.hideKeyboard()
	//			}
	//			else if(lastitemnameafterswipe.equals("FRUITA VITALS PEACH 200ML")){
	//				textfield.setValue(dataforvisitchilleravailableproducts.getObjectValue("Fruita Vitals Peach 200ml", 1))
	//				Mobile.hideKeyboard()
	//			}
	//		}
	//	}
	//	@Keyword
	//	def visitChillerAvailableWaterProducts(){
	//		int index = 0
	//		ArrayList<MobileElement> products = ProjectConstants.driver.findElementsByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ScrollView[1]/android.widget.LinearLayout[1]/*")
	//		for(int i=2; i<=products.size(); i=i+3){
	//			index = index + 1
	//			MobileElement product = ProjectConstants.driver.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ScrollView[1]/android.widget.LinearLayout[1]/android.widget.TextView["+index+"]")
	//			String productname = product.getText()
	//			MobileElement textfield = ProjectConstants.driver.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ScrollView[1]/android.widget.LinearLayout[1]/android.widget.LinearLayout["+index+"]/android.widget.EditText[1]")
	//			if(productname.equals("Water Npl 500ML")){
	//				textfield.setValue(dataforvisitchilleravailableproducts.getObjectValue("Water Npl 500ml", 1))
	//				Mobile.hideKeyboard()
	//			}
	//			else if(productname.equals("Water Npl 1500ML")){
	//				textfield.setValue(dataforvisitchilleravailableproducts.getObjectValue("Water Npl 1500ml", 1))
	//				Mobile.hideKeyboard()
	//			}
	//		}
	//	}
	//	@Keyword
	//	def visitChillerAvailableYogurtProducts(){
	//		int index = 0
	//		ArrayList<MobileElement> products = ProjectConstants.driver.findElementsByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ScrollView[1]/android.widget.LinearLayout[1]/*")
	//		for(int i=2; i<=products.size(); i=i+3){
	//			index = index + 1
	//			MobileElement product = ProjectConstants.driver.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ScrollView[1]/android.widget.LinearLayout[1]/android.widget.TextView["+index+"]")
	//			String productname = product.getText()
	//			MobileElement textfield = ProjectConstants.driver.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ScrollView[1]/android.widget.LinearLayout[1]/android.widget.LinearLayout["+index+"]/android.widget.EditText[1]")
	//			if(productname.equals("MILKPAK YOGURT POUCH 500GM")){
	//				textfield.setValue(dataforvisitchilleravailableproducts.getObjectValue("Milkpak Yogurt Pouch 500gm", 1))
	//				Mobile.hideKeyboard()
	//			}
	//			else if(productname.equals("Sweet & Tasty 400GM")){
	//				textfield.setValue(dataforvisitchilleravailableproducts.getObjectValue("Sweet & Tasty 400gm", 1))
	//				Mobile.hideKeyboard()
	//			}
	//			else if(productname.equals("Nestle Nesvita YOGURT 400GM")){
	//				textfield.setValue(dataforvisitchilleravailableproducts.getObjectValue("Nestle Nesvita Yogurt 400gm", 1))
	//				Mobile.hideKeyboard()
	//			}
	//			else if(productname.equals("Rawaity Maza 400GM")){
	//				textfield.setValue(dataforvisitchilleravailableproducts.getObjectValue("Rawaity Maza 400gm", 1))
	//				Mobile.hideKeyboard()
	//			}
	//			else if(productname.equals("NESTLE ZEERA RAITA 250GM")){
	//				textfield.setValue(dataforvisitchilleravailableproducts.getObjectValue("Nestle Zeera Raita 250gm", 1))
	//				Mobile.hideKeyboard()
	//			}
	//			else if(productname.equals("Mint Raita 250GM")){
	//				textfield.setValue(dataforvisitchilleravailableproducts.getObjectValue("Main Raita 250gm", 1))
	//				Mobile.hideKeyboard()
	//			}
	//		}
	//	}
	//	@Keyword
	//	def visitChillerNotAvailableProductsCategories(){
	//		ArrayList<MobileElement> categories = ProjectConstants.driver.findElementsByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.ListView[1]/*")
	//		for(int i=1; i<=categories.size(); i++){
	//			MobileElement category = ProjectConstants.driver.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout["+i+"]/android.widget.TextView[1]")
	//			String categoryname = category.getText()
	//			if(categoryname.equals("Juices - 200ML")){
	//				ProjectConstants.driver.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout["+i+"]").click()
	//				Mobile.callTestCase(findTestCase("Test Cases/ShopOpen/ChillerUtilization/ChillerNotAvailableProducts/VisitJuices-200ML"), null)
	//			}
	//			categories = ProjectConstants.driver.findElementsByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.ListView[1]/*")
	//		}
	//	}
	//	@Keyword
	//	def visitChillerNotAvailableJuices200mlProducts(){
	//		int index = 0
	//		ArrayList<MobileElement> products = ProjectConstants.driver.findElementsByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ScrollView[1]/android.widget.LinearLayout[1]/*")
	//		for(int i=2; i<=products.size(); i=i+3){
	//			index = index + 1
	//			MobileElement product = ProjectConstants.driver.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ScrollView[1]/android.widget.LinearLayout[1]/android.widget.TextView["+index+"]")
	//			String productname = product.getText()
	//			MobileElement textfield = ProjectConstants.driver.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ScrollView[1]/android.widget.LinearLayout[1]/android.widget.LinearLayout["+index+"]/android.widget.EditText[1]")
	//			if(productname.equals("NESFRUTA MANGO 200ML")){
	//				textfield.setValue(dataforvisitchillernotavailableproducts.getObjectValue("Nesfruta Mango 200ml", 1))
	//				Mobile.hideKeyboard()
	//			}
	//			else if(productname.equals("FRUITA VITALS CHAUNSA 200ML")){
	//				textfield.setValue(dataforvisitchillernotavailableproducts.getObjectValue("Fruita Vitals Chaunsa 200ml", 1))
	//				Mobile.hideKeyboard()
	//			}
	//			else if(productname.equals("FRUITA VITALS RED GRAPES 200ML")){
	//				textfield.setValue(dataforvisitchillernotavailableproducts.getObjectValue("Fruita Vitals Red Grapes 200ml", 1))
	//				Mobile.hideKeyboard()
	//			}
	//			else if(productname.equals("FRUITA VITALS APPLE 200ML")){
	//				textfield.setValue(dataforvisitchillernotavailableproducts.getObjectValue("Fruita Vitals Apple 200ml", 1))
	//				Mobile.hideKeyboard()
	//			}
	//		}
	//	}
	//	@Keyword
	//	def continueChillerAvailableModuleFlowForChillerNeedMaintenance(){
	//		Mobile.callTestCase(findTestCase("Test Cases/ShopOpen/ChillerUtilization/ChillerAvailable/VisitChillerAvailable"), null)
	//	}
	//	@Keyword
	//	def continueChillerNotAvailableModuleFlowForChillerRemovedForMaintenance(){
	//		Mobile.callTestCase(findTestCase("Test Cases/ShopOpen/ChillerUtilization/ChillerNotAvailable/VisitChillerNotAvailable"), null)
	//	}
	//	@Keyword
	//	def continueChillerNotAvailableModuleFlowForChillerNotInAccess(){
	//		Mobile.callTestCase(findTestCase("Test Cases/ShopOpen/ChillerUtilization/ChillerNotAvailable/VisitChillerNotAvailable"), null)
	//	}
	//	@Keyword
	//	def comapareImages(){
	//		int index = 0
	//		final int[] image1Data = null
	//		File screenshot = ((TakesScreenshot)ProjectConstants.driver).getScreenshotAs(OutputType.FILE)
	//		BufferedImage fullImg = ImageIO.read(screenshot)
	//
	//		MobileElement expectedimageview = ProjectConstants.driver.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.GridView[1]/android.widget.RelativeLayout[1]/android.widget.ImageView[1]")
	//
	//		int imagewidth = expectedimageview.getSize().getWidth()
	//		int imageheight = expectedimageview.getSize().getHeight()
	//
	//		Point expectedimagepointer = expectedimageview.getLocation()
	//
	//		Image expectedimage = fullImg.getSubimage(expectedimagepointer.getX(), expectedimagepointer.getY(),imagewidth, imageheight)
	//
	//		PixelGrabber grabImage1Pixels = new PixelGrabber(expectedimage, 0, 0, -1,-1, false)
	//
	//		if (grabImage1Pixels.grabPixels()) {
	//			int width = grabImage1Pixels.getWidth()
	//			int height = grabImage1Pixels.getHeight()
	//			image1Data = new int[width * height]
	//			image1Data = (int[]) grabImage1Pixels.getPixels()
	//		}
	//
	//		try {
	//			ArrayList<MobileElement> images = ProjectConstants.driver.findElementsByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.GridView[1]/*")
	//			for(int i=1; i<=(images.size()-2); i++){
	//				MobileElement actualimageview = ProjectConstants.driver.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.GridView[1]/android.widget.RelativeLayout["+i+"]/android.widget.ImageView[1]")
	//
	//				Point actualimagepointer = actualimageview.getLocation()
	//
	//				Image actualimage = fullImg.getSubimage(actualimagepointer.getX(), actualimagepointer.getY(),imagewidth, imageheight)
	//
	//				final PixelGrabber grabImage2Pixels = new PixelGrabber(actualimage, 0, 0, -1,-1, false)
	//				int[] image2Data = null
	//				if (grabImage2Pixels.grabPixels()) {
	//					int width = grabImage2Pixels.getWidth()
	//					int height = grabImage2Pixels.getHeight()
	//					image2Data = new int[width * height]
	//					image2Data = (int[]) grabImage2Pixels.getPixels()
	//				}
	//
	//				if(!java.util.Arrays.equals(image1Data, image2Data)){
	//					ProjectConstants.driver.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.GridView[1]/android.widget.RelativeLayout["+i+"]").click()
	//					Mobile.verifyElementExist(findTestObject("Object Repository/CommonScreenElements/Validate_CameraScreen"), 0)
	//					Mobile.tap(findTestObject("Object Repository/CommonScreenElements/TakePictureButton"), 0)
	//					Mobile.waitForElementPresent(findTestObject("Object Repository/CommonScreenElements/DoneButton"), 0)
	//					Mobile.tap(findTestObject("Object Repository/CommonScreenElements/DoneButton"), 0)
	//					break
	//				}
	//			}
	//			images = ProjectConstants.driver.findElementsByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.GridView[1]/*")
	//		}
	//		catch (InterruptedException e1) {
	//			e1.printStackTrace()
	//		}
	//		//		Mobile.swipe(0, 370, 0,200)
	//		//		while(true){
	//		//			index = ProjectConstants.driver.findElementsByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.GridView[1]/*").size()
	//		//			MobileElement productbeforeswipe = ProjectConstants.driver.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.GridView[1]/android.widget.RelativeLayout["+index+"]/android.widget.TextView[1]")
	//		//			String productnamebeforeswipe = productbeforeswipe.getText()
	//		//			Mobile.swipe(0, 540, 0, 200)
	//		//			MobileElement productafterswipe = ProjectConstants.driver.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.GridView[1]/android.widget.RelativeLayout["+index+"]/android.widget.TextView[1]")
	//		//			String productnameafterswipe = productafterswipe.getText()
	//		//			if(productnamebeforeswipe.equals(productnameafterswipe)){
	//		//				Mobile.swipe(0, 500, 0, 200)
	//		//				if(index == 6){
	//		//					index = index - 2
	//		//				}
	//		//				else{
	//		//					index = index - 1
	//		//				}
	//		//				for(int j=3; j<=index; j++){
	//		//					MobileElement actualimageview = ProjectConstants.driver.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.GridView[1]/android.widget.RelativeLayout["+j+"]/android.widget.ImageView[1]")
	//		//
	//		//					Point actualimagepointer = actualimageview.getLocation()
	//		//
	//		//					Image actualimage = fullImg.getSubimage(actualimagepointer.getX(), actualimagepointer.getY(),imagewidth, imageheight)
	//		//
	//		//					PixelGrabber grabImage2Pixels = new PixelGrabber(actualimage, 0, 0, -1,-1, false)
	//		//					int[] image2Data = null
	//		//					if (grabImage2Pixels.grabPixels()) {
	//		//						int width = grabImage2Pixels.getWidth()
	//		//						int height = grabImage2Pixels.getHeight()
	//		//						image2Data = new int[width * height]
	//		//						image2Data = (int[]) grabImage2Pixels.getPixels()
	//		//					}
	//		//
	//		//					if(!java.util.Arrays.equals(image1Data, image2Data)){
	//		//						ProjectConstants.driver.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.GridView[1]/android.widget.RelativeLayout["+j+"]").click()
	//		//						Mobile.verifyElementExist(findTestObject("Object Repository/CommonScreenElements/Validate_CameraScreen"), 0)
	//		//						Mobile.tap(findTestObject("Object Repository/CommonScreenElements/TakePictureButton"), 0)
	//		//						Mobile.waitForElementPresent(findTestObject("Object Repository/CommonScreenElements/DoneButton"), 0)
	//		//						Mobile.tap(findTestObject("Object Repository/CommonScreenElements/DoneButton"), 0)
	//		//					}
	//		//				}
	//		//				break
	//		//			}
	//		//			else{
	//		//				if(index == 6){
	//		//					index = index - 2
	//		//				}
	//		//				else{
	//		//					index = index - 1
	//		//				}
	//		//				for(int j=3; j<=index; j++){
	//		//					MobileElement actualimageview = ProjectConstants.driver.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.GridView[1]/android.widget.RelativeLayout["+j+"]/android.widget.ImageView[1]")
	//		//
	//		//					Point actualimagepointer = actualimageview.getLocation()
	//		//
	//		//					Image actualimage = fullImg.getSubimage(actualimagepointer.getX(), actualimagepointer.getY(),imagewidth, imageheight)
	//		//
	//		//					PixelGrabber grabImage2Pixels = new PixelGrabber(actualimage, 0, 0, -1,-1, false)
	//		//					int[] image2Data = null
	//		//					if (grabImage2Pixels.grabPixels()) {
	//		//						int width = grabImage2Pixels.getWidth()
	//		//						int height = grabImage2Pixels.getHeight()
	//		//						image2Data = new int[width * height]
	//		//						image2Data = (int[]) grabImage2Pixels.getPixels()
	//		//					}
	//		//
	//		//					if(!java.util.Arrays.equals(image1Data, image2Data)){
	//		//						ProjectConstants.driver.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.GridView[1]/android.widget.RelativeLayout["+j+"]").click()
	//		//						Mobile.verifyElementExist(findTestObject("Object Repository/CommonScreenElements/Validate_CameraScreen"), 0)
	//		//						Mobile.tap(findTestObject("Object Repository/CommonScreenElements/TakePictureButton"), 0)
	//		//						Mobile.waitForElementPresent(findTestObject("Object Repository/CommonScreenElements/DoneButton"), 0)
	//		//						Mobile.tap(findTestObject("Object Repository/CommonScreenElements/DoneButton"), 0)
	//		//					}
	//		//				}
	//		//			}
	//		//		}
	//	}
}
