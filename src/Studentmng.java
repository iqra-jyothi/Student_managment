import java.sql.*;
import java.util.Scanner;

public class Studentmng {
    private Connection connection;
    private Scanner scanner;

    void Add(Connection connection, Scanner scanner) {
        scanner.nextLine();
        System.out.println("enter Roll number");
        int number = scanner.nextInt();
        scanner.nextLine();
        System.out.println("enter name of student");
        String name = scanner.nextLine();
//        scanner.nextLine();
        System.out.println("enter student marks");
        String marks = scanner.nextLine();
        String sql = "insert into  student(rollno,name,marks)values(?,?,?)";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, number);
            preparedStatement.setString(2, name);
            preparedStatement.setString(3, marks);
            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("sucessfully created");
            } else {
                throw new RuntimeException("details unable to Create!!");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
//        throw new RuntimeException("roll number allready esist");
    }

    void details(Connection connection, Scanner scanner) {
        System.out.println("enter the roll  number");
        int number = scanner.nextInt();
        String sql = "select * from student where rollno=?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, number);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                System.out.println("roll number" + resultSet.getInt("rollno"));
                System.out.println("student name" + resultSet.getString("name"));
                System.out.println("student marks" + resultSet.getString("marks"));
            } else {
                System.out.println("not found");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    void display(Connection connection, Scanner scanner) {
        String sql = "select * from student";
        try {

            Statement start = connection.createStatement();
            ResultSet rs = start.executeQuery(sql);
            while (rs.next()) {
                int id = rs.getInt("rollno");
                String name = rs.getString("name");
                String marks = rs.getString("marks");

                System.out.println();
                System.out.println("********************");
                System.out.println("rollno: " + id + " name: " + name + " marks " + marks);//+"  salary:   "+salary+"  job titla:  "+job_title);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    void delete(Connection connection, Scanner scanner) {
        scanner.nextLine();
        System.out.println("enter the roll number");
        int roll = scanner.nextInt();
        String sql = "delete from student where rollno=?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, roll);
            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("sucessfully deleted " + roll);
            } else {
                throw new RuntimeException(" unable to delete!!");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    void update(Connection connection, Scanner scanner) {
        scanner.nextLine();
        System.out.println("enter the roll number");
        int roll = scanner.nextInt();
        scanner.nextLine();
        System.out.println("enter the name");
        String name = scanner.nextLine();
//        scanner.nextLine();
        System.out.println("enter the marks");
        String marks = scanner.nextLine();
        String sql = "update student set name=? ,marks=? where rollno=?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, marks);
            preparedStatement.setInt(3, roll);
            int row = preparedStatement.executeUpdate();
            if (row > 0) {
                System.out.println("sucessfully updated");
            } else {
                System.out.println("not present such roll no ");
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}

