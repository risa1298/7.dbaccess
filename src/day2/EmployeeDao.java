package day2;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDao {
    private static final String TABLE_NAME= "employees";

    public Employee load(int id){
        Connection con = DBManager.createConnection();

        String sql="SELECT id,name,age,gender,department_id from " + TABLE_NAME + " WHERE id = ?";

        try{
            PreparedStatement pstmt =con.prepareStatement(sql);
            pstmt.setInt(1,id);
            ResultSet rs =pstmt.executeQuery();

            if(rs.next()){
                Employee employee = new Employee();
                employee.setId(rs.getInt("id"));
                employee.setName(rs.getString("name"));
                employee.setAge(rs.getInt("age"));
                employee.setGender(rs.getString("gender"));
                employee.setDepartmentId(rs.getInt("department_id"));
                return employee;
            }
            return null;
        }catch(SQLException ex){
            System.err.println("SQL="+sql);
            throw new RuntimeException("load処理に失敗しました",ex);
        }finally{
            DBManager.closeConnection(con);
        }
    }

    public List<Employee> findByDepartmentId(int departmentId){
        Connection con = DBManager.createConnection();
        String sql ="select id,name,gender,department_id from " + TABLE_NAME + " where department_id = ?";
        try{
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setInt(1,departmentId);
            ResultSet rs = pstmt.executeQuery();
            List<Employee> employeeList=new ArrayList<>();

            while(rs.next()){
                Employee employee = new Employee();
                employee.setId(rs.getInt("id"));
                employee.setName(rs.getString("name"));
                employee.setGender(rs.getString("gender"));
                employee.setDepartmentId(rs.getInt("department_id"));
                employeeList.add(employee);
            }
            return employeeList;
        }catch(SQLException ex){
            System.err.println("SQL = "+ sql);
            throw new RuntimeException("検索処理に失敗しました",ex);
        }finally{
            DBManager.closeConnection(con);
        }
    }

    public int insert(Employee employee){
        //データベースへの接続とSQL文の設定
        Connection con = DBManager.createConnection();
        String sql = "insert into " + TABLE_NAME + " (id,name,age,gender,department_id)" + " values " + "(?,?,?,?,?)";
        //mainメソッドで与えられた引数をpstmtのSQL文に渡してセットする（？が前から何番目か,Entity(employee)のどの情報をゲットするか）
        try{
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setInt(1,employee.getId());
            pstmt.setString(2,employee.getName());
            pstmt.setInt(3,employee.getAge());
            pstmt.setString(4, employee.getGender());
            pstmt.setInt(5,employee.getDepartmentId());
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

    public int update(Employee employee){
        Connection con = DBManager.createConnection();
        String sql = "update "+ TABLE_NAME + "set name = ?,age = ?,gender = ?,department_id = ? where id = ?";

        try{
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setString(1,employee.getName());
            pstmt.setInt(2, employee.getAge());
            pstmt.setString(3, employee.getGender());
            pstmt.setInt(4,employee.getDepartmentId());

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