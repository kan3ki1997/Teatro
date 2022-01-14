public class Prenotazione {

    private Utente utente;
    private long idPrenotazione;
    private char filaPosto;
    private int numeroPosto;
    private Spettacolo spettacolo;

    public Prenotazione(Utente utente, Spettacolo spettacolo, char filaPosto, int numeroPosto) {
        this.utente = utente;
        this.spettacolo = spettacolo;
        idPrenotazione = (long) (Math.random() * 9199999999999999999l);
        this.filaPosto = filaPosto;
        this.numeroPosto = numeroPosto;
    }

    public Utente getUtente() {
        return utente;
    }

    public void setUtente(Utente utente) {
        this.utente = utente;
    }

    public Spettacolo getSpettacolo() {
        return spettacolo;
    }

    public void setSpettacolo(Spettacolo spettacolo) {
        this.spettacolo = spettacolo;
    }

    public long getIdPrenotazione() {
        return idPrenotazione;
    }

    public void setIdPrenotazione(long idPrenotazione) {
        this.idPrenotazione = idPrenotazione;
    }

    public char getFilaPosto() {
        return filaPosto;
    }

    public void setFilaPosto(char filaPosto) {
        this.filaPosto = filaPosto;
    }

    public int getNumeroPosto() {
        return numeroPosto;
    }

    public void setNumeroPosto(int numeroPosto) {
        this.numeroPosto = numeroPosto;
    }
}
