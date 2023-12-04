package comp322.NCVS.repository;

import comp322.NCVS.connection.DBConnectionUtil;
import comp322.NCVS.form.LoginForm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.sql.*;

@Repository
@Slf4j
public class ClientRepository {
    public String save(String Client_Id, LoginForm loginForm) throws SQLException {
        String sql = "insert into CLIENT(Client_Id, ID, PASSWARD, LOCATION_X, LOCATION_Y) values (?, ?, ?, ? ,?)";

        Connection con = null;
        PreparedStatement pstmt = null;

        try {
            con = getConnection();
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, Client_Id);
            pstmt.setString(2, loginForm.getId());
            pstmt.setString(3, loginForm.getPassword());
            pstmt.setDouble(4, loginForm.getLoc_x());
            pstmt.setDouble(5,loginForm.getLoc_y());
            pstmt.executeUpdate();

            return "new client saved";
        } catch (SQLException e) {
            throw e;
        }finally {
            close(con,pstmt,null);
        }
    }

    public String findPasswordById(String id) throws SQLException {
        String sql = "select * from CLIENT where id = ?";

        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try{
            con = getConnection();
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, id);
            rs = pstmt.executeQuery();
            if (rs.next()){
                return rs.getString("PASSWARD");
            }else{
                return null;
            }
        }catch (SQLException e){
            throw e;
        }finally {

            close(con, pstmt, rs);
        }
    }


    public String findKeyIdById(String id) throws SQLException {
        String sql = "select * from CLIENT where id = ?";

        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try{
            con = getConnection();
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, id);
            rs = pstmt.executeQuery();
            if (rs.next()){
                return rs.getString("CLIENT_ID");
            }else{
                return null;
            }
        }catch (SQLException e){
            throw e;
        }finally {

            close(con, pstmt, rs);
        }
    }

    private void close(Connection con, Statement stmt, ResultSet rs){

        if (rs != null){
            try {
                rs.close();
            } catch (SQLException e) {
            }
        }

        if (stmt != null){
            try {
                stmt.close();
            } catch (SQLException e) {
            }
        }

        if (con != null){
            try {
                con.close();
            } catch (SQLException e) {
            }
        }
    }

    private Connection getConnection(){
        return DBConnectionUtil.getConnection();
    }
}

