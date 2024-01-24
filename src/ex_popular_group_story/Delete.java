package ex_popular_group_story;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Delete{
    public static void main(String[] args) {
        String url ="jdbc:postgresql://localhost:5432/student";
        String user ="postgres";
        String password ="postgres";

        Connection con = null;
        PreparedStatement pstmt = null;
        String sql = null;

        try{
            con = DriverManager.getConnection(url,user,password);
            sql = "delete from members where name in('金子梨紗','櫻井 翔')";
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