package com;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
@WebServlet(name = "GetCustomer", value="/value")
public class GetCustomer extends HttpServlet{
	  Connection conn;
	  final String createCustomerTableSql = "CREATE TABLE IF NOT EXISTS customer (user_id INT NOT NULL AUTO_INCREMENT, username VARCHAR(64) NOT NULL,info VARCHAR(1000) NOT NULL, PRIMARY KEY (user_id));";
	  final String getCustomerSql = "SELECT * FROM customer where username = ? order by user_id desc";
	  @Override
	  public void init() throws ServletException {
	    try {
	      String url = System.getProperty("cloudsql");
	      url = "jdbc:google:mysql://webserviceproject-1998:asia-east1:myinstance/content?user=root&password=hello1234";	  
	      try {
	        conn = DriverManager.getConnection(url);
	        // Create the tables so that the SELECT query doesn't throw an exception
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
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
		throws ServletException, IOException {
		response.setContentType("text/json");
		    response.setCharacterEncoding("UTF-8");
		    String username = request.getParameter("username");
		    try (PreparedStatement statementGet = conn.prepareStatement(getCustomerSql)) {
		    	statementGet.setString(1, username);
		        ResultSet rs = statementGet.executeQuery();
		        while(rs.next()) {
		            String info = rs.getString("info");
		            response.getWriter().print("{\"Username\":\""+username+"\" , \"Info\":\""+info+"\"}");
		            break;
		      }
		    }catch (SQLException e) {
		    	   throw new ServletException("SQL error when creating post", e);
		    }	    
		}
	}
