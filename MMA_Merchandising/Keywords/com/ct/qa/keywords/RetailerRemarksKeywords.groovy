package com.ct.qa.keywords

import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

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

import MobileBuiltInKeywords as Mobile
import WSBuiltInKeywords as WS
import WebUiBuiltInKeywords as WebUI
import com.ct.qa.constants.ProjectConstants
import io.appium.java_client.MobileElement

public class RetailerRemarksKeywords {
	@Keyword
	def visitRetailerRemarks(){
		int totalproducts = ProjectConstants.DRIVER.findElementsByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ListView[1]/*").size()
		for(int i=1; i<=totalproducts; i++){
			ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout["+i+"]").click()
			Mobile.verifyElementExist(findTestObject("Object Repository/ShopOpen/RetailerRemarks/Validate_RetailerRemarks_RemarksScreen" , [('package') : ProjectConstants.PACKAGENAME]), 0)
			int totalremarks = ProjectConstants.DRIVER.findElementsByClassName("android.widget.CheckBox").size()
			for(int j=1; j<= totalremarks; j++){
				MobileElement remark = ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.ScrollView[1]/android.widget.LinearLayout[1]/android.widget.LinearLayout["+j+"]/android.widget.CheckBox[1]")
				String remark_text = remark.getText()
				if(remark_text.equalsIgnoreCase("OB not Visiting")){
					ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.ScrollView[1]/android.widget.LinearLayout[1]/android.widget.LinearLayout["+j+"]/android.widget.CheckBox[1]").click()
					break
				}
			}
			Mobile.pressBack()
			Mobile.verifyElementText(findTestObject("Object Repository/ShopOpen/RetailerRemarks/Validate_RetailerRemarksScreen" , [('package') : ProjectConstants.PACKAGENAME]), "KPI: Retailer Remarks")
		}
	}
	@Keyword
	def overwriteRetailerRemarks(){
		int totalproducts = ProjectConstants.DRIVER.findElementsByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ListView[1]/*").size()
		for(int i=1; i<=totalproducts; i++){
			ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout["+i+"]").click()
			Mobile.verifyElementExist(findTestObject("Object Repository/ShopOpen/RetailerRemarks/Validate_RetailerRemarks_RemarksScreen" , [('package') : ProjectConstants.PACKAGENAME]), 0)
			int totalremarks = ProjectConstants.DRIVER.findElementsByClassName("android.widget.CheckBox").size()
			for(int j=1; j<= totalremarks; j++){
				MobileElement remark = ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.ScrollView[1]/android.widget.LinearLayout[1]/android.widget.LinearLayout["+j+"]/android.widget.CheckBox[1]")
				String remark_text = remark.getText()
				if(remark_text.equalsIgnoreCase("SM not Visiting")){
					ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.ScrollView[1]/android.widget.LinearLayout[1]/android.widget.LinearLayout["+j+"]/android.widget.CheckBox[1]").click()
					break
				}
			}
			Mobile.pressBack()
			Mobile.verifyElementText(findTestObject("Object Repository/ShopOpen/RetailerRemarks/Validate_RetailerRemarksScreen" , [('package') : ProjectConstants.PACKAGENAME]), "KPI: Retailer Remarks")
		}
	}
}
