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

public class ShopProductsData {
	public String product
	public String facingdata
	public String stocktakingdata_stockcountdata
	public String depthdata
	public String overwritefacingdata
	public String overwritestocktakingdata_stockcountdata
	public String overwritedepthdata
	public String getProduct() {
		return product;
	}
	public void setProduct(String product) {
		this.product = product;
	}
	public String getFacingdata() {
		return facingdata;
	}
	public void setFacingdata(String facingdata) {
		this.facingdata = facingdata;
	}
	public String getStocktakingdata_stockcountdata() {
		return stocktakingdata_stockcountdata;
	}
	public void setStocktakingdata_stockcountdata(String stocktakingdata_stockcountdata) {
		this.stocktakingdata_stockcountdata = stocktakingdata_stockcountdata;
	}
	public String getDepthdata() {
		return depthdata;
	}
	public void setDepthdata(String depthdata) {
		this.depthdata = depthdata;
	}
	public String getOverwritefacingdata() {
		return overwritefacingdata;
	}
	public void setOverwritefacingdata(String overwritefacingdata) {
		this.overwritefacingdata = overwritefacingdata;
	}
	public String getOverwritestocktakingdata_stockcountdata() {
		return overwritestocktakingdata_stockcountdata;
	}
	public void setOverwritestocktakingdata_stockcountdata(String overwritestocktakingdata_stockcountdata) {
		this.overwritestocktakingdata_stockcountdata = overwritestocktakingdata_stockcountdata;
	}
	public String getOverwritedepthdata() {
		return overwritedepthdata;
	}
	public void setOverwritedepthdata(String overwritedepthdata) {
		this.overwritedepthdata = overwritedepthdata;
	}

	
}
