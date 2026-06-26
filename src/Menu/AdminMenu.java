package Menu;

import Database.AdminDB;
import Database.DBConnection;
import Helper.AttendanceHelper;
import Model.*;
import Model.Enum.Years;
import Model.Enum.Gender;
import Model.Enum.Departments;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;


public class AdminMenu
{
  public static void adminlogin(Scanner scan, ArrayList <Admin> admin, ArrayList<Student>students, ArrayList<Staff>staff, Semester sem, ArrayList<Attendance>attendances)
  {
      while(true)
      {
          System.out.println("ADMIN MENU");
          System.out.println();
          System.out.println("1.Login");
          System.out.println("0.Logout");

          System.out.println("Enter your choice:");
          int choice = scan.nextInt();
          scan.nextLine();

          switch (choice)
          {
              case 1:
                  login(scan,students,staff,sem,attendances);
                  break;
              case 0:
                  System.out.println("Returning to the Main menu...");
                  return;
              default:
                  System.out.println("Invalid option ");
                  break;
          }

      }
  }
  private static void login(Scanner scan, ArrayList<Student>students, ArrayList<Staff>staff, Semester sem, ArrayList<Attendance>attendances)
  {
       String  adminId = "admin123";
       String  password = "qwer";

      System.out.println("Enter your id :");
      String id = scan.nextLine();
      System.out.println("Enter your password :");
      String pass = scan.nextLine();

      if(adminId.equals(id) && password.equals(pass))
      {
          System.out.println("Login Succesfully ");
          adminDashboard(scan,students,staff,sem,attendances);
      }
      else
      {
          System.out.println("Invalid Credentials..");
      }
  }

  private static void adminDashboard(Scanner scan, ArrayList<Student>students, ArrayList<Staff>staff,  Semester sem, ArrayList<Attendance>attendances)
  {
      while(true)
      {
          System.out.println("ADMIN DASHBOARD");
          System.out.println();
          System.out.println(" 1. View students by year and department");
          System.out.println(" 2. View the class Advisors by year and department");
          System.out.println(" 3. View staff pending approvals ");
          System.out.println(" 4. Approve staffs");
          System.out.println(" 5. Assign staffs to classes");
          System.out.println(" 6. View below 75% students ");
          System.out.println(" 7. Add staffs ");
          System.out.println(" 8. Remove staffs");
          System.out.println(" 9. Set Semester StartDate");
          System.out.println(" 10. Set Semester EndDate");
          System.out.println(" 0. Exit");

          System.out.println("Enter the choice :");
          int choice = scan.nextInt();
          scan.nextLine();


          switch (choice)
          {
              case 1:
                  viewStudents(students,scan);
              break;
              case 2:
                  viewStaffs(staff,scan);
                  break;
              case 3:
                  pendingApprovals();
                  break;
              case 4:
                  approvals(staff,scan);
                  break;
              case 5:
                  assignStaffs(scan,staff);
                  break;
              case 6:
                  viewBelow75Percentage(attendances,sem,students);
                  break;
              case 7:
                  addStaff(staff,scan);
                  break;
              case 8:
                  removeStaff(staff,scan);
                  break;
              case 9:
                  stDate(scan,sem);
                  break;
              case 10:
                  endDate(scan,sem);
                  break;
              case 0:
                  System.out.println("Logging out...");
                  return;
          }
      }
  }

  private static void viewStudents(ArrayList<Student>students,Scanner scan)
  {
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


      Departments department;

      while(true)
      {
          System.out.println("Enter the Department ( CSE / IT / ECE / MECH / CIVIL / EEE / AUTOMOBILE / AGRI ) : ");
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
      AdminDB.viewStudents(year,department);
  }

  private static void viewStaffs(ArrayList<Staff>staff,Scanner scan)
  {
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

      Departments department;

      while(true)
      {
          System.out.println("Enter your Department ( CSE / IT / ECE / MECH / CIVIL / EEE / AUTOMOBILE / AGRI ) : ");
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

      for(Staff s:staff)
      {
          if(s.getAdvisorOfYear().equals(year) && s.getAdvisorOfDepartment().equals(department))
          {
               System.out.println(s.getId()+" "+s.getName()+" "+s.getAdvisorOfYear()+" "+s.getAdvisorOfDepartment()+" "+s.getGender());
               break;
          }
      }
  }

  private static void pendingApprovals()
  {
      AdminDB.pendingStaffs();
  }

  private static void approvals(ArrayList<Staff>staff,Scanner scan)
  {
      System.out.println("Enter the Staff id :");
      String id = scan.nextLine();

      try
      {
          String query = "SELECT * FROM staffs WHERE id = ?";
          Connection con = DBConnection.connection();

          PreparedStatement pst = con.prepareStatement(query);
          pst.setString(1,id);

           ResultSet rs = pst.executeQuery();

           boolean found = false;

           if(rs.next())
           {
               found = true;

               while (true)
               {
                   System.out.println("Name :" + rs.getString("name") + " To Approve this Staff ? ( Yes (or) No )");
                   String input = scan.nextLine();

                   if (input.equalsIgnoreCase("yes"))
                   {
                       AdminDB.approveStaffs(id);
                       System.out.println("Approval Successfully for this Staff :" + rs.getString("name"));
                       return;
                   }
                   else if (input.equalsIgnoreCase("no"))
                   {
                       System.out.println("Approval cancelled Successfully..");
                       break;
                   }
                   else
                   {
                       System.out.println("Invalid input only click to Yes or No..");
                   }
               }
           }
           if(!found)
           {
               System.out.println("Staff Not Found");
           }

           rs.close();
           pst.close();
           con.close();
      }

      catch(Exception e)
      {
          e.printStackTrace();
      }

  }

  private static void assignStaffs(Scanner scan,ArrayList<Staff>staff)
  {
      System.out.println("Enter the Staff id :");
      String id = scan.nextLine();

      boolean found = false;

      try
      {
        String query = "SELECT*FROM staffs WHERE id = ?";
        Connection con = DBConnection.connection();
        PreparedStatement pst = con.prepareStatement(query);

        pst.setString(1,id);

         ResultSet rs = pst.executeQuery();

         if(rs.next())
         {
             found = true;

             if(!rs.getBoolean("approval"))
             {
                 System.out.println("Name : " + rs.getString("name")+
                                     "Id : "+ rs.getString("id")+
                                     "This staff is not Approved by You...");
                 return;
             }

             if(rs.getString("advisor_department") != null || rs.getString("advisor_year") !=null)
             {
                 System.out.println( "Name : " + rs.getString("name")+
                                     "Id : "+ rs.getString("id")+
                                     "This staff Already assigned..."+
                                     "Class :" +rs.getString("advisor_department")+
                                     "Year :"+rs.getString("advisor_year"));
                 return;
             }

             AdminDB.assignClasses(id,scan);

         }

         rs.close();
         con.close();
      }

      catch(Exception e)
      {
          e.printStackTrace();
      }

      if(!found)
      {
          System.out.println("Account not found..");
      }


  }

  private static void viewBelow75Percentage(ArrayList<Attendance>attendances, Semester sem, ArrayList<Student>students)
  {
      for(Student s:students)
      {

      int percentage = AttendanceHelper.semPercentage(attendances,sem,s);

      if(percentage<75)
      {
          System.out.println(s.getName()+"Percentage :"+percentage);
      }

      }
  }

  private static void addStaff(ArrayList<Staff>staff,Scanner scan)
  {
      System.out.println("Enter the name :");
      String name = scan.nextLine();
      System.out.println("Enter the id: ");
      String id = scan.nextLine();
      System.out.println("Enter the password :");
      String password = scan.nextLine();

      Gender gender ;

      while(true)
      {
      System.out.println("Enter the Gender :( MALE / FEMALE / OTHERS)");
      String g = scan.nextLine().toUpperCase();
      try
      {
          gender = Gender.valueOf(g);
          break;
      }
      catch (Exception e)
      {
          System.out.println("Invalid input..!");
      }

      }

      Staff s1 = new Staff(name,id,password,gender,true);
      staff.add(s1);
  }


  private static void removeStaff(ArrayList<Staff>staff,Scanner scan)
  {
     System.out.println("Enter the staff id :");
     String id = scan.nextLine();

     for(int i=0;i<staff.size();i++)
     {
         if(staff.get(i).getId().equals(id))
         {
             staff.remove(i);
             System.out.println("Staff deleted..");
             break;
         }
     }
  }

  private static void stDate(Scanner scan, Semester sem)
  {
      System.out.println("Enter the Semester StartDate :(yyyy-mm-dd)");
      String input = scan.nextLine();
      LocalDate stDate = LocalDate.parse(input);
      sem.setStartDate(stDate);
      System.out.println("Start date fixed Succesfully ");
  }

  private static void endDate(Scanner scan, Semester sem)
  {
      System.out.println("Enter the Semester endDate :(yyyy-mm-dd)");
      String input = scan.nextLine();
      LocalDate endDate = LocalDate.parse(input);
      sem.setEndDate(endDate);
      System.out.println("End date fixed Succesfully..");
  }

}

