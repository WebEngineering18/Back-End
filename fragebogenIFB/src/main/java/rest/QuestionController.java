package rest;



import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import entity.Question;
import repository.QuestionRepository;

@RestController
public class QuestionController {
	
	@Autowired
	private QuestionRepository questionRepository;

	
	@RequestMapping("/saveAllQuestions")
	public String saveQuestion() {
		questionRepository.deleteAll();
		questionRepository.saveAll(Arrays.asList(new Question("Fachbereich Ihres Unternehmens"),new Question("Tätig als"),
				new Question("Gesamtanzahl Bauvorhaben / Projekte in den letzten 5 Jahren"),new Question("Bauvorhaben / Projekte"),
				new Question("Festgestellte Planungsschäden, Anzahl insgesamt"),new Question("Festgestellte Schäden im Einzelnen"),
				new Question("Probleme bei der Vergabe"),new Question("Benennung der Probleme"),
				new Question("Festgestellte Ausführungsschäden, Anzahl insgesamt"),new Question("Besonders schadensanfällige Bauteile, Schadenstellen"),
				new Question("Schadenursache"),new Question("Probleme bei der Abnahme"),
				new Question("Benennung der Probleme"),new Question("Festgestellte Schäden"),
				new Question("Schäden an eigener Leistung"),new Question("Regulierte Mangel- / Schadenbeseitigungskosten durch Versicherungen"),
				new Question("Bei einem Selbstbehalt von"),new Question("Festgestellte Bauschadenkosten"),
				new Question("Wie hat sich die Anzahll der Bauschäden in Ihrem Fachbereich in den letzten 5 Jahren verändert?"),
				new Question("Wie hat sich die Bauqualität in Deutschland in den letzten 5 Jahren verändert")));
		
		return "Alle Fragen gespeichert";
	}
	
	@GetMapping("/findAllQuestions")
	public String findAll() {
		
		List<Question> list = questionRepository.findAll();
		String result = "";
		for (Question question : list) {
			result += question.toString() + "<br>";
		}
		return result;
	}
	
	@GetMapping("/question/{id}")
	public Question getQuestionById(@PathVariable(value = "id") int id) {
		return questionRepository.findQuestionById(id);
	}
	
	

}
