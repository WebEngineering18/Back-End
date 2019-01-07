package rest;

import java.util.List;

import entity.User;
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
import repository.UserRepository;

import javax.servlet.http.HttpServletRequest;

@RestController
public class AnswerController {

    @Autowired
    private AnswerRepository answerRepository;
    @Autowired
    private AnswerQuestionRepository aqRepository;

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private UserRepository userRepository;

    String singleAnswer;

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = "application/json", value = "/testAnswer")
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
                    if (!singleAnswer.equals(""))
                        saveAnswer(questionId, user);
                }
            } else {

                singleAnswer = "";
                singleAnswer += jb.get("answer");
                if (!singleAnswer.equals(""))
                    saveAnswer(questionId, user);
            }
        }
    }

    private void saveAnswer(int questionId, User user) {
        Answer antwort = new Answer(singleAnswer);
        answerRepository.save(antwort);

        if (questionRepository.existsById(questionId)) {

            Question q = questionRepository.findQuestionById(questionId);
            aqRepository.save(new Answer_Question(antwort, q, user));
        }
    }

    @GetMapping("/findAnswersWithQuestions")
    public String findAnswersWithQuestions() {

        List<Answer_Question> list = aqRepository.findAll();
        String question = "";
        String answer = "";
        String result = "";

        for (Answer_Question aq : list) {


            if (question.contains(aq.getQuestion().getQuestion())) {

                answer = aq.getAnswer().getAnswer();
                result += "," + answer;

            } else {
                question = "<br>" + aq.getQuestion().getQuestion() + ": ";
                answer = aq.getAnswer().getAnswer();

                result += question + answer;
            }

        }
        return result;
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
