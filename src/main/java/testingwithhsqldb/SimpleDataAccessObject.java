package testingwithhsqldb;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sql.DataSource;

public class SimpleDataAccessObject {
	private final DataSource myDataSource;
	
	public SimpleDataAccessObject(DataSource dataSource) {
		myDataSource = dataSource;
	}

	/**
	 * Renvoie le nom d'un client à partir de son ID
	 * @param id la clé du client à chercher
	 * @return le nom du client (LastName) ou null si pas trouvé
	 * @throws SQLException 
	 */
	public String nameOfCustomer(int id) throws SQLException {
		String result = null;
		
		String sql = "SELECT LastName FROM Customer WHERE ID = ?";
		try (Connection myConnection = myDataSource.getConnection(); 
		     PreparedStatement statement = myConnection.prepareStatement(sql)) {
			statement.setInt(1, id); // On fixe le 1° paramètre de la requête
			try ( ResultSet resultSet = statement.executeQuery()) {
				if (resultSet.next()) {
					// est-ce qu'il y a un résultat ? (pas besoin de "while", 
                                        // il y a au plus un enregistrement)
					// On récupère les champs de l'enregistrement courant
					result = resultSet.getString("LastName");
				}
			}
		}
		// dernière ligne : on renvoie le résultat
		return result;
	}
        
        public void insertProduct(int ID, String Name,int Price) throws SQLException{
            String sql = "INSERT INTO Product VALUES (?,?,?)";
		try (   Connection connection = myDataSource.getConnection();
			PreparedStatement stmt = connection.prepareStatement(sql)
                ) {
                        // Définir la valeur du paramètre
			stmt.setInt(1, ID);
                        stmt.setString(2, Name);
                        stmt.setInt(3, Price);
			
			stmt.executeUpdate();

		}
		}
        
        public int numberOfProducts() throws SQLException {
		int result = 0;

		String sql = "SELECT COUNT(*) AS NUMBER FROM PRODUCT";
		// Syntaxe "try with resources" 
		// cf. https://stackoverflow.com/questions/22671697/try-try-with-resources-and-connection-statement-and-resultset-closing
		try (Connection connection = myDataSource.getConnection(); // Ouvrir une connexion
			Statement stmt = connection.createStatement(); // On crée un statement pour exécuter une requête
			ResultSet rs = stmt.executeQuery(sql) // Un ResultSet pour parcourir les enregistrements du résultat
			) {
			if (rs.next()) { // Pas la peine de faire while, il y a 1 seul enregistrement
				// On récupère le champ NUMBER de l'enregistrement courant
				result = rs.getInt("NUMBER");
			}
		} 
		

		return result;
	
        }
        
        ProductEntity findCustomer(int customerID) throws SQLException {
                String sql = "SELECT NAME AS NOM,PRICE AS Price FROM PRODUCT WHERE ID = ? ";
                String name = "";
                int prix=0;
                try (Connection connection = myDataSource.getConnection(); 
			PreparedStatement stmt = connection.prepareStatement(sql); 
			) {
                         stmt.setInt(1, customerID);
                        try {
                            ResultSet rs = stmt.executeQuery();
                            if (rs.next()) {
				name = rs.getString("NOM");
                                prix=rs.getInt("Price");
                                
                            }
                        }  catch (SQLException ex) { 
                            Logger.getLogger(SimpleDataAccessObject.class.getName()).log(Level.SEVERE, null, ex);
		}   
		} catch (SQLException ex) { 
                            Logger.getLogger(SimpleDataAccessObject.class.getName()).log(Level.SEVERE, null, ex);
		}
                
                
                ProductEntity customer = new ProductEntity(customerID,name,prix);

            return customer;
	}
        
}
	

