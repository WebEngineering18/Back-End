package entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "answer_question")
@SequenceGenerator(
        name = "AnswerQuestionGenerator1",
        allocationSize = 1,
        initialValue = 1)
public class Answer_Question {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "AnswerQuestionGenerator1")
    @Column(name = "answer_question_id")
    private int id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "answer_id_fk")
    private Answer answer;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "question_id_fk")
    private Question question;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id_fk")
    private User user;

    public Answer_Question() {

    }

    public Answer_Question(Answer answer, Question question, User user) {
        this.answer = answer;
        this.question = question;
        this.user = user;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Answer getAnswer() {
        return answer;
    }

    public void setAnswer(Answer answer) {
        this.answer = answer;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    @Override
    public String toString() {
        return "Answer_Question [id=" + id + ", answer=" + answer + ", question=" + question + "]";
    }


}
