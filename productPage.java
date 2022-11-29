package com.example.suppychain;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;

import java.sql.ResultSet;
import java.sql.SQLException;

public class productPage {
    ListView<HBox> products;

    ListView<HBox> showProductsbysearch(String search) throws SQLException {
        ObservableList<HBox> productlist= FXCollections.observableArrayList();
        ResultSet res= HelloApplication.connection.executeQuery("select * from product");
        products=new ListView<>();

        Label name=new Label();
        Label price=new Label();
        Label id=new Label();

        HBox details= new HBox();
        name.setMinWidth(100);
        price.setMinWidth(100);
        id.setMinWidth(100);

        name.setText(" Name ");
        price.setText(" price ");
        id.setText( " productId ");


        details.getChildren().addAll(id,name,price);
        productlist.add(details);

        while(res.next()) {

            if (res.getString("productName").toLowerCase().contains(search.toLowerCase())) {

                Label productName = new Label();
                Label productPrice = new Label();
                Label productId = new Label();
                Button buy = new Button();
                HBox productDetails = new HBox();
                productName.setMinWidth(100);
                productPrice.setMinWidth(100);
                productId.setMinWidth(100);
                buy.setText("Buy");

                buy.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent actionEvent) {

                        if (HelloApplication.emailId.equals("")) {
                            Dialog<String> dialog = new Dialog<>();
                            dialog.setTitle("login");
                            ButtonType type = new ButtonType("ok", ButtonBar.ButtonData.OK_DONE);
                            dialog.setContentText("login first then buy....");
                            dialog.getDialogPane().getButtonTypes().add(type);
                            dialog.showAndWait();
                        } else {
                            System.out.println("you clicked on buy");

                            try {
                                Order place = new Order();
                                place.placeOrder(productId.getText());
                            } catch (SQLException e) {
                                throw new RuntimeException(e);
                            }
                        }
                    }
                });
                productName.setText(res.getString("productName"));
                productPrice.setText(res.getString("price"));
                productId.setText(" " + res.getInt("productId"));

                productDetails.getChildren().addAll(productId, productName, productPrice, buy);
                productlist.add(productDetails);

            }
        }

        if(productlist.size()==1){
            Dialog<String> dialog = new Dialog<>();
            dialog.setTitle("search");
            ButtonType type=new ButtonType("ok", ButtonBar.ButtonData.OK_DONE);
            dialog.setContentText("This item is not available!! will be available soon");
            dialog.getDialogPane().getButtonTypes().add(type);
            dialog.showAndWait();
        }
            products.setItems(productlist);
            return products;

    }

    ListView<HBox> showProducts() throws SQLException {
        ObservableList<HBox> productlist= FXCollections.observableArrayList();
        ResultSet res= HelloApplication.connection.executeQuery("select * from product");
        products=new ListView<>();

        Label name=new Label();
        Label price=new Label();
        Label id=new Label();

        HBox details= new HBox();
        name.setMinWidth(100);
        price.setMinWidth(100);
        id.setMinWidth(100);

        name.setText(" Name ");
        price.setText(" price ");
        id.setText( " productId ");


        details.getChildren().addAll(id,name,price);
        productlist.add(details);

        while(res.next()){

            Label productName=new Label();
            Label productPrice=new Label();
            Label productId=new Label();
            Button buy=new Button();
            HBox productDetails= new HBox();
            productName.setMinWidth(100);
            productPrice.setMinWidth(100);
            productId.setMinWidth(100);
            buy.setText("Buy");

            buy.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {

                    if(HelloApplication.emailId.equals("")){
                        Dialog<String> dialog = new Dialog<>();
                        dialog.setTitle("login");
                        ButtonType type=new ButtonType("ok", ButtonBar.ButtonData.OK_DONE);
                        dialog.setContentText("login first then buy....");
                        dialog.getDialogPane().getButtonTypes().add(type);
                        dialog.showAndWait();
                    }
                    else{
                        System.out.println("you clicked on buy");

                        try {
                            Order place= new Order();
                            place.placeOrder(productId.getText());
                        } catch (SQLException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }
            });
            productName.setText(res.getString("productName"));
            productPrice.setText(res.getString("price"));
            productId.setText(" "+res.getInt("productId"));

            productDetails.getChildren().addAll(productId,productName,productPrice,buy);
            productlist.add(productDetails);

        }
        products.setItems(productlist);
        return products;
    }




}
