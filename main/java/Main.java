import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.time.LocalDate;

public class Main {

    private static String URL = "jdbc:postgresql://localhost:5432/postgres";
    private static String USER = "postgres";
    private static String PASSWORD = "root";
    private static Connection connection = null;

    public static void main(String[] args) throws SQLException {
        try {
            Class.forName("org.postgresql.Driver");
            try {
                connection = DriverManager.getConnection(URL, USER, PASSWORD); // stabiliamo la connessione al server
                Piattaforma piattaforma = new Piattaforma();

                /* variabili per i test

                Utente utente = new Utente("niki", "pera", "indirizzoblabla", "nuovamail@nonloso.it", "banana");
                Sede sede = new Sede("NomeSede", "indirizzoSala", false, "Roma");
                Sala sala = new Sala("nomeSala1", sede);
                Spettacolo spettacolo = new Spettacolo("Fantasy", 120, 1, LocalDate.of(2021, 12, 28), sala);
                Prenotazione prenotazione = new Prenotazione(utente, spettacolo, 'A', 4);
                */

                /* metodi per i test

                piattaforma.effettuaLogin(connection, "luigineri@mail.it", "blabla");
                piattaforma.prenota(connection, prenotazione);
                */
                piattaforma.menu(connection);

            } catch (SQLException sqlE) {
                System.out.println("Impossibile connettersi al DB: " + sqlE.getMessage());
            }
        } catch (ClassNotFoundException e) {
            System.out.println("Impossibile trovare il driver JDBC: " + e.getMessage());
        } finally {
            try {
                if (connection != null)
                    connection.close();
            } catch (SQLException sqlE) {
                System.out.println("Impossibile chiudere il DB: " + sqlE.getMessage());
            }
        }
    }

}
