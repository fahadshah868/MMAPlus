package com.ct.qa.keywords

import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import com.ct.qa.constants.ProjectConstants
import com.ct.qa.struct.LoadProductsData
import com.ct.qa.struct.QuestionsData
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
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords

import internal.GlobalVariable
import org.apache.poi.ss.usermodel.DataFormatter
import org.apache.poi.ss.usermodel.Row
import org.apache.poi.xssf.usermodel.XSSFSheet
import org.apache.poi.xssf.usermodel.XSSFWorkbook
import org.stringtemplate.v4.compiler.CodeGenerator.region_return

import MobileBuiltInKeywords as Mobile
import WSBuiltInKeywords as WS
import WebUiBuiltInKeywords as WebUI

public class LoadDataKeywords {
	//load channel wise products sheet
	def static loadChannelProductsSheet(){
		try{
			FileInputStream inputStream = new FileInputStream(new File(ProjectConstants.EXCEL_FILEPATH))
			XSSFWorkbook wb = new XSSFWorkbook(inputStream)
			XSSFSheet sheet = wb.getSheet(ProjectConstants.CHANNEL_PRODUCTSSHEET)
			return sheet
		}
		catch(Exception ex){
		}
	}
	//load chiller wise products sheet
	def static loadChillerProductsSheet(){
		try{
			FileInputStream inputStream = new FileInputStream(new File(ProjectConstants.EXCEL_FILEPATH))
			XSSFWorkbook wb = new XSSFWorkbook(inputStream)
			XSSFSheet sheet = wb.getSheet(ProjectConstants.CHILLER_PRODUCTSSHEET)
			return sheet
		}
		catch(Exception ex){
		}
	}
	//load distribution point sheet
	def static loadDistributionPointSheet(){
		try{
			FileInputStream inputStream = new FileInputStream(new File(ProjectConstants.EXCEL_FILEPATH))
			XSSFWorkbook wb = new XSSFWorkbook(inputStream)
			XSSFSheet sheet = wb.getSheet(ProjectConstants.DISTRIBUTION_SHEET)
			return sheet
		}
		catch(Exception ex){
		}
	}
	//load Slider Options sheet
	def static loadSliderOptionsSheet(){
		try{
			FileInputStream inputStream = new FileInputStream(new File(ProjectConstants.EXCEL_FILEPATH))
			XSSFWorkbook wb = new XSSFWorkbook(inputStream)
			XSSFSheet sheet = wb.getSheet(ProjectConstants.SLIDEROPTIONSSHEET)
			return sheet
		}
		catch(Exception ex){
		}
	}
	//load Slider Options sheet
	def static loadShopActionsSheet(){
		try{
			FileInputStream inputStream = new FileInputStream(new File(ProjectConstants.EXCEL_FILEPATH))
			XSSFWorkbook wb = new XSSFWorkbook(inputStream)
			XSSFSheet sheet = wb.getSheet(ProjectConstants.SHOPACTIONSSHEET)
			return sheet
		}
		catch(Exception ex){
		}
	}
	//load Slider Options sheet
	def static loadSurveyQuestionsSheet(){
		try{
			FileInputStream inputStream = new FileInputStream(new File(ProjectConstants.EXCEL_FILEPATH))
			XSSFWorkbook wb = new XSSFWorkbook(inputStream)
			XSSFSheet sheet = wb.getSheet(ProjectConstants.SURVEYQUESTIONSSHEET)
			return sheet
		}
		catch(Exception ex){
		}
	}
	//load audit question category list
	def static loadSurveyQuestionCategoryList(){
		DataFormatter dataformatter = new DataFormatter()
		ArrayList<String> expectedquestioncategorylist = new ArrayList<String>()
		XSSFSheet sheet = loadSurveyQuestionsSheet()
		int totalrows = sheet.getLastRowNum()
		for(int i=1; i<= totalrows; i++){
			Row row = sheet.getRow(i)
			String questioncategory = dataformatter.formatCellValue(row.getCell(ProjectConstants.SURVEY_QUESTIONCATEGORY))
			expectedquestioncategorylist.add(questioncategory)
		}
		return expectedquestioncategorylist
	}
	//load audit question list
	def static loadSurveyQuestionsList(){
		DataFormatter dataformatter = new DataFormatter()
		ArrayList<QuestionsData> questions = new ArrayList<QuestionsData>()
		XSSFSheet sheet = loadSurveyQuestionsSheet()
		int totalrows = sheet.getLastRowNum()
		for(int i=1; i<= totalrows; i++){
			Row row = sheet.getRow(i)
			String questioncategory = dataformatter.formatCellValue(row.getCell(ProjectConstants.SURVEY_QUESTIONCATEGORY))
			if(ProjectConstants.CURRENTVISITING_QUESTIONCATEGORY.equalsIgnoreCase(questioncategory)){
				QuestionsData question = new QuestionsData()
				question.setQuestion(dataformatter.formatCellValue(row.getCell(ProjectConstants.SURVEY_QUESTION)))
				question.setQuestionoption(dataformatter.formatCellValue(row.getCell(ProjectConstants.SURVEY_QUESTIONOPTION)))
				question.setQuestionoption_takepicture(dataformatter.formatCellValue(row.getCell(ProjectConstants.SURVEY_QUESTIONOPTION_TAKEPICTURE)))
				questions.add(question)
			}
		}
		return questions
	}
	//load shop actions
	def static loadShopActionsList(){
		DataFormatter dataformatter = new DataFormatter()
		ArrayList<String> expectedshopactionslist = new ArrayList<String>()
		XSSFSheet sheet = loadShopActionsSheet()
		int totalrows = sheet.getLastRowNum()
		for(int i=1; i<= totalrows; i++){
			Row row = sheet.getRow(i)
			String shopaction = dataformatter.formatCellValue(row.getCell(ProjectConstants.SHOPACTIONS))
			expectedshopactionslist.add(shopaction)
		}
		return expectedshopactionslist
	}
	//load shop categories
	def static loadShopCategories(){
		DataFormatter dataformatter = new DataFormatter()
		ArrayList<String> expectedshopcategories = new ArrayList<String>()
		XSSFSheet sheet = loadChannelProductsSheet()
		int totalrows = sheet.getLastRowNum()
		for(int i=1; i<=totalrows; i++){
			Row row = sheet.getRow(i)
			String channel = dataformatter.formatCellValue(row.getCell(ProjectConstants.CHANNEL))
			String channelname = "Channel: "+channel
			if(ProjectConstants.CURRENTVISITING_SHOPCHANNEL.equalsIgnoreCase(channelname)){
				String category = dataformatter.formatCellValue(row.getCell(ProjectConstants.CHANNEL_MAINCATEGORY))
				expectedshopcategories.add(category)
			}
		}
		return expectedshopcategories
	}
	//load channel wise sub categories
	def static loadChannelWiseSubCategories(){
		ArrayList<String> expectedsubcategories = new ArrayList<String>()
		DataFormatter dataformatter = new DataFormatter()
		XSSFSheet sheet = LoadDataKeywords.loadChannelProductsSheet()
		String currentvisitingmaincategory = ""
		if(ProjectConstants.CURRENTVISITING_MAINCATEGORY.equalsIgnoreCase("Chiller Utilization")){
			currentvisitingmaincategory = "Chiller"
		}
		else{
			currentvisitingmaincategory = ProjectConstants.CURRENTVISITING_MAINCATEGORY
		}
		int totalrows = sheet.getLastRowNum()
		for(int i=1; i<=totalrows; i++){
			Row row = sheet.getRow(i)
			String channel = dataformatter.formatCellValue(row.getCell(ProjectConstants.CHANNEL))
			String channelname = "Channel: "+channel
			String maincategory = dataformatter.formatCellValue(row.getCell(ProjectConstants.CHANNEL_MAINCATEGORY))
			if(ProjectConstants.CURRENTVISITING_SHOPCHANNEL.equalsIgnoreCase(channelname) && maincategory.equalsIgnoreCase(currentvisitingmaincategory)){
				String productcategory = dataformatter.formatCellValue(row.getCell(ProjectConstants.CHANNEL_PRODUCTCATEGORY))
				expectedsubcategories.add(productcategory)
			}
		}
		return expectedsubcategories
	}
	//load channel wise products and quantity
	def static loadChannelWiseProductsList(XSSFSheet sheet, int column){
		DataFormatter dataformatter = new DataFormatter()
		ArrayList<LoadProductsData> channelproducts = new ArrayList<LoadProductsData>()
		int totalrows = sheet.getLastRowNum()
		for(int i=1; i<=totalrows; i++){
			Row row = sheet.getRow(i)
			String channel = dataformatter.formatCellValue(row.getCell(ProjectConstants.CHANNEL))
			String channelname = "Channel: "+channel
			String maincategory = dataformatter.formatCellValue(row.getCell(ProjectConstants.CHANNEL_MAINCATEGORY))
			String productcategory = dataformatter.formatCellValue(row.getCell(ProjectConstants.CHANNEL_PRODUCTCATEGORY))
			if((ProjectConstants.CURRENTVISITING_SHOPCHANNEL.equalsIgnoreCase(channelname) && ProjectConstants.CURRENTVISITING_MAINCATEGORY.equalsIgnoreCase(maincategory)) && ProjectConstants.CURRENTVISITING_PRODUCTCATEGORY.equalsIgnoreCase(productcategory)){
				LoadProductsData channelproduct = new LoadProductsData()
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
	//load chiller wise sub categories
	def static loadChillerWiseSubCategories(){
		DataFormatter dataformatter = new DataFormatter()
		XSSFSheet sheet = LoadDataKeywords.loadChillerProductsSheet()
		ArrayList<String> expectedsubcategories = new ArrayList<String>()
		int totalrows = sheet.getLastRowNum()
		for(int i=1; i<=totalrows; i++){
			Row row = sheet.getRow(i)
			String chiller = dataformatter.formatCellValue(row.getCell(ProjectConstants.CHILLER_TYPE))
			if(ProjectConstants.CURRENTVISITING_CHILLERTYPE.equalsIgnoreCase(chiller)){
				String productcategory = dataformatter.formatCellValue(row.getCell(ProjectConstants.CHILLER_PRODUCTCATEGORY))
				expectedsubcategories.add(productcategory)
			}
		}
		return expectedsubcategories
	}
	//load chiller products and quantity
	def static loadChillerAvailableProductsList(XSSFSheet sheet, int column){
		DataFormatter dataformatter = new DataFormatter()
		ArrayList<LoadProductsData> channelproducts = new ArrayList<LoadProductsData>()
		int totalrows = sheet.getLastRowNum()
		for(int i=1; i<=totalrows; i++){
			Row row = sheet.getRow(i)
			String chillertype = dataformatter.formatCellValue(row.getCell(ProjectConstants.CHILLER_TYPE))
			String productcategory = dataformatter.formatCellValue(row.getCell(ProjectConstants.CHILLER_PRODUCTCATEGORY))
			if(ProjectConstants.CURRENTVISITING_CHILLERTYPE.equalsIgnoreCase(chillertype) && ProjectConstants.CURRENTVISITING_PRODUCTCATEGORY.equalsIgnoreCase(productcategory)){
				LoadProductsData channelproduct = new LoadProductsData()
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
	//load channel products and quantity
	def static loadChillerNotAvailableProductsList(XSSFSheet sheet, int column){
		DataFormatter dataformatter = new DataFormatter()
		ArrayList<LoadProductsData> channelproducts = new ArrayList<LoadProductsData>()
		int totalrows = sheet.getLastRowNum()
		for(int i=1; i<=totalrows; i++){
			Row row = sheet.getRow(i)
			String channel = dataformatter.formatCellValue(row.getCell(ProjectConstants.CHANNEL))
			String channelname = "Channel: "+channel
			String maincategory = dataformatter.formatCellValue(row.getCell(ProjectConstants.CHANNEL_MAINCATEGORY))
			String productcategory = dataformatter.formatCellValue(row.getCell(ProjectConstants.CHANNEL_PRODUCTCATEGORY))
			if((ProjectConstants.CURRENTVISITING_SHOPCHANNEL.equalsIgnoreCase(channelname) && maincategory.equalsIgnoreCase("Chiller")) && ProjectConstants.CURRENTVISITING_PRODUCTCATEGORY.equalsIgnoreCase(productcategory)){
				LoadProductsData channelproduct = new LoadProductsData()
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
	def static loadSliderOptions(){
		DataFormatter dataformatter = new DataFormatter()
		XSSFSheet sheet = loadSliderOptionsSheet()
		int totalrows = sheet.getLastRowNum()
		ArrayList<String> slideroptions = new ArrayList<String>()
		for(int i=1; i<=totalrows; i++){
			Row row = sheet.getRow(i)
			slideroptions.add(dataformatter.formatCellValue(row.getCell(ProjectConstants.SLIDEROPTIONS)))
		}
		return slideroptions
	}
}
