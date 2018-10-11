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
import com.ct.qa.struct.TaggedChillersRemark
import com.ct.qa.struct.VisitedCategoryData
import com.ct.qa.struct.VisitedShopDataInfo
import com.ct.qa.struct.VisitedChillerProductsCategoryData
import com.ct.qa.struct.MissingCategoryData
import com.ct.qa.struct.MissingChillerProductsCategoryData
import com.ct.qa.struct.MissingShopDataInfo

public class ShopVisitingScenariosKeywords{

	def displayDataInReport(){
		String message = "\n\n---------------------------------------------Missing Shop Data-----------------------------------------------------------------------------------------------------\n\n"+
				"<-------------------------------------------------------------------------------------------------------------------------------------->"
		for(int i=0; i<ProjectConstants.missingshopdatainfo.size(); i++){
			boolean flag = false
			MissingShopDataInfo missingshopdatainfo = ProjectConstants.missingshopdatainfo.get(i)
			if(missingshopdatainfo != null){
				if(missingshopdatainfo.getMissingshopcategories() != null){
					if(flag == false){
						message = message+"\n\n"+
								String.format("%-11s%-60s%-60s","Shop Name:",missingshopdatainfo.getShopname(),missingshopdatainfo.getShopchannel())+"\n\n"+
								String.format("%-30s%-100s", "Visiting Scenarios:",missingshopdatainfo.getScenario())+
								"\n\n" + String.format("%-30s", "Shop Categories:")
						for(int j=0; j<missingshopdatainfo.getMissingshopcategories().size(); j++){
							message = message+missingshopdatainfo.getMissingshopcategories().get(j)+",   "
						}
						message = message+"\n"+missingshopdatainfo.getMissingshopcategories_errormessage() + "\n\n"
						flag = true
					}
					else{
					}
				}
				if(missingshopdatainfo.getMissingCategoriesData() != null){
					for(int j=0; j<missingshopdatainfo.getMissingCategoriesData().size(); j++){
						MissingCategoryData missingcategorydata = missingshopdatainfo.getMissingCategoriesData().get(j)
						if(missingcategorydata.getMaincategory().equalsIgnoreCase("Chiller Utilization")){
							for(int m=0; m< missingcategorydata.getTaggedchillersremarks().size(); m++){
								TaggedChillersRemark taggedchillerremarks = missingcategorydata.getTaggedchillersremarks().get(m)
								if(taggedchillerremarks.getMissingchillerproductscategories() != null){
									for(int k=0; k<taggedchillerremarks.getMissingchillerproductscategories().size() ; k++){
										MissingChillerProductsCategoryData missingchillerproductcategory = taggedchillerremarks.getMissingchillerproductscategories().get(k)
										if(missingchillerproductcategory.getProductcategories() != null){
											if(flag == false){
												message = message+"\n\n"+
														String.format("%-11s%-60s%-60s","Shop Name:",missingshopdatainfo.getShopname(),missingshopdatainfo.getShopchannel())+"\n\n"+
														String.format("%-30s%-100s", "Visiting Scenarios:",missingshopdatainfo.getScenario())+
														"\n\nProduct Categories:\n\n" +
														String.format("%-30s%-60s","Main Category:",missingcategorydata.getMaincategory()) + "\n" +
														String.format("%-30s%-60s","Chiller type:",taggedchillerremarks.getChillertype()) + "\n" +
														String.format("%-30s%-60s","Chiller Remark:",taggedchillerremarks.getChillerremark()) + "\n" +
														String.format("%-30s","Product Categories:")
												for(int n=0; n<missingchillerproductcategory.getProductcategories().size() ; n++){
													message = message + missingchillerproductcategory.getProductcategories().get(n) + ",   "
												}
												message = message + "\n" + missingchillerproductcategory.getErrormessage_forproductcategories() + "\n\n"
												flag = true
											}
											else{
												message = message +
														"\n\nProduct Categories:\n\n" +
														String.format("%-30s%-60s","Main Category:",missingcategorydata.getMaincategory()) + "\n" +
														String.format("%-30s%-60s","Chiller type:",taggedchillerremarks.getChillertype()) + "\n" +
														String.format("%-30s%-60s","Chiller Remark:",taggedchillerremarks.getChillerremark()) + "\n" +
														String.format("%-30s","Product Categories:")
												for(int n=0; n<missingchillerproductcategory.getProductcategories().size() ; n++){
													message = message + missingchillerproductcategory.getProductcategories().get(n) + ",   "
												}
												message = message + "\n" + missingchillerproductcategory.getErrormessage_forproductcategories() + "\n\n"
											}
										}
									}
								}
							}
						}
						else{
							if(missingcategorydata.getProductcategories() != null){
								if(flag == false){
									message = message+"\n\n"+
											String.format("%-11s%-60s%-60s","Shop Name:",missingshopdatainfo.getShopname(),missingshopdatainfo.getShopchannel())+"\n\n"+
											String.format("%-30s%-100s", "Visiting Scenarios:",missingshopdatainfo.getScenario())+
											"\n\nProduct Categories:\n\n" +
											String.format("%-30s%-60s","Main Category:",missingcategorydata.getMaincategory()) + "\n" +
											String.format("%-30s","Product Categories:")
									for(int k=0; k<missingcategorydata.getProductcategories().size(); k++){
										message = message+missingcategorydata.getProductcategories().get(k)+",	"
									}
									message = message+"\n"+missingcategorydata.getProductcategories_errormessage()+"\n\n"
									flag = true
								}
								else{
									message = message+
											"\n\nProduct Categories:\n\n" +
											String.format("%-30s%-60s","Main Category:",missingcategorydata.getMaincategory()) + "\n" +
											String.format("%-30s","Product Categories:")
									for(int k=0; k<missingcategorydata.getProductcategories().size(); k++){
										message = message+missingcategorydata.getProductcategories().get(k)+",	"
									}
									message = message+"\n"+missingcategorydata.getProductcategories_errormessage()+"\n\n"
								}
							}
						}
					}
				}
				if(missingshopdatainfo.getMissingCategoriesData() != null){
					for(int j=0; j<missingshopdatainfo.getMissingCategoriesData().size(); j++){
						MissingCategoryData missingcategorydata = missingshopdatainfo.getMissingCategoriesData().get(j)
						if(missingcategorydata.getMaincategory().equalsIgnoreCase("Chiller Utilization")){
							for(int m=0; m< missingcategorydata.getTaggedchillersremarks().size(); m++){
								TaggedChillersRemark taggedchillerremarks = missingcategorydata.getTaggedchillersremarks().get(m)
								if(taggedchillerremarks.getMissingchillerproductscategories() != null){
									for(int k=0; k<taggedchillerremarks.getMissingchillerproductscategories().size() ; k++){
										MissingChillerProductsCategoryData missingchillerproductcategory = taggedchillerremarks.getMissingchillerproductscategories().get(k)
										if(missingchillerproductcategory.getProductcategory() != null){
											if(flag == false){
												message = message+"\n\n"+
														String.format("%-11s%-60s%-60s","Shop Name:",missingshopdatainfo.getShopname(),missingshopdatainfo.getShopchannel())+"\n\n"+
														String.format("%-30s%-100s", "Visiting Scenarios:",missingshopdatainfo.getScenario())+
														"\n\nProducts:\n\n" +
														String.format("%-30s%-60s","Main Category:",missingcategorydata.getMaincategory()) + "\n" +
														String.format("%-30s%-60s","Chiller type:",taggedchillerremarks.getChillertype()) + "\n" +
														String.format("%-30s%-60s","Chiller Remark:",taggedchillerremarks.getChillerremark()) + "\n" +
														String.format("%-30s%-60s","Product Category:",missingchillerproductcategory.getProductcategory()) + "\n" +
														String.format("%-30s", "Products:")
												for(int n=0; n<missingchillerproductcategory.getProducts().size() ; n++){
													message = message + missingchillerproductcategory.getProducts().get(n)+",   "
												}
												message = message + "\n" + missingchillerproductcategory.getErrormessage_forproducts() + "\n\n"
												flag = true
											}
											else{
												message = message+
														"\n\nProducts:\n\n" +
														String.format("%-30s%-60s","Main Category:",missingcategorydata.getMaincategory()) + "\n" +
														String.format("%-30s%-60s","Chiller type:",taggedchillerremarks.getChillertype()) + "\n" +
														String.format("%-30s%-60s","Chiller Remark:",taggedchillerremarks.getChillerremark()) + "\n" +
														String.format("%-30s%-60s","Product Category:",missingchillerproductcategory.getProductcategory()) + "\n" +
														String.format("%-30s", "Products:")
												for(int n=0; n<missingchillerproductcategory.getProducts().size() ; n++){
													message = message + missingchillerproductcategory.getProducts().get(n)+", "
												}
												message = message + "\n" + missingchillerproductcategory.getErrormessage_forproducts() + "\n\n"
											}
										}
									}
								}
							}
						}
						else{
							if(missingcategorydata.getProducts() != null){
								if(flag == false){
									message = message+"\n\n"+
											String.format("%-11s%-60s%-60s","Shop Name:",missingshopdatainfo.getShopname(),missingshopdatainfo.getShopchannel())+"\n\n"+
											String.format("%-30s%-100s", "Visiting Scenarios:",missingshopdatainfo.getScenario())+
											"\n\nProducts:\n\n" +
											String.format("%-30s%-60s","Main Category:",missingcategorydata.getMaincategory()) + "\n" +
											String.format("%-30s%-60s","Product Category:",missingcategorydata.getProductCategory()) + "\n" +
											String.format("%-30s", "Products:")
									for(int k=0; k<missingcategorydata.getProducts().size(); k++){
										message = message+missingcategorydata.getProducts().get(k) + ",	"
									}
									message = message + "\n"+missingcategorydata.getProducts_errormessage() + "\n\n"
									flag = true
								}
								else{
									message = message+
											"\n\nProducts:\n\n" +
											String.format("%-30s%-60s","Main Category:",missingcategorydata.getMaincategory()) + "\n" +
											String.format("%-30s%-60s","Product Category:",missingcategorydata.getProductCategory()) + "\n" +
											String.format("%-30s", "Products:")
									for(int k=0; k<missingcategorydata.getProducts().size(); k++){
										message = message+missingcategorydata.getProducts().get(k) + ",	"
									}
									message = message + "\n"+missingcategorydata.getProducts_errormessage() + "\n\n"
								}
							}
						}
					}
				}
				if(flag != false){
					message = message+"\n\n<-------------------------------------------------------------------------------------------------------------------------------------->\n\n"
					KeywordUtil.logInfo(message)
					message = ""
				}
				else{
				}
			}
		}
		message = "\n\n\n---------------------------------------------Visited Shop Data-----------------------------------------------------------------------------------------------------\n\n"+
				"<-------------------------------------------------------------------------------------------------------------------------------------->"
		for(int i=0; i<ProjectConstants.visitedshopdatainfo.size(); i++){
			VisitedShopDataInfo visitedshopdatainfo = ProjectConstants.visitedshopdatainfo.get(i)
			if(visitedshopdatainfo != null){
				message = message+"\n\n"+
						String.format("%-11s%-60s%-60s","Shop Name:",visitedshopdatainfo.getShopname(),visitedshopdatainfo.getShopchannel())+"\n\n"+
						String.format("%-30s%-100s", "Visiting Scenarios:",visitedshopdatainfo.getScenario())
				if(visitedshopdatainfo.getVisitedcategoriesdata() != null){
					for(int j=0; j< visitedshopdatainfo.getVisitedcategoriesdata().size(); j++){
						VisitedCategoryData visitedcategorydata = visitedshopdatainfo.getVisitedcategoriesdata().get(j)
						if(visitedcategorydata.getMaincategory().equalsIgnoreCase("Chiller Utilization")){
							for(int k=0; k< visitedcategorydata.getTaggedchillersremark().size(); k++){
								TaggedChillersRemark taggedchillerremarks = visitedcategorydata.getTaggedchillersremark().get(k)
								if(taggedchillerremarks.getChillerremark().equalsIgnoreCase("Chiller Available") || taggedchillerremarks.getChillerremark().equalsIgnoreCase("Chiller need maintenance") || taggedchillerremarks.getChillerremark().equalsIgnoreCase("Chiller Type not Available")){
									if(taggedchillerremarks.getVisitedchillerproductscategories() != null){
										for(int m=0; m<taggedchillerremarks.getVisitedchillerproductscategories().size() ; m++){
											VisitedChillerProductsCategoryData visitedchillerproductcategory = taggedchillerremarks.getVisitedchillerproductscategories().get(m)
											if(visitedchillerproductcategory.getProductCategory() != null){
												message = message + "\n\n" +
														String.format("%-30s%-60s", "Main Category:",visitedcategorydata.getMaincategory()) + "\n" +
														String.format("%-30s%-60s", "Chiller Type:",taggedchillerremarks.getChillertype()) + "\n" +
														String.format("%-30s%-60s", "Chiller Remark:",taggedchillerremarks.getChillerremark()) + "\n" +
														String.format("%-30s%-60s", "Product Category:",visitedchillerproductcategory.getProductCategory()) + "\n" +
														String.format("%-45s%-14s%-14s%-20s%-24s%-24s%-30s", "Products","Facing","Depth","Stock Count","Overwrite Facing","Overwrite Depth","Overwrite Stock Count")+"\n"
												for(int n=0; n<visitedchillerproductcategory.getShopproductsdata().size() ; n++){
													ShopProductsData shopproductsdata = visitedchillerproductcategory.getShopproductsdata().get(n)
													message = message + String.format("%-45s%-14s%-14s%-20s%-24s%-24s%-30s", shopproductsdata.getProduct(),shopproductsdata.getFacingdata(),shopproductsdata.getDepthdata(),shopproductsdata.getStockcountdata(),shopproductsdata.getOverwritefacingdata(),shopproductsdata.getOverwritedepthdata(),shopproductsdata.getOverwritestockcountdata())+"\n"
												}
											}
										}
									}
								}
								else{
									if(taggedchillerremarks.getVisitedchillerproductscategories() != null){
										for(int m=0; m<taggedchillerremarks.getVisitedchillerproductscategories().size() ; m++){
											VisitedChillerProductsCategoryData visitedchillerproductcategory = taggedchillerremarks.getVisitedchillerproductscategories().get(m)
											if(visitedchillerproductcategory.getProductCategory() != null){
												message = message+ "\n\n" +
														String.format("%-30s%-60s", "Main Category:",visitedcategorydata.getMaincategory()) + "\n" +
														String.format("%-30s%-60s", "Chiller Type:",taggedchillerremarks.getChillertype()) + "\n" +
														String.format("%-30s%-60s", "Chiller Remark:",taggedchillerremarks.getChillerremark()) + "\n" +
														String.format("%-30s%-60s", "Product Category:",visitedchillerproductcategory.getProductCategory()) + "\n" +
														String.format("%-50s%-20s%-20s%-30s%-30s", "Products:","Facing","Stock Taking","Overwrite Facing","Overwrite Stock Taking")+"\n"
												for(int n=0; n<visitedchillerproductcategory.getShopproductsdata().size() ; n++){
													ShopProductsData shopproductsdata = visitedchillerproductcategory.getShopproductsdata().get(n)
													message = message + String.format("%-50s%-20s%-20s%-30s%-30s", shopproductsdata.getProduct(),shopproductsdata.getFacingdata(),shopproductsdata.getStocktakingdata(),shopproductsdata.getOverwritefacingdata(),shopproductsdata.getOverwritestocktakingdata())+"\n"
												}
											}
										}
									}
								}
							}
						}
						else{
							message = message+ "\n\n" +
									String.format("%-30s%-60s", "Main Category:",visitedcategorydata.getMaincategory()) + "\n" +
									String.format("%-30s%-60s", "Product Category:",visitedcategorydata.getProductcategory()) + "\n" +
									String.format("%-50s%-20s%-20s%-30s%-30s", "Products:","Facing","Stock Taking","Overwrite Facing","Overwrite Stock Taking")+"\n"
							for(int k=0; k<visitedcategorydata.getShopProductsdata().size() ; k++){
								ShopProductsData shopproductsdata = visitedcategorydata.getShopProductsdata().get(k)
								message = message + String.format("%-50s%-20s%-20s%-30s%-30s", shopproductsdata.getProduct(),shopproductsdata.getFacingdata(),shopproductsdata.getStocktakingdata(),shopproductsdata.getOverwritefacingdata(),shopproductsdata.getOverwritestocktakingdata())+"\n"
							}
						}
					}
				}
				message = message + "\n\n<-------------------------------------------------------------------------------------------------------------------------------------->\n\n"
				KeywordUtil.logInfo(message)
				message = ""
			}
		}
	}
	def findShop(String _shopname){
		int index = 0
		int totalshops = ProjectConstants.DRIVER.findElementsByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.ListView[1]/*").size()
		for(int i=1; i<=totalshops; i++){
			MobileElement shop = ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout["+i+"]/android.widget.TextView[1]")
			String shopname = shop.getText()
			if(shopname.equalsIgnoreCase(_shopname)){
				ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout["+i+"]").click()
				MobileBuiltInKeywords.verifyElementText(findTestObject("Object Repository/Validate_ShopOptionsScreen" , [('package') : ProjectConstants.PACKAGENAME]), "Options")
				MobileBuiltInKeywords.tap(findTestObject("Object Repository/StartWorking" , [('package') : ProjectConstants.PACKAGENAME]), 0)
				ProjectConstants.visitPopUpForOverwriting()
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
	@Keyword
	def visitShopWithChannelWiseDataVerification(){
		int index = 0
		int totalshops = ProjectConstants.DRIVER.findElementsByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.ListView[1]/*").size()
		for(int i=1; i<=totalshops; i++){
			MissingShopDataInfo missingshopdatainfo = new MissingShopDataInfo()
			VisitedShopDataInfo visitedshopdatainfo = new VisitedShopDataInfo()
			MobileElement shop = ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout["+i+"]/android.widget.TextView[1]")
			ProjectConstants.CURRENTVISITING_SHOPNAME = shop.getText()
			missingshopdatainfo.setShopname(shop.getText())
			visitedshopdatainfo.setShopname(shop.getText())
			ProjectConstants.missingshopdatainfo.add(missingshopdatainfo)
			ProjectConstants.visitedshopdatainfo.add(visitedshopdatainfo)
			ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout["+i+"]").click()
			MobileBuiltInKeywords.verifyElementText(findTestObject("Object Repository/Validate_ShopOptionsScreen" , [('package') : ProjectConstants.PACKAGENAME]), "Options")
			MobileBuiltInKeywords.tap(findTestObject("Object Repository/StartWorking" , [('package') : ProjectConstants.PACKAGENAME]), 0)
			Mobile.delay(15)
			Mobile.verifyElementExist(findTestObject("Object Repository/CommonScreenElements/Validate_MapScreen" , [('package') : ProjectConstants.PACKAGENAME]), 60)
			MobileBuiltInKeywords.tap(findTestObject("Object Repository/CommonScreenElements/Location_CheckIn" , [('package') : ProjectConstants.PACKAGENAME]), 0)
			Mobile.verifyElementExist(findTestObject("Object Repository/CommonScreenElements/Validate_InfoPopUP" , [('package') : ProjectConstants.PACKAGENAME]), 0)
			MobileBuiltInKeywords.tap(findTestObject("Object Repository/CommonScreenElements/InfoPopUp_NoButton" , [('package') : ProjectConstants.PACKAGENAME]), 0)
			if(i == 1){
				Mobile.callTestCase(findTestCase("Test Cases/ShopOpen/VisitShopOpen"), null)
				Mobile.callTestCase(findTestCase("ShopOpen/VisitCategoryScenariosWithoutOverwritePopUp_WithDifferentRemarks/VisitShopCategoriesWith_SKDNA_CNAv_NSFD"), null)
				Mobile.callTestCase(findTestCase("Test Cases/ShopOpen/SaveShop"), null)
				for(int j=0; j<ProjectConstants.missingshopdatainfo.size(); j++){
					if(ProjectConstants.missingshopdatainfo.get(j).getShopname().equals(ProjectConstants.CURRENTVISITING_SHOPNAME)) {
						ProjectConstants.missingshopdatainfo.get(j).setShopchannel(ProjectConstants.CURRENTVISITING_SHOPCHANNEL)
						String message = "Data Verification"
						ProjectConstants.missingshopdatainfo.get(j).setScenario(message)
						break
					}
				}
				for(int j=0; j<ProjectConstants.visitedshopdatainfo.size(); j++){
					if(ProjectConstants.visitedshopdatainfo.get(j).getShopname().equals(ProjectConstants.CURRENTVISITING_SHOPNAME)) {
						ProjectConstants.visitedshopdatainfo.get(j).setShopchannel(ProjectConstants.CURRENTVISITING_SHOPCHANNEL)
						String message = "'SKDNA' for chiller with 'Expiry Issue' remark\n"+
								String.format("%-34s%-100s", "","'No Space for Display' for remaining categories")+"\n"+
								String.format("%-34s%-100s", "","'RTM visit frequency' with 'Twice a week'")+"\n"+
								String.format("%-34s%-100s", "","'Pop Application' with 'No' remark")+"\n"+
								String.format("%-34s%-100s", "","'Retailer Remarks' with 'SM not visiting' remark")+"\n"+
								String.format("%-34s%-100s", "","'Hanger Availability' with 'No' remark")
						ProjectConstants.visitedshopdatainfo.get(j).setScenario(message)
						break
					}
				}
				Mobile.verifyElementExist(findTestObject("Object Repository/Validate_ShopListScreen" , [('package') : ProjectConstants.PACKAGENAME]), 0)
			}
			else if(i == 2){
				Mobile.callTestCase(findTestCase("Test Cases/ShopOpen/VisitShopOpen"), null)
				Mobile.callTestCase(findTestCase("ShopOpen/VisitCategoryScenariosWithoutOverwritePopUp_WithDifferentRemarks/OverwriteShopCategoriesWith_SKDNA_SKDNA_SKDNA"), null)
				Mobile.callTestCase(findTestCase("Test Cases/ShopOpen/SaveShop"), null)
				for(int j=0; j<ProjectConstants.missingshopdatainfo.size(); j++){
					if(ProjectConstants.missingshopdatainfo.get(j).getShopname().equals(ProjectConstants.CURRENTVISITING_SHOPNAME)) {
						ProjectConstants.missingshopdatainfo.get(j).setShopchannel(ProjectConstants.CURRENTVISITING_SHOPCHANNEL)
						String message = "Data Verification"
						ProjectConstants.missingshopdatainfo.get(j).setScenario(message)
						break
					}
				}
				for(int j=0; j<ProjectConstants.visitedshopdatainfo.size(); j++){
					if(ProjectConstants.visitedshopdatainfo.get(j).getShopname().equals(ProjectConstants.CURRENTVISITING_SHOPNAME)) {
						ProjectConstants.visitedshopdatainfo.get(j).setShopchannel(ProjectConstants.CURRENTVISITING_SHOPCHANNEL)
						String message = "'SKDNA' for chiller with 'Others' remark\n"+
								String.format("%-34s%-100s", "","'SKDNA' for remaining categories with 'Others' remark")+"\n"+
								String.format("%-34s%-100s", "","'RTM visit frequency' with 'Twice a week'")+"\n"+
								String.format("%-34s%-100s", "","'Pop Application' with 'No' remark")+"\n"+
								String.format("%-34s%-100s", "","'Retailer Remarks' with 'SM not visiting' remark")+"\n"+
								String.format("%-34s%-100s", "","'Hanger Availability' with 'No' remark")
						ProjectConstants.visitedshopdatainfo.get(j).setScenario(message)
						break
					}
				}
				Mobile.verifyElementExist(findTestObject("Object Repository/Validate_ShopListScreen" , [('package') : ProjectConstants.PACKAGENAME]), 0)
			}
			else if(i == 3){
				Mobile.callTestCase(findTestCase("Test Cases/ShopOpen/VisitShopOpen"), null)
				Mobile.callTestCase(findTestCase("ShopOpen/VisitCategoryScenariosWithoutOverwritePopUp_WithDifferentRemarks/VisitShopCategoriesWith_CNAl_CAv_DSA"), null)
				Mobile.callTestCase(findTestCase("Test Cases/ShopOpen/SaveShop"), null)
				for(int j=0; j<ProjectConstants.missingshopdatainfo.size(); j++){
					if(ProjectConstants.missingshopdatainfo.get(j).getShopname().equals(ProjectConstants.CURRENTVISITING_SHOPNAME)) {
						ProjectConstants.missingshopdatainfo.get(j).setShopchannel(ProjectConstants.CURRENTVISITING_SHOPCHANNEL)
						String message = "Data Verification"
						ProjectConstants.missingshopdatainfo.get(j).setScenario(message)
						break
					}
				}
				for(int j=0; j<ProjectConstants.visitedshopdatainfo.size(); j++){
					if(ProjectConstants.visitedshopdatainfo.get(j).getShopname().equals(ProjectConstants.CURRENTVISITING_SHOPNAME)) {
						ProjectConstants.visitedshopdatainfo.get(j).setShopchannel(ProjectConstants.CURRENTVISITING_SHOPCHANNEL)
						String message = "'Chiller Not Allocated' for chiller\n"+
								String.format("%-34s%-100s", "","'Display Space Available' for remaining categories")+"\n"+
								String.format("%-34s%-100s", "","'RTM visit frequency' with 'Once a week'")+"\n"+
								String.format("%-34s%-100s", "","'Pop Application' with 'No' remark")+"\n"+
								String.format("%-34s%-100s", "","'Retailer Remarks' with 'OB not visiting' remark")+"\n"+
								String.format("%-34s%-100s", "","'Hanger Availability' with 'Yes' remark")
						ProjectConstants.visitedshopdatainfo.get(j).setScenario(message)
						break
					}
				}
				Mobile.verifyElementExist(findTestObject("Object Repository/Validate_ShopListScreen" , [('package') : ProjectConstants.PACKAGENAME]), 0)
			}
			else if(i == 4){
				Mobile.callTestCase(findTestCase("Test Cases/ShopOpen/VisitShopOpen"), null)
				Mobile.callTestCase(findTestCase("ShopOpen/VisitCategoryScenariosWithoutOverwritePopUp_WithDifferentRemarks/OverwriteShopCategoriesWith_CNAl_CAv_DSA"), null)
				Mobile.callTestCase(findTestCase("Test Cases/ShopOpen/SaveShop"), null)
				for(int j=0; j<ProjectConstants.missingshopdatainfo.size(); j++){
					if(ProjectConstants.missingshopdatainfo.get(j).getShopname().equals(ProjectConstants.CURRENTVISITING_SHOPNAME)) {
						ProjectConstants.missingshopdatainfo.get(j).setShopchannel(ProjectConstants.CURRENTVISITING_SHOPCHANNEL)
						String message = "Data Verification"
						ProjectConstants.missingshopdatainfo.get(j).setScenario(message)
						break
					}
				}
				for(int j=0; j<ProjectConstants.visitedshopdatainfo.size(); j++){
					if(ProjectConstants.visitedshopdatainfo.get(j).getShopname().equals(ProjectConstants.CURRENTVISITING_SHOPNAME)) {
						ProjectConstants.visitedshopdatainfo.get(j).setShopchannel(ProjectConstants.CURRENTVISITING_SHOPCHANNEL)
						String message = "Overwrite 'Chiller Not Allocated' for chiller\n"+
								String.format("%-34s%-100s","","Overwrite 'Display Space Available' for remaining categories")+"\n"+
								String.format("%-34s%-100s","","'RTM visit frequency' with 'Twice a week'")+"\n"+
								String.format("%-34s%-100s","","'Pop Application' with 'No' remark")+"\n"+
								String.format("%-34s%-100s","","'Retailer Remarks' with 'SM not visiting' remark")+"\n"+
								String.format("%-34s%-100s","","'Hanger Availability' with 'No' remark")
						ProjectConstants.visitedshopdatainfo.get(j).setScenario(message)
						break
					}
				}
				Mobile.verifyElementExist(findTestObject("Object Repository/Validate_ShopListScreen" , [('package') : ProjectConstants.PACKAGENAME]), 0)
			}
			else{
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
						String message = "'Chiller Not Allocated' for chiller\n"+
								String.format("%-34s%-100s","","'Display Space Available' for remaining categories")+"\n"+
								String.format("%-34s%-100s","","'RTM visit frequency' with 'Once a week'")+"\n"+
								String.format("%-34s%-100s","","'Pop Application' with 'No' remark")+"\n"+
								String.format("%-34s%-100s","","'Retailer Remarks' with 'OB not visiting' remark")+"\n"+
								String.format("%-34s%-100s","","'Hanger Availability' with 'Yes' remark")
						ProjectConstants.visitedshopdatainfo.get(j).setScenario(message)
						break
					}
				}
				Mobile.verifyElementExist(findTestObject("Object Repository/Validate_ShopListScreen" , [('package') : ProjectConstants.PACKAGENAME]), 0)
			}
		}
		while(true){
			MissingShopDataInfo missingshopdatainfo = new MissingShopDataInfo()
			VisitedShopDataInfo visitedshopdatainfo = new VisitedShopDataInfo()
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
				missingshopdatainfo.setShopname(shop.getText())
				visitedshopdatainfo.setShopname(shop.getText())
				ProjectConstants.missingshopdatainfo.add(missingshopdatainfo)
				ProjectConstants.visitedshopdatainfo.add(visitedshopdatainfo)
				ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout["+index+"]").click()
				MobileBuiltInKeywords.verifyElementText(findTestObject("Object Repository/Validate_ShopOptionsScreen" , [('package') : ProjectConstants.PACKAGENAME]), "Options")
				MobileBuiltInKeywords.tap(findTestObject("Object Repository/StartWorking" , [('package') : ProjectConstants.PACKAGENAME]), 0)
				Mobile.delay(15)
				Mobile.verifyElementExist(findTestObject("Object Repository/CommonScreenElements/Validate_MapScreen" , [('package') : ProjectConstants.PACKAGENAME]), 60)
				MobileBuiltInKeywords.tap(findTestObject("Object Repository/CommonScreenElements/Location_CheckIn" , [('package') : ProjectConstants.PACKAGENAME]), 0)
				Mobile.verifyElementExist(findTestObject("Object Repository/CommonScreenElements/Validate_InfoPopUP" , [('package') : ProjectConstants.PACKAGENAME]), 0)
				MobileBuiltInKeywords.tap(findTestObject("Object Repository/CommonScreenElements/InfoPopUp_NoButton" , [('package') : ProjectConstants.PACKAGENAME]), 0)
				Mobile.callTestCase(findTestCase("Test Cases/ShopOpen/VisitShopOpen"), null)
				Mobile.callTestCase(findTestCase("ShopOpen/VisitCategoryScenarios/VisitShopCategoriesWithDataVerification"), null)
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
						String message = "'Chiller Not Allocated' for chiller\n"+
								String.format("%-34s%-100s","","'Display Space Available' for remaining categories")+"\n"+
								String.format("%-34s%-100s","","'RTM visit frequency' with 'Once a week'")+"\n"+
								String.format("%-34s%-100s","","'Pop Application' with 'No' remark")+"\n"+
								String.format("%-34s%-100s","","'Retailer Remarks' with 'OB not visiting' remark")+"\n"+
								String.format("%-34s%-100s","","'Hanger Availability' with 'Yes' remark")
						ProjectConstants.visitedshopdatainfo.get(j).setScenario(message)
						break
					}
				}
				Mobile.verifyElementExist(findTestObject("Object Repository/Validate_ShopListScreen" , [('package') : ProjectConstants.PACKAGENAME]), 0)
			}
		}
		displayDataInReport()
	}
	@Keyword
	def visitShopWithChillerWiseDataVerification(){
		int index = 0
		int totalshops = ProjectConstants.DRIVER.findElementsByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.ListView[1]/*").size()
		for(int i=1; i<= totalshops; i++){
			MissingShopDataInfo missingshopdatainfo = new MissingShopDataInfo()
			VisitedShopDataInfo visitedshopdatainfo = new VisitedShopDataInfo()
			MobileElement shop = ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout["+i+"]/android.widget.TextView[1]")
			ProjectConstants.CURRENTVISITING_SHOPNAME = shop.getText()
			missingshopdatainfo.setShopname(shop.getText())
			visitedshopdatainfo.setShopname(shop.getText())
			ProjectConstants.missingshopdatainfo.add(missingshopdatainfo)
			ProjectConstants.visitedshopdatainfo.add(visitedshopdatainfo)
			ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout["+i+"]").click()
			MobileBuiltInKeywords.verifyElementText(findTestObject("Object Repository/Validate_ShopOptionsScreen" , [('package') : ProjectConstants.PACKAGENAME]), "Options")
			MobileBuiltInKeywords.tap(findTestObject("Object Repository/StartWorking" , [('package') : ProjectConstants.PACKAGENAME]), 0)
			Mobile.delay(15)
			Mobile.verifyElementExist(findTestObject("Object Repository/CommonScreenElements/Validate_MapScreen" , [('package') : ProjectConstants.PACKAGENAME]), 60)
			MobileBuiltInKeywords.tap(findTestObject("Object Repository/CommonScreenElements/Location_CheckIn" , [('package') : ProjectConstants.PACKAGENAME]), 0)
			Mobile.verifyElementExist(findTestObject("Object Repository/CommonScreenElements/Validate_InfoPopUP" , [('package') : ProjectConstants.PACKAGENAME]), 0)
			MobileBuiltInKeywords.tap(findTestObject("Object Repository/CommonScreenElements/InfoPopUp_NoButton" , [('package') : ProjectConstants.PACKAGENAME]), 0)
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
					String message = "'Scenario given bellow' for chiller utilization\n"+
							String.format("%-34s%-100s","","'Display Space Available' for remaining categories")+"\n"+
							String.format("%-34s%-100s","","'RTM visit frequency' with 'Once a week'")+"\n"+
							String.format("%-34s%-100s","","'Pop Application' with 'No' remark")+"\n"+
							String.format("%-34s%-100s","","'Retailer Remarks' with 'OB not visiting' remark")+"\n"+
							String.format("%-34s%-100s","","'Hanger Availability' with 'Yes' remark")
					ProjectConstants.visitedshopdatainfo.get(j).setScenario(message)
					break
				}
			}
			Mobile.verifyElementExist(findTestObject("Object Repository/Validate_ShopListScreen" , [('package') : ProjectConstants.PACKAGENAME]), 0)
		}
		while(true){
			MissingShopDataInfo missingshopdatainfo = new MissingShopDataInfo()
			VisitedShopDataInfo visitedshopdatainfo = new VisitedShopDataInfo()
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
				missingshopdatainfo.setShopname(shop.getText())
				visitedshopdatainfo.setShopname(shop.getText())
				ProjectConstants.missingshopdatainfo.add(missingshopdatainfo)
				ProjectConstants.visitedshopdatainfo.add(visitedshopdatainfo)
				ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout["+index+"]").click()
				MobileBuiltInKeywords.verifyElementText(findTestObject("Object Repository/Validate_ShopOptionsScreen" , [('package') : ProjectConstants.PACKAGENAME]), "Options")
				MobileBuiltInKeywords.tap(findTestObject("Object Repository/StartWorking" , [('package') : ProjectConstants.PACKAGENAME]), 0)
				Mobile.delay(15)
				Mobile.verifyElementExist(findTestObject("Object Repository/CommonScreenElements/Validate_MapScreen" , [('package') : ProjectConstants.PACKAGENAME]), 60)
				MobileBuiltInKeywords.tap(findTestObject("Object Repository/CommonScreenElements/Location_CheckIn" , [('package') : ProjectConstants.PACKAGENAME]), 0)
				Mobile.verifyElementExist(findTestObject("Object Repository/CommonScreenElements/Validate_InfoPopUP" , [('package') : ProjectConstants.PACKAGENAME]), 0)
				MobileBuiltInKeywords.tap(findTestObject("Object Repository/CommonScreenElements/InfoPopUp_NoButton" , [('package') : ProjectConstants.PACKAGENAME]), 0)
				Mobile.callTestCase(findTestCase("Test Cases/ShopOpen/VisitShopOpen"), null)
				Mobile.callTestCase(findTestCase("ShopOpen/VisitCategoryScenarios/VisitShopCategoriesWithDataVerification"), null)
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
						String message = "'Scenario given bellow' for chiller utilization\n"+
								String.format("%-34s%-100s","","'Display Space Available' for remaining categories")+"\n"+
								String.format("%-34s%-100s","","'RTM visit frequency' with 'Once a week'")+"\n"+
								String.format("%-34s%-100s","","'Pop Application' with 'No' remark")+"\n"+
								String.format("%-34s%-100s","","'Retailer Remarks' with 'OB not visiting' remark")+"\n"+
								String.format("%-34s%-100s","","'Hanger Availability' with 'Yes' remark")
						ProjectConstants.visitedshopdatainfo.get(j).setScenario(message)
						break
					}
				}
				Mobile.verifyElementExist(findTestObject("Object Repository/Validate_ShopListScreen" , [('package') : ProjectConstants.PACKAGENAME]), 0)
			}
		}
		displayDataInReport()
	}
	@Keyword
	def visitShopsWithFirstVisit_BeforeOverwriting(){
		int index = 0
		int totalshops = ProjectConstants.DRIVER.findElementsByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.ListView[1]/*").size()
		for(int i=1; i<=6; i++){
			MissingShopDataInfo missingshopdatainfo = new MissingShopDataInfo()
			VisitedShopDataInfo visitedshopdatainfo = new VisitedShopDataInfo()
			MobileElement shop = ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout["+i+"]/android.widget.TextView[1]")
			ProjectConstants.CURRENTVISITING_SHOPNAME = shop.getText()
			missingshopdatainfo.setShopname(shop.getText())
			visitedshopdatainfo.setShopname(shop.getText())
			ProjectConstants.missingshopdatainfo.add(missingshopdatainfo)
			ProjectConstants.visitedshopdatainfo.add(visitedshopdatainfo)
			ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout["+i+"]").click()
			MobileBuiltInKeywords.verifyElementText(findTestObject("Object Repository/Validate_ShopOptionsScreen" , [('package') : ProjectConstants.PACKAGENAME]), "Options")
			MobileBuiltInKeywords.tap(findTestObject("Object Repository/StartWorking" , [('package') : ProjectConstants.PACKAGENAME]), 0)
			Mobile.delay(15)
			Mobile.verifyElementExist(findTestObject("Object Repository/CommonScreenElements/Validate_MapScreen" , [('package') : ProjectConstants.PACKAGENAME]), 60)
			MobileBuiltInKeywords.tap(findTestObject("Object Repository/CommonScreenElements/Location_CheckIn" , [('package') : ProjectConstants.PACKAGENAME]), 0)
			Mobile.verifyElementExist(findTestObject("Object Repository/CommonScreenElements/Validate_InfoPopUP" , [('package') : ProjectConstants.PACKAGENAME]), 0)
			MobileBuiltInKeywords.tap(findTestObject("Object Repository/CommonScreenElements/InfoPopUp_NoButton" , [('package') : ProjectConstants.PACKAGENAME]), 0)
			// shop closed to shop open
			if(i == 1){
				Mobile.callTestCase(findTestCase("Test Cases/ShopOpen/VisitShopOpen"), null)
				Mobile.callTestCase(findTestCase("Test Cases/ShopOpen/VisitCategoryScenarios/VisitShopCategoriesWith_SKDNA_SKDNA_SKDNA"), null)
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
						String message = "visit shop with 'Shop Open' with SKDNA 'Expiry Issue' remark"
						ProjectConstants.visitedshopdatainfo.get(j).setScenario(message)
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
						String message = "visit shop with 'Shop Closed'"
						ProjectConstants.visitedshopdatainfo.get(j).setScenario(message)
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
						String message = "visit shop with 'Shopkeeper did not allow' with 'Others' remark"
						ProjectConstants.visitedshopdatainfo.get(j).setScenario(message)
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
						String message = "visit shop with 'Shop not found'"
						ProjectConstants.visitedshopdatainfo.get(j).setScenario(message)
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
						String message = "visit shop with 'Shop permanently closed'"
						ProjectConstants.visitedshopdatainfo.get(j).setScenario(message)
						break
					}
				}
				Mobile.verifyElementExist(findTestObject("Object Repository/Validate_ShopListScreen" , [('package') : ProjectConstants.PACKAGENAME]), 0)
			}
			//shop open to shop open
			else if(i == 6){
				Mobile.callTestCase(findTestCase("Test Cases/ShopOpen/VisitShopOpen"), null)
				Mobile.callTestCase(findTestCase("ShopOpen/VisitCategoryScenariosWithoutOverwritePopUp_WithDifferentRemarks/VisitShopCategoriesWith_SKDNA_CNAv_NSFD"), null)
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
						String message = "'SKDNA' for chiller with 'Expiry Issue' remark\n"+
								String.format("%-34s%-100s", "","'No Space for Display' for remaining categories")+"\n"+
								String.format("%-34s%-100s", "","'RTM visit frequency' with 'Twice a week'")+"\n"+
								String.format("%-34s%-100s", "","'Pop Application' with 'No' remark")+"\n"+
								String.format("%-34s%-100s", "","'Retailer Remarks' with 'SM not visiting' remark")+"\n"+
								String.format("%-34s%-100s", "","'Hanger Availability' with 'No' remark")
						ProjectConstants.visitedshopdatainfo.get(j).setScenario(message)
						break
					}
				}
				Mobile.verifyElementExist(findTestObject("Object Repository/Validate_ShopListScreen" , [('package') : ProjectConstants.PACKAGENAME]), 0)
			}
			else{
				break
			}
		}
		displayDataInReport()
	}
	@Keyword
	def visitShopsWithShopLevelOverwriting(){
		int index = 0
		int totalshops = ProjectConstants.DRIVER.findElementsByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.ListView[1]/*").size()
		for(int i=1; i<=6; i++){
			MissingShopDataInfo missingshopdatainfo = new MissingShopDataInfo()
			VisitedShopDataInfo visitedshopdatainfo = new VisitedShopDataInfo()
			MobileElement shop = ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout["+i+"]/android.widget.TextView[1]")
			ProjectConstants.CURRENTVISITING_SHOPNAME = shop.getText()
			missingshopdatainfo.setShopname(shop.getText())
			visitedshopdatainfo.setShopname(shop.getText())
			ProjectConstants.missingshopdatainfo.add(missingshopdatainfo)
			ProjectConstants.visitedshopdatainfo.add(visitedshopdatainfo)
			ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout["+i+"]").click()
			MobileBuiltInKeywords.verifyElementText(findTestObject("Object Repository/Validate_ShopOptionsScreen" , [('package') : ProjectConstants.PACKAGENAME]), "Options")
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
				Mobile.callTestCase(findTestCase("Test Cases/ShopOpen/VisitShopOpen"), null)
				Mobile.callTestCase(findTestCase("Test Cases/ShopOpen/VisitCategoryScenarios/VisitShopCategoriesWith_SKDNA_SKDNA_SKDNA"), null)
				Mobile.callTestCase(findTestCase("Test Cases/ShopOpen/SaveShop"), null)
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
						String message = "(1) visit shop with 'Shop Closed'\n"+
								String.format("%-30s%-100s", "","(2) visit shop with 'Shop Open' with SKDNA 'Expiry Issue' remark")
						ProjectConstants.visitedshopdatainfo.get(j).setScenario(message)
						break
					}
				}
				Mobile.verifyElementExist(findTestObject("Object Repository/Validate_ShopListScreen" , [('package') : ProjectConstants.PACKAGENAME]), 0)
			}
			//shop open to shop closed
			else if(i == 2){
				Mobile.callTestCase(findTestCase("Test Cases/ShopOpen/VisitShopOpen"), null)
				Mobile.callTestCase(findTestCase("Test Cases/ShopOpen/VisitCategoryScenarios/VisitShopCategoriesWith_SKDNA_SKDNA_SKDNA"), null)
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
						String message = "(1) visit shop with 'Shop Open' with SKDNA 'Expiry Issue' remark\n"+
								String.format("%-30s%-100s","","(2) visit shop with 'Shop Closed'")
						ProjectConstants.visitedshopdatainfo.get(j).setScenario(message)
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
						String message = "(1) visit shop with 'Shop Keeper did not allow' with 'Expiry Issue' remark\n"+
								String.format("%-30s%-100s","","(2) visit shop with 'Shopkeeper did not allow' with 'Others' remark")
						ProjectConstants.visitedshopdatainfo.get(j).setScenario(message)
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
						String message = "(1) visit shop with 'Shop permanently closed'\n"+
								String.format("%-30s%-100s", "","(2) visit shop with 'Shop not found'")
						ProjectConstants.visitedshopdatainfo.get(j).setScenario(message)
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
						String message = "(1) visit shop with 'Shop Closed'\n"+
								String.format("%-30s%-100s", "","(2) visit shop with 'Shop permanently closed'")
						ProjectConstants.visitedshopdatainfo.get(j).setScenario(message)
						break
					}
				}
				Mobile.verifyElementExist(findTestObject("Object Repository/Validate_ShopListScreen" , [('package') : ProjectConstants.PACKAGENAME]), 0)
			}
			//shop open to shop open
			else if(i == 6){
				Mobile.callTestCase(findTestCase("Test Cases/ShopOpen/VisitShopOpen"), null)
				Mobile.callTestCase(findTestCase("Test Cases/ShopOpen/VisitCategoryScenarios/VisitShopCategoriesWith_CNAl_CAv_DSA"), null)
				Mobile.callTestCase(findTestCase("Test Cases/ShopOpen/SaveShop"), null)
				Mobile.verifyElementExist(findTestObject("Object Repository/Validate_ShopListScreen" , [('package') : ProjectConstants.PACKAGENAME]), 0)
				findShop(ProjectConstants.CURRENTVISITING_SHOPNAME)
				Mobile.callTestCase(findTestCase("Test Cases/ShopOpen/VisitShopOpen"), null)
				Mobile.callTestCase(findTestCase("ShopOpen/VisitCategoryScenariosWithoutOverwritePopUp_WithDifferentRemarks/VisitShopCategoriesWith_SKDNA_CNAv_NSFD"), null)
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
						String message = "(1) 'Chiller Not Allocated' for chiller\n"+
								String.format("%-34s%-100s", "","'Display Space Available' for remaining categories")+"\n"+
								String.format("%-34s%-100s", "","'RTM visit frequency' with 'Once a week'")+"\n"+
								String.format("%-34s%-100s", "","'Pop Application' with 'No' remark")+"\n"+
								String.format("%-34s%-100s", "","'Retailer Remarks' with 'OB not visiting' remark")+"\n"+
								String.format("%-34s%-100s", "","'Hanger Availability' with 'Yes' remark")+"\n\n"+
								String.format("%-30s%-100s", "","(2) 'SKDNA' for chiller with 'Expiry Issue' remark")+"\n"+
								String.format("%-34s%-100s", "","'No Space for Display' for remaining categories")+"\n"+
								String.format("%-34s%-100s", "","'RTM visit frequency' with 'Twice a week'")+"\n"+
								String.format("%-34s%-100s", "","'Pop Application' with 'No' remark")+"\n"+
								String.format("%-34s%-100s", "","'Retailer Remarks' with 'SM not visiting' remark")+"\n"+
								String.format("%-34s%-100s", "","'Hanger Availability' with 'No' remark")
						ProjectConstants.visitedshopdatainfo.get(j).setScenario(message)
						break
					}
				}
				Mobile.verifyElementExist(findTestObject("Object Repository/Validate_ShopListScreen" , [('package') : ProjectConstants.PACKAGENAME]), 0)
			}
			else{
				break
			}
		}
		displayDataInReport()
	}
	@Keyword
	def visitShopsWithCategoryLevel_Chiller_FirstVisit(){
		int index = 0
		int totalshops = ProjectConstants.DRIVER.findElementsByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.ListView[1]/*").size()
		for(int i=1; i<=4; i++){
			MissingShopDataInfo missingshopdatainfo = new MissingShopDataInfo()
			VisitedShopDataInfo visitedshopdatainfo = new VisitedShopDataInfo()
			MobileElement shop = ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout["+i+"]/android.widget.TextView[1]")
			ProjectConstants.CURRENTVISITING_SHOPNAME = shop.getText()
			missingshopdatainfo.setShopname(shop.getText())
			visitedshopdatainfo.setShopname(shop.getText())
			ProjectConstants.missingshopdatainfo.add(missingshopdatainfo)
			ProjectConstants.visitedshopdatainfo.add(visitedshopdatainfo)
			ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout["+i+"]").click()
			MobileBuiltInKeywords.verifyElementText(findTestObject("Object Repository/Validate_ShopOptionsScreen" , [('package') : ProjectConstants.PACKAGENAME]), "Options")
			MobileBuiltInKeywords.tap(findTestObject("Object Repository/StartWorking" , [('package') : ProjectConstants.PACKAGENAME]), 0)
			Mobile.delay(15)
			Mobile.verifyElementExist(findTestObject("Object Repository/CommonScreenElements/Validate_MapScreen" , [('package') : ProjectConstants.PACKAGENAME]), 60)
			MobileBuiltInKeywords.tap(findTestObject("Object Repository/CommonScreenElements/Location_CheckIn" , [('package') : ProjectConstants.PACKAGENAME]), 0)
			Mobile.verifyElementExist(findTestObject("Object Repository/CommonScreenElements/Validate_InfoPopUP" , [('package') : ProjectConstants.PACKAGENAME]), 0)
			MobileBuiltInKeywords.tap(findTestObject("Object Repository/CommonScreenElements/InfoPopUp_NoButton" , [('package') : ProjectConstants.PACKAGENAME]), 0)
			if(i == 1){
				Mobile.callTestCase(findTestCase("Test Cases/ShopOpen/VisitShopOpen"), null)
				Mobile.callTestCase(findTestCase("ShopOpen/VisitCategoryScenariosWithoutOverwritePopUp_WithDifferentRemarks/VisitShopCategoriesWith_SKDNA_CNAv_NSFD"), null)
				Mobile.callTestCase(findTestCase("Test Cases/ShopOpen/SaveShop"), null)
				for(int j=0; j<ProjectConstants.missingshopdatainfo.size(); j++){
					if(ProjectConstants.missingshopdatainfo.get(j).getShopname().equals(ProjectConstants.CURRENTVISITING_SHOPNAME)) {
						ProjectConstants.missingshopdatainfo.get(j).setShopchannel(ProjectConstants.CURRENTVISITING_SHOPCHANNEL)
						String message = "Category First Visit"
						ProjectConstants.missingshopdatainfo.get(j).setScenario(message)
						break
					}
				}
				for(int j=0; j<ProjectConstants.visitedshopdatainfo.size(); j++){
					if(ProjectConstants.visitedshopdatainfo.get(j).getShopname().equals(ProjectConstants.CURRENTVISITING_SHOPNAME)) {
						ProjectConstants.visitedshopdatainfo.get(j).setShopchannel(ProjectConstants.CURRENTVISITING_SHOPCHANNEL)
						String message = "'SKDNA' for chiller with 'Expiry Issue' remark\n"+
								String.format("%-34s%-100s", "","'No Space for Display' for remaining categories")+"\n"+
								String.format("%-34s%-100s", "","'RTM visit frequency' with 'Twice a week'")+"\n"+
								String.format("%-34s%-100s", "","'Pop Application' with 'No' remark")+"\n"+
								String.format("%-34s%-100s", "","'Retailer Remarks' with 'SM not visiting' remark")+"\n"+
								String.format("%-34s%-100s", "","'Hanger Availability' with 'No' remark")
						ProjectConstants.visitedshopdatainfo.get(j).setScenario(message)
						break
					}
				}
				Mobile.verifyElementExist(findTestObject("Object Repository/Validate_ShopListScreen" , [('package') : ProjectConstants.PACKAGENAME]), 0)
			}
			else if(i == 2){
				Mobile.callTestCase(findTestCase("Test Cases/ShopOpen/VisitShopOpen"), null)
				Mobile.callTestCase(findTestCase("ShopOpen/VisitCategoryScenariosWithoutOverwritePopUp_WithDifferentRemarks/OverwriteShopCategoriesWith_SKDNA_SKDNA_SKDNA"), null)
				Mobile.callTestCase(findTestCase("Test Cases/ShopOpen/SaveShop"), null)
				for(int j=0; j<ProjectConstants.missingshopdatainfo.size(); j++){
					if(ProjectConstants.missingshopdatainfo.get(j).getShopname().equals(ProjectConstants.CURRENTVISITING_SHOPNAME)) {
						ProjectConstants.missingshopdatainfo.get(j).setShopchannel(ProjectConstants.CURRENTVISITING_SHOPCHANNEL)
						String message = "Category First Visit"
						ProjectConstants.missingshopdatainfo.get(j).setScenario(message)
						break
					}
				}
				for(int j=0; j<ProjectConstants.visitedshopdatainfo.size(); j++){
					if(ProjectConstants.visitedshopdatainfo.get(j).getShopname().equals(ProjectConstants.CURRENTVISITING_SHOPNAME)) {
						ProjectConstants.visitedshopdatainfo.get(j).setShopchannel(ProjectConstants.CURRENTVISITING_SHOPCHANNEL)
						String message = "'SKDNA' for chiller with 'Others' remark\n"+
								String.format("%-34s%-100s", "","'SKDNA' for remaining categories with 'Others' remark")+"\n"+
								String.format("%-34s%-100s", "","'RTM visit frequency' with 'Twice a week'")+"\n"+
								String.format("%-34s%-100s", "","'Pop Application' with 'No' remark")+"\n"+
								String.format("%-34s%-100s", "","'Retailer Remarks' with 'SM not visiting' remark")+"\n"+
								String.format("%-34s%-100s", "","'Hanger Availability' with 'No' remark")
						ProjectConstants.visitedshopdatainfo.get(j).setScenario(message)
						break
					}
				}
				Mobile.verifyElementExist(findTestObject("Object Repository/Validate_ShopListScreen" , [('package') : ProjectConstants.PACKAGENAME]), 0)
			}
			else if(i == 3){
				Mobile.callTestCase(findTestCase("Test Cases/ShopOpen/VisitShopOpen"), null)
				Mobile.callTestCase(findTestCase("ShopOpen/VisitCategoryScenariosWithoutOverwritePopUp_WithDifferentRemarks/VisitShopCategoriesWith_CNAl_CAv_DSA"), null)
				Mobile.callTestCase(findTestCase("Test Cases/ShopOpen/SaveShop"), null)
				for(int j=0; j<ProjectConstants.missingshopdatainfo.size(); j++){
					if(ProjectConstants.missingshopdatainfo.get(j).getShopname().equals(ProjectConstants.CURRENTVISITING_SHOPNAME)) {
						ProjectConstants.missingshopdatainfo.get(j).setShopchannel(ProjectConstants.CURRENTVISITING_SHOPCHANNEL)
						String message = "Category First Visit"
						ProjectConstants.missingshopdatainfo.get(j).setScenario(message)
						break
					}
				}
				for(int j=0; j<ProjectConstants.visitedshopdatainfo.size(); j++){
					if(ProjectConstants.visitedshopdatainfo.get(j).getShopname().equals(ProjectConstants.CURRENTVISITING_SHOPNAME)) {
						ProjectConstants.visitedshopdatainfo.get(j).setShopchannel(ProjectConstants.CURRENTVISITING_SHOPCHANNEL)
						String message = "'Chiller Not Allocated' for chiller\n"+
								String.format("%-34s%-100s", "","'Display Space Available' for remaining categories")+"\n"+
								String.format("%-34s%-100s", "","'RTM visit frequency' with 'Once a week'")+"\n"+
								String.format("%-34s%-100s", "","'Pop Application' with 'No' remark")+"\n"+
								String.format("%-34s%-100s", "","'Retailer Remarks' with 'OB not visiting' remark")+"\n"+
								String.format("%-34s%-100s", "","'Hanger Availability' with 'Yes' remark")
						ProjectConstants.visitedshopdatainfo.get(j).setScenario(message)
						break
					}
				}
				Mobile.verifyElementExist(findTestObject("Object Repository/Validate_ShopListScreen" , [('package') : ProjectConstants.PACKAGENAME]), 0)
			}
			else if(i == 4){
				Mobile.callTestCase(findTestCase("Test Cases/ShopOpen/VisitShopOpen"), null)
				Mobile.callTestCase(findTestCase("ShopOpen/VisitCategoryScenariosWithoutOverwritePopUp_WithDifferentRemarks/OverwriteShopCategoriesWith_CNAl_CAv_DSA"), null)
				Mobile.callTestCase(findTestCase("Test Cases/ShopOpen/SaveShop"), null)
				for(int j=0; j<ProjectConstants.missingshopdatainfo.size(); j++){
					if(ProjectConstants.missingshopdatainfo.get(j).getShopname().equals(ProjectConstants.CURRENTVISITING_SHOPNAME)) {
						ProjectConstants.missingshopdatainfo.get(j).setShopchannel(ProjectConstants.CURRENTVISITING_SHOPCHANNEL)
						String message = "Category First Visit"
						ProjectConstants.missingshopdatainfo.get(j).setScenario(message)
						break
					}
				}
				for(int j=0; j<ProjectConstants.visitedshopdatainfo.size(); j++){
					if(ProjectConstants.visitedshopdatainfo.get(j).getShopname().equals(ProjectConstants.CURRENTVISITING_SHOPNAME)) {
						ProjectConstants.visitedshopdatainfo.get(j).setShopchannel(ProjectConstants.CURRENTVISITING_SHOPCHANNEL)
						String message = "Overwrite 'Chiller Not Allocated' for chiller\n"+
								String.format("%-34s%-100s","","Overwrite 'Display Space Available' for remaining categories")+"\n"+
								String.format("%-34s%-100s","","'RTM visit frequency' with 'Twice a week'")+"\n"+
								String.format("%-34s%-100s","","'Pop Application' with 'No' remark")+"\n"+
								String.format("%-34s%-100s","","'Retailer Remarks' with 'SM not visiting' remark")+"\n"+
								String.format("%-34s%-100s","","'Hanger Availability' with 'No' remark")
						ProjectConstants.visitedshopdatainfo.get(j).setScenario(message)
						break
					}
				}
				Mobile.verifyElementExist(findTestObject("Object Repository/Validate_ShopListScreen" , [('package') : ProjectConstants.PACKAGENAME]), 0)
			}
			else{
				break
			}
		}
		displayDataInReport()
	}






	@Keyword
	def visitShopsWithCategoryLevel_Chiller_Overwriting(){
		int index = 0
		int totalshops = ProjectConstants.DRIVER.findElementsByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.ListView[1]/*").size()
		for(int i=1; i<=4; i++){
			MissingShopDataInfo missingshopdatainfo = new MissingShopDataInfo()
			VisitedShopDataInfo visitedshopdatainfo = new VisitedShopDataInfo()
			MobileElement shop = ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout["+i+"]/android.widget.TextView[1]")
			ProjectConstants.CURRENTVISITING_SHOPNAME = shop.getText()
			missingshopdatainfo.setShopname(shop.getText())
			visitedshopdatainfo.setShopname(shop.getText())
			ProjectConstants.missingshopdatainfo.add(missingshopdatainfo)
			ProjectConstants.visitedshopdatainfo.add(visitedshopdatainfo)
			ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout["+i+"]").click()
			MobileBuiltInKeywords.verifyElementText(findTestObject("Object Repository/Validate_ShopOptionsScreen" , [('package') : ProjectConstants.PACKAGENAME]), "Options")
			MobileBuiltInKeywords.tap(findTestObject("Object Repository/StartWorking" , [('package') : ProjectConstants.PACKAGENAME]), 0)
			Mobile.delay(15)
			Mobile.verifyElementExist(findTestObject("Object Repository/CommonScreenElements/Validate_MapScreen" , [('package') : ProjectConstants.PACKAGENAME]), 60)
			MobileBuiltInKeywords.tap(findTestObject("Object Repository/CommonScreenElements/Location_CheckIn" , [('package') : ProjectConstants.PACKAGENAME]), 0)
			Mobile.verifyElementExist(findTestObject("Object Repository/CommonScreenElements/Validate_InfoPopUP" , [('package') : ProjectConstants.PACKAGENAME]), 0)
			MobileBuiltInKeywords.tap(findTestObject("Object Repository/CommonScreenElements/InfoPopUp_NoButton" , [('package') : ProjectConstants.PACKAGENAME]), 0)
			if(i == 1){
				Mobile.callTestCase(findTestCase("Test Cases/ShopOpen/VisitShopOpen"), null)
				Mobile.callTestCase(findTestCase("Test Cases/ShopOpen/VisitCategoryScenarios/VisitShopCategoriesWith_CNAl_CAv_DSA"), null)
				Mobile.callTestCase(findTestCase("ShopOpen/VisitCategoryScenariosWithOverwritePopUp_WithDifferentRemarks/VisitShopCategoriesWith_SKDNA_CNAv_NSFD"), null)
				Mobile.callTestCase(findTestCase("Test Cases/ShopOpen/SaveShop"), null)
				for(int j=0; j<ProjectConstants.missingshopdatainfo.size(); j++){
					if(ProjectConstants.missingshopdatainfo.get(j).getShopname().equals(ProjectConstants.CURRENTVISITING_SHOPNAME)) {
						ProjectConstants.missingshopdatainfo.get(j).setShopchannel(ProjectConstants.CURRENTVISITING_SHOPCHANNEL)
						String message = "Category Overwrite Scenarios"
						ProjectConstants.missingshopdatainfo.get(j).setScenario(message)
						break
					}
				}
				for(int j=0; j<ProjectConstants.visitedshopdatainfo.size(); j++){
					if(ProjectConstants.visitedshopdatainfo.get(j).getShopname().equals(ProjectConstants.CURRENTVISITING_SHOPNAME)) {
						ProjectConstants.visitedshopdatainfo.get(j).setShopchannel(ProjectConstants.CURRENTVISITING_SHOPCHANNEL)
						String message = "(1) 'Chiller Not Allocated' for chiller\n"+
								String.format("%-34s%-100s", "","'Display Space Available' for remaining categories")+"\n"+
								String.format("%-34s%-100s", "","'RTM visit frequency' with 'Once a week'")+"\n"+
								String.format("%-34s%-100s", "","'Pop Application' with 'No' remark")+"\n"+
								String.format("%-34s%-100s", "","'Retailer Remarks' with 'OB not visiting' remark")+"\n"+
								String.format("%-34s%-100s", "","'Hanger Availability' with 'Yes' remark")+"\n\n"+
								String.format("%-30s%-100s", "","(2) 'SKDNA' for chiller with 'Expiry Issue' remark")+"\n"+
								String.format("%-34s%-100s", "","'No Space for Display' for remaining categories")+"\n"+
								String.format("%-34s%-100s", "","'RTM visit frequency' with 'Twice a week'")+"\n"+
								String.format("%-34s%-100s", "","'Pop Application' with 'No' remark")+"\n"+
								String.format("%-34s%-100s", "","'Retailer Remarks' with 'SM not visiting' remark")+"\n"+
								String.format("%-34s%-100s", "","'Hanger Availability' with 'No' remark")
						ProjectConstants.visitedshopdatainfo.get(j).setScenario(message)
						break
					}
				}
				Mobile.verifyElementExist(findTestObject("Object Repository/Validate_ShopListScreen" , [('package') : ProjectConstants.PACKAGENAME]), 0)
			}
			else if(i == 2){
				Mobile.callTestCase(findTestCase("Test Cases/ShopOpen/VisitShopOpen"), null)
				Mobile.callTestCase(findTestCase("Test Cases/ShopOpen/VisitCategoryScenarios/VisitShopCategoriesWith_SKDNA_SKDNA_SKDNA"), null)
				Mobile.callTestCase(findTestCase("ShopOpen/VisitCategoryScenariosWithOverwritePopUp_WithDifferentRemarks/OverwriteShopCategoriesWith_SKDNA_SKDNA_SKDNA"), null)
				Mobile.callTestCase(findTestCase("Test Cases/ShopOpen/SaveShop"), null)
				for(int j=0; j<ProjectConstants.missingshopdatainfo.size(); j++){
					if(ProjectConstants.missingshopdatainfo.get(j).getShopname().equals(ProjectConstants.CURRENTVISITING_SHOPNAME)) {
						ProjectConstants.missingshopdatainfo.get(j).setShopchannel(ProjectConstants.CURRENTVISITING_SHOPCHANNEL)
						String message = "Category Overwrite Scenarios"
						ProjectConstants.missingshopdatainfo.get(j).setScenario(message)
						break
					}
				}
				for(int j=0; j<ProjectConstants.visitedshopdatainfo.size(); j++){
					if(ProjectConstants.visitedshopdatainfo.get(j).getShopname().equals(ProjectConstants.CURRENTVISITING_SHOPNAME)) {
						ProjectConstants.visitedshopdatainfo.get(j).setShopchannel(ProjectConstants.CURRENTVISITING_SHOPCHANNEL)
						String message = "(1) 'SKDNA' for chiller with 'Expiry Issue' remark\n"+
								String.format("%-34s%-100s", "","'SKDNA' for remaining categories with 'Expiry Issue' remark")+"\n"+
								String.format("%-34s%-100s", "","'RTM visit frequency' with 'Once a week'")+"\n"+
								String.format("%-34s%-100s", "","'Pop Application' with 'No' remark")+"\n"+
								String.format("%-34s%-100s", "","'Retailer Remarks' with 'OB not visiting' remark")+"\n"+
								String.format("%-34s%-100s", "","'Hanger Availability' with 'Yes' remark")+"\n\n"+
								String.format("%-30s%-100s", "","(2) 'SKDNA' for chiller with 'Others' remark")+"\n"+
								String.format("%-34s%-100s", "","'SKDNA' for remaining categories with 'Others' remark")+"\n"+
								String.format("%-34s%-100s", "","'RTM visit frequency' with 'Twice a week'")+"\n"+
								String.format("%-34s%-100s", "","'Pop Application' with 'No' remark")+"\n"+
								String.format("%-34s%-100s", "","'Retailer Remarks' with 'SM not visiting' remark")+"\n"+
								String.format("%-34s%-100s", "","'Hanger Availability' with 'No' remark")
						ProjectConstants.visitedshopdatainfo.get(j).setScenario(message)
						break
					}
				}
				Mobile.verifyElementExist(findTestObject("Object Repository/Validate_ShopListScreen" , [('package') : ProjectConstants.PACKAGENAME]), 0)
			}
			else if(i == 3){
				Mobile.callTestCase(findTestCase("Test Cases/ShopOpen/VisitShopOpen"), null)
				Mobile.callTestCase(findTestCase("Test Cases/ShopOpen/VisitCategoryScenarios/VisitShopCategoriesWith_SKDNA_CNAv_NSFD"), null)
				Mobile.callTestCase(findTestCase("ShopOpen/VisitCategoryScenariosWithOverwritePopUp_WithDifferentRemarks/VisitShopCategoriesWith_CNAl_CAv_DSA"), null)
				Mobile.callTestCase(findTestCase("Test Cases/ShopOpen/SaveShop"), null)
				for(int j=0; j<ProjectConstants.missingshopdatainfo.size(); j++){
					if(ProjectConstants.missingshopdatainfo.get(j).getShopname().equals(ProjectConstants.CURRENTVISITING_SHOPNAME)) {
						ProjectConstants.missingshopdatainfo.get(j).setShopchannel(ProjectConstants.CURRENTVISITING_SHOPCHANNEL)
						String message = "Category Overwrite Scenarios"
						ProjectConstants.missingshopdatainfo.get(j).setScenario(message)
						break
					}
				}
				for(int j=0; j<ProjectConstants.visitedshopdatainfo.size(); j++){
					if(ProjectConstants.visitedshopdatainfo.get(j).getShopname().equals(ProjectConstants.CURRENTVISITING_SHOPNAME)) {
						ProjectConstants.visitedshopdatainfo.get(j).setShopchannel(ProjectConstants.CURRENTVISITING_SHOPCHANNEL)
						String message = "(1) 'SKDNA' for chiller with 'Expiry Issue' remark\n"+
								String.format("%-34s%-100s", "","'SKDNA' for remaining categories with 'Expiry Issue' remark")+"\n"+
								String.format("%-34s%-100s", "","'RTM visit frequency' with 'Once a week'")+"\n"+
								String.format("%-34s%-100s", "","'Pop Application' with 'No' remark")+"\n"+
								String.format("%-34s%-100s", "","'Retailer Remarks' with 'OB not visiting' remark")+"\n"+
								String.format("%-34s%-100s", "","'Hanger Availability' with 'Yes' remark")+"\n\n"+
								String.format("%-30s%-100s", "","(2) 'Chiller Not Allocated' for chiller")+"\n"+
								String.format("%-34s%-100s", "","'Display Space Available' for remaining categories")+"\n"+
								String.format("%-34s%-100s", "","'RTM visit frequency' with 'Once a week'")+"\n"+
								String.format("%-34s%-100s", "","'Pop Application' with 'No' remark")+"\n"+
								String.format("%-34s%-100s", "","'Retailer Remarks' with 'OB not visiting' remark")+"\n"+
								String.format("%-34s%-100s", "","'Hanger Availability' with 'Yes' remark")
						ProjectConstants.visitedshopdatainfo.get(j).setScenario(message)
						break
					}
				}
				Mobile.verifyElementExist(findTestObject("Object Repository/Validate_ShopListScreen" , [('package') : ProjectConstants.PACKAGENAME]), 0)
			}
			else if(i == 4){
				Mobile.callTestCase(findTestCase("Test Cases/ShopOpen/VisitShopOpen"), null)
				Mobile.callTestCase(findTestCase("Test Cases/ShopOpen/VisitCategoryScenarios/VisitShopCategoriesWith_CNAl_CAv_DSA"), null)
				Mobile.callTestCase(findTestCase("ShopOpen/VisitCategoryScenariosWithOverwritePopUp_WithDifferentRemarks/OverwriteShopCategoriesWith_CNAl_CAv_DSA"), null)
				Mobile.callTestCase(findTestCase("Test Cases/ShopOpen/SaveShop"), null)
				for(int j=0; j<ProjectConstants.missingshopdatainfo.size(); j++){
					if(ProjectConstants.missingshopdatainfo.get(j).getShopname().equals(ProjectConstants.CURRENTVISITING_SHOPNAME)) {
						ProjectConstants.missingshopdatainfo.get(j).setShopchannel(ProjectConstants.CURRENTVISITING_SHOPCHANNEL)
						String message = "Category Overwrite Scenarios"
						ProjectConstants.missingshopdatainfo.get(j).setScenario(message)
						break
					}
				}
				for(int j=0; j<ProjectConstants.visitedshopdatainfo.size(); j++){
					if(ProjectConstants.visitedshopdatainfo.get(j).getShopname().equals(ProjectConstants.CURRENTVISITING_SHOPNAME)) {
						ProjectConstants.visitedshopdatainfo.get(j).setShopchannel(ProjectConstants.CURRENTVISITING_SHOPCHANNEL)
						String message = "(1) 'Chiller Not Allocated' for chiller\n"+
								String.format("%-34s%-100s","","'Display Space Available' for remaining categories")+"\n"+
								String.format("%-34s%-100s","","'RTM visit frequency' with 'Once a week'")+"\n"+
								String.format("%-34s%-100s","","'Pop Application' with 'No' remark")+"\n"+
								String.format("%-34s%-100s","","'Retailer Remarks' with 'OB not visiting' remark")+"\n"+
								String.format("%-34s%-100s","","'Hanger Availability' with 'Yes' remark")+"\n\n"+
								String.format("%-30s%-100s","","(2) Overwrite 'Chiller Not Allocated' for chiller")+"\n"+
								String.format("%-34s%-100s","","Overwrite 'Display Space Available' for remaining categories")+"\n"+
								String.format("%-34s%-100s","","'RTM visit frequency' with 'Twice a week'")+"\n"+
								String.format("%-34s%-100s","","'Pop Application' with 'No' remark")+"\n"+
								String.format("%-34s%-100s","","'Retailer Remarks' with 'SM not visiting' remark")+"\n"+
								String.format("%-34s%-100s","","'Hanger Availability' with 'No' remark")
						ProjectConstants.visitedshopdatainfo.get(j).setScenario(message)
						break
					}
				}
				Mobile.verifyElementExist(findTestObject("Object Repository/Validate_ShopListScreen" , [('package') : ProjectConstants.PACKAGENAME]), 0)
			}
			else{
				break
			}
		}
		displayDataInReport()
	}
	@Keyword
	def visitShopsWithCategoryLevel_ChillerUtilization_FirstVisit(){
		int index = 0
		int totalshops = ProjectConstants.DRIVER.findElementsByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.ListView[1]/*").size()
		for(int i=1; i<=7; i++){
			MissingShopDataInfo missingshopdatainfo = new MissingShopDataInfo()
			VisitedShopDataInfo visitedshopdatainfo = new VisitedShopDataInfo()
			MobileElement shop = ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout["+i+"]/android.widget.TextView[1]")
			ProjectConstants.CURRENTVISITING_SHOPNAME = shop.getText()
			missingshopdatainfo.setShopname(shop.getText())
			visitedshopdatainfo.setShopname(shop.getText())
			ProjectConstants.missingshopdatainfo.add(missingshopdatainfo)
			ProjectConstants.visitedshopdatainfo.add(visitedshopdatainfo)
			ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout["+i+"]").click()
			MobileBuiltInKeywords.verifyElementText(findTestObject("Object Repository/Validate_ShopOptionsScreen" , [('package') : ProjectConstants.PACKAGENAME]), "Options")
			MobileBuiltInKeywords.tap(findTestObject("Object Repository/StartWorking" , [('package') : ProjectConstants.PACKAGENAME]), 0)
			Mobile.delay(15)
			Mobile.verifyElementExist(findTestObject("Object Repository/CommonScreenElements/Validate_MapScreen" , [('package') : ProjectConstants.PACKAGENAME]), 60)
			MobileBuiltInKeywords.tap(findTestObject("Object Repository/CommonScreenElements/Location_CheckIn" , [('package') : ProjectConstants.PACKAGENAME]), 0)
			Mobile.verifyElementExist(findTestObject("Object Repository/CommonScreenElements/Validate_InfoPopUP" , [('package') : ProjectConstants.PACKAGENAME]), 0)
			MobileBuiltInKeywords.tap(findTestObject("Object Repository/CommonScreenElements/InfoPopUp_NoButton" , [('package') : ProjectConstants.PACKAGENAME]), 0)
			if(i == 1){
				Mobile.callTestCase(findTestCase("Test Cases/ShopOpen/VisitShopOpen"), null)
				Mobile.callTestCase(findTestCase("ShopOpen/VisitCategoryScenariosWithoutOverwritePopUp_WithDifferentRemarks/VisitShopCategoriesWith_SKDNA_CAv_DSA"), null)
				Mobile.callTestCase(findTestCase("Test Cases/ShopOpen/SaveShop"), null)
				for(int j=0; j<ProjectConstants.missingshopdatainfo.size(); j++){
					if(ProjectConstants.missingshopdatainfo.get(j).getShopname().equals(ProjectConstants.CURRENTVISITING_SHOPNAME)) {
						ProjectConstants.missingshopdatainfo.get(j).setShopchannel(ProjectConstants.CURRENTVISITING_SHOPCHANNEL)
						String message = "Category First Visit"
						ProjectConstants.missingshopdatainfo.get(j).setScenario(message)
						break
					}
				}
				for(int j=0; j<ProjectConstants.visitedshopdatainfo.size(); j++){
					if(ProjectConstants.visitedshopdatainfo.get(j).getShopname().equals(ProjectConstants.CURRENTVISITING_SHOPNAME)) {
						ProjectConstants.visitedshopdatainfo.get(j).setShopchannel(ProjectConstants.CURRENTVISITING_SHOPCHANNEL)
						String message = "'Chiller Available' for chiller Utilization\n"+
								String.format("%-34s%-100s","","'Display Space Available' for remaining categories")+"\n"+
								String.format("%-34s%-100s","","'RTM visit frequency' with 'Twice a week'")+"\n"+
								String.format("%-34s%-100s","","'Pop Application' with 'No' remark")+"\n"+
								String.format("%-34s%-100s","","'Retailer Remarks' with 'SM not visiting' remark")+"\n"+
								String.format("%-34s%-100s","","'Hanger Availability' with 'No' remark")
						ProjectConstants.visitedshopdatainfo.get(j).setScenario(message)
						break
					}
				}
				Mobile.verifyElementExist(findTestObject("Object Repository/Validate_ShopListScreen" , [('package') : ProjectConstants.PACKAGENAME]), 0)
			}
			else if(i == 2){
				Mobile.callTestCase(findTestCase("Test Cases/ShopOpen/VisitShopOpen"), null)
				Mobile.callTestCase(findTestCase("ShopOpen/VisitCategoryScenariosWithoutOverwritePopUp_WithDifferentRemarks/OverwriteShopCategoriesWith_SKDNA_SKDNA_SKDNA"), null)
				Mobile.callTestCase(findTestCase("Test Cases/ShopOpen/SaveShop"), null)
				for(int j=0; j<ProjectConstants.missingshopdatainfo.size(); j++){
					if(ProjectConstants.missingshopdatainfo.get(j).getShopname().equals(ProjectConstants.CURRENTVISITING_SHOPNAME)) {
						ProjectConstants.missingshopdatainfo.get(j).setShopchannel(ProjectConstants.CURRENTVISITING_SHOPCHANNEL)
						String message = "Category First Visit"
						ProjectConstants.missingshopdatainfo.get(j).setScenario(message)
						break
					}
				}
				for(int j=0; j<ProjectConstants.visitedshopdatainfo.size(); j++){
					if(ProjectConstants.visitedshopdatainfo.get(j).getShopname().equals(ProjectConstants.CURRENTVISITING_SHOPNAME)) {
						ProjectConstants.visitedshopdatainfo.get(j).setShopchannel(ProjectConstants.CURRENTVISITING_SHOPCHANNEL)
						String message = "'SKDNA' for chiller with 'Others' remark\n"+
								String.format("%-34s%-100s","","'SKDNA' for remaining categories with 'Others' remark")+"\n"+
								String.format("%-34s%-100s","","'RTM visit frequency' with 'Twice a week'")+"\n"+
								String.format("%-34s%-100s","","'Pop Application' with 'No' remark")+"\n"+
								String.format("%-34s%-100s","","'Retailer Remarks' with 'SM not visiting' remark")+"\n"+
								String.format("%-34s%-100s","","'Hanger Availability' with 'No' remark")
						ProjectConstants.visitedshopdatainfo.get(j).setScenario(message)
						break
					}
				}
				Mobile.verifyElementExist(findTestObject("Object Repository/Validate_ShopListScreen" , [('package') : ProjectConstants.PACKAGENAME]), 0)
			}
			else if(i == 3){
				Mobile.callTestCase(findTestCase("Test Cases/ShopOpen/VisitShopOpen"), null)
				Mobile.callTestCase(findTestCase("ShopOpen/VisitCategoryScenariosWithoutOverwritePopUp_WithDifferentRemarks/VisitShopCategoriesWith_CNAl_CAv_DSA"), null)
				Mobile.callTestCase(findTestCase("Test Cases/ShopOpen/SaveShop"), null)
				for(int j=0; j<ProjectConstants.missingshopdatainfo.size(); j++){
					if(ProjectConstants.missingshopdatainfo.get(j).getShopname().equals(ProjectConstants.CURRENTVISITING_SHOPNAME)) {
						ProjectConstants.missingshopdatainfo.get(j).setShopchannel(ProjectConstants.CURRENTVISITING_SHOPCHANNEL)
						String message = "Category First Visit"
						ProjectConstants.missingshopdatainfo.get(j).setScenario(message)
						break
					}
				}
				for(int j=0; j<ProjectConstants.visitedshopdatainfo.size(); j++){
					if(ProjectConstants.visitedshopdatainfo.get(j).getShopname().equals(ProjectConstants.CURRENTVISITING_SHOPNAME)) {
						ProjectConstants.visitedshopdatainfo.get(j).setShopchannel(ProjectConstants.CURRENTVISITING_SHOPCHANNEL)
						String message = "'Chiller Available' for chiller Utilization\n"+
								String.format("%-34s%-100s","","'Display Space Available' for remaining categories")+"\n"+
								String.format("%-34s%-100s","","'RTM visit frequency' with 'Twice a week'")+"\n"+
								String.format("%-34s%-100s","","'Pop Application' with 'No' remark")+"\n"+
								String.format("%-34s%-100s","","'Retailer Remarks' with 'SM not visiting' remark")+"\n"+
								String.format("%-34s%-100s","","'Hanger Availability' with 'No' remark")
						ProjectConstants.visitedshopdatainfo.get(j).setScenario(message)
						break
					}
				}
				Mobile.verifyElementExist(findTestObject("Object Repository/Validate_ShopListScreen" , [('package') : ProjectConstants.PACKAGENAME]), 0)
			}
			else if(i == 4){
				Mobile.callTestCase(findTestCase("Test Cases/ShopOpen/VisitShopOpen"), null)
				Mobile.callTestCase(findTestCase("ShopOpen/VisitCategoryScenariosWithoutOverwritePopUp_WithDifferentRemarks/OverwriteShopCategoriesWith_CNAl_CAv_DSA"), null)
				Mobile.callTestCase(findTestCase("Test Cases/ShopOpen/SaveShop"), null)
				for(int j=0; j<ProjectConstants.missingshopdatainfo.size(); j++){
					if(ProjectConstants.missingshopdatainfo.get(j).getShopname().equals(ProjectConstants.CURRENTVISITING_SHOPNAME)) {
						ProjectConstants.missingshopdatainfo.get(j).setShopchannel(ProjectConstants.CURRENTVISITING_SHOPCHANNEL)
						String message = "Category First Visit"
						ProjectConstants.missingshopdatainfo.get(j).setScenario(message)
						break
					}
				}
				for(int j=0; j<ProjectConstants.visitedshopdatainfo.size(); j++){
					if(ProjectConstants.visitedshopdatainfo.get(j).getShopname().equals(ProjectConstants.CURRENTVISITING_SHOPNAME)) {
						ProjectConstants.visitedshopdatainfo.get(j).setShopchannel(ProjectConstants.CURRENTVISITING_SHOPCHANNEL)
						String message = "Overwrite 'Chiller Available' for chiller utilization\n"+
								String.format("%-34s%-100s","","Overwrite 'Display Space Available' for remaining categories")+"\n"+
								String.format("%-34s%-100s","","'RTM visit frequency' with 'Twice a week'")+"\n"+
								String.format("%-34s%-100s","","'Pop Application' with 'No' remark")+"\n"+
								String.format("%-34s%-100s","","'Retailer Remarks' with 'SM not visiting' remark")+"\n"+
								String.format("%-34s%-100s","","'Hanger Availability' with 'No' remark")
						ProjectConstants.visitedshopdatainfo.get(j).setScenario(message)
						break
					}
				}
				Mobile.verifyElementExist(findTestObject("Object Repository/Validate_ShopListScreen" , [('package') : ProjectConstants.PACKAGENAME]), 0)
			}
			else if(i == 5){
				Mobile.callTestCase(findTestCase("Test Cases/ShopOpen/VisitShopOpen"), null)
				Mobile.callTestCase(findTestCase("ShopOpen/VisitCategoryScenariosWithoutOverwritePopUp_WithDifferentRemarks/VisitShopCategoriesWith_SKDNA_CAv_SKDNA"), null)
				Mobile.callTestCase(findTestCase("Test Cases/ShopOpen/SaveShop"), null)
				for(int j=0; j<ProjectConstants.missingshopdatainfo.size(); j++){
					if(ProjectConstants.missingshopdatainfo.get(j).getShopname().equals(ProjectConstants.CURRENTVISITING_SHOPNAME)) {
						ProjectConstants.missingshopdatainfo.get(j).setShopchannel(ProjectConstants.CURRENTVISITING_SHOPCHANNEL)
						String message = "Category First Visit"
						ProjectConstants.missingshopdatainfo.get(j).setScenario(message)
						break
					}
				}
				for(int j=0; j<ProjectConstants.visitedshopdatainfo.size(); j++){
					if(ProjectConstants.visitedshopdatainfo.get(j).getShopname().equals(ProjectConstants.CURRENTVISITING_SHOPNAME)) {
						ProjectConstants.visitedshopdatainfo.get(j).setShopchannel(ProjectConstants.CURRENTVISITING_SHOPCHANNEL)
						String message = "'Chiller Available' for chiller utilization\n"+
								String.format("%-34s%-100s","","'SKDNA' for remaining categories with 'Expiry issue' remark")+"\n"+
								String.format("%-34s%-100s","","'RTM visit frequency' with 'Twice a week'")+"\n"+
								String.format("%-34s%-100s","","'Pop Application' with 'No' remark")+"\n"+
								String.format("%-34s%-100s","","'Retailer Remarks' with 'SM not visiting' remark")+"\n"+
								String.format("%-34s%-100s","","'Hanger Availability' with 'No' remark")
						ProjectConstants.visitedshopdatainfo.get(j).setScenario(message)
						break
					}
				}
				Mobile.verifyElementExist(findTestObject("Object Repository/Validate_ShopListScreen" , [('package') : ProjectConstants.PACKAGENAME]), 0)
			}
			else if(i == 6){
				Mobile.callTestCase(findTestCase("Test Cases/ShopOpen/VisitShopOpen"), null)
				Mobile.callTestCase(findTestCase("ShopOpen/VisitCategoryScenariosWithoutOverwritePopUp_WithDifferentRemarks/VisitShopCategoriesWith_SKDNA_CAv_DSA"), null)
				Mobile.callTestCase(findTestCase("Test Cases/ShopOpen/SaveShop"), null)
				for(int j=0; j<ProjectConstants.missingshopdatainfo.size(); j++){
					if(ProjectConstants.missingshopdatainfo.get(j).getShopname().equals(ProjectConstants.CURRENTVISITING_SHOPNAME)) {
						ProjectConstants.missingshopdatainfo.get(j).setShopchannel(ProjectConstants.CURRENTVISITING_SHOPCHANNEL)
						String message = "Category First Visit"
						ProjectConstants.missingshopdatainfo.get(j).setScenario(message)
						break
					}
				}
				for(int j=0; j<ProjectConstants.visitedshopdatainfo.size(); j++){
					if(ProjectConstants.visitedshopdatainfo.get(j).getShopname().equals(ProjectConstants.CURRENTVISITING_SHOPNAME)) {
						ProjectConstants.visitedshopdatainfo.get(j).setShopchannel(ProjectConstants.CURRENTVISITING_SHOPCHANNEL)
						String message = "'Chiller Available' for chiller utilization\n"+
								String.format("%-34s%-100s","","'Display Space Available' for remaining categories")+"\n"+
								String.format("%-34s%-100s","","'RTM visit frequency' with 'Twice a week'")+"\n"+
								String.format("%-34s%-100s","","'Pop Application' with 'No' remark")+"\n"+
								String.format("%-34s%-100s","","'Retailer Remarks' with 'SM not visiting' remark")+"\n"+
								String.format("%-34s%-100s","","'Hanger Availability' with 'No' remark")
						ProjectConstants.visitedshopdatainfo.get(j).setScenario(message)
						break
					}
				}
				Mobile.verifyElementExist(findTestObject("Object Repository/Validate_ShopListScreen" , [('package') : ProjectConstants.PACKAGENAME]), 0)
			}
			else if(i == 7){
				Mobile.callTestCase(findTestCase("Test Cases/ShopOpen/VisitShopOpen"), null)
				Mobile.callTestCase(findTestCase("ShopOpen/VisitCategoryScenariosWithoutOverwritePopUp_WithDifferentRemarks/VisitShopCategoriesWith_SKDNA_CNAv_SKDNA"), null)
				Mobile.callTestCase(findTestCase("Test Cases/ShopOpen/SaveShop"), null)
				for(int j=0; j<ProjectConstants.missingshopdatainfo.size(); j++){
					if(ProjectConstants.missingshopdatainfo.get(j).getShopname().equals(ProjectConstants.CURRENTVISITING_SHOPNAME)) {
						ProjectConstants.missingshopdatainfo.get(j).setShopchannel(ProjectConstants.CURRENTVISITING_SHOPCHANNEL)
						String message = "Category First Visit"
						ProjectConstants.missingshopdatainfo.get(j).setScenario(message)
						break
					}
				}
				for(int j=0; j<ProjectConstants.visitedshopdatainfo.size(); j++){
					if(ProjectConstants.visitedshopdatainfo.get(j).getShopname().equals(ProjectConstants.CURRENTVISITING_SHOPNAME)) {
						ProjectConstants.visitedshopdatainfo.get(j).setShopchannel(ProjectConstants.CURRENTVISITING_SHOPCHANNEL)
						String message = "'Chiller Not Available' for chiller utilization\n"+
								String.format("%-34s%-100s","","'SKDNA' for remaining categories with 'Expiry Issue' remark")+"\n"+
								String.format("%-34s%-100s","","'RTM visit frequency' with 'Twice a week'")+"\n"+
								String.format("%-34s%-100s","","'Pop Application' with 'No' remark")+"\n"+
								String.format("%-34s%-100s","","'Retailer Remarks' with 'SM not visiting' remark")+"\n"+
								String.format("%-34s%-100s","","'Hanger Availability' with 'No' remark")
						ProjectConstants.visitedshopdatainfo.get(j).setScenario(message)
						break
					}
				}
				Mobile.verifyElementExist(findTestObject("Object Repository/Validate_ShopListScreen" , [('package') : ProjectConstants.PACKAGENAME]), 0)
			}
			else{
				break
			}
		}
		displayDataInReport()
	}
	@Keyword
	def visitShopsWithCategoryLevel_ChillerUtilization_Overwriting(){
		int index = 0
		int totalshops = ProjectConstants.DRIVER.findElementsByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.ListView[1]/*").size()
		for(int i=1; i<=7; i++){
			MissingShopDataInfo missingshopdatainfo = new MissingShopDataInfo()
			VisitedShopDataInfo visitedshopdatainfo = new VisitedShopDataInfo()
			MobileElement shop = ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout["+i+"]/android.widget.TextView[1]")
			ProjectConstants.CURRENTVISITING_SHOPNAME = shop.getText()
			missingshopdatainfo.setShopname(shop.getText())
			visitedshopdatainfo.setShopname(shop.getText())
			ProjectConstants.missingshopdatainfo.add(missingshopdatainfo)
			ProjectConstants.visitedshopdatainfo.add(visitedshopdatainfo)
			ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout["+i+"]").click()
			MobileBuiltInKeywords.verifyElementText(findTestObject("Object Repository/Validate_ShopOptionsScreen" , [('package') : ProjectConstants.PACKAGENAME]), "Options")
			MobileBuiltInKeywords.tap(findTestObject("Object Repository/StartWorking"), 0)
			Mobile.delay(15)
			Mobile.verifyElementExist(findTestObject("Object Repository/CommonScreenElements/Validate_MapScreen" , [('package') : ProjectConstants.PACKAGENAME]), 60)
			MobileBuiltInKeywords.tap(findTestObject("Object Repository/CommonScreenElements/Location_CheckIn" , [('package') : ProjectConstants.PACKAGENAME]), 0)
			Mobile.verifyElementExist(findTestObject("Object Repository/CommonScreenElements/Validate_InfoPopUP" , [('package') : ProjectConstants.PACKAGENAME]), 0)
			MobileBuiltInKeywords.tap(findTestObject("Object Repository/CommonScreenElements/InfoPopUp_NoButton" , [('package') : ProjectConstants.PACKAGENAME]), 0)
			if(i == 1){
				Mobile.callTestCase(findTestCase("Test Cases/ShopOpen/VisitShopOpen"), null)
				Mobile.callTestCase(findTestCase("Test Cases/ShopOpen/VisitCategoryScenarios/VisitShopCategoriesWith_CNAl_CTNAv_NSFD"), null)
				Mobile.callTestCase(findTestCase("ShopOpen/VisitCategoryScenariosWithOverwritePopUp_WithDifferentRemarks/VisitShopCategoriesWith_SKDNA_CAv_DSA"), null)
				Mobile.callTestCase(findTestCase("Test Cases/ShopOpen/SaveShop"), null)
				for(int j=0; j<ProjectConstants.missingshopdatainfo.size(); j++){
					if(ProjectConstants.missingshopdatainfo.get(j).getShopname().equals(ProjectConstants.CURRENTVISITING_SHOPNAME)) {
						ProjectConstants.missingshopdatainfo.get(j).setShopchannel(ProjectConstants.CURRENTVISITING_SHOPCHANNEL)
						String message = "Category Overwrite Scenarios"
						ProjectConstants.missingshopdatainfo.get(j).setScenario(message)
						break
					}
				}
				for(int j=0; j<ProjectConstants.visitedshopdatainfo.size(); j++){
					if(ProjectConstants.visitedshopdatainfo.get(j).getShopname().equals(ProjectConstants.CURRENTVISITING_SHOPNAME)) {
						ProjectConstants.visitedshopdatainfo.get(j).setShopchannel(ProjectConstants.CURRENTVISITING_SHOPCHANNEL)
						String message = "(1) 'Chiller Type not Available' for chiller Utilization\n"+
								String.format("%-34s%-100s","","'No Space for Display' for remaining categories")+"\n"+
								String.format("%-34s%-100s","","'RTM visit frequency' with 'Once a week'")+"\n"+
								String.format("%-34s%-100s","","'Pop Application' with 'No' remark")+"\n"+
								String.format("%-34s%-100s","","'Retailer Remarks' with 'OB not visiting' remark")+"\n"+
								String.format("%-34s%-100s","","'Hanger Availability' with 'Yes' remark")+"\n\n"+
								String.format("%-30s%-100s","","(2) 'Chiller Available' for chiller Utilization")+"\n"+
								String.format("%-34s%-100s","","'Display Space Available' for remaining categories")+"\n"+
								String.format("%-34s%-100s","","'RTM visit frequency' with 'Twice a week'")+"\n"+
								String.format("%-34s%-100s","","'Pop Application' with 'No' remark")+"\n"+
								String.format("%-34s%-100s","","'Retailer Remarks' with 'SM not visiting' remark")+"\n"+
								String.format("%-34s%-100s","","'Hanger Availability' with 'No' remark")
						ProjectConstants.visitedshopdatainfo.get(j).setScenario(message)
						break
					}
				}
				Mobile.verifyElementExist(findTestObject("Object Repository/Validate_ShopListScreen" , [('package') : ProjectConstants.PACKAGENAME]), 0)
			}
			else if(i == 2){
				Mobile.callTestCase(findTestCase("Test Cases/ShopOpen/VisitShopOpen"), null)
				Mobile.callTestCase(findTestCase("Test Cases/ShopOpen/VisitCategoryScenarios/VisitShopCategoriesWith_SKDNA_SKDNA_SKDNA"), null)
				Mobile.callTestCase(findTestCase("ShopOpen/VisitCategoryScenariosWithOverwritePopUp_WithDifferentRemarks/OverwriteShopCategoriesWith_SKDNA_SKDNA_SKDNA"), null)
				Mobile.callTestCase(findTestCase("Test Cases/ShopOpen/SaveShop"), null)
				for(int j=0; j<ProjectConstants.missingshopdatainfo.size(); j++){
					if(ProjectConstants.missingshopdatainfo.get(j).getShopname().equals(ProjectConstants.CURRENTVISITING_SHOPNAME)) {
						ProjectConstants.missingshopdatainfo.get(j).setShopchannel(ProjectConstants.CURRENTVISITING_SHOPCHANNEL)
						String message = "Category overwrite scenarios"
						ProjectConstants.missingshopdatainfo.get(j).setScenario(message)
						break
					}
				}
				for(int j=0; j<ProjectConstants.visitedshopdatainfo.size(); j++){
					if(ProjectConstants.visitedshopdatainfo.get(j).getShopname().equals(ProjectConstants.CURRENTVISITING_SHOPNAME)) {
						ProjectConstants.visitedshopdatainfo.get(j).setShopchannel(ProjectConstants.CURRENTVISITING_SHOPCHANNEL)
						String message = "(1) 'SKDNA' for chiller Utilization with 'Expiry Issue' remark\n"+
								String.format("%-34s%-100s","","'SKDNA' for remaining categories with 'Expiry Issue' remark")+"\n"+
								String.format("%-34s%-100s","","'RTM visit frequency' with 'Once a week'")+"\n"+
								String.format("%-34s%-100s","","'Pop Application' with 'No' remark")+"\n"+
								String.format("%-34s%-100s","","'Retailer Remarks' with 'OB not visiting' remark")+"\n"+
								String.format("%-34s%-100s","","'Hanger Availability' with 'Yes' remark")+"\n\n"+
								String.format("%-30s%-100s","","(2) 'SKDNA' for chiller with 'Others' remark")+"\n"+
								String.format("%-34s%-100s","","'SKDNA' for remaining categories with 'Others' remark")+"\n"+
								String.format("%-34s%-100s","","'RTM visit frequency' with 'Twice a week'")+"\n"+
								String.format("%-34s%-100s","","'Pop Application' with 'No' remark")+"\n"+
								String.format("%-34s%-100s","","'Retailer Remarks' with 'SM not visiting' remark")+"\n"+
								String.format("%-34s%-100s","","'Hanger Availability' with 'No' remark")
						ProjectConstants.visitedshopdatainfo.get(j).setScenario(message)
						break
					}
				}
				Mobile.verifyElementExist(findTestObject("Object Repository/Validate_ShopListScreen" , [('package') : ProjectConstants.PACKAGENAME]), 0)
			}
			else if(i == 3){
				Mobile.callTestCase(findTestCase("Test Cases/ShopOpen/VisitShopOpen"), null)
				Mobile.callTestCase(findTestCase("Test Cases/ShopOpen/VisitCategoryScenarios/VisitShopCategoriesWith_SKDNA_SKDNA_SKDNA"), null)
				Mobile.callTestCase(findTestCase("ShopOpen/VisitCategoryScenariosWithOverwritePopUp_WithDifferentRemarks/VisitShopCategoriesWith_CNAl_CAv_DSA"), null)
				Mobile.callTestCase(findTestCase("Test Cases/ShopOpen/SaveShop"), null)
				for(int j=0; j<ProjectConstants.missingshopdatainfo.size(); j++){
					if(ProjectConstants.missingshopdatainfo.get(j).getShopname().equals(ProjectConstants.CURRENTVISITING_SHOPNAME)) {
						ProjectConstants.missingshopdatainfo.get(j).setShopchannel(ProjectConstants.CURRENTVISITING_SHOPCHANNEL)
						String message = "Category overwrite scenarios"
						ProjectConstants.missingshopdatainfo.get(j).setScenario(message)
						break
					}
				}
				for(int j=0; j<ProjectConstants.visitedshopdatainfo.size(); j++){
					if(ProjectConstants.visitedshopdatainfo.get(j).getShopname().equals(ProjectConstants.CURRENTVISITING_SHOPNAME)) {
						ProjectConstants.visitedshopdatainfo.get(j).setShopchannel(ProjectConstants.CURRENTVISITING_SHOPCHANNEL)
						String message = "(1) 'SKDNA' for chiller Utilization with 'Expiry Issue' remark\n"+
								String.format("%-34s%-100s","","'SKDNA' for remaining categories with 'Expiry Issue' remark")+"\n"+
								String.format("%-34s%-100s","","'RTM visit frequency' with 'Once a week'")+"\n"+
								String.format("%-34s%-100s","","'Pop Application' with 'No' remark")+"\n"+
								String.format("%-34s%-100s","","'Retailer Remarks' with 'OB not visiting' remark")+"\n"+
								String.format("%-34s%-100s","","'Hanger Availability' with 'Yes' remark")+"\n\n"+
								String.format("%-30s%-100s","","(2) 'Chiller Available' for chiller Utilization")+"\n"+
								String.format("%-34s%-100s","","'Display Space Available' for remaining categories")+"\n"+
								String.format("%-34s%-100s","","'RTM visit frequency' with 'Twice a week'")+"\n"+
								String.format("%-34s%-100s","","'Pop Application' with 'No' remark")+"\n"+
								String.format("%-34s%-100s","","'Retailer Remarks' with 'SM not visiting' remark")+"\n"+
								String.format("%-34s%-100s","","'Hanger Availability' with 'No' remark")
						ProjectConstants.visitedshopdatainfo.get(j).setScenario(message)
						break
					}
				}
				Mobile.verifyElementExist(findTestObject("Object Repository/Validate_ShopListScreen" , [('package') : ProjectConstants.PACKAGENAME]), 0)
			}
			else if(i == 4){
				Mobile.callTestCase(findTestCase("Test Cases/ShopOpen/VisitShopOpen"), null)
				Mobile.callTestCase(findTestCase("Test Cases/ShopOpen/VisitCategoryScenarios/VisitShopCategoriesWith_CNAl_CAv_DSA"), null)
				Mobile.callTestCase(findTestCase("ShopOpen/VisitCategoryScenariosWithOverwritePopUp_WithDifferentRemarks/OverwriteShopCategoriesWith_CNAl_CAv_DSA"), null)
				Mobile.callTestCase(findTestCase("Test Cases/ShopOpen/SaveShop"), null)
				for(int j=0; j<ProjectConstants.missingshopdatainfo.size(); j++){
					if(ProjectConstants.missingshopdatainfo.get(j).getShopname().equals(ProjectConstants.CURRENTVISITING_SHOPNAME)) {
						ProjectConstants.missingshopdatainfo.get(j).setShopchannel(ProjectConstants.CURRENTVISITING_SHOPCHANNEL)
						String message = "Category Overwrite Scenarios"
						ProjectConstants.missingshopdatainfo.get(j).setScenario(message)
						break
					}
				}
				for(int j=0; j<ProjectConstants.visitedshopdatainfo.size(); j++){
					if(ProjectConstants.visitedshopdatainfo.get(j).getShopname().equals(ProjectConstants.CURRENTVISITING_SHOPNAME)) {
						ProjectConstants.visitedshopdatainfo.get(j).setShopchannel(ProjectConstants.CURRENTVISITING_SHOPCHANNEL)
						String message = "(1) 'Chiller Available' for chiller utilization\n"+
								String.format("%-34s%-100s","","'Display Space Available' for remaining categories")+"\n"+
								String.format("%-34s%-100s","","'RTM visit frequency' with 'Once a week'")+"\n"+
								String.format("%-34s%-100s","","'Pop Application' with 'No' remark")+"\n"+
								String.format("%-34s%-100s","","'Retailer Remarks' with 'OB not visiting' remark")+"\n"+
								String.format("%-34s%-100s","","'Hanger Availability' with 'Yes' remark")+"\n\n"+
								String.format("%-30s%-100s","","(2) Overwrite 'Chiller Available' for chiller utilization")+"\n"+
								String.format("%-34s%-100s","","Overwrite 'Display Space Available' for remaining categories")+"\n"+
								String.format("%-34s%-100s","","'RTM visit frequency' with 'Twice a week'")+"\n"+
								String.format("%-34s%-100s","","'Pop Application' with 'No' remark")+"\n"+
								String.format("%-34s%-100s","","'Retailer Remarks' with 'SM not visiting' remark")+"\n"+
								String.format("%-34s%-100s","","'Hanger Availability' with 'No' remark")
						ProjectConstants.visitedshopdatainfo.get(j).setScenario(message)
						break
					}
				}
				Mobile.verifyElementExist(findTestObject("Object Repository/Validate_ShopListScreen" , [('package') : ProjectConstants.PACKAGENAME]), 0)
			}
			else if(i == 5){
				Mobile.callTestCase(findTestCase("Test Cases/ShopOpen/VisitShopOpen"), null)
				Mobile.callTestCase(findTestCase("Test Cases/ShopOpen/VisitCategoryScenarios/VisitShopCategoriesWith_CNAl_CNAv_DSA"), null)
				Mobile.callTestCase(findTestCase("ShopOpen/VisitCategoryScenariosWithOverwritePopUp_WithDifferentRemarks/VisitShopCategoriesWith_SKDNA_CAv_SKDNA"), null)
				Mobile.callTestCase(findTestCase("Test Cases/ShopOpen/SaveShop"), null)
				for(int j=0; j<ProjectConstants.missingshopdatainfo.size(); j++){
					if(ProjectConstants.missingshopdatainfo.get(j).getShopname().equals(ProjectConstants.CURRENTVISITING_SHOPNAME)) {
						ProjectConstants.missingshopdatainfo.get(j).setShopchannel(ProjectConstants.CURRENTVISITING_SHOPCHANNEL)
						String message = "Category Overwrite Scenarios"
						ProjectConstants.missingshopdatainfo.get(j).setScenario(message)
						break
					}
				}
				for(int j=0; j<ProjectConstants.visitedshopdatainfo.size(); j++){
					if(ProjectConstants.visitedshopdatainfo.get(j).getShopname().equals(ProjectConstants.CURRENTVISITING_SHOPNAME)) {
						ProjectConstants.visitedshopdatainfo.get(j).setShopchannel(ProjectConstants.CURRENTVISITING_SHOPCHANNEL)
						String message = "(1) 'Chiller Not Available' for chiller utilization\n"+
								String.format("%-34s%-100s","","'Display Space Available' for remaining categories")+"\n"+
								String.format("%-34s%-100s","","'RTM visit frequency' with 'Once a week'")+"\n"+
								String.format("%-34s%-100s","","'Pop Application' with 'No' remark")+"\n"+
								String.format("%-34s%-100s","","'Retailer Remarks' with 'OB not visiting' remark")+"\n"+
								String.format("%-34s%-100s","","'Hanger Availability' with 'Yes' remark")+"\n\n"+
								String.format("%-30s%-100s","","(2) 'Chiller Available' for chiller utilization")+"\n"+
								String.format("%-34s%-100s","","'SKDNA' for remaining categories with 'Expiry issue' remark")+"\n"+
								String.format("%-34s%-100s","","'RTM visit frequency' with 'Twice a week'")+"\n"+
								String.format("%-34s%-100s","","'Pop Application' with 'No' remark")+"\n"+
								String.format("%-34s%-100s","","'Retailer Remarks' with 'SM not visiting' remark")+"\n"+
								String.format("%-34s%-100s","","'Hanger Availability' with 'No' remark")
						ProjectConstants.visitedshopdatainfo.get(j).setScenario(message)
						break
					}
				}
				Mobile.verifyElementExist(findTestObject("Object Repository/Validate_ShopListScreen" , [('package') : ProjectConstants.PACKAGENAME]), 0)
			}
			else if(i == 6){
				Mobile.callTestCase(findTestCase("Test Cases/ShopOpen/VisitShopOpen"), null)
				Mobile.callTestCase(findTestCase("Test Cases/ShopOpen/VisitCategoryScenarios/VisitShopCategoriesWith_CNAl_CNAv_SKDNA"), null)
				Mobile.callTestCase(findTestCase("ShopOpen/VisitCategoryScenariosWithOverwritePopUp_WithDifferentRemarks/VisitShopCategoriesWith_SKDNA_CAv_DSA"), null)
				Mobile.callTestCase(findTestCase("Test Cases/ShopOpen/SaveShop"), null)
				for(int j=0; j<ProjectConstants.missingshopdatainfo.size(); j++){
					if(ProjectConstants.missingshopdatainfo.get(j).getShopname().equals(ProjectConstants.CURRENTVISITING_SHOPNAME)) {
						ProjectConstants.missingshopdatainfo.get(j).setShopchannel(ProjectConstants.CURRENTVISITING_SHOPCHANNEL)
						String message = "Category Overwrite Scenarios"
						ProjectConstants.missingshopdatainfo.get(j).setScenario(message)
						break
					}
				}
				for(int j=0; j<ProjectConstants.visitedshopdatainfo.size(); j++){
					if(ProjectConstants.visitedshopdatainfo.get(j).getShopname().equals(ProjectConstants.CURRENTVISITING_SHOPNAME)) {
						ProjectConstants.visitedshopdatainfo.get(j).setShopchannel(ProjectConstants.CURRENTVISITING_SHOPCHANNEL)
						String message = "(1) 'Chiller Not Available' for chiller utilization\n"+
								String.format("%-34s%-100s","","'SKDNA' for remaining categories with 'Expiry Issue' remark")+"\n"+
								String.format("%-34s%-100s","","'RTM visit frequency' with 'Once a week'")+"\n"+
								String.format("%-34s%-100s","","'Pop Application' with 'No' remark")+"\n"+
								String.format("%-34s%-100s","","'Retailer Remarks' with 'OB not visiting' remark")+"\n"+
								String.format("%-34s%-100s","","'Hanger Availability' with 'Yes' remark")+"\n\n"+
								String.format("%-30s%-100s","","(2) 'Chiller Available' for chiller utilization")+"\n"+
								String.format("%-34s%-100s","","'Display Space Available' for remaining categories")+"\n"+
								String.format("%-34s%-100s","","'RTM visit frequency' with 'Twice a week'")+"\n"+
								String.format("%-34s%-100s","","'Pop Application' with 'No' remark")+"\n"+
								String.format("%-34s%-100s","","'Retailer Remarks' with 'SM not visiting' remark")+"\n"+
								String.format("%-34s%-100s","","'Hanger Availability' with 'No' remark")
						ProjectConstants.visitedshopdatainfo.get(j).setScenario(message)
						break
					}
				}
				Mobile.verifyElementExist(findTestObject("Object Repository/Validate_ShopListScreen" , [('package') : ProjectConstants.PACKAGENAME]), 0)
			}
			else if(i == 7){
				Mobile.callTestCase(findTestCase("Test Cases/ShopOpen/VisitShopOpen"), null)
				Mobile.callTestCase(findTestCase("Test Cases/ShopOpen/VisitCategoryScenarios/VisitShopCategoriesWith_CNAl_CAv_NSFD"), null)
				Mobile.callTestCase(findTestCase("ShopOpen/VisitCategoryScenariosWithOverwritePopUp_WithDifferentRemarks/VisitShopCategoriesWith_SKDNA_CNAv_SKDNA"), null)
				Mobile.callTestCase(findTestCase("Test Cases/ShopOpen/SaveShop"), null)
				for(int j=0; j<ProjectConstants.missingshopdatainfo.size(); j++){
					if(ProjectConstants.missingshopdatainfo.get(j).getShopname().equals(ProjectConstants.CURRENTVISITING_SHOPNAME)) {
						ProjectConstants.missingshopdatainfo.get(j).setShopchannel(ProjectConstants.CURRENTVISITING_SHOPCHANNEL)
						String message = "Category Overwrite Scenarios"
						ProjectConstants.missingshopdatainfo.get(j).setScenario(message)
						break
					}
				}
				for(int j=0; j<ProjectConstants.visitedshopdatainfo.size(); j++){
					if(ProjectConstants.visitedshopdatainfo.get(j).getShopname().equals(ProjectConstants.CURRENTVISITING_SHOPNAME)) {
						ProjectConstants.visitedshopdatainfo.get(j).setShopchannel(ProjectConstants.CURRENTVISITING_SHOPCHANNEL)
						String message = "(1) 'Chiller Available' for chiller utilization\n"+
								String.format("%-34s%-100s","","'No Space For Display' for remaining categories")+"\n"+
								String.format("%-34s%-100s","","'RTM visit frequency' with 'Once a week'")+"\n"+
								String.format("%-34s%-100s","","'Pop Application' with 'No' remark")+"\n"+
								String.format("%-34s%-100s","","'Retailer Remarks' with 'OB not visiting' remark")+"\n"+
								String.format("%-34s%-100s","","'Hanger Availability' with 'Yes' remark")+"\n\n"+
								String.format("%-30s%-100s","","(2) 'Chiller Not Available' for chiller utilization")+"\n"+
								String.format("%-34s%-100s","","'SKDNA' for remaining categories with 'Expiry Issue' remark")+"\n"+
								String.format("%-34s%-100s","","'RTM visit frequency' with 'Twice a week'")+"\n"+
								String.format("%-34s%-100s","","'Pop Application' with 'No' remark")+"\n"+
								String.format("%-34s%-100s","","'Retailer Remarks' with 'SM not visiting' remark")+"\n"+
								String.format("%-34s%-100s","","'Hanger Availability' with 'No' remark")
						ProjectConstants.visitedshopdatainfo.get(j).setScenario(message)
						break
					}
				}
				Mobile.verifyElementExist(findTestObject("Object Repository/Validate_ShopListScreen" , [('package') : ProjectConstants.PACKAGENAME]), 0)
			}
			else{
				break
			}
		}
		displayDataInReport()
	}
}
