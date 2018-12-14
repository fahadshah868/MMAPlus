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


suiteProperties.put('id', 'Test Suites/MMA Merchandising With Category Level Overwrite Scenarios')

suiteProperties.put('name', 'MMA Merchandising With Category Level Overwrite Scenarios')

suiteProperties.put('description', '')
 

DriverCleanerCollector.getInstance().addDriverCleaner(new com.kms.katalon.core.webui.contribution.WebUiDriverCleaner())
DriverCleanerCollector.getInstance().addDriverCleaner(new com.kms.katalon.core.mobile.contribution.MobileDriverCleaner())
DriverCleanerCollector.getInstance().addDriverCleaner(new com.kms.katalon.core.cucumber.keyword.internal.CucumberDriverCleaner())



RunConfiguration.setExecutionSettingFile("F:\\Git Projects\\MMA_Merchandising\\MMA_Merchandising\\Reports\\MMA Merchandising With Category Level Overwrite Scenarios\\20181214_171729\\execution.properties")

TestCaseMain.beforeStart()

TestCaseMain.startTestSuite('Test Suites/MMA Merchandising With Category Level Overwrite Scenarios', suiteProperties, [new TestCaseBinding('Test Cases/LaunchingApp/LaunchApp', 'Test Cases/LaunchingApp/LaunchApp',  null), new TestCaseBinding('Test Cases/DistributionPoint/VisitDistributionPoint', 'Test Cases/DistributionPoint/VisitDistributionPoint',  null), new TestCaseBinding('Test Cases/VisitShopScenarios/VisitShopsWithCategoryLevelOverwriting', 'Test Cases/VisitShopScenarios/VisitShopsWithCategoryLevelOverwriting',  null)])
