package com.example.suppychain;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;


public class LoginPageController {

    @FXML
    TextField email;
    @FXML
    PasswordField password;
    @FXML
    public void login(MouseEvent event) throws SQLException, IOException {
        String query= String.format("select * from user where emailId='%s' and pass='%s' ",email.getText(),password.getText());
        ResultSet res=HelloApplication.connection.executeQuery(query);
        if(res.next()){
            String userType= res.getString("userType");
            HelloApplication.emailId=res.getString("emailId");
            if(userType.equals("buyer")){

                System.out.println("logged as buyer");
                productPage product=new productPage();
                Header header =new Header();
                ListView<HBox> productslist=product.showProducts();
                AnchorPane productPane=new AnchorPane();
                productPane.setLayoutX(150);
                productPane.setLayoutY(100);
                productPane.getChildren().add(productslist);
                HelloApplication.root.getChildren().clear();
                HelloApplication.root.getChildren().addAll(header.root,productPane);
            }
            else{

                System.out.println("logged as seller");
                AnchorPane sellerpage= FXMLLoader.load((getClass().getResource("SellerPage.fxml")));
                HelloApplication.root.getChildren().add(sellerpage);
            }

        }

        else{

            Dialog<String> dialog = new Dialog<>();
            dialog.setTitle("login");
            ButtonType type=new ButtonType("ok", ButtonBar.ButtonData.OK_DONE);
            dialog.setContentText("not logged in try again!!");
            dialog.getDialogPane().getButtonTypes().add(type);
            dialog.showAndWait();

        }
    }

}
