import java.io.*;

public class Configuracion implements Serializable {
    private String urlFeed;

    public void guardarConfig(){
        try {
            File fichero=new File("../data/config.ser");
            FileOutputStream fos=new FileOutputStream(fichero);
            ObjectOutputStream oos= new ObjectOutputStream(fos);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
    public void cargarConfig(){
        try {
            File fichero=new File("../data/config.ser");
            FileInputStream fis=new FileInputStream(fichero);
            ObjectInputStream ois= new ObjectInputStream(fis);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
