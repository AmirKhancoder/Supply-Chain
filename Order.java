package com.example.suppychain;

import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Order {
    void placeOrder(String productId) throws SQLException {
        ResultSet res=HelloApplication.connection.executeQuery("Select max(orderId) from orders");
        int orderId=0;
        if(res.next())
            orderId=res.getInt("max(orderId)")+1;

        String query=String.format("insert into orders values(%s,%s,'%s')",orderId, productId,HelloApplication.emailId);
        int response=HelloApplication.connection.executeUpdate(query);
        if(response>0){
            System.out.println("order is palced");
            Dialog<String> dialog = new Dialog<>();
            dialog.setTitle("order");
            ButtonType type=new ButtonType("ok", ButtonBar.ButtonData.OK_DONE);
            dialog.setContentText("your order placed !! Congratulation");
            dialog.getDialogPane().getButtonTypes().add(type);
            dialog.showAndWait();
        }
    }
}
