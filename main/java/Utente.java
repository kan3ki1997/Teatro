public class Utente {

    private String nome;
    private String cognome;
    private String indirizzo;
    private String mail;
    private String password;

    public Utente(String nome, String cognome, String indirizzo, String mail, String password) {
        this.nome = nome;
        this.cognome = cognome;
        this.indirizzo = indirizzo;
        this.mail = mail;
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public String getIndirizzo() {
        return indirizzo;
    }

    public void setIndirizzo(String indirizzo) {
        this.indirizzo = indirizzo;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }
}
