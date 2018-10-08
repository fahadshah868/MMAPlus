package com.ct.qa.keywords

import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import java.awt.Image
import java.awt.Toolkit
import java.awt.image.BufferedImage
import java.awt.image.DataBuffer
import java.awt.image.PixelGrabber
import java.awt.image.Raster
import java.text.SimpleDateFormat

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
import com.ct.qa.constants.ProjectConstants
import io.appium.java_client.MobileElement

public class AdditionalPictureKeywords {
	@Keyword
	def takeMendatoryImages(){
		int images = ProjectConstants.DRIVER.findElementsByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.GridView[1]/*").size()
		for(int i=1; i<images; i++){
			MobileElement image = ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.GridView[1]/android.widget.RelativeLayout["+i+"]/android.widget.TextView[1]")
			String imagename = image.getText()
			if(imagename.equalsIgnoreCase("UHT Competition")){
				ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.GridView[1]/android.widget.RelativeLayout["+i+"]").click()
				Mobile.verifyElementExist(findTestObject("Object Repository/CommonScreenElements/Validate_CameraScreen" , [('package') : ProjectConstants.PACKAGENAME]), 0)
				Mobile.tap(findTestObject("Object Repository/CommonScreenElements/TakePictureButton" , [('package') : ProjectConstants.PACKAGENAME]), 0)
				Mobile.delay(5)
				Mobile.tap(findTestObject("Object Repository/CommonScreenElements/DoneButton" , [('package') : ProjectConstants.PACKAGENAME]), 0)
				break
			}
		}
		//		int index = 0
		//		final int[] image1Data = null
		//		File screenshot = ((TakesScreenshot)ProjectConstants.driver).getScreenshotAs(OutputType.FILE)
		//		BufferedImage fullImg = ImageIO.read(screenshot)
		//
		//		MobileElement expectedimageview = ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.GridView[1]/android.widget.RelativeLayout[1]/android.widget.ImageView[1]")
		//
		//		int imagewidth = expectedimageview.getSize().getWidth()
		//		int imageheight = expectedimageview.getSize().getHeight()
		//
		//		Point expectedimagepointer = expectedimageview.getLocation()
		//
		//		Image expectedimage = fullImg.getSubimage(expectedimagepointer.getX(), expectedimagepointer.getY(),imagewidth, imageheight)
		//
		//		PixelGrabber grabImage1Pixels = new PixelGrabber(expectedimage, 0, 0, -1,-1, false)
		//
		//		if (grabImage1Pixels.grabPixels()) {
		//			int width = grabImage1Pixels.getWidth()
		//			int height = grabImage1Pixels.getHeight()
		//			image1Data = new int[width * height]
		//			image1Data = (int[]) grabImage1Pixels.getPixels()
		//		}
		//
		//		try {
		//			ArrayList<MobileElement> images = ProjectConstants.DRIVER.findElementsByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.GridView[1]/*")
		//			for(int i=1; i<=(images.size()-2); i++){
		//				MobileElement actualimageview = ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.GridView[1]/android.widget.RelativeLayout["+i+"]/android.widget.ImageView[1]")
		//
		//				Point actualimagepointer = actualimageview.getLocation()
		//
		//				Image actualimage = fullImg.getSubimage(actualimagepointer.getX(), actualimagepointer.getY(),imagewidth, imageheight)
		//
		//				final PixelGrabber grabImage2Pixels = new PixelGrabber(actualimage, 0, 0, -1,-1, false)
		//				int[] image2Data = null
		//				if (grabImage2Pixels.grabPixels()) {
		//					int width = grabImage2Pixels.getWidth()
		//					int height = grabImage2Pixels.getHeight()
		//					image2Data = new int[width * height]
		//					image2Data = (int[]) grabImage2Pixels.getPixels()
		//				}
		//
		//				if(!java.util.Arrays.equals(image1Data, image2Data)){
		//					ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.GridView[1]/android.widget.RelativeLayout["+i+"]").click()
		//					Mobile.verifyElementExist(findTestObject("Object Repository/CommonScreenElements/Validate_CameraScreen" , [('package') : ProjectConstants.PACKAGENAME]), 0)
		//					Mobile.tap(findTestObject("Object Repository/CommonScreenElements/TakePictureButton" , [('package') : ProjectConstants.PACKAGENAME]), 0)
		//					Mobile.waitForElementPresent(findTestObject("Object Repository/CommonScreenElements/DoneButton" , [('package') : ProjectConstants.PACKAGENAME]), 0)
		//					Mobile.tap(findTestObject("Object Repository/CommonScreenElements/DoneButton" , [('package') : ProjectConstants.PACKAGENAME]), 0)
		//					break
		//				}
		//			}
		//			images = ProjectConstants.DRIVER.findElementsByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.GridView[1]/*")
		//		}
		//		catch (InterruptedException e1) {
		//			e1.printStackTrace()
		//		}
		//		//		Mobile.swipe(0, 370, 0,200)
		//		//		while(true){
		//		//			index = ProjectConstants.DRIVER.findElementsByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.GridView[1]/*").size()
		//		//			MobileElement productbeforeswipe = ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.GridView[1]/android.widget.RelativeLayout["+index+"]/android.widget.TextView[1]")
		//		//			String productnamebeforeswipe = productbeforeswipe.getText()
		//		//			Mobile.swipe(0, 540, 0, 200)
		//		//			MobileElement productafterswipe = ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.GridView[1]/android.widget.RelativeLayout["+index+"]/android.widget.TextView[1]")
		//		//			String productnameafterswipe = productafterswipe.getText()
		//		//			if(productnamebeforeswipe.equals(productnameafterswipe)){
		//		//				Mobile.swipe(0, 500, 0, 200)
		//		//				if(index == 6){
		//		//					index = index - 2
		//		//				}
		//		//				else{
		//		//					index = index - 1
		//		//				}
		//		//				for(int j=3; j<=index; j++){
		//		//					MobileElement actualimageview = ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.GridView[1]/android.widget.RelativeLayout["+j+"]/android.widget.ImageView[1]")
		//		//
		//		//					Point actualimagepointer = actualimageview.getLocation()
		//		//
		//		//					Image actualimage = fullImg.getSubimage(actualimagepointer.getX(), actualimagepointer.getY(),imagewidth, imageheight)
		//		//
		//		//					PixelGrabber grabImage2Pixels = new PixelGrabber(actualimage, 0, 0, -1,-1, false)
		//		//					int[] image2Data = null
		//		//					if (grabImage2Pixels.grabPixels()) {
		//		//						int width = grabImage2Pixels.getWidth()
		//		//						int height = grabImage2Pixels.getHeight()
		//		//						image2Data = new int[width * height]
		//		//						image2Data = (int[]) grabImage2Pixels.getPixels()
		//		//					}
		//		//
		//		//					if(!java.util.Arrays.equals(image1Data, image2Data)){
		//		//						ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.GridView[1]/android.widget.RelativeLayout["+j+"]").click()
		//		//						Mobile.verifyElementExist(findTestObject("Object Repository/CommonScreenElements/Validate_CameraScreen" , [('package') : ProjectConstants.PACKAGENAME]), 0)
		//		//						Mobile.tap(findTestObject("Object Repository/CommonScreenElements/TakePictureButton" , [('package') : ProjectConstants.PACKAGENAME]), 0)
		//		//						Mobile.waitForElementPresent(findTestObject("Object Repository/CommonScreenElements/DoneButton" , [('package') : ProjectConstants.PACKAGENAME]), 0)
		//		//						Mobile.tap(findTestObject("Object Repository/CommonScreenElements/DoneButton" , [('package') : ProjectConstants.PACKAGENAME]), 0)
		//		//					}
		//		//				}
		//		//				break
		//		//			}
		//		//			else{
		//		//				if(index == 6){
		//		//					index = index - 2
		//		//				}
		//		//				else{
		//		//					index = index - 1
		//		//				}
		//		//				for(int j=3; j<=index; j++){
		//		//					MobileElement actualimageview = ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.GridView[1]/android.widget.RelativeLayout["+j+"]/android.widget.ImageView[1]")
		//		//
		//		//					Point actualimagepointer = actualimageview.getLocation()
		//		//
		//		//					Image actualimage = fullImg.getSubimage(actualimagepointer.getX(), actualimagepointer.getY(),imagewidth, imageheight)
		//		//
		//		//					PixelGrabber grabImage2Pixels = new PixelGrabber(actualimage, 0, 0, -1,-1, false)
		//		//					int[] image2Data = null
		//		//					if (grabImage2Pixels.grabPixels()) {
		//		//						int width = grabImage2Pixels.getWidth()
		//		//						int height = grabImage2Pixels.getHeight()
		//		//						image2Data = new int[width * height]
		//		//						image2Data = (int[]) grabImage2Pixels.getPixels()
		//		//					}
		//		//
		//		//					if(!java.util.Arrays.equals(image1Data, image2Data)){
		//		//						ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.GridView[1]/android.widget.RelativeLayout["+j+"]").click()
		//		//						Mobile.verifyElementExist(findTestObject("Object Repository/CommonScreenElements/Validate_CameraScreen" , [('package') : ProjectConstants.PACKAGENAME]), 0)
		//		//						Mobile.tap(findTestObject("Object Repository/CommonScreenElements/TakePictureButton" , [('package') : ProjectConstants.PACKAGENAME]), 0)
		//		//						Mobile.waitForElementPresent(findTestObject("Object Repository/CommonScreenElements/DoneButton" , [('package') : ProjectConstants.PACKAGENAME]), 0)
		//		//						Mobile.tap(findTestObject("Object Repository/CommonScreenElements/DoneButton" , [('package') : ProjectConstants.PACKAGENAME]), 0)
		//		//					}
		//		//				}
		//		//			}
		//		//		}
	}
}
