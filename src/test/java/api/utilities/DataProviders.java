package api.utilities;

import java.io.IOException;

import org.testng.annotations.DataProvider;

public class DataProviders {
	
	
	 // DataProvider for all user details
	@DataProvider(name = "userData")
	public Object[][] getUserData() throws IOException {
	    String path = System.getProperty("user.dir") + "/testData/UserData.xlsx";
	    XLUtility xlUtil = new XLUtility(path);

	    int totalRows = xlUtil.getRowCount("Sheet1");
	    int totalCols = xlUtil.getCellCount("Sheet1", 1);

	    Object[][] data = new Object[totalRows][totalCols];

	    for (int i = 1; i <= totalRows; i++) {
	        for (int j = 0; j < totalCols; j++) {
	            data[i - 1][j] = xlUtil.getCellData("Sheet1", i, j);
	        }
	    }
	    return data;
	}
	
	
	
	// New DataProvider — only returns UserName column
	@DataProvider(name = "userNames")
    public String[] getUserNames() throws IOException {
        String path = System.getProperty("user.dir") + "/testData/UserData.xlsx";
        XLUtility xlUtil = new XLUtility(path);

        String sheetName = "Sheet1";
        int totalRows = xlUtil.getRowCount(sheetName);

        // Assuming UserName is in column index 1 (2nd column)
        int userNameColIndex = 1;
        String[] userNames = new String[totalRows];

        for (int i = 1; i <= totalRows; i++) {
            userNames[i - 1] = xlUtil.getCellData(sheetName, i, userNameColIndex);
        }

        return userNames;
    }

}
