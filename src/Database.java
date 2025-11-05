import java.sql.*;

// singleton fa uso di static (tutte le istanze di un ogg userano la stessa)
public class Database {
    private Connection connection;
    private static Database instance = null; // tutte le istanze di lui, useranno questo oggetto statico

    private Database() throws SQLException {
        String url = "jdbc:sqlite:database/sushi.db"; // crea il file se non esiste
        connection = DriverManager.getConnection(url);
        System.out.println("Connected to database");
    }

    public static Database getIstance() throws SQLException {
        if (instance == null)
            instance = new Database();
        return instance;
    }


    // --- SELECT ALL ---
    public String selectAll() {
        StringBuilder result = new StringBuilder();
        String query = "SELECT * FROM menu";

        try {
            if (connection == null || !connection.isValid(5)) {
                System.err.println("Database not connected");
                return null;
            }

            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet piatti = statement.executeQuery();

            while (piatti.next()) {
                result.append(piatti.getInt("id")).append("\t")
                        .append(piatti.getString("piatto")).append("\t")
                        .append(piatti.getFloat("prezzo")).append("\t")
                        .append(piatti.getInt("quantita")).append("\n");
            }

        } catch (SQLException e) {
            System.err.println("Errore di query: " + e.getMessage());
            return null;
        }

        return result.toString();
    }

    // --- INSERT ---
    public boolean insert(String nomePiatto, float prezzo, int quantita) {
        String query = "INSERT INTO menu (piatto, prezzo, quantita) VALUES (?, ?, ?)";

        try {
            if (connection == null || !connection.isValid(5)) {
                System.err.println("Database not connected");
                return false;
            }

            PreparedStatement stnt = connection.prepareStatement(query);
            stnt.setString(1, nomePiatto);
            stnt.setFloat(2, prezzo);
            stnt.setInt(3, quantita);
            stnt.executeUpdate();

        } catch (SQLException e) {
            System.err.println("Errore di query: " + e.getMessage());
            return false;
        }
        return true;
    }

    // --- UPDATE ---
    public boolean update(String nomePiatto, float prezzo, int quantita) {
        String query = "UPDATE menu SET prezzo = ?, quantita = ? WHERE piatto = ?";

        try {
            if (connection == null || !connection.isValid(5)) {
                System.err.println("Database not connected");
                return false;
            }

            PreparedStatement stnt = connection.prepareStatement(query);
            stnt.setFloat(1, prezzo);
            stnt.setInt(2, quantita);
            stnt.setString(3, nomePiatto);
            stnt.executeUpdate();

        } catch (SQLException e) {
            System.err.println("Errore di query: " + e.getMessage());
            return false;
        }
        return true;
    }

    // --- DELETE ---
    public boolean delete(String nomePiatto) {
        String query = "DELETE FROM menu WHERE piatto = ?";

        try {
            if (connection == null || !connection.isValid(5)) {
                System.err.println("Database not connected");
                return false;
            }

            PreparedStatement stnt = connection.prepareStatement(query);
            stnt.setString(1, nomePiatto);
            stnt.executeUpdate();

        } catch (SQLException e) {
            System.err.println("Errore di query: " + e.getMessage());
            return false;
        }
        return true;
    }

    // --- PRINT (fissato) ---
    public boolean print() {
        String query = "SELECT piatto, prezzo, quantita FROM menu";

        try {
            if (connection == null || !connection.isValid(5)) {
                System.err.println("Database not connected");
                return false;
            }

            PreparedStatement stnt = connection.prepareStatement(query);
            ResultSet rs = stnt.executeQuery();

            System.out.println("=== MENU ===");
            while (rs.next()) {
                String piatto = rs.getString("piatto");
                float prezzo = rs.getFloat("prezzo");
                int quantita = rs.getInt("quantita");

                System.out.printf("%-15s  €%.2f  x%d%n", piatto, prezzo, quantita);
            }

        } catch (SQLException e) {
            System.err.println("Errore di query: " + e.getMessage());
            return false;
        }
        return true;
    }
}
