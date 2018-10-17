/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import sample.marks.MarkDTO;

/**
 *
 * @author Mr.Long
 */
public class RemoveFeedBackServlet extends HttpServlet {

    private final String feedBackPage = "feedBack.jsp";

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
        String url = feedBackPage;
        
        HttpSession session = request.getSession(false);
        ArrayList<MarkDTO> listResult = (ArrayList<MarkDTO>) session.getAttribute("feedBackInfo");
        
        String success = (String) request.getAttribute("success");
        if (success != null) {
            request.setAttribute("success", success);
        }
        
        try {
            String[] listSubjectFeedBackRemove = (String[]) request.getAttribute("listSubjectFeedBackRemove");
            if (listSubjectFeedBackRemove != null) {
                String subjectID = "";
                int block = 0;
                if (listResult != null) {
                    for (int i = 0; i < listSubjectFeedBackRemove.length; i++) {
                        String[] blockAndSubjectID = listSubjectFeedBackRemove[i].split("_");
                        subjectID = blockAndSubjectID[0];
                        block = Integer.parseInt(blockAndSubjectID[1]);
                        for (int j = 0; j < listResult.size(); j++) {
                            if (subjectID.equals(listResult.get(j).getSubjectID())) {
                                if (block == listResult.get(j).getBlock()) {
                                    listResult.remove(j);
                                }
                            }
                        }
                    }
                }
                session.setAttribute("feedBackInfo", listResult);
            }
        } finally {
            RequestDispatcher rd = request.getRequestDispatcher(url);
            rd.forward(request, response);
            out.close();
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
