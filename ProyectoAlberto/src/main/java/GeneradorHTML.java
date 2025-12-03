import org.w3c.dom.Document;
import org.w3c.dom.Element;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList; // <--- NUEVO IMPORT
import java.util.Date;
import java.util.List;
// import java.util.stream.Collectors; // <--- PUEDES BORRAR ESTE IMPORT

public class GeneradorHTML {

    private static final String PROYECTO_BASE = System.getProperty("user.dir") + "\\ProyectoAlberto\\";
    private static final String RUTA_PLANTILLA = PROYECTO_BASE + "data\\plantilla_resumen.xsl";
    private static final String DIR_REPORTES = PROYECTO_BASE + "reports\\";

    public void generarInforme(List<Noticia> noticias) {
        Logger logger = new Logger();
        String rutaSalida= "";
        try {
            SimpleDateFormat sdfDia = new SimpleDateFormat("yyyy-MM-dd");
            String hoy = sdfDia.format(new Date());
            List<Noticia> noticiasHoy = new ArrayList<>();
            for (Noticia n : noticias) {
                if (sdfDia.format(new Date(n.getFecha())).equals(hoy)) {
                    noticiasHoy.add(n);
                }
            }

            Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();
            Element root = doc.createElement("resumen");
            doc.appendChild(root);

            SimpleDateFormat horaFmt = new SimpleDateFormat("HH:mm");

            for (Noticia n : noticiasHoy) {
                Element item = doc.createElement("noticia");

                Element titulo = doc.createElement("titulo");
                titulo.setTextContent(n.getTitulo());
                item.appendChild(titulo);

                Element link = doc.createElement("link");
                link.setTextContent(n.getLink());
                item.appendChild(link);

                Element fuente = doc.createElement("fuente");
                fuente.setTextContent(n.getFuente());
                item.appendChild(fuente);

                Element fechaStr = doc.createElement("fechaString");
                fechaStr.setTextContent(horaFmt.format(new Date(n.getFecha())));
                item.appendChild(fechaStr);

                root.appendChild(item);
            }

            rutaSalida = DIR_REPORTES + "resumen-" + hoy + ".html";

            Transformer transformer = TransformerFactory.newInstance().newTransformer(new StreamSource(new File(RUTA_PLANTILLA)));
            transformer.transform(new DOMSource(doc), new StreamResult(new File(rutaSalida)));

            logger.log("Informe HTML generado correctamente en: " + rutaSalida);

        } catch (Exception e) {
            logger.log("ERROR: No se ha podido generar el informe HTML (" + rutaSalida + "). Mensaje: " + e.getMessage());
        }
    }
}