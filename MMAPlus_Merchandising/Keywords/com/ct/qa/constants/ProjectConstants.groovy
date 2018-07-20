package com.ct.qa.constants

import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.checkpoint.Checkpoint
import com.kms.katalon.core.checkpoint.CheckpointFactory
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords
import com.kms.katalon.core.mobile.keyword.internal.MobileDriverFactory
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testcase.TestCase
import com.kms.katalon.core.testcase.TestCaseFactory
import com.kms.katalon.core.testdata.TestData
import com.kms.katalon.core.testdata.TestDataFactory
import com.kms.katalon.core.testobject.ObjectRepository
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords

import internal.GlobalVariable
import io.appium.java_client.AppiumDriver
import io.appium.java_client.MobileElement
import org.apache.poi.ss.usermodel.DataFormatter
import org.apache.poi.ss.usermodel.Row
import org.apache.poi.xssf.usermodel.XSSFSheet
import org.apache.poi.xssf.usermodel.XSSFWorkbook
import org.openqa.selenium.remote.server.handler.AcceptAlert

import MobileBuiltInKeywords as Mobile
import WSBuiltInKeywords as WS
import WebUiBuiltInKeywords as WebUI

public class ProjectConstants {
	//variables for excel file and sheets
	public static final String EXCEL_FILEPATH = "F:\\Git Projects\\MMAPlus_Merchandising\\MMAPlus_Merchandising\\MMAPlus_Merchandising.xlsx"
	public static final String CHANNEL_PRODUCTSSHEET = "Channel Products"
	public static final String CHILLER_PRODUCTSSHEET = "Chiller Products"
	public static final String DISTRIBUTION_SHEET = "Distribution Point"
	public static final AppiumDriver<MobileElement> DRIVER = MobileDriverFactory.getDriver()
	//variables for display messages
	//channel wise products
	
	public static final String MESSAGEFOR_CHILLERNOTALLOCATED_FACING_DISPLAYEDPRODUCTSARE_GREATER = "displayed products in CHILLER NOT ALLOCATED WITH "+CURRENTVISITING_PRODUCTCATEGORY+" FACING are greater than to expected products..."
	public static final String MESSAGEFOR_CHILLERNOTALLOCATED_FACING_DISPLAYEDPRODUCTSARE_LESS = "displayed products in CHILLER NOT ALLOCATED WITH "+CURRENTVISITING_PRODUCTCATEGORY+" FACING are less than to expected products..."
	public static final String MESSAGEFOR_CHILLERNOTALLOCATED_STOCKTAKING_DISPLAYEDPRODUCTSARE__GREATER = "displayed products in CHILLER NOT ALLOCATED WITH "+CURRENTVISITING_PRODUCTCATEGORY+" STOCK TAKING are greater than to expected products..."
	public static final String MESSAGEFOR_CHILLERNOTALLOCATED_STOCKTAKING_DISPLAYEDPRODUCTSARE_LESS = "displayed products in CHILLER NOT ALLOCATED WITH "+CURRENTVISITING_PRODUCTCATEGORY+" STOCK TAKING are less than to expected products..."
	
	public static final String MESSAGEFOR_DISPLAYSPACEAVAILABLE_FACING_DISPLAYEDPRODUCTSARE_GREATER = "displayed products in DISPLAY SPACE AVAILABLE FACING are greater than to expected products..."
	public static final String MESSAGEFOR_DISPLAYSPACEAVAILABLE_FACING_DISPLAYEDPRODUCTSARE_LESS = "displayed products in DISPLAY SPACE AVAILABLE FACING are less than to expected products..."
	public static final String MESSAGEFOR_DISPLAYSPACEAVAILABLE_STOCKTAKING_DISPLAYEDPRODUCTSARE_GREATER = "displayed products in DISPLAY SPACE AVAILABLE STOCK TAKING are greater than to expected products..."
	public static final String MESSAGEFOR_DISPLAYSPACEAVAILABLE_STOCKTAKING_DISPLAYEDPRODUCTSARE_LESS = "displayed products in DISPLAY SPACE AVAILABLE STOCK TAKING are less than to expected products..."
	
	public static final String MESSAGEFOR_NOSPACEFORDISPLAY_FACING_DISPLAYEDPRODUCTSARE__GREATER = "displayed products in NO SPACE FOR DISPLAY FACING are greater than to expected products..."
	public static final String MESSAGEFOR_NOSPACEFORDISPLAY_FACING_DISPLAYEDPRODUCTSARE_LESS = "displayed products in NO SPACE FOR DISPLAY FACING are less than to expected products..."
	public static final String MESSAGEFOR_NOSPACEFORDISPLAY_STOCKTAKING_DISPLAYEDPRODUCTSARE_GREATER = "displayed products in NO SPACE FOR DISPLAY STOCK TAKING are greater than to expected products..."
	public static final String MESSAGEFOR_NOSPACEFORDISPLAY_STOCKTAKING_DISPLAYEDPRODUCTSARE_LESS = "displayed products in NO SPACE FOR DISPLAY STOCK TAKING are less than to expected products..."


	//chiller available products
	public static final String MESSAGEFOR_CHILLERAVAILABLE_FACING_DISPLAYEDPRODUCTSARE_GREATER = "displayed products in CHILLER AVAILABLE WITH "+CURRENTVISITING_PRODUCTCATEGORY+" FACING are greater than to expected products..."
	public static final String MESSAGEFOR_CHILLERAVAILABLE_FACING_DISPLAYEDPRODUCTSARE_LESS = "displayed products in CHILLER AVAILABLE WITH "+CURRENTVISITING_PRODUCTCATEGORY+" FACING are less than to expected products..."
	public static final String MESSAGEFOR_CHILLERAVAILABLE_DEPTH_DISPLAYEDPRODUCTSARE_GREATER = "displayed products in CHILLER AVAILABLE WITH "+CURRENTVISITING_PRODUCTCATEGORY+" DEPTH are greater than to expected products..."
	public static final String MESSAGEFOR_CHILLERAVAILABLE_DEPTH_DISPLAYEDPRODUCTSARE_LESS = "displayed products in CHILLER AVAILABLE WITH "+CURRENTVISITING_PRODUCTCATEGORY+" DEPTH are less than to expected products..."
	public static final String MESSAGEFOR_CHILLERAVAILABLE_STOCKCOUNT_DISPLAYEDPRODUCTSARE_GREATER = "displayed products in CHILLER AVAILABLE WITH "+CURRENTVISITING_PRODUCTCATEGORY+" STOCK TAKING are greater than to expected products..."
	public static final String MESSAGEFOR_CHILLERAVAILABLE_STOCKCOUNT_DISPLAYEDPRODUCTSARE_LESS = "displayed products in CHILLER AVAILABLE WITH "+CURRENTVISITING_PRODUCTCATEGORY+" STOCK TAKING are less than to expected products..."

	//chiller not available products
	public static final String MESSAGEFOR_CHILLERNOTAVAILABLE_FACING_DISPLAYEDPRODUCTSARE_GREATER = "displayed products in CHILLER NOT AVAILABLE WITH "+CURRENTVISITING_PRODUCTCATEGORY+" FACING are greater than to expected products..."
	public static final String MESSAGEFOR_CHILLERNOTAVAILABLE_FACING_DISPLAYEDPRODUCTSARE_LESS = "displayed products in CHILLER NOT AVAILABLE WITH "+CURRENTVISITING_PRODUCTCATEGORY+" FACING are less than to expected products..."
	public static final String MESSAGEFOR_CHILLERNOTAVAILABLE_STOCKTAKING_DISPLAYEDPRODUCTSARE_GREATER = "displayed products in CHILLER NOT AVAILABLE WITH "+CURRENTVISITING_PRODUCTCATEGORY+" STOCK TAKING are greater than to expected products..."
	public static final String MESSAGEFOR_CHILLERNOTAVAILABLE_STOCKTAKING_DISPLAYEDPRODUCTSARE_LESS = "displayed products in CHILLER NOT AVAILABLE WITH "+CURRENTVISITING_PRODUCTCATEGORY+" STOCK TAKING are less than to expected products..."

	//products categories comparison messages
	public static final String MESSAGEFOR_CHILLERNOTALLOCATED_DISPLAYEDPRODUCTSCATEGORIESARE_GREATER = "displayed products categories in CHILLER NOT ALLOCATED are greater than to expected products..."
	public static final String MESSAGEFOR_CHILLERNOTALLOCATED_DISPLAYEDPRODUCTSCATEGORIESARE_LESS = "displayed products categories in CHILLER NOT ALLOCATED are less than to expected products..."
	public static final String MESSAGEFOR_CHILLERUTILIZATION_DISPLAYEDPRODUCTSCATEGORIESARE_GREATER = "displayed products categories in "+CURRENTVISITING_CHILLERREMARK+" are greater than to expected products..."
	public static final String MESSAGEFOR_CHILLERUTILIZATION_DISPLAYEDPRODUCTSCATEGORIESARE_LESS = "displayed products categories in "+CURRENTVISITING_CHILLERREMARK+" are less than to expected products..."
	

	//variables for excel sheet columns index
	//channel wise product categories product columns
	public static final int CHANNEL
	public static final int CHANNEL_MAINCATEGORY
	public static final int CHANNEL_PRODUCTCATEGORY
	public static final int CHANNEL_PRODUCT
	public static final int CHANNEL_DSA_FACING
	public static final int CHANNEL_DSA_STOCKTAKING
	public static final int CHANNEL_NSFD_FACING
	public static final int CHANNEL_NSFD_STOCKTAKING
	public static final int CHANNEL_DSA_OVERWRITEFACING
	public static final int CHANNEL_DSA_OVERWRITESTOCKTAKING
	public static final int CHANNEL_NSFD_OVERWRITEFACING
	public static final int CHANNEL_NSFD_OVERWRITESTOCKTAKING
	public static final int CHANNEL_CHILLER_FACING
	public static final int CHANNEL_CHILLER_STOCKTAKING
	public static final int CHANNEL_CHILLER_OVERWRITEFACING
	public static final int CHANNEL_CHILLER_OVERWRITESTOCKTAKING

	//chiller available columns
	public static final int CHILLER_TYPE
	public static final int CHILLER_PRODUCTCATEGORY
	public static final int CHILLER_PRODUCT
	public static final int CHILLER_FACING
	public static final int CHILLER_DEPTH
	public static final int CHILLER_STOCKCOUNT
	public static final int CHILLER_OVERWRITEFACING
	public static final int CHILLER_OVERWRITEDEPTH
	public static final int CHILLER_OVERWRITESTOCKCOUNT

	//chiller not available columns
	public static final int CHANNEL_CNA_FACING
	public static final int CHANNEL_CNA_STOCKTAKING
	public static final int CHANNEL_CNA_OVERWRITEFACING
	public static final int CHANNEL_CNA_OVERWRITESTOCKTAKING

	//variables for current visiting shop channels, chiller and categories
	public static String CURRENTVISITING_SHOPNAME
	public static String CURRENTVISITING_SHOPCHANNEL = ""
	public static String CURRENTVISITING_MAINCATEGORY = ""
	public static String CURRENTVISITING_PRODUCTCATEGORY = ""
	public static String CURRENTVISITING_CHILLERTYPE = ""
	public static String CURRENTVISITING_CHILLERREMARK = ""
	public static int CURRENTVISITING_CHILLERINDEX = 0

	//initialization of sheet columns index
	static{
		XSSFSheet channelproductssheet = loadChannelProductsSheet()
		XSSFSheet chillerproductssheet = loadChillerProductsSheet()
		Row chillerproductssheetheaderrow = chillerproductssheet.getRow(0)
		Row channelproductssheetheaderrow = channelproductssheet.getRow(0)
		int channelproductssheettotalcolumns = channelproductssheetheaderrow.getLastCellNum()
		int chillerproductssheettotalcolumns = chillerproductssheetheaderrow.getLastCellNum()
		for(int cellnumber=0; cellnumber<channelproductssheettotalcolumns; cellnumber++){
			String columnname = channelproductssheetheaderrow.getCell(cellnumber)
			if(columnname.equalsIgnoreCase("Channel")){
				CHANNEL = cellnumber
			}
			else if(columnname.equalsIgnoreCase("Main Category")){
				CHANNEL_MAINCATEGORY = cellnumber
			}
			else if(columnname.equalsIgnoreCase("Product Category")){
				CHANNEL_PRODUCTCATEGORY = cellnumber
			}
			else if(columnname.equalsIgnoreCase("Product")){
				CHANNEL_PRODUCT = cellnumber
			}
			else if(columnname.equalsIgnoreCase("Facing For DSA")){
				CHANNEL_DSA_FACING = cellnumber
			}
			else if(columnname.equalsIgnoreCase("Stock Taking For DSA")){
				CHANNEL_DSA_STOCKTAKING = cellnumber
			}
			else if(columnname.equalsIgnoreCase("Facing For NSFD")){
				CHANNEL_NSFD_FACING = cellnumber
			}
			else if(columnname.equalsIgnoreCase("Stock Taking For NSFD")){
				CHANNEL_NSFD_STOCKTAKING = cellnumber
			}
			else if(columnname.equalsIgnoreCase("Overwrite Facing For DSA")){
				CHANNEL_DSA_OVERWRITEFACING = cellnumber
			}
			else if(columnname.equalsIgnoreCase("Overwrite Stock Taking For DSA")){
				CHANNEL_DSA_OVERWRITESTOCKTAKING = cellnumber
			}
			else if(columnname.equalsIgnoreCase("Overwrite Facing For NSFD")){
				CHANNEL_NSFD_OVERWRITEFACING = cellnumber
			}
			else if(columnname.equalsIgnoreCase("Overwrite Stock Taking For NSFD")){
				CHANNEL_NSFD_OVERWRITESTOCKTAKING = cellnumber
			}
			else if(columnname.equalsIgnoreCase("Facing For Chiller")){
				CHANNEL_CHILLER_FACING = cellnumber
			}
			else if(columnname.equalsIgnoreCase("Stock Taking For Chiller")){
				CHANNEL_CHILLER_STOCKTAKING = cellnumber
			}
			else if(columnname.equalsIgnoreCase("Overwrite Facing For Chiller")){
				CHANNEL_CHILLER_OVERWRITEFACING = cellnumber
			}
			else if(columnname.equalsIgnoreCase("Overwrite Stock Taking For Chiller")){
				CHANNEL_CHILLER_OVERWRITESTOCKTAKING = cellnumber
			}
			else if(columnname.equalsIgnoreCase("Facing For Chiller Not Available")){
				CHANNEL_CNA_FACING = cellnumber
			}
			else if(columnname.equalsIgnoreCase("Stock Taking For Chiller Not Available")){
				CHANNEL_CNA_STOCKTAKING = cellnumber
			}
			else if(columnname.equalsIgnoreCase("Overwrite Facing For Chiller Not Available")){
				CHANNEL_CNA_OVERWRITEFACING = cellnumber
			}
			else if(columnname.equalsIgnoreCase("Overwrite Stock Taking For Chiller Not Available")){
				CHANNEL_CNA_OVERWRITESTOCKTAKING = cellnumber
			}
		}
		for(int cellnumber=0; cellnumber<chillerproductssheettotalcolumns; cellnumber++){
			String columnname = chillerproductssheetheaderrow.getCell(cellnumber)
			if(columnname.equalsIgnoreCase("Chiller Type")){
				CHILLER_TYPE = cellnumber
			}
			if(columnname.equalsIgnoreCase("Category")){
				CHILLER_PRODUCTCATEGORY = cellnumber
			}
			else if(columnname.equalsIgnoreCase("Product")){
				CHILLER_PRODUCT = cellnumber
			}
			else if(columnname.equalsIgnoreCase("Facing ")){
				CHILLER_FACING = cellnumber
			}
			else if(columnname.equalsIgnoreCase("Depth")){
				CHILLER_DEPTH = cellnumber
			}
			else if(columnname.equalsIgnoreCase("Stock Count")){
				CHILLER_STOCKCOUNT = cellnumber
			}
			else if(columnname.equalsIgnoreCase("Overwrite Facing ")){
				CHILLER_OVERWRITEFACING = cellnumber
			}
			else if(columnname.equalsIgnoreCase("Overwrite Depth")){
				CHILLER_OVERWRITEDEPTH = cellnumber
			}
			else if(columnname.equalsIgnoreCase("Overwrite Stock Count")){
				CHILLER_OVERWRITESTOCKCOUNT = cellnumber
			}
		}
	}

	//load channel wise products sheet
	def static loadChannelProductsSheet(){
		try{
			FileInputStream inputStream = new FileInputStream(new File(EXCEL_FILEPATH))
			XSSFWorkbook wb = new XSSFWorkbook(inputStream)
			XSSFSheet sheet = wb.getSheet(CHANNEL_PRODUCTSSHEET)
			return sheet
		}
		catch(Exception ex){
		}
	}
	//load chiller wise products sheet
	def static loadChillerProductsSheet(){
		try{
			FileInputStream inputStream = new FileInputStream(new File(EXCEL_FILEPATH))
			XSSFWorkbook wb = new XSSFWorkbook(inputStream)
			XSSFSheet sheet = wb.getSheet(CHILLER_PRODUCTSSHEET)
			return sheet
		}
		catch(Exception ex){
		}
	}
	//load distribution point sheet
	def static loadDistributionPointSheet(){
		try{
			FileInputStream inputStream = new FileInputStream(new File(EXCEL_FILEPATH))
			XSSFWorkbook wb = new XSSFWorkbook(inputStream)
			XSSFSheet sheet = wb.getSheet(DISTRIBUTION_SHEET)
			return sheet
		}
		catch(Exception ex){
		}
	}	
	def static compareChannelWiseProductsCategories(){
		DataFormatter dataformatter = new DataFormatter()
		XSSFSheet sheet = loadChannelProductsSheet()
		ArrayList<String> expectedproductscategorieslist = new ArrayList<String>()
		ArrayList<String> displayedproductscategorieslist = new ArrayList<String>()
		int totalrows = sheet.getLastRowNum()
		for(int i=1; i<=totalrows; i++){
			Row row = sheet.getRow(i)
			String channel = dataformatter.formatCellValue(row.getCell(CHANNEL))
			String maincategory = dataformatter.formatCellValue(row.getCell(CHANNEL_MAINCATEGORY))
			if(CURRENTVISITING_SHOPCHANNEL.contains(channel) && maincategory.equalsIgnoreCase("Chiller")){
				String productcategory = dataformatter.formatCellValue(row.getCell(CHANNEL_PRODUCTCATEGORY))
				expectedproductscategorieslist.add(productcategory)
			}
		}
		int totalproductscategories = DRIVER.findElementsByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.ListView[1]/*").size()
		for(int i=1; i<=totalproductscategories; i++){
			MobileElement productcategory = DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout["+i+"]/android.widget.TextView[1]")
			displayedproductscategorieslist.add(productcategory.getText())
		}
		Set<String> expectedproductscategories = new HashSet<String>(expectedproductscategorieslist)
		Set<String> displayedproductscategories = new HashSet<String>(displayedproductscategorieslist)
		if(!expectedproductscategories.containsAll(displayedproductscategories)){
			return 1
		}
		else if(!displayedproductscategories.containsAll(expectedproductscategories)){
			return -1
		}
		else{
			return 0
		}
	}
	def static compareChillerWiseProductsCategories(){
		DataFormatter dataformatter = new DataFormatter()
		XSSFSheet sheet = loadChillerProductsSheet()
		ArrayList<String> expectedproductscategorieslist = new ArrayList<String>()
		ArrayList<String> displayedproductscategorieslist = new ArrayList<String>()
		int totalrows = sheet.getLastRowNum()
		for(int i=1; i<=totalrows; i++){
			Row row = sheet.getRow(i)
			String chiller = dataformatter.formatCellValue(row.getCell(CHILLER_TYPE))
			if(CURRENTVISITING_CHILLERTYPE.equalsIgnoreCase(chiller)){
				String productcategory = dataformatter.formatCellValue(row.getCell(CHILLER_PRODUCTCATEGORY))
				expectedproductscategorieslist.add(productcategory)
			}
		}
		int totalproductscategories = DRIVER.findElementsByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.ListView[1]/*").size()
		for(int i=1; i<=totalproductscategories; i++){
			MobileElement productcategory = DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout["+i+"]/android.widget.TextView[1]")
			displayedproductscategorieslist.add(productcategory.getText())
		}
		Set<String> expectedproductscategories = new HashSet<String>(expectedproductscategorieslist)
		Set<String> displayedproductscategories = new HashSet<String>(displayedproductscategorieslist)
		if(!expectedproductscategories.containsAll(displayedproductscategories)){
			return 1
		}
		else if(!displayedproductscategories.containsAll(expectedproductscategories)){
			return -1
		}
		else{
			return 0
		}
	}
}
