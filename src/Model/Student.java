package Model;
import Model.Enum.Gender;
import Model.Enum.Departments;

public class Student
{
    private String name;
    private String password;
    private String registerNumber;
    private Departments department;
    private String year;
    private Gender gender;

    public Student(String name, String password, String registerNumber, Departments department, String year, Gender gender)
    {
        this.name = name;
        this.password = password;
        this.registerNumber = registerNumber;
        this.department = department;
        this.year = year;
        this.gender = gender;
    }

    // Getters

    public String getName()
    {
        return  name;
    }
    public String getPassword()
    {
        return password;
    }
    public String getRegisterNumber()
    {
        return registerNumber;
    }
    public Departments getDepartment()
    {
        return department;
    }
    public String getYear()
    {
        return year;
    }
    public Gender getGender()
    {
        return gender;
    }

    // Setters

    public void setName(String name)
    {
        this.name = name;
    }
    public void setPassword(String password)
    {
        if(password.length()>5)
        {
            this.password = password;
        }
    }
    public void setDepartment(Departments department)
    {
        this.department = department;
    }
    public void setRegisterNumber(String registerNumber)
    {
        if(registerNumber.startsWith("9523"))
        {
            this.registerNumber = registerNumber;
        }
    }
    public void setYear(String year)
    {
        this.year = year;
    }
    public void setGender(Gender gender)
    {
        this.gender = gender;
    }






}
