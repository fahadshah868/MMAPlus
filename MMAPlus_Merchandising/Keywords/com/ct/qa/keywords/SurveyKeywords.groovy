package com.ct.qa.keywords

import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import com.ct.qa.constants.ProjectConstants
import com.ct.qa.struct.LoadProductsData
import com.ct.qa.struct.MissingCategoryData
import com.ct.qa.struct.ProductCategoryWithProducts
import com.ct.qa.struct.QuestionsData
import com.ct.qa.struct.UnmatchedItems
import com.ct.qa.struct.VisitedCategoryData
import com.ct.qa.struct.VisitedShopDataInfo
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
		ArrayList<QuestionsData> visitedquestions = new ArrayList<QuestionsData>()
		ArrayList<LoadProductsData> expectedquestionslist = LoadDataKeywords.loadSurveyQuestionsList()
		for(int i=0; i< expectedquestionslist.size(); i++){
			expectedquestions.add(expectedquestionslist.get(i).getProduct())
		}
		int questionlist = ProjectConstants.DRIVER.findElementsByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ScrollView[1]/android.widget.LinearLayout[1]/*").size()
		for(int i=1; i<= questionlist; i++){
			QuestionsData visitedquestion = new QuestionsData()
			ArrayList<LoadProductsData> expectedsimilarquestions = new ArrayList<LoadProductsData>()
			if(i == questionlist){
				Mobile.swipe(2, 500, 2, 400)
				MobileElement question = ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ScrollView[1]/android.widget.LinearLayout[1]/android.widget.Spinner["+i+"]/android.widget.LinearLayout[1]/android.widget.TextView[1]")
				String questiontext = question.getText()
				displayedquestions.add(questiontext)
				visitedquestion.setQuestion(questiontext)
				for(int j=0; j< expectedquestionslist.size(); j++){
					LoadProductsData expectedquestion = expectedquestionslist.get(j)
					if(expectedquestion.getProduct().equalsIgnoreCase(questiontext)){
						expectedsimilarquestions.add(expectedquestionslist.get(j))
					}
				}
				if(ProjectConstants.SCENARIO.equalsIgnoreCase("first visit")){
					if(expectedsimilarquestions.size() > 0){
						if(expectedsimilarquestions.size() == 1){
							if(expectedsimilarquestions.get(0).getOptions().equalsIgnoreCase("Y")){
								ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ScrollView[1]/android.widget.LinearLayout[1]/android.widget.Spinner["+i+"]").click()
								Mobile.verifyElementExist(findTestObject("ShopOpen/Survey/Validate_RemarksPopup", [('package') : ProjectConstants.PACKAGENAME]), 0)
								Mobile.tap(findTestObject("ShopOpen/Survey/RemarksPopup_Yes", [('package') : ProjectConstants.PACKAGENAME]), 0)
								visitedquestion.setQuestionoption("Y")
								if(expectedsimilarquestions.get(0).getStatus().equalsIgnoreCase("Y")){
									CommonKeywords.takePicture()
									visitedquestion.setQuestionoption_takepicture("Y")
								}
								else{
									visitedquestion.setQuestionoption_takepicture("N")
								}
							}
							else{
								ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ScrollView[1]/android.widget.LinearLayout[1]/android.widget.Spinner["+i+"]").click()
								Mobile.verifyElementExist(findTestObject("ShopOpen/Survey/Validate_RemarksPopup", [('package') : ProjectConstants.PACKAGENAME]), 0)
								Mobile.tap(findTestObject("ShopOpen/Survey/RemarksPopup_No", [('package') : ProjectConstants.PACKAGENAME]), 0)
								visitedquestion.setQuestionoption("N")
								if(expectedsimilarquestions.get(0).getStatus().equalsIgnoreCase("Y")){
									CommonKeywords.takePicture()
									visitedquestion.setQuestionoption_takepicture("Y")
								}
								else{
									visitedquestion.setQuestionoption_takepicture("N")
								}
							}
						}
						else{
							boolean flag = false
							for(int q=0; q< expectedsimilarquestions.size(); q++){
								LoadProductsData _question = expectedsimilarquestions.get(q)
								if(_question.getOptions().equalsIgnoreCase("Y")){
									ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ScrollView[1]/android.widget.LinearLayout[1]/android.widget.Spinner["+i+"]").click()
									Mobile.verifyElementExist(findTestObject("ShopOpen/Survey/Validate_RemarksPopup", [('package') : ProjectConstants.PACKAGENAME]), 0)
									Mobile.tap(findTestObject("ShopOpen/Survey/RemarksPopup_Yes", [('package') : ProjectConstants.PACKAGENAME]), 0)
									visitedquestion.setQuestionoption("Y")
									if(_question.getStatus().equalsIgnoreCase("Y")){
										flag = true
										CommonKeywords.takePicture()
										visitedquestion.setQuestionoption_takepicture("Y")
									}
									break
								}
							}
							if(flag == false){
								visitedquestion.setQuestionoption_takepicture("N")
							}
						}
					}
					else{
						visitedquestion.setQuestionoption("Y")
						visitedquestion.setQuestionoption_takepicture("Not Mention")
						ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ScrollView[1]/android.widget.LinearLayout[1]/android.widget.Spinner["+i+"]").click()
						Mobile.verifyElementExist(findTestObject("ShopOpen/Survey/Validate_RemarksPopup", [('package') : ProjectConstants.PACKAGENAME]), 0)
						Mobile.tap(findTestObject("ShopOpen/Survey/RemarksPopup_Yes", [('package') : ProjectConstants.PACKAGENAME]), 0)
						CommonKeywords.takePicture()
					}
					visitedquestions.add(visitedquestion)
				}
				else{
					if(expectedsimilarquestions.size() > 0){
						if(expectedsimilarquestions.size() == 1){
							if(expectedsimilarquestions.get(0).getOptions().equalsIgnoreCase("Y")){
								ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ScrollView[1]/android.widget.LinearLayout[1]/android.widget.Spinner["+i+"]").click()
								Mobile.verifyElementExist(findTestObject("ShopOpen/Survey/Validate_RemarksPopup", [('package') : ProjectConstants.PACKAGENAME]), 0)
								Mobile.tap(findTestObject("ShopOpen/Survey/RemarksPopup_Yes", [('package') : ProjectConstants.PACKAGENAME]), 0)
								visitedquestion.setOverwrite_questionoption("Y")
								if(expectedsimilarquestions.get(0).getStatus().equalsIgnoreCase("Y")){
									CommonKeywords.takePicture()
									visitedquestion.setOverwrite_questionoption_takepicture("Y")
								}
								else{
									visitedquestion.setOverwrite_questionoption_takepicture("N")
								}
							}
							else{
								ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ScrollView[1]/android.widget.LinearLayout[1]/android.widget.Spinner["+i+"]").click()
								Mobile.verifyElementExist(findTestObject("ShopOpen/Survey/Validate_RemarksPopup", [('package') : ProjectConstants.PACKAGENAME]), 0)
								Mobile.tap(findTestObject("ShopOpen/Survey/RemarksPopup_No", [('package') : ProjectConstants.PACKAGENAME]), 0)
								visitedquestion.setOverwrite_questionoption("N")
								if(expectedsimilarquestions.get(0).getStatus().equalsIgnoreCase("Y")){
									CommonKeywords.takePicture()
									visitedquestion.setOverwrite_questionoption_takepicture("Y")
								}
								else{
									visitedquestion.setOverwrite_questionoption_takepicture("N")
								}
							}
						}
						else{
							boolean flag = false
							for(int q=0; q< expectedsimilarquestions.size(); q++){
								LoadProductsData _question = expectedsimilarquestions.get(q)
								if(_question.getOptions().equalsIgnoreCase("Y")){
									ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ScrollView[1]/android.widget.LinearLayout[1]/android.widget.Spinner["+i+"]").click()
									Mobile.verifyElementExist(findTestObject("ShopOpen/Survey/Validate_RemarksPopup", [('package') : ProjectConstants.PACKAGENAME]), 0)
									Mobile.tap(findTestObject("ShopOpen/Survey/RemarksPopup_Yes", [('package') : ProjectConstants.PACKAGENAME]), 0)
									visitedquestion.setOverwrite_questionoption("Y")
									if(_question.getStatus().equalsIgnoreCase("Y")){
										flag = true
										CommonKeywords.takePicture()
										visitedquestion.setOverwrite_questionoption_takepicture("Y")
									}
									break
								}
							}
							if(flag == false){
								visitedquestion.setOverwrite_questionoption_takepicture("N")
							}
						}
					}
					else{
						visitedquestion.setOverwrite_questionoption("Y")
						visitedquestion.setOverwrite_questionoption_takepicture("Not Mention")
						ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ScrollView[1]/android.widget.LinearLayout[1]/android.widget.Spinner["+i+"]").click()
						Mobile.verifyElementExist(findTestObject("ShopOpen/Survey/Validate_RemarksPopup", [('package') : ProjectConstants.PACKAGENAME]), 0)
						Mobile.tap(findTestObject("ShopOpen/Survey/RemarksPopup_Yes", [('package') : ProjectConstants.PACKAGENAME]), 0)
						CommonKeywords.takePicture()
					}
					visitedquestions.add(visitedquestion)
				}
			}
			else{
				MobileElement question = ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ScrollView[1]/android.widget.LinearLayout[1]/android.widget.Spinner["+i+"]/android.widget.LinearLayout[1]/android.widget.TextView[1]")
				String questiontext = question.getText()
				displayedquestions.add(questiontext)
				visitedquestion.setQuestion(questiontext)
				for(int j=0; j< expectedquestionslist.size(); j++){
					LoadProductsData expectedquestion = expectedquestionslist.get(j)
					if(expectedquestion.getProduct().equalsIgnoreCase(questiontext)){
						expectedsimilarquestions.add(expectedquestionslist.get(j))
					}
				}
				if(ProjectConstants.SCENARIO.equalsIgnoreCase("first visit")){
					if(expectedsimilarquestions.size() > 0){
						if(expectedsimilarquestions.size() == 1){
							if(expectedsimilarquestions.get(0).getOptions().equalsIgnoreCase("Y")){
								ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ScrollView[1]/android.widget.LinearLayout[1]/android.widget.Spinner["+i+"]").click()
								Mobile.verifyElementExist(findTestObject("ShopOpen/Survey/Validate_RemarksPopup", [('package') : ProjectConstants.PACKAGENAME]), 0)
								Mobile.tap(findTestObject("ShopOpen/Survey/RemarksPopup_Yes", [('package') : ProjectConstants.PACKAGENAME]), 0)
								visitedquestion.setQuestionoption("Y")
								if(expectedsimilarquestions.get(0).getStatus().equalsIgnoreCase("Y")){
									CommonKeywords.takePicture()
									visitedquestion.setQuestionoption_takepicture("Y")
								}
								else{
									visitedquestion.setQuestionoption_takepicture("N")
								}
							}
							else{
								ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ScrollView[1]/android.widget.LinearLayout[1]/android.widget.Spinner["+i+"]").click()
								Mobile.verifyElementExist(findTestObject("ShopOpen/Survey/Validate_RemarksPopup", [('package') : ProjectConstants.PACKAGENAME]), 0)
								Mobile.tap(findTestObject("ShopOpen/Survey/RemarksPopup_No", [('package') : ProjectConstants.PACKAGENAME]), 0)
								visitedquestion.setQuestionoption("N")
								if(expectedsimilarquestions.get(0).getStatus().equalsIgnoreCase("Y")){
									CommonKeywords.takePicture()
									visitedquestion.setQuestionoption_takepicture("Y")
								}
								else{
									visitedquestion.setQuestionoption_takepicture("N")
								}
							}
						}
						else{
							boolean flag = false
							for(int q=0; q< expectedsimilarquestions.size(); q++){
								LoadProductsData _question = expectedsimilarquestions.get(q)
								if(_question.getOptions().equalsIgnoreCase("Y")){
									ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ScrollView[1]/android.widget.LinearLayout[1]/android.widget.Spinner["+i+"]").click()
									Mobile.verifyElementExist(findTestObject("ShopOpen/Survey/Validate_RemarksPopup", [('package') : ProjectConstants.PACKAGENAME]), 0)
									Mobile.tap(findTestObject("ShopOpen/Survey/RemarksPopup_Yes", [('package') : ProjectConstants.PACKAGENAME]), 0)
									visitedquestion.setQuestionoption("Y")
									if(_question.getStatus().equalsIgnoreCase("Y")){
										flag = true
										CommonKeywords.takePicture()
										visitedquestion.setQuestionoption_takepicture("Y")
									}
									break
								}
							}
							if(flag == false){
								visitedquestion.setQuestionoption_takepicture("N")
							}
						}
					}
					else{
						visitedquestion.setQuestionoption("Y")
						visitedquestion.setQuestionoption_takepicture("Not Mention")
						ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ScrollView[1]/android.widget.LinearLayout[1]/android.widget.Spinner["+i+"]").click()
						Mobile.verifyElementExist(findTestObject("ShopOpen/Survey/Validate_RemarksPopup", [('package') : ProjectConstants.PACKAGENAME]), 0)
						Mobile.tap(findTestObject("ShopOpen/Survey/RemarksPopup_Yes", [('package') : ProjectConstants.PACKAGENAME]), 0)
						CommonKeywords.takePicture()
					}
					visitedquestions.add(visitedquestion)
				}
				else{
					if(expectedsimilarquestions.size() > 0){
						if(expectedsimilarquestions.size() == 1){
							if(expectedsimilarquestions.get(0).getOptions().equalsIgnoreCase("Y")){
								ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ScrollView[1]/android.widget.LinearLayout[1]/android.widget.Spinner["+i+"]").click()
								Mobile.verifyElementExist(findTestObject("ShopOpen/Survey/Validate_RemarksPopup", [('package') : ProjectConstants.PACKAGENAME]), 0)
								Mobile.tap(findTestObject("ShopOpen/Survey/RemarksPopup_Yes", [('package') : ProjectConstants.PACKAGENAME]), 0)
								visitedquestion.setOverwrite_questionoption("Y")
								if(expectedsimilarquestions.get(0).getStatus().equalsIgnoreCase("Y")){
									CommonKeywords.takePicture()
									visitedquestion.setOverwrite_questionoption_takepicture("Y")
								}
								else{
									visitedquestion.setOverwrite_questionoption_takepicture("N")
								}
							}
							else{
								ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ScrollView[1]/android.widget.LinearLayout[1]/android.widget.Spinner["+i+"]").click()
								Mobile.verifyElementExist(findTestObject("ShopOpen/Survey/Validate_RemarksPopup", [('package') : ProjectConstants.PACKAGENAME]), 0)
								Mobile.tap(findTestObject("ShopOpen/Survey/RemarksPopup_No", [('package') : ProjectConstants.PACKAGENAME]), 0)
								visitedquestion.setOverwrite_questionoption("N")
								if(expectedsimilarquestions.get(0).getStatus().equalsIgnoreCase("Y")){
									CommonKeywords.takePicture()
									visitedquestion.setOverwrite_questionoption_takepicture("Y")
								}
								else{
									visitedquestion.setOverwrite_questionoption_takepicture("N")
								}
							}
						}
						else{
							boolean flag = false
							for(int q=0; q< expectedsimilarquestions.size(); q++){
								LoadProductsData _question = expectedsimilarquestions.get(q)
								if(_question.getOptions().equalsIgnoreCase("Y")){
									ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ScrollView[1]/android.widget.LinearLayout[1]/android.widget.Spinner["+i+"]").click()
									Mobile.verifyElementExist(findTestObject("ShopOpen/Survey/Validate_RemarksPopup", [('package') : ProjectConstants.PACKAGENAME]), 0)
									Mobile.tap(findTestObject("ShopOpen/Survey/RemarksPopup_Yes", [('package') : ProjectConstants.PACKAGENAME]), 0)
									visitedquestion.setOverwrite_questionoption("Y")
									if(_question.getStatus().equalsIgnoreCase("Y")){
										flag = true
										CommonKeywords.takePicture()
										visitedquestion.setOverwrite_questionoption_takepicture("Y")
									}
									break
								}
							}
							if(flag == false){
								visitedquestion.setOverwrite_questionoption_takepicture("N")
							}
						}
					}
					else{
						visitedquestion.setOverwrite_questionoption("Y")
						visitedquestion.setOverwrite_questionoption_takepicture("Not Mention")
						ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ScrollView[1]/android.widget.LinearLayout[1]/android.widget.Spinner["+i+"]").click()
						Mobile.verifyElementExist(findTestObject("ShopOpen/Survey/Validate_RemarksPopup", [('package') : ProjectConstants.PACKAGENAME]), 0)
						Mobile.tap(findTestObject("ShopOpen/Survey/RemarksPopup_Yes", [('package') : ProjectConstants.PACKAGENAME]), 0)
						CommonKeywords.takePicture()
					}
					visitedquestions.add(visitedquestion)
				}
			}
			Mobile.verifyElementText(findTestObject('ShopOpen/Survey/Validate_QuestionsScreen', [('package') : ProjectConstants.PACKAGENAME]), 'Questions')
		}
		while(true){
			ArrayList<LoadProductsData> expectedsimilarquestions = new ArrayList<LoadProductsData>()
			QuestionsData visitedquestion = new QuestionsData()
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
				visitedquestion.setQuestion(questiontext)
				for(int j=0; j< expectedquestionslist.size(); j++){
					LoadProductsData expectedquestion = expectedquestionslist.get(j)
					if(expectedquestion.getProduct().equalsIgnoreCase(questiontext)){
						expectedsimilarquestions.add(expectedquestionslist.get(j))
					}
				}
				if(ProjectConstants.SCENARIO.equalsIgnoreCase("first visit")){
					if(expectedsimilarquestions.size() > 0){
						if(expectedsimilarquestions.size() == 1){
							if(expectedsimilarquestions.get(0).getOptions().equalsIgnoreCase("Y")){
								ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ScrollView[1]/android.widget.LinearLayout[1]/android.widget.Spinner["+index+"]").click()
								Mobile.verifyElementExist(findTestObject("ShopOpen/Survey/Validate_RemarksPopup", [('package') : ProjectConstants.PACKAGENAME]), 0)
								Mobile.tap(findTestObject("ShopOpen/Survey/RemarksPopup_Yes", [('package') : ProjectConstants.PACKAGENAME]), 0)
								visitedquestion.setQuestionoption("Y")
								if(expectedsimilarquestions.get(0).getStatus().equalsIgnoreCase("Y")){
									CommonKeywords.takePicture()
									visitedquestion.setQuestionoption_takepicture("Y")
								}
								else{
									visitedquestion.setQuestionoption_takepicture("N")
								}
							}
							else{
								ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ScrollView[1]/android.widget.LinearLayout[1]/android.widget.Spinner["+index+"]").click()
								Mobile.verifyElementExist(findTestObject("ShopOpen/Survey/Validate_RemarksPopup", [('package') : ProjectConstants.PACKAGENAME]), 0)
								Mobile.tap(findTestObject("ShopOpen/Survey/RemarksPopup_No", [('package') : ProjectConstants.PACKAGENAME]), 0)
								visitedquestion.setQuestionoption("N")
								if(expectedsimilarquestions.get(0).getStatus().equalsIgnoreCase("Y")){
									CommonKeywords.takePicture()
									visitedquestion.setQuestionoption_takepicture("Y")
								}
								else{
									visitedquestion.setQuestionoption_takepicture("N")
								}
							}
						}
						else{
							boolean flag = false
							for(int q=0; q< expectedsimilarquestions.size(); q++){
								LoadProductsData _question = expectedsimilarquestions.get(q)
								if(_question.getOptions().equalsIgnoreCase("Y")){
									ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ScrollView[1]/android.widget.LinearLayout[1]/android.widget.Spinner["+index+"]").click()
									Mobile.verifyElementExist(findTestObject("ShopOpen/Survey/Validate_RemarksPopup", [('package') : ProjectConstants.PACKAGENAME]), 0)
									Mobile.tap(findTestObject("ShopOpen/Survey/RemarksPopup_Yes", [('package') : ProjectConstants.PACKAGENAME]), 0)
									visitedquestion.setQuestionoption("Y")
									if(_question.getStatus().equalsIgnoreCase("Y")){
										flag = true
										CommonKeywords.takePicture()
										visitedquestion.setQuestionoption_takepicture("Y")
									}
									break
								}
							}
							if(flag == false){
								visitedquestion.setQuestionoption_takepicture("N")
							}
						}
					}
					else{
						visitedquestion.setQuestionoption("Y")
						visitedquestion.setQuestionoption_takepicture("Not Mention")
						ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ScrollView[1]/android.widget.LinearLayout[1]/android.widget.Spinner["+index+"]").click()
						Mobile.verifyElementExist(findTestObject("ShopOpen/Survey/Validate_RemarksPopup", [('package') : ProjectConstants.PACKAGENAME]), 0)
						Mobile.tap(findTestObject("ShopOpen/Survey/RemarksPopup_Yes", [('package') : ProjectConstants.PACKAGENAME]), 0)
						CommonKeywords.takePicture()
					}
					visitedquestions.add(visitedquestion)
				}
				else{
					if(expectedsimilarquestions.size() > 0){
						if(expectedsimilarquestions.size() == 1){
							if(expectedsimilarquestions.get(0).getOptions().equalsIgnoreCase("Y")){
								ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ScrollView[1]/android.widget.LinearLayout[1]/android.widget.Spinner["+index+"]").click()
								Mobile.verifyElementExist(findTestObject("ShopOpen/Survey/Validate_RemarksPopup", [('package') : ProjectConstants.PACKAGENAME]), 0)
								Mobile.tap(findTestObject("ShopOpen/Survey/RemarksPopup_Yes", [('package') : ProjectConstants.PACKAGENAME]), 0)
								visitedquestion.setOverwrite_questionoption("Y")
								if(expectedsimilarquestions.get(0).getStatus().equalsIgnoreCase("Y")){
									CommonKeywords.takePicture()
									visitedquestion.setOverwrite_questionoption_takepicture("Y")
								}
								else{
									visitedquestion.setOverwrite_questionoption_takepicture("N")
								}
							}
							else{
								ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ScrollView[1]/android.widget.LinearLayout[1]/android.widget.Spinner["+index+"]").click()
								Mobile.verifyElementExist(findTestObject("ShopOpen/Survey/Validate_RemarksPopup", [('package') : ProjectConstants.PACKAGENAME]), 0)
								Mobile.tap(findTestObject("ShopOpen/Survey/RemarksPopup_No", [('package') : ProjectConstants.PACKAGENAME]), 0)
								visitedquestion.setOverwrite_questionoption("N")
								if(expectedsimilarquestions.get(0).getStatus().equalsIgnoreCase("Y")){
									CommonKeywords.takePicture()
									visitedquestion.setOverwrite_questionoption_takepicture("Y")
								}
								else{
									visitedquestion.setOverwrite_questionoption_takepicture("N")
								}
							}
						}
						else{
							boolean flag = false
							for(int q=0; q< expectedsimilarquestions.size(); q++){
								LoadProductsData _question = expectedsimilarquestions.get(q)
								if(_question.getOptions().equalsIgnoreCase("Y")){
									ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ScrollView[1]/android.widget.LinearLayout[1]/android.widget.Spinner["+index+"]").click()
									Mobile.verifyElementExist(findTestObject("ShopOpen/Survey/Validate_RemarksPopup", [('package') : ProjectConstants.PACKAGENAME]), 0)
									Mobile.tap(findTestObject("ShopOpen/Survey/RemarksPopup_Yes", [('package') : ProjectConstants.PACKAGENAME]), 0)
									visitedquestion.setOverwrite_questionoption("Y")
									if(_question.getStatus().equalsIgnoreCase("Y")){
										flag = true
										CommonKeywords.takePicture()
										visitedquestion.setOverwrite_questionoption_takepicture("Y")
									}
									break
								}
							}
							if(flag == false){
								visitedquestion.setOverwrite_questionoption_takepicture("N")
							}
						}
					}
					else{
						visitedquestion.setOverwrite_questionoption("Y")
						visitedquestion.setOverwrite_questionoption_takepicture("Not Mention")
						ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ScrollView[1]/android.widget.LinearLayout[1]/android.widget.Spinner["+index+"]").click()
						Mobile.verifyElementExist(findTestObject("ShopOpen/Survey/Validate_RemarksPopup", [('package') : ProjectConstants.PACKAGENAME]), 0)
						Mobile.tap(findTestObject("ShopOpen/Survey/RemarksPopup_Yes", [('package') : ProjectConstants.PACKAGENAME]), 0)
						CommonKeywords.takePicture()
					}
					visitedquestions.add(visitedquestion)
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
		VisitedCategoryData visitedcategory = new VisitedCategoryData()
		ProductCategoryWithProducts productcategorywithproducts = new ProductCategoryWithProducts()
		productcategorywithproducts.setProductcategory(ProjectConstants.CURRENTVISITING_QUESTIONCATEGORY)
		productcategorywithproducts.setSurveyquestions(visitedquestions)
		visitedcategory.setMaincategory(ProjectConstants.CURRENTVISITING_MAINCATEGORY)
		visitedcategory.setProductcategorywithproducts(productcategorywithproducts)
		for(int i=0; i< ProjectConstants.visitedshopdatainfo.size(); i++){
			if(ProjectConstants.visitedshopdatainfo.get(i).getShopname().equals(ProjectConstants.CURRENTVISITING_SHOPNAME)){
				VisitedShopDataInfo visitedshopdata = ProjectConstants.visitedshopdatainfo.get(i)
				ArrayList<VisitedCategoryData> visitedcategoriesdata = visitedshopdata.getVisitedcategoriesdata()
				if(visitedcategoriesdata != null){
					boolean maincategory_flag = false
					for(int k=0; k<visitedcategoriesdata.size(); k++){
						VisitedCategoryData visitedcategorydata = visitedcategoriesdata.get(k)
						if(visitedcategorydata.getMaincategory().equals(visitedcategory.getMaincategory())){
							maincategory_flag = true
							ArrayList<ProductCategoryWithProducts> existingsubcategorylist = visitedcategorydata.getProductcategorywithproducts()
							for(int l=0; l< existingsubcategorylist.size(); l++){
								ProductCategoryWithProducts existingsubcategory = existingsubcategorylist.get(l)
								boolean subcategory_flag = false
								if(existingsubcategory.getProductcategory().equals(ProjectConstants.CURRENTVISITING_QUESTIONCATEGORY)){
									subcategory_flag = true
									ArrayList<QuestionsData> questions = existingsubcategory.getSurveyquestions()
									for(int ex=0; ex< questions.size(); ex++){
										QuestionsData ex_question = questions.get(ex)
										for(int ds=0; ds< visitedquestions.size(); ds++){
											QuestionsData ds_question = visitedquestions.get(ds)
											if(ex_question.getQuestion().equals(ds_question.getQuestion())){
												if(ProjectConstants.SCENARIO.equalsIgnoreCase("first visit")){
													ex_question.setQuestionoption(ds_question.getQuestionoption())
													ex_question.setQuestionoption_takepicture(ds_question.getQuestionoption_takepicture())
												}
												else{
													ex_question.setOverwrite_questionoption(ds_question.getOverwrite_questionoption())
													ex_question.setOverwrite_questionoption_takepicture(ds_question.getOverwrite_questionoption_takepicture())
												}
												break
											}
										}
									}
								}
								if(subcategory_flag == false){
									visitedcategorydata.setProductcategorywithproducts(productcategorywithproducts)
									break
								}
							}
						}
					}
					if(maincategory_flag == false){
						ProjectConstants.visitedshopdatainfo.get(i).setVisitedcategoriesdata(visitedcategory)
						break
					}
				}
				else{
					ProjectConstants.visitedshopdatainfo.get(i).setVisitedcategoriesdata(visitedcategory)
					break
				}
			}
		}
	}
	@Keyword
	def overwriteQuestions(){
		MissingCategoryData missingcategory = new MissingCategoryData()
		int index = 0
		ArrayList<String> displayedquestions = new ArrayList<String>()
		ArrayList<String> expectedquestions = new ArrayList<String>()
		ArrayList<QuestionsData> visitedquestions = new ArrayList<QuestionsData>()
		ArrayList<LoadProductsData> expectedquestionslist = LoadDataKeywords.loadSurveyQuestionsList()
		for(int i=0; i< expectedquestionslist.size(); i++){
			expectedquestions.add(expectedquestionslist.get(i).getProduct())
		}
		int questionlist = ProjectConstants.DRIVER.findElementsByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ScrollView[1]/android.widget.LinearLayout[1]/*").size()
		for(int i=1; i<= questionlist; i++){
			QuestionsData visitedquestion = new QuestionsData()
			ArrayList<LoadProductsData> expectedsimilarquestions = new ArrayList<LoadProductsData>()
			if(i == questionlist){
				Mobile.swipe(2, 500, 2, 400)
				MobileElement question = ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ScrollView[1]/android.widget.LinearLayout[1]/android.widget.Spinner["+i+"]/android.widget.LinearLayout[1]/android.widget.TextView[1]")
				String questiontext = question.getText()
				displayedquestions.add(questiontext)
				visitedquestion.setQuestion(questiontext)
				for(int j=0; j< expectedquestionslist.size(); j++){
					LoadProductsData expectedquestion = expectedquestionslist.get(j)
					if(expectedquestion.getProduct().equalsIgnoreCase(questiontext)){
						expectedsimilarquestions.add(expectedquestionslist.get(j))
					}
				}
				if(ProjectConstants.SCENARIO.equalsIgnoreCase("first visit")){
					if(expectedsimilarquestions.size() > 0){
						if(expectedsimilarquestions.size() == 1){
							if(expectedsimilarquestions.get(0).getOptions().equalsIgnoreCase("Y")){
								ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ScrollView[1]/android.widget.LinearLayout[1]/android.widget.Spinner["+i+"]").click()
								Mobile.verifyElementExist(findTestObject("ShopOpen/Survey/Validate_RemarksPopup", [('package') : ProjectConstants.PACKAGENAME]), 0)
								Mobile.tap(findTestObject("ShopOpen/Survey/RemarksPopup_Yes", [('package') : ProjectConstants.PACKAGENAME]), 0)
								visitedquestion.setQuestionoption("Y")
								if(expectedsimilarquestions.get(0).getStatus().equalsIgnoreCase("Y")){
									CommonKeywords.takePicture()
									visitedquestion.setQuestionoption_takepicture("Y")
								}
								else{
									visitedquestion.setQuestionoption_takepicture("N")
								}
							}
							else{
								ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ScrollView[1]/android.widget.LinearLayout[1]/android.widget.Spinner["+i+"]").click()
								Mobile.verifyElementExist(findTestObject("ShopOpen/Survey/Validate_RemarksPopup", [('package') : ProjectConstants.PACKAGENAME]), 0)
								Mobile.tap(findTestObject("ShopOpen/Survey/RemarksPopup_No", [('package') : ProjectConstants.PACKAGENAME]), 0)
								visitedquestion.setQuestionoption("N")
								if(expectedsimilarquestions.get(0).getStatus().equalsIgnoreCase("Y")){
									CommonKeywords.takePicture()
									visitedquestion.setQuestionoption_takepicture("Y")
								}
								else{
									visitedquestion.setQuestionoption_takepicture("N")
								}
							}
						}
						else{
							boolean flag = false
							for(int q=0; q< expectedsimilarquestions.size(); q++){
								LoadProductsData _question = expectedsimilarquestions.get(q)
								if(_question.getOptions().equalsIgnoreCase("N")){
									ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ScrollView[1]/android.widget.LinearLayout[1]/android.widget.Spinner["+i+"]").click()
									Mobile.verifyElementExist(findTestObject("ShopOpen/Survey/Validate_RemarksPopup", [('package') : ProjectConstants.PACKAGENAME]), 0)
									Mobile.tap(findTestObject("ShopOpen/Survey/RemarksPopup_No", [('package') : ProjectConstants.PACKAGENAME]), 0)
									visitedquestion.setQuestionoption("N")
									if(_question.getStatus().equalsIgnoreCase("Y")){
										flag = true
										CommonKeywords.takePicture()
										visitedquestion.setQuestionoption_takepicture("Y")
									}
									break
								}
							}
							if(flag == false){
								visitedquestion.setQuestionoption_takepicture("N")
							}
						}
					}
					else{
						visitedquestion.setQuestionoption("Y")
						visitedquestion.setQuestionoption_takepicture("Not Mention")
						ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ScrollView[1]/android.widget.LinearLayout[1]/android.widget.Spinner["+i+"]").click()
						Mobile.verifyElementExist(findTestObject("ShopOpen/Survey/Validate_RemarksPopup", [('package') : ProjectConstants.PACKAGENAME]), 0)
						Mobile.tap(findTestObject("ShopOpen/Survey/RemarksPopup_Yes", [('package') : ProjectConstants.PACKAGENAME]), 0)
						CommonKeywords.takePicture()
					}
					visitedquestions.add(visitedquestion)
				}
				else{
					if(expectedsimilarquestions.size() > 0){
						if(expectedsimilarquestions.size() == 1){
							if(expectedsimilarquestions.get(0).getOptions().equalsIgnoreCase("Y")){
								ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ScrollView[1]/android.widget.LinearLayout[1]/android.widget.Spinner["+i+"]").click()
								Mobile.verifyElementExist(findTestObject("ShopOpen/Survey/Validate_RemarksPopup", [('package') : ProjectConstants.PACKAGENAME]), 0)
								Mobile.tap(findTestObject("ShopOpen/Survey/RemarksPopup_Yes", [('package') : ProjectConstants.PACKAGENAME]), 0)
								visitedquestion.setOverwrite_questionoption("Y")
								if(expectedsimilarquestions.get(0).getStatus().equalsIgnoreCase("Y")){
									CommonKeywords.takePicture()
									visitedquestion.setOverwrite_questionoption_takepicture("Y")
								}
								else{
									visitedquestion.setOverwrite_questionoption_takepicture("N")
								}
							}
							else{
								ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ScrollView[1]/android.widget.LinearLayout[1]/android.widget.Spinner["+i+"]").click()
								Mobile.verifyElementExist(findTestObject("ShopOpen/Survey/Validate_RemarksPopup", [('package') : ProjectConstants.PACKAGENAME]), 0)
								Mobile.tap(findTestObject("ShopOpen/Survey/RemarksPopup_No", [('package') : ProjectConstants.PACKAGENAME]), 0)
								visitedquestion.setOverwrite_questionoption("N")
								if(expectedsimilarquestions.get(0).getStatus().equalsIgnoreCase("Y")){
									CommonKeywords.takePicture()
									visitedquestion.setOverwrite_questionoption_takepicture("Y")
								}
								else{
									visitedquestion.setOverwrite_questionoption_takepicture("N")
								}
							}
						}
						else{
							boolean flag = false
							for(int q=0; q< expectedsimilarquestions.size(); q++){
								LoadProductsData _question = expectedsimilarquestions.get(q)
								if(_question.getOptions().equalsIgnoreCase("N")){
									ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ScrollView[1]/android.widget.LinearLayout[1]/android.widget.Spinner["+i+"]").click()
									Mobile.verifyElementExist(findTestObject("ShopOpen/Survey/Validate_RemarksPopup", [('package') : ProjectConstants.PACKAGENAME]), 0)
									Mobile.tap(findTestObject("ShopOpen/Survey/RemarksPopup_No", [('package') : ProjectConstants.PACKAGENAME]), 0)
									visitedquestion.setOverwrite_questionoption("N")
									if(_question.getStatus().equalsIgnoreCase("Y")){
										flag = true
										CommonKeywords.takePicture()
										visitedquestion.setOverwrite_questionoption_takepicture("Y")
									}
									break
								}
							}
							if(flag == false){
								visitedquestion.setOverwrite_questionoption_takepicture("N")
							}
						}
					}
					else{
						visitedquestion.setOverwrite_questionoption("Y")
						visitedquestion.setOverwrite_questionoption_takepicture("Not Mention")
						ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ScrollView[1]/android.widget.LinearLayout[1]/android.widget.Spinner["+i+"]").click()
						Mobile.verifyElementExist(findTestObject("ShopOpen/Survey/Validate_RemarksPopup", [('package') : ProjectConstants.PACKAGENAME]), 0)
						Mobile.tap(findTestObject("ShopOpen/Survey/RemarksPopup_Yes", [('package') : ProjectConstants.PACKAGENAME]), 0)
						CommonKeywords.takePicture()
					}
					visitedquestions.add(visitedquestion)
				}
			}
			else{
				MobileElement question = ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ScrollView[1]/android.widget.LinearLayout[1]/android.widget.Spinner["+i+"]/android.widget.LinearLayout[1]/android.widget.TextView[1]")
				String questiontext = question.getText()
				displayedquestions.add(questiontext)
				visitedquestion.setQuestion(questiontext)
				for(int j=0; j< expectedquestionslist.size(); j++){
					LoadProductsData expectedquestion = expectedquestionslist.get(j)
					if(expectedquestion.getProduct().equalsIgnoreCase(questiontext)){
						expectedsimilarquestions.add(expectedquestionslist.get(j))
					}
				}
				if(ProjectConstants.SCENARIO.equalsIgnoreCase("first visit")){
					if(expectedsimilarquestions.size() > 0){
						if(expectedsimilarquestions.size() == 1){
							if(expectedsimilarquestions.get(0).getOptions().equalsIgnoreCase("Y")){
								ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ScrollView[1]/android.widget.LinearLayout[1]/android.widget.Spinner["+i+"]").click()
								Mobile.verifyElementExist(findTestObject("ShopOpen/Survey/Validate_RemarksPopup", [('package') : ProjectConstants.PACKAGENAME]), 0)
								Mobile.tap(findTestObject("ShopOpen/Survey/RemarksPopup_Yes", [('package') : ProjectConstants.PACKAGENAME]), 0)
								visitedquestion.setQuestionoption("Y")
								if(expectedsimilarquestions.get(0).getStatus().equalsIgnoreCase("Y")){
									CommonKeywords.takePicture()
									visitedquestion.setQuestionoption_takepicture("Y")
								}
								else{
									visitedquestion.setQuestionoption_takepicture("N")
								}
							}
							else{
								ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ScrollView[1]/android.widget.LinearLayout[1]/android.widget.Spinner["+i+"]").click()
								Mobile.verifyElementExist(findTestObject("ShopOpen/Survey/Validate_RemarksPopup", [('package') : ProjectConstants.PACKAGENAME]), 0)
								Mobile.tap(findTestObject("ShopOpen/Survey/RemarksPopup_No", [('package') : ProjectConstants.PACKAGENAME]), 0)
								visitedquestion.setQuestionoption("N")
								if(expectedsimilarquestions.get(0).getStatus().equalsIgnoreCase("Y")){
									CommonKeywords.takePicture()
									visitedquestion.setQuestionoption_takepicture("Y")
								}
								else{
									visitedquestion.setQuestionoption_takepicture("N")
								}
							}
						}
						else{
							boolean flag = false
							for(int q=0; q< expectedsimilarquestions.size(); q++){
								LoadProductsData _question = expectedsimilarquestions.get(q)
								if(_question.getOptions().equalsIgnoreCase("N")){
									ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ScrollView[1]/android.widget.LinearLayout[1]/android.widget.Spinner["+i+"]").click()
									Mobile.verifyElementExist(findTestObject("ShopOpen/Survey/Validate_RemarksPopup", [('package') : ProjectConstants.PACKAGENAME]), 0)
									Mobile.tap(findTestObject("ShopOpen/Survey/RemarksPopup_No", [('package') : ProjectConstants.PACKAGENAME]), 0)
									visitedquestion.setQuestionoption("N")
									if(_question.getStatus().equalsIgnoreCase("Y")){
										flag = true
										CommonKeywords.takePicture()
										visitedquestion.setQuestionoption_takepicture("Y")
									}
									break
								}
							}
							if(flag == false){
								visitedquestion.setQuestionoption_takepicture("N")
							}
						}
					}
					else{
						visitedquestion.setQuestionoption("Y")
						visitedquestion.setQuestionoption_takepicture("Not Mention")
						ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ScrollView[1]/android.widget.LinearLayout[1]/android.widget.Spinner["+i+"]").click()
						Mobile.verifyElementExist(findTestObject("ShopOpen/Survey/Validate_RemarksPopup", [('package') : ProjectConstants.PACKAGENAME]), 0)
						Mobile.tap(findTestObject("ShopOpen/Survey/RemarksPopup_Yes", [('package') : ProjectConstants.PACKAGENAME]), 0)
						CommonKeywords.takePicture()
					}
					visitedquestions.add(visitedquestion)
				}
				else{
					if(expectedsimilarquestions.size() > 0){
						if(expectedsimilarquestions.size() == 1){
							if(expectedsimilarquestions.get(0).getOptions().equalsIgnoreCase("Y")){
								ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ScrollView[1]/android.widget.LinearLayout[1]/android.widget.Spinner["+i+"]").click()
								Mobile.verifyElementExist(findTestObject("ShopOpen/Survey/Validate_RemarksPopup", [('package') : ProjectConstants.PACKAGENAME]), 0)
								Mobile.tap(findTestObject("ShopOpen/Survey/RemarksPopup_Yes", [('package') : ProjectConstants.PACKAGENAME]), 0)
								visitedquestion.setOverwrite_questionoption("Y")
								if(expectedsimilarquestions.get(0).getStatus().equalsIgnoreCase("Y")){
									CommonKeywords.takePicture()
									visitedquestion.setOverwrite_questionoption_takepicture("Y")
								}
								else{
									visitedquestion.setOverwrite_questionoption_takepicture("N")
								}
							}
							else{
								ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ScrollView[1]/android.widget.LinearLayout[1]/android.widget.Spinner["+i+"]").click()
								Mobile.verifyElementExist(findTestObject("ShopOpen/Survey/Validate_RemarksPopup", [('package') : ProjectConstants.PACKAGENAME]), 0)
								Mobile.tap(findTestObject("ShopOpen/Survey/RemarksPopup_No", [('package') : ProjectConstants.PACKAGENAME]), 0)
								visitedquestion.setOverwrite_questionoption("N")
								if(expectedsimilarquestions.get(0).getStatus().equalsIgnoreCase("Y")){
									CommonKeywords.takePicture()
									visitedquestion.setOverwrite_questionoption_takepicture("Y")
								}
								else{
									visitedquestion.setOverwrite_questionoption_takepicture("N")
								}
							}
						}
						else{
							boolean flag = false
							for(int q=0; q< expectedsimilarquestions.size(); q++){
								LoadProductsData _question = expectedsimilarquestions.get(q)
								if(_question.getOptions().equalsIgnoreCase("N")){
									ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ScrollView[1]/android.widget.LinearLayout[1]/android.widget.Spinner["+i+"]").click()
									Mobile.verifyElementExist(findTestObject("ShopOpen/Survey/Validate_RemarksPopup", [('package') : ProjectConstants.PACKAGENAME]), 0)
									Mobile.tap(findTestObject("ShopOpen/Survey/RemarksPopup_No", [('package') : ProjectConstants.PACKAGENAME]), 0)
									visitedquestion.setOverwrite_questionoption("N")
									if(_question.getStatus().equalsIgnoreCase("Y")){
										flag = true
										CommonKeywords.takePicture()
										visitedquestion.setOverwrite_questionoption_takepicture("Y")
									}
									break
								}
							}
							if(flag == false){
								visitedquestion.setOverwrite_questionoption_takepicture("N")
							}
						}
					}
					else{
						visitedquestion.setOverwrite_questionoption("Y")
						visitedquestion.setOverwrite_questionoption_takepicture("Not Mention")
						ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ScrollView[1]/android.widget.LinearLayout[1]/android.widget.Spinner["+i+"]").click()
						Mobile.verifyElementExist(findTestObject("ShopOpen/Survey/Validate_RemarksPopup", [('package') : ProjectConstants.PACKAGENAME]), 0)
						Mobile.tap(findTestObject("ShopOpen/Survey/RemarksPopup_Yes", [('package') : ProjectConstants.PACKAGENAME]), 0)
						CommonKeywords.takePicture()
					}
					visitedquestions.add(visitedquestion)
				}
			}
			Mobile.verifyElementText(findTestObject('ShopOpen/Survey/Validate_QuestionsScreen', [('package') : ProjectConstants.PACKAGENAME]), 'Questions')
		}
		while(true){
			ArrayList<LoadProductsData> expectedsimilarquestions = new ArrayList<LoadProductsData>()
			QuestionsData visitedquestion = new QuestionsData()
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
				visitedquestion.setQuestion(questiontext)
				for(int j=0; j< expectedquestionslist.size(); j++){
					LoadProductsData expectedquestion = expectedquestionslist.get(j)
					if(expectedquestion.getProduct().equalsIgnoreCase(questiontext)){
						expectedsimilarquestions.add(expectedquestionslist.get(j))
					}
				}
				if(ProjectConstants.SCENARIO.equalsIgnoreCase("first visit")){
					if(expectedsimilarquestions.size() > 0){
						if(expectedsimilarquestions.size() == 1){
							if(expectedsimilarquestions.get(0).getOptions().equalsIgnoreCase("Y")){
								ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ScrollView[1]/android.widget.LinearLayout[1]/android.widget.Spinner["+index+"]").click()
								Mobile.verifyElementExist(findTestObject("ShopOpen/Survey/Validate_RemarksPopup", [('package') : ProjectConstants.PACKAGENAME]), 0)
								Mobile.tap(findTestObject("ShopOpen/Survey/RemarksPopup_Yes", [('package') : ProjectConstants.PACKAGENAME]), 0)
								visitedquestion.setQuestionoption("Y")
								if(expectedsimilarquestions.get(0).getStatus().equalsIgnoreCase("Y")){
									CommonKeywords.takePicture()
									visitedquestion.setQuestionoption_takepicture("Y")
								}
								else{
									visitedquestion.setQuestionoption_takepicture("N")
								}
							}
							else{
								ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ScrollView[1]/android.widget.LinearLayout[1]/android.widget.Spinner["+index+"]").click()
								Mobile.verifyElementExist(findTestObject("ShopOpen/Survey/Validate_RemarksPopup", [('package') : ProjectConstants.PACKAGENAME]), 0)
								Mobile.tap(findTestObject("ShopOpen/Survey/RemarksPopup_No", [('package') : ProjectConstants.PACKAGENAME]), 0)
								visitedquestion.setQuestionoption("N")
								if(expectedsimilarquestions.get(0).getStatus().equalsIgnoreCase("Y")){
									CommonKeywords.takePicture()
									visitedquestion.setQuestionoption_takepicture("Y")
								}
								else{
									visitedquestion.setQuestionoption_takepicture("N")
								}
							}
						}
						else{
							boolean flag = false
							for(int q=0; q< expectedsimilarquestions.size(); q++){
								LoadProductsData _question = expectedsimilarquestions.get(q)
								if(_question.getOptions().equalsIgnoreCase("N")){
									ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ScrollView[1]/android.widget.LinearLayout[1]/android.widget.Spinner["+index+"]").click()
									Mobile.verifyElementExist(findTestObject("ShopOpen/Survey/Validate_RemarksPopup", [('package') : ProjectConstants.PACKAGENAME]), 0)
									Mobile.tap(findTestObject("ShopOpen/Survey/RemarksPopup_No", [('package') : ProjectConstants.PACKAGENAME]), 0)
									visitedquestion.setQuestionoption("N")
									if(_question.getStatus().equalsIgnoreCase("Y")){
										flag = true
										CommonKeywords.takePicture()
										visitedquestion.setQuestionoption_takepicture("Y")
									}
									break
								}
							}
							if(flag == false){
								visitedquestion.setQuestionoption_takepicture("N")
							}
						}
					}
					else{
						visitedquestion.setQuestionoption("Y")
						visitedquestion.setQuestionoption_takepicture("Not Mention")
						ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ScrollView[1]/android.widget.LinearLayout[1]/android.widget.Spinner["+index+"]").click()
						Mobile.verifyElementExist(findTestObject("ShopOpen/Survey/Validate_RemarksPopup", [('package') : ProjectConstants.PACKAGENAME]), 0)
						Mobile.tap(findTestObject("ShopOpen/Survey/RemarksPopup_Yes", [('package') : ProjectConstants.PACKAGENAME]), 0)
						CommonKeywords.takePicture()
					}
					visitedquestions.add(visitedquestion)
				}
				else{
					if(expectedsimilarquestions.size() > 0){
						if(expectedsimilarquestions.size() == 1){
							if(expectedsimilarquestions.get(0).getOptions().equalsIgnoreCase("Y")){
								ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ScrollView[1]/android.widget.LinearLayout[1]/android.widget.Spinner["+index+"]").click()
								Mobile.verifyElementExist(findTestObject("ShopOpen/Survey/Validate_RemarksPopup", [('package') : ProjectConstants.PACKAGENAME]), 0)
								Mobile.tap(findTestObject("ShopOpen/Survey/RemarksPopup_Yes", [('package') : ProjectConstants.PACKAGENAME]), 0)
								visitedquestion.setOverwrite_questionoption("Y")
								if(expectedsimilarquestions.get(0).getStatus().equalsIgnoreCase("Y")){
									CommonKeywords.takePicture()
									visitedquestion.setOverwrite_questionoption_takepicture("Y")
								}
								else{
									visitedquestion.setOverwrite_questionoption_takepicture("N")
								}
							}
							else{
								ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ScrollView[1]/android.widget.LinearLayout[1]/android.widget.Spinner["+index+"]").click()
								Mobile.verifyElementExist(findTestObject("ShopOpen/Survey/Validate_RemarksPopup", [('package') : ProjectConstants.PACKAGENAME]), 0)
								Mobile.tap(findTestObject("ShopOpen/Survey/RemarksPopup_No", [('package') : ProjectConstants.PACKAGENAME]), 0)
								visitedquestion.setOverwrite_questionoption("N")
								if(expectedsimilarquestions.get(0).getStatus().equalsIgnoreCase("Y")){
									CommonKeywords.takePicture()
									visitedquestion.setOverwrite_questionoption_takepicture("Y")
								}
								else{
									visitedquestion.setOverwrite_questionoption_takepicture("N")
								}
							}
						}
						else{
							boolean flag = false
							for(int q=0; q< expectedsimilarquestions.size(); q++){
								LoadProductsData _question = expectedsimilarquestions.get(q)
								if(_question.getOptions().equalsIgnoreCase("N")){
									ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ScrollView[1]/android.widget.LinearLayout[1]/android.widget.Spinner["+index+"]").click()
									Mobile.verifyElementExist(findTestObject("ShopOpen/Survey/Validate_RemarksPopup", [('package') : ProjectConstants.PACKAGENAME]), 0)
									Mobile.tap(findTestObject("ShopOpen/Survey/RemarksPopup_No", [('package') : ProjectConstants.PACKAGENAME]), 0)
									visitedquestion.setOverwrite_questionoption("N")
									if(_question.getStatus().equalsIgnoreCase("Y")){
										flag = true
										CommonKeywords.takePicture()
										visitedquestion.setOverwrite_questionoption_takepicture("Y")
									}
									break
								}
							}
							if(flag == false){
								visitedquestion.setOverwrite_questionoption_takepicture("N")
							}
						}
					}
					else{
						visitedquestion.setOverwrite_questionoption("Y")
						visitedquestion.setOverwrite_questionoption_takepicture("Not Mention")
						ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ScrollView[1]/android.widget.LinearLayout[1]/android.widget.Spinner["+index+"]").click()
						Mobile.verifyElementExist(findTestObject("ShopOpen/Survey/Validate_RemarksPopup", [('package') : ProjectConstants.PACKAGENAME]), 0)
						Mobile.tap(findTestObject("ShopOpen/Survey/RemarksPopup_Yes", [('package') : ProjectConstants.PACKAGENAME]), 0)
						CommonKeywords.takePicture()
					}
					visitedquestions.add(visitedquestion)
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
		VisitedCategoryData visitedcategory = new VisitedCategoryData()
		ProductCategoryWithProducts productcategorywithproducts = new ProductCategoryWithProducts()
		productcategorywithproducts.setProductcategory(ProjectConstants.CURRENTVISITING_QUESTIONCATEGORY)
		productcategorywithproducts.setSurveyquestions(visitedquestions)
		visitedcategory.setMaincategory(ProjectConstants.CURRENTVISITING_MAINCATEGORY)
		visitedcategory.setProductcategorywithproducts(productcategorywithproducts)
		for(int i=0; i< ProjectConstants.visitedshopdatainfo.size(); i++){
			if(ProjectConstants.visitedshopdatainfo.get(i).getShopname().equals(ProjectConstants.CURRENTVISITING_SHOPNAME)){
				VisitedShopDataInfo visitedshopdata = ProjectConstants.visitedshopdatainfo.get(i)
				ArrayList<VisitedCategoryData> visitedcategoriesdata = visitedshopdata.getVisitedcategoriesdata()
				if(visitedcategoriesdata != null){
					boolean maincategory_flag = false
					for(int k=0; k<visitedcategoriesdata.size(); k++){
						VisitedCategoryData visitedcategorydata = visitedcategoriesdata.get(k)
						if(visitedcategorydata.getMaincategory().equals(visitedcategory.getMaincategory())){
							maincategory_flag = true
							ArrayList<ProductCategoryWithProducts> existingsubcategorylist = visitedcategorydata.getProductcategorywithproducts()
							for(int l=0; l< existingsubcategorylist.size(); l++){
								ProductCategoryWithProducts existingsubcategory = existingsubcategorylist.get(l)
								boolean subcategory_flag = false
								if(existingsubcategory.getProductcategory().equals(ProjectConstants.CURRENTVISITING_QUESTIONCATEGORY)){
									subcategory_flag = true
									ArrayList<QuestionsData> questions = existingsubcategory.getSurveyquestions()
									for(int ex=0; ex< questions.size(); ex++){
										QuestionsData ex_question = questions.get(ex)
										for(int ds=0; ds< visitedquestions.size(); ds++){
											QuestionsData ds_question = visitedquestions.get(ds)
											if(ex_question.getQuestion().equals(ds_question.getQuestion())){
												if(ProjectConstants.SCENARIO.equalsIgnoreCase("first visit")){
													ex_question.setQuestionoption(ds_question.getQuestionoption())
													ex_question.setQuestionoption_takepicture(ds_question.getQuestionoption_takepicture())
												}
												else{
													ex_question.setOverwrite_questionoption(ds_question.getOverwrite_questionoption())
													ex_question.setOverwrite_questionoption_takepicture(ds_question.getOverwrite_questionoption_takepicture())
												}
												break
											}
										}
									}
								}
								if(subcategory_flag == false){
									visitedcategorydata.setProductcategorywithproducts(productcategorywithproducts)
									break
								}
							}
						}
					}
					if(maincategory_flag == false){
						ProjectConstants.visitedshopdatainfo.get(i).setVisitedcategoriesdata(visitedcategory)
						break
					}
				}
				else{
					ProjectConstants.visitedshopdatainfo.get(i).setVisitedcategoriesdata(visitedcategory)
					break
				}
			}
		}
	}
}
