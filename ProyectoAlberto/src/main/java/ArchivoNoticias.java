import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;

public class ArchivoNoticias {
    private String rutaFichero = System.getProperty("user.dir") + "\\ProyectoAlberto\\data\\noticias.dat";
    private static final int TAM_TITULO = 200;
    private static final int TAM_LINK = 300;
    private static final int TAM_FUENTE = 100;

    //String son 2 bytes por caracter y el long de la fecha siempre es 8
    private static final int TAM_TOTAL = (TAM_TITULO * 2) + (TAM_LINK * 2) + (TAM_FUENTE * 2) + 8;

    public boolean guardarNoticia(Noticia n) {
        Logger log = new Logger();
        try {
            RandomAccessFile raf = new RandomAccessFile(rutaFichero,"rw");
            while (raf.getFilePointer() < raf.length()) {
                //saltamos el tiutlo
                raf.skipBytes(TAM_TITULO*2);
                String link = leerStringFijo(raf,TAM_LINK);
                //saltamos fuente y fecha
                raf.skipBytes((TAM_FUENTE*2)+8);
                if (link.equals(n.getLink())){
                    log.log("La noticia "+ n.getTitulo() +" ya existe en el fichero" );
                    return false;
                }
            }
            raf.seek(raf.length());

            escribirStringFijo(raf,n.getTitulo(),TAM_TITULO);
            escribirStringFijo(raf,n.getLink(),TAM_LINK);
            escribirStringFijo(raf,n.getFuente(),TAM_FUENTE);
            raf.writeLong(n.getFecha());
            raf.close();
            log.log("Noticia "+ n.getTitulo() +" guardada en el fichero" );
            return true;
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);

        } catch (IOException e) {
            throw new RuntimeException(e);

        }
    }
    private void escribirStringFijo(RandomAccessFile raf, String str, int tamano){
        try {
            StringBuffer buffer = new StringBuffer(str);
            buffer.setLength(tamano);
            raf.writeChars(buffer.toString());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    private String leerStringFijo(RandomAccessFile raf, int tamano){
        try {
            char[] contenido = new char[tamano];
            for (int i = 0; i < tamano; i++) {
                contenido[i]=raf.readChar();
            }
            return new String(contenido).trim();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    private List<Noticia> leerTodasLasNoticias() {
        try {
            List<Noticia> noticias = new ArrayList<Noticia>();
            RandomAccessFile raf = new RandomAccessFile(rutaFichero, "r");
            while (raf.getFilePointer() < raf.length()) {
                Noticia n = new Noticia();
                n.setTitulo(leerStringFijo(raf,TAM_TITULO));
                n.setLink(leerStringFijo(raf,TAM_LINK));
                n.setFuente(leerStringFijo(raf,TAM_FUENTE));
                n.setFecha(raf.readLong());
                noticias.add(n);
            }
            return noticias;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
