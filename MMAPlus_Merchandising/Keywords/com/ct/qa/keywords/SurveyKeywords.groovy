package com.ct.qa.keywords

import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import com.ct.qa.constants.ProjectConstants
import com.ct.qa.struct.MissingCategoryData
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

public class SurveyKeywords {

	@Keyword
	def visitQuestionCategories(int flag){
		MissingCategoryData missingcategory = new MissingCategoryData()
		UnmatchedItems unmatcheditems = CompareDataKeywords.compareSurveyQuestionCategories()
		if(unmatcheditems.getStatus() == 2){
			for(int j=0; j<ProjectConstants.missingshopdatainfo.size(); j++){
				if(ProjectConstants.missingshopdatainfo.get(j).getShopname().equalsIgnoreCase(ProjectConstants.CURRENTVISITING_SHOPNAME)) {
					missingcategory.setMaincategory(ProjectConstants.CURRENTVISITING_MAINCATEGORY)
					missingcategory.setMissing_auditquestioncategories(unmatcheditems.getItems())
					missingcategory.setMissing_auditquestioncategories_errormessage(ProjectConstants.MESSAGEFOR_ITEMSARE_NOTMATCH)
					ProjectConstants.missingshopdatainfo.get(j).setMissingCategoriesData(missingcategory, "")
					break
				}
				else{
				}
			}
		}
		else if(unmatcheditems.getStatus() == 1){
			for(int j=0; j<ProjectConstants.missingshopdatainfo.size(); j++){
				if(ProjectConstants.missingshopdatainfo.get(j).getShopname().equalsIgnoreCase(ProjectConstants.CURRENTVISITING_SHOPNAME)) {
					missingcategory.setMaincategory(ProjectConstants.CURRENTVISITING_MAINCATEGORY)
					missingcategory.setMissing_auditquestioncategories(unmatcheditems.getItems())
					missingcategory.setMissing_auditquestioncategories_errormessage(ProjectConstants.MESSAGEFOR_ITEMSARE_MORE)
					ProjectConstants.missingshopdatainfo.get(j).setMissingCategoriesData(missingcategory, "")
					break
				}
				else{
				}
			}
		}
		else if(unmatcheditems.getStatus() == -1){
			for(int j=0; j<ProjectConstants.missingshopdatainfo.size(); j++){
				if(ProjectConstants.missingshopdatainfo.get(j).getShopname().equalsIgnoreCase(ProjectConstants.CURRENTVISITING_SHOPNAME)) {
					missingcategory.setMaincategory(ProjectConstants.CURRENTVISITING_MAINCATEGORY)
					missingcategory.setMissing_auditquestioncategories(unmatcheditems.getItems())
					missingcategory.setMissing_auditquestioncategories_errormessage(ProjectConstants.MESSAGEFOR_ITEMSARE_MISSING)
					ProjectConstants.missingshopdatainfo.get(j).setMissingCategoriesData(missingcategory, "")
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
			ProjectConstants.CURRENTVISITING_QUESTIONCATEGORY = questioncategory.getText()
			ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout["+i+"]").click()
			if(flag == 1){
				Mobile.callTestCase(findTestCase("Test Cases/ShopOpen/Survey/VisitQuestions"), null)
			}
			else{
				Mobile.callTestCase(findTestCase("Test Cases/ShopOpen/Survey/OverwriteQuestions"), null)
			}
		}
	}
	@Keyword
	def visitQuestions(){
		MissingCategoryData missingcategory = new MissingCategoryData()
		int index = 0
		ArrayList<String> displayedquestions = new ArrayList<String>()
		ArrayList<String> expectedquestions = new ArrayList<String>()
		ArrayList<QuestionsData> expectedquestionslist = LoadDataKeywords.loadSurveyQuestionsList()
		for(int i=0; i< expectedquestionslist.size(); i++){
			expectedquestions.add(expectedquestionslist.get(i).getQuestion())
		}
		int questionlist = ProjectConstants.DRIVER.findElementsByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ScrollView[1]/android.widget.LinearLayout[1]/*").size()
		for(int i=1; i<= questionlist; i++){
			boolean flag = false
			if(i == questionlist){
				Mobile.swipe(2, 500, 2, 400)
				MobileElement question = ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ScrollView[1]/android.widget.LinearLayout[1]/android.widget.Spinner["+i+"]/android.widget.LinearLayout[1]/android.widget.TextView[1]")
				String questiontext = question.getText()
				displayedquestions.add(questiontext)
				for(int j=0; j< expectedquestionslist.size(); j++){
					QuestionsData expectedquestion = expectedquestionslist.get(j)
					if(expectedquestion.getQuestion().equalsIgnoreCase(questiontext) && expectedquestion.getQuestionoption().equalsIgnoreCase("Y")){
						flag = true
						ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ScrollView[1]/android.widget.LinearLayout[1]/android.widget.Spinner["+i+"]").click()
						Mobile.verifyElementExist(findTestObject("ShopOpen/Survey/Validate_RemarksPopup", [('package') : ProjectConstants.PACKAGENAME]), 0)
						Mobile.tap(findTestObject("ShopOpen/Survey/RemarksPopup_Yes", [('package') : ProjectConstants.PACKAGENAME]), 0)
						if(expectedquestion.getQuestionoption_takepicture().equalsIgnoreCase("Y")){
							CommonKeywords.takePicture()
						}
						else{
						}
					}
				}
				if(flag == false){
					ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ScrollView[1]/android.widget.LinearLayout[1]/android.widget.Spinner["+i+"]").click()
					Mobile.verifyElementExist(findTestObject("ShopOpen/Survey/Validate_RemarksPopup", [('package') : ProjectConstants.PACKAGENAME]), 0)
					Mobile.tap(findTestObject("ShopOpen/Survey/RemarksPopup_Yes", [('package') : ProjectConstants.PACKAGENAME]), 0)
					CommonKeywords.takePicture()
				}
			}
			else{
				MobileElement question = ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ScrollView[1]/android.widget.LinearLayout[1]/android.widget.Spinner["+i+"]/android.widget.LinearLayout[1]/android.widget.TextView[1]")
				String questiontext = question.getText()
				displayedquestions.add(questiontext)
				for(int j=0; j< expectedquestionslist.size(); j++){
					QuestionsData expectedquestion = expectedquestionslist.get(j)
					if(expectedquestion.getQuestion().equalsIgnoreCase(questiontext) && expectedquestion.getQuestionoption().equalsIgnoreCase("Y")){
						flag = true
						ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ScrollView[1]/android.widget.LinearLayout[1]/android.widget.Spinner["+i+"]").click()
						Mobile.verifyElementExist(findTestObject("ShopOpen/Survey/Validate_RemarksPopup", [('package') : ProjectConstants.PACKAGENAME]), 0)
						Mobile.tap(findTestObject("ShopOpen/Survey/RemarksPopup_Yes", [('package') : ProjectConstants.PACKAGENAME]), 0)
						if(expectedquestion.getQuestionoption_takepicture().equalsIgnoreCase("Y")){
							CommonKeywords.takePicture()
						}
						else{
						}
						break
					}
				}
				if(flag == false){
					ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ScrollView[1]/android.widget.LinearLayout[1]/android.widget.Spinner["+i+"]").click()
					Mobile.verifyElementExist(findTestObject("ShopOpen/Survey/Validate_RemarksPopup", [('package') : ProjectConstants.PACKAGENAME]), 0)
					Mobile.tap(findTestObject("ShopOpen/Survey/RemarksPopup_Yes", [('package') : ProjectConstants.PACKAGENAME]), 0)
					CommonKeywords.takePicture()
				}
			}
			Mobile.verifyElementText(findTestObject('ShopOpen/Survey/Validate_QuestionsScreen', [('package') : ProjectConstants.PACKAGENAME]), 'Questions')
		}
		while(true){
			boolean flag = false
			index = ProjectConstants.DRIVER.findElementsByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ScrollView[1]/android.widget.LinearLayout[1]/*").size()
			MobileElement itembeforeswipe = ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ScrollView[1]/android.widget.LinearLayout[1]/android.widget.Spinner["+index+"]/android.widget.LinearLayout[1]/android.widget.TextView[1]")
			String itemtextbeforeswipe = itembeforeswipe.getText()
			Mobile.swipe(2, 548, 2, 400)
			index = ProjectConstants.DRIVER.findElementsByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ScrollView[1]/android.widget.LinearLayout[1]/*").size()
			MobileElement itemafterswipe = ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ScrollView[1]/android.widget.LinearLayout[1]/android.widget.Spinner["+index+"]/android.widget.LinearLayout[1]/android.widget.TextView[1]")
			String itemtextafterswipe = itemafterswipe.getText()
			if(itemtextbeforeswipe.equals(itemtextafterswipe)){
				break
			}
			else{
				MobileElement question = ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ScrollView[1]/android.widget.LinearLayout[1]/android.widget.Spinner["+index+"]/android.widget.LinearLayout[1]/android.widget.TextView[1]")
				String questiontext = question.getText()
				displayedquestions.add(questiontext)
				for(int j=0; j< expectedquestionslist.size(); j++){
					QuestionsData expectedquestion = expectedquestionslist.get(j)
					if(expectedquestion.getQuestion().equalsIgnoreCase(questiontext) && expectedquestion.getQuestionoption().equalsIgnoreCase("Y")){
						flag = true
						ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ScrollView[1]/android.widget.LinearLayout[1]/android.widget.Spinner["+index+"]").click()
						Mobile.verifyElementExist(findTestObject("ShopOpen/Survey/Validate_RemarksPopup", [('package') : ProjectConstants.PACKAGENAME]), 0)
						Mobile.tap(findTestObject("ShopOpen/Survey/RemarksPopup_Yes", [('package') : ProjectConstants.PACKAGENAME]), 0)
						if(expectedquestion.getQuestionoption_takepicture().equalsIgnoreCase("Y")){
							CommonKeywords.takePicture()
						}
						else{
						}
						break
					}
				}
				if(flag == false){
					ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ScrollView[1]/android.widget.LinearLayout[1]/android.widget.Spinner["+index+"]").click()
					Mobile.verifyElementExist(findTestObject("ShopOpen/Survey/Validate_RemarksPopup", [('package') : ProjectConstants.PACKAGENAME]), 0)
					Mobile.tap(findTestObject("ShopOpen/Survey/RemarksPopup_Yes", [('package') : ProjectConstants.PACKAGENAME]), 0)
					CommonKeywords.takePicture()
				}
			}
			Mobile.verifyElementText(findTestObject('ShopOpen/Survey/Validate_QuestionsScreen', [('package') : ProjectConstants.PACKAGENAME]), 'Questions')
		}
		ArrayList<String> _expectedquestions = new HashSet<String>(expectedquestions)
		UnmatchedItems unmatcheditems = CompareDataKeywords.compareLists(_expectedquestions, displayedquestions)
		if(unmatcheditems.getStatus() == 2){
			for(int j=0; j<ProjectConstants.missingshopdatainfo.size(); j++){
				if(ProjectConstants.missingshopdatainfo.get(j).getShopname().equalsIgnoreCase(ProjectConstants.CURRENTVISITING_SHOPNAME)) {
					missingcategory.setMaincategory(ProjectConstants.CURRENTVISITING_MAINCATEGORY)
					missingcategory.setQuestionCategory(ProjectConstants.CURRENTVISITING_QUESTIONCATEGORY)
					missingcategory.setMissing_auditquestions(unmatcheditems.getItems())
					missingcategory.setMissing_auditquestions_errormessage(ProjectConstants.MESSAGEFOR_ITEMSARE_NOTMATCH)
					ProjectConstants.missingshopdatainfo.get(j).setMissingCategoriesData(missingcategory, "")
					break
				}
				else{
				}
			}
		}
		else if(unmatcheditems.getStatus() == 1){
			for(int j=0; j<ProjectConstants.missingshopdatainfo.size(); j++){
				if(ProjectConstants.missingshopdatainfo.get(j).getShopname().equalsIgnoreCase(ProjectConstants.CURRENTVISITING_SHOPNAME)) {
					missingcategory.setMaincategory(ProjectConstants.CURRENTVISITING_MAINCATEGORY)
					missingcategory.setQuestionCategory(ProjectConstants.CURRENTVISITING_QUESTIONCATEGORY)
					missingcategory.setMissing_auditquestions(unmatcheditems.getItems())
					missingcategory.setMissing_auditquestions_errormessage(ProjectConstants.MESSAGEFOR_ITEMSARE_MORE)
					ProjectConstants.missingshopdatainfo.get(j).setMissingCategoriesData(missingcategory, "")
					break
				}
				else{
				}
			}
		}
		else if(unmatcheditems.getStatus() == -1){
			for(int j=0; j<ProjectConstants.missingshopdatainfo.size(); j++){
				if(ProjectConstants.missingshopdatainfo.get(j).getShopname().equalsIgnoreCase(ProjectConstants.CURRENTVISITING_SHOPNAME)) {
					missingcategory.setMaincategory(ProjectConstants.CURRENTVISITING_MAINCATEGORY)
					missingcategory.setQuestionCategory(ProjectConstants.CURRENTVISITING_QUESTIONCATEGORY)
					missingcategory.setMissing_auditquestions(unmatcheditems.getItems())
					missingcategory.setMissing_auditquestions_errormessage(ProjectConstants.MESSAGEFOR_ITEMSARE_MISSING)
					ProjectConstants.missingshopdatainfo.get(j).setMissingCategoriesData(missingcategory, "")
					break
				}
				else{
				}
			}
		}
		else{
		}
	}
	@Keyword
	def overwriteQuestions(){
		MissingCategoryData missingcategory = new MissingCategoryData()
		int index = 0
		ArrayList<String> displayedquestions = new ArrayList<String>()
		ArrayList<String> expectedquestions = new ArrayList<String>()
		ArrayList<QuestionsData> expectedquestionslist = LoadDataKeywords.loadSurveyQuestionsList()
		for(int i=0; i< expectedquestionslist.size(); i++){
			expectedquestions.add(expectedquestionslist.get(i).getQuestion())
		}
		int questionlist = ProjectConstants.DRIVER.findElementsByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ScrollView[1]/android.widget.LinearLayout[1]/*").size()
		for(int i=1; i<= questionlist; i++){
			boolean flag = false
			if(i == questionlist){
				Mobile.swipe(2, 500, 2, 400)
				MobileElement question = ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ScrollView[1]/android.widget.LinearLayout[1]/android.widget.Spinner["+i+"]/android.widget.LinearLayout[1]/android.widget.TextView[1]")
				String questiontext = question.getText()
				displayedquestions.add(questiontext)
				for(int j=0; j< expectedquestionslist.size(); j++){
					QuestionsData expectedquestion = expectedquestionslist.get(j)
					if(expectedquestion.getQuestion().equalsIgnoreCase(questiontext) && expectedquestion.getQuestionoption().equalsIgnoreCase("Y")){
						flag = true
						ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ScrollView[1]/android.widget.LinearLayout[1]/android.widget.Spinner["+i+"]").click()
						Mobile.verifyElementExist(findTestObject("ShopOpen/Survey/Validate_RemarksPopup", [('package') : ProjectConstants.PACKAGENAME]), 0)
						Mobile.tap(findTestObject("Object Repository/ShopOpen/Survey/RemarksPopup_No", [('package') : ProjectConstants.PACKAGENAME]), 0)
						if(expectedquestion.getQuestionoption_takepicture().equalsIgnoreCase("Y")){
							CommonKeywords.takePicture()
						}
						else{
						}
					}
				}
				if(flag == false){
					ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ScrollView[1]/android.widget.LinearLayout[1]/android.widget.Spinner["+i+"]").click()
					Mobile.verifyElementExist(findTestObject("ShopOpen/Survey/Validate_RemarksPopup", [('package') : ProjectConstants.PACKAGENAME]), 0)
					Mobile.tap(findTestObject("Object Repository/ShopOpen/Survey/RemarksPopup_No", [('package') : ProjectConstants.PACKAGENAME]), 0)
					CommonKeywords.takePicture()
				}
			}
			else{
				MobileElement question = ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ScrollView[1]/android.widget.LinearLayout[1]/android.widget.Spinner["+i+"]/android.widget.LinearLayout[1]/android.widget.TextView[1]")
				String questiontext = question.getText()
				displayedquestions.add(questiontext)
				for(int j=0; j< expectedquestionslist.size(); j++){
					QuestionsData expectedquestion = expectedquestionslist.get(j)
					if(expectedquestion.getQuestion().equalsIgnoreCase(questiontext) && expectedquestion.getQuestionoption().equalsIgnoreCase("Y")){
						flag = true
						ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ScrollView[1]/android.widget.LinearLayout[1]/android.widget.Spinner["+i+"]").click()
						Mobile.verifyElementExist(findTestObject("ShopOpen/Survey/Validate_RemarksPopup", [('package') : ProjectConstants.PACKAGENAME]), 0)
						Mobile.tap(findTestObject("Object Repository/ShopOpen/Survey/RemarksPopup_No", [('package') : ProjectConstants.PACKAGENAME]), 0)
						if(expectedquestion.getQuestionoption_takepicture().equalsIgnoreCase("Y")){
							CommonKeywords.takePicture()
						}
						else{
						}
						break
					}
				}
				if(flag == false){
					ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ScrollView[1]/android.widget.LinearLayout[1]/android.widget.Spinner["+i+"]").click()
					Mobile.verifyElementExist(findTestObject("ShopOpen/Survey/Validate_RemarksPopup", [('package') : ProjectConstants.PACKAGENAME]), 0)
					Mobile.tap(findTestObject("Object Repository/ShopOpen/Survey/RemarksPopup_No", [('package') : ProjectConstants.PACKAGENAME]), 0)
					CommonKeywords.takePicture()
				}
			}
			Mobile.verifyElementText(findTestObject('ShopOpen/Survey/Validate_QuestionsScreen', [('package') : ProjectConstants.PACKAGENAME]), 'Questions')
		}
		while(true){
			boolean flag = false
			index = ProjectConstants.DRIVER.findElementsByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ScrollView[1]/android.widget.LinearLayout[1]/*").size()
			MobileElement itembeforeswipe = ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ScrollView[1]/android.widget.LinearLayout[1]/android.widget.Spinner["+index+"]/android.widget.LinearLayout[1]/android.widget.TextView[1]")
			String itemtextbeforeswipe = itembeforeswipe.getText()
			Mobile.swipe(2, 548, 2, 400)
			index = ProjectConstants.DRIVER.findElementsByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ScrollView[1]/android.widget.LinearLayout[1]/*").size()
			MobileElement itemafterswipe = ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ScrollView[1]/android.widget.LinearLayout[1]/android.widget.Spinner["+index+"]/android.widget.LinearLayout[1]/android.widget.TextView[1]")
			String itemtextafterswipe = itemafterswipe.getText()
			if(itemtextbeforeswipe.equals(itemtextafterswipe)){
				break
			}
			else{
				MobileElement question = ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ScrollView[1]/android.widget.LinearLayout[1]/android.widget.Spinner["+index+"]/android.widget.LinearLayout[1]/android.widget.TextView[1]")
				String questiontext = question.getText()
				displayedquestions.add(questiontext)
				for(int j=0; j< expectedquestionslist.size(); j++){
					QuestionsData expectedquestion = expectedquestionslist.get(j)
					if(expectedquestion.getQuestion().equalsIgnoreCase(questiontext) && expectedquestion.getQuestionoption().equalsIgnoreCase("Y")){
						flag = true
						ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ScrollView[1]/android.widget.LinearLayout[1]/android.widget.Spinner["+index+"]").click()
						Mobile.verifyElementExist(findTestObject("ShopOpen/Survey/Validate_RemarksPopup", [('package') : ProjectConstants.PACKAGENAME]), 0)
						Mobile.tap(findTestObject("Object Repository/ShopOpen/Survey/RemarksPopup_No", [('package') : ProjectConstants.PACKAGENAME]), 0)
						if(expectedquestion.getQuestionoption_takepicture().equalsIgnoreCase("Y")){
							CommonKeywords.takePicture()
						}
						else{
						}
						break
					}
				}
				if(flag == false){
					ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ScrollView[1]/android.widget.LinearLayout[1]/android.widget.Spinner["+index+"]").click()
					Mobile.verifyElementExist(findTestObject("ShopOpen/Survey/Validate_RemarksPopup", [('package') : ProjectConstants.PACKAGENAME]), 0)
					Mobile.tap(findTestObject("Object Repository/ShopOpen/Survey/RemarksPopup_No", [('package') : ProjectConstants.PACKAGENAME]), 0)
					CommonKeywords.takePicture()
				}
			}
			Mobile.verifyElementText(findTestObject('ShopOpen/Survey/Validate_QuestionsScreen', [('package') : ProjectConstants.PACKAGENAME]), 'Questions')
		}
		ArrayList<String> _expectedquestions = new HashSet<String>(expectedquestions)
		UnmatchedItems unmatcheditems = CompareDataKeywords.compareLists(_expectedquestions, displayedquestions)
		if(unmatcheditems.getStatus() == 2){
			for(int j=0; j<ProjectConstants.missingshopdatainfo.size(); j++){
				if(ProjectConstants.missingshopdatainfo.get(j).getShopname().equalsIgnoreCase(ProjectConstants.CURRENTVISITING_SHOPNAME)) {
					missingcategory.setMaincategory(ProjectConstants.CURRENTVISITING_MAINCATEGORY)
					missingcategory.setQuestionCategory(ProjectConstants.CURRENTVISITING_QUESTIONCATEGORY)
					missingcategory.setMissing_auditquestions(unmatcheditems.getItems())
					missingcategory.setMissing_auditquestions_errormessage(ProjectConstants.MESSAGEFOR_ITEMSARE_NOTMATCH)
					ProjectConstants.missingshopdatainfo.get(j).setMissingCategoriesData(missingcategory, "")
					break
				}
				else{
				}
			}
		}
		else if(unmatcheditems.getStatus() == 1){
			for(int j=0; j<ProjectConstants.missingshopdatainfo.size(); j++){
				if(ProjectConstants.missingshopdatainfo.get(j).getShopname().equalsIgnoreCase(ProjectConstants.CURRENTVISITING_SHOPNAME)) {
					missingcategory.setMaincategory(ProjectConstants.CURRENTVISITING_MAINCATEGORY)
					missingcategory.setQuestionCategory(ProjectConstants.CURRENTVISITING_QUESTIONCATEGORY)
					missingcategory.setMissing_auditquestions(unmatcheditems.getItems())
					missingcategory.setMissing_auditquestions_errormessage(ProjectConstants.MESSAGEFOR_ITEMSARE_MORE)
					ProjectConstants.missingshopdatainfo.get(j).setMissingCategoriesData(missingcategory, "")
					break
				}
				else{
				}
			}
		}
		else if(unmatcheditems.getStatus() == -1){
			for(int j=0; j<ProjectConstants.missingshopdatainfo.size(); j++){
				if(ProjectConstants.missingshopdatainfo.get(j).getShopname().equalsIgnoreCase(ProjectConstants.CURRENTVISITING_SHOPNAME)) {
					missingcategory.setMaincategory(ProjectConstants.CURRENTVISITING_MAINCATEGORY)
					missingcategory.setQuestionCategory(ProjectConstants.CURRENTVISITING_QUESTIONCATEGORY)
					missingcategory.setMissing_auditquestions(unmatcheditems.getItems())
					missingcategory.setMissing_auditquestions_errormessage(ProjectConstants.MESSAGEFOR_ITEMSARE_MISSING)
					ProjectConstants.missingshopdatainfo.get(j).setMissingCategoriesData(missingcategory, "")
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
