package src.com.thinking.machines.dl.dao;
import java.io.*;
import javax.servlet.*;
import com.google.gson.*;
import javax.servlet.http.*;
public class GetQuestionByQuestionCode extends HttpServlet
{
public void doPost(HttpServletRequest rq,HttpServletResponse rs)
{
doGet(rq,rs);
}
public void doGet(HttpServletRequest rq,HttpServletResponse rs)
{
PrintWriter pw=null;
QuestionsDAO questionsDAO;
String description;
int questionCode;
try
{
pw=rs.getWriter();
questionCode=Integer.parseInt(rq.getParameter("code"));
Gson gson=new Gson();
questionsDAO=new QuestionsDAO();
description=questionsDAO.getQuestionByQuestionCode(questionCode);
String jsonString=gson.toJson(description);
pw.print(jsonString);
pw.flush();
}
catch(Exception eee)
{
try
{
rs.sendError(HttpServletResponse.SC_FORBIDDEN);
}
catch(Exception ees)
{
System.out.println(ees);
}
System.out.println(eee);
}
}
}