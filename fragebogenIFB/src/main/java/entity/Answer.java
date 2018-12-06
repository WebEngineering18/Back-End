package entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="answers")
@SequenceGenerator(
		name = "AnswerGenerator1",
		allocationSize = 1,
		initialValue = 1)
public class Answer extends Created_At {

	/**
	 * 
	 */
	private static final long serialVersionUID = -584324990481516603L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="AnswerGenerator1")
	@Column(name="answer_id")
	private int id;
	
	@Column(name="answer")
	private String answer;
	
	protected Answer() {
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
