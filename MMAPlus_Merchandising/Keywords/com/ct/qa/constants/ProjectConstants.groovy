package com.ct.qa.constants

import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import com.ct.qa.keywords.LoadDataKeywords
import com.ct.qa.struct.MissingShopDataInfo
import com.ct.qa.struct.MissingSliderOptions
import com.ct.qa.struct.VisitedShopDataInfo
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
import org.openqa.selenium.Point
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
	public static final String SLIDEROPTIONSSHEET = "Slider Options"
	public static final String SHOPACTIONSSHEET = "Shop Actions"
	public static final String SURVEYQUESTIONSSHEET = "Survey Questions"
	public static final AppiumDriver<MobileElement> DRIVER = MobileDriverFactory.getDriver()

	//variables for display messages
	//products comparison messages
	public static final String MESSAGEFOR_ITEMSARE_MORE = "above items are displaying on device more than to expected items..."
	public static final String MESSAGEFOR_ITEMSARE_MISSING = "above items are missing on device..."
	public static final String MESSAGEFOR_ITEMSARE_NOTMATCH = "above items are display on device not match with expected items..."

	//package name for elements
	public static final String PACKAGENAME = "com.concavetech.audit"

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

	//shop actions columns
	public static final int SHOPACTIONS

	//slider options columns
	public static final int SLIDEROPTIONS

	//audit questions columns
	public static final int SURVEY_QUESTIONCATEGORY
	public static final int SURVEY_QUESTION
	public static final int SURVEY_QUESTIONOPTION
	public static final int SURVEY_QUESTIONOPTION_TAKEPICTURE

	//variables for current visiting shop channels, chiller and categories
	public static String CURRENTVISITING_SHOPNAME = ""
	public static String CURRENTVISITING_SHOPCHANNEL = ""
	public static String CURRENTVISITING_MAINCATEGORY = ""
	public static String CURRENTVISITING_CATEGORYREMARK = ""
	public static String CURRENTVISITING_PRODUCTCATEGORY = ""
	public static String CURRENTVISITING_CHILLERCOUNT = ""
	public static String CURRENTVISITING_CHILLERTYPE = ""
	public static String CURRENTVISITING_CHILLERREMARK = ""
	public static String SCENARIO = ""
	public static String CURRENTVISITING_QUESTIONCATEGORY = ""
	public static int VISITED_CHILLERREMARKS = 0
	public static int SHOP_ATTEMPT = 0
	public static String CHILLER_OVERWRITEPOPUP = "no"


	//list for containing shop info
	public static ArrayList<MissingShopDataInfo> missingshopdatainfo = new ArrayList<MissingShopDataInfo>()
	public static ArrayList<VisitedShopDataInfo> visitedshopdatainfo = new ArrayList<MissingShopDataInfo>()
	public static MissingSliderOptions missingslideroptions = new MissingSliderOptions()

	//initialization of sheet columns index
	static{
		XSSFSheet channelproductssheet = LoadDataKeywords.loadChannelProductsSheet()
		XSSFSheet chillerproductssheet = LoadDataKeywords.loadChillerProductsSheet()
		XSSFSheet slideroptionssheet = LoadDataKeywords.loadSliderOptionsSheet()
		XSSFSheet shopactionssheet = LoadDataKeywords.loadShopActionsSheet()
		XSSFSheet auditquestionssheet = LoadDataKeywords.loadSurveyQuestionsSheet()
		Row chillerproductssheetheaderrow = chillerproductssheet.getRow(0)
		Row channelproductssheetheaderrow = channelproductssheet.getRow(0)
		Row slideroptionssheetheaderrow = slideroptionssheet.getRow(0)
		Row shopactionssheetheaderrow = shopactionssheet.getRow(0)
		Row auditquestionssheetheaderrow = auditquestionssheet.getRow(0)
		int channelproductssheettotalcolumns = channelproductssheetheaderrow.getLastCellNum()
		int chillerproductssheettotalcolumns = chillerproductssheetheaderrow.getLastCellNum()
		int slideroptionssheettotalcolumns = slideroptionssheetheaderrow.getLastCellNum()
		int shopactionssheettotalcolumns = shopactionssheetheaderrow.getLastCellNum()
		int auditquestionssheettotalcolumns = auditquestionssheetheaderrow.getLastCellNum()
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
		for(int cellnumber=0; cellnumber<slideroptionssheettotalcolumns; cellnumber++ ){
			String columnname = slideroptionssheetheaderrow.getCell(cellnumber)
			if(columnname.equalsIgnoreCase("Slider Options")){
				SLIDEROPTIONS = cellnumber
			}
			else{
			}
		}
		for(int cellnumber=0; cellnumber<shopactionssheettotalcolumns; cellnumber++ ){
			String columnname = shopactionssheetheaderrow.getCell(cellnumber)
			if(columnname.equalsIgnoreCase("Shop Actions")){
				SHOPACTIONS = cellnumber
			}
			else{
			}
		}
		for(int cellnumber=0; cellnumber<auditquestionssheettotalcolumns; cellnumber++ ){
			String columnname = auditquestionssheetheaderrow.getCell(cellnumber)
			if(columnname.equalsIgnoreCase("Question Category")){
				SURVEY_QUESTIONCATEGORY = cellnumber
			}
			else if(columnname.equalsIgnoreCase("Question")){
				SURVEY_QUESTION = cellnumber
			}
			else if(columnname.equalsIgnoreCase("Option")){
				SURVEY_QUESTIONOPTION = cellnumber
			}
			else if(columnname.equalsIgnoreCase("Take Picture")){
				SURVEY_QUESTIONOPTION_TAKEPICTURE = cellnumber
			}
			else{
			}
		}
	}
}
