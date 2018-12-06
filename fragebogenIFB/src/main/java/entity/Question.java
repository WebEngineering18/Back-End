package entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "questions")
@SequenceGenerator(
		name = "questionGenerator",
		initialValue = 1)
public class Question {

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="questionGenerator")
	@Column(name="question_id")
	private int id;
	
	@Column(name="question", nullable = false)
	private String question;
	
	public Question() {
		super();
	}
	
	public Question(String question) {
		super();
		this.question = question;
	}



	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	@Override
	public String toString() {
		return "Questions [id=" + id + ", question=" + question + "]";
	}

	
	
	

}
