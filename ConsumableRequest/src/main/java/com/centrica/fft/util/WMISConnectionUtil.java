package com.centrica.fft.util;

import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.Properties;

import javax.sql.DataSource;

import oracle.jdbc.pool.OracleDataSource;

public class WMISConnectionUtil {

	static OracleDataSource ods;

	/**
	 * @Method_Name : getDataSource
	 * @Input_Parameters: 
	 * @Return_Type : DataSource
	 * @Functionality : Reading DataSource Properties from Properties file
	 * @throws : IOException 
	 */
	public static DataSource getDataSource() throws IOException {
		try {
			ods = new OracleDataSource();
			Properties prop = new Properties();
			String filename="properties/datasource.properties";
			InputStream inputStream = WMISConnectionUtil.class.getClassLoader().getResourceAsStream(filename);
			if (inputStream != null) {
				prop.load(inputStream);
			}
			ods.setConnectionProperties(prop);
			ods.setURL(prop.getProperty("url"));
		} catch (SQLException e) {
			FFTUtil.stackTraceToString(e);
		}
		return ods;
	}

}
