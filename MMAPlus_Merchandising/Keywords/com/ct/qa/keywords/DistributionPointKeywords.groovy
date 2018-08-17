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

import org.apache.poi.ss.usermodel.DataFormatter
import org.apache.poi.ss.usermodel.Row
import org.apache.poi.xssf.usermodel.XSSFSheet
import org.apache.poi.xssf.usermodel.XSSFWorkbook

import MobileBuiltInKeywords as Mobile
import WSBuiltInKeywords as WS
import WebUiBuiltInKeywords as WebUI
import com.ct.qa.constants.ProjectConstants
import com.ct.qa.struct.LoadProductsData
import io.appium.java_client.MobileElement

public class DistributionPointKeywords {
	@Keyword
	def visitdistributionPoint(){
		DataFormatter dataFormatter = new DataFormatter()
		XSSFSheet sheet = LoadDataKeywords.loadDistributionPointSheet()
		int totalrows = sheet.getLastRowNum()
		int totaldistributionitems = ProjectConstants.DRIVER.findElementsByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/*").size()
		for(int i=1; i<=(totaldistributionitems-2); i++){
			MobileElement distributionitem = ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.LinearLayout["+i+"]/android.widget.TextView[1]")
			String distributionitemname = distributionitem.getText()
			for(int j=1; j<=totalrows; j++){
				Row row = sheet.getRow(j)
				String distributionpoint = row.getCell(0)
				String distributionpointvalue = dataFormatter.formatCellValue(row.getCell(1))
				if(distributionitemname.contains(distributionpoint)){
					MobileElement distributionitemtextfield = ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.LinearLayout["+i+"]/android.widget.EditText[1]")
					distributionitemtextfield.setValue(distributionpointvalue)
					Mobile.hideKeyboard()
					break
				}
			}
		}
	}
}
