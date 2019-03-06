/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author mona
 */
public class getUserDetails extends HttpServlet {

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
       //Get user inputs
       String id = request.getParameter("id");
       String fname = request.getParameter("firstname");
       String lname = request.getParameter("lastname");
       String pnumber = request.getParameter("phonenumber");
       
       out.println("<h1> this is your data:" +id + fname+ "</h1>" );
       
       //connect to database
       Connection con = null;
       Statement stmt = null;
       String databaseURL="jdbc:mysql://localhost:3306/user";
       String driverName = "com.mysql.jdbc.Driver";
       String user ="root";
       String password = "root";
       try {
           Class.forName(driverName).newInstance();
           con = DriverManager.getConnection (databaseURL,user, password);
           stmt = con.createStatement();
       } catch(ClassNotFoundException | IllegalAccessException | InstantiationException | SQLException e){
           System.out.println("Error connecting to DB:" + e);
       }
       
       //insert data
       
       String insertStmt = "INSERT INTO studentuser (ID,fname,lname,pnumber) values ('"+id+"', '"+fname+"', '"+lname+"','"+pnumber+"')";
       
        try {
            stmt.executeUpdate(insertStmt);
            
            
            
            
            
        } catch (SQLException e) {
            System.out.println("Error: Insert into DB" +e);
           
        }
        //close database
        try {
            
            stmt.close();
            con.close();
        } catch (SQLException e) {
            System.out.println("Workshop Register: Error: Closing DB connection: " +e);
            
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
