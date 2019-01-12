package rest;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import app.ExcelView;
import entity.Answer;

@RestController
public class ExcelController {
	
/*	@GetMapping("/download")
	public ModelAndView getExcel() {
		
		List<Answer> answerList = new ArrayList<Answer>();
		answerList.add(new Answer("Hallo"));
		answerList.add(new Answer("Tschüss"));
		return new ModelAndView(new ExcelView(), "answerList", answerList);
		
	} */
}
