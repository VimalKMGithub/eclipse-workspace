package day19;

import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class WritingExel {
	public static void main(String[] args) {
		try (

				FileOutputStream fi = new FileOutputStream(System.getProperty("user.dir")
						+ "\\src\\test\\resources\\xlsx_files\\static_data_writing.xlsx");

				XSSFWorkbook book = new XSSFWorkbook()

		) {
			XSSFSheet sheet = book.createSheet("Data");

			XSSFRow row = sheet.createRow(0);
			row.createCell(0).setCellValue("Java");
			row.createCell(1).setCellValue(1256);
			row.createCell(2).setCellValue("Java dest");

			row = sheet.createRow(1);
			row.createCell(0).setCellValue("Java 1");
			row.createCell(1).setCellValue(1256);
			row.createCell(2).setCellValue("Java 1 dest");

			row = sheet.createRow(2);
			row.createCell(0).setCellValue("Java 2");
			row.createCell(1).setCellValue(1256);
			row.createCell(2).setCellValue("Java 2 dest");

			book.write(fi);
			System.out.println("File created...");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
