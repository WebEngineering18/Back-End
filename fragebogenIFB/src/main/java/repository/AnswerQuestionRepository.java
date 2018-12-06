package repository;

import org.springframework.data.jpa.repository.JpaRepository;

import entity.Answer_Question;

public interface AnswerQuestionRepository extends JpaRepository<Answer_Question, Integer> {

	Answer_Question findByAnswerId(int id);
	
	Answer_Question findById(int id);
}
