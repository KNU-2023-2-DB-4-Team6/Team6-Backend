package comp322.NCVS.repository;

import comp322.NCVS.connection.DBConnectionUtil;
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
        String sql = "select * from HAS where Store_id = ?";

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
                product.setProductId(rs.getString("Product_Id"));
                product.setQuantity(rs.getInt("Quantity"));
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
                searchForm.setStoreId(rs.getString("STORE_Id"));
                searchForm.setQuantity(rs.getInt("Quantity"));
                searchForms.add(searchForm);
            }
            return searchForms;
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
