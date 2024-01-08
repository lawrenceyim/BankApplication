package com.solvd.bankapplication.domain;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "employee_login_detail")
@XmlAccessorType(XmlAccessType.FIELD)
public class EmployeeLoginDetail {
    @XmlElement(name = "employee_id")
    private long employeeID;
    @XmlElement
    private String username;
    @XmlElement
    private String password;

    public long getEmployeeID() {
        return employeeID;
    }

    public void setEmployeeID(long employeeID) {
        this.employeeID = employeeID;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Employee ID: ").append(employeeID).append(System.lineSeparator());
        sb.append("Username: ").append(username).append(System.lineSeparator());
        return sb.toString();
    }
}
