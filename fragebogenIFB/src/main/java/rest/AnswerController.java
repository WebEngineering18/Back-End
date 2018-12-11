package rest;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonParser;

import entity.Answer;
import entity.Answer_Question;
import entity.Question;
import repository.AnswerQuestionRepository;
import repository.AnswerRepository;
import repository.QuestionRepository;
import org.json.*;

@RestController
public class AnswerController {

	@Autowired
	private AnswerRepository answerRepository;
	@Autowired
	private AnswerQuestionRepository aqRepository;
	@Autowired
	private QuestionRepository questionRepository;

	@RequestMapping("/edit")
	public String edit(@RequestParam("frage_id") int frage_id, @RequestParam("antwort") String antwort) {
		
		Answer answer = new Answer(antwort);
		answerRepository.save(answer);
		
		if (questionRepository.existsById(frage_id)) {
			Question question = questionRepository.findQuestionById(frage_id);
			aqRepository.save(new Answer_Question(answer, question));
		}

		return antwort;

	}
	
	@CrossOrigin(origins = "http://localhost:8080")
	@PostMapping(produces = MediaType.APPLICATION_JSON_VALUE,
			consumes = MediaType.APPLICATION_JSON_VALUE, value = "/testAnswer")
	public List<Answer> create(@RequestBody final Answer answer) {
		
		
		JSONObject obj = new JSONObject(answer);
		
		
		
		
		
		answerRepository.save(answer);
		return answerRepository.findAll();
	}

	@RequestMapping("/saveAnswer/{answer}/{questionId}")
	public Answer_Question saveAnswer(@PathVariable String answer, @PathVariable int questionId) {
		Answer a = new Answer(answer);
		answerRepository.save(a);

		if (questionRepository.existsById(questionId)) {
			Question q = questionRepository.findQuestionById(questionId);
			aqRepository.save(new Answer_Question(a, q));
		}

		return aqRepository.findByAnswerId(a.getId());

	}

	@GetMapping("/answer/{id}")
	public Answer getAnswerById(@PathVariable(value = "id") int id) {
		return answerRepository.findAnswerById(id);
	}

	@GetMapping("/findAllAnswers")
	public String findAll() {

		List<Answer> list = answerRepository.findAll();
		String result = "";
		for (Answer answer : list) {
			result += answer.toString() + "<br>";
		}
		return result;
	}

	@RequestMapping("/deleteAllAnswers")
	public String deleteAll() {
		answerRepository.deleteAll();
		return "Alle Daten gelöscht";
	}

	@RequestMapping("/test")
	public Answer save() {
		return answerRepository.save(new Answer("5"));
	}
}
