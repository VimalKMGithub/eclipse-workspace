package day19;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ReadingExel {
	public static void main(String[] args) {
		try (

				FileInputStream fi = new FileInputStream(
						System.getProperty("user.dir") + "\\src\\test\\resources\\xlsx_files\\sample_data.xlsx");

				XSSFWorkbook book = new XSSFWorkbook(fi)

		) {
			XSSFSheet sheet = book.getSheet("Sheet1");
			int rows = sheet.getLastRowNum();
			int cols = sheet.getRow(0).getLastCellNum();
			System.out.println(rows + " - " + cols);

			for (int i = 0; i <= rows; i++) {
				XSSFRow cRow = sheet.getRow(i);
				for (int j = 0; j < cols; j++) {
					XSSFCell cCell = cRow.getCell(j);
					System.out.print(cCell.toString() + "\t");
				}
				System.out.println();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
