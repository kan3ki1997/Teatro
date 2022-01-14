import java.util.UUID;

public class Sede {

    private UUID id;
    private String nome;
    private String indirizzo;
    private boolean luogo; // aperto o al chiuso
    private String comune;

    public Sede(String nome, String indirizzo, boolean luogo, String comune) {

        this.nome = nome;
        this.indirizzo = indirizzo;
        this.luogo = luogo;
        this.comune = comune;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getIndirizzo() {
        return indirizzo;
    }

    public void setIndirizzo(String indirizzo) {
        this.indirizzo = indirizzo;
    }

    public boolean isLuogo() {
        return luogo;
    }

    public void setLuogo(boolean luogo) {
        this.luogo = luogo;
    }

    public String getComune() {
        return comune;
    }

    public void setComune(String comune) {
        this.comune = comune;
    }

}
