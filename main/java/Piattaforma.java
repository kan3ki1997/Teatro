import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Piattaforma {

    // bug: oggetto sala dentro il metodo scegli spettacolo, disponibilità posto
    // fix opzionali: non suggerire spettacoli già visti

    private Scanner sc = new Scanner(System.in);
    private boolean login;
    private String QRY; // query
    private PreparedStatement statement;
    private ResultSet resultSet;
    private Utente utenteLoggato;
    private Spettacolo spettacoloScelto;

    // costruttori

    public Piattaforma() throws SQLException {
        login = false;
        spettacoloScelto = null;
    }

    // metodi

    public void effettuaRegistrazione(Connection connection, Utente utente) throws SQLException {

        QRY = "SELECT mail FROM utente WHERE utente.mail = '" + utente.getMail() + "'";
        statement = connection.prepareStatement(QRY);
        resultSet = statement.executeQuery();

        if (!resultSet.next()) { // non ha trovato l'utente nel DB e lo registra
            statement = connection.prepareStatement("INSERT INTO utente (nome,cognome,indirizzo,mail,password) VALUES " + "('" + utente.getNome() + "','" + utente.getCognome() + "','" + utente.getIndirizzo() + "','" + utente.getMail() + "','" + utente.getPassword() + "')");
            statement.executeQuery();
            System.out.println("Registrazione effettuata con successo");
        } else {
            System.out.println("utente già registrato");
        }

    }

    public void effettuaLogin(Connection connection, String mail, String password) throws SQLException {

        if (!isLogin()) {

            QRY = "SELECT * FROM utente WHERE utente.mail = '" + mail + "' AND utente.password = '" + password + "'";
            statement = connection.prepareStatement(QRY);
            resultSet = statement.executeQuery();

            if (!resultSet.next()) { // non ha trovato l'utente nel DB
                System.out.println("Mail o password errata");

            } else { // effettua il login

                this.utenteLoggato = new Utente(resultSet.getString("nome"), resultSet.getString("cognome"), resultSet.getString("indirizzo"), resultSet.getString("mail"), resultSet.getString("password"));
                setLogin(true);
                System.out.println("login effetuato con successo, benvenuto/a");
            }
        } else System.out.println("Sei già loggato");

    }

    public void scegliSpettacolo(Connection connection, int idSpettacolo) throws SQLException {

        QRY = "SELECT * FROM spettacolo WHERE spettacolo.id = '" + idSpettacolo + "'";
        statement = connection.prepareStatement(QRY);
        resultSet = statement.executeQuery();

        this.spettacoloScelto = new Spettacolo(resultSet.getString("genere"),resultSet.getInt("durata"),resultSet.getInt("id"),LocalDate.parse(resultSet.getString("data")), (Sala) resultSet.getObject("sala"));
    }

    public void prenota(Connection connection, Prenotazione prenotazione) throws SQLException {

        if (login) {

            QRY = "INSERT INTO prenotazione (id,id_spettacolo,fila_posto,numero_posto,nome_sala_posto) VALUES ('" + prenotazione.getIdPrenotazione() + "','" + prenotazione.getSpettacolo().getIdSpettacolo() + "','" + prenotazione.getFilaPosto() + "','" + prenotazione.getNumeroPosto() + "','" + prenotazione.getSpettacolo().getSala().getNome() + "')";
            statement = connection.prepareStatement(QRY);
            resultSet = statement.executeQuery();

        } else System.out.println("Devi effettuare il login per poter fare una prenotazione");
    }

    public void mostraSpettacoliDisponibili(Connection connection, LocalDate data, String genere, String luogo) throws SQLException { // facoltativamente genere e luogo (all aperto o al chiuso)
        QRY = "SELECT * FROM spettacolo WHERE spettacolo.data = '" + data.toString() + "' AND spettacolo.genere = '" + genere + "'";
        statement = connection.prepareStatement(QRY);
        resultSet = statement.executeQuery();

        System.out.println("Ecco gli spettacoli disponibili in : " + data.toString() + " " + genere);

        if (resultSet.next()) {
            while (resultSet.next()) {
                String prezzo = resultSet.getString("prezzo");
                String durata = resultSet.getString("durata");
                String orario = resultSet.getString("orario");
                String id = resultSet.getString("id");

                System.out.println("Genere: " + genere + " - Prezzo: " + prezzo + " - Durata: " + durata + " - Orario : " + orario + " - Id: " + id);
            }
        } else {
            System.out.println("Spiacenti, nessun spettacolo disponibile quel giorno");
        }
    }

    public void mostraSpettacoliDisponibili(Connection connection, LocalDate dataInput) throws SQLException {

        QRY = "SELECT * FROM spettacolo"; // spettacoli sucessivi alla data
        statement = connection.prepareStatement(QRY);
        resultSet = statement.executeQuery();

        System.out.println("Ecco gli spettacoli disponibili oggi o nei peossimi giorni :");

        if (resultSet.next()) {

            while (resultSet.next()) {
                LocalDate dataSpettacolo = LocalDate.parse(resultSet.getString("data"));

                if (dataInput.compareTo(dataSpettacolo) > 0) continue;

                String genere = resultSet.getString("genere");
                String prezzo = resultSet.getString("prezzo");
                String durata = resultSet.getString("durata");
                String orario = resultSet.getString("orario");
                String id = resultSet.getString("id");
                String data = resultSet.getString("data");

                System.out.println("Genere: " + genere + " - Prezzo: " + prezzo + " - Durata: " + durata + " - Orario : " + orario + " - Id: " + id + " - Data : " + data);
            }
        } else {
            System.out.println("Spiacenti, nessun spettacolo disponibile oggi o in programmazione al momento");
        }
    }

    public void riceviSuggerimenti(Connection connection, List<String> generiPreferiti) throws SQLException {

        System.out.println("Spettacoli consigliati:\n");

        for (int i = 0; i < generiPreferiti.size(); i++) {
            QRY = "SELECT * FROM spettacolo WHERE LOWER (spettacolo.genere) = ('" + generiPreferiti.get(i) + "')";
            statement = connection.prepareStatement(QRY);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                String genere = resultSet.getString("genere");
                String prezzo = resultSet.getString("prezzo");
                String durata = resultSet.getString("durata");
                String orario = resultSet.getString("orario");
                String id = resultSet.getString("id");

                System.out.println("Genere :" + genere + " - Prezzo: " + prezzo + " - Durata: " + durata + " - Orario : " + orario + " - Id: " + id);
            }

            if (resultSet == null)
                System.out.println("Nessuno spettacolo da suggerire per " + generiPreferiti.get(i) + "\n");
        }

    }

    public void menu(Connection connection) throws SQLException {
        while (true) {

            System.out.println("Scegli una delle seguenti opzioni,\n" +
                    "- 1 : Registra un nuovo utente.\n" +
                    "- 2 : Mostra spettacoli disponibili.\n" +
                    "- 3 : Effettua il login.\n" +
                    "- Altro pulsante : Spegni.");

            switch (sc.nextInt()) {

                case 1: // registra utente
                    try {
                        System.out.println("Inserisci nome");
                        String nome = sc.next();
                        System.out.println("Inserisci cognome");
                        String cognome = sc.next();
                        System.out.println("Inserisci indirizzo");
                        String indirizzo = sc.next();
                        System.out.println("Inserisci mail");
                        String mail = sc.next();
                        System.out.println("Inserisci password");
                        String pass = sc.next();

                        System.out.println("Dati inseriti: " + nome + " " + cognome + " " + " " + indirizzo + " " + mail + " " + pass);
                        Utente utente = new Utente(nome, cognome, indirizzo, mail, pass);
                        this.effettuaRegistrazione(connection, utente);

                    } catch (Exception e) {
                        System.out.println(e);
                        System.out.println("Parametro non valido, riprova");
                    } finally {
                        continue;
                    }

                case 2: // spettacoli disponibili
                    try {
                        System.out.println("Inserisci Giorno");
                        int giorno = sc.nextInt();
                        System.out.println("Inserisci Mese");
                        int mese = sc.nextInt();
                        System.out.println("Inserisci Anno");
                        int anno = sc.nextInt();

                        this.mostraSpettacoliDisponibili(connection, LocalDate.of(anno, mese, giorno));

                    } catch (Exception e) {
                        System.out.println("Dati non validi, riprova");
                    } finally {
                        continue;
                    }

                case 3: // login
                    try {
                        System.out.println("Inserisci la mail");
                        String mail = sc.next();
                        System.out.println("Inserisci la password");
                        String pass = sc.next();

                        this.effettuaLogin(connection, mail, pass);

                        while (true) { // menu per utenti loggati

                            System.out.println("Scegli una delle seguenti opzioni,\n" +
                                    "- 1 : Mostra suggerimenti.\n" +
                                    "- 2 : Prenota.\n" +
                                    "- Altro pulsante : Effettua il logout");

                            if (sc.nextInt() == 1) { // suggerimenti
                                System.out.println("Inserisci i tuoi genere preferito");

                                ArrayList<String> generiPreferitiUtente = new ArrayList<>();

                                while (true) {
                                    System.out.println("Seleziona \n1 Aggiungei un genere ai tuoi preferiti,\nAltro pulsante se hai finito");
                                    int opzione = sc.nextInt();

                                    if (opzione == 1) {

                                        System.out.println("Inserisci i tuoi genere preferito");
                                        String genere = sc.next();
                                        generiPreferitiUtente.add(genere);
                                        continue;
                                    } else {
                                        System.out.println("OK");
                                        this.riceviSuggerimenti(connection, generiPreferitiUtente);
                                        break;
                                    }
                                }

                            } else if (sc.nextInt() == 2) { // prenota

                                System.out.println("Inserisci id dello spettacolo");
                                int idSpettacolo = sc.nextInt();
                                scegliSpettacolo(connection, idSpettacolo);

                                System.out.println("Inserisci fila posto es. 'A', 'B' ecc..");
                                char filaPosto = sc.next().charAt(0);
                                System.out.println("Inserisci numero posto es. 8, 3, 11");
                                int numeroPosto = sc.nextInt();

                                Prenotazione prenotazione = new Prenotazione(utenteLoggato, spettacoloScelto, filaPosto, numeroPosto);
                                this.prenota(connection, prenotazione);

                            } else {
                                setLogin(false);
                                System.out.println("Logout effettuato con successo");
                                break;
                            }

                        } // fine while menu utente

                    } catch (Exception e) {
                        System.out.println("Dati non validi, riprova");
                    } finally {
                        continue;
                    }

                default: // exit
                    System.out.println("A presto!");
                    break;
            }
            break;
        }
    }

    // getters and setters

    public boolean isLogin() {
        return login;
    }

    public void setLogin(boolean login) {
        this.login = login;
    }
}
