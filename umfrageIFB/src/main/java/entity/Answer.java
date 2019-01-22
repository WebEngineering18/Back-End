package entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * Entität für die Answers Tabelle.
 * 
 * @author Michael Nickel
 */
@Entity
@Table(name="answers")
@SequenceGenerator(
		name = "AnswerSequence",
		allocationSize = 1,
		initialValue = 1)
public class Answer extends Created_At {

	/**
	 * 
	 */
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="AnswerSequence")
	@Column(name="answer_id")
	private int id;
	
	@Column(name="answer")
	private String answer;
	
	public Answer() {
		super();
	}

	public Answer(String answer) {
		super();
		this.answer = answer;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	@Override
	public String toString() {
		return "Answer [id=" + id + ", answer=" + answer + ", time=" + getCreatedAt() + "]";
	}
	
	
	
	
	
	
	
	

}
