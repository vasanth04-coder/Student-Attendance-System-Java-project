package Model;

import java.time.LocalDate;

public class Attendance
{
    private LocalDate date;
    private String studentName;
    private String studentRegNumber;
    private boolean present ;

    public Attendance(LocalDate date,String studentRegNumber,String studentName,boolean present)
    {
        this.date = date;
        this.studentName = studentName;
        this.studentRegNumber = studentRegNumber;
        this.present = present;
    }

    public LocalDate getDate()
    {
        return date;
    }
    public String getStudentName()
    {
        return studentName;
    }
    public String getStudentRegNumber()
    {
        return studentRegNumber;
    }
    public boolean isPresent()
    {
        return present;
    }
    public void setPresent(boolean present)
    {
        this.present = present;
    }
}
