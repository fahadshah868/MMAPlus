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

public class MissingChillerProductsCategoryData {
	public String productcategory
	public ArrayList<String> productcategories
	public String errormessage_forproductcategories
	public ArrayList<String> products
	public String errormessage_forproducts
	public String getErrormessage_forproducts() {
		return errormessage_forproducts;
	}
	public void setErrormessage_forproducts(String errormessage_forproducts) {
		this.errormessage_forproducts = errormessage_forproducts;
	}
	public String getProductcategory() {
		return productcategory;
	}
	public void setProductcategory(String productcategory) {
		this.productcategory = productcategory;
	}
	public ArrayList<String> getProductcategories() {
		return productcategories;
	}
	public void setProductcategories(ArrayList<String> productcategories) {
		this.productcategories = productcategories;
	}
	public String getErrormessage_forproductcategories() {
		return errormessage_forproductcategories;
	}
	public void setErrormessage_forproductcategories(String errormessage_forproductcategories) {
		this.errormessage_forproductcategories = errormessage_forproductcategories;
	}
	public ArrayList<String> getProducts() {
		return products;
	}
	public void setProducts(ArrayList<String> products) {
		this.products = products;
	}
}
