package com.ct.qa.keywords

import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import org.apache.poi.ss.usermodel.DataFormatter
import org.apache.poi.ss.usermodel.Row
import org.apache.poi.xssf.usermodel.XSSFSheet
import org.apache.poi.xssf.usermodel.XSSFWorkbook

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

import MobileBuiltInKeywords as Mobile
import WSBuiltInKeywords as WS
import WebUiBuiltInKeywords as WebUI

import com.ct.qa.constants.ProjectConstants
import com.ct.qa.struct.LoadProductsData
import com.ct.qa.struct.MissingCategoryData
import com.ct.qa.struct.ProductCategoryWithProducts
import com.ct.qa.struct.ShopProductsData
import com.ct.qa.struct.UnmatchedItems
import com.ct.qa.struct.VisitedCategoryData
import com.ct.qa.struct.VisitedShopDataInfo

import io.appium.java_client.MobileElement

public class ChannelProductsDataKeywords {
	//visit chiller not allocated product categories in chiller
	@Keyword
	def visitChillerNotAllocatedProductCategories(int flag){
		UnmatchedItems unmatcheditems = CompareDataKeywords.compareChannelWiseProductsCategories()
		if(unmatcheditems.getStatus() == 2){
			MissingCategoryData missingcategorydata = new MissingCategoryData()
			missingcategorydata.setMaincategory(ProjectConstants.CURRENTVISITING_MAINCATEGORY)
			missingcategorydata.setProductcategories(unmatcheditems.getItems())
			missingcategorydata.setProductcategories_errormessage(ProjectConstants.MESSAGEFOR_ITEMSARE_NOTMATCH)
			for(int j=0; j<ProjectConstants.missingshopdatainfo.size(); j++){
				if(ProjectConstants.missingshopdatainfo.get(j).getShopname().equalsIgnoreCase(ProjectConstants.CURRENTVISITING_SHOPNAME)) {
					ProjectConstants.missingshopdatainfo.get(j).setMissingCategoriesData(missingcategorydata, "")
					break
				}
				else{
				}
			}
		}
		else if(unmatcheditems.getStatus() == 1){
			MissingCategoryData missingcategorydata = new MissingCategoryData()
			missingcategorydata.setMaincategory(ProjectConstants.CURRENTVISITING_MAINCATEGORY)
			missingcategorydata.setProductcategories(unmatcheditems.getItems())
			missingcategorydata.setProductcategories_errormessage(ProjectConstants.MESSAGEFOR_ITEMSARE_MORE)
			for(int j=0; j<ProjectConstants.missingshopdatainfo.size(); j++){
				if(ProjectConstants.missingshopdatainfo.get(j).getShopname().equalsIgnoreCase(ProjectConstants.CURRENTVISITING_SHOPNAME)) {
					ProjectConstants.missingshopdatainfo.get(j).setMissingCategoriesData(missingcategorydata, "")
					break
				}
				else{
				}
			}
		}
		else if(unmatcheditems.getStatus() == -1){
			MissingCategoryData missingcategorydata = new MissingCategoryData()
			missingcategorydata.setMaincategory(ProjectConstants.CURRENTVISITING_MAINCATEGORY)
			missingcategorydata.setProductcategories(unmatcheditems.getItems())
			missingcategorydata.setProductcategories_errormessage(ProjectConstants.MESSAGEFOR_ITEMSARE_MISSING)
			for(int j=0; j<ProjectConstants.missingshopdatainfo.size(); j++){
				if(ProjectConstants.missingshopdatainfo.get(j).getShopname().equalsIgnoreCase(ProjectConstants.CURRENTVISITING_SHOPNAME)) {
					ProjectConstants.missingshopdatainfo.get(j).setMissingCategoriesData(missingcategorydata, "")
					break
				}
				else{
				}
			}
		}
		else{
		}
		int totalproductcategories = ProjectConstants.DRIVER.findElementsByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.ListView[1]/*").size()
		for(int i=1; i<=totalproductcategories; i++){
			MobileElement productcategory = ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout["+i+"]/android.widget.TextView[1]")
			ProjectConstants.CURRENTVISITING_PRODUCTCATEGORY = productcategory.getText()
			ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout["+i+"]").click()
			if(flag == 1){
				Mobile.callTestCase(findTestCase("Test Cases/ShopOpen/Chiller/VisitProductCategoryAssets"), null)
				MobileBuiltInKeywords.verifyElementText(findTestObject("ShopOpen/Chiller/Validate_ChillerNotAllocatedProductCategoryScreen" , [('package') : ProjectConstants.PACKAGENAME]),"Category:Chiller")
			}
			else if(flag == 2){
				Mobile.callTestCase(findTestCase("Test Cases/ShopOpen/Chiller/OverwriteProductCategoryAssets"), null)
				MobileBuiltInKeywords.verifyElementText(findTestObject("ShopOpen/Chiller/Validate_ChillerNotAllocatedProductCategoryScreen" , [('package') : ProjectConstants.PACKAGENAME]),"Category:Chiller")
			}
			else{
			}
		}
	}
	@Keyword
	def visitNestradeProductsCategoriesWithDSA(int flag){
		UnmatchedItems unmatcheditems = CompareDataKeywords.compareChannelWiseProductsCategories()
		if(unmatcheditems.getStatus() == 2){
			MissingCategoryData missingcategorydata = new MissingCategoryData()
			missingcategorydata.setMaincategory(ProjectConstants.CURRENTVISITING_MAINCATEGORY)
			missingcategorydata.setProductcategories(unmatcheditems.getItems())
			missingcategorydata.setProductcategories_errormessage(ProjectConstants.MESSAGEFOR_ITEMSARE_NOTMATCH)
			for(int j=0; j<ProjectConstants.missingshopdatainfo.size(); j++){
				if(ProjectConstants.missingshopdatainfo.get(j).getShopname().equalsIgnoreCase(ProjectConstants.CURRENTVISITING_SHOPNAME)) {
					ProjectConstants.missingshopdatainfo.get(j).setMissingCategoriesData(missingcategorydata, "")
					break
				}
				else{
				}
			}
		}
		else if(unmatcheditems.getStatus() == 1){
			MissingCategoryData missingcategorydata = new MissingCategoryData()
			missingcategorydata.setMaincategory(ProjectConstants.CURRENTVISITING_MAINCATEGORY)
			missingcategorydata.setProductcategories(unmatcheditems.getItems())
			missingcategorydata.setProductcategories_errormessage(ProjectConstants.MESSAGEFOR_ITEMSARE_MORE)
			for(int j=0; j<ProjectConstants.missingshopdatainfo.size(); j++){
				if(ProjectConstants.missingshopdatainfo.get(j).getShopname().equalsIgnoreCase(ProjectConstants.CURRENTVISITING_SHOPNAME)) {
					ProjectConstants.missingshopdatainfo.get(j).setMissingCategoriesData(missingcategorydata, "")
					break
				}
				else{
				}
			}
		}
		else if(unmatcheditems.getStatus() == -1){
			MissingCategoryData missingcategorydata = new MissingCategoryData()
			missingcategorydata.setMaincategory(ProjectConstants.CURRENTVISITING_MAINCATEGORY)
			missingcategorydata.setProductcategories(unmatcheditems.getItems())
			missingcategorydata.setProductcategories_errormessage(ProjectConstants.MESSAGEFOR_ITEMSARE_MISSING)
			for(int j=0; j<ProjectConstants.missingshopdatainfo.size(); j++){
				if(ProjectConstants.missingshopdatainfo.get(j).getShopname().equalsIgnoreCase(ProjectConstants.CURRENTVISITING_SHOPNAME)) {
					ProjectConstants.missingshopdatainfo.get(j).setMissingCategoriesData(missingcategorydata, "")
					break
				}
				else{
				}
			}
		}
		else{
		}
		int totalproductcategories = ProjectConstants.DRIVER.findElementsByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.ListView[1]/*").size()
		for(int i=1; i<=totalproductcategories; i++){
			MobileElement productcategory = ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout["+i+"]/android.widget.TextView[1]")
			ProjectConstants.CURRENTVISITING_PRODUCTCATEGORY = productcategory.getText()
			ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout["+i+"]").click()
			if(flag == 1){
				Mobile.callTestCase(findTestCase("Test Cases/ShopOpen/Nestrade/VisitProductCategoryAssetsWithDSA"), null)
				MobileBuiltInKeywords.verifyElementText(findTestObject('ShopOpen/Nestrade/Validate_NestradePorductCategoryScreen', [('package') : ProjectConstants.PACKAGENAME]),'Category:Nestrade')
			}
			else if(flag == 2){
				Mobile.callTestCase(findTestCase("Test Cases/ShopOpen/Nestrade/OverwriteProductCategoryAssetsWithDSA"), null)
				MobileBuiltInKeywords.verifyElementText(findTestObject('ShopOpen/Nestrade/Validate_NestradePorductCategoryScreen', [('package') : ProjectConstants.PACKAGENAME]),'Category:Nestrade')
			}
			else{
			}
		}
	}
	@Keyword
	def visitNestradeProductsCategoriesWithNSFD(int flag){
		UnmatchedItems unmatcheditems = CompareDataKeywords.compareChannelWiseProductsCategories()
		if(unmatcheditems.getStatus() == 2){
			MissingCategoryData missingcategorydata = new MissingCategoryData()
			missingcategorydata.setMaincategory(ProjectConstants.CURRENTVISITING_MAINCATEGORY)
			missingcategorydata.setProductcategories(unmatcheditems.getItems())
			missingcategorydata.setProductcategories_errormessage(ProjectConstants.MESSAGEFOR_ITEMSARE_NOTMATCH)
			for(int j=0; j<ProjectConstants.missingshopdatainfo.size(); j++){
				if(ProjectConstants.missingshopdatainfo.get(j).getShopname().equalsIgnoreCase(ProjectConstants.CURRENTVISITING_SHOPNAME)) {
					ProjectConstants.missingshopdatainfo.get(j).setMissingCategoriesData(missingcategorydata, "")
					break
				}
				else{
				}
			}
		}
		else if(unmatcheditems.getStatus() == 1){
			MissingCategoryData missingcategorydata = new MissingCategoryData()
			missingcategorydata.setMaincategory(ProjectConstants.CURRENTVISITING_MAINCATEGORY)
			missingcategorydata.setProductcategories(unmatcheditems.getItems())
			missingcategorydata.setProductcategories_errormessage(ProjectConstants.MESSAGEFOR_ITEMSARE_MORE)
			for(int j=0; j<ProjectConstants.missingshopdatainfo.size(); j++){
				if(ProjectConstants.missingshopdatainfo.get(j).getShopname().equalsIgnoreCase(ProjectConstants.CURRENTVISITING_SHOPNAME)) {
					ProjectConstants.missingshopdatainfo.get(j).setMissingCategoriesData(missingcategorydata, "")
					break
				}
				else{
				}
			}
		}
		else if(unmatcheditems.getStatus() == -1){
			MissingCategoryData missingcategorydata = new MissingCategoryData()
			missingcategorydata.setMaincategory(ProjectConstants.CURRENTVISITING_MAINCATEGORY)
			missingcategorydata.setProductcategories(unmatcheditems.getItems())
			missingcategorydata.setProductcategories_errormessage(ProjectConstants.MESSAGEFOR_ITEMSARE_MISSING)
			for(int j=0; j<ProjectConstants.missingshopdatainfo.size(); j++){
				if(ProjectConstants.missingshopdatainfo.get(j).getShopname().equalsIgnoreCase(ProjectConstants.CURRENTVISITING_SHOPNAME)) {
					ProjectConstants.missingshopdatainfo.get(j).setMissingCategoriesData(missingcategorydata, "")
					break
				}
				else{
				}
			}
		}
		else{
		}
		int totalproductcategories = ProjectConstants.DRIVER.findElementsByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.ListView[1]/*").size()
		for(int i=1; i<=totalproductcategories; i++){
			MobileElement productcategory = ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout["+i+"]/android.widget.TextView[1]")
			ProjectConstants.CURRENTVISITING_PRODUCTCATEGORY = productcategory.getText()
			ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout["+i+"]").click()
			if(flag == 1){
				Mobile.callTestCase(findTestCase("Test Cases/ShopOpen/Nestrade/VisitProductCategoryAssetsWithNSFD"), null)
				MobileBuiltInKeywords.verifyElementText(findTestObject('ShopOpen/Nestrade/Validate_NestradePorductCategoryScreen', [('package') : ProjectConstants.PACKAGENAME]),'Category:Nestrade')
			}
			else if(flag == 2){
				Mobile.callTestCase(findTestCase("Test Cases/ShopOpen/Nestrade/OverwriteProductCategoryAssetsWithNSFD"), null)
				MobileBuiltInKeywords.verifyElementText(findTestObject('ShopOpen/Nestrade/Validate_NestradePorductCategoryScreen', [('package') : ProjectConstants.PACKAGENAME]),'Category:Nestrade')
			}
			else{
			}
		}
	}
	//enter quantity to related field
	@Keyword
	def visitChannelWiseProductsData(int columnindex, String assettype){
		ArrayList<ShopProductsData> shopproductsdata = new ArrayList<ShopProductsData>()
		int index = 0
		XSSFSheet channelproductssheet = LoadDataKeywords.loadChannelProductsSheet()
		ArrayList<String> displayproductslist = new ArrayList<String>()
		ArrayList<LoadProductsData> expectedproductslist = LoadDataKeywords.loadChannelWiseProductsList(channelproductssheet, columnindex)
		int expectedproducts = expectedproductslist.size()
		int totalproducts = ProjectConstants.DRIVER.findElementsByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ScrollView[1]/android.widget.LinearLayout[1]/*").size()
		for(int i=1; i<totalproducts; i=i+3){
			ShopProductsData productsdata = new ShopProductsData()
			boolean flag = false
			index = index + 1
			MobileElement selectedproduct = ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ScrollView[1]/android.widget.LinearLayout[1]/android.widget.TextView["+index+"]")
			String selectedproductname = selectedproduct.getText()
			productsdata.setProduct(selectedproductname)
			displayproductslist.add(selectedproductname)
			for(int j=0; j<expectedproductslist.size(); j++){
				LoadProductsData channelproduct = expectedproductslist.get(j)
				String productname = channelproduct.getProduct()
				if(selectedproductname.equalsIgnoreCase(productname)){
					flag = true
					String productquantity = channelproduct.getProduct_data()
					MobileElement selectedproducttextfield = ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ScrollView[1]/android.widget.LinearLayout[1]/android.widget.LinearLayout["+index+"]/android.widget.EditText[1]")
					selectedproducttextfield.setValue(productquantity)
					if(ProjectConstants.SCENARIO.equals("first visit")){
						if(assettype.equalsIgnoreCase("Facing")){
							productsdata.setFacingdata(productquantity)
						}
						else if(assettype.equalsIgnoreCase("Stock Taking")){
							productsdata.setStocktakingdata_stockcountdata(productquantity)
						}
						else{}
					}
					else{
						if(assettype.equalsIgnoreCase("Facing")){
							productsdata.setOverwritefacingdata(productquantity)
						}
						else if(assettype.equalsIgnoreCase("Stock Taking")){
							productsdata.setOverwritestocktakingdata_stockcountdata(productquantity)
						}
						else{
						}
					}
					Mobile.hideKeyboard()
					break
				}
				else{
				}
			}
			if(flag == false){
				MobileElement selectedproducttextfield = ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ScrollView[1]/android.widget.LinearLayout[1]/android.widget.LinearLayout["+index+"]/android.widget.EditText[1]")
				selectedproducttextfield.setValue("0000")
				if(ProjectConstants.SCENARIO.equals("first visit")){
					if(assettype.equalsIgnoreCase("Facing")){
						productsdata.setFacingdata("0000")
					}
					else if(assettype.equalsIgnoreCase("Stock Taking")){
						productsdata.setStocktakingdata_stockcountdata("0000")
					}
					else{}
				}
				else{
					if(assettype.equalsIgnoreCase("Facing")){
						productsdata.setOverwritefacingdata("0000")
					}
					else if(assettype.equalsIgnoreCase("Stock Taking")){
						productsdata.setOverwritestocktakingdata_stockcountdata("0000")
					}
					else{
					}
				}
				Mobile.hideKeyboard()
			}
			shopproductsdata.add(productsdata)
		}
		totalproducts = ProjectConstants.DRIVER.findElementsByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ScrollView[1]/android.widget.LinearLayout[1]/*").size()
		if(totalproducts >= 16){
			while(true){
				ShopProductsData productsdata = new ShopProductsData()
				int xlocation = CommonKeywords.getXPoint()
				MobileElement lastproductbeforeswipe = ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ScrollView[1]/android.widget.LinearLayout[1]/android.widget.TextView[5]")
				String lastproductnamebeforeswipe = lastproductbeforeswipe.getText()
				Mobile.swipe(xlocation, 359, xlocation, 250)
				MobileElement lastproductafterswipe = ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ScrollView[1]/android.widget.LinearLayout[1]/android.widget.TextView[5]")
				String lastproductnameafterswipe = lastproductafterswipe.getText()
				if(lastproductnamebeforeswipe.equalsIgnoreCase(lastproductnameafterswipe)){
					break
				}
				else{
					boolean flag = false
					displayproductslist.add(lastproductnameafterswipe)
					productsdata.setProduct(lastproductnameafterswipe)
					for(int j=0; j<expectedproductslist.size(); j++){
						LoadProductsData channelproduct = expectedproductslist.get(j)
						String productname = channelproduct.getProduct()
						if(lastproductnameafterswipe.equalsIgnoreCase(productname)){
							flag = true
							String productquantity = channelproduct.getProduct_data()
							MobileElement selectedproducttextfield = ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ScrollView[1]/android.widget.LinearLayout[1]/android.widget.LinearLayout[6]/android.widget.EditText[1]")
							selectedproducttextfield.setValue(productquantity)
							if(ProjectConstants.SCENARIO.equals("first visit")){
								if(assettype.equalsIgnoreCase("Facing")){
									productsdata.setFacingdata(productquantity)
								}
								else if(assettype.equalsIgnoreCase("Stock Taking")){
									productsdata.setStocktakingdata_stockcountdata(productquantity)
								}
								else{}
							}
							else{
								if(assettype.equalsIgnoreCase("Facing")){
									productsdata.setOverwritefacingdata(productquantity)
								}
								else if(assettype.equalsIgnoreCase("Stock Taking")){
									productsdata.setOverwritestocktakingdata_stockcountdata(productquantity)
								}
								else{
								}
							}
							Mobile.hideKeyboard()
							break
						}
						else{
						}
					}
					if(flag == false){
						MobileElement selectedproducttextfield = ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ScrollView[1]/android.widget.LinearLayout[1]/android.widget.LinearLayout[6]/android.widget.EditText[1]")
						selectedproducttextfield.setValue("0000")
						if(ProjectConstants.SCENARIO.equals("first visit")){
							if(assettype.equalsIgnoreCase("Facing")){
								productsdata.setFacingdata("0000")
							}
							else if(assettype.equalsIgnoreCase("Stock Taking")){
								productsdata.setStocktakingdata_stockcountdata("0000")
							}
							else{}
						}
						else{
							if(assettype.equalsIgnoreCase("Facing")){
								productsdata.setOverwritefacingdata("0000")
							}
							else if(assettype.equalsIgnoreCase("Stock Taking")){
								productsdata.setOverwritestocktakingdata_stockcountdata("0000")
							}
							else{
							}
						}
						Mobile.hideKeyboard()
					}
				}
				shopproductsdata.add(productsdata)
			}
		}
		if(expectedproductslist.size() == displayproductslist.size()){
			ArrayList<String> products = new ArrayList<String>()
			for(int i=0; i<displayproductslist.size(); i++){
				boolean match = false
				for(int j=0; j<expectedproductslist.size(); j++){
					if(displayproductslist.get(i).equalsIgnoreCase(expectedproductslist.get(j).getProduct())){
						match = true
						break
					}
				}
				if(match == false){
					products.add(displayproductslist.get(i))
				}
				else{
				}
			}
			if(!products.isEmpty()){
				MissingCategoryData missingcategorydata = new MissingCategoryData()
				missingcategorydata.setMaincategory(ProjectConstants.CURRENTVISITING_MAINCATEGORY)
				missingcategorydata.setProducts(products)
				missingcategorydata.setProductCategory(ProjectConstants.CURRENTVISITING_PRODUCTCATEGORY)
				missingcategorydata.setProducts_errormessage(ProjectConstants.MESSAGEFOR_ITEMSARE_NOTMATCH)
				for(int j=0; j<ProjectConstants.missingshopdatainfo.size(); j++){
					if(ProjectConstants.missingshopdatainfo.get(j).getShopname().equalsIgnoreCase(ProjectConstants.CURRENTVISITING_SHOPNAME)) {
						ProjectConstants.missingshopdatainfo.get(j).setMissingCategoriesData(missingcategorydata, assettype)
						break
					}
					else{
					}
				}
			}
			else{
			}
		}
		else if(expectedproductslist.size() < displayproductslist.size()){
			ArrayList<String> products = new ArrayList<String>()
			for(int i=0; i<displayproductslist.size(); i++){
				boolean match = false
				for(int j=0; j<expectedproductslist.size(); j++){
					if(displayproductslist.get(i).equalsIgnoreCase(expectedproductslist.get(j).getProduct())){
						match = true
						break
					}
				}
				if(match == false){
					products.add(displayproductslist.get(i))
				}
				else{
				}
			}
			MissingCategoryData missingcategorydata = new MissingCategoryData()
			missingcategorydata.setMaincategory(ProjectConstants.CURRENTVISITING_MAINCATEGORY)
			missingcategorydata.setProducts(products)
			missingcategorydata.setProductCategory(ProjectConstants.CURRENTVISITING_PRODUCTCATEGORY)
			missingcategorydata.setProducts_errormessage(ProjectConstants.MESSAGEFOR_ITEMSARE_MORE)
			for(int j=0; j<ProjectConstants.missingshopdatainfo.size(); j++){
				if(ProjectConstants.missingshopdatainfo.get(j).getShopname().equalsIgnoreCase(ProjectConstants.CURRENTVISITING_SHOPNAME)) {
					ProjectConstants.missingshopdatainfo.get(j).setMissingCategoriesData(missingcategorydata, assettype)
				}
				else{
				}
			}
		}
		else if(expectedproductslist.size() > displayproductslist.size()){
			ArrayList<String> products = new ArrayList<String>()
			for(int i=0; i<expectedproductslist.size(); i++){
				boolean match = false
				for(int j=0; j<displayproductslist.size(); j++){
					if(expectedproductslist.get(i).getProduct().equalsIgnoreCase(displayproductslist.get(j))){
						match = true
						break
					}
				}
				if(match == false){
					products.add(expectedproductslist.get(i).getProduct())
				}
				else{
				}
			}
			MissingCategoryData missingcategorydata = new MissingCategoryData()
			missingcategorydata.setMaincategory(ProjectConstants.CURRENTVISITING_MAINCATEGORY)
			missingcategorydata.setProductCategory(ProjectConstants.CURRENTVISITING_PRODUCTCATEGORY)
			missingcategorydata.setProducts_errormessage(ProjectConstants.MESSAGEFOR_ITEMSARE_MISSING)
			missingcategorydata.setProducts(products)
			for(int j=0; j<ProjectConstants.missingshopdatainfo.size(); j++){
				if(ProjectConstants.missingshopdatainfo.get(j).getShopname().equalsIgnoreCase(ProjectConstants.CURRENTVISITING_SHOPNAME)) {
					ProjectConstants.missingshopdatainfo.get(j).setMissingCategoriesData(missingcategorydata, assettype)
					break
				}
				else{
				}
			}
		}
		else{

			String message = "Main Category: "+ProjectConstants.CURRENTVISITING_MAINCATEGORY+"\nProduct Category: "+ProjectConstants.CURRENTVISITING_PRODUCTCATEGORY+"\n"+ProjectConstants.MESSAGEFOR_DISPLAYEDPRODUCTSARE_EQUAL
			KeywordUtil.logInfo(message)
		}
		VisitedCategoryData visitedcategorydata = new VisitedCategoryData()
		ProductCategoryWithProducts productcategorywithproducts = new ProductCategoryWithProducts()
		visitedcategorydata.setMaincategory(ProjectConstants.CURRENTVISITING_MAINCATEGORY)
		if(ProjectConstants.SCENARIO.equalsIgnoreCase("first visit")){
			visitedcategorydata.setFirstvisit_categoryremark(ProjectConstants.CURRENTVISITING_CATEGORYREMARK)
		}
		else{
			visitedcategorydata.setOverwrite_categoryremark(ProjectConstants.CURRENTVISITING_CATEGORYREMARK)
		}
		productcategorywithproducts.setProductcategory(ProjectConstants.CURRENTVISITING_PRODUCTCATEGORY)
		productcategorywithproducts.setShopproductsdata(shopproductsdata)
		visitedcategorydata.setProductcategorywithproducts(productcategorywithproducts)
		for(int i=0; i< ProjectConstants.visitedshopdatainfo.size(); i++){
			if(ProjectConstants.visitedshopdatainfo.get(i).getShopname().equals(ProjectConstants.CURRENTVISITING_SHOPNAME)){
				VisitedShopDataInfo visitedshopdata = ProjectConstants.visitedshopdatainfo.get(i)
				ArrayList<VisitedCategoryData> visitedcategoriesdata = visitedshopdata.getVisitedcategoriesdata()
				if(visitedcategoriesdata.size() != 0){
					boolean flag = false
					for(int k=0; k<visitedcategoriesdata.size(); k++){
						VisitedCategoryData visitedcategorydatainfo = visitedcategoriesdata.get(k)
						if(visitedcategorydatainfo.getMaincategory().equals(visitedcategorydata.getMaincategory())){
							flag = true
							if(ProjectConstants.SCENARIO.equalsIgnoreCase("first visit")){
								visitedcategorydatainfo.setFirstvisit_categoryremark(ProjectConstants.CURRENTVISITING_CATEGORYREMARK)
							}
							else{
								visitedcategorydatainfo.setOverwrite_categoryremark(ProjectConstants.CURRENTVISITING_CATEGORYREMARK)
							}
							ArrayList<ProductCategoryWithProducts> productcategorywithproductsdatalist = visitedcategorydatainfo.getProductcategorywithproducts()
							if(productcategorywithproductsdatalist != null){
								boolean productcategoryflag = false
								for(int h=0; h< productcategorywithproductsdatalist.size(); h++){
									ProductCategoryWithProducts productcategorywithproductsdata = productcategorywithproductsdatalist.get(h)
									if(productcategorywithproductsdata.getProductcategory().equals(productcategorywithproducts.getProductcategory())){
										productcategoryflag = true
										for(int l=0; l< productcategorywithproductsdata.getShopproductsdata().size(); l++){
											ShopProductsData existingproductsdata = productcategorywithproductsdata.getShopproductsdata().get(l)
											for(int m=0; m< shopproductsdata.size(); m++){
												ShopProductsData newproductsdatainfo = shopproductsdata.get(m)
												if(existingproductsdata.getProduct().equals(newproductsdatainfo.getProduct())){
													if(ProjectConstants.SCENARIO.equals("first visit")){
														if(assettype.equals("Facing")){
															existingproductsdata.setFacingdata(newproductsdatainfo.getFacingdata())
															break
														}
														else if(assettype.equals("Stock Taking")){
															existingproductsdata.setStocktakingdata_stockcountdata(newproductsdatainfo.getStocktakingdata_stockcountdata())
															break
														}
														else{
														}
													}
													else{
														if(assettype.equals("Facing")){
															existingproductsdata.setOverwritefacingdata(newproductsdatainfo.getOverwritefacingdata())
															break
														}
														else if(assettype.equals("Stock Taking")){
															existingproductsdata.setOverwritestocktakingdata_stockcountdata(newproductsdatainfo.getOverwritestocktakingdata_stockcountdata())
															break
														}
														else{
														}
													}
												}
											}
										}
									}
									else{
									}
								}
								if(productcategoryflag == false){
									if(ProjectConstants.SCENARIO.equalsIgnoreCase("first visit")){
										visitedcategorydatainfo.setFirstvisit_categoryremark(ProjectConstants.CURRENTVISITING_CATEGORYREMARK)
									}
									else if(assettype.equalsIgnoreCase("Facing")){
										visitedcategorydatainfo.setOverwrite_categoryremark(ProjectConstants.CURRENTVISITING_CATEGORYREMARK)
									}
									else{}
									visitedcategorydatainfo.setProductcategorywithproducts(productcategorywithproducts)
								}
							}
						}
					}
					if(flag == false){
						ProjectConstants.visitedshopdatainfo.get(i).setVisitedcategoriesdata(visitedcategorydata)
						break
					}
				}
				else{
					ProjectConstants.visitedshopdatainfo.get(i).setVisitedcategoriesdata(visitedcategorydata)
					break
				}
			}
		}
	}
}
