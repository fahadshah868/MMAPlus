package com.ct.qa.struct

import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

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

public class ScenariosCombination {
	public int firstvisit_scenario
	public int overwrite_scenario
	public int getFirstvisit_scenario() {
		return firstvisit_scenario;
	}
	public void setFirstvisit_scenario(int firstvisit_scenario) {
		this.firstvisit_scenario = firstvisit_scenario;
	}
	public int getOverwrite_scenario() {
		return overwrite_scenario;
	}
	public void setOverwrite_scenario(int overwrite_scenario) {
		this.overwrite_scenario = overwrite_scenario;
	}
}
