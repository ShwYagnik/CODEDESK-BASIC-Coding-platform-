package src.com.thinking.machines.dl.dao;
import java.sql.*;
import java.util.*;
public class AdminDAO
{
public boolean usernameExists(String username) throws DAOException
{
boolean found=false;
if(username.length()==0) throw new DAOException("Invalid Username");
PreparedStatement preparedStatement;
Connection connection;
ResultSet rs;
try
{
connection=DAOConnection.getConnection();
preparedStatement=connection.prepareStatement("select * from admin where username=?");
preparedStatement.setString(1,username);
rs=preparedStatement.executeQuery();

if(!(rs.next())) 
{
rs.close();
preparedStatement.close();
connection.close();
}
else
{
rs.close();
preparedStatement.close();
connection.close();
found=true;
}
}
catch(Exception e)
{
throw new DAOException(e.getMessage());
}
return found;
}
public boolean verifyUser(AdminDTO adminDTO) throws DAOException
{
String username;
String password;
PreparedStatement preparedStatement;
Connection c;
ResultSet rs;
boolean found=false;
if(adminDTO.getUsername().length()==0) throw new DAOException("Invalid username sent for verification");
if(adminDTO.getPassword().length()==0) throw new DAOException("Invalid password sent for verification");
username=adminDTO.getUsername();
try
{
if(usernameExists(username)==false) return found;
c=DAOConnection.getConnection();
preparedStatement=c.prepareStatement("select password from admin where username=?");
preparedStatement.setString(1,username);
rs=preparedStatement.executeQuery();

rs.next();
password=rs.getString("password");
if(password.equals(adminDTO.getPassword()))
{
found=true;
}
rs.close();
preparedStatement.close();
c.close();
}
catch(DAOException daoE)
{
throw new DAOException(daoE.getMessage());
}
catch(Exception e)
{
throw new DAOException(e.getMessage());
}
return found;
}
public void addUser(AdminDTO adminDTO) throws DAOException
{
Connection c;
PreparedStatement preparedStatement;

if(adminDTO.getUsername().length()==0) throw new DAOException("Invalid username to add");
if(adminDTO.getPassword().length()==0) throw new DAOException("Invalid password to add");
try
{
if(usernameExists(adminDTO.getUsername())) throw new DAOException("Username already exists");
c=DAOConnection.getConnection();
preparedStatement=c.prepareStatement("insert into admin values(?,?);");
preparedStatement.setString(1,adminDTO.getUsername());
preparedStatement.setString(2,adminDTO.getPassword());
preparedStatement.executeUpdate();

preparedStatement.close();
c.close();
}
catch(DAOException daoE)
{
throw new DAOException(daoE.getMessage());
}
catch(Exception e)
{
throw new DAOException(e.getMessage());
}
}
}
