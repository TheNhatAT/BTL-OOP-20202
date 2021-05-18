package thenhat.code.managerwebapp.helper;

import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;
import thenhat.code.managerwebapp.model.entity.Class;
import thenhat.code.managerwebapp.model.entity.Schedule;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Slf4j
public class ExcelHelper {
    //== fields ==

    //-- Microsoft office types for HTTP content streaming for .xlsx file --
    public static String TYPE = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";

    //== methods ==
    public static boolean hasExelFormat(MultipartFile file) {
        //-- check .xlsx --
        return TYPE.equals(file.getContentType());
    }

    public static List<Schedule> excelToLichThi(InputStream is) {
        //-- count id --
        int countId = 1;

        try {
            Workbook workbook = new XSSFWorkbook(is);

            //-- get the first sheet at file --
            //-- can update get all sheets in the future --
            Sheet sheet = workbook.getSheetAt(0);
            Iterator<Row> rows = sheet.iterator();

            List<Schedule> scheduleList = new ArrayList<>();

            int rowNumber = 0;
            //-- read row by row --
            while (rows.hasNext()) {
                Row currentRow = rows.next();

                //-- skip header --
                if (rowNumber == 0) {
                    rowNumber++;
                    continue;
                }
                Iterator<Cell> cellsInRow = currentRow.iterator();
                Schedule schedule = new Schedule();
                int cellIndex = 0;

                while (cellsInRow.hasNext()) {
                    Cell currentCell = cellsInRow.next();
                    switch (cellIndex) {
                        case 0:
                            schedule.setTenVien(currentCell.getStringCellValue());
                            break;
                        case 1:
                            schedule.setMaLop((int) currentCell.getNumericCellValue());
                            break;
                        case 2:
                            schedule.setMaHp(currentCell.getStringCellValue());
                            break;
                        case 3:
                            schedule.setTenHp(currentCell.getStringCellValue());
                            break;
                        case 4:
                            schedule.setGhiChu(currentCell.getStringCellValue());
                            break;
                        case 5:
                            schedule.setTenNhom(currentCell.getStringCellValue());
                            break;
                        case 6:
                            schedule.setDotMo(currentCell.getStringCellValue());
                            break;
                        case 7:
                            schedule.setTuan(currentCell.getStringCellValue());
                            break;
                        case 8:
                            schedule.setThu(currentCell.getStringCellValue());
                            break;
                        case 9:
                            schedule.setNgayThi(currentCell.getStringCellValue());
                            break;
                        case 10:
                            schedule.setKipThi(currentCell.getStringCellValue());
                            break;
                        case 11:
                            schedule.setSoLuongDk(((int) currentCell.getNumericCellValue()));
                            break;
                        case 12:
                            schedule.setPhongThi(currentCell.getStringCellValue());
                            break;
                        //-- dont need to use this case 'cause use  @GeneratedValue(strategy = GenerationType.IDENTITY)
//                        case 13:
//                            lichThi.setId(BigInteger.valueOf(countId));
//                            log.info("id of lich thi = {}", Long.valueOf(countId));
//                            countId++;
//                            break;
                        default:
                            log.info("Error when read cell");
                            break;
                    }
                    cellIndex++;
                    scheduleList.add(schedule);
                }
                workbook.close();
            }

            return scheduleList;
        } catch (IOException e) {
            log.info("Exception is {}", e.toString());
            throw new RuntimeException("fail to parse Excel file: " + e.getMessage());
        }
    }

    public static List<Class> exelToLopHoc(InputStream is) {
        try {
            Workbook workbook = new XSSFWorkbook(is);

            //-- get the first sheet at file --
            //-- can update get all sheets in the future --
            Sheet sheet = workbook.getSheetAt(0);
            Iterator<Row> rows = sheet.iterator();

            List<Class> classList = new ArrayList<>();

            int rowNumber = 0;
            //-- read row by row --
            while (rows.hasNext()) {
                Row currentRow = rows.next();
                //-- skip header --
                if (rowNumber <= 2) {
                    rowNumber++;
                    continue;
                }
                Iterator<Cell> cellsInRow = currentRow.iterator();
                Class aClass = new Class();
                int cellIndex = 0;

                while (cellsInRow.hasNext()) {
                    Cell currentCell = cellsInRow.next();
                    switch (cellIndex) {
                        case 0:
                            aClass.setKyHoc(currentCell.getStringCellValue());
                            break;
                        case 2:
                            if(currentCell.getStringCellValue() == "") {
                                log.info("cellIndex = {}, value = {}", cellIndex, currentCell.getStringCellValue());
                                break;
                            }
                            aClass.setMaLop(Long.valueOf(currentCell.getStringCellValue()));
                            break;
                        case 5:
                            aClass.setTenLop(currentCell.getStringCellValue());
                            break;
                        case 18:
                            try {
                                aClass.setSoLuongSV(Integer.valueOf(currentCell.getStringCellValue()));
                                break;
                            } catch (Exception e) {
                                break;
                            }
                        default:
                            break;
                    }
                    cellIndex++;
                }
                if(aClass.getMaLop() != null) {
                    classList.add(aClass);
                    log.info("class = {}", aClass);
                }
                workbook.close();
            }

            return classList;
        } catch (IOException e) {
            log.info("Exception is {}", e.toString());
            throw new RuntimeException("fail to parse Excel file: " + e.getMessage());
        }
    }
}
