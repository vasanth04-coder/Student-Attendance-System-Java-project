package Database;

import Model.Enum.Years;
import Model.Enum.Departments;
import Model.Enum.Gender;
import Model.Student;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class StudentDB
{
    public static void studentRegister(Student s)
    {
        try
        {
            String query = "INSERT INTO students (register_number,name,password,department,student_year,gender) values (?,?,?,?,?,?)";
            Connection con = DBConnection.connection();
            PreparedStatement pst = con.prepareStatement(query);

            pst.setString(1, s.getRegisterNumber());
            pst.setString(2, s.getName());
            pst.setString(3, s.getPassword());
            pst.setString(4, s.getDepartment().name());
            pst.setString(5, s.getYear().name());
            pst.setString(6, s.getGender().name());

            pst.executeUpdate();

            pst.close();
            con.close();

            System.out.println("Registration Successfull ");

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public static Student studentLogin(String register_number, String password)
    {
          try
          {
            String query = "SELECT * FROM students WHERE register_number = ? AND password =?";

            Connection con = DBConnection.connection();

            PreparedStatement pst = con.prepareStatement(query);

            pst.setString(1, register_number);
            pst.setString(2, password);

            ResultSet rs = pst.executeQuery();

            if (rs.next())
            {
                Student s = new Student(rs.getString("register_number"),
                                        rs.getString("name"),
                                        rs.getString("password"),
                                        Departments.valueOf(rs.getString("department")),
                                        Years.valueOf(rs.getString("student_year")),
                                        Gender.valueOf(rs.getString("gender")));
                return s;

            }
            rs.close();
            pst.close();
            con.close();

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

          return null;
    }
}
