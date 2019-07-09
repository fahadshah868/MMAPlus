package qa.keywords

import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import qa.constants.ProjectConstants
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

public class WorkActionsKeywords {

	@Keyword
	//select work action against action e.g. WR, WW, Merchandising
	def selectAction(String _action){
		int totalactions = ProjectConstants.DRIVER.findElementsByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.ListView[1]/*").size()
		for(int i=1; i<= totalactions; i++){
			MobileElement action = ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout["+i+"]/android.widget.TextView[1]")
			String action_text = action.getText()
			ProjectConstants.WORKING_ACTION = action_text
			ProjectConstants.SHOP_ATTEMPT = ProjectConstants.SHOP_ATTEMPT + 1
			if(action_text.equalsIgnoreCase(_action)){
				ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout["+i+"]").click()
				break
			}
		}
	}
	@Keyword
	//select sub work action e.g. un-captured shop OR route after work action e.g. WR, WW, Merchandising
	def selectSubAction(String _subaction){
		int totalactions = ProjectConstants.DRIVER.findElementsByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.ListView[1]/*").size()
		for(int i=1; i<= totalactions; i++){
			MobileElement action = ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout["+i+"]/android.widget.TextView[1]")
			String action_text = action.getText()
			if(action_text.equalsIgnoreCase(_subaction)){
				ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout["+i+"]").click()
				break
			}
		}
	}
	@Keyword
	//select route day
	def selectRoute(){
		MobileElement route = ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout[1]/android.widget.TextView[1]")
		String route_text = route.getText()
		ProjectConstants.CURRENTVISITING_ROUTE = route_text
		ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout[1]").click()
	}
	@Keyword
	//select same route day for different work actions e.g. WW, WR, Merchandising
	def selectSameRouteForDifferentWorkActions(String _route){
		int totalroutes = ProjectConstants.DRIVER.findElementsByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.ListView[1]/*").size()
		for(int i=1; i<= totalroutes; i++){
			MobileElement route = ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout["+i+"]/android.widget.TextView[1]")
			String route_text = route.getText()
			if(route_text.equalsIgnoreCase(_route)){
				ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout["+i+"]").click()
				break
			}
		}
	}
	@Keyword
	//check whether shops are available or not for un-captured shops
	def validateShopListScreenForUnCapturedShops(){
		if(Mobile.verifyElementExist(findTestObject("Object Repository/WorkActions/Validate_NoDataAvailable_Popup", [('package') : ProjectConstants.PACKAGENAME]), 10, FailureHandling.OPTIONAL)){
			Mobile.tap(findTestObject('Object Repository/CommonScreenElements/InfoPopUp_OKButton', [('package') : ProjectConstants.PACKAGENAME]), 0)
		}
		else{
			Mobile.verifyElementExist(findTestObject('Validate_UncapturedShopsList_Screen', [('package') : ProjectConstants.PACKAGENAME]), 100)
			Mobile.verifyElementText(findTestObject('Validate_UncapturedShopsList_Screen', [('package') : ProjectConstants.PACKAGENAME]), 'Uncaptured Shops')
			Mobile.pressBack()
			Mobile.verifyElementText(findTestObject('WorkActions/Validate_SubWorkActionsScreen'), 'Select')
		}
	}
}
