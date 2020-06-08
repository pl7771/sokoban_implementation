module sokoban_g86 {
	
	requires javafx.base;
	requires javafx.graphics;
	requires javafx.controls;
	requires javafx.fxml;
	requires java.sql;
	
	
	opens gui to javafx.graphics,javafx.fxml; 
	opens main to javafx.graphics,javafx.fxml; 
}