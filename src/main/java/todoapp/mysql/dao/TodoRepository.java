package todoapp.mysql.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import todoapp.mysql.entity.Todo;


@Repository
public interface TodoRepository extends JpaRepository<Todo, Integer> {
}
