package com.thinking.machines.hr.servlets;
import com.thinking.machines.hr.dl.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.util.*;
import java.sql.*;
import java.text.*;
public class DeleteEmployee extends HttpServlet
{
public void doGet(HttpServletRequest request,HttpServletResponse response)
{
PrintWriter pw=null;
String employeeId=request.getParameter("employeeId");

try{
pw=response.getWriter();
EmployeeDAO employeeDAO;
employeeDAO=new EmployeeDAO();
employeeDAO.deleteByEmployeeId(employeeId);
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
pw.println("Employee Deleted<br>");
pw.println("<form action='/styleone/employeesView'>");
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
pw.println("Unable to delete Employee<br>");
pw.println("<b>"+daoException.getMessage()+"</b>");
pw.println("<form action='/styleone/employeesView'>");
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
List<EmployeeDTO> employees=new EmployeeDAO().getAll();
PrintWriter pw=response.getWriter();
response.setContentType("text/html");
pw.println("<!Doctype html>");
pw.println("<html lang='en'>");
pw.println("<head>");
pw.println("<meta charset-'utf-8'>");
pw.println("<title>HR Application</title>");
pw.println("<script>");
pw.println("function Employee()");
pw.println("{");
pw.println("this.employeeId=\"\";");
pw.println("this.designationCode=0;");
pw.println("this.name=\"\";");
pw.println("this.aadharCardNumber=\"\";");
pw.println("this.panNumber=\"\";");
pw.println("this.basicSalary=0;");
pw.println("this.dateOfBirth=\"\";");
pw.println("this.isIndian=true;");
pw.println("this.gender=\"\";");
pw.println("}");
pw.println("var selectedRow=null;");
pw.println("var employees=[];");
SimpleDateFormat simpleDateFormat;
simpleDateFormat=new SimpleDateFormat("dd/MM/yyyy");
int i=0;
for(EmployeeDTO employee:employees)
{

pw.println("var employee=new Employee();");
pw.println("");
pw.println("employee.employeeId=\""+employee.getEmployeeId()+"\";");
pw.println("employee.designationCode="+employee.getDesignationCode()+";");
pw.println("employee.name='"+employee.getName()+"';");
pw.println("employee.designation=\""+employee.getDesignation()+"\";");
pw.println("employee.aadharCardNumber=\""+employee.getAadharCardNumber()+"\";");
pw.println("employee.panNumber=\""+employee.getPanNumber()+"\";");
pw.println("employee.gender=\""+employee.getGender()+"\";");
pw.println("employee.dateOfBirth=\""+simpleDateFormat.format(employee.getDateOfBirth())+"\";");
pw.println("employee.basicSalary="+employee.getBasicSalary().toPlainString()+";");
pw.println("employee.isIndian="+employee.getIsIndian()+";");
pw.println("employees["+i+"]=employee;");
i++;
}

pw.println("function selectEmployee(row,employeeId)");
pw.println("{");
pw.println("if(row==selectedRow)return;");
pw.println("if(selectedRow!=null)");
pw.println("{");
pw.println("selectedRow.style.background=\"white\";");
pw.println("selectedRow.style.color=\"black\";");
pw.println("}");
pw.println("row.style.background=\"#7c7B7B\";");
pw.println("row.style.color=\"white\";");
pw.println("selectedRow=row;");
pw.println("var i;");
pw.println("for(i=0;i<employees.length;i++)");
pw.println("{");
pw.println("if(employees[i].employeeId==employeeId)");
pw.println("{");
pw.println("break;");
pw.println("}");
pw.println("}");
pw.println("var emp=employees[i];");
pw.println("document.getElementById(\"detailPanel_employeeId\").innerHTML=emp.employeeId;");
pw.println("document.getElementById(\"detailPanel_name\").innerHTML=emp.name;");
pw.println("document.getElementById(\"detailPanel_designation\").innerHTML=emp.designation;");
pw.println("document.getElementById(\"detailPanel_dateOfBirth\").innerHTML=emp.dateOfBirth;");
pw.println("document.getElementById(\"detailPanel_gender\").innerHTML=emp.gender;");
pw.println("if(emp.isIndian)");
pw.println("{");
pw.println("document.getElementById(\"detailPanel_isIndian\").innerHTML='Yes';");
pw.println("}");
pw.println("else");
pw.println("{");
pw.println("document.getElementById(\"detailPanel_isIndian\").innerHTML='No';");
pw.println("}");
pw.println("document.getElementById(\"detailPanel_basicSalary\").innerHTML=emp.basicSalary;");
pw.println("document.getElementById(\"detailPanel_panNumber\").innerHTML=emp.panNumber;");
pw.println("document.getElementById(\"detailPanel_aadharCardNumber\").innerHTML=emp.aadharCardNumber;");
pw.println("}");
pw.println("</script>");
pw.println("</head>");
pw.println("<body>");
pw.println("");
pw.println("<div style='height:auto;width:90hw;border:1px solid black'>");
pw.println("");
pw.println("<div style='height:auto;width:90hw;margin:5px;border:1px solid black'>");
pw.println("<img src='/styleone/images/logo.png' style='float:left'>");
pw.println("<div style='font-size:20pt;margin-top:5px;margin-bottom:5px'>Thinking machines");
pw.println("</div>");
pw.println("</div>");
pw.println("");
pw.println("<div style='width:90hw;height:70vh;margin:5px;border:1px solid black'>");
pw.println("");
pw.println("<div style='height:65vh;margin:5px;padding:5px;float:left;border:1px solid black'>");
pw.println("<a href='/styleone/designationsView'>Designations</a><br>");
pw.println("<b>Employee</b><br><br>");
pw.println("<a href='/styleone/index.html'>Home</a>");
pw.println("");
pw.println("</div>");
pw.println("");
pw.println("<div style='height:65vh;margin-top:5px;margin-left:105px;margin-right:5px;padding:5px;margin-bottom:px;border:1px solid black'>");
pw.println("<h3>Employees</h3>");
pw.println("<div style='height:28vh;padding:5px;width:90hw;margin-left:5px;margin-right:5px;border:1px solid black;overflow:scroll'>");
pw.println("");
pw.println("<table border='1'>");
pw.println("<thead>");
pw.println("<tr>");
pw.println("<th colspan='6' style='text-align:right;padding:5px'>");
pw.println("<a href='/styleone/getEmployeeAddForm'>Add Employee</a>");
pw.println("</th>");
pw.println("</tr>");
pw.println("<tr>");
pw.println("<th style='text-align:center;width:60px'>S.No.</th>");
pw.println("<th style='text-align:center;width:100px'>Id.</th>");
pw.println("<th style='text-align:center;width:200px'>Name</th>");
pw.println("<th style='text-align:center;width:150px'>Designation</th>");
pw.println("<th style='text-align:center;width:80px'>Edit</th>");
pw.println("<th style='text-align:center;width:80px'>Delete</th>");
pw.println("</tr>");
pw.println("</thead>");
pw.println("<tbody>");
int j=1;
for(EmployeeDTO employee:employees)
{
pw.println("<tr style='cursor:pointer' onclick='selectEmployee(this,\""+employee.getEmployeeId()+"\")'>");
pw.println("<td style='text-align:right'>"+j+"</td>");
pw.println("<td style='text-align:center'>"+employee.getEmployeeId()+"</td>");
pw.println("<td style='text-align:center'>"+employee.getName()+"</td>");
pw.println("<td style='text-align:center'>"+employee.getDesignation()+"</td>");
pw.println("<td style='text-align:center'><a href='/styleone/editEmployee?employeeId="+employee.getEmployeeId()+"'>Edit</a></td>");
pw.println("<td style='text-align:center'><a href='/styleone/confirmDeleteEmployee?employeeId="+employee.getEmployeeId()+"'>Delete</a></td>");
pw.println("</tr>");
j++;
}
pw.println("</tbody>");
pw.println("</table>");
pw.println("");
pw.println("</div>");
pw.println("<div style='height:19vh;width:90hw;margin-left:5px;margin-right:5px;margin-top:5px;margin-bottom:px;padding:5px;border:1px solid black'>");
pw.println("<label style='margin-left:5px;background:grey;color:white'>Details-</label>");
pw.println("<table width='100%'>");
pw.println("<tbody>");
pw.println("<tr>");
pw.println("<td>Employee Id :<span id='detailPanel_employeeId'></span> </td>");
pw.println("<td>Name :<span id='detailPanel_name'></span></td>");
pw.println("<td>Designation :<span id='detailPanel_designation'></span> </td>");
pw.println("</tr>");
pw.println("<tr>");
pw.println("<td>Date of Birth :<span id='detailPanel_dateOfBirth'></span> </td>");
pw.println("<td>Gender :<span id='detailPanel_gender'></span> </td>");
pw.println("<td>Is indian :<span id='detailPanel_isIndian'></span> </td>");
pw.println("</tr>");
pw.println("<tr>");
pw.println("<td>Basic Salary :<span id='detailPanel_basicSalary'></span> </td>");
pw.println("<td>PAN Number : <span id='detailPanel_panNumber'></span></td>");
pw.println("<td>Aadhar Card Number :<span id='detailPanel_aadharCardNumber'></span> </td>");
pw.println("</tr>");
pw.println("<tbody>");
pw.println("</table>");
pw.println("</div>");
pw.println("");
pw.println("");
pw.println("</div>");
pw.println("</div>");
pw.println("");
pw.println("<div style='height:auto;width:90hw;margin:5px;border:1px solid black;text-align:center'>&copy;Thinking Machines 2020");
pw.println("</div>");
pw.println("");
pw.println("</div>");
pw.println("");
pw.println("</body>");
pw.println("</html>");


}catch(Exception e)
{
System.out.println(e);
}

}
}
