package com.rent;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import com.mysql.cj.jdbc.result.ResultSetMetaData;

public class User {
    static Scanner sc=new Scanner(System.in);
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

    static void showUser(String name){
        System.out.println("---Welcome "+name+"!!---");
        System.out.println();
        UserMenu();
    }
    static void UserMenu()
    {
        int n;
        do{
            System.out.println("Enter Your Choice:");
            System.out.println("1. Display Vehicle");
            System.out.println("2. Rent Vehicle");
            System.out.println("3. Retuen Vehicle");
            System.out.println("4. Exit");
            System.out.println();
            n=sc.nextInt();
            switch(n)
            {
                case 1:{
                    Admin.DisplayVehicles();
                    break;
                }
                case 2:
                {
                    Rent();
                    break;
                }
                case 3:{
                    Return();
                    break;
                }
                case 4:
                {
                    System.out.println("---Exiting Successfullt---");
                    break;
                }
                default:{
                    System.out.println("---Invalid Option---");
                }
            }
        }while(n!=4);
    }


    static void Rent()
    {
        
    }

    static void Return()
    {

    }
    public static void main(String[] args){

    }
}
