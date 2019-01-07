package rest;

import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import entity.Answer;
import entity.Answer_Question;
import entity.Question;
import repository.AnswerQuestionRepository;
import repository.AnswerRepository;
import repository.QuestionRepository;

@RestController
public class AnswerController {

	@Autowired
	private AnswerRepository answerRepository;
	@Autowired
	private AnswerQuestionRepository aqRepository;
	@Autowired
	private QuestionRepository questionRepository;
	String singleAnswer;

	@PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = "application/json", value = "/testAnswer")
	public void create(@RequestBody final String answer) {

		JSONArray wholeArray = new JSONArray(answer);
		
		for (int i = 0; i < wholeArray.length(); i++) {
			
			JSONObject jb = wholeArray.getJSONObject(i);
			int questionId = jb.getInt("question_id");
			JSONArray singleAnswerArray = jb.optJSONArray("answer");
			
			if (singleAnswerArray != null) {
				
				for (int j = 0; j < singleAnswerArray.length(); j++) {
					
					singleAnswer = "";
					singleAnswer += singleAnswerArray.get(j);
					if (!singleAnswer.equals(""))
					saveAnswer(questionId);
				}
			} else {
				
				singleAnswer = "";
				singleAnswer += jb.get("answer");
				if (!singleAnswer.equals(""))
				saveAnswer(questionId);
			}
		}
	}

	private void saveAnswer(int questionId) {
		Answer antwort = new Answer(singleAnswer);
		answerRepository.save(antwort);
		
		if (questionRepository.existsById(questionId)) {
			
			Question q = questionRepository.findQuestionById(questionId);
			aqRepository.save(new Answer_Question(antwort, q));
		}
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

}
