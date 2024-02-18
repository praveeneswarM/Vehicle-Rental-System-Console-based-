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
        String mail = sc.nextLine();
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

    static void ret(String v)
    {
        try{
            String g = "SELECT deposit,rent,k_meter FROM disp WHERE id=?";
        PreparedStatement st = con.prepareStatement(g);
        st.setString(1, v);
        ResultSet rtt = st.executeQuery();
                    if (rtt.next()) {
                        String dep = rtt.getString("deposit");
                        String rent = rtt.getString("rent");
                        String km = rtt.getString("k_meter");
                        System.out.print("Enter Kilometer:");
                        String kilo = sc.nextLine();
                        System.out.println("Damage Chart:");
                        System.out.println("NO");
                        System.out.println("LOW");
                        System.out.println("MEDIUM");
                        System.out.println("HIGH");
                        System.out.print("Enter Damage Rate:");
                        String dam = sc.nextLine().toUpperCase();
                        int depo = 0;
                        if (dam.equals("LOW")) {
                            int de = Integer.valueOf(dep);
                            depo = de - (de / 100) * 20;
                        } else if (dam.equals("MEDIUM")) {
                            int de = Integer.valueOf(dep);
                            depo = de - (de / 100) * 50;
                        } else if (dam.equals("HIGH")) {
                            int de = Integer.valueOf(dep);
                            depo = de - (de / 100) * 75;
                        }
                        else if(dam.equals("NO"))
                        {
                            depo=Integer.valueOf(dep);
                        }
                        if (Integer.valueOf(kilo) - Integer.valueOf(km) >= 500) {
                            int r = Integer.valueOf(rent);
                            int ren = r + (r / 100) * 15;
                            System.out.println("Rent Should be payed: " + ren);
                            System.out.println("Your Deposit After Damage Fair is " + depo);
                        } else {
                            System.out.println("Rent Should be payed: " + rent);
                            System.out.println("Your Deposit After Damage Fair is " + depo);
                        }
                        String t = "UPDATE disp SET k_meter=? WHERE id=?";
                        PreparedStatement j = con.prepareStatement(t);
                        j.setString(1, kilo);
                        j.setString(2, v);
                        int rr = j.executeUpdate();
                        if (rr > 0)
                            System.out.println("--- Vehicle Returned Successfully ---");
                        else {
                            System.out.println("--- Try Again ---");
                            ret(v);
                        }
                    }
                    
                }catch (SQLException e) {
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
