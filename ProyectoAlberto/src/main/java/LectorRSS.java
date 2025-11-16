import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class LectorRSS {
    public List<Noticia> leerFeed(String urlString){
        List<Noticia> noticias= new ArrayList<Noticia>();
        try {

            URL url = new URL(urlString);
            InputStream f = url.openStream();
            DocumentBuilderFactory dFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder= dFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(f);
            Element root = doc.getDocumentElement();
            NodeList nList = root.getElementsByTagName("item");
            for (int i = 0; i<nList.getLength();i++){
                Node nodo = nList.item(i);
                if (nodo.getNodeType()==Node.ELEMENT_NODE){
                    Element elemento = (Element) nodo;
                    String titulo = elemento.getElementsByTagName("title").item(0).getTextContent();
                    String link = elemento.getElementsByTagName("link").item(0).getTextContent();
                    String pubDate= elemento.getElementsByTagName("pubDate").item(0).getTextContent();
                    Noticia noticia= new Noticia();
                    noticia.setTitulo(titulo);
                    noticia.setLink(link);
                    noticia.setFecha(parsearFecha(pubDate));
                    noticias.add(noticia);
                }
            }
            f.close();
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ParserConfigurationException e) {
            throw new RuntimeException(e);
        } catch (SAXException e) {
            throw new RuntimeException(e);
        }
        return noticias;
    }
    private long parsearFecha(String fechaStr) {
        try {
            SimpleDateFormat parser = new SimpleDateFormat("E, dd MMM yyyy HH:mm:ss Z", Locale.ENGLISH);
            Date fecha = parser.parse(fechaStr);
            return fecha.getTime();
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }
}
