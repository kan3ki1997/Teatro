import java.time.LocalDate;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class Spettacolo {

    private String genere;
    private int durata;
    private double prezzo;
    private LocalDateTime orario;
    private LocalDate data;
    private Sala sala;
    int idSpettacolo;

    public Spettacolo(String genere, int durata, int idSpettacolo, LocalDate data, Sala sala) {
        this.genere = genere;
        this.durata = durata;
        this.idSpettacolo = idSpettacolo;
        this.data = data;
        this.sala = sala;
    }

    // getters and setters

    public String getGenere() {
        return genere;
    }

    public void setGenere(String genere) {
        this.genere = genere;
    }

    public int getDurata() {
        return durata;
    }

    public void setDurata(int durata) {
        this.durata = durata;
    }

    public double getPrezzo() {
        return prezzo;
    }

    public void setPrezzo(double prezzo) {
        this.prezzo = prezzo;
    }

    public LocalDateTime getOrario() {
        return orario;
    }

    public void setOrario(LocalDateTime orario) {
        this.orario = orario;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public Sala getSala() {
        return sala;
    }

    public void setSala(Sala sala) {
        this.sala = sala;
    }

    public int getIdSpettacolo() {
        return idSpettacolo;
    }

    public void setIdSpettacolo(int idSpettacolo) {
        this.idSpettacolo = idSpettacolo;
    }
}
