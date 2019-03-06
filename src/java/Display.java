/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author mona
 */
public class Display extends HttpServlet {

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
       
       //connect to Database
       Connection con = null;
       Statement stmt = null;
       String databaseURL="jdbc:mysql://localhost:3306/user";
       String driverName = "com.mysql.jdbc.Driver";
       String user ="root";
       String password = "Mrk72gtz";
       try {
           Class.forName(driverName).newInstance();
           con = DriverManager.getConnection (databaseURL,user, password);
           stmt = con.createStatement();
       } catch(ClassNotFoundException | IllegalAccessException | InstantiationException | SQLException e){
           System.out.println("Error connecting to DB:" + e);
       }
       
       //select and display data
       
      
        try {
             String selectStmt = "select id, fname, lname, pnumber from studentuser";
              ResultSet rst = stmt.executeQuery(selectStmt);
            
               while (rst.next()) {
           //retrive by column name
           String id = rst.getString("ID");
           String fname = rst.getString("fname");
           String lname = rst.getString("lname");
           out.println("<p> the students data as following "+id + " "+fname + " "+ lname + "</p>");
           
       }
               out.print("<a href=\"index.html\"> Back</a>");
        } catch (SQLException ex) {
            Logger.getLogger(Display.class.getName()).log(Level.SEVERE, null, ex);
            out.println("Error: wrong User or Password" + ex);
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
