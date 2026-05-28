package Menu;

import Helper.AttendanceHelper;
import Model.*;
import Model.Enum.Gender;
import Model.Enum.Departments;

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
          System.out.println("1.Register");
          System.out.println("2.Login");
          System.out.println("0.Logout");

          System.out.println("Enter your choice:");
          int choice = scan.nextInt();
          scan.nextLine();

          switch (choice)
          {
              case 1:
                  register(scan,admin);
                  break;
              case 2:
                  login(scan,admin,students,staff,sem,attendances);
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
  private static void register(Scanner scan,ArrayList<Admin>admin)
  {
      System.out.println("Enter your name: ");
      String name = scan.nextLine();
      System.out.println("Enter your id : ");
      String id = scan.nextLine();
      System.out.println("Enter your password :");
      String pass = scan.nextLine();

      Admin a = new Admin(name,id,pass);
      admin.add(a);
  }

  private static void login(Scanner scan , ArrayList<Admin>admin, ArrayList<Student>students, ArrayList<Staff>staff, Semester sem, ArrayList<Attendance>attendances)
  {
   System.out.println("Enter your id :");
   String id = scan.nextLine();
   System.out.println("Enter your password :");
   String password = scan.nextLine();

   boolean found = false;

   for(Admin a:admin)
   {
      if(a.getId().equals(id) && a.getPassword().equals(password))
      {
          found = true;
          System.out.println("Login Succesfully ");
          adminDashboard(scan,students,staff,a,sem,attendances);
          break;
      }
   }
   if(!found)
   {
       System.out.println("Account not found ");
   }

  }

  private static void adminDashboard(Scanner scan, ArrayList<Student>students, ArrayList<Staff>staff, Admin admin, Semester sem, ArrayList<Attendance>attendances)
  {
      while(true)
      {
          System.out.println("ADMIN DASHBOARD");
          System.out.println();
          System.out.println(" 1. View students by year and department");
          System.out.println(" 2. View the class Advisors by year and department");
          System.out.println(" 3. View staff pending approvals ");
          System.out.println(" 4. Approve staffs");
          System.out.println(" 5. View below 75% students ");
          System.out.println(" 6. Add staffs ");
          System.out.println(" 7. Remove staffs");
          System.out.println(" 8. Set Semester StartDate");
          System.out.println(" 9. Set Semester EndDate");
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
                  pendingApprovels();
                  break;
              case 4:
                  approvels();
                  break;
              case 5:
                  viewBelow75Percentage(attendances,sem,students);
                  break;
              case 6:
                  addStaff(staff,scan);
                  break;
              case 7:
                  removeStaff(staff,scan);
                  break;
              case 8:
                  stDate(scan,sem);
                  break;
              case 9:
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
      System.out.println("Enter the year :");
      String year = scan.nextLine();
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

      for(Student s : students)
      {
          if(s.getYear().equalsIgnoreCase(year) && s.getDepartment().equals(department))
          {
              System.out.println(s.getRegisterNumber()+" "+s.getName()+" "+s.getYear()+" "+s.getDepartment()+" "+s.getGender());
          }
      }
  }

  private static void viewStaffs(ArrayList<Staff>staff,Scanner scan)
  {
      System.out.println("Enter the year :");
      String year = scan.nextLine();
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
          if(s.getAdvisorOfYear().equalsIgnoreCase(year) && s.getAdvisorOfDepartment().equals(department))
          {
               System.out.println(s.getId()+" "+s.getName()+" "+s.getAdvisorOfYear()+" "+s.getAdvisorOfDepartment()+" "+s.getGender());
               break;
          }
      }
  }

  private static void pendingApprovels()
  {

  }

  private static void approvels()
  {

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

      System.out.println("Enter the Advisor of year: ");
      String year = scan.nextLine();

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
      Staff s = new Staff(name,id,password,department,year,gender);
      staff.add(s);
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

