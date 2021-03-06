package gui;

import java.io.IOException;

import domein.DomeinController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;

import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import util.Taal;

public class GeenAdminrechtenMenuController extends Pane{
	@FXML
	private Label lblWelkom;
	@FXML
	private Button btnAfsluiten;
	@FXML
	private Button btnSpeelspel;
	private DomeinController dc;
	private Taal taal = Taal.getInstance();
	
	public GeenAdminrechtenMenuController(DomeinController dc) {
		// TODO Auto-generated constructor stub
		this.dc = dc;
		
		FXMLLoader loader = new FXMLLoader(getClass().getResource("GeenAdminrechtenMenu.fxml"));
		loader.setRoot(this);
		loader.setController(this);
		try {
            loader.load();
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
        catch(NullPointerException ie){
        }
		lblWelkom.setText(taal.vertaal("welkom")+" "+dc.geefSpeler()[0]);
		btnAfsluiten.setText(taal.vertaal("afsluiten"));
		btnSpeelspel.setText(taal.vertaal("speelSpel"));        
	}
	
	
	 	@FXML
	    void aflsuiten(ActionEvent event) {
	 		Scene scene = new Scene(new TaalSchermController(dc), 800, 800);
			Stage stage = (Stage) this.getScene().getWindow();
			stage.setScene(scene);
			stage.show();	
	    }

	    @FXML
	    void speelSpel(ActionEvent event) {
	    	Scene scene = new Scene(new KiesSpelSchermController(dc), 800, 800);
	    	Stage stage = (Stage) this.getScene().getWindow();
	    	stage.setScene(scene);
	    	stage.show();
	    }
}
