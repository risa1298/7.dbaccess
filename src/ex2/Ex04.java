package ex2;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Ex04 {
    public static void main(String[] args) {
        String url = "jdbc:postgresql://localhost:5432/student";
        String user = "postgres";
        String password = "postgres";

        Connection con = null;
        PreparedStatement pstmt = null;
        String sql = null;

        try{
            con = DriverManager.getConnection(url, user, password);
            sql = "update members set name = '金子 梨紗',birth_day = '1998-12-04',gender = '女',color_id =6 where name = '大野 智';";
            pstmt = con.prepareStatement(sql);
            int numOfUpdate = pstmt.executeUpdate();
            System.out.println(numOfUpdate + "件のデータを操作しました");

        }catch(SQLException ex){
            System.err.println("SQL関連のエラーが発生しました");
            System.err.println("SQL=" + sql);
            ex.printStackTrace();
        }finally{
            try{
                if(con != null){
                    con.close();
                }
                if(pstmt != null){
                    pstmt.close();
                }
            }catch(SQLException e){
                e.printStackTrace();
            }
        }
    }
}
