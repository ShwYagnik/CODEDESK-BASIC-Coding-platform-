package com.thinking.machines.servlets;
import com.thinking.machines.dl.dao.*;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
public class VerifyUser extends HttpServlet
{
public void doPost(HttpServletRequest rq,HttpServletResponse rs)
{
doGet(rq,rs);
}
public void doGet(HttpServletRequest rq,HttpServletResponse rs)
{
PrintWriter pw;
AdminDTO adminDTO;
AdminDAO adminDAO;
String username;
String password;
try
{
pw=rs.getWriter();
username=rq.getParameter("username");
password=rq.getParameter("password");
adminDAO=new AdminDAO();
adminDTO=new AdminDTO();
adminDTO.setUsername(username);
adminDTO.setPassword(password);
adminDAO.addUser(adminDTO);
pw.print("User added successfully!!");
pw.flush();
}
catch(Exception e)
{
System.out.println(e);
rs.sendError(HttpServletResponse.SC_FORBIDDEN);
}
}
}