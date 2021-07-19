package src.com.thinking.machines.dl.dao;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
public class LogUser extends HttpServlet
{
public void doPost(HttpServletRequest rq,HttpServletResponse rs)
{
doGet(rq,rs);
}
public void doGet(HttpServletRequest rq,HttpServletResponse rs)
{
AdminDTO adminDTO;
AdminDAO adminDAO;
String username;
String password;
PrintWriter pw=null;
boolean verified=false;
try
{
pw=rs.getWriter();
username=rq.getParameter("username");
password=rq.getParameter("password");
adminDAO=new AdminDAO();
adminDTO=new AdminDTO();
adminDTO.setUsername(username);
adminDTO.setPassword(password);
verified=adminDAO.verifyUser(adminDTO);
if(verified)
{
pw.print("Logged in!");
pw.flush();
}
else
{
pw.print("Failed login!");
pw.flush();
}
}
catch(DAOException daoE)
{
try
{
rs.sendError(HttpServletResponse.SC_FORBIDDEN);
}
catch(Exception ee)
{
System.out.println(ee);
}
}
catch(Exception exec)
{
System.out.println(exec);
}
}
}