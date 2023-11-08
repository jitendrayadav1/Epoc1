package com.E_POC2.TestCases;


import static org.testng.Assert.assertEquals;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import com.E_POC2.DataBaseConfiguration.DataBaseConnection;
import com.E_POC2.PageObject.PowerBIDashBoard;
import com.E_POC2.TestBase.TestBase;
import com.E_POC2.TestBase.TestDataManipulation;
import com.E_POC2.Utility.Demo_ExtentReport;
import com.E_POC2.Utility.EmailOfTestExecution;
import com.E_POC2.Utility.JiraPolicy;
import com.E_POC2.Utility.ReadFromExcel;
import com.E_POC2.Utility.setResultToExcel;

public class PowerBIDashBoardTest1 extends TestBase{

	DataBaseConnection dbCon=new DataBaseConnection();

    public 	static List<String> dropDownHeading=new ArrayList<>();
    public static List<String> testData=new ArrayList<>();
    public static List<String> dropDownData=new ArrayList<>();
    public static List<String> prevdropDownData=new ArrayList<>();

    public static List<String> vNamesList=new ArrayList<>();
    public static List<String> vDataList=new ArrayList<>();
    public static List<String> vXpathHeading=new ArrayList<>();
    public static List<String> vDataXpath=new ArrayList<>();

/*	
	static List<String> lowAttendanceNamesList=new ArrayList<>();
	static List<String> lowAttendanceperList=new ArrayList<>();
	static List<String> lowAttendanceXpathHeading=new ArrayList<>();
	static List<String> lowAttendanceDataXpath=new ArrayList<>();

	static List<String> gridHeadingList=new ArrayList<>();
	static List<String> gridContentList=new ArrayList<>();
	static List<String> gridXpathHeading=new ArrayList<>();
	static List<String> gridDataXpath=new ArrayList<>();

	static List<String> charHeadingList=new ArrayList<>();
	static List<String> charContentList=new ArrayList<>();
	static List<String> charXpathHeading=new ArrayList<>();
	static List<String> charDataXpath=new ArrayList<>();
	
	static List<String> MarksHeadingList=new ArrayList<>();
	static List<String> MarksContentList=new ArrayList<>();
	static List<String> MarksXpathHeading=new ArrayList<>();
	static List<String> MarksDataXpath=new ArrayList<>();
*/

	public static List<String> queryDBDataList=new ArrayList<>();
	public static List<String> queryHeading=new ArrayList<>();
	public static List<String> queriesList=new ArrayList<>();
	public static HashMap<String,String> dashBoard_DataPerVisuals=new HashMap<String,String>();
	public static HashMap<String,String> dataBase_DataPerVisuals=new HashMap<String,String>();


	static int dataColCount=0;
	static Object[][] data1;
	static Object[][] data;

	@JiraPolicy(logTicketReady = true)
	@Test
	public void loginAndDropDownSelection() throws InterruptedException, ClassNotFoundException, SQLException, IOException {

		Demo_ExtentReport e = new Demo_ExtentReport();

		//  dbCon.dBConnection();

		data=ReadFromExcel.readExcelData(config.getTestCasesSheet());

		//	testData=TestDataManipulation.getSortedTestDataOfExcel(data,3);

		/*dataColCount=testData.get(1).split(">").length;
		System.out.println("data.length : "+data.length);
		data1=new Object[dataColCount][data.length-1];*/

		driver.get(baseUrl);

		PowerBIDashBoard pBIDashBoard=new PowerBIDashBoard(driver);

		Thread.sleep(3000);
		pBIDashBoard.getEmailId().sendKeys(config.getEmail());
		pBIDashBoard.getEmailIdFrameSubmit().click();

		Thread.sleep(6000);
		pBIDashBoard.getPassword().sendKeys(config.getPassword());
		pBIDashBoard.getPasswordAndStaySignInButton().click();
		Thread.sleep(3000);
		pBIDashBoard.getPasswordAndStaySignInButton().click();

		Thread.sleep(6000);
		Thread.sleep(6000);

		
	}
	
	@JiraPolicy(logTicketReady = true)
	@Test(dependsOnMethods ="loginAndDropDownSelection")
	public void monthPerformance() throws InterruptedException, ClassNotFoundException, SQLException, IOException
	{
		//getRespectiveTestDataList1(testData);
		System.out.println("into month test case..............");
		
		TestDataManipulation.prevDataClearanceFromList();
		
		//TestDataManipulation.testDataOperation( data, 3, 0, dropDownHeading, dropDownData);
		//TestBase.dropDownIteration(dropDownHeading,dropDownData,prevdropDownData);
		
	
		//TestDataManipulation.testDataOperation( data, 4, 0, vXpathHeading, vDataXpath);
				
		System.out.println("Month Performance........");
		String monthName="svg[name$='Line and stacked column chart'] text[class='setFocusRing']";
		String monthPer="svg[name$='Line and stacked column chart'] text[class='label']";
		
		lineAndStackedOrStackedBarChart( vNamesList, vDataList, monthName, monthPer);
		

		dashBoard_DataPerVisuals=TestDataManipulation.listToHashMap(vNamesList, vDataList);

		System.out.println("MnList : "+vNamesList);
		System.out.println("PerList : "+vDataList);
		
		TestDataManipulation.testDataOperation( data, 5, 0, queryHeading, queriesList);
		
		dbCon.dBConnection(queriesList.get(0),"attendance_month","attendance");		
		System.out.println("Sorted List : "+DataBaseConnection.visualNamesList);
	   dataBase_DataPerVisuals=TestDataManipulation.listToHashMap(DataBaseConnection.visualNamesList, DataBaseConnection.visualDataList);

	   executionResult_setToExcel(dashBoard_DataPerVisuals, dataBase_DataPerVisuals, 1, 7, 8, 9 );
	   assertEquals(dashBoard_DataPerVisuals,dataBase_DataPerVisuals);
	  

		System.out.println("Assert Successfull.....");

	}
	

	@JiraPolicy(logTicketReady = true)
	@Test(dependsOnMethods ="loginAndDropDownSelection",priority = 1)
	public void lowAttendancePerformance() throws InterruptedException, ClassNotFoundException, SQLException, IOException
	{
		
		
		TestDataManipulation.prevDataClearanceFromList();
		
		TestDataManipulation.testDataOperation( data, 3, 1, dropDownHeading, dropDownData);	
		TestBase.dropDownIteration(dropDownHeading,dropDownData,prevdropDownData);

		TestDataManipulation.testDataOperation( data, 4, 1, vXpathHeading, vDataXpath);
		System.out.println("Low Attendance Performance........");
		lineAndStackedOrStackedBarChart( vNamesList, vDataList, vDataXpath.get(0), vDataXpath.get(1));

		dashBoard_DataPerVisuals=TestDataManipulation.listToHashMap(vNamesList, vDataList);

		System.out.println("lowAttendanceNamesList : "+vNamesList);
		System.out.println("lowAttendanceperList : "+vDataList);

		TestDataManipulation.testDataOperation( data, 5, 1, queryHeading, queriesList);
		dbCon.dBConnection(queriesList.get(0),"Attendance_Remarks","Percentage");
		
		System.out.println("Sorted List : "+DataBaseConnection.visualNamesList);
	   dataBase_DataPerVisuals=TestDataManipulation.listToHashMap(DataBaseConnection.visualNamesList, DataBaseConnection.visualDataList);

	   executionResult_setToExcel(dashBoard_DataPerVisuals, dataBase_DataPerVisuals, 2, 7, 8, 9 );
	   assertEquals(dashBoard_DataPerVisuals,dataBase_DataPerVisuals);
		

	}
	
	@JiraPolicy(logTicketReady = true)
	@Test(dependsOnMethods ="loginAndDropDownSelection",priority = 2)
	public void GirdPerformance() throws InterruptedException, ClassNotFoundException, SQLException, IOException
	{
		
		TestDataManipulation.prevDataClearanceFromList();
	
		TestDataManipulation.testDataOperation( data, 3, 2, dropDownHeading, dropDownData);
		TestBase.dropDownIteration(dropDownHeading,dropDownData,prevdropDownData);
			
		TestDataManipulation.testDataOperation( data, 4, 2, vXpathHeading, vDataXpath);
		
		System.out.println("Grid Performance........");
		TestBase.readGridData(vNamesList, vDataList, vDataXpath.get(0),vDataXpath.get(1));
		
		dashBoard_DataPerVisuals=TestDataManipulation.listToHashMap(vNamesList, vDataList);
		System.out.println("dashBoard_DataPerVisuals : "+dashBoard_DataPerVisuals);

		
		TestDataManipulation.testDataOperation( data, 5, 2, queryHeading, queriesList);		
		dbCon.dBConnection(queriesList.get(0),"Student_Name","Subject_Name","Marks_Obtained","Total_Marks",
				"Attendance_percentage","Remark","Enrollment_No");
		
		System.out.println("Sorted List : "+DataBaseConnection.visualNamesList);
		dataBase_DataPerVisuals=TestDataManipulation.listToHashMap(DataBaseConnection.visualNamesList, DataBaseConnection.visualDataList);
		System.out.println("dataBase_DataPerVisuals : "+dataBase_DataPerVisuals);

		executionResult_setToExcel(dashBoard_DataPerVisuals, dataBase_DataPerVisuals, 3, 7, 8, 9 );
		assertEquals(dashBoard_DataPerVisuals,dataBase_DataPerVisuals);
		
		

	}

	@JiraPolicy(logTicketReady = true)
	@Test(dependsOnMethods ="loginAndDropDownSelection",priority = 3)
	public void characteristicPerformance() throws InterruptedException, ClassNotFoundException, SQLException, IOException
	{
		
		
		TestDataManipulation.prevDataClearanceFromList();
	
		TestDataManipulation.testDataOperation( data, 3, 3, dropDownHeading, dropDownData);
		TestBase.dropDownIteration(dropDownHeading,dropDownData,prevdropDownData);
		
		TestDataManipulation.testDataOperation( data, 4, 3, vXpathHeading, vDataXpath);
		System.out.println("Char Performance........");
		
		Thread.sleep(3000);
		List<String> charListCmp=readDashBoardCharacteristicData( vNamesList, vDataList,  vDataXpath.get(0), vDataXpath.get(1));
		
		dashBoard_DataPerVisuals=TestDataManipulation.listToHashMap(vNamesList, vDataList);
		System.out.println("dashBoard_DataPerVisuals : "+dashBoard_DataPerVisuals);
 
		TestDataManipulation.testDataOperation( data, 5, 3, queryHeading, queriesList);
		dbCon.dBConnection(queriesList.get(0),"Exam_Remark");

		System.out.println("Sorted List : "+DataBaseConnection.visualNamesList);

		readDataBaseCharacteristicData(charListCmp);
		
		dataBase_DataPerVisuals=TestDataManipulation.listToHashMap(DataBaseConnection.visualNamesList, DataBaseConnection.visualDataList);
		System.out.println("dataBase_DataPerVisuals : "+dataBase_DataPerVisuals);

		executionResult_setToExcel(dashBoard_DataPerVisuals, dataBase_DataPerVisuals, 4, 7, 8, 9 );	
		assertEquals(dashBoard_DataPerVisuals,dataBase_DataPerVisuals);

	}

	@JiraPolicy(logTicketReady = true)
	@Test(dependsOnMethods ="loginAndDropDownSelection",priority = 4)
	public void Marks_Obtained() throws InterruptedException, ClassNotFoundException, SQLException, IOException
	{
		TestDataManipulation.prevDataClearanceFromList();
		
		TestDataManipulation.testDataOperation( data, 3, 4, dropDownHeading, dropDownData);
		TestBase.dropDownIteration(dropDownHeading,dropDownData,prevdropDownData);
		
		TestDataManipulation.testDataOperation( data, 4, 4, vXpathHeading, vDataXpath);
		System.out.println("Marks Obtained Performance........");
		
		Thread.sleep(3000);
		System.out.println("VdataxpathMarks : "+vDataXpath.get(0)); 
		upperVisualData( vNamesList, vDataList, "Marks_Obtained", vDataXpath.get(0));
		
		dashBoard_DataPerVisuals=TestDataManipulation.listToHashMap(vNamesList, vDataList);
		
		System.out.println("dashBoard_DataPerVisuals : "+dashBoard_DataPerVisuals);
 
		TestDataManipulation.testDataOperation( data, 5, 4, queryHeading, queriesList);
		dbCon.dBConnection(queriesList.get(0),"Marks_Obtained");

		System.out.println("Sorted List : "+DataBaseConnection.visualNamesList);

		//readDataBaseCharacteristicData(charListCmp);
		
		dataBase_DataPerVisuals=TestDataManipulation.listToHashMap(DataBaseConnection.visualNamesList, DataBaseConnection.visualDataList);
		System.out.println("dataBase_DataPerVisuals : "+dataBase_DataPerVisuals);

		executionResult_setToExcel(dashBoard_DataPerVisuals, dataBase_DataPerVisuals, 5, 7, 8, 9 );	
		assertEquals(dashBoard_DataPerVisuals,dataBase_DataPerVisuals);

		
	}
	
	@JiraPolicy(logTicketReady = true)
	@Test(dependsOnMethods ="loginAndDropDownSelection",priority = 5)
	public void total_Marks() throws InterruptedException, ClassNotFoundException, SQLException, IOException
	{
		TestDataManipulation.prevDataClearanceFromList();
		
		TestDataManipulation.testDataOperation( data, 3, 5, dropDownHeading, dropDownData);
		TestBase.dropDownIteration(dropDownHeading,dropDownData,prevdropDownData);
		
		TestDataManipulation.testDataOperation( data, 4, 5, vXpathHeading, vDataXpath);
		System.out.println("total Marks Performance........");
		
		Thread.sleep(3000);
		System.out.println("VdataxpathMarks : "+vDataXpath.get(0)); 
		upperVisualData( vNamesList, vDataList, "Total_Marks", vDataXpath.get(0));
		
		dashBoard_DataPerVisuals=TestDataManipulation.listToHashMap(vNamesList, vDataList);
		
		System.out.println("dashBoard_DataPerVisuals : "+dashBoard_DataPerVisuals);
 
		TestDataManipulation.testDataOperation( data, 5, 5, queryHeading, queriesList);
		dbCon.dBConnection(queriesList.get(0),"Total_Marks");

		System.out.println("Sorted List : "+DataBaseConnection.visualNamesList);

		//readDataBaseCharacteristicData(charListCmp);
		
		dataBase_DataPerVisuals=TestDataManipulation.listToHashMap(DataBaseConnection.visualNamesList, DataBaseConnection.visualDataList);
		System.out.println("dataBase_DataPerVisuals : "+dataBase_DataPerVisuals);

		executionResult_setToExcel(dashBoard_DataPerVisuals, dataBase_DataPerVisuals, 6, 7, 8, 9 );	
		assertEquals(dashBoard_DataPerVisuals,dataBase_DataPerVisuals);

		
	}
	
	@JiraPolicy(logTicketReady = true)
	@Test(dependsOnMethods ="loginAndDropDownSelection",priority = 6)
	public void marks_Percentage() throws InterruptedException, ClassNotFoundException, SQLException, IOException
	{
		TestDataManipulation.prevDataClearanceFromList();
		
		TestDataManipulation.testDataOperation( data, 3, 6, dropDownHeading, dropDownData);
		TestBase.dropDownIteration(dropDownHeading,dropDownData,prevdropDownData);
		
		TestDataManipulation.testDataOperation( data, 4, 6, vXpathHeading, vDataXpath);
		System.out.println("Marks Percentage Performance........");
		
		Thread.sleep(3000);
		System.out.println("VdataxpathMarks : "+vDataXpath.get(0)); 
		upperVisualData( vNamesList, vDataList, "Percentage", vDataXpath.get(0));
		
		System.out.println("vNamsList : "+vNamesList);
		System.out.println("vDataList : "+vDataList);
		
		dashBoard_DataPerVisuals=TestDataManipulation.listToHashMap(vNamesList, vDataList);
		
		System.out.println("dashBoard_DataPerVisuals : "+dashBoard_DataPerVisuals);
 
		TestDataManipulation.testDataOperation( data, 5, 6, queryHeading, queriesList);
		dbCon.dBConnection(queriesList.get(0),"Percentage");

		System.out.println("Sorted List : "+DataBaseConnection.visualNamesList);

		//readDataBaseCharacteristicData(charListCmp);
		
		dataBase_DataPerVisuals=TestDataManipulation.listToHashMap(DataBaseConnection.visualNamesList, DataBaseConnection.visualDataList);
		System.out.println("dataBase_DataPerVisuals : "+dataBase_DataPerVisuals);

		executionResult_setToExcel(dashBoard_DataPerVisuals, dataBase_DataPerVisuals, 7, 7, 8, 9 );	
		assertEquals(dashBoard_DataPerVisuals,dataBase_DataPerVisuals);

		
	}
	
	@JiraPolicy(logTicketReady = true)
	@Test(dependsOnMethods ="loginAndDropDownSelection",priority = 7)
	public void attendance_Percentage() throws InterruptedException, ClassNotFoundException, SQLException, IOException
	{
		TestDataManipulation.prevDataClearanceFromList();
		
		TestDataManipulation.testDataOperation( data, 3, 7, dropDownHeading, dropDownData);
		TestBase.dropDownIteration(dropDownHeading,dropDownData,prevdropDownData);
		
		TestDataManipulation.testDataOperation( data, 4, 7, vXpathHeading, vDataXpath);
		System.out.println("Ateendance Percentage Performance........");
		
		Thread.sleep(3000);
		System.out.println("VdataxpathMarks : "+vDataXpath.get(0)); 
		upperVisualData( vNamesList, vDataList, "Attendance_Percentage", vDataXpath.get(0));
		
		System.out.println("vNamsList : "+vNamesList);
		System.out.println("vDataList : "+vDataList);
		
		dashBoard_DataPerVisuals=TestDataManipulation.listToHashMap(vNamesList, vDataList);
		
		System.out.println("dashBoard_DataPerVisuals : "+dashBoard_DataPerVisuals);
 
		TestDataManipulation.testDataOperation( data, 5, 7, queryHeading, queriesList);
		dbCon.dBConnection(queriesList.get(0),"Attendance_Percentage");

		System.out.println("Sorted List : "+DataBaseConnection.visualNamesList);

		//readDataBaseCharacteristicData(charListCmp);
		
		dataBase_DataPerVisuals=TestDataManipulation.listToHashMap(DataBaseConnection.visualNamesList, DataBaseConnection.visualDataList);
		System.out.println("dataBase_DataPerVisuals : "+dataBase_DataPerVisuals);

		executionResult_setToExcel(dashBoard_DataPerVisuals, dataBase_DataPerVisuals, 8, 7, 8, 9 );	
		assertEquals(dashBoard_DataPerVisuals,dataBase_DataPerVisuals);

		
	}
	
	@JiraPolicy(logTicketReady = true)
	@Test(dependsOnMethods ="loginAndDropDownSelection",priority = 8)
	public void Group_Marking() throws InterruptedException, ClassNotFoundException, SQLException, IOException
	{
		TestDataManipulation.prevDataClearanceFromList();
		
		TestDataManipulation.testDataOperation( data, 3, 8, dropDownHeading, dropDownData);
		TestBase.dropDownIteration(dropDownHeading,dropDownData,prevdropDownData);
		
		TestDataManipulation.testDataOperation( data, 4, 8, vXpathHeading, vDataXpath);
		System.out.println("Group Marking Performance........");
		
		Thread.sleep(3000);
		System.out.println("VdataxpathMarks : "+vDataXpath.get(0)); 
		upperVisualData( vNamesList, vDataList, "Group_student", vDataXpath.get(0));
		
		System.out.println("vNamsList : "+vNamesList);
		System.out.println("vDataList : "+vDataList);
		
		dashBoard_DataPerVisuals=TestDataManipulation.listToHashMap(vNamesList, vDataList);
		
		System.out.println("dashBoard_DataPerVisuals : "+dashBoard_DataPerVisuals);
 
		TestDataManipulation.testDataOperation( data, 5, 8, queryHeading, queriesList);
		dbCon.dBConnection(queriesList.get(0),"Group_student");

		System.out.println("Sorted List : "+DataBaseConnection.visualNamesList);

		//readDataBaseCharacteristicData(charListCmp);
		
		dataBase_DataPerVisuals=TestDataManipulation.listToHashMap(DataBaseConnection.visualNamesList, DataBaseConnection.visualDataList);
		System.out.println("dataBase_DataPerVisuals : "+dataBase_DataPerVisuals);

		executionResult_setToExcel(dashBoard_DataPerVisuals, dataBase_DataPerVisuals, 9, 7, 8, 9 );	
		assertEquals(dashBoard_DataPerVisuals,dataBase_DataPerVisuals);

		
	}
	


}
