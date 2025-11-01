import java.sql.*;

public class Database {
    // connetti
    // query

    // sqlite -> 1 solo file
    // Java Data Base Connectivity
    // API -> HTTP

    private Connection connection;

    public Database() throws SQLException {
        // non propagare di troppo le eccezioni
        String url = "jdbc:sqlite:database/sushi.db"; // se sqlite non trova il db, crea il file
        connection = DriverManager.getConnection(url);
        System.out.println("Connected to database");
    }

    public String selectAll() {
        String result = "";
        try {
            if(connection == null || !connection.isValid(5)){
                System.err.println("Database not connected");
                return null;
            }
        } catch (SQLException e) {
            System.out.println("Error connecting to database");
            return null;
        }

        String query = "select * from menu";

        try {
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet piatti = statement.executeQuery();
            while(piatti.next()){
                result += piatti.getString("id") + "\t";
                result += piatti.getString("piatto")+ "\t";
                result += piatti.getString("prezzo")+ "\t";
                result += piatti.getString("quantita")+ "\n";
            }
        } catch (SQLException e) {
            System.out.println("Errore di query:  " + e.getMessage());
            return null;
        }
        return result;
    }

    public boolean insert(String nomePiatto, float prezzo, int quantita) {
        try {
            if(connection == null || !connection.isValid(5)){
                System.err.println("Database not connected");
                return false;
            }
        } catch (SQLException e) {
            System.out.println("Error connecting to database");
            return false;
        }

        String query = "INSERT into menu(piatto, prezzo, quantita) VALUES(?,?,?)";

        try {
            PreparedStatement stnt= connection.prepareStatement(query);

            stnt.setString(1, nomePiatto); // se provo un injection, ci sarà qualcosa che mi dirà che tutto è una stringa unica
            stnt.setFloat(2, prezzo);
            stnt.setInt(3, quantita);

            stnt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Errore di query:  " + e.getMessage());
            return false;
        }
        return true;
    }

    public boolean update(String nomePiatto, float prezzo, int quantita){
        try {
            if(connection == null || !connection.isValid(5)){
                System.err.println("Database not connected");
                return false;
            }
        } catch (SQLException e) {
            System.out.println("Error connecting to database");
            return false;
        }

        String query = "UPDATE menu SET prezzo=?,quantita=? WHERE piatto=?";

        try {
            PreparedStatement stnt= connection.prepareStatement(query);
            
            stnt.setFloat(1, prezzo);
            stnt.setInt(2, quantita);
            stnt.setString(3, piatto);

            stnt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Errore di query:  " + e.getMessage());
            return false;
        }
        return true;
    }

    public boolean delete(String nomePiatto){
        try {
            if(connection == null || !connection.isValid(5)){
                System.err.println("Database not connected");
                return false;
            }
        } catch (SQLException e) {
            System.out.println("Error connecting to database");
            return false;
        }

        String query = "DELETE from menu WHERE piatto=?";

        try {
            PreparedStatement stnt= connection.prepareStatement(query);
            
            stnt.setString(1, nomePiatto);

            stnt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Errore di query:  " + e.getMessage());
            return false;
        }
        return true;
    }

    public boolean print(){
        try {
            if(connection == null || !connection.isValid(5)){
                System.err.println("Database not connected");
                return false;
            }
        } catch (SQLException e) {
            System.out.println("Error connecting to database");
            return false;
        }

        String query = "SELECT piatto, prezzo, quantita FROM menu";

        try {
            PreparedStatement stnt= connection.prepareStatement(query);
            rs=stnt.executeQuery(query);
			while(rs.next()) {
                nomePiatto=rs.getString("PIATTO");
                prezzo=rs.getFloat("PREZZO");
				quantita=rs.getInt("QUANTITA");
				
 
				System.out.print(piatto+" ");
				System.out.print(prezzo+" ");
				System.out.println(quantita+" ");
 
			}
        } catch (SQLException e) {
            System.err.println("Errore di query:  " + e.getMessage());
            return false;
        }
        return true;
    }
}
