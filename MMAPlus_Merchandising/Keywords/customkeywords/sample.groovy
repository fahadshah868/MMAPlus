package customkeywords

import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import java.awt.List
import org.apache.poi.ss.formula.functions.Column
import org.apache.poi.ss.usermodel.Row
import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.checkpoint.Checkpoint
import com.kms.katalon.core.checkpoint.CheckpointFactory
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testcase.TestCase
import com.kms.katalon.core.testcase.TestCaseFactory
import com.kms.katalon.core.testdata.TestData
import com.kms.katalon.core.testdata.TestDataColumn
import com.kms.katalon.core.testdata.TestDataFactory
import com.kms.katalon.core.testobject.ObjectRepository
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords
import com.sun.media.ui.ColumnData

import internal.GlobalVariable

import MobileBuiltInKeywords as Mobile
import WSBuiltInKeywords as WS
import WebUiBuiltInKeywords as WebUI

public class sample {

	public static TestData abcd = findTestData("Data Files/sample")

	@Keyword
	def samplee(){
		ArrayList<String> data1 = abcd.getColumnNames()
		String check = ""
		int tt = abcd.getRowNumbers()
		for(int i=0; i<data1.size(); i++){
			String abc = data1.get(i)
			if(abc.equals("Values")){
				for(int j=1; j<=tt; j++){
					check = abcd.getObjectValue(i+1, j)
					if(abcd.getObjectValue(i+1, j) != ""){
						Mobile.delay(2)
					}
					else{
						break
					}
				}
			}
		}
	}
}
