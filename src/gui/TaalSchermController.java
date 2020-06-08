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

public class TaalSchermController extends Pane {
	@FXML
	private Button btnEng;
	@FXML
	private Button btnNl;
	@FXML
	private Button btnFr;
	@FXML
	private Button btnSluiten;
	
	
	
	
	private DomeinController dc;
    private Taal taal;
	
	public TaalSchermController(DomeinController dc) {
		this.dc = dc;
		FXMLLoader loader = new FXMLLoader(getClass().getResource("TaalScherm.fxml"));
        loader.setRoot(this);
        loader.setController(this);
        try {
            loader.load();
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
        catch(NullPointerException ie){
        }
        
        btnSluiten.setText("Exit");
	}
	
	// Event Listener on Button[#btnSluiten].onAction
		@FXML
		public void afsluiten(ActionEvent event) {
			System.exit(0);
		}
		
	// Event Listener on Button[#btnEng].onAction
	@FXML
	public void kiesEngels(ActionEvent event) {
		setTaal(2);
		naarHoofdMenuScherm();
	}
	// Event Listener on Button[#btnNl].onAction
	@FXML
	public void kiesNederlands(ActionEvent event) {
		setTaal(1);
		naarHoofdMenuScherm();
	}
	// Event Listener on Button[#btnFr].onAction
	@FXML
	public void kiesFrans(ActionEvent event) {
		setTaal(3);
		naarHoofdMenuScherm();
	}
	
	
	private void naarHoofdMenuScherm() {
		Scene scene = new Scene(new HoofdSchermController(dc), 800, 800);
		Stage stage = (Stage) this.getScene().getWindow();
		stage.setScene(scene);
		stage.show();
	}
	

	private void setTaal(int gekozenTaal) {
		taal = Taal.getInstance();
		taal.setTaal(gekozenTaal);
	}
	
	
	
	
}
