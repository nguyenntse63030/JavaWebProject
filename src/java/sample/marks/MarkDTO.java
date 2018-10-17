/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.marks;

import java.io.Serializable;

/**
 *
 * @author Mr.Long
 */
public class MarkDTO implements Serializable {

    private String subjectID;
    private String subjectName;
    private int block;
    private String semester;
    private int year;
    private float subjectAvg;
    private String status;
    private int credits;
   
    public MarkDTO() {
    }

    public MarkDTO(String subjectID, String subjectName, int block, String semester, int year, float subjectAvg, String status) {
        this.subjectID = subjectID;
        this.subjectName = subjectName;
        this.block = block;
        this.semester = semester;
        this.year = year;
        this.subjectAvg = subjectAvg;
        this.status = status;
    }

    public MarkDTO(String subjectID, String subjectName, int block, String semester, int year, float subjectAvg, String status, int credits) {
        this.subjectID = subjectID;
        this.subjectName = subjectName;
        this.block = block;
        this.semester = semester;
        this.year = year;
        this.subjectAvg = subjectAvg;
        this.status = status;
        this.credits = credits;
    }

//    public MarkDTO(String subjectID, String subjectName, float subjectAvg) {
//        this.subjectID = subjectID;
//        this.subjectName = subjectName;
//        this.subjectAvg = subjectAvg;
//    }

    public MarkDTO(String subjectID, String subjectName, int block, String semester, int year, String status, float subjectAvg) {
        this.subjectID = subjectID;
        this.subjectName = subjectName;
        this.block = block;
        this.semester = semester;
        this.year = year;
         this.status = status;
        this.subjectAvg = subjectAvg;
    }

    /**
     * @return the subjectID
     */
    public String getSubjectID() {
        return subjectID;
    }

    /**
     * @param subjectID the subjectID to set
     */
    public void setSubjectID(String subjectID) {
        this.subjectID = subjectID;
    }

    /**
     * @return the block
     */
    public int getBlock() {
        return block;
    }

    /**
     * @param block the block to set
     */
    public void setBlock(int block) {
        this.block = block;
    }

    /**
     * @return the semester
     */
    public String getSemester() {
        return semester;
    }

    /**
     * @param semester the semester to set
     */
    public void setSemester(String semester) {
        this.semester = semester;
    }

    /**
     * @return the year
     */
    public int getYear() {
        return year;
    }

    /**
     * @param year the year to set
     */
    public void setYear(int year) {
        this.year = year;
    }

    /**
     * @return the subjectAvg
     */
    public float getSubjectAvg() {
        return subjectAvg;
    }

    /**
     * @param subjectAvg the subjectAvg to set
     */
    public void setSubjectAvg(float subjectAvg) {
        this.subjectAvg = subjectAvg;
    }

    /**
     * @return the status
     */
    public String getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(String status) {
        this.status = status;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public int getCredits() {
        return credits;
    }

    public void setCredits(int credits) {
        this.credits = credits;
    }
    
  
   
}
