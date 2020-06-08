package gui;

import java.io.IOException;

import domein.DomeinController;
import domein.Richting;
import domein.Spelbord;
import domein.Veld;
import javafx.fxml.FXMLLoader;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import static gui.ImageFactory.SoortImage.*; 

public class SpelGrondController extends GridPane {

	private DomeinController dc;
	private char[][] spelbord; 
	private ImageView image;
	
	
	
	public SpelGrondController(DomeinController dc) {
		this.dc = dc;
		FXMLLoader loader = new FXMLLoader(getClass().getResource("SpelGrond.fxml"));
		loader.setRoot(this);
		loader.setController(this);
		try {
            loader.load();
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
		// this.setStyle("-fx-background-color: green;"); //groen achtergrond kleur instellen
	}
	
	
	//Methode steld de afbeeldingen voor elke veld afhankelijk van de VeldType
	public void setVeldenOpGrond(Richting richting) {
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

				if(spelbord[i][j] == 'o') {
					image =  new ImageView(ImageFactory.createImage(KIST_DOEL)); 
					this.add(image, j,i);
				}
				if((spelbord[i][j] == '@' )) {
					imageVoorWerkman(richting, j, i);
				}	
				if((spelbord[i][j] == ' ')) {
					image =  new ImageView(ImageFactory.createImage(LEEGVELD)); 
					this.add(image, j, i);
				}	
				if((spelbord[i][j] == '&')) {
					image =  new ImageView(ImageFactory.createImage(WERKMAN_DOEL)); 
					this.add(image, j, i);
				}	
			}
		}
	}	
	
	
	//Methode past afbeeldingen van werkMan aan naargelang Richtin,
	//er mocht natuurlijk een niewe variabele gebruikt worden, maar toch gekozen voor Richting
	private void imageVoorWerkman(Richting richting, int j, int i) { 
		String kantVanWerkman = richting.getRichting();
		switch(kantVanWerkman) {
			case "links": {
				image = new ImageView(ImageFactory.createImage(WERKMAN_LINKS)); 
				this.add(image, j,i);
				break;
			}
			case "rechts": {
				image = new ImageView(ImageFactory.createImage(WERKMAN_RECHTS)); 
				this.add(image, j,i);
				break;
			}
			case "boven": {
				image = new ImageView(ImageFactory.createImage(WERKMAN_BOVEN)); 
				this.add(image, j,i);
				break;
			}
			case "beneden": {
				image = new ImageView(ImageFactory.createImage(WERKMAN_BENEDEN)); 
				this.add(image, j,i);
				break;
			}
		}
	}
	
}
