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
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import sample.marks.MarkDAO;
import sample.marks.MarkDTO;

/**
 *
 * @author Mr.Long
 */
public class ShowMarkTableServlet extends HttpServlet {

    private final String markPage = "markTable.jsp";

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
        String url = markPage;

        try {
            /* TODO output your page here. You may use following sample code. */
            HttpSession session = request.getSession();
            String username = (String) session.getAttribute("STUDENTID");
            MarkDAO dao = new MarkDAO();
            dao.searchStudentIdForGetMarkTable(username);
            ArrayList<MarkDTO> resultMark = (ArrayList<MarkDTO>) dao.getListMark();
            if (resultMark != null) {
                for (int i = 0; i < resultMark.size() - 1; i++) {
                    for (int j = i + 1; j < resultMark.size(); j++) {
                        if (resultMark.get(i).getSubjectID().equals(resultMark.get(j).getSubjectID())) {
                            if (resultMark.get(i).getBlock() > resultMark.get(j).getBlock()) {
                                resultMark.remove(j);
                            } else {
                                resultMark.remove(i);
                            }
                        }
                    }
                }
                int credits = 0;
                float gpa = (float) 0.0;
                int count = 0;
                for (int i = 0; i < resultMark.size(); i++) {
                    if (!resultMark.get(i).getStatus().equals("Not Passed")) {
                        if (!resultMark.get(i).getStatus().equals("Not Started")) {
                            credits += resultMark.get(i).getCredits();
                        }

                    }
                    if (!resultMark.get(i).getStatus().equals("Not Started")) {
                        count++;
                        gpa += resultMark.get(i).getSubjectAvg();
                    }
                }
                gpa = (gpa / count);
                gpa = (float) (Math.ceil(gpa * 100.0) / 100.0);

                request.setAttribute("GPA", gpa);
                request.setAttribute("creditsPassed", credits);
                request.setAttribute("markResult", resultMark);
            }

        } catch (SQLException ex) {
            log("ShowMarkTableServlet _ SQLException: " + ex.getMessage());
            //ex.printStackTrace();
        } catch (NamingException ex) {
            log("ShowMarkTableServlet _ NamingException: " + ex.getMessage());
        } catch (NumberFormatException ex) {
            log("ShowMarkTableServlet _ NumberFormatException: " + ex.getMessage());
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
