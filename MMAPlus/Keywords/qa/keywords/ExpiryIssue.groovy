package qa.keywords

import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import java.time.Duration

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.checkpoint.Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testcase.TestCase
import com.kms.katalon.core.testdata.TestData
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

import internal.GlobalVariable
import io.appium.java_client.MobileElement
import io.appium.java_client.TouchAction
import qa.constants.ProjectConstants
import qa.struct.ExpiryIssueProduct
import qa.struct.LoadProductsData
import qa.struct.MissingCategoryData
import qa.struct.ProductCategoryWithProducts
import qa.struct.ShopProductsData
import qa.struct.UnmatchedItems
import qa.struct.VisitedCategoryData
import qa.struct.VisitedShopDataInfo

public class ExpiryIssue {

	@Keyword
	def visitExpiryIssueRemark(){
		int expirecategories = ProjectConstants.DRIVER.findElementsByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ListView[1]/*").size()
		for(int i=2; i<= expirecategories; i++){
			MobileElement expirecategory = ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout["+i+"]/android.widget.TextView[1]")
			String categorytext = expirecategory.getText()
			ProjectConstants.CURRENTVISITING_CATEGORYREMARK = categorytext
			expirecategory.click()
			Mobile.callTestCase(findTestCase("ShopOpen/ExpiryIssue/VisitProductCategory"), null)
			Mobile.verifyElementText(findTestObject('ShopOpen/ExpiryIssue/Validate_ExpiredCategoryScreen', [('package') : ProjectConstants.PACKAGENAME]), 'Expire Category')
		}
	}
	@Keyword
	def validateProductCategory(){
		TouchAction touchaction = new TouchAction(ProjectConstants.DRIVER)
		int index
		ArrayList<String> displayedproductcategories = new ArrayList<String>()
		ArrayList<String> expectedproductcategories = LoadDataKeywords.loadExpiryIssueSubCategories()
		MobileElement listcontainer = ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ListView[1]")
		ArrayList<MobileElement> productcategories = listcontainer.findElementsByClassName("android.widget.TextView")
		for(int i=0; i< productcategories.size(); i++){
			MobileElement productcategory = productcategories.get(i)
			displayedproductcategories.add(productcategory.getText())
		}
		while(true){
			productcategories = listcontainer.findElementsByClassName("android.widget.TextView")
			MobileElement lastitembeforeswipe = productcategories.get((productcategories.size()-1))
			String lastitemnamebeforeswipe = lastitembeforeswipe.getText()
			// swipe using touch action from element to element
			index = productcategories.size()
			MobileElement startpoint = ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout["+index+"]")
			MobileElement endpoint = ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout[1]")
			touchaction.longPress(startpoint).waitAction(Duration.ofMillis(500)).moveTo(endpoint).release().perform()
			Thread.sleep(500)
			productcategories = listcontainer.findElementsByClassName("android.widget.TextView")
			MobileElement lastitemafterswipe = productcategories.get((productcategories.size()-1))
			String lastitemnameafterswipe = lastitemafterswipe.getText()
			if(lastitemnamebeforeswipe.equalsIgnoreCase(lastitemnameafterswipe)){
				break
			}
			else{
				productcategories = listcontainer.findElementsByClassName("android.widget.TextView")
				for(int i=0; i< productcategories.size(); i++){
					MobileElement category = productcategories.get(i)
					displayedproductcategories.add(category.getText())
				}
			}
		}
		ArrayList<String> displayedproductcategorieslist = new HashSet<String>(displayedproductcategories)
		ArrayList<String> expectedproductcategorieslist = new HashSet<String>(expectedproductcategories)
		UnmatchedItems unmatcheditems = CompareDataKeywords.compareLists(expectedproductcategorieslist, displayedproductcategorieslist)
		if(unmatcheditems.getStatus() == 2){
			MissingCategoryData missingcategorydata = new MissingCategoryData()
			missingcategorydata.setMaincategory(ProjectConstants.CURRENTVISITING_MAINCATEGORY)
			missingcategorydata.setCategoryremark(ProjectConstants.CURRENTVISITING_CATEGORYREMARK)
			missingcategorydata.setProductcategories(unmatcheditems.getItems())
			missingcategorydata.setProductcategories_errormessage(ProjectConstants.MESSAGEFOR_ITEMSARE_NOTMATCH)
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
			missingcategorydata.setMaincategory(ProjectConstants.CURRENTVISITING_MAINCATEGORY)
			missingcategorydata.setCategoryremark(ProjectConstants.CURRENTVISITING_CATEGORYREMARK)
			missingcategorydata.setProductcategories(unmatcheditems.getItems())
			missingcategorydata.setProductcategories_errormessage(ProjectConstants.MESSAGEFOR_ITEMSARE_MORE)
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
			missingcategorydata.setMaincategory(ProjectConstants.CURRENTVISITING_MAINCATEGORY)
			missingcategorydata.setCategoryremark(ProjectConstants.CURRENTVISITING_CATEGORYREMARK)
			missingcategorydata.setProductcategories(unmatcheditems.getItems())
			missingcategorydata.setProductcategories_errormessage(ProjectConstants.MESSAGEFOR_ITEMSARE_MISSING)
			for(int j=0; j<ProjectConstants.missingshopdatainfo.size(); j++){
				if(ProjectConstants.missingshopdatainfo.get(j).getShopname().equalsIgnoreCase(ProjectConstants.CURRENTVISITING_SHOPNAME)) {
					ProjectConstants.missingshopdatainfo.get(j).setMissingCategoriesData(missingcategorydata, "")
					break
				}
				else{
				}
			}
		}
	}
	@Keyword
	def visitProductCategories(){
		int visitedcategories
		MobileElement listcontainer = ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ListView[1]")
		ArrayList<MobileElement> productcategories = listcontainer.findElementsByClassName("android.widget.TextView")
		if(ProjectConstants.CURRENTVISITING_CATEGORYREMARK.equalsIgnoreCase("Expired Product")){
			for(int i=0; i< 3; i++){
				MobileElement productcategory = productcategories.get(i)
				ProjectConstants.CURRENTVISITING_PRODUCTCATEGORY = productcategory.getText()
				productcategory.click()
				if(ProjectConstants.SCENARIO.equalsIgnoreCase("first visit")){
					Mobile.callTestCase(findTestCase("ShopOpen/ExpiryIssue/ExpiredProduct/VisitProduct"), null)
				}
				else{
					Mobile.callTestCase(findTestCase(""), null)
				}
				Mobile.verifyElementText(findTestObject('ShopOpen/ExpiryIssue/Validate_ProductCategoryScreen', [('package') : ProjectConstants.PACKAGENAME]), 'Expire CATEGORY')
			}
		}
		else if(ProjectConstants.CURRENTVISITING_CATEGORYREMARK.equalsIgnoreCase("Shortly Expire")){
			for(int i=3; i< 5; i++){
				MobileElement productcategory = productcategories.get(i)
				ProjectConstants.CURRENTVISITING_PRODUCTCATEGORY = productcategory.getText()
				productcategory.click()
				if(ProjectConstants.SCENARIO.equalsIgnoreCase("first visit")){
					Mobile.callTestCase(findTestCase("Test Cases/ShopOpen/ExpiryIssue/ShortlyExpire/VisitProduct"), null)
				}
				else{
					Mobile.callTestCase(findTestCase(""), null)
				}
				Mobile.verifyElementText(findTestObject('ShopOpen/ExpiryIssue/Validate_ProductCategoryScreen', [('package') : ProjectConstants.PACKAGENAME]), 'Expire CATEGORY')
			}
		}
	}
	@Keyword
	def validateProducts(){
		TouchAction touchaction = new TouchAction(ProjectConstants.DRIVER)
		int index
		ArrayList<String> displayedproducts = new ArrayList<String>()
		ArrayList<String> expectedproductslist = new ArrayList<String>()
		ArrayList<LoadProductsData> expectedproducts = LoadDataKeywords.loadExpiryIssueChannelProduct(ProjectConstants.CHANNEL_EXPIREDPRODUCT)
		MobileElement listcontainer = ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ListView[1]")
		ArrayList<MobileElement> products = listcontainer.findElementsByClassName("android.widget.TextView")
		for(int i=0; i< products.size(); i++){
			MobileElement product = products.get(i)
			displayedproducts.add(product.getText())
		}
		while(true){
			products = listcontainer.findElementsByClassName("android.widget.TextView")
			MobileElement lastitembeforeswipe = products.get((products.size()-1))
			String lastitemnamebeforeswipe = lastitembeforeswipe.getText()
			// swipe using touch action from element to element
			index = products.size()
			if(index > 1){
				MobileElement startpoint = ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout["+index+"]")
				MobileElement endpoint = ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout[1]")
				touchaction.longPress(startpoint).waitAction(Duration.ofMillis(500)).moveTo(endpoint).release().perform()
				Thread.sleep(500)
			}
			products = listcontainer.findElementsByClassName("android.widget.TextView")
			MobileElement lastitemafterswipe = products.get((products.size()-1))
			String lastitemnameafterswipe = lastitemafterswipe.getText()
			if(lastitemnamebeforeswipe.equalsIgnoreCase(lastitemnameafterswipe)){
				break
			}
			else{
				products = listcontainer.findElementsByClassName("android.widget.TextView")
				for(int i=0; i< products.size(); i++){
					MobileElement product = products.get(i)
					displayedproducts.add(product.getText())
				}
			}
		}
		ArrayList<String> displayedproductslist = new HashSet<String>(displayedproducts)
		for(int i=0; i< expectedproducts.size(); i++){
			LoadProductsData productdata = expectedproducts.get(i)
			expectedproductslist.add(productdata.getProduct())
		}
		UnmatchedItems unmatcheditems = CompareDataKeywords.compareLists(expectedproductslist, displayedproductslist)
		if(unmatcheditems.getStatus() == 2){
			MissingCategoryData missingcategorydata = new MissingCategoryData()
			missingcategorydata.setMaincategory(ProjectConstants.CURRENTVISITING_MAINCATEGORY)
			missingcategorydata.setCategoryremark(ProjectConstants.CURRENTVISITING_CATEGORYREMARK)
			missingcategorydata.setProductCategory(ProjectConstants.CURRENTVISITING_PRODUCTCATEGORY)
			missingcategorydata.setProducts(unmatcheditems.getItems())
			missingcategorydata.setProducts_errormessage(ProjectConstants.MESSAGEFOR_ITEMSARE_NOTMATCH)
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
			missingcategorydata.setMaincategory(ProjectConstants.CURRENTVISITING_MAINCATEGORY)
			missingcategorydata.setCategoryremark(ProjectConstants.CURRENTVISITING_CATEGORYREMARK)
			missingcategorydata.setProductCategory(ProjectConstants.CURRENTVISITING_PRODUCTCATEGORY)
			missingcategorydata.setProducts(unmatcheditems.getItems())
			missingcategorydata.setProducts_errormessage(ProjectConstants.MESSAGEFOR_ITEMSARE_MORE)
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
			missingcategorydata.setMaincategory(ProjectConstants.CURRENTVISITING_MAINCATEGORY)
			missingcategorydata.setCategoryremark(ProjectConstants.CURRENTVISITING_CATEGORYREMARK)
			missingcategorydata.setProductCategory(ProjectConstants.CURRENTVISITING_PRODUCTCATEGORY)
			missingcategorydata.setProducts(unmatcheditems.getItems())
			missingcategorydata.setProducts_errormessage(ProjectConstants.MESSAGEFOR_ITEMSARE_MISSING)
			for(int j=0; j<ProjectConstants.missingshopdatainfo.size(); j++){
				if(ProjectConstants.missingshopdatainfo.get(j).getShopname().equalsIgnoreCase(ProjectConstants.CURRENTVISITING_SHOPNAME)) {
					ProjectConstants.missingshopdatainfo.get(j).setMissingCategoriesData(missingcategorydata, "")
					break
				}
				else{
				}
			}
		}
	}
	//visit expired products from expiry issue category
	@Keyword
	def visitExpiredProducts(int columnindex){
		ArrayList<ShopProductsData> shopproductsdata = new ArrayList<ShopProductsData>()
		ArrayList<LoadProductsData> expectedproducts = LoadDataKeywords.loadExpiryIssueChannelProduct(columnindex)
		MobileElement listcontainer = ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ListView[1]")
		ArrayList<MobileElement> products = listcontainer.findElementsByClassName("android.widget.TextView")
		for(int i=0; i< products.size(); i++){
			ShopProductsData shopproductdata = new ShopProductsData()
			boolean flag = false
			MobileElement product = products.get(i)
			shopproductdata.setProduct(product.getText())
			for(int j=0; j< expectedproducts.size(); j++){
				LoadProductsData expectedproduct = expectedproducts.get(j)
				if(product.getText().equalsIgnoreCase(expectedproduct.getProduct())){
					product.click()
					if(ProjectConstants.CURRENTVISITING_CATEGORYREMARK.equalsIgnoreCase("Expired Product")){
						visitExpiredProductPopup(expectedproduct.getProduct_data())
					}
					else if(ProjectConstants.CURRENTVISITING_CATEGORYREMARK.equalsIgnoreCase("Shortly Expire")){
						visitShortlyExpiredProductPopup(expectedproduct.getProduct_data())
					}
					shopproductdata.setFacingdata(expectedproduct.getProduct_data())
					flag = true
					break
				}
			}
			if(!flag){
				product.click()
				if(ProjectConstants.CURRENTVISITING_CATEGORYREMARK.equalsIgnoreCase("Expired Product")){
					visitExpiredProductPopup("0000")
				}
				else if(ProjectConstants.CURRENTVISITING_CATEGORYREMARK.equalsIgnoreCase("Shortly Expire")){
					visitShortlyExpiredProductPopup("0000")
				}
				shopproductdata.setFacingdata("0000")
			}
			shopproductsdata.add(shopproductdata)
		}
		VisitedCategoryData visitedcategorydata = new VisitedCategoryData()
		ExpiryIssueProduct expiryissueproduct = new ExpiryIssueProduct()
		ProductCategoryWithProducts productcategorywithproducts = new ProductCategoryWithProducts()
		visitedcategorydata.setMaincategory(ProjectConstants.CURRENTVISITING_MAINCATEGORY)
		productcategorywithproducts.setProductcategory(ProjectConstants.CURRENTVISITING_PRODUCTCATEGORY)
		productcategorywithproducts.setShopproductsdata(shopproductsdata)
		expiryissueproduct.setRemark(ProjectConstants.CURRENTVISITING_CATEGORYREMARK)
		expiryissueproduct.setProductcategorywithproducts(productcategorywithproducts)
		visitedcategorydata.setExpiryissueproducts(expiryissueproduct)
		//set visited data
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
							ArrayList<ExpiryIssueProduct> expiryissueproductsinfo = visitedcategorydatainfo.getExpiryissueproducts()
							if(expiryissueproductsinfo != null){
								boolean expiryproduct_flag = false
								for(int m=0; m< expiryissueproductsinfo.size(); m++){
									ExpiryIssueProduct expiryissueproductinfo = expiryissueproductsinfo.get(m)
									if(ProjectConstants.CURRENTVISITING_CATEGORYREMARK.equalsIgnoreCase(expiryissueproductinfo.getRemark())){
										expiryproduct_flag = true
										ArrayList<ProductCategoryWithProducts> productcategorywithproductsinfo = expiryissueproductinfo.getProductcategorywithproducts()
										if(productcategorywithproductsinfo != null){
											boolean productcategory_flag = false
											for(int n=0; n< productcategorywithproductsinfo.size(); n++){
												ProductCategoryWithProducts productcategorywithproductinfo = productcategorywithproductsinfo.get(n)
												if(ProjectConstants.CURRENTVISITING_PRODUCTCATEGORY.equalsIgnoreCase(productcategorywithproductinfo.getProductcategory())){
													productcategory_flag = true
													ArrayList<ShopProductsData> shopproductsdatainfo = productcategorywithproductinfo.getShopproductsdata()
													for(int ex_product=0; ex_product< shopproductsdatainfo.size(); ex_product++){
														ShopProductsData ex_productdata = shopproductsdatainfo.get(ex_product)
														for(int ds_product=0; ds_product< shopproductsdata.size(); ds_product++){
															ShopProductsData ds_productdata = shopproductsdata.get(ds_product)
															if(ex_productdata.getProduct().equalsIgnoreCase(ds_productdata.getProduct())){
																if(ProjectConstants.SCENARIO.equals("first visit")){
																	ex_productdata.setFacingdata(ds_productdata.getFacingdata())
																	break
																}
																else{
																	ex_productdata.setOverwritefacingdata(ds_productdata.getOverwritefacingdata())
																	break
																}
															}
														}
													}
												}
											}
											if(!productcategory_flag){
												expiryissueproductinfo.setRemark(ProjectConstants.CURRENTVISITING_CATEGORYREMARK)
												expiryissueproductinfo.setProductcategorywithproducts(productcategorywithproducts)
												break
											}
										}
									}
								}
								if(!expiryproduct_flag){
									visitedcategorydatainfo.setExpiryissueproducts(expiryissueproduct)
									break
								}
							}
						}
					}
					if(maincategory_flag == false){
						ProjectConstants.visitedshopdatainfo.get(i).setVisitedcategoriesdata(visitedcategorydata)
						break
					}
				}
				else{
					ProjectConstants.visitedshopdatainfo.get(i).setVisitedcategoriesdata(visitedcategorydata)
					break
				}
			}
		}
	}
	def visitExpiredProductPopup(String val){
		Mobile.verifyElementExist(findTestObject("ShopOpen/ExpiryIssue/ExpiredProduct/Validate_QuantityPopup", [('package') : ProjectConstants.PACKAGENAME]), 0)
		MobileElement quantity_edittext = ProjectConstants.DRIVER.findElementByXPath("//*[@class = 'android.widget.EditText' and @instance = '0']")
		quantity_edittext.setValue(val)
		Mobile.hideKeyboard()
		Mobile.tap(findTestObject("ShopOpen/ExpiryIssue/ExpiredProduct/QuantityPopupSubmitButton", [('package') : ProjectConstants.PACKAGENAME]), 0)
		Mobile.verifyElementText(findTestObject('ShopOpen/ExpiryIssue/ExpiredProduct/Validate_ExpiredProductScreen', [('package') : ProjectConstants.PACKAGENAME]), 'Expire Product List')
	}
	def visitShortlyExpiredProductPopup(String val){
		Mobile.verifyElementExist(findTestObject("Object Repository/ShopOpen/ExpiryIssue/ShortlyExpire/Validate_QuantityPopup", [('package') : ProjectConstants.PACKAGENAME]), 0)
		MobileElement quantity_edittext = ProjectConstants.DRIVER.findElementByXPath("//*[@class = 'android.widget.EditText' and @instance = '0']")
		quantity_edittext.setValue(val)
		Mobile.hideKeyboard()
		MobileElement date_edittext = ProjectConstants.DRIVER.findElementByXPath("//*[@class = 'android.widget.EditText' and @instance = '1']")
		date_edittext.click()
		Mobile.verifyElementExist(findTestObject("Object Repository/ShopOpen/ExpiryIssue/ShortlyExpire/Calendar_SelectButton"), 0)
		Mobile.tap(findTestObject("Object Repository/ShopOpen/ExpiryIssue/ShortlyExpire/Calendar_SelectButton"), 0)
		Mobile.verifyElementExist(findTestObject("Object Repository/ShopOpen/ExpiryIssue/ShortlyExpire/Validate_QuantityPopup", [('package') : ProjectConstants.PACKAGENAME]), 0)
		Mobile.tap(findTestObject("Object Repository/ShopOpen/ExpiryIssue/ShortlyExpire/QuantityPopupSubmitButton", [('package') : ProjectConstants.PACKAGENAME]), 0)
		Mobile.verifyElementText(findTestObject('Object Repository/ShopOpen/ExpiryIssue/ShortlyExpire/Validate_ExpiredProductScreen', [('package') : ProjectConstants.PACKAGENAME]), 'Expire Product List')
	}
}
