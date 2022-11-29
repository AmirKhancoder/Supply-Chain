package com.example.suppychain;
import java.sql.*;

public class DatabaseConnection {

    String SQLURL ="jdbc:mysql://localhost:3306/supplyChain?useSSL=false ";
    String userName="root";
    String password="Kaifiya#567";
    Connection con=null;

    DatabaseConnection(){
        try {
            con = DriverManager.getConnection(SQLURL, userName, password);
            if(con!=null){
                System.out.println("Our Connection is Successfull");
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    public ResultSet executeQuery(String query){
        ResultSet res= null;
        try{
            Statement statement = con.createStatement();
            res=statement.executeQuery(query);
            return res;
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return res;
    }

    public int executeUpdate(String query){
        int res=0;
        try{
            Statement statement =con.createStatement();
            res= statement.executeUpdate(query);
            return res;
        }
        catch (Exception e){
            e.printStackTrace();
        }

        return res;


    }

}
