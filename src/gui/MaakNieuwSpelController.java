package gui;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;

import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import util.Taal;

import java.io.IOException;

import domein.DomeinController;
import exceptions.SpelBestaatException;
import javafx.event.ActionEvent;

import javafx.scene.control.Label;

public class MaakNieuwSpelController extends Pane{
	@FXML
	private Label lblmaakNieuwSpel;
	@FXML
	private Label lblNieuwSpelbord;
	@FXML
	private TextField txfNaamNieuwSpel;
	@FXML
	private Button btnAanmakenSpelbord;
	@FXML
	private Button btnAnnuleer;
	private DomeinController dc;
	private Taal taal = Taal.getInstance();
	
	public MaakNieuwSpelController(DomeinController dc) {
		this.dc = dc;

		FXMLLoader loader = new FXMLLoader(getClass().getResource("MaakNieuwSpel.fxml"));
        loader.setRoot(this);
        loader.setController(this);
        try {
            loader.load();
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
        catch(NullPointerException ie){
        }
        
        lblmaakNieuwSpel.setText(taal.vertaal("geefNaamSpelGui"));
        btnAnnuleer.setText(taal.vertaal("anulleerGui"));
        btnAanmakenSpelbord.setText(taal.vertaal("verderGui"));
        
	}
	
	// Event Listener on Button[#btnAanmakenSpelbord].onAction
	@FXML
	public void aanmelden(ActionEvent event) {
		try {
			dc.maakNieuwSpel(txfNaamNieuwSpel.getText());
			dc.maakLeegSpelbordAan();
			Scene scene = new Scene(new MaakNieuwSpelbordController(dc), 800, 800);
			Stage stage = (Stage) this.getScene().getWindow();
			stage.setScene(scene);
			stage.show();	
		} catch (SpelBestaatException ex) {
			lblNieuwSpelbord.setText(String.format("Spel %s bestaat, kies andere naam", txfNaamNieuwSpel.getText()));
			txfNaamNieuwSpel.clear();
		} catch (IllegalArgumentException ex) {
			lblNieuwSpelbord.setText(ex.getMessage());
			txfNaamNieuwSpel.clear();
		}
	}
	// Event Listener on Button[#btnAnnuleer].onAction
	@FXML
	public void annulleer(ActionEvent event) {
		Scene scene = new Scene(new AdminRechtenMenuController(dc), 800, 800);
		Stage stage = (Stage) this.getScene().getWindow();
		stage.setScene(scene);
		stage.show();	
	}
	
	
	
}
