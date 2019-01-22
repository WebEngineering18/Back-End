package repository;


import org.springframework.data.jpa.repository.JpaRepository;

import entity.Question;

/**
 * Repository, welches Methoden für Question Entities liefert.
 * 
 * @author Michael Nickel
 */
public interface QuestionRepository extends JpaRepository<Question, Integer> {
	
	Question findQuestionById(int id);

}
