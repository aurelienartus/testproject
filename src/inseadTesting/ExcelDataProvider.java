package inseadTesting;

import java.io.File;
import java.io.FileInputStream;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.Assert;
import org.testng.annotations.DataProvider;

// --------------------------------------------------------------------------------------------------------
// Data provider that reads data from an Excel file and returns results

public class ExcelDataProvider {

	// Data will be read from an Excel file, cached and then returned whenever required by test methods
	private static Object[][] mCachedData = null;

	// --------------------------------------------------------------------------------------------------------
	// The data provider method that will be called by test methods
	@DataProvider(name = "excelData")
	public static Object[][] getData()
	{
		if (null == mCachedData) 
		{
			mCachedData = readExcelFile();
		}
		Assert.assertTrue(mCachedData != null, "Unable to read data from Excel file");
		return mCachedData;
	}
	
	// --------------------------------------------------------------------------------------------------------
	// Reads an excel file and creates test data from it
	private static Object[][] readExcelFile()
	{
		List<MyInseadUser> myInseadUsers = new ArrayList<MyInseadUser>();
		TestParameters testParameters = new TestParameters();


		// Create the data provider object
		Object[][] testData = new Object[][] { new Object[myInseadUsers.size()] };
		
		for (int iUser = 0; iUser < myInseadUsers.size(); ++iUser)
		{
			testData[iUser] = new Object[] {testParameters, myInseadUsers.get(iUser)};
		}
		return testData;
	}
	
	// --------------------------------------------------------------------------------------------------------
	// Read MyInsead parameters from an excel file

	
}

