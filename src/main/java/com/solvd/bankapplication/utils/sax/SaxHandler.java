package com.solvd.bankapplication.utils.sax;

import com.solvd.bankapplication.domain.Employee;
import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;
import java.util.List;

public class SaxHandler extends DefaultHandler {
    private List<Employee> employeeList = new ArrayList<>();
    StringBuilder employeeData;
    private Employee employee;
    private boolean bEmployeeId = false;
    private boolean bBankId = false;
    private boolean bFirstName = false;
    private boolean bMiddleName = false;
    private boolean bLastName = false;
    private boolean bPosition = false;
    private boolean bBranchId = false;
    private boolean bEmail = false;

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) {
        employeeData = new StringBuilder();
        if (qName.equalsIgnoreCase("employee_id")) {
            employee = new Employee();
            employeeList.add(employee);
            bEmployeeId = true;
        } else if (qName.equalsIgnoreCase("bank_id")) {
            bBankId = true;
        } else if (qName.equalsIgnoreCase("first_name")) {
            bFirstName = true;
        } else if (qName.equalsIgnoreCase("middle_name")) {
            bMiddleName = true;
        } else if (qName.equalsIgnoreCase("last_name")) {
            bLastName = true;
        } else if (qName.equalsIgnoreCase("position")) {
            bPosition = true;
        } else if (qName.equalsIgnoreCase("branch_id")) {
            bBranchId = true;
        } else if (qName.equalsIgnoreCase("email")) {
            bEmail = true;
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) {
        if (bEmployeeId) {
            employee.setEmployeeID(Long.valueOf(employeeData.toString()));
            bEmployeeId = false;
        } else if (bBankId) {
            employee.setBankID(Long.valueOf(employeeData.toString()));
            bBankId = false;
        } else if (bFirstName) {
            employee.setFirstName(employeeData.toString());
            bFirstName = false;
        } else if (bMiddleName) {
            employee.setMiddleName(employeeData.toString());
            bMiddleName = false;
        } else if (bLastName) {
            employee.setLastName(employeeData.toString());
            bLastName = false;
        } else if (bPosition) {
            employee.setPosition(Long.valueOf(employeeData.toString()));
            bPosition = false;
        } else if (bBranchId) {
            employee.setBranchID(Long.valueOf(employeeData.toString()));
            bBranchId = false;
        } else if (bEmail) {
            employee.setEmail(employeeData.toString());
            bEmail = false;
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) {
        employeeData.append(new String(ch, start, length));
    }

    public List<Employee> getEmployees() {
        return employeeList;
    }
}
