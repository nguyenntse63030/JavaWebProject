/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.err;

import java.io.Serializable;

/**
 *
 * @author Mr.Long
 */
public class ContentsStatusErr implements Serializable{
    private String contentsLengthErr;

    /**
     * @return the contentsLengthErr
     */
    public String getContentsLengthErr() {
        return contentsLengthErr;
    }

    /**
     * @param contentsLengthErr the contentsLengthErr to set
     */
    public void setContentsLengthErr(String contentsLengthErr) {
        this.contentsLengthErr = contentsLengthErr;
    }
    
}
