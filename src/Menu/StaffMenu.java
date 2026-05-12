package Menu;
import java.time.LocalDate;
import java.util.Scanner;
import java.util.ArrayList;

import Model.Attendance;
import Model.Enum.Gender;
import Model.Enum.Departments;
import Model.Staff;
import Model.Student;


public class StaffMenu
{
    public static void stafflogin (Scanner scan, ArrayList<Staff>staffs, ArrayList<Student>students, ArrayList<Attendance>attendances)
    {
        while(true)
        {
            System.out.println();
            System.out.println(" STAFF MENU ");
            System.out.println();
            System.out.println(" 1. Register ");
            System.out.println(" 2. Login ");
            System.out.println(" 0. Exit ");

            System.out.println("Enter the Number : ");

            int choice = scan.nextInt();
            scan.nextLine();

            switch (choice)
            {
                case 1:
                    register(scan, staffs);
                    break;
                case 2:
                    login(scan, staffs,students,attendances);
                    break;
                case 0:
                    System.out.println("Returning main menu..");
                    return;
                default:
                    System.out.println("Invalid Number");
                    break;
            }
        }
    }
    private static void register(Scanner scan , ArrayList<Staff>staffs)
    {
        System.out.println("Enter your name : ");
        String name = scan.nextLine();

        System.out.println("Enter your id : ");
        String id = scan.nextLine();

        System.out.println("Enter your password : ");
        String password = scan.nextLine();

        Departments department;

        while(true)
        {
         System.out.println("Enter the department of Advisor - ( CSE / IT / ECE / MECH / CIVIL / EEE / AGRI / AUTOMOBILE ) : ");
         String dept = scan.nextLine().toUpperCase();

         try
         {
             department = Departments.valueOf(dept);
             break;
         }
         catch (Exception e)
         {
             System.out.println("Invalid input Try Again..");
         }

        }

        System.out.println("Enter the year of Advisor - ( First year / Second year / Third year / Final year)" );
        String year = scan.nextLine();


         Gender gender;

         while(true)
         {
            System.out.println("Enter your Gender - ( MALE / FEMALE / OTHERS ) : ");
            String g = scan.nextLine().toUpperCase();
            try
            {
                gender = Gender.valueOf(g);
                break;
            }
            catch (Exception e)
            {
                System.out.println("Invalid Input !");
            }
         }

         System.out.println(" Registration Succesfull ");

        Staff s1 = new Staff(name,id,password,department,year,gender);
        staffs.add(s1);
    }

    private static void login(Scanner scan,ArrayList<Staff>staffs,ArrayList<Student>students,ArrayList<Attendance>attendaces)
    {
       System.out.println("Enter your id : ");
       String id = scan.nextLine();

       System.out.println("Enter your password : ");
       String password = scan.nextLine();

       boolean found = false;

       for(Staff s1 : staffs)
       {
           if(s1.getId().equals(id) && s1.getPassword().equals(password))
           {
               System.out.println("Login Succesfully ");
               StaffDashboard(scan,s1,students,attendaces);

               found = true;
               break;
           }
       }
       if(!found)
       {
           System.out.println("Account not found");
       }
    }

    private static void StaffDashboard(Scanner scan,Staff s1,ArrayList<Student>students,ArrayList<Attendance>attendances)
    {
        while(true)
        {
            System.out.println();
            System.out.println(" STAFF DASHBOARD");
            System.out.println("Good Morning Have a Great Dayy..");
            System.out.println();
            System.out.println(" 1 . Take Attendace Today ");
            System.out.println(" 2 . View My class Students ");
            System.out.println(" 3 . Add Students");
            System.out.println(" 4 . Delete Students ");
            System.out.println(" 5 . Edit Attendance ");
            System.out.println(" 6 . View below 75% Students ");
            System.out.println(" 7 . Update student profile");
            System.out.println(" 0 . Exit");


            int choice = scan.nextInt();
            scan.nextLine();

            switch (choice)
            {
                case 1:
                    takeAttendance(scan, students, attendances,s1);
                    break;
                case 2:
                    viewClassStudents(students,s1);
                    break;
                case 3:
                    addStudent(scan,students);
                    break;
                case 4:
                    deleteStudent(scan,students);
                    break;
                case 5:
                    editAttendance(scan,attendances,students);
                    break;
                case 6:
                    viewBelow75percentage();
                    break;
                case 7:
                    updateStudentProfile(students,scan);
                    break;
                case 0 :
                    System.out.println("Logging out..");
                    return;
            }
        }
    }

    private static void takeAttendance(Scanner scan,ArrayList<Student>students,ArrayList<Attendance>attendances,Staff s1)
    {
        LocalDate today = LocalDate.now();

        for(Student s :students)
        {
            if(s1.getAdvisorOfDepartment().equals(s.getDepartment()) && s1.getAdvisorOfYear().equalsIgnoreCase(s.getYear()))
            {
            System.out.println(s.getName()+" - PRESENT  ||  ABSENT ");
            String status = scan.nextLine();
            boolean present = status.equalsIgnoreCase("present");
            Attendance  a = new Attendance(today,s.getRegisterNumber(),s.getName(),present);
            attendances.add(a);
            }

        }
    }

    private static void viewClassStudents(ArrayList<Student>students,Staff s1)
    {
        System.out.println("  Name  "+"  Register Number  "+"  Department  "+"  Year  "+"     Gender  ");
        System.out.println();

        for(Student s :students)
        {
            if(s1.getAdvisorOfDepartment().equals(s.getDepartment()) && s1.getAdvisorOfYear().equals(s.getYear()))
            {
              System.out.println("  "+s.getName()+"    "+ s.getRegisterNumber()+"      " +s.getDepartment() + "       " +s.getYear()+"      "+s.getGender() );
            }
        }
    }

    private static void addStudent(Scanner scan,ArrayList<Student>students)
    {
       System.out.println("Enter student name : ") ;
       String name = scan.nextLine();
       System.out.println("Enter the password :");
       String password = scan.nextLine();
       System.out.println("Enter registernumber :");
       String regNumber = scan.nextLine();

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

        System.out.println("Enter year :");
        String year = scan.nextLine();

       Gender gender;

       while(true)
       {
           System.out.println("Enter the gender : ( MALE / FEMALE / OTHERS )");
           String g = scan.nextLine().toUpperCase();
           try
           {
           gender = Gender.valueOf(g);
           break;
           }
           catch (Exception e)
           {
               System.out.println("Invalid input");
           }
       }

       Student s = new Student(name,password,regNumber,department,year,gender);
       students.add(s);
    }

    private static void deleteStudent(Scanner scan,ArrayList<Student>students)
    {
      System.out.println("Enter the registernumber:");
      String reg = scan.nextLine();

        for(Student s : students)
        {
          if (s.getRegisterNumber().equals(reg))
          {
             students.remove(s);
             System.out.println("Student deleted");
             break;
          }
       }
    }

    private static void editAttendance(Scanner scan,ArrayList<Attendance>attendances,ArrayList<Student>students)
    {
            System.out.println("Enter the RegisterNumber :");
            String reg = scan.nextLine();

            System.out.println("Enter the date : (yyyy-mm-dd) ");
            String dateInput = scan.nextLine();
            LocalDate date = LocalDate.parse(dateInput);

                for(Attendance a:attendances)
                {
                    if(a.getStudentRegNumber().equals(reg)&& a.getDate().equals(date))
                    {
                        System.out.println(a.getStudentName()+" "+a.getDate()+" status -"+(a.isPresent()? "Present":"Absent"));
                        System.out.println("Edit Attendance : PRESENT OR  ABSENT");
                        String input = scan.nextLine();
                        boolean status = input.equalsIgnoreCase("present");
                        a.setPresent(status);
                        break;
                    }
                }
     }

    private static void viewBelow75percentage()
    {

    }

    private static void updateStudentProfile(ArrayList<Student>students,Scanner scan)
    {
        while(true)
        {
        System.out.println(" UPDATE STUDENT PROFILE ");
        System.out.println();

        System.out.println(" 1 . Update name ");
        System.out.println(" 2 . Reset password ");
        System.out.println(" 3.  Exit");

        System.out.println("Enter the choice : ");
        int choice = scan.nextInt();
        scan.nextLine();

        switch (choice)
        {
            case 1:
                updateName(scan,students);
            break;
            case 2:
                resetPassword(scan,students);
                break;
            case 3:
                System.out.println("Returning main menu..");
                return;
        }

        }
    }

    private static void updateName(Scanner scan,ArrayList<Student>students)
    {
        System.out.println("Enter the registerNumber: ");
        String reg = scan.nextLine();

        boolean found = false;

        for (Student s : students)
        {
            if (s.getRegisterNumber().equals(reg))
            {
                found = true;
                System.out.println("Enter the new name :");
                String name = scan.nextLine();
                s.setName(name);
                System.out.println("Name updated Succesfully..");
                break;
            }
        }
        if(!found)
        {
            System.out.println("Student not found ");
        }
    }

    private static void resetPassword(Scanner scan,ArrayList<Student>students)
    {
        System.out.println("Enter the registerNumber:");
        String reg = scan.nextLine();
        boolean found = false;

        for(Student s : students)
        {
            if(s.getRegisterNumber().equals(reg))
            {
                found = true;
                System.out.println("Enter the new password : ");
                String pass = scan.nextLine().trim();
                System.out.println("Re-Enter the password : ");
                String reEnter = scan.nextLine().trim();

                if(!pass.equals(reEnter))
                {
                    System.out.println("Password Mismatch.!");
                }
                else if(pass.length()<5)
                {
                    System.out.println("password must be above 5 characters !");
                }
                else
                {
                    s.setPassword(pass);
                    System.out.println("Password reset Succesfully ");
                }
                break;
            }
        }
        if(!found)
        {
            System.out.println("Student not found ");
        }
    }

}



