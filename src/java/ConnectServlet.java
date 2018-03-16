/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.sql.Connection;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Raul
 */
@WebServlet(urlPatterns = {"/miservlet"})
public class ConnectServlet extends HttpServlet {

    private String user;
    private String password;
    private String bd1;
    private String bd2;
    private String url;
    private String dbdriver;
    PrintWriter out = null;
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        PropertyValues pv = new PropertyValues();
                out = response.getWriter();

                
        user = pv.getPropValues().getProperty("user");
        password = pv.getPropValues().getProperty("password");
        bd1 = pv.getPropValues().getProperty("bd1");
        bd2 = pv.getPropValues().getProperty("url");
        url = pv.getPropValues().getProperty("url");
        dbdriver = pv.getPropValues().getProperty("dbdriver");
        String url_completa = url+bd1;

//                    Connection conn = DriverManager.getConnection(url, user, password);
        // Establecer conexión
            try  {
                   Class.forName(dbdriver).newInstance();
                    Connection conn = DriverManager.getConnection(url, user, password);
                   System.out.println("Conectado OK");  
        
        autenticar(conn, request.getParameter("usuario"),request.getParameter("password"));
        
            }catch(Exception e){
            System.err.println(e.getMessage());
            }
        
    }

    private void autenticar(Connection conn, String usr, String pss){
        
        String str ="SELECT * FROM USUARIO WHERE USUARIO=? AND PASSWORD=?";
        
        try (PreparedStatement ps = conn.prepareStatement(str);
                )
            {
            ps.setString(1, usr);
            ps.setString(2, pss);
            
            ps.executeQuery();
            ResultSet rs = ps.getResultSet();
            if (!rs.wasNull())        

                    while (rs.next()) {

                        if (rs.getString("usuario").equals(usr)&&
                                rs.getString("password").equals(pss)){
                             

                            out.println("LOGIN CORRECTO");
        

                        }
                    }
            
        }catch (Exception e){
        
            System.err.println(e.getMessage());
        }
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
        doGet(request, response);
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
