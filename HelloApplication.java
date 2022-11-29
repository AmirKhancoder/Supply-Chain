package com.example.suppychain;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

public class HelloApplication extends Application {
    public static DatabaseConnection connection;
    public static Group root;
    public static String emailId;

    @Override
    public void start(Stage stage) throws IOException, SQLException {
         connection = new DatabaseConnection();
         emailId="";
        root= new Group();
        Header header =new Header();
        productPage product =new productPage();
        ListView<HBox> productslist=product.showProducts();
        AnchorPane productPane=new AnchorPane();
        productPane.setLayoutX(150);
        productPane.setLayoutY(100);
        productPane.getChildren().add(productslist);
        root.getChildren().addAll(header.root,productPane);


//        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
//        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        stage.setScene(new Scene(root,500,500));
        stage.setTitle("supplyChain");
//        stage.setScene(scene);
        stage.show();
        stage.setOnCloseRequest(e ->{
            try {
                connection.con.close();
                System.out.println("Connection is closed");
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        });
    }

    public static void main(String[] args) {
        launch();
    }
}