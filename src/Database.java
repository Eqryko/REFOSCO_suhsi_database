import java.sql.*;

public class Database {
    // connetti
    // query

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
                result += piatti.getString("id");
                result += piatti.getString("piatto");
                result += piatti.getString("prezzo");
                result += piatti.getString("quantita");
            }
        } catch (SQLException e) {
            System.out.println("Errore di query:  " + e.getMessage());
            return null;
        }
        return result;
    }
}
