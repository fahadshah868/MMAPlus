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

import MobileBuiltInKeywords as Mobile
import WSBuiltInKeywords as WS
import WebUiBuiltInKeywords as WebUI

public class VisitedCategoryData {
	public String maincategory
	public String firstvisit_categoryremark
	public String overwrite_categoryremark
	public ArrayList<TaggedChillersRemark> taggedchillersremark
	public ArrayList<ProductCategoryWithProducts> productcategorywithproducts

	public VisitedCategoryData(){
		this.taggedchillersremark = new ArrayList<TaggedChillersRemark>()
		this.productcategorywithproducts = new ArrayList<ProductCategoryWithProducts>()
	}

	public String getFirstvisit_categoryremark() {
		return firstvisit_categoryremark;
	}
	public void setFirstvisit_categoryremark(String firstvisit_categoryremark) {
		this.firstvisit_categoryremark = firstvisit_categoryremark;
	}
	public String getOverwrite_categoryremark() {
		return overwrite_categoryremark;
	}
	public void setOverwrite_categoryremark(String overwrite_categoryremark) {
		this.overwrite_categoryremark = overwrite_categoryremark;
	}
	public String getMaincategory() {
		return maincategory;
	}
	public void setMaincategory(String maincategory) {
		this.maincategory = maincategory;
	}
	public ArrayList<TaggedChillersRemark> getTaggedchillersremark() {
		return taggedchillersremark;
	}
	public void setTaggedchillersremark(TaggedChillersRemark taggedchillersremark) {
		this.taggedchillersremark.add(taggedchillersremark);
	}
	public ArrayList<ProductCategoryWithProducts> getProductcategorywithproducts() {
		return productcategorywithproducts;
	}
	public void setProductcategorywithproducts(ProductCategoryWithProducts productcategorywithproducts) {
		this.productcategorywithproducts.add(productcategorywithproducts);
	}
}
