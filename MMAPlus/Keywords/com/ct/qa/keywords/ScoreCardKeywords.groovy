package com.ct.qa.keywords

import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import com.ct.qa.constants.ProjectConstants
import com.ct.qa.struct.ScoreCard
import com.ct.qa.struct.UnmatchedItems
import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.checkpoint.Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testcase.TestCase
import com.kms.katalon.core.testdata.TestData
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.util.KeywordUtil
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

import internal.GlobalVariable
import io.appium.java_client.MobileElement

public class ScoreCardKeywords {

	@Keyword
	def displayMissingScoreCardInReport(){
		if(!ProjectConstants.missingscorecardremarks.isEmpty()){
			String message = "\n\n-----------------------------------------------------Missing Score Card------------------------------------------------------------------------------------------\n\n"
			for(int i=0; i<ProjectConstants.missingscorecardremarks.size() ;i++){
				ScoreCard scorecard = ProjectConstants.missingscorecardremarks.get(i)
				message = message +
						String.format("%-35s%-100s", "User Role:",scorecard.getUser_role())+"\n"+
						String.format("%-35s%-100s", "Button Title:",scorecard.getButton_title())+"\n"+
						String.format("%-35s", "Remarks:")
				for(int j=0; j<scorecard.getRemarks().size(); j++){
					message = message + scorecard.getRemarks().get(j)+", "
				}
				message = message + "\n" + scorecard.getMissingremarks_errormessage() + "\n\n"
			}
			message = message +
					"\n\n---------------------------------------------------------------------------------------------------------------------------------------------------------------------\n\n"
			KeywordUtil.logInfo(message)
		}
		else{}
	}
	@Keyword
	//load and compare supervisor and merchandiser score card remarks
	def validate_Supervisor_Merchandisers_PD_MTD_Remarks(){
		ArrayList<String> expectedscorecardremarks = LoadDataKeywords.loadScoreCardRemarksList()
		ArrayList<String> displayedscorecardremarks = new ArrayList<String>()
		//validate supervisor score buttons remarks
		//P.D button
		ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.RelativeLayout[1]/android.widget.Button[1]").click()
		Mobile.verifyElementText(findTestObject("ScoreCard/Validate_ScoreDetailsScreen", [('package') : ProjectConstants.PACKAGENAME]), "Score Details")
		int totalremarks = ProjectConstants.DRIVER.findElementsByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.ListView[1]/*").size()
		for(int i=1; i<= totalremarks; i++){
			MobileElement remark = ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.ListView[1]/android.widget.RelativeLayout["+i+"]/android.widget.TextView[1]")
			displayedscorecardremarks.add(remark.getText())
		}
		UnmatchedItems unmatcheditems_status = CompareDataKeywords.compareLists(expectedscorecardremarks, displayedscorecardremarks)
		if(unmatcheditems_status.getStatus() == 2){
			ScoreCard scorecard = new ScoreCard()
			scorecard.setUser_role("Supervisor")
			scorecard.setButton_title("P.D")
			scorecard.setRemarks(unmatcheditems_status.getItems())
			scorecard.setMissingremarks_errormessage(ProjectConstants.MESSAGEFOR_ITEMSARE_NOTMATCH)
			ProjectConstants.missingscorecardremarks.add(scorecard)
		}
		else if(unmatcheditems_status.getStatus() == 1){
			ScoreCard scorecard = new ScoreCard()
			scorecard.setUser_role("Supervisor")
			scorecard.setButton_title("P.D")
			scorecard.setRemarks(unmatcheditems_status.getItems())
			scorecard.setMissingremarks_errormessage(ProjectConstants.MESSAGEFOR_ITEMSARE_MORE)
			ProjectConstants.missingscorecardremarks.add(scorecard)
		}
		else if(unmatcheditems_status.getStatus() == -1){
			ScoreCard scorecard = new ScoreCard()
			scorecard.setUser_role("Supervisor")
			scorecard.setButton_title("P.D")
			scorecard.setRemarks(unmatcheditems_status.getItems())
			scorecard.setMissingremarks_errormessage(ProjectConstants.MESSAGEFOR_ITEMSARE_MISSING)
			ProjectConstants.missingscorecardremarks.add(scorecard)
		}
		else{
		}
		Mobile.verifyElementText(findTestObject("ScoreCard/Validate_ScoreDetailsScreen", [('package') : ProjectConstants.PACKAGENAME]), "Score Details")
		Mobile.pressBack()
		Mobile.verifyElementText(findTestObject('ScoreCard/Validate_MerchandisersListScreen', [('package') : ProjectConstants.PACKAGENAME]), 'Scoring')
		//MTD button
		displayedscorecardremarks = new ArrayList<String>()
		ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.RelativeLayout[1]/android.widget.Button[2]").click()
		Mobile.verifyElementText(findTestObject("ScoreCard/Validate_ScoreDetailsScreen", [('package') : ProjectConstants.PACKAGENAME]), "Score Details")
		totalremarks = ProjectConstants.DRIVER.findElementsByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.ListView[1]/*").size()
		for(int i=1; i<= totalremarks; i++){
			MobileElement remark = ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.ListView[1]/android.widget.RelativeLayout["+i+"]/android.widget.TextView[1]")
			displayedscorecardremarks.add(remark.getText())
		}
		unmatcheditems_status = CompareDataKeywords.compareLists(expectedscorecardremarks, displayedscorecardremarks)
		if(unmatcheditems_status.getStatus() == 2){
			ScoreCard scorecard = new ScoreCard()
			scorecard.setUser_role("Supervisor")
			scorecard.setButton_title("MTD")
			scorecard.setRemarks(unmatcheditems_status.getItems())
			scorecard.setMissingremarks_errormessage(ProjectConstants.MESSAGEFOR_ITEMSARE_NOTMATCH)
			ProjectConstants.missingscorecardremarks.add(scorecard)
		}
		else if(unmatcheditems_status.getStatus() == 1){
			ScoreCard scorecard = new ScoreCard()
			scorecard.setUser_role("Supervisor")
			scorecard.setButton_title("MTD")
			scorecard.setRemarks(unmatcheditems_status.getItems())
			scorecard.setMissingremarks_errormessage(ProjectConstants.MESSAGEFOR_ITEMSARE_MORE)
			ProjectConstants.missingscorecardremarks.add(scorecard)
		}
		else if(unmatcheditems_status.getStatus() == -1){
			ScoreCard scorecard = new ScoreCard()
			scorecard.setUser_role("Supervisor")
			scorecard.setButton_title("MTD")
			scorecard.setRemarks(unmatcheditems_status.getItems())
			scorecard.setMissingremarks_errormessage(ProjectConstants.MESSAGEFOR_ITEMSARE_MISSING)
			ProjectConstants.missingscorecardremarks.add(scorecard)
		}
		else{
		}
		Mobile.verifyElementText(findTestObject("ScoreCard/Validate_ScoreDetailsScreen", [('package') : ProjectConstants.PACKAGENAME]), "Score Details")
		Mobile.pressBack()
		Mobile.verifyElementText(findTestObject('ScoreCard/Validate_MerchandisersListScreen', [('package') : ProjectConstants.PACKAGENAME]), 'Scoring')
		//validate merchandiser score buttons remarks
		//P.D button
		displayedscorecardremarks = new ArrayList<String>()
		ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.ListView[1]/android.widget.RelativeLayout[1]/android.widget.Button[1]").click()
		Mobile.verifyElementText(findTestObject("ScoreCard/Validate_ScoreDetailsScreen", [('package') : ProjectConstants.PACKAGENAME]), "Score Details")
		totalremarks = ProjectConstants.DRIVER.findElementsByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.ListView[1]/*").size()
		for(int i=1; i<= totalremarks; i++){
			MobileElement remark = ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.ListView[1]/android.widget.RelativeLayout["+i+"]/android.widget.TextView[1]")
			displayedscorecardremarks.add(remark.getText())
		}
		unmatcheditems_status = CompareDataKeywords.compareLists(expectedscorecardremarks, displayedscorecardremarks)
		if(unmatcheditems_status.getStatus() == 2){
			ScoreCard scorecard = new ScoreCard()
			scorecard.setUser_role("Merchandiser")
			scorecard.setButton_title("P.D")
			scorecard.setRemarks(unmatcheditems_status.getItems())
			scorecard.setMissingremarks_errormessage(ProjectConstants.MESSAGEFOR_ITEMSARE_NOTMATCH)
			ProjectConstants.missingscorecardremarks.add(scorecard)
		}
		else if(unmatcheditems_status.getStatus() == 1){
			ScoreCard scorecard = new ScoreCard()
			scorecard.setUser_role("Merchandiser")
			scorecard.setButton_title("P.D")
			scorecard.setRemarks(unmatcheditems_status.getItems())
			scorecard.setMissingremarks_errormessage(ProjectConstants.MESSAGEFOR_ITEMSARE_MORE)
			ProjectConstants.missingscorecardremarks.add(scorecard)
		}
		else if(unmatcheditems_status.getStatus() == -1){
			ScoreCard scorecard = new ScoreCard()
			scorecard.setUser_role("Merchandiser")
			scorecard.setButton_title("P.D")
			scorecard.setRemarks(unmatcheditems_status.getItems())
			scorecard.setMissingremarks_errormessage(ProjectConstants.MESSAGEFOR_ITEMSARE_MISSING)
			ProjectConstants.missingscorecardremarks.add(scorecard)
		}
		else{
		}
		Mobile.verifyElementText(findTestObject("ScoreCard/Validate_ScoreDetailsScreen", [('package') : ProjectConstants.PACKAGENAME]), "Score Details")
		Mobile.pressBack()
		Mobile.verifyElementText(findTestObject('ScoreCard/Validate_MerchandisersListScreen', [('package') : ProjectConstants.PACKAGENAME]), 'Scoring')
		//MTD button
		displayedscorecardremarks = new ArrayList<String>()
		ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.ListView[1]/android.widget.RelativeLayout[1]/android.widget.Button[2]").click()
		Mobile.verifyElementText(findTestObject("ScoreCard/Validate_ScoreDetailsScreen", [('package') : ProjectConstants.PACKAGENAME]), "Score Details")
		totalremarks = ProjectConstants.DRIVER.findElementsByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.ListView[1]/*").size()
		for(int i=1; i<= totalremarks; i++){
			MobileElement remark = ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.ListView[1]/android.widget.RelativeLayout["+i+"]/android.widget.TextView[1]")
			displayedscorecardremarks.add(remark.getText())
		}
		unmatcheditems_status = CompareDataKeywords.compareLists(expectedscorecardremarks, displayedscorecardremarks)
		if(unmatcheditems_status.getStatus() == 2){
			ScoreCard scorecard = new ScoreCard()
			scorecard.setUser_role("Merchandiser")
			scorecard.setButton_title("MTD")
			scorecard.setRemarks(unmatcheditems_status.getItems())
			scorecard.setMissingremarks_errormessage(ProjectConstants.MESSAGEFOR_ITEMSARE_NOTMATCH)
			ProjectConstants.missingscorecardremarks.add(scorecard)
		}
		else if(unmatcheditems_status.getStatus() == 1){
			ScoreCard scorecard = new ScoreCard()
			scorecard.setUser_role("Merchandiser")
			scorecard.setButton_title("MTD")
			scorecard.setRemarks(unmatcheditems_status.getItems())
			scorecard.setMissingremarks_errormessage(ProjectConstants.MESSAGEFOR_ITEMSARE_MORE)
			ProjectConstants.missingscorecardremarks.add(scorecard)
		}
		else if(unmatcheditems_status.getStatus() == -1){
			ScoreCard scorecard = new ScoreCard()
			scorecard.setUser_role("Merchandiser")
			scorecard.setButton_title("MTD")
			scorecard.setRemarks(unmatcheditems_status.getItems())
			scorecard.setMissingremarks_errormessage(ProjectConstants.MESSAGEFOR_ITEMSARE_MISSING)
			ProjectConstants.missingscorecardremarks.add(scorecard)
		}
		else{
		}
		Mobile.verifyElementText(findTestObject("ScoreCard/Validate_ScoreDetailsScreen", [('package') : ProjectConstants.PACKAGENAME]), "Score Details")
		Mobile.pressBack()
		Mobile.verifyElementText(findTestObject('ScoreCard/Validate_MerchandisersListScreen', [('package') : ProjectConstants.PACKAGENAME]), 'Scoring')
		//pick supervisor and first merchandiser name
		MobileElement supervisor = ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.RelativeLayout[1]/android.widget.TextView[2]")
		ProjectConstants.SUPERVISOR_NAME = supervisor.getText()
		MobileElement merchandiser = ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.ListView[1]/android.widget.RelativeLayout[1]/android.widget.TextView[1]")
		ProjectConstants.MERCHANDISER_NAME = merchandiser.getText()
		//click on first merchandiser
		ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.ListView[1]/android.widget.RelativeLayout[1]").click()
	}
}
