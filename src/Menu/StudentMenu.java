package Menu;
import Model.Attendance;
import Model.Enum.Gender;
import Model.Enum.Departments;
import Model.Semester;
import Model.Student;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;
import static Helper.AttendanceHelper.semPercentage;



public class StudentMenu
{
    public static void studentLogin(Scanner scan, ArrayList<Student> students, ArrayList<Attendance> attendances, Semester sem)
    {
        while (true)
        {
            System.out.println();
            System.out.println(" STUDENT MENU  ");
            System.out.println();
            System.out.println(" 1 - Register ");
            System.out.println(" 2 - Login ");
            System.out.println(" 0 - Back ");

            System.out.println("Enter the number : ");

            int choice = scan.nextInt();
            scan.nextLine();

            switch (choice)
            {
                case 1:
                    register(scan, students);
                    break;
                case 2:
                    login(scan, students, attendances,sem);
                    break;
                case 0:
                    System.out.println(" Returning to Main menu..");
                    return;
                default:
                    System.out.println("Invalid option");
                    break;
            }
        }
    }


    private static void register(Scanner scan, ArrayList<Student> students)
    {
        System.out.println("Enter your name : ");
        String name = scan.nextLine();

        System.out.println("Enter your password :");          // recommended date of birth
        String password = scan.nextLine();

        String registerNumber;

        while (true)
        {
            System.out.println("Enter your registerNumber Must start with 9523 : ");
            registerNumber = scan.nextLine();

            if (registerNumber.startsWith("9523"))
            {
                for (Student s : students)
                {
                    if (s.getRegisterNumber().equals(registerNumber))
                    {
                        System.out.println(" This RegisterNumber is Already Registerd !");
                        return;
                    }
                }
                break;
            }
            else
            {
                System.out.println("Invalid register Number!");
            }
        }

         Departments department;

         while(true)
         {
             System.out.println(" Enter your Department ( CSE / IT / ECE / MECH / CIVIL / EEE / AUTOMOBILE / AGRI ) : ");
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

        System.out.println("Enter Year - ( First year / Second year / Third year / Final year ) : ");
        String year = scan.nextLine();

        Gender gender;

        while (true)
        {
            System.out.println(" Enter your Gender - ( MALE / FEMALE / OTHERS ) : ");
            String g = scan.nextLine().toUpperCase();
            try
            {
                gender = Gender.valueOf(g);
                break;
            }
            catch (Exception e)
            {
                System.out.println("Invalid input try again ");
            }
        }
        System.out.println("Registration Successfull ");

        Student s = new Student(name, password, registerNumber, department, year, gender);
        students.add(s);
    }

    private static void login(Scanner scan, ArrayList<Student> students, ArrayList<Attendance> attendances, Semester sem)
    {
        System.out.println("Enter your register number: ");
        String registerNumber = scan.nextLine();
        System.out.println("Enter yor password : ");
        String password = scan.nextLine();

        boolean found = false;

        for (Student s : students)
        {
            if (s.getRegisterNumber().equals(registerNumber) && s.getPassword().equals(password))
            {
                System.out.println("Login Successful");
                studentDashboard(scan, s, attendances,sem);
                found = true;
                break;
            }
        }
        if (!found)
        {
            System.out.println(" Account not found ");
        }
    }

    private static void studentDashboard(Scanner scan, Student s, ArrayList<Attendance> attendances, Semester sem)
    {
        while (true)
        {
            System.out.println();
            System.out.println(" STUDENT DASHBOARD ");
            System.out.println();

            System.out.println(" 1. Today Status ");
            System.out.println(" 2. View Attendance percentage ( This Month ) ");
            System.out.println(" 3. View Attendance Percentage ( This Semester ) ");
            System.out.println(" 4. View Profile ");
            System.out.println(" 5. Change password ");
            System.out.println(" 6. Logout ");

            System.out.println(" Enter the Number : ");
            int choice = scan.nextInt();
            scan.nextLine();

            switch (choice)
            {
                case 1:
                    todayStatus(attendances, s);
                    break;
                case 2:
                    monthlyPercentage(attendances,s);
                    break;
                case 3:
                     viewSemPercentage(attendances,sem,s);
                    break;
                case 4:
                    viewProfile(s);
                    break;
                case 5:
                    changePassword(scan, s);
                    break;
                case 6:
                    System.out.println("Logging out..");
                    return;
                default:
                    System.out.println("Invalid option..!");
                    break;
            }
        }
    }

    private static void todayStatus(ArrayList<Attendance> attendances, Student s)
    {
        LocalDate today = LocalDate.now();
        boolean found = false;

        for (Attendance a : attendances)
        {
            if (s.getRegisterNumber().equals(a.getStudentRegNumber()) && a.getDate().equals(today))
            {
                found = true;
                if (a.isPresent())
                {
                    System.out.println("Today " + a.getDate() + " You are PRESENT");
                }
                else
                {
                    System.out.println("Today " + a.getDate() + " You are ABSENT");
                }
                break;
            }
        }
        if (!found)
        {
            System.out.println("Attendance did't marked today..");
        }
    }

    private static void monthlyPercentage(ArrayList<Attendance>attendances,Student s)
    {
        int totalDays = 0;
        int presentCount = 0;

        LocalDate today = LocalDate.now();

        for(Attendance a :attendances)
        {
           if(s.getRegisterNumber().equals(a.getStudentRegNumber()) && a.getDate().getMonth() == today.getMonth() && a.getDate().getYear() == today.getYear())
           {
               totalDays++;

               if (a.isPresent())
               {
                   presentCount++;
               }
           }
        }
        if(totalDays == 0)
        {
            System.out.println("Attendance not marked ");
        }
        else
        {
            int percentage = (presentCount * 100) / totalDays;
            System.out.println("Your Monthly Percentage is : " + percentage +"%");
        }
    }

    private static void viewSemPercentage(ArrayList<Attendance>attendances, Semester sem, Student s)
    {
        int percentage = semPercentage(attendances,sem,s);
        System.out.println("This Semester Percentage "+percentage);
    }

    private static void viewProfile(Student s)
    {
        System.out.println(" RegisterNumber :" + s.getRegisterNumber());
        System.out.println(" Name :" + s.getName());
        System.out.println(" Department : " + s.getDepartment());
        System.out.println(" Year " + s.getYear());
        System.out.println(" Gender :" + s.getGender());
    }

    private static void changePassword(Scanner scan, Student s)
    {
        System.out.println("Enter the old password : ");
        String oldPass = scan.nextLine().trim();


        if(s.getPassword().equals(oldPass))
        {
            System.out.println("Enter the new Password ");
            String newPass = scan.nextLine().trim();
            System.out.println("Confirm password :");
            String confirm = scan.nextLine().trim();

            if(!newPass.equals(confirm))
            {
                System.out.println("Password Mismatch ..!");
            }
            else if(newPass.length()<5)
            {
                System.out.println("Password length must be above 5 Characters.");
            }
            else
            {
                s.setPassword(newPass);
                System.out.println("new password updated Succesfully ");
            }
        }
        else
        {
            System.out.println("Your old password Mistmatch..");
        }
    }
}

