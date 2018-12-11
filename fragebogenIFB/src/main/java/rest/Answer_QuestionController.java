package rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import entity.Answer;
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
	
	/*@PostMapping(produces = MediaType.APPLICATION_JSON_VALUE,
			consumes = MediaType.APPLICATION_JSON_VALUE, value = "/testAnswerQuestion")
	public List<Answer> create(@RequestBody final Answer answer) {
		answerRepository.save(answer);
		return answerRepository.findAll();
	} */
	

}
