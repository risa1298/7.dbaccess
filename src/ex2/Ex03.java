package ex2;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Ex03 {
    public static void main(String[] args) {
        String url ="jdbc:postgresql://localhost:5432/student";
        String user = "postgres";
        String password = "postgres";

        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String sql = null;

        try{
            con = DriverManager.getConnection(url, user, password);
            sql = "select m.name,m.birth_day,m.gender,c.name as color_name from members as m inner join colors as c on m.color_id=c.id;";
            pstmt = con.prepareStatement(sql);
            rs = pstmt.executeQuery();

            while(rs.next()){
                String name = rs.getString("name");
                String birth_day = rs.getString("birth_day");
                String gender = rs.getString("gender");
                String color_name = rs.getString("color_name");

                System.out.print("name="+name);
                System.out.print("  birth_day="+birth_day);
                System.out.print("  gender="+gender);
                System.out.print("  color_name="+color_name);
                System.out.println();

            }
        }catch(SQLException ex){
            System.err.println("SQL関連のエラーが発生しました");
            System.err.println("SQL=" +sql);
            ex.printStackTrace();
        }finally{
            try{
                if(con != null){
                    con.close();
                }
                if(pstmt != null){
                    pstmt.close();
                }
                if(rs != null){
                    rs.close();
                }
            }catch(SQLException e){
                e.printStackTrace();
            }
        }

    }
}
