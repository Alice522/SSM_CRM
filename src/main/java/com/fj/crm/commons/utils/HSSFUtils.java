package com.fj.crm.commons.utils;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.ss.usermodel.CellType;

public class HSSFUtils {

    public static String getCellValueForString(HSSFCell cell){
        String res = "";
        CellType cellType = cell.getCellType();
        if(cellType == CellType.STRING){
            res = cell.getStringCellValue();
        }else if (cellType == CellType.NUMERIC){
            res += cell.getNumericCellValue();
        }else if(cellType == CellType.BOOLEAN){
            res += cell.getBooleanCellValue();
        }else if(cellType == CellType.FORMULA){
            res = cell.getCellFormula();
        }
        return res;
    }
}
