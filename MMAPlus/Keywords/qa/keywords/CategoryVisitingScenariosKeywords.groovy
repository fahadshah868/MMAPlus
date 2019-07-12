package qa.keywords

import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import java.time.Duration

import qa.constants.ProjectConstants
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
import qa.struct.ScenariosCombination
import qa.struct.UnmatchedItems

import internal.GlobalVariable
import io.appium.java_client.MobileElement
import io.appium.java_client.TouchAction
import MobileBuiltInKeywords as Mobile
import WSBuiltInKeywords as WS
import WebUiBuiltInKeywords as WebUI

public class CategoryVisitingScenariosKeywords{

	/********************************************************************
	 VISIT SHOP MAIN CATEGORIES WITH DATA VERFICATIONS
	 *******************************************************************/

	def visitShopCategoriesWithDataVerification(MobileElement category){
		String categoryname = category.getText()
		if(categoryname.equalsIgnoreCase("Chiller")){
			ProjectConstants.CURRENTVISITING_MAINCATEGORY = categoryname
			category.click()
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
			category.click()
			CommonKeywords.takePicture()
			Mobile.callTestCase(findTestCase("ShopOpen/ChillerUtilization/VisitChillerScenarios_WithCategeoryLevel/VisitChillerUtilizationWithDataVerification"), null)
		}
		else if(categoryname.equalsIgnoreCase("Additional Picture")){
			category.click()
			Mobile.callTestCase(findTestCase("Test Cases/ShopOpen/AdditionalPicture/VisitAdditionalPicture"), null)
		}
		else if(categoryname.equalsIgnoreCase("POP Application")){
			category.click()
			Mobile.callTestCase(findTestCase("Test Cases/ShopOpen/POPApplication/OverwritePOPApplication"), null)
		}
		else if(categoryname.equalsIgnoreCase("Competition Tracking")){
			category.click()
			Mobile.callTestCase(findTestCase("Test Cases/ShopOpen/CompetitionTracking/VisitCompetitionTracking"), null)
			Mobile.swipe(0, 200, 0, 750)
		}
		else if(categoryname.equalsIgnoreCase("Retailer Remarks")){
			category.click()
			Mobile.callTestCase(findTestCase("Test Cases/ShopOpen/RetailerRemarks/VisitRetailerRemarks"), null)
		}
		else if(categoryname.equalsIgnoreCase("RTM -Visit Frequency")){
			category.click()
			Mobile.callTestCase(findTestCase("Test Cases/ShopOpen/RTMVisitFrequency/VisitRTMVisitFrequency"), null)
		}
		else if(categoryname.equalsIgnoreCase("Hanger Availibility")){
			category.click()
			Mobile.callTestCase(findTestCase("Test Cases/ShopOpen/HangerAvailability/VisitHangerAvailability"), null)
		}
		else if(categoryname.equalsIgnoreCase("Nestrade")){
			ProjectConstants.CURRENTVISITING_MAINCATEGORY = categoryname
			category.click()
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
			category.click()
			CommonKeywords.takePicture()
			Mobile.callTestCase(findTestCase("Test Cases/ShopOpen/Survey/visitSurveyCategories"), null)
		}
		else if(categoryname.equalsIgnoreCase("Expiry Issue")){
			ProjectConstants.CURRENTVISITING_MAINCATEGORY = categoryname
			category.click()
			Mobile.callTestCase(findTestCase("Test Cases/ExpiryIssue/VisitExpiryIssue"), null)
		}
		else{
			ProjectConstants.CURRENTVISITING_MAINCATEGORY = categoryname
			ProjectConstants.CURRENTVISITING_PRODUCTCATEGORY = categoryname
			category.click()
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
	@Keyword
	def visitShopCategoriesWithDataVerification(){
		TouchAction touchaction = new TouchAction(ProjectConstants.DRIVER)
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
		int index, swipecounter
		String visitedcategory
		MobileElement listcontainer = ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ListView[1]")
		ArrayList<MobileElement> categories = listcontainer.findElementsByClassName("android.widget.TextView")
		for(int i=0; i< categories.size(); i++){
			MobileElement category = categories.get(i)
			visitedcategory = category.getText()
			visitShopCategoriesWithDataVerification(category)
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
							visitShopCategoriesWithDataVerification(category)
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

	/********************************************************************
	 VISIT SHOP MAIN CATEGORIES WITH OVERWRITE SCENARIOS
	 *******************************************************************/

	def visitShopCategoriesWithOverwriteScenarios(MobileElement category){
		String categoryname = category.getText()
		ArrayList<ScenariosCombination> scenarioscombination = new ArrayList<ScenariosCombination>()
		if(categoryname.equalsIgnoreCase("Chiller")){
			ProjectConstants.CURRENTVISITING_MAINCATEGORY = categoryname
			category.click()
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
			category.click()
			CommonKeywords.takePicture()
			ProjectConstants.CHILLER_OVERWRITEPOPUP = "no"
			visitChillerUtilizationOverwriteScenarios()
		}
		else if(categoryname.equalsIgnoreCase("Additional Picture")){
			category.click()
			Mobile.callTestCase(findTestCase("Test Cases/ShopOpen/AdditionalPicture/VisitAdditionalPicture"), null)
		}
		else if(categoryname.equalsIgnoreCase("POP Application")){
			category.click()
			Mobile.callTestCase(findTestCase("Test Cases/ShopOpen/POPApplication/OverwritePOPApplication"), null)
		}
		else if(categoryname.equalsIgnoreCase("Competition Tracking")){
			//			category.click()
			//			Mobile.callTestCase(findTestCase("Test Cases/ShopOpen/CompetitionTracking/VisitCompetitionTracking"), null)
			Mobile.swipe(0, 200, 0, 750)
		}
		else if(categoryname.equalsIgnoreCase("Retailer Remarks")){
			category.click()
			Mobile.callTestCase(findTestCase("Test Cases/ShopOpen/RetailerRemarks/VisitRetailerRemarks"), null)
		}
		else if(categoryname.equalsIgnoreCase("RTM -Visit Frequency")){
			category.click()
			Mobile.callTestCase(findTestCase("Test Cases/ShopOpen/RTMVisitFrequency/VisitRTMVisitFrequency"), null)
		}
		else if(categoryname.equalsIgnoreCase("Hanger Availibility")){
			category.click()
			Mobile.callTestCase(findTestCase("Test Cases/ShopOpen/HangerAvailability/VisitHangerAvailability"), null)
		}
		else if(categoryname.equalsIgnoreCase("Nestrade")){
			ProjectConstants.CURRENTVISITING_MAINCATEGORY = categoryname
			category.click()
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
			category.click()
			CommonKeywords.takePicture()
			Mobile.callTestCase(findTestCase("ShopOpen/Survey/visitSurveyCategories"), null)
		}
		else{
			ProjectConstants.CURRENTVISITING_MAINCATEGORY = categoryname
			ProjectConstants.CURRENTVISITING_PRODUCTCATEGORY = categoryname
			category.click()
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
	@Keyword
	def visitShopCategoriesWithOverwriteScenarios(){
		TouchAction touchaction = new TouchAction(ProjectConstants.DRIVER)
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
		int index, swipecounter
		String visitedcategory
		MobileElement listcontainer = ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ListView[1]")
		ArrayList<MobileElement> categories = listcontainer.findElementsByClassName("android.widget.TextView")
		for(int i=0; i< categories.size(); i++){
			MobileElement category = categories.get(i)
			visitedcategory = category.getText()
			visitShopCategoriesWithOverwriteScenarios(category)
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
							visitShopCategoriesWithOverwriteScenarios(category)
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

	/********************************************************************
	 VISIT SHOP MAIN CATEGORIES WITH OVERWRITING SCENARIOS
	 *******************************************************************/

	def visitShopCategoriesWithOverwritingScenarios(MobileElement category){
		String categoryname = category.getText()
		ArrayList<ScenariosCombination> scenarioscombination = new ArrayList<ScenariosCombination>()
		if(categoryname.equalsIgnoreCase("Chiller")){
			ProjectConstants.CURRENTVISITING_MAINCATEGORY = categoryname
			category.click()
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
			CommonKeywords.takePicture()
			ProjectConstants.CHILLER_OVERWRITEPOPUP = "no"
			visitChillerUtilizationOverwritingScenarios()
		}
		else if(categoryname.equalsIgnoreCase("Additional Picture")){
			category.click()
			Mobile.callTestCase(findTestCase("Test Cases/ShopOpen/AdditionalPicture/OverwriteAdditionalPicture"), null)
		}
		else if(categoryname.equalsIgnoreCase("POP Application")){
			category.click()
			Mobile.callTestCase(findTestCase("Test Cases/ShopOpen/POPApplication/OverwritePOPApplication"), null)
		}
		else if(categoryname.equalsIgnoreCase("Competition Tracking")){
			//			category.click()
			//			Mobile.callTestCase(findTestCase("Test Cases/ShopOpen/CompetitionTracking/OverwriteCompetitionTracking"), null)
			Mobile.swipe(0, 200, 0, 750)
		}
		else if(categoryname.equalsIgnoreCase("Retailer Remarks")){
			category.click()
			Mobile.callTestCase(findTestCase("Test Cases/ShopOpen/RetailerRemarks/OverwriteRetailerRemarks"), null)
		}
		else if(categoryname.equalsIgnoreCase("RTM -Visit Frequency")){
			category.click()
			Mobile.callTestCase(findTestCase("Test Cases/ShopOpen/RTMVisitFrequency/OverwriteRTMVisitFrequency"), null)
		}
		else if(categoryname.equalsIgnoreCase("Hanger Availibility")){
			category.click()
			Mobile.callTestCase(findTestCase("Test Cases/ShopOpen/HangerAvailability/OverwriteHangerAvailability"), null)
		}
		else if(categoryname.equalsIgnoreCase("Nestrade")){
			ProjectConstants.CURRENTVISITING_MAINCATEGORY = categoryname
			category.click()
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

	/********************************************************************
	 VISIT SHOP MAIN CATEGORIES WITH Chiller Level OVERWRITE SCENARIOS
	 *******************************************************************/
	@Keyword
	def visitShopCategoriesWith_ChillerLevelOverwriteScenarios(){
		ArrayList<ScenariosCombination> scenarioscombination = new ArrayList<ScenariosCombination>()
		MobileElement channel = ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.RelativeLayout[1]/android.widget.TextView[1]")
		ProjectConstants.CURRENTVISITING_SHOPCHANNEL = channel.getText()
		Mobile.swipe(0, 200, 0, 750)
		Mobile.swipe(0, 200, 0, 750)
		int index = 0
		String lastvisitedcategory = ""
		int totalcategories = ProjectConstants.DRIVER.findElementsByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ListView[1]/*").size()
		for(int i=1; i<=1; i++){
			MobileElement category = ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout["+i+"]/android.widget.TextView[1]")
			String categoryname = category.getText()
			if(categoryname.equalsIgnoreCase("Chiller Utilization")){
				ProjectConstants.CURRENTVISITING_MAINCATEGORY = categoryname
				ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout["+i+"]").click()
				CommonKeywords.takePicture()
				ProjectConstants.CHILLER_OVERWRITEPOPUP = "no"
				visitChillerUtilization_WithChillerLevel_OverwriteScenarios()
				ProjectConstants.SCENARIO = "overwrite"
				ProjectConstants.CHILLER_OVERWRITEPOPUP = "yes"
				visitChillerUtilizationOverwritingScenarios()
			}
		}
	}

	/********************************************************************
	 VISIT CHILLER UTILIZATION OVERWRITE SCENARIOS
	 *******************************************************************/

	//overwrite scenarios
	def visitChillerUtilizationOverwriteScenarios(){
		if(ProjectConstants.SHOP_ATTEMPT == 1){
			Mobile.callTestCase(findTestCase("ShopOpen/ChillerUtilization/VisitChillerScenarios_WithCategeoryLevel/VisitChillerUtilizationWith_ChillerAvailable"), null)
		}
		else if(ProjectConstants.SHOP_ATTEMPT == 2){
			Mobile.callTestCase(findTestCase("ShopOpen/ChillerUtilization/VisitChillerScenarios_WithCategeoryLevel/VisitChillerUtilizationWith_ChillerRemovedForMaintenance"), null)
		}
		else if(ProjectConstants.SHOP_ATTEMPT == 3){
			Mobile.callTestCase(findTestCase("ShopOpen/ChillerUtilization/VisitChillerScenarios_WithCategeoryLevel/VisitChillerUtilizationWith_ChillerTypeNotAvailable"), null)
		}
		//		else if(ProjectConstants.SHOP_ATTEMPT == 4){
		//			Mobile.callTestCase(findTestCase("ShopOpen/ChillerUtilization/VisitChillerScenarios_WithCategeoryLevel/VisitChillerUtilizationWith_ShopKeeperDidNotAllow"), null)
		//		}
		//		else if(ProjectConstants.SHOP_ATTEMPT == 5){
		//			Mobile.callTestCase(findTestCase("ShopOpen/ChillerUtilization/VisitChillerScenarios_WithCategeoryLevel/VisitChillerUtilizationWith_ShopKeeperDidNotAllow"), null)
		//		}
		else if(ProjectConstants.SHOP_ATTEMPT == 4){
			Mobile.callTestCase(findTestCase("ShopOpen/ChillerUtilization/VisitChillerScenarios_WithCategeoryLevel/VisitChillerUtilizationWith_ChillerNotAvailable"), null)
		}
		else if(ProjectConstants.SHOP_ATTEMPT == 5){
			Mobile.callTestCase(findTestCase("ShopOpen/ChillerUtilization/VisitChillerScenarios_WithCategeoryLevel/VisitChillerUtilizationWith_ChillerAvailable"), null)
		}
		else if(ProjectConstants.SHOP_ATTEMPT == 6){
			Mobile.callTestCase(findTestCase("Test Cases/ShopOpen/ChillerUtilization/VisitChillerScenarios_WithCategeoryLevel/VisitChillerUtilizationWith_ChillerNotAvailable_WithOneSubCategory"), null)
		}
		else if(ProjectConstants.SHOP_ATTEMPT == 7){
			Mobile.callTestCase(findTestCase("ShopOpen/ChillerUtilization/VisitChillerScenarios_WithCategeoryLevel/VisitChillerUtilizationWith_ChillerAvailable"), null)
		}
		else{
			Mobile.callTestCase(findTestCase("ShopOpen/ChillerUtilization/VisitChillerScenarios_WithCategeoryLevel/VisitChillerUtilizationWith_ChillerAvailable"), null)
		}
	}
	//overwriting scenarios
	def visitChillerUtilizationOverwritingScenarios(){
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
	//chiller level overwrite scenarios
	def visitChillerUtilization_WithChillerLevel_OverwriteScenarios(){
		if(ProjectConstants.SHOP_ATTEMPT == 1){
			Mobile.callTestCase(findTestCase("Test Cases/ShopOpen/ChillerUtilization/VisitChillerScenarios_WithChillerLevel/VisitChillerUtilizationWith_ChillerAvailable"), null)
		}
		else if(ProjectConstants.SHOP_ATTEMPT == 2){
			Mobile.callTestCase(findTestCase("Test Cases/ShopOpen/ChillerUtilization/VisitChillerScenarios_WithChillerLevel/VisitChillerUtilizationWith_ChillerRemovedForMaintenance"), null)
		}
		else if(ProjectConstants.SHOP_ATTEMPT == 3){
			Mobile.callTestCase(findTestCase("Test Cases/ShopOpen/ChillerUtilization/VisitChillerScenarios_WithChillerLevel/VisitChillerUtilizationWith_ChillerTypeNotAvailable"), null)
		}
		//		else if(ProjectConstants.SHOP_ATTEMPT == 4){
		//			Mobile.callTestCase(findTestCase("Test Cases/ShopOpen/ChillerUtilization/VisitChillerScenarios_WithChillerLevel/VisitChillerUtilizationWith_ShopKeeperDidNotAllow"), null)
		//		}
		//		else if(ProjectConstants.SHOP_ATTEMPT == 5){
		//			Mobile.callTestCase(findTestCase("Test Cases/ShopOpen/ChillerUtilization/VisitChillerScenarios_WithChillerLevel/VisitChillerUtilizationWith_ShopKeeperDidNotAllow"), null)
		//		}
		else if(ProjectConstants.SHOP_ATTEMPT == 4){
			Mobile.callTestCase(findTestCase("Test Cases/ShopOpen/ChillerUtilization/VisitChillerScenarios_WithChillerLevel/VisitChillerUtilizationWith_ChillerNotAvailable"), null)
		}
		else if(ProjectConstants.SHOP_ATTEMPT == 5){
			Mobile.callTestCase(findTestCase("Test Cases/ShopOpen/ChillerUtilization/VisitChillerScenarios_WithChillerLevel/VisitChillerUtilizationWith_ChillerAvailable"), null)
		}
		else if(ProjectConstants.SHOP_ATTEMPT == 6){
			Mobile.callTestCase(findTestCase("Test Cases/ShopOpen/ChillerUtilization/VisitChillerScenarios_WithChillerLevel/VisitChillerUtilizationWith_ChillerNotAvailable_WithOneSubCategory"), null)
		}
		else if(ProjectConstants.SHOP_ATTEMPT == 7){
			Mobile.callTestCase(findTestCase("Test Cases/ShopOpen/ChillerUtilization/VisitChillerScenarios_WithChillerLevel/VisitChillerUtilizationWith_ChillerAvailable"), null)
		}
		else{
			Mobile.callTestCase(findTestCase("Test Cases/ShopOpen/ChillerUtilization/VisitChillerScenarios_WithChillerLevel/VisitChillerUtilizationWith_ChillerAvailable"), null)
		}
	}
}