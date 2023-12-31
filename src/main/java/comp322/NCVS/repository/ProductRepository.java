package comp322.NCVS.repository;

import comp322.NCVS.connection.DBConnectionUtil;
import comp322.NCVS.form.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.security.Key;
import java.sql.*;
import java.util.ArrayList;

@Repository
@Slf4j
public class ProductRepository {
    public void findProductInfo(ProductForm product) throws SQLException {
        String sql = "select * from PRODUCT where PRODUCT_ID = ?";

        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try{
            con = getConnection();
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, product.getProductId());
            rs = pstmt.executeQuery();
            if (rs.next()){
                product.setName(rs.getString("NAME"));
                product.setPrice(rs.getInt("PRICE"));
                product.setCategory(rs.getString("CATEGORY"));
                product.setImage_Url(rs.getString("Image_Url"));
            }
        }catch (SQLException e){
            throw e;
        }finally {
            close(con, pstmt, rs);
        }
    }

    public void findTopProductInfo(TopProductForm topProduct) throws SQLException {
        String sql = "select * from PRODUCT where PRODUCT_ID = ?";

        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try{
            con = getConnection();
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, topProduct.getProductId());
            rs = pstmt.executeQuery();
            if (rs.next()){
                topProduct.setName(rs.getString("NAME"));
                topProduct.setPrice(rs.getInt("PRICE"));
                topProduct.setCategory(rs.getString("CATEGORY"));
                topProduct.setImage_Url(rs.getString("Image_Url"));
            }
        }catch (SQLException e){
            throw e;
        }finally {
            close(con, pstmt, rs);
        }
    }

    public ArrayList<String> findByKeyword(String Keyword) throws SQLException {
        String sql = "select DISTINCT NAME from PRODUCT where NAME LIKE ?";

        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try{
            con = getConnection();
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, '%' + Keyword + '%');
            rs = pstmt.executeQuery();
            ArrayList<String> productNames = new ArrayList<>();
            while (rs.next()){
                productNames.add(rs.getString("NAME"));
            }
            return productNames;
        }catch (SQLException e){
            throw e;
        }finally {
            close(con, pstmt, rs);
        }
    }

    public String findIdByName(String Name) throws SQLException {
        String sql = "select * from PRODUCT where NAME = ?";

        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try{
            con = getConnection();
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, Name);
            rs = pstmt.executeQuery();
            if (rs.next()){
                return rs.getString("PRODUCT_ID");
            }
            return null;
        }catch (SQLException e){
            throw e;
        }finally {
            close(con, pstmt, rs);
        }
    }

    public void findInfoById(FavoriteForm favoriteForm) throws SQLException {
        String sql = "select * from PRODUCT where PRODUCT_ID = ?";

        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try{
            con = getConnection();
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, favoriteForm.getProductId());
            rs = pstmt.executeQuery();
            if (rs.next()){
                favoriteForm.setProductName(rs.getString("NAME"));
                favoriteForm.setPrice(rs.getInt("PRICE"));
                favoriteForm.setCategory(rs.getString("CATEGORY"));
                favoriteForm.setImage_Url(rs.getString("Image_Url"));
            }
        }catch (SQLException e){
            throw e;
        }finally {
            close(con, pstmt, rs);
        }
    }

    public void findNPCImageById(OrderForm order) throws SQLException {
        String sql = "select * from PRODUCT where PRODUCT_ID = ?";

        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try{
            con = getConnection();
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, order.getProductId());
            rs = pstmt.executeQuery();
            if (rs.next()){
                order.setImage_Url(rs.getString("Image_Url"));
                order.setProductName(rs.getString("NAME"));
                order.setProductPrice(rs.getInt("PRICE"));
                order.setCategory(rs.getString("CATEGORY"));
            }
        }catch (SQLException e){
            throw e;
        }finally {
            close(con, pstmt, rs);
        }
    }

    public int findCost(RevenueForm revenueForm) throws SQLException {
        String sql = "select * from PRODUCT where PRODUCT_ID = ?";

        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try{
            con = getConnection();
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, revenueForm.getProductId());
            rs = pstmt.executeQuery();
            if (rs.next()){
                return (rs.getInt("PRICE") * revenueForm.getQuantity());
            }
            return 0;
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
