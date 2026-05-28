import Menu.AdminMenu;
import Menu.StaffMenu;
import Menu.StudentMenu;
import Model.*;

import java.util.ArrayList;
import java.util.Scanner;

public class Main
{
    public static void main(String[] args)
    {
         Scanner scan = new Scanner(System.in);
         System.out.println(" STUDENT ATTENDANCE SYSTEM ");
         System.out.println(" PSN ENGINEERING COLLEGE ");
         System.out.println();

        ArrayList <Student> students = new ArrayList<>();
        ArrayList <Staff> staffs = new ArrayList<>();
        ArrayList <Admin> admin = new ArrayList<>();
        ArrayList<Attendance>attendances = new ArrayList<>();

        Semester sem = new Semester(null,null);

       // Main menu

        while(true)
        {
            System.out.println("Enter your role: ");
            System.out.println("1.Student ");
            System.out.println("2.Staff ");
            System.out.println("3.Admin ");
            System.out.println("0.Exit");

            int choice ;
            try
            {
                choice = scan.nextInt();
                scan.nextLine();
            }
            catch (Exception e)
            {
                System.out.println("Enter Numbers only");
                scan.nextLine();
                continue;
            }
            switch (choice)
            {
                case 1:
                    StudentMenu.studentLogin(scan,students,attendances,sem);
                    break;
                case 2:
                    StaffMenu.stafflogin(scan,staffs,students,attendances,sem);
                    break;
                case 3:
                    AdminMenu.adminlogin(scan,admin,students,staffs,sem,attendances);
                    break;
                case 0:
                    System.out.println(" Program End ");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid option");
                    break;
            }

        }
    }
}