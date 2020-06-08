package gui;

import java.io.IOException;

import domein.DomeinController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;

import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import util.Taal;

public class SpelVoltooidSchermController extends BorderPane{
	@FXML
	private Label lblVoltooidSpel;
	@FXML
	private Button btnTerug;
	private DomeinController dc;
	private Taal taal = Taal.getInstance();
	
	public SpelVoltooidSchermController(DomeinController dc, int aantalStappen) {
		this.dc = dc;
		FXMLLoader loader = new FXMLLoader(getClass().getResource("SpelVoltooidScherm.fxml"));
		loader.setRoot(this);
		loader.setController(this);
	
		 
		try {
            loader.load();
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
		ImageView ivImage = new ImageView(
                new Image(getClass().getResourceAsStream("/images/voltooid.png"))
       );
		this.setCenter(ivImage);
		lblVoltooidSpel.setText(String.format("%s %d %s%s", taal.vertaal("spelbordvoltooid"), aantalStappen , 
				taal.vertaal("verplaatsingen"), taal.vertaal("kiesterug")));
		btnTerug.setText(String.format("%s", taal.vertaal("terugNaarSpelkeuze")));
		btnTerug.setOnAction(e -> {
			Scene scene = new Scene(new KiesSpelSchermController(dc), 800, 800);
			Stage stage = (Stage) this.getScene().getWindow();
			stage.setScene(scene);
			stage.show();
		});
	}

}
