package com.thinking.machines.hr.servlets;
import com.thinking.machines.hr.dl.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.util.*;
import java.sql.*;
public class DeleteDesignation extends HttpServlet
{
public void doGet(HttpServletRequest request,HttpServletResponse response)
{
PrintWriter pw=null;
int code=0;
try{
pw=response.getWriter();
try{
code=Integer.parseInt(request.getParameter("code"));
}catch(NumberFormatException nfw)
{
sendBackView(response);
return;
}
DesignationDAO designationDAO;
designationDAO=new DesignationDAO();
designationDAO.deleteByCode(code);
response.setContentType("text/html");

pw.println("<!Doctype html>");
pw.println("<html lang='en'>");
pw.println("<head>");
pw.println("<meta charset-'utf-8'>");
pw.println("<title>HR Application</title>");
pw.println("</head>");
pw.println("<body>");

pw.println("<div style='height:auto;width:90hw;border:1px solid black'>");

pw.println("<div style='height:auto;width:90hw;margin:5px;border:1px solid black'>");
pw.println("<img src='/styleone/images/logo.png' style='float:left'>");
pw.println("<div style='font-size:20pt;margin-top:5px;margin-bottom:5px'>Thinking machines");
pw.println("</div>");
pw.println("</div>");

pw.println("<div style='width:90hw;height:70vh;margin:5px;border:1px solid black'>");

pw.println("<div style='height:65vh;margin:5px;padding:5px;float:left;border:1px solid black'>");
pw.println("<a href='/styleone/designationsView'>Designations</a><br>");
pw.println("<a href='/styleone/employeeView'>Employee</a>");
pw.println("</div>");

pw.println("<div style='height:65vh;margin-top:5px;margin-left:105px;margin-right:5px;padding:5px;margin-bottom:px;border:1px solid black'>");
pw.println("<h3>Notification<h3>");
pw.println("Designation Deleted<br>");
pw.println("<form action='/styleone/designationsView'>");
pw.println("<button type='submit'>Ok</button>");
pw.println("</form>");
pw.println("</div>");
pw.println("</div>");

pw.println("<div style='height:auto;width:90hw;margin:5px;border:1px solid black;text-align:center'>&copy;Thinking Machines 2020");
pw.println("</div>");

pw.println("</div>");

pw.println("</body>");
pw.println("</html>");

}catch(DAOException daoException)
{
response.setContentType("text/html");
pw.println("<!Doctype html>");
pw.println("<html lang='en'>");
pw.println("<head>");
pw.println("<meta charset-'utf-8'>");
pw.println("<title>HR Application</title>");
pw.println("</head>");
pw.println("<body>");

pw.println("<div style='height:auto;width:90hw;border:1px solid black'>");

pw.println("<div style='height:auto;width:90hw;margin:5px;border:1px solid black'>");
pw.println("<img src='/styleone/images/logo.png' style='float:left'>");
pw.println("<div style='font-size:20pt;margin-top:5px;margin-bottom:5px'>Thinking machines");
pw.println("</div>");
pw.println("</div>");

pw.println("<div style='width:90hw;height:70vh;margin:5px;border:1px solid black'>");

pw.println("<div style='height:65vh;margin:5px;padding:5px;float:left;border:1px solid black'>");
pw.println("<a href='/styleone/designationsView'>Designations</a><br>");
pw.println("<a href='/styleone/employeeView'>Employee</a>");
pw.println("</div>");

pw.println("<div style='height:65vh;margin-top:5px;margin-left:105px;margin-right:5px;padding:5px;margin-bottom:px;border:1px solid black'>");
pw.println("<h3>Notification<h3>");
pw.println("Unable to delete Designation<br>");
pw.println("<b>"+daoException.getMessage()+"</b>");
pw.println("<form action='/styleone/designationsView'>");
pw.println("<button type='submit'>Ok</button>");
pw.println("</form>");
pw.println("</div>");
pw.println("</div>");

pw.println("<div style='height:auto;width:90hw;margin:5px;border:1px solid black;text-align:center'>&copy;Thinking Machines 2020");
pw.println("</div>");
pw.println("</div>");
pw.println("</body>");
pw.println("</html>");



}
catch(Exception exception)
{
System.out.println(exception);
}
}
public void doPost(HttpServletRequest request,HttpServletResponse response)
{
doGet(request,response);
}
private void sendBackView(HttpServletResponse response)
{
try{
DesignationDAO designationDAO;
designationDAO=new DesignationDAO();
List<DesignationDTO>designations;
designations=designationDAO.getAll();
PrintWriter pw=response.getWriter();
response.setContentType("text/html");

pw.println("<!Doctype html>");
pw.println("<html lang='en'>");
pw.println("<head>");
pw.println("<meta charset-'utf-8'>");
pw.println("<title>HR Application</title>");
pw.println("</head>");
pw.println("<body>");

pw.println("<div style='height:auto;width:90hw;border:1px solid black'>");

pw.println("<div style='height:auto;width:90hw;margin:5px;border:1px solid black'>");
pw.println("<img src='/styleone/images/logo.png' style='float:left'>");
pw.println("<div style='font-size:20pt;margin-top:5px;margin-bottom:5px'>Thinking machines");
pw.println("</div>");
pw.println("</div>");

pw.println("<div style='width:90hw;height:70vh;margin:5px;border:1px solid black'>");

pw.println("<div style='height:65vh;margin:5px;padding:5px;float:left;border:1px solid black'>");
pw.println("<b>Designations</b><br>");
pw.println("<a href='/styleone/EmployeeView'>Employee</a>");
pw.println("</div>");

pw.println("<div style='height:65vh;margin-top:5px;margin-left:105px;margin-right:5px;padding:5px;margin-bottom:px;border:1px solid black;overflow:scroll'>");
pw.println("<h3>Designations</h3>");
pw.println("<table border='1'>");
pw.println("<thead>");

pw.println("<tr>");
pw.println("<th colspan='4' style='text-align:right;padding:5px'>");
pw.println("<a href='/styleone/AddDesignation.html'>Add new Designation</a>");
pw.println("</th>");
pw.println("</tr>");

pw.println("<tr>");
pw.println("<th style='width:60px;text-align:center'>S.No.</th>");
pw.println("<th style='width:200px;text-align:center'>Designation</th>");
pw.println("<th style='width:100px;text-align:center'>Edit</th>");
pw.println("<th style='width:100px;text-align:center'>Delete</th>");
pw.println("</tr>");
pw.println("</thead>");
pw.println("<tbody>");
int x;
DesignationDTO designationDTO;
int sno=0;
int code;
String title;
for(x=0;x<designations.size();x++)
{
sno++;
designationDTO=designations.get(x);
code=designationDTO.getCode();
title=designationDTO.getTitle();
pw.println("<tr>");
pw.println("<td style='text-align:right'>"+sno+".</td>");
pw.println("<td>"+title+"</td>");
pw.println("<td style='text-align:center'><a href='/styleone/editDesignation?code="+code+"'>Edit</a></td>");
pw.println("<td style='text-align:center'><a href='/styleone/confirmDeleteDesignation?code="+code+"'>Delete</a></td>");
pw.println("</tr>");
}
pw.println("</tbody>");
pw.println("</table>");

pw.println("</div>");
pw.println("</div>");

pw.println("<div style='height:auto;width:90hw;margin:5px;border:1px solid black;text-align:center'>&copy;Thinking Machines 2020");
pw.println("</div>");

pw.println("</div>");

pw.println("</body>");
pw.println("</html>");

}catch(DAOException daoException)
{
System.out.println(daoException.getMessage());
}
catch(Exception exception)
{
System.out.println(exception.getMessage());
}

}
}
