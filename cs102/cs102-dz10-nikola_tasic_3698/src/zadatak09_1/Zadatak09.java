package zadatak09_1;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOError;
import java.io.IOException;
import java.util.ArrayList;

public class Zadatak09 extends Application {
	private ProgressBar progressBar;
	private String url = "http://kuhinjica.rs/";

	@Override
	public void start(Stage stage) {
		VBox root = new VBox();
		Scene scene = new Scene(root, 600, 400);

		progressBar = new ProgressBar(0f);


		root.getChildren().add(progressBar);

		stage.setScene(scene);
		stage.show();


		Thread getTitlesThread = new Thread(() -> {
			try {
				ArrayList<String> titles = getTitles(this.url);
				File dir = new File("zad09_1");
				if (!dir.exists()){
					if (!dir.mkdirs()) {
						throw new IOException("Unable to create dir");
					}
				}
				for (int i = 0; i < titles.size(); i++) {
					final int iter = i;
					String title = titles.get(iter);
					if (!title.isEmpty()) {
						File fname = new File(String.format(dir.getAbsolutePath() + "/title%d.txt", iter));
						FileOutputStream fileOutputStream = new FileOutputStream(fname);
						fileOutputStream.write(((title + "\n").getBytes()));
						fileOutputStream.close();
						Platform.runLater(() -> {
							progressBar.setProgress((1f / titles.size()) * (iter + 1f));
							root.getChildren().add(new Label(title));
						});
						Thread.sleep(500);
					}
				}

			} catch (IOException | InterruptedException e) {
				e.printStackTrace();
			}
		});
		getTitlesThread.start();
	}

	private static ArrayList<String> getTitles(String url) throws IOException {
		ArrayList<String> out = new ArrayList<>();
		Document document = Jsoup.connect(url).get();
		Elements titles = document.select("h1");
		titles.forEach(t -> out.add(t.text()));
		return out;
	}
}
