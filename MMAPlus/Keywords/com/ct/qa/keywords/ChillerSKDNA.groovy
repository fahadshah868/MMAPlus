package com.ct.qa.keywords

import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import com.ct.qa.constants.ProjectConstants
import com.ct.qa.struct.TaggedChillersRemark
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

public class ChillerSKDNA {
	
	@Keyword
	def visitSKDNA(){
		int totalremarks = ProjectConstants.DRIVER.findElementsByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ListView[1]/*").size()
		String remark_text = ""
		for(int i=1; i<= totalremarks; i++){
			MobileElement remark = ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout["+i+"]/android.widget.TextView[1]")
			remark_text = remark.getText()
			if(ProjectConstants.SCENARIO.equalsIgnoreCase("first visit")){
				if(remark_text.equalsIgnoreCase("Expiry Issue")){
					ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout["+i+"]").click()
					break
				}
				else{}
			}
			else{
				if(remark_text.equalsIgnoreCase("Others")){
					ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout["+i+"]").click()
					break
				}
				else{}
			}
		}
		VisitedCategoryData visitedcategorydata = new VisitedCategoryData()
		TaggedChillersRemark taggedchillerremarks = new TaggedChillersRemark()
		visitedcategorydata.setMaincategory(ProjectConstants.CURRENTVISITING_MAINCATEGORY)
		if(ProjectConstants.SCENARIO.equalsIgnoreCase("first visit")){
			taggedchillerremarks.setFirstvisit_chillertype(ProjectConstants.CURRENTVISITING_CHILLERTYPE)
			taggedchillerremarks.setFirstvisit_chillerremark(ProjectConstants.CURRENTVISITING_CHILLERREMARK+" with '"+remark_text+"' remark")
		}
		else{
			taggedchillerremarks.setOverwrite_chillertype(ProjectConstants.CURRENTVISITING_CHILLERTYPE)
			taggedchillerremarks.setOverwrite_chillerremark(ProjectConstants.CURRENTVISITING_CHILLERREMARK+" with '"+remark_text+"' remark")
		}
		visitedcategorydata.setTaggedchillersremark(taggedchillerremarks)
		for(int i=0; i< ProjectConstants.visitedshopdatainfo.size(); i++){
			if(ProjectConstants.visitedshopdatainfo.get(i).getShopname().equals(ProjectConstants.CURRENTVISITING_SHOPNAME)){
				VisitedShopDataInfo visitedshopdata = ProjectConstants.visitedshopdatainfo.get(i)
				ArrayList<VisitedCategoryData> visitedcategoriesdata = visitedshopdata.getVisitedcategoriesdata()
				if(visitedcategoriesdata.size() != 0){
					boolean maincategory_flag = false
					for(int k=0; k<visitedcategoriesdata.size(); k++){
						VisitedCategoryData visitedcategorydatainfo = visitedcategoriesdata.get(k)
						if(visitedcategorydatainfo.getMaincategory().equals(visitedcategorydata.getMaincategory())){
							maincategory_flag = true
							ArrayList<TaggedChillersRemark> taggedchillersremarks = visitedcategorydatainfo.getTaggedchillersremark()
							for(int p=0; p<taggedchillersremarks.size(); p++){
								TaggedChillersRemark taggedchillerremarkinfo = taggedchillersremarks.get(p)
								if(ProjectConstants.SCENARIO.equalsIgnoreCase("first visit")){
									taggedchillerremarkinfo.setFirstvisit_chillertype(ProjectConstants.CURRENTVISITING_CHILLERTYPE)
									taggedchillerremarkinfo.setFirstvisit_chillerremark(ProjectConstants.CURRENTVISITING_CHILLERREMARK+" with '"+remark_text+"' remark")
								}
								else{
									taggedchillerremarkinfo.setOverwrite_chillertype(ProjectConstants.CURRENTVISITING_CHILLERTYPE)
									taggedchillerremarkinfo.setOverwrite_chillerremark(ProjectConstants.CURRENTVISITING_CHILLERREMARK+" with '"+remark_text+"' remark")
								}
							}
						}
					}
					if(maincategory_flag == false){
						ProjectConstants.visitedshopdatainfo.get(i).setVisitedcategoriesdata(visitedcategorydata)
						break
					}
				}
				else{
					ProjectConstants.visitedshopdatainfo.get(i).setVisitedcategoriesdata(visitedcategorydata)
					break
				}
			}
		}
	}
}
