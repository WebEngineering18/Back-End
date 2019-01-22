package repository;

import org.springframework.data.jpa.repository.JpaRepository;

import entity.Answer;

/**
 * Repository, welches Methoden für Answer Entities liefert.
 * 
 * @author Micha2
 */
public interface AnswerRepository extends JpaRepository<Answer, Integer> {

	Answer findAnswerById(int id);

}
