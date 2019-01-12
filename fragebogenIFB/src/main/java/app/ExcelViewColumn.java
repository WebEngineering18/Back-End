package app;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.web.servlet.view.document.AbstractXlsView;

import entity.Answer_Question;
import entity.Question;
import entity.User;

public class ExcelViewColumn extends AbstractXlsView {

	@Override
	protected void buildExcelDocument(Map<String, Object> model, Workbook workbook, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		int rowCount = 1;
		int iterate = 1;
		Row answerRow;
		int cellId;

		int cell = 1;

		int rowCountFunction = rowCount + 1;
		int previousElement = 0;
		int aqPrevious = 0;
		int aqCurrent = 0;
		int helpVariable = 0;
		// change the file name
		response.setHeader("Content-Disposition", "attachment; filename=\"fragebogen-auswertung.xls\"");

		@SuppressWarnings("unchecked")
		List<Answer_Question> aq_list = (List<Answer_Question>) model.get("answer_questions");
		List<User> u_list = (List<User>) model.get("users");
		List<Question> q_list = (List<Question>) model.get("questions");

		// create excel xls sheet
		Sheet sheet = workbook.createSheet("Fragebogen Auswertung");
		sheet.setDefaultColumnWidth(40);

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

		for (Question q : q_list) {
			header.createCell(cell).setCellValue(q.getQuestion());
			header.getCell(cell).setCellStyle(style);
			cell++;
		}

		for (Answer_Question aq : aq_list) {
			answerRow = sheet.createRow(iterate++);
		}

		for (User user : u_list) {

			cellId = rowCount;
			for (int i = 0; i < 10; i++) {
				answerRow = sheet.getRow(cellId++);
				answerRow.createCell(0).setCellValue(user.getId());
			}
			answerRow = sheet.getRow(rowCount);
			cell = 1;

			rowCountFunction = rowCount + 1;

			for (int i = 0; i < aq_list.size(); i++) {

				if (user.getId() == aq_list.get(i).getUser().getId()) {

					helpVariable = i - 1;
					if (i == 0 || (aq_list.get(i).getUser().getId() != aq_list.get(helpVariable).getUser().getId())) {
						answerRow.createCell(cell).setCellValue(aq_list.get(i).getAnswer().getAnswer());
					} else {
						helpVariable = i;
						previousElement = helpVariable - 1;
						aqCurrent = aq_list.get(i).getQuestion().getId();
						aqPrevious = aq_list.get(previousElement).getQuestion().getId();
						if (aqCurrent == aqPrevious) {
							answerRow = sheet.getRow(rowCountFunction++);
							answerRow.createCell(cell).setCellValue(aq_list.get(i).getAnswer().getAnswer());
						} else {
							++cell;
							rowCountFunction = rowCount + 1;
							answerRow = sheet.getRow(rowCount);
							answerRow.createCell(cell).setCellValue(aq_list.get(i).getAnswer().getAnswer());
						}

					}
				}
			}
			rowCount += 10;
		}
	}

}
