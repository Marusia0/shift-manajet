package view;

import controller.LoginController;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.Employee;
import model.Position;

import java.util.Random;

import static com.sun.javafx.tools.resource.DeployResource.Type.data;

public class BasicSettingView extends View{
//
    private final TableView<Position> table = new TableView<>();
    private final ObservableList<Position> data =
            FXCollections.observableArrayList();
    final HBox hb = new HBox();
//
//    public static void main(String[] args) {
//        launch(args);
//    }
//
//    @Override
//    public void start(Stage stage) {
//        Scene scene = new Scene(new Group());
//        stage.setTitle("Table View Sample");
//        stage.setWidth(1000);
//        stage.setHeight(760);
//
//        final Label label = new Label("Create shift position");
//        label.setFont(new Font("Arial", 20));
//
//        table.setEditable(true);
//
//        TableColumn positionNameCol = new TableColumn("Position name");
//        positionNameCol.setMinWidth(400);
//        positionNameCol.setCellValueFactory(
//                new PropertyValueFactory<>("positionName"));
//
//
//
//        table.setItems(data);
//        table.getColumns().addAll(positionNameCol);
//
//        final TextField addPositionName = new TextField();
//        addPositionName.setPromptText("Position name");
//        addPositionName.setMaxWidth(positionNameCol.getPrefWidth());
//
//        final Button addButton = new Button("Add");
//        addButton.setOnAction((ActionEvent e) -> {
//            data.add(new Person(
//                    addPositionName.getText()));
//            addPositionName.clear();
//        });
//
//        hb.getChildren().addAll(addPositionName, addButton);
//        hb.setSpacing(3);
//
//        final VBox vbox = new VBox();
//        vbox.setSpacing(5);
//        vbox.setPadding(new Insets(10, 0, 0, 10));
//        vbox.getChildren().addAll(label, table, hb);
//
//        ((Group) scene.getRoot()).getChildren().addAll(vbox);
//
//        stage.setScene(scene);
//        stage.show();
//    }
//
//    public static class Person {
//
//        private final SimpleStringProperty positionName;
//
//        private Person(String pName) {
//            this.positionName = new SimpleStringProperty(pName);
//        }
//
//        public String getPositionName() {
//            return positionName.get();
//        }
//
//        public void setPositionName(String pName) {
//            positionName.set(pName);
//        }
//
//    }






    public void start(Stage stage) {
        try {

            GridPane grid = new GridPane();
            grid.setAlignment(Pos.CENTER);
            grid.setHgap(10);
            grid.setVgap(10);

                        final VBox vbox = new VBox();
            vbox.setSpacing(5);
            vbox.setPadding(new Insets(10, 0, 0, 10));
//            vbox.getChildren().addAll(grid);

            grid.setPadding(new Insets(25, 25, 25, 25));
            Scene scene = new Scene(grid,1184, 775);


            // create title field
            Text title = new Text("BASIC SETTING");
            title.setFont(new Font("Arial",30));
            grid.add(title, 1, 1);


            // create company name input field
            TextField companyName = new TextField();
            grid.add(companyName, 1, 2);
            Label labelCompanyName = new Label();
            labelCompanyName.setText("Company name:");
            labelCompanyName.setLabelFor(companyName);
            grid.add(labelCompanyName, 0, 2);


            //create period type radio button field
            Label lb = new Label();
            lb.setText("Period Type:");
            lb.setLabelFor(companyName);
            grid.add(lb, 0, 3);
            final ToggleGroup group = new ToggleGroup();
            RadioButton rb1 = new RadioButton("Every month");
            rb1.setToggleGroup(group);
            grid.add(rb1, 1, 3);
            RadioButton rb2 = new RadioButton("Every week");
            rb2.setToggleGroup(group);
            grid.add(rb2, 2, 3);
            RadioButton rb3 = new RadioButton("Every 2week");
            rb3.setToggleGroup(group);
            grid.add(rb3, 3, 3);



            // Shift positions
            final Label label = new Label("Create shift position");
            label.setFont(new Font("Arial", 20));

            table.setEditable(true);

            TableColumn<Position, String> positionNameCol = new TableColumn<>("Position name");
            positionNameCol.setMinWidth(400);
            positionNameCol.setCellValueFactory(
            new PropertyValueFactory<>("name"));



            table.setItems(data);
            table.getColumns().addAll(positionNameCol);

            final TextField addPositionName = new TextField();
            addPositionName.setPromptText("Position name");
            addPositionName.setMaxWidth(positionNameCol.getPrefWidth());

            final Button addButton = new Button("Add");
            addButton.setOnAction((ActionEvent e) -> {
                data.add(new Position(
                        addPositionName.getText()));
                addPositionName.clear();
            });



            // delete bottom
            TableColumn<Position, Position> deleteCol = new TableColumn<>("Delete");
            deleteCol.setMinWidth(100);
            deleteCol.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue()));
            deleteCol.setCellFactory(param -> new TableCell<Position, Position>() {
                private final Button deleteButton = new Button("delete");

                @Override
                protected void updateItem(Position position, boolean empty) {
                    super.updateItem(position, empty);

                    if (position == null) {
                        setGraphic(null);
                        return;
                    }

                    setGraphic(deleteButton);
                    deleteButton.setOnAction(event -> data.remove(position));
                }
            });







            hb.getChildren().addAll(addPositionName, addButton);
            hb.setSpacing(3);



//            ((Group) scene.getRoot()).getChildren().addAll(vbox);
            grid.add(vbox, 1,6);
            vbox.getChildren().addAll(label, table, hb);

            // create start button
            Button btnStart = new Button("Start");
            HBox hbBtn = new HBox(10);
            //Set box alignment on the grid
            hbBtn.setAlignment(Pos.BOTTOM_RIGHT);
            //Add login button to the button box
            hbBtn.getChildren().add(btnStart);
            //Add button box to the grid(root) element on the column/line index
            grid.add(hbBtn, 1, 7);




//            btnStart.setOnAction(new EventHandler<ActionEvent>() {
//                @Override
//                public void handle(ActionEvent event) {//implements the method that will be called
//                    //before start process clean previous messages
//                    loginMessage.setText("");
//                    //call meethod login on controller and check if login is true or false
//                    if(controller.login(username.getText(), password.getText())){
//                        System.out.println("Login sucessfull!");
//                    }else{
//                        //Login false, set message on text component
//                        loginMessage.setText("Invalid email/password.");
//                        //Set message color to red
//                        loginMessage.setFill(Color.RED);
//                    }
//                }
//            });


            stage.setScene(scene);
            stage.show();


        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}