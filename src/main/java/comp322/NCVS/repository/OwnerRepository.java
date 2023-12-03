package comp322.NCVS.repository;

import comp322.NCVS.connection.DBConnectionUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.sql.*;

@Repository
@Slf4j
public class OwnerRepository {
    public String save(String Owner_Id, String id, String password) throws SQLException {
        String sql = "insert into OWNER(Owner_Id, id, password) values (?, ?, ?)";

        Connection con = null;
        PreparedStatement pstmt = null;

        try {
            con = getConnection();
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, Owner_Id);
            pstmt.setString(2, id);
            pstmt.setString(3, password);
            pstmt.executeUpdate();

            return "new owner saved";
        } catch (SQLException e) {
            throw e;
        }finally {
            close(con,pstmt,null);
        }
    }

    public String findPasswordById(String id) throws SQLException {
        String sql = "select * from Owner where id = ?";

        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try{
            con = getConnection();
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, id);
            rs = pstmt.executeQuery();
            if (rs.next()){
                return rs.getString("password");
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
