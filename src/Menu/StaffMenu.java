package Menu;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.Scanner;
import java.util.ArrayList;

import Database.DBConnection;
import Database.StaffDB;
import Model.Attendance;
import Model.Enum.Years;
import Model.Enum.Gender;
import Model.Enum.Departments;
import Model.Semester;
import Model.Staff;
import Model.Student;
import static Helper.AttendanceHelper.semPercentage;


public class StaffMenu
{
    public static void stafflogin (Scanner scan, ArrayList<Staff>staffs, ArrayList<Student>students, ArrayList<Attendance>attendances, Semester sem)
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
                    login(scan, staffs,students,attendances,sem);
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
        String id ;
        while(true)
        {
            System.out.println("Enter your id : ");
             id = scan.nextLine();

            try
            {
                String query = "SELECT * FROM staffs WHERE id = ?";
                Connection con = DBConnection.connection();
                PreparedStatement pst = con.prepareStatement(query);

                pst.setString(1, id);

                ResultSet rs = pst.executeQuery();

                if (rs.next())
                {
                    System.out.println(rs.getString("id")+" : This id Already Registerd");
                    continue;
                }

                rs.close();
                pst.close();
                con.close();
                break;
            }

            catch (Exception e)
            {
                e.printStackTrace();
            }
        }

        System.out.println("Enter your name : ");
        String name = scan.nextLine();

        System.out.println("Enter your password : ");
        String password = scan.nextLine();

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

        Staff s = new Staff(id,name,password,gender,false);

        StaffDB.staffRegister(s);

        System.out.println("Waiting for the approvel from Admin to Login");
    }

    private static void login(Scanner scan, ArrayList<Staff>staffs, ArrayList<Student>students, ArrayList<Attendance>attendaces, Semester sem)
    {
       System.out.println("Enter your id : ");
       String id = scan.nextLine();

       System.out.println("Enter your password : ");
       String password = scan.nextLine();

       Staff s = StaffDB.staffLogin(id,password);

       if(s!=null)
       {
           System.out.println("Login Successfully..");
           StaffDashboard(scan,s,students,attendaces,sem);
       }
       else
       {
          System.out.println("Login failed..");
       }
    }

    private static void StaffDashboard(Scanner scan, Staff s1, ArrayList<Student>students, ArrayList<Attendance>attendances, Semester sem)
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
                    editAttendance(scan,attendances);
                    break;
                case 6:
                    viewBelow75percentage(attendances,sem,students,s1);
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
        boolean studentFound = false;

        for(Student s :students)
        {
            if(s1.getAdvisorOfDepartment().equals(s.getDepartment()) && s1.getAdvisorOfYear().equals(s.getYear()))
            {
                studentFound = true;

                boolean attendanceMarked = false;

                for (Attendance a : attendances)
                {
                    if (a.getStudentRegNumber().equals(s.getRegisterNumber()) && a.getDate().equals(today))
                    {
                        attendanceMarked = true;
                        break;
                    }
                }
                if (attendanceMarked)
                {
                    System.out.println(s.getName() + " - Attendance already Marked today ");
                    continue;
                }

                boolean present;

                while (true)
                {
                    System.out.println( s.getName() + " -  PRESENT  || ABSENT ");
                    String input = scan.nextLine().trim();

                    if(input.equalsIgnoreCase("present"))
                    {
                        present = true;
                        break;
                    }
                    else if(input.equalsIgnoreCase("absent"))
                    {
                        present = false;
                        break;
                    }
                    else
                    {
                        System.out.println("Invalid Input  Enter Present or Absent only..");
                    }
                }

                Attendance a = new Attendance(today,s.getRegisterNumber(),s.getName(),present);
                attendances.add(a);
                System.out.println("Attendance marked for "+s.getName());
            }
        }

        if(!studentFound)
        {
            System.out.println("No student assigned for this advisor ");
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

        for(int i = 0; i < students.size(); i++)
        {
            if(students.get(i).getRegisterNumber().equals(reg))
            {
                students.remove(i);

                System.out.println("Student deleted");

                break;
            }
        }
    }

    private static void editAttendance(Scanner scan,ArrayList<Attendance>attendances)
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

    private static void viewBelow75percentage(ArrayList<Attendance>attendances, Semester sem, ArrayList<Student>students, Staff s1)
    {
        for(Student s:students)
        {
            if(s1.getAdvisorOfDepartment().equals(s.getDepartment()) && s1.getAdvisorOfYear().equals(s.getYear()))
            {
              int percentage = semPercentage(attendances,sem,s);

              if(percentage<75)
              {
              System.out.println(s.getName()+"Percentage :"+percentage);
              }
            }
        }
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



