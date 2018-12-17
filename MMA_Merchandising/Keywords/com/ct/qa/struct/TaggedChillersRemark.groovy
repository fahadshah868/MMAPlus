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
	public String firstvisit_chillertype
	public String overwrite_chillertype
	public String firstvisit_chillerremark
	public String overwrite_chillerremark
	public ArrayList<VisitedChillerProductsCategoryData> visitedchillerproductscategories
	public ArrayList<MissingChillerProductsCategoryData> missingchillerproductscategories
	public String errormessage_formissingproductscategories

	public TaggedChillersRemark(){
		this.visitedchillerproductscategories = new ArrayList<VisitedChillerProductsCategoryData>()
		this.missingchillerproductscategories = new ArrayList<MissingChillerProductsCategoryData>()
	}
	public String getFirstvisit_chillertype() {
		return firstvisit_chillertype;
	}
	public void setFirstvisit_chillertype(String firstvisit_chillertype) {
		this.firstvisit_chillertype = firstvisit_chillertype;
	}
	public String getOverwrite_chillertype() {
		return overwrite_chillertype;
	}
	public void setOverwrite_chillertype(String overwrite_chillertype) {
		this.overwrite_chillertype = overwrite_chillertype;
	}
	public String getFirstvisit_chillerremark() {
		return firstvisit_chillerremark;
	}
	public void setFirstvisit_chillerremark(String firstvisit_chillerremark) {
		this.firstvisit_chillerremark = firstvisit_chillerremark;
	}
	public String getOverwrite_chillerremark() {
		return overwrite_chillerremark;
	}
	public void setOverwrite_chillerremark(String overwrite_chillerremark) {
		this.overwrite_chillerremark = overwrite_chillerremark;
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
