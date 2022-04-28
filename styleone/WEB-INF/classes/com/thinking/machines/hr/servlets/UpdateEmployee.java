package com.thinking.machines.hr.servlets;
import java.util.*;
import java.math.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.text.*;
import com.thinking.machines.hr.dl.*;
public class UpdateEmployee extends HttpServlet
{
public void doGet(HttpServletRequest request,HttpServletResponse response)
{
try{
PrintWriter pw=response.getWriter();
response.setContentType("text/html");
SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd");
String employeeId=request.getParameter("employeeId");
String name=request.getParameter("name");
int designationCode=Integer.parseInt(request.getParameter("designationCode"));
Date dateOfBirth=simpleDateFormat.parse(request.getParameter("dateOfBirth"));
String gender=request.getParameter("gender");
String isIndian=request.getParameter("isIndian");
if(isIndian==null) isIndian="N";
BigDecimal basicSalary=new BigDecimal(request.getParameter("basicSalary"));
String panNumber=request.getParameter("panNumber");
String aadharCardNumber=request.getParameter("aadharCardNumber");
DesignationDAO designationDAO=new DesignationDAO();
EmployeeDAO employeeDAO=new EmployeeDAO();
try{
if(employeeDAO.employeeIdExists(employeeId)==false);
{
sendBackView(response);
return;
}
boolean designationCodeExists=designationDAO.designationCodeExists(designationCode);
boolean panNumberExists=false;
EmployeeDTO employeeDTO;

try{
employeeDTO=employeeDAO.getByPanNumber(panNumber);
if(employeeDTO.getEmployeeId().equalsIgnoreCase(employeeId)==false)
{
panNumberExists=true;
}
}catch(DAOException daoException)
{
panNumberExists=false;
}

boolean aadharCardNumberExists=false;
try{
employeeDTO=employeeDAO.getByAadharCardNumber(aadharCardNumber);
if(employeeDTO.getEmployeeId().equalsIgnoreCase(employeeId)==false)
{
aadharCardNumberExists=true;
}
}catch(DAOException daoException)
{
aadharCardNumberExists=false;
}

if(designationCodeExists==false||panNumberExists==true||aadharCardNumberExists==true)
{
pw.println("<!Doctype html>");
pw.println("<html lang='en'>");
pw.println("<head>");
pw.println("<meta charset-'utf-8'>");
pw.println("<title>HR Application</title>");
pw.println("<script>");
pw.println("function validateForm(frm)");
pw.println("{");
pw.println("var valid=true;");
pw.println("var firstInvalidComponent=null;");
pw.println("var name=frm.name.value.trim();");
pw.println("var nameErrorSection=document.getElementById('nameErrorSection');");
pw.println("nameErrorSection.innerHTML='';");
pw.println("if(name.length==0)");
pw.println("{");
pw.println("nameErrorSection.innerHTML='Name Required';");
pw.println("valid=false;");
pw.println("firstInvalidComponent=frm.name;");
pw.println("}");
pw.println("");
pw.println("var designationCode=frm.designationCode.value.trim();");
pw.println("var designmationCodeErrorSection=document.getElementById('designationCodeErrorSection');");
pw.println("designationCodeErrorSection.innerHTML='';");
pw.println("if(designationCode==-1)");
pw.println("{");
pw.println("designationCodeErrorSection.innerHTML='Select designation code';");
pw.println("valid=false;");
pw.println("if(firstInvalidComponent==null)firstInvalidComponent=frm.designationCode;");
pw.println("}");
pw.println("");
pw.println("var dateOfBirth=frm.dateOfBirth.value;");
pw.println("var dateOfBirthErrorSection=document.getElementById('dateOfBirthErrorSection');");
pw.println("dateOfBirthErrorSection.innerHTML='';");
pw.println("if(dateOfBirth.length==0)");
pw.println("{");
pw.println("dateOfBirthErrorSection.innerHTML='Select Date Of Birth';");
pw.println("valid=false;");
pw.println("if(firstInvalidComponent==null)firstInvalidComponent=frm.dateOfBirth;");
pw.println("}");
pw.println("");
pw.println("");
pw.println("");
pw.println("var genderErrorSection=document.getElementById('genderErrorSection');");
pw.println("genderErrorSection.innerHTML='';");
pw.println("if(frm.gender[0].checked==false && frm.gender[1].checked==false)");
pw.println("{");
pw.println("genderErrorSection.innerHTML='Select gender';");
pw.println("valid=false;");
pw.println("}");
pw.println("");
pw.println("var basicSalary=frm.basicSalary.value;");
pw.println("var basicSalaryErrorSection=document.getElementById('basicSalaryErrorSection');");
pw.println("basicSalaryErrorSection.innerHTML='';");
pw.println("if(basicSalary.length==0)");
pw.println("{");
pw.println("basicSalaryErrorSection.innerHTML='Basic Salary required';");
pw.println("valid=false;");
pw.println("if(firstInvalidComponent==null)firstInvalidComponent=frm.basicSalary;");
pw.println("}");
pw.println("else");
pw.println("{");
pw.println("var v='0123456789.';");
pw.println("var e=0;");
pw.println("var isBasicSalaryValid=true;");
pw.println("while(e<basicSalary.length)");
pw.println("{");
pw.println("if(v.indexOf(basicSalary.charAt(e))==-1)");
pw.println("{");
pw.println("basicSalaryErrorSection.innerHTML='Invalid Basic Salary';");
pw.println("valid=false;");
pw.println("if(firstInvalidComponent==null)firstInvalidComponent=frm.basicSalary;");
pw.println("isBasicSalaryValid=false;");
pw.println("break;");
pw.println("}");
pw.println("e++;");
pw.println("}");
pw.println("if(isBasicSalaryValid)");
pw.println("{");
pw.println("var dot=basicSalary.indexOf('.');");
pw.println("if(dot!=-1)");
pw.println("{");
pw.println("var numberOfFractions=basicSalary.length-(dot+1);");
pw.println("if(numberOfFractions>2)");
pw.println("{");
pw.println("basicSalaryErrorSection.innerHTML='Invalid Basic Salary';");
pw.println("valid=false;");
pw.println("if(firstInvalidComponent==null)firstInvalidComponent=frm.basicSalary;");
pw.println("}");
pw.println("}");
pw.println("}");
pw.println("}");
pw.println("var panNumber=frm.panNumber.value.trim();");
pw.println("var panNumberErrorSection=document.getElementById('panNumberErrorSection');");
pw.println("panNumberErrorSection.innerHTML='';");
pw.println("if(panNumber.length==0)");
pw.println("{");
pw.println("panNumberErrorSection.innerHTML='PAN Number Required';");
pw.println("valid=false;");
pw.println("if(firstInvalidComponent==null)firstInvalidComponent=frm.panNumber;");
pw.println("}");
pw.println("");
pw.println("var aadharCardNumber=frm.aadharCardNumber.value.trim();");
pw.println("var aadharCardNumberErrorSection=document.getElementById('aadharCardNumberErrorSection');");
pw.println("aadharCardNumberErrorSection.innerHTML='';");
pw.println("if(aadharCardNumber.length==0)");
pw.println("{");
pw.println("aadharCardNumberErrorSection.innerHTML='AAdhar Card Number Required';");
pw.println("valid=false;");
pw.println("if(firstInvalidComponent==null)firstInvalidComponent=frm.aadharCardNumber;");
pw.println("}");
pw.println("if(!valid) firstInvalidComponent.focus();");
pw.println("return valid;");
pw.println("}");
pw.println("function cancelUpdate()");
pw.println("{");
pw.println("document.getElementById('cancelUpdateForm').submit();");
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
pw.println("</div>");
pw.println("");
pw.println("<div style='height:65vh;margin-top:5px;margin-left:105px;margin-right:5px;padding:5px;margin-bottom:px;border:1px solid black'>");
pw.println("<b>Employee(Edit module)</b>");
pw.println("<form method='post' action='/styleone/updateEmployee' onsubmit='return validateForm(this)'>");
pw.println("<input type='hidden' id='employeeId' name='employeeId' value='"+employeeId+"'>");
pw.println("<table>");
pw.println("<tr>");
pw.println("<td>Name</td>");
pw.println("<td><input type='text' name='name' id='name' maxlength='50' size='51' value='"+name+"'>");
pw.println("<span id='nameErrorSection' style='color:red'></span></td>");
pw.println("</tr>");
pw.println("<tr>");
pw.println("<td>Designation</td>");
pw.println("<td><select id='designationCode' name='designationCode'>");
pw.println("<option value='-1'>&lt;Select Designation&gt;</option>");
List<DesignationDTO>designations=designationDAO.getAll();
int code;
String title;
for(DesignationDTO designation:designations)
{
code=designation.getCode();
title=designation.getTitle();
if(code!=designationCode)
{
pw.println("<option value='"+code+"'>"+title+"</option>");
}
else
{
pw.println("<option selected value='"+code+"'>"+title+"</option>");
}
}
//ujuuuu
pw.println("</select>");
if(designationCodeExists==false)
{
pw.println("<span id='designationCodeErrorSection' style='color:red'>Invalid designation</span>");
}
else
{
pw.println("<span id='designationCodeErrorSection' style='color:red'></span>");

}
pw.println("</td>");
pw.println("</tr>");
pw.println("<tr>");
pw.println("<td>Date of birth</td>");
pw.println("<td><input type='date' id='dateOfBirth' name='dateOfBirth' value='"+simpleDateFormat.format(dateOfBirth)+"'>");
pw.println("<span id='dateOfBirthErrorSection' style='color:red'></span></td>");
pw.println("</tr>");
pw.println("<tr>");
pw.println("<td>Gender</td>");

pw.println("<td>");
if(gender.equals("M")==false)
{
pw.println("<input type='radio' id='male' name='gender' value='M'>Male");
}
else
{
pw.println("<input checked type='radio' id='male' name='gender' value='M'>Male");
}
pw.println("&nbsp;&nbsp;&nbsp;&nbsp;");
if(gender.equals("F")==false)
{
pw.println("<input type='radio' id='female' name='gender' value='F'>Female");
}
else
{
pw.println("<input checked type='radio' id='female' name='gender' value='F'>Female");
}
pw.println("<span id='genderErrorSection' style='color:red'></span></td>");
pw.println("</td>");
pw.println("</tr>");
pw.println("<tr>");
pw.println("<td>Indian ?</td>");
pw.println("<td>");
if(isIndian.equals("Y"))
{
pw.println("<input checked type='checkbox' name='isIndian' id='isIndian' value='Y'>");
}
else
{
pw.println("<input type='checkbox' name='isIndian' id='isIndian' value='Y'>");
}
pw.println("</td>");
pw.println("</tr>");
pw.println("<tr>");
pw.println("<td>Basic Salary</td>");
pw.println("<td>");
pw.println("<input type='text' style='text-align:right' name='basicSalary' id='basicSalary' maxlength='12' size='13' value='"+basicSalary.toPlainString()+"'>");
pw.println("<span id='basicSalaryErrorSection' style='color:red'></span></td>");
pw.println("</td>");
pw.println("</tr>");
pw.println("<tr>");
pw.println("<td>PAN Number</td>");
pw.println("<td>");

pw.println("<input type='text' id='panNumber' name='panNumber' maxlength='15' size='16' value='"+panNumber+"'>");

if(panNumberExists)
{
pw.println("<span id='panNumberErrorSection' style='color:red'>PAN Number exists</span>");
}
else
{
pw.println("<span id='panNumberErrorSection' style='color:red'></span>");
}
pw.println("</td>");
pw.println("</tr>");
pw.println("<tr>");
pw.println("<td>Aadhar Card Number</td>");
pw.println("<td>");
pw.println("<input type='text' id='aadharCardNumber' name='aadharCardNumber' maxlength='15' size='16' value='"+aadharCardNumber+"'>");
if(aadharCardNumberExists)
{
pw.println("<span id='aadharCardNumberErrorSection' style='color:red'>Aadhar Card number exists</span>");
}
else
{
pw.println("<span id='aadharCardNumberErrorSection' style='color:red'></span>");
}
pw.println("</td>");
pw.println("</tr>");
pw.println("");
pw.println("<tr>");
pw.println("<td colspan='2'><button type='submit'>Update</button>");
pw.println("&nbsp;&nbsp;<button type='Button' onclick='cancelUpdate())'>Cancel</button>");
pw.println("</td>");
pw.println("</table>");
pw.println("</form>");
pw.println("</div>");
pw.println("</div>");
pw.println("");
pw.println("<div style='height:auto;width:90hw;margin:5px;border:1px solid black;text-align:center'>&copy;Thinking Machines 2020");
pw.println("</div>");
pw.println("");
pw.println("</div>");
pw.println("<form id='cancelUpdateForm' action='/styleone/employeesView'>");
pw.println("</form>");
pw.println("</body>");
pw.println("</html>");
return;
}
}catch(DAOException daoException)
{
System.out.println(daoException);
}
EmployeeDTO employee=new EmployeeDTO();
employee.setEmployeeId(employeeId);
employee.setName(name);
employee.setDesignationCode(designationCode);
employee.setDateOfBirth(dateOfBirth);
employee.setGender(gender);
employee.setIsIndian(isIndian.equals("Y"));
employee.setGender(gender);
employee.setBasicSalary(basicSalary);
employee.setPanNumber(panNumber);
employee.setAadharCardNumber(aadharCardNumber);

try{
employeeDAO.update(employee);
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
pw.println("<h3>Notification</h3>");
pw.println("Employee updated<br>");
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
//recreate form with error message at top of form
System.out.println(daoException);
}


}catch(Exception exception)
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