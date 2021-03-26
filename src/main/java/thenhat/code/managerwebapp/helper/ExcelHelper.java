package thenhat.code.managerwebapp.helper;

import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;
import thenhat.code.managerwebapp.model.LichThi;
import thenhat.code.managerwebapp.model.LopHoc;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Slf4j
public class ExcelHelper {
    //== fields ==

    @PersistenceContext
    private static EntityManager em;

    //-- Microsoft office types for HTTP content streaming for .xlsx file --
    public static String TYPE = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";

    //== methods ==
    public static boolean hasExelFormat(MultipartFile file) {
        //-- check .xlsx --
        if (!TYPE.equals(file.getContentType())) {
            return false;
        }
        return true;
    }

    public static List<LichThi> excelToLichThi(InputStream is) {
        //-- count id --
        int countId = 1;

        try {
            Workbook workbook = new XSSFWorkbook(is);

            //-- get the first sheet at file --
            //-- can update get all sheets in the future --
            Sheet sheet = workbook.getSheetAt(0);
            Iterator<Row> rows = sheet.iterator();

            List<LichThi> lichThiList = new ArrayList<>();

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

                LichThi lichThi = new LichThi();

                int cellIndex = 0;
                while (cellsInRow.hasNext()) {
                    Cell currentCell = cellsInRow.next();
                    switch (cellIndex) {
                        case 0:
                            lichThi.setTenVien(currentCell.getStringCellValue());
                            break;
                        case 1:
                            //-- insert Lop Hoc vào Lich Thi --
                            try {
                                lichThi.setLopHoc(em.find(LopHoc.class, Long.valueOf((int) currentCell.getNumericCellValue())));
                            } catch (Exception e) {
                                //-- không lấy được lớp học từ DB --
                                lichThi.setLopHoc(null);
                            }
                            //lichThi.setMaLop(Integer.valueOf((int) currentCell.getNumericCellValue()));
                            break;
                        case 2:
                            lichThi.setMaHp(currentCell.getStringCellValue());
                            break;
                        case 3:
                            lichThi.setTenHp(currentCell.getStringCellValue());
                            break;
                        case 4:
                            lichThi.setGhiChu(currentCell.getStringCellValue());
                            break;
                        case 5:
                            lichThi.setTenNhom(currentCell.getStringCellValue());
                            break;
                        case 6:
                            lichThi.setDotMo(currentCell.getStringCellValue());
                            break;
                        case 7:
                            lichThi.setTuan(currentCell.getStringCellValue());
                            break;
                        case 8:
                            lichThi.setThu(currentCell.getStringCellValue());
                            break;
                        case 9:
                            lichThi.setNgayThi(currentCell.getStringCellValue());
                            break;
                        case 10:
                            lichThi.setKipThi(currentCell.getStringCellValue());
                            break;
                        case 11:
                            lichThi.setSoLuongDk((Integer.valueOf((int) currentCell.getNumericCellValue())));
                            break;
                        case 12:
                            lichThi.setPhongThi(currentCell.getStringCellValue());
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
                    lichThiList.add(lichThi);
                }
                workbook.close();
            }

            return lichThiList;
        } catch (IOException e) {
            log.info("Exception is {}", e.toString());
            throw new RuntimeException("fail to parse Excel file: " + e.getMessage());
        }
    }
}
