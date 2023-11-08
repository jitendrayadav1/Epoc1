package com.E_POC2.TestBase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.E_POC2.TestCases.PowerBIDashBoardTest1;

public class TestDataManipulation {

	static List<String> testData=new ArrayList<>();
	static List<String> tList=new ArrayList<>();
	static List<String> tHeading=new ArrayList<>();
	static List<String> tTestData=new ArrayList<>();
	static List<String> prevTestData=new ArrayList<>();
	static int dataColCount=0;
	static Object[][] data1;


	public static List<String> getSortedTestDataOfExcel(Object[][] data,int colNum)
	{
		int count=0;
		int col=3;
		int rowcount=1;
	

		for(int i=1;i<data.length;i++)
		{
			for(int j=0;j<data[i].length;j++)
			{  
				if(j==colNum) {
					System.out.println("i : "+i+" j : "+j);
					System.out.println("Object Data : "+data[i][j]);
					testData.add((String)data[i][j]);
					break;
				}
				/*if(j==colNum) {
					System.out.println("i : "+i+" j : "+j);
					System.out.println("Object Data : "+data[i][j]);
					testData.add((String)data[i][j]);
					break;
				}*/
			}
			//System.out.println("i : "+i+" col : "+col);
			//System.out.println("Object Data : "+data[i][col]);
			//testData.add((String)data[i][col]);

		}
		/*
		dataColCount=testData.get(1).split(">").length;
		System.out.println("data.length : "+data.length);
		data1=new Object[dataColCount][data.length-1];*/
	//	getRespectiveTestDataList(testData,data1);
		return testData;

	}
	
	public static void prevDataClearanceFromList()	
	{
		PowerBIDashBoardTest1.dropDownHeading.clear();
		PowerBIDashBoardTest1.dropDownData.clear();
		PowerBIDashBoardTest1.queryHeading.clear();
		PowerBIDashBoardTest1.queryDBDataList.clear();
		
		PowerBIDashBoardTest1.vNamesList.clear();
		PowerBIDashBoardTest1.vDataList.clear();
		PowerBIDashBoardTest1.vXpathHeading.clear();
		PowerBIDashBoardTest1.vDataXpath.clear();	
		
		PowerBIDashBoardTest1.dashBoard_DataPerVisuals.clear();
		PowerBIDashBoardTest1.dataBase_DataPerVisuals.clear();
		
		
	}
		

	public static void getRespectiveTestDataList1(List<String> testData,int testCaseRowNum,List<String> headingList,List<String> valueList) throws InterruptedException
	{
		int count=0;
		valueList.clear();
		headingList.clear();
		String dropName;
		//String str1=testDataStr.replace("<", "");
		String[] str=testData.get(testCaseRowNum).split(">>");

		System.out.println("Str.lenght : "+str.length);

		for(int i=0;i<str.length;i++)
		{
			System.out.println("String data : "+str[i]);
			String[] str11=str[i].split("=<<");
			//getRespectiveTestDataList(str11,count);

			for(int j=0;j<str11.length;j++)
			{
				if(j==0) {
					dropName=str11[j].trim();

					if(!dropName.equalsIgnoreCase("Teacher Name"))
						dropName=dropName.replace(" ", "_");
					System.out.println("dropName : "+dropName);
					//dropDownHeading.add(str11[j]);
					headingList.add(dropName);
				}
				else		
					valueList.add(str11[j]);

			}


		}

		System.out.println("tHeading1 : "+headingList);
		System.out.println("TestDataList1 : "+valueList);
		System.out.println("getRespectiveTestDataList1");

		//	System.out.println("TestDataList : "+tdata);

	}
	
	public static HashMap<String, String> listToHashMap(List<String> v_nameList,List<String> v_dataList)
	{
		HashMap<String,String> map=new HashMap<>();


		for( int i=0;i<v_nameList.size();i++)
		{
			map.put(v_nameList.get(i), v_dataList.get(i));
		}
		//System.out.println("Maps : "+map);
		return map;

	}
	
	public static void testDataOperation(Object[][] data,int colNum,int testDataListIndex,List<String> vHeadingList,List<String> vDataList) throws InterruptedException
	{
		testData.clear();
		testData=getSortedTestDataOfExcel(data,colNum);
		getRespectiveTestDataList1(testData, testDataListIndex, vHeadingList, vDataList);
	}
	

}
