package day20;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtils {
    public static FileInputStream fi;
    public static FileOutputStream fo;
    public static XSSFWorkbook wb;
    public static XSSFSheet ws;
    public static XSSFRow row;
    public static XSSFCell cell;
    public static CellStyle style;

    public static int getRowCount(String xlfile, String xlsheet) throws IOException {
        fi = new FileInputStream(xlfile);
        wb = new XSSFWorkbook(fi);
        ws = wb.getSheet(xlsheet);

        int rowcount = -1;
        if (ws != null) {
            rowcount = ws.getLastRowNum();
        } else {
            System.out.println(xlsheet + " sheet does not exist");
        }

        wb.close();
        fi.close();
        return rowcount;
    }

    public static int getCellCount(String xlfile, String xlsheet, int rownum) throws IOException {
        fi = new FileInputStream(xlfile);
        wb = new XSSFWorkbook(fi);
        ws = wb.getSheet(xlsheet);

        int cellcount = -1;
        if (ws != null) {
            row = ws.getRow(rownum);
            if (row != null) {
                cellcount = row.getLastCellNum();
            } else {
                System.out.println(rownum + " row does not exist");
            }
        } else {
            System.out.println(xlsheet + " sheet does not exist");
        }

        wb.close();
        fi.close();
        return cellcount;
    }

    public static String getCellData(String xlfile, String xlsheet, int rownum, int colnum) throws IOException {
        fi = new FileInputStream(xlfile);
        wb = new XSSFWorkbook(fi);
        ws = wb.getSheet(xlsheet);

        String data = "";
        if (ws != null) {
            row = ws.getRow(rownum);
            if (row != null) {
                cell = row.getCell(colnum);
                if (cell != null) {
                    DataFormatter formatter = new DataFormatter();
                    data = formatter.formatCellValue(cell);
                } else {
                    System.out.println(colnum + " column does not exist");
                }
            } else {
                System.out.println(rownum + " row does not exist");
            }
        } else {
            System.out.println(xlsheet + " sheet does not exist");
        }

        wb.close();
        fi.close();
        return data;
    }

    public static void setCellData(String xlfile, String xlsheet, int rownum, int colnum, String data)
            throws IOException {
        fi = new FileInputStream(xlfile);
        wb = new XSSFWorkbook(fi);
        ws = wb.getSheet(xlsheet);

        if (ws == null) {
            System.out.println(xlsheet + " sheet does not exist, creating new sheet");
            ws = wb.createSheet(xlsheet);
        }

        row = ws.getRow(rownum);
        if (row == null) {
            System.out.println(rownum + " row does not exist, creating new row");
            row = ws.createRow(rownum);
        }

        cell = row.getCell(colnum);
        if (cell == null) {
            System.out.println(colnum + " column does not exist, creating new column");
            cell = row.createCell(colnum);
        }

        cell.setCellValue(data);

        fo = new FileOutputStream(xlfile);
        wb.write(fo);

        fo.close();
        wb.close();
        fi.close();
    }

    public static void fillGreenColor(String xlfile, String xlsheet, int rownum, int colnum) throws IOException {
        fi = new FileInputStream(xlfile);
        wb = new XSSFWorkbook(fi);
        ws = wb.getSheet(xlsheet);

        if (ws == null) {
            System.out.println(xlsheet + " sheet does not exist, creating new sheet");
            ws = wb.createSheet(xlsheet);
        }

        row = ws.getRow(rownum);
        if (row == null) {
            System.out.println(rownum + " row does not exist, creating new row");
            row = ws.createRow(rownum);
        }

        cell = row.getCell(colnum);
        if (cell == null) {
            System.out.println(colnum + " column does not exist, creating new column");
            cell = row.createCell(colnum);
        }

        style = wb.createCellStyle();
        style.setFillForegroundColor(IndexedColors.GREEN.getIndex());
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        cell.setCellStyle(style);

        fo = new FileOutputStream(xlfile);
        wb.write(fo);

        fo.close();
        wb.close();
        fi.close();
    }

    public static void fillRedColor(String xlfile, String xlsheet, int rownum, int colnum) throws IOException {
        fi = new FileInputStream(xlfile);
        wb = new XSSFWorkbook(fi);
        ws = wb.getSheet(xlsheet);

        if (ws == null) {
            System.out.println(xlsheet + " sheet does not exist, creating new sheet");
            ws = wb.createSheet(xlsheet);
        }

        row = ws.getRow(rownum);
        if (row == null) {
            System.out.println(rownum + " row does not exist, creating new row");
            row = ws.createRow(rownum);
        }

        cell = row.getCell(colnum);
        if (cell == null) {
            System.out.println(colnum + " column does not exist, creating new column");
            cell = row.createCell(colnum);
        }

        style = wb.createCellStyle();
        style.setFillForegroundColor(IndexedColors.RED.getIndex());
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        cell.setCellStyle(style);

        fo = new FileOutputStream(xlfile);
        wb.write(fo);

        fo.close();
        wb.close();
        fi.close();
    }

    /* test */
    // public static void main(String[] args) throws IOException {
    // String fname = "excel_util_test_day20.xlsx";
    // String fpath = System.getProperty("user.dir") +
    // "\\seleniumwebdriver\\src\\test\\resources\\xlsx_files\\"
    // + fname;

    // // System.out.println(getRowCount(fpath, "Sheet1"));
    // // System.out.println(getCellCount(fpath, "Sheet1", 0));
    // // System.out.println(getCellData(fpath, "Sheet1", 0, 0));

    // // setCellData(fpath, "Sheet1", 0, 0, "boooook");

    // // fillGreenColor(fpath, "Sheet1", 0, 0);
    // fillRedColor(fpath, "Sheet1", 0, 1);
    // }
}