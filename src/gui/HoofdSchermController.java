package gui;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import util.Taal;

import java.io.IOException;

import domein.DomeinController;
import javafx.event.ActionEvent;

public class HoofdSchermController extends Pane{
	@FXML
	private Button btnAanmelden;
	@FXML
	private Button btnSluiten;
	@FXML
	private Button btnNieuwGebruiker;
	private DomeinController dc;
	private Taal taal = Taal.getInstance();
	
	public HoofdSchermController(DomeinController dc) {
		this.dc = dc;

		FXMLLoader loader = new FXMLLoader(getClass().getResource("HoofdScherm.fxml"));
        loader.setRoot(this);
        loader.setController(this);
        try {
            loader.load();
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
        catch(NullPointerException ie){
        }
        
        btnNieuwGebruiker.setText(taal.vertaal("newUser"));
        btnAanmelden.setText(taal.vertaal("aanmelden"));
        btnSluiten.setText(taal.vertaal("sluitenSpel"));
	}
	
	// Event Listener on Button[#btnAanmelden].onAction
	@FXML
	public void aanmelden(ActionEvent event) {
		naarAanmeldScherm();
	}
	// Event Listener on Button[#btnSluiten].onAction
	@FXML
	public void afsluiten(ActionEvent event) {
		System.exit(0);
	}
	// Event Listener on Button[#btnNieuwGebruiker].onAction
	@FXML
	public void addNieuwGebruiker(ActionEvent event) {
		Scene scene = new Scene(new RegistratieSchermController(dc), 800, 800);
		Stage stage = (Stage) this.getScene().getWindow();
		stage.setScene(scene);
		stage.show();
	}
	
	private void naarAanmeldScherm() {
		Scene scene = new Scene(new AanmeldSchermController(dc), 800, 800);
		Stage stage = (Stage) this.getScene().getWindow();
		stage.setScene(scene);
		stage.show();	
	}
}
