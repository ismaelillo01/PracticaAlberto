import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.format.DateTimeFormatter;

public class Logger {
    private final String RUTALOG=System.getProperty("user.dir") + "\\ProyectoAlberto\\data\\resumen_diario.log";
    private DateTimeFormatter formatter=DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");



    public Logger(){
        try {
        File f = new File(RUTALOG);
        if(!f.exists()){
            f.getParentFile().mkdirs();
            f.createNewFile();
            }
            } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void log(String mensaje){
        try {
            FileWriter fw= new FileWriter(RUTALOG,true);
            PrintWriter pw=new PrintWriter(fw);
            String fecha=formatter.format(java.time.LocalDateTime.now());
            pw.println(fecha+" "+mensaje);
            pw.close();
            fw.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
