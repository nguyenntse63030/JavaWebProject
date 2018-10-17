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
import sample.err.ContentsStatusErr;
import sample.marks.MarkDTO;

/**
 *
 * @author Mr.Long
 */
public class GetContentsServlet extends HttpServlet {

    private final String errPage = "writeContents.jsp";
    private final String feedBackPage = "feedBack.jsp";
    private final String markTabkePage = "ShowMarkTableServlet";
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
        String url = errPage;

        HttpSession session = request.getSession(false);
        ArrayList<MarkDTO> listResult = (ArrayList<MarkDTO>) session.getAttribute("feedBackInfo");
        ContentsStatusErr errors = new ContentsStatusErr();
        boolean error = false;
        
        try {
            String subjectID = request.getParameter("subjectID");
            int block = Integer.parseInt(request.getParameter("block"));
            String contents = request.getParameter("txtContents");

            if (contents.length() <= 0 || contents.length() > 250) {
                error = true;
                errors.setContentsLengthErr("Your contents must between 0-250 character");
            }
            if (error) {
                request.setAttribute("subjectID", subjectID);
                request.setAttribute("block", block);
                request.setAttribute("contentsErr", errors);
            } else {
                if (listResult != null) {
                    for (int i = 0; i < listResult.size(); i++) {
                        if (subjectID.equals(listResult.get(i).getSubjectID())) {
                            if (block == listResult.get(i).getBlock()) {
                                listResult.get(i).setStatus(contents);
                            }
                        }
                    }
                    session.setAttribute("feedBackInfo", listResult);
                    url = feedBackPage;
                }else{
                    url = markTabkePage;
                }
            }

        }catch(Exception ex){
            log("GetContentsServlet_Exception: " + ex.getMessage());
        }
        finally {
          
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
