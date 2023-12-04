package comp322.NCVS.repository;

import comp322.NCVS.connection.DBConnectionUtil;
import comp322.NCVS.form.AllEventForm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;

@Repository
@Slf4j
public class EventRepository {
    public ArrayList<AllEventForm> findAllEvent() throws SQLException {
        String sql = "select * from EVENT";

        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try{
            con = getConnection();
            pstmt = con.prepareStatement(sql);
            rs = pstmt.executeQuery();
            ArrayList<AllEventForm> events = new ArrayList<>();

            while (rs.next()){
                AllEventForm event = new AllEventForm();
                event.setEventName(rs.getString("NAME"));
                event.setPolicy(rs.getString("POLICY"));
                event.setStart(rs.getString("ESTART"));
                event.setEnd(rs.getString("EEND"));
                event.setImage_Url(rs.getString("Image_Url"));
                events.add(event);
            }
            return events;
        }catch (SQLException e){
            throw e;
        }finally {

            close(con, pstmt, rs);
        }
    }

    public AllEventForm findById(String eventId) throws SQLException {
        String sql = "select * from EVENT where EVENT_ID = ?";

        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try{
            con = getConnection();
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, eventId);
            rs = pstmt.executeQuery();

            if (rs.next()){
                AllEventForm event = new AllEventForm();
                event.setEventName(rs.getString("NAME"));
                event.setPolicy(rs.getString("POLICY"));
                event.setStart(rs.getString("ESTART"));
                event.setEnd(rs.getString("EEND"));
                event.setImage_Url(rs.getString("Image_Url"));
                return event;
            }
            return null;
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
