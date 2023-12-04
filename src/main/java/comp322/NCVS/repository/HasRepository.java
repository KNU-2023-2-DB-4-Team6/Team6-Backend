package comp322.NCVS.repository;

import comp322.NCVS.connection.DBConnectionUtil;
import comp322.NCVS.form.BuyForm;
import comp322.NCVS.form.FavoriteForm;
import comp322.NCVS.form.ProductForm;
import comp322.NCVS.form.SearchForm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;

@Repository
@Slf4j
public class HasRepository {
    public ArrayList<ProductForm> findAllProductId(String storeId) throws SQLException {
        String sql = "select * from HAS where STORE_ID = ?";

        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try{
            con = getConnection();
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, storeId);
            rs = pstmt.executeQuery();
            ArrayList<ProductForm> Products = new ArrayList<>();
            while (rs.next()){
                ProductForm product = new ProductForm();
                product.setProductId(rs.getString("PRODUCT_ID"));
                product.setQuantity(rs.getInt("QUANTITY"));
                Products.add(product);
            }
            return Products;
        }catch (SQLException e){
            throw e;
        }finally {

            close(con, pstmt, rs);
        }
    }

    public ArrayList<SearchForm> findSQByProductId(String productId) throws SQLException {
        String sql = "select * from HAS where PRODUCT_ID = ?";

        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try{
            con = getConnection();
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, productId);
            rs = pstmt.executeQuery();
            ArrayList<SearchForm> searchForms = new ArrayList<>();
            while (rs.next()){
                SearchForm searchForm = new SearchForm();
                searchForm.setStoreId(rs.getString("STORE_ID"));
                searchForm.setQuantity(rs.getInt("QUANTITY"));
                searchForms.add(searchForm);
            }
            return searchForms;
        }catch (SQLException e){
            throw e;
        }finally {

            close(con, pstmt, rs);
        }
    }

    public int isPurchasable(BuyForm buyForm) throws SQLException {
        String sql = "select * from HAS where PRODUCT_ID = ? AND STORE_ID = ?";

        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try{
            con = getConnection();
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, buyForm.getProductId());
            pstmt.setString(2, buyForm.getStoreId());
            rs = pstmt.executeQuery();
            if (rs.next()){
                return (rs.getInt("QUANTITY") - buyForm.getQuantity());
            }
            return -1;
        }catch (SQLException e){
            throw e;
        }finally {

            close(con, pstmt, rs);
        }
    }

    public void findQuantityById(FavoriteForm favoriteForm) throws SQLException {
        String sql = "select * from HAS where PRODUCT_ID = ? AND STORE_ID = ?";

        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try{
            con = getConnection();
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, favoriteForm.getProductId());
            pstmt.setString(2, favoriteForm.getStoreId());
            rs = pstmt.executeQuery();
            if (rs.next()){
                favoriteForm.setQuantity(rs.getInt("QUANTITY"));
            }
        }catch (SQLException e){
            throw e;
        }finally {

            close(con, pstmt, rs);
        }
    }

    public void updateBuy(int newQuantity, BuyForm buyForm) throws SQLException {
        String sql = "UPDATE HAS SET QUANTITY = ? WHERE PRODUCT_ID = ? AND STORE_ID = ?";

        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try{
            con = getConnection();
            pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, newQuantity);
            pstmt.setString(2, buyForm.getProductId());
            pstmt.setString(3, buyForm.getStoreId());
            rs = pstmt.executeQuery();
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
