package comp322.NCVS.repository;

import comp322.NCVS.connection.DBConnectionUtil;
import comp322.NCVS.form.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;

@Repository
@Slf4j
public class StoreRepository {
    public ArrayList<AllStoreForm> findAllStore() throws SQLException {
        String sql = "select * from STORE";

        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try{
            con = getConnection();
            pstmt = con.prepareStatement(sql);
            rs = pstmt.executeQuery();
            ArrayList<AllStoreForm> allStore = new ArrayList<>();
            while (rs.next()){
                AllStoreForm oneStore = new AllStoreForm();
                oneStore.setStore_id(rs.getString("STORE_ID"));
                oneStore.setLoc_x(rs.getString("LOCATION_X"));
                oneStore.setLoc_y(rs.getString("LOCATION_Y"));
                allStore.add(oneStore);
            }
            return allStore;
        }catch (SQLException e){
            throw e;
        }finally {

            close(con, pstmt, rs);
        }
    }

    public ArrayList<String[]> findByOwnerId(String ownerId) throws SQLException {
        String sql = "select * from STORE where OWNER_ID = ?";

        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try{
            con = getConnection();
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1,ownerId);
            rs = pstmt.executeQuery();
            ArrayList<String[]> stores = new ArrayList<>();
            while (rs.next()){
                String[] store = {rs.getString("STORE_ID"),rs.getString("NAME"),rs.getString("ADDRESS"),rs.getString("PHONENUMBER")};
                stores.add(store);
            }
            return stores;
        }catch (SQLException e){
            throw e;
        }finally {

            close(con, pstmt, rs);
        }
    }

    public void findLocById(SearchForm searchForm) throws SQLException {
        String sql = "select * from STORE where STORE_ID = ?";

        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try{
            con = getConnection();
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1,searchForm.getStoreId());
            rs = pstmt.executeQuery();

            if (rs.next()){
                searchForm.setLoc_x(rs.getDouble("LOCATION_X"));
                searchForm.setLoc_y(rs.getDouble("LOCATION_Y"));
            }
        }catch (SQLException e){
            throw e;
        }finally {

            close(con, pstmt, rs);
        }
    }

    public OneStoreForm findById(String storeId) throws SQLException {
        String sql = "select * from STORE where STORE_ID = ?";

        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try{
            con = getConnection();
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1,storeId);
            rs = pstmt.executeQuery();
            OneStoreForm oneStoreForm = new OneStoreForm();
            if (rs.next()){
                oneStoreForm.setStoreId(storeId);
                oneStoreForm.setName(rs.getString("NAME"));
                oneStoreForm.setAddress(rs.getString("ADDRESS"));
                oneStoreForm.setPhoneNumber(rs.getString("PHONENUMBER"));
            }
            return oneStoreForm;
        }catch (SQLException e){
            throw e;
        }finally {

            close(con, pstmt, rs);
        }
    }

    public String save(CVSForm cvsInfo) throws SQLException {
        String sql = "insert into STORE(STORE_ID, NAME, ADDRESS, PHONENUMBER, LOCATION_X, LOCATION_Y, OWNER_ID) values (?, ?, ?, ?, ?, ?, ?)";

        Connection con = null;
        PreparedStatement pstmt = null;

        try {
            con = getConnection();
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, cvsInfo.getStoreId());
            pstmt.setString(2, cvsInfo.getNAME());
            pstmt.setString(3, cvsInfo.getAddress());
            pstmt.setString(4, cvsInfo.getPhoneNumber());
            pstmt.setDouble(5, cvsInfo.getLoc_x());
            pstmt.setDouble(6, cvsInfo.getLoc_y());
            pstmt.setString(7, cvsInfo.getOwnerId());

            pstmt.executeUpdate();

            return "new CVS saved";
        } catch (SQLException e) {
            throw e;
        }finally {
            close(con,pstmt,null);
        }
    }

    public void delete(String storeId) throws SQLException {
        String sql = "delete from STORE where STORE_ID = ?";

        Connection con = null;
        PreparedStatement pstmt = null;

        try {
            con = getConnection();
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, storeId);
            pstmt.executeUpdate();
            log.info("delete favorite");
        } catch (SQLException e) {
            throw e;
        }finally {
            close(con,pstmt,null);
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
