package com.ct.qa.keywords

import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import com.ct.qa.constants.ProjectConstants
import com.ct.qa.struct.QuestionsData
import com.ct.qa.struct.UnmatchedItems
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

public class AuditKeywords {

	@Keyword
	def visitQuestionCategories(){
		UnmatchedItems unmatcheditems = CompareDataKeywords.compareAuditQuestionCategories()
		if(unmatcheditems.getStatus() == 2){
			for(int j=0; j<ProjectConstants.missingshopdatainfo.size(); j++){
				if(ProjectConstants.missingshopdatainfo.get(j).getShopname().equalsIgnoreCase(ProjectConstants.CURRENTVISITING_SHOPNAME)) {
					ProjectConstants.missingshopdatainfo.get(j).setMissing_auditquestioncategories(unmatcheditems.getItems())
					ProjectConstants.missingshopdatainfo.get(j).setMissing_auditquestioncategories_errormessage(ProjectConstants.MESSAGEFOR_ITEMSARE_NOTMATCH)
					break
				}
				else{
				}
			}
		}
		else if(unmatcheditems.getStatus() == 1){
			for(int j=0; j<ProjectConstants.missingshopdatainfo.size(); j++){
				if(ProjectConstants.missingshopdatainfo.get(j).getShopname().equalsIgnoreCase(ProjectConstants.CURRENTVISITING_SHOPNAME)) {
					ProjectConstants.missingshopdatainfo.get(j).setMissing_auditquestioncategories(unmatcheditems.getItems())
					ProjectConstants.missingshopdatainfo.get(j).setMissing_auditquestioncategories_errormessage(ProjectConstants.MESSAGEFOR_ITEMSARE_MORE)
					break
				}
				else{
				}
			}
		}
		else if(unmatcheditems.getStatus() == -1){
			for(int j=0; j<ProjectConstants.missingshopdatainfo.size(); j++){
				if(ProjectConstants.missingshopdatainfo.get(j).getShopname().equalsIgnoreCase(ProjectConstants.CURRENTVISITING_SHOPNAME)) {
					ProjectConstants.missingshopdatainfo.get(j).setMissing_auditquestioncategories(unmatcheditems.getItems())
					ProjectConstants.missingshopdatainfo.get(j).setMissing_auditquestioncategories_errormessage(ProjectConstants.MESSAGEFOR_ITEMSARE_MISSING)
					break
				}
				else{
				}
			}
		}
		else{
		}
		int questioncategorieslist = ProjectConstants.DRIVER.findElementsByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.ListView[1]/*").size()
		for(int i=1; i<= questioncategorieslist; i++){
			MobileElement questioncategory = ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout["+i+"]/android.widget.TextView[1]")
			ProjectConstants.CURRENTVISITING_AUDITQUESTIONCATEGORY = questioncategory.getText()
			ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout["+i+"]").click()
			Mobile.callTestCase(findTestCase("Test Cases/ShopOpen/Audit/VisitQuestions"), null)
		}
	}
	@Keyword
	def visitQuestions(){
		int index = 0
		ArrayList<String> displayedquestions = new ArrayList<String>()
		ArrayList<String> expectedquestions = new ArrayList<String>()
		ArrayList<QuestionsData> expectedquestionslist = LoadDataKeywords.loadAuditQuestionsList()
		for(int i=0; i< expectedquestionslist.size(); i++){
			expectedquestions.add(expectedquestionslist.get(i).getQuestion())
		}
		int questionlist = ProjectConstants.DRIVER.findElementsByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ScrollView[1]/android.widget.LinearLayout[1]/*").size()
		for(int i=1; i<= questionlist; i++){
			if(i == questionlist){
				Mobile.swipe(0, 300, 0, 200)
				MobileElement question = ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ScrollView[1]/android.widget.LinearLayout[1]/android.widget.Spinner["+i+"]/android.widget.LinearLayout[1]/android.widget.TextView[1]")
				String questiontext = question.getText()
				displayedquestions.add(questiontext)
				for(int j=0; j< expectedquestionslist; j++){
					QuestionsData expectedquestion = expectedquestionslist.get(j)
					if(expectedquestion.getQuestion().equalsIgnoreCase(questiontext) && expectedquestion.getQuestionoption().equalsIgnoreCase("Y")){
						ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ScrollView[1]/android.widget.LinearLayout[1]/android.widget.Spinner["+i+"]").click()
						Mobile.verifyElementExist(findTestObject("Object Repository/ShopOpen/Audit/Validate_RemarksPopup"), 0)
						Mobile.tap(findTestObject("Object Repository/ShopOpen/Audit/RemarksPopup_Yes"), 0)
						if(expectedquestion.getQuestionoption_takepicture().equalsIgnoreCase("Y")){
							CommonKeywords.takeCategoryPrePicture()
						}
						else{
						}
					}
				}
			}
			else{
				MobileElement question = ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ScrollView[1]/android.widget.LinearLayout[1]/android.widget.Spinner["+i+"]/android.widget.LinearLayout[1]/android.widget.TextView[1]")
				String questiontext = question.getText()
				displayedquestions.add(questiontext)
				for(int j=0; j< expectedquestionslist; j++){
					QuestionsData expectedquestion = expectedquestionslist.get(j)
					if(expectedquestion.getQuestion().equalsIgnoreCase(questiontext) && expectedquestion.getQuestionoption().equalsIgnoreCase("Y")){
						ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ScrollView[1]/android.widget.LinearLayout[1]/android.widget.Spinner["+i+"]").click()
						Mobile.verifyElementExist(findTestObject("Object Repository/ShopOpen/Audit/Validate_RemarksPopup"), 0)
						Mobile.tap(findTestObject("Object Repository/ShopOpen/Audit/RemarksPopup_Yes"), 0)
						if(expectedquestion.getQuestionoption_takepicture().equalsIgnoreCase("Y")){
							CommonKeywords.takeCategoryPrePicture()
						}
						else{
						}
					}
				}
			}
		}
		while(true){
			index = ProjectConstants.DRIVER.findElementsByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ScrollView[1]/android.widget.LinearLayout[1]/*").size()
			MobileElement itembeforeswipe = ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ScrollView[1]/android.widget.LinearLayout[1]/android.widget.Spinner["+index+"]/android.widget.LinearLayout[1]/android.widget.TextView[1]")
			String itemtextbeforeswipe = itembeforeswipe.getText()
			Mobile.swipe(0, 348, 0, 200)
			index = ProjectConstants.DRIVER.findElementsByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ScrollView[1]/android.widget.LinearLayout[1]/*").size()
			MobileElement itemafterswipe = ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ScrollView[1]/android.widget.LinearLayout[1]/android.widget.Spinner["+index+"]/android.widget.LinearLayout[1]/android.widget.TextView[1]")
			String itemtextafterswipe = itemafterswipe.getText()
			if(itemtextbeforeswipe.equals(itemtextafterswipe)){
				break
			}
			else{
				MobileElement question = ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ScrollView[1]/android.widget.LinearLayout[1]/android.widget.Spinner["+i+"]/android.widget.LinearLayout[1]/android.widget.TextView[1]")
				String questiontext = question.getText()
				displayedquestions.add(questiontext)
				for(int j=0; j< expectedquestionslist; j++){
					QuestionsData expectedquestion = expectedquestionslist.get(j)
					if(expectedquestion.getQuestion().equalsIgnoreCase(questiontext) && expectedquestion.getQuestionoption().equalsIgnoreCase("Y")){
						ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ScrollView[1]/android.widget.LinearLayout[1]/android.widget.Spinner["+i+"]").click()
						Mobile.verifyElementExist(findTestObject("Object Repository/ShopOpen/Audit/Validate_RemarksPopup"), 0)
						Mobile.tap(findTestObject("Object Repository/ShopOpen/Audit/RemarksPopup_Yes"), 0)
						if(expectedquestion.getQuestionoption_takepicture().equalsIgnoreCase("Y")){
							CommonKeywords.takeCategoryPrePicture()
						}
						else{
						}
					}
				}
			}
		}
		ArrayList<String> _expectedquestions = new HashSet<String>(expectedquestions)
		UnmatchedItems unmatcheditems = CompareDataKeywords.compareLists(_expectedquestions, displayedquestions)
		if(unmatcheditems.getStatus() == 2){
			for(int j=0; j<ProjectConstants.missingshopdatainfo.size(); j++){
				if(ProjectConstants.missingshopdatainfo.get(j).getShopname().equalsIgnoreCase(ProjectConstants.CURRENTVISITING_SHOPNAME)) {
					ProjectConstants.missingshopdatainfo.get(j).setMissing_auditquestions(unmatcheditems.getItems())
					ProjectConstants.missingshopdatainfo.get(j).setMissing_auditquestions_errormessage(ProjectConstants.MESSAGEFOR_ITEMSARE_NOTMATCH)
					break
				}
				else{
				}
			}
		}
		else if(unmatcheditems.getStatus() == 1){
			for(int j=0; j<ProjectConstants.missingshopdatainfo.size(); j++){
				if(ProjectConstants.missingshopdatainfo.get(j).getShopname().equalsIgnoreCase(ProjectConstants.CURRENTVISITING_SHOPNAME)) {
					ProjectConstants.missingshopdatainfo.get(j).setMissing_auditquestions(unmatcheditems.getItems())
					ProjectConstants.missingshopdatainfo.get(j).setMissing_auditquestions_errormessage(ProjectConstants.MESSAGEFOR_ITEMSARE_MORE)
					break
				}
				else{
				}
			}
		}
		else if(unmatcheditems.getStatus() == -1){
			for(int j=0; j<ProjectConstants.missingshopdatainfo.size(); j++){
				if(ProjectConstants.missingshopdatainfo.get(j).getShopname().equalsIgnoreCase(ProjectConstants.CURRENTVISITING_SHOPNAME)) {
					ProjectConstants.missingshopdatainfo.get(j).setMissing_auditquestions(unmatcheditems.getItems())
					ProjectConstants.missingshopdatainfo.get(j).setMissing_auditquestions_errormessage(ProjectConstants.MESSAGEFOR_ITEMSARE_MISSING)
					break
				}
				else{
				}
			}
		}
		else{
		}
	}
}
