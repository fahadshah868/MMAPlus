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

public class MissingCategoryData {
	public String maincategory
	public ArrayList<TaggedChillersRemark> taggedchillersremarks

	public String ProductCategory
	public ArrayList<String> productcategories
	public ArrayList<String> products
	public String productcategories_errormessage
	public String products_errormessage

	public MissingCategoryData(){
		this.taggedchillersremarks = new ArrayList<TaggedChillersRemark>()
	}
	public String getMaincategory() {
		return maincategory;
	}
	public void setMaincategory(String maincategory) {
		this.maincategory = maincategory;
	}
	public ArrayList<TaggedChillersRemark> getTaggedchillersremarks() {
		return taggedchillersremarks;
	}
	public void setTaggedchillersremarks(TaggedChillersRemark taggedchillersremarks) {
		this.taggedchillersremarks.add(taggedchillersremarks);
	}
	public String getProductCategory() {
		return ProductCategory;
	}
	public void setProductCategory(String productCategory) {
		this.ProductCategory = productCategory;
	}
	public ArrayList<String> getProductcategories() {
		return productcategories;
	}
	public void setProductcategories(ArrayList<String> productcategories) {
		this.productcategories = productcategories;
	}
	public ArrayList<String> getProducts() {
		return products;
	}
	public void setProducts(ArrayList<String> products) {
		this.products = products;
	}
	public String getProductcategories_errormessage() {
		return productcategories_errormessage;
	}
	public void setProductcategories_errormessage(String productcategories_errormessage) {
		this.productcategories_errormessage = productcategories_errormessage;
	}
	public String getProducts_errormessage() {
		return products_errormessage;
	}
	public void setProducts_errormessage(String products_errormessage) {
		this.products_errormessage = products_errormessage;
	}
}
