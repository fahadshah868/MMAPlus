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

	public static final String excelFilePath = "F:\\Git Projects\\MMAPlus_Merchandising\\MMAPlus_Merchandising\\MMAPlus_Merchandising.xlsx"
	public static final String channelproductssheet = "Channel Products"
	public static final String chillerproductssheet = "Chiller Products"
	public static final String DistributionPointSheet = "Distribution Point"
	public static final AppiumDriver<MobileElement> driver = MobileDriverFactory.getDriver()
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
	public static final int chiller
	public static final int chiller_category
	public static final int chiller_product
	public static final int chiller_facing
	public static final int chiller_depth
	public static final int chiller_stocktaking
	public static final int chiller_overwritefacing
	public static final int chiller_overwritedepth
	public static final int chiller_overwritestocktaking

	public static String currentvisitingshopname
	public static String currentvisitingshopchannel
	public static String currentvisitingmaincategory
	public static String currentvisitingproductcategory


	static{
		XSSFSheet channelproductssheet = loadChannelProductsSheet()
		XSSFSheet chillerproductssheet = loadChannelProductsSheet()
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
			else if(columnname.equals("Overwrite Facing For DSA ")){
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
		}
		for(int cellnumber=0; cellnumber<chillerproductssheettotalcolumns; cellnumber++){
			String columnname = chillerproductssheetheaderrow.getCell(cellnumber)
			if(columnname.equals("Chiller Type")){
				chiller = cellnumber
			}
			if(columnname.equals("Category")){
				chiller_category = cellnumber
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
			else if(columnname.equals("Stock Taking")){
				chiller_stocktaking = cellnumber
			}
			else if(columnname.equals("Overwrite Facing ")){
				chiller_overwritefacing = cellnumber
			}
			else if(columnname.equals("Overwrite Depth")){
				chiller_overwritedepth = cellnumber
			}
			else if(columnname.equals("Overwrite Stock Taking")){
				chiller_overwritestocktaking = cellnumber
			}
		}
	}


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
