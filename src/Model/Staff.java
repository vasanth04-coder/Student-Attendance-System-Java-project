package Model;
import Model.Enum.Gender;
import Model.Enum.Departments;
import Model.Enum.Years;

public class Staff
{
    private String name ;
    private String id;
    private String password;
    private Gender gender;
    private boolean approvel;

    private Departments advisorOfDepartment;
    private  Years advisorOfYear;

    public Staff(String id,String name,String password,Gender gender,boolean approvel)
    {
        this.id = id;
        this.name = name;
        this.password = password;
        this.gender = gender;
        this.approvel = isApprovel();

        this.advisorOfDepartment = null;
        this.advisorOfYear = null;
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
    public Years getAdvisorOfYear()
    {
        return advisorOfYear;
    }
    public Gender getGender()
    {
        return gender;
    }
    public boolean isApprovel()
    {
        return approvel;
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
    public void setAdvisorOfClass(Years advisorOfYear)
    {
        this.advisorOfYear = advisorOfYear;
    }
    public void setGender(Gender gender)
    {
        this.gender = gender;
    }
    public void setApprovel(boolean approvel)
    {
        this.approvel = approvel;
    }






}
