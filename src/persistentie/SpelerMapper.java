package persistentie;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import domein.Speler;

public class SpelerMapper {
	
	 private static final String INSERT_SPELER = "INSERT INTO ID222177_g86.Speler (gebruikersnaam, wachtwoord, adminrechten, naam, voornaam)" + "VALUES (?, ?, ?, ?, ?)";
	 private static final String GEEF_ALLE_SPELERS = "SELECT * FROM ID222177_g86.Speler";
	 private static final String GEEF_SPELER = "SELECT * FROM ID222177_g86.Speler WHERE gebruikersnaam = ?";
	
	 public void voegToe(Speler speler) {
	        try (
	            Connection conn = DriverManager.getConnection(Connectie.JBDC_URL);
	            PreparedStatement query = conn.prepareStatement(INSERT_SPELER)) {
	            query.setString(1, speler.getGebruikersnaam());
	            query.setString(2, speler.getWachtwoord());
	            query.setBoolean(3, speler.getAdminrechten());  
	            query.setString(4, speler.getNaam());
	            query.setString(5, speler.getVoornaam());
	            query.executeUpdate();
	        } catch (SQLException ex) {
	            throw new RuntimeException(ex);
	        }
	  }
	
	 public List<Speler> geefSpelers() {
	        List<Speler> spelers = new ArrayList<>();
	        try (Connection conn = DriverManager.getConnection(Connectie.JBDC_URL);
	                PreparedStatement query = conn.prepareStatement(GEEF_ALLE_SPELERS);
	                ResultSet rs = query.executeQuery()) {
	            while (rs.next()) {
	                String naam = rs.getString("naam");
	                String voornaam = rs.getString("voornaam");
	                String wachtwoord = rs.getString("wachtwoord");
	                String gebruikersnaam = rs.getString("wachtwoord");
	                boolean adminrechten = rs.getBoolean("adminrechten");
	               
	                spelers.add(new Speler(gebruikersnaam, wachtwoord, adminrechten, naam, voornaam));
	            }
	        } catch (SQLException ex) {
	            throw new RuntimeException(ex);
	        }
	        return spelers;
	    }
	
	public Speler zoekSpeler(String gebruikersnaam) {
        Speler speler = null;
        
        
        try (Connection conn = DriverManager.getConnection(Connectie.JBDC_URL);
            PreparedStatement query = conn.prepareStatement(GEEF_SPELER)) {
            query.setString(1, gebruikersnaam);
            try (ResultSet rs = query.executeQuery()) {
                if (rs.next()) {
                	String naam = rs.getString("naam");
	                String voornaam = rs.getString("voornaam");
	                String wachtwoord = rs.getString("wachtwoord");         
	                boolean adminrechten = rs.getBoolean("adminrechten");
                    speler = new Speler(gebruikersnaam, wachtwoord, adminrechten, naam, voornaam);
                }
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        return speler; 
	}

}