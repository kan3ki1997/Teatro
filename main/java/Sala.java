import java.util.HashMap;
import java.util.Objects;

public class Sala {

    private HashMap<Character, Integer> postiSala;
    private String nome;
    private Sede sede;

    public Sala(String nome, Sede sede) {
        this.nome = nome;
        this.sede = sede;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Sede getSede() {
        return sede;
    }

    public void setSede(Sede sede) {
        this.sede = sede;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Sala sala = (Sala) o;
        return Objects.equals(postiSala, sala.postiSala);
    }

    @Override
    public int hashCode() {
        return Objects.hash(postiSala);
    }
}
