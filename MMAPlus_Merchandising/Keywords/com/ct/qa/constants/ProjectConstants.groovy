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
import org.apache.poi.ss.usermodel.Row
import org.apache.poi.xssf.usermodel.XSSFSheet
import org.apache.poi.xssf.usermodel.XSSFWorkbook
import org.openqa.selenium.remote.server.handler.AcceptAlert

import MobileBuiltInKeywords as Mobile
import WSBuiltInKeywords as WS
import WebUiBuiltInKeywords as WebUI

public class ProjectConstants {
	//variables for excel file and sheets
	public static final String excelFilePath = "F:\\Git Projects\\MMAPlus_Merchandising\\MMAPlus_Merchandising\\MMAPlus_Merchandising.xlsx"
	public static final String channelproductssheet = "Channel Products"
	public static final String chillerproductssheet = "Chiller Products"
	public static final String DistributionPointSheet = "Distribution Point"
	public static final AppiumDriver<MobileElement> driver = MobileDriverFactory.getDriver()
	//variables for display messages
	//channel wise products
	public static final String visitchillernotallocatedfacing_displayedproductsaregreater = "displayed products in VISIT CHILLER NOT ALLOCATED WITH "+ProjectConstants.currentvisitingproductcategory+" FACING are greater than to expected products..."
	public static final String visitchillernotallocatedfacing_displayedproductsareless = "displayed products in VISIT CHILLER NOT ALLOCATED WITH "+ProjectConstants.currentvisitingproductcategory+" FACING are less than to expected products..."
	public static final String visitchillernotallocatedstocktaking_displayedproductsaregreater = "displayed products in VISIT CHILLER NOT ALLOCATED WITH "+ProjectConstants.currentvisitingproductcategory+" STOCK TAKING are greater than to expected products..."
	public static final String visitchillernotallocatedstocktaking_displayedproductsareless = "displayed products in VISIT CHILLER NOT ALLOCATED WITH "+ProjectConstants.currentvisitingproductcategory+" STOCK TAKING are less than to expected products..."
	public static final String visitdisplayspaceavailablefacing_displayedproductsaregreater = "displayed products in VISIT DISPLAY SPACE AVAILABLE FACING are greater than to expected products..."
	public static final String visitdisplayspaceavailablefacing_displayedproductsareless = "displayed products in VISIT DISPLAY SPACE AVAILABLE FACING are less than to expected products..."
	public static final String visitdisplayspaceavailablestocktaking_displayedproductsaregreater = "displayed products in VISIT DISPLAY SPACE AVAILABLE STOCK TAKING are greater than to expected products..."
	public static final String visitdisplayspaceavailablestocktaking_displayedproductsareless = "displayed products in VISIT DISPLAY SPACE AVAILABLE STOCK TAKING are less than to expected products..."
	public static final String visitnospacefordisplayfacing_displayedproductsaregreater = "displayed products in VISIT NO SPACE FOR DISPLAY FACING are greater than to expected products..."
	public static final String visitnospacefordisplayfacing_displayedproductsareless = "displayed products in VISIT NO SPACE FOR DISPLAY FACING are less than to expected products..."
	public static final String visitnospacefordisplaystocktaking_displayedproductsaregreater = "displayed products in VISIT NO SPACE FOR DISPLAY STOCK TAKING are greater than to expected products..."
	public static final String visitnospacefordisplaystocktaking_displayedproductsareless = "displayed products in VISIT NO SPACE FOR DISPLAY STOCK TAKING are less than to expected products..."

	public static final String overwritechillernotallocatedfacing_displayedproductsaregreater = "displayed products in OVERWRITE CHILLER NOT ALLOCATED with "+ProjectConstants.currentvisitingproductcategory+" FACING are greater than to expected products..."
	public static final String overwritechillernotallocatedfacing_displayedproductsareless = "displayed products in OVERWRITE CHILLER NOT ALLOCATED with "+ProjectConstants.currentvisitingproductcategory+" FACING are less than to expected products..."
	public static final String overwritechillernotallocatedstocktaking_displayedproductsaregreater = "displayed products in OVERWRITE CHILLER NOT ALLOCATED with "+ProjectConstants.currentvisitingproductcategory+" STOCK TAKING are greater than to expected products..."
	public static final String overwritechillernotallocatedstocktaking_displayedproductsareless = "displayed products in OVERWRITE CHILLER NOT ALLOCATED with "+ProjectConstants.currentvisitingproductcategory+" STOCK TAKING are less than to expected products..."
	public static final String overwritedisplayspaceavailablefacing_displayedproductsaregreater = "displayed products in OVERWRITE DISPLAY SPACE AVAILABLE FACING are greater than to expected products..."
	public static final String overwritedisplayspaceavailablefacing_displayedproductsareless = "displayed products in OVERWRITE DISPLAY SPACE AVAILABLE FACING are less than to expected products..."
	public static final String overwritedisplayspaceavailablestocktaking_displayedproductsaregreater = "displayed products in OVERWRITE DISPLAY SPACE AVAILABLE STOCK TAKING are greater than to expected products..."
	public static final String overwritedisplayspaceavailablestocktaking_displayedproductsareless = "displayed products in OVERWRITE DISPLAY SPACE AVAILABLE STOCK TAKING are less than to expected products..."
	public static final String overwritenospacefordisplayfacing_displayedproductsaregreater = "displayed products in OVERWRITE NO SPACE FOR DISPLAY FACING are greater than to expected products..."
	public static final String overwritenospacefordisplayfacing_displayedproductsareless = "displayed products in OVERWRITE NO SPACE FOR DISPLAY FACING are less than to expected products..."
	public static final String overwritenospacefordisplaystocktaking_displayedproductsaregreater = "displayed products in OVERWRITE NO SPACE FOR DISPLAY STOCK TAKING are greater than to expected products..."
	public static final String overwritenospacefordisplaystocktaking_displayedproductsareless = "displayed products in OVERWRITE NO SPACE FOR DISPLAY STOCK TAKING are less than to expected products..."

	//chiller available products
	public static final String visitchilleravailablefacing_displayedproductsaregreater = "displayed products in VISIT CHILLER AVAILABLE WITH "+ProjectConstants.currentvisitingproductcategory+" FACING are greater than to expected products..."
	public static final String visitchilleravailablefacing_displayedproductsareless = "displayed products in VISIT CHILLER AVAILABLE WITH "+ProjectConstants.currentvisitingproductcategory+" FACING are less than to expected products..."
	public static final String visitchilleravailabledepth_displayedproductsaregreater = "displayed products in VISIT CHILLER AVAILABLE WITH "+ProjectConstants.currentvisitingproductcategory+" DEPTH are greater than to expected products..."
	public static final String visitchilleravailabledepth_displayedproductsareless = "displayed products in VISIT CHILLER AVAILABLE WITH "+ProjectConstants.currentvisitingproductcategory+" DEPTH are less than to expected products..."
	public static final String visitchilleravailablestockcount_displayedproductsaregreater = "displayed products in VISIT CHILLER AVAILABLE WITH "+ProjectConstants.currentvisitingproductcategory+" STOCK TAKING are greater than to expected products..."
	public static final String visitchilleravailablestockcount_displayedproductsareless = "displayed products in VISIT CHILLER AVAILABLE WITH "+ProjectConstants.currentvisitingproductcategory+" STOCK TAKING are less than to expected products..."

	public static final String overwritechilleravailablefacing_displayedproductsaregreater = "displayed products in OVERWRITING CHILLER AVAILABLE WITH "+ProjectConstants.currentvisitingproductcategory+" FACING are greater than to expected products..."
	public static final String overwritechilleravailablefacing_displayedproductsareless = "displayed products in OVERWRITING CHILLER AVAILABLE WITH "+ProjectConstants.currentvisitingproductcategory+" FACING are less than to expected products..."
	public static final String overwritechilleravailabledepth_displayedproductsaregreater = "displayed products in OVERWRITING CHILLER AVAILABLE WITH "+ProjectConstants.currentvisitingproductcategory+" DEPTH are greater than to expected products..."
	public static final String overwritechilleravailabledepth_displayedproductsareless = "displayed products in OVERWRITING CHILLER AVAILABLE WITH "+ProjectConstants.currentvisitingproductcategory+" DEPTH are less than to expected products..."
	public static final String overwritechilleravailablestockcount_displayedproductsaregreater = "displayed products in OVERWRITING CHILLER AVAILABLE WITH "+ProjectConstants.currentvisitingproductcategory+" STOCK TAKING are greater than to expected products..."
	public static final String overwritechilleravailablestockcount_displayedproductsareless = "displayed products in OVERWRITING CHILLER AVAILABLE WITH "+ProjectConstants.currentvisitingproductcategory+" STOCK TAKING are less than to expected products..."

	//chiller not available products
	public static final String visitchillernotavailablefacing_displayedproductsaregreater = "displayed products in VISIT CHILLER NOT AVAILABLE WITH "+ProjectConstants.currentvisitingproductcategory+" FACING are greater than to expected products..."
	public static final String visitchillernotavailablefacing_displayedproductsareless = "displayed products in VISIT CHILLER NOT AVAILABLE WITH "+ProjectConstants.currentvisitingproductcategory+" FACING are less than to expected products..."
	public static final String visitchillernotavailablestocktaking_displayedproductsaregreater = "displayed products in VISIT CHILLER NOT AVAILABLE WITH "+ProjectConstants.currentvisitingproductcategory+" STOCK TAKING are greater than to expected products..."
	public static final String visitchillernotavailablestocktaking_displayedproductsareless = "displayed products in VISIT CHILLER NOT AVAILABLE WITH "+ProjectConstants.currentvisitingproductcategory+" STOCK TAKING are less than to expected products..."

	public static final String overwritechillernotavailablefacing_displayedproductsaregreater = "displayed products in OVERWRITE CHILLER NOT AVAILABLE WITH "+ProjectConstants.currentvisitingproductcategory+" FACING are greater than to expected products..."
	public static final String overwritechillernotavailablefacing_displayedproductsareless = "displayed products in OVERWRITE CHILLER NOT AVAILABLE WITH "+ProjectConstants.currentvisitingproductcategory+" FACING are less than to expected products..."
	public static final String overwritechillernotavailablestocktaking_displayedproductsaregreater = "displayed products in OVERWRITE CHILLER NOT AVAILABLE WITH "+ProjectConstants.currentvisitingproductcategory+" STOCK TAKING are greater than to expected products..."
	public static final String overwritechillernotavailablestocktaking_displayedproductsareless = "displayed products in OVERWRITE CHILLER NOT AVAILABLE WITH "+ProjectConstants.currentvisitingproductcategory+" STOCK TAKING are less than to expected products..."

	//variables for excel sheet columns index
	//channel wise product categories product columns
	public static final int channel
	public static final int channel_maincategory
	public static final int channel_productcategory
	public static final int channel_product
	public static final int channel_dsa_facing
	public static final int channel_dsa_stocktaking
	public static final int channel_nsfd_facing
	public static final int channel_nsfd_stocktaking
	public static final int channel_dsa_overwritefacing
	public static final int channel_dsa_overwritestocktaking
	public static final int channel_nsfd_overwritefacing
	public static final int channel_nsfd_overwritestocktaking
	public static final int channel_chiller_facing
	public static final int channel_chiller_stocktaking
	public static final int channel_chiller_overwritefacing
	public static final int channel_chiller_overwritestocktaking

	//chiller available columns
	public static final int chiller_type
	public static final int chiller_productcategory
	public static final int chiller_product
	public static final int chiller_facing
	public static final int chiller_depth
	public static final int chiller_stockcount
	public static final int chiller_overwritefacing
	public static final int chiller_overwritedepth
	public static final int chiller_overwritestockcount

	//chiller not available columns
	public static final int channel_cna_facing
	public static final int channel_cna_stocktaking
	public static final int channel_cna_overwritefacing
	public static final int channel_cna_overwritestocktaking

	//variables for current visiting shop channels, chiller and categories
	public static String currentvisitingshopname = ""
	public static String currentvisitingshopchannel = ""
	public static String currentvisitingmaincategory = ""
	public static String currentvisitingproductcategory = ""
	public static String currentvisitingchillertype = ""
	public static int currentvisitingchillerindex = 0

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
			if(columnname.equals("Channel")){
				channel = cellnumber
			}
			else if(columnname.equals("Main Category")){
				channel_maincategory = cellnumber
			}
			else if(columnname.equals("Product Category")){
				channel_productcategory = cellnumber
			}
			else if(columnname.equals("Product")){
				channel_product = cellnumber
			}
			else if(columnname.equals("Facing For DSA")){
				channel_dsa_facing = cellnumber
			}
			else if(columnname.equals("Stock Taking For DSA")){
				channel_dsa_stocktaking = cellnumber
			}
			else if(columnname.equals("Facing For NSFD")){
				channel_nsfd_facing = cellnumber
			}
			else if(columnname.equals("Stock Taking For NSFD")){
				channel_nsfd_stocktaking = cellnumber
			}
			else if(columnname.equals("Overwrite Facing For DSA")){
				channel_dsa_overwritefacing = cellnumber
			}
			else if(columnname.equals("Overwrite Stock Taking For DSA")){
				channel_dsa_overwritestocktaking = cellnumber
			}
			else if(columnname.equals("Overwrite Facing For NSFD")){
				channel_nsfd_overwritefacing = cellnumber
			}
			else if(columnname.equals("Overwrite Stock Taking For NSFD")){
				channel_nsfd_overwritestocktaking = cellnumber
			}
			else if(columnname.equals("Facing For Chiller")){
				channel_chiller_facing = cellnumber
			}
			else if(columnname.equals("Stock Taking For Chiller")){
				channel_chiller_stocktaking = cellnumber
			}
			else if(columnname.equals("Overwrite Facing For Chiller")){
				channel_chiller_overwritefacing = cellnumber
			}
			else if(columnname.equals("Overwrite Stock Taking For Chiller")){
				channel_chiller_overwritestocktaking = cellnumber
			}
			else if(columnname.equals("Facing For Chiller Not Available")){
				channel_cna_facing = cellnumber
			}
			else if(columnname.equals("Stock Taking For Chiller Not Available")){
				channel_cna_stocktaking = cellnumber
			}
			else if(columnname.equals("Overwrite Facing For Chiller Not Available")){
				channel_cna_overwritefacing = cellnumber
			}
			else if(columnname.equals("Overwrite Stock Taking For Chiller Not Available")){
				channel_cna_overwritestocktaking = cellnumber
			}
		}
		for(int cellnumber=0; cellnumber<chillerproductssheettotalcolumns; cellnumber++){
			String columnname = chillerproductssheetheaderrow.getCell(cellnumber)
			if(columnname.equals("Chiller Type")){
				chiller_type = cellnumber
			}
			if(columnname.equals("Category")){
				chiller_productcategory = cellnumber
			}
			else if(columnname.equals("Product")){
				chiller_product = cellnumber
			}
			else if(columnname.equals("Facing ")){
				chiller_facing = cellnumber
			}
			else if(columnname.equals("Depth")){
				chiller_depth = cellnumber
			}
			else if(columnname.equals("Stock Count")){
				chiller_stockcount = cellnumber
			}
			else if(columnname.equals("Overwrite Facing ")){
				chiller_overwritefacing = cellnumber
			}
			else if(columnname.equals("Overwrite Depth")){
				chiller_overwritedepth = cellnumber
			}
			else if(columnname.equals("Overwrite Stock Count")){
				chiller_overwritestockcount = cellnumber
			}
		}
	}

	//load channel wise products sheet
	def static loadChannelProductsSheet(){
		try{
			FileInputStream inputStream = new FileInputStream(new File(ProjectConstants.excelFilePath))
			XSSFWorkbook wb = new XSSFWorkbook(inputStream)
			XSSFSheet sheet = wb.getSheet(ProjectConstants.channelproductssheet)
			return sheet
		}
		catch(Exception ex){
		}
	}
	//load chiller wise products sheet
	def static loadChillerProductsSheet(){
		try{
			FileInputStream inputStream = new FileInputStream(new File(ProjectConstants.excelFilePath))
			XSSFWorkbook wb = new XSSFWorkbook(inputStream)
			XSSFSheet sheet = wb.getSheet(ProjectConstants.chillerproductssheet)
			return sheet
		}
		catch(Exception ex){
		}
	}
	//load distribution point sheet
	def static loadDistributionPointSheet(){
		try{
			FileInputStream inputStream = new FileInputStream(new File(ProjectConstants.excelFilePath))
			XSSFWorkbook wb = new XSSFWorkbook(inputStream)
			XSSFSheet sheet = wb.getSheet(DistributionPointSheet)
			return sheet
		}
		catch(Exception ex){
		}
	}
}
