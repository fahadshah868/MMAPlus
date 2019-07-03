package com.ct.qa.keywords

import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import java.time.Duration

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
import io.appium.java_client.TouchAction
import MobileBuiltInKeywords as Mobile
import WSBuiltInKeywords as WS
import WebUiBuiltInKeywords as WebUI

public class CategoryVisitingScenariosWithOverwritePopUpKeywords{


	/********************************************************************
	 VISIT SHOP MAIN CATEGORIES WITH OVERWRITE SCENARIOS
	 *******************************************************************/

	def visitShopCategoriesWithOverwritingScenarios(MobileElement category){
		String categoryname = category.getText()
		ArrayList<ScenariosCombination> scenarioscombination = new ArrayList<ScenariosCombination>()
		if(categoryname.equalsIgnoreCase("Chiller")){
			ProjectConstants.CURRENTVISITING_MAINCATEGORY = categoryname
			category.click()
			CommonKeywords.visitPopUpForOverwriting()
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
			category.click()
			CommonKeywords.visitPopUpForOverwriting()
			CommonKeywords.takePicture()
			visitChillerUtilizationOverwriteScenarios()
		}
		else if(categoryname.equalsIgnoreCase("Additional Picture")){
			category.click()
			Mobile.callTestCase(findTestCase("Test Cases/ShopOpen/AdditionalPicture/OverwriteAdditionalPicture"), null)
		}
		else if(categoryname.equalsIgnoreCase("POP Application")){
			category.click()
			CommonKeywords.visitPopUpForOverwriting()
			Mobile.callTestCase(findTestCase("Test Cases/ShopOpen/POPApplication/OverwritePOPApplication"), null)
		}
		else if(categoryname.equalsIgnoreCase("Competition Tracking")){
			//			category.click()
			//			CommonKeywords.visitPopUpForOverwriting()
			//			Mobile.callTestCase(findTestCase("Test Cases/ShopOpen/CompetitionTracking/OverwriteCompetitionTracking"), null)
			Mobile.swipe(0, 200, 0, 750)
		}
		else if(categoryname.equalsIgnoreCase("Retailer Remarks")){
			category.click()
			CommonKeywords.visitPopUpForOverwriting()
			Mobile.callTestCase(findTestCase("Test Cases/ShopOpen/RetailerRemarks/OverwriteRetailerRemarks"), null)
		}
		else if(categoryname.equalsIgnoreCase("RTM -Visit Frequency")){
			category.click()
			CommonKeywords.visitPopUpForOverwriting()
			Mobile.callTestCase(findTestCase("Test Cases/ShopOpen/RTMVisitFrequency/OverwriteRTMVisitFrequency"), null)
		}
		else if(categoryname.equalsIgnoreCase("Hanger Availibility")){
			category.click()
			Mobile.callTestCase(findTestCase("Test Cases/ShopOpen/HangerAvailability/OverwriteHangerAvailability"), null)
		}
		else if(categoryname.equalsIgnoreCase("Nestrade")){
			ProjectConstants.CURRENTVISITING_MAINCATEGORY = categoryname
			category.click()
			CommonKeywords.visitPopUpForOverwriting()
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
			category.click()
			CommonKeywords.takePicture()
			Mobile.callTestCase(findTestCase("ShopOpen/Survey/OverwriteSurveyCategories"), null)
		}
		else{
			ProjectConstants.CURRENTVISITING_MAINCATEGORY = categoryname
			ProjectConstants.CURRENTVISITING_PRODUCTCATEGORY = categoryname
			category.click()
			CommonKeywords.visitPopUpForOverwriting()
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
	@Keyword
	def visitShopCategoriesWithOverwritingScenarios(){
		TouchAction touchaction = new TouchAction(ProjectConstants.DRIVER)
		MobileElement channel = ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.RelativeLayout[1]/android.widget.TextView[1]")
		ProjectConstants.CURRENTVISITING_SHOPCHANNEL = channel.getText()
		Mobile.swipe(0, 200, 0, 750)
		Mobile.swipe(0, 200, 0, 750)
		int index, swipecounter
		String visitedcategory
		MobileElement listcontainer = ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ListView[1]")
		ArrayList<MobileElement> categories = listcontainer.findElementsByClassName("android.widget.TextView")
		for(int i=0; i< categories.size(); i++){
			MobileElement category = categories.get(i)
			visitedcategory = category.getText()
			visitShopCategoriesWithOverwritingScenarios(category)
		}
		touchaction.press(0, 230).waitAction(Duration.ofMillis(500)).moveTo(0, 200).release().perform()
		Thread.sleep(500)
		while(true){
			swipecounter++
			categories = listcontainer.findElementsByClassName("android.widget.TextView")
			MobileElement lastitembeforeswipe = categories.get((categories.size()-1))
			String lastitemnamebeforeswipe = lastitembeforeswipe.getText()
			index = categories.size()
			MobileElement startpoint = ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout["+index+"]")
			MobileElement endpoint = ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout[1]")
			touchaction.longPress(startpoint).waitAction(Duration.ofMillis(500)).moveTo(endpoint).release().perform()
			Thread.sleep(500)
			categories = listcontainer.findElementsByClassName("android.widget.TextView")
			MobileElement lastitemafterswipe = categories.get((categories.size()-1))
			String lastitemnameafterswipe = lastitemafterswipe.getText()
			if(lastitemnamebeforeswipe.equalsIgnoreCase(lastitemnameafterswipe)){
				break
			}
			else{
				categories = listcontainer.findElementsByClassName("android.widget.TextView")
				for(int i=0; i< categories.size(); i++){
					MobileElement category = categories.get(i)
					//if current element match with last visited element
					if(visitedcategory.equalsIgnoreCase(category.getText())){
						//if current element is not a last element of screen
						if(i < (categories.size()-1)){
							category = categories.get((i+1))
							visitedcategory = category.getText()
							visitShopCategoriesWithOverwritingScenarios(category)
							//swipe to last visited item screen view
							touchaction.press(0, 230).waitAction(Duration.ofMillis(500)).moveTo(0, 200).release().perform()
							Thread.sleep(500)
							for(int j=1; j<= swipecounter; j++){
								categories = listcontainer.findElementsByClassName("android.widget.TextView")
								index = categories.size()
								startpoint = ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout["+index+"]")
								endpoint = ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout[1]")
								touchaction.longPress(startpoint).waitAction(Duration.ofMillis(500)).moveTo(endpoint).release().perform()
								Thread.sleep(500)
							}
						}
					}
				}
			}
		}
	}
	def visitChillerUtilizationOverwriteScenarios(){
		if(ProjectConstants.SHOP_ATTEMPT == 1){
			Mobile.callTestCase(findTestCase("ShopOpen/ChillerUtilization/VisitChillerScenarios_WithCategeoryLevel/OverwriteChillerUtilizationWith_ChillerNotAvailable"), null)
		}
		else if(ProjectConstants.SHOP_ATTEMPT == 2){
			Mobile.callTestCase(findTestCase("ShopOpen/ChillerUtilization/VisitChillerScenarios_WithCategeoryLevel/OverwriteChillerUtilizationWith_ChillerNeedMaintainence"), null)
		}
		else if(ProjectConstants.SHOP_ATTEMPT == 3){
			Mobile.callTestCase(findTestCase("ShopOpen/ChillerUtilization/VisitChillerScenarios_WithCategeoryLevel/OverwriteChillerUtilizationWith_ChillerAvailable"), null)
		}
		//		else if(ProjectConstants.SHOP_ATTEMPT == 4){
		//			Mobile.callTestCase(findTestCase("ShopOpen/ChillerUtilization/VisitChillerScenarios_WithCategeoryLevel/OverwriteChillerUtilizationWith_ShopKeeperDidNotAllow"), null)
		//		}
		//		else if(ProjectConstants.SHOP_ATTEMPT == 5){
		//			Mobile.callTestCase(findTestCase("ShopOpen/ChillerUtilization/VisitChillerScenarios_WithCategeoryLevel/OverwriteChillerUtilizationWith_ChillerAvailable"), null)
		//		}
		else if(ProjectConstants.SHOP_ATTEMPT == 4){
			Mobile.callTestCase(findTestCase("ShopOpen/ChillerUtilization/VisitChillerScenarios_WithCategeoryLevel/OverwriteChillerUtilizationWith_ChillerAvailable"), null)
		}
		else if(ProjectConstants.SHOP_ATTEMPT == 5){
			Mobile.callTestCase(findTestCase("ShopOpen/ChillerUtilization/VisitChillerScenarios_WithCategeoryLevel/OverwriteChillerUtilizationWith_ChillerAvailable"), null)
		}
		else if(ProjectConstants.SHOP_ATTEMPT == 6){
			Mobile.callTestCase(findTestCase("ShopOpen/ChillerUtilization/VisitChillerScenarios_WithCategeoryLevel/OverwriteChillerUtilizationWith_ChillerAvailable"), null)
		}
		else if(ProjectConstants.SHOP_ATTEMPT == 7){
			Mobile.callTestCase(findTestCase("Test Cases/ShopOpen/ChillerUtilization/VisitChillerScenarios_WithCategeoryLevel/OverwriteChillerUtilizationWith_ChillerTypeNotAvailable"), null)
		}
		else{
			Mobile.callTestCase(findTestCase("ShopOpen/ChillerUtilization/VisitChillerScenarios_WithCategeoryLevel/OverwriteChillerUtilizationWith_ChillerAvailable"), null)
		}
	}
}
