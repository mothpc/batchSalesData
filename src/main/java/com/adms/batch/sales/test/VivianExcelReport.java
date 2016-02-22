package com.adms.batch.sales.test;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLClassLoader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import com.adms.imex.excelformat.DataHolder;
import com.adms.imex.excelformat.ExcelFormat;
import com.adms.imex.excelformat.SimpleMapDataHolder;
import com.adms.utils.PropertyResource;

public class VivianExcelReport {

	public static final String CONFIG_FILE_LOCATION = "config/vivian.properties";
	
	private static final String DB_DRIVER = "net.sourceforge.jtds.jdbc.Driver";
	private static final String DB_CONNECTION = "db.url";
	private static final String DB_USER = "db.username";
	private static final String DB_PASSWORD = "db.password";
	private static final String DB_SQL = "db.sql";
	private static final String OUTPUT_FILENAME = "output.filename";
	
	public static void main(String[] s) throws Exception
	{
		new VivianExcelReport().generateReport();
	}

	public void sendEmail()
	{
		
	}
	
	public void generateReport() throws Exception
	{

		DataHolder fileDataHolder = null;
		fileDataHolder = toDataHolder(fileDataHolder);
		
		

		InputStream fileFormat = null;
		OutputStream output = null;
		try
		{
			fileFormat = URLClassLoader.getSystemResourceAsStream("config/FileFormat_vivian-output.xml");
			String outputFilename = PropertyResource.getInstance(CONFIG_FILE_LOCATION).getValue(OUTPUT_FILENAME);
			String processDate = new SimpleDateFormat("ddMMMMMyyyy", Locale.US).format(new Date());
			outputFilename = outputFilename.replace("[processDate]", processDate);
			output = new FileOutputStream(outputFilename);
			new ExcelFormat(fileFormat).writeExcel(output, fileDataHolder);
		}
		catch (Exception e)
		{
			throw e;
		}
		finally
		{
			try
			{
				fileFormat.close();
			}
			catch (Exception e)
			{
			}
		}
	}

	private DataHolder toDataHolder(DataHolder fileDataHolder) throws Exception
	{
		if (fileDataHolder == null)
		{
			fileDataHolder = new SimpleMapDataHolder();
		}

		// data sheet
		DataHolder sheetDataHolder = new SimpleMapDataHolder();
		fileDataHolder.put("SomeSheetName", sheetDataHolder);
		fileDataHolder.setSheetNameByIndex(0, "SomeSheetName");
		
		sheetDataHolder.put("CONTENT_1", new SimpleMapDataHolder().setValue("This is dynamic content from java"));

		List<DataHolder> someDataList = new ArrayList<DataHolder>();
		sheetDataHolder.putDataList("someDataList", someDataList);
		
		selectRecordsFromDbUserTable(someDataList);
		
		return fileDataHolder;
	}

	private void selectRecordsFromDbUserTable(List<DataHolder> someDataList)
			throws Exception
	{
		Connection dbConnection = null;
		Statement statement = null;

		try
		{
			dbConnection = getDBConnection();
			statement = dbConnection.createStatement();

			System.out.println(PropertyResource.getInstance(CONFIG_FILE_LOCATION).getValue(DB_SQL));

			ResultSet rs = statement.executeQuery(PropertyResource.getInstance(CONFIG_FILE_LOCATION).getValue(DB_SQL));

			while (rs.next())
			{
				DataHolder record = toRecordDataHolder(rs);
				someDataList.add(record);
			}
		}
		catch (SQLException e)
		{
			System.out.println(e.getMessage());
		}
		finally
		{
			if (statement != null)
			{
				statement.close();
			}

			if (dbConnection != null)
			{
				dbConnection.close();
			}
		}
	}

	private Connection getDBConnection() throws Exception
	{
		Connection dbConnection = null;
		try
		{
			Class.forName(DB_DRIVER);
		}
		catch (ClassNotFoundException e)
		{
			System.out.println(e.getMessage());
		}
		try
		{
			dbConnection = DriverManager.getConnection(PropertyResource.getInstance(CONFIG_FILE_LOCATION).getValue(DB_CONNECTION), PropertyResource.getInstance(CONFIG_FILE_LOCATION).getValue(DB_USER), PropertyResource.getInstance(CONFIG_FILE_LOCATION).getValue(DB_PASSWORD));
			return dbConnection;
		}
		catch (SQLException e)
		{
			System.out.println(e.getMessage());
		}
		return dbConnection;
	}

	private DataHolder toRecordDataHolder(ResultSet rs) throws SQLException
	{
		DataHolder record = new SimpleMapDataHolder();
		

		int columnCount = rs.getMetaData().getColumnCount();
		
		for (int i = 1; i <= columnCount; i++)
		{
			String columnName = rs.getMetaData().getColumnName(i);
			int columnType = rs.getMetaData().getColumnType(i);
			
			Object value = null;
			
			switch (columnType) {
			case 2: //numeric
				value = rs.getBigDecimal(i);
				break;
			case 4: //int
				value = rs.getBigDecimal(i);
				break;
			case 12: //nvarchar
				value = rs.getString(i);
				break;
			case 93: //datetime
				java.sql.Date date = rs.getDate(i, Calendar.getInstance(Locale.US));
				if (date != null)
				{
					value = new Date(date.getTime());
				}
				break;

			default:
				System.out.println("data type not supported [" + columnType + ":" + rs.getMetaData().getColumnTypeName(i) + "]");
				break;
			}
			
			record.put(columnName, new SimpleMapDataHolder().setValue(value));
		}

		return record;
	}

}
