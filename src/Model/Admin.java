package Model;

public class Admin
{
    private String name;
    private String id;
    private String password;

    public Admin(String name,String id,String password)
    {
       this.name = name;
       this.id = id;
       this.password = password;
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

     // Setters

    public void setName(String name)
    {
        this.name = name;
    }
    public void setId(String id)
    {
        this.id = id;
    }
    public void setPassword(String password)
    {
        this.password = password;
    }
}
