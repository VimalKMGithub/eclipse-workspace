package day20;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Collection;
import java.util.Properties;
import java.util.Set;

public class ReadingPropertiesFile {
	public static void main(String[] args) {
		String fname = "config.properties";
		String fpath = System.getProperty("user.dir") + "\\src\\test\\resources\\properties_file\\" + fname;

		Properties properties = new Properties();
		try (FileInputStream fi = new FileInputStream(fpath)) {
			properties.load(fi);
			String appurl = properties.getProperty("appurl");
			String email = properties.getProperty("email");
			String password = properties.getProperty("password");
			String oderid = properties.getProperty("oderid");
			String customerid = properties.getProperty("customerid");

			System.out.println(appurl);
			System.out.println(email);
			System.out.println(password);
			System.out.println(oderid);
			System.out.println(customerid);

			/* all keys */
			/* 1st method */
			Set<String> keys = properties.stringPropertyNames();
			System.out.println(keys);

			/* 2nd method */
			Set<Object> keys2 = properties.keySet();
			System.out.println(keys2);

			/* all values */
			Collection<Object> values = properties.values();
			System.out.println(values);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
