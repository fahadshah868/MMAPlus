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

public class ChillerProducts {
	public String category
	public String product
	public String facing
	public String depth
	public String stocktaking
	public String overwritefacing
	public String overwritedepth
	public String overwritestocktaking
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getProduct() {
		return product;
	}
	public void setProduct(String product) {
		this.product = product;
	}
	public String getFacing() {
		return facing;
	}
	public void setFacing(String facing) {
		this.facing = facing;
	}
	public String getDepth() {
		return depth;
	}
	public void setDepth(String depth) {
		this.depth = depth;
	}
	public String getStocktaking() {
		return stocktaking;
	}
	public void setStocktaking(String stocktaking) {
		this.stocktaking = stocktaking;
	}
	public String getOverwritefacing() {
		return overwritefacing;
	}
	public void setOverwritefacing(String overwritefacing) {
		this.overwritefacing = overwritefacing;
	}
	public String getOverwritedepth() {
		return overwritedepth;
	}
	public void setOverwritedepth(String overwritedepth) {
		this.overwritedepth = overwritedepth;
	}
	public String getOverwritestocktaking() {
		return overwritestocktaking;
	}
	public void setOverwritestocktaking(String overwritestocktaking) {
		this.overwritestocktaking = overwritestocktaking;
	}
}