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
import com.ct.qa.struct.MissingChillerProductsCategoryData
import com.ct.qa.struct.LoadProductsData
import com.ct.qa.struct.MissingCategoryData
import com.ct.qa.struct.ShopProductsData
import com.ct.qa.struct.TaggedChillersRemark
import com.ct.qa.struct.UnmatchedItems
import com.ct.qa.struct.VisitedCategoryData
import com.ct.qa.struct.VisitedChillerProductsCategoryData
import com.ct.qa.struct.VisitedShopDataInfo
import io.appium.java_client.MobileElement

public class ChillerProductsDataKeywords {

	@Keyword
	def visitChillerAvailableProductCategories(int flag){
		UnmatchedItems unmatcheditems = CompareDataKeywords.compareChillerWiseProductsCategories()
		if(unmatcheditems.getStatus() == 2){
			MissingCategoryData missingcategorydata = new MissingCategoryData()
			TaggedChillersRemark taggedchillerremark = new TaggedChillersRemark()
			MissingChillerProductsCategoryData missingchillercategory = new MissingChillerProductsCategoryData()
			missingcategorydata.setMaincategory(ProjectConstants.CURRENTVISITING_MAINCATEGORY)
			taggedchillerremark.setFirstvisit_chillertype(ProjectConstants.CURRENTVISITING_CHILLERTYPE)
			taggedchillerremark.setFirstvisit_chillerremark(ProjectConstants.CURRENTVISITING_CHILLERREMARK)
			missingchillercategory.setProductcategories(unmatcheditems.getItems())
			missingchillercategory.setErrormessage_forproductcategories(ProjectConstants.MESSAGEFOR_ITEMSARE_NOTMATCH)
			taggedchillerremark.setMissingchillerproductscategories(missingchillercategory)
			missingcategorydata.setTaggedchillersremarks(taggedchillerremark)
			for(int j=0; j<ProjectConstants.missingshopdatainfo.size(); j++){
				if(ProjectConstants.missingshopdatainfo.get(j).getShopname().equalsIgnoreCase(ProjectConstants.CURRENTVISITING_SHOPNAME)) {
					ProjectConstants.missingshopdatainfo.get(j).setMissingCategoriesData(missingcategorydata, "")
					break
				}
				else{
				}
			}
		}
		else if(unmatcheditems.getStatus() == 1){
			MissingCategoryData missingcategorydata = new MissingCategoryData()
			TaggedChillersRemark taggedchillerremark = new TaggedChillersRemark()
			MissingChillerProductsCategoryData missingchillercategory = new MissingChillerProductsCategoryData()
			missingcategorydata.setMaincategory(ProjectConstants.CURRENTVISITING_MAINCATEGORY)
			taggedchillerremark.setFirstvisit_chillertype(ProjectConstants.CURRENTVISITING_CHILLERTYPE)
			taggedchillerremark.setFirstvisit_chillerremark(ProjectConstants.CURRENTVISITING_CHILLERREMARK)
			missingchillercategory.setProductcategories(unmatcheditems.getItems())
			missingchillercategory.setErrormessage_forproductcategories(ProjectConstants.MESSAGEFOR_ITEMSARE_MORE)
			taggedchillerremark.setMissingchillerproductscategories(missingchillercategory)
			missingcategorydata.setTaggedchillersremarks(taggedchillerremark)
			for(int j=0; j<ProjectConstants.missingshopdatainfo.size(); j++){
				if(ProjectConstants.missingshopdatainfo.get(j).getShopname().equalsIgnoreCase(ProjectConstants.CURRENTVISITING_SHOPNAME)) {
					ProjectConstants.missingshopdatainfo.get(j).setMissingCategoriesData(missingcategorydata, "")
					break
				}
				else{
				}
			}
		}
		else if(unmatcheditems.getStatus() == -1){
			MissingCategoryData missingcategorydata = new MissingCategoryData()
			TaggedChillersRemark taggedchillerremark = new TaggedChillersRemark()
			MissingChillerProductsCategoryData missingchillercategory = new MissingChillerProductsCategoryData()
			missingcategorydata.setMaincategory(ProjectConstants.CURRENTVISITING_MAINCATEGORY)
			taggedchillerremark.setFirstvisit_chillertype(ProjectConstants.CURRENTVISITING_CHILLERTYPE)
			taggedchillerremark.setFirstvisit_chillerremark(ProjectConstants.CURRENTVISITING_CHILLERREMARK)
			missingchillercategory.setProductcategories(unmatcheditems.getItems())
			missingchillercategory.setErrormessage_forproductcategories(ProjectConstants.MESSAGEFOR_ITEMSARE_MISSING)
			taggedchillerremark.setMissingchillerproductscategories(missingchillercategory)
			missingcategorydata.setTaggedchillersremarks(taggedchillerremark)
			for(int j=0; j<ProjectConstants.missingshopdatainfo.size(); j++){
				if(ProjectConstants.missingshopdatainfo.get(j).getShopname().equalsIgnoreCase(ProjectConstants.CURRENTVISITING_SHOPNAME)) {
					ProjectConstants.missingshopdatainfo.get(j).setMissingCategoriesData(missingcategorydata, "")
					break
				}
				else{
				}
			}
		}
		else{
		}
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
	@Keyword
	def visitChillerNotAvailableProductCategories(int flag){
		UnmatchedItems unmatcheditems = CompareDataKeywords.compareChannelWiseProductsCategories()
		if(unmatcheditems.getStatus() == 2){
			MissingCategoryData missingcategorydata = new MissingCategoryData()
			TaggedChillersRemark taggedchillerremark = new TaggedChillersRemark()
			MissingChillerProductsCategoryData missingchillercategory = new MissingChillerProductsCategoryData()
			missingcategorydata.setMaincategory(ProjectConstants.CURRENTVISITING_MAINCATEGORY)
			taggedchillerremark.setFirstvisit_chillertype(ProjectConstants.CURRENTVISITING_CHILLERTYPE)
			taggedchillerremark.setFirstvisit_chillerremark(ProjectConstants.CURRENTVISITING_CHILLERREMARK)
			missingchillercategory.setProductcategories(unmatcheditems.getItems())
			missingchillercategory.setErrormessage_forproductcategories(ProjectConstants.MESSAGEFOR_ITEMSARE_NOTMATCH)
			taggedchillerremark.setMissingchillerproductscategories(missingchillercategory)
			missingcategorydata.setTaggedchillersremarks(taggedchillerremark)
			for(int j=0; j<ProjectConstants.missingshopdatainfo.size(); j++){
				if(ProjectConstants.missingshopdatainfo.get(j).getShopname().equalsIgnoreCase(ProjectConstants.CURRENTVISITING_SHOPNAME)) {
					ProjectConstants.missingshopdatainfo.get(j).setMissingCategoriesData(missingcategorydata, "")
					break
				}
				else{
				}
			}
		}
		else if(unmatcheditems.getStatus() == 1){
			MissingCategoryData missingcategorydata = new MissingCategoryData()
			TaggedChillersRemark taggedchillerremark = new TaggedChillersRemark()
			MissingChillerProductsCategoryData missingchillercategory = new MissingChillerProductsCategoryData()
			missingcategorydata.setMaincategory(ProjectConstants.CURRENTVISITING_MAINCATEGORY)
			taggedchillerremark.setFirstvisit_chillertype(ProjectConstants.CURRENTVISITING_CHILLERTYPE)
			taggedchillerremark.setFirstvisit_chillerremark(ProjectConstants.CURRENTVISITING_CHILLERREMARK)
			missingchillercategory.setProductcategories(unmatcheditems.getItems())
			missingchillercategory.setErrormessage_forproductcategories(ProjectConstants.MESSAGEFOR_ITEMSARE_MORE)
			taggedchillerremark.setMissingchillerproductscategories(missingchillercategory)
			missingcategorydata.setTaggedchillersremarks(taggedchillerremark)
			for(int j=0; j<ProjectConstants.missingshopdatainfo.size(); j++){
				if(ProjectConstants.missingshopdatainfo.get(j).getShopname().equalsIgnoreCase(ProjectConstants.CURRENTVISITING_SHOPNAME)) {
					ProjectConstants.missingshopdatainfo.get(j).setMissingCategoriesData(missingcategorydata, "")
					break
				}
				else{
				}
			}
		}
		else if(unmatcheditems.getStatus() == -1){
			MissingCategoryData missingcategorydata = new MissingCategoryData()
			TaggedChillersRemark taggedchillerremark = new TaggedChillersRemark()
			MissingChillerProductsCategoryData missingchillercategory = new MissingChillerProductsCategoryData()
			missingcategorydata.setMaincategory(ProjectConstants.CURRENTVISITING_MAINCATEGORY)
			taggedchillerremark.setFirstvisit_chillertype(ProjectConstants.CURRENTVISITING_CHILLERTYPE)
			taggedchillerremark.setFirstvisit_chillerremark(ProjectConstants.CURRENTVISITING_CHILLERREMARK)
			missingchillercategory.setProductcategories(unmatcheditems.getItems())
			missingchillercategory.setErrormessage_forproductcategories(ProjectConstants.MESSAGEFOR_ITEMSARE_MISSING)
			taggedchillerremark.setMissingchillerproductscategories(missingchillercategory)
			missingcategorydata.setTaggedchillersremarks(taggedchillerremark)
			for(int j=0; j<ProjectConstants.missingshopdatainfo.size(); j++){
				if(ProjectConstants.missingshopdatainfo.get(j).getShopname().equalsIgnoreCase(ProjectConstants.CURRENTVISITING_SHOPNAME)) {
					ProjectConstants.missingshopdatainfo.get(j).setMissingCategoriesData(missingcategorydata, "")
					break
				}
				else{
				}
			}
		}
		else{
		}
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
			else if(flag == 3){
				if(i == 1){
					Mobile.callTestCase(findTestCase("Test Cases/ShopOpen/ChillerUtilization/ChillerNotAvailable/VisitProductCategoryAssets"), null)
				}
				else{}
			}
			else{
			}
		}
	}
	@Keyword
	def visitChillerAvailableProductsData(int columnindex, String assettype){
		ArrayList<ShopProductsData> shopproductsdata = new ArrayList<ShopProductsData>()
		int index = 0
		XSSFSheet chillerproductssheet = LoadDataKeywords.loadChillerProductsSheet()
		ArrayList<String> displayproductslist = new ArrayList<String>()
		ArrayList<LoadProductsData> expectedproductslist = LoadDataKeywords.loadChillerAvailableProductsList(chillerproductssheet, columnindex)
		int totalproducts = ProjectConstants.DRIVER.findElementsByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ScrollView[1]/android.widget.LinearLayout[1]/*").size()
		for(int i=1; i<totalproducts; i=i+3){
			ShopProductsData productsdata = new ShopProductsData()
			boolean flag = false
			index = index + 1
			MobileElement selectedproduct = ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ScrollView[1]/android.widget.LinearLayout[1]/android.widget.TextView["+index+"]")
			String selectedproductname = selectedproduct.getText()
			productsdata.setProduct(selectedproductname)
			displayproductslist.add(selectedproductname)
			for(int j=0; j<expectedproductslist.size(); j++){
				LoadProductsData channelproduct = expectedproductslist.get(j)
				String productname = channelproduct.getProduct()
				if(selectedproductname.equalsIgnoreCase(productname)){
					flag = true
					String productquantity = channelproduct.getProduct_data()
					MobileElement selectedproducttextfield = ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ScrollView[1]/android.widget.LinearLayout[1]/android.widget.LinearLayout["+index+"]/android.widget.EditText[1]")
					selectedproducttextfield.setValue(productquantity)
					if(ProjectConstants.SCENARIO.equals("first visit")){
						if(assettype.equalsIgnoreCase("Facing")){
							productsdata.setFacingdata(productquantity)
						}
						else if(assettype.equalsIgnoreCase("Stock Count")){
							productsdata.setStocktakingdata_stockcountdata(productquantity)
						}
						else if(assettype.equalsIgnoreCase("Depth")){
							productsdata.setDepthdata(productquantity)
						}
						else{}
					}
					else{
						if(assettype.equalsIgnoreCase("Facing")){
							productsdata.setOverwritefacingdata(productquantity)
						}
						else if(assettype.equalsIgnoreCase("Stock Count")){
							productsdata.setOverwritestocktakingdata_stockcountdata(productquantity)
						}
						else if(assettype.equalsIgnoreCase("Depth")){
							productsdata.setOverwritedepthdata(productquantity)
						}
						else{}
					}
					Mobile.hideKeyboard()
					break
				}
				else{
				}
			}
			if(flag == false){
				MobileElement selectedproducttextfield = ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ScrollView[1]/android.widget.LinearLayout[1]/android.widget.LinearLayout["+index+"]/android.widget.EditText[1]")
				selectedproducttextfield.setValue("0000")
				if(ProjectConstants.SCENARIO.equals("first visit")){
					if(assettype.equalsIgnoreCase("Facing")){
						productsdata.setFacingdata("0000")
					}
					else if(assettype.equalsIgnoreCase("Stock Count")){
						productsdata.setStocktakingdata_stockcountdata("0000")
					}
					else if(assettype.equalsIgnoreCase("Depth")){
						productsdata.setDepthdata("0000")
					}
					else{}
				}
				else{
					if(assettype.equalsIgnoreCase("Facing")){
						productsdata.setOverwritefacingdata("0000")
					}
					else if(assettype.equalsIgnoreCase("Stock Count")){
						productsdata.setOverwritestocktakingdata_stockcountdata("0000")
					}
					else if(assettype.equalsIgnoreCase("Depth")){
						productsdata.setOverwritedepthdata("0000")
					}
					else{
					}
				}
				Mobile.hideKeyboard()
			}
			shopproductsdata.add(productsdata)
		}
		totalproducts = ProjectConstants.DRIVER.findElementsByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ScrollView[1]/android.widget.LinearLayout[1]/*").size()
		if(totalproducts >= 16){
			while(true){
				ShopProductsData productsdata = new ShopProductsData()
				int xlocation = CommonKeywords.getXPoint()
				MobileElement lastproductbeforeswipe = ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ScrollView[1]/android.widget.LinearLayout[1]/android.widget.TextView[5]")
				String lastproductnamebeforeswipe = lastproductbeforeswipe.getText()
				Mobile.swipe(xlocation, 359, xlocation, 250)
				MobileElement lastproductafterswipe = ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ScrollView[1]/android.widget.LinearLayout[1]/android.widget.TextView[5]")
				String lastproductnameafterswipe = lastproductafterswipe.getText()
				if(lastproductnamebeforeswipe.equalsIgnoreCase(lastproductnameafterswipe)){
					break
				}
				else{
					boolean flag = false
					displayproductslist.add(lastproductnameafterswipe)
					productsdata.setProduct(lastproductnameafterswipe)
					for(int j=0; j<expectedproductslist.size(); j++){
						LoadProductsData channelproduct = expectedproductslist.get(j)
						String productname = channelproduct.getProduct()
						if(lastproductnameafterswipe.equalsIgnoreCase(productname)){
							flag = true
							String productquantity = channelproduct.getProduct_data()
							MobileElement selectedproducttextfield = ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ScrollView[1]/android.widget.LinearLayout[1]/android.widget.LinearLayout[6]/android.widget.EditText[1]")
							selectedproducttextfield.setValue(productquantity)
							if(ProjectConstants.SCENARIO.equals("first visit")){
								if(assettype.equalsIgnoreCase("Facing")){
									productsdata.setFacingdata(productquantity)
								}
								else if(assettype.equalsIgnoreCase("Stock Count")){
									productsdata.setStocktakingdata_stockcountdata(productquantity)
								}
								else if(assettype.equalsIgnoreCase("Depth")){
									productsdata.setDepthdata(productquantity)
								}
								else{}
							}
							else{
								if(assettype.equalsIgnoreCase("Facing")){
									productsdata.setOverwritefacingdata(productquantity)
								}
								else if(assettype.equalsIgnoreCase("Stock Count")){
									productsdata.setOverwritestocktakingdata_stockcountdata(productquantity)
								}
								else if(assettype.equalsIgnoreCase("Depth")){
									productsdata.setOverwritedepthdata(productquantity)
								}
								else{}
							}
							Mobile.hideKeyboard()
							break
						}
						else{
						}
					}
					if(flag == false){
						MobileElement selectedproducttextfield = ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ScrollView[1]/android.widget.LinearLayout[1]/android.widget.LinearLayout["+index+"]/android.widget.EditText[1]")
						selectedproducttextfield.setValue("0000")
						if(ProjectConstants.SCENARIO.equals("first visit")){
							if(assettype.equalsIgnoreCase("Facing")){
								productsdata.setFacingdata("0000")
							}
							else if(assettype.equalsIgnoreCase("Stock Count")){
								productsdata.setStocktakingdata_stockcountdata("0000")
							}
							else if(assettype.equalsIgnoreCase("Depth")){
								productsdata.setDepthdata("0000")
							}
							else{}
						}
						else{
							if(assettype.equalsIgnoreCase("Facing")){
								productsdata.setOverwritefacingdata("0000")
							}
							else if(assettype.equalsIgnoreCase("Stock Count")){
								productsdata.setOverwritestocktakingdata_stockcountdata("0000")
							}
							else if(assettype.equalsIgnoreCase("Depth")){
								productsdata.setOverwritedepthdata("0000")
							}
							else{
							}
						}
						Mobile.hideKeyboard()
					}
					shopproductsdata.add(productsdata)
				}
			}
		}
		if(expectedproductslist.size() == displayproductslist.size()){
			ArrayList<String> products = new ArrayList<String>()
			for(int i=0; i<displayproductslist.size(); i++){
				boolean match = false
				for(int j=0; j<expectedproductslist.size(); j++){
					if(displayproductslist.get(i).equalsIgnoreCase(expectedproductslist.get(j).getProduct())){
						match = true
						break
					}
				}
				if(match == false){
					products.add(displayproductslist.get(i))
				}
				else{
				}
			}
			if(!products.isEmpty()){
				TaggedChillersRemark taggedchillerremark = new TaggedChillersRemark()
				MissingCategoryData missingcategorydata = new MissingCategoryData()
				MissingChillerProductsCategoryData missingchillerproductscategory = new MissingChillerProductsCategoryData()
				missingcategorydata.setMaincategory(ProjectConstants.CURRENTVISITING_MAINCATEGORY)
				taggedchillerremark.setFirstvisit_chillertype(ProjectConstants.CURRENTVISITING_CHILLERTYPE)
				taggedchillerremark.setFirstvisit_chillerremark(ProjectConstants.CURRENTVISITING_CHILLERREMARK)
				missingchillerproductscategory.setProductcategory(ProjectConstants.CURRENTVISITING_PRODUCTCATEGORY)
				missingchillerproductscategory.setProducts(products)
				missingchillerproductscategory.setErrormessage_forproducts(ProjectConstants.MESSAGEFOR_ITEMSARE_NOTMATCH)
				taggedchillerremark.setMissingchillerproductscategories(missingchillerproductscategory)
				missingcategorydata.setTaggedchillersremarks(taggedchillerremark)
				for(int j=0; j<ProjectConstants.missingshopdatainfo.size(); j++){
					if(ProjectConstants.missingshopdatainfo.get(j).getShopname().equalsIgnoreCase(ProjectConstants.CURRENTVISITING_SHOPNAME)) {
						ProjectConstants.missingshopdatainfo.get(j).setMissingCategoriesData(missingcategorydata, assettype)
						break
					}
					else{
					}
				}
			}
		}
		else if(expectedproductslist.size() < displayproductslist.size()){
			ArrayList<String> products = new ArrayList<String>()
			for(int i=0; i<displayproductslist.size(); i++){
				boolean match = false
				for(int j=0; j<expectedproductslist.size(); j++){
					if(displayproductslist.get(i).equalsIgnoreCase(expectedproductslist.get(j).getProduct())){
						match = true
						break
					}
				}
				if(match == false){
					products.add(displayproductslist.get(i))
				}
				else{
				}
			}
			TaggedChillersRemark taggedchillerremark = new TaggedChillersRemark()
			MissingCategoryData missingcategorydata = new MissingCategoryData()
			MissingChillerProductsCategoryData missingchillerproductscategory = new MissingChillerProductsCategoryData()
			missingcategorydata.setMaincategory(ProjectConstants.CURRENTVISITING_MAINCATEGORY)
			taggedchillerremark.setFirstvisit_chillertype(ProjectConstants.CURRENTVISITING_CHILLERTYPE)
			taggedchillerremark.setFirstvisit_chillerremark(ProjectConstants.CURRENTVISITING_CHILLERREMARK)
			missingchillerproductscategory.setProductcategory(ProjectConstants.CURRENTVISITING_PRODUCTCATEGORY)
			missingchillerproductscategory.setProducts(products)
			missingchillerproductscategory.setErrormessage_forproducts(ProjectConstants.MESSAGEFOR_ITEMSARE_MORE)
			taggedchillerremark.setMissingchillerproductscategories(missingchillerproductscategory)
			missingcategorydata.setTaggedchillersremarks(taggedchillerremark)
			for(int j=0; j<ProjectConstants.missingshopdatainfo.size(); j++){
				if(ProjectConstants.missingshopdatainfo.get(j).getShopname().equalsIgnoreCase(ProjectConstants.CURRENTVISITING_SHOPNAME)) {
					ProjectConstants.missingshopdatainfo.get(j).setMissingCategoriesData(missingcategorydata, assettype)
					break
				}
				else{
				}
			}
		}
		else if(expectedproductslist.size() > displayproductslist.size()){
			ArrayList<String> products = new ArrayList<String>()
			for(int i=0; i<expectedproductslist.size(); i++){
				boolean match = false
				for(int j=0; j<displayproductslist.size(); j++){
					if(expectedproductslist.get(i).toString().equalsIgnoreCase(displayproductslist.get(j).toString())){
						match = true
						break
					}
				}
				if(match == false){
					products.add(expectedproductslist.get(i).getProduct())
				}
				else{
				}
			}
			TaggedChillersRemark taggedchillerremark = new TaggedChillersRemark()
			MissingCategoryData missingcategorydata = new MissingCategoryData()
			MissingChillerProductsCategoryData missingchillerproductscategory = new MissingChillerProductsCategoryData()
			missingcategorydata.setMaincategory(ProjectConstants.CURRENTVISITING_MAINCATEGORY)
			taggedchillerremark.setFirstvisit_chillertype(ProjectConstants.CURRENTVISITING_CHILLERTYPE)
			taggedchillerremark.setFirstvisit_chillerremark(ProjectConstants.CURRENTVISITING_CHILLERREMARK)
			missingchillerproductscategory.setProductcategory(ProjectConstants.CURRENTVISITING_PRODUCTCATEGORY)
			missingchillerproductscategory.setProducts(products)
			missingchillerproductscategory.setErrormessage_forproducts(ProjectConstants.MESSAGEFOR_ITEMSARE_MISSING)
			taggedchillerremark.setMissingchillerproductscategories(missingchillerproductscategory)
			missingcategorydata.setTaggedchillersremarks(taggedchillerremark)
			for(int j=0; j<ProjectConstants.missingshopdatainfo.size(); j++){
				if(ProjectConstants.missingshopdatainfo.get(j).getShopname().equalsIgnoreCase(ProjectConstants.CURRENTVISITING_SHOPNAME)) {
					ProjectConstants.missingshopdatainfo.get(j).setMissingCategoriesData(missingcategorydata, assettype)
					break
				}
				else{
				}
			}
		}
		else{
			String message = "Main Category: "+ProjectConstants.CURRENTVISITING_MAINCATEGORY+"\nProduct Category: "+ProjectConstants.CURRENTVISITING_PRODUCTCATEGORY+"\n"+ProjectConstants.MESSAGEFOR_DISPLAYEDPRODUCTSARE_EQUAL
			KeywordUtil.logInfo(message)
		}
		VisitedCategoryData visitedcategorydata = new VisitedCategoryData()
		TaggedChillersRemark taggedchillerremarks = new TaggedChillersRemark()
		VisitedChillerProductsCategoryData visitedchillerproductscategory = new VisitedChillerProductsCategoryData()
		visitedcategorydata.setMaincategory(ProjectConstants.CURRENTVISITING_MAINCATEGORY)
		if(ProjectConstants.SCENARIO.equalsIgnoreCase("first visit")){
			taggedchillerremarks.setFirstvisit_chillertype(ProjectConstants.CURRENTVISITING_CHILLERTYPE)
			taggedchillerremarks.setFirstvisit_chillerremark(ProjectConstants.CURRENTVISITING_CHILLERREMARK)
		}
		else{
			taggedchillerremarks.setOverwrite_chillertype(ProjectConstants.CURRENTVISITING_CHILLERTYPE)
			taggedchillerremarks.setOverwrite_chillerremark(ProjectConstants.CURRENTVISITING_CHILLERREMARK)
		}
		visitedchillerproductscategory.setProductCategory(ProjectConstants.CURRENTVISITING_PRODUCTCATEGORY)
		visitedchillerproductscategory.setShopproductsdata(shopproductsdata)
		taggedchillerremarks.setVisitedchillerproductscategories(visitedchillerproductscategory)
		visitedcategorydata.setTaggedchillersremark(taggedchillerremarks)
		for(int i=0; i< ProjectConstants.visitedshopdatainfo.size(); i++){
			if(ProjectConstants.visitedshopdatainfo.get(i).getShopname().equals(ProjectConstants.CURRENTVISITING_SHOPNAME)){
				VisitedShopDataInfo visitedshopdata = ProjectConstants.visitedshopdatainfo.get(i)
				ArrayList<VisitedCategoryData> visitedcategoriesdata = visitedshopdata.getVisitedcategoriesdata()
				if(visitedcategoriesdata.size() != 0){
					boolean maincategory_flag = false
					for(int k=0; k<visitedcategoriesdata.size(); k++){
						VisitedCategoryData visitedcategorydatainfo = visitedcategoriesdata.get(k)
						if(visitedcategorydatainfo.getMaincategory().equals(visitedcategorydata.getMaincategory())){
							maincategory_flag = true
							ArrayList<TaggedChillersRemark> taggedchillersremarks = visitedcategorydatainfo.getTaggedchillersremark()
							boolean chiller_type_remark_flag = false
							for(int p=0; p<taggedchillersremarks.size(); p++){
								TaggedChillersRemark taggedchillerremarkinfo = taggedchillersremarks.get(p)
								if(ProjectConstants.SCENARIO.equalsIgnoreCase("first visit")){
									taggedchillerremarkinfo.setFirstvisit_chillertype(ProjectConstants.CURRENTVISITING_CHILLERTYPE)
									taggedchillerremarkinfo.setFirstvisit_chillerremark(ProjectConstants.CURRENTVISITING_CHILLERREMARK)
								}
								else{
									taggedchillerremarkinfo.setOverwrite_chillertype(ProjectConstants.CURRENTVISITING_CHILLERTYPE)
									taggedchillerremarkinfo.setOverwrite_chillerremark(ProjectConstants.CURRENTVISITING_CHILLERREMARK)
								}
								if((taggedchillerremarkinfo.getFirstvisit_chillertype().equals(taggedchillerremarks.getFirstvisit_chillertype()) && taggedchillerremarkinfo.getFirstvisit_chillerremark().equals(taggedchillerremarks.getFirstvisit_chillerremark())) || (taggedchillerremarkinfo.getFirstvisit_chillertype().equals(taggedchillerremarks.getOverwrite_chillertype()) && taggedchillerremarkinfo.getFirstvisit_chillerremark().equals(taggedchillerremarks.getOverwrite_chillerremark()))){
									chiller_type_remark_flag = true
									ArrayList<VisitedChillerProductsCategoryData> visitedchillerproductcategorydata = taggedchillerremarkinfo.getVisitedchillerproductscategories()
									boolean productcategory_flag = false
									for(int y=0; y< visitedchillerproductcategorydata.size(); y++){
										VisitedChillerProductsCategoryData visitedchillerproductscategoryinfo = visitedchillerproductcategorydata.get(y)
										if(visitedchillerproductscategoryinfo.getProductCategory().equals(visitedchillerproductscategory.getProductCategory())){
											productcategory_flag = true
											ArrayList<ShopProductsData> shopproductdata = visitedchillerproductscategoryinfo.getShopproductsdata()
											for(int z=0; z<shopproductdata.size(); z++){
												ShopProductsData existingproductsdata = shopproductdata.get(z)
												for(int x=0; x< shopproductsdata.size(); x++){
													ShopProductsData newproductsdatainfo = shopproductsdata.get(x)
													if(existingproductsdata.getProduct().equals(newproductsdatainfo.getProduct())){
														if(ProjectConstants.SCENARIO.equals("first visit")){
															if(assettype.equals("Facing")){
																existingproductsdata.setFacingdata(newproductsdatainfo.getFacingdata())
																break
															}
															else if(assettype.equals("Stock Count")){
																existingproductsdata.setStocktakingdata_stockcountdata(newproductsdatainfo.getStocktakingdata_stockcountdata())
																break
															}
															else if(assettype.equals("Depth")){
																existingproductsdata.setDepthdata(newproductsdatainfo.getDepthdata())
																break
															}
															else{}
														}
														else{
															if(assettype.equals("Facing")){
																existingproductsdata.setOverwritefacingdata(newproductsdatainfo.getOverwritefacingdata())
																break
															}
															else if(assettype.equals("Depth")){
																existingproductsdata.setOverwritedepthdata(newproductsdatainfo.getOverwritedepthdata())
																break
															}
															else if(assettype.equals("Stock Count")){
																existingproductsdata.setOverwritestocktakingdata_stockcountdata(newproductsdatainfo.getOverwritestocktakingdata_stockcountdata())
																break
															}
															else{
															}
														}
													}
												}
											}
										}
									}
									if(productcategory_flag == false){
										taggedchillerremarkinfo.setVisitedchillerproductscategories(visitedchillerproductscategory)
										break
									}
								}
							}
							if(chiller_type_remark_flag == false){
								visitedcategorydatainfo.setTaggedchillersremark(taggedchillerremarks)
								break
							}
						}
					}
					if(maincategory_flag == false){
						ProjectConstants.visitedshopdatainfo.get(i).setVisitedcategoriesdata(visitedcategorydata)
						break
					}
					else{
					}
				}
				else{
					ProjectConstants.visitedshopdatainfo.get(i).setVisitedcategoriesdata(visitedcategorydata)
					break
				}
			}
		}
	}
	@Keyword
	def VisitChillerNotAvailableProductsData(int columnindex, String assettype){
		ArrayList<ShopProductsData> shopproductsdata = new ArrayList<ShopProductsData>()
		int index = 0
		XSSFSheet channelproductssheet = LoadDataKeywords.loadChannelProductsSheet()
		ArrayList<String> displayproductslist = new ArrayList<String>()
		ArrayList<LoadProductsData> expectedproductslist = LoadDataKeywords.loadChillerNotAvailableProductsList(channelproductssheet, columnindex)
		int totalproducts = ProjectConstants.DRIVER.findElementsByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ScrollView[1]/android.widget.LinearLayout[1]/*").size()
		for(int i=1; i<totalproducts; i=i+3){
			ShopProductsData productsdata = new ShopProductsData()
			boolean flag = false
			index = index + 1
			MobileElement selectedproduct = ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ScrollView[1]/android.widget.LinearLayout[1]/android.widget.TextView["+index+"]")
			String selectedproductname = selectedproduct.getText()
			productsdata.setProduct(selectedproductname)
			displayproductslist.add(selectedproductname)
			for(int j=0; j<expectedproductslist.size(); j++){
				LoadProductsData channelproduct = expectedproductslist.get(j)
				String productname = channelproduct.getProduct()
				if(selectedproductname.equalsIgnoreCase(productname)){
					flag = true
					String productquantity = channelproduct.getProduct_data()
					MobileElement selectedproducttextfield = ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ScrollView[1]/android.widget.LinearLayout[1]/android.widget.LinearLayout["+index+"]/android.widget.EditText[1]")
					selectedproducttextfield.setValue(productquantity)
					if(ProjectConstants.SCENARIO.equals("first visit")){
						if(assettype.equalsIgnoreCase("Facing")){
							productsdata.setFacingdata(productquantity)
						}
						else if(assettype.equalsIgnoreCase("Stock Taking")){
							productsdata.setStocktakingdata_stockcountdata(productquantity)
						}
						else{}
					}
					else{
						if(assettype.equalsIgnoreCase("Facing")){
							productsdata.setOverwritefacingdata(productquantity)
						}
						else if(assettype.equalsIgnoreCase("Stock Taking")){
							productsdata.setOverwritestocktakingdata_stockcountdata(productquantity)
						}
						else{
						}
					}
					Mobile.hideKeyboard()
					break
				}
				else{
				}
			}
			if(flag == false){
				MobileElement selectedproducttextfield = ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ScrollView[1]/android.widget.LinearLayout[1]/android.widget.LinearLayout["+index+"]/android.widget.EditText[1]")
				selectedproducttextfield.setValue("0000")
				if(ProjectConstants.SCENARIO.equals("first visit")){
					if(assettype.equalsIgnoreCase("Facing")){
						productsdata.setFacingdata("0000")
					}
					else if(assettype.equalsIgnoreCase("Stock Taking")){
						productsdata.setStocktakingdata_stockcountdata("0000")
					}
					else{}
				}
				else{
					if(assettype.equalsIgnoreCase("Facing")){
						productsdata.setOverwritefacingdata("0000")
					}
					else if(assettype.equalsIgnoreCase("Stock Taking")){
						productsdata.setOverwritestocktakingdata_stockcountdata("0000")
					}
					else{
					}
				}
				Mobile.hideKeyboard()
			}
			shopproductsdata.add(productsdata)
		}
		totalproducts = ProjectConstants.DRIVER.findElementsByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ScrollView[1]/android.widget.LinearLayout[1]/*").size()
		if(totalproducts >= 16){
			while(true){
				ShopProductsData productsdata = new ShopProductsData()
				int xlocation = CommonKeywords.getXPoint()
				MobileElement lastproductbeforeswipe = ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ScrollView[1]/android.widget.LinearLayout[1]/android.widget.TextView[5]")
				String lastproductnamebeforeswipe = lastproductbeforeswipe.getText()
				Mobile.swipe(xlocation, 359, xlocation, 250)
				MobileElement lastproductafterswipe = ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ScrollView[1]/android.widget.LinearLayout[1]/android.widget.TextView[5]")
				String lastproductnameafterswipe = lastproductafterswipe.getText()
				if(lastproductnamebeforeswipe.equalsIgnoreCase(lastproductnameafterswipe)){
					break
				}
				else{
					boolean flag = false
					displayproductslist.add(lastproductnameafterswipe)
					productsdata.setProduct(lastproductnameafterswipe)
					for(int j=0; j<expectedproductslist.size(); j++){
						LoadProductsData channelproduct = expectedproductslist.get(j)
						String productname = channelproduct.getProduct()
						if(lastproductnameafterswipe.equalsIgnoreCase(productname)){
							flag = true
							String productquantity = channelproduct.getProduct_data()
							MobileElement selectedproducttextfield = ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ScrollView[1]/android.widget.LinearLayout[1]/android.widget.LinearLayout[6]/android.widget.EditText[1]")
							selectedproducttextfield.setValue(productquantity)
							if(ProjectConstants.SCENARIO.equals("first visit")){
								if(assettype.equalsIgnoreCase("Facing")){
									productsdata.setFacingdata(productquantity)
								}
								else if(assettype.equalsIgnoreCase("Stock Taking")){
									productsdata.setStocktakingdata_stockcountdata(productquantity)
								}
								else{}
							}
							else{
								if(assettype.equalsIgnoreCase("Facing")){
									productsdata.setOverwritefacingdata(productquantity)
								}
								else if(assettype.equalsIgnoreCase("Stock Taking")){
									productsdata.setOverwritestocktakingdata_stockcountdata(productquantity)
								}
								else{
								}
							}
							Mobile.hideKeyboard()
							break
						}
						else{
						}
					}
					if(flag == false){
						MobileElement selectedproducttextfield = ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ScrollView[1]/android.widget.LinearLayout[1]/android.widget.LinearLayout["+index+"]/android.widget.EditText[1]")
						selectedproducttextfield.setValue("0000")
						if(ProjectConstants.SCENARIO.equals("first visit")){
							if(assettype.equalsIgnoreCase("Facing")){
								productsdata.setFacingdata("0000")
							}
							else if(assettype.equalsIgnoreCase("Stock Taking")){
								productsdata.setStocktakingdata_stockcountdata("0000")
							}
							else{}
						}
						else{
							if(assettype.equalsIgnoreCase("Facing")){
								productsdata.setOverwritefacingdata("0000")
							}
							else if(assettype.equalsIgnoreCase("Stock Taking")){
								productsdata.setOverwritestocktakingdata_stockcountdata("0000")
							}
							else{
							}
						}
						Mobile.hideKeyboard()
					}
					shopproductsdata.add(productsdata)
				}
			}
		}
		if(expectedproductslist.size() == displayproductslist.size()){
			ArrayList<String> products = new ArrayList<String>()
			for(int i=0; i<displayproductslist.size(); i++){
				boolean match = false
				for(int j=0; j<expectedproductslist.size(); j++){
					if(displayproductslist.get(i).equalsIgnoreCase(expectedproductslist.get(j).getProduct())){
						match = true
						break
					}
				}
				if(match == false){
					products.add(displayproductslist.get(i))
				}
				else{
				}
			}
			if(!products.isEmpty()){
				TaggedChillersRemark taggedchillerremark = new TaggedChillersRemark()
				MissingCategoryData missingcategorydata = new MissingCategoryData()
				MissingChillerProductsCategoryData missingchillerproductscategory = new MissingChillerProductsCategoryData()
				missingcategorydata.setMaincategory(ProjectConstants.CURRENTVISITING_MAINCATEGORY)
				taggedchillerremark.setFirstvisit_chillertype(ProjectConstants.CURRENTVISITING_CHILLERTYPE)
				taggedchillerremark.setFirstvisit_chillerremark(ProjectConstants.CURRENTVISITING_CHILLERREMARK)
				missingchillerproductscategory.setProductcategory(ProjectConstants.CURRENTVISITING_PRODUCTCATEGORY)
				missingchillerproductscategory.setProducts(products)
				missingchillerproductscategory.setErrormessage_forproducts(ProjectConstants.MESSAGEFOR_ITEMSARE_NOTMATCH)
				taggedchillerremark.setMissingchillerproductscategories(missingchillerproductscategory)
				missingcategorydata.setTaggedchillersremarks(taggedchillerremark)
				for(int j=0; j<ProjectConstants.missingshopdatainfo.size(); j++){
					if(ProjectConstants.missingshopdatainfo.get(j).getShopname().equalsIgnoreCase(ProjectConstants.CURRENTVISITING_SHOPNAME)) {
						ProjectConstants.missingshopdatainfo.get(j).setMissingCategoriesData(missingcategorydata, assettype)
						break
					}
					else{
					}
				}
			}
		}
		else if(expectedproductslist.size() < displayproductslist.size()){
			ArrayList<String> products = new ArrayList<String>()
			for(int i=0; i<displayproductslist.size(); i++){
				boolean match = false
				for(int j=0; j<expectedproductslist.size(); j++){
					if(displayproductslist.get(i).equalsIgnoreCase(expectedproductslist.get(j).getProduct())){
						match = true
						break
					}
				}
				if(match == false){
					products.add(displayproductslist.get(i))
				}
				else{
				}
			}
			TaggedChillersRemark taggedchillerremark = new TaggedChillersRemark()
			MissingCategoryData missingcategorydata = new MissingCategoryData()
			MissingChillerProductsCategoryData missingchillerproductscategory = new MissingChillerProductsCategoryData()
			missingcategorydata.setMaincategory(ProjectConstants.CURRENTVISITING_MAINCATEGORY)
			taggedchillerremark.setFirstvisit_chillertype(ProjectConstants.CURRENTVISITING_CHILLERTYPE)
			taggedchillerremark.setFirstvisit_chillerremark(ProjectConstants.CURRENTVISITING_CHILLERREMARK)
			missingchillerproductscategory.setProductcategory(ProjectConstants.CURRENTVISITING_PRODUCTCATEGORY)
			missingchillerproductscategory.setProducts(products)
			missingchillerproductscategory.setErrormessage_forproducts(ProjectConstants.MESSAGEFOR_ITEMSARE_MORE)
			taggedchillerremark.setMissingchillerproductscategories(missingchillerproductscategory)
			missingcategorydata.setTaggedchillersremarks(taggedchillerremark)
			for(int j=0; j<ProjectConstants.missingshopdatainfo.size(); j++){
				if(ProjectConstants.missingshopdatainfo.get(j).getShopname().equalsIgnoreCase(ProjectConstants.CURRENTVISITING_SHOPNAME)) {
					ProjectConstants.missingshopdatainfo.get(j).setMissingCategoriesData(missingcategorydata, assettype)
					break
				}
				else{
				}
			}
		}
		else if(expectedproductslist.size() > displayproductslist.size()){
			ArrayList<String> products = new ArrayList<String>()
			for(int i=0; i<expectedproductslist.size(); i++){
				boolean match = false
				for(int j=0; j<displayproductslist.size(); j++){
					if(expectedproductslist.get(i).toString().equalsIgnoreCase(displayproductslist.get(j).toString())){
						match = true
						break
					}
				}
				if(match == false){
					products.add(expectedproductslist.get(i).getProduct())
				}
				else{
				}
			}
			TaggedChillersRemark taggedchillerremark = new TaggedChillersRemark()
			MissingCategoryData missingcategorydata = new MissingCategoryData()
			MissingChillerProductsCategoryData missingchillerproductscategory = new MissingChillerProductsCategoryData()
			missingcategorydata.setMaincategory(ProjectConstants.CURRENTVISITING_MAINCATEGORY)
			taggedchillerremark.setFirstvisit_chillertype(ProjectConstants.CURRENTVISITING_CHILLERTYPE)
			taggedchillerremark.setFirstvisit_chillerremark(ProjectConstants.CURRENTVISITING_CHILLERREMARK)
			missingchillerproductscategory.setProductcategory(ProjectConstants.CURRENTVISITING_PRODUCTCATEGORY)
			missingchillerproductscategory.setProducts(products)
			missingchillerproductscategory.setErrormessage_forproducts(ProjectConstants.MESSAGEFOR_ITEMSARE_MISSING)
			taggedchillerremark.setMissingchillerproductscategories(missingchillerproductscategory)
			missingcategorydata.setTaggedchillersremarks(taggedchillerremark)
			for(int j=0; j<ProjectConstants.missingshopdatainfo.size(); j++){
				if(ProjectConstants.missingshopdatainfo.get(j).getShopname().equalsIgnoreCase(ProjectConstants.CURRENTVISITING_SHOPNAME)) {
					ProjectConstants.missingshopdatainfo.get(j).setMissingCategoriesData(missingcategorydata, assettype)
					break
				}
				else{
				}
			}
		}
		else{
			String message = "Main Category: "+ProjectConstants.CURRENTVISITING_MAINCATEGORY+"\nProduct Category: "+ProjectConstants.CURRENTVISITING_PRODUCTCATEGORY+"\n"+ProjectConstants.MESSAGEFOR_DISPLAYEDPRODUCTSARE_EQUAL
			KeywordUtil.logInfo(message)
		}
		VisitedCategoryData visitedcategorydata = new VisitedCategoryData()
		TaggedChillersRemark taggedchillerremarks = new TaggedChillersRemark()
		VisitedChillerProductsCategoryData visitedchillerproductscategory = new VisitedChillerProductsCategoryData()
		visitedcategorydata.setMaincategory(ProjectConstants.CURRENTVISITING_MAINCATEGORY)
		if(ProjectConstants.SCENARIO.equalsIgnoreCase("first visit")){
			taggedchillerremarks.setFirstvisit_chillertype(ProjectConstants.CURRENTVISITING_CHILLERTYPE)
			taggedchillerremarks.setFirstvisit_chillerremark(ProjectConstants.CURRENTVISITING_CHILLERREMARK)
		}
		else{
			taggedchillerremarks.setOverwrite_chillertype(ProjectConstants.CURRENTVISITING_CHILLERTYPE)
			taggedchillerremarks.setOverwrite_chillerremark(ProjectConstants.CURRENTVISITING_CHILLERREMARK)
		}
		visitedchillerproductscategory.setProductCategory(ProjectConstants.CURRENTVISITING_PRODUCTCATEGORY)
		visitedchillerproductscategory.setShopproductsdata(shopproductsdata)
		taggedchillerremarks.setVisitedchillerproductscategories(visitedchillerproductscategory)
		visitedcategorydata.setTaggedchillersremark(taggedchillerremarks)
		for(int i=0; i< ProjectConstants.visitedshopdatainfo.size(); i++){
			if(ProjectConstants.visitedshopdatainfo.get(i).getShopname().equals(ProjectConstants.CURRENTVISITING_SHOPNAME)){
				VisitedShopDataInfo visitedshopdata = ProjectConstants.visitedshopdatainfo.get(i)
				ArrayList<VisitedCategoryData> visitedcategoriesdata = visitedshopdata.getVisitedcategoriesdata()
				if(visitedcategoriesdata.size() != 0){
					boolean maincategory_flag = false
					for(int k=0; k<visitedcategoriesdata.size(); k++){
						VisitedCategoryData visitedcategorydatainfo = visitedcategoriesdata.get(k)
						if(visitedcategorydatainfo.getMaincategory().equals(visitedcategorydata.getMaincategory())){
							maincategory_flag = true
							ArrayList<TaggedChillersRemark> taggedchillersremarks = visitedcategorydatainfo.getTaggedchillersremark()
							boolean chiller_type_remark_flag = false
							for(int p=0; p<taggedchillersremarks.size(); p++){
								TaggedChillersRemark taggedchillerremarkinfo = taggedchillersremarks.get(p)
								if(ProjectConstants.SCENARIO.equalsIgnoreCase("first visit")){
									taggedchillerremarkinfo.setFirstvisit_chillertype(ProjectConstants.CURRENTVISITING_CHILLERTYPE)
									taggedchillerremarkinfo.setFirstvisit_chillerremark(ProjectConstants.CURRENTVISITING_CHILLERREMARK)
								}
								else{
									taggedchillerremarkinfo.setOverwrite_chillertype(ProjectConstants.CURRENTVISITING_CHILLERTYPE)
									taggedchillerremarkinfo.setOverwrite_chillerremark(ProjectConstants.CURRENTVISITING_CHILLERREMARK)
								}
								if(taggedchillerremarkinfo.getFirstvisit_chillertype().equals(taggedchillerremarks.getFirstvisit_chillertype()) && taggedchillerremarkinfo.getFirstvisit_chillerremark().equals(taggedchillerremarks.getFirstvisit_chillerremark())){
									chiller_type_remark_flag = true
									ArrayList<VisitedChillerProductsCategoryData> visitedchillerproductcategorydata = taggedchillerremarkinfo.getVisitedchillerproductscategories()
									boolean productcategory_flag = false
									for(int y=0; y< visitedchillerproductcategorydata.size(); y++){
										VisitedChillerProductsCategoryData visitedchillerproductscategoryinfo = visitedchillerproductcategorydata.get(y)
										if(visitedchillerproductscategoryinfo.getProductCategory().equals(visitedchillerproductscategory.getProductCategory())){
											productcategory_flag = true
											ArrayList<ShopProductsData> shopproductdata = visitedchillerproductscategoryinfo.getShopproductsdata()
											for(int z=0; z<shopproductdata.size(); z++){
												ShopProductsData existingproductsdata = shopproductdata.get(z)
												for(int x=0; x< shopproductsdata.size(); x++){
													ShopProductsData newproductsdatainfo = shopproductsdata.get(x)
													if(existingproductsdata.getProduct().equals(newproductsdatainfo.getProduct())){
														if(ProjectConstants.SCENARIO.equals("first visit")){
															if(assettype.equals("Facing")){
																existingproductsdata.setFacingdata(newproductsdatainfo.getFacingdata())
																break
															}
															else if(assettype.equals("Stock Taking")){
																existingproductsdata.setStocktakingdata_stockcountdata(newproductsdatainfo.getStocktakingdata_stockcountdata())
																break
															}
															else{}
														}
														else{
															if(assettype.equals("Facing")){
																existingproductsdata.setOverwritefacingdata(newproductsdatainfo.getOverwritefacingdata())
																break
															}
															else if(assettype.equals("Stock Taking")){
																existingproductsdata.setOverwritestocktakingdata_stockcountdata(newproductsdatainfo.getOverwritestocktakingdata_stockcountdata())
																break
															}
															else{
															}
														}
													}
												}
											}
										}
									}
									if(productcategory_flag == false){
										taggedchillerremarkinfo.setVisitedchillerproductscategories(visitedchillerproductscategory)
									}
								}
							}
							if(chiller_type_remark_flag == false){
								visitedcategorydatainfo.setTaggedchillersremark(taggedchillerremarks)
							}
						}
					}
					if(maincategory_flag == false){
						ProjectConstants.visitedshopdatainfo.get(i).setVisitedcategoriesdata(visitedcategorydata)
						break
					}
					else{
					}
				}
				else{
					ProjectConstants.visitedshopdatainfo.get(i).setVisitedcategoriesdata(visitedcategorydata)
					break
				}
			}
		}
	}
}
