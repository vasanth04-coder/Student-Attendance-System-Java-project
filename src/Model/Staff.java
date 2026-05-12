package Model;
import Model.Enum.Gender;
import Model.Enum.Departments;

public class Staff
{
    private String name ;
    private String id;
    private String password;
    private Departments advisorOfDepartment;
    private String advisorOfYear;
    private Gender gender;

    public Staff(String name,String id,String password,Departments advisorOfDepartment,String advisorOfYear,Gender gender)
    {
        this.name = name;
        this.id = id;
        this.password = password;
        this.advisorOfDepartment = advisorOfDepartment;
        this.advisorOfYear = advisorOfYear;
        this.gender = gender;
    }

    // Getters

    public String getName()
    {
        return name;
    }
    public String getId()
    {
        return id;
    }
    public String getPassword()
    {
        return password;
    }
    public Departments getAdvisorOfDepartment()
    {
        return advisorOfDepartment;
    }
    public String getAdvisorOfYear()
    {
        return advisorOfYear;
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
    public void setId(String id)
    {
        this.id = id;
    }
    public void setAdvisorOfDepartment(Departments advisorOfDepartment)
    {
        this.advisorOfDepartment = advisorOfDepartment;
    }
    public void setAdvisorOfClass(String advisorOfYear)
    {
        this.advisorOfYear = advisorOfYear;
    }
    public void setGender(Gender gender)
    {
        this.gender = gender;
    }






}
