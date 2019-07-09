package qa.keywords

import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import qa.constants.ProjectConstants
import qa.struct.UnmatchedItems
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

import internal.GlobalVariable
import io.appium.java_client.MobileElement
import io.appium.java_client.TouchAction
import io.appium.java_client.touch.offset.PointOption

import java.awt.Dimension
import java.time.Duration

import org.apache.poi.ss.usermodel.DataFormatter
import org.apache.poi.ss.usermodel.Row
import org.apache.poi.xssf.usermodel.XSSFSheet
import MobileBuiltInKeywords as Mobile
import WSBuiltInKeywords as WS
import WebUiBuiltInKeywords as WebUI

public class CompareDataKeywords {

	def static compareLists(ArrayList<String> expectedlist, ArrayList<String> displayedlist){
		if(expectedlist.size() == displayedlist.size()){
			ArrayList<String> products = new ArrayList<String>()
			UnmatchedItems UnmatchedItems_status = new UnmatchedItems()
			for(int i=0; i<displayedlist.size(); i++){
				boolean match = false
				for(int j=0; j<expectedlist.size(); j++){
					if(displayedlist.get(i).equalsIgnoreCase(expectedlist.get(j))){
						match = true
						break
					}
				}
				if(match == false){
					products.add(displayedlist.get(i))
				}
				else{
				}
			}
			if(!products.isEmpty()){
				UnmatchedItems_status.setItems(products)
				UnmatchedItems_status.setStatus(2)
				return UnmatchedItems_status
			}
			else{
				UnmatchedItems_status.setItems(products)
				UnmatchedItems_status.setStatus(0)
				return UnmatchedItems_status
			}
		}
		else if(expectedlist.size() < displayedlist.size()){
			ArrayList<String> products = new ArrayList<String>()
			UnmatchedItems UnmatchedItems_status = new UnmatchedItems()
			for(int i=0; i<displayedlist.size(); i++){
				boolean match = false
				for(int j=0; j<expectedlist.size(); j++){
					if(displayedlist.get(i).equalsIgnoreCase(expectedlist.get(j))){
						match = true
						break
					}
				}
				if(match == false){
					products.add(displayedlist.get(i))
				}
				else{
				}
			}
			UnmatchedItems_status.setItems(products)
			UnmatchedItems_status.setStatus(1)
			return UnmatchedItems_status
		}
		else if(expectedlist.size() > displayedlist.size()){
			ArrayList<String> products = new ArrayList<String>()
			UnmatchedItems UnmatchedItems_status = new UnmatchedItems()
			for(int i=0; i<expectedlist.size(); i++){
				boolean match = false
				for(int j=0; j<displayedlist.size(); j++){
					if(expectedlist.get(i).equalsIgnoreCase(displayedlist.get(j))){
						match = true
						break
					}
				}
				if(match == false){
					products.add(expectedlist.get(i))
				}
				else{
				}
			}
			UnmatchedItems_status.setItems(products)
			UnmatchedItems_status.setStatus(-1)
			return UnmatchedItems_status
		}
	}
	//compare display and actual channel wise products categories
	def static compareChannelWiseProductsCategories(){
		ArrayList<String> displayedproductscategorieslist = new ArrayList<String>()
		ArrayList<String> expectedproductscategorieslist = LoadDataKeywords.loadChannelWiseSubCategories()
		int totalproductscategories = ProjectConstants.DRIVER.findElementsByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.ListView[1]/*").size()
		for(int i=1; i<=totalproductscategories; i++){
			MobileElement productcategory = ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout["+i+"]/android.widget.TextView[1]")
			displayedproductscategorieslist.add(productcategory.getText())
		}
		ArrayList<String> expectedproductscategories = new HashSet<String>(expectedproductscategorieslist)
		UnmatchedItems unmatcheditems = compareLists(expectedproductscategories, displayedproductscategorieslist)
		return unmatcheditems
	}
	//compare display and actual chiller wise products categories
	def static compareChillerWiseProductsCategories(){
		ArrayList<String> displayedproductscategorieslist = new ArrayList<String>()
		ArrayList<String> expectedproductscategorieslist = LoadDataKeywords.loadChillerWiseSubCategories()
		int totalproductscategories = ProjectConstants.DRIVER.findElementsByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.ListView[1]/*").size()
		for(int i=1; i<=totalproductscategories; i++){
			MobileElement productcategory = ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout["+i+"]/android.widget.TextView[1]")
			displayedproductscategorieslist.add(productcategory.getText())
		}
		ArrayList<String> expectedproductscategories = new HashSet<String>(expectedproductscategorieslist)
		UnmatchedItems unmatcheditems = compareLists(expectedproductscategories, displayedproductscategorieslist)
		return unmatcheditems
	}
	//compare display and actual shop main categories
	def static compareShopCategories(){
		TouchAction touchaction = new TouchAction(ProjectConstants.DRIVER)
		ArrayList<String> displayedshopcategories = new ArrayList<String>()
		int index = 0
		MobileElement listcontainer = ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ListView[1]")
		ArrayList<MobileElement> categories = listcontainer.findElementsByClassName("android.widget.TextView")
		for(int i=0; i< categories.size(); i++){
			MobileElement category = categories.get(i)
			String categoryname = category.getText()
			if(categoryname.equalsIgnoreCase("Chiller Utilization")){
				displayedshopcategories.add("Chiller")
			}
			else{
				displayedshopcategories.add(categoryname)
			}
		}
		touchaction.press(0, 230).waitAction(Duration.ofMillis(500)).moveTo(0, 200).release().perform()
		Thread.sleep(500)
		while(true){
			categories = listcontainer.findElementsByClassName("android.widget.TextView")
			MobileElement lastitembeforeswipe = categories.get((categories.size()-1))
			String lastitemnamebeforeswipe = lastitembeforeswipe.getText()
			// swipe using touch action from element to element
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
					String categorytext = category.getText()
					if(categorytext.equalsIgnoreCase("Chiller Utilization")){
						displayedshopcategories.add("Chiller")
					}
					else{
						displayedshopcategories.add(categorytext)
					}
				}
			}
		}
		ArrayList<String> expectedshopcategorieslist = LoadDataKeywords.loadShopCategories()
		ArrayList<String> expectedshopcategories = new HashSet<String>(expectedshopcategorieslist)
		ArrayList<String> displayedshopcategorieslist = new HashSet<String>(displayedshopcategories)
		UnmatchedItems unmatcheditems = compareLists(expectedshopcategories, displayedshopcategorieslist)
		return unmatcheditems
	}
	def static compareSliderOptions(){
		TouchAction touchaction = new TouchAction(ProjectConstants.DRIVER)
		int index = 0
		ArrayList<String> expectedslideroptions = LoadDataKeywords.loadSliderOptions()
		ArrayList<String> displayedslideroptions = new ArrayList<String>()
		MobileElement listcontainer = ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.support.v4.widget.DrawerLayout[1]/android.widget.LinearLayout[2]/android.widget.ListView[1]")
		ArrayList<MobileElement> slideroptions = listcontainer.findElementsByClassName("android.widget.TextView")
		for(int i=0; i< slideroptions.size(); i++){
			MobileElement slideroption = slideroptions.get(i)
			displayedslideroptions.add(slideroption.getText())
		}
		touchaction.press(0, 230).waitAction(Duration.ofMillis(500)).moveTo(0, 200).release().perform()
		Thread.sleep(500)
		while(true){
			slideroptions = listcontainer.findElementsByClassName("android.widget.TextView")
			MobileElement lastitembeforeswipe = slideroptions.get((slideroptions.size()-1))
			String lastitemnamebeforeswipe = lastitembeforeswipe.getText()
			// swipe using touch action from element to element
			index = slideroptions.size()
			MobileElement startpoint = ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.support.v4.widget.DrawerLayout[1]/android.widget.LinearLayout[2]/android.widget.ListView[1]/android.widget.LinearLayout["+index+"]")
			MobileElement endpoint = ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.support.v4.widget.DrawerLayout[1]/android.widget.LinearLayout[2]/android.widget.ListView[1]/android.widget.LinearLayout[1]")
			touchaction.longPress(startpoint).waitAction(Duration.ofMillis(500)).moveTo(endpoint).release().perform()
			Thread.sleep(500)
			slideroptions = listcontainer.findElementsByClassName("android.widget.TextView")
			MobileElement lastitemafterswipe = slideroptions.get((slideroptions.size()-1))
			String lastitemnameafterswipe = lastitemafterswipe.getText()
			if(lastitemnamebeforeswipe.equalsIgnoreCase(lastitemnameafterswipe)){
				break
			}
			else{
				slideroptions = listcontainer.findElementsByClassName("android.widget.TextView")
				for(int i=0; i< slideroptions.size(); i++){
					MobileElement category = slideroptions.get(i)
					String categorytext = category.getText()
					displayedslideroptions.add(categorytext)
				}
			}
		}
		ArrayList<String> displayedslideroptionslist = new HashSet<String>(displayedslideroptions)
		UnmatchedItems unmatcheditems = compareLists(expectedslideroptions, displayedslideroptionslist)
		return unmatcheditems
	}
	def static compareShopActionsList(){
		ArrayList<String> displayedshopactionslist = new ArrayList<String>()
		ArrayList<String> expectedshopactionslist = LoadDataKeywords.loadShopActionsList()
		int actionslist = ProjectConstants.DRIVER.findElementsByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ListView[1]/*").size()
		for(int i=1; i<= actionslist; i++){
			MobileElement action = ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout["+i+"]/android.widget.TextView[1]")
			displayedshopactionslist.add(action.getText())
		}
		UnmatchedItems unmatcheditems = compareLists(expectedshopactionslist, displayedshopactionslist)
		return unmatcheditems
	}
	def static compareShopRemarksList(){
		ArrayList<String> displayedshopremarkslist = new ArrayList<String>()
		ArrayList<String> expectedshopremarkslist = LoadDataKeywords.loadShopRemarksList()
		int remarkslist = ProjectConstants.DRIVER.findElementsByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.ListView[1]/*").size()
		for(int i=1; i<= remarkslist; i++){
			MobileElement action = ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout["+i+"]/android.widget.TextView[1]")
			displayedshopremarkslist.add(action.getText())
		}
		UnmatchedItems unmatcheditems = compareLists(expectedshopremarkslist, displayedshopremarkslist)
		return unmatcheditems
	}
	def static compareSurveyQuestionCategories(){
		ArrayList<String> expectedquestioncategories = LoadDataKeywords.loadSurveyQuestionCategoryList()
		ArrayList<String> displayedquestioncategorieslist = new ArrayList<String>()
		int questioncategorieslist = ProjectConstants.DRIVER.findElementsByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.ListView[1]/*").size()
		for(int i=1; i<= questioncategorieslist; i++){
			MobileElement questioncategory = ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout["+i+"]/android.widget.TextView[1]")
			displayedquestioncategorieslist.add(questioncategory.getText())
		}
		ArrayList<String> expectedquestioncategorieslist = new HashSet<String>(expectedquestioncategories)
		UnmatchedItems unmatcheditems = compareLists(expectedquestioncategorieslist, displayedquestioncategorieslist)
		return unmatcheditems
	}
}
