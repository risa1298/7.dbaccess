// package practice;

// public class Exam1 {
//     Create table members(id Integer primary key,)
//     "insert into members (name,birth_day,gender)values('risa','1998-12-04','女');";
//     update members set(name = '',,,,)where id=
//     delete from members where id=
//     drop table members


//     String url
//     String user
//     String password
//     String sql="";

//     try(Coccection con = DriverManager.getConnection(url,user,password);){
//         con.setAutoCommit(false);
//         try(PreparedStatement pstmt = con.preparedStatement(sql);){
//             int numOfUpdate = pstmt.executeUpdate();
//             System.out.println(numOfUpdate+"件のデータを操作しました");
//             con.commit();
//         }catch(SQLException e){
//             con.rollback();
//         }
//     }catch(SQLException ex){
//         System.err.println("例外が発生しました");
//         ex.ptrinStackTrace();
//     }

// }
