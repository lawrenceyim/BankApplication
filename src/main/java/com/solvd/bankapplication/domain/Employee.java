package com.solvd.bankapplication.domain;

public class Employee {
    private long employeeID;
    private long bankID;
    private String firstName;
    private String middleName;
    private String lastName;
    private long position;
    private long branchID;
    private String email;

    public long getEmployeeID() {
        return employeeID;
    }

    public void setEmployeeID(long employeeID) {
        this.employeeID = employeeID;
    }

    public long getBankID() {
        return bankID;
    }

    public void setBankID(long bankID) {
        this.bankID = bankID;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public long getPosition() {
        return position;
    }

    public void setPosition(long position) {
        this.position = position;
    }

    public long getBranchID() {
        return branchID;
    }

    public void setBranchID(long branchID) {
        this.branchID = branchID;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Employee ID: ").append(employeeID).append(System.lineSeparator());
        sb.append("Bank ID: ").append(bankID).append(System.lineSeparator());
        sb.append("First Name: ").append(firstName).append(System.lineSeparator());
        sb.append("Middle Name: ").append(middleName).append(System.lineSeparator());
        sb.append("Last Name: ").append(lastName).append(System.lineSeparator());
        sb.append("Position ID: ").append(position).append(System.lineSeparator());
        sb.append("Branch ID: ").append(branchID).append(System.lineSeparator());
        sb.append("Email: ").append(email).append(System.lineSeparator());
        return sb.toString();
    }
}
