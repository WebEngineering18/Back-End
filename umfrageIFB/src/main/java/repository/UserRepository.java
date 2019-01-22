package repository;

import entity.Answer;
import entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository, welches Methoden für User Entities liefert.
 * @author Frank Köhn
 */
public interface UserRepository extends JpaRepository<User, Integer> {

	User findUserById(int id);

}
