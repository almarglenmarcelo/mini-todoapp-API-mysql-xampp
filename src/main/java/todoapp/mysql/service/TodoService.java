package todoapp.mysql.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import todoapp.mysql.dao.TodoRepository;
import todoapp.mysql.entity.Todo;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Transactional
@Service
public class TodoService{

    private TodoRepository todoRepository;

    @Autowired
    public TodoService(TodoRepository theTodoRepository) {
        this.todoRepository = theTodoRepository;
    }


    public List<Todo> findAll() {

        return todoRepository.findAll();
    }

    public Optional<Todo> findById(int todoId) {

        return todoRepository.findById(todoId);

    }

    public void save(Todo theTodo) {
        todoRepository.save(theTodo);
    }

    public void delete(int todoId) {
        todoRepository.deleteById(todoId);
    }
}
