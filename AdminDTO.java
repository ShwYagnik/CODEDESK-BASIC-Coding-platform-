package src.com.thinking.machines.dl.dao;
public class AdminDTO implements java.io.Serializable,Comparable<AdminDTO>
{
private String username;
private String password;
public AdminDTO()
{
this.username=null;
this.password=null;
}
public void setUsername(String username)
{
this.username=username;
}
public String getUsername()
{
return this.username;
}
public void setPassword(String password)
{
this.password=password;
}
public String getPassword()
{
return this.password;
}
public boolean equals(Object other)
{
if(!(other instanceof AdminDTO)) return false;
AdminDTO adminDTO=(AdminDTO)other;
return this.username.equals(adminDTO.getUsername());
}
public int compareTo(AdminDTO adminDTO)
{
return this.username.compareTo(adminDTO.getUsername());
}
public int hashCode()
{
return this.username.hashCode();
}
}