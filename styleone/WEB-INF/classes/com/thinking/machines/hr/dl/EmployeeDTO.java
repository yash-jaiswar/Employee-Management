package com.thinking.machines.hr.dl;
import java.util.*;
import java.math.*;
public class EmployeeDTO implements java.io.Serializable,Comparable<EmployeeDTO>
{
private String employeeId;
private int designationCode;
private String name;
private Date dateOfBirth;
private String gender;
private boolean isIndian;
private BigDecimal basicSalary;
private String panNumber,aadharCardNumber;
private String designation;
public EmployeeDTO()
{
this.employeeId="";
this.designation="";
this.name="";
this.designationCode=0;
//this.title="";
this.dateOfBirth=null;
this.gender="";
this.isIndian=false;
this.basicSalary=null;
this.panNumber="";
this.aadharCardNumber="";
}
public void setEmployeeId(String id)
{
this.employeeId=id;
}

public String getEmployeeId()
{
return this.employeeId;
} 

public void setName(String name)
{
this.name=name;
}
public String getName()
{
return this.name;
}
public void setDesignation(String designation)
{
this.designation=designation;
}
public String getDesignation()
{
return this.designation;
}
public void setDesignationCode(int code)
{
this.designationCode=code;
}

public int getDesignationCode()
{
return this.designationCode;
} 
/*public void setTitle(String title)
{
this.title=title;
}

public String getTitle()
{
return this.title;
}*/ 
public void setDateOfBirth(Date dateOfBirth)
{
this.dateOfBirth=dateOfBirth;
}

public Date getDateOfBirth()
{
return this.dateOfBirth;
} 
public void setGender(String gender)
{
this.gender=gender;
}

public String getGender()
{
return this.gender;
} 
public void setIsIndian(boolean isIndian)
{
this.isIndian=isIndian;
}

public boolean getIsIndian()
{
return this.isIndian;
} 
public void setBasicSalary(BigDecimal basicSalary)
{
this.basicSalary=basicSalary;
}

public BigDecimal getBasicSalary()
{
return this.basicSalary;
} 
public void setPanNumber(String panNumber)
{
this.panNumber=panNumber;
}

public String getPanNumber()
{
return this.panNumber;
} 
public void setAadharCardNumber(String aadharCardNumber)
{
this.aadharCardNumber=aadharCardNumber;
}
public String getAadharCardNumber()
{
return this.aadharCardNumber;
}
public int hashCode()
{
return employeeId.hashCode();
}
public boolean equals(Object object)
{
if(!(object instanceof EmployeeDTO)) return false;
EmployeeDTO employee=(EmployeeDTO)object;
return this.employeeId.equalsIgnoreCase(employee.employeeId);
}
public int compareTo(EmployeeDTO employee)
{
return this.employeeId.compareTo(employee.employeeId);
}
}

