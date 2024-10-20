import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;

public class Student {
    private static final String url = "jdbc:mysql://localhost:3306/studentmng";
    private static final String username = "root";
    private static final String pass = "12345";

    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        try {// loading driver
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("drivers loaded ");
        } catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());

        }
        try {
            Connection connection = DriverManager.getConnection(url, username, pass);


            Studentmng studentmang=new Studentmng();
            while (true)
            {
                System.out.println(" Student managment system");
                System.out.println("1.Add new details");
                System.out.println("2.display all student");
                System.out.println("3.update");
                System.out.println("4.Delete");
                System.out.println("5. Student Details");
                System.out.println("6.Exit");

                System.out.println("choose an option");
                Scanner scanner=new Scanner(System.in);
                int choice=scanner.nextInt();

                switch (choice)
                {
                    case 1:studentmang.Add(connection,scanner);
                        break;
                    case 2:studentmang.display(connection,scanner);
                        break;
                    case 3:
                        studentmang.update(connection,scanner);
                        break;
                    case 4:studentmang.delete(connection,scanner);
                        break;
                    case 5:
                        studentmang.details(connection,scanner);
                        break;
                    case 6:System.out.println("exit");
                        return;
                    default:System.out.println("invalid choice please try again.");
                        break;

                }
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
}
