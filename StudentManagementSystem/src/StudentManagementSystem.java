import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

public class StudentManagementSystem {

    static Scanner sc = new Scanner(System.in);

    // ADD STUDENT
    public static void addStudent() {

        String sql = "INSERT INTO students(name, email, course, age) VALUES (?, ?, ?, ?)";

        try {

            Connection con = DBConnection.getConnection();

            System.out.print("Enter student name: ");
            String name = sc.nextLine();

            System.out.print("Enter email: ");
            String email = sc.nextLine();

            System.out.print("Enter course: ");
            String course = sc.nextLine();

            System.out.print("Enter age: ");
            int age = sc.nextInt();
            sc.nextLine();

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, name);
            ps.setString(2, email);
            ps.setString(3, course);
            ps.setInt(4, age);

            int result = ps.executeUpdate();

            if (result > 0) {
                System.out.println("Student added successfully!");
            }

            ps.close();
            con.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    // VIEW STUDENTS
    public static void viewStudents() {

        String sql = "SELECT * FROM students";

        try {

            Connection con = DBConnection.getConnection();

            PreparedStatement ps = con.prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            System.out.println("\n===== STUDENT LIST =====");

            boolean found = false;

            while (rs.next()) {

                found = true;

                System.out.println("ID     : " + rs.getInt("id"));
                System.out.println("Name   : " + rs.getString("name"));
                System.out.println("Email  : " + rs.getString("email"));
                System.out.println("Course : " + rs.getString("course"));
                System.out.println("Age    : " + rs.getInt("age"));

                System.out.println("-------------------------");
            }

            if (!found) {
                System.out.println("No students found.");
            }

            rs.close();
            ps.close();
            con.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    // UPDATE STUDENT
    public static void updateStudent() {

        String sql = "UPDATE students SET name=?, email=?, course=?, age=? WHERE id=?";

        try {

            Connection con = DBConnection.getConnection();

            System.out.print("Enter student ID to update: ");
            int id = sc.nextInt();
            sc.nextLine();

            System.out.print("Enter new name: ");
            String name = sc.nextLine();

            System.out.print("Enter new email: ");
            String email = sc.nextLine();

            System.out.print("Enter new course: ");
            String course = sc.nextLine();

            System.out.print("Enter new age: ");
            int age = sc.nextInt();
            sc.nextLine();

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, name);
            ps.setString(2, email);
            ps.setString(3, course);
            ps.setInt(4, age);
            ps.setInt(5, id);

            int result = ps.executeUpdate();

            if (result > 0) {
                System.out.println("Student updated successfully!");
            } else {
                System.out.println("Student with ID " + id + " not found.");
            }

            ps.close();
            con.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    // DELETE STUDENT
    public static void deleteStudent() {

        String sql = "DELETE FROM students WHERE id=?";

        try {

            Connection con = DBConnection.getConnection();

            System.out.print("Enter student ID to delete: ");
            int id = sc.nextInt();
            sc.nextLine();

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setInt(1, id);

            int result = ps.executeUpdate();

            if (result > 0) {
                System.out.println("Student deleted successfully!");
            } else {
                System.out.println("Student with ID " + id + " not found.");
            }

            ps.close();
            con.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    // MAIN METHOD
    public static void main(String[] args) {

        while (true) {

            System.out.println("\n=================================");
            System.out.println("     STUDENT MANAGEMENT SYSTEM");
            System.out.println("=================================");
            System.out.println("1. Add Student");
            System.out.println("2. View Students");
            System.out.println("3. Update Student");
            System.out.println("4. Delete Student");
            System.out.println("5. Exit");
            System.out.println("=================================");

            System.out.print("Enter your choice: ");

            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {

                case 1:
                    addStudent();
                    break;

                case 2:
                    viewStudents();
                    break;

                case 3:
                    updateStudent();
                    break;

                case 4:
                    deleteStudent();
                    break;

                case 5:
                    System.out.println("Thank you for using Student Management System!");
                    sc.close();
                    System.exit(0);

                default:
                    System.out.println("Invalid choice! Please try again.");
            }
        }
    }
}