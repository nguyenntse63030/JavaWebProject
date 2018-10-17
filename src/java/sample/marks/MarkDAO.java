/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.marks;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;
import sample.connection.MyConnection;

/**
 *
 * @author Mr.Long
 */
public class MarkDAO implements Serializable {

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
    List<MarkDTO> listMark;

    public List<MarkDTO> getListMark() {
        return listMark;
    }

    public String searchSubjectIdForSubjectName(String subjectID) throws SQLException, NamingException {
        String subjectName = "";
        Connection con2 = null;
        PreparedStatement stm2 = null;
        ResultSet rs2 = null;
        try {
            con2 = MyConnection.getMyConnection();
            if (con2 != null) {
                String sql = "select subjectName from subject where subjectID = ?";
                stm2 = con2.prepareStatement(sql);
                stm2.setString(1, subjectID);
                rs2 = stm2.executeQuery();
                if (rs2.next()) {

                    subjectName = rs2.getString("subjectName");

                }
            }
        } finally {
            if (rs2 != null) {
                rs2.close();
            }
            if (stm2 != null) {
                stm2.close();
            }
            if (con2 != null) {
                con2.close();
            }
        }
        return subjectName;
    }

    public int searchSubjectIdForGetCredits(String subjectID) throws SQLException, NamingException {
        int credits = 0;
        Connection con3 = null;
        PreparedStatement stm3 = null;
        ResultSet rs3 = null;

        try {
            con3 = MyConnection.getMyConnection();
            if (con3 != null) {
                String sql = "select credits from subject where subjectID = ?";
                stm3 = con3.prepareStatement(sql);
                stm3.setString(1, subjectID);
                rs3 = stm3.executeQuery();
                if (rs3.next()) {
                    credits = rs3.getInt("credits");
                }
            }
        } finally {
            if (rs3 != null) {
                rs3.close();
            }
            if (stm3 != null) {
                stm3.close();
            }
            if (con3 != null) {
                con3.close();
            }
        }
        return credits;
    }

    public void searchStudentIdForGetMarkTable(String studentID) throws SQLException, NamingException {
        try {
            con = MyConnection.getMyConnection();
            if (con != null) {
                String sql = "select subjectID, blockSemester, subjectAvg, status from marks where studentID = ?";
                stm = con.prepareStatement(sql);
                stm.setString(1, studentID);
                rs = stm.executeQuery();

                String subjectID;
                String subjectName = "";
                int block;
                String semester;
                int year = 0;
                float subjectAvg;
                String status;
                String blockSemester;
                int lengthSemester;
                int credits = 0;
                while (rs.next()) {
                    subjectID = rs.getString("subjectID");

                    subjectAvg = rs.getFloat("subjectAvg");
                    status = rs.getString("status");

                    blockSemester = rs.getString("blockSemester");

                    subjectName = searchSubjectIdForSubjectName(subjectID);

                    credits = searchSubjectIdForGetCredits(subjectID);
                    String[] semesterSubject = blockSemester.split("_");

                    block = Integer.parseInt(semesterSubject[1]);

                    lengthSemester = semesterSubject[0].length();
                    year = Integer.parseInt(semesterSubject[0].substring(lengthSemester - 4));
                    semester = semesterSubject[0].substring(0, lengthSemester - 4);

                    if (this.listMark == null) {
                        this.listMark = new ArrayList<>();
                    }

                    MarkDTO dto = new MarkDTO(subjectID, subjectName, block, semester, year, subjectAvg, status, credits);
                    this.listMark.add(dto);
                }
            }
        } finally {
            closeConnection();
        }
    }

    public void searchDetailMark(String studentID, String subjectID) throws SQLException, NamingException {
        try {
            con = MyConnection.getMyConnection();
            if (con != null) {
                String sql = "select subjectID, blockSemester, subjectAvg, status from marks where studentID = ? and subjectID = ?";
                stm = con.prepareStatement(sql);
                stm.setString(1, studentID);
                stm.setString(2, subjectID);
                rs = stm.executeQuery();

                String subjectName;
                int block;
                String semester;
                int year = 0;
                float subjectAvg;
                String status;
                String blockSemester;
                int lengthSemester;

                while (rs.next()) {
                    subjectID = rs.getString("subjectID");
                    subjectName = searchSubjectIdForSubjectName(subjectID);
                    subjectAvg = rs.getFloat("subjectAvg");
                    status = rs.getString("status");

                    blockSemester = rs.getString("blockSemester");
                    String[] semesterSubject = blockSemester.split("_");

                    block = Integer.parseInt(semesterSubject[1]);

                    lengthSemester = semesterSubject[0].length();
                    year = Integer.parseInt(semesterSubject[0].substring(lengthSemester - 4));
                    semester = semesterSubject[0].substring(0, lengthSemester - 4);

                    if (this.listMark == null) {
                        this.listMark = new ArrayList<>();
                    }

                    MarkDTO dto = new MarkDTO(subjectID, subjectName, block, semester, year, subjectAvg, status);
                    this.listMark.add(dto);

                }
            }
        } finally {
            closeConnection();
        }
    }

    public void searchGetListForSendFeedBack(String subjectID, String studentID) throws SQLException, NamingException {

        try {
            con = MyConnection.getMyConnection();
            if (con != null) {
                String sql = "select subjectID, subjectAvg, blockSemester from marks where studentID = ? and subjectID = ?";
                stm = con.prepareStatement(sql);
                stm.setString(1, studentID);
                stm.setString(2, subjectID);
                rs = stm.executeQuery();

                String subjectName;
                float subjectAvg = (float) 0.0;
                String blockSemester;
                String status = "";
                int block;
                String semester;
                int year = 0;
                int lengthSemester;
                while (rs.next()) {
                    subjectID = rs.getString("subjectID");
                    subjectName = searchSubjectIdForSubjectName(subjectID);
                    subjectAvg = rs.getFloat("subjectAvg");
                    blockSemester = rs.getString("blockSemester");
                    String[] semesterSubject = blockSemester.split("_");

                    block = Integer.parseInt(semesterSubject[1]);

                    lengthSemester = semesterSubject[0].length();
                    year = Integer.parseInt(semesterSubject[0].substring(lengthSemester - 4));
                    semester = semesterSubject[0].substring(0, lengthSemester - 4);
                    if (this.listMark == null) {
                        this.listMark = new ArrayList<>();
                    }
                    
                    MarkDTO dto = new MarkDTO(subjectID, subjectName, block, semester, year, status, subjectAvg);
                    this.listMark.add(dto);
                }
            }
        } finally {
            closeConnection();
        }

    }
}
