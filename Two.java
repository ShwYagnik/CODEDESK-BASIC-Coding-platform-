package src.com.thinking.machines;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
public class Two extends HttpServlet
{
public void doPost(HttpServletRequest rq,HttpServletResponse rs)
{
doGet(rq,rs);
}
public void doGet(HttpServletRequest rq,HttpServletResponse rs)
{
PrintWriter pw;
String g;
try
{
rs.setContentType("text/plain");
rs.setCharacterEncoding("utf-8");
pw=rs.getWriter();
g=rq.getParameter("code");
System.out.println(g);
pw.print(g);
pw.flush();
}
catch(Exception e)
{
System.out.println(e);
}
}
}