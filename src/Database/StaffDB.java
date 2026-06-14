package Database;
import Model.Enum.Departments;
import Model.Enum.Years;
import Model.Enum.Gender;
import Model.Staff;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class StaffDB
{
    public static void staffRegister(Staff s)
    {
        try
        {
            String query = "INSERT INTO staffs (id,name,password,gender,approval,advisor_department,advisor_year) VALUES (?,?,?,?,?,?,?)";
            Connection con = DBConnection.connection();
            PreparedStatement pst = con.prepareStatement(query);

            pst.setString(1, s.getId());
            pst.setString(2, s.getName());
            pst.setString(3, s.getPassword());
            pst.setString(4, s.getGender().name());
            pst.setBoolean(5, s.isApprovel());
            pst.setString(6, null);
            pst.setString(7, null);

            pst.executeUpdate();

            System.out.println("Registerd Successfully..");

            pst.close();
            con.close();

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public static Staff staffLogin(String id,String password)
    {
        try
        {
            String query = " SELECT * FROM staffs WHERE id = ? AND password = ? ";
            Connection con = DBConnection.connection();
            PreparedStatement pst =  con.prepareStatement(query);

            pst.setString(1,id);
            pst.setString(2,password);

            ResultSet rs = pst.executeQuery();

            if(rs.next())
            {
                if(!rs.getBoolean("approval"))
                {
                   System.out.println(" Your Approval Still Pending...");
                   return null;
                }
                if(rs.getString("advisor_department") == null || rs.getString("advisor_year")==null)
                {
                    System.out.println("Not Asssigned Class");
                    return null;
                }
                    Staff s = new Staff(rs.getString("id"),
                                        rs.getString("name"),
                                        rs.getString("password"),
                                        Gender.valueOf(rs.getString("gender")),
                                        rs.getBoolean("approval")
                                      );

                  s.setAdvisorOfDepartment(Departments.valueOf(rs.getString("advisor_department")));
                  s.setAdvisorOfClass(Years.valueOf(rs.getString("advisor_year")));

                  return s;
            }
            else
            {
                System.out.println("Account not found..");
            }
            rs.close();
            pst.close();
            con.close();
        }
        catch (Exception e)
        {
           e.printStackTrace();
        }

        return null;
    }

}
