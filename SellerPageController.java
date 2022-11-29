package com.example.suppychain;

import javafx.fxml.FXML;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.security.DigestOutputStream;
import java.sql.ResultSet;
import java.sql.SQLException;


public class SellerPageController {
    @FXML
    TextField name;
    @FXML
    TextField price;
    @FXML
    TextField email;
    @FXML
    public void Add(MouseEvent event) throws SQLException {

        ResultSet res=HelloApplication.connection.executeQuery("select max(productId) from product");
        int productId=0;
        if(res.next()){
            productId=res.getInt("max(productId)")+1;
        }
        String query=String.format("Insert into product values(%s , '%s' , %s , '%s')",productId, name.getText(), price.getText(), email.getText());
        int response= HelloApplication.connection.executeUpdate(query);
        Dialog<String> dialog = new Dialog<>();
        dialog.setTitle("Product Add");
        ButtonType type=new ButtonType("ok", ButtonBar.ButtonData.OK_DONE);
        if(response>0){

            dialog.setContentText("A new product is added");

        }
        else{

            dialog.setContentText("the product is not added");

        }
        dialog.getDialogPane().getButtonTypes().add(type);
        dialog.showAndWait();
    }
}
