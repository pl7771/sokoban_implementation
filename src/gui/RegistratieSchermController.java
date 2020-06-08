package gui;

import java.io.IOException;

import domein.DomeinController;
import domein.Speler;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import util.Taal;

public class RegistratieSchermController extends Pane{
	
	@FXML
    private Label lblGebruikersnaam;

    @FXML
    private TextField txfGebruikersnaam;

    @FXML
    private Label lblWachtwoord;

    @FXML
    private PasswordField pwfWachtwoord;

    @FXML
    private Label lblBevestigWachtwoord;

    @FXML
    private PasswordField pwfWachwoordbevestiging;

    @FXML
    private Label lblVoornaam;

    @FXML
    private TextField txfVoornaam;

    @FXML
    private Label lblAchternaam;

    @FXML
    private TextField txfAchternaam;

    @FXML
    private Button btnRegistreer;
    
    
    @FXML
    private Button btnAnnuleer;

    private Speler speler;
	
	private DomeinController dc;
	private Taal taal = Taal.getInstance();
	public RegistratieSchermController(DomeinController dc) {
		this.dc = dc;
		FXMLLoader loader = new FXMLLoader(getClass().getResource("RegistratieScherm.fxml"));
        loader.setRoot(this);
        loader.setController(this);
        try {
            loader.load();
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
        catch(NullPointerException ie){
        }
        
        lblGebruikersnaam.setText(taal.vertaal("nieuweGebruikersnaam"));
        lblWachtwoord.setText(taal.vertaal("nieuwWachtwoord"));
        lblBevestigWachtwoord.setText(taal.vertaal("bevestigWachtwoord"));
        lblVoornaam.setText(taal.vertaal("voornaam"));
        lblAchternaam.setText(taal.vertaal("achternaam"));
        btnRegistreer.setText(taal.vertaal("registeer"));
        btnAnnuleer.setText(taal.vertaal("anulleerGui"));
	}
	

    

    @FXML
    void registreer(ActionEvent event) {
    	try {
    		speler = new Speler(txfGebruikersnaam.getText(), pwfWachtwoord.getText(), txfVoornaam.getText(), txfAchternaam.getText());
			dc.registreer(txfGebruikersnaam.getText(), pwfWachtwoord.getText(), txfVoornaam.getText(), txfAchternaam.getText());
			
			
				naarGewoonMenuScherm();
			
			
		} catch (IllegalArgumentException e) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error");
			alert.setHeaderText("Registratie error");
			alert.setContentText(e.getMessage());
			alert.showAndWait();
		}
    }
    
    @FXML
    void annuleer(ActionEvent event) {
    	Scene scene = new Scene(new TaalSchermController(dc), 800, 800);
		Stage stage = (Stage) this.getScene().getWindow();
		stage.setScene(scene);
		stage.show();	
    }
    
    private void naarGewoonMenuScherm() {
		Scene scene = new Scene(new GeenAdminrechtenMenuController(dc), 800, 800);
		Stage stage = (Stage) this.getScene().getWindow();
		stage.setScene(scene);
		stage.show();	
	} 

}
