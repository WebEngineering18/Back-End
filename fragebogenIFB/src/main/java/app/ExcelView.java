package app;

import entity.Answer_Question;
import entity.Question;
import entity.User;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.*;
import org.springframework.web.servlet.view.document.AbstractXlsView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

public class ExcelView extends AbstractXlsView {
    @Override
    protected void buildExcelDocument(Map<String, Object> model,
                                      Workbook workbook,
                                      HttpServletRequest request,
                                      HttpServletResponse response) throws Exception {

        // change the file name
        response.setHeader("Content-Disposition", "attachment; filename=\"fragebogen-auswertung.xls\"");

        @SuppressWarnings("unchecked")
        List<Answer_Question> aq_list = (List<Answer_Question>) model.get("answer_questions");
        List<User> u_list = (List<User>) model.get("users");
        List<Question> q_list = (List<Question>) model.get("questions");

        // create excel xls sheet
        Sheet sheet = workbook.createSheet("Fragebogen Auswertung");
        sheet.setDefaultColumnWidth(30);

        // create style for header cells
        CellStyle style = workbook.createCellStyle();
        Font font = workbook.createFont();
        font.setFontName("Arial");
        style.setFillForegroundColor(HSSFColor.BLUE.index);
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        font.setBold(true);
        font.setColor(HSSFColor.WHITE.index);
        style.setFont(font);


        // create header row
        Row header = sheet.createRow(0);
        header.createCell(0).setCellValue("Id");
        header.getCell(0).setCellStyle(style);

        int cell = 1;
        for (Question q : q_list) {
            header.createCell(cell).setCellValue(q.getQuestion());
            header.getCell(cell).setCellStyle(style);
            cell++;
        }


        int rowCount = 1;
        for (User user : u_list) {
            Row answerRow = sheet.createRow(rowCount++);
            answerRow.createCell(0).setCellValue(user.getId());
            cell = 1;
            for (Answer_Question aq : aq_list) {
                if(user.getId() == aq.getUser().getId() && cell == aq.getQuestion().getId()) {
                    answerRow.createCell(cell).setCellValue(aq.getAnswer().getAnswer());
                    cell++;
                }
            }
        }

    }
}
