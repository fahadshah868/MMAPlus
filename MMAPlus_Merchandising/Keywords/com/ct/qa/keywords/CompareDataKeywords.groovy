package com.ct.qa.keywords

import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import com.ct.qa.constants.ProjectConstants
import com.ct.qa.struct.UnmatchedProducts
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
import io.appium.java_client.MobileElement
import org.apache.poi.ss.usermodel.DataFormatter
import org.apache.poi.ss.usermodel.Row
import org.apache.poi.xssf.usermodel.XSSFSheet
import MobileBuiltInKeywords as Mobile
import WSBuiltInKeywords as WS
import WebUiBuiltInKeywords as WebUI

public class CompareDataKeywords {
	//compare display and actual channel wise products categories
	def static compareChannelWiseProductsCategories(){
		DataFormatter dataformatter = new DataFormatter()
		XSSFSheet sheet = LoadDataKeywords.loadChannelProductsSheet()
		ArrayList<String> expectedproductscategorieslist = new ArrayList<String>()
		ArrayList<String> displayedproductscategorieslist = new ArrayList<String>()
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
				expectedproductscategorieslist.add(productcategory)
			}
		}
		int totalproductscategories = ProjectConstants.DRIVER.findElementsByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.ListView[1]/*").size()
		for(int i=1; i<=totalproductscategories; i++){
			MobileElement productcategory = ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout["+i+"]/android.widget.TextView[1]")
			displayedproductscategorieslist.add(productcategory.getText())
		}
		ArrayList<String> expectedproductscategories = new HashSet<String>(expectedproductscategorieslist)
		if(expectedproductscategories.size() == displayedproductscategorieslist.size()){
			ArrayList<String> products = new ArrayList<String>()
			UnmatchedProducts unmatchedproducts_status = new UnmatchedProducts()
			for(int i=0; i<displayedproductscategorieslist.size(); i++){
				boolean match = false
				for(int j=0; j<expectedproductscategories.size(); j++){
					if(displayedproductscategorieslist.get(i).equalsIgnoreCase(expectedproductscategories.get(j))){
						match = true
						break
					}
				}
				if(match == false){
					products.add(displayedproductscategorieslist.get(i))
				}
				else{
				}
			}
			if(!products.isEmpty()){
				unmatchedproducts_status.setProducts(products)
				unmatchedproducts_status.setStatus(2)
				return unmatchedproducts_status
			}
			else{
				unmatchedproducts_status.setProducts(products)
				unmatchedproducts_status.setStatus(0)
				return unmatchedproducts_status
			}
		}
		else if(expectedproductscategories.size() < displayedproductscategorieslist.size()){
			ArrayList<String> products = new ArrayList<String>()
			UnmatchedProducts unmatchedproducts_status = new UnmatchedProducts()
			for(int i=0; i<displayedproductscategorieslist.size(); i++){
				boolean match = false
				for(int j=0; j<expectedproductscategories.size(); j++){
					if(displayedproductscategorieslist.get(i).equalsIgnoreCase(expectedproductscategories.get(j))){
						match = true
						break
					}
				}
				if(match == false){
					products.add(displayedproductscategorieslist.get(i))
				}
				else{
				}
			}
			unmatchedproducts_status.setProducts(products)
			unmatchedproducts_status.setStatus(1)
			return unmatchedproducts_status
		}
		else if(expectedproductscategories.size() > displayedproductscategorieslist.size()){
			ArrayList<String> products = new ArrayList<String>()
			UnmatchedProducts unmatchedproducts_status = new UnmatchedProducts()
			for(int i=0; i<expectedproductscategories.size(); i++){
				boolean match = false
				for(int j=0; j<displayedproductscategorieslist.size(); j++){
					if(expectedproductscategories.get(i).equalsIgnoreCase(displayedproductscategorieslist.get(j))){
						match = true
						break
					}
				}
				if(match == false){
					products.add(expectedproductscategories.get(i))
				}
				else{
				}
			}
			unmatchedproducts_status.setProducts(products)
			unmatchedproducts_status.setStatus(-1)
			return unmatchedproducts_status
		}
		else{
			String message = "Main Category: "+ProjectConstants.CURRENTVISITING_MAINCATEGORY+"\nProduct Category: "+ProjectConstants.CURRENTVISITING_PRODUCTCATEGORY+"\n"+ProjectConstants.MESSAGEFOR_DISPLAYEDPRODUCTSARE_EQUAL
			KeywordUtil.logInfo(message)
		}
	}
	//compare display and actual chiller wise products categories
	def static compareChillerWiseProductsCategories(){
		DataFormatter dataformatter = new DataFormatter()
		XSSFSheet sheet = LoadDataKeywords.loadChillerProductsSheet()
		ArrayList<String> expectedproductscategorieslist = new ArrayList<String>()
		ArrayList<String> displayedproductscategorieslist = new ArrayList<String>()
		int totalrows = sheet.getLastRowNum()
		for(int i=1; i<=totalrows; i++){
			Row row = sheet.getRow(i)
			String chiller = dataformatter.formatCellValue(row.getCell(ProjectConstants.CHILLER_TYPE))
			if(ProjectConstants.CURRENTVISITING_CHILLERTYPE.equalsIgnoreCase(chiller)){
				String productcategory = dataformatter.formatCellValue(row.getCell(ProjectConstants.CHILLER_PRODUCTCATEGORY))
				expectedproductscategorieslist.add(productcategory)
			}
		}
		int totalproductscategories = ProjectConstants.DRIVER.findElementsByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.ListView[1]/*").size()
		for(int i=1; i<=totalproductscategories; i++){
			MobileElement productcategory = ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout["+i+"]/android.widget.TextView[1]")
			displayedproductscategorieslist.add(productcategory.getText())
		}
		ArrayList<String> expectedproductscategories = new HashSet<String>(expectedproductscategorieslist)
		if(expectedproductscategories.size() == displayedproductscategorieslist.size()){
			ArrayList<String> products = new ArrayList<String>()
			UnmatchedProducts unmatchedproducts_status = new UnmatchedProducts()
			for(int i=0; i<displayedproductscategorieslist.size(); i++){
				boolean match = false
				for(int j=0; j<expectedproductscategories.size(); j++){
					if(displayedproductscategorieslist.get(i).equalsIgnoreCase(expectedproductscategories.get(j))){
						match = true
						break
					}
				}
				if(match == false){
					products.add(displayedproductscategorieslist.get(i))
				}
				else{
				}
			}
			if(!products.isEmpty()){
				unmatchedproducts_status.setProducts(products)
				unmatchedproducts_status.setStatus(2)
				return unmatchedproducts_status
			}
			else{
				unmatchedproducts_status.setProducts(products)
				unmatchedproducts_status.setStatus(0)
				return unmatchedproducts_status
			}
		}
		else if(expectedproductscategories.size() < displayedproductscategorieslist.size()){
			ArrayList<String> products = new ArrayList<String>()
			UnmatchedProducts unmatchedproducts_status = new UnmatchedProducts()
			for(int i=0; i<displayedproductscategorieslist.size(); i++){
				boolean match = false
				for(int j=0; j<expectedproductscategories.size(); j++){
					if(displayedproductscategorieslist.get(i).equalsIgnoreCase(expectedproductscategories.get(j))){
						match = true
						break
					}
				}
				if(match == false){
					products.add(displayedproductscategorieslist.get(i))
				}
				else{
				}
			}
			unmatchedproducts_status.setProducts(products)
			unmatchedproducts_status.setStatus(1)
			return unmatchedproducts_status
		}
		else if(expectedproductscategories.size() > displayedproductscategorieslist.size()){
			ArrayList<String> products = new ArrayList<String>()
			UnmatchedProducts unmatchedproducts_status = new UnmatchedProducts()
			for(int i=0; i<expectedproductscategories.size(); i++){
				boolean match = false
				for(int j=0; j<displayedproductscategorieslist.size(); j++){
					if(expectedproductscategories.get(i).equalsIgnoreCase(displayedproductscategorieslist.get(j))){
						match = true
						break
					}
				}
				if(match == false){
					products.add(expectedproductscategories.get(i))
				}
				else{
				}
			}
			unmatchedproducts_status.setProducts(products)
			unmatchedproducts_status.setStatus(-1)
			return unmatchedproducts_status
		}
		else{
			String message = "Main Category: "+ProjectConstants.CURRENTVISITING_MAINCATEGORY+"\nProduct Category: "+ProjectConstants.CURRENTVISITING_PRODUCTCATEGORY+"\n"+ProjectConstants.MESSAGEFOR_DISPLAYEDPRODUCTSARE_EQUAL
			KeywordUtil.logInfo(message)
		}
	}
	//compare display and actual shop main categories
	def static compareShopCategories(){
		ArrayList<String> displayshopcategorieslist = new ArrayList<String>()
		int index = 0
		int mandatorycategories = 0
		int totalcategories = ProjectConstants.DRIVER.findElementsByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ListView[1]/*").size()
		for(int i=1; i<=totalcategories; i++){
			MobileElement category = ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout["+i+"]/android.widget.TextView[1]")
			String categoryname = category.getText()
			if(categoryname.equalsIgnoreCase("Chiller")){
				displayshopcategorieslist.add(categoryname)
				mandatorycategories = mandatorycategories + 1
			}
			else if(categoryname.equalsIgnoreCase("Chiller Utilization")){
				displayshopcategorieslist.add("Chiller")
				mandatorycategories = mandatorycategories + 1
			}
			else if(categoryname.equalsIgnoreCase("Additional Picture")){
				mandatorycategories = mandatorycategories + 1
			}
			else if(categoryname.equalsIgnoreCase("POP Application")){
				mandatorycategories = mandatorycategories + 1
			}
			else if(categoryname.equalsIgnoreCase("Competition Tracking")){
				mandatorycategories = mandatorycategories + 1
			}
			else if(categoryname.equalsIgnoreCase("Retailer Remarks")){
				mandatorycategories = mandatorycategories + 1
			}
			else if(categoryname.equalsIgnoreCase("RTM -Visit Frequency")){
				mandatorycategories = mandatorycategories + 1
			}
			else if(categoryname.equalsIgnoreCase("Hanger Availibility")){
				mandatorycategories = mandatorycategories + 1
			}
			else{
				displayshopcategorieslist.add(categoryname)
			}
		}
		while(true){
			index = ProjectConstants.DRIVER.findElementsByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ListView[1]/*").size()
			MobileElement lastitembeforeswipe = ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout["+index+"]/android.widget.TextView[1]")
			String lastitemnamebeforeswipe = lastitembeforeswipe.getText()
			Mobile.swipe(0, 293, 0, 200)
			index = ProjectConstants.DRIVER.findElementsByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ListView[1]/*").size()
			MobileElement lastitemafterswipe = ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout["+index+"]/android.widget.TextView[1]")
			String lastitemnameafterswipe = lastitemafterswipe.getText()
			if(lastitemnamebeforeswipe.equalsIgnoreCase(lastitemnameafterswipe)){
				break
			}
			else if(lastitemnameafterswipe.equalsIgnoreCase("Chiller")){
				displayshopcategorieslist.add(lastitemnameafterswipe)
				mandatorycategories = mandatorycategories + 1
			}
			else if(lastitemnameafterswipe.equalsIgnoreCase("Chiller Utilization")){
				displayshopcategorieslist.add("Chiller")
				mandatorycategories = mandatorycategories + 1
			}
			else if(lastitemnameafterswipe.equalsIgnoreCase("Additional Picture")){
				mandatorycategories = mandatorycategories + 1
			}
			else if(lastitemnameafterswipe.equalsIgnoreCase("Competition Tracking")){
				mandatorycategories = mandatorycategories + 1
			}
			else if(lastitemnameafterswipe.equalsIgnoreCase("Retailer Remarks")){
				mandatorycategories = mandatorycategories + 1
			}
			else if(lastitemnameafterswipe.equalsIgnoreCase("RTM -Visit Frequency")){
				mandatorycategories = mandatorycategories + 1
			}
			else if(lastitemnameafterswipe.equalsIgnoreCase("POP Application")){
				mandatorycategories = mandatorycategories + 1
			}
			else if(lastitemnameafterswipe.equalsIgnoreCase("Hanger Availibility")){
				mandatorycategories = mandatorycategories + 1
			}
			else{
				displayshopcategorieslist.add(lastitemnameafterswipe)
			}
		}
		ArrayList<String> expectedshopcategorieslist = LoadDataKeywords.loadShopCategories()
		ArrayList<String> expectedshopcategories = new HashSet<String>(expectedshopcategorieslist)
		if(expectedshopcategories.size() == displayshopcategorieslist.size()){
			ArrayList<String> products = new ArrayList<String>()
			UnmatchedProducts unmatchedproducts_status = new UnmatchedProducts()
			for(int i=0; i<displayshopcategorieslist.size(); i++){
				boolean match = false
				for(int j=0; j<expectedshopcategories.size(); j++){
					if(displayshopcategorieslist.get(i).equalsIgnoreCase(expectedshopcategories.get(j))){
						match = true
						break
					}
				}
				if(match == false){
					products.add(displayshopcategorieslist.get(i))
				}
				else{
				}
			}
			if(!products.isEmpty()){
				unmatchedproducts_status.setProducts(products)
				unmatchedproducts_status.setStatus(2)
				return unmatchedproducts_status
			}
			else{
				unmatchedproducts_status.setProducts(products)
				unmatchedproducts_status.setStatus(0)
				return unmatchedproducts_status
			}
		}
		else if(expectedshopcategories.size() < displayshopcategorieslist.size()){
			ArrayList<String> products = new ArrayList<String>()
			UnmatchedProducts unmatchedproducts_status = new UnmatchedProducts()
			for(int i=0; i<displayshopcategorieslist.size(); i++){
				boolean match = false
				for(int j=0; j<expectedshopcategories.size(); j++){
					if(displayshopcategorieslist.get(i).equalsIgnoreCase(expectedshopcategories.get(j))){
						match = true
						break
					}
				}
				if(match == false){
					products.add(displayshopcategorieslist.get(i))
				}
				else{
				}
			}
			unmatchedproducts_status.setProducts(products)
			unmatchedproducts_status.setStatus(1)
			return unmatchedproducts_status
		}
		else if(expectedshopcategories.size() > displayshopcategorieslist.size()){
			ArrayList<String> products = new ArrayList<String>()
			UnmatchedProducts unmatchedproducts_status = new UnmatchedProducts()
			for(int i=0; i<expectedshopcategories.size(); i++){
				boolean match = false
				for(int j=0; j<displayshopcategorieslist.size(); j++){
					if(expectedshopcategories.get(i).equalsIgnoreCase(displayshopcategorieslist.get(j))){
						match = true
						break
					}
				}
				if(match == false){
					products.add(expectedshopcategories.get(i))
				}
				else{
				}
			}
			unmatchedproducts_status.setProducts(products)
			unmatchedproducts_status.setStatus(-1)
			return unmatchedproducts_status
		}
		else{
			String message = "Main Category: "+ProjectConstants.CURRENTVISITING_MAINCATEGORY+"\nProduct Category: "+ProjectConstants.CURRENTVISITING_PRODUCTCATEGORY+"\n"+ProjectConstants.MESSAGEFOR_DISPLAYEDPRODUCTSARE_EQUAL
			KeywordUtil.logInfo(message)
		}
	}
}
