import java.io.*;

public class Configuracion implements Serializable {
    private String urlFeed;
    private static final String PATH="../data/config.ser";


    public Configuracion(){
        this.urlFeed="https://www.xataka.com/feedburner.xml";
    }

    public void guardarConfig(){
        try {
            ObjectOutputStream oos=new ObjectOutputStream(new FileOutputStream(PATH));
            oos.writeObject(this);
            oos.close();
            System.out.println("Configuracion guardada en "+PATH);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
    public static Configuracion cargarConfig(){
        try {
            ObjectInputStream ois=new ObjectInputStream(new FileInputStream(PATH));
            Configuracion config=(Configuracion)ois.readObject();
            ois.close();
            System.out.println("Configuracion cargada desde "+PATH);
            return config;
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
