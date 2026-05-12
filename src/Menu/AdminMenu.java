package Menu;

import Model.Admin;
import Model.Enum.Gender;
import Model.Enum.Departments;
import Model.Staff;
import Model.Student;

import java.util.ArrayList;
import java.util.Scanner;

public class AdminMenu
{
  public static void adminlogin(Scanner scan, ArrayList <Admin> admin, ArrayList<Student>students,ArrayList<Staff>staff)
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
                  register(scan, admin);
                  break;
              case 2:
                  login(scan,admin,students,staff);
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

  private static void login(Scanner scan ,ArrayList<Admin>admin,ArrayList<Student>students,ArrayList<Staff>staff)
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
          adminDashboard(scan,students,staff,a);
          break;
      }
   }
   if(!found)
   {
       System.out.println("Account not found ");
   }

  }

  private static void adminDashboard(Scanner scan,ArrayList<Student>students,ArrayList<Staff>staff,Admin admin)
  {
      while(true)
      {
          System.out.println("ADMIN DASHBOARD");
          System.out.println();
          System.out.println(" 1. View students by year and department: ");
          System.out.println(" 2. View the class Advisors by year and department : ");
          System.out.println(" 3. View staff pending approvals :");
          System.out.println(" 4. Approve staffs: ");
          System.out.println(" 5. View below 75% students :");
          System.out.println(" 6. Add staffs :");
          System.out.println(" 7. Remove staffs : ");
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
              case 6:
                  addStaff(staff,scan);
                  break;
              case 7:
                  removeStaff(staff,scan);
                  break;
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
              break;
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

     for(Staff s:staff)
     {
         if(s.getId().equals(id))
         {
             staff.remove(s);
             System.out.println("Staff deleted..");
             break;
         }
     }
  }

}

