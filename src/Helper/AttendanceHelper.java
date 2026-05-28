package Helper;

import Model.Attendance;
import Model.Semester;
import Model.Student;

import java.time.LocalDate;
import java.util.ArrayList;

public class AttendanceHelper
{

    public static int semPercentage(ArrayList<Attendance> attendances, Semester sem, Student s)
    {
        int totalDays = 0,presentCount = 0;

        LocalDate today = LocalDate.now();

        LocalDate startDate = sem.getStartDate();

        for(Attendance a :attendances)
        {
            if(a.getStudentRegNumber().equals(s.getRegisterNumber()) && !a.getDate().isBefore(startDate) && !a.getDate().isAfter(today) )
            {
                totalDays++;
                if(a.isPresent())
                {
                    presentCount++;
                }
            }
        }

        if(totalDays==0)
        {
            System.out.println("Attendance not  marked");
        }

        return (presentCount*100)/totalDays;
    }


}
