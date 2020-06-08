package gui;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import util.Taal;
import java.io.IOException;

import domein.DomeinController;
import domein.Richting;
import exceptions.TegenMuurException;
import javafx.application.Platform;
import javafx.event.ActionEvent;

public class SpeelSpelSchermController extends BorderPane{
	@FXML
	private Button btnReset;
	@FXML
	private Button btnExit;
	
	private DomeinController dc;
	private Taal taal = Taal.getInstance();
	private SpelGrondController spelGrondController; //instantie van spelGrond wordt toegevoegd in de constructor van SpeelSchermConstructor
	@FXML
    private Button btnUp;

    @FXML
    private Button btnLeft;

    @FXML
    private Button btnRight;

    @FXML
    private Button btnDown;

    @FXML
    private Button btnVolgende;
    
    @FXML
    private Label lblaantalVerplaatsingen;
	
    private int aantalVerplaatsingen;
   
	public SpeelSpelSchermController (DomeinController dc) {
		this.dc = dc;
		FXMLLoader loader = new FXMLLoader(getClass().getResource("SpeelSpelScherm.fxml"));
		loader.setRoot(this);
		loader.setController(this);
		
		try {
            loader.load();
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
        catch(NullPointerException ie) {}
		
		spelGrondController = new SpelGrondController(dc);		
		this.setCenter(spelGrondController);
		lblaantalVerplaatsingen.setText("verplaatsingen: 0");
		lblaantalVerplaatsingen.setText(String.format("%d %s", dc.geefAantalVerplaatsingen(), dc.geefAantalVerplaatsingen()==1? taal.vertaal("verplaatsing"): taal.vertaal("meerdereVerplaatsingen")));
		btnReset.setText(taal.vertaal("resetGUI")); //Tekst om spelbord te resetten
		btnExit.setText(taal.vertaal("exitSpelbordGUI")); // Tekst om spelbordscherm te verlaten
		spelGrondController.setVeldenOpGrond(Richting.LINKS);//hier wordt de initiele richting doorgegeven omdat methode Richting ontvangt; moet niet spcifiek LINKS zijn.
		
		
		//code om de pijltijes te kunnen gebruiken voor verplaatsen van werkman
		this.addEventFilter(KeyEvent.KEY_PRESSED, keyEvent -> {
			
           if(keyEvent.getCode() == KeyCode.UP) {
        	   verplaats(Richting.BOVEN);
           }
           if(keyEvent.getCode() == KeyCode.DOWN) {
        	   verplaats(Richting.BENEDEN);
           }
           if(keyEvent.getCode() == KeyCode.LEFT) {
        	   verplaats(Richting.LINKS);
           }
           if(keyEvent.getCode() == KeyCode.RIGHT) {
        	   verplaats(Richting.RECHTS);
           }
        });	
		
     }
	
	
	// Event Listener on Button[#btnReset].onAction
	@FXML
	public void resetSpelbord(ActionEvent event) {
		dc.resetSpelbord();
		spelGrondController.setVeldenOpGrond(Richting.LINKS);
	}
	
	// Event Listener on Button[#btnExit].onAction
	@FXML
	public void verlaatSpelbord(ActionEvent event) {		
	    	Scene scene = new Scene(new KiesSpelSchermController(dc), 800, 800);
			Stage stage = (Stage) this.getScene().getWindow();
			stage.setScene(scene);
			stage.show();		    
	}
	
	
	//man verplaatsen met pijltjes op scherm rechts
	@FXML
    void naarBeneden(ActionEvent event) {
		verplaats(Richting.BENEDEN);
    }

    @FXML
    void naarBoven(ActionEvent event) {
    	verplaats(Richting.BOVEN);
    }

    @FXML
    void naarLinks(ActionEvent event) {
    	verplaats(Richting.LINKS);
    }

    @FXML
    void naarRechts(ActionEvent event) {
    	verplaats(Richting.RECHTS);
    }
    
    private void verplaats(Richting richting) {
    	try {
			dc.verplaatsWerkman(richting); //werkman wordt verplaatst in echte DomeinCOntroller
			aantalVerplaatsingen = dc.geefAantalVerplaatsingen();
			lblaantalVerplaatsingen.setText(String.format("%d %s", aantalVerplaatsingen, aantalVerplaatsingen==1? taal.vertaal("verplaatsing"): taal.vertaal("meerdereVerplaatsingen")));
			spelGrondController.getChildren().clear(); //spelgrond wordt opgekuist
			spelGrondController.setVeldenOpGrond(richting); //beeldjes van spelgrond worden opniew getoond 
			if(dc.isSpelbordVoltooid()) { //als spelbord voltooid is wordt dan de spelbordvoltooid scherm getoond
				this.setCenter(new SpelbordVoltooidSchermController(dc, aantalVerplaatsingen));
				btnVolgende.setDisable(false); //button NEXT wordt actief
				btnReset.setDisable(true); //reset wordt niet meer actief na het spelbord voltooid is
				lblaantalVerplaatsingen.setVisible(false);
				
			}
		} catch (TegenMuurException e) { //als exception word opgevange, moet niet gebeuren; mag wel;
			// gebeurt niets
		} catch (IndexOutOfBoundsException e) { //daar wordt een indexoutofb.. gegooid als geen spelborden meer zitten in spelbord
			Scene scene = new Scene(new SpelVoltooidSchermController(dc, aantalVerplaatsingen), 800, 800);
			Stage stage = (Stage) this.getScene().getWindow();
			stage.setScene(scene);
			stage.show();
		}
    }
    
	// Event Listener on Button[#btnVolgende].onAction
    @FXML
	 void naarDeVolgendeSpelbord(ActionEvent event) { //volgende spelbord wordt getoond
    	spelGrondController = new SpelGrondController(dc);		
		this.setCenter(spelGrondController);
		spelGrondController.setVeldenOpGrond(Richting.LINKS); //hier wordt de initiele richting doorgegeven omdat methode Richting ontvangt; moet niet spcifiek LINKS zijn.
		btnVolgende.setDisable(true); // button NEXT wordt opnieuw innactive
		btnReset.setDisable(false); //knopje reset wordt terug actief
		lblaantalVerplaatsingen.setVisible(true);
		lblaantalVerplaatsingen.setText(String.format("%s", taal.vertaal("0verplaatsingen")));
	 }
}
