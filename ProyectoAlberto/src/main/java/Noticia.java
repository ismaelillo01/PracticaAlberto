import java.io.Serializable;
import java.sql.Timestamp;

public class Noticia implements Serializable {

    private String titulo;
    private String link;
    private String fuente;
    private long fecha;






    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getFuente() {
        return fuente;
    }

    public void setFuente(String fuente) {
        this.fuente = fuente;
    }

    public long getFecha() {
        return fecha;
    }

    public void setFecha(long fecha) {
        this.fecha = fecha;
    }

    @Override
    public String toString() {
        return "Noticia{" +
                "titulo='" + titulo + '\'' +
                ", link='" + link + '\'' +
                ", fuente='" + fuente + '\'' +
                ", fecha=" + fecha +
                '}';
    }
}
