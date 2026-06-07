package Model;
import Model.Enum.Gender;
import Model.Enum.Departments;
import Model.Enum.Years;

public class Student
{
    private String registerNumber;
    private String name;
    private String password;
    private Departments department;
    private Years year;
    private Gender gender;

    public Student(String registerNumber, String name, String password,Departments department, Years year, Gender gender)
    {
        this.registerNumber = registerNumber;
        this.name = name;
        this.password = password;
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
    public Years getYear()
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
    public void setYear(Years year)
    {
        this.year = year;
    }
    public void setGender(Gender gender)
    {
        this.gender = gender;
    }






}
