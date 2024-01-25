package dya2lesson2;
    import java.sql.Connection;
    import java.sql.DriverManager;
    import java.sql.PreparedStatement;
    import java.sql.ResultSet;
    import java.sql.SQLException;


        public class DepartmentDao {
        
            private static final String TABLE_NAME= "departments";

            public Department load(int id){
                Connection con = DBManager.createConnection();

                String sql="SELECT id,name from " + TABLE_NAME + " WHERE id = ?";

                try{
                    PreparedStatement pstmt =con.prepareStatement(sql);
                    pstmt.setInt(1,id);
                    ResultSet rs =pstmt.executeQuery();

                    if(rs.next()){
                        Department department = new Department();
                        department.setId(rs.getInt("id"));
                        department.setName(rs.getString("name"));
                        return department;
                    }
                    return null;
                }catch(SQLException ex){
                    System.err.println("SQL="+sql);
                    throw new RuntimeException("load処理に失敗しました",ex);
                }finally{
                    DBManager.closeConnection(con);
                }
            }

        
            public int insert(Department department){
                //データベースへの接続とSQL文の設定
                Connection con = DBManager.createConnection();
                String sql = "insert into " + TABLE_NAME + " (id,name)" + " values " + "(?,?)";
                //mainメソッドで与えられた引数をpstmtのSQL文に渡してセットする（？が前から何番目か,Entity(employee)のどの情報をゲットするか）
                try{
                    PreparedStatement pstmt = con.prepareStatement(sql);
                    pstmt.setInt(1,department.getId());
                    pstmt.setString(2,department.getName());
                //戻り値を呼び出し元に返す
                    int numOfUpdate = pstmt.executeUpdate();
                    return numOfUpdate;
                //エラー処理
                }catch(SQLException ex){
                    System.err.println("SQL=" + sql);
                    throw new RuntimeException("insert処理に失敗しました",ex);
                //データベースとの接続を切断する
                }finally{
                    DBManager.closeConnection(con);
                }
            }

            public int update(Department department){
                Connection con = DBManager.createConnection();
                String sql = "update "+ TABLE_NAME + "set name = ? where id = ?";

                try{
                    PreparedStatement pstmt = con.prepareStatement(sql);
                    pstmt.setString(1,department.getName());
                    pstmt.setInt(2, department.getId());

                    int numOfUpdate = pstmt.executeUpdate();
                    return numOfUpdate;

                }catch(SQLException ex){
                    System.err.println("SQL=" + sql);
                    throw new RuntimeException("update処理に失敗しました",ex);
                //データベースとの接続を切断する
                }finally{
                    DBManager.closeConnection(con);

                }
            }

            public int deleteById(int id){
                Connection con = DBManager.createConnection();
                String sql = "delete table " + TABLE_NAME + "where id = ?";

                try{
                    PreparedStatement pstmt = con.prepareStatement(sql);
                    pstmt.setInt(1, id);
                    int numOfUpdate = pstmt.executeUpdate();
                    return numOfUpdate;
                }catch(SQLException ex){
                    System.err.println("SQL =" + sql);
                    throw new RuntimeException("delete処理に失敗しました",ex);
                }finally{
                    DBManager.closeConnection(con);
                }


            }
}
