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
import com.ct.qa.struct.ProductsData
import io.appium.java_client.MobileElement

public class ChannelKeywords {

	//load channel products and quantity
	def loadChannelWiseProductsList(XSSFSheet sheet, int column){
		DataFormatter dataformatter = new DataFormatter()
		ArrayList<ProductsData> channelproducts = new ArrayList<ProductsData>()
		int totalrows = sheet.getLastRowNum()
		for(int i=1; i<=totalrows; i++){
			Row row = sheet.getRow(i)
			String channel = dataformatter.formatCellValue(row.getCell(ProjectConstants.channel))
			String maincategory = dataformatter.formatCellValue(row.getCell(ProjectConstants.channel_maincategory))
			String productcategory = dataformatter.formatCellValue(row.getCell(ProjectConstants.channel_productcategory))
			if((ProjectConstants.currentvisitingshopchannel.contains(channel) && ProjectConstants.currentvisitingmaincategory.equalsIgnoreCase(maincategory)) && ProjectConstants.currentvisitingproductcategory.equalsIgnoreCase(productcategory)){
				ProductsData channelproduct = new ProductsData()
				String product = dataformatter.formatCellValue(row.getCell(ProjectConstants.channel_product))
				String columndata = dataformatter.formatCellValue(row.getCell(column))
				channelproduct.setProduct(product)
				channelproduct.setProduct_data(columndata)
				channelproducts.add(channelproduct)
			}
		}
		return channelproducts
	}
	//visit chiller not allocated product categories in chiller
	@Keyword
	def visitChillerNotAllocatedProductCategories(int flag){
		ArrayList<MobileElement> productcategories = ProjectConstants.driver.findElementsByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.ListView[1]/*")
		for(int i=1; i<=productcategories.size(); i++){
			MobileElement productcategory = ProjectConstants.driver.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout["+i+"]/android.widget.TextView[1]")
			String selectedproductcategory = productcategory.getText()
			ProjectConstants.currentvisitingproductcategory = selectedproductcategory
			ProjectConstants.driver.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout["+i+"]").click()
			if(flag == 1){
				Mobile.callTestCase(findTestCase("Test Cases/ShopOpen/Chiller/VisitProductCategoryAssets"), null)
			}
			else if(flag == 2){
				Mobile.callTestCase(findTestCase("Test Cases/ShopOpen/Chiller/OverwriteProductCategoryAssets"), null)
			}
			productcategories = ProjectConstants.driver.findElementsByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.ListView[1]/*")
		}
	}
	//enter quantity to related field
	@Keyword
	def visitChannelWiseProductsData(int columnindex, String messageondisplayedproductsgreater, String messageondisplayedproductsless){
		int displayedproducts = 0
		int index = 0
		XSSFSheet channelproductssheet = ProjectConstants.loadChannelProductsSheet()
		ArrayList<ProductsData> channelproducts = loadChannelWiseProductsList(channelproductssheet, columnindex)
		int expectedproducts = channelproducts.size()
		ArrayList<MobileElement> products = ProjectConstants.driver.findElementsByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ScrollView[1]/android.widget.LinearLayout[1]/*")
		for(int i=1; i<products.size(); i=i+3){
			index = index + 1
			MobileElement selectedproduct = ProjectConstants.driver.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ScrollView[1]/android.widget.LinearLayout[1]/android.widget.TextView["+index+"]")
			String selectedproductname = selectedproduct.getText()
			if(i == products.size()-1){
				Mobile.swipe(0, 230, 0, 220)
			}
			for(int j=0; j<channelproducts.size(); j++){
				ProductsData channelproduct = channelproducts.get(j)
				String productname = channelproduct.getProduct()
				if(selectedproductname.equalsIgnoreCase(productname)){
					displayedproducts = displayedproducts + 1
					String productquantity = channelproduct.getProduct_data()
					MobileElement selectedproducttextfield = ProjectConstants.driver.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ScrollView[1]/android.widget.LinearLayout[1]/android.widget.LinearLayout["+index+"]/android.widget.EditText[1]")
					selectedproducttextfield.setValue(productquantity)
					Mobile.hideKeyboard()
				}
			}
		}
		if(products.size() == 16){
			while(true){
				MobileElement lastproductbeforeswipe = ProjectConstants.driver.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ScrollView[1]/android.widget.LinearLayout[1]/android.widget.TextView[5]")
				String lastproductnamebeforeswipe = lastproductbeforeswipe.getText()
				Mobile.swipe(0, 309, 0, 200)
				MobileElement lastproductafterswipe = ProjectConstants.driver.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ScrollView[1]/android.widget.LinearLayout[1]/android.widget.TextView[5]")
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
							MobileElement selectedproducttextfield = ProjectConstants.driver.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ScrollView[1]/android.widget.LinearLayout[1]/android.widget.LinearLayout[6]/android.widget.EditText[1]")
							selectedproducttextfield.setValue(productquantity)
							Mobile.hideKeyboard()
						}
					}
				}
			}
		}
		int result = displayedproducts.compareTo(expectedproducts)
		//if displayed products are greater than expected products
		if(result == 1)
		{
			KeywordUtil.markFailedAndStop(messageondisplayedproductsgreater)
		}
		//if displayed products are less than expected products
		else if(result == -1)
		{
			KeywordUtil.markFailed(messageondisplayedproductsless)
		}
		else{
			System.out.println("displayed products are equals to expected products...")
		}
	}
}
