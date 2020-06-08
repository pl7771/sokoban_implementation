package gui;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import java.io.IOException;
import java.util.Arrays;

import domein.DomeinController;
import domein.Richting;
import domein.Spelbord;
import domein.Veld;
import domein.VeldType;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import static gui.ImageFactory.SoortImage.*; 

public class MaakNieuwSpelgrondController extends GridPane {

	private DomeinController dc;
	private char[][] spelbord; 
	private ImageView image;
		
	public MaakNieuwSpelgrondController(DomeinController dc) {
		this.dc = dc;
		FXMLLoader loader = new FXMLLoader(getClass().getResource("SpelGrond.fxml"));
		loader.setRoot(this);
		loader.setController(this);
		try {
            loader.load();
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
		//this.setStyle("-fx-background-color: green;"); //groen achtergrond kleur instellen
		this.setVgap(10);
		this.setHgap(10);	
	}
		
	//Methode steld de afbeeldingen voor elke veld afhankelijk van de VeldType
	public void setVeldenOpGrond() {
		spelbord = dc.geefSpelbord();
		for(int i = 0; i < spelbord.length; i++) {
			for(int j = 0; j < spelbord.length; j++) {
				if(spelbord[i][j] == '#') {
					image = new ImageView(ImageFactory.createImage(MUUR)); 
					this.add(image, j,i);
				}
				if(spelbord[i][j] == '0') {
					image =  new ImageView(ImageFactory.createImage(KIST)); 
					this.add(image, j,i);
				}
				if(spelbord[i][j] == 'x') {
					image =  new ImageView(ImageFactory.createImage(DOEL)); 
					this.add(image, j,i);
				}
				if((spelbord[i][j] == '@')) {
					image = new ImageView(ImageFactory.createImage(WERKMAN_LINKS)); 
					this.add(image, j,i);
				}		
				if((spelbord[i][j] == ' ')) {
					image = new ImageView(ImageFactory.createImage(LEEGVELD)); 
					this.add(image, j,i);
				}	
			}
		}
	}	

}