package src.com.thinking.machines.dl.dao;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import com.google.gson.*;
public class GetTopicDescription extends HttpServlet
{
public void doPost(HttpServletRequest rq,HttpServletResponse rs)
{
doGet(rq,rs);
}
public void doGet(HttpServletRequest rq,HttpServletResponse rs)
{
PrintWriter pw=null;
TopicDAO topicDAO;
String descr;
int code;
try
{
pw=rs.getWriter();
code=Integer.parseInt(rq.getParameter("code"));
topicDAO=new TopicDAO();
descr=topicDAO.getTopicDescriptionByCode(code);
Gson gson=new Gson();
String jsonString=gson.toJson(descr);
pw.print(descr);
pw.flush();
}
catch(Exception ee)
{
try
{
rs.sendError(HttpServletResponse.SC_FORBIDDEN);
}
catch(Exception ece)
{
System.out.println(ece);
}
System.out.println(ee);
}
}
}