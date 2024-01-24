package ex2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Ex01 {
    public static void main(String[] args) {
        String url = "jdbc:postgresql://localhost:5432/student";
        String user ="postgres";
        String password ="postgres";

        Connection con = null;
        PreparedStatement pstmt1 = null;
        PreparedStatement pstmt2 = null;
        String sql1 = null;
        String sql2 = null;

        try{
            con = DriverManager.getConnection(url,user,password);
            sql1 = "drop table if exists colors; create table colors(id integer primary key,name text)";
            sql2 = " drop table if exists member; create table members(id serial primary key,name text not null,birth_day date,gender varchar(1),color_id integer references colors (id))";
            pstmt1 = con.prepareStatement(sql1);
            int numOfUpdate1 = pstmt1.executeUpdate();
            System.out.println(numOfUpdate1 + "件のデータを操作しました");
            pstmt2 = con.prepareStatement(sql2);
            int numOfUpdate2 = pstmt2.executeUpdate();
            System.out.println(numOfUpdate2 + "件のデータを操作しました");

        }catch(SQLException ex){
            System.err.println("SQL関連のエラーが発生しました");
            System.err.println("SQL=" + sql1 +"or"+ sql2);
            ex.printStackTrace();
        }finally{
            try{
                if(con != null){
                    con.close();
                }
                if(pstmt1 != null){
                    pstmt1.close();
                }
                if(pstmt2 != null){
                    pstmt2.close();
                }
                
            }catch(SQLException e){
                e.printStackTrace();
            }
        }

    }
}
