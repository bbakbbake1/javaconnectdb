package kh.student;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class PropertiesTest {
	public static void main(String[] args) throws FileNotFoundException, IOException {
		String filePath = "D:/doitjava/student/src/kh/student/db.properties";
		Properties properties = new Properties();
		properties.load(new FileReader(filePath));
		System.out.println(properties.getProperty("url"));
		System.out.println(properties.getProperty("user"));
		System.out.println(properties.getProperty("password"));
	}
}
