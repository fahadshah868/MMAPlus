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
import com.sun.jna.Structure.FFIType.size_t

import internal.GlobalVariable

import MobileBuiltInKeywords as Mobile
import WSBuiltInKeywords as WS
import WebUiBuiltInKeywords as WebUI
import com.ct.qa.constants.ProjectConstants
import com.ct.qa.struct.ProductsData
import com.ct.qa.struct.UnmatchedProducts
import io.appium.java_client.MobileElement

public class ChillerProductsDataKeywords {
	def loadChillerAvailableProductsList(XSSFSheet sheet, int column){
		DataFormatter dataformatter = new DataFormatter()
		ArrayList<ProductsData> channelproducts = new ArrayList<ProductsData>()
		int totalrows = sheet.getLastRowNum()
		for(int i=1; i<=totalrows; i++){
			Row row = sheet.getRow(i)
			String chillertype = dataformatter.formatCellValue(row.getCell(ProjectConstants.CHILLER_TYPE))
			String productcategory = dataformatter.formatCellValue(row.getCell(ProjectConstants.CHILLER_PRODUCTCATEGORY))
			if(ProjectConstants.CURRENTVISITING_CHILLERTYPE.equalsIgnoreCase(chillertype) && ProjectConstants.CURRENTVISITING_PRODUCTCATEGORY.equalsIgnoreCase(productcategory)){
				ProductsData channelproduct = new ProductsData()
				String product = dataformatter.formatCellValue(row.getCell(ProjectConstants.CHILLER_PRODUCT))
				String columndata = dataformatter.formatCellValue(row.getCell(column))
				channelproduct.setProduct(product)
				channelproduct.setProduct_data(columndata)
				channelproducts.add(channelproduct)
			}
			else{
			}
		}
		return channelproducts
	}
	def loadChillerNotAvailableProductsList(XSSFSheet sheet, int column){
		DataFormatter dataformatter = new DataFormatter()
		ArrayList<ProductsData> channelproducts = new ArrayList<ProductsData>()
		int totalrows = sheet.getLastRowNum()
		for(int i=1; i<=totalrows; i++){
			Row row = sheet.getRow(i)
			String channel = dataformatter.formatCellValue(row.getCell(ProjectConstants.CHANNEL))
			String maincategory = dataformatter.formatCellValue(row.getCell(ProjectConstants.CHANNEL_MAINCATEGORY))
			String productcategory = dataformatter.formatCellValue(row.getCell(ProjectConstants.CHANNEL_PRODUCTCATEGORY))
			if((ProjectConstants.CURRENTVISITING_SHOPCHANNEL.contains(channel) && maincategory.equalsIgnoreCase("Chiller")) && ProjectConstants.CURRENTVISITING_PRODUCTCATEGORY.equalsIgnoreCase(productcategory)){
				ProductsData channelproduct = new ProductsData()
				String product = dataformatter.formatCellValue(row.getCell(ProjectConstants.CHANNEL_PRODUCT))
				String columndata = dataformatter.formatCellValue(row.getCell(column))
				channelproduct.setProduct(product)
				channelproduct.setProduct_data(columndata)
				channelproducts.add(channelproduct)
			}
			else{
			}
		}
		return channelproducts
	}
	@Keyword
	def visitChillerAvailableProductCategories(int flag){
		UnmatchedProducts unmatchedproducts_status = ProjectConstants.compareChannelWiseProductsCategories()
		//if displayed rpoducts are more than to expected products
		if(unmatchedproducts_status.getStatus() == 1){
			ArrayList<String> products = unmatchedproducts_status.getProducts()
			String message = ProjectConstants.CURRENTVISITING_SHOPCHANNEL+"\n\nMainCategory: "+ProjectConstants.CURRENTVISITING_MAINCATEGORY+"\n\nProduct Categories: "
			for(int i=0; i<products.size(); i++){
				message = message+products.get(i)+" , "
			}
			message = message+"\n\n"+ProjectConstants.MESSAGEFOR_PRODUCTSCATEGORIESARE_MORE
			KeywordUtil.markErrorAndStop(message)
		}
		//if displayed products are less than to expected products
		else if(unmatchedproducts_status.getStatus() == -1){
			ArrayList<String> products = unmatchedproducts_status.getProducts()
			String message = ProjectConstants.CURRENTVISITING_SHOPCHANNEL+"\n\nMainCategory: "+ProjectConstants.CURRENTVISITING_MAINCATEGORY+"\n\nProduct Categories: "
			for(int i=0; i<products.size(); i++){
				message = message+products.get(i)+" , "
			}
			message = message+"\n\n"+ProjectConstants.MESSAGEFOR_PRODUCTSCATEGORIESARE_MISSING
			KeywordUtil.markErrorAndStop(message)
		}
		else{
			int totalproductcategories = ProjectConstants.DRIVER.findElementsByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.ListView[1]/*").size()
			for(int i=1; i<=totalproductcategories; i++){
				MobileElement productcategory = ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout["+i+"]/android.widget.TextView[1]")
				ProjectConstants.CURRENTVISITING_PRODUCTCATEGORY = productcategory.getText()
				ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout["+i+"]").click()
				if(flag == 1){
					Mobile.callTestCase(findTestCase("Test Cases/ShopOpen/ChillerUtilization/ChillerAvailable/VisitProductCategoryAssets"), null)
				}
				else if(flag == 2){
					Mobile.callTestCase(findTestCase("Test Cases/ShopOpen/ChillerUtilization/ChillerAvailable/OverwriteProductCategoryAssets"), null)
				}
				else{
				}
			}
		}
	}
	@Keyword
	def visitChillerNotAvailableProductCategories(int flag){
		UnmatchedProducts unmatchedproducts_status = ProjectConstants.compareChannelWiseProductsCategories()
		//if displayed rpoducts are more than to expected products
		if(unmatchedproducts_status.getStatus() == 1){
			ArrayList<String> products = unmatchedproducts_status.getProducts()
			String message = ProjectConstants.CURRENTVISITING_SHOPCHANNEL+"\n\nMainCategory: "+ProjectConstants.CURRENTVISITING_MAINCATEGORY+"\n\nProduct Categories: "
			for(int i=0; i<products.size(); i++){
				message = message+products.get(i)+" , "
			}
			message = message+"\n\n"+ProjectConstants.MESSAGEFOR_PRODUCTSCATEGORIESARE_MORE
			KeywordUtil.markErrorAndStop(message)
		}
		//if displayed products are less than to expected products
		else if(unmatchedproducts_status.getStatus() == -1){
			ArrayList<String> products = unmatchedproducts_status.getProducts()
			String message = ProjectConstants.CURRENTVISITING_SHOPCHANNEL+"\n\nMainCategory: "+ProjectConstants.CURRENTVISITING_MAINCATEGORY+"\n\nProduct Categories: "
			for(int i=0; i<products.size(); i++){
				message = message+products.get(i)+" , "
			}
			message = message+"\n\n"+ProjectConstants.MESSAGEFOR_PRODUCTSCATEGORIESARE_MISSING
			KeywordUtil.markErrorAndStop(message)
		}
		else{
			int totalproductcategories = ProjectConstants.DRIVER.findElementsByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.ListView[1]/*").size()
			for(int i=1; i<=totalproductcategories; i++){
				MobileElement productcategory = ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout["+i+"]/android.widget.TextView[1]")
				ProjectConstants.CURRENTVISITING_PRODUCTCATEGORY = productcategory.getText()
				ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout["+i+"]").click()
				if(flag == 1){
					Mobile.callTestCase(findTestCase("Test Cases/ShopOpen/ChillerUtilization/ChillerNotAvailable/VisitProductCategoryAssets"), null)
				}
				else if(flag == 2){
					Mobile.callTestCase(findTestCase("Test Cases/ShopOpen/ChillerUtilization/ChillerNotAvailable/VisitProductCategoryAssets"), null)
				}
				else{
				}
			}
		}
	}
	@Keyword
	def visitChillerAvailableProductsData(int columnindex){
		int displayedproducts = 0
		int index = 0
		XSSFSheet chillerproductssheet = ProjectConstants.loadChillerProductsSheet()
		ArrayList<String> displayedproductslist = new ArrayList<String>()
		ArrayList<ProductsData> expectedproductslist = loadChillerAvailableProductsList(chillerproductssheet, columnindex)
		int expectedproducts = expectedproductslist.size()
		int totalproducts = ProjectConstants.DRIVER.findElementsByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ScrollView[1]/android.widget.LinearLayout[1]/*").size()
		for(int i=1; i<totalproducts; i=i+3){
			displayedproducts = displayedproducts + 1
			index = index + 1
			MobileElement selectedproduct = ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ScrollView[1]/android.widget.LinearLayout[1]/android.widget.TextView["+index+"]")
			String selectedproductname = selectedproduct.getText()
			displayedproductslist.add(selectedproductname)
			for(int j=0; j<expectedproductslist.size(); j++){
				ProductsData channelproduct = expectedproductslist.get(j)
				String productname = channelproduct.getProduct()
				if(selectedproductname.equalsIgnoreCase(productname)){
					String productquantity = channelproduct.getProduct_data()
					MobileElement selectedproducttextfield = ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ScrollView[1]/android.widget.LinearLayout[1]/android.widget.LinearLayout["+index+"]/android.widget.EditText[1]")
					selectedproducttextfield.setValue(productquantity)
					Mobile.hideKeyboard()
					break
				}
				else{
				}
			}
		}
		totalproducts = ProjectConstants.DRIVER.findElementsByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ScrollView[1]/android.widget.LinearLayout[1]/*").size()
		if(totalproducts == 16){
			while(true){
				int xlocation = ProjectConstants.getXPoint()
				MobileElement lastproductbeforeswipe = ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ScrollView[1]/android.widget.LinearLayout[1]/android.widget.TextView[5]")
				String lastproductnamebeforeswipe = lastproductbeforeswipe.getText()
				Mobile.swipe(xlocation, 359, xlocation, 250)
				MobileElement lastproductafterswipe = ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ScrollView[1]/android.widget.LinearLayout[1]/android.widget.TextView[5]")
				String lastproductnameafterswipe = lastproductafterswipe.getText()
				if(lastproductnamebeforeswipe.equalsIgnoreCase(lastproductnameafterswipe)){
					break
				}
				else{
					displayedproducts = displayedproducts + 1
					for(int j=0; j<expectedproductslist.size(); j++){
						ProductsData channelproduct = expectedproductslist.get(j)
						String productname = channelproduct.getProduct()
						if(lastproductnameafterswipe.equalsIgnoreCase(productname)){
							String productquantity = channelproduct.getProduct_data()
							MobileElement selectedproducttextfield = ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ScrollView[1]/android.widget.LinearLayout[1]/android.widget.LinearLayout[6]/android.widget.EditText[1]")
							selectedproducttextfield.setValue(productquantity)
							Mobile.hideKeyboard()
							break
						}
						else{
						}
					}
				}
			}
		}
		int result = displayedproducts.compareTo(expectedproducts)
		//if displayed products are greater than expected products
		if(result == 1)
		{
			ArrayList<String> products = new ArrayList<String>()
			for(int i=0; i<displayedproductslist.size(); i++){
				boolean match = false
				for(int j=0; j<expectedproductslist.size(); j++){
					if(displayedproductslist.get(i).equalsIgnoreCase(expectedproductslist.get(j))){
						match = true
					}
				}
				if(match == false){
					products.add(displayedproductslist.get(i))
				}
				else{
				}
			}
			String message = ProjectConstants.CURRENTVISITING_SHOPCHANNEL+"\n\nMain Categories: "+ProjectConstants.CURRENTVISITING_MAINCATEGORY+"\n\nProducts: "
			for(int i=0; i<products.size(); i++){
				message = message+products.get(i)+" , "
			}
			message = message+"\n\n"+ProjectConstants.MESSAGEFOR_PRODUCTSARE_MORE
			KeywordUtil.markErrorAndStop(message)
		}
		//if displayed products are less than expected products
		else if(result == -1)
		{
			ArrayList<String> products = new ArrayList<String>()
			for(int i=0; i<displayedproductslist.size(); i++){
				boolean match = false
				for(int j=0; j<expectedproductslist.size(); j++){
					if(displayedproductslist.get(i).equalsIgnoreCase(expectedproductslist.get(j))){
						match = true
					}
				}
				if(match == false){
					products.add(displayedproductslist.get(i))
				}
				else{
				}
			}
			String message = ProjectConstants.CURRENTVISITING_SHOPCHANNEL+"\n\nMain Categories: "+ProjectConstants.CURRENTVISITING_MAINCATEGORY+"\n\nProducts: "
			for(int i=0; i<products.size(); i++){
				message = message+products.get(i)+" , "
			}
			message = message+"\n\n"+ProjectConstants.MESSAGEFOR_PRODUCTSARE_MISSING
			KeywordUtil.markErrorAndStop(message)
		}
		else{
			String message = "Main Category: "+ProjectConstants.CURRENTVISITING_MAINCATEGORY+"\nProduct Category: "+ProjectConstants.CURRENTVISITING_PRODUCTCATEGORY+"\n"+ProjectConstants.MESSAGEFOR_DISPLAYEDPRODUCTSARE_EQUAL
			KeywordUtil.logInfo(message)
		}
	}
	@Keyword
	def VisitChillerNotAvailableProductsData(int columnindex){
		int displayedproducts = 0
		int index = 0
		XSSFSheet channelproductssheet = ProjectConstants.loadChannelProductsSheet()
		ArrayList<String> displayedproductslist = new ArrayList<String>()
		ArrayList<ProductsData> expectedproductslist = loadChillerNotAvailableProductsList(channelproductssheet, columnindex)
		int expectedproducts = expectedproductslist.size()
		int totalproducts = ProjectConstants.DRIVER.findElementsByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ScrollView[1]/android.widget.LinearLayout[1]/*").size()
		for(int i=1; i<totalproducts; i=i+3){
			displayedproducts = displayedproducts + 1
			index = index + 1
			MobileElement selectedproduct = ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ScrollView[1]/android.widget.LinearLayout[1]/android.widget.TextView["+index+"]")
			String selectedproductname = selectedproduct.getText()
			displayedproductslist.add(selectedproductname)
			for(int j=0; j<expectedproductslist.size(); j++){
				ProductsData channelproduct = expectedproductslist.get(j)
				String productname = channelproduct.getProduct()
				if(selectedproductname.equalsIgnoreCase(productname)){
					String productquantity = channelproduct.getProduct_data()
					MobileElement selectedproducttextfield = ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ScrollView[1]/android.widget.LinearLayout[1]/android.widget.LinearLayout["+index+"]/android.widget.EditText[1]")
					selectedproducttextfield.setValue(productquantity)
					Mobile.hideKeyboard()
					break
				}
				else{
				}
			}
		}
		if(totalproducts == 16){
			while(true){
				int xlocation = ProjectConstants.getXPoint()
				MobileElement lastproductbeforeswipe = ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ScrollView[1]/android.widget.LinearLayout[1]/android.widget.TextView[5]")
				String lastproductnamebeforeswipe = lastproductbeforeswipe.getText()
				Mobile.swipe(xlocation, 359, xlocation, 250)
				MobileElement lastproductafterswipe = ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ScrollView[1]/android.widget.LinearLayout[1]/android.widget.TextView[5]")
				String lastproductnameafterswipe = lastproductafterswipe.getText()
				if(lastproductnamebeforeswipe.equalsIgnoreCase(lastproductnameafterswipe)){
					break
				}
				else{
					displayedproducts = displayedproducts + 1
					for(int j=0; j<expectedproductslist.size(); j++){
						ProductsData channelproduct = expectedproductslist.get(j)
						String productname = channelproduct.getProduct()
						if(lastproductnameafterswipe.equalsIgnoreCase(productname)){
							String productquantity = channelproduct.getProduct_data()
							MobileElement selectedproducttextfield = ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ScrollView[1]/android.widget.LinearLayout[1]/android.widget.LinearLayout[6]/android.widget.EditText[1]")
							selectedproducttextfield.setValue(productquantity)
							Mobile.hideKeyboard()
							break
						}
						else{
						}
					}
				}
			}
		}
		int result = displayedproducts.compareTo(expectedproducts)
		//if displayed products are greater than expected products
		if(result == 1)
		{
			ArrayList<String> products = new ArrayList<String>()
			for(int i=0; i<displayedproductslist.size(); i++){
				boolean match = false
				for(int j=0; j<expectedproductslist.size(); j++){
					if(displayedproductslist.get(i).equalsIgnoreCase(expectedproductslist.get(j))){
						match = true
					}
				}
				if(match == false){
					products.add(displayedproductslist.get(i))
				}
				else{
				}
			}
			String message = ProjectConstants.CURRENTVISITING_SHOPCHANNEL+"\n\nMain Categories: "+ProjectConstants.CURRENTVISITING_MAINCATEGORY+"\n\nProducts: "
			for(int i=0; i<products.size(); i++){
				message = message+products.get(i)+" , "
			}
			message = message+"\n\n"+ProjectConstants.MESSAGEFOR_PRODUCTSARE_MORE
			KeywordUtil.markErrorAndStop(message)
		}
		//if displayed products are less than expected products
		else if(result == -1)
		{
			ArrayList<String> products = new ArrayList<String>()
			for(int i=0; i<displayedproductslist.size(); i++){
				boolean match = false
				for(int j=0; j<expectedproductslist.size(); j++){
					if(displayedproductslist.get(i).equalsIgnoreCase(expectedproductslist.get(j))){
						match = true
					}
				}
				if(match == false){
					products.add(displayedproductslist.get(i))
				}
				else{
				}
			}
			String message = ProjectConstants.CURRENTVISITING_SHOPCHANNEL+"\n\nMain Categories: "+ProjectConstants.CURRENTVISITING_MAINCATEGORY+"\n\nProducts: "
			for(int i=0; i<products.size(); i++){
				message = message+products.get(i)+" , "
			}
			message = message+"\n\n"+ProjectConstants.MESSAGEFOR_PRODUCTSARE_MISSING
			KeywordUtil.markErrorAndStop(message)
		}
		else{
			String message = "Main Category: "+ProjectConstants.CURRENTVISITING_MAINCATEGORY+"\nProduct Category: "+ProjectConstants.CURRENTVISITING_PRODUCTCATEGORY+"\n"+ProjectConstants.MESSAGEFOR_DISPLAYEDPRODUCTSARE_EQUAL
			KeywordUtil.logInfo(message)
		}
	}
}
