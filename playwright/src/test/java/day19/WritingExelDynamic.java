package day19;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Scanner;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class WritingExelDynamic {
    public static void main(String[] args) {
        try (

                FileOutputStream fi = new FileOutputStream(System.getProperty("user.dir")
                        + "\\seleniumwebdriver\\src\\test\\resources\\xlsx_files\\dynamic_data_writing.xlsx");

                XSSFWorkbook book = new XSSFWorkbook();

                Scanner sc = new Scanner(System.in)

        ) {
            XSSFSheet sheet = book.createSheet("Data");

            System.out.print("Enter rows: ");
            int r = sc.nextInt();

            System.out.print("Enter cols: ");
            int c = sc.nextInt();

            XSSFRow row = null;
            for (int i = 0; i < r; i++) {
                row = sheet.createRow(i);
                for (int j = 0; j < c; j++) {
                    row.createCell(j).setCellValue("cv");
                }
            }

            book.write(fi);
            System.out.println("File created...");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
