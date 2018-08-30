package com.ct.qa.interfaces

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

public interface ShopCategories {
	// for data verification
	def visitShopCategoriesWithDataVerification()

	/*category flows with chiller*/

	//chiller not allocated -> SKDNA  || DSA -> NSFD || chiller available -> chiller not available
	def visitShopCategoriesWith_CNAl_CAv_DSA()
	def visitShopCategoriesWith_SKDNA_CNAv_NSFD()

	//SKDNA -> Chiller not allocated || NSFD -> DSA || chiller not available -> chiller available
	//def visitShopCategoriesWith_SKDNA_CNAv_NSFD()
	//def visitShopCategoriesWith_CNAl_CAv_DSA()

	//SKDNA -> SKDNA
	def visitShopCategoriesWith_SKDNA_SKDNA_SKDNA()
	def overwriteShopCategoriesWith_SKDNA_SKDNA_SKDNA()

	//chiller not allocated -> chiller not allocated || chiller availble -> chiller available || DSA -> DSA
	//def visitShopCategoriesWith_CNAl_CAv_DSA()     (implemented above)
	def overwriteShopCategoriesWith_CNAl_CAv_DSA()


	/*--------------------------------------------------------------------------------------------------------------------------------*/


	/*category flows with chiller utilization*/

	//chiller not allocated -> SKDNA || CAv -> CNAv || NSFD -> SKDNA
	def visitShopCategoriesWith_CNAl_CAv_NSFD()
	def visitShopCategoriesWith_SKDNA_CNAv_SKDNA()

	//chiller not allocated -> SKDNA || CNAv -> CAv || SKDNA -> DSA
	def visitShopCategoriesWith_CNAl_CNAv_SKDNA()
	def visitShopCategoriesWith_SKDNA_CAv_DSA()

	//chiller not allocated -> SKDNA || CNAv -> CAv || DSA -> SKDNA
	def visitShopCategoriesWith_CNAl_CNAv_DSA()
	def visitShopCategoriesWith_SKDNA_CAv_SKDNA()

	//chiller not allocated -> chiller not allocated || chiller availble -> chiller available || DSA -> DSA
	//def visitShopCategoriesWith_CNAl_CAv_DSA()     (implemented above)
	//def overwriteShopCategoriesWith_CNAl_CAv_DSA()

	//SKDNA -> chiller not allocated || SKDNA -> chiller available || SKDNA -> DSA
	//def visitShopCategoriesWith_SKDNA_SKDNA_SKDNA()		(implemented above)
	//def visitShopCategoriesWith_CNAl_CAv_DSA()

	//chiller not allocated -> SKDNA || chiller type not available -> chiller available || NSFD -> DSA
	def visitShopCategoriesWith_CNAl_CTNAv_NSFD()
	//def visitShopCategoriesWith_SKDNA_CAv_DSA()		(implemented above)

	//SKDNA -> SKDNA
	//def visitShopCategoriesWith_SKDNA_SKDNA_SKDNA()
	//def overwriteShopCategoriesWith_SKDNA_SKDNA_SKDNA()

}
