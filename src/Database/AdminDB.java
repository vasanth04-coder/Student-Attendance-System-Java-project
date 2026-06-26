package Database;

import Model.Enum.Gender;
import Model.Enum.Departments;
import Model.Enum.Years;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

public class AdminDB
{
    public static void pendingStaffs()
    {
        try
        {
            String query = " SELECT * FROM staffs WHERE approval = false";
            Connection con = DBConnection.connection();
            PreparedStatement pst = con.prepareStatement(query);


            ResultSet rs = pst.executeQuery();

            boolean found = false;

            while(rs.next())
            {
                found = true;
                System.out.println("Pending staffs :"+ "Name :"+rs.getString("name")+ " Id:"+rs.getString("id"));
            }

            if(!found)
            {
              System.out.println("No pending Staffs..");
            }
            rs.close();
            pst.close();
            con.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public static void approveStaffs(String id)
    {
        try
        {
            String query = "UPDATE staffs SET approval = ? WHERE id = ?";
            Connection con = DBConnection.connection();
            PreparedStatement pst = con.prepareStatement(query);

            pst.setBoolean(1,true);
            pst.setString(2,id);

            pst.executeUpdate();

            pst.close();
            con.close();
        }

        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    public static void assignClasses(String id,Scanner scan)
    {
        try
        {
            String query = " UPDATE staffs SET advisor_department = ?,advisor_year = ? WHERE id =?";
            Connection con = DBConnection.connection();
            PreparedStatement pst = con.prepareStatement(query);

            Departments department;

            while(true)
            {
                System.out.println(" Enter Department ( CSE / IT / ECE / MECH / CIVIL / EEE / AUTOMOBILE / AGRI ) : ");
                String dept = scan.nextLine().toUpperCase();
                try
                {
                    department = Departments.valueOf(dept);
                    break;
                }
                catch (Exception e)
                {
                    System.out.println(" Invalid Input try again ");
                }
            }

            Years year;

            while(true)
            {
                System.out.println("Enter year -: ( FIRST_YEAR / SECOND_YEAR / THIRD_YEAR / FINAL_YEAR )");

                try
                {
                    String y = scan.nextLine().toUpperCase().trim();
                    year = Years.valueOf(y);
                    break;
                }
                catch (Exception e)
                {
                    System.out.println("Invalid input");
                }
            }

            pst.setString(1,department.name());
            pst.setString(2,year.name());
            pst.setString(3,id);

            int row = pst.executeUpdate();

            if(row>0)
            {
                System.out.println("Updated Suceesfully..");
            }

            pst.close();
            con.close();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    public static void viewStudents(Years year,Departments dept)
    {
       try
       {
           String query = " SELECT * FROM STUDENTS WHERE student_year = ? AND department = ?";
           Connection con = DBConnection.connection();
           PreparedStatement pst = con.prepareStatement(query);

           pst.setString(1,year.name());
           pst.setString(2,dept.name());

           ResultSet rs = pst.executeQuery();

           while(rs.next())
           {
             System.out.println("Name : " +rs.getString("name") +
                                "RegNumber : " +rs.getString("register_number") +
                                "Department :" +Departments.valueOf(rs.getString("department")) +
                                "Year :" +Years.valueOf(rs.getString("student_year")) +
                                "Gender :" + Gender.valueOf(rs.getString("gender"))
                                 );

           }
       }

       catch (Exception e)
       {
          e.printStackTrace();
       }

    }
}