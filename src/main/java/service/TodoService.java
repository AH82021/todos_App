package service;

import dao.TodoDao;
import dao.TodoDaoImpl;
import model.Todo;
import model.TodoStatus;

import java.util.List;

public class TodoService {

    private final TodoDao todoDao;


    public TodoService() {
        this.todoDao = new TodoDaoImpl();
    }




    public  void  showAllTodos(){

      List<Todo> todos = todoDao.getAllTodos();

      if(todos.isEmpty()){
          System.out.println("No Todos found. Add one to get started");
      } else {
          System.out.println(" Your todo List ");
          for (Todo todo :todos){
              System.out.println(todo.toString());
          }

          System.out.println("----------------\n");
      }

    }


    public  void  createTodo(String title, String description){


        if(title == null || title.trim().isEmpty()){
            System.err.println("Error: Title cannot be empty.");
            return;
        }

        Todo newTodo = new Todo(title,description, TodoStatus.PENDING);

       boolean   success =  todoDao.addTodo(newTodo);

       if(success){
           System.out.println(" Todo Create successfully ");
       } else {
           System.err.println(" Failed to create Todo.");
       }


    }

    public  void  updateTodo(Long id, String newTitle, String newDescription, TodoStatus newStatus){

        if(newTitle == null || newTitle.trim().isEmpty()){
            System.err.println("Error: Title cannot be empty.");
            return;
        }

        Todo todoUpdate = new Todo();
         todoUpdate.setId(id);
         todoUpdate.setTitle(newTitle);
         todoUpdate.setDescription(newDescription);
         todoUpdate.setStatus(newStatus);

         boolean success =   todoDao.updateTodos(todoUpdate);

        if(success){
            System.out.println(" Todo Updated successfully ");
        } else {
            System.err.println(" Failed to create Todo. Please Check if ID exists");
        }

    }


    public void deleteTodo(Long id){
        boolean success = todoDao.deleteTodo(id);

        if(success){
            System.out.println(" Todo Deleted successfully ");
        } else {
            System.err.println(" Failed to delete Todo. Please check if ID exits");
        }
    }


    public void completeTodo(Long id){
        boolean success = todoDao.marksAsCompleted(id);

        if(success){
            System.out.println(" Todo marked as COMPLETED! ");
        } else {
            System.err.println(" Failed to mart  Todo as completed. Please check if ID exits");
        }
    }



}
