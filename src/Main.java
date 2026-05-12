import Menu.AdminMenu;
import Menu.StaffMenu;
import Menu.StudentMenu;
import Model.Admin;
import Model.Attendance;
import Model.Staff;
import Model.Student;

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
        ArrayList<Admin> admin = new ArrayList<>();
        ArrayList<Attendance>attendances = new ArrayList<>();


       // Main menu

        while(true)
        {
            System.out.println("Enter your role: ");
            System.out.println("1.Student ");
            System.out.println("2.Staff ");
            System.out.println("3.Admin ");
            System.out.println("0.Exit");

            int choice =-1;
            try
            {
                choice = scan.nextInt();
                scan.nextLine();
            }
            catch (Exception e )
            {
                System.out.println("Enter Numbers only");
                scan.nextLine();
                continue;
            }

            switch (choice)
            {
                case 1:
                    StudentMenu.studentLogin(scan,students,attendances);
                    break;
                case 2:
                    StaffMenu.stafflogin(scan,staffs,students,attendances);
                    break;
                case 3:
                    AdminMenu.adminlogin(scan,admin,students,staffs);
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