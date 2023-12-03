package comp322.NCVS.repository;

import comp322.NCVS.connection.DBConnectionUtil;
import comp322.NCVS.form.AllStoreForm;
import comp322.NCVS.form.CVSForm;
import comp322.NCVS.form.OrderForm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;

@Repository
@Slf4j
public class OrderRepository {
    public void save(OrderForm orderForm) throws SQLException {
        String sql = "insert into PORDER(ORDER_ID, PRODUCT_QUANTITY, ARRIVAL_TIME, ARRIVAL_STATE, OWNER_ID, PRODUCT_ID) " +
                "values (?, ?, ?, ?, ?, ?)";

        Connection con = null;
        PreparedStatement pstmt = null;

        try {
            con = getConnection();
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, orderForm.getOrderId());
            pstmt.setInt(2, orderForm.getProduct_quantity());
            pstmt.setString(3, orderForm.getArrival_time());
            pstmt.setString(4, orderForm.getArrival_state());
            pstmt.setString(5, orderForm.getOwnerId());
            pstmt.setString(6, orderForm.getProductId());
            pstmt.executeUpdate();
            log.info("new order saved");
        } catch (SQLException e) {
            throw e;
        }finally {
            close(con,pstmt,null);
        }
    }

    public ArrayList<OrderForm> findByStoreId(String storeId) throws SQLException {
        String sql = "select * from PORDER where STORE_ID = ?";

        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try{
            con = getConnection();
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, storeId);
            rs = pstmt.executeQuery();
            ArrayList<OrderForm> orders = new ArrayList<>();
            while (rs.next()) {
                OrderForm order = new OrderForm();
                order.setOrderId(rs.getString("ORDER_ID"));
                order.setProduct_quantity(rs.getInt("PRODUCT_QUANTITY"));
                order.setArrival_time(rs.getString("ARRIVAL_TIME"));
                order.setArrival_state(rs.getString("ARRIVAL_STATE"));
                order.setOwnerId(rs.getString("OWNER_ID"));
                order.setProductId(rs.getString("PRODUCT_ID"));
                orders.add(order);
            }
            return orders;

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
