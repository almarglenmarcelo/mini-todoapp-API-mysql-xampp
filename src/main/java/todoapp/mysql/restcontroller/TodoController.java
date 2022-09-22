package todoapp.mysql.restcontroller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import todoapp.mysql.entity.Todo;
import todoapp.mysql.service.TodoService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class TodoController{


    private TodoService todoService;

    @Autowired
    public TodoController(TodoService theTodoService) {
        this.todoService = theTodoService;
    }


    @GetMapping("/todos")
    public List<Todo> findAll() {
        return todoService.findAll();
    }


    @GetMapping("/todos/{todoId}")
    public String findTodo(@PathVariable int todoId) {

        Optional<Todo> tempTodo = todoService.findById(todoId);

        Todo todo = null;

        if(!tempTodo.isEmpty()) {
            todo = tempTodo.get();

        }else {
            return "Todo does not exist!";
        }

        return "Here is the Todo " + todo;
    }

    @PutMapping("/todos")
    public String updateTodo(@RequestBody Todo theTodo) {

        try {
            todoService.save(theTodo);

            return "Your task has been updated!";
        }catch(Exception e) {
            return e.getMessage();
        }
    }

    @PostMapping("/todos")
    public String saveTodo(@RequestBody Todo theTodo) {

        todoService.save(theTodo);

        Optional<Todo> tempTodo = todoService.findById(theTodo.getId());

        Todo todo = null;
        if(tempTodo.isPresent()) {
            todo = tempTodo.get();
        }

        return "The task " + theTodo.getTaskName() + " has successfully saved! Here is the object's id " + todo.getId() ;
    }


    @DeleteMapping("/todos/{todoId}")
    public String deleteTodo(@PathVariable int todoId) {

        Optional<Todo> tempTodo = todoService.findById(todoId);

        if(tempTodo.isEmpty()) {
            return "Todo Does not Exist!";
        }

        todoService.delete(todoId);

        return "The Todo - '" + tempTodo.get().getTaskName() +"' has been deleted!";

    }

}
