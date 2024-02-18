package com.rent;
import java.util.*;
public class Main{
    public static void main(String atrgs[])
    {
        Scanner sc=new Scanner(System.in);
        System.out.println("Enter your choice: ");
        System.out.println("1. SignIn");
        System.out.println("2. SignUp");
        int n=sc.nextInt();
        switch(n)
        {
            case 1:
            {
                Auth.SignIn();
                break;
            }
            case 2:
            {
                Auth.SignUp();
                break; 
            }
        }
    }
    
}
