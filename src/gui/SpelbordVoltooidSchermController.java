package gui;

import java.io.IOException;

import domein.DomeinController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import util.Taal;

public class SpelbordVoltooidSchermController extends BorderPane {
	
	private DomeinController dc;
	private Taal taal = Taal.getInstance();
	@FXML
	private Label lblVoltooid;

	
	public SpelbordVoltooidSchermController(DomeinController dc, int aantalVereplaatsingen) {
		this.dc = dc;
		FXMLLoader loader = new FXMLLoader(getClass().getResource("SpelbordVoltooidScherm.fxml"));
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
		lblVoltooid.setText(String.format("%s %d %s", taal.vertaal("spelbordvoltooid"), aantalVereplaatsingen, taal.vertaal("verplaatsingen")));

	}

	
	
}
