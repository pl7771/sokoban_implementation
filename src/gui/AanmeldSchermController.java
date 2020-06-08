package gui;

import java.io.IOException;
import java.util.Optional;

import domein.DomeinController;
import exceptions.FoutWachtwoordException;
import exceptions.SpelerNietGekendException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import util.Taal;
import javafx.event.ActionEvent;

public class AanmeldSchermController extends Pane {
	
	private DomeinController dc;
	@FXML
	private Button btnAanmelden;
	@FXML
	private Label lblGebruikersnaam;
	@FXML
	private TextField txfGebruikersnaam;
	@FXML
	private Label lblWachtwoord;
	@FXML
	private PasswordField pwfWachtwoord;
	private Taal taal = Taal.getInstance();
	
	@FXML
    private Button btnAnnuleer;

    
	
	public AanmeldSchermController(DomeinController dc) {
		// TODO Auto-generated constructor stub
		this.dc = dc;
		
		FXMLLoader loader = new FXMLLoader(getClass().getResource("AanmeldScherm.fxml"));
		loader.setRoot(this);
		loader.setController(this);
		try {
            loader.load();
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
        catch(NullPointerException ie){
        }
        
        lblGebruikersnaam.setText(taal.vertaal("gebruikersnaam"));
        lblWachtwoord.setText(taal.vertaal("wachtwoord"));
        btnAanmelden.setText(taal.vertaal("aanmeldenGui"));
        btnAnnuleer.setText(taal.vertaal("anulleerGui"));
        
        
	}
	
	@FXML
    void annulleer(ActionEvent event) {
		Scene scene = new Scene(new TaalSchermController(dc), 800, 800);
		Stage stage = (Stage) this.getScene().getWindow();
		stage.setScene(scene);
		stage.show();	
    }
	
	
	
	// Event Listener on Button[#btnAanmelden].onAction
		@FXML
		public void aanmelden(ActionEvent event) {
			String login = txfGebruikersnaam.getText();
			String passwoord = pwfWachtwoord.getText();
			
			
			
			try {
				dc.meldAan(login, passwoord);
				
				if(dc.geefSpeler()[1].equalsIgnoreCase("true")) {
					naarAdminMenuScherm();
				}else {
					naarGewoonMenuScherm();
				}
				
				
				
			} catch (FoutWachtwoordException ex) {
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("Error");
				alert.setHeaderText(taal.vertaal("aanmelfoutGui"));
				alert.setContentText(taal.vertaal("foutPasswdExc"));
				alert.showAndWait();
				txfGebruikersnaam.clear();
				pwfWachtwoord.clear();
			} catch (SpelerNietGekendException ex) {
				Alert alert = new Alert(AlertType.CONFIRMATION);
				alert.setTitle("Confirm");
				alert.setHeaderText(taal.vertaal("geenUserGui"));
				alert.setContentText(taal.vertaal("spelerBestaatNietExcGui"));

				Optional<ButtonType> result = alert.showAndWait();
				if (result.get() == ButtonType.OK){
				    
					Scene scene = new Scene(new RegistratieSchermController(dc), 800, 800);
					Stage stage = (Stage) this.getScene().getWindow();
					stage.setScene(scene);
					stage.show();
					
				} else {  
					Scene scene = new Scene(new TaalSchermController(dc), 800, 800);
					Stage stage = (Stage) this.getScene().getWindow();
					stage.setScene(scene);
					stage.show();	
				}
			}
			
		}

		private void naarAdminMenuScherm() {
			Scene scene = new Scene(new AdminRechtenMenuController(dc), 800, 800);
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
