package gui;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import java.io.IOException;
import domein.DomeinController;
import domein.VeldType;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import util.Taal;

public class MaakNieuwSpelbordController extends BorderPane{
	@FXML
	private ImageView imgKist;
	@FXML
	private ImageView imgMuur;
	@FXML
	private ImageView imgDoel;
	@FXML
	private ImageView imgWerkman;
	@FXML
	private Button btnValideer;
	@FXML
	private ImageView imgLeegVeld;
	@FXML
	private Button btnSlaSpelbordOp;
	@FXML
	private Button btnVolgendeSpelbord;
	@FXML
	private Button btnSpelOpslaan;
	@FXML
	private Button btnAnnulleer;
	private int x;
	private int y;
	
	private DomeinController dc;
	private Taal taal = Taal.getInstance();
	private MaakNieuwSpelgrondController nieuwSpelgrondController;
	private VeldType type;
	
	public MaakNieuwSpelbordController(DomeinController dc) {
		this.dc = dc;

		FXMLLoader loader = new FXMLLoader(getClass().getResource("MaakNieuwSpelbord.fxml"));
        loader.setRoot(this);
        loader.setController(this);
        try {
            loader.load();
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
        catch(NullPointerException ie){
        }
        
        btnAnnulleer.setText(taal.vertaal("anulleerGui"));
        btnValideer.setText(taal.vertaal("valideer"));
        btnSlaSpelbordOp.setText(taal.vertaal("spelbordopslaan"));
        btnVolgendeSpelbord.setText(taal.vertaal("volgendespelbord"));
        btnSpelOpslaan.setText(taal.vertaal("spelopslaan"));
        btnSlaSpelbordOp.setDisable(true);
        btnVolgendeSpelbord.setDisable(true);
        btnSpelOpslaan.setDisable(true);
        nieuwSpelgrondController = new MaakNieuwSpelgrondController(dc);		
		this.setCenter(nieuwSpelgrondController);
		nieuwSpelgrondController.setVeldenOpGrond();
		if(type == null) {
        	type = VeldType.LEEGVELD;
        }
		
			nieuwSpelgrondController.addEventFilter(MouseEvent.MOUSE_PRESSED, new EventHandler<MouseEvent>() {
			    @Override
			    public void handle(MouseEvent e) {

			        for( Node node: nieuwSpelgrondController.getChildren()) {

			            if( node instanceof ImageView) {
			                if( node.getBoundsInParent().contains(e.getSceneX(),  e.getSceneY())) {
			                	x = GridPane.getRowIndex(node);
			                	y = GridPane.getColumnIndex(node);
			                    System.out.println(x + " " + y);			                    
			                }
			            }
			        }
                    setSelectedVeldOpGegevenCoordinaden(y,x,type);
			    }				
			});	
	}
	

	// Event Listener on ImageView[#imgKist].onMouseClicked
	@FXML
	public void kistSelected(MouseEvent event) {			
		type = VeldType.KIST;
		setScaleGroter(imgKist, 1.3);
		setScaleKleiner(imgMuur, imgDoel, imgWerkman, imgLeegVeld);
	}
	// Event Listener on ImageView[#imgMuur].onMouseClicked
	@FXML
	public void muurSelected(MouseEvent event) {
		type = VeldType.MUUR;
		setScaleGroter(imgMuur, 1.3);
		setScaleKleiner(imgKist, imgDoel, imgWerkman, imgLeegVeld);
	}
	// Event Listener on ImageView[#imgDoel].onMouseClicked
	@FXML
	public void doelSelected(MouseEvent event) {
		type = VeldType.DOEL;
		setScaleGroter(imgDoel, 1.3);
		setScaleKleiner(imgKist, imgMuur, imgWerkman, imgLeegVeld);
	}
	// Event Listener on ImageView[#imgWerkman].onMouseClicked
	@FXML
	public void werkmanSelected(MouseEvent event) {
		type = VeldType.WERKMAN;
		setScaleGroter(imgWerkman, 1.3);
		setScaleKleiner(imgKist, imgMuur, imgDoel, imgLeegVeld);
	}
	@FXML
    void onLeegVeldClicked(MouseEvent event) {
		type = VeldType.LEEGVELD;
		setScaleGroter(imgLeegVeld, 1.3);
		setScaleKleiner(imgKist, imgMuur, imgDoel, imgWerkman);
    }
	// Event Listener on Button[#btnValideer].onAction
	@FXML
	public void valideerSpelbord(ActionEvent event) {
		if(dc.valideerSpelbord()) {			
			btnSlaSpelbordOp.setDisable(false);
			btnSpelOpslaan.setDisable(false);

			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle(taal.vertaal("spelbordIsGevalideerd"));
			alert.setContentText(taal.vertaal("volgendSpelbordOfOpslaan"));
			alert.showAndWait();
		}
		else {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle(taal.vertaal("validationFail"));
			alert.setContentText(taal.vertaal("probeerOpnieuw"));
			alert.showAndWait();
		}
	}
	// Event Listener on Button[#btnSlaSpelbordOp].onAction
	@FXML
	public void slaSpelbordOp(ActionEvent event) {
		btnVolgendeSpelbord.setDisable(false);
		dc.registreerSpelbord();
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("");
		alert.setContentText(taal.vertaal("spelOpgeslagen"));//("Spel is opgeslagen");
		alert.showAndWait();
		btnSlaSpelbordOp.setDisable(true);
	}
	// Event Listener on Button[#btnVolgendeSpelbord].onAction
	@FXML
	public void maakVolgendeSpelbord(ActionEvent event) {
		dc.maakLeegSpelbordAan();
		nieuwSpelgrondController.setVeldenOpGrond();
		btnSlaSpelbordOp.setDisable(false);
		btnSpelOpslaan.setDisable(false);
		btnVolgendeSpelbord.setDisable(false);
	}
	// Event Listener on Button[#btnSpelOpslaan].onAction
	@FXML
	public void spelOpslaan(ActionEvent event) {
		dc.registreerSpel();
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("");
		alert.setContentText(taal.vertaal("spelOpgeslagenTerugNaarMenu"));//("Spel is opgeslagen, terug naar menu");
		alert.showAndWait();
		Scene scene = new Scene(new AdminRechtenMenuController(dc), 800, 800);
		Stage stage = (Stage) this.getScene().getWindow();
		stage.setScene(scene);
		stage.show();
	}
	// Event Listener on Button[#btnAnnulleer].onAction
	
	@FXML
	public void annulleer(ActionEvent event) {
		Scene scene = new Scene(new MaakNieuwSpelController(dc), 800, 800);
		Stage stage = (Stage) this.getScene().getWindow();
		stage.setScene(scene);
		stage.show();	
	}
	
	
	public void setScaleGroter(ImageView image, double scale) {
		image.setScaleX(scale);
		image.setScaleY(scale);
		System.out.println(type.toString());
	}
	
	public void setScaleKleiner(ImageView image1,ImageView image2,ImageView image3,ImageView image4) {
		image1.setScaleX(1.0);
		image1.setScaleY(1.0);
		image2.setScaleX(1.0);
		image2.setScaleY(1.0);
		image3.setScaleX(1.0);
		image3.setScaleY(1.0);
		image4.setScaleX(1.0);
		image4.setScaleY(1.0);
	}
	
	private void setSelectedVeldOpGegevenCoordinaden(int y, int x, VeldType type) {
		dc.wijzigSpelbord(y, x, type);
		nieuwSpelgrondController.getChildren().clear();
		nieuwSpelgrondController.setVeldenOpGrond();
	}
	
}
