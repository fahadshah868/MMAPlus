package qa.struct

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

public class VisitedShopDataInfo {
	public String supervisorname
	public String merchandisername
	public String workingaction
	public String route
	public String shopname
	public String shopchannel
	public String shop_scenario
	public String remark
	public String othercategories_scenarios
	public ArrayList<VisitedCategoryData> visitedcategoriesdata

	public VisitedShopDataInfo(){
		this.visitedcategoriesdata = new ArrayList<VisitedCategoryData>()
	}
	public String getSupervisorname() {
		return supervisorname;
	}
	public void setSupervisorname(String supervisorname) {
		this.supervisorname = supervisorname;
	}
	public String getMerchandisername() {
		return merchandisername;
	}
	public void setMerchandisername(String merchandisername) {
		this.merchandisername = merchandisername;
	}
	public String getWorkingaction() {
		return workingaction;
	}
	public void setWorkingaction(String workingaction) {
		this.workingaction = workingaction;
	}
	public String getRoute() {
		return route;
	}
	public void setRoute(String route) {
		this.route = route;
	}
	public String getShop_scenario() {
		return shop_scenario;
	}
	public void setShop_scenario(String shop_scenario) {
		this.shop_scenario = shop_scenario;
	}
	public String getOthercategories_scenarios() {
		return othercategories_scenarios;
	}
	public void setOthercategories_scenarios(String othercategories_scenarios) {
		this.othercategories_scenarios = othercategories_scenarios;
	}
	public String getShopname() {
		return shopname;
	}
	public void setShopname(String shopname) {
		this.shopname = shopname;
	}
	public String getShopchannel() {
		return shopchannel;
	}
	public void setShopchannel(String shopchannel) {
		this.shopchannel = shopchannel;
	}
	public ArrayList<VisitedCategoryData> getVisitedcategoriesdata() {
		return visitedcategoriesdata;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public void setVisitedcategoriesdata(VisitedCategoryData visitedcategoriesdata) {
		this.visitedcategoriesdata.add(visitedcategoriesdata);
	}
}
