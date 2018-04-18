package com;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.annotation.WebServlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
@WebServlet(name = "CreateCustomer", value="/create")
public class CreateCustomer extends HttpServlet {
  Connection conn;
  final String createCustomerTableSql = "CREATE TABLE IF NOT EXISTS customer (user_id INT NOT NULL AUTO_INCREMENT, username VARCHAR(64) NOT NULL,info VARCHAR(1000) NOT NULL, PRIMARY KEY (user_id));";
  final String createCustomerSql = "INSERT INTO customer (username, info) VALUES (?, ?)";

  @Override
  public void init() throws ServletException {
    try {
      String url = System.getProperty("cloudsql");
      url = "jdbc:google:mysql://webserviceproject-1998:asia-east1:myinstance/content?user=root&password=hello1234";
      try {
        conn = DriverManager.getConnection(url);
        conn.createStatement().executeUpdate(createCustomerTableSql); // create customer table
      } catch (SQLException e) {
        throw new ServletException("Unable to connect to SQL server", e);
      }
    }
    finally {
      // Nothing really to do here.
    }
  }
  
  @Override
  public void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    response.setContentType("text/json");
    response.setCharacterEncoding("UTF-8");
    String username = request.getParameter("username");
    String info = request.getParameter("info");
    
    // Build the SQL command to insert the blog post into the database
    try (PreparedStatement statementCreatePost = conn.prepareStatement(createCustomerSql)) {
	   statementCreatePost.setString(1, username);
	   statementCreatePost.setString(2, info);
	   statementCreatePost.executeUpdate();
	   response.getWriter().print("{\"status\":\"ok\"}");
    } catch (SQLException e) {
    	   throw new ServletException("SQL error when creating post", e);
    }
  }
}