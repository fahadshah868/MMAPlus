import com.kms.katalon.core.logging.KeywordLogger
import com.kms.katalon.core.exception.StepFailedException
import com.kms.katalon.core.reporting.ReportUtil
import com.kms.katalon.core.main.TestCaseMain
import com.kms.katalon.core.testdata.TestDataColumn
import groovy.lang.MissingPropertyException
import com.kms.katalon.core.testcase.TestCaseBinding
import com.kms.katalon.core.driver.internal.DriverCleanerCollector
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.configuration.RunConfiguration
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import internal.GlobalVariable as GlobalVariable

Map<String, String> suiteProperties = new HashMap<String, String>();


suiteProperties.put('id', 'Test Suites/MMA Plus Merchandising')

suiteProperties.put('name', 'MMA Plus Merchandising')

suiteProperties.put('description', '')
 

DriverCleanerCollector.getInstance().addDriverCleaner(new com.kms.katalon.core.webui.contribution.WebUiDriverCleaner())
DriverCleanerCollector.getInstance().addDriverCleaner(new com.kms.katalon.core.mobile.contribution.MobileDriverCleaner())



RunConfiguration.setExecutionSettingFile("F:\\Git Projects\\MMAPlus_Merchandising\\MMAPlus_Merchandising\\Reports\\MMA Plus Merchandising\\20180627_182347\\execution.properties")

TestCaseMain.beforeStart()

TestCaseMain.startTestSuite('Test Suites/MMA Plus Merchandising', suiteProperties, [new TestCaseBinding('Test Cases/LaunchingApp/LaunchApp', 'Test Cases/LaunchingApp/LaunchApp',  null), new TestCaseBinding('Test Cases/DistributionPoint/VisitDistributionPoint', 'Test Cases/DistributionPoint/VisitDistributionPoint',  null), new TestCaseBinding('Test Cases/ShopWithChiller/ChooseShopForShopClosed', 'Test Cases/ShopWithChiller/ChooseShopForShopClosed',  null), new TestCaseBinding('Test Cases/ShopClosed/VisitShopClosed', 'Test Cases/ShopClosed/VisitShopClosed',  null), new TestCaseBinding('Test Cases/ShopWithChiller/ChooseShopForShopOpen', 'Test Cases/ShopWithChiller/ChooseShopForShopOpen',  null), new TestCaseBinding('Test Cases/ShopOpen/VisitShopOpenWithChillerNotAllocated', 'Test Cases/ShopOpen/VisitShopOpenWithChillerNotAllocated',  null), new TestCaseBinding('Test Cases/ShopOpen/VisitShopProductsWithChillerNotAllocated', 'Test Cases/ShopOpen/VisitShopProductsWithChillerNotAllocated',  null)])
