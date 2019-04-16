package zadatak08;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Popup;
import javafx.stage.PopupWindow;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.EventListener;

public class Zadatak08 extends Application {
	private ArrayList<Contact> contacts = new ArrayList<>();

	@Override
	public void start(Stage primaryStage) throws IOException {
		try {
			this.contacts = readFile();
		} catch (FileNotFoundException e) {
			Files.write(Paths.get("contacts"), "".getBytes());
		}
		BorderPane root = new BorderPane();
		Scene scene = new Scene(root);
		// VIEW
		VBox viewPane = new VBox();
		viewPane.setSpacing(25);
		viewPane.setMaxWidth(450);
		viewPane.setMinWidth(450);

		viewPane.setAlignment(Pos.TOP_CENTER);
		Text textName = new Text("Test");
		Text textNum = new Text("Test");

		viewPane.getChildren().addAll(textName, textNum);
		// VIEW END

		// LIST
		FlowPane listPane = new FlowPane();
		listPane.setMaxWidth(200);
		listPane.setMinWidth(200);
		ListView<Contact> list = new ListView<>(FXCollections.observableArrayList(this.contacts));
		list.setOnMousePressed(e -> {
			Contact selectedContact = list.getSelectionModel().getSelectedItem();
			textName.setText(selectedContact.name);
			textNum.setText(selectedContact.number);
			root.setCenter(viewPane);

		});
		listPane.getChildren().addAll(list);
		// LIST END

		// FORM
		VBox formPane = new VBox();

		formPane.setAlignment(Pos.TOP_CENTER);
		formPane.setSpacing(25);
		formPane.setMaxWidth(450);
		formPane.setMinWidth(450);

		TextField inpName = new TextField();
		TextField inpNum = new TextField();
		inpName.setMaxWidth(200);
		inpNum.setMaxWidth(200);
		Button btnAddCont = new Button("Add");

		btnAddCont.setOnMousePressed(e -> {
			if (!inpName.getText().equals("") && !inpNum.getText().equals("")) {
				Contact newContact = new Contact(inpName.getText(), inpNum.getText());
				// kada implementiram svoj equals metod
				// koji je potreban da bi contains radilo
				// ListView izbacuje NullPointerException
				// iz nekog nepoznatog razloga
				// mrzi me da debagujem jer ima tipa
				// 40 linija stack trace-a
				// laku noc
				if (!this.contacts.contains(newContact)) {
					this.contacts.add(newContact);

					inpName.setText("");
					inpNum.setText("");
					try {
						writeFile(this.contacts);
					} catch (IOException ex) {
						ex.printStackTrace();
					}
					// nije efikasno
					list.setItems(FXCollections.observableArrayList(this.contacts));
				} else {
					Popup popup = new Popup();
					popup.setAutoHide(true);
					Label message = new Label("Contact already exits");
					message.setMinHeight(50);
					message.setMinWidth(150);
					message.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
					message.setStyle(" -fx-background-color: white;");
					popup.setHideOnEscape(true);
					popup.getContent().add(message);
					popup.show(primaryStage);
				}
			}
		});

		formPane.getChildren().addAll(inpName, inpNum, btnAddCont);

		// FORM END



		// TOP
		FlowPane top = new FlowPane();
		top.setPadding(new Insets(10));
		top.setHgap(10);
		Button btnListView = new Button("List");
		Button btnAddView = new Button("Add");

		btnListView.setOnMousePressed(e -> {
			if (e.getButton() == MouseButton.PRIMARY) {
				root.setCenter(viewPane);
			}
		});

		btnAddView.setOnMousePressed(e -> {
			if (e.getButton() == MouseButton.PRIMARY) {
				root.setCenter(formPane);
			}
		});

		top.getChildren().addAll(btnAddView, btnListView);
		// TOP END

		root.setTop(top);
		root.setCenter(formPane);
		root.setLeft(listPane);

		primaryStage.setResizable(false);
		primaryStage.setMinHeight(400);
		primaryStage.setMinWidth(650);
		primaryStage.setScene(scene);
		primaryStage.setTitle("Contacts");
		primaryStage.show();

	}

	public static ArrayList<Contact> readFile() throws IOException {
		File fp = new File("contacts");
		String buffer;
		ArrayList<Contact> out = new ArrayList<>();
		BufferedReader bufferedReader = new BufferedReader(new FileReader(fp));
		try {
			while ((buffer = bufferedReader.readLine()) != null) {
				String[] lineSplit = buffer.split(",");
				Contact newContact = new Contact(lineSplit[0], lineSplit[1]);
				out.add(newContact);
			}
			return out;
		} catch (IOException e) {
			return out;
		} finally {
			bufferedReader.close();
		}
	}

	public static void writeFile(ArrayList<Contact> contacts) throws IOException {
		File fp = new File("contacts");
		BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(fp));
		for (Contact contact : contacts) {
			System.out.println(contact.toString());
			bufferedWriter.write(String.format("%s\n", contact.toString()));
		}
		bufferedWriter.flush();
		bufferedWriter.close();
	}

	static class Contact {
		public String name;
		public String number;

		public Contact(String name, String number) {
			this.name = name;
			this.number = number;
		}

		@Override
		public String toString() {
			return String.format("%s,%s", this.name, this.number);
		}

//		@Override
//		public boolean equals(Object obj) {
//			return this.name.equals(((Contact) obj).name);
//		}

	}
}


