package com.ct.qa.keywords

import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import com.ct.qa.constants.ProjectConstants
import com.googlecode.javacv.CanvasFrame.Exception
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
import com.kms.katalon.core.util.KeywordUtil
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords
import com.ct.qa.struct.ScenariosCombination
import com.ct.qa.struct.UnmatchedItems

import internal.GlobalVariable
import io.appium.java_client.MobileElement
import MobileBuiltInKeywords as Mobile
import WSBuiltInKeywords as WS
import WebUiBuiltInKeywords as WebUI

public class CategoryVisitingScenariosKeywords{

	/********************************************************************
	 VISIT SHOP MAIN CATEGORIES WITH DATA VERFICATIONS
	 *******************************************************************/

	@Keyword
	def visitShopCategoriesWithDataVerification(){
		MobileElement channel = ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.RelativeLayout[1]/android.widget.TextView[1]")
		ProjectConstants.CURRENTVISITING_SHOPCHANNEL = channel.getText()
		UnmatchedItems unmatcheditems = CompareDataKeywords.compareShopCategories()
		if(unmatcheditems.getStatus() == 2){
			for(int j=0; j<ProjectConstants.missingshopdatainfo.size(); j++){
				if(ProjectConstants.missingshopdatainfo.get(j).getShopname().equalsIgnoreCase(ProjectConstants.CURRENTVISITING_SHOPNAME)) {
					ProjectConstants.missingshopdatainfo.get(j).setMissingshopcategories(unmatcheditems.getItems())
					ProjectConstants.missingshopdatainfo.get(j).setMissingshopcategories_errormessage(ProjectConstants.MESSAGEFOR_ITEMSARE_NOTMATCH)
					break
				}
				else{
				}
			}
		}
		else if(unmatcheditems.getStatus() == 1){
			for(int j=0; j<ProjectConstants.missingshopdatainfo.size(); j++){
				if(ProjectConstants.missingshopdatainfo.get(j).getShopname().equalsIgnoreCase(ProjectConstants.CURRENTVISITING_SHOPNAME)) {
					ProjectConstants.missingshopdatainfo.get(j).setMissingshopcategories(unmatcheditems.getItems())
					ProjectConstants.missingshopdatainfo.get(j).setMissingshopcategories_errormessage(ProjectConstants.MESSAGEFOR_ITEMSARE_MORE)
					break
				}
				else{
				}
			}
		}
		else if(unmatcheditems.getStatus() == -1){
			for(int j=0; j<ProjectConstants.missingshopdatainfo.size(); j++){
				if(ProjectConstants.missingshopdatainfo.get(j).getShopname().equalsIgnoreCase(ProjectConstants.CURRENTVISITING_SHOPNAME)) {
					ProjectConstants.missingshopdatainfo.get(j).setMissingshopcategories(unmatcheditems.getItems())
					ProjectConstants.missingshopdatainfo.get(j).setMissingshopcategories_errormessage(ProjectConstants.MESSAGEFOR_ITEMSARE_MISSING)
					break
				}
				else{
				}
			}
		}
		else{
		}
		Mobile.swipe(0, 200, 0, 750)
		Mobile.swipe(0, 200, 0, 750)
		int index = 0
		String lastvisitedcategory = ""
		int totalcategories = ProjectConstants.DRIVER.findElementsByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ListView[1]/*").size()
		for(int i=1; i<=totalcategories; i++){
			MobileElement category = ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout["+i+"]/android.widget.TextView[1]")
			String categoryname = category.getText()
			if(categoryname.equalsIgnoreCase("Chiller")){
				ProjectConstants.CURRENTVISITING_MAINCATEGORY = categoryname
				ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout["+i+"]").click()
				CommonKeywords.takePicture()
				MobileBuiltInKeywords.verifyElementText(findTestObject('ShopOpen/Chiller/Validate_ChillerScreen', [('package') : ProjectConstants.PACKAGENAME]), 'KPI: Chiller')
				int remarks = ProjectConstants.DRIVER.findElementsByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ListView[1]/*").size();
				for(int j=1; j<= remarks; j++){
					MobileElement remark = ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout["+j+"]/android.widget.TextView[1]")
					String remarktext = remark.getText()
					if(remarktext.equalsIgnoreCase("Chiller Not Allocated")){
						ProjectConstants.CURRENTVISITING_CATEGORYREMARK = remarktext
						ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout["+j+"]").click()
						Mobile.callTestCase(findTestCase("ShopOpen/Chiller/VisitChillerWithChillerNotAllocated"), null)
						break
					}
				}
			}
			else if(categoryname.equalsIgnoreCase("Chiller Utilization")){
				ProjectConstants.CURRENTVISITING_MAINCATEGORY = categoryname
				ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout["+i+"]").click()
				CommonKeywords.takePicture()
				Mobile.callTestCase(findTestCase("Test Cases/ShopOpen/ChillerUtilization/VisitChillerScenarios/VisitChillerUtilizationWithDataVerification"), null)
			}
			else if(categoryname.equalsIgnoreCase("Additional Picture")){
				ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout["+i+"]").click()
				Mobile.callTestCase(findTestCase("Test Cases/ShopOpen/AdditionalPicture/VisitAdditionalPicture"), null)
			}
			else if(categoryname.equalsIgnoreCase("POP Application")){
				ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout["+i+"]").click()
				Mobile.callTestCase(findTestCase("Test Cases/ShopOpen/POPApplication/OverwritePOPApplication"), null)
			}
			else if(categoryname.equalsIgnoreCase("Competition Tracking")){
				ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout["+i+"]").click()
				Mobile.callTestCase(findTestCase("Test Cases/ShopOpen/CompetitionTracking/VisitCompetitionTracking"), null)
				Mobile.swipe(0, 200, 0, 750)
			}
			else if(categoryname.equalsIgnoreCase("Retailer Remarks")){
				ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout["+i+"]").click()
				Mobile.callTestCase(findTestCase("Test Cases/ShopOpen/RetailerRemarks/VisitRetailerRemarks"), null)
			}
			else if(categoryname.equalsIgnoreCase("RTM -Visit Frequency")){
				ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout["+i+"]").click()
				Mobile.callTestCase(findTestCase("Test Cases/ShopOpen/RTMVisitFrequency/VisitRTMVisitFrequency"), null)
			}
			else if(categoryname.equalsIgnoreCase("Hanger Availibility")){
				ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout["+i+"]").click()
				Mobile.callTestCase(findTestCase("Test Cases/ShopOpen/HangerAvailability/VisitHangerAvailability"), null)
			}
			else if(categoryname.equalsIgnoreCase("Nestrade")){
				ProjectConstants.CURRENTVISITING_MAINCATEGORY = categoryname
				ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout["+i+"]").click()
				CommonKeywords.takePicture()
				MobileBuiltInKeywords.verifyElementText(findTestObject('ShopOpen/RemainingCategories/Validate_MainCategoryDetailScreen', [('package') : ProjectConstants.PACKAGENAME]), 'Display Space Available')
				int remarks = ProjectConstants.DRIVER.findElementsByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ListView[1]/*").size()
				for(int j=1; j<= remarks; j++){
					MobileElement remark = ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout["+j+"]/android.widget.TextView[1]")
					String remarktext = remark.getText()
					if(remarktext.equalsIgnoreCase("Display Space Available")){
						ProjectConstants.CURRENTVISITING_CATEGORYREMARK = remarktext
						ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout["+j+"]").click()
						Mobile.callTestCase(findTestCase("Test Cases/ShopOpen/Nestrade/VisitNestradeWithDSA"), null)
						break
					}
				}
			}
			else if(categoryname.equalsIgnoreCase("Survey")){
				ProjectConstants.CURRENTVISITING_MAINCATEGORY = categoryname
				ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout["+i+"]").click()
				CommonKeywords.takePicture()
				Mobile.callTestCase(findTestCase("ShopOpen/Survey/visitSurvey"), null)
			}
			else{
				ProjectConstants.CURRENTVISITING_MAINCATEGORY = categoryname
				ProjectConstants.CURRENTVISITING_PRODUCTCATEGORY = categoryname
				ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout["+i+"]").click()
				CommonKeywords.takePicture()
				MobileBuiltInKeywords.verifyElementText(findTestObject('ShopOpen/RemainingCategories/Validate_MainCategoryDetailScreen', [('package') : ProjectConstants.PACKAGENAME]), 'Display Space Available')
				int remarks = ProjectConstants.DRIVER.findElementsByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ListView[1]/*").size()
				for(int j=1; j<= remarks; j++){
					MobileElement remark = ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout["+j+"]/android.widget.TextView[1]")
					String remarktext = remark.getText()
					if(remarktext.equalsIgnoreCase("Display Space Available")){
						ProjectConstants.CURRENTVISITING_CATEGORYREMARK = remarktext
						ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout["+j+"]").click()
						Mobile.callTestCase(findTestCase("Test Cases/ShopOpen/RemainingCategories/VisitRemainingCategoriesWithDSA"), null)
						break
					}
				}
			}
		}
		while(true){
			index = ProjectConstants.DRIVER.findElementsByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ListView[1]/*").size()
			MobileElement lastitembeforeswipe = ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout["+index+"]/android.widget.TextView[1]")
			String lastitemnamebeforeswipe = lastitembeforeswipe.getText()
			Mobile.swipe(0, 293, 0, 200)
			index = ProjectConstants.DRIVER.findElementsByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ListView[1]/*").size()
			MobileElement lastitemafterswipe = ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout["+index+"]/android.widget.TextView[1]")
			String lastitemnameafterswipe = lastitemafterswipe.getText()
			if(lastitemnamebeforeswipe.equalsIgnoreCase(lastitemnameafterswipe)){
				break
			}
			else if(lastitemnameafterswipe.equalsIgnoreCase("Chiller")){
				lastvisitedcategory = lastitemnameafterswipe
				ProjectConstants.CURRENTVISITING_MAINCATEGORY = lastitemnameafterswipe
				ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout["+index+"]").click()
				CommonKeywords.takePicture()
				MobileBuiltInKeywords.verifyElementText(findTestObject('ShopOpen/Chiller/Validate_ChillerScreen', [('package') : ProjectConstants.PACKAGENAME]), 'KPI: Chiller')
				int remarks = ProjectConstants.DRIVER.findElementsByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ListView[1]/*").size();
				for(int j=1; j<= remarks; j++){
					MobileElement remark = ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout["+j+"]/android.widget.TextView[1]")
					String remarktext = remark.getText()
					if(remarktext.equalsIgnoreCase("Chiller Not Allocated")){
						ProjectConstants.CURRENTVISITING_CATEGORYREMARK = remarktext
						ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout["+j+"]").click()
						Mobile.callTestCase(findTestCase("ShopOpen/Chiller/VisitChillerWithChillerNotAllocated"), null)
						break
					}
				}
			}
			else if(lastitemnameafterswipe.equalsIgnoreCase("Chiller Utilization")){
				lastvisitedcategory = lastitemnameafterswipe
				ProjectConstants.CURRENTVISITING_MAINCATEGORY = lastitemnameafterswipe
				ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout["+index+"]").click()
				CommonKeywords.takePicture()
				Mobile.callTestCase(findTestCase("ShopOpen/ChillerUtilization/VisitChillerScenarios/VisitChillerUtilizationWith_ChillerAvailable"), null)
			}
			else if(lastitemnameafterswipe.equalsIgnoreCase("Additional Picture")){
				lastvisitedcategory = lastitemnameafterswipe
				ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout["+index+"]").click()
				Mobile.callTestCase(findTestCase("Test Cases/ShopOpen/AdditionalPicture/VisitAdditionalPicture"), null)
			}
			else if(lastitemnameafterswipe.equalsIgnoreCase("Competition Tracking")){
				lastvisitedcategory = lastitemnameafterswipe
				ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout["+index+"]").click()
				Mobile.callTestCase(findTestCase("Test Cases/ShopOpen/CompetitionTracking/VisitCompetitionTracking"), null)
			}
			else if(lastitemnameafterswipe.equalsIgnoreCase("Retailer Remarks")){
				lastvisitedcategory = lastitemnameafterswipe
				ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout["+index+"]").click()
				Mobile.callTestCase(findTestCase("Test Cases/ShopOpen/RetailerRemarks/VisitRetailerRemarks"), null)
			}
			else if(lastitemnameafterswipe.equalsIgnoreCase("RTM -Visit Frequency")){
				lastvisitedcategory = lastitemnameafterswipe
				ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout["+index+"]").click()
				Mobile.callTestCase(findTestCase("Test Cases/ShopOpen/RTMVisitFrequency/VisitRTMVisitFrequency"), null)
			}
			else if(lastitemnameafterswipe.equalsIgnoreCase("POP Application")){
				lastvisitedcategory = lastitemnameafterswipe
				ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout["+index+"]").click()
				Mobile.callTestCase(findTestCase("Test Cases/ShopOpen/POPApplication/OverwritePOPApplication"), null)
			}
			else if(lastitemnameafterswipe.equalsIgnoreCase("Hanger Availibility")){
				lastvisitedcategory = lastitemnameafterswipe
				ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout["+index+"]").click()
				Mobile.callTestCase(findTestCase("Test Cases/ShopOpen/HangerAvailability/VisitHangerAvailability"), null)
			}
			else if(lastitemnameafterswipe.equalsIgnoreCase("Nestrade")){
				lastvisitedcategory = lastitemnameafterswipe
				ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout["+index+"]").click()
				CommonKeywords.takePicture()
				MobileBuiltInKeywords.verifyElementText(findTestObject('ShopOpen/RemainingCategories/Validate_MainCategoryDetailScreen', [('package') : ProjectConstants.PACKAGENAME]), 'Display Space Available')
				int remarks = ProjectConstants.DRIVER.findElementsByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ListView[1]/*").size()
				for(int j=1; j<= remarks; j++){
					MobileElement remark = ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout["+j+"]/android.widget.TextView[1]")
					String remarktext = remark.getText()
					if(remarktext.equalsIgnoreCase("Display Space Available")){
						ProjectConstants.CURRENTVISITING_CATEGORYREMARK = remarktext
						ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout["+j+"]").click()
						Mobile.callTestCase(findTestCase("Test Cases/ShopOpen/Nestrade/VisitNestradeWithDSA"), null)
						break
					}
				}
			}
			else if(lastitemnameafterswipe.equalsIgnoreCase("Survey")){
				lastvisitedcategory = lastitemnameafterswipe
				ProjectConstants.CURRENTVISITING_MAINCATEGORY = lastitemnameafterswipe
				ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout["+index+"]").click()
				CommonKeywords.takePicture()
				Mobile.callTestCase(findTestCase("ShopOpen/Survey/visitSurvey"), null)
			}
			else{
				lastvisitedcategory = lastitemnameafterswipe
				ProjectConstants.CURRENTVISITING_MAINCATEGORY = lastitemnameafterswipe
				ProjectConstants.CURRENTVISITING_PRODUCTCATEGORY = lastitemnameafterswipe
				ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout["+index+"]").click()
				CommonKeywords.takePicture()
				MobileBuiltInKeywords.verifyElementText(findTestObject('ShopOpen/RemainingCategories/Validate_MainCategoryDetailScreen', [('package') : ProjectConstants.PACKAGENAME]), 'Display Space Available')
				int remarks = ProjectConstants.DRIVER.findElementsByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ListView[1]/*").size()
				for(int j=1; j<= remarks; j++){
					MobileElement remark = ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout["+j+"]/android.widget.TextView[1]")
					String remarktext = remark.getText()
					if(remarktext.equalsIgnoreCase("Display Space Available")){
						ProjectConstants.CURRENTVISITING_CATEGORYREMARK = remarktext
						ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout["+j+"]").click()
						Mobile.callTestCase(findTestCase("Test Cases/ShopOpen/RemainingCategories/VisitRemainingCategoriesWithDSA"), null)
						break
					}
				}
			}
			while(true){
				Mobile.swipe(0, 293, 0, 200)
				index = ProjectConstants.DRIVER.findElementsByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ListView[1]/*").size()
				MobileElement product = ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout["+index+"]/android.widget.TextView[1]")
				String productname = product.getText()
				if(productname.equalsIgnoreCase(lastvisitedcategory)){
					break
				}
			}
		}
	}

	/********************************************************************
	 VISIT SHOP MAIN CATEGORIES WITH OVERWRITE SCENARIOS
	 *******************************************************************/

	@Keyword
	def visitShopCategoriesWithOverwriteScenarios(){
		ArrayList<ScenariosCombination> scenarioscombination = new ArrayList<ScenariosCombination>()
		MobileElement channel = ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.RelativeLayout[1]/android.widget.TextView[1]")
		ProjectConstants.CURRENTVISITING_SHOPCHANNEL = channel.getText()
		UnmatchedItems unmatcheditems = CompareDataKeywords.compareShopCategories()
		if(unmatcheditems.getStatus() == 2){
			for(int j=0; j<ProjectConstants.missingshopdatainfo.size(); j++){
				if(ProjectConstants.missingshopdatainfo.get(j).getShopname().equalsIgnoreCase(ProjectConstants.CURRENTVISITING_SHOPNAME)) {
					ProjectConstants.missingshopdatainfo.get(j).setMissingshopcategories(unmatcheditems.getItems())
					ProjectConstants.missingshopdatainfo.get(j).setMissingshopcategories_errormessage(ProjectConstants.MESSAGEFOR_ITEMSARE_NOTMATCH)
					break
				}
				else{
				}
			}
		}
		else if(unmatcheditems.getStatus() == 1){
			for(int j=0; j<ProjectConstants.missingshopdatainfo.size(); j++){
				if(ProjectConstants.missingshopdatainfo.get(j).getShopname().equalsIgnoreCase(ProjectConstants.CURRENTVISITING_SHOPNAME)) {
					ProjectConstants.missingshopdatainfo.get(j).setMissingshopcategories(unmatcheditems.getItems())
					ProjectConstants.missingshopdatainfo.get(j).setMissingshopcategories_errormessage(ProjectConstants.MESSAGEFOR_ITEMSARE_MORE)
					break
				}
				else{
				}
			}
		}
		else if(unmatcheditems.getStatus() == -1){
			for(int j=0; j<ProjectConstants.missingshopdatainfo.size(); j++){
				if(ProjectConstants.missingshopdatainfo.get(j).getShopname().equalsIgnoreCase(ProjectConstants.CURRENTVISITING_SHOPNAME)) {
					ProjectConstants.missingshopdatainfo.get(j).setMissingshopcategories(unmatcheditems.getItems())
					ProjectConstants.missingshopdatainfo.get(j).setMissingshopcategories_errormessage(ProjectConstants.MESSAGEFOR_ITEMSARE_MISSING)
					break
				}
				else{
				}
			}
		}
		else{
		}
		Mobile.swipe(0, 200, 0, 750)
		Mobile.swipe(0, 200, 0, 750)
		int index = 0
		String lastvisitedcategory = ""
		int totalcategories = ProjectConstants.DRIVER.findElementsByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ListView[1]/*").size()
		for(int i=1; i<=totalcategories; i++){
			MobileElement category = ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout["+i+"]/android.widget.TextView[1]")
			String categoryname = category.getText()
			if(categoryname.equalsIgnoreCase("Chiller")){
				ProjectConstants.CURRENTVISITING_MAINCATEGORY = categoryname
				ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout["+i+"]").click()
				CommonKeywords.takePicture()
				MobileBuiltInKeywords.verifyElementText(findTestObject('ShopOpen/Chiller/Validate_ChillerScreen', [('package') : ProjectConstants.PACKAGENAME]), 'KPI: Chiller')
				int remarks = ProjectConstants.DRIVER.findElementsByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ListView[1]/*").size();
				for(int j=1; j<= remarks; j++){
					for(int k=1; k<= remarks; k++){
						ScenariosCombination _scenarioscombination = new ScenariosCombination()
						_scenarioscombination.setFirstvisit_scenario(j)
						_scenarioscombination.setOverwrite_scenario(k)
						scenarioscombination.add(_scenarioscombination)
					}
				}
				if(scenarioscombination.size() >= ProjectConstants.SHOP_ATTEMPT){
					ScenariosCombination scenario = scenarioscombination.get((ProjectConstants.SHOP_ATTEMPT-1))
					MobileElement remark = ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout["+scenario.getFirstvisit_scenario()+"]/android.widget.TextView[1]")
					String remarktext = remark.getText()
					ProjectConstants.CURRENTVISITING_CATEGORYREMARK = remarktext
					if(remarktext.equalsIgnoreCase("Chiller Not Allocated")){
						ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout["+scenario.getFirstvisit_scenario()+"]").click()
						Mobile.callTestCase(findTestCase("ShopOpen/Chiller/VisitChillerWithChillerNotAllocated"), null)
					}
					else if(remarktext.equalsIgnoreCase("Shopkeeper did not allow")){
						ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout["+scenario.getFirstvisit_scenario()+"]").click()
						Mobile.callTestCase(findTestCase("Test Cases/ShopOpen/Chiller/VisitChillerWithSKDNA"), null)
					}
					else{}
				}
				else{
					MobileElement remark = ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout[1]/android.widget.TextView[1]")
					String remarktext = remark.getText()
					ProjectConstants.CURRENTVISITING_CATEGORYREMARK = remarktext
					if(remarktext.equalsIgnoreCase("Chiller Not Allocated")){
						ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout[1]").click()
						Mobile.callTestCase(findTestCase("ShopOpen/Chiller/VisitChillerWithChillerNotAllocated"), null)
					}
					else if(remarktext.equalsIgnoreCase("Shopkeeper did not allow")){
						ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout[1]").click()
						Mobile.callTestCase(findTestCase("Test Cases/ShopOpen/Chiller/VisitChillerWithSKDNA"), null)
					}
					else{}
				}
			}
			else if(categoryname.equalsIgnoreCase("Chiller Utilization")){
				ProjectConstants.CURRENTVISITING_MAINCATEGORY = categoryname
				ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout["+i+"]").click()
				CommonKeywords.takePicture()
				visitChillerUtilizationOverwriteScenarios()
			}
			else if(categoryname.equalsIgnoreCase("Additional Picture")){
				ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout["+i+"]").click()
				Mobile.callTestCase(findTestCase("Test Cases/ShopOpen/AdditionalPicture/VisitAdditionalPicture"), null)
			}
			else if(categoryname.equalsIgnoreCase("POP Application")){
				ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout["+i+"]").click()
				Mobile.callTestCase(findTestCase("Test Cases/ShopOpen/POPApplication/OverwritePOPApplication"), null)
			}
			else if(categoryname.equalsIgnoreCase("Competition Tracking")){
				ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout["+i+"]").click()
				Mobile.callTestCase(findTestCase("Test Cases/ShopOpen/CompetitionTracking/VisitCompetitionTracking"), null)
				Mobile.swipe(0, 200, 0, 750)
			}
			else if(categoryname.equalsIgnoreCase("Retailer Remarks")){
				ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout["+i+"]").click()
				Mobile.callTestCase(findTestCase("Test Cases/ShopOpen/RetailerRemarks/VisitRetailerRemarks"), null)
			}
			else if(categoryname.equalsIgnoreCase("RTM -Visit Frequency")){
				ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout["+i+"]").click()
				Mobile.callTestCase(findTestCase("Test Cases/ShopOpen/RTMVisitFrequency/VisitRTMVisitFrequency"), null)
			}
			else if(categoryname.equalsIgnoreCase("Hanger Availibility")){
				ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout["+i+"]").click()
				Mobile.callTestCase(findTestCase("Test Cases/ShopOpen/HangerAvailability/VisitHangerAvailability"), null)
			}
			else if(categoryname.equalsIgnoreCase("Nestrade")){
				ProjectConstants.CURRENTVISITING_MAINCATEGORY = categoryname
				ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout["+i+"]").click()
				CommonKeywords.takePicture()
				MobileBuiltInKeywords.verifyElementText(findTestObject('ShopOpen/RemainingCategories/Validate_MainCategoryDetailScreen', [('package') : ProjectConstants.PACKAGENAME]), 'Display Space Available')
				int remarks = ProjectConstants.DRIVER.findElementsByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ListView[1]/*").size();
				for(int j=1; j<= remarks; j++){
					for(int k=1; k<= remarks; k++){
						ScenariosCombination _scenarioscombination = new ScenariosCombination()
						_scenarioscombination.setFirstvisit_scenario(j)
						_scenarioscombination.setOverwrite_scenario(k)
						scenarioscombination.add(_scenarioscombination)
					}
				}
				if(scenarioscombination.size() >= ProjectConstants.SHOP_ATTEMPT){
					ScenariosCombination scenario = scenarioscombination.get((ProjectConstants.SHOP_ATTEMPT-1))
					MobileElement remark = ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout["+scenario.getFirstvisit_scenario()+"]/android.widget.TextView[1]")
					String remarktext = remark.getText()
					ProjectConstants.CURRENTVISITING_CATEGORYREMARK = remarktext
					if(remarktext.equalsIgnoreCase("Display Space Available")){
						ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout["+scenario.getFirstvisit_scenario()+"]").click()
						Mobile.callTestCase(findTestCase("Test Cases/ShopOpen/Nestrade/VisitNestradeWithDSA"), null)
					}
					else if(remarktext.equalsIgnoreCase("No Space For Display")){
						ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout["+scenario.getFirstvisit_scenario()+"]").click()
						Mobile.callTestCase(findTestCase("Test Cases/ShopOpen/Nestrade/VisitNestradeWithNSFD"), null)
					}
					else if(remarktext.equalsIgnoreCase("Shopkeeper did not allow")){
						ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout["+scenario.getFirstvisit_scenario()+"]").click()
						Mobile.callTestCase(findTestCase("Test Cases/ShopOpen/Nestrade/VisitNestradeWithSKDNA"), null)
					}
					else{}
				}
				else{
					MobileElement remark = ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout[1]/android.widget.TextView[1]")
					String remarktext = remark.getText()
					ProjectConstants.CURRENTVISITING_CATEGORYREMARK = remarktext
					if(remarktext.equalsIgnoreCase("Display Space Available")){
						ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout[1]").click()
						Mobile.callTestCase(findTestCase("Test Cases/ShopOpen/Nestrade/VisitNestradeWithDSA"), null)
					}
					else if(remarktext.equalsIgnoreCase("No Space For Display")){
						ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout[1]").click()
						Mobile.callTestCase(findTestCase("Test Cases/ShopOpen/Nestrade/VisitNestradeWithNSFD"), null)
					}
					else if(remarktext.equalsIgnoreCase("Shopkeeper did not allow")){
						ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout[1]").click()
						Mobile.callTestCase(findTestCase("Test Cases/ShopOpen/Nestrade/VisitNestradeWithSKDNA"), null)
					}
					else{}
				}
			}
			else if(categoryname.equalsIgnoreCase("Survey")){
				ProjectConstants.CURRENTVISITING_MAINCATEGORY = categoryname
				ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout["+i+"]").click()
				CommonKeywords.takePicture()
				Mobile.callTestCase(findTestCase("ShopOpen/Survey/visitSurvey"), null)
			}
			else{
				ProjectConstants.CURRENTVISITING_MAINCATEGORY = categoryname
				ProjectConstants.CURRENTVISITING_PRODUCTCATEGORY = categoryname
				ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout["+i+"]").click()
				CommonKeywords.takePicture()
				MobileBuiltInKeywords.verifyElementText(findTestObject('ShopOpen/RemainingCategories/Validate_MainCategoryDetailScreen', [('package') : ProjectConstants.PACKAGENAME]), 'Display Space Available')
				int remarks = ProjectConstants.DRIVER.findElementsByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ListView[1]/*").size();
				for(int j=1; j<= remarks; j++){
					for(int k=1; k<= remarks; k++){
						ScenariosCombination _scenarioscombination = new ScenariosCombination()
						_scenarioscombination.setFirstvisit_scenario(j)
						_scenarioscombination.setOverwrite_scenario(k)
						scenarioscombination.add(_scenarioscombination)
					}
				}
				if(scenarioscombination.size() >= ProjectConstants.SHOP_ATTEMPT){
					ScenariosCombination scenario = scenarioscombination.get((ProjectConstants.SHOP_ATTEMPT-1))
					MobileElement remark = ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout["+scenario.getFirstvisit_scenario()+"]/android.widget.TextView[1]")
					String remarktext = remark.getText()
					ProjectConstants.CURRENTVISITING_CATEGORYREMARK = remarktext
					if(remarktext.equalsIgnoreCase("Display Space Available")){
						ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout["+scenario.getFirstvisit_scenario()+"]").click()
						Mobile.callTestCase(findTestCase("Test Cases/ShopOpen/RemainingCategories/VisitRemainingCategoriesWithDSA"), null)
					}
					else if(remarktext.equalsIgnoreCase("No Space For Display")){
						ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout["+scenario.getFirstvisit_scenario()+"]").click()
						Mobile.callTestCase(findTestCase("Test Cases/ShopOpen/RemainingCategories/VisitRemainingCategoriesWithNSFD"), null)
					}
					else if(remarktext.equalsIgnoreCase("Shopkeeper did not allow")){
						ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout["+scenario.getFirstvisit_scenario()+"]").click()
						Mobile.callTestCase(findTestCase("Test Cases/ShopOpen/RemainingCategories/VisitRemainingCategoriesWithSKDNA"), null)
					}
					else{}
				}
				else{
					MobileElement remark = ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout[1]/android.widget.TextView[1]")
					String remarktext = remark.getText()
					ProjectConstants.CURRENTVISITING_CATEGORYREMARK = remarktext
					if(remarktext.equalsIgnoreCase("Display Space Available")){
						ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout[1]").click()
						Mobile.callTestCase(findTestCase("Test Cases/ShopOpen/RemainingCategories/VisitRemainingCategoriesWithDSA"), null)
					}
					else if(remarktext.equalsIgnoreCase("No Space For Display")){
						ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout[1]").click()
						Mobile.callTestCase(findTestCase("Test Cases/ShopOpen/RemainingCategories/VisitRemainingCategoriesWithNSFD"), null)
					}
					else if(remarktext.equalsIgnoreCase("Shopkeeper did not allow")){
						ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout[1]").click()
						Mobile.callTestCase(findTestCase("Test Cases/ShopOpen/RemainingCategories/VisitRemainingCategoriesWithSKDNA"), null)
					}
					else{}
				}
			}
		}
		while(true){
			index = ProjectConstants.DRIVER.findElementsByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ListView[1]/*").size()
			MobileElement lastitembeforeswipe = ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout["+index+"]/android.widget.TextView[1]")
			String lastitemnamebeforeswipe = lastitembeforeswipe.getText()
			Mobile.swipe(0, 293, 0, 200)
			index = ProjectConstants.DRIVER.findElementsByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ListView[1]/*").size()
			MobileElement lastitemafterswipe = ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout["+index+"]/android.widget.TextView[1]")
			String lastitemnameafterswipe = lastitemafterswipe.getText()
			if(lastitemnamebeforeswipe.equalsIgnoreCase(lastitemnameafterswipe)){
				break
			}
			else if(lastitemnameafterswipe.equalsIgnoreCase("Chiller")){
				lastvisitedcategory = lastitemnameafterswipe
				ProjectConstants.CURRENTVISITING_MAINCATEGORY = lastitemnameafterswipe
				ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout["+index+"]").click()
				CommonKeywords.takePicture()
				MobileBuiltInKeywords.verifyElementText(findTestObject('ShopOpen/Chiller/Validate_ChillerScreen', [('package') : ProjectConstants.PACKAGENAME]), 'KPI: Chiller')
				int remarks = ProjectConstants.DRIVER.findElementsByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ListView[1]/*").size();
				for(int j=1; j<= remarks; j++){
					for(int k=1; k<= remarks; k++){
						ScenariosCombination _scenarioscombination = new ScenariosCombination()
						_scenarioscombination.setFirstvisit_scenario(j)
						_scenarioscombination.setOverwrite_scenario(k)
						scenarioscombination.add(_scenarioscombination)
					}
				}
				if(scenarioscombination.size() >= ProjectConstants.SHOP_ATTEMPT){
					ScenariosCombination scenario = scenarioscombination.get((ProjectConstants.SHOP_ATTEMPT-1))
					MobileElement remark = ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout["+scenario.getFirstvisit_scenario()+"]/android.widget.TextView[1]")
					String remarktext = remark.getText()
					ProjectConstants.CURRENTVISITING_CATEGORYREMARK = remarktext
					if(remarktext.equalsIgnoreCase("Chiller Not Allocated")){
						ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout["+scenario.getFirstvisit_scenario()+"]").click()
						Mobile.callTestCase(findTestCase("ShopOpen/Chiller/VisitChillerWithChillerNotAllocated"), null)
					}
					else if(remarktext.equalsIgnoreCase("Shopkeeper did not allow")){
						ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout["+scenario.getFirstvisit_scenario()+"]").click()
						Mobile.callTestCase(findTestCase("Test Cases/ShopOpen/Chiller/VisitChillerWithSKDNA"), null)
					}
					else{}
				}
				else{
					MobileElement remark = ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout[1]/android.widget.TextView[1]")
					String remarktext = remark.getText()
					ProjectConstants.CURRENTVISITING_CATEGORYREMARK = remarktext
					if(remarktext.equalsIgnoreCase("Chiller Not Allocated")){
						ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout[1]").click()
						Mobile.callTestCase(findTestCase("ShopOpen/Chiller/VisitChillerWithChillerNotAllocated"), null)
					}
					else if(remarktext.equalsIgnoreCase("Shopkeeper did not allow")){
						ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout[1]").click()
						Mobile.callTestCase(findTestCase("Test Cases/ShopOpen/Chiller/VisitChillerWithSKDNA"), null)
					}
					else{}
				}
			}
			else if(lastitemnameafterswipe.equalsIgnoreCase("Chiller Utilization")){
				lastvisitedcategory = lastitemnameafterswipe
				ProjectConstants.CURRENTVISITING_MAINCATEGORY = lastitemnameafterswipe
				ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout["+index+"]").click()
				CommonKeywords.takePicture()
				visitChillerUtilizationOverwriteScenarios()
			}
			else if(lastitemnameafterswipe.equalsIgnoreCase("Additional Picture")){
				lastvisitedcategory = lastitemnameafterswipe
				ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout["+index+"]").click()
				Mobile.callTestCase(findTestCase("Test Cases/ShopOpen/AdditionalPicture/VisitAdditionalPicture"), null)
			}
			else if(lastitemnameafterswipe.equalsIgnoreCase("Competition Tracking")){
				lastvisitedcategory = lastitemnameafterswipe
				ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout["+index+"]").click()
				Mobile.callTestCase(findTestCase("Test Cases/ShopOpen/CompetitionTracking/VisitCompetitionTracking"), null)
			}
			else if(lastitemnameafterswipe.equalsIgnoreCase("Retailer Remarks")){
				lastvisitedcategory = lastitemnameafterswipe
				ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout["+index+"]").click()
				Mobile.callTestCase(findTestCase("Test Cases/ShopOpen/RetailerRemarks/VisitRetailerRemarks"), null)
			}
			else if(lastitemnameafterswipe.equalsIgnoreCase("RTM -Visit Frequency")){
				lastvisitedcategory = lastitemnameafterswipe
				ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout["+index+"]").click()
				Mobile.callTestCase(findTestCase("Test Cases/ShopOpen/RTMVisitFrequency/VisitRTMVisitFrequency"), null)
			}
			else if(lastitemnameafterswipe.equalsIgnoreCase("POP Application")){
				lastvisitedcategory = lastitemnameafterswipe
				ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout["+index+"]").click()
				Mobile.callTestCase(findTestCase("Test Cases/ShopOpen/POPApplication/OverwritePOPApplication"), null)
			}
			else if(lastitemnameafterswipe.equalsIgnoreCase("Hanger Availibility")){
				lastvisitedcategory = lastitemnameafterswipe
				ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout["+index+"]").click()
				Mobile.callTestCase(findTestCase("Test Cases/ShopOpen/HangerAvailability/VisitHangerAvailability"), null)
			}
			else if(lastitemnameafterswipe.equalsIgnoreCase("Nestrade")){
				lastvisitedcategory = lastitemnameafterswipe
				ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout["+index+"]").click()
				CommonKeywords.takePicture()
				MobileBuiltInKeywords.verifyElementText(findTestObject('ShopOpen/RemainingCategories/Validate_MainCategoryDetailScreen', [('package') : ProjectConstants.PACKAGENAME]), 'Display Space Available')
				int remarks = ProjectConstants.DRIVER.findElementsByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ListView[1]/*").size();
				for(int j=1; j<= remarks; j++){
					for(int k=1; k<= remarks; k++){
						ScenariosCombination _scenarioscombination = new ScenariosCombination()
						_scenarioscombination.setFirstvisit_scenario(j)
						_scenarioscombination.setOverwrite_scenario(k)
						scenarioscombination.add(_scenarioscombination)
					}
				}
				if(scenarioscombination.size() >= ProjectConstants.SHOP_ATTEMPT){
					ScenariosCombination scenario = scenarioscombination.get((ProjectConstants.SHOP_ATTEMPT-1))
					MobileElement remark = ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout["+scenario.getFirstvisit_scenario()+"]/android.widget.TextView[1]")
					String remarktext = remark.getText()
					ProjectConstants.CURRENTVISITING_CATEGORYREMARK = remarktext
					if(remarktext.equalsIgnoreCase("Display Space Available")){
						ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout["+scenario.getFirstvisit_scenario()+"]").click()
						Mobile.callTestCase(findTestCase("Test Cases/ShopOpen/Nestrade/VisitNestradeWithDSA"), null)
					}
					else if(remarktext.equalsIgnoreCase("No Space For Display")){
						ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout["+scenario.getFirstvisit_scenario()+"]").click()
						Mobile.callTestCase(findTestCase("Test Cases/ShopOpen/Nestrade/VisitNestradeWithNSFD"), null)
					}
					else if(remarktext.equalsIgnoreCase("Shopkeeper did not allow")){
						ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout["+scenario.getFirstvisit_scenario()+"]").click()
						Mobile.callTestCase(findTestCase("Test Cases/ShopOpen/Nestrade/VisitNestradeWithSKDNA"), null)
					}
					else{}
				}
				else{
					MobileElement remark = ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout[1]/android.widget.TextView[1]")
					String remarktext = remark.getText()
					ProjectConstants.CURRENTVISITING_CATEGORYREMARK = remarktext
					if(remarktext.equalsIgnoreCase("Display Space Available")){
						ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout[1]").click()
						Mobile.callTestCase(findTestCase("Test Cases/ShopOpen/Nestrade/VisitNestradeWithDSA"), null)
					}
					else if(remarktext.equalsIgnoreCase("No Space For Display")){
						ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout[1]").click()
						Mobile.callTestCase(findTestCase("Test Cases/ShopOpen/Nestrade/VisitNestradeWithNSFD"), null)
					}
					else if(remarktext.equalsIgnoreCase("Shopkeeper did not allow")){
						ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout[1]").click()
						Mobile.callTestCase(findTestCase("Test Cases/ShopOpen/Nestrade/VisitNestradeWithSKDNA"), null)
					}
					else{}
				}
			}
			else if(lastitemnameafterswipe.equalsIgnoreCase("Survey")){
				lastvisitedcategory = lastitemnameafterswipe
				ProjectConstants.CURRENTVISITING_MAINCATEGORY = lastitemnameafterswipe
				ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout["+index+"]").click()
				CommonKeywords.takePicture()
				Mobile.callTestCase(findTestCase("ShopOpen/Survey/visitSurvey"), null)
			}
			else{
				lastvisitedcategory = lastitemnameafterswipe
				ProjectConstants.CURRENTVISITING_MAINCATEGORY = lastitemnameafterswipe
				ProjectConstants.CURRENTVISITING_PRODUCTCATEGORY = lastitemnameafterswipe
				ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout["+index+"]").click()
				CommonKeywords.takePicture()
				MobileBuiltInKeywords.verifyElementText(findTestObject('ShopOpen/RemainingCategories/Validate_MainCategoryDetailScreen', [('package') : ProjectConstants.PACKAGENAME]), 'Display Space Available')
				int remarks = ProjectConstants.DRIVER.findElementsByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ListView[1]/*").size();
				for(int j=1; j<= remarks; j++){
					for(int k=1; k<= remarks; k++){
						ScenariosCombination _scenarioscombination = new ScenariosCombination()
						_scenarioscombination.setFirstvisit_scenario(j)
						_scenarioscombination.setOverwrite_scenario(k)
						scenarioscombination.add(_scenarioscombination)
					}
				}
				if(scenarioscombination.size() >= ProjectConstants.SHOP_ATTEMPT){
					ScenariosCombination scenario = scenarioscombination.get((ProjectConstants.SHOP_ATTEMPT-1))
					MobileElement remark = ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout["+scenario.getFirstvisit_scenario()+"]/android.widget.TextView[1]")
					String remarktext = remark.getText()
					ProjectConstants.CURRENTVISITING_CATEGORYREMARK = remarktext
					if(remarktext.equalsIgnoreCase("Display Space Available")){
						ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout["+scenario.getFirstvisit_scenario()+"]").click()
						Mobile.callTestCase(findTestCase("Test Cases/ShopOpen/RemainingCategories/VisitRemainingCategoriesWithDSA"), null)
					}
					else if(remarktext.equalsIgnoreCase("No Space For Display")){
						ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout["+scenario.getFirstvisit_scenario()+"]").click()
						Mobile.callTestCase(findTestCase("Test Cases/ShopOpen/RemainingCategories/VisitRemainingCategoriesWithNSFD"), null)
					}
					else if(remarktext.equalsIgnoreCase("Shopkeeper did not allow")){
						ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout["+scenario.getFirstvisit_scenario()+"]").click()
						Mobile.callTestCase(findTestCase("Test Cases/ShopOpen/RemainingCategories/VisitRemainingCategoriesWithSKDNA"), null)
					}
					else{}
				}
				else{
					MobileElement remark = ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout[1]/android.widget.TextView[1]")
					String remarktext = remark.getText()
					ProjectConstants.CURRENTVISITING_CATEGORYREMARK = remarktext
					if(remarktext.equalsIgnoreCase("Display Space Available")){
						ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout[1]").click()
						Mobile.callTestCase(findTestCase("Test Cases/ShopOpen/RemainingCategories/VisitRemainingCategoriesWithDSA"), null)
					}
					else if(remarktext.equalsIgnoreCase("No Space For Display")){
						ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout[1]").click()
						Mobile.callTestCase(findTestCase("Test Cases/ShopOpen/RemainingCategories/VisitRemainingCategoriesWithNSFD"), null)
					}
					else if(remarktext.equalsIgnoreCase("Shopkeeper did not allow")){
						ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout[1]").click()
						Mobile.callTestCase(findTestCase("Test Cases/ShopOpen/RemainingCategories/VisitRemainingCategoriesWithSKDNA"), null)
					}
					else{}
				}
			}
			while(true){
				Mobile.swipe(0, 293, 0, 200)
				index = ProjectConstants.DRIVER.findElementsByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ListView[1]/*").size()
				MobileElement product = ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout["+index+"]/android.widget.TextView[1]")
				String productname = product.getText()
				if(productname.equalsIgnoreCase(lastvisitedcategory)){
					break
				}
			}
		}
	}
	def visitChillerUtilizationOverwriteScenarios(){
		if(ProjectConstants.SHOP_ATTEMPT == 1){
			Mobile.callTestCase(findTestCase("Test Cases/ShopOpen/ChillerUtilization/VisitChillerScenarios/VisitChillerUtilizationWith_ChillerAvailable"), null)
		}
		else if(ProjectConstants.SHOP_ATTEMPT == 2){
			Mobile.callTestCase(findTestCase("Test Cases/ShopOpen/ChillerUtilization/VisitChillerScenarios/VisitChillerUtilizationWith_ChillerRemovedForMaintenance"), null)
		}
		else if(ProjectConstants.SHOP_ATTEMPT == 3){
			Mobile.callTestCase(findTestCase("Test Cases/ShopOpen/ChillerUtilization/VisitChillerScenarios/VisitChillerUtilizationWith_ChillerTypeNotAvailable"), null)
		}
		else if(ProjectConstants.SHOP_ATTEMPT == 4){
			Mobile.callTestCase(findTestCase("Test Cases/ShopOpen/ChillerUtilization/VisitChillerScenarios/VisitChillerUtilizationWith_ShopKeeperDidNotAllow"), null)
		}
		else if(ProjectConstants.SHOP_ATTEMPT == 5){
			Mobile.callTestCase(findTestCase("Test Cases/ShopOpen/ChillerUtilization/VisitChillerScenarios/VisitChillerUtilizationWith_ShopKeeperDidNotAllow"), null)
		}
		else if(ProjectConstants.SHOP_ATTEMPT == 6){
			Mobile.callTestCase(findTestCase("Test Cases/ShopOpen/ChillerUtilization/VisitChillerScenarios/VisitChillerUtilizationWith_ChillerNotAvailable"), null)
		}
		else if(ProjectConstants.SHOP_ATTEMPT == 7){
			Mobile.callTestCase(findTestCase("Test Cases/ShopOpen/ChillerUtilization/VisitChillerScenarios/VisitChillerUtilizationWith_ChillerAvailable"), null)
		}
		else if(ProjectConstants.SHOP_ATTEMPT == 8){
			Mobile.callTestCase(findTestCase("Test Cases/ShopOpen/ChillerUtilization/ChillerNotAvailable/VisitChillerNotAvailableWithOneProductCategory"), null)
		}
		else{
			Mobile.callTestCase(findTestCase("Test Cases/ShopOpen/ChillerUtilization/VisitChillerScenarios/VisitChillerUtilizationWith_ChillerAvailable"), null)
		}
	}
	@Keyword
	def visitShopCategoriesWithOverwritingScenarios(){
		ArrayList<ScenariosCombination> scenarioscombination = new ArrayList<ScenariosCombination>()
		MobileElement channel = ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.RelativeLayout[1]/android.widget.TextView[1]")
		ProjectConstants.CURRENTVISITING_SHOPCHANNEL = channel.getText()
		Mobile.swipe(0, 200, 0, 750)
		Mobile.swipe(0, 200, 0, 750)
		int index = 0
		String lastvisitedcategory = ""
		int totalcategories = ProjectConstants.DRIVER.findElementsByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ListView[1]/*").size()
		for(int i=1; i<=totalcategories; i++){
			MobileElement category = ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout["+i+"]/android.widget.TextView[1]")
			String categoryname = category.getText()
			if(categoryname.equalsIgnoreCase("Chiller")){
				ProjectConstants.CURRENTVISITING_MAINCATEGORY = categoryname
				ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout["+i+"]").click()

				CommonKeywords.takePicture()
				MobileBuiltInKeywords.verifyElementText(findTestObject('ShopOpen/Chiller/Validate_ChillerScreen', [('package') : ProjectConstants.PACKAGENAME]), 'KPI: Chiller')
				int remarks = ProjectConstants.DRIVER.findElementsByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ListView[1]/*").size();
				for(int j=1; j<= remarks; j++){
					for(int k=1; k<= remarks; k++){
						ScenariosCombination _scenarioscombination = new ScenariosCombination()
						_scenarioscombination.setFirstvisit_scenario(j)
						_scenarioscombination.setOverwrite_scenario(k)
						scenarioscombination.add(_scenarioscombination)
					}
				}
				if(scenarioscombination.size() >= ProjectConstants.SHOP_ATTEMPT){
					ScenariosCombination scenario = scenarioscombination.get((ProjectConstants.SHOP_ATTEMPT-1))
					MobileElement remark = ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout["+scenario.getOverwrite_scenario()+"]/android.widget.TextView[1]")
					String remarktext = remark.getText()
					ProjectConstants.CURRENTVISITING_CATEGORYREMARK = remarktext
					if(remarktext.equalsIgnoreCase("Chiller Not Allocated")){
						ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout["+scenario.getOverwrite_scenario()+"]").click()
						Mobile.callTestCase(findTestCase("Test Cases/ShopOpen/Chiller/OverwriteChillerWithChillerNotAllocated"), null)
					}
					else if(remarktext.equalsIgnoreCase("Shopkeeper did not allow")){
						ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout["+scenario.getOverwrite_scenario()+"]").click()
						Mobile.callTestCase(findTestCase("Test Cases/ShopOpen/Chiller/OverwriteChillerWithSKDNA"), null)
					}
					else{}
				}
				else{
					MobileElement remark = ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout[1]/android.widget.TextView[1]")
					String remarktext = remark.getText()
					ProjectConstants.CURRENTVISITING_CATEGORYREMARK = remarktext
					if(remarktext.equalsIgnoreCase("Chiller Not Allocated")){
						ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout[1]").click()
						Mobile.callTestCase(findTestCase("Test Cases/ShopOpen/Chiller/OverwriteChillerWithChillerNotAllocated"), null)
					}
					else if(remarktext.equalsIgnoreCase("Shopkeeper did not allow")){
						ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout[1]").click()
						Mobile.callTestCase(findTestCase("Test Cases/ShopOpen/Chiller/OverwriteChillerWithSKDNA"), null)
					}
					else{}
				}
			}
			else if(categoryname.equalsIgnoreCase("Chiller Utilization")){
				ProjectConstants.CURRENTVISITING_MAINCATEGORY = categoryname
				ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout["+i+"]").click()

				CommonKeywords.takePicture()
				visitChillerUtilizationOverwritingScenarios()
			}
			else if(categoryname.equalsIgnoreCase("Additional Picture")){
				ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout["+i+"]").click()
				Mobile.callTestCase(findTestCase("Test Cases/ShopOpen/AdditionalPicture/OverwriteAdditionalPicture"), null)
			}
			else if(categoryname.equalsIgnoreCase("POP Application")){
				ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout["+i+"]").click()

				Mobile.callTestCase(findTestCase("Test Cases/ShopOpen/POPApplication/OverwritePOPApplication"), null)
			}
			else if(categoryname.equalsIgnoreCase("Competition Tracking")){
				ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout["+i+"]").click()

				Mobile.callTestCase(findTestCase("Test Cases/ShopOpen/CompetitionTracking/OverwriteCompetitionTracking"), null)
				Mobile.swipe(0, 200, 0, 750)
			}
			else if(categoryname.equalsIgnoreCase("Retailer Remarks")){
				ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout["+i+"]").click()

				Mobile.callTestCase(findTestCase("Test Cases/ShopOpen/RetailerRemarks/OverwriteRetailerRemarks"), null)
			}
			else if(categoryname.equalsIgnoreCase("RTM -Visit Frequency")){
				ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout["+i+"]").click()

				Mobile.callTestCase(findTestCase("Test Cases/ShopOpen/RTMVisitFrequency/OverwriteRTMVisitFrequency"), null)
			}
			else if(categoryname.equalsIgnoreCase("Hanger Availibility")){
				ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout["+i+"]").click()
				Mobile.callTestCase(findTestCase("Test Cases/ShopOpen/HangerAvailability/OverwriteHangerAvailability"), null)
			}
			else if(categoryname.equalsIgnoreCase("Nestrade")){
				ProjectConstants.CURRENTVISITING_MAINCATEGORY = categoryname
				ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout["+i+"]").click()

				CommonKeywords.takePicture()
				MobileBuiltInKeywords.verifyElementText(findTestObject('ShopOpen/RemainingCategories/Validate_MainCategoryDetailScreen', [('package') : ProjectConstants.PACKAGENAME]), 'Display Space Available')
				int remarks = ProjectConstants.DRIVER.findElementsByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ListView[1]/*").size();
				for(int j=1; j<= remarks; j++){
					for(int k=1; k<= remarks; k++){
						ScenariosCombination _scenarioscombination = new ScenariosCombination()
						_scenarioscombination.setFirstvisit_scenario(j)
						_scenarioscombination.setOverwrite_scenario(k)
						scenarioscombination.add(_scenarioscombination)
					}
				}
				if(scenarioscombination.size() >= ProjectConstants.SHOP_ATTEMPT){
					ScenariosCombination scenario = scenarioscombination.get((ProjectConstants.SHOP_ATTEMPT-1))
					MobileElement remark = ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout["+scenario.getOverwrite_scenario()+"]/android.widget.TextView[1]")
					String remarktext = remark.getText()
					ProjectConstants.CURRENTVISITING_CATEGORYREMARK = remarktext
					if(remarktext.equalsIgnoreCase("Display Space Available")){
						ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout["+scenario.getOverwrite_scenario()+"]").click()
						Mobile.callTestCase(findTestCase("Test Cases/ShopOpen/Nestrade/OverwriteNestradeWithDSA"), null)
					}
					else if(remarktext.equalsIgnoreCase("No Space For Display")){
						ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout["+scenario.getOverwrite_scenario()+"]").click()
						Mobile.callTestCase(findTestCase("Test Cases/ShopOpen/Nestrade/OverwriteNestradeWithNSFD"), null)
					}
					else if(remarktext.equalsIgnoreCase("Shopkeeper did not allow")){
						ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout["+scenario.getOverwrite_scenario()+"]").click()
						Mobile.callTestCase(findTestCase("Test Cases/ShopOpen/Nestrade/OverwriteNestradeWithSKDNA"), null)
					}
					else{}
				}
				else{
					MobileElement remark = ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout[1]/android.widget.TextView[1]")
					String remarktext = remark.getText()
					ProjectConstants.CURRENTVISITING_CATEGORYREMARK = remarktext
					if(remarktext.equalsIgnoreCase("Display Space Available")){
						ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout[1]").click()
						Mobile.callTestCase(findTestCase("Test Cases/ShopOpen/Nestrade/OverwriteNestradeWithDSA"), null)
					}
					else if(remarktext.equalsIgnoreCase("No Space For Display")){
						ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout[1]").click()
						Mobile.callTestCase(findTestCase("Test Cases/ShopOpen/Nestrade/OverwriteNestradeWithNSFD"), null)
					}
					else if(remarktext.equalsIgnoreCase("Shopkeeper did not allow")){
						ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout[1]").click()
						Mobile.callTestCase(findTestCase("Test Cases/ShopOpen/Nestrade/OverwriteNestradeWithSKDNA"), null)
					}
					else{}
				}
			}
			else if(categoryname.equalsIgnoreCase("Survey")){
				ProjectConstants.CURRENTVISITING_MAINCATEGORY = categoryname
				ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout["+i+"]").click()
				CommonKeywords.takePicture()
				Mobile.callTestCase(findTestCase("Test Cases/ShopOpen/Survey/OverwriteSurvey"), null)
			}
			else{
				ProjectConstants.CURRENTVISITING_MAINCATEGORY = categoryname
				ProjectConstants.CURRENTVISITING_PRODUCTCATEGORY = categoryname
				ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout["+i+"]").click()

				CommonKeywords.takePicture()
				MobileBuiltInKeywords.verifyElementText(findTestObject('ShopOpen/RemainingCategories/Validate_MainCategoryDetailScreen', [('package') : ProjectConstants.PACKAGENAME]), 'Display Space Available')
				int remarks = ProjectConstants.DRIVER.findElementsByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ListView[1]/*").size();
				for(int j=1; j<= remarks; j++){
					for(int k=1; k<= remarks; k++){
						ScenariosCombination _scenarioscombination = new ScenariosCombination()
						_scenarioscombination.setFirstvisit_scenario(j)
						_scenarioscombination.setOverwrite_scenario(k)
						scenarioscombination.add(_scenarioscombination)
					}
				}
				if(scenarioscombination.size() >= ProjectConstants.SHOP_ATTEMPT){
					ScenariosCombination scenario = scenarioscombination.get((ProjectConstants.SHOP_ATTEMPT-1))
					MobileElement remark = ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout["+scenario.getOverwrite_scenario()+"]/android.widget.TextView[1]")
					String remarktext = remark.getText()
					ProjectConstants.CURRENTVISITING_CATEGORYREMARK = remarktext
					if(remarktext.equalsIgnoreCase("Display Space Available")){
						ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout["+scenario.getOverwrite_scenario()+"]").click()
						Mobile.callTestCase(findTestCase("Test Cases/ShopOpen/RemainingCategories/OverwriteRemainingCategoriesWithDSA"), null)
					}
					else if(remarktext.equalsIgnoreCase("No Space For Display")){
						ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout["+scenario.getOverwrite_scenario()+"]").click()
						Mobile.callTestCase(findTestCase("Test Cases/ShopOpen/RemainingCategories/OverwriteRemainingCategoriesWithNSFD"), null)
					}
					else if(remarktext.equalsIgnoreCase("Shopkeeper did not allow")){
						ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout["+scenario.getOverwrite_scenario()+"]").click()
						Mobile.callTestCase(findTestCase("Test Cases/ShopOpen/RemainingCategories/OverwriteRemainingCategoriesWithSKDNA"), null)
					}
					else{}
				}
				else{
					MobileElement remark = ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout[1]/android.widget.TextView[1]")
					String remarktext = remark.getText()
					ProjectConstants.CURRENTVISITING_CATEGORYREMARK = remarktext
					if(remarktext.equalsIgnoreCase("Display Space Available")){
						ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout[1]").click()
						Mobile.callTestCase(findTestCase("Test Cases/ShopOpen/RemainingCategories/OverwriteRemainingCategoriesWithDSA"), null)
					}
					else if(remarktext.equalsIgnoreCase("No Space For Display")){
						ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout[1]").click()
						Mobile.callTestCase(findTestCase("Test Cases/ShopOpen/RemainingCategories/OverwriteRemainingCategoriesWithNSFD"), null)
					}
					else if(remarktext.equalsIgnoreCase("Shopkeeper did not allow")){
						ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout[1]").click()
						Mobile.callTestCase(findTestCase("Test Cases/ShopOpen/RemainingCategories/OverwriteRemainingCategoriesWithSKDNA"), null)
					}
					else{}
				}
			}
		}
		while(true){
			index = ProjectConstants.DRIVER.findElementsByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ListView[1]/*").size()
			MobileElement lastitembeforeswipe = ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout["+index+"]/android.widget.TextView[1]")
			String lastitemnamebeforeswipe = lastitembeforeswipe.getText()
			Mobile.swipe(0, 293, 0, 200)
			index = ProjectConstants.DRIVER.findElementsByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ListView[1]/*").size()
			MobileElement lastitemafterswipe = ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout["+index+"]/android.widget.TextView[1]")
			String lastitemnameafterswipe = lastitemafterswipe.getText()
			if(lastitemnamebeforeswipe.equalsIgnoreCase(lastitemnameafterswipe)){
				break
			}
			else if(lastitemnameafterswipe.equalsIgnoreCase("Chiller")){
				lastvisitedcategory = lastitemnameafterswipe
				ProjectConstants.CURRENTVISITING_MAINCATEGORY = lastitemnameafterswipe
				ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout["+index+"]").click()

				CommonKeywords.takePicture()
				MobileBuiltInKeywords.verifyElementText(findTestObject('ShopOpen/Chiller/Validate_ChillerScreen', [('package') : ProjectConstants.PACKAGENAME]), 'KPI: Chiller')
				int remarks = ProjectConstants.DRIVER.findElementsByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ListView[1]/*").size();
				for(int j=1; j<= remarks; j++){
					for(int k=1; k<= remarks; k++){
						ScenariosCombination _scenarioscombination = new ScenariosCombination()
						_scenarioscombination.setFirstvisit_scenario(j)
						_scenarioscombination.setOverwrite_scenario(k)
						scenarioscombination.add(_scenarioscombination)
					}
				}
				if(scenarioscombination.size() >= ProjectConstants.SHOP_ATTEMPT){
					ScenariosCombination scenario = scenarioscombination.get((ProjectConstants.SHOP_ATTEMPT-1))
					MobileElement remark = ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout["+scenario.getOverwrite_scenario()+"]/android.widget.TextView[1]")
					String remarktext = remark.getText()
					ProjectConstants.CURRENTVISITING_CATEGORYREMARK = remarktext
					if(remarktext.equalsIgnoreCase("Chiller Not Allocated")){
						ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout["+scenario.getOverwrite_scenario()+"]").click()
						Mobile.callTestCase(findTestCase("Test Cases/ShopOpen/Chiller/OverwriteChillerWithChillerNotAllocated"), null)
					}
					else if(remarktext.equalsIgnoreCase("Shopkeeper did not allow")){
						ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout["+scenario.getOverwrite_scenario()+"]").click()
						Mobile.callTestCase(findTestCase("Test Cases/ShopOpen/Chiller/OverwriteChillerWithSKDNA"), null)
					}
					else{}
				}
				else{
					MobileElement remark = ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout[1]/android.widget.TextView[1]")
					String remarktext = remark.getText()
					ProjectConstants.CURRENTVISITING_CATEGORYREMARK = remarktext
					if(remarktext.equalsIgnoreCase("Chiller Not Allocated")){
						ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout[1]").click()
						Mobile.callTestCase(findTestCase("Test Cases/ShopOpen/Chiller/OverwriteChillerWithChillerNotAllocated"), null)
					}
					else if(remarktext.equalsIgnoreCase("Shopkeeper did not allow")){
						ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout[1]").click()
						Mobile.callTestCase(findTestCase("Test Cases/ShopOpen/Chiller/OverwriteChillerWithSKDNA"), null)
					}
					else{}
				}
			}
			else if(lastitemnameafterswipe.equalsIgnoreCase("Chiller Utilization")){
				lastvisitedcategory = lastitemnameafterswipe
				ProjectConstants.CURRENTVISITING_MAINCATEGORY = lastitemnameafterswipe
				ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout["+index+"]").click()

				CommonKeywords.takePicture()
				visitChillerUtilizationOverwritingScenarios()
			}
			else if(lastitemnameafterswipe.equalsIgnoreCase("Additional Picture")){
				lastvisitedcategory = lastitemnameafterswipe
				ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout["+index+"]").click()
				Mobile.callTestCase(findTestCase("Test Cases/ShopOpen/AdditionalPicture/OverwriteAdditionalPicture"), null)
			}
			else if(lastitemnameafterswipe.equalsIgnoreCase("Competition Tracking")){
				lastvisitedcategory = lastitemnameafterswipe
				ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout["+index+"]").click()

				Mobile.callTestCase(findTestCase("Test Cases/ShopOpen/CompetitionTracking/OverwriteCompetitionTracking"), null)
			}
			else if(lastitemnameafterswipe.equalsIgnoreCase("Retailer Remarks")){
				lastvisitedcategory = lastitemnameafterswipe
				ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout["+index+"]").click()

				Mobile.callTestCase(findTestCase("Test Cases/ShopOpen/RetailerRemarks/OverwriteRetailerRemarks"), null)
			}
			else if(lastitemnameafterswipe.equalsIgnoreCase("RTM -Visit Frequency")){
				lastvisitedcategory = lastitemnameafterswipe
				ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout["+index+"]").click()

				Mobile.callTestCase(findTestCase("Test Cases/ShopOpen/RTMVisitFrequency/OverwriteRTMVisitFrequency"), null)
			}
			else if(lastitemnameafterswipe.equalsIgnoreCase("POP Application")){
				lastvisitedcategory = lastitemnameafterswipe
				ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout["+index+"]").click()

				Mobile.callTestCase(findTestCase("Test Cases/ShopOpen/POPApplication/OverwritePOPApplication"), null)
			}
			else if(lastitemnameafterswipe.equalsIgnoreCase("Hanger Availibility")){
				lastvisitedcategory = lastitemnameafterswipe
				ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout["+index+"]").click()
				Mobile.callTestCase(findTestCase("Test Cases/ShopOpen/HangerAvailability/OverwriteHangerAvailability"), null)
			}
			else if(lastitemnameafterswipe.equalsIgnoreCase("Nestrade")){
				lastvisitedcategory = lastitemnameafterswipe
				ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout["+index+"]").click()

				CommonKeywords.takePicture()
				MobileBuiltInKeywords.verifyElementText(findTestObject('ShopOpen/RemainingCategories/Validate_MainCategoryDetailScreen', [('package') : ProjectConstants.PACKAGENAME]), 'Display Space Available')
				int remarks = ProjectConstants.DRIVER.findElementsByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ListView[1]/*").size();
				for(int j=1; j<= remarks; j++){
					for(int k=1; k<= remarks; k++){
						ScenariosCombination _scenarioscombination = new ScenariosCombination()
						_scenarioscombination.setFirstvisit_scenario(j)
						_scenarioscombination.setOverwrite_scenario(k)
						scenarioscombination.add(_scenarioscombination)
					}
				}
				if(scenarioscombination.size() >= ProjectConstants.SHOP_ATTEMPT){
					ScenariosCombination scenario = scenarioscombination.get((ProjectConstants.SHOP_ATTEMPT-1))
					MobileElement remark = ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout["+scenario.getOverwrite_scenario()+"]/android.widget.TextView[1]")
					String remarktext = remark.getText()
					ProjectConstants.CURRENTVISITING_CATEGORYREMARK = remarktext
					if(remarktext.equalsIgnoreCase("Display Space Available")){
						ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout["+scenario.getOverwrite_scenario()+"]").click()
						Mobile.callTestCase(findTestCase("Test Cases/ShopOpen/Nestrade/OverwriteNestradeWithDSA"), null)
					}
					else if(remarktext.equalsIgnoreCase("No Space For Display")){
						ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout["+scenario.getOverwrite_scenario()+"]").click()
						Mobile.callTestCase(findTestCase("Test Cases/ShopOpen/Nestrade/OverwriteNestradeWithNSFD"), null)
					}
					else if(remarktext.equalsIgnoreCase("Shopkeeper did not allow")){
						ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout["+scenario.getOverwrite_scenario()+"]").click()
						Mobile.callTestCase(findTestCase("Test Cases/ShopOpen/Nestrade/OverwriteNestradeWithSKDNA"), null)
					}
					else{}
				}
				else{
					MobileElement remark = ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout[1]/android.widget.TextView[1]")
					String remarktext = remark.getText()
					ProjectConstants.CURRENTVISITING_CATEGORYREMARK = remarktext
					if(remarktext.equalsIgnoreCase("Display Space Available")){
						ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout[1]").click()
						Mobile.callTestCase(findTestCase("Test Cases/ShopOpen/Nestrade/OverwriteNestradeWithDSA"), null)
					}
					else if(remarktext.equalsIgnoreCase("No Space For Display")){
						ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout[1]").click()
						Mobile.callTestCase(findTestCase("Test Cases/ShopOpen/Nestrade/OverwriteNestradeWithNSFD"), null)
					}
					else if(remarktext.equalsIgnoreCase("Shopkeeper did not allow")){
						ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout[1]").click()
						Mobile.callTestCase(findTestCase("Test Cases/ShopOpen/Nestrade/OverwriteNestradeWithSKDNA"), null)
					}
					else{}
				}
			}
			else if(lastitemnameafterswipe.equalsIgnoreCase("Survey")){
				lastvisitedcategory = lastitemnameafterswipe
				ProjectConstants.CURRENTVISITING_MAINCATEGORY = lastitemnameafterswipe
				ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout["+index+"]").click()
				CommonKeywords.takePicture()
				Mobile.callTestCase(findTestCase("Test Cases/ShopOpen/Survey/OverwriteSurvey"), null)
			}
			else{
				lastvisitedcategory = lastitemnameafterswipe
				ProjectConstants.CURRENTVISITING_MAINCATEGORY = lastitemnameafterswipe
				ProjectConstants.CURRENTVISITING_PRODUCTCATEGORY = lastitemnameafterswipe
				ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout["+index+"]").click()

				CommonKeywords.takePicture()
				MobileBuiltInKeywords.verifyElementText(findTestObject('ShopOpen/RemainingCategories/Validate_MainCategoryDetailScreen', [('package') : ProjectConstants.PACKAGENAME]), 'Display Space Available')
				int remarks = ProjectConstants.DRIVER.findElementsByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ListView[1]/*").size();
				for(int j=1; j<= remarks; j++){
					for(int k=1; k<= remarks; k++){
						ScenariosCombination _scenarioscombination = new ScenariosCombination()
						_scenarioscombination.setFirstvisit_scenario(j)
						_scenarioscombination.setOverwrite_scenario(k)
						scenarioscombination.add(_scenarioscombination)
					}
				}
				if(scenarioscombination.size() >= ProjectConstants.SHOP_ATTEMPT){
					ScenariosCombination scenario = scenarioscombination.get((ProjectConstants.SHOP_ATTEMPT-1))
					MobileElement remark = ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout["+scenario.getOverwrite_scenario()+"]/android.widget.TextView[1]")
					String remarktext = remark.getText()
					ProjectConstants.CURRENTVISITING_CATEGORYREMARK = remarktext
					if(remarktext.equalsIgnoreCase("Display Space Available")){
						ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout["+scenario.getOverwrite_scenario()+"]").click()
						Mobile.callTestCase(findTestCase("Test Cases/ShopOpen/RemainingCategories/OverwriteRemainingCategoriesWithDSA"), null)
					}
					else if(remarktext.equalsIgnoreCase("No Space For Display")){
						ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout["+scenario.getOverwrite_scenario()+"]").click()
						Mobile.callTestCase(findTestCase("Test Cases/ShopOpen/RemainingCategories/OverwriteRemainingCategoriesWithNSFD"), null)
					}
					else if(remarktext.equalsIgnoreCase("Shopkeeper did not allow")){
						ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout["+scenario.getOverwrite_scenario()+"]").click()
						Mobile.callTestCase(findTestCase("Test Cases/ShopOpen/RemainingCategories/OverwriteRemainingCategoriesWithSKDNA"), null)
					}
					else{}
				}
				else{
					MobileElement remark = ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout[1]/android.widget.TextView[1]")
					String remarktext = remark.getText()
					ProjectConstants.CURRENTVISITING_CATEGORYREMARK = remarktext
					if(remarktext.equalsIgnoreCase("Display Space Available")){
						ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout[1]").click()
						Mobile.callTestCase(findTestCase("Test Cases/ShopOpen/RemainingCategories/OverwriteRemainingCategoriesWithDSA"), null)
					}
					else if(remarktext.equalsIgnoreCase("No Space For Display")){
						ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout[1]").click()
						Mobile.callTestCase(findTestCase("Test Cases/ShopOpen/RemainingCategories/OverwriteRemainingCategoriesWithNSFD"), null)
					}
					else if(remarktext.equalsIgnoreCase("Shopkeeper did not allow")){
						ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout[1]").click()
						Mobile.callTestCase(findTestCase("Test Cases/ShopOpen/RemainingCategories/OverwriteRemainingCategoriesWithSKDNA"), null)
					}
					else{}
				}
			}
			while(true){
				Mobile.swipe(0, 293, 0, 200)
				index = ProjectConstants.DRIVER.findElementsByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ListView[1]/*").size()
				MobileElement product = ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout["+index+"]/android.widget.TextView[1]")
				String productname = product.getText()
				if(productname.equalsIgnoreCase(lastvisitedcategory)){
					break
				}
			}
		}
	}
	def visitChillerUtilizationOverwritingScenarios(){
		if(ProjectConstants.SHOP_ATTEMPT == 1){
			Mobile.callTestCase(findTestCase("Test Cases/ShopOpen/ChillerUtilization/VisitChillerScenarios/OverwriteChillerUtilizationWith_ChillerNotAvailable"), null)
		}
		else if(ProjectConstants.SHOP_ATTEMPT == 2){
			Mobile.callTestCase(findTestCase("Test Cases/ShopOpen/ChillerUtilization/VisitChillerScenarios/OverwriteChillerUtilizationWith_ChillerNeedMaintainence"), null)
		}
		else if(ProjectConstants.SHOP_ATTEMPT == 3){
			Mobile.callTestCase(findTestCase("Test Cases/ShopOpen/ChillerUtilization/VisitChillerScenarios/OverwriteChillerUtilizationWith_ChillerAvailable"), null)
		}
		else if(ProjectConstants.SHOP_ATTEMPT == 4){
			Mobile.callTestCase(findTestCase("Test Cases/ShopOpen/ChillerUtilization/VisitChillerScenarios/OverwriteChillerUtilizationWith_ShopKeeperDidNotAllow"), null)
		}
		else if(ProjectConstants.SHOP_ATTEMPT == 5){
			Mobile.callTestCase(findTestCase("Test Cases/ShopOpen/ChillerUtilization/VisitChillerScenarios/OverwriteChillerUtilizationWith_ChillerAvailable"), null)
		}
		else if(ProjectConstants.SHOP_ATTEMPT == 6){
			Mobile.callTestCase(findTestCase("Test Cases/ShopOpen/ChillerUtilization/VisitChillerScenarios/OverwriteChillerUtilizationWith_ChillerAvailable"), null)
		}
		else if(ProjectConstants.SHOP_ATTEMPT == 7){
			Mobile.callTestCase(findTestCase("Test Cases/ShopOpen/ChillerUtilization/VisitChillerScenarios/OverwriteChillerUtilizationWith_ChillerAvailable"), null)
		}
		else if(ProjectConstants.SHOP_ATTEMPT == 8){
			Mobile.callTestCase(findTestCase("Test Cases/ShopOpen/ChillerUtilization/VisitChillerScenarios/OverwriteChillerUtilizationWith_ChillerAvailable"), null)
		}
		else{
			Mobile.callTestCase(findTestCase("Test Cases/ShopOpen/ChillerUtilization/VisitChillerScenarios/OverwriteChillerUtilizationWith_ChillerAvailable"), null)
		}
	}
}
