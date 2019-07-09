package qa.struct

import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

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

public class ScoreCard {

	public String user_role
	public String button_title
	public ArrayList<String> remarks
	public String missingremarks_errormessage

	public String getUser_role() {
		return user_role;
	}
	public void setUser_role(String user_role) {
		this.user_role = user_role;
	}
	public String getButton_title() {
		return button_title;
	}
	public void setButton_title(String button_title) {
		this.button_title = button_title;
	}
	public ArrayList<String> getRemarks() {
		return remarks;
	}
	public void setRemarks(ArrayList<String> remarks) {
		this.remarks = remarks;
	}
	public String getMissingremarks_errormessage() {
		return missingremarks_errormessage;
	}
	public void setMissingremarks_errormessage(String missingremarks_errormessage) {
		this.missingremarks_errormessage = missingremarks_errormessage;
	}
}
