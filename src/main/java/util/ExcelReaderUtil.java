package util;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelReaderUtil {
    public static List<ExcelEntity> readExcel() {
        List<ExcelEntity> entityList = new ArrayList<>();
        try (InputStream inputStream = ExcelReaderUtil.class.getClassLoader().getResourceAsStream("zhuge.xlsx");
             XSSFWorkbook workbook = new XSSFWorkbook(inputStream)) {
            // 获取第一个工作表
            XSSFSheet sheet = workbook.getSheetAt(0);
            Iterator<Row> rowIterator = sheet.iterator();
            // 跳过表头
            if (rowIterator.hasNext()) {
                rowIterator.next();
            }
            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();
                Iterator<Cell> cellIterator = row.cellIterator();
                String id = "";
                String title = "";
                String context = "";
                String wen = "";
                String gujie = "";
                String jie = "";
                String jie1 = "";
                String jie2 = "";
                String jie3 = "";
                String jie4 = "";
                String jie5 = "";
                String jie6 = "";
                int cellIndex = 0;
                while (cellIterator.hasNext()) {
                    Cell cell = cellIterator.next();
                    switch (cellIndex) {
                        case 0:
                            id = cell.getStringCellValue();
                            break;
                        case 1:
                            title = cell.getStringCellValue();
                            break;
                        case 2:
                            context = cell.getStringCellValue();
                            break;
                        case 3:
                            wen = cell.getStringCellValue();
                            break;
                        case 4:
                            gujie = cell.getStringCellValue();
                            break;
                        case 5:
                            jie = cell.getStringCellValue();
                            break;
                        case 6:
                            jie1 = cell.getStringCellValue();
                            break;
                        case 7:
                            jie2 = cell.getStringCellValue();
                            break;
                        case 8:
                            jie3 = cell.getStringCellValue();
                            break;
                        case 9:
                            jie4 = cell.getStringCellValue();
                            break;
                        case 10:
                            jie5 = cell.getStringCellValue();
                            break;
                        case 11:
                            jie6 = cell.getStringCellValue();
                            break;
                        default:
                            break;
                    }
                    cellIndex++;
                }
                // 创建实体类对象并添加到列表中
                ExcelEntity entity = new ExcelEntity(id, title, context, wen, gujie, jie, jie1, jie2, jie3, jie4, jie5, jie6);
                entityList.add(entity);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return entityList;
    }
}