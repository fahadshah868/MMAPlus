package customkeywords


import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import java.awt.Image
import java.awt.Toolkit
import java.awt.image.BufferedImage
import java.awt.image.DataBuffer
import java.awt.image.PixelGrabber
import java.awt.image.Raster
import java.text.SimpleDateFormat

import java.time.DayOfWeek

import javax.imageio.ImageIO
import javax.imageio.ImageReader
import javax.swing.text.html.ImageView

import org.junit.After
import org.openqa.selenium.OutputType
import org.openqa.selenium.Point
import org.openqa.selenium.TakesScreenshot
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement

import ru.yandex.qatools.ashot.AShot
import ru.yandex.qatools.ashot.Screenshot
import ru.yandex.qatools.ashot.comparison.ImageDiff
import ru.yandex.qatools.ashot.comparison.ImageDiffer
import sun.awt.image.FileImageSource
import com.gargoylesoftware.htmlunit.javascript.host.ImageBitmap
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
import com.kms.katalon.core.webui.driver.DriverFactory
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords

import io.appium.java_client.AppiumDriver
import io.appium.java_client.MobileElement
import io.appium.java_client.ScreenshotState
import MobileBuiltInKeywords as Mobile
import WSBuiltInKeywords as WS
import WebUiBuiltInKeywords as WebUI




public class CustomKeywordsCollection {

	public static AppiumDriver<?> driver = MobileDriverFactory.getDriver()
	public static TestData datafordistributionpoint = findTestData("Data Files/DistributionPoint/DistributionPoint")
	public static TestData dataforshopwithchiller = findTestData("Shops/ShopWithChiller")
	public static TestData dataforshopwithchillerutilization = findTestData("Data Files/Shops/ShopWithChillerUtilization")
	public static TestData dataforvisitchillerproducts = findTestData("Data Files/Chiller/VisitChillerProducts")
	public static TestData dataforoverwritechillerproducts = findTestData("Data Files/Chiller/OverwriteChillerProducts")
	public static TestData dataforvisitsachetproducts = findTestData("Data Files/Sachets/VisitSachets")
	public static TestData dataforoverwritesachetproducts = findTestData("Data Files/Sachets/OverwriteSachets")
	public static TestData dataforvisitchilleravailableproducts = findTestData("Data Files/ChillerUtilization/VisitChillerAvailableProducts")
	public static TestData dataforoverwritechilleravailableproducts = findTestData("Data Files/ChillerUtilization/OverwriteChillerAvailableProducts")
	public static TestData dataforvisitchillernotavailableproducts = findTestData("Data Files/ChillerUtilization/VisitChillerNotAvailableProducts")


	@Keyword
	def selectday(){
		Calendar calendar = Calendar.getInstance();
		int day = calendar.get(Calendar.DAY_OF_WEEK);
		ArrayList<MobileElement> days = driver.findElementsByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.LinearLayout[1]/android.widget.ListView[1]/*")
		if(day == 1){
			driver.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.LinearLayout[1]/android.widget.ListView[1]/android.widget.TextView[7]").click()
		}
		else if(day == 2){
			driver.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.LinearLayout[1]/android.widget.ListView[1]/android.widget.TextView[1]").click()
		}
		else if(day == 3){
			driver.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.LinearLayout[1]/android.widget.ListView[1]/android.widget.TextView[2]").click()
		}
		else if(day == 4){
			driver.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.LinearLayout[1]/android.widget.ListView[1]/android.widget.TextView[3]").click()
		}
		else if(day == 5){
			driver.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.LinearLayout[1]/android.widget.ListView[1]/android.widget.TextView[4]").click()
		}
		else if(day == 6){
			driver.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.LinearLayout[1]/android.widget.ListView[1]/android.widget.TextView[5]").click()
		}
		else if(day == 7){
			driver.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.LinearLayout[1]/android.widget.ListView[1]/android.widget.TextView[6]").click()
		}
	}
	@Keyword
	def visitdistributionPoint(){
		ArrayList<MobileElement> distributionitems = driver.findElementsByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/*")
		for(int i=1; i<=(distributionitems.size()-2); i++){
			MobileElement distributionitem = driver.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.LinearLayout["+i+"]/android.widget.TextView[1]")
			String distributionitemname = distributionitem.getText()
			if(distributionitemname.equals("Poster Quantity:")){
				MobileElement distributionitemtextfield = driver.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.LinearLayout["+i+"]/android.widget.EditText[1]")
				distributionitemtextfield.setValue(datafordistributionpoint.getObjectValue("Poster Quantity", 1))
				Mobile.hideKeyboard()
			}
			else if(distributionitemname.equals("Bunting Quantity:")){
				MobileElement distributionitemtextfield = driver.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.LinearLayout["+i+"]/android.widget.EditText[1]")
				distributionitemtextfield.setValue(datafordistributionpoint.getObjectValue("Bunting Quantity", 1))
				Mobile.hideKeyboard()
			}
			else if(distributionitemname.equals("Shelf Talker Quantity:")){
				MobileElement distributionitemtextfield = driver.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.LinearLayout["+i+"]/android.widget.EditText[1]")
				distributionitemtextfield.setValue(datafordistributionpoint.getObjectValue("Shelf Talker Quantity", 1))
				Mobile.hideKeyboard()
			}
			else if(distributionitemname.equals("Banners:")){
				MobileElement distributionitemtextfield = driver.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.LinearLayout["+i+"]/android.widget.EditText[1]")
				distributionitemtextfield.setValue(datafordistributionpoint.getObjectValue("Banners", 1))
				Mobile.hideKeyboard()
			}
		}
	}
	@Keyword
	def findPictureImageView(){
		try{
			driver.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.LinearLayout[1]/android.widget.LinearLayout[2]/android.widget.ImageButton[1]").isDisplayed()
			Mobile.tap(findTestObject("Object Repository/CommonScreenElements/Picture_ImageView"), 0)
			Mobile.verifyElementExist(findTestObject("Object Repository/CommonScreenElements/Validate_CameraScreen"), 0)
			Mobile.tap(findTestObject("Object Repository/CommonScreenElements/TakePictureButton"), 0)
			Mobile.delay(5)
			Mobile.tap(findTestObject("Object Repository/CommonScreenElements/DoneButton"), 0)
		}
		catch(Exception ex){
		}
	}
	@Keyword
	def selectShopWithChiller(){
		boolean flag = false
		int index = 0
		String _shopname = dataforshopwithchiller.getObjectValue("Shop Name", 1)
		ArrayList<MobileElement> shops = driver.findElementsByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.ListView[1]/*")
		for(int i=1; i<=shops.size(); i++){
			MobileElement shop = driver.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout["+i+"]/android.widget.TextView[1]")
			String shopname = shop.getText()
			if(shopname.contains(_shopname)){
				flag = true
				driver.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout["+i+"]").click()
				break
			}
			shops = driver.findElementsByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.ListView[1]/*")
		}
		if(flag == false){
			while(true){
				index = driver.findElementsByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.ListView[1]/*").size()
				MobileElement lastitembeforeswipe  = driver.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout["+index+"]/android.widget.TextView[1]")
				String lastitemnamebeforeswipe = lastitembeforeswipe.getText()
				Mobile.swipe(0, 292, 0, 200)
				index = driver.findElementsByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.ListView[1]/*").size()
				MobileElement lastitemafterswipe = driver.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout["+index+"]/android.widget.TextView[1]")
				String lastitemnameafterswipe = lastitemafterswipe.getText()
				if(lastitemnamebeforeswipe.equals(lastitemnameafterswipe)){
					break
				}
				else if(lastitemnameafterswipe.contains(_shopname)){
					driver.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout["+index+"]").click()
					break
				}
			}
		}
	}
	@Keyword
	def selectShopWithChillerUtilization(){
		boolean flag = false
		int index = 0
		String _shopname = dataforshopwithchillerutilization.getObjectValue("Shop Name", 1)
		ArrayList<MobileElement> shops = driver.findElementsByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.ListView[1]/*")
		for(int i=1; i<=shops.size(); i++){
			MobileElement shop = driver.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout["+i+"]/android.widget.TextView[1]")
			String shopname = shop.getText()
			if(shopname.contains(_shopname)){
				flag = true
				driver.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout["+i+"]").click()
				break
			}
			shops = driver.findElementsByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.ListView[1]/*")
		}
		if(flag == false){
			while(true){
				index = driver.findElementsByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.ListView[1]/*").size()
				MobileElement lastitembeforeswipe  = driver.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout["+index+"]/android.widget.TextView[1]")
				String lastitemnamebeforeswipe = lastitembeforeswipe.getText()
				Mobile.swipe(0, 292, 0, 200)
				index = driver.findElementsByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.ListView[1]/*").size()
				MobileElement lastitemafterswipe = driver.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout["+index+"]/android.widget.TextView[1]")
				String lastitemnameafterswipe = lastitemafterswipe.getText()
				if(lastitemnamebeforeswipe.equals(lastitemnameafterswipe)){
					break
				}
				else if(lastitemnameafterswipe.contains(_shopname)){
					driver.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout["+index+"]").click()
					break
				}
			}
		}
	}
	@Keyword
	def visitShopProducts(){
		int flag = 0
		int index = 0
		ArrayList<MobileElement> products = driver.findElementsByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ListView[1]/*")
		for(int i=1; i<=products.size(); i++){
			MobileElement product = driver.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout["+i+"]/android.widget.TextView[1]")
			String productname = product.getText()
			if(productname.equals("Chiller")){
				flag = flag+1
				driver.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout["+i+"]").click()
				Mobile.callTestCase(findTestCase("Test Cases/ShopOpen/Chiller/VisitChiller"), null)
			}
			else if(productname.equals("Chiller Utilization")){
				flag = flag+1
				driver.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout["+i+"]").click()
				Mobile.callTestCase(findTestCase("Test Cases/ShopOpen/ChillerUtilization/VisitChillerUtilization"), null)
			}
			else if(productname.equals("Sachets")){
				flag = flag+1
				driver.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout["+i+"]").click()
				Mobile.callTestCase(findTestCase("Test Cases/ShopOpen/Sachets/VisitSachets"), null)
			}
			else if(productname.equals("Additional Picture")){
				flag = flag+1
				driver.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout["+i+"]").click()
				Mobile.callTestCase(findTestCase("Test Cases/ShopOpen/AdditionalPicture/VisitAdditionalPicture"), null)
			}
			//			else if(productname.equals("Competition Tracking")){
			//				flag = flag+1
			//				driver.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout["+i+"]").click()
			//				Mobile.callTestCase(findTestCase("Test Cases/ShopOpen/CompetitionTracking/VisitCompetitionTracking"), null)
			//			}
			else if(productname.equals("Retailer Remarks")){
				flag = flag+1
				driver.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout["+i+"]").click()
				Mobile.callTestCase(findTestCase("Test Cases/ShopOpen/RetailerRemarks/VisitRetailerRemarks"), null)
			}
			else if(productname.equals("RTM -Visit Frequency")){
				flag = flag+1
				driver.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout["+i+"]").click()
				Mobile.callTestCase(findTestCase("Test Cases/ShopOpen/RTMVisitFrequency/VisitRTMVisitFrequency"), null)
			}
			else if(productname.equals("Hanger Availibility")){
				flag = flag+1
				driver.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout["+i+"]").click()
				Mobile.callTestCase(findTestCase("Test Cases/ShopOpen/HangerAvailability/VisitHangerAvailability"), null)
			}
			products = driver.findElementsByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ListView[1]/*")
		}
		if(flag<6){
			while(true){
				boolean _flag = false
				String lastvisitedproduct = ""
				index = driver.findElementsByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ListView[1]/*").size()
				MobileElement lastitembeforeswipe = driver.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout["+index+"]/android.widget.TextView[1]")
				String lastitemnamebeforeswipe = lastitembeforeswipe.getText()
				Mobile.swipe(0, 293, 0, 200)
				index = driver.findElementsByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ListView[1]/*").size()
				MobileElement lastitemafterswipe = driver.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout["+index+"]/android.widget.TextView[1]")
				String lastitemnameafterswipe = lastitemafterswipe.getText()
				if(lastitemnamebeforeswipe.equals(lastitemnameafterswipe)){
					break
				}
				else if(lastitemnameafterswipe.equals("Chiller")){
					_flag = true
					lastvisitedproduct = "Chiller"
					driver.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout["+index+"]").click()
					Mobile.callTestCase(findTestCase("Test Cases/ShopOpen/Chiller/VisitChiller"), null)
				}
				else if(lastitemnameafterswipe.equals("Chiller Utilization")){
					_flag = true
					lastvisitedproduct = "Chiller Utilization"
					driver.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout["+index+"]").click()
					Mobile.callTestCase(findTestCase("Test Cases/ShopOpen/ChillerUtilization/VisitChillerUtilization"), null)
				}
				else if(lastitemnameafterswipe.equals("Sachets")){
					_flag = true
					lastvisitedproduct = "Sachets"
					driver.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout["+index+"]").click()
					Mobile.callTestCase(findTestCase("Test Cases/ShopOpen/Sachets/VisitSachets"), null)
				}
				else if(lastitemnameafterswipe.equals("Additional Picture")){
					_flag = true
					lastvisitedproduct = "Additional Picture"
					driver.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout["+index+"]").click()
					Mobile.callTestCase(findTestCase("Test Cases/ShopOpen/AdditionalPicture/VisitAdditionalPicture"), null)
				}
				//				else if(lastitemnameafterswipe.equals("Competition Tracking")){
				//					_flag = true
				//					lastvisitedproduct = "Competition Tracking"
				//					driver.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout["+index+"]").click()
				//					Mobile.callTestCase(findTestCase("Test Cases/ShopOpen/CompetitionTracking/VisitCompetitionTracking"), null)
				//				}
				else if(lastitemnameafterswipe.equals("Retailer Remarks")){
					_flag = true
					lastvisitedproduct = "Retailer Remarks"
					driver.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout["+index+"]").click()
					Mobile.callTestCase(findTestCase("Test Cases/ShopOpen/RetailerRemarks/VisitRetailerRemarks"), null)
				}
				else if(lastitemnameafterswipe.equals("RTM -Visit Frequency")){
					_flag = true
					lastvisitedproduct = "RTM -Visit Frequency"
					driver.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout["+index+"]").click()
					Mobile.callTestCase(findTestCase("Test Cases/ShopOpen/RTMVisitFrequency/VisitRTMVisitFrequency"), null)
				}
				else if(lastitemnameafterswipe.equals("Hanger Availibility")){
					_flag = true
					lastvisitedproduct = "Hanger Availibility"
					driver.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout["+index+"]").click()
					Mobile.callTestCase(findTestCase("Test Cases/ShopOpen/HangerAvailability/VisitHangerAvailability"), null)
				}
				if(_flag == true){
					while(true){
						Mobile.swipe(0, 293, 0, 200)
						index = driver.findElementsByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ListView[1]/*").size()
						MobileElement product = driver.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout["+index+"]/android.widget.TextView[1]")
						String productname = product.getText()
						if(productname.equals(lastvisitedproduct)){
							_flag = false
							break
						}
					}
				}
			}
		}
	}
	@Keyword
	def overwriteShopProducts(){
		int flag = 0
		int index = 0
		ArrayList<MobileElement> products = driver.findElementsByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ListView[1]/*")
		for(int i=1; i<=products.size(); i++){
			MobileElement product = driver.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout["+i+"]/android.widget.TextView[1]")
			String productname = product.getText()
			if(productname.equals("Chiller")){
				flag = flag+1
				driver.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout["+i+"]").click()
				Mobile.verifyElementExist(findTestObject("Object Repository/CommonScreenElements/Validate_InfoPopUP"), 0)
				Mobile.tap(findTestObject("Object Repository/CommonScreenElements/InfoPopUp_YesButton"), 0)
				Mobile.callTestCase(findTestCase("Test Cases/ShopOpen/Chiller/OverwriteChiller"), null)
			}
			else if(productname.equals("Chiller Utilization")){
				flag = flag+1
				driver.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout["+i+"]").click()
				Mobile.verifyElementExist(findTestObject("Object Repository/CommonScreenElements/Validate_InfoPopUP"), 0)
				Mobile.tap(findTestObject("Object Repository/CommonScreenElements/InfoPopUp_YesButton"), 0)
				Mobile.callTestCase(findTestCase("Test Cases/ShopOpen/ChillerUtilization/OverwriteChillerUtilization"), null)
			}
			else if(productname.equals("Sachets")){
				flag = flag+1
				driver.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout["+i+"]").click()
				Mobile.verifyElementExist(findTestObject("Object Repository/CommonScreenElements/Validate_InfoPopUP"), 0)
				Mobile.tap(findTestObject("Object Repository/CommonScreenElements/InfoPopUp_YesButton"), 0)
				Mobile.callTestCase(findTestCase("Test Cases/ShopOpen/Sachets/OverwriteSachets"), null)
			}
			else if(productname.equals("Additional Picture")){
				flag = flag+1
				driver.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout["+i+"]").click()
				Mobile.callTestCase(findTestCase("Test Cases/ShopOpen/AdditionalPicture/OverwriteAdditionalPicture"), null)
			}
			//			else if(productname.equals("Competition Tracking")){
			//				flag = flag+1
			//				driver.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout["+i+"]").click()
			//				Mobile.verifyElementExist(findTestObject("Object Repository/CommonScreenElements/Validate_InfoPopUP"), 0)
			//				Mobile.tap(findTestObject("Object Repository/CommonScreenElements/InfoPopUp_YesButton"), 0)
			//				Mobile.callTestCase(findTestCase("Test Cases/ShopOpen/CompetitionTracking/OverwriteCompetitionTracking"), null)
			//			}
			else if(productname.equals("Retailer Remarks")){
				flag = flag+1
				driver.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout["+i+"]").click()
				Mobile.verifyElementExist(findTestObject("Object Repository/CommonScreenElements/Validate_InfoPopUP"), 0)
				Mobile.tap(findTestObject("Object Repository/CommonScreenElements/InfoPopUp_YesButton"), 0)
				Mobile.callTestCase(findTestCase("Test Cases/ShopOpen/RetailerRemarks/OverwriteRetailerRemarks"), null)
			}
			else if(productname.equals("RTM -Visit Frequency")){
				flag = flag+1
				driver.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout["+i+"]").click()
				Mobile.verifyElementExist(findTestObject("Object Repository/CommonScreenElements/Validate_InfoPopUP"), 0)
				Mobile.tap(findTestObject("Object Repository/CommonScreenElements/InfoPopUp_YesButton"), 0)
				Mobile.callTestCase(findTestCase("Test Cases/ShopOpen/RTMVisitFrequency/OverwriteRTMVisitFrequency"), null)
			}
			else if(productname.equals("Hanger Availibility")){
				flag = flag+1
				driver.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout["+i+"]").click()
				Mobile.callTestCase(findTestCase("Test Cases/ShopOpen/HangerAvailability/OverwriteHangerAvailability"), null)
			}
			products = driver.findElementsByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ListView[1]/*")
		}
		if(flag<6){
			while(true){
				boolean _flag = false
				String lastvisitedproduct = ""
				index = driver.findElementsByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ListView[1]/*").size()
				MobileElement lastitembeforeswipe = driver.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout["+index+"]/android.widget.TextView[1]")
				String lastitemnamebeforeswipe = lastitembeforeswipe.getText()
				Mobile.swipe(0, 293, 0, 200)
				index = driver.findElementsByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ListView[1]/*").size()
				MobileElement lastitemafterswipe = driver.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout["+index+"]/android.widget.TextView[1]")
				String lastitemnameafterswipe = lastitemafterswipe.getText()
				if(lastitemnamebeforeswipe.equals(lastitemnameafterswipe)){
					break
				}
				else if(lastitemnameafterswipe.equals("Chiller")){
					_flag = true
					lastvisitedproduct = "Chiller"
					driver.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout["+index+"]").click()
					Mobile.verifyElementExist(findTestObject("Object Repository/CommonScreenElements/Validate_InfoPopUP"), 0)
					Mobile.tap(findTestObject("Object Repository/CommonScreenElements/InfoPopUp_YesButton"), 0)
					Mobile.callTestCase(findTestCase("Test Cases/ShopOpen/Chiller/OverwriteChiller"), null)
				}
				else if(lastitemnameafterswipe.equals("Chiller Utilization")){
					_flag = true
					lastvisitedproduct = "Chiller Utilization"
					driver.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout["+index+"]").click()
					Mobile.verifyElementExist(findTestObject("Object Repository/CommonScreenElements/Validate_InfoPopUP"), 0)
					Mobile.tap(findTestObject("Object Repository/CommonScreenElements/InfoPopUp_YesButton"), 0)
					Mobile.callTestCase(findTestCase("Test Cases/ShopOpen/ChillerUtilization/OverwriteChillerUtilization"), null)
				}
				else if(lastitemnameafterswipe.equals("Sachets")){
					_flag = true
					lastvisitedproduct = "Sachets"
					driver.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout["+index+"]").click()
					Mobile.verifyElementExist(findTestObject("Object Repository/CommonScreenElements/Validate_InfoPopUP"), 0)
					Mobile.tap(findTestObject("Object Repository/CommonScreenElements/InfoPopUp_YesButton"), 0)
					Mobile.callTestCase(findTestCase("Test Cases/ShopOpen/Sachets/OverwriteSachets"), null)
				}
				else if(lastitemnameafterswipe.equals("Additional Picture")){
					_flag = true
					lastvisitedproduct = "Additional Picture"
					driver.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout["+index+"]").click()
					Mobile.callTestCase(findTestCase("Test Cases/ShopOpen/AdditionalPicture/OverwriteAdditionalPicture"), null)
				}
				//				else if(lastitemnameafterswipe.equals("Competition Tracking")){
				//					_flag = true
				//					lastvisitedproduct = "Competition Tracking"
				//					driver.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout["+index+"]").click()
				//					Mobile.verifyElementExist(findTestObject("Object Repository/CommonScreenElements/Validate_InfoPopUP"), 0)
				//					Mobile.tap(findTestObject("Object Repository/CommonScreenElements/InfoPopUp_YesButton"), 0)
				//					Mobile.callTestCase(findTestCase("Test Cases/ShopOpen/CompetitionTracking/OverwriteCompetitionTracking"), null)
				//				}
				else if(lastitemnameafterswipe.equals("Retailer Remarks")){
					_flag = true
					lastvisitedproduct = "Retailer Remarks"
					driver.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout["+index+"]").click()
					Mobile.verifyElementExist(findTestObject("Object Repository/CommonScreenElements/Validate_InfoPopUP"), 0)
					Mobile.tap(findTestObject("Object Repository/CommonScreenElements/InfoPopUp_YesButton"), 0)
					Mobile.callTestCase(findTestCase("Test Cases/ShopOpen/RetailerRemarks/OverwriteRetailerRemarks"), null)
				}
				else if(lastitemnameafterswipe.equals("RTM -Visit Frequency")){
					_flag = true
					lastvisitedproduct = "RTM -Visit Frequency"
					driver.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout["+index+"]").click()
					Mobile.verifyElementExist(findTestObject("Object Repository/CommonScreenElements/Validate_InfoPopUP"), 0)
					Mobile.tap(findTestObject("Object Repository/CommonScreenElements/InfoPopUp_YesButton"), 0)
					Mobile.callTestCase(findTestCase("Test Cases/ShopOpen/RTMVisitFrequency/OverwriteRTMVisitFrequency"), null)
				}
				else if(lastitemnameafterswipe.equals("Hanger Availibility")){
					_flag = true
					lastvisitedproduct = "Hanger Availibility"
					driver.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout["+index+"]").click()
					Mobile.callTestCase(findTestCase("Test Cases/ShopOpen/HangerAvailability/OverwriteHangerAvailability"), null)
				}
				if(_flag == true){
					while(true){
						Mobile.swipe(0, 293, 0, 200)
						index = driver.findElementsByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ListView[1]/*").size()
						MobileElement product = driver.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout["+index+"]/android.widget.TextView[1]")
						String productname = product.getText()
						if(productname.equals(lastvisitedproduct)){
							_flag = false
							break
						}
					}
				}
			}
		}
	}
	@Keyword
	def checkPlanogramAvailability(){
		try{
			driver.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]").isDisplayed()
			Mobile.tap(findTestObject("Object Repository/CommonScreenElements/InfoPopUp_OKButton"), 0)
		}
		catch(Exception ex){
		}
	}
	@Keyword
	def visitChillerJuices(){
		int index = 0
		ArrayList<MobileElement> juices = driver.findElementsByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ScrollView[1]/android.widget.LinearLayout[1]/*")
		for(int i=2; i<=juices.size(); i=i+3){
			index = index+1
			MobileElement juice = driver.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ScrollView[1]/android.widget.LinearLayout[1]/android.widget.TextView["+index+"]")
			String juicename = juice.getText()
			MobileElement textfield = driver.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ScrollView[1]/android.widget.LinearLayout[1]/android.widget.LinearLayout["+index+"]/android.widget.EditText[1]")
			if(juicename.equals("NESFRUTA MANGO 200ML")){
				textfield.setValue(dataforvisitchillerproducts.getObjectValue("Nesfruta Mango 200ml", 1))
				Mobile.hideKeyboard()
			}
			else if(juicename.equals("FRUITA VITALS CHAUNSA 200ML")){
				textfield.setValue(dataforvisitchillerproducts.getObjectValue("Fruita Vitals Chaunsa 200ml", 1))
				Mobile.hideKeyboard()
			}
			else if(juicename.equals("FRUITA VITALS RED GRAPES 200ML")){
				textfield.setValue(dataforvisitchillerproducts.getObjectValue("Fruita Vitals Red Grapes 200ml", 1))
				Mobile.hideKeyboard()
			}
			else if(juicename.equals("FRUITA VITALS APPLE 200ML")){
				textfield.setValue(dataforvisitchillerproducts.getObjectValue("Fruita Vitals Apple 200ml", 1))
				Mobile.hideKeyboard()
			}
			juices = driver.findElementsByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ScrollView[1]/android.widget.LinearLayout[1]/*")
		}
	}
	@Keyword
	def overwriteChillerJuices(){
		int index = 0
		ArrayList<MobileElement> juices = driver.findElementsByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ScrollView[1]/android.widget.LinearLayout[1]/*")
		for(int i=2; i<=juices.size(); i=i+3){
			index = index+1
			MobileElement juice = driver.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ScrollView[1]/android.widget.LinearLayout[1]/android.widget.TextView["+index+"]")
			String juicename = juice.getText()
			MobileElement textfield = driver.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ScrollView[1]/android.widget.LinearLayout[1]/android.widget.LinearLayout["+index+"]/android.widget.EditText[1]")
			if(juicename.equals("NESFRUTA MANGO 200ML")){
				textfield.setValue(dataforoverwritechillerproducts.getObjectValue("Nesfruta Mango 200ml", 1))
				Mobile.hideKeyboard()
			}
			else if(juicename.equals("FRUITA VITALS CHAUNSA 200ML")){
				textfield.setValue(dataforoverwritechillerproducts.getObjectValue("Fruita Vitals Chaunsa 200ml", 1))
				Mobile.hideKeyboard()
			}
			else if(juicename.equals("FRUITA VITALS RED GRAPES 200ML")){
				textfield.setValue(dataforoverwritechillerproducts.getObjectValue("Fruita Vitals Red Grapes 200ml", 1))
				Mobile.hideKeyboard()
			}
			else if(juicename.equals("FRUITA VITALS APPLE 200ML")){
				textfield.setValue(dataforoverwritechillerproducts.getObjectValue("Fruita Vitals Apple 200ml", 1))
				Mobile.hideKeyboard()
			}
			juices = driver.findElementsByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ScrollView[1]/android.widget.LinearLayout[1]/*")
		}
	}
	@Keyword
	def findShopProduct(String _productname){
		boolean flag = false
		int index = 0
		ArrayList<MobileElement> products = driver.findElementsByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ListView[1]/*")
		for(int i=1; i<=products.size(); i++){
			MobileElement product = driver.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout["+i+"]/android.widget.TextView[1]")
			String productname = product.getText()
			if(productname.equals(_productname)){
				flag = true
				driver.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout["+i+"]").click()
				Mobile.verifyElementExist(findTestObject("Object Repository/CommonScreenElements/Validate_InfoPopUP"), 0)
				Mobile.tap(findTestObject("Object Repository/CommonScreenElements/InfoPopUp_YesButton"), 0)
				break
			}
			products = driver.findElementsByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ListView[1]/*")
		}
		if(flag == false){
			while(true){
				index = driver.findElementsByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ListView[1]/*").size()
				MobileElement lastitembeforeswipe = driver.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout["+index+"]/android.widget.TextView[1]")
				String lastitemnamebeforeswipe = lastitembeforeswipe.getText()
				Mobile.swipe(0, 293, 0, 200)
				index = driver.findElementsByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ListView[1]/*").size()
				MobileElement lastitemafterswipe = driver.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout["+index+"]/android.widget.TextView[1]")
				String lastitemnameafterswipe = lastitemafterswipe.getText()
				if(lastitemnamebeforeswipe.equals(lastitemnameafterswipe)){
					break
				}
				else if(lastitemnameafterswipe.equals(_productname)){
					driver.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout["+index+"]").click()
					Mobile.verifyElementExist(findTestObject("Object Repository/CommonScreenElements/Validate_InfoPopUP"), 0)
					Mobile.tap(findTestObject("Object Repository/CommonScreenElements/InfoPopUp_YesButton"), 0)
					break
				}
			}
		}
	}
	@Keyword
	def visitSachetsProducts(){
		int index = 0
		ArrayList<MobileElement> products = driver.findElementsByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ScrollView[1]/android.widget.LinearLayout[1]/*")
		for(int i=2;i<=products.size(); i=i+3){
			index = index+1
			MobileElement product = driver.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ScrollView[1]/android.widget.LinearLayout[1]/android.widget.TextView["+index+"]")
			String productname = product.getText()
			MobileElement textfield = driver.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ScrollView[1]/android.widget.LinearLayout[1]/android.widget.LinearLayout["+index+"]/android.widget.EditText[1]")
			if(productname.equals("EVERYDAY 16.8GM")){
				textfield.setValue(dataforvisitsachetproducts.getObjectValue("Everyday 16.8gm", 1))
				Mobile.hideKeyboard()
			}
			else if(productname.equals("CERELAC 3 FRUIT 25GM")){
				textfield.setValue(dataforvisitsachetproducts.getObjectValue("Cerelac 3 Fruit 25gm", 1))
				Mobile.hideKeyboard()
			}
			else if(productname.equals("NESTLE BUNYAD 26GM")){
				textfield.setValue(dataforvisitsachetproducts.getObjectValue("Nestle Bunyad 26gm", 1))
				Mobile.hideKeyboard()
			}
			else if(productname.equals("CERELAC RICE 25GM")){
				textfield.setValue(dataforvisitsachetproducts.getObjectValue("Cerelac Rice 25gm", 1))
				Mobile.hideKeyboard()
			}
			else if(productname.equals("EVERYDAY 34GM")){
				textfield.setValue(dataforvisitsachetproducts.getObjectValue("Everyday 34gm", 1))
				Mobile.hideKeyboard()
			}
			else if(productname.equals("CERELAC Wheat 25GM")){
				textfield.setValue(dataforvisitsachetproducts.getObjectValue("Cerelac Wheat 25gm", 1))
				Mobile.hideKeyboard()
			}
			products = driver.findElementsByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ScrollView[1]/android.widget.LinearLayout[1]/*")
		}
	}
	@Keyword
	def overwriteSachetsProducts(){
		int index = 0
		ArrayList<MobileElement> products = driver.findElementsByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ScrollView[1]/android.widget.LinearLayout[1]/*")
		for(int i=2;i<=products.size(); i=i+3){
			index = index+1
			MobileElement product = driver.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ScrollView[1]/android.widget.LinearLayout[1]/android.widget.TextView["+index+"]")
			String productname = product.getText()
			MobileElement textfield = driver.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ScrollView[1]/android.widget.LinearLayout[1]/android.widget.LinearLayout["+index+"]/android.widget.EditText[1]")
			if(productname.equals("EVERYDAY 16.8GM")){
				textfield.setValue(dataforoverwritesachetproducts.getObjectValue("Everyday 16.8gm", 1))
				Mobile.hideKeyboard()
			}
			else if(productname.equals("CERELAC 3 FRUIT 25GM")){
				textfield.setValue(dataforoverwritesachetproducts.getObjectValue("Cerelac 3 Fruit 25gm", 1))
				Mobile.hideKeyboard()
			}
			else if(productname.equals("NESTLE BUNYAD 26GM")){
				textfield.setValue(dataforoverwritesachetproducts.getObjectValue("Nestle Bunyad 26gm", 1))
				Mobile.hideKeyboard()
			}
			else if(productname.equals("CERELAC RICE 25GM")){
				textfield.setValue(dataforoverwritesachetproducts.getObjectValue("Cerelac Rice 25gm", 1))
				Mobile.hideKeyboard()
			}
			else if(productname.equals("EVERYDAY 34GM")){
				textfield.setValue(dataforoverwritesachetproducts.getObjectValue("Everyday 34gm", 1))
				Mobile.hideKeyboard()
			}
			else if(productname.equals("CERELAC Wheat 25GM")){
				textfield.setValue(dataforoverwritesachetproducts.getObjectValue("Cerelac Wheat 25gm", 1))
				Mobile.hideKeyboard()
			}
			products = driver.findElementsByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ScrollView[1]/android.widget.LinearLayout[1]/*")
		}
	}
	@Keyword
	def visitCompetitiontrackingProducts(){
		int index = 0
		int val = 10
		ArrayList<MobileElement> products = driver.findElementsByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ScrollView[1]/android.widget.LinearLayout[1]/*")
		for(int i=2; i<=products.size(); i=i+3){
			index = index+1
			val = val+1
			MobileElement textfield = driver.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ScrollView[1]/android.widget.LinearLayout[1]/android.widget.LinearLayout["+index+"]/android.widget.EditText[1]")
			textfield.setValue(val.toString())
			Mobile.hideKeyboard()
			products = driver.findElementsByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ScrollView[1]/android.widget.LinearLayout[1]/*")
		}
	}
	@Keyword
	def overwriteCompetitiontrackingProducts(){
		int index = 0
		int val = 20
		ArrayList<MobileElement> products = driver.findElementsByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ScrollView[1]/android.widget.LinearLayout[1]/*")
		for(int i=2; i<=products.size(); i=i+3){
			index = index+1
			val = val+1
			MobileElement textfield = driver.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ScrollView[1]/android.widget.LinearLayout[1]/android.widget.LinearLayout["+index+"]/android.widget.EditText[1]")
			textfield.setValue(val.toString())
			Mobile.hideKeyboard()
			products = driver.findElementsByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ScrollView[1]/android.widget.LinearLayout[1]/*")
		}
	}
	@Keyword
	def visitRTMVisitFrequency(){
		ArrayList<MobileElement> products = driver.findElementsByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ListView[1]/*")
		for(int i=1; i<=products.size(); i++){
			driver.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout["+i+"]").click()
			Mobile.verifyElementExist(findTestObject("Object Repository/ShopOpen/RTMVisitFrequency/Validate_RTMVisitFrequencyRemarks"), 0)
			Mobile.tap(findTestObject("Object Repository/ShopOpen/RTMVisitFrequency/OnceAWeek"), 0)
			products = driver.findElementsByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ListView[1]/*")
		}
	}
	@Keyword
	def overwriteRTMVisitFrequency(){
		ArrayList<MobileElement> products = driver.findElementsByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ListView[1]/*")
		for(int i=1; i<=products.size(); i++){
			driver.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout["+i+"]").click()
			Mobile.verifyElementExist(findTestObject("Object Repository/ShopOpen/RTMVisitFrequency/Validate_RTMVisitFrequencyRemarks"), 0)
			Mobile.tap(findTestObject("Object Repository/ShopOpen/RTMVisitFrequency/TwiceAWeek"), 0)
			products = driver.findElementsByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ListView[1]/*")
		}
	}
	@Keyword
	def visitRetailerRemarks(){
		ArrayList<MobileElement> products = driver.findElementsByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ListView[1]/*")
		for(int i=1; i<=products.size(); i++){
			driver.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout["+i+"]").click()
			Mobile.verifyElementText(findTestObject("Object Repository/ShopOpen/RetailerRemarks/Validate_RetailerRemarksDetailScreen"), "Channel:Small Kiryana")
			Mobile.tap(findTestObject("Object Repository/ShopOpen/RetailerRemarks/OBNotVisiting"), 0)
			Mobile.pressBack()
			Mobile.verifyElementText(findTestObject("Object Repository/ShopOpen/RetailerRemarks/Validate_RetailerRemarksScreen"), "KPI: Retailer Remarks")
			products = driver.findElementsByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ListView[1]/*")
		}
	}
	@Keyword
	def overwriteRetailerRemarks(){
		ArrayList<MobileElement> products = driver.findElementsByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ListView[1]/*")
		for(int i=1; i<=products.size(); i++){
			driver.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout["+i+"]").click()
			Mobile.verifyElementText(findTestObject("Object Repository/ShopOpen/RetailerRemarks/Validate_RetailerRemarksDetailScreen"), "Channel:Small Kiryana")
			Mobile.tap(findTestObject("Object Repository/ShopOpen/RetailerRemarks/SMNotVisiting"), 0)
			Mobile.pressBack()
			Mobile.verifyElementText(findTestObject("Object Repository/ShopOpen/RetailerRemarks/Validate_RetailerRemarksScreen"), "KPI: Retailer Remarks")
			products = driver.findElementsByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ListView[1]/*")
		}
	}
	@Keyword
	def visitChillerUtilization(){
		ArrayList<MobileElement> actions = driver.findElementsByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ListView[1]/*")
		for(int i=1; i<=actions.size(); i++){
			MobileElement action = driver.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout["+i+"]/android.widget.TextView[1]")
			String actionname = action.getText()
			if(actionname.equals("Chiller Available")){
				driver.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout["+i+"]").click()
				Mobile.callTestCase(findTestCase("Test Cases/ShopOpen/ChillerUtilization/ChillerAvailable/VisitChillerAvailable"), null)
				actions = driver.findElementsByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ListView[1]/*")
			}
			else if(actionname.equals("Chiller not Available")){
				driver.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout["+i+"]").click()
				Mobile.callTestCase(findTestCase("Test Cases/ShopOpen/ChillerUtilization/ChillerNotAvailable/VisitChillerNotAvailable"), null)
				actions = driver.findElementsByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ListView[1]/*")
			}
			else if(actionname.equals("Chiller need maintenance")){
				driver.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout["+i+"]").click()
				Mobile.callTestCase(findTestCase("Test Cases/ShopOpen/ChillerUtilization/ChillerNeedMaintenance/VisitChillerNeedMaintenance"), null)
				actions = driver.findElementsByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ListView[1]/*")
			}
			else if(actionname.equals("Chiller removed for maintenance")){
				driver.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout["+i+"]").click()
				Mobile.callTestCase(findTestCase("Test Cases/ShopOpen/ChillerUtilization/ChillerRemovedForMaintenance/VisitChillerRemovedForMaintenance"), null)
				actions = driver.findElementsByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ListView[1]/*")
			}
			else if(actionname.equals("Chiller not in access")){
				driver.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout["+i+"]").click()
				Mobile.callTestCase(findTestCase("Test Cases/ShopOpen/ChillerUtilization/ChillerNotInAccess/VisitChillerNotInAccess"), null)
				actions = driver.findElementsByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ListView[1]/*")
			}
			else if(actionname.equals("Shopkeeper did not allow")){
				driver.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout["+i+"]").click()
				Mobile.callTestCase(findTestCase("Test Cases/ShopOpen/ChillerUtilization/ShopKeeperDidNotAllow/VisitShopKeeperDidNotAllow"), null)
				actions = driver.findElementsByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ListView[1]/*")
			}
			else if(actionname.equals("Chiller Type not Available")){
				driver.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout["+i+"]").click()
				Mobile.callTestCase(findTestCase("Test Cases/ShopOpen/ChillerUtilization/ChillerTypeNotAvailable/VisitChillerTypeNotAvailable"), null)
			}
		}
	}
	@Keyword
	def overwriteChillerUtilization(){
		ArrayList<MobileElement> actions = driver.findElementsByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ListView[1]/*")
		for(int i=1; i<=actions.size(); i++){
			MobileElement action = driver.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout["+i+"]/android.widget.TextView[1]")
			String actionname = action.getText()
			if(actionname.equals("Chiller Type not Available")){
				driver.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout["+i+"]").click()
				Mobile.callTestCase(findTestCase("Test Cases/ShopOpen/ChillerUtilization/ChillerTypeNotAvailable/overwriteChillerTypeNotAvailable"), null)
			}
		}
	}
	@Keyword
	def visitChillerAvailableProductsCategories(){
		ArrayList<MobileElement> categories = driver.findElementsByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.ListView[1]/*")
		for(int i=1; i<=categories.size(); i++){
			MobileElement category = driver.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout["+i+"]/android.widget.TextView[1]")
			String categoryname = category.getText()
			if(categoryname.equals("Flavoured Milk")){
				driver.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout["+i+"]").click()
				Mobile.callTestCase(findTestCase("Test Cases/ShopOpen/ChillerUtilization/ChillerAvailableProducts/VisitFlavouredMilkProducts"), null)
			}
			else if(categoryname.equals("Juices - 1000ML")){
				driver.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout["+i+"]").click()
				Mobile.callTestCase(findTestCase("Test Cases/ShopOpen/ChillerUtilization/ChillerAvailableProducts/VisitJuices1000mlProducts"), null)
			}
			else if(categoryname.equals("Juices - 200ML")){
				driver.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout["+i+"]").click()
				Mobile.callTestCase(findTestCase("Test Cases/ShopOpen/ChillerUtilization/ChillerAvailableProducts/VisitJuices200mlProducts"), null)
			}
			else if(categoryname.equals("Water")){
				driver.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout["+i+"]").click()
				Mobile.callTestCase(findTestCase("Test Cases/ShopOpen/ChillerUtilization/ChillerAvailableProducts/VisitWaterProducts"), null)
			}
			else if(categoryname.equals("Yogurt")){
				driver.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout["+i+"]").click()
				Mobile.callTestCase(findTestCase("Test Cases/ShopOpen/ChillerUtilization/ChillerAvailableProducts/VisitYogurtProducts"), null)
			}
			categories = driver.findElementsByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.ListView[1]/*")
		}
	}

	@Keyword
	def overwriteChillerAvailableProductsCategories(){
		ArrayList<MobileElement> categories = driver.findElementsByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.ListView[1]/*")
		for(int i=1; i<=categories.size(); i++){
			MobileElement category = driver.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout["+i+"]/android.widget.TextView[1]")
			String categoryname = category.getText()
			if(categoryname.equals("Flavoured Milk")){
				driver.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout["+i+"]").click()
				Mobile.callTestCase(findTestCase("Test Cases/ShopOpen/ChillerUtilization/ChillerAvailableProducts/OverwriteFlavouredMilkProducts"), null)
			}
			else if(categoryname.equals("Juices - 1000ML")){
				driver.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout["+i+"]").click()
				Mobile.callTestCase(findTestCase("Test Cases/ShopOpen/ChillerUtilization/ChillerAvailableProducts/OverwriteJuices1000mlProducts"), null)
			}
			else if(categoryname.equals("Juices - 200ML")){
				driver.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout["+i+"]").click()
				Mobile.callTestCase(findTestCase("Test Cases/ShopOpen/ChillerUtilization/ChillerAvailableProducts/OverwriteJuices200mlProducts"), null)
			}
			else if(categoryname.equals("Water")){
				driver.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout["+i+"]").click()
				Mobile.callTestCase(findTestCase("Test Cases/ShopOpen/ChillerUtilization/ChillerAvailableProducts/OverwriteWaterProducts"), null)
			}
			else if(categoryname.equals("Yogurt")){
				driver.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout["+i+"]").click()
				Mobile.callTestCase(findTestCase("Test Cases/ShopOpen/ChillerUtilization/ChillerAvailableProducts/OverwriteYogurtProducts"), null)
			}
			categories = driver.findElementsByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.ListView[1]/*")
		}
	}
	@Keyword
	def overwriteChillerAvailableFlavouredMilkProducts(){
		int index = 0
		ArrayList<MobileElement> products = driver.findElementsByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ScrollView[1]/android.widget.LinearLayout[1]/*")
		for(int i=2; i<=products.size(); i=i+3){
			index = index + 1
			MobileElement product = driver.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ScrollView[1]/android.widget.LinearLayout[1]/android.widget.TextView["+index+"]")
			String productname = product.getText()
			MobileElement textfield = driver.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ScrollView[1]/android.widget.LinearLayout[1]/android.widget.LinearLayout["+index+"]/android.widget.EditText[1]")
			if(productname.equals("MILO RTD 200ML")){
				textfield.setValue(dataforoverwritechilleravailableproducts.getObjectValue("Milo RTD 200ml", 1))
				Mobile.hideKeyboard()
			}
		}
	}
	@Keyword
	def overwriteChillerAvailableJuices1000mlProducts(){
		int index = 0
		ArrayList<MobileElement> products = driver.findElementsByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ScrollView[1]/android.widget.LinearLayout[1]/*")
		for(int i=2; i<=products.size(); i=i+3){
			index = index + 1
			MobileElement product = driver.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ScrollView[1]/android.widget.LinearLayout[1]/android.widget.TextView["+index+"]")
			String productname = product.getText()
			MobileElement textfield = driver.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ScrollView[1]/android.widget.LinearLayout[1]/android.widget.LinearLayout["+index+"]/android.widget.EditText[1]")
			if(productname.equals("FRUITA VITALS APPLE 1000ML")){
				textfield.setValue(dataforoverwritechilleravailableproducts.getObjectValue("Fruita Vitals Apple 1000ml", 1))
				Mobile.hideKeyboard()
			}
			else if(productname.equals("FRUITA VITALS CHAUNSA 1000ML")){
				textfield.setValue(dataforoverwritechilleravailableproducts.getObjectValue("Fruita Vitals Chaunsa 1000ml", 1))
				Mobile.hideKeyboard()
			}
			else if(productname.equals("FRUITA VITALS RED GRAPES 1000ML")){
				textfield.setValue(dataforoverwritechilleravailableproducts.getObjectValue("Fruita Vitals Red Grapes 1000ml", 1))
				Mobile.hideKeyboard()
			}
		}
	}
	@Keyword
	def overwriteChillerAvailableJuices200mlProducts(){
		int index = 0
		ArrayList<MobileElement> products = driver.findElementsByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ScrollView[1]/android.widget.LinearLayout[1]/*")
		for(int i=2; i<=products.size(); i=i+3){
			index = index + 1
			MobileElement product = driver.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ScrollView[1]/android.widget.LinearLayout[1]/android.widget.TextView["+index+"]")
			String productname = product.getText()
			MobileElement textfield = driver.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ScrollView[1]/android.widget.LinearLayout[1]/android.widget.LinearLayout["+index+"]/android.widget.EditText[1]")
			if(productname.equals("NESFRUTA MANGO 200ML")){
				textfield.setValue(dataforoverwritechilleravailableproducts.getObjectValue("Nesfruta Mango 200ml", 1))
				Mobile.hideKeyboard()
			}
			else if(productname.equals("FRUITA VITALS APPLE 200ML")){
				textfield.setValue(dataforoverwritechilleravailableproducts.getObjectValue("Fruita Vitals Apple 200ml", 1))
				Mobile.hideKeyboard()
			}
			else if(productname.equals("FRUITA VITALS PINEAPPLE 200ML")){
				textfield.setValue(dataforoverwritechilleravailableproducts.getObjectValue("Fruita Vitals PineApple 200ml", 1))
				Mobile.hideKeyboard()
			}
			else if(productname.equals("FRUITA VITALS CHAUNSA 200ML")){
				textfield.setValue(dataforoverwritechilleravailableproducts.getObjectValue("Fruita Vitals Chaunsa 200ml", 1))
				Mobile.hideKeyboard()
			}
			else if(productname.equals("NESFRUTA APPLE 200ML")){
				textfield.setValue(dataforoverwritechilleravailableproducts.getObjectValue("Nesfruta Apple 200ml", 1))
				Mobile.hideKeyboard()
			}
			else if(productname.equals("FRUITA VITALS KINOW 200ML")){
				textfield.setValue(dataforoverwritechilleravailableproducts.getObjectValue("Fruita Vitals Kinow 200ml", 1))
				Mobile.hideKeyboard()
			}
			else if(productname.equals("FRUITA VITALS GUAVA 200ML")){
				textfield.setValue(dataforoverwritechilleravailableproducts.getObjectValue("Fruita Vitals Guava 200ml", 1))
				Mobile.hideKeyboard()
			}
			else if(productname.equals("FRUITA VITALS RED GRAPES 200ML")){
				textfield.setValue(dataforoverwritechilleravailableproducts.getObjectValue("Fruita Vitals Red Grapes 200ml", 1))
				Mobile.hideKeyboard()
			}
			else if(productname.equals("FRUITA VITALS PEACH 200ML")){
				textfield.setValue(dataforoverwritechilleravailableproducts.getObjectValue("Fruita Vitals Peach 200ml", 1))
				Mobile.hideKeyboard()
			}
			products = driver.findElementsByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ScrollView[1]/android.widget.LinearLayout[1]/*")
		}
		while(true){
			MobileElement lastitembeforeswipe = driver.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ScrollView[1]/android.widget.LinearLayout[1]/android.widget.TextView[5]")
			String lastitemnamebeforeswipe = lastitembeforeswipe.getText()
			Mobile.swipe(0, 259, 0, 150)
			MobileElement lastitemafterswipe = driver.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ScrollView[1]/android.widget.LinearLayout[1]/android.widget.TextView[5]")
			String lastitemnameafterswipe = lastitemafterswipe.getText()
			MobileElement textfield = driver.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ScrollView[1]/android.widget.LinearLayout[1]/android.widget.LinearLayout[6]/android.widget.EditText[1]")
			if(lastitemnamebeforeswipe.equals(lastitemnameafterswipe)){
				break
			}
			else if(lastitemnameafterswipe.equals("NESFRUTA MANGO 200ML")){
				textfield.setValue(dataforoverwritechilleravailableproducts.getObjectValue("Nesfruta Mango 200ml", 1))
				Mobile.hideKeyboard()
			}
			else if(lastitemnameafterswipe.equals("FRUITA VITALS APPLE 200ML")){
				textfield.setValue(dataforoverwritechilleravailableproducts.getObjectValue("Fruita Vitals Apple 200ml", 1))
				Mobile.hideKeyboard()
			}
			else if(lastitemnameafterswipe.equals("FRUITA VITALS PINEAPPLE 200ML")){
				textfield.setValue(dataforoverwritechilleravailableproducts.getObjectValue("Fruita Vitals PineApple 200ml", 1))
				Mobile.hideKeyboard()
			}
			else if(lastitemnameafterswipe.equals("FRUITA VITALS CHAUNSA 200ML")){
				textfield.setValue(dataforoverwritechilleravailableproducts.getObjectValue("Fruita Vitals Chaunsa 200ml", 1))
				Mobile.hideKeyboard()
			}
			else if(lastitemnameafterswipe.equals("NESFRUTA APPLE 200ML")){
				textfield.setValue(dataforoverwritechilleravailableproducts.getObjectValue("Nesfruta Apple 200ml", 1))
				Mobile.hideKeyboard()
			}
			else if(lastitemnameafterswipe.equals("FRUITA VITALS KINOW 200ML")){
				textfield.setValue(dataforoverwritechilleravailableproducts.getObjectValue("Fruita Vitals Kinow 200ml", 1))
				Mobile.hideKeyboard()
			}
			else if(lastitemnameafterswipe.equals("FRUITA VITALS GUAVA 200ML")){
				textfield.setValue(dataforoverwritechilleravailableproducts.getObjectValue("Fruita Vitals Guava 200ml", 1))
				Mobile.hideKeyboard()
			}
			else if(lastitemnameafterswipe.equals("FRUITA VITALS RED GRAPES 200ML")){
				textfield.setValue(dataforoverwritechilleravailableproducts.getObjectValue("Fruita Vitals Red Grapes 200ml", 1))
				Mobile.hideKeyboard()
			}
			else if(lastitemnameafterswipe.equals("FRUITA VITALS PEACH 200ML")){
				textfield.setValue(dataforoverwritechilleravailableproducts.getObjectValue("Fruita Vitals Peach 200ml", 1))
				Mobile.hideKeyboard()
			}
		}
	}
	@Keyword
	def overwriteChillerAvailableWaterProducts(){
		int index = 0
		ArrayList<MobileElement> products = driver.findElementsByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ScrollView[1]/android.widget.LinearLayout[1]/*")
		for(int i=2; i<=products.size(); i=i+3){
			index = index + 1
			MobileElement product = driver.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ScrollView[1]/android.widget.LinearLayout[1]/android.widget.TextView["+index+"]")
			String productname = product.getText()
			MobileElement textfield = driver.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ScrollView[1]/android.widget.LinearLayout[1]/android.widget.LinearLayout["+index+"]/android.widget.EditText[1]")
			if(productname.equals("Water Npl 500ML")){
				textfield.setValue(dataforoverwritechilleravailableproducts.getObjectValue("Water Npl 500ml", 1))
				Mobile.hideKeyboard()
			}
			else if(productname.equals("Water Npl 1500ML")){
				textfield.setValue(dataforoverwritechilleravailableproducts.getObjectValue("Water Npl 1500ml", 1))
				Mobile.hideKeyboard()
			}
		}
	}
	@Keyword
	def overwriteChillerAvailableYogurtProducts(){
		int index = 0
		ArrayList<MobileElement> products = driver.findElementsByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ScrollView[1]/android.widget.LinearLayout[1]/*")
		for(int i=2; i<=products.size(); i=i+3){
			index = index + 1
			MobileElement product = driver.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ScrollView[1]/android.widget.LinearLayout[1]/android.widget.TextView["+index+"]")
			String productname = product.getText()
			MobileElement textfield = driver.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ScrollView[1]/android.widget.LinearLayout[1]/android.widget.LinearLayout["+index+"]/android.widget.EditText[1]")
			if(productname.equals("MILKPAK YOGURT POUCH 500GM")){
				textfield.setValue(dataforoverwritechilleravailableproducts.getObjectValue("Milkpak Yogurt Pouch 500gm", 1))
				Mobile.hideKeyboard()
			}
			else if(productname.equals("Sweet & Tasty 400GM")){
				textfield.setValue(dataforoverwritechilleravailableproducts.getObjectValue("Sweet & Tasty 400gm", 1))
				Mobile.hideKeyboard()
			}
			else if(productname.equals("Nestle Nesvita YOGURT 400GM")){
				textfield.setValue(dataforoverwritechilleravailableproducts.getObjectValue("Nestle Nesvita Yogurt 400gm", 1))
				Mobile.hideKeyboard()
			}
			else if(productname.equals("Rawaity Maza 400GM")){
				textfield.setValue(dataforoverwritechilleravailableproducts.getObjectValue("Rawaity Maza 400gm", 1))
				Mobile.hideKeyboard()
			}
			else if(productname.equals("NESTLE ZEERA RAITA 250GM")){
				textfield.setValue(dataforoverwritechilleravailableproducts.getObjectValue("Nestle Zeera Raita 250gm", 1))
				Mobile.hideKeyboard()
			}
			else if(productname.equals("Mint Raita 250GM")){
				textfield.setValue(dataforoverwritechilleravailableproducts.getObjectValue("Main Raita 250gm", 1))
				Mobile.hideKeyboard()
			}
		}
	}
	@Keyword
	def visitChillerAvailableFlavouredMilkProducts(){
		int index = 0
		ArrayList<MobileElement> products = driver.findElementsByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ScrollView[1]/android.widget.LinearLayout[1]/*")
		for(int i=2; i<=products.size(); i=i+3){
			index = index + 1
			MobileElement product = driver.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ScrollView[1]/android.widget.LinearLayout[1]/android.widget.TextView["+index+"]")
			String productname = product.getText()
			MobileElement textfield = driver.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ScrollView[1]/android.widget.LinearLayout[1]/android.widget.LinearLayout["+index+"]/android.widget.EditText[1]")
			if(productname.equals("MILO RTD 200ML")){
				textfield.setValue(dataforvisitchilleravailableproducts.getObjectValue("Milo RTD 200ml", 1))
				Mobile.hideKeyboard()
			}
		}
	}
	@Keyword
	def visitChillerAvailableJuices1000mlProducts(){
		int index = 0
		ArrayList<MobileElement> products = driver.findElementsByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ScrollView[1]/android.widget.LinearLayout[1]/*")
		for(int i=2; i<=products.size(); i=i+3){
			index = index + 1
			MobileElement product = driver.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ScrollView[1]/android.widget.LinearLayout[1]/android.widget.TextView["+index+"]")
			String productname = product.getText()
			MobileElement textfield = driver.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ScrollView[1]/android.widget.LinearLayout[1]/android.widget.LinearLayout["+index+"]/android.widget.EditText[1]")
			if(productname.equals("FRUITA VITALS APPLE 1000ML")){
				textfield.setValue(dataforvisitchilleravailableproducts.getObjectValue("Fruita Vitals Apple 1000ml", 1))
				Mobile.hideKeyboard()
			}
			else if(productname.equals("FRUITA VITALS CHAUNSA 1000ML")){
				textfield.setValue(dataforvisitchilleravailableproducts.getObjectValue("Fruita Vitals Chaunsa 1000ml", 1))
				Mobile.hideKeyboard()
			}
			else if(productname.equals("FRUITA VITALS RED GRAPES 1000ML")){
				textfield.setValue(dataforvisitchilleravailableproducts.getObjectValue("Fruita Vitals Red Grapes 1000ml", 1))
				Mobile.hideKeyboard()
			}
		}
	}
	@Keyword
	def visitChillerAvailableJuices200mlProducts(){
		int index = 0
		ArrayList<MobileElement> products = driver.findElementsByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ScrollView[1]/android.widget.LinearLayout[1]/*")
		for(int i=2; i<=products.size(); i=i+3){
			index = index + 1
			MobileElement product = driver.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ScrollView[1]/android.widget.LinearLayout[1]/android.widget.TextView["+index+"]")
			String productname = product.getText()
			MobileElement textfield = driver.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ScrollView[1]/android.widget.LinearLayout[1]/android.widget.LinearLayout["+index+"]/android.widget.EditText[1]")
			if(productname.equals("NESFRUTA MANGO 200ML")){
				textfield.setValue(dataforvisitchilleravailableproducts.getObjectValue("Nesfruta Mango 200ml", 1))
				Mobile.hideKeyboard()
			}
			else if(productname.equals("FRUITA VITALS APPLE 200ML")){
				textfield.setValue(dataforvisitchilleravailableproducts.getObjectValue("Fruita Vitals Apple 200ml", 1))
				Mobile.hideKeyboard()
			}
			else if(productname.equals("FRUITA VITALS PINEAPPLE 200ML")){
				textfield.setValue(dataforvisitchilleravailableproducts.getObjectValue("Fruita Vitals PineApple 200ml", 1))
				Mobile.hideKeyboard()
			}
			else if(productname.equals("FRUITA VITALS CHAUNSA 200ML")){
				textfield.setValue(dataforvisitchilleravailableproducts.getObjectValue("Fruita Vitals Chaunsa 200ml", 1))
				Mobile.hideKeyboard()
			}
			else if(productname.equals("NESFRUTA APPLE 200ML")){
				textfield.setValue(dataforvisitchilleravailableproducts.getObjectValue("Nesfruta Apple 200ml", 1))
				Mobile.hideKeyboard()
			}
			else if(productname.equals("FRUITA VITALS KINOW 200ML")){
				textfield.setValue(dataforvisitchilleravailableproducts.getObjectValue("Fruita Vitals Kinow 200ml", 1))
				Mobile.hideKeyboard()
			}
			else if(productname.equals("FRUITA VITALS GUAVA 200ML")){
				textfield.setValue(dataforvisitchilleravailableproducts.getObjectValue("Fruita Vitals Guava 200ml", 1))
				Mobile.hideKeyboard()
			}
			else if(productname.equals("FRUITA VITALS RED GRAPES 200ML")){
				textfield.setValue(dataforvisitchilleravailableproducts.getObjectValue("Fruita Vitals Red Grapes 200ml", 1))
				Mobile.hideKeyboard()
			}
			else if(productname.equals("FRUITA VITALS PEACH 200ML")){
				textfield.setValue(dataforvisitchilleravailableproducts.getObjectValue("Fruita Vitals Peach 200ml", 1))
				Mobile.hideKeyboard()
			}
			products = driver.findElementsByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ScrollView[1]/android.widget.LinearLayout[1]/*")
		}
		while(true){
			MobileElement lastitembeforeswipe = driver.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ScrollView[1]/android.widget.LinearLayout[1]/android.widget.TextView[5]")
			String lastitemnamebeforeswipe = lastitembeforeswipe.getText()
			Mobile.swipe(0, 259, 0, 150)
			MobileElement lastitemafterswipe = driver.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ScrollView[1]/android.widget.LinearLayout[1]/android.widget.TextView[5]")
			String lastitemnameafterswipe = lastitemafterswipe.getText()
			MobileElement textfield = driver.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ScrollView[1]/android.widget.LinearLayout[1]/android.widget.LinearLayout[6]/android.widget.EditText[1]")
			if(lastitemnamebeforeswipe.equals(lastitemnameafterswipe)){
				break
			}
			else if(lastitemnameafterswipe.equals("NESFRUTA MANGO 200ML")){
				textfield.setValue(dataforvisitchilleravailableproducts.getObjectValue("Nesfruta Mango 200ml", 1))
				Mobile.hideKeyboard()
			}
			else if(lastitemnameafterswipe.equals("FRUITA VITALS APPLE 200ML")){
				textfield.setValue(dataforvisitchilleravailableproducts.getObjectValue("Fruita Vitals Apple 200ml", 1))
				Mobile.hideKeyboard()
			}
			else if(lastitemnameafterswipe.equals("FRUITA VITALS PINEAPPLE 200ML")){
				textfield.setValue(dataforvisitchilleravailableproducts.getObjectValue("Fruita Vitals PineApple 200ml", 1))
				Mobile.hideKeyboard()
			}
			else if(lastitemnameafterswipe.equals("FRUITA VITALS CHAUNSA 200ML")){
				textfield.setValue(dataforvisitchilleravailableproducts.getObjectValue("Fruita Vitals Chaunsa 200ml", 1))
				Mobile.hideKeyboard()
			}
			else if(lastitemnameafterswipe.equals("NESFRUTA APPLE 200ML")){
				textfield.setValue(dataforvisitchilleravailableproducts.getObjectValue("Nesfruta Apple 200ml", 1))
				Mobile.hideKeyboard()
			}
			else if(lastitemnameafterswipe.equals("FRUITA VITALS KINOW 200ML")){
				textfield.setValue(dataforvisitchilleravailableproducts.getObjectValue("Fruita Vitals Kinow 200ml", 1))
				Mobile.hideKeyboard()
			}
			else if(lastitemnameafterswipe.equals("FRUITA VITALS GUAVA 200ML")){
				textfield.setValue(dataforvisitchilleravailableproducts.getObjectValue("Fruita Vitals Guava 200ml", 1))
				Mobile.hideKeyboard()
			}
			else if(lastitemnameafterswipe.equals("FRUITA VITALS RED GRAPES 200ML")){
				textfield.setValue(dataforvisitchilleravailableproducts.getObjectValue("Fruita Vitals Red Grapes 200ml", 1))
				Mobile.hideKeyboard()
			}
			else if(lastitemnameafterswipe.equals("FRUITA VITALS PEACH 200ML")){
				textfield.setValue(dataforvisitchilleravailableproducts.getObjectValue("Fruita Vitals Peach 200ml", 1))
				Mobile.hideKeyboard()
			}
		}
	}
	@Keyword
	def visitChillerAvailableWaterProducts(){
		int index = 0
		ArrayList<MobileElement> products = driver.findElementsByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ScrollView[1]/android.widget.LinearLayout[1]/*")
		for(int i=2; i<=products.size(); i=i+3){
			index = index + 1
			MobileElement product = driver.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ScrollView[1]/android.widget.LinearLayout[1]/android.widget.TextView["+index+"]")
			String productname = product.getText()
			MobileElement textfield = driver.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ScrollView[1]/android.widget.LinearLayout[1]/android.widget.LinearLayout["+index+"]/android.widget.EditText[1]")
			if(productname.equals("Water Npl 500ML")){
				textfield.setValue(dataforvisitchilleravailableproducts.getObjectValue("Water Npl 500ml", 1))
				Mobile.hideKeyboard()
			}
			else if(productname.equals("Water Npl 1500ML")){
				textfield.setValue(dataforvisitchilleravailableproducts.getObjectValue("Water Npl 1500ml", 1))
				Mobile.hideKeyboard()
			}
		}
	}
	@Keyword
	def visitChillerAvailableYogurtProducts(){
		int index = 0
		ArrayList<MobileElement> products = driver.findElementsByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ScrollView[1]/android.widget.LinearLayout[1]/*")
		for(int i=2; i<=products.size(); i=i+3){
			index = index + 1
			MobileElement product = driver.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ScrollView[1]/android.widget.LinearLayout[1]/android.widget.TextView["+index+"]")
			String productname = product.getText()
			MobileElement textfield = driver.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ScrollView[1]/android.widget.LinearLayout[1]/android.widget.LinearLayout["+index+"]/android.widget.EditText[1]")
			if(productname.equals("MILKPAK YOGURT POUCH 500GM")){
				textfield.setValue(dataforvisitchilleravailableproducts.getObjectValue("Milkpak Yogurt Pouch 500gm", 1))
				Mobile.hideKeyboard()
			}
			else if(productname.equals("Sweet & Tasty 400GM")){
				textfield.setValue(dataforvisitchilleravailableproducts.getObjectValue("Sweet & Tasty 400gm", 1))
				Mobile.hideKeyboard()
			}
			else if(productname.equals("Nestle Nesvita YOGURT 400GM")){
				textfield.setValue(dataforvisitchilleravailableproducts.getObjectValue("Nestle Nesvita Yogurt 400gm", 1))
				Mobile.hideKeyboard()
			}
			else if(productname.equals("Rawaity Maza 400GM")){
				textfield.setValue(dataforvisitchilleravailableproducts.getObjectValue("Rawaity Maza 400gm", 1))
				Mobile.hideKeyboard()
			}
			else if(productname.equals("NESTLE ZEERA RAITA 250GM")){
				textfield.setValue(dataforvisitchilleravailableproducts.getObjectValue("Nestle Zeera Raita 250gm", 1))
				Mobile.hideKeyboard()
			}
			else if(productname.equals("Mint Raita 250GM")){
				textfield.setValue(dataforvisitchilleravailableproducts.getObjectValue("Main Raita 250gm", 1))
				Mobile.hideKeyboard()
			}
		}
	}
	@Keyword
	def visitChillerNotAvailableProductsCategories(){
		ArrayList<MobileElement> categories = driver.findElementsByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.ListView[1]/*")
		for(int i=1; i<=categories.size(); i++){
			MobileElement category = driver.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout["+i+"]/android.widget.TextView[1]")
			String categoryname = category.getText()
			if(categoryname.equals("Juices - 200ML")){
				driver.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout["+i+"]").click()
				Mobile.callTestCase(findTestCase("Test Cases/ShopOpen/ChillerUtilization/ChillerNotAvailableProducts/VisitJuices-200ML"), null)
			}
			categories = driver.findElementsByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.ListView[1]/*")
		}
	}
	@Keyword
	def visitChillerNotAvailableJuices200mlProducts(){
		int index = 0
		ArrayList<MobileElement> products = driver.findElementsByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ScrollView[1]/android.widget.LinearLayout[1]/*")
		for(int i=2; i<=products.size(); i=i+3){
			index = index + 1
			MobileElement product = driver.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ScrollView[1]/android.widget.LinearLayout[1]/android.widget.TextView["+index+"]")
			String productname = product.getText()
			MobileElement textfield = driver.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ScrollView[1]/android.widget.LinearLayout[1]/android.widget.LinearLayout["+index+"]/android.widget.EditText[1]")
			if(productname.equals("NESFRUTA MANGO 200ML")){
				textfield.setValue(dataforvisitchillernotavailableproducts.getObjectValue("Nesfruta Mango 200ml", 1))
				Mobile.hideKeyboard()
			}
			else if(productname.equals("FRUITA VITALS CHAUNSA 200ML")){
				textfield.setValue(dataforvisitchillernotavailableproducts.getObjectValue("Fruita Vitals Chaunsa 200ml", 1))
				Mobile.hideKeyboard()
			}
			else if(productname.equals("FRUITA VITALS RED GRAPES 200ML")){
				textfield.setValue(dataforvisitchillernotavailableproducts.getObjectValue("Fruita Vitals Red Grapes 200ml", 1))
				Mobile.hideKeyboard()
			}
			else if(productname.equals("FRUITA VITALS APPLE 200ML")){
				textfield.setValue(dataforvisitchillernotavailableproducts.getObjectValue("Fruita Vitals Apple 200ml", 1))
				Mobile.hideKeyboard()
			}
		}
	}
	@Keyword
	def continueChillerAvailableModuleFlowForChillerNeedMaintenance(){
		Mobile.callTestCase(findTestCase("Test Cases/ShopOpen/ChillerUtilization/ChillerAvailable/VisitChillerAvailable"), null)
	}
	@Keyword
	def continueChillerNotAvailableModuleFlowForChillerRemovedForMaintenance(){
		Mobile.callTestCase(findTestCase("Test Cases/ShopOpen/ChillerUtilization/ChillerNotAvailable/VisitChillerNotAvailable"), null)
	}
	@Keyword
	def continueChillerNotAvailableModuleFlowForChillerNotInAccess(){
		Mobile.callTestCase(findTestCase("Test Cases/ShopOpen/ChillerUtilization/ChillerNotAvailable/VisitChillerNotAvailable"), null)
	}
	@Keyword
	def comapareImages(){
		int index = 0
		final int[] image1Data = null
		File screenshot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE)
		BufferedImage fullImg = ImageIO.read(screenshot)

		MobileElement expectedimageview = driver.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.GridView[1]/android.widget.RelativeLayout[1]/android.widget.ImageView[1]")

		int imagewidth = expectedimageview.getSize().getWidth()
		int imageheight = expectedimageview.getSize().getHeight()

		Point expectedimagepointer = expectedimageview.getLocation()

		Image expectedimage = fullImg.getSubimage(expectedimagepointer.getX(), expectedimagepointer.getY(),imagewidth, imageheight)

		PixelGrabber grabImage1Pixels = new PixelGrabber(expectedimage, 0, 0, -1,-1, false)

		if (grabImage1Pixels.grabPixels()) {
			int width = grabImage1Pixels.getWidth()
			int height = grabImage1Pixels.getHeight()
			image1Data = new int[width * height]
			image1Data = (int[]) grabImage1Pixels.getPixels()
		}

		try {
			ArrayList<MobileElement> images = driver.findElementsByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.GridView[1]/*")
			for(int i=1; i<=(images.size()-2); i++){
				MobileElement actualimageview = driver.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.GridView[1]/android.widget.RelativeLayout["+i+"]/android.widget.ImageView[1]")

				Point actualimagepointer = actualimageview.getLocation()

				Image actualimage = fullImg.getSubimage(actualimagepointer.getX(), actualimagepointer.getY(),imagewidth, imageheight)

				final PixelGrabber grabImage2Pixels = new PixelGrabber(actualimage, 0, 0, -1,-1, false)
				int[] image2Data = null
				if (grabImage2Pixels.grabPixels()) {
					int width = grabImage2Pixels.getWidth()
					int height = grabImage2Pixels.getHeight()
					image2Data = new int[width * height]
					image2Data = (int[]) grabImage2Pixels.getPixels()
				}

				if(!java.util.Arrays.equals(image1Data, image2Data)){
					driver.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.GridView[1]/android.widget.RelativeLayout["+i+"]").click()
					Mobile.verifyElementExist(findTestObject("Object Repository/CommonScreenElements/Validate_CameraScreen"), 0)
					Mobile.tap(findTestObject("Object Repository/CommonScreenElements/TakePictureButton"), 0)
					Mobile.waitForElementPresent(findTestObject("Object Repository/CommonScreenElements/DoneButton"), 0)
					Mobile.tap(findTestObject("Object Repository/CommonScreenElements/DoneButton"), 0)
					break
				}
			}
			images = driver.findElementsByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.GridView[1]/*")
		}
		catch (InterruptedException e1) {
			e1.printStackTrace()
		}
		//		Mobile.swipe(0, 370, 0,200)
		//		while(true){
		//			index = driver.findElementsByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.GridView[1]/*").size()
		//			MobileElement productbeforeswipe = driver.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.GridView[1]/android.widget.RelativeLayout["+index+"]/android.widget.TextView[1]")
		//			String productnamebeforeswipe = productbeforeswipe.getText()
		//			Mobile.swipe(0, 540, 0, 200)
		//			MobileElement productafterswipe = driver.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.GridView[1]/android.widget.RelativeLayout["+index+"]/android.widget.TextView[1]")
		//			String productnameafterswipe = productafterswipe.getText()
		//			if(productnamebeforeswipe.equals(productnameafterswipe)){
		//				Mobile.swipe(0, 500, 0, 200)
		//				if(index == 6){
		//					index = index - 2
		//				}
		//				else{
		//					index = index - 1
		//				}
		//				for(int j=3; j<=index; j++){
		//					MobileElement actualimageview = driver.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.GridView[1]/android.widget.RelativeLayout["+j+"]/android.widget.ImageView[1]")
		//
		//					Point actualimagepointer = actualimageview.getLocation()
		//
		//					Image actualimage = fullImg.getSubimage(actualimagepointer.getX(), actualimagepointer.getY(),imagewidth, imageheight)
		//
		//					PixelGrabber grabImage2Pixels = new PixelGrabber(actualimage, 0, 0, -1,-1, false)
		//					int[] image2Data = null
		//					if (grabImage2Pixels.grabPixels()) {
		//						int width = grabImage2Pixels.getWidth()
		//						int height = grabImage2Pixels.getHeight()
		//						image2Data = new int[width * height]
		//						image2Data = (int[]) grabImage2Pixels.getPixels()
		//					}
		//
		//					if(!java.util.Arrays.equals(image1Data, image2Data)){
		//						driver.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.GridView[1]/android.widget.RelativeLayout["+j+"]").click()
		//						Mobile.verifyElementExist(findTestObject("Object Repository/CommonScreenElements/Validate_CameraScreen"), 0)
		//						Mobile.tap(findTestObject("Object Repository/CommonScreenElements/TakePictureButton"), 0)
		//						Mobile.waitForElementPresent(findTestObject("Object Repository/CommonScreenElements/DoneButton"), 0)
		//						Mobile.tap(findTestObject("Object Repository/CommonScreenElements/DoneButton"), 0)
		//					}
		//				}
		//				break
		//			}
		//			else{
		//				if(index == 6){
		//					index = index - 2
		//				}
		//				else{
		//					index = index - 1
		//				}
		//				for(int j=3; j<=index; j++){
		//					MobileElement actualimageview = driver.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.GridView[1]/android.widget.RelativeLayout["+j+"]/android.widget.ImageView[1]")
		//
		//					Point actualimagepointer = actualimageview.getLocation()
		//
		//					Image actualimage = fullImg.getSubimage(actualimagepointer.getX(), actualimagepointer.getY(),imagewidth, imageheight)
		//
		//					PixelGrabber grabImage2Pixels = new PixelGrabber(actualimage, 0, 0, -1,-1, false)
		//					int[] image2Data = null
		//					if (grabImage2Pixels.grabPixels()) {
		//						int width = grabImage2Pixels.getWidth()
		//						int height = grabImage2Pixels.getHeight()
		//						image2Data = new int[width * height]
		//						image2Data = (int[]) grabImage2Pixels.getPixels()
		//					}
		//
		//					if(!java.util.Arrays.equals(image1Data, image2Data)){
		//						driver.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.GridView[1]/android.widget.RelativeLayout["+j+"]").click()
		//						Mobile.verifyElementExist(findTestObject("Object Repository/CommonScreenElements/Validate_CameraScreen"), 0)
		//						Mobile.tap(findTestObject("Object Repository/CommonScreenElements/TakePictureButton"), 0)
		//						Mobile.waitForElementPresent(findTestObject("Object Repository/CommonScreenElements/DoneButton"), 0)
		//						Mobile.tap(findTestObject("Object Repository/CommonScreenElements/DoneButton"), 0)
		//					}
		//				}
		//			}
		//		}
	}
}
