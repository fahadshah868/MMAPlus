package com.ct.qa.struct

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
import net.sourceforge.htmlunit.corejs.javascript.ast.ArrayLiteral
import MobileBuiltInKeywords as Mobile
import WSBuiltInKeywords as WS
import WebUiBuiltInKeywords as WebUI

public class TaggedChillersRemark {
	public String chillertype
	public String chillerremark
	public ArrayList<VisitedChillerProductsCategoryData> visitedchillerproductscategories
	public ArrayList<MissingChillerProductsCategoryData> missingchillerproductscategories
	public String errormessage_formissingproductscategories

	public TaggedChillersRemark(){
		this.visitedchillerproductscategories = new ArrayList<VisitedChillerProductsCategoryData>()
		this.missingchillerproductscategories = new ArrayList<MissingChillerProductsCategoryData>()
	}
	public String getChillertype() {
		return chillertype;
	}
	public void setChillertype(String chillertype) {
		this.chillertype = chillertype;
	}
	public String getChillerremark() {
		return chillerremark;
	}
	public void setChillerremark(String chillerremark) {
		this.chillerremark = chillerremark;
	}
	public ArrayList<VisitedChillerProductsCategoryData> getVisitedchillerproductscategories() {
		return visitedchillerproductscategories;
	}
	public void setVisitedchillerproductscategories(
			VisitedChillerProductsCategoryData visitedchillerproductscategories) {
		this.visitedchillerproductscategories.add(visitedchillerproductscategories);
	}
	public ArrayList<MissingChillerProductsCategoryData> getMissingchillerproductscategories() {
		return missingchillerproductscategories;
	}
	public void setMissingchillerproductscategories(
			MissingChillerProductsCategoryData missingchillerproductscategories) {
		this.missingchillerproductscategories.add(missingchillerproductscategories);
	}
	public String getErrormessage_formissingproductscategories() {
		return errormessage_formissingproductscategories;
	}
	public void setErrormessage_formissingproductscategories(String errormessage_formissingproductscategories) {
		this.errormessage_formissingproductscategories = errormessage_formissingproductscategories;
	}
}
