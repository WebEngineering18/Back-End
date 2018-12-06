package rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import entity.Answer_Question;
import repository.AnswerQuestionRepository;

@RestController
public class Answer_QuestionController {
	
	@Autowired
	private AnswerQuestionRepository aqRepository;
	
	@GetMapping("/findAQ/{id}")
	public Answer_Question findById(@PathVariable(value="id") int id) {
		return aqRepository.findById(id);
	}
	
	@RequestMapping("/deleteAllAQ")
	public String deleteAll() {
		aqRepository.deleteAll();
		return "Alle Daten gelöscht";
	}

}
