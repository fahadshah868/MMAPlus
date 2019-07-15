package qa.keywords

import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import java.time.Duration
import org.openqa.selenium.JavascriptExecutor
import org.openqa.selenium.Point
import org.openqa.selenium.interactions.touch.TouchActions
import qa.constants.ProjectConstants
import qa.struct.MissingCategoryData
import qa.struct.MissingChillerProductsCategoryData
import qa.struct.MissingShopDataInfo
import qa.struct.ProductCategoryWithProducts
import qa.struct.QuestionsData
import qa.struct.ShopProductsData
import qa.struct.TaggedChillersRemark
import qa.struct.UnmatchedItems
import qa.struct.VisitedCategoryData
import qa.struct.VisitedChillerProductsCategoryData
import qa.struct.VisitedShopDataInfo
import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.util.KeywordUtil
import io.appium.java_client.touch.WaitOptions
import io.appium.java_client.touch.offset.PointOption

import io.appium.java_client.MobileElement
import io.appium.java_client.TouchAction
import io.appium.java_client.android.AndroidDriver

public class ShopVisitingScenariosKeywords{

	//find shop from shops list against shop name
	def findShop(String _shopname){
		int index = 0
		int totalshops = ProjectConstants.DRIVER.findElementsByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.LinearLayout[1]/android.widget.ListView[1]/*").size()
		for(int i=1; i<=totalshops; i++){
			MobileElement shop = ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.LinearLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout["+i+"]/android.widget.TextView[1]")
			String shopname = shop.getText()
			if(shopname.equalsIgnoreCase(_shopname)){
				ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.LinearLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout["+i+"]").click()
				MobileBuiltInKeywords.verifyElementText(findTestObject("Object Repository/Validate_ShopOptionsScreen" , [('package') : ProjectConstants.PACKAGENAME]), "Options")
				MobileBuiltInKeywords.tap(findTestObject("Object Repository/StartWorking" , [('package') : ProjectConstants.PACKAGENAME]), 0)
				CommonKeywords.visitPopUpForOverwriting()
				Mobile.delay(15)
				Mobile.verifyElementExist(findTestObject("Object Repository/CommonScreenElements/Validate_MapScreen" , [('package') : ProjectConstants.PACKAGENAME]), 60)
				MobileBuiltInKeywords.tap(findTestObject("Object Repository/CommonScreenElements/Location_CheckIn" , [('package') : ProjectConstants.PACKAGENAME]), 0)
				Mobile.verifyElementExist(findTestObject("Object Repository/CommonScreenElements/Validate_InfoPopUP" , [('package') : ProjectConstants.PACKAGENAME]), 0)
				MobileBuiltInKeywords.tap(findTestObject("Object Repository/CommonScreenElements/InfoPopUp_NoButton" , [('package') : ProjectConstants.PACKAGENAME]), 0)
				break
			}
			else{
			}
		}
	}
	//capture missing shop actions list e.g. start working, shop fascia, get route etc
	def static missingShopActionsList(){
		UnmatchedItems unmatcheditems = CompareDataKeywords.compareShopActionsList()
		if(unmatcheditems.getStatus() == 2){
			for(int j=0; j<ProjectConstants.missingshopdatainfo.size(); j++){
				if(ProjectConstants.missingshopdatainfo.get(j).getShopname().equalsIgnoreCase(ProjectConstants.CURRENTVISITING_SHOPNAME)) {
					ProjectConstants.missingshopdatainfo.get(j).setMissingshopactions(unmatcheditems.getItems())
					ProjectConstants.missingshopdatainfo.get(j).setMissingshopactions_errormessage(ProjectConstants.MESSAGEFOR_ITEMSARE_NOTMATCH)
					break
				}
				else{
				}
			}
		}
		else if(unmatcheditems.getStatus() == 1){
			for(int j=0; j<ProjectConstants.missingshopdatainfo.size(); j++){
				if(ProjectConstants.missingshopdatainfo.get(j).getShopname().equalsIgnoreCase(ProjectConstants.CURRENTVISITING_SHOPNAME)) {
					ProjectConstants.missingshopdatainfo.get(j).setMissingshopactions(unmatcheditems.getItems())
					ProjectConstants.missingshopdatainfo.get(j).setMissingshopactions_errormessage(ProjectConstants.MESSAGEFOR_ITEMSARE_MORE)
					break
				}
				else{
				}
			}
		}
		else if(unmatcheditems.getStatus() == -1){
			for(int j=0; j<ProjectConstants.missingshopdatainfo.size(); j++){
				if(ProjectConstants.missingshopdatainfo.get(j).getShopname().equalsIgnoreCase(ProjectConstants.CURRENTVISITING_SHOPNAME)) {
					ProjectConstants.missingshopdatainfo.get(j).setMissingshopactions(unmatcheditems.getItems())
					ProjectConstants.missingshopdatainfo.get(j).setMissingshopactions_errormessage(ProjectConstants.MESSAGEFOR_ITEMSARE_MISSING)
					break
				}
				else{
				}
			}
		}
		else{
		}
	}
	//capture missing shop remarks list e.g. shop open, shop closed, shop to be removed etc
	def static missingShopRemarksList(){
		UnmatchedItems unmatcheditems = CompareDataKeywords.compareShopRemarksList()
		if(unmatcheditems.getStatus() == 2){
			for(int j=0; j<ProjectConstants.missingshopdatainfo.size(); j++){
				if(ProjectConstants.missingshopdatainfo.get(j).getShopname().equalsIgnoreCase(ProjectConstants.CURRENTVISITING_SHOPNAME)) {
					ProjectConstants.missingshopdatainfo.get(j).setMissingshopremarks(unmatcheditems.getItems())
					ProjectConstants.missingshopdatainfo.get(j).setMissingshopremarks_errormessage(ProjectConstants.MESSAGEFOR_ITEMSARE_NOTMATCH)
					break
				}
				else{
				}
			}
		}
		else if(unmatcheditems.getStatus() == 1){
			for(int j=0; j<ProjectConstants.missingshopdatainfo.size(); j++){
				if(ProjectConstants.missingshopdatainfo.get(j).getShopname().equalsIgnoreCase(ProjectConstants.CURRENTVISITING_SHOPNAME)) {
					ProjectConstants.missingshopdatainfo.get(j).setMissingshopremarks(unmatcheditems.getItems())
					ProjectConstants.missingshopdatainfo.get(j).setMissingshopremarks_errormessage(ProjectConstants.MESSAGEFOR_ITEMSARE_MORE)
					break
				}
				else{
				}
			}
		}
		else if(unmatcheditems.getStatus() == -1){
			for(int j=0; j<ProjectConstants.missingshopdatainfo.size(); j++){
				if(ProjectConstants.missingshopdatainfo.get(j).getShopname().equalsIgnoreCase(ProjectConstants.CURRENTVISITING_SHOPNAME)) {
					ProjectConstants.missingshopdatainfo.get(j).setMissingshopremarks(unmatcheditems.getItems())
					ProjectConstants.missingshopdatainfo.get(j).setMissingshopremarks_errormessage(ProjectConstants.MESSAGEFOR_ITEMSARE_MISSING)
					break
				}
				else{
				}
			}
		}
		else{
		}
	}
	/******************************************************************
	 VISIT SHOPS FROM SLIDER E.G. OUT OF ROUTE SHOP, RED FLAG SHOP ETC
	 *****************************************************************/
	@Keyword
	def static visitSliderShops(String remark){
		int index = 0
		int totalshops = ProjectConstants.DRIVER.findElementsByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.LinearLayout[1]/android.widget.ListView[1]/*").size()
		for(int i=1; i<= 1; i++){
			MissingShopDataInfo missingshopdatainfo = new MissingShopDataInfo()
			VisitedShopDataInfo visitedshopdatainfo = new VisitedShopDataInfo()
			MobileElement shop = ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.LinearLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout["+i+"]/android.widget.TextView[1]")
			ProjectConstants.CURRENTVISITING_SHOPNAME = shop.getText()
			//setting info about shop for missing items
			missingshopdatainfo.setSupervisorname(ProjectConstants.SUPERVISOR_NAME)
			missingshopdatainfo.setMerchandisername(ProjectConstants.MERCHANDISER_NAME)
			missingshopdatainfo.setWorkingaction(ProjectConstants.WORKING_ACTION)
			missingshopdatainfo.setRoute(ProjectConstants.CURRENTVISITING_ROUTE)
			missingshopdatainfo.setShopname(shop.getText())
			missingshopdatainfo.setRemark(remark)
			//setting info about shop for visiting items
			visitedshopdatainfo.setSupervisorname(ProjectConstants.SUPERVISOR_NAME)
			visitedshopdatainfo.setMerchandisername(ProjectConstants.MERCHANDISER_NAME)
			visitedshopdatainfo.setWorkingaction(ProjectConstants.WORKING_ACTION)
			visitedshopdatainfo.setRoute(ProjectConstants.CURRENTVISITING_ROUTE)
			visitedshopdatainfo.setShopname(shop.getText())
			visitedshopdatainfo.setRemark(remark)
			ProjectConstants.missingshopdatainfo.add(missingshopdatainfo)
			ProjectConstants.visitedshopdatainfo.add(visitedshopdatainfo)
			ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.LinearLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout["+i+"]").click()
			MobileBuiltInKeywords.verifyElementText(findTestObject("Object Repository/Validate_ShopOptionsScreen" , [('package') : ProjectConstants.PACKAGENAME]), "Options")
			//validate missing shop actions list e.g. start working / get routes etc...
			missingShopActionsList()
			MobileBuiltInKeywords.tap(findTestObject("Object Repository/StartWorking" , [('package') : ProjectConstants.PACKAGENAME]), 0)
			Mobile.delay(15)
			Mobile.verifyElementExist(findTestObject("Object Repository/CommonScreenElements/Validate_MapScreen" , [('package') : ProjectConstants.PACKAGENAME]), 60)
			MobileBuiltInKeywords.tap(findTestObject("Object Repository/CommonScreenElements/Location_CheckIn" , [('package') : ProjectConstants.PACKAGENAME]), 0)
			Mobile.verifyElementExist(findTestObject("Object Repository/CommonScreenElements/Validate_InfoPopUP" , [('package') : ProjectConstants.PACKAGENAME]), 0)
			MobileBuiltInKeywords.tap(findTestObject("Object Repository/CommonScreenElements/InfoPopUp_NoButton" , [('package') : ProjectConstants.PACKAGENAME]), 0)
			//validate missing shop remarks list e.g. shop open, closed etc...
			missingShopRemarksList()
			Mobile.callTestCase(findTestCase("Test Cases/ShopOpen/VisitShopOpen"), null)
			ProjectConstants.SCENARIO = "first visit"
			Mobile.callTestCase(findTestCase("Test Cases/ShopOpen/VisitCategoryScenarios/VisitShopCategoriesWithDataVerification"), null)
			Mobile.callTestCase(findTestCase("Test Cases/ShopOpen/SaveShop"), null)
			for(int j=0; j<ProjectConstants.missingshopdatainfo.size(); j++){
				if(ProjectConstants.missingshopdatainfo.get(j).getShopname().equals(ProjectConstants.CURRENTVISITING_SHOPNAME)) {
					ProjectConstants.missingshopdatainfo.get(j).setShopchannel(ProjectConstants.CURRENTVISITING_SHOPCHANNEL)
					ProjectConstants.missingshopdatainfo.get(j).setScenario("Data Verification")
					break
				}
			}
			for(int j=0; j<ProjectConstants.visitedshopdatainfo.size(); j++){
				if(ProjectConstants.visitedshopdatainfo.get(j).getShopname().equals(ProjectConstants.CURRENTVISITING_SHOPNAME)) {
					ProjectConstants.visitedshopdatainfo.get(j).setShopchannel(ProjectConstants.CURRENTVISITING_SHOPCHANNEL)
					ProjectConstants.visitedshopdatainfo.get(j).setShop_scenario("Shop Open")
					String message = "(1) 'RTM visit frequency' with 'Once a week'\n"+
							String.format("%-44s%-100s","","'Pop Application' with 'No' remark")+"\n"+
							String.format("%-44s%-100s","","'Retailer Remarks' with 'OB not visiting' remark")+"\n"+
							String.format("%-44s%-100s","","'Hanger Availability' with 'Yes' remark")+"\n"+
							String.format("%-40s%-100s","","(2) 'RTM visit frequency' with 'Twice a week'")+"\n"+
							String.format("%-44s%-100s","","'Pop Application' with 'No' remark")+"\n"+
							String.format("%-44s%-100s","","'Retailer Remarks' with 'SM not visiting' remark")+"\n"+
							String.format("%-44s%-100s","","'Hanger Availability' with 'No' remark")
					ProjectConstants.visitedshopdatainfo.get(j).setOthercategories_scenarios(message)
					break
				}
			}
			Mobile.verifyElementExist(findTestObject("Object Repository/Validate_ShopListScreen" , [('package') : ProjectConstants.PACKAGENAME]), 0)
		}
	}
	/******************************************************************
	 VISIT SHOPS FROM WORK ACTIONS INCLUDING WW, WR, MERCHANDISING
	 *****************************************************************/
	@Keyword
	def visitShopWith_HappyFlow(){
		int index = 0
		int _shop = ProjectConstants.SHOP_ATTEMPT
		MissingShopDataInfo missingshopdatainfo = new MissingShopDataInfo()
		VisitedShopDataInfo visitedshopdatainfo = new VisitedShopDataInfo()
		MobileElement shop = ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.LinearLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout["+_shop+"]/android.widget.TextView[1]")
		ProjectConstants.CURRENTVISITING_SHOPNAME = shop.getText()
		missingshopdatainfo.setSupervisorname(ProjectConstants.SUPERVISOR_NAME)
		missingshopdatainfo.setMerchandisername(ProjectConstants.MERCHANDISER_NAME)
		missingshopdatainfo.setWorkingaction(ProjectConstants.WORKING_ACTION)
		missingshopdatainfo.setRoute(ProjectConstants.CURRENTVISITING_ROUTE)
		missingshopdatainfo.setShopname(shop.getText())
		missingshopdatainfo.setRemark("Normal Shops")
		visitedshopdatainfo.setSupervisorname(ProjectConstants.SUPERVISOR_NAME)
		visitedshopdatainfo.setMerchandisername(ProjectConstants.MERCHANDISER_NAME)
		visitedshopdatainfo.setWorkingaction(ProjectConstants.WORKING_ACTION)
		visitedshopdatainfo.setRoute(ProjectConstants.CURRENTVISITING_ROUTE)
		visitedshopdatainfo.setShopname(shop.getText())
		visitedshopdatainfo.setRemark("Normal Shops")
		ProjectConstants.missingshopdatainfo.add(missingshopdatainfo)
		ProjectConstants.visitedshopdatainfo.add(visitedshopdatainfo)
		ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.LinearLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout["+_shop+"]").click()
		if(Mobile.verifyElementExist(findTestObject("Object Repository/Validate_VisitDetail_Popup", [('package') : ProjectConstants.PACKAGENAME]), 0, FailureHandling.OPTIONAL)){
			Mobile.tap(findTestObject("Object Repository/VisitDetailsPopup_ProceedButton", [('package') : ProjectConstants.PACKAGENAME]), 0, FailureHandling.STOP_ON_FAILURE)
		}
		else{}
		MobileBuiltInKeywords.verifyElementText(findTestObject("Object Repository/Validate_ShopOptionsScreen" , [('package') : ProjectConstants.PACKAGENAME]), "Options")
		//validate missing shop actions list e.g. start working / get routes etc...
		missingShopActionsList()
		MobileBuiltInKeywords.tap(findTestObject("Object Repository/StartWorking" , [('package') : ProjectConstants.PACKAGENAME]), 0)
		Mobile.delay(15)
		Mobile.verifyElementExist(findTestObject("Object Repository/CommonScreenElements/Validate_MapScreen" , [('package') : ProjectConstants.PACKAGENAME]), 60)
		MobileBuiltInKeywords.tap(findTestObject("Object Repository/CommonScreenElements/Location_CheckIn" , [('package') : ProjectConstants.PACKAGENAME]), 0)
		Mobile.verifyElementExist(findTestObject("Object Repository/CommonScreenElements/Validate_InfoPopUP" , [('package') : ProjectConstants.PACKAGENAME]), 0)
		MobileBuiltInKeywords.tap(findTestObject("Object Repository/CommonScreenElements/InfoPopUp_NoButton" , [('package') : ProjectConstants.PACKAGENAME]), 0)
		//validate missing shop remarks list e.g. shop open, closed etc...
		missingShopRemarksList()
		Mobile.callTestCase(findTestCase("Test Cases/ShopOpen/VisitShopOpen"), null)
		ProjectConstants.SCENARIO = "first visit"
		Mobile.callTestCase(findTestCase("Test Cases/ShopOpen/VisitCategoryScenarios/VisitShopCategoriesWithDataVerification"), null)
		Mobile.callTestCase(findTestCase("Test Cases/ShopOpen/SaveShop"), null)
		for(int j=0; j<ProjectConstants.missingshopdatainfo.size(); j++){
			if(ProjectConstants.missingshopdatainfo.get(j).getWorkingaction().equals(ProjectConstants.WORKING_ACTION) && ProjectConstants.missingshopdatainfo.get(j).getShopname().equals(ProjectConstants.CURRENTVISITING_SHOPNAME)) {
				ProjectConstants.missingshopdatainfo.get(j).setShopchannel(ProjectConstants.CURRENTVISITING_SHOPCHANNEL)
				ProjectConstants.missingshopdatainfo.get(j).setScenario("Data Verification")
				break
			}
		}
		for(int j=0; j<ProjectConstants.visitedshopdatainfo.size(); j++){
			if(ProjectConstants.visitedshopdatainfo.get(j).getWorkingaction().equals(ProjectConstants.WORKING_ACTION) && ProjectConstants.visitedshopdatainfo.get(j).getShopname().equals(ProjectConstants.CURRENTVISITING_SHOPNAME)) {
				ProjectConstants.visitedshopdatainfo.get(j).setShopchannel(ProjectConstants.CURRENTVISITING_SHOPCHANNEL)
				ProjectConstants.visitedshopdatainfo.get(j).setShop_scenario("Shop Open")
				String message = "(1) 'RTM visit frequency' with 'Once a week'\n"+
						String.format("%-44s%-100s","","'Pop Application' with 'No' remark")+"\n"+
						String.format("%-44s%-100s","","'Retailer Remarks' with 'OB not visiting' remark")+"\n"+
						String.format("%-44s%-100s","","'Hanger Availability' with 'Yes' remark")+"\n"+
						String.format("%-40s%-100s","","(2) 'RTM visit frequency' with 'Twice a week'")+"\n"+
						String.format("%-44s%-100s","","'Pop Application' with 'No' remark")+"\n"+
						String.format("%-44s%-100s","","'Retailer Remarks' with 'SM not visiting' remark")+"\n"+
						String.format("%-44s%-100s","","'Hanger Availability' with 'No' remark")
				ProjectConstants.visitedshopdatainfo.get(j).setOthercategories_scenarios(message)
				break
			}
		}
		Mobile.verifyElementExist(findTestObject("Object Repository/Validate_ShopListScreen" , [('package') : ProjectConstants.PACKAGENAME]), 0)
	}
	/***********************************************************
	 VISIT SHOPS WITH DATA VERIFICATION
	 ***********************************************************/
	def visitShopWith_DataVerification(MobileElement shop){
		MissingShopDataInfo missingshopdatainfo = new MissingShopDataInfo()
		VisitedShopDataInfo visitedshopdatainfo = new VisitedShopDataInfo()
		ProjectConstants.CURRENTVISITING_SHOPNAME = shop.getText()
		missingshopdatainfo.setShopname(shop.getText())
		visitedshopdatainfo.setShopname(shop.getText())
		missingshopdatainfo.setRemark("Normal Shops")
		visitedshopdatainfo.setRemark("Normal Shops")
		ProjectConstants.missingshopdatainfo.add(missingshopdatainfo)
		ProjectConstants.visitedshopdatainfo.add(visitedshopdatainfo)
		shop.click()
		MobileBuiltInKeywords.verifyElementText(findTestObject("Object Repository/Validate_ShopOptionsScreen" , [('package') : ProjectConstants.PACKAGENAME]), "Options")
		//validate missing shop actions list e.g. start working / get routes etc...
		missingShopActionsList()
		MobileBuiltInKeywords.tap(findTestObject("Object Repository/StartWorking" , [('package') : ProjectConstants.PACKAGENAME]), 0)
		Mobile.delay(15)
		Mobile.verifyElementExist(findTestObject("Object Repository/CommonScreenElements/Validate_MapScreen" , [('package') : ProjectConstants.PACKAGENAME]), 60)
		MobileBuiltInKeywords.tap(findTestObject("Object Repository/CommonScreenElements/Location_CheckIn" , [('package') : ProjectConstants.PACKAGENAME]), 0)
		Mobile.verifyElementExist(findTestObject("Object Repository/CommonScreenElements/Validate_InfoPopUP" , [('package') : ProjectConstants.PACKAGENAME]), 0)
		MobileBuiltInKeywords.tap(findTestObject("Object Repository/CommonScreenElements/InfoPopUp_NoButton" , [('package') : ProjectConstants.PACKAGENAME]), 0)
		//validate missing shop remarks list e.g. shop open, closed etc...
		missingShopRemarksList()
		Mobile.callTestCase(findTestCase("Test Cases/ShopOpen/VisitShopOpen"), null)
		ProjectConstants.SCENARIO = "first visit"
		Mobile.callTestCase(findTestCase("Test Cases/ShopOpen/VisitCategoryScenarios/VisitShopCategoriesWithDataVerification"), null)
		Mobile.callTestCase(findTestCase("Test Cases/ShopOpen/SaveShop"), null)
		for(int j=0; j<ProjectConstants.missingshopdatainfo.size(); j++){
			if(ProjectConstants.missingshopdatainfo.get(j).getShopname().equals(ProjectConstants.CURRENTVISITING_SHOPNAME)) {
				ProjectConstants.missingshopdatainfo.get(j).setShopchannel(ProjectConstants.CURRENTVISITING_SHOPCHANNEL)
				ProjectConstants.missingshopdatainfo.get(j).setScenario("Data Verification")
				break
			}
		}
		for(int j=0; j<ProjectConstants.visitedshopdatainfo.size(); j++){
			if(ProjectConstants.visitedshopdatainfo.get(j).getShopname().equals(ProjectConstants.CURRENTVISITING_SHOPNAME)) {
				ProjectConstants.visitedshopdatainfo.get(j).setShopchannel(ProjectConstants.CURRENTVISITING_SHOPCHANNEL)
				ProjectConstants.visitedshopdatainfo.get(j).setShop_scenario("Shop Open")
				String message = "(1) 'RTM visit frequency' with 'Once a week'\n"+
						String.format("%-44s%-100s","","'Pop Application' with 'No' remark")+"\n"+
						String.format("%-44s%-100s","","'Retailer Remarks' with 'OB not visiting' remark")+"\n"+
						String.format("%-44s%-100s","","'Hanger Availability' with 'Yes' remark")+"\n"+
						String.format("%-40s%-100s","","(2) 'RTM visit frequency' with 'Twice a week'")+"\n"+
						String.format("%-44s%-100s","","'Pop Application' with 'No' remark")+"\n"+
						String.format("%-44s%-100s","","'Retailer Remarks' with 'SM not visiting' remark")+"\n"+
						String.format("%-44s%-100s","","'Hanger Availability' with 'No' remark")
				ProjectConstants.visitedshopdatainfo.get(j).setOthercategories_scenarios(message)
				break
			}
		}
	}
	@Keyword
	def visitShopWith_DataVerification(){
		TouchAction touchaction = new TouchAction(ProjectConstants.DRIVER)
		int index, swipecounter
		String visitedshop
		MobileElement listcontainer = ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.LinearLayout[1]/android.widget.ListView[1]")
		ArrayList<MobileElement> shops = listcontainer.findElementsByClassName("android.widget.TextView")
		for(int i=0; i< 1; i++){
			MobileElement shop = shops.get(i)
			visitedshop = shop.getText()
			visitShopWith_DataVerification(shop)
			Mobile.verifyElementExist(findTestObject("Object Repository/Validate_ShopListScreen" , [('package') : ProjectConstants.PACKAGENAME]), 0)
		}
		//		touchaction.press(0, 220).waitAction(Duration.ofMillis(500)).moveTo(0, 200).release().perform()
		//		Thread.sleep(500)
		//		while(true){
		//			swipecounter++
		//			index = listcontainer.findElementsByClassName("android.widget.TextView").size()
		//			MobileElement lastitembeforeswipe = ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.LinearLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout["+index+"]/android.widget.TextView[1]")
		//			String lastitemnamebeforeswipe = lastitembeforeswipe.getText()
		//			MobileElement startpoint = ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.LinearLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout["+index+"]")
		//			MobileElement endpoint = ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.LinearLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout[1]")
		//			touchaction.press(startpoint).waitAction(Duration.ofMillis(2000)).moveTo(endpoint).release().perform()
		//			Thread.sleep(500)
		//			index = listcontainer.findElementsByClassName("android.widget.TextView").size()
		//			MobileElement lastitemafterswipe = ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.LinearLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout["+index+"]/android.widget.TextView[1]")
		//			String lastitemnameafterswipe = lastitemafterswipe.getText()
		//			if(lastitemnamebeforeswipe.equalsIgnoreCase(lastitemnameafterswipe)){
		//				break
		//			}
		//			else{
		//				shops = listcontainer.findElementsByClassName("android.widget.TextView")
		//				for(int i=0; i< shops.size(); i++){
		//					MobileElement shop = shops.get(i)
		//					//if current element match with last visited element
		//					if(visitedshop.equalsIgnoreCase(shop.getText())){
		//						//if current element is not a last element of screen
		//						if(i < (shops.size()-1)){
		//							shop = shops.get((i+1))
		//							visitedshop = shop.getText()
		//							visitShopWith_DataVerification(shop)
		//							//swipe to last visited item screen view
		//							touchaction.press(0, 230).waitAction(Duration.ofMillis(500)).moveTo(0, 200).release().perform()
		//							Thread.sleep(500)
		//							for(int j=1; j<= swipecounter; j++){
		//								index = listcontainer.findElementsByClassName("android.widget.TextView").size()
		//								startpoint = ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.LinearLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout["+index+"]")
		//								endpoint = ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.LinearLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout[1]")
		//								touchaction.press(startpoint).waitAction(Duration.ofMillis(2000)).moveTo(endpoint).release().perform()
		//								Thread.sleep(500)
		//							}
		//						}
		//					}
		//				}
		//			}
		//		}
		/****Generate Report and Display Data in Report****/
		DisplayReportKeywords.displayDataInReport()
	}

	/*************************************************
	 SHOP LEVEL OVERWRITE SCENARIOS
	 ************************************************/

	@Keyword
	def visitShopsWithShopLevel_OverwritingScenarios(){
		for(int i=1; i<=6; i++){
			MissingShopDataInfo missingshopdatainfo = new MissingShopDataInfo()
			VisitedShopDataInfo visitedshopdatainfo = new VisitedShopDataInfo()
			MobileElement shop = ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.LinearLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout["+i+"]/android.widget.TextView[1]")
			ProjectConstants.CURRENTVISITING_SHOPNAME = shop.getText()
			missingshopdatainfo.setShopname(shop.getText())
			missingshopdatainfo.setRemark("Normal Shops")
			visitedshopdatainfo.setShopname(shop.getText())
			visitedshopdatainfo.setRemark("Normal Shops")
			ProjectConstants.missingshopdatainfo.add(missingshopdatainfo)
			ProjectConstants.visitedshopdatainfo.add(visitedshopdatainfo)
			ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.LinearLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout["+i+"]").click()
			MobileBuiltInKeywords.verifyElementText(findTestObject("Object Repository/Validate_ShopOptionsScreen" , [('package') : ProjectConstants.PACKAGENAME]), "Options")
			//validate missing shop actions list e.g. start working / get routes etc...
			missingShopActionsList()
			MobileBuiltInKeywords.tap(findTestObject("Object Repository/StartWorking" , [('package') : ProjectConstants.PACKAGENAME]), 0)
			Mobile.delay(15)
			Mobile.verifyElementExist(findTestObject("Object Repository/CommonScreenElements/Validate_MapScreen" , [('package') : ProjectConstants.PACKAGENAME]), 60)
			MobileBuiltInKeywords.tap(findTestObject("Object Repository/CommonScreenElements/Location_CheckIn" , [('package') : ProjectConstants.PACKAGENAME]), 0)
			Mobile.verifyElementExist(findTestObject("Object Repository/CommonScreenElements/Validate_InfoPopUP" , [('package') : ProjectConstants.PACKAGENAME]), 0)
			MobileBuiltInKeywords.tap(findTestObject("Object Repository/CommonScreenElements/InfoPopUp_NoButton" , [('package') : ProjectConstants.PACKAGENAME]), 0)
			// shop closed to shop open
			if(i == 1){
				//validate missing shop remarks list e.g. shop open, closed etc...
				missingShopRemarksList()
				Mobile.callTestCase(findTestCase("Test Cases/ShopOpen/VisitShopOpen"), null)
				ProjectConstants.SCENARIO = "overwrite"
				Mobile.callTestCase(findTestCase("Test Cases/ShopOpen/VisitCategoryScenarios/VisitShopCategoriesWithDataVerification"), null)
				Mobile.callTestCase(findTestCase("Test Cases/ShopOpen/SaveShop"), null)
				Mobile.verifyElementExist(findTestObject("Object Repository/Validate_ShopListScreen" , [('package') : ProjectConstants.PACKAGENAME]), 0)
				for(int j=0; j<ProjectConstants.missingshopdatainfo.size(); j++){
					if(ProjectConstants.missingshopdatainfo.get(j).getShopname().equals(ProjectConstants.CURRENTVISITING_SHOPNAME)) {
						ProjectConstants.missingshopdatainfo.get(j).setShopchannel(ProjectConstants.CURRENTVISITING_SHOPCHANNEL)
						ProjectConstants.missingshopdatainfo.get(j).setScenario("Shop Level First Visit Before Overwriting")
						break
					}
				}
				for(int j=0; j<ProjectConstants.visitedshopdatainfo.size(); j++){
					if(ProjectConstants.visitedshopdatainfo.get(j).getShopname().equals(ProjectConstants.CURRENTVISITING_SHOPNAME)) {
						ProjectConstants.visitedshopdatainfo.get(j).setShopchannel(ProjectConstants.CURRENTVISITING_SHOPCHANNEL)
						ProjectConstants.visitedshopdatainfo.get(j).setShop_scenario("Shop Closed  ==>  Shop Open")
						String message = "(1) 'RTM visit frequency' with 'Once a week'\n"+
								String.format("%-44s%-100s","","'Pop Application' with 'No' remark")+"\n"+
								String.format("%-44s%-100s","","'Retailer Remarks' with 'OB not visiting' remark")+"\n"+
								String.format("%-44s%-100s","","'Hanger Availability' with 'Yes' remark")+"\n"+
								String.format("%-40s%-100s","","(2) 'RTM visit frequency' with 'Twice a week'")+"\n"+
								String.format("%-44s%-100s","","'Pop Application' with 'No' remark")+"\n"+
								String.format("%-44s%-100s","","'Retailer Remarks' with 'SM not visiting' remark")+"\n"+
								String.format("%-44s%-100s","","'Hanger Availability' with 'No' remark")
						ProjectConstants.visitedshopdatainfo.get(j).setOthercategories_scenarios(message)
						break
					}
				}
				Mobile.verifyElementExist(findTestObject("Object Repository/Validate_ShopListScreen" , [('package') : ProjectConstants.PACKAGENAME]), 0)
			}
			//shop open to shop closed
			else if(i == 2){
				Mobile.callTestCase(findTestCase("Test Cases/ShopClosed/VisitShopClosed"), null)
				Mobile.verifyElementExist(findTestObject("Object Repository/Validate_ShopListScreen" , [('package') : ProjectConstants.PACKAGENAME]), 0)
				for(int j=0; j<ProjectConstants.missingshopdatainfo.size(); j++){
					if(ProjectConstants.missingshopdatainfo.get(j).getShopname().equals(ProjectConstants.CURRENTVISITING_SHOPNAME)) {
						ProjectConstants.missingshopdatainfo.get(j).setShopchannel(ProjectConstants.CURRENTVISITING_SHOPCHANNEL)
						ProjectConstants.missingshopdatainfo.get(j).setScenario("Shop Level First Visit Before Overwriting")
						break
					}
				}
				for(int j=0; j<ProjectConstants.visitedshopdatainfo.size(); j++){
					if(ProjectConstants.visitedshopdatainfo.get(j).getShopname().equals(ProjectConstants.CURRENTVISITING_SHOPNAME)) {
						ProjectConstants.visitedshopdatainfo.get(j).setShopchannel(ProjectConstants.CURRENTVISITING_SHOPCHANNEL)
						ProjectConstants.visitedshopdatainfo.get(j).setShop_scenario("Shop Open  ==>  Shop Closed")
						String message = "(1) 'RTM visit frequency' with 'Once a week'\n"+
								String.format("%-44s%-100s","","'Pop Application' with 'No' remark")+"\n"+
								String.format("%-44s%-100s","","'Retailer Remarks' with 'OB not visiting' remark")+"\n"+
								String.format("%-44s%-100s","","'Hanger Availability' with 'Yes' remark")+"\n"+
								String.format("%-40s%-100s","","(2) 'RTM visit frequency' with 'Twice a week'")+"\n"+
								String.format("%-44s%-100s","","'Pop Application' with 'No' remark")+"\n"+
								String.format("%-44s%-100s","","'Retailer Remarks' with 'SM not visiting' remark")+"\n"+
								String.format("%-44s%-100s","","'Hanger Availability' with 'No' remark")
						ProjectConstants.visitedshopdatainfo.get(j).setOthercategories_scenarios(message)
						break
					}
				}
				Mobile.verifyElementExist(findTestObject("Object Repository/Validate_ShopListScreen" , [('package') : ProjectConstants.PACKAGENAME]), 0)
			}
			//SKDNA to SKDNA
			else if(i == 3){
				Mobile.callTestCase(findTestCase("Test Cases/ShopKeeperDidNotAllow/OverwriteShopKeeperDidNotAllow"), null)
				Mobile.verifyElementExist(findTestObject("Object Repository/Validate_ShopListScreen" , [('package') : ProjectConstants.PACKAGENAME]), 0)
				for(int j=0; j<ProjectConstants.missingshopdatainfo.size(); j++){
					if(ProjectConstants.missingshopdatainfo.get(j).getShopname().equals(ProjectConstants.CURRENTVISITING_SHOPNAME)) {
						ProjectConstants.missingshopdatainfo.get(j).setShopchannel(ProjectConstants.CURRENTVISITING_SHOPCHANNEL)
						ProjectConstants.missingshopdatainfo.get(j).setScenario("Shop Level First Visit Before Overwriting")
						break
					}
				}
				for(int j=0; j<ProjectConstants.visitedshopdatainfo.size(); j++){
					if(ProjectConstants.visitedshopdatainfo.get(j).getShopname().equals(ProjectConstants.CURRENTVISITING_SHOPNAME)) {
						ProjectConstants.visitedshopdatainfo.get(j).setShopchannel(ProjectConstants.CURRENTVISITING_SHOPCHANNEL)
						ProjectConstants.visitedshopdatainfo.get(j).setShop_scenario("SKDNA with 'Expiry Issue' remark  ==>  SKDNA with 'Others' remark")
						String message = "(1) 'RTM visit frequency' with 'Once a week'\n"+
								String.format("%-44s%-100s","","'Pop Application' with 'No' remark")+"\n"+
								String.format("%-44s%-100s","","'Retailer Remarks' with 'OB not visiting' remark")+"\n"+
								String.format("%-44s%-100s","","'Hanger Availability' with 'Yes' remark")+"\n"+
								String.format("%-40s%-100s","","(2) 'RTM visit frequency' with 'Twice a week'")+"\n"+
								String.format("%-44s%-100s","","'Pop Application' with 'No' remark")+"\n"+
								String.format("%-44s%-100s","","'Retailer Remarks' with 'SM not visiting' remark")+"\n"+
								String.format("%-44s%-100s","","'Hanger Availability' with 'No' remark")
						ProjectConstants.visitedshopdatainfo.get(j).setOthercategories_scenarios(message)
						break
					}
				}
				Mobile.verifyElementExist(findTestObject("Object Repository/Validate_ShopListScreen" , [('package') : ProjectConstants.PACKAGENAME]), 0)
			}
			//shop permanently closed to shop not found
			else if(i == 4){
				Mobile.callTestCase(findTestCase("Test Cases/ShopNotFound/VisitShopNotFound"), null)
				Mobile.verifyElementExist(findTestObject("Object Repository/Validate_ShopListScreen" , [('package') : ProjectConstants.PACKAGENAME]), 0)
				for(int j=0; j<ProjectConstants.missingshopdatainfo.size(); j++){
					if(ProjectConstants.missingshopdatainfo.get(j).getShopname().equals(ProjectConstants.CURRENTVISITING_SHOPNAME)) {
						ProjectConstants.missingshopdatainfo.get(j).setShopchannel(ProjectConstants.CURRENTVISITING_SHOPCHANNEL)
						ProjectConstants.missingshopdatainfo.get(j).setScenario("Shop Level First Visit Before Overwriting")
						break
					}
				}
				for(int j=0; j<ProjectConstants.visitedshopdatainfo.size(); j++){
					if(ProjectConstants.visitedshopdatainfo.get(j).getShopname().equals(ProjectConstants.CURRENTVISITING_SHOPNAME)) {
						ProjectConstants.visitedshopdatainfo.get(j).setShopchannel(ProjectConstants.CURRENTVISITING_SHOPCHANNEL)
						ProjectConstants.visitedshopdatainfo.get(j).setShop_scenario("Shop Permanently Closed  ==>  Shop Not Found")
						String message = "(1) 'RTM visit frequency' with 'Once a week'\n"+
								String.format("%-44s%-100s","","'Pop Application' with 'No' remark")+"\n"+
								String.format("%-44s%-100s","","'Retailer Remarks' with 'OB not visiting' remark")+"\n"+
								String.format("%-44s%-100s","","'Hanger Availability' with 'Yes' remark")+"\n"+
								String.format("%-40s%-100s","","(2) 'RTM visit frequency' with 'Twice a week'")+"\n"+
								String.format("%-44s%-100s","","'Pop Application' with 'No' remark")+"\n"+
								String.format("%-44s%-100s","","'Retailer Remarks' with 'SM not visiting' remark")+"\n"+
								String.format("%-44s%-100s","","'Hanger Availability' with 'No' remark")
						ProjectConstants.visitedshopdatainfo.get(j).setOthercategories_scenarios(message)
						break
					}
				}
				Mobile.verifyElementExist(findTestObject("Object Repository/Validate_ShopListScreen" , [('package') : ProjectConstants.PACKAGENAME]), 0)
			}
			//shop closed to shop permanently closed
			else if(i == 5){
				Mobile.callTestCase(findTestCase("Test Cases/ShopPermanentlyClosed/VisitShopPermanentlyClosed"), null)
				Mobile.verifyElementExist(findTestObject("Object Repository/Validate_ShopListScreen" , [('package') : ProjectConstants.PACKAGENAME]), 0)
				for(int j=0; j<ProjectConstants.missingshopdatainfo.size(); j++){
					if(ProjectConstants.missingshopdatainfo.get(j).getShopname().equals(ProjectConstants.CURRENTVISITING_SHOPNAME)) {
						ProjectConstants.missingshopdatainfo.get(j).setShopchannel(ProjectConstants.CURRENTVISITING_SHOPCHANNEL)
						ProjectConstants.missingshopdatainfo.get(j).setScenario("Shop Level First Visit Before Overwriting")
						break
					}
				}
				for(int j=0; j<ProjectConstants.visitedshopdatainfo.size(); j++){
					if(ProjectConstants.visitedshopdatainfo.get(j).getShopname().equals(ProjectConstants.CURRENTVISITING_SHOPNAME)) {
						ProjectConstants.visitedshopdatainfo.get(j).setShopchannel(ProjectConstants.CURRENTVISITING_SHOPCHANNEL)
						ProjectConstants.visitedshopdatainfo.get(j).setShop_scenario("Shop Closed  ==>  Shop Permanently Closed")
						String message = "(1) 'RTM visit frequency' with 'Once a week'\n"+
								String.format("%-44s%-100s","","'Pop Application' with 'No' remark")+"\n"+
								String.format("%-44s%-100s","","'Retailer Remarks' with 'OB not visiting' remark")+"\n"+
								String.format("%-44s%-100s","","'Hanger Availability' with 'Yes' remark")+"\n"+
								String.format("%-40s%-100s","","(2) 'RTM visit frequency' with 'Twice a week'")+"\n"+
								String.format("%-44s%-100s","","'Pop Application' with 'No' remark")+"\n"+
								String.format("%-44s%-100s","","'Retailer Remarks' with 'SM not visiting' remark")+"\n"+
								String.format("%-44s%-100s","","'Hanger Availability' with 'No' remark")
						ProjectConstants.visitedshopdatainfo.get(j).setOthercategories_scenarios(message)
						break
					}
				}
				Mobile.verifyElementExist(findTestObject("Object Repository/Validate_ShopListScreen" , [('package') : ProjectConstants.PACKAGENAME]), 0)
			}
			//shop open to shop open
			else if(i == 6){
				//validate missing shop remarks list e.g. shop open, closed etc...
				missingShopRemarksList()
				Mobile.callTestCase(findTestCase("Test Cases/ShopOpen/VisitShopOpen"), null)
				ProjectConstants.SCENARIO = "overwrite"
				ProjectConstants.SHOP_ATTEMPT = 1
				Mobile.callTestCase(findTestCase("ShopOpen/VisitCategoryScenarios/VisitShopCategoriesWithOverwritingScenarios"), null)
				Mobile.callTestCase(findTestCase("Test Cases/ShopOpen/SaveShop"), null)
				Mobile.verifyElementExist(findTestObject("Object Repository/Validate_ShopListScreen" , [('package') : ProjectConstants.PACKAGENAME]), 0)
				for(int j=0; j<ProjectConstants.missingshopdatainfo.size(); j++){
					if(ProjectConstants.missingshopdatainfo.get(j).getShopname().equals(ProjectConstants.CURRENTVISITING_SHOPNAME)) {
						ProjectConstants.missingshopdatainfo.get(j).setShopchannel(ProjectConstants.CURRENTVISITING_SHOPCHANNEL)
						String message = "Shop Level First Visit Before Overwriting"
						ProjectConstants.missingshopdatainfo.get(j).setScenario(message)
						break
					}
				}
				for(int j=0; j<ProjectConstants.visitedshopdatainfo.size(); j++){
					if(ProjectConstants.visitedshopdatainfo.get(j).getShopname().equals(ProjectConstants.CURRENTVISITING_SHOPNAME)) {
						ProjectConstants.visitedshopdatainfo.get(j).setShopchannel(ProjectConstants.CURRENTVISITING_SHOPCHANNEL)
						ProjectConstants.visitedshopdatainfo.get(j).setShop_scenario("Shop Open  ==>  Shop Open")
						String message = "(1) 'RTM visit frequency' with 'Once a week'\n"+
								String.format("%-44s%-100s","","'Pop Application' with 'No' remark")+"\n"+
								String.format("%-44s%-100s","","'Retailer Remarks' with 'OB not visiting' remark")+"\n"+
								String.format("%-44s%-100s","","'Hanger Availability' with 'Yes' remark")+"\n"+
								String.format("%-40s%-100s","","(2) 'RTM visit frequency' with 'Twice a week'")+"\n"+
								String.format("%-44s%-100s","","'Pop Application' with 'No' remark")+"\n"+
								String.format("%-44s%-100s","","'Retailer Remarks' with 'SM not visiting' remark")+"\n"+
								String.format("%-44s%-100s","","'Hanger Availability' with 'No' remark")
						ProjectConstants.visitedshopdatainfo.get(j).setOthercategories_scenarios(message)
						break
					}
				}
				Mobile.verifyElementExist(findTestObject("Object Repository/Validate_ShopListScreen" , [('package') : ProjectConstants.PACKAGENAME]), 0)
			}
			else{
				break
			}
		}
		/****Generate Report and Display Data in Report****/
		DisplayReportKeywords.displayDataInReport()
	}
	@Keyword
	def visitShopsWithShopLevel_OverwriteScenarios(){
		for(int i=1; i<=6; i++){
			MissingShopDataInfo missingshopdatainfo = new MissingShopDataInfo()
			VisitedShopDataInfo visitedshopdatainfo = new VisitedShopDataInfo()
			MobileElement shop = ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.LinearLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout["+i+"]/android.widget.TextView[1]")
			ProjectConstants.CURRENTVISITING_SHOPNAME = shop.getText()
			missingshopdatainfo.setShopname(shop.getText())
			missingshopdatainfo.setRemark("Normal Shops")
			visitedshopdatainfo.setShopname(shop.getText())
			visitedshopdatainfo.setRemark("Normal Shops")
			ProjectConstants.missingshopdatainfo.add(missingshopdatainfo)
			ProjectConstants.visitedshopdatainfo.add(visitedshopdatainfo)
			ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.LinearLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout["+i+"]").click()
			MobileBuiltInKeywords.verifyElementText(findTestObject("Object Repository/Validate_ShopOptionsScreen" , [('package') : ProjectConstants.PACKAGENAME]), "Options")
			//validate missing shop actions list e.g. start working / get routes etc...
			missingShopActionsList()
			MobileBuiltInKeywords.tap(findTestObject("Object Repository/StartWorking" , [('package') : ProjectConstants.PACKAGENAME]), 0)
			Mobile.delay(15)
			Mobile.verifyElementExist(findTestObject("Object Repository/CommonScreenElements/Validate_MapScreen" , [('package') : ProjectConstants.PACKAGENAME]), 60)
			MobileBuiltInKeywords.tap(findTestObject("Object Repository/CommonScreenElements/Location_CheckIn" , [('package') : ProjectConstants.PACKAGENAME]), 0)
			Mobile.verifyElementExist(findTestObject("Object Repository/CommonScreenElements/Validate_InfoPopUP" , [('package') : ProjectConstants.PACKAGENAME]), 0)
			MobileBuiltInKeywords.tap(findTestObject("Object Repository/CommonScreenElements/InfoPopUp_NoButton" , [('package') : ProjectConstants.PACKAGENAME]), 0)
			// shop closed to shop open
			if(i == 1){
				Mobile.callTestCase(findTestCase("Test Cases/ShopClosed/VisitShopClosed"), null)
				Mobile.verifyElementExist(findTestObject("Object Repository/Validate_ShopListScreen" , [('package') : ProjectConstants.PACKAGENAME]), 0)
				findShop(ProjectConstants.CURRENTVISITING_SHOPNAME)
				//validate missing shop remarks list e.g. shop open, closed etc...
				missingShopRemarksList()
				Mobile.callTestCase(findTestCase("Test Cases/ShopOpen/VisitShopOpen"), null)
				ProjectConstants.SCENARIO = "overwrite"
				Mobile.callTestCase(findTestCase("Test Cases/ShopOpen/VisitCategoryScenarios/VisitShopCategoriesWithDataVerification"), null)
				Mobile.callTestCase(findTestCase("Test Cases/ShopOpen/SaveShop"), null)
				Mobile.verifyElementExist(findTestObject("Object Repository/Validate_ShopListScreen" , [('package') : ProjectConstants.PACKAGENAME]), 0)
				for(int j=0; j<ProjectConstants.missingshopdatainfo.size(); j++){
					if(ProjectConstants.missingshopdatainfo.get(j).getShopname().equals(ProjectConstants.CURRENTVISITING_SHOPNAME)) {
						ProjectConstants.missingshopdatainfo.get(j).setShopchannel(ProjectConstants.CURRENTVISITING_SHOPCHANNEL)
						ProjectConstants.missingshopdatainfo.get(j).setScenario("Shop Level First Visit Before Overwriting")
						break
					}
				}
				for(int j=0; j<ProjectConstants.visitedshopdatainfo.size(); j++){
					if(ProjectConstants.visitedshopdatainfo.get(j).getShopname().equals(ProjectConstants.CURRENTVISITING_SHOPNAME)) {
						ProjectConstants.visitedshopdatainfo.get(j).setShopchannel(ProjectConstants.CURRENTVISITING_SHOPCHANNEL)
						ProjectConstants.visitedshopdatainfo.get(j).setShop_scenario("Shop Closed  ==>  Shop Open")
						String message = "(1) 'RTM visit frequency' with 'Once a week'\n"+
								String.format("%-44s%-100s","","'Pop Application' with 'No' remark")+"\n"+
								String.format("%-44s%-100s","","'Retailer Remarks' with 'OB not visiting' remark")+"\n"+
								String.format("%-44s%-100s","","'Hanger Availability' with 'Yes' remark")+"\n"+
								String.format("%-40s%-100s","","(2) 'RTM visit frequency' with 'Twice a week'")+"\n"+
								String.format("%-44s%-100s","","'Pop Application' with 'No' remark")+"\n"+
								String.format("%-44s%-100s","","'Retailer Remarks' with 'SM not visiting' remark")+"\n"+
								String.format("%-44s%-100s","","'Hanger Availability' with 'No' remark")
						ProjectConstants.visitedshopdatainfo.get(j).setOthercategories_scenarios(message)
						break
					}
				}
				Mobile.verifyElementExist(findTestObject("Object Repository/Validate_ShopListScreen" , [('package') : ProjectConstants.PACKAGENAME]), 0)
			}
			//shop open to shop closed
			else if(i == 2){
				//validate missing shop remarks list e.g. shop open, closed etc...
				missingShopRemarksList()
				Mobile.callTestCase(findTestCase("Test Cases/ShopOpen/VisitShopOpen"), null)
				ProjectConstants.SCENARIO = "first visit"
				Mobile.callTestCase(findTestCase("Test Cases/ShopOpen/VisitCategoryScenarios/VisitShopCategoriesWithDataVerification"), null)
				Mobile.callTestCase(findTestCase("Test Cases/ShopOpen/SaveShop"), null)
				Mobile.verifyElementExist(findTestObject("Object Repository/Validate_ShopListScreen" , [('package') : ProjectConstants.PACKAGENAME]), 0)
				findShop(ProjectConstants.CURRENTVISITING_SHOPNAME)
				Mobile.callTestCase(findTestCase("Test Cases/ShopClosed/VisitShopClosed"), null)
				Mobile.verifyElementExist(findTestObject("Object Repository/Validate_ShopListScreen" , [('package') : ProjectConstants.PACKAGENAME]), 0)
				for(int j=0; j<ProjectConstants.missingshopdatainfo.size(); j++){
					if(ProjectConstants.missingshopdatainfo.get(j).getShopname().equals(ProjectConstants.CURRENTVISITING_SHOPNAME)) {
						ProjectConstants.missingshopdatainfo.get(j).setShopchannel(ProjectConstants.CURRENTVISITING_SHOPCHANNEL)
						ProjectConstants.missingshopdatainfo.get(j).setScenario("Shop Overwrite Scenarios")
						break
					}
				}
				for(int j=0; j<ProjectConstants.visitedshopdatainfo.size(); j++){
					if(ProjectConstants.visitedshopdatainfo.get(j).getShopname().equals(ProjectConstants.CURRENTVISITING_SHOPNAME)) {
						ProjectConstants.visitedshopdatainfo.get(j).setShopchannel(ProjectConstants.CURRENTVISITING_SHOPCHANNEL)
						ProjectConstants.visitedshopdatainfo.get(j).setShop_scenario("Shop Open  ==>  Shop Closed")
						String message = "(1) 'RTM visit frequency' with 'Once a week'\n"+
								String.format("%-44s%-100s","","'Pop Application' with 'No' remark")+"\n"+
								String.format("%-44s%-100s","","'Retailer Remarks' with 'OB not visiting' remark")+"\n"+
								String.format("%-44s%-100s","","'Hanger Availability' with 'Yes' remark")+"\n"+
								String.format("%-40s%-100s","","(2) 'RTM visit frequency' with 'Twice a week'")+"\n"+
								String.format("%-44s%-100s","","'Pop Application' with 'No' remark")+"\n"+
								String.format("%-44s%-100s","","'Retailer Remarks' with 'SM not visiting' remark")+"\n"+
								String.format("%-44s%-100s","","'Hanger Availability' with 'No' remark")
						ProjectConstants.visitedshopdatainfo.get(j).setOthercategories_scenarios(message)
						break
					}
				}
				Mobile.verifyElementExist(findTestObject("Object Repository/Validate_ShopListScreen" , [('package') : ProjectConstants.PACKAGENAME]), 0)
			}
			//SKDNA to SKDNA
			else if(i == 3){
				Mobile.callTestCase(findTestCase("Test Cases/ShopKeeperDidNotAllow/VisitShopKeeperDidNotAllow"), null)
				Mobile.verifyElementExist(findTestObject("Object Repository/Validate_ShopListScreen" , [('package') : ProjectConstants.PACKAGENAME]), 0)
				findShop(ProjectConstants.CURRENTVISITING_SHOPNAME)
				Mobile.callTestCase(findTestCase("Test Cases/ShopKeeperDidNotAllow/OverwriteShopKeeperDidNotAllow"), null)
				Mobile.verifyElementExist(findTestObject("Object Repository/Validate_ShopListScreen" , [('package') : ProjectConstants.PACKAGENAME]), 0)
				for(int j=0; j<ProjectConstants.missingshopdatainfo.size(); j++){
					if(ProjectConstants.missingshopdatainfo.get(j).getShopname().equals(ProjectConstants.CURRENTVISITING_SHOPNAME)) {
						ProjectConstants.missingshopdatainfo.get(j).setShopchannel(ProjectConstants.CURRENTVISITING_SHOPCHANNEL)
						ProjectConstants.missingshopdatainfo.get(j).setScenario("Shop Overwrite Scenarios")
						break
					}
				}
				for(int j=0; j<ProjectConstants.visitedshopdatainfo.size(); j++){
					if(ProjectConstants.visitedshopdatainfo.get(j).getShopname().equals(ProjectConstants.CURRENTVISITING_SHOPNAME)) {
						ProjectConstants.visitedshopdatainfo.get(j).setShopchannel(ProjectConstants.CURRENTVISITING_SHOPCHANNEL)
						ProjectConstants.visitedshopdatainfo.get(j).setShop_scenario("SKDNA with 'Expiry Issue' remark  ==>  SKDNA with 'Others' remark")
						String message = "(1) 'RTM visit frequency' with 'Once a week'\n"+
								String.format("%-44s%-100s","","'Pop Application' with 'No' remark")+"\n"+
								String.format("%-44s%-100s","","'Retailer Remarks' with 'OB not visiting' remark")+"\n"+
								String.format("%-44s%-100s","","'Hanger Availability' with 'Yes' remark")+"\n"+
								String.format("%-40s%-100s","","(2) 'RTM visit frequency' with 'Twice a week'")+"\n"+
								String.format("%-44s%-100s","","'Pop Application' with 'No' remark")+"\n"+
								String.format("%-44s%-100s","","'Retailer Remarks' with 'SM not visiting' remark")+"\n"+
								String.format("%-44s%-100s","","'Hanger Availability' with 'No' remark")
						ProjectConstants.visitedshopdatainfo.get(j).setOthercategories_scenarios(message)
						break
					}
				}
				Mobile.verifyElementExist(findTestObject("Object Repository/Validate_ShopListScreen" , [('package') : ProjectConstants.PACKAGENAME]), 0)
			}
			//shop permanently closed to shop not found
			else if(i == 4){
				Mobile.callTestCase(findTestCase("Test Cases/ShopPermanentlyClosed/VisitShopPermanentlyClosed"), null)
				Mobile.verifyElementExist(findTestObject("Object Repository/Validate_ShopListScreen" , [('package') : ProjectConstants.PACKAGENAME]), 0)
				findShop(ProjectConstants.CURRENTVISITING_SHOPNAME)
				Mobile.callTestCase(findTestCase("Test Cases/ShopNotFound/VisitShopNotFound"), null)
				Mobile.verifyElementExist(findTestObject("Object Repository/Validate_ShopListScreen" , [('package') : ProjectConstants.PACKAGENAME]), 0)
				for(int j=0; j<ProjectConstants.missingshopdatainfo.size(); j++){
					if(ProjectConstants.missingshopdatainfo.get(j).getShopname().equals(ProjectConstants.CURRENTVISITING_SHOPNAME)) {
						ProjectConstants.missingshopdatainfo.get(j).setShopchannel(ProjectConstants.CURRENTVISITING_SHOPCHANNEL)
						ProjectConstants.missingshopdatainfo.get(j).setScenario("Shop Overwrite Scenarios")
						break
					}
				}
				for(int j=0; j<ProjectConstants.visitedshopdatainfo.size(); j++){
					if(ProjectConstants.visitedshopdatainfo.get(j).getShopname().equals(ProjectConstants.CURRENTVISITING_SHOPNAME)) {
						ProjectConstants.visitedshopdatainfo.get(j).setShopchannel(ProjectConstants.CURRENTVISITING_SHOPCHANNEL)
						ProjectConstants.visitedshopdatainfo.get(j).setShop_scenario("Shop Permanently Closed  ==>  Shop Not Found")
						String message = "(1) 'RTM visit frequency' with 'Once a week'\n"+
								String.format("%-44s%-100s","","'Pop Application' with 'No' remark")+"\n"+
								String.format("%-44s%-100s","","'Retailer Remarks' with 'OB not visiting' remark")+"\n"+
								String.format("%-44s%-100s","","'Hanger Availability' with 'Yes' remark")+"\n"+
								String.format("%-40s%-100s","","(2) 'RTM visit frequency' with 'Twice a week'")+"\n"+
								String.format("%-44s%-100s","","'Pop Application' with 'No' remark")+"\n"+
								String.format("%-44s%-100s","","'Retailer Remarks' with 'SM not visiting' remark")+"\n"+
								String.format("%-44s%-100s","","'Hanger Availability' with 'No' remark")
						ProjectConstants.visitedshopdatainfo.get(j).setOthercategories_scenarios(message)
						break
					}
				}
				Mobile.verifyElementExist(findTestObject("Object Repository/Validate_ShopListScreen" , [('package') : ProjectConstants.PACKAGENAME]), 0)
			}
			//shop closed to shop permanently closed
			else if(i == 5){
				Mobile.callTestCase(findTestCase("Test Cases/ShopClosed/VisitShopClosed"), null)
				Mobile.verifyElementExist(findTestObject("Object Repository/Validate_ShopListScreen" , [('package') : ProjectConstants.PACKAGENAME]), 0)
				findShop(ProjectConstants.CURRENTVISITING_SHOPNAME)
				Mobile.callTestCase(findTestCase("Test Cases/ShopPermanentlyClosed/VisitShopPermanentlyClosed"), null)
				Mobile.verifyElementExist(findTestObject("Object Repository/Validate_ShopListScreen" , [('package') : ProjectConstants.PACKAGENAME]), 0)
				for(int j=0; j<ProjectConstants.missingshopdatainfo.size(); j++){
					if(ProjectConstants.missingshopdatainfo.get(j).getShopname().equals(ProjectConstants.CURRENTVISITING_SHOPNAME)) {
						ProjectConstants.missingshopdatainfo.get(j).setShopchannel(ProjectConstants.CURRENTVISITING_SHOPCHANNEL)
						ProjectConstants.missingshopdatainfo.get(j).setScenario("Shop Overwrite Scenario")
						break
					}
				}
				for(int j=0; j<ProjectConstants.visitedshopdatainfo.size(); j++){
					if(ProjectConstants.visitedshopdatainfo.get(j).getShopname().equals(ProjectConstants.CURRENTVISITING_SHOPNAME)) {
						ProjectConstants.visitedshopdatainfo.get(j).setShopchannel(ProjectConstants.CURRENTVISITING_SHOPCHANNEL)
						ProjectConstants.visitedshopdatainfo.get(j).setShop_scenario("Shop Closed  ==>  Shop Permanently Closed")
						String message = "(1) 'RTM visit frequency' with 'Once a week'\n"+
								String.format("%-44s%-100s","","'Pop Application' with 'No' remark")+"\n"+
								String.format("%-44s%-100s","","'Retailer Remarks' with 'OB not visiting' remark")+"\n"+
								String.format("%-44s%-100s","","'Hanger Availability' with 'Yes' remark")+"\n"+
								String.format("%-40s%-100s","","(2) 'RTM visit frequency' with 'Twice a week'")+"\n"+
								String.format("%-44s%-100s","","'Pop Application' with 'No' remark")+"\n"+
								String.format("%-44s%-100s","","'Retailer Remarks' with 'SM not visiting' remark")+"\n"+
								String.format("%-44s%-100s","","'Hanger Availability' with 'No' remark")
						ProjectConstants.visitedshopdatainfo.get(j).setOthercategories_scenarios(message)
						break
					}
				}
				Mobile.verifyElementExist(findTestObject("Object Repository/Validate_ShopListScreen" , [('package') : ProjectConstants.PACKAGENAME]), 0)
			}
			//shop open to shop open
			else if(i == 6){
				//validate missing shop remarks list e.g. shop open, closed etc...
				missingShopRemarksList()
				Mobile.callTestCase(findTestCase("Test Cases/ShopOpen/VisitShopOpen"), null)
				ProjectConstants.SCENARIO = "first visit"
				ProjectConstants.SHOP_ATTEMPT = 1
				Mobile.callTestCase(findTestCase("Test Cases/ShopOpen/VisitCategoryScenarios/VisitShopCategoriesWithOverwriteScenarios"), null)
				Mobile.callTestCase(findTestCase("Test Cases/ShopOpen/SaveShop"), null)
				Mobile.verifyElementExist(findTestObject("Object Repository/Validate_ShopListScreen" , [('package') : ProjectConstants.PACKAGENAME]), 0)
				findShop(ProjectConstants.CURRENTVISITING_SHOPNAME)
				Mobile.callTestCase(findTestCase("Test Cases/ShopOpen/VisitShopOpen"), null)
				ProjectConstants.SCENARIO = "overwrite"
				Mobile.callTestCase(findTestCase("Test Cases/ShopOpen/VisitCategoryScenarios/VisitShopCategoriesWithOverwritingScenarios"), null)
				Mobile.callTestCase(findTestCase("Test Cases/ShopOpen/SaveShop"), null)
				Mobile.verifyElementExist(findTestObject("Object Repository/Validate_ShopListScreen" , [('package') : ProjectConstants.PACKAGENAME]), 0)
				for(int j=0; j<ProjectConstants.missingshopdatainfo.size(); j++){
					if(ProjectConstants.missingshopdatainfo.get(j).getShopname().equals(ProjectConstants.CURRENTVISITING_SHOPNAME)) {
						ProjectConstants.missingshopdatainfo.get(j).setShopchannel(ProjectConstants.CURRENTVISITING_SHOPCHANNEL)
						String message = "Shop Overwrite Scenarios"
						ProjectConstants.missingshopdatainfo.get(j).setScenario(message)
						break
					}
				}
				for(int j=0; j<ProjectConstants.visitedshopdatainfo.size(); j++){
					if(ProjectConstants.visitedshopdatainfo.get(j).getShopname().equals(ProjectConstants.CURRENTVISITING_SHOPNAME)) {
						ProjectConstants.visitedshopdatainfo.get(j).setShopchannel(ProjectConstants.CURRENTVISITING_SHOPCHANNEL)
						ProjectConstants.visitedshopdatainfo.get(j).setShop_scenario("Shop Open  ==>  Shop Open")
						String message = "(1) 'RTM visit frequency' with 'Once a week'\n"+
								String.format("%-44s%-100s","","'Pop Application' with 'No' remark")+"\n"+
								String.format("%-44s%-100s","","'Retailer Remarks' with 'OB not visiting' remark")+"\n"+
								String.format("%-44s%-100s","","'Hanger Availability' with 'Yes' remark")+"\n"+
								String.format("%-40s%-100s","","(2) 'RTM visit frequency' with 'Twice a week'")+"\n"+
								String.format("%-44s%-100s","","'Pop Application' with 'No' remark")+"\n"+
								String.format("%-44s%-100s","","'Retailer Remarks' with 'SM not visiting' remark")+"\n"+
								String.format("%-44s%-100s","","'Hanger Availability' with 'No' remark")
						ProjectConstants.visitedshopdatainfo.get(j).setOthercategories_scenarios(message)
						break
					}
				}
				Mobile.verifyElementExist(findTestObject("Object Repository/Validate_ShopListScreen" , [('package') : ProjectConstants.PACKAGENAME]), 0)
			}
			else{
				break
			}
		}
		/****Generate Report and Display Data in Report****/
		DisplayReportKeywords.displayDataInReport()
	}

	/*************************************************
	 CATEGORY LEVEL OVERWRITING SCENARIOS
	 ************************************************/

	def visitShopsWith_CategoryLevel_OverwritingScenarios(MobileElement shop){
		MissingShopDataInfo missingshopdatainfo = new MissingShopDataInfo()
		VisitedShopDataInfo visitedshopdatainfo = new VisitedShopDataInfo()
		ProjectConstants.CURRENTVISITING_SHOPNAME = shop.getText()
		missingshopdatainfo.setShopname(shop.getText())
		missingshopdatainfo.setRemark("Normal Shops")
		visitedshopdatainfo.setShopname(shop.getText())
		visitedshopdatainfo.setRemark("Normal Shops")
		ProjectConstants.missingshopdatainfo.add(missingshopdatainfo)
		ProjectConstants.visitedshopdatainfo.add(visitedshopdatainfo)
		shop.click()
		MobileBuiltInKeywords.verifyElementText(findTestObject("Object Repository/Validate_ShopOptionsScreen" , [('package') : ProjectConstants.PACKAGENAME]), "Options")
		//validate missing shop actions list e.g. start working / get routes etc...
		missingShopActionsList()
		MobileBuiltInKeywords.tap(findTestObject("Object Repository/StartWorking" , [('package') : ProjectConstants.PACKAGENAME]), 0)
		Mobile.delay(15)
		Mobile.verifyElementExist(findTestObject("Object Repository/CommonScreenElements/Validate_MapScreen" , [('package') : ProjectConstants.PACKAGENAME]), 60)
		MobileBuiltInKeywords.tap(findTestObject("Object Repository/CommonScreenElements/Location_CheckIn" , [('package') : ProjectConstants.PACKAGENAME]), 0)
		Mobile.verifyElementExist(findTestObject("Object Repository/CommonScreenElements/Validate_InfoPopUP" , [('package') : ProjectConstants.PACKAGENAME]), 0)
		MobileBuiltInKeywords.tap(findTestObject("Object Repository/CommonScreenElements/InfoPopUp_NoButton" , [('package') : ProjectConstants.PACKAGENAME]), 0)
		//validate missing shop remarks list e.g. shop open, closed etc...
		missingShopRemarksList()
		Mobile.callTestCase(findTestCase("Test Cases/ShopOpen/VisitShopOpen"), null)
		ProjectConstants.SCENARIO = "Overwrite"
		Mobile.callTestCase(findTestCase("ShopOpen/VisitCategoryScenarios/VisitShopCategoriesWithOverwritingScenarios"), null)
		Mobile.callTestCase(findTestCase("Test Cases/ShopOpen/SaveShop"), null)
		for(int j=0; j<ProjectConstants.missingshopdatainfo.size(); j++){
			if(ProjectConstants.missingshopdatainfo.get(j).getShopname().equals(ProjectConstants.CURRENTVISITING_SHOPNAME)) {
				ProjectConstants.missingshopdatainfo.get(j).setShopchannel(ProjectConstants.CURRENTVISITING_SHOPCHANNEL)
				ProjectConstants.missingshopdatainfo.get(j).setScenario("Data Verification")
				break
			}
		}
		for(int j=0; j<ProjectConstants.visitedshopdatainfo.size(); j++){
			if(ProjectConstants.visitedshopdatainfo.get(j).getShopname().equals(ProjectConstants.CURRENTVISITING_SHOPNAME)) {
				ProjectConstants.visitedshopdatainfo.get(j).setShopchannel(ProjectConstants.CURRENTVISITING_SHOPCHANNEL)
				ProjectConstants.visitedshopdatainfo.get(j).setShop_scenario("Shop Open")
				String message = "(1) 'RTM visit frequency' with 'Once a week'\n"+
						String.format("%-44s%-100s","","'Pop Application' with 'No' remark")+"\n"+
						String.format("%-44s%-100s","","'Retailer Remarks' with 'OB not visiting' remark")+"\n"+
						String.format("%-44s%-100s","","'Hanger Availability' with 'Yes' remark")+"\n"+
						String.format("%-40s%-100s","","(2) 'RTM visit frequency' with 'Twice a week'")+"\n"+
						String.format("%-44s%-100s","","'Pop Application' with 'No' remark")+"\n"+
						String.format("%-44s%-100s","","'Retailer Remarks' with 'SM not visiting' remark")+"\n"+
						String.format("%-44s%-100s","","'Hanger Availability' with 'No' remark")
				ProjectConstants.visitedshopdatainfo.get(j).setOthercategories_scenarios(message)
				break
			}
		}
	}

	@Keyword
	def visitShopsWith_CategoryLevel_OverwritingScenarios(){
		TouchAction touchaction = new TouchAction(ProjectConstants.DRIVER)
		int index, swipecounter
		int _shop = 1
		String visitedshop
		MobileElement listcontainer = ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.LinearLayout[1]/android.widget.ListView[1]")
		ArrayList<MobileElement> shops = listcontainer.findElementsByClassName("android.widget.TextView")
		for(int i=0; i< 4; i++){
			MobileElement shop = shops.get(i)
			visitedshop = shop.getText()
			ProjectConstants.SHOP_ATTEMPT = _shop;
			visitShopsWith_CategoryLevel_OverwritingScenarios(shop)
			Mobile.verifyElementExist(findTestObject("Object Repository/Validate_ShopListScreen" , [('package') : ProjectConstants.PACKAGENAME]), 0)
			_shop++
		}
		touchaction.press(0, 230).waitAction(Duration.ofMillis(500)).moveTo(0, 200).release().perform()
		Thread.sleep(500)
		while(true){
			swipecounter++
			index = listcontainer.findElementsByClassName("android.widget.TextView").size()
			MobileElement lastitembeforeswipe = ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.LinearLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout["+index+"]/android.widget.TextView[1]")
			String lastitemnamebeforeswipe = lastitembeforeswipe.getText()
			MobileElement startpoint = ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.LinearLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout["+index+"]")
			MobileElement endpoint = ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.LinearLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout[1]")
			touchaction.press(startpoint).waitAction(Duration.ofMillis(2000)).moveTo(endpoint).release().perform()
			Thread.sleep(500)
			index = listcontainer.findElementsByClassName("android.widget.TextView").size()
			MobileElement lastitemafterswipe = ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.LinearLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout["+index+"]/android.widget.TextView[1]")
			String lastitemnameafterswipe = lastitemafterswipe.getText()
			if(lastitemnamebeforeswipe.equalsIgnoreCase(lastitemnameafterswipe)){
				break
			}
			else{
				shops = listcontainer.findElementsByClassName("android.widget.TextView")
				for(int i=0; i< shops.size(); i++){
					MobileElement shop = shops.get(i)
					//if current element match with last visited element
					if(visitedshop.equalsIgnoreCase(shop.getText())){
						//if current element is not a last element of screen
						if(i < (shops.size()-1)){
							ProjectConstants.SHOP_ATTEMPT = _shop;
							shop = shops.get((i+1))
							visitedshop = shop.getText()
							visitShopsWith_CategoryLevel_OverwritingScenarios(shop)
							//swipe to last visited item screen view
							touchaction.press(0, 230).waitAction(Duration.ofMillis(500)).moveTo(0, 200).release().perform()
							Thread.sleep(500)
							for(int j=1; j<= swipecounter; j++){
								index = listcontainer.findElementsByClassName("android.widget.TextView").size()
								startpoint = ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.LinearLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout["+index+"]")
								endpoint = ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.LinearLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout[1]")
								touchaction.press(startpoint).waitAction(Duration.ofMillis(2000)).moveTo(endpoint).release().perform()
								Thread.sleep(500)
							}
						}
					}
				}
			}
			Mobile.verifyElementExist(findTestObject("Object Repository/Validate_ShopListScreen" , [('package') : ProjectConstants.PACKAGENAME]), 0)
			_shop++
		}
		/****Generate Report and Display Data in Report****/
		DisplayReportKeywords.displayDataInReport()
	}

	/*************************************************
	 CATEGORY LEVEL OVERWRITE SCENARIOS
	 ************************************************/

	def visitShopsWith_CategoryLevel_OverwriteScenarios(MobileElement shop){
		MissingShopDataInfo missingshopdatainfo = new MissingShopDataInfo()
		VisitedShopDataInfo visitedshopdatainfo = new VisitedShopDataInfo()
		ProjectConstants.CURRENTVISITING_SHOPNAME = shop.getText()
		missingshopdatainfo.setShopname(shop.getText())
		missingshopdatainfo.setRemark("Normal Shops")
		visitedshopdatainfo.setShopname(shop.getText())
		visitedshopdatainfo.setRemark("Normal Shops")
		ProjectConstants.missingshopdatainfo.add(missingshopdatainfo)
		ProjectConstants.visitedshopdatainfo.add(visitedshopdatainfo)
		shop.click()
		MobileBuiltInKeywords.verifyElementText(findTestObject("Object Repository/Validate_ShopOptionsScreen" , [('package') : ProjectConstants.PACKAGENAME]), "Options")
		//validate missing shop actions list e.g. start working / get routes etc...
		missingShopActionsList()
		MobileBuiltInKeywords.tap(findTestObject("Object Repository/StartWorking" , [('package') : ProjectConstants.PACKAGENAME]), 0)
		Mobile.delay(15)
		Mobile.verifyElementExist(findTestObject("Object Repository/CommonScreenElements/Validate_MapScreen" , [('package') : ProjectConstants.PACKAGENAME]), 60)
		MobileBuiltInKeywords.tap(findTestObject("Object Repository/CommonScreenElements/Location_CheckIn" , [('package') : ProjectConstants.PACKAGENAME]), 0)
		Mobile.verifyElementExist(findTestObject("Object Repository/CommonScreenElements/Validate_InfoPopUP" , [('package') : ProjectConstants.PACKAGENAME]), 0)
		MobileBuiltInKeywords.tap(findTestObject("Object Repository/CommonScreenElements/InfoPopUp_NoButton" , [('package') : ProjectConstants.PACKAGENAME]), 0)
		//validate missing shop remarks list e.g. shop open, closed etc...
		missingShopRemarksList()
		Mobile.callTestCase(findTestCase("Test Cases/ShopOpen/VisitShopOpen"), null)
		ProjectConstants.SCENARIO = "first visit"
		Mobile.callTestCase(findTestCase("Test Cases/ShopOpen/VisitCategoryScenarios/VisitShopCategoriesWithOverwriteScenarios"), null)
		ProjectConstants.SCENARIO = "Overwrite"
		Mobile.callTestCase(findTestCase("ShopOpen/VisitCategoryScenariosWithOverwritePopUp/VisitShopCategoriesWithOverwritingScenarios"), null)
		Mobile.callTestCase(findTestCase("Test Cases/ShopOpen/SaveShop"), null)
		for(int j=0; j<ProjectConstants.missingshopdatainfo.size(); j++){
			if(ProjectConstants.missingshopdatainfo.get(j).getShopname().equals(ProjectConstants.CURRENTVISITING_SHOPNAME)) {
				ProjectConstants.missingshopdatainfo.get(j).setShopchannel(ProjectConstants.CURRENTVISITING_SHOPCHANNEL)
				ProjectConstants.missingshopdatainfo.get(j).setScenario("Data Verification")
				break
			}
		}
		for(int j=0; j<ProjectConstants.visitedshopdatainfo.size(); j++){
			if(ProjectConstants.visitedshopdatainfo.get(j).getShopname().equals(ProjectConstants.CURRENTVISITING_SHOPNAME)) {
				ProjectConstants.visitedshopdatainfo.get(j).setShopchannel(ProjectConstants.CURRENTVISITING_SHOPCHANNEL)
				ProjectConstants.visitedshopdatainfo.get(j).setShop_scenario("Shop Open")
				String message = "(1) 'RTM visit frequency' with 'Once a week'\n"+
						String.format("%-44s%-100s","","'Pop Application' with 'No' remark")+"\n"+
						String.format("%-44s%-100s","","'Retailer Remarks' with 'OB not visiting' remark")+"\n"+
						String.format("%-44s%-100s","","'Hanger Availability' with 'Yes' remark")+"\n"+
						String.format("%-40s%-100s","","(2) 'RTM visit frequency' with 'Twice a week'")+"\n"+
						String.format("%-44s%-100s","","'Pop Application' with 'No' remark")+"\n"+
						String.format("%-44s%-100s","","'Retailer Remarks' with 'SM not visiting' remark")+"\n"+
						String.format("%-44s%-100s","","'Hanger Availability' with 'No' remark")
				ProjectConstants.visitedshopdatainfo.get(j).setOthercategories_scenarios(message)
				break
			}
		}
	}
	@Keyword
	def visitShopsWith_CategoryLevel_OverwriteScenarios(){
		TouchAction touchaction = new TouchAction(ProjectConstants.DRIVER)
		int index, swipecounter
		int _shop = 1
		String visitedshop
		MobileElement listcontainer = ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.LinearLayout[1]/android.widget.ListView[1]")
		ArrayList<MobileElement> shops = listcontainer.findElementsByClassName("android.widget.TextView")
		for(int i=0; i< 5; i++){
			MobileElement shop = shops.get(i)
			visitedshop = shop.getText()
			ProjectConstants.SHOP_ATTEMPT = _shop
			visitShopsWith_CategoryLevel_OverwriteScenarios(shop)
			Mobile.verifyElementExist(findTestObject("Object Repository/Validate_ShopListScreen" , [('package') : ProjectConstants.PACKAGENAME]), 0)
			_shop++
		}
		touchaction.press(0, 220).waitAction(Duration.ofMillis(500)).moveTo(0, 200).release().perform()
		Thread.sleep(500)
		//		while(true){
		//			swipecounter++
		//			index = listcontainer.findElementsByClassName("android.widget.TextView").size()
		//			MobileElement lastitembeforeswipe = ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.LinearLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout["+index+"]/android.widget.TextView[1]")
		//			String lastitemnamebeforeswipe = lastitembeforeswipe.getText()
		//			MobileElement startpoint = ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.LinearLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout["+index+"]")
		//			MobileElement endpoint = ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.LinearLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout[1]")
		//			touchaction.press(startpoint).waitAction(Duration.ofMillis(2000)).moveTo(endpoint).release().perform()
		//			Thread.sleep(500)
		//			index = listcontainer.findElementsByClassName("android.widget.TextView").size()
		//			MobileElement lastitemafterswipe = ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.LinearLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout["+index+"]/android.widget.TextView[1]")
		//			String lastitemnameafterswipe = lastitemafterswipe.getText()
		//			if(lastitemnamebeforeswipe.equalsIgnoreCase(lastitemnameafterswipe)){
		//				break
		//			}
		//			else{
		//				shops = listcontainer.findElementsByClassName("android.widget.TextView")
		//				for(int i=0; i< shops.size(); i++){
		//					MobileElement shop = shops.get(i)
		//					//if current element match with last visited element
		//					if(visitedshop.equalsIgnoreCase(shop.getText())){
		//						//if current element is not a last element of screen
		//						if(i < (shops.size()-1)){
		//							_shop++
		//							ProjectConstants.SHOP_ATTEMPT = _shop;
		//							shop = shops.get((i+1))
		//							visitedshop = shop.getText()
		//							visitShopsWith_CategoryLevel_OverwriteScenarios(shop)
		//							//swipe to last visited item screen view
		//							touchaction.press(0, 230).waitAction(Duration.ofMillis(500)).moveTo(0, 200).release().perform()
		//							Thread.sleep(500)
		//							for(int j=1; j<= swipecounter; j++){
		//								index = listcontainer.findElementsByClassName("android.widget.TextView").size()
		//								startpoint = ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.LinearLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout["+index+"]")
		//								endpoint = ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.LinearLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout[1]")
		//								touchaction.press(startpoint).waitAction(Duration.ofMillis(2000)).moveTo(endpoint).release().perform()
		//								Thread.sleep(500)
		//							}
		//						}
		//					}
		//				}
		//			}
		//			Mobile.verifyElementExist(findTestObject("Object Repository/Validate_ShopListScreen" , [('package') : ProjectConstants.PACKAGENAME]), 0)
		//			_shop++
		//		}
		/****Generate Report and Display Data in Report****/
		DisplayReportKeywords.displayDataInReport()
	}

	/*************************************************
	 CHILLER LEVEL OVERWRITE SCENARIOS
	 ************************************************/
	def visitShopsWith_ChillerLevel_OverwriteScenarios(MobileElement shop){
		MissingShopDataInfo missingshopdatainfo = new MissingShopDataInfo()
		VisitedShopDataInfo visitedshopdatainfo = new VisitedShopDataInfo()
		ProjectConstants.CURRENTVISITING_SHOPNAME = shop.getText()
		missingshopdatainfo.setShopname(shop.getText())
		missingshopdatainfo.setRemark("Normal Shops")
		visitedshopdatainfo.setShopname(shop.getText())
		visitedshopdatainfo.setRemark("Normal Shops")
		ProjectConstants.missingshopdatainfo.add(missingshopdatainfo)
		ProjectConstants.visitedshopdatainfo.add(visitedshopdatainfo)
		shop.click()
		MobileBuiltInKeywords.verifyElementText(findTestObject("Object Repository/Validate_ShopOptionsScreen" , [('package') : ProjectConstants.PACKAGENAME]), "Options")
		//validate missing shop actions list e.g. start working / get routes etc...
		missingShopActionsList()
		MobileBuiltInKeywords.tap(findTestObject("Object Repository/StartWorking" , [('package') : ProjectConstants.PACKAGENAME]), 0)
		Mobile.delay(15)
		Mobile.verifyElementExist(findTestObject("Object Repository/CommonScreenElements/Validate_MapScreen" , [('package') : ProjectConstants.PACKAGENAME]), 60)
		MobileBuiltInKeywords.tap(findTestObject("Object Repository/CommonScreenElements/Location_CheckIn" , [('package') : ProjectConstants.PACKAGENAME]), 0)
		Mobile.verifyElementExist(findTestObject("Object Repository/CommonScreenElements/Validate_InfoPopUP" , [('package') : ProjectConstants.PACKAGENAME]), 0)
		MobileBuiltInKeywords.tap(findTestObject("Object Repository/CommonScreenElements/InfoPopUp_NoButton" , [('package') : ProjectConstants.PACKAGENAME]), 0)
		//validate missing shop remarks list e.g. shop open, closed etc...
		missingShopRemarksList()
		Mobile.callTestCase(findTestCase("Test Cases/ShopOpen/VisitShopOpen"), null)
		ProjectConstants.SCENARIO = "first visit"
		Mobile.callTestCase(findTestCase("Test Cases/ShopOpen/VisitCategoryScenarios/VisitShopCategoriesWith_ChillerLevelOverwriteScenarios"), null)
		Mobile.callTestCase(findTestCase("Test Cases/ShopOpen/SaveShop"), null)
		for(int j=0; j<ProjectConstants.missingshopdatainfo.size(); j++){
			if(ProjectConstants.missingshopdatainfo.get(j).getShopname().equals(ProjectConstants.CURRENTVISITING_SHOPNAME)) {
				ProjectConstants.missingshopdatainfo.get(j).setShopchannel(ProjectConstants.CURRENTVISITING_SHOPCHANNEL)
				ProjectConstants.missingshopdatainfo.get(j).setScenario("Data Verification")
				break
			}
		}
		for(int j=0; j<ProjectConstants.visitedshopdatainfo.size(); j++){
			if(ProjectConstants.visitedshopdatainfo.get(j).getShopname().equals(ProjectConstants.CURRENTVISITING_SHOPNAME)) {
				ProjectConstants.visitedshopdatainfo.get(j).setShopchannel(ProjectConstants.CURRENTVISITING_SHOPCHANNEL)
				ProjectConstants.visitedshopdatainfo.get(j).setShop_scenario("Shop Open")
				String message = "(1) 'RTM visit frequency' with 'Once a week'\n"+
						String.format("%-44s%-100s","","'Pop Application' with 'No' remark")+"\n"+
						String.format("%-44s%-100s","","'Retailer Remarks' with 'OB not visiting' remark")+"\n"+
						String.format("%-44s%-100s","","'Hanger Availability' with 'Yes' remark")+"\n"+
						String.format("%-40s%-100s","","(2) 'RTM visit frequency' with 'Twice a week'")+"\n"+
						String.format("%-44s%-100s","","'Pop Application' with 'No' remark")+"\n"+
						String.format("%-44s%-100s","","'Retailer Remarks' with 'SM not visiting' remark")+"\n"+
						String.format("%-44s%-100s","","'Hanger Availability' with 'No' remark")
				ProjectConstants.visitedshopdatainfo.get(j).setOthercategories_scenarios(message)
				break
			}
		}
	}
	@Keyword
	def visitShopsWith_ChillerLevel_OverwriteScenarios(){
		TouchAction touchaction = new TouchAction(ProjectConstants.DRIVER)
		int index, swipecounter
		int _shop = 5
		String visitedshop
		MobileElement listcontainer = ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.LinearLayout[1]/android.widget.ListView[1]")
		ArrayList<MobileElement> shops = listcontainer.findElementsByClassName("android.widget.TextView")
		for(int i=4; i< 7; i++){
			MobileElement shop = shops.get(i)
			visitedshop = shop.getText()
			ProjectConstants.SHOP_ATTEMPT = _shop;
			visitShopsWith_ChillerLevel_OverwriteScenarios(shop)
			Mobile.verifyElementExist(findTestObject("Object Repository/Validate_ShopListScreen" , [('package') : ProjectConstants.PACKAGENAME]), 0)
			_shop++
		}
		touchaction.press(0, 220).waitAction(Duration.ofMillis(500)).moveTo(0, 200).release().perform()
		Thread.sleep(500)
		//		while(true){
		//			swipecounter++
		//			index = listcontainer.findElementsByClassName("android.widget.TextView").size()
		//			MobileElement lastitembeforeswipe = ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.LinearLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout["+index+"]/android.widget.TextView[1]")
		//			String lastitemnamebeforeswipe = lastitembeforeswipe.getText()
		//			MobileElement startpoint = ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.LinearLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout["+index+"]")
		//			MobileElement endpoint = ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.LinearLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout[1]")
		//			touchaction.press(startpoint).waitAction(Duration.ofMillis(2000)).moveTo(endpoint).release().perform()
		//			Thread.sleep(500)
		//			index = listcontainer.findElementsByClassName("android.widget.TextView").size()
		//			MobileElement lastitemafterswipe = ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.LinearLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout["+index+"]/android.widget.TextView[1]")
		//			String lastitemnameafterswipe = lastitemafterswipe.getText()
		//			if(lastitemnamebeforeswipe.equalsIgnoreCase(lastitemnameafterswipe)){
		//				break
		//			}
		//			else{
		//				shops = listcontainer.findElementsByClassName("android.widget.TextView")
		//				for(int i=0; i< shops.size(); i++){
		//					MobileElement shop = shops.get(i)
		//					//if current element match with last visited element
		//					if(visitedshop.equalsIgnoreCase(shop.getText())){
		//						//if current element is not a last element of screen
		//						if(i < (shops.size()-1)){
		//							_shop++
		//							ProjectConstants.SHOP_ATTEMPT = _shop;
		//							shop = shops.get((i+1))
		//							visitedshop = shop.getText()
		//							visitShopsWith_ChillerLevel_OverwriteScenarios(shop)
		//							//swipe to last visited item screen view
		//							touchaction.press(0, 230).waitAction(Duration.ofMillis(500)).moveTo(0, 200).release().perform()
		//							Thread.sleep(500)
		//							for(int j=1; j<= swipecounter; j++){
		//								index = listcontainer.findElementsByClassName("android.widget.TextView").size()
		//								startpoint = ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.LinearLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout["+index+"]")
		//								endpoint = ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.LinearLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout[1]")
		//								touchaction.press(startpoint).waitAction(Duration.ofMillis(2000)).moveTo(endpoint).release().perform()
		//								Thread.sleep(500)
		//							}
		//						}
		//					}
		//				}
		//			}
		//			Mobile.verifyElementExist(findTestObject("Object Repository/Validate_ShopListScreen" , [('package') : ProjectConstants.PACKAGENAME]), 0)
		//			_shop++
		//		}
		/****Generate Report and Display Data in Report****/
		DisplayReportKeywords.displayDataInReport()
	}
}
