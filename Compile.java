package src.com.thinking.machines.dl.dao;
import java.io.*;
import java.util.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;
import com.google.gson.*;
public class Compile extends HttpServlet
{
public void doPost(HttpServletRequest rq,HttpServletResponse rs)
{
doGet(rq,rs);
}
public void doGet(HttpServletRequest rq,HttpServletResponse rs)
{
PrintWriter pw=null;
int qCode;
String answer="";
int lCode;
List<String> jString;
String actualProgram=null;
PreparedStatement preparedStatement;
Connection c;
ResultSet rss;
boolean isAnArray;
int arraySize;
File file;
RandomAccessFile raf;
String dataType=null;
String inFiles=null;
String outFiles=null;
String outputCame=null;
List<String> listing;
List<String> inputGiven;
String g;
String errs=null;
String errorArray[];
int success[];
int j,i,k;
String errorGot=null;
try
{
jString=new ArrayList<>();
Gson gson=new Gson();
pw=rs.getWriter();
//an array to see whether both the testcases pass or not
qCode=Integer.parseInt(rq.getParameter("qCode"));
lCode=Integer.parseInt(rq.getParameter("lCode"));
actualProgram=rq.getParameter("program");
//servlet se data nikala

c=DAOConnection.getConnection();
preparedStatement=c.prepareStatement("select is_an_array,array_size,data_type,in_file,out_file from ioset where question_code=?");
preparedStatement.setInt(1,qCode);
rss=preparedStatement.executeQuery();
inputGiven=new ArrayList<>();
success=new int[2];
errorArray=new String[2];
j=0;
k=0;
while(rss.next())
{

isAnArray=rss.getBoolean("is_an_array");
arraySize=rss.getInt("array_size");
dataType=rss.getString("data_type");
inFiles=rss.getString("in_file");
outFiles=rss.getString("out_file");

//call letscheck method of class Script
listing=new ArrayList<>();
file=new File(inFiles);
raf=new RandomAccessFile(file,"rw");
while(raf.getFilePointer()<raf.length())
{
g=raf.readLine();
listing.add(g);
inputGiven.add(g);
}
raf.close();
Script ss=new Script();
outputCame=ss.letscheck(lCode,actualProgram,listing,errorGot);
System.out.println(outputCame);
if(outputCame.length()>5 && outputCame.endsWith("ERROR"))
{
System.out.println("GOT ERROR"+outputCame);
errs=outputCame.substring(0,outputCame.length()-5);
}
else
{
//checking and verification

if(dataType.equals("int"))
{
System.out.println("FIle to be opened :  "+outFiles);
file=new File(outFiles);
raf=new RandomAccessFile(file,"rw");
answer=raf.readLine();
System.out.println("ANSWER :"+answer);
raf.close();
if(Integer.parseInt(answer)==Integer.parseInt(outputCame))
{
success[j]=1;
System.out.println("MATCH");
}
else
{
errs="  OUTPUT: "+outputCame;
errs+="   EXPECTED OUTPUT: ";
errs+=answer;
success[j]=0;
System.out.println("UNMATCH");
}
}
else
{
//string
System.out.println("FIle to be opened :  "+outFiles);
file=new File(outFiles);
raf=new RandomAccessFile(file,"rw");
answer=raf.readLine();
raf.close();
if(answer.equals(outputCame))
{
success[j]=1;
}
else
{
success[j]=0;
}
}
}

j++;
k++;


}
rss.close();
preparedStatement.close();
c.close();




String jsonString=gson.toJson(errs);
pw.print(jsonString);
pw.flush();
//create a JSON here fill it with necessary information
//print the information in it using PrintWriter
}
catch(Exception eee)
{
System.out.println("C    "+eee);
}
}
}




