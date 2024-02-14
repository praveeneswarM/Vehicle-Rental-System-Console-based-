package com.rent;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import com.mysql.cj.jdbc.result.ResultSetMetaData;

public class Admin {
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
    static void showAdmin(String name){
        System.out.println("---Welcome Admin "+name+"!!---");
        System.out.println();
        adminmenu();
    }
    static void adminmenu(){
        int n;
        do{
        System.out.println("---Admin Menu---");
        System.out.println("Enter Choice:");
        System.out.println("1. Add Admin");
        System.out.println("2. Display Vehicles");
        System.out.println("3. Add Vehicle");
        System.out.println("4. Remove Vehicle");
        System.out.println("5. Exit");
        n=sc.nextInt();
        sc.nextLine();
        switch (n) {
            case 1:
            {
                AddAdmin();
                break;
            }
            case 2:
            {
                DisplayVehicles();
                break;
            }
            case 3:
            {
                AddVehicle();
                break;
            }
            case 4:
            {
                RemoveVehicle();
                break;
            }
            case 5:
            {
                System.out.println("---Exited Succesfully---");
                break;
            }
            default:{
                System.out.println("---Invalid Choice---");
            }

        }
    }while(n!=5);

}
    static void AddAdmin(){
        try{
            System.out.print("Name: ");
        String name = sc.nextLine();
        System.out.print("Email: ");
        String mail = sc.nextLine().trim().toLowerCase();
        if(!mail.contains("@gmail.com"))
        {
            System.out.println("Enter Valid Email");
            AddAdmin();
        }
        System.out.print("Password: ");
        String pass = sc.nextLine();
        String sql="INSERT INTO user(name,email,pass,role) VALUES(?,?,?,?)";
        PreparedStatement stmt= con.prepareStatement(sql);
        stmt.setString(1, name);
        stmt.setString(2, mail);
        stmt.setString(3, pass);
        stmt.setString(4, "admin");
        int res=stmt.executeUpdate();
        if(res>0)
        System.out.println("---Admin added Successfully---");
        else{
            System.out.println("---Try Again---");
            AddAdmin();
        }
    }catch(SQLException e)
        {
            e.printStackTrace();
        }
    }

    static void DisplayVehicles() {
        try {
            String sql = "SELECT * FROM disp";
            PreparedStatement stmt = con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            ResultSetMetaData rsmd = (ResultSetMetaData)rs.getMetaData();
            int col = rsmd.getColumnCount();
            for (int i = 1; i <= col; i++) {
                if (i > 1)
                    System.out.print("\t");
                System.out.print(rsmd.getColumnName(i));
            }
            System.out.println();
            while (rs.next()) {
                for (int i = 1; i <= col; i++) {
                    if (i > 1)
                        System.out.print("\t");
                    String val = rs.getString(i);
                    System.out.print(val);
                }
                System.out.println();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    

    static void AddVehicle(){
        try{
            System.out.print("Enter Model:");
            String model=sc.nextLine();
            System.out.print("Enter Type:");
            String type=sc.nextLine();
            System.out.print("Enter Color:");
            String color=sc.nextLine();
            System.out.print("Enter Vehicle Number:");
            String ve_no=sc.nextLine();
            System.out.print("Enter Seat:");
            int seat=sc.nextInt();
            System.out.print("Enter Deposit:");
            int deposit=sc.nextInt();
            System.out.print("Enter kilometer:");
            int k_meter=sc.nextInt();
            System.out.print("Enter rent:");
            int rent=sc.nextInt();
            String sql="INSERT INTO disp(model,type,color,deposit,ve_no,seat,k_meter,rent) VALUES(?,?,?,?,?,?,?,?)";
            PreparedStatement stmt=con.prepareStatement(sql);
            stmt.setString(1, model);
            stmt.setString(2, type);
            stmt.setString(3, color);
            stmt.setInt(4, deposit);
            stmt.setString(5, ve_no);
            stmt.setInt(6, seat);
            stmt.setInt(7, k_meter);
            stmt.setInt(8, rent);

            int rs=stmt.executeUpdate();
            if(rs>0)
            System.out.println("---Vehicle Added Successfully---");
            else
            {
                System.out.println("Try Again");
                AddVehicle();
            }
        }catch(SQLException e)
        {
            e.printStackTrace();
        }
    }

    static void RemoveVehicle()
    {
        try{
            System.out.println("Enter Vehicle Number:");
            String ve_no=sc.nextLine();
            String sql="DELETE FROM disp WHERE ve_no = ?";
            PreparedStatement stmt=con.prepareStatement(sql);
            stmt.setString(1, ve_no);
            int rs=stmt.executeUpdate();
            if(rs>0)
            {
                System.out.println("--- Vehicle Removed Successfully ---");
            }
            else{
                System.out.println("--- Try Again ---");
                RemoveVehicle();
            }
        }catch(SQLException e)
        {
            e.printStackTrace();
        }
    }
    public static void main(String[] args){
        
    }
}
