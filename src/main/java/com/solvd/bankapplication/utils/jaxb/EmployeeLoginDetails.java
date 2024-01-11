package com.solvd.bankapplication.utils.jaxb;

import com.solvd.bankapplication.domain.EmployeeLoginDetail;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

import java.util.List;

@XmlRootElement(name = "employee_login_details")
@XmlAccessorType(XmlAccessType.FIELD)
public class EmployeeLoginDetails {
    @XmlElement(name = "employee_login_detail")
    private List<EmployeeLoginDetail> employeeLoginDetails;

    public List<EmployeeLoginDetail> getEmployeeLoginDetails() {
        return employeeLoginDetails;
    }

    public void setEmployeeLoginDetails(List<EmployeeLoginDetail> employeeLoginDetails) {
        this.employeeLoginDetails = employeeLoginDetails;
    }
}