package comp322.NCVS.repository;

import comp322.NCVS.connection.DBConnectionUtil;
import comp322.NCVS.form.FavoriteForm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;

@Repository
@Slf4j
public class FavoriteRepository {
    public String save(FavoriteForm favoriteForm) throws SQLException {
        String sql = "insert into FAVORITE(CLIENT_ID, STORE_ID, PRODUCT_ID) values (?, ?, ?)";

        Connection con = null;
        PreparedStatement pstmt = null;

        try {
            con = getConnection();
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, favoriteForm.getClientId());
            pstmt.setString(2, favoriteForm.getStoreId());
            pstmt.setString(3, favoriteForm.getProductId());
            pstmt.executeUpdate();

            return "new favorite saved";
        } catch (SQLException e) {
            throw e;
        }finally {
            close(con,pstmt,null);
        }
    }

    public void delete(FavoriteForm favoriteForm) throws SQLException {
        String sql = "delete from FAVORITE where CLIENT_ID = ? AND STORE_ID = ? AND PRODUCT_ID = ?";

        Connection con = null;
        PreparedStatement pstmt = null;

        try {
            con = getConnection();
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, favoriteForm.getClientId());
            pstmt.setString(2, favoriteForm.getStoreId());
            pstmt.setString(3, favoriteForm.getProductId());
            pstmt.executeUpdate();
            log.info("delete favorite");
        } catch (SQLException e) {
            throw e;
        }finally {
            close(con,pstmt,null);
        }
    }

    public Boolean isFavorite(String clientId, String storeId, String productId) throws SQLException {
        String sql = "select * from FAVORITE where CLIENT_ID = ? AND STORE_ID = ? AND PRODUCT_ID = ?";

        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try{
            con = getConnection();
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, clientId);
            pstmt.setString(2, storeId);
            pstmt.setString(3, productId);
            rs = pstmt.executeQuery();
            if (rs.next()){
                return true;
            }
            return false;

        }catch (SQLException e){
            throw e;
        }finally {

            close(con, pstmt, rs);
        }
    }

    public ArrayList<FavoriteForm> findAllFavorite(String clientId) throws SQLException {
        String sql = "select * from FAVORITE where CLIENT_ID = ?";

        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try{
            con = getConnection();
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, clientId);
            rs = pstmt.executeQuery();
            ArrayList<FavoriteForm> favorites = new ArrayList<>();
            while (rs.next()){
                FavoriteForm favorite = new FavoriteForm();
                favorite.setClientId(rs.getString("CLIENT_ID"));
                favorite.setStoreId(rs.getString("STORE_ID"));
                favorite.setProductId(rs.getString("PRODUCT_ID"));
                favorites.add(favorite);
            }
            return favorites;
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

