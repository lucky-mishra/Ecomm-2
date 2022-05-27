package utils;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ExcelReader {
    private Sheet sh;
    Workbook wb;

    public ExcelReader(String sheetName) {
        try {
            FileInputStream fis = new FileInputStream(System.getProperty("user.dir") + "/src/test/resources/testdata/Registration.xlsx");
            wb = new XSSFWorkbook(fis);
            sh = wb.getSheet(sheetName);
        } catch (IOException e) {
            e.getMessage();
        }
    }

    public Map<String, String> getExcelData(int rownum) throws IOException {
        HashMap<String, String> hm = new HashMap<String, String>();
        for (int i = 0; i < sh.getRow(rownum).getLastCellNum(); i++) {
            String key = sh.getRow(rownum).getCell(i).getStringCellValue();
            String value = sh.getRow(rownum + 1).getCell(i).getStringCellValue();
            hm.put(key, value);
            System.out.println("hm data :" + hm);
        }
        return hm;
    }

    public Map<String, String> getExcelData() throws IOException {
        HashMap<String, String> hm = new HashMap<String, String>();
        for (int i = 0; i < sh.getLastRowNum()+1; i++) {
            System.out.println("total rows :" +sh.getLastRowNum());
            Row row = sh.getRow(i);
            Cell keyCell = row.getCell(0);
            String key = keyCell.getStringCellValue().trim();
            System.out.println("key:" + key);
            Cell valueCell = row.getCell(1);
            String value = valueCell.getStringCellValue().trim();
            System.out.println("value:" + value);
            hm.put(key, value);
            System.out.println("hm data :" + hm);
        }
        return hm;
    }

    public int getRowCount() {
        return sh.getLastRowNum();
    }

    public int getColCount() {
        return sh.getRow(0).getLastCellNum();
    }

}
