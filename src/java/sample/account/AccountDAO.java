/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.account;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.naming.NamingException;
import sample.connection.MyConnection;

/**
 *
 * @author Mr.Long
 */
public class AccountDAO implements Serializable {

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

    public boolean checkLogin(String username, String password) throws SQLException, NamingException {
        try {
            con = MyConnection.getMyConnection();
            if (con != null) {
                String sql = "select username, password, status from account where username = ? and password = ? and status != ?";
                stm = con.prepareStatement(sql);
                stm.setString(1, username);
                stm.setString(2, password);
                stm.setString(3, "dropout");
                rs = stm.executeQuery();
                if (rs.next()) {
                    return true;
                }
            }
        } finally {
            closeConnection();
        }
        return false;
    }

    public String searchNameStudent(String studentID) throws SQLException, NamingException {
        String studentName = "";
        try {
            con = MyConnection.getMyConnection();
            if (con != null) {
                String sql = "Select firstName from student where studentID = ?";
                stm = con.prepareStatement(sql);
                stm.setString(1, studentID);
                rs = stm.executeQuery();
                if (rs.next()) {
                    studentName = rs.getString("firstName");
                }
            }
        } finally {
            closeConnection();
        }
        return studentName;
    }

    public String searchFullNameStudent(String studentID) throws SQLException, NamingException {
        String fullName = "";
        try {
            con = MyConnection.getMyConnection();
            if (con != null) {
                String sql = "Select firstName, middleName, lastName from student where studentID = ?";
                stm = con.prepareStatement(sql);
                stm.setString(1, studentID);
                rs = stm.executeQuery();
                if (rs.next()) {
                    fullName = rs.getString("lastName") + " " + rs.getString("middleName") + " " + rs.getString("firstName") ;
                }
            }
        } finally {
            closeConnection();
        }
        return fullName;
    }
}
