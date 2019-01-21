package rest;


import java.util.List;
import java.util.Map;
import app.ExcelView;
import entity.User;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import entity.Answer;
import entity.Answer_Question;
import entity.Question;
import org.springframework.web.servlet.ModelAndView;
import repository.AnswerQuestionRepository;
import repository.AnswerRepository;
import repository.QuestionRepository;
import repository.UserRepository;
import javax.servlet.http.HttpServletRequest;
 

/**
 * Controller zum Speicern der Antworten einer fertigen Umfrage in der Datenbank und zum Downloaden der Excel-Datei.
 * 
 * @author Frank Köhn, Michael Nickel
 */
@RestController
public class UmfrageController {

	@Autowired
	private AnswerRepository answerRepository;
	@Autowired
	private AnswerQuestionRepository aqRepository;

	@Autowired
	private QuestionRepository questionRepository;

	@Autowired
	private UserRepository userRepository;

	String singleAnswer;
        
        
        /**
         * Verarbeitet das JSONArray mit den Antworten einer Umfrage und speichert diese in der Datenbank.<br>
         * <br>
         * Das gesamte JSONArray wird durch iteriert und jede Antwort wird mit der saveAnswer()-Methode gespeichert in der Answers und
         * in der Answer_Question Tabelle gespeichert.
         * @param answer JSONArray, welches alle Antworten einer fertigen Umfrage beinhaltet
         * @param request wird gebraucht um aus dem HTTP Request die IP-Adresse des Users zu bekommen
         */
        @CrossOrigin(origins = "http://localhost:8080")
	@PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = "application/json", value = "/saveAnswers")
	public void create(@RequestBody final String answer, HttpServletRequest request) {

		User user = new User(request.getRemoteAddr());
		userRepository.save(user);

		JSONArray wholeArray = new JSONArray(answer);

		for (int i = 0; i < wholeArray.length(); i++) {

			JSONObject jb = wholeArray.getJSONObject(i);
			int questionId = jb.getInt("question_id");
			JSONArray singleAnswerArray = jb.optJSONArray("answer");

			if (singleAnswerArray != null) {

				for (int j = 0; j < singleAnswerArray.length(); j++) {

					singleAnswer = "";
					singleAnswer += singleAnswerArray.get(j);

					if(singleAnswer.equals("null") || singleAnswer.equals("")){
						singleAnswer = "0";
					}

					saveAnswer(questionId, user);
				}
			} else {

				singleAnswer = "";
				singleAnswer += jb.get("answer");

				saveAnswer(questionId, user);
			}
		}
	}
        
        /**
         * Methode für das Speichern einer Antwort in der Datenbank.
         * 
         * @param questionId ID einer Frage
         * @param user IP-Adresse des Users der die Umfrage ausgefüllt hat
         */
	private void saveAnswer(int questionId, User user) {
		Answer antwort = new Answer(singleAnswer);
		answerRepository.save(antwort);

		if (questionRepository.existsById(questionId)) {

			Question q = questionRepository.findQuestionById(questionId);
			aqRepository.save(new Answer_Question(antwort, q, user));
		}
	}

        /**
         * Lädt eine Excel-Datei mit allen Daten aus der Datenbank herunter.
         * @param model enthält alle Daten
         * @return eine Excel-Datei
         */
        @CrossOrigin(origins = "http://localhost:8080")
	@GetMapping(value = "/download")
	public ModelAndView download(Model model) {

		model.addAttribute("answer_questions", aqRepository.findAll());
		model.addAttribute("questions", questionRepository.findAll());
		model.addAttribute("users", userRepository.findAll());
		return new ModelAndView(new ExcelView(), (Map<String, ?>) model);
	}

        /**
         * Liefert eine bestimmte Antwort zurück.
         * @param id ID der gesuchten Antwort
         * @return die Antwort nach der gesucht wurde
         */
	@GetMapping("/answer/{id}")
	public Answer getAnswerById(@PathVariable(value = "id") int id) {
		return answerRepository.findAnswerById(id);
	}
        
        /**
         * Liefert alle Antworten die in der Datenbank gespeichert sind zurück.
         * 
         * @return Alle Antworten in der Datenbank
         */
	@GetMapping("/findAllAnswers")
	public String findAll() {

		List<Answer> list = answerRepository.findAll();
		String result = "";
		for (Answer answer : list) {
			result += answer.toString() + "<br>";
		}
		return result;
	}

        /**
         * Löscht alle Daten aus der Answers sowie Answer_Question Tabelle.
         * @return Bestätigungsnachricht
         */
	@RequestMapping("/deleteAnswers")
	public String deleteAll() {
                aqRepository.deleteAll();
		answerRepository.deleteAll();
		return "Alle Daten aus der Answers und der Answers_Questions Tabelle gelöscht";
	}

}
