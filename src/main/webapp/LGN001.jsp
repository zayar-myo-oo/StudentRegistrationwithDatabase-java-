<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="org.apache.catalina.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
  <!DOCTYPE html>
  <html>
  <head>
    <link rel="stylesheet" href="test.css">
    <title> Student Registration LGN001 </title>
  </head>
  <body class="login-page-body">

    <div class="login-page">
      <div class="form">
        <div class="login">
          <div class="login-header">
            <h1>Welcome!</h1>   
            <p>${error}</p>
          </div>
        </div>
        <form class="login-form" action="LoginUserServlet" method="post" name="confirm">
          <input type="text" placeholder="User ID" value="USR1" name="lguserid"/>
          <input type="password" placeholder="Password" value="zayar" name="lgpassword"/>
          <button>login</button>
          <p class="message">Not registered? <a href="#">Create an account</a></p>
        </form>
      </div>
    </div>
  </body>

  </html>