package dao;

import model.Todo;
import model.TodoStatus;
import util.DatabaseUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;



public class TodoDaoImpl implements TodoDao{

    private static  final String SELECT_ALL_TODO = "SELECT * from todos";
    private static  final String INSERT_TODO = "INSERT INTO todos(title,description,status) VALUES(?,?,?)";
    private static final String UPDATE_TODO = "UPDATE todos set title= ? , description=? , status= ? WHERE id =?";
    private static final String MARK_COMPLETED = "UPDATE todos set status =? WHERE id =?";

    private static final String DELETE_TODO = "DELETE FROM todos WHERE id = ?";

    @Override


    public boolean addTodo(Todo todo) {

        try(
                Connection connection = DatabaseUtil.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(INSERT_TODO);)
        {

            preparedStatement.setString(1,todo.getTitle());
            preparedStatement.setString(2, todo.getDescription());
            preparedStatement.setString(3,todo.getStatus().name());


        int rowAffected =     preparedStatement.executeUpdate();
        return  rowAffected > 0;



        }catch (SQLException e){
           handleSQLException(e);

           return  false;
        }


    }

    @Override
    public List<Todo> getAllTodos() {

        List<Todo> todos = new ArrayList<>();

       try( Connection connection = DatabaseUtil.getConnection();

            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_TODO );
            ResultSet resultSet = preparedStatement.executeQuery();)
       {

          while (resultSet.next()){
              Long id = resultSet.getLong("id");
              String title = resultSet.getString("title");
              String description = resultSet.getString("description");
              String status = resultSet.getString("status");
              Timestamp createAt = resultSet.getTimestamp("created_at");

              Todo todo = new Todo(id, title,description, TodoStatus.valueOf(status),createAt);
              todos.add(todo);
          }


       }catch (SQLException e){
         handleSQLException(e);
       }
        return todos;
    }

    @Override
    public boolean updateTodos(Todo todo) {
 Connection connection = null;
      try {
          connection = DatabaseUtil.getConnection();

          connection.setAutoCommit(false);

          try( PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_TODO)) {

              preparedStatement.setString(1, todo.getTitle());
              preparedStatement.setString(2,todo.getDescription());
              preparedStatement.setString(3,todo.getStatus().name());
              preparedStatement.setLong(4,todo.getId());

              int rowAffected = preparedStatement.executeUpdate();
              // Commit the transaction if the execution was successful
              connection.commit();

              return  rowAffected >0;

          }catch ( SQLException e){
              connection.rollback();
              throw  e;
          }

      } catch (SQLException e) {
          handleSQLException(e);
          return  false;
      } finally {
          if(connection != null){
             try {
                 connection.setAutoCommit(true);
                 connection.close();
             } catch (SQLException e){
                 e.printStackTrace();;
             }
          }
      }

    }

    @Override
    public boolean deleteTodo(Long id) {
       try(  Connection connection = DatabaseUtil.getConnection();

             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_TODO);) {
           preparedStatement.setLong(1,id);

           return  preparedStatement.executeUpdate() > 0;



       }catch (SQLException e){
           handleSQLException(e);
           return  false;
       }
       }

    @Override
    public boolean marksAsCompleted(Long id) {

        try(  Connection connection = DatabaseUtil.getConnection();

              PreparedStatement preparedStatement = connection.prepareStatement(MARK_COMPLETED);) {
            preparedStatement.setString(1, TodoStatus.COMPLETED.name());
            preparedStatement.setLong(2,id);

            return  preparedStatement.executeUpdate() > 0;



        }catch (SQLException e){
            handleSQLException(e);
            return  false;
        }
    }

    private void handleSQLException(SQLException ex){
        System.err.println("--- SQLException Caught ---");
        System.err.println("SQLState: "+ ex.getSQLState());
        System.err.println("Error Code: "+ ex.getErrorCode());
        System.err.println("Message : "+ ex.getMessage());
        ex.printStackTrace();


    }
}
