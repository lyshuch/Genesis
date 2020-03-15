package sample;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Employee {
    private SimpleIntegerProperty employeeNumber;
    private SimpleIntegerProperty departmentID;
    private SimpleStringProperty f_name;
    private SimpleStringProperty l_name;
    private SimpleDoubleProperty sales;
    private SimpleStringProperty password;
    private SimpleIntegerProperty override_number;
    private SimpleStringProperty genesisUsername;

    public String getGenesisUsername() {
        return genesisUsername.get();
    }

    public SimpleStringProperty genesisUsernameProperty() {
        return genesisUsername;
    }

    public void setGenesisUsername(String genesisUsername) {
        this.genesisUsername.set(genesisUsername);
    }

    public String getGenesisPassword() {
        return genesisPassword.get();
    }

    public SimpleStringProperty genesisPasswordProperty() {
        return genesisPassword;
    }

    public void setGenesisPassword(String genesisPassword) {
        this.genesisPassword.set(genesisPassword);
    }

    private SimpleStringProperty genesisPassword;

    public int getEmployeeNumber() {
        return employeeNumber.get();
    }

    public SimpleIntegerProperty employeeNumberProperty() {
        return employeeNumber;
    }

    public void setEmployeeNumber(int employeeNumber) {
        this.employeeNumber.set(employeeNumber);
    }

    public int getDepartmentID() {
        return departmentID.get();
    }

    public SimpleIntegerProperty departmentIDProperty() {
        return departmentID;
    }

    public void setDepartmentID(int departmentID) {
        this.departmentID.set(departmentID);
    }

    public String getF_name() {
        return f_name.get();
    }

    public SimpleStringProperty f_nameProperty() {
        return f_name;
    }

    public void setF_name(String f_name) {
        this.f_name.set(f_name);
    }

    public String getL_name() {
        return l_name.get();
    }

    public SimpleStringProperty l_nameProperty() {
        return l_name;
    }

    public void setL_name(String l_name) {
        this.l_name.set(l_name);
    }

    public double getSales() {
        return sales.get();
    }

    public SimpleDoubleProperty salesProperty() {
        return sales;
    }

    public void setSales(double sales) {
        this.sales.set(sales);
    }

    public String getPassword() {
        return password.get();
    }

    public SimpleStringProperty passwordProperty() {
        return password;
    }

    public void setPassword(String password) {
        this.password.set(password);
    }

    public int getOverride_number() {
        return override_number.get();
    }

    public SimpleIntegerProperty override_numberProperty() {
        return override_number;
    }

    public void setOverride_number(int override_number) {
        this.override_number.set(override_number);
    }

    public Employee() {
        this.employeeNumber = new SimpleIntegerProperty();
        this.departmentID = new SimpleIntegerProperty();
        this.f_name = new SimpleStringProperty();
        this.l_name = new SimpleStringProperty();
        this.sales = new SimpleDoubleProperty();
        this.password = new SimpleStringProperty();
        this.override_number = new SimpleIntegerProperty();
        this.genesisPassword = new SimpleStringProperty();
        this.genesisUsername = new SimpleStringProperty();
    }
}
