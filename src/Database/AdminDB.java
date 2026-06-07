package Database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class AdminDB
{
    public static void pendingStaffs()
    {
        try
        {
            String query = " SELECT * FROM staffs WHERE approval = false";
            Connection con = DBConnection.connection();
            PreparedStatement pst = con.prepareStatement(query);


            ResultSet rs = pst.executeQuery();

            boolean found = false;

            while(rs.next())
            {
                found = true;
              System.out.println("Pending staffs :"+ "Name :"+rs.getString("name")+ " Id:"+rs.getString("id"));
            }

            if(!found)
            {
              System.out.println("No pending Staffs..");
            }
            rs.close();
            pst.close();
            con.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }


    public static void approveStaffs(String id)
    {
        try
        {
            String query = "UPDATE staffs SET approval = ? WHERE id = ?";
            Connection con = DBConnection.connection();
            PreparedStatement pst = con.prepareStatement(query);

            pst.setBoolean(1,true);
            pst.setString(2,id);

            pst.executeUpdate();

            pst.close();
            con.close();
        }

        catch(Exception e)
        {
            e.printStackTrace();
        }
    }


}
