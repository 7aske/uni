package client.components;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

public class TopControls extends HBox {
	public Button btnCompose;
	public Button btnMessages;
	public Button btnRefresh;
	public Button btnLogout;

	public TopControls() {
		this.btnMessages = new Button("Messages");
		this.btnCompose = new Button("Compose");
		this.btnRefresh = new Button("Refresh");
		this.btnLogout = new Button("Logout");

		this.setPadding(new Insets(5, 0, 5, 0));
		this.setSpacing(5);
		this.btnMessages.setMaxWidth(150);
		this.btnMessages.setMaxWidth(150);
		this.btnCompose.setMaxWidth(150);
		this.btnLogout.setMaxWidth(150);

		this.getChildren().addAll(this.btnRefresh, this.btnCompose, this.btnMessages, btnLogout);
		this.setBackground(new Background(new BackgroundFill(Color.web("#454545"), CornerRadii.EMPTY, Insets.EMPTY)));
	}
}
