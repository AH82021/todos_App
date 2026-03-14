import model.TodoStatus;
import service.TodoService;

import java.sql.CallableStatement;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        TodoService service = new TodoService();

        try ( Scanner scanner = new Scanner(System.in)){
            boolean running = true;
            while (running){
              printMenu();
                System.out.println("Choose an option: ");
                int choice;


                try {
                    choice = Integer.parseInt(scanner.nextLine());
                } catch (NumberFormatException e) {
                    System.out.println("Invalid input. Please enter a valid number");
                    continue;
                }

                switch (choice) {

                    case 1 :
                        System.out.println("Please enter todo title");
                        String title = scanner.nextLine();
                        System.out.println("Please enter todo description");
                        String description = scanner.nextLine();
                        service.createTodo(title,description);
                        break;
                    case 2:
                       service.showAllTodos();
                        break;

                    case 3:
                        System.out.println("Enter the ID of the Todo to update: ");
                        Long updateId = readLongInput(scanner);
                        if(updateId == null){
                            break;
                        }



                        System.out.println("Enter new title: ");

                        String newTitle = scanner.nextLine();

                        System.out.println("Enter new description: ");

                        String newDesc = scanner.nextLine();

                        System.out.println("Enter new status (1 for PENDING, 2 for COMPLETED");

                        String statusChoice = scanner.nextLine();
//                         if(statusChoice.equals("1")){
//                             TodoStatus newStatus = TodoStatus.PENDING;
//                         }else if(statusChoice.equals("2")){
//                             TodoStatus newStatus = TodoStatus.COMPLETED;
//                         }

                        TodoStatus newStatus = statusChoice.equals("2")  ?  TodoStatus.COMPLETED   : TodoStatus.PENDING;

                        service.updateTodo(updateId,newTitle,newDesc,newStatus);


                        break;

                    case 4:
                        System.out.println("Enter the ID of the todo to delete: ");
                        Long id = readLongInput(scanner);
                        service.deleteTodo(id);
                        break;

                    case 5:
                        System.out.println("Enter the ID of the Todo to mark as completed: ");
                        Long completedId = readLongInput(scanner);

                        if(completedId != null){
                            service.completeTodo(completedId);
                        }
                        break;

                    case 6 :
                        running = false;
                        System.out.println("Exiting application. Good bye");
                    default:
                        System.out.println(" Invalid choice.PLease select an option between i and 6 ");

                }

            }

        }


    }


    public static void printMenu(){
        System.out.println("--MAIN MENU----");
        System.out.println("1. Add a new Todo");
        System.out.println("2. List all Todos");
        System.out.println("3. Update a todo");
        System.out.println("4. Delete a todo");
        System.out.println("5. Mark a Todo as completed");
        System.out.println("6. Exit application.");
    }

    private static Long readLongInput(Scanner scanner){
        try {
            return Long.parseLong(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Invalid ID format. Please enter a valid number.");
            return null;
        }
    }
}
