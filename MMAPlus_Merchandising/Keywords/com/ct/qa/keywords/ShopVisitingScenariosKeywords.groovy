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
import com.ct.qa.struct.ProductsData
import com.ct.qa.struct.ShopInfo

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
		for(int i=1; i<=totalshops; i++){
			ShopInfo shopinfo = new ShopInfo()
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
			Mobile.callTestCase(findTestCase("Test Cases/ShopOpen/VisitShopOpen"), null)
			Mobile.callTestCase(findTestCase("Test Cases/ShopOpen/VisitCategoryScenarios/VisitShopCategoriesWithDataVerification"), null)
			Mobile.callTestCase(findTestCase("Test Cases/ShopOpen/SaveShop"), null)
			shopinfo.setShopname(ProjectConstants.CURRENTVISITING_SHOPNAME)
			shopinfo.setShopchannel(ProjectConstants.CURRENTVISITING_SHOPCHANNEL)
			shopinfo.setScenario("Data Verification")
			ProjectConstants.shopinfo.add(shopinfo)
		}
		while(true){
			ShopInfo shopinfo = new ShopInfo()
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
				MobileElement shop = ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout["+index+"]/android.widget.TextView[1]")
				ProjectConstants.CURRENTVISITING_SHOPNAME = shop.getText()
				ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout["+index+"]").click()
				MobileBuiltInKeywords.verifyElementText(findTestObject("Object Repository/Validate_ShopOptionsScreen"), "Options")
				MobileBuiltInKeywords.tap(findTestObject("Object Repository/StartWorking"), 0)
				Mobile.delay(15)
				Mobile.verifyElementExist(findTestObject("Object Repository/CommonScreenElements/Validate_MapScreen"), 60)
				MobileBuiltInKeywords.tap(findTestObject("Object Repository/CommonScreenElements/Location_CheckIn"), 0)
				Mobile.verifyElementExist(findTestObject("Object Repository/CommonScreenElements/Validate_InfoPopUP"), 0)
				MobileBuiltInKeywords.tap(findTestObject("Object Repository/CommonScreenElements/InfoPopUp_NoButton"), 0)
				Mobile.callTestCase(findTestCase("Test Cases/ShopOpen/VisitShopOpen"), null)
				Mobile.callTestCase(findTestCase("ShopOpen/VisitCategoryScenarios/VisitShopCategoriesWithDataVerification"), null)
				Mobile.callTestCase(findTestCase("Test Cases/ShopOpen/SaveShop"), null)
				shopinfo.setShopname(ProjectConstants.CURRENTVISITING_SHOPNAME)
				shopinfo.setShopchannel(ProjectConstants.CURRENTVISITING_SHOPCHANNEL)
				shopinfo.setScenario("Data Verification")
				ProjectConstants.shopinfo.add(shopinfo)
			}
		}
		for(int i=0; i<ProjectConstants.shopinfo.size(); i++){
			ShopInfo shopinfo = ProjectConstants.shopinfo.get(i)
			String message = "\n\n<------------------------------------------------------------------------------------------------------>\n\n"+
					"Shop Name:  "+shopinfo.getShopname()+"		,		Shop Channel:  "+shopinfo.getShopchannel()+"\n\n"+
					shopinfo.getScenario()+
					"\n\n<------------------------------------------------------------------------------------------------------>"
			KeywordUtil.logInfo(message)
		}
	}
	@Keyword
	def visitShopsWithShopLevelOverwriting(){
		int index = 0
		int totalshops = ProjectConstants.DRIVER.findElementsByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.ListView[1]/*").size()
		for(int i=1; i<=totalshops; i++){
			ShopInfo shopinfo = new ShopInfo()
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
				shopinfo.setShopname(ProjectConstants.CURRENTVISITING_SHOPNAME)
				shopinfo.setShopchannel(ProjectConstants.CURRENTVISITING_SHOPCHANNEL)
				shopinfo.setScenario("(1) visit shop with 'Shop Closed'\n(2) visit shop with 'Shop Open' with SKDNA 'Others' remark")
				ProjectConstants.shopinfo.add(shopinfo)
				Mobile.verifyElementExist(findTestObject("Object Repository/Validate_ShopListScreen"), 0)
			}
			else if(i == 2){
				Mobile.callTestCase(findTestCase("Test Cases/ShopKeeperDidNotAllow/VisitShopKeeperDidNotAllow"), null)
				Mobile.verifyElementExist(findTestObject("Object Repository/Validate_ShopListScreen"), 0)
				findShop()
				Mobile.callTestCase(findTestCase("Test Cases/ShopKeeperDidNotAllow/OverwriteShopKeeperDidNotAllow"), null)
				shopinfo.setShopname(ProjectConstants.CURRENTVISITING_SHOPNAME)
				shopinfo.setShopchannel(ProjectConstants.CURRENTVISITING_SHOPCHANNEL)
				shopinfo.setScenario("(1) visit shop with 'Shop Keeper did not allow' with 'Expiry Issue' remark\n(2) visit shop with 'Shopkeeper did not allow' with 'Others' remark")
				ProjectConstants.shopinfo.add(shopinfo)
				Mobile.verifyElementExist(findTestObject("Object Repository/Validate_ShopListScreen"), 0)
			}
			else if(i == 3){
				Mobile.callTestCase(findTestCase("Test Cases/ShopNotFound/VisitShopNotFound"), null)
				Mobile.verifyElementExist(findTestObject("Object Repository/Validate_ShopListScreen"), 0)
				findShop()
				Mobile.callTestCase(findTestCase("Test Cases/ShopToBeRemoved/VisitShopToBeRemoved"), null)
				shopinfo.setShopname(ProjectConstants.CURRENTVISITING_SHOPNAME)
				shopinfo.setShopchannel(ProjectConstants.CURRENTVISITING_SHOPCHANNEL)
				shopinfo.setScenario("(1) visit shop with 'Shop not found'\n(2) visit shop with 'Shop to be removed'")
				ProjectConstants.shopinfo.add(shopinfo)
				Mobile.verifyElementExist(findTestObject("Object Repository/Validate_ShopListScreen"), 0)
			}
			else if(i == 4){
				Mobile.callTestCase(findTestCase("Test Cases/ShopClosed/VisitShopClosed"), null)
				Mobile.verifyElementExist(findTestObject("Object Repository/Validate_ShopListScreen"), 0)
				findShop()
				Mobile.callTestCase(findTestCase("Test Cases/ShopPermanentlyClosed/VisitShopPermanentlyClosed"), null)
				shopinfo.setShopname(ProjectConstants.CURRENTVISITING_SHOPNAME)
				shopinfo.setShopchannel(ProjectConstants.CURRENTVISITING_SHOPCHANNEL)
				shopinfo.setScenario("(1) visit shop with 'Shop Closed'\n(2) visit shop with 'Shop permanently closed'")
				ProjectConstants.shopinfo.add(shopinfo)
				Mobile.verifyElementExist(findTestObject("Object Repository/Validate_ShopListScreen"), 0)
			}
			else{
				break
			}
		}
		for(int i=0; i<ProjectConstants.shopinfo.size(); i++){
			ShopInfo shopinfo = ProjectConstants.shopinfo.get(i)
			String message = "\n\n<------------------------------------------------------------------------------------------------------>\n\n"+
					"Shop Name:  "+shopinfo.getShopname()+"		,		Shop Channel:  "+shopinfo.getShopchannel()+"\n\n"+
					shopinfo.getScenario()+
					"\n\n<------------------------------------------------------------------------------------------------------>"
			KeywordUtil.logInfo(message)
		}
	}
	@Keyword
	def visitShopsWithCategoryLevel_Chiller_Overwriting(){
		int index = 0
		int totalshops = ProjectConstants.DRIVER.findElementsByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.ListView[1]/*").size()
		for(int i=1; i<=totalshops; i++){
			ShopInfo shopinfo = new ShopInfo()
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
				shopinfo.setShopname(ProjectConstants.CURRENTVISITING_SHOPNAME)
				shopinfo.setShopchannel(ProjectConstants.CURRENTVISITING_SHOPCHANNEL)
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
				shopinfo.setScenario(message)
				ProjectConstants.shopinfo.add(shopinfo)
				Mobile.verifyElementExist(findTestObject("Object Repository/Validate_ShopListScreen"), 0)
			}
			else if(i == 2){
				Mobile.callTestCase(findTestCase("Test Cases/ShopOpen/VisitShopOpen"), null)
				Mobile.callTestCase(findTestCase("Test Cases/ShopOpen/VisitCategoryScenarios/VisitShopCategoriesWith_SKDNA_SKDNA_SKDNA"), null)
				Mobile.callTestCase(findTestCase("Test Cases/ShopOpen/VisitCategoryScenariosWithOverwritePopUp/OverwriteShopCategoriesWith_SKDNA_SKDNA_SKDNA"), null)
				Mobile.callTestCase(findTestCase("Test Cases/ShopOpen/SaveShop"), null)
				shopinfo.setShopname(ProjectConstants.CURRENTVISITING_SHOPNAME)
				shopinfo.setShopchannel(ProjectConstants.CURRENTVISITING_SHOPCHANNEL)
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
				shopinfo.setScenario(message)
				ProjectConstants.shopinfo.add(shopinfo)
				Mobile.verifyElementExist(findTestObject("Object Repository/Validate_ShopListScreen"), 0)
			}
			else if(i == 3){
				Mobile.callTestCase(findTestCase("Test Cases/ShopOpen/VisitShopOpen"), null)
				Mobile.callTestCase(findTestCase("Test Cases/ShopOpen/VisitCategoryScenarios/VisitShopCategoriesWith_SKDNA_SKDNA_SKDNA"), null)
				Mobile.callTestCase(findTestCase("Test Cases/ShopOpen/VisitCategoryScenariosWithOverwritePopUp/VisitShopCategoriesWith_CNAl_CAv_DSA"), null)
				Mobile.callTestCase(findTestCase("Test Cases/ShopOpen/SaveShop"), null)
				shopinfo.setShopname(ProjectConstants.CURRENTVISITING_SHOPNAME)
				shopinfo.setShopchannel(ProjectConstants.CURRENTVISITING_SHOPCHANNEL)
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
				shopinfo.setScenario(message)
				ProjectConstants.shopinfo.add(shopinfo)
				Mobile.verifyElementExist(findTestObject("Object Repository/Validate_ShopListScreen"), 0)
			}
			else if(i == 4){
				Mobile.callTestCase(findTestCase("Test Cases/ShopOpen/VisitShopOpen"), null)
				Mobile.callTestCase(findTestCase("Test Cases/ShopOpen/VisitCategoryScenarios/VisitShopCategoriesWith_CNAl_CAv_DSA"), null)
				Mobile.callTestCase(findTestCase("Test Cases/ShopOpen/VisitCategoryScenariosWithOverwritePopUp/OverwriteShopCategoriesWith_CNAl_CAv_DSA"), null)
				Mobile.callTestCase(findTestCase("Test Cases/ShopOpen/SaveShop"), null)
				shopinfo.setShopname(ProjectConstants.CURRENTVISITING_SHOPNAME)
				shopinfo.setShopchannel(ProjectConstants.CURRENTVISITING_SHOPCHANNEL)
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
				shopinfo.setScenario(message)
				ProjectConstants.shopinfo.add(shopinfo)
				Mobile.verifyElementExist(findTestObject("Object Repository/Validate_ShopListScreen"), 0)
			}
			else{
				break
			}
		}
		for(int i=0; i<ProjectConstants.shopinfo.size(); i++){
			ShopInfo shopinfo = ProjectConstants.shopinfo.get(i)
			String message = "\n\n<------------------------------------------------------------------------------------------------------>\n\n"+
					"Shop Name:  "+shopinfo.getShopname()+"		,		Shop Channel:  "+shopinfo.getShopchannel()+"\n\n"+
					shopinfo.getScenario()+
					"\n\n<------------------------------------------------------------------------------------------------------>"
			KeywordUtil.logInfo(message)
		}
	}
	@Keyword
	def visitShopsWithCategoryLevel_ChillerUtilization_Overwriting(){
		int index = 0
		int totalshops = ProjectConstants.DRIVER.findElementsByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.ListView[1]/*").size()
		for(int i=1; i<=totalshops; i++){
			ShopInfo shopinfo = new ShopInfo()
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
				shopinfo.setShopname(ProjectConstants.CURRENTVISITING_SHOPNAME)
				shopinfo.setShopchannel(ProjectConstants.CURRENTVISITING_SHOPCHANNEL)
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
				shopinfo.setScenario(message)
				ProjectConstants.shopinfo.add(shopinfo)
				Mobile.verifyElementExist(findTestObject("Object Repository/Validate_ShopListScreen"), 0)
			}
			else if(i == 2){
				Mobile.callTestCase(findTestCase("Test Cases/ShopOpen/VisitShopOpen"), null)
				Mobile.callTestCase(findTestCase("Test Cases/ShopOpen/VisitCategoryScenarios/VisitShopCategoriesWith_SKDNA_SKDNA_SKDNA"), null)
				Mobile.callTestCase(findTestCase("Test Cases/ShopOpen/VisitCategoryScenariosWithOverwritePopUp/OverwriteShopCategoriesWith_SKDNA_SKDNA_SKDNA"), null)
				Mobile.callTestCase(findTestCase("Test Cases/ShopOpen/SaveShop"), null)
				shopinfo.setShopname(ProjectConstants.CURRENTVISITING_SHOPNAME)
				shopinfo.setShopchannel(ProjectConstants.CURRENTVISITING_SHOPCHANNEL)
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
				shopinfo.setScenario(message)
				ProjectConstants.shopinfo.add(shopinfo)
				Mobile.verifyElementExist(findTestObject("Object Repository/Validate_ShopListScreen"), 0)
			}
			else if(i == 1){
				Mobile.callTestCase(findTestCase("Test Cases/ShopOpen/VisitShopOpen"), null)
				Mobile.callTestCase(findTestCase("Test Cases/ShopOpen/VisitCategoryScenarios/VisitShopCategoriesWith_CNAl_CTNAv_NSFD"), null)
				Mobile.callTestCase(findTestCase("Test Cases/ShopOpen/VisitCategoryScenariosWithOverwritePopUp/VisitShopCategoriesWith_SKDNA_CAv_DSA"), null)
				Mobile.callTestCase(findTestCase("Test Cases/ShopOpen/SaveShop"), null)
				shopinfo.setShopname(ProjectConstants.CURRENTVISITING_SHOPNAME)
				shopinfo.setShopchannel(ProjectConstants.CURRENTVISITING_SHOPCHANNEL)
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
				shopinfo.setScenario(message)
				ProjectConstants.shopinfo.add(shopinfo)
				Mobile.verifyElementExist(findTestObject("Object Repository/Validate_ShopListScreen"), 0)
			}
			else if(i == 4){
				Mobile.callTestCase(findTestCase("Test Cases/ShopOpen/VisitShopOpen"), null)
				Mobile.callTestCase(findTestCase("Test Cases/ShopOpen/VisitCategoryScenarios/VisitShopCategoriesWith_CNAl_CAv_DSA"), null)
				Mobile.callTestCase(findTestCase("Test Cases/ShopOpen/VisitCategoryScenariosWithOverwritePopUp/OverwriteShopCategoriesWith_CNAl_CAv_DSA"), null)
				Mobile.callTestCase(findTestCase("Test Cases/ShopOpen/SaveShop"), null)
				shopinfo.setShopname(ProjectConstants.CURRENTVISITING_SHOPNAME)
				shopinfo.setShopchannel(ProjectConstants.CURRENTVISITING_SHOPCHANNEL)
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
				shopinfo.setScenario(message)
				ProjectConstants.shopinfo.add(shopinfo)
				Mobile.verifyElementExist(findTestObject("Object Repository/Validate_ShopListScreen"), 0)
			}
			else{
			}
		}
		for(int i=0; i<ProjectConstants.shopinfo.size(); i++){
			ShopInfo shopinfo = ProjectConstants.shopinfo.get(i)
			String message = "\n\n<------------------------------------------------------------------------------------------------------>\n\n"+
					"Shop Name:  "+shopinfo.getShopname()+"		,		Shop Channel:  "+shopinfo.getShopchannel()+"\n\n"+
					shopinfo.getScenario()+
					"\n\n<------------------------------------------------------------------------------------------------------>"
			KeywordUtil.logInfo(message)
		}
	}
}
