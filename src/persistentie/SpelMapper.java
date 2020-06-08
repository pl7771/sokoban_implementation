package persistentie;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import domein.Spel;
import domein.Speler;
import persistentie.SpelbordMapper;

public class SpelMapper {
	 private static final String INSERT_SPEL = "INSERT INTO ID222177_g86.Spel (naamSpel, gebruikersnaam)" + "VALUES (?, ?)";
	 private static final String GEEF_ALLE_SPELLEN = "SELECT * FROM ID222177_g86.Spel";
	 private static final String GEEF_SPEL = "SELECT * FROM ID222177_g86.Spel WHERE naamSpel = ?"; 
	 
	 public void voegToe(Spel spel, Speler speler) {
	        try (
	            Connection conn = DriverManager.getConnection(Connectie.JBDC_URL);
	            PreparedStatement query = conn.prepareStatement(INSERT_SPEL)) {
	            query.setString(1, spel.getNaamSpel());
	            query.setString(2, speler.getGebruikersnaam());
	            query.executeUpdate();

		        new SpelbordMapper().voegSpelbordenToe(this.geefIDvanEenSpel(spel.getNaamSpel()), spel.getLijstVanSpelborden());
	            
	        } catch (SQLException ex) {
	            throw new RuntimeException(ex);
	        }
	  }
	
		public List<String> geefNamenBestaandeSpellen() {
			
	        List<String> namenSpelen = new ArrayList<>();
	        try (Connection conn = DriverManager.getConnection(Connectie.JBDC_URL);
	                PreparedStatement query = conn.prepareStatement(GEEF_ALLE_SPELLEN);
	                ResultSet rs = query.executeQuery()) {
	            while (rs.next()) {
	                String naam = rs.getString("naamSpel");
	                namenSpelen.add(naam);
	            }
	        } catch (SQLException ex) {
	            throw new RuntimeException(ex);
	        }
	        return namenSpelen;
	    }
		
		public Spel zoekSpel(String naamSpel) {
	        Spel spel = null;
	        List<String> lijstVanSpelborden = new ArrayList<>();
	        lijstVanSpelborden = new SpelbordMapper().geefLijstVanSpelborden(naamSpel);
	        
	        try (Connection conn = DriverManager.getConnection(Connectie.JBDC_URL);
	            PreparedStatement query = conn.prepareStatement(GEEF_SPEL)) {
	            query.setString(1, naamSpel);
	            try (ResultSet rs = query.executeQuery()) {
	                if (rs.next()) {	          
	                    spel = new Spel(naamSpel, lijstVanSpelborden);
	                }
	            }
	        } catch (SQLException ex) {
	            throw new RuntimeException(ex);
	        }
	        return spel; 
		}
		
		public String geefIDvanEenSpel(String naamSpel) {	      
	        String id = null;
	        try (Connection conn = DriverManager.getConnection(Connectie.JBDC_URL);
	            PreparedStatement query = conn.prepareStatement(GEEF_SPEL)) {
	            query.setString(1, naamSpel);
	            try (ResultSet rs = query.executeQuery()) {
	            	while (rs.next()) {
	                	id = rs.getString("spelID");
	                }
	            }
	        } catch (SQLException ex) {
	            throw new RuntimeException(ex);
	        }
	        return id; 
		}
	

}
