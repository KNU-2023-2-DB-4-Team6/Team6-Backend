package comp322.NCVS.repository;

import comp322.NCVS.connection.DBConnectionUtil;
import comp322.NCVS.form.BuyForm;
import comp322.NCVS.form.LoginForm;
import comp322.NCVS.form.RevenueForm;
import comp322.NCVS.form.TopProductForm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;

@Repository
@Slf4j
public class PaymentRepository {
    public String save(String paymentId, BuyForm buyForm) throws SQLException {
        String sql = "insert into PAYMENT(PAYMENT_ID, PRODUCT_QUANTITY, STORE_ID, PRODUCT_ID) values (?, ?, ? ,?)";

        Connection con = null;
        PreparedStatement pstmt = null;

        try {
            con = getConnection();
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, paymentId);
            pstmt.setInt(2, buyForm.getQuantity());
            pstmt.setString(3, buyForm.getStoreId());
            pstmt.setString(4, buyForm.getProductId());
            pstmt.executeUpdate();

            return "new payment saved";
        } catch (SQLException e) {
            throw e;
        }finally {
            close(con,pstmt,null);
        }
    }

    public ArrayList<RevenueForm> findQandP(String storeId) throws SQLException {
        String sql = "select * from PAYMENT where STORE_ID = ?";

        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try{
            con = getConnection();
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, storeId);
            rs = pstmt.executeQuery();
            ArrayList<RevenueForm> revenueForms = new ArrayList<>();
            while (rs.next()){
                RevenueForm revenueForm = new RevenueForm();
                revenueForm.setQuantity(rs.getInt("PRODUCT_QUANTITY"));
                revenueForm.setProductId(rs.getString("PRODUCT_ID"));
                revenueForms.add(revenueForm);
            }
            return revenueForms;
        }catch (SQLException e){
            throw e;
        }finally {

            close(con, pstmt, rs);
        }
    }

    public ArrayList<TopProductForm> findTopProduct(String storeId) throws SQLException {
        String sql = "SELECT product_id, SUM(product_quantity) as tq FROM PAYMENT " +
                "WHERE store_id = ? " +
                "GROUP BY product_id " +
                "ORDER BY tq DESC";

        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try{
            con = getConnection();
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, storeId);
            rs = pstmt.executeQuery();
            ArrayList<TopProductForm> topProducts = new ArrayList<>();
            while (rs.next()){
                TopProductForm topProduct = new TopProductForm();
                topProduct.setProductId(rs.getString("PRODUCT_ID"));
                topProduct.setQuantity(rs.getInt("tq"));
                topProducts.add(topProduct);
            }
            return topProducts;
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
