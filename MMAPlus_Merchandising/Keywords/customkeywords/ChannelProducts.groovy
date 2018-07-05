package customkeywords

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

public class ChannelProducts {
	public String maincategory
	public String productcategory
	public String product
	public String dsa_facing
	public String dsa_stocktaking
	public String nsfd_facing
	public String nsfd_stocktaking
	public String dsa_overwritefacing
	public String dsa_overwritestocktaking
	public String nsfd_overwritefacing
	public String nsfd_overwritestocktaking
	public String chiller_facing
	public String chiller_stocktaking
	public String chiller_overwritefacing
	public String chiller_overwritestocktaking

	public int _maincategory
	public int _productcategory
	public int _product
	public int _dsa_facing
	public int _dsa_stocktaking
	public int _nsfd_facing
	public int _nsfd_stocktaking
	public int _dsa_overwritefacing
	public int _dsa_overwritestocktaking
	public int _nsfd_overwritefacing
	public int _nsfd_overwritestocktaking
	public int _chiller_facing
	public int _chiller_stocktaking
	public int _chiller_overwritefacing
	public int _chiller_overwritestocktaking

	public String getMaincategory() {
		return maincategory;
	}
	public void setMaincategory(String maincategory) {
		this.maincategory = maincategory;
	}
	public String getProductcategory() {
		return productcategory;
	}
	public void setProductcategory(String productcategory) {
		this.productcategory = productcategory;
	}
	public String getProduct() {
		return product;
	}
	public void setProduct(String product) {
		this.product = product;
	}
	public String getDsa_facing() {
		return dsa_facing;
	}
	public void setDsa_facing(String dsa_facing) {
		this.dsa_facing = dsa_facing;
	}
	public String getDsa_stocktaking() {
		return dsa_stocktaking;
	}
	public void setDsa_stocktaking(String dsa_stocktaking) {
		this.dsa_stocktaking = dsa_stocktaking;
	}
	public String getNsfd_facing() {
		return nsfd_facing;
	}
	public void setNsfd_facing(String nsfd_facing) {
		this.nsfd_facing = nsfd_facing;
	}
	public String getNsfd_stocktaking() {
		return nsfd_stocktaking;
	}
	public void setNsfd_stocktaking(String nsfd_stocktaking) {
		this.nsfd_stocktaking = nsfd_stocktaking;
	}
	public String getDsa_overwritefacing() {
		return dsa_overwritefacing;
	}
	public void setDsa_overwritefacing(String dsa_overwritefacing) {
		this.dsa_overwritefacing = dsa_overwritefacing;
	}
	public String getDsa_overwritestocktaking() {
		return dsa_overwritestocktaking;
	}
	public void setDsa_overwritestocktaking(String dsa_overwritestocktaking) {
		this.dsa_overwritestocktaking = dsa_overwritestocktaking;
	}
	public String getNsfd_overwritefacing() {
		return nsfd_overwritefacing;
	}
	public void setNsfd_overwritefacing(String nsfd_overwritefacing) {
		this.nsfd_overwritefacing = nsfd_overwritefacing;
	}
	public String getNsfd_overwritestocktaking() {
		return nsfd_overwritestocktaking;
	}
	public void setNsfd_overwritestocktaking(String nsfd_overwritestocktaking) {
		this.nsfd_overwritestocktaking = nsfd_overwritestocktaking;
	}
	public String getChiller_facing() {
		return chiller_facing;
	}
	public void setChiller_facing(String chiller_facing) {
		this.chiller_facing = chiller_facing;
	}
	public String getChiller_stocktaking() {
		return chiller_stocktaking;
	}
	public void setChiller_stocktaking(String chiller_stocktaking) {
		this.chiller_stocktaking = chiller_stocktaking;
	}
	public String getChiller_overwritefacing() {
		return chiller_overwritefacing;
	}
	public void setChiller_overwritefacing(String chiller_overwritefacing) {
		this.chiller_overwritefacing = chiller_overwritefacing;
	}
	public String getChiller_overwritestocktaking() {
		return chiller_overwritestocktaking;
	}
	public void setChiller_overwritestocktaking(String chiller_overwritestocktaking) {
		this.chiller_overwritestocktaking = chiller_overwritestocktaking;
	}

	def loadColumns(){
	}
}
