package com.rent;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Auth {
    static Scanner sc = new Scanner(System.in);
    static DB db = new DB();
    static Connection con;

    static {
        try {
            con = db.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    public static void SignIn() {
        try{
        
        System.out.print("Email: ");
        String email = sc.nextLine();
        System.out.print("Password: ");
        String pass = sc.nextLine();
        // System.out.println(email + " welcome!!");
        String sql = "SELECT * FROM user where email = ? and pass= ?"; //select * from uder where email = "prabveen "and pass ="12345" --> 
        PreparedStatement stmt = con.prepareStatement(sql);
        stmt.setString(1,email);
        stmt.setString(2,pass);
        ResultSet rs=stmt.executeQuery();
        if(rs.next()){
            String role = rs.getString("role");
            String name = rs.getString("name");
            System.out.println();
            if(role.equals("admin")){
                Admin.showAdmin(name);
            }else{
                User.showUser(name);
            }
        }else{
            System.out.println();
            System.out.println("----Please enter correct credentials----");
            System.out.println("----Or else SigUp as new User----");
            System.out.println();
            Main.main(null);
        }
        // Admin.main();
    }catch(SQLException e){
        e.printStackTrace();
    }
}

    public static void SignUp() {
        try{
        System.out.print("Name: ");
        String name = sc.nextLine();
        System.out.print("Email: ");
        String mail = sc.nextLine();
        System.out.print("Password: ");
        String pass = sc.nextLine();
        String sql="INSERT INTO user(name,email,pass) VALUES(?,?,?)";
        PreparedStatement stmt= con.prepareStatement(sql);
        stmt.setString(1,name);
        stmt.setString(2,mail);
        stmt.setString(3,pass);
        int res=stmt.executeUpdate();  
        if(res>0)
        {
            System.out.println();
            System.out.println("---User added Succesfully---");
            System.out.println();
            SignIn();
        }
        else{
            System.out.println();
            System.out.println("Try again!!!");
            System.out.println();
            SignUp();
        }
    }catch(SQLException e){
        e.printStackTrace();
    }
}

    public static void main(String args[]) {
        // Your main method code goes here
    }
}
