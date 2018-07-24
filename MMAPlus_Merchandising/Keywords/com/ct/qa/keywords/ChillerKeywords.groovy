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

public class ChillerKeywords {
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
		}
		return channelproducts
	}
	@Keyword
	def visitChillerTaggedInChillerUtilization(){
		int totalchillers = ProjectConstants.DRIVER.findElementsByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ListView[1]/*").size()
		for(int i=1; i<=totalchillers; i++){
			MobileElement chiller = ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout["+i+"]/android.widget.TextView[1]")
			ProjectConstants.CURRENTVISITING_CHILLERTYPE = chiller.getText()
			ProjectConstants.CURRENTVISITING_CHILLERINDEX = i
			ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout["+i+"]").click()
			Mobile.verifyElementExist(findTestObject("Object Repository/CommonScreenElements/Validate_CameraScreen"), 0)
			Mobile.tap(findTestObject("Object Repository/CommonScreenElements/TakePictureButton"), 0)
			Mobile.delay(5)
			Mobile.tap(findTestObject("Object Repository/CommonScreenElements/DoneButton"),0)
			Mobile.callTestCase(findTestCase("Test Cases/ShopOpen/ChillerUtilization/VisitChillerRemarks"), null)
		}
	}
	@Keyword
	def findTaggedVisitingChiller(){
		ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout["+ProjectConstants.CURRENTVISITING_CHILLERINDEX+"]").click()
		Mobile.verifyElementExist(findTestObject("Object Repository/CommonScreenElements/Validate_InfoPopUP"), 0)
		Mobile.tap(findTestObject("Object Repository/CommonScreenElements/InfoPopUp_YesButton"), 0)
		Mobile.verifyElementExist(findTestObject("Object Repository/CommonScreenElements/Validate_CameraScreen"), 0)
		Mobile.tap(findTestObject("Object Repository/CommonScreenElements/TakePictureButton"), 0)
		Mobile.delay(5)
		Mobile.tap(findTestObject("Object Repository/CommonScreenElements/DoneButton"),0)
	}
	@Keyword
	def visitChillerRemarks(){
		int totalremarks = ProjectConstants.DRIVER.findElementsByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ListView[1]/*").size()
		for(int i=7; i<=totalremarks; i++){
			MobileElement remark = ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout["+i+"]/android.widget.TextView[1]")
			String remarkname = remark.getText()
			ProjectConstants.CURRENTVISITING_CHILLERREMARK = remarkname
			if(remarkname.equalsIgnoreCase("Chiller Available")){
				ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout["+i+"]").click()
				Mobile.callTestCase(findTestCase("Test Cases/ShopOpen/ChillerUtilization/ChillerAvailable/VisitChillerAvailable"), null)
			}
			else if(remarkname.equalsIgnoreCase("Chiller not Available")){
				ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout["+i+"]").click()
				Mobile.callTestCase(findTestCase("Test Cases/ShopOpen/ChillerUtilization/ChillerNotAvailable/VisitChillerNotAvailable"), null)
			}
			else if(remarkname.equalsIgnoreCase("Chiller need maintenance")){
				ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout["+i+"]").click()
				Mobile.callTestCase(findTestCase("Test Cases/ShopOpen/ChillerUtilization/ChillerNeedMaintenance/VisitChillerNeedMaintenance"), null)
			}
			else if(remarkname.equalsIgnoreCase("Chiller removed for maintenance")){
				ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout["+i+"]").click()
				Mobile.callTestCase(findTestCase("Test Cases/ShopOpen/ChillerUtilization/ChillerRemovedForMaintenance/VisitChillerRemovedForMaintenance"), null)
			}
			else if(remarkname.equalsIgnoreCase("Chiller not in access")){
				ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout["+i+"]").click()
				Mobile.callTestCase(findTestCase("Test Cases/ShopOpen/ChillerUtilization/ChillerNotInAccess/VisitChillerNotInAccess"), null)
			}
			else if(remarkname.equalsIgnoreCase("Shopkeeper did not allow")){
				ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout["+i+"]").click()
				Mobile.callTestCase(findTestCase("Test Cases/ShopOpen/ChillerUtilization/ShopKeeperDidNotAllow/VisitShopKeeperDidNotAllow"), null)
			}
			else if(remarkname.equalsIgnoreCase("Chiller Type not Available")){
				ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout["+i+"]").click()
				Mobile.callTestCase(findTestCase("Test Cases/ShopOpen/ChillerUtilization/ChillerTypeNotAvailable/VisitChillerTypeNotAvailable"), null)
			}
		}
	}
	@Keyword
	def visitChillerAvailableProductCategories(int flag){
		boolean status = ProjectConstants.compareChillerWiseProductsCategories()
		if(status == 1){
			String message = "Chiller Type: "+ProjectConstants.CURRENTVISITING_CHILLERTYPE+"\nChiller Remark: "+ProjectConstants.CURRENTVISITING_CHILLERREMARK+"\n"+ProjectConstants.MESSAGEFOR_DISPLAYEDPRODUCTSARE_GREATER
			KeywordUtil.markErrorAndStop(message)
		}
		else if(status == -1){
			String message = "Chiller Type: "+ProjectConstants.CURRENTVISITING_CHILLERTYPE+"\nChiller Remark: "+ProjectConstants.CURRENTVISITING_CHILLERREMARK+"\n"+ProjectConstants.MESSAGEFOR_DISPLAYEDPRODUCTSARE_LESS
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
			}
		}
	}
	@Keyword
	def visitChillerNotAvailableProductCategories(int flag){
		boolean status = ProjectConstants.compareChannelWiseProductsCategories()
		if(status == 1){
			String message = "Chiller Type: "+ProjectConstants.CURRENTVISITING_CHILLERTYPE+"\nChiller Remark: "+ProjectConstants.CURRENTVISITING_CHILLERREMARK+"\n"+ProjectConstants.MESSAGEFOR_DISPLAYEDPRODUCTSARE_GREATER
			KeywordUtil.markErrorAndStop(message)
		}
		else if(status == -1){
			String message = "Chiller Type: "+ProjectConstants.CURRENTVISITING_CHILLERTYPE+"\nChiller Remark: "+ProjectConstants.CURRENTVISITING_CHILLERREMARK+"\n"+ProjectConstants.MESSAGEFOR_DISPLAYEDPRODUCTSARE_LESS
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
			}
		}
		if(totalproducts == 16){
			while(true){
				MobileElement lastproductbeforeswipe = ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ScrollView[1]/android.widget.LinearLayout[1]/android.widget.TextView[5]")
				String lastproductnamebeforeswipe = lastproductbeforeswipe.getText()
				Mobile.swipe(0, 359, 0, 250)
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
			}
		}
		if(totalproducts == 16){
			while(true){
				MobileElement lastproductbeforeswipe = ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ScrollView[1]/android.widget.LinearLayout[1]/android.widget.TextView[5]")
				String lastproductnamebeforeswipe = lastproductbeforeswipe.getText()
				Mobile.swipe(0, 359, 0, 250)
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
		}
	}
	@Keyword
	def selectChillerType(){
		int totalchillertypes = ProjectConstants.DRIVER.findElementsByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.ListView[1]/*").size()
		for(int i=1; i<=totalchillertypes-1; i++){
			MobileElement chiller = ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout["+i+"]/android.widget.TextView[1]")
			ProjectConstants.CURRENTVISITING_CHILLERTYPE = chiller.getText()
			ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout["+i+"]").click()
			break
		}
	}
	@Keyword
	def continueChillerAvailableCategoryFlowForChillerNeedMaintenance(){
		Mobile.callTestCase(findTestCase("Test Cases/ShopOpen/ChillerUtilization/ChillerAvailable/VisitChillerAvailable"), null)
	}
	@Keyword
	def continueChillerNotAvailableCategoryFlowForChillerRemovedForMaintenance(){
		Mobile.callTestCase(findTestCase("Test Cases/ShopOpen/ChillerUtilization/ChillerNotAvailable/VisitChillerNotAvailable"), null)
	}
	@Keyword
	def continueChillerNotAvailableCategoryFlowForChillerNotInAccess(){
		Mobile.callTestCase(findTestCase("Test Cases/ShopOpen/ChillerUtilization/ChillerNotAvailable/VisitChillerNotAvailable"), null)
	}
	@Keyword
	def continueChillerAvailableCategoryFlowForChillerTypeNotAvailable(){
		Mobile.callTestCase(findTestCase("Test Cases/ShopOpen/ChillerUtilization/ChillerTypeNotAvailable/VisitChillerType"), null)
	}
}
