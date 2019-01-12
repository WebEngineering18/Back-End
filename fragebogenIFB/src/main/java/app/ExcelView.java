package app;

import entity.Answer_Question;
import entity.Question;
import entity.User;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.hssf.util.HSSFColor.HSSFColorPredefined;
import org.apache.poi.ss.usermodel.*;
import org.springframework.web.servlet.view.document.AbstractXlsView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

public class ExcelView extends AbstractXlsView {
	@Override
	protected void buildExcelDocument(Map<String, Object> model, Workbook workbook, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		// change the file name
		response.setHeader("Content-Disposition", "attachment; filename=\"fragebogen-auswertung.xls\"");

		@SuppressWarnings("unchecked")
		List<Answer_Question> aq_list = (List<Answer_Question>) model.get("answer_questions");

		for (Answer_Question aq : aq_list) {
			System.out.println(aq.toString());
		}
		List<User> u_list = (List<User>) model.get("users");
		List<Question> q_list = (List<Question>) model.get("questions");

		// create excel xls sheet
		Sheet sheet = workbook.createSheet("Fragebogen Auswertung");
		sheet.setDefaultColumnWidth(30);

		// create style for header cells
		CellStyle style = workbook.createCellStyle();
		Font font = workbook.createFont();
		font.setFontName("Arial");
		style.setFillForegroundColor(HSSFColor.HSSFColorPredefined.BLUE.getIndex());
		style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		font.setBold(true);
		font.setColor(HSSFColor.HSSFColorPredefined.WHITE.getIndex());
		style.setFont(font);

		// create header row
		Row header = sheet.createRow(0);
		header.createCell(0).setCellValue("Id");
		header.getCell(0).setCellStyle(style);

		int cell = 1;
		for (Question q : q_list) {

			if (q.getId() == 1) {
				for (int i = 0; i < 10; i++) {
					header.createCell(cell).setCellValue(q.getQuestion());
					header.getCell(cell).setCellStyle(style);
					cell++;
				}
			} else if (q.getId() == 15) {
				for (int i = 0; i < 6; i++) {
					header.createCell(cell).setCellValue(q.getQuestion());
					header.getCell(cell).setCellStyle(style);
					cell++;
				}
			} else if (q.getId() == 4) {
				for (int i = 0; i < 2; i++) {
					header.createCell(cell).setCellValue(q.getQuestion());
					header.getCell(cell).setCellStyle(style);
					cell++;
				}
			} else if (q.getId() == 5) {
				for (int i = 0; i < 6; i++) {
					header.createCell(cell).setCellValue(q.getQuestion());
					header.getCell(cell).setCellStyle(style);
					cell++;
				}
			} else if (q.getId() == 6) {
				for (int i = 0; i < 4; i++) {
					header.createCell(cell).setCellValue(q.getQuestion());
					header.getCell(cell).setCellStyle(style);
					cell++;
				}
			} else if (q.getId() == 8) {
				for (int i = 0; i < 5; i++) {
					header.createCell(cell).setCellValue(q.getQuestion());
					header.getCell(cell).setCellStyle(style);
					cell++;
				}
			} else if (q.getId() == 9) {
				for (int i = 0; i < 6; i++) {
					header.createCell(cell).setCellValue(q.getQuestion());
					header.getCell(cell).setCellStyle(style);
					cell++;
				}
			} else if (q.getId() == 10) {
				for (int i = 0; i < 9; i++) {
					header.createCell(cell).setCellValue(q.getQuestion());
					header.getCell(cell).setCellStyle(style);
					cell++;
				}
			} else if (q.getId() == 11) {
				for (int i = 0; i < 4; i++) {
					header.createCell(cell).setCellValue(q.getQuestion());
					header.getCell(cell).setCellStyle(style);
					cell++;
				}
			} else if (q.getId() == 13) {
				for (int i = 0; i < 5; i++) {
					header.createCell(cell).setCellValue(q.getQuestion());
					header.getCell(cell).setCellStyle(style);
					cell++;
				}
			} else if (q.getId() == 14) {
				for (int i = 0; i < 3; i++) {
					header.createCell(cell).setCellValue(q.getQuestion());
					header.getCell(cell).setCellStyle(style);
					cell++;
				}
			} else if (q.getId() == 16) {
				for (int i = 0; i < 6; i++) {
					header.createCell(cell).setCellValue(q.getQuestion());
					header.getCell(cell).setCellStyle(style);
					cell++;
				}
			} else if (q.getId() == 18) {
				for (int i = 0; i < 6; i++) {
					header.createCell(cell).setCellValue(q.getQuestion());
					header.getCell(cell).setCellStyle(style);
					cell++;
				}
			} 

			else {
				header.createCell(cell).setCellValue(q.getQuestion());
				header.getCell(cell).setCellStyle(style);
				cell++;
			}

		}


		int rowCount = 1;
		for (User user : u_list) {
			Row answerRow = sheet.createRow(rowCount++);
			answerRow.createCell(0).setCellValue(user.getId());
			cell = 1;
			for (Answer_Question aq : aq_list) {
				if (user.getId() == aq.getUser().getId() /* && cell == aq.getQuestion().getId() */) {
					if (aq.getQuestion().getQuestion().equals(header.getCell(cell).toString())) {
						answerRow.createCell(cell).setCellValue(aq.getAnswer().getAnswer());
						++cell;

					} else {
						while (!aq.getQuestion().getQuestion().equals(header.getCell(cell).toString())) {
							cell++;

						}
						answerRow.createCell(cell).setCellValue(aq.getAnswer().getAnswer());
						cell++;
					}
				}

			}
		}

	}
}
