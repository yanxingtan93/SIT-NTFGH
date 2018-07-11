package model;

import DatabaseConnector.DBConn;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class User {

    /*
    CREATE TABLE USERS (user_NRIC varchar(20) NOT NULL PRIMARY KEY, user_DOB varchar(20), user_password varchar(100), user_contact int(10),
 user_name varchar(100), user_email varchar(100), user_address varchar(200), user_special_condition varchar(500));
     */


    private String NRIC;
    private String name;
    private String password;
    private String dob;
    private int contact;
    private String email;
    private String address;
    private String specialCondition;
    private String role;

    public User() {
    }

    public User(String NRIC,String name, String password, String dob, int contact, String email, String address,String role){
        this.NRIC = NRIC;
        this.name = name;
        this.password = password;
        this.dob = dob;
        this.contact = contact;
        this.email = email;
        this.address = address;
        this.role = role;

    }

    public User(String NRIC,String name, String password, String dob, int contact, String email, String address,String role,String condition){
        this.NRIC = NRIC;
        this.name = name;
        this.password = password;
        this.dob = dob;
        this.contact = contact;
        this.email = email;
        this.address = address;
        this.role = role;
        this.specialCondition = condition;

    }

    public String getNRIC() {
        return NRIC;
    }

    public void setNRIC(String NRIC) {
        this.NRIC = NRIC;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public int getContact() {
        return contact;
    }

    public void setContact(int contact) {
        this.contact = contact;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getSpecialCondition() {
        return specialCondition;
    }

    public void setSpecialCondition(String specialCondition) {
        this.specialCondition = specialCondition;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public static void addFakeUsers(){
        DBConn db = new DBConn();

        String sql1 = "INSERT INTO USERS (user_NRIC,user_name,user_dob,user_contact,user_email,user_address,user_password) VALUES" +
                " ('S1231313X','Terry Brew','16/12/1993','92141011','roofietw@gmail.com','Teck Leo Road Blk 53C','abc123')";


        String sql = "INSERT INTO USERS (user_NRIC,user_name,user_dob,user_contact,user_email,user_address,user_password) VALUES" +
                " ('S1291313X','Johnny Lim','16/12/1994','92138011','roofie@gmail.com','Teck Leo Road Blk 59C','abc123')";


        String sql2 = "INSERT INTO USERS (user_NRIC,user_name,user_dob,user_contact,user_email,user_address,user_password) VALUES" +
                " ('S1291378X','Soma Lisa','16/12/1996','94198011','lostIn@gmail.com','Teck Leo Road Blk 99C','abc123')";



        try {
            Connection conn = db.getConnection();
            Statement stmt = conn.createStatement();
            stmt.executeUpdate(sql1);
            conn.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public static void addNewUser(String NRIC,String name, int contact, String email, String address,String dob,String password, String role){

        DBConn db = new DBConn();

        String sql = "INSERT INTO USERS (user_NRIC,user_name,user_dob,user_contact,user_email,user_address,user_password) VALUES" +
                " ('"+NRIC+"','"+name+"','"+dob+"','"+contact+"','"+email+"','"+address+"','"+password+"')";

        try {
            Connection conn = db.getConnection();
            Statement stmt = conn.createStatement();
            stmt.executeUpdate(sql);
            conn.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        String myRole = role.toLowerCase().trim();

        String sqlRole ="";
        switch(myRole){

            case "admin":
                sqlRole = "INSERT INTO ADMINS (user_NRIC) VALUES" +
                        " ('"+NRIC+"')";
                break;

            case "caregiver":
                sqlRole = "INSERT INTO CAREGIVERS (user_NRIC) VALUES" +
                        " ('"+NRIC+"')";
                break;

            case "patient":
                sqlRole = "INSERT INTO PATIENTS (user_NRIC) VALUES" +
                        " ('"+NRIC+"')";
                break;

            case "pharmacist":
                sqlRole = "INSERT INTO PHARMACISTS (user_NRIC) VALUES" +
                        " ('"+NRIC+"')";
                break;


        }


        try {
            Connection conn1 = db.getConnection();
            Statement stmt2 = conn1.createStatement();
            stmt2.executeUpdate(sqlRole);
            conn1.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }


}
