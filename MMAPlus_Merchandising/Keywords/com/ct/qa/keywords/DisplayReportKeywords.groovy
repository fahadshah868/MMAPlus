package com.ct.qa.keywords

import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import com.ct.qa.constants.ProjectConstants
import com.ct.qa.struct.MissingCategoryData
import com.ct.qa.struct.MissingChillerProductsCategoryData
import com.ct.qa.struct.MissingShopDataInfo
import com.ct.qa.struct.ProductCategoryWithProducts
import com.ct.qa.struct.QuestionsData
import com.ct.qa.struct.ShopProductsData
import com.ct.qa.struct.TaggedChillersRemark
import com.ct.qa.struct.VisitedCategoryData
import com.ct.qa.struct.VisitedChillerProductsCategoryData
import com.ct.qa.struct.VisitedShopDataInfo
import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.checkpoint.Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testcase.TestCase
import com.kms.katalon.core.testdata.TestData
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.util.KeywordUtil
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

import internal.GlobalVariable

public class DisplayReportKeywords {

	def static displayDataInReport(){
		String message = "\n\n---------------------------------------------Missing Shop Data-----------------------------------------------------------------------------------------------------\n\n"+
				"<-------------------------------------------------------------------------------------------------------------------------------------->"
		for(int i=0; i<ProjectConstants.missingshopdatainfo.size(); i++){
			boolean flag = false
			MissingShopDataInfo missingshopdatainfo = ProjectConstants.missingshopdatainfo.get(i)
			if(missingshopdatainfo != null){
				if(missingshopdatainfo.getMissingshopcategories() != null){
					if(flag == false){
						message = message+"\n\n"+
								String.format("%-60s%-60s","Shop Name: "+missingshopdatainfo.getShopname(),missingshopdatainfo.getShopchannel())+"\n"+
								String.format("%-60s%-60s", "Visiting Scenarios: "+missingshopdatainfo.getScenario(),"Remark: "+missingshopdatainfo.getRemark())+
								"\n\n" + String.format("%-30s", "Shop Categories:")
						for(int j=0; j<missingshopdatainfo.getMissingshopcategories().size(); j++){
							message = message+missingshopdatainfo.getMissingshopcategories().get(j)+",   "
						}
						message = message+"\n"+missingshopdatainfo.getMissingshopcategories_errormessage() + "\n\n"
						flag = true
					}
					else{
					}
				}
				if(missingshopdatainfo.getMissingshopactions() != null){
					if(flag == false){
						message = message+"\n\n"+
								String.format("%-60s%-60s","Shop Name: "+missingshopdatainfo.getShopname(),missingshopdatainfo.getShopchannel())+"\n"+
								String.format("%-60s%-60s", "Visiting Scenarios: "+missingshopdatainfo.getScenario(),"Remark: "+missingshopdatainfo.getRemark())+
								"\n\n" + String.format("%-30s", "Shop Actions:")
						for(int j=0; j<missingshopdatainfo.getMissingshopactions().size(); j++){
							message = message+missingshopdatainfo.getMissingshopactions().get(j)+",   "
						}
						message = message+"\n"+missingshopdatainfo.getMissingshopactions_errormessage() + "\n\n"
						flag = true
					}
					else{
						message = message+"\n\n"+
								String.format("%-30s", "Shop Actions:")
						for(int j=0; j<missingshopdatainfo.getMissingshopactions().size(); j++){
							message = message+missingshopdatainfo.getMissingshopactions().get(j)+",   "
						}
						message = message+"\n"+missingshopdatainfo.getMissingshopactions_errormessage() + "\n\n"
					}
				}
				if(missingshopdatainfo.getMissingshopremarks() != null){
					if(flag == false){
						message = message+"\n\n"+
								String.format("%-60s%-60s","Shop Name: "+missingshopdatainfo.getShopname(),missingshopdatainfo.getShopchannel())+"\n"+
								String.format("%-60s%-60s", "Visiting Scenarios: "+missingshopdatainfo.getScenario(),"Remark: "+missingshopdatainfo.getRemark())+
								"\n\n" + String.format("%-30s", "Shop Remarks:")
						for(int j=0; j<missingshopdatainfo.getMissingshopremarks().size(); j++){
							message = message+missingshopdatainfo.getMissingshopremarks().get(j)+",   "
						}
						message = message+"\n"+missingshopdatainfo.getMissingshopremarks_errormessage() + "\n\n"
						flag = true
					}
					else{
						message = message+"\n\n"+
								String.format("%-30s", "Shop Remarks:")
						for(int j=0; j<missingshopdatainfo.getMissingshopremarks().size(); j++){
							message = message+missingshopdatainfo.getMissingshopremarks().get(j)+",   "
						}
						message = message+"\n"+missingshopdatainfo.getMissingshopremarks_errormessage() + "\n\n"
					}
				}
				if(missingshopdatainfo.getMissingCategoriesData() != null){
					for(int j=0; j<missingshopdatainfo.getMissingCategoriesData().size(); j++){
						MissingCategoryData missingcategorydata = missingshopdatainfo.getMissingCategoriesData().get(j)
						if(missingcategorydata.getMaincategory().equalsIgnoreCase("Chiller Utilization")){
							for(int m=0; m< missingcategorydata.getTaggedchillersremarks().size(); m++){
								TaggedChillersRemark taggedchillerremarks = missingcategorydata.getTaggedchillersremarks().get(m)
								if(taggedchillerremarks.getMissingchillerproductscategories() != null){
									for(int k=0; k<taggedchillerremarks.getMissingchillerproductscategories().size() ; k++){
										MissingChillerProductsCategoryData missingchillerproductcategory = taggedchillerremarks.getMissingchillerproductscategories().get(k)
										if(missingchillerproductcategory.getProductcategories() != null){
											if(flag == false){
												message = message+"\n\n"+
														String.format("%-60s%-60s","Shop Name: "+missingshopdatainfo.getShopname(),missingshopdatainfo.getShopchannel())+"\n"+
														String.format("%-60s%-60s", "Visiting Scenarios: "+missingshopdatainfo.getScenario(),"Remark: "+missingshopdatainfo.getRemark())+
														"\n\nProduct Categories:\n\n" +
														String.format("%-30s%-60s","Main Category:",missingcategorydata.getMaincategory()) + "\n" +
														String.format("%-30s%-60s","Chiller type:",taggedchillerremarks.getFirstvisit_chillertype()) + "\n" +
														String.format("%-30s%-60s","Chiller Remark:",taggedchillerremarks.getFirstvisit_chillerremark()) + "\n" +
														String.format("%-30s","Product Categories:")
												for(int n=0; n<missingchillerproductcategory.getProductcategories().size() ; n++){
													message = message + missingchillerproductcategory.getProductcategories().get(n) + ",   "
												}
												message = message + "\n" + missingchillerproductcategory.getErrormessage_forproductcategories() + "\n\n"
												flag = true
											}
											else{
												message = message +
														"\n\nProduct Categories:\n\n" +
														String.format("%-30s%-60s","Main Category:",missingcategorydata.getMaincategory()) + "\n" +
														String.format("%-30s%-60s","Chiller type:",taggedchillerremarks.getFirstvisit_chillertype()) + "\n" +
														String.format("%-30s%-60s","Chiller Remark:",taggedchillerremarks.getFirstvisit_chillerremark()) + "\n" +
														String.format("%-30s","Product Categories:")
												for(int n=0; n<missingchillerproductcategory.getProductcategories().size() ; n++){
													message = message + missingchillerproductcategory.getProductcategories().get(n) + ",   "
												}
												message = message + "\n" + missingchillerproductcategory.getErrormessage_forproductcategories() + "\n\n"
											}
										}
									}
								}
							}
						}
						else{
							if(missingcategorydata.getProductcategories() != null){
								if(flag == false){
									message = message+"\n\n"+
											String.format("%-60s%-60s","Shop Name: "+missingshopdatainfo.getShopname(),missingshopdatainfo.getShopchannel())+"\n"+
											String.format("%-60s%-60s", "Visiting Scenarios: "+missingshopdatainfo.getScenario(),"Remark: "+missingshopdatainfo.getRemark())+
											"\n\nProduct Categories:\n\n" +
											String.format("%-30s%-60s","Main Category:",missingcategorydata.getMaincategory()) + "\n" +
											String.format("%-30s","Product Categories:")
									for(int k=0; k<missingcategorydata.getProductcategories().size(); k++){
										message = message+missingcategorydata.getProductcategories().get(k)+",	"
									}
									message = message+"\n"+missingcategorydata.getProductcategories_errormessage()+"\n\n"
									flag = true
								}
								else{
									message = message+
											"\n\nProduct Categories:\n\n" +
											String.format("%-30s%-60s","Main Category:",missingcategorydata.getMaincategory()) + "\n" +
											String.format("%-30s","Product Categories:")
									for(int k=0; k<missingcategorydata.getProductcategories().size(); k++){
										message = message+missingcategorydata.getProductcategories().get(k)+",	"
									}
									message = message+"\n"+missingcategorydata.getProductcategories_errormessage()+"\n\n"
								}
							}
							else if(missingcategorydata.getMissing_auditquestioncategories() != null){
								if(flag == false){
									message = message+"\n\n"+
											String.format("%-60s%-60s","Shop Name: "+missingshopdatainfo.getShopname(),missingshopdatainfo.getShopchannel())+"\n"+
											String.format("%-60s%-60s", "Visiting Scenarios: "+missingshopdatainfo.getScenario(),"Remark: "+missingshopdatainfo.getRemark())+
											"\n\nQuestion Categories:\n\n" +
											String.format("%-30s%-60s","Main Category:",missingcategorydata.getMaincategory()) + "\n" +
											String.format("%-30s","Question Categories:")
									for(int k=0; k<missingcategorydata.getMissing_auditquestioncategories().size(); k++){
										message = message+missingcategorydata.getMissing_auditquestioncategories().get(k)+",	"
									}
									message = message+"\n"+missingcategorydata.getMissing_auditquestioncategories_errormessage()+"\n\n"
									flag = true
								}
								else{
									message = message+
											"\n\nQuestion Categories:\n\n" +
											String.format("%-30s%-60s","Main Category:",missingcategorydata.getMaincategory()) + "\n" +
											String.format("%-30s","Question Categories:")
									for(int k=0; k<missingcategorydata.getMissing_auditquestioncategories().size(); k++){
										message = message+missingcategorydata.getMissing_auditquestioncategories().get(k)+",	"
									}
									message = message+"\n"+missingcategorydata.getMissing_auditquestioncategories_errormessage()+"\n\n"
								}
							}
							else{}
						}
					}
				}
				if(missingshopdatainfo.getMissingCategoriesData() != null){
					for(int j=0; j<missingshopdatainfo.getMissingCategoriesData().size(); j++){
						MissingCategoryData missingcategorydata = missingshopdatainfo.getMissingCategoriesData().get(j)
						if(missingcategorydata.getMaincategory().equalsIgnoreCase("Chiller Utilization")){
							for(int m=0; m< missingcategorydata.getTaggedchillersremarks().size(); m++){
								TaggedChillersRemark taggedchillerremarks = missingcategorydata.getTaggedchillersremarks().get(m)
								if(taggedchillerremarks.getMissingchillerproductscategories() != null){
									for(int k=0; k<taggedchillerremarks.getMissingchillerproductscategories().size() ; k++){
										MissingChillerProductsCategoryData missingchillerproductcategory = taggedchillerremarks.getMissingchillerproductscategories().get(k)
										if(missingchillerproductcategory.getProductcategory() != null){
											if(flag == false){
												message = message+"\n\n"+
														String.format("%-60s%-60s","Shop Name: "+missingshopdatainfo.getShopname(),missingshopdatainfo.getShopchannel())+"\n"+
														String.format("%-60s%-60s", "Visiting Scenarios: "+missingshopdatainfo.getScenario(),"Remark: "+missingshopdatainfo.getRemark())+
														"\n\nProducts:\n\n" +
														String.format("%-30s%-60s","Main Category:",missingcategorydata.getMaincategory()) + "\n" +
														String.format("%-30s%-60s","Chiller type:",taggedchillerremarks.getFirstvisit_chillertype()) + "\n" +
														String.format("%-30s%-60s","Chiller Remark:",taggedchillerremarks.getFirstvisit_chillerremark()) + "\n" +
														String.format("%-30s%-60s","Product Category:",missingchillerproductcategory.getProductcategory()) + "\n" +
														String.format("%-30s", "Products:")
												for(int n=0; n<missingchillerproductcategory.getProducts().size() ; n++){
													message = message + missingchillerproductcategory.getProducts().get(n)+",   "
												}
												message = message + "\n" + missingchillerproductcategory.getErrormessage_forproducts() + "\n\n"
												flag = true
											}
											else{
												message = message+
														"\n\nProducts:\n\n" +
														String.format("%-30s%-60s","Main Category:",missingcategorydata.getMaincategory()) + "\n" +
														String.format("%-30s%-60s","Chiller type:",taggedchillerremarks.getFirstvisit_chillertype()) + "\n" +
														String.format("%-30s%-60s","Chiller Remark:",taggedchillerremarks.getFirstvisit_chillerremark()) + "\n" +
														String.format("%-30s%-60s","Product Category:",missingchillerproductcategory.getProductcategory()) + "\n" +
														String.format("%-30s", "Products:")
												for(int n=0; n<missingchillerproductcategory.getProducts().size() ; n++){
													message = message + missingchillerproductcategory.getProducts().get(n)+", "
												}
												message = message + "\n" + missingchillerproductcategory.getErrormessage_forproducts() + "\n\n"
											}
										}
									}
								}
							}
						}
						else{
							if(missingcategorydata.getProducts() != null){
								if(flag == false){
									message = message+"\n\n"+
											String.format("%-60s%-60s","Shop Name: "+missingshopdatainfo.getShopname(),missingshopdatainfo.getShopchannel())+"\n"+
											String.format("%-60s%-60s", "Visiting Scenarios: "+missingshopdatainfo.getScenario(),"Remark: "+missingshopdatainfo.getRemark())+
											"\n\nProducts:\n\n" +
											String.format("%-30s%-60s","Main Category:",missingcategorydata.getMaincategory()) + "\n" +
											String.format("%-30s%-60s","Product Category:",missingcategorydata.getProductCategory()) + "\n" +
											String.format("%-30s", "Products:")
									for(int k=0; k<missingcategorydata.getProducts().size(); k++){
										message = message+missingcategorydata.getProducts().get(k) + ",	"
									}
									message = message + "\n"+missingcategorydata.getProducts_errormessage() + "\n\n"
									flag = true
								}
								else{
									message = message+
											"\n\nProducts:\n\n" +
											String.format("%-30s%-60s","Main Category:",missingcategorydata.getMaincategory()) + "\n" +
											String.format("%-30s%-60s","Product Category:",missingcategorydata.getProductCategory()) + "\n" +
											String.format("%-30s", "Products:")
									for(int k=0; k<missingcategorydata.getProducts().size(); k++){
										message = message+missingcategorydata.getProducts().get(k) + ",	"
									}
									message = message + "\n"+missingcategorydata.getProducts_errormessage() + "\n\n"
								}
							}
							else if(missingcategorydata.getMissing_auditquestions() != null){
								if(flag == false){
									message = message+"\n\n"+
											String.format("%-60s%-60s","Shop Name: "+missingshopdatainfo.getShopname(),missingshopdatainfo.getShopchannel())+"\n"+
											String.format("%-60s%-60s", "Visiting Scenarios: "+missingshopdatainfo.getScenario(),"Remark: "+missingshopdatainfo.getRemark())+
											"\n\nQuestions:\n\n" +
											String.format("%-30s%-60s","Main Category:",missingcategorydata.getMaincategory()) + "\n" +
											String.format("%-30s%-60s","Question Category:",missingcategorydata.getQuestionCategory()) + "\n" +
											String.format("%-30s", "Questions:")
									for(int k=0; k<missingcategorydata.getMissing_auditquestions().size(); k++){
										message = message+missingcategorydata.getMissing_auditquestions().get(k) + ",	"
									}
									message = message + "\n"+missingcategorydata.getMissing_auditquestions_errormessage() + "\n\n"
									flag = true
								}
								else{
									message = message+
											"\n\nQuestions:\n\n" +
											String.format("%-30s%-60s","Main Category:",missingcategorydata.getMaincategory()) + "\n" +
											String.format("%-30s%-60s","Question Category:",missingcategorydata.getQuestionCategory()) + "\n" +
											String.format("%-30s", "Questions:")
									for(int k=0; k<missingcategorydata.getMissing_auditquestions().size(); k++){
										message = message+missingcategorydata.getMissing_auditquestions().get(k) + ",	"
									}
									message = message + "\n"+missingcategorydata.getMissing_auditquestions_errormessage() + "\n\n"
								}
							}
							else{}
						}
					}
				}
				if(flag != false){
					message = message+"\n\n<-------------------------------------------------------------------------------------------------------------------------------------->\n\n"
					KeywordUtil.logInfo(message)
					message = ""
				}
				else{
				}
			}
		}
		message = "\n\n\n---------------------------------------------Visited Shop Data-----------------------------------------------------------------------------------------------------\n\n"+
				"<-------------------------------------------------------------------------------------------------------------------------------------->"
		for(int i=0; i<ProjectConstants.visitedshopdatainfo.size(); i++){
			VisitedShopDataInfo visitedshopdatainfo = ProjectConstants.visitedshopdatainfo.get(i)
			if(visitedshopdatainfo != null){
				message = message+"\n\n"+
						String.format("%-60s%-60s","Shop Name: "+visitedshopdatainfo.getShopname(),visitedshopdatainfo.getShopchannel())+"\n"+
						String.format("%-60s%-60s", "Visiting Scenarios: "+visitedshopdatainfo.getShop_scenario(),"Remark: "+visitedshopdatainfo.getRemark())+"\n\n"+
						String.format("%-40s%-100s", "Other Categories Visiting Scenarios:",visitedshopdatainfo.getOthercategories_scenarios())
				if(visitedshopdatainfo.getVisitedcategoriesdata() != null){
					for(int j=0; j< visitedshopdatainfo.getVisitedcategoriesdata().size(); j++){
						int chiller_count
						VisitedCategoryData visitedcategorydata = visitedshopdatainfo.getVisitedcategoriesdata().get(j)
						ArrayList<ProductCategoryWithProducts> productcategorywithproducts = visitedcategorydata.getProductcategorywithproducts()
						if(visitedcategorydata.getMaincategory().equalsIgnoreCase("Chiller Utilization")){
							message = message+ "\n\n" +
									String.format("%-30s%-130s", "Main Category:",visitedcategorydata.getMaincategory()) + "\n"
							for(int k=0; k< visitedcategorydata.getTaggedchillersremark().size(); k++){
								TaggedChillersRemark taggedchillerremarks = visitedcategorydata.getTaggedchillersremark().get(k)
								if(taggedchillerremarks.getVisitedchillerproductscategories() != null){
									if(chiller_count == null || chiller_count != taggedchillerremarks.getCount()){
										chiller_count = taggedchillerremarks.getCount()
										if(taggedchillerremarks.getFirstvisit_chillertype() == null){
											message = message + "\n\n" +
													String.format("%-100s","Overwrite Chiller "+taggedchillerremarks.getCount()+" ----------------------------------------------------------------------->")+"\n"+
													String.format("%-30s%-130s", "Chiller Type:",taggedchillerremarks.getOverwrite_chillertype()) + "\n" +
													String.format("%-30s%-130s", "Chiller Remark:",taggedchillerremarks.getOverwrite_chillerremark()) + "\n"
										}
										else{
											message = message+ "\n\n" +
													String.format("%-30s%-130s", "Chiller Type Scenarios:",taggedchillerremarks.getFirstvisit_chillertype()+"  ==>  "+taggedchillerremarks.getOverwrite_chillertype()) + "\n" +
													String.format("%-30s%-130s", "Chiller Remark Scenarios:",taggedchillerremarks.getFirstvisit_chillerremark()+"  ==>  "+taggedchillerremarks.getOverwrite_chillerremark()) + "\n"
										}
									}
									for(int m=0; m<taggedchillerremarks.getVisitedchillerproductscategories().size() ; m++){
										VisitedChillerProductsCategoryData visitedchillerproductcategory = taggedchillerremarks.getVisitedchillerproductscategories().get(m)
										if(visitedchillerproductcategory.getProductCategory() != null){
											if(taggedchillerremarks.getFirstvisit_chillerremark() != null){
												message = message + "\n\n" +
														String.format("%-30s%-60s", "Product Category:",visitedchillerproductcategory.getProductCategory()) + "\n" +
														String.format("%-50s%-12s%-19s%-11s%-22s%-29s%-21s", "Products:","Facing","Stock Taking/","Depth","Overwrite Facing","Overwrite Stock Taking/","Overwrite Depth")+"\n"+
														String.format("%-50s%-12s%-19s%-11s%-22s%-29s%-21s", "","","Stock Count","","","Overwrite Stock Count","")+"\n"
												for(int n=0; n<visitedchillerproductcategory.getShopproductsdata().size() ; n++){
													ShopProductsData shopproductsdata = visitedchillerproductcategory.getShopproductsdata().get(n)
													message = message + String.format("%-50s%-12s%-19s%-11s%-22s%-29s%-21s", shopproductsdata.getProduct(),shopproductsdata.getFacingdata(),shopproductsdata.getStocktakingdata_stockcountdata(),shopproductsdata.getDepthdata(),shopproductsdata.getOverwritefacingdata(),shopproductsdata.getOverwritestocktakingdata_stockcountdata(),shopproductsdata.getOverwritedepthdata())+"\n"
												}
											}
											else{
												message = message + "\n\n" +
														String.format("%-30s%-60s", "Product Category:",visitedchillerproductcategory.getProductCategory()) + "\n" +
														String.format("%-50s%-12s%-19s%-11s%-22s%-29s%-21s", "Products:","Facing","Stock Taking/","Depth","Overwrite Facing","Overwrite Stock Taking/","Overwrite Depth")+"\n"+
														String.format("%-50s%-12s%-19s%-11s%-22s%-29s%-21s", "","","Stock Count","","","Overwrite Stock Count","")+"\n"
												for(int n=0; n<visitedchillerproductcategory.getShopproductsdata().size() ; n++){
													ShopProductsData shopproductsdata = visitedchillerproductcategory.getShopproductsdata().get(n)
													message = message + String.format("%-50s%-12s%-19s%-11s%-22s%-29s%-21s", shopproductsdata.getProduct(),shopproductsdata.getFacingdata(),shopproductsdata.getStocktakingdata_stockcountdata(),shopproductsdata.getDepthdata(),shopproductsdata.getOverwritefacingdata(),shopproductsdata.getOverwritestocktakingdata_stockcountdata(),shopproductsdata.getOverwritedepthdata())+"\n"
												}
											}
										}
									}
								}
							}
						}
						else if(visitedcategorydata.getMaincategory().equalsIgnoreCase("Survey")){
							message = message+ "\n\n" +
									String.format("%-30s%-60s", "Main Category:",visitedcategorydata.getMaincategory()) + "\n\n"
							ArrayList<ProductCategoryWithProducts> productcategorywithproductslist = visitedcategorydata.getProductcategorywithproducts()
							for(int qc=0; qc< productcategorywithproductslist.size(); qc++){
								ProductCategoryWithProducts productcategorywithproduct = productcategorywithproductslist.get(qc)
								message = message +
										String.format("%-30s%-60s", "Question Category:",productcategorywithproduct.getProductcategory()) + "\n"
								ArrayList<QuestionsData> questions = productcategorywithproduct.getSurveyquestions()
								message = message +
										String.format("%-108s%-12s%-13s%-15s%-16s","Question","Option","Picture","Overwrite","Overwrite")+"\n"+
										String.format("%-108s%-12s%-13s%-15s%-16s","","","Status","Option","Picture Status")+"\n"
								for(int q=0; q< questions.size(); q++){
									QuestionsData question = questions.get(q)
									message = message +
											String.format("%-108s%-12s%-13s%-15s%-16s",question.getQuestion(),question.getQuestionoption(),question.getQuestionoption_takepicture(),question.getOverwrite_questionoption(),question.getOverwrite_questionoption_takepicture())+"\n"
								}
							}
						}
						else{
							message = message+ "\n\n" +
									String.format("%-30s%-60s", "Main Category:",visitedcategorydata.getMaincategory()) + "\n" +
									String.format("%-30s%-130s", "Scenarios:",visitedcategorydata.getFirstvisit_categoryremark()+"  ==>  "+visitedcategorydata.getOverwrite_categoryremark())
							if(productcategorywithproducts != null){
								for(int h=0; h< productcategorywithproducts.size(); h++){
									ProductCategoryWithProducts productcategorywithproduct = productcategorywithproducts.get(h)
									message = message + "\n\n" +
											String.format("%-30s%-60s", "Product Category:",productcategorywithproduct.getProductcategory()) + "\n" +
											String.format("%-50s%-28s%-28s%-28s%-28s", "Products:","Facing","Stock Taking","Overwrite Facing","Overwrite Stock Taking")+"\n"
									for(int k=0; k<productcategorywithproduct.getShopproductsdata().size() ; k++){
										ShopProductsData shopproductsdata = productcategorywithproduct.getShopproductsdata().get(k)
										message = message + String.format("%-50s%-28s%-28s%-28s%-28s", shopproductsdata.getProduct(),shopproductsdata.getFacingdata(),shopproductsdata.getStocktakingdata_stockcountdata(),shopproductsdata.getOverwritefacingdata(),shopproductsdata.getOverwritestocktakingdata_stockcountdata())+"\n"
									}
								}
							}
						}
						message = message + "\n" +
								String.format("%-32s%-100s%-32s", "","----------------------------------------------------------------------------------------------------","")+"\n"
					}
				}
				message = message + "\n\n<-------------------------------------------------------------------------------------------------------------------------------------->\n\n"
				KeywordUtil.logInfo(message)
				message = ""
			}
		}
	}
}
