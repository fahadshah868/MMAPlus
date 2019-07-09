package qa.keywords

import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import java.time.Duration

import org.junit.After

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.checkpoint.Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.helper.MobileElementCommonHelper
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testcase.TestCase
import com.kms.katalon.core.testdata.TestData
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.util.KeywordUtil
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.mysql.jdbc.DatabaseMetaData.SingleStringIterator
import qa.constants.ProjectConstants
import qa.struct.MissingShopDataInfo
import qa.struct.MissingSliderOptions
import qa.struct.UnmatchedItems
import qa.struct.VisitedShopDataInfo
import internal.GlobalVariable
import io.appium.java_client.MobileElement
import io.appium.java_client.TouchAction

public class SliderOptionsKeywords {
	@Keyword
	def valideSliderOptions(){
		UnmatchedItems unmatcheditems = CompareDataKeywords.compareSliderOptions()
		if(unmatcheditems.getStatus() == 2){
			ProjectConstants.missingslideroptions.setMissingslideroptions(unmatcheditems.getItems())
			ProjectConstants.missingslideroptions.setMessageformissingslideroptions(ProjectConstants.MESSAGEFOR_ITEMSARE_NOTMATCH)
		}
		else if(unmatcheditems.getStatus() == 1){
			ProjectConstants.missingslideroptions.setMissingslideroptions(unmatcheditems.getItems())
			ProjectConstants.missingslideroptions.setMessageformissingslideroptions(ProjectConstants.MESSAGEFOR_ITEMSARE_MORE)
		}
		else if(unmatcheditems.getStatus() == -1){
			ProjectConstants.missingslideroptions.setMissingslideroptions(unmatcheditems.getItems())
			ProjectConstants.missingslideroptions.setMessageformissingslideroptions(ProjectConstants.MESSAGEFOR_ITEMSARE_MISSING)
		}
		else{
		}
	}
	@Keyword
	def displayMissingSliderOptionsInReport(){
		if(!ProjectConstants.missingslideroptions.getMissingslideroptions().isEmpty()){
			String message = "\n\n-----------------------------------------------------Missing Slider Options------------------------------------------------------------------------------------------\n\n"+
					String.format("%-30s", "Slider Options:")
			for(int i=0; i<ProjectConstants.missingslideroptions.getMissingslideroptions().size() ;i++){
				message = message +
						ProjectConstants.missingslideroptions.getMissingslideroptions().get(i)+", "
			}
			message = message + "\n" +ProjectConstants.missingslideroptions.getMessageformissingslideroptions()+
					"\n\n---------------------------------------------------------------------------------------------------------------------------------------------------------------------\n\n"
			KeywordUtil.logInfo(message)
		}
		DisplayReportKeywords.displayDataInReport()
		ProjectConstants.missingshopdatainfo = new ArrayList<MissingShopDataInfo>()
		ProjectConstants.visitedshopdatainfo = new ArrayList<VisitedShopDataInfo>()
	}
	@Keyword
	def findSliderOption(String option){
		TouchAction touchaction = new TouchAction(ProjectConstants.DRIVER)
		Mobile.swipe(5, 200, 5, 500)
		Mobile.swipe(5, 200, 5, 500)
		int index = 0
		boolean flag = false
		MobileElement listcontainer = ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.support.v4.widget.DrawerLayout[1]/android.widget.LinearLayout[2]/android.widget.ListView[1]")
		ArrayList<MobileElement> slideroptions =listcontainer.findElementsByClassName("android.widget.TextView")
		for(int i=0; i< slideroptions.size(); i++){
			MobileElement slideroption = slideroptions.get(i)
			String slideroptionname = slideroption.getText()
			if(slideroptionname.equalsIgnoreCase(option)){
				flag = true
				slideroption.click()
				if(option.equalsIgnoreCase("Update Profile")){
					Mobile.verifyElementExist(findTestObject("Object Repository/SliderOptions/UpdateProfile_EnterEmail" , [('package') : ProjectConstants.PACKAGENAME]), 0)
					Mobile.verifyElementExist(findTestObject("Object Repository/SliderOptions/UpdateProfile_EnterPhone" , [('package') : ProjectConstants.PACKAGENAME]), 0)
					Mobile.verifyElementExist(findTestObject("Object Repository/SliderOptions/UpdateProfile_EnterCNIC" , [('package') : ProjectConstants.PACKAGENAME]), 0)
					Mobile.pressBack()
					Mobile.verifyElementExist(findTestObject("Object Repository/SliderOptions/Validate_SliderItemsScreen" , [('package') : ProjectConstants.PACKAGENAME]), 0)
				}
				else if(option.equalsIgnoreCase("Out of Route")){
					Mobile.verifyElementText(findTestObject('SliderOptions/Validate_OutOfRoute_RouteListScreen' , [('package') : ProjectConstants.PACKAGENAME]), 'Route LIST')
					findRoute()
					Mobile.verifyElementText(findTestObject('SliderOptions/Validate_OutOfRoute_ShopOnRouteScreen' , [('package') : ProjectConstants.PACKAGENAME]), 'Shops on Route')
					ShopVisitingScenariosKeywords.visitSliderShops("Out Of Route Shops")
					Mobile.pressBack()
					Mobile.verifyElementText(findTestObject('SliderOptions/Validate_OutOfRoute_RouteListScreen' , [('package') : ProjectConstants.PACKAGENAME]), 'Route LIST')
					Mobile.pressBack()
					Mobile.verifyElementExist(findTestObject("Object Repository/SliderOptions/Validate_SliderItemsScreen" , [('package') : ProjectConstants.PACKAGENAME]), 0)
				}
				else if(option.equalsIgnoreCase("Info")){
					Mobile.verifyElementText(findTestObject('SliderOptions/Validate_LoginCode' , [('package') : ProjectConstants.PACKAGENAME]), 'Login Code')
					Mobile.verifyElementText(findTestObject('SliderOptions/Validate_LoginTime' , [('package') : ProjectConstants.PACKAGENAME]), 'Login Time')
					Mobile.verifyElementText(findTestObject('SliderOptions/Validate_SyncAttempted' , [('package') : ProjectConstants.PACKAGENAME]), 'Sync Attempted')
					Mobile.verifyElementText(findTestObject('SliderOptions/Validate_SyncSuccessful' , [('package') : ProjectConstants.PACKAGENAME]), 'Sync Successful')
					Mobile.verifyElementText(findTestObject('SliderOptions/Validate_RefreshRoutes' , [('package') : ProjectConstants.PACKAGENAME]), 'Refresh Routes')
					Mobile.verifyElementText(findTestObject('SliderOptions/Validate_BuildVersion' , [('package') : ProjectConstants.PACKAGENAME]), 'Build Version')
					Mobile.pressBack()
					Mobile.verifyElementExist(findTestObject("Object Repository/SliderOptions/Validate_SliderItemsScreen" , [('package') : ProjectConstants.PACKAGENAME]), 0)
				}
				else if(option.equalsIgnoreCase("Abnormal Shops")){
					Mobile.delay(10)
					try{
						Mobile.verifyElementExist(findTestObject("Object Repository/Validate_ShopListScreen" , [('package') : ProjectConstants.PACKAGENAME]), 20)
						Mobile.verifyElementText(findTestObject("Object Repository/Validate_ShopListScreen" , [('package') : ProjectConstants.PACKAGENAME]), "Shops on Route")
						ShopVisitingScenariosKeywords.visitSliderShops("Abnormal Shops")
						Mobile.pressBack()
					}
					catch(Exception ex){
						Mobile.verifyElementExist(findTestObject("Object Repository/CommonScreenElements/Validate_InfoPopUP" , [('package') : ProjectConstants.PACKAGENAME]), 0)
						Mobile.tap(findTestObject("Object Repository/CommonScreenElements/InfoPopUp_OKButton" , [('package') : ProjectConstants.PACKAGENAME]), 0)
					}
				}
				else if(option.equalsIgnoreCase("RedFlag Shops")){
					Mobile.delay(10)
					try{
						Mobile.verifyElementExist(findTestObject("Object Repository/Validate_ShopListScreen" , [('package') : ProjectConstants.PACKAGENAME]), 20)
						Mobile.verifyElementText(findTestObject("Object Repository/Validate_ShopListScreen" , [('package') : ProjectConstants.PACKAGENAME]), "Shops on Route")
						ShopVisitingScenariosKeywords.visitSliderShops("RedFlag Shops")
						Mobile.pressBack()
					}
					catch(Exception ex){
						Mobile.verifyElementExist(findTestObject("Object Repository/CommonScreenElements/Validate_InfoPopUP" , [('package') : ProjectConstants.PACKAGENAME]), 0)
						Mobile.tap(findTestObject("Object Repository/CommonScreenElements/InfoPopUp_OKButton" , [('package') : ProjectConstants.PACKAGENAME]), 0)
					}
				}
				break
			}
			else{}
		}
		if(flag == false){
			touchaction.press(0, 230).waitAction(Duration.ofMillis(500)).moveTo(0, 200).release().perform()
			Thread.sleep(500)
			while(true){
				slideroptions = listcontainer.findElementsByClassName("android.widget.TextView")
				MobileElement lastitembeforeswipe = slideroptions.get((slideroptions.size()-1))
				String lastitemnamebeforeswipe = lastitembeforeswipe.getText()
				// swipe using touch action from element to element
				index = slideroptions.size()
				MobileElement startpoint = ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.support.v4.widget.DrawerLayout[1]/android.widget.LinearLayout[2]/android.widget.ListView[1]/android.widget.LinearLayout["+index+"]")
				MobileElement endpoint = ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.support.v4.widget.DrawerLayout[1]/android.widget.LinearLayout[2]/android.widget.ListView[1]/android.widget.LinearLayout[1]")
				touchaction.longPress(startpoint).waitAction(Duration.ofMillis(500)).moveTo(endpoint).release().perform()
				Thread.sleep(500)
				slideroptions = listcontainer.findElementsByClassName("android.widget.TextView")
				MobileElement lastitemafterswipe = slideroptions.get((slideroptions.size()-1))
				String lastitemnameafterswipe = lastitemafterswipe.getText()
				if(lastitemnamebeforeswipe.equalsIgnoreCase(lastitemnameafterswipe)){
					break
				}
				else{
					slideroptions = listcontainer.findElementsByClassName("android.widget.TextView")
					for(int i=0; i< slideroptions.size(); i++){
						MobileElement slideroption = slideroptions.get(i)
						String slideroptionname = slideroption.getText()
						if(slideroptionname.equalsIgnoreCase(option)){
							flag = true
							slideroption.click()
							if(option.equalsIgnoreCase("Update Profile")){
								Mobile.verifyElementExist(findTestObject("Object Repository/SliderOptions/UpdateProfile_EnterEmail" , [('package') : ProjectConstants.PACKAGENAME]), 0)
								Mobile.verifyElementExist(findTestObject("Object Repository/SliderOptions/UpdateProfile_EnterPhone" , [('package') : ProjectConstants.PACKAGENAME]), 0)
								Mobile.verifyElementExist(findTestObject("Object Repository/SliderOptions/UpdateProfile_EnterCNIC" , [('package') : ProjectConstants.PACKAGENAME]), 0)
								Mobile.pressBack()
								Mobile.verifyElementExist(findTestObject("Object Repository/SliderOptions/Validate_SliderItemsScreen" , [('package') : ProjectConstants.PACKAGENAME]), 0)
							}
							else if(option.equalsIgnoreCase("Out of Route")){
								Mobile.verifyElementText(findTestObject('SliderOptions/Validate_OutOfRoute_RouteListScreen' , [('package') : ProjectConstants.PACKAGENAME]), 'Route LIST')
								findRoute()
								Mobile.verifyElementText(findTestObject('SliderOptions/Validate_OutOfRoute_ShopOnRouteScreen' , [('package') : ProjectConstants.PACKAGENAME]), 'Shops on Route')
								ShopVisitingScenariosKeywords.visitSliderShops("Out Of Route Shops")
								Mobile.pressBack()
								Mobile.verifyElementText(findTestObject('SliderOptions/Validate_OutOfRoute_RouteListScreen' , [('package') : ProjectConstants.PACKAGENAME]), 'Route LIST')
								Mobile.pressBack()
								Mobile.verifyElementExist(findTestObject("Object Repository/SliderOptions/Validate_SliderItemsScreen" , [('package') : ProjectConstants.PACKAGENAME]), 0)
							}
							else if(option.equalsIgnoreCase("Info")){
								Mobile.verifyElementText(findTestObject('SliderOptions/Validate_LoginCode' , [('package') : ProjectConstants.PACKAGENAME]), 'Login Code')
								Mobile.verifyElementText(findTestObject('SliderOptions/Validate_LoginTime' , [('package') : ProjectConstants.PACKAGENAME]), 'Login Time')
								Mobile.verifyElementText(findTestObject('SliderOptions/Validate_SyncAttempted' , [('package') : ProjectConstants.PACKAGENAME]), 'Sync Attempted')
								Mobile.verifyElementText(findTestObject('SliderOptions/Validate_SyncSuccessful' , [('package') : ProjectConstants.PACKAGENAME]), 'Sync Successful')
								Mobile.verifyElementText(findTestObject('SliderOptions/Validate_RefreshRoutes' , [('package') : ProjectConstants.PACKAGENAME]), 'Refresh Routes')
								Mobile.verifyElementText(findTestObject('SliderOptions/Validate_BuildVersion' , [('package') : ProjectConstants.PACKAGENAME]), 'Build Version')
								Mobile.pressBack()
								Mobile.verifyElementExist(findTestObject("Object Repository/SliderOptions/Validate_SliderItemsScreen" , [('package') : ProjectConstants.PACKAGENAME]), 0)
							}
							else if(option.equalsIgnoreCase("Abnormal Shops")){
								Mobile.delay(10)
								try{
									Mobile.verifyElementExist(findTestObject("Object Repository/Validate_ShopListScreen" , [('package') : ProjectConstants.PACKAGENAME]), 20)
									Mobile.verifyElementText(findTestObject("Object Repository/Validate_ShopListScreen" , [('package') : ProjectConstants.PACKAGENAME]), "Shops on Route")
									ShopVisitingScenariosKeywords.visitSliderShops("Abnormal Shops")
									Mobile.pressBack()
								}
								catch(Exception ex){
									Mobile.verifyElementExist(findTestObject("Object Repository/CommonScreenElements/Validate_InfoPopUP" , [('package') : ProjectConstants.PACKAGENAME]), 0)
									Mobile.tap(findTestObject("Object Repository/CommonScreenElements/InfoPopUp_OKButton" , [('package') : ProjectConstants.PACKAGENAME]), 0)
								}
							}
							else if(option.equalsIgnoreCase("RedFlag Shops")){
								Mobile.delay(10)
								try{
									Mobile.verifyElementExist(findTestObject("Object Repository/Validate_ShopListScreen" , [('package') : ProjectConstants.PACKAGENAME]), 20)
									Mobile.verifyElementText(findTestObject("Object Repository/Validate_ShopListScreen" , [('package') : ProjectConstants.PACKAGENAME]), "Shops on Route")
									ShopVisitingScenariosKeywords.visitSliderShops("RedFlag Shops")
									Mobile.pressBack()
								}
								catch(Exception ex){
									Mobile.verifyElementExist(findTestObject("Object Repository/CommonScreenElements/Validate_InfoPopUP" , [('package') : ProjectConstants.PACKAGENAME]), 0)
									Mobile.tap(findTestObject("Object Repository/CommonScreenElements/InfoPopUp_OKButton" , [('package') : ProjectConstants.PACKAGENAME]), 0)
								}
							}
							break
						}
						else{}
					}
				}
			}
		}
		else{}
	}
	@Keyword
	def findRoute(){
		Calendar calendar = Calendar.getInstance()
		int day = calendar.get(Calendar.DAY_OF_WEEK)
		if(day == 1){
			getRoute("Monday")
		}
		else if(day == 2){
			getRoute("Tuesday")
		}
		else if(day == 3){
			getRoute("Wednesday")
		}
		else if(day == 4){
			getRoute("Thursday")
		}
		else if(day == 5){
			getRoute("Friday")
		}
		else if(day == 6){
			getRoute("Saturday")
		}
		else if(day == 7){
			getRoute("Saturday")
		}
	}
	def getRoute(String _route){
		int totalroutes = ProjectConstants.DRIVER.findElementsByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.ListView[1]/*").size()
		for(int i=1; i<=totalroutes; i++){
			MobileElement route = ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout["+i+"]/android.widget.TextView[1]")
			String routename = route.getText()
			if(routename.equalsIgnoreCase(_route)){
				ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout["+i+"]").click()
				break
			}
		}
	}
}
