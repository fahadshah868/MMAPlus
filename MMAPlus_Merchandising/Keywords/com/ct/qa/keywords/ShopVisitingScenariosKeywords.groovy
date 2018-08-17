package com.ct.qa.keywords

import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.checkpoint.Checkpoint
import com.kms.katalon.core.checkpoint.CheckpointFactory
import com.kms.katalon.core.logging.KeywordLogger
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
import MobileBuiltInKeywords as Mobile
import WSBuiltInKeywords as WS
import WebUiBuiltInKeywords as WebUI
import com.ct.qa.constants.ProjectConstants
import com.ct.qa.struct.ShopProductsData
import com.ct.qa.struct.VisitedCategoryData
import com.ct.qa.struct.VisitedShopDataInfo
import com.ct.qa.struct.MissingCategoryData
import com.ct.qa.struct.MissingShopDataInfo

public class ShopVisitingScenariosKeywords{
	def findShop(){
		int index = 0
		int totalshops = ProjectConstants.DRIVER.findElementsByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.ListView[1]/*").size()
		for(int i=1; i<=totalshops; i++){
			MobileElement shop = ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout["+i+"]/android.widget.TextView[1]")
			String shopname = shop.getText()
			if(shopname.equalsIgnoreCase(ProjectConstants.CURRENTVISITING_SHOPNAME)){
				ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout["+i+"]").click()
				MobileBuiltInKeywords.verifyElementText(findTestObject("Object Repository/Validate_ShopOptionsScreen"), "Options")
				MobileBuiltInKeywords.tap(findTestObject("Object Repository/StartWorking"), 0)
				ProjectConstants.visitPopUpForOverwriting()
				Mobile.delay(15)
				Mobile.verifyElementExist(findTestObject("Object Repository/CommonScreenElements/Validate_MapScreen"), 60)
				MobileBuiltInKeywords.tap(findTestObject("Object Repository/CommonScreenElements/Location_CheckIn"), 0)
				Mobile.verifyElementExist(findTestObject("Object Repository/CommonScreenElements/Validate_InfoPopUP"), 0)
				MobileBuiltInKeywords.tap(findTestObject("Object Repository/CommonScreenElements/InfoPopUp_NoButton"), 0)
				break
			}
			else{
			}
		}
		while(true){
			index = ProjectConstants.DRIVER.findElementsByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.ListView[1]/*").size()
			MobileElement lastitembeforeswipe  = ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout["+index+"]/android.widget.TextView[1]")
			String lastitemnamebeforeswipe = lastitembeforeswipe.getText()
			Mobile.swipe(0, 292, 0, 200)
			index = ProjectConstants.DRIVER.findElementsByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.ListView[1]/*").size()
			MobileElement lastitemafterswipe = ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout["+index+"]/android.widget.TextView[1]")
			String lastitemnameafterswipe = lastitemafterswipe.getText()
			if(lastitemnamebeforeswipe.equalsIgnoreCase(lastitemnameafterswipe)){
				break
			}
			else{
				if(lastitemnameafterswipe.equalsIgnoreCase(ProjectConstants.CURRENTVISITING_SHOPNAME)){
					ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout["+i+"]").click()
					MobileBuiltInKeywords.verifyElementText(findTestObject("Object Repository/Validate_ShopOptionsScreen"), "Options")
					MobileBuiltInKeywords.tap(findTestObject("Object Repository/StartWorking"), 0)
					ProjectConstants.visitPopUpForOverwriting()
					Mobile.delay(15)
					Mobile.verifyElementExist(findTestObject("Object Repository/CommonScreenElements/Validate_MapScreen"), 60)
					MobileBuiltInKeywords.tap(findTestObject("Object Repository/CommonScreenElements/Location_CheckIn"), 0)
					Mobile.verifyElementExist(findTestObject("Object Repository/CommonScreenElements/Validate_InfoPopUP"), 0)
					MobileBuiltInKeywords.tap(findTestObject("Object Repository/CommonScreenElements/InfoPopUp_NoButton"), 0)
					break
				}
				else{
				}
			}
		}
	}
	@Keyword
	def visitShopWithDataVerification(){
		int index = 0
		int totalshops = ProjectConstants.DRIVER.findElementsByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.ListView[1]/*").size()
		for(int i=1; i<=1; i++){
			MissingShopDataInfo missingshopdatainfo = new MissingShopDataInfo()
			VisitedShopDataInfo visitedshopdatainfo = new VisitedShopDataInfo()
			MobileElement shop = ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout["+i+"]/android.widget.TextView[1]")
			ProjectConstants.CURRENTVISITING_SHOPNAME = shop.getText()
			missingshopdatainfo.setShopname(shop.getText())
			visitedshopdatainfo.setShopname(shop.getText())
			ProjectConstants.missingshopdatainfo.add(missingshopdatainfo)
			ProjectConstants.visitedshopdatainfo.add(visitedshopdatainfo)
			ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout["+i+"]").click()
			MobileBuiltInKeywords.verifyElementText(findTestObject("Object Repository/Validate_ShopOptionsScreen"), "Options")
			MobileBuiltInKeywords.tap(findTestObject("Object Repository/StartWorking"), 0)
			Mobile.delay(15)
			Mobile.verifyElementExist(findTestObject("Object Repository/CommonScreenElements/Validate_MapScreen"), 60)
			MobileBuiltInKeywords.tap(findTestObject("Object Repository/CommonScreenElements/Location_CheckIn"), 0)
			Mobile.verifyElementExist(findTestObject("Object Repository/CommonScreenElements/Validate_InfoPopUP"), 0)
			MobileBuiltInKeywords.tap(findTestObject("Object Repository/CommonScreenElements/InfoPopUp_NoButton"), 0)
			Mobile.callTestCase(findTestCase("Test Cases/ShopOpen/VisitShopOpen"), null)
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
					ProjectConstants.visitedshopdatainfo.get(j).setScenario("Data Verification")
					break
				}
			}
		}
		//		while(true){
		//			MissingShopDataInfo missingshopdatainfo = new MissingShopDataInfo()
		//			index = ProjectConstants.DRIVER.findElementsByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.ListView[1]/*").size()
		//			MobileElement lastitembeforeswipe  = ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout["+index+"]/android.widget.TextView[1]")
		//			String lastitemnamebeforeswipe = lastitembeforeswipe.getText()
		//			Mobile.swipe(0, 292, 0, 200)
		//			index = ProjectConstants.DRIVER.findElementsByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.ListView[1]/*").size()
		//			MobileElement lastitemafterswipe = ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout["+index+"]/android.widget.TextView[1]")
		//			String lastitemnameafterswipe = lastitemafterswipe.getText()
		//			if(lastitemnamebeforeswipe.equalsIgnoreCase(lastitemnameafterswipe)){
		//				break
		//			}
		//			else{
		//				MobileElement shop = ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout["+index+"]/android.widget.TextView[1]")
		//				ProjectConstants.CURRENTVISITING_SHOPNAME = shop.getText()
		//				ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout["+index+"]").click()
		//				MobileBuiltInKeywords.verifyElementText(findTestObject("Object Repository/Validate_ShopOptionsScreen"), "Options")
		//				MobileBuiltInKeywords.tap(findTestObject("Object Repository/StartWorking"), 0)
		//				Mobile.delay(15)
		//				Mobile.verifyElementExist(findTestObject("Object Repository/CommonScreenElements/Validate_MapScreen"), 60)
		//				MobileBuiltInKeywords.tap(findTestObject("Object Repository/CommonScreenElements/Location_CheckIn"), 0)
		//				Mobile.verifyElementExist(findTestObject("Object Repository/CommonScreenElements/Validate_InfoPopUP"), 0)
		//				MobileBuiltInKeywords.tap(findTestObject("Object Repository/CommonScreenElements/InfoPopUp_NoButton"), 0)
		//				Mobile.callTestCase(findTestCase("Test Cases/ShopOpen/VisitShopOpen"), null)
		//				Mobile.callTestCase(findTestCase("ShopOpen/VisitCategoryScenarios/VisitShopCategoriesWithDataVerification"), null)
		//				Mobile.callTestCase(findTestCase("Test Cases/ShopOpen/SaveShop"), null)
		//				missingshopdatainfo.setShopname(ProjectConstants.CURRENTVISITING_SHOPNAME)
		//				missingshopdatainfo.setShopchannel(ProjectConstants.CURRENTVISITING_SHOPCHANNEL)
		//				missingshopdatainfo.setScenario("Data Verification")
		//				ProjectConstants.missingshopdatainfo.add(missingshopdatainfo)
		//			}
		//		}

		String message = "\n\n---------------------------------------------Missing Shop Data-----------------------------------------------------------------------------------------------------\n\n"
		if(ProjectConstants.missingshopdatainfo != null){
			for(int i=0; i<ProjectConstants.missingshopdatainfo.size(); i++){
				MissingShopDataInfo missingshopdatainfo = ProjectConstants.missingshopdatainfo.get(i)
				message = message+"<------------------------------------------------------------------------------------------------------>\n\n"+
						"Shop Name:	"+missingshopdatainfo.getShopname()+"		,		"+missingshopdatainfo.getShopchannel()+
						"\n\nVisiting Scenarios:\n"+missingshopdatainfo.getScenario()
				if(missingshopdatainfo.getMissingshopcategories() != null){
					message = message+"\n\nShop Categories:	"
					for(int j=0; j<missingshopdatainfo.getMissingshopcategories().size(); j++){
						message = message+missingshopdatainfo.getMissingshopcategories().get(j)+",	"
					}
					message = message+"\n"+missingshopdatainfo.getMissingshopcategories_errormessage()
				}
				if(missingshopdatainfo.getMissingCategoriesData() != null){
					message = message+"\n\nProduct Categories:\n\n"
					for(int j=0; j<missingshopdatainfo.getMissingCategoriesData().size(); j++){
						MissingCategoryData missingcategorydata = missingshopdatainfo.getMissingCategoriesData().get(j)
						if(missingcategorydata.getProductcategories() != null){
							message = message+"Main Category:	"
							message = message+missingcategorydata.getMaincategory()+"\nProduct Categories:	"
							for(int k=0; k<missingcategorydata.getProductcategories().size(); k++){
								message = message+missingcategorydata.getProductcategories().get(k)+",	"
							}
							message = message+"\n"+missingcategorydata.getProductcategories_errormessage()+"\n\n"
						}
					}
				}
				if(missingshopdatainfo.getMissingCategoriesData() != null){
					message = message+"\n\nProducts:\n\n"
					for(int j=0; j<missingshopdatainfo.getMissingCategoriesData().size(); j++){
						MissingCategoryData missingcategorydata = missingshopdatainfo.getMissingCategoriesData().get(j)
						if(missingcategorydata.getProducts() != null){
							message = message+"Main Category:	"
							message = message+missingcategorydata.getMaincategory()+"\nProduct Category:	"+
									missingcategorydata.getProductCategory()+
									"\nProducts:	"
							for(int k=0; k<missingcategorydata.getProducts().size(); k++){
								message = message+missingcategorydata.getProducts().get(k)+",	"
							}
							message = message+"\n"+missingcategorydata.getProducts_errormessage()+"\n\n"
						}
					}
				}
				message = message+"\n\n<------------------------------------------------------------------------------------------------------>\n\n"
				KeywordUtil.logInfo(message)
			}
		}
		else{
		}
		message = "\n\n\n---------------------------------------------Visited Shop Data-----------------------------------------------------------------------------------------------------\n\n"
		if(ProjectConstants.visitedshopdatainfo != null){
			for(int i=0; i<ProjectConstants.visitedshopdatainfo.size(); i++){
				VisitedShopDataInfo visitedshopdatainfo = ProjectConstants.visitedshopdatainfo.get(i)
				message = message+"<---------------------------------------------------------------------------------------------------------------------------------------------------------------->\n\n"+
						"Shop Name:	"+visitedshopdatainfo.getShopname()+"		,		"+visitedshopdatainfo.getShopchannel()+
						"\n\nVisiting Scenarios:\n"+visitedshopdatainfo.getScenario()
				if(visitedshopdatainfo.getVisitedcategoriesdata() != null){
					for(int j=0; j< visitedshopdatainfo.getVisitedcategoriesdata().size(); j++){
						VisitedCategoryData visitedcategorydata = visitedshopdatainfo.getVisitedcategoriesdata().get(j)
						if(visitedcategorydata.getMaincategory().equalsIgnoreCase("Chiller Utilization")){
							if(visitedcategorydata.getChillertype().equalsIgnoreCase("Chiller Available") || visitedcategorydata.getChillertype().equalsIgnoreCase("Chiller need maintenance") || visitedcategorydata.getChillertype().equalsIgnoreCase("Chiller Type not Available")){
								message = message+"\n\nMain Category:	"+visitedcategorydata.getMaincategory()+
										"\nChiller Type:	"+visitedcategorydata.getChillertype()+
										"\nProduct Category:	"+visitedcategorydata.getProductcategory()
								message = message + "\n" + String.format("%-45s%-14s%-20s%-13s%-24s%-30s%-15s", "Products:","Facing","Stock Taking","Depth","Overwrite Facing","Overwrite Stock Taking","Overwrite depth")+"\n"
								for(int k=0; k<visitedcategorydata.getShopProductsdata().size() ; k++){
									ShopProductsData shopproductsdata = visitedcategorydata.getShopProductsdata().get(k)
									message = message + String.format("%-45s%-14s%-20s%-13s%-24s%-30s%-15s", shopproductsdata.getProduct(),shopproductsdata.getFacingdata(),shopproductsdata.getStocktakingdata(),shopproductsdata.getDepthdata(),shopproductsdata.getOverwritefacingdata(),shopproductsdata.getOverwritestocktakingdata(),shopproductsdata.getOverwritedepthdata())
								}
							}
							else if(visitedcategorydata.getChillertype().equalsIgnoreCase("Chiller not Available") || visitedcategorydata.getChillertype().equalsIgnoreCase("Chiller not in access") || visitedcategorydata.getChillertype().equalsIgnoreCase("Chiller removed for maintenance")){
								message = message+"\n\nMain Category:	"+
										visitedcategorydata.getMaincategory()+
										"\nProduct Category:	"+visitedcategorydata.getProductcategory()
								message = message + "\n" + String.format("%-50s%-20s%-20s%-30s%-30s", "Products:","Facing","Stock Taking","Overwrite Facing","Overwrite Stock Taking")+"\n"
								for(int k=0; k<visitedcategorydata.getShopProductsdata().size() ; k++){
									ShopProductsData shopproductsdata = visitedcategorydata.getShopProductsdata().get(k)
									message = message + String.format("%-50s%-20s%-20s%-30s%-30s", shopproductsdata.getProduct(),shopproductsdata.getFacingdata(),shopproductsdata.getStocktakingdata(),shopproductsdata.getOverwritefacingdata(),shopproductsdata.getOverwritestocktakingdata())
								}
							}
							else{
							}
						}
						else{
							message = message+"\n\nMain Category:	"+
									visitedcategorydata.getMaincategory()+
									"\nProduct Category:	"+visitedcategorydata.getProductcategory()
							message = message + "\n" + String.format("%-50s%-20s%-20s%-30s%-30s", "Products:","Facing","Stock Taking","Overwrite Facing","Overwrite Stock Taking")+"\n"
							for(int k=0; k<visitedcategorydata.getShopProductsdata().size() ; k++){
								ShopProductsData shopproductsdata = visitedcategorydata.getShopProductsdata().get(k)
								message = message + String.format("%-50s%-20s%-20s%-30s%-30s", shopproductsdata.getProduct(),shopproductsdata.getFacingdata(),shopproductsdata.getStocktakingdata(),shopproductsdata.getOverwritefacingdata(),shopproductsdata.getOverwritestocktakingdata())
							}
						}
					}
				}
				message = message + "\n\n<---------------------------------------------------------------------------------------------------------------------------------------------------------------->\n\n"
				KeywordUtil.logInfo(message)
			}
		}
		else{
		}
	}
	@Keyword
	def visitShopsWithShopLevelOverwriting(){
		int index = 0
		int totalshops = ProjectConstants.DRIVER.findElementsByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.ListView[1]/*").size()
		for(int i=1; i<=totalshops; i++){
			MissingShopDataInfo missingshopdatainfo = new MissingShopDataInfo()
			MobileElement shop = ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout["+i+"]/android.widget.TextView[1]")
			ProjectConstants.CURRENTVISITING_SHOPNAME = shop.getText()
			ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout["+i+"]").click()
			MobileBuiltInKeywords.verifyElementText(findTestObject("Object Repository/Validate_ShopOptionsScreen"), "Options")
			MobileBuiltInKeywords.tap(findTestObject("Object Repository/StartWorking"), 0)
			Mobile.delay(15)
			Mobile.verifyElementExist(findTestObject("Object Repository/CommonScreenElements/Validate_MapScreen"), 60)
			MobileBuiltInKeywords.tap(findTestObject("Object Repository/CommonScreenElements/Location_CheckIn"), 0)
			Mobile.verifyElementExist(findTestObject("Object Repository/CommonScreenElements/Validate_InfoPopUP"), 0)
			MobileBuiltInKeywords.tap(findTestObject("Object Repository/CommonScreenElements/InfoPopUp_NoButton"), 0)
			if(i == 1){
				Mobile.callTestCase(findTestCase("Test Cases/ShopClosed/VisitShopClosed"), null)
				Mobile.verifyElementExist(findTestObject("Object Repository/Validate_ShopListScreen"), 0)
				findShop()
				Mobile.callTestCase(findTestCase("Test Cases/ShopOpen/VisitShopOpen"), null)
				Mobile.callTestCase(findTestCase("Test Cases/ShopOpen/VisitCategoryScenarios/VisitShopCategoriesWith_SKDNA_SKDNA_SKDNA"), null)
				Mobile.callTestCase(findTestCase("Test Cases/ShopOpen/SaveShop"), null)
				missingshopdatainfo.setShopname(ProjectConstants.CURRENTVISITING_SHOPNAME)
				missingshopdatainfo.setShopchannel(ProjectConstants.CURRENTVISITING_SHOPCHANNEL)
				missingshopdatainfo.setScenario("(1) visit shop with 'Shop Closed'\n(2) visit shop with 'Shop Open' with SKDNA 'Others' remark")
				ProjectConstants.missingshopdatainfo.add(missingshopdatainfo)
				Mobile.verifyElementExist(findTestObject("Object Repository/Validate_ShopListScreen"), 0)
			}
			else if(i == 2){
				Mobile.callTestCase(findTestCase("Test Cases/ShopKeeperDidNotAllow/VisitShopKeeperDidNotAllow"), null)
				Mobile.verifyElementExist(findTestObject("Object Repository/Validate_ShopListScreen"), 0)
				findShop()
				Mobile.callTestCase(findTestCase("Test Cases/ShopKeeperDidNotAllow/OverwriteShopKeeperDidNotAllow"), null)
				missingshopdatainfo.setShopname(ProjectConstants.CURRENTVISITING_SHOPNAME)
				missingshopdatainfo.setShopchannel(ProjectConstants.CURRENTVISITING_SHOPCHANNEL)
				missingshopdatainfo.setScenario("(1) visit shop with 'Shop Keeper did not allow' with 'Expiry Issue' remark\n(2) visit shop with 'Shopkeeper did not allow' with 'Others' remark")
				ProjectConstants.missingshopdatainfo.add(missingshopdatainfo)
				Mobile.verifyElementExist(findTestObject("Object Repository/Validate_ShopListScreen"), 0)
			}
			else if(i == 3){
				Mobile.callTestCase(findTestCase("Test Cases/ShopNotFound/VisitShopNotFound"), null)
				Mobile.verifyElementExist(findTestObject("Object Repository/Validate_ShopListScreen"), 0)
				findShop()
				Mobile.callTestCase(findTestCase("Test Cases/ShopToBeRemoved/VisitShopToBeRemoved"), null)
				missingshopdatainfo.setShopname(ProjectConstants.CURRENTVISITING_SHOPNAME)
				missingshopdatainfo.setShopchannel(ProjectConstants.CURRENTVISITING_SHOPCHANNEL)
				missingshopdatainfo.setScenario("(1) visit shop with 'Shop not found'\n(2) visit shop with 'Shop to be removed'")
				ProjectConstants.missingshopdatainfo.add(missingshopdatainfo)
				Mobile.verifyElementExist(findTestObject("Object Repository/Validate_ShopListScreen"), 0)
			}
			else if(i == 4){
				Mobile.callTestCase(findTestCase("Test Cases/ShopClosed/VisitShopClosed"), null)
				Mobile.verifyElementExist(findTestObject("Object Repository/Validate_ShopListScreen"), 0)
				findShop()
				Mobile.callTestCase(findTestCase("Test Cases/ShopPermanentlyClosed/VisitShopPermanentlyClosed"), null)
				missingshopdatainfo.setShopname(ProjectConstants.CURRENTVISITING_SHOPNAME)
				missingshopdatainfo.setShopchannel(ProjectConstants.CURRENTVISITING_SHOPCHANNEL)
				missingshopdatainfo.setScenario("(1) visit shop with 'Shop Closed'\n(2) visit shop with 'Shop permanently closed'")
				ProjectConstants.missingshopdatainfo.add(missingshopdatainfo)
				Mobile.verifyElementExist(findTestObject("Object Repository/Validate_ShopListScreen"), 0)
			}
			else{
				break
			}
		}
		for(int i=0; i<ProjectConstants.missingshopdatainfo.size(); i++){
			MissingShopDataInfo missingshopdatainfo = ProjectConstants.missingshopdatainfo.get(i)
			String message = "\n\n<------------------------------------------------------------------------------------------------------>\n\n"+
					"Shop Name:  "+missingshopdatainfo.getShopname()+"		,		Shop Channel:  "+missingshopdatainfo.getShopchannel()+"\n\n"+
					missingshopdatainfo.getScenario()+
					"\n\n<------------------------------------------------------------------------------------------------------>"
			KeywordUtil.logInfo(message)
		}
	}
	@Keyword
	def visitShopsWithCategoryLevel_Chiller_Overwriting(){
		int index = 0
		int totalshops = ProjectConstants.DRIVER.findElementsByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.ListView[1]/*").size()
		for(int i=1; i<=totalshops; i++){
			MissingShopDataInfo missingshopdatainfo = new MissingShopDataInfo()
			MobileElement shop = ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout["+i+"]/android.widget.TextView[1]")
			ProjectConstants.CURRENTVISITING_SHOPNAME = shop.getText()
			ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout["+i+"]").click()
			MobileBuiltInKeywords.verifyElementText(findTestObject("Object Repository/Validate_ShopOptionsScreen"), "Options")
			MobileBuiltInKeywords.tap(findTestObject("Object Repository/StartWorking"), 0)
			Mobile.delay(15)
			Mobile.verifyElementExist(findTestObject("Object Repository/CommonScreenElements/Validate_MapScreen"), 60)
			MobileBuiltInKeywords.tap(findTestObject("Object Repository/CommonScreenElements/Location_CheckIn"), 0)
			Mobile.verifyElementExist(findTestObject("Object Repository/CommonScreenElements/Validate_InfoPopUP"), 0)
			MobileBuiltInKeywords.tap(findTestObject("Object Repository/CommonScreenElements/InfoPopUp_NoButton"), 0)
			if(i == 1){
				Mobile.callTestCase(findTestCase("Test Cases/ShopOpen/VisitShopOpen"), null)
				Mobile.callTestCase(findTestCase("Test Cases/ShopOpen/VisitCategoryScenarios/VisitShopCategoriesWith_CNAl_CAv_DSA"), null)
				Mobile.callTestCase(findTestCase("Test Cases/ShopOpen/VisitCategoryScenariosWithOverwritePopUp/VisitShopCategoriesWith_SKDNA_CNAv_NSFD"), null)
				Mobile.callTestCase(findTestCase("Test Cases/ShopOpen/SaveShop"), null)
				missingshopdatainfo.setShopname(ProjectConstants.CURRENTVISITING_SHOPNAME)
				missingshopdatainfo.setShopchannel(ProjectConstants.CURRENTVISITING_SHOPCHANNEL)
				String message = "(1) 'Chiller Not Allocated' for chiller\n"+
						"'Display Space Available' for remaining categories\n"+
						"'RTM visit frequency' with 'Once a week'\n"+
						"'Pop Application' with 'No' remark\n"+
						"'Retailer Remarks' with 'OB not visiting' remark\n"+
						"'Hanger Availability' with 'Yes' remark\n\n"+
						"(2) 'SKDNA' for chiller with 'Expiry Issue' remark\n"+
						"'No Space for Display' for remaining categories\n"+
						"'RTM visit frequency' with 'Twice a week'\n"+
						"'Pop Application' with 'No' remark\n"+
						"'Retailer Remarks' with 'SM not visiting' remark\n"+
						"'Hanger Availability' with 'No' remark"
				missingshopdatainfo.setScenario(message)
				ProjectConstants.missingshopdatainfo.add(missingshopdatainfo)
				Mobile.verifyElementExist(findTestObject("Object Repository/Validate_ShopListScreen"), 0)
			}
			else if(i == 2){
				Mobile.callTestCase(findTestCase("Test Cases/ShopOpen/VisitShopOpen"), null)
				Mobile.callTestCase(findTestCase("Test Cases/ShopOpen/VisitCategoryScenarios/VisitShopCategoriesWith_SKDNA_SKDNA_SKDNA"), null)
				Mobile.callTestCase(findTestCase("Test Cases/ShopOpen/VisitCategoryScenariosWithOverwritePopUp/OverwriteShopCategoriesWith_SKDNA_SKDNA_SKDNA"), null)
				Mobile.callTestCase(findTestCase("Test Cases/ShopOpen/SaveShop"), null)
				missingshopdatainfo.setShopname(ProjectConstants.CURRENTVISITING_SHOPNAME)
				missingshopdatainfo.setShopchannel(ProjectConstants.CURRENTVISITING_SHOPCHANNEL)
				String message = "(1) 'SKDNA' for chiller with 'Expiry Issue' remark\n"+
						"'SKDNA' for remaining categories with 'Expiry Issue' remark\n"+
						"'RTM visit frequency' with 'Once a week'\n"+
						"'Pop Application' with 'No' remark\n"+
						"'Retailer Remarks' with 'OB not visiting' remark\n"+
						"'Hanger Availability' with 'Yes' remark\n\n"+
						"(2) 'SKDNA' for chiller with 'Others' remark\n"+
						"'SKDNA' for remaining categories with 'Others' remark\n"+
						"'RTM visit frequency' with 'Twice a week'\n"+
						"'Pop Application' with 'No' remark\n"+
						"'Retailer Remarks' with 'SM not visiting' remark\n"+
						"'Hanger Availability' with 'No' remark"
				missingshopdatainfo.setScenario(message)
				ProjectConstants.missingshopdatainfo.add(missingshopdatainfo)
				Mobile.verifyElementExist(findTestObject("Object Repository/Validate_ShopListScreen"), 0)
			}
			else if(i == 3){
				Mobile.callTestCase(findTestCase("Test Cases/ShopOpen/VisitShopOpen"), null)
				Mobile.callTestCase(findTestCase("Test Cases/ShopOpen/VisitCategoryScenarios/VisitShopCategoriesWith_SKDNA_SKDNA_SKDNA"), null)
				Mobile.callTestCase(findTestCase("Test Cases/ShopOpen/VisitCategoryScenariosWithOverwritePopUp/VisitShopCategoriesWith_CNAl_CAv_DSA"), null)
				Mobile.callTestCase(findTestCase("Test Cases/ShopOpen/SaveShop"), null)
				missingshopdatainfo.setShopname(ProjectConstants.CURRENTVISITING_SHOPNAME)
				missingshopdatainfo.setShopchannel(ProjectConstants.CURRENTVISITING_SHOPCHANNEL)
				String message = "(1) 'SKDNA' for chiller with 'Expiry Issue' remark\n"+
						"'SKDNA' for remaining categories with 'Expiry Issue' remark\n"+
						"'RTM visit frequency' with 'Once a week'\n"+
						"'Pop Application' with 'No' remark\n"+
						"'Retailer Remarks' with 'OB not visiting' remark\n"+
						"'Hanger Availability' with 'Yes' remark\n\n"+
						"(2) 'Chiller Not Allocated' for chiller\n"+
						"'Display Space Available' for remaining categories\n"+
						"'RTM visit frequency' with 'Once a week'\n"+
						"'Pop Application' with 'No' remark\n"+
						"'Retailer Remarks' with 'OB not visiting' remark\n"+
						"'Hanger Availability' with 'Yes' remark"
				missingshopdatainfo.setScenario(message)
				ProjectConstants.missingshopdatainfo.add(missingshopdatainfo)
				Mobile.verifyElementExist(findTestObject("Object Repository/Validate_ShopListScreen"), 0)
			}
			else if(i == 4){
				Mobile.callTestCase(findTestCase("Test Cases/ShopOpen/VisitShopOpen"), null)
				Mobile.callTestCase(findTestCase("Test Cases/ShopOpen/VisitCategoryScenarios/VisitShopCategoriesWith_CNAl_CAv_DSA"), null)
				Mobile.callTestCase(findTestCase("Test Cases/ShopOpen/VisitCategoryScenariosWithOverwritePopUp/OverwriteShopCategoriesWith_CNAl_CAv_DSA"), null)
				Mobile.callTestCase(findTestCase("Test Cases/ShopOpen/SaveShop"), null)
				missingshopdatainfo.setShopname(ProjectConstants.CURRENTVISITING_SHOPNAME)
				missingshopdatainfo.setShopchannel(ProjectConstants.CURRENTVISITING_SHOPCHANNEL)
				String message = "(1) 'Chiller Not Allocated' for chiller\n"+
						"'Display Space Available' for remaining categories\n"+
						"'RTM visit frequency' with 'Once a week'\n"+
						"'Pop Application' with 'No' remark\n"+
						"'Retailer Remarks' with 'OB not visiting' remark\n"+
						"'Hanger Availability' with 'Yes' remark\n\n"+
						"(2) Overwrite 'Chiller Not Allocated' for chiller\n"+
						"Overwrite 'Display Space Available' for remaining categories\n"+
						"'RTM visit frequency' with 'Twice a week'\n"+
						"'Pop Application' with 'No' remark\n"+
						"'Retailer Remarks' with 'SM not visiting' remark\n"+
						"'Hanger Availability' with 'No' remark"
				missingshopdatainfo.setScenario(message)
				ProjectConstants.missingshopdatainfo.add(missingshopdatainfo)
				Mobile.verifyElementExist(findTestObject("Object Repository/Validate_ShopListScreen"), 0)
			}
			else{
				break
			}
		}
		for(int i=0; i<ProjectConstants.missingshopdatainfo.size(); i++){
			MissingShopDataInfo missingshopdatainfo = ProjectConstants.missingshopdatainfo.get(i)
			String message = "\n\n<------------------------------------------------------------------------------------------------------>\n\n"+
					"Shop Name:  "+missingshopdatainfo.getShopname()+"		,		Shop Channel:  "+missingshopdatainfo.getShopchannel()+"\n\n"+
					missingshopdatainfo.getScenario()+
					"\n\n<------------------------------------------------------------------------------------------------------>"
			KeywordUtil.logInfo(message)
		}
	}
	@Keyword
	def visitShopsWithCategoryLevel_ChillerUtilization_Overwriting(){
		int index = 0
		int totalshops = ProjectConstants.DRIVER.findElementsByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.ListView[1]/*").size()
		for(int i=1; i<=totalshops; i++){
			MissingShopDataInfo missingshopdatainfo = new MissingShopDataInfo()
			MobileElement shop = ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout["+i+"]/android.widget.TextView[1]")
			ProjectConstants.CURRENTVISITING_SHOPNAME = shop.getText()
			ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout["+i+"]").click()
			MobileBuiltInKeywords.verifyElementText(findTestObject("Object Repository/Validate_ShopOptionsScreen"), "Options")
			MobileBuiltInKeywords.tap(findTestObject("Object Repository/StartWorking"), 0)
			Mobile.delay(15)
			Mobile.verifyElementExist(findTestObject("Object Repository/CommonScreenElements/Validate_MapScreen"), 60)
			MobileBuiltInKeywords.tap(findTestObject("Object Repository/CommonScreenElements/Location_CheckIn"), 0)
			Mobile.verifyElementExist(findTestObject("Object Repository/CommonScreenElements/Validate_InfoPopUP"), 0)
			MobileBuiltInKeywords.tap(findTestObject("Object Repository/CommonScreenElements/InfoPopUp_NoButton"), 0)
			if(i == 3){
				Mobile.callTestCase(findTestCase("Test Cases/ShopOpen/VisitShopOpen"), null)
				Mobile.callTestCase(findTestCase("Test Cases/ShopOpen/VisitCategoryScenarios/VisitShopCategoriesWith_CNAl_CAv_DSA"), null)
				Mobile.callTestCase(findTestCase("Test Cases/ShopOpen/VisitCategoryScenariosWithOverwritePopUp/VisitShopCategoriesWith_SKDNA_CNAv_NSFD"), null)
				Mobile.callTestCase(findTestCase("Test Cases/ShopOpen/SaveShop"), null)
				missingshopdatainfo.setShopname(ProjectConstants.CURRENTVISITING_SHOPNAME)
				missingshopdatainfo.setShopchannel(ProjectConstants.CURRENTVISITING_SHOPCHANNEL)
				String message = "(1) 'Chiller Available' for 'Chiller Utilization'\n"+
						"'Display Space Available' for remaining categories\n"+
						"'RTM visit frequency' with 'Once a week'\n"+
						"'Pop Application' with 'No' remark\n"+
						"'Retailer Remarks' with 'OB not visiting' remark\n"+
						"'Hanger Availability' with 'Yes' remark\n\n"+
						"(2) 'Chiller Not Available' for 'Chiller Utilization'\n"+
						"'No Space for Display' for remaining categories\n"+
						"'RTM visit frequency' with 'Twice a week'\n"+
						"'Pop Application' with 'No' remark\n"+
						"'Retailer Remarks' with 'SM not visiting' remark\n"+
						"'Hanger Availability' with 'No' remark"
				missingshopdatainfo.setScenario(message)
				ProjectConstants.missingshopdatainfo.add(missingshopdatainfo)
				Mobile.verifyElementExist(findTestObject("Object Repository/Validate_ShopListScreen"), 0)
			}
			else if(i == 2){
				Mobile.callTestCase(findTestCase("Test Cases/ShopOpen/VisitShopOpen"), null)
				Mobile.callTestCase(findTestCase("Test Cases/ShopOpen/VisitCategoryScenarios/VisitShopCategoriesWith_SKDNA_SKDNA_SKDNA"), null)
				Mobile.callTestCase(findTestCase("Test Cases/ShopOpen/VisitCategoryScenariosWithOverwritePopUp/OverwriteShopCategoriesWith_SKDNA_SKDNA_SKDNA"), null)
				Mobile.callTestCase(findTestCase("Test Cases/ShopOpen/SaveShop"), null)
				missingshopdatainfo.setShopname(ProjectConstants.CURRENTVISITING_SHOPNAME)
				missingshopdatainfo.setShopchannel(ProjectConstants.CURRENTVISITING_SHOPCHANNEL)
				String message = "(1) 'SKDNA' for chiller Utilization with 'Expiry Issue' remark\n"+
						"'SKDNA' for remaining categories with 'Expiry Issue' remark\n"+
						"'RTM visit frequency' with 'Once a week'\n"+
						"'Pop Application' with 'No' remark\n"+
						"'Retailer Remarks' with 'OB not visiting' remark\n"+
						"'Hanger Availability' with 'Yes' remark\n\n"+
						"(2) 'SKDNA' for chiller with 'Others' remark\n"+
						"'SKDNA' for remaining categories with 'Others' remark\n"+
						"'RTM visit frequency' with 'Twice a week'\n"+
						"'Pop Application' with 'No' remark\n"+
						"'Retailer Remarks' with 'SM not visiting' remark\n"+
						"'Hanger Availability' with 'No' remark"
				missingshopdatainfo.setScenario(message)
				ProjectConstants.missingshopdatainfo.add(missingshopdatainfo)
				Mobile.verifyElementExist(findTestObject("Object Repository/Validate_ShopListScreen"), 0)
			}
			else if(i == 1){
				Mobile.callTestCase(findTestCase("Test Cases/ShopOpen/VisitShopOpen"), null)
				Mobile.callTestCase(findTestCase("Test Cases/ShopOpen/VisitCategoryScenarios/VisitShopCategoriesWith_CNAl_CTNAv_NSFD"), null)
				Mobile.callTestCase(findTestCase("Test Cases/ShopOpen/VisitCategoryScenariosWithOverwritePopUp/VisitShopCategoriesWith_SKDNA_CAv_DSA"), null)
				Mobile.callTestCase(findTestCase("Test Cases/ShopOpen/SaveShop"), null)
				missingshopdatainfo.setShopname(ProjectConstants.CURRENTVISITING_SHOPNAME)
				missingshopdatainfo.setShopchannel(ProjectConstants.CURRENTVISITING_SHOPCHANNEL)
				String message = "(1) 'Chiller Type not Available' for chiller Utilization   '(note: if tagged chiller is with yogurt than select chiller without yogurt otherwise select chiller with withouyogurt)'\n"+
						"'No Space for Display' for remaining categories\n"+
						"'RTM visit frequency' with 'Once a week'\n"+
						"'Pop Application' with 'No' remark\n"+
						"'Retailer Remarks' with 'OB not visiting' remark\n"+
						"'Hanger Availability' with 'Yes' remark\n\n"+
						"(2) 'Chiller Available' for chiller Utilization\n"+
						"Overwrite 'No Space for Display' for remaining categories\n"+
						"'RTM visit frequency' with 'Twice a week'\n"+
						"'Pop Application' with 'No' remark\n"+
						"'Retailer Remarks' with 'SM not visiting' remark\n"+
						"'Hanger Availability' with 'No' remark"
				missingshopdatainfo.setScenario(message)
				ProjectConstants.missingshopdatainfo.add(missingshopdatainfo)
				Mobile.verifyElementExist(findTestObject("Object Repository/Validate_ShopListScreen"), 0)
			}
			else if(i == 4){
				Mobile.callTestCase(findTestCase("Test Cases/ShopOpen/VisitShopOpen"), null)
				Mobile.callTestCase(findTestCase("Test Cases/ShopOpen/VisitCategoryScenarios/VisitShopCategoriesWith_CNAl_CAv_DSA"), null)
				Mobile.callTestCase(findTestCase("Test Cases/ShopOpen/VisitCategoryScenariosWithOverwritePopUp/OverwriteShopCategoriesWith_CNAl_CAv_DSA"), null)
				Mobile.callTestCase(findTestCase("Test Cases/ShopOpen/SaveShop"), null)
				missingshopdatainfo.setShopname(ProjectConstants.CURRENTVISITING_SHOPNAME)
				missingshopdatainfo.setShopchannel(ProjectConstants.CURRENTVISITING_SHOPCHANNEL)
				String message = "(1) 'Chiller Available' for chiller utilization\n"+
						"'Display Space Available' for remaining categories\n"+
						"'RTM visit frequency' with 'Once a week'\n"+
						"'Pop Application' with 'No' remark\n"+
						"'Retailer Remarks' with 'OB not visiting' remark\n"+
						"'Hanger Availability' with 'Yes' remark\n\n"+
						"(2) Overwrite 'Chiller Available' for chiller utilization\n"+
						"Overwrite 'Display Space Available' for remaining categories\n"+
						"'RTM visit frequency' with 'Twice a week'\n"+
						"'Pop Application' with 'No' remark\n"+
						"'Retailer Remarks' with 'SM not visiting' remark\n"+
						"'Hanger Availability' with 'No' remark"
				missingshopdatainfo.setScenario(message)
				ProjectConstants.missingshopdatainfo.add(missingshopdatainfo)
				Mobile.verifyElementExist(findTestObject("Object Repository/Validate_ShopListScreen"), 0)
			}
			else{
			}
		}
		for(int i=0; i<ProjectConstants.missingshopdatainfo.size(); i++){
			MissingShopDataInfo missingshopdatainfo = ProjectConstants.missingshopdatainfo.get(i)
			String message = "\n\n<------------------------------------------------------------------------------------------------------>\n\n"+
					"Shop Name:  "+missingshopdatainfo.getShopname()+"		,		Shop Channel:  "+missingshopdatainfo.getShopchannel()+"\n\n"+
					missingshopdatainfo.getScenario()+
					"\n\n<------------------------------------------------------------------------------------------------------>"
			KeywordUtil.logInfo(message)
		}
	}
}
