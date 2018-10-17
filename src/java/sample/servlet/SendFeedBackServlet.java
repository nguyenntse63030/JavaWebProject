/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import sample.feedback.FeedBackDAO;
import sample.marks.MarkDTO;

/**
 *
 * @author Mr.Long
 */
public class SendFeedBackServlet extends HttpServlet {

    private final String removeFeedBack = "RemoveFeedBackServlet";
    private final String feedBackPage = "feedBack.jsp";

    private String subjectID = "";
    private int block = 0;
    private String contents = "";
    private int flag = 0;
    private int count = 0;
    private Map<Integer, Integer> flagCheck = null;
    private boolean error = false;
    private String studentID = "";

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        HttpSession seesion = request.getSession(false);
        studentID = (String) seesion.getAttribute("STUDENTID");
        ArrayList<MarkDTO> listResult = (ArrayList<MarkDTO>) seesion.getAttribute("feedBackInfo");
        String url = feedBackPage;

        try {
            String[] listSubjectFeedBackSend = (String[]) request.getAttribute("listSubjectFeedBackSend");
            checkSendFeedBack(listSubjectFeedBackSend, listResult);
            if (error) {
                request.setAttribute("flagCheck", flagCheck);
                error = false;
                subjectID = "";
                block = 0;
                contents = "";
                flag = 0;
                count = 0;
                flagCheck = null;

            } else {
                error = false;
                subjectID = "";
                block = 0;
                contents = "";
                flag = 0;
                count = 0;
                flagCheck = null;

                boolean result = SendFeedBack(listSubjectFeedBackSend, listResult);
                if (result) {
                    request.setAttribute("listSubjectFeedBackRemove", listSubjectFeedBackSend);
                    String success = "Send feedBack Success!!!";
                    request.setAttribute("success", success);
                    url = removeFeedBack;
                    
                } else {
                    String fall = "Send feedBack Fall!!!";
                    request.setAttribute("fall", fall);
                    url = feedBackPage;
                }

            }

        } catch (NumberFormatException ex) {
            log("SendFeedBackServlet_NumberFormatException: " + ex.getMessage());
        } finally {
            RequestDispatcher rd = request.getRequestDispatcher(url);
            rd.forward(request, response);
            out.close();
        }
    }

    private boolean SendFeedBack(String[] listSubjectFeedBackSend, ArrayList<MarkDTO> listResult) {
        boolean resultSendFeedBack = false;
        if (listSubjectFeedBackSend != null) {
            if (listResult != null) {

                FeedBackDAO dao = new FeedBackDAO();
                
                try {
                    for (int i = 0; i < listSubjectFeedBackSend.length; i++) {
                        
                        String[] infoFeedBackSend = listSubjectFeedBackSend[i].split("_");
                        subjectID = infoFeedBackSend[0];
                        block = Integer.parseInt(infoFeedBackSend[1]);
                        
                        for (int j = 0; j < listResult.size(); j++) {
                            
                            if (subjectID.equals(listResult.get(j).getSubjectID())) {
                                
                                if (block == listResult.get(j).getBlock()) {
                                    
                                    contents = listResult.get(j).getStatus();
                                    int idFeedBack = dao.getMaxIdFeedBack() + 1;
                                    
                                    long millis = System.currentTimeMillis();
                                    Timestamp date = new Timestamp(millis);
                                    
                                    String feedBackContents = "subjectID: " + subjectID + "_" + "Block: " + block + "_" + "contents: " + contents;
                                    
                                    resultSendFeedBack = dao.insertFeedBack(idFeedBack, date, feedBackContents, studentID, false);
                                }
                            }
                        }

                    }
                } catch (SQLException ex) {
                    log("SendFeedBackServlet_SQLException: " + ex.getMessage());
                } catch (NamingException ex) {
                    log("SendFeedBackServlet_NamingException: " + ex.getMessage());
                }

            }
        }
        return resultSendFeedBack;
    }

    private void checkSendFeedBack(String[] listSubjectFeedBackSend, ArrayList<MarkDTO> listResult) {
        if (listSubjectFeedBackSend != null) {
            if (listResult != null) {
                
                for (int i = 0; i < listSubjectFeedBackSend.length; i++) {
                    
                    String[] infoFeedBackSend = listSubjectFeedBackSend[i].split("_");
                    subjectID = infoFeedBackSend[0];
                    block = Integer.parseInt(infoFeedBackSend[1]);
                    flag = Integer.parseInt(infoFeedBackSend[2]);

                    for (int j = 0; j < listResult.size(); j++) {
                        if (subjectID.equals(listResult.get(j).getSubjectID())) {
                            if (block == listResult.get(j).getBlock()) {
                                
                                contents = listResult.get(j).getStatus();
                                count++;
                                
                                if (contents.length() == 0) {
                                    
                                    error = true;
                                    if (flagCheck == null) {
                                        flagCheck = new HashMap<>();
                                    }
                                    flagCheck.put(count, flag);

                                }
                            }
                        }
                    }
                }
            }

        }

    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
