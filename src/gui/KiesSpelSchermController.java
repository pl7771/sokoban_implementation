package gui;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import domein.DomeinController;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import util.Taal;

public class KiesSpelSchermController extends BorderPane {
	@FXML
	private Label lblSpelkeuze;
	@FXML
	private ListView<String> spellenListView;
	@FXML
	private Button btnTerug;

	private DomeinController dc;
	private Taal taal = Taal.getInstance();
// EINDE ATTRIBUTEN
	
	public KiesSpelSchermController(DomeinController dc) {
		this.dc = dc;
		FXMLLoader loader = new FXMLLoader(getClass().getResource("KiesSpelScherm.fxml"));
		loader.setRoot(this);
		loader.setController(this);
		try {
            loader.load();
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
        catch(NullPointerException ie) {}
		
		//Tekst voor Label
		lblSpelkeuze.setText(taal.vertaal("keuzeSpel"));
		
		
		//ListView - neemt array van bestaande spellen - add elke string in de listview
		List<String> lijstSpellen = new ArrayList<String>();
		lijstSpellen = dc.geefNamenBestaandeSpellen();
		
		spellenListView.setItems(FXCollections.observableArrayList(lijstSpellen));
		
		spellenListView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE); //kan slechts 1 spel selecteren
		spellenListView.getSelectionModel() .selectedItemProperty().addListener(observable -> kiesSpelUitLijst()); //listener op listview

		
		 
    }
			
	
// EINDE CONSTRUCTOR
	
	// Event Listener on Button[#btnConfirm].onAction
	@FXML
	public void kiesDitSpel(ActionEvent event) {
			/// een listener opgehaangd ivm met feedback
	}
	
	
	// Event Listener on Button[#btnTerug].onAction
	@FXML
	public void keerTerug(ActionEvent event) { //keer terug naar vorig scherm afhankelijk van adminrechten true of false
		if(dc.geefSpeler()[1].equalsIgnoreCase("true")) {
			naarAdminMenuScherm();
		}else {
			naarGewoonMenuScherm();
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
	
	
	private void kiesSpelUitLijst() {
        String spel = spellenListView.getSelectionModel().getSelectedItem();
        dc.kiesSpel(spel);
        Scene scene = new Scene(new SpeelSpelSchermController(dc), 800, 800);
		Stage stage = (Stage) this.getScene().getWindow();
		stage.setScene(scene);
		stage.show();
    }



}
