package dao;

import model.Todo;

import java.util.List;

public interface TodoDao {

    /**
     *  @param todo
     *  @return true if successful, false otherwise
     */

    boolean addTodo(Todo todo);

    /**
     *  Retrieves all Todo items form the data base
     *  @return A list containing all Todos
     */

    List<Todo> getAllTodos();


    /**
     * Update an existing Todo item's title, descripiton , and status
     *  @param todo
     *  @return true if successful, false otherwise
     */


    boolean updateTodos(Todo todo);


    /**
     * Deletes a Todo by its ID
     *  @param id
     *  @return true if successful, false otherwise
     */

    boolean deleteTodo(Long id);


    boolean marksAsCompleted(Long id);


}
