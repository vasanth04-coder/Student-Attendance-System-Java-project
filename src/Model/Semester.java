package Model;

import java.time.LocalDate;

public class Semester
{
    private LocalDate startDate;
    private LocalDate endDate;

    public Semester(LocalDate startDate, LocalDate endDate)
    {
      this.startDate = startDate;
      this.endDate = endDate;
    }


    public LocalDate getStartDate()
    {
        return startDate;
    }
    public LocalDate getEndDate()
    {
        return endDate;
    }


    public void setStartDate(LocalDate startDate)
    {
        this.startDate = startDate;
    }
    public void setEndDate(LocalDate endDate)
    {
        this.endDate = endDate;
    }
}

