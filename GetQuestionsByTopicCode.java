package src.com.thinking.machines.dl.dao;
import com.google.gson.*;
import java.sql.*;
import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
public class GetQuestionsByTopicCode extends HttpServlet
{
public void doPost(HttpServletRequest rq,HttpServletResponse rs)
{
doGet(rq,rs);
}
public void doGet(HttpServletRequest rq,HttpServletResponse rs)
{
QuestionsDAO questionsDAO;
List<QuestionsDTO> lists;
int code;
PrintWriter pw=null;
try
{
pw=rs.getWriter();
code=Integer.parseInt(rq.getParameter("code"));
questionsDAO=new QuestionsDAO();
lists=questionsDAO.getQuestionsByTopicCode(code);
Gson gson=new Gson();
String jsonString=gson.toJson(lists);
pw.print(jsonString);
pw.flush();
}
catch(Exception ee)
{
try
{
rs.sendError(HttpServletResponse.SC_FORBIDDEN);
}
catch(Exception eex)
{
System.out.println(eex);
}
System.out.println(ee);
}
}
}