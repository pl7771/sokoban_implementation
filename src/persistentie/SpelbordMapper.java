package persistentie;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SpelbordMapper {
	
	private static final String GEEF_MAPPEN_VAN_EEN_SPEL = "SELECT levelMap FROM ID222177_g86.Spelbord sb JOIN ID222177_g86.Spel sp ON sb.SpelID = sp.spelID WHERE sp.naamSpel = ?";
	private static final String INSERT_SPELBORDEN = "INSERT INTO ID222177_g86.Spelbord (levelMap, SpelID)" + "VALUES (?, ?)";
	
		public List<String> geefLijstVanSpelborden(String naamSpel) {
			List<String> mappenVanEenSpel = new ArrayList<>();
	        
	        
	        try (Connection conn = DriverManager.getConnection(Connectie.JBDC_URL);
	            PreparedStatement query = conn.prepareStatement(GEEF_MAPPEN_VAN_EEN_SPEL)) {
	            query.setString(1, naamSpel);
	            try (ResultSet rs = query.executeQuery()) {
	                while (rs.next()) {
	                	String map = rs.getString("levelMap");
	                    mappenVanEenSpel.add(map);
	                }
	            }
	        } catch (SQLException ex) {
	            throw new RuntimeException(ex);
	        }
	        return mappenVanEenSpel; 
		}
		
		public void voegSpelbordenToe(String spelId, List<String> spelborden) {
			 try (
			            Connection conn = DriverManager.getConnection(Connectie.JBDC_URL);
			            PreparedStatement query = conn.prepareStatement(INSERT_SPELBORDEN)) {
				 
				 
			            for (int i = 0; i < spelborden.size(); i++) {
			            	query.setString(1, spelborden.get(i).replaceAll("\n", ""));
			            	query.setString(2, spelId);
							query.executeUpdate();
						}

				       
			            
			        } catch (SQLException ex) {
			            throw new RuntimeException(ex);
			        }
		}
		 

}
