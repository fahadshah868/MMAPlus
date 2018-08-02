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
		int status = ProjectConstants.compareChillerWiseProductsCategories()
		if(status == 2){
			String message = ProjectConstants.CURRENTVISITING_SHOPCHANNEL+"\nCategory: "+ProjectConstants.CURRENTVISITING_MAINCATEGORY+"\n"+ProjectConstants.MESSAGEFOR_CATEGORYDATAISNOTAVAILABLE
			KeywordUtil.markErrorAndStop(message)
		}
		else if(status == 1){
			String message = "Chiller Type: "+ProjectConstants.CURRENTVISITING_CHILLERTYPE+"\nChiller Remark: "+ProjectConstants.CURRENTVISITING_CHILLERREMARK+"\n"+ProjectConstants.MESSAGEFOR_DISPLAYEDPRODUCTSCATEGORIESARE_GREATER
			KeywordUtil.markErrorAndStop(message)
		}
		else if(status == -1){
			String message = "Chiller Type: "+ProjectConstants.CURRENTVISITING_CHILLERTYPE+"\nChiller Remark: "+ProjectConstants.CURRENTVISITING_CHILLERREMARK+"\n"+ProjectConstants.MESSAGEFOR_DISPLAYEDPRODUCTSCATEGORIESARE_GREATER
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
		int status = ProjectConstants.compareChannelWiseProductsCategories()
		if(status == 2){
			String message = ProjectConstants.CURRENTVISITING_SHOPCHANNEL+"\nCategory: "+ProjectConstants.CURRENTVISITING_MAINCATEGORY+"\n"+ProjectConstants.MESSAGEFOR_CATEGORYDATAISNOTAVAILABLE
			KeywordUtil.markErrorAndStop(message)
		}
		else if(status == 1){
			String message = ProjectConstants.CURRENTVISITING_SHOPCHANNEL+"\nMain Category: "+ProjectConstants.CURRENTVISITING_MAINCATEGORY+"\n"+ProjectConstants.MESSAGEFOR_DISPLAYEDPRODUCTSCATEGORIESARE_GREATER
			KeywordUtil.markErrorAndStop(message)
		}
		else if(status == -1){
			String message = ProjectConstants.CURRENTVISITING_SHOPCHANNEL+"\nMain Category: "+ProjectConstants.CURRENTVISITING_MAINCATEGORY+" \n"+ProjectConstants.MESSAGEFOR_DISPLAYEDPRODUCTSCATEGORIESARE_LESS
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
		ArrayList<ProductsData> chillerproducts = loadChillerAvailableProductsList(chillerproductssheet, columnindex)
		int expectedproducts = chillerproducts.size()
		int totalproducts = ProjectConstants.DRIVER.findElementsByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ScrollView[1]/android.widget.LinearLayout[1]/*").size()
		for(int i=1; i<totalproducts; i=i+3){
			index = index + 1
			MobileElement selectedproduct = ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ScrollView[1]/android.widget.LinearLayout[1]/android.widget.TextView["+index+"]")
			String selectedproductname = selectedproduct.getText()
			for(int j=0; j<chillerproducts.size(); j++){
				ProductsData channelproduct = chillerproducts.get(j)
				String productname = channelproduct.getProduct()
				if(selectedproductname.equalsIgnoreCase(productname)){
					displayedproducts = displayedproducts + 1
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
					for(int j=0; j<chillerproducts.size(); j++){
						ProductsData channelproduct = chillerproducts.get(j)
						String productname = channelproduct.getProduct()
						if(lastproductnameafterswipe.equalsIgnoreCase(productname)){
							displayedproducts = displayedproducts + 1
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
			String message = "Chiller Type: "+ProjectConstants.CURRENTVISITING_CHILLERTYPE+"\nChiller Remark: "+ProjectConstants.CURRENTVISITING_CHILLERREMARK+"\nProduct Category: "+ProjectConstants.CURRENTVISITING_PRODUCTCATEGORY+"\n"+ProjectConstants.MESSAGEFOR_DISPLAYEDPRODUCTSARE_GREATER
			KeywordUtil.markErrorAndStop(message)
		}
		//if displayed products are less than expected products
		else if(result == -1)
		{
			String message = "Chiller Type: "+ProjectConstants.CURRENTVISITING_CHILLERTYPE+"\nChiller Remark: "+ProjectConstants.CURRENTVISITING_CHILLERREMARK+"\nProduct Category: "+ProjectConstants.CURRENTVISITING_PRODUCTCATEGORY+"\n"+ProjectConstants.MESSAGEFOR_DISPLAYEDPRODUCTSARE_LESS
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
		ArrayList<ProductsData> channelproducts = loadChillerNotAvailableProductsList(channelproductssheet, columnindex)
		int expectedproducts = channelproducts.size()
		int totalproducts = ProjectConstants.DRIVER.findElementsByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ScrollView[1]/android.widget.LinearLayout[1]/*").size()
		for(int i=1; i<totalproducts; i=i+3){
			index = index + 1
			MobileElement selectedproduct = ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ScrollView[1]/android.widget.LinearLayout[1]/android.widget.TextView["+index+"]")
			String selectedproductname = selectedproduct.getText()
			for(int j=0; j<channelproducts.size(); j++){
				ProductsData channelproduct = channelproducts.get(j)
				String productname = channelproduct.getProduct()
				if(selectedproductname.equalsIgnoreCase(productname)){
					displayedproducts = displayedproducts + 1
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
					for(int j=0; j<channelproducts.size(); j++){
						ProductsData channelproduct = channelproducts.get(j)
						String productname = channelproduct.getProduct()
						if(lastproductnameafterswipe.equalsIgnoreCase(productname)){
							displayedproducts = displayedproducts + 1
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
			String message = "Chiller Type: "+ProjectConstants.CURRENTVISITING_CHILLERTYPE+"\nChiller Remark: "+ProjectConstants.CURRENTVISITING_CHILLERREMARK+"\nProduct Category: "+ProjectConstants.CURRENTVISITING_PRODUCTCATEGORY+"\n"+ProjectConstants.MESSAGEFOR_DISPLAYEDPRODUCTSARE_GREATER
			KeywordUtil.markErrorAndStop(message)
		}
		//if displayed products are less than expected products
		else if(result == -1)
		{
			String message = "Chiller Type: "+ProjectConstants.CURRENTVISITING_CHILLERTYPE+"\nChiller Remark: "+ProjectConstants.CURRENTVISITING_CHILLERREMARK+"\nProduct Category: "+ProjectConstants.CURRENTVISITING_PRODUCTCATEGORY+"\n"+ProjectConstants.MESSAGEFOR_DISPLAYEDPRODUCTSARE_LESS
			KeywordUtil.markErrorAndStop(message)
		}
		else{
			String message = "Main Category: "+ProjectConstants.CURRENTVISITING_MAINCATEGORY+"\nProduct Category: "+ProjectConstants.CURRENTVISITING_PRODUCTCATEGORY+"\n"+ProjectConstants.MESSAGEFOR_DISPLAYEDPRODUCTSARE_EQUAL
			KeywordUtil.logInfo(message)
		}
	}
}
