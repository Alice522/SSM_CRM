package com.fj.crm.workbench;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import java.io.FileOutputStream;
import java.io.IOException;

public class CreateExcelTest {
    public static void main(String[] args) throws IOException {
        HSSFWorkbook wb = new HSSFWorkbook();
        HSSFSheet sheet = wb.createSheet("学生列表");
        HSSFRow row = sheet.createRow(0);
        HSSFCell cell = row.createCell(0);
        cell.setCellValue("学号");
        cell = row.createCell(1);
        cell.setCellValue("姓名");
        cell = row.createCell(2);
        cell.setCellValue("年龄");

        for(int i=1;i <= 10;i++){
            HSSFRow row1 = sheet.createRow(i);
            cell = row1.createCell(0);
            cell.setCellValue(100+i);
            cell = row1.createCell(1);
            cell.setCellValue("Name" + i);
            cell = row1.createCell(2);
            cell.setCellValue(20+i);
        }

        FileOutputStream fileOutputStream = new FileOutputStream("test.xls");
        wb.write(fileOutputStream);
    }
}
