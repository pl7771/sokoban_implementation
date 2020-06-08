package main;


import domein.DomeinController;
import gui.TaalSchermController;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;


public class StartUpGUI extends Application
{
    @Override
    public void start(Stage stage)
    {
    	DomeinController controller = new DomeinController();
        Scene scene = new Scene(new TaalSchermController(controller), 800, 800);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.getIcons().add(new Image(getClass().getResourceAsStream("/images/logoG86.jpg")));
        stage.show();
    }

    public static void main(String... args)
    {
        Application.launch(StartUpGUI.class, args);
    }
}