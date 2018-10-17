/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.feedback;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import javax.naming.NamingException;
import sample.connection.MyConnection;

/**
 *
 * @author Mr.Long
 */
public class FeedBackDAO implements Serializable {

    private Connection con = null;
    private PreparedStatement stm = null;
    private ResultSet rs = null;

    private void closeConnection() throws SQLException {
        if (rs != null) {
            rs.close();
        }
        if (stm != null) {
            stm.close();
        }
        if (con != null) {
            con.close();
        }
    }

    public boolean insertFeedBack(int feedBackId, Timestamp fbDate, String contents, String studentID, boolean feedBackStatus) throws SQLException, NamingException {
        try {
            con = MyConnection.getMyConnection();
            if (con != null) {
                String sql = "insert into feedback(id, fbDate, contents, studentID, status) "
                        + "values(?, ?, ?, ?, ?)";
                stm = con.prepareStatement(sql);

                stm.setInt(1, feedBackId);
                //stm.setDate(2, fbDate);
                stm.setTimestamp(2, fbDate);
                stm.setString(3, contents);
                stm.setString(4, studentID);
                stm.setBoolean(5, feedBackStatus);

                int row = stm.executeUpdate();
                if (row > 0) {
                    return true;
                }
            }
        } finally {
            closeConnection();
        }
        return false;
    }

    public int getMaxIdFeedBack() throws SQLException, NamingException {
        int result = 0;
        con = MyConnection.getMyConnection();
        if (con != null) {
            String sql = "select Max(id) as count from feedback";
            stm = con.prepareStatement(sql);
            rs = stm.executeQuery();
            if (rs.next()) {
                result = rs.getInt("count");
            }
        }
        return result;
    }
}
