package main;


import dbController.DBController;
import flightReservation.FlightReservation;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Date;


public class Main extends Application {
	ArrayList<FlightReservation> flightReservations;

	@Override
	public void start(Stage stage) {
		DBController.initDatabase();

		FlightReservation f = new FlightReservation(new Date(), "Nikola Tasic", "INI-BGD", (short) 3, 5000);
		// DBController.addFlight(f);

		BorderPane root = new BorderPane();
		Scene scene = new Scene(root, 600, 400);

		FlowPane left = new FlowPane();
		flightReservations = DBController.getAllFlights();

		ListView<FlightReservation> flightsList = new ListView<>(FXCollections.observableArrayList(flightReservations));
		flightsList.setCellFactory(param -> new ListCell<FlightReservation>() {
			@Override
			protected void updateItem(FlightReservation flight, boolean empty) {
				super.updateItem(flight, empty);
				if (empty || flight == null) {
					setText(null);
				} else {
					setText(flight.toString());
				}
			}
		});

		flightsList.setOnKeyPressed(e -> {
			if (e.getCode() == KeyCode.DELETE) {
				FlightReservation sf = flightsList.getSelectionModel().getSelectedItem();
				if (sf != null) {
					DBController.removeFlight(sf);
					updateFlightList();
					flightsList.setItems(FXCollections.observableArrayList(flightReservations));
					flightsList.refresh();
				}
			}
		});
		left.getChildren().addAll(flightsList);

		VBox center = new VBox();


		TextField reservedByTf = new TextField();
		TextField fromToTf = new TextField();
		TextField passengersTf = new TextField();
		TextField priceTf = new TextField();

		reservedByTf.setPromptText("Your Name");
		fromToTf.setPromptText("From-To");
		passengersTf.setPromptText("# of passengers");
		priceTf.setPromptText("Price");

		Button addBtn = new Button("Add");

		addBtn.setOnMouseClicked(e -> {
			if (e.getButton() == MouseButton.PRIMARY) {
				FlightReservation newFlight = new FlightReservation(new Date(), reservedByTf.getText(), fromToTf.getText(), Short.parseShort(passengersTf.getText()), Double.parseDouble(priceTf.getText()));
				DBController.addFlight(newFlight);
				updateFlightList();
				flightsList.setItems(FXCollections.observableArrayList(flightReservations));
				flightsList.refresh();
			}
		});

		center.getChildren().addAll(reservedByTf, fromToTf, passengersTf, priceTf, addBtn);
		center.setAlignment(Pos.CENTER);

		root.setLeft(left);
		root.setCenter(center);

		stage.setScene(scene);
		stage.setResizable(false);
		stage.show();
		flightsList.requestFocus();
	}

	private void updateFlightList() {
		flightReservations = DBController.getAllFlights();

	}
}
