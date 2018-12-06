package repository;


import org.springframework.data.jpa.repository.JpaRepository;

import entity.Question;

public interface QuestionRepository extends JpaRepository<Question, Integer> {
	
	Question findQuestionById(int id);

}
