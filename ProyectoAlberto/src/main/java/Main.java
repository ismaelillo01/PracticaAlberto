import java.sql.SQLOutput;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args){
        Scanner entrada=new Scanner(System.in);
        Configuracion config= Configuracion.cargarConfig();
        Logger logger = new Logger();
        LectorRSS lector=new LectorRSS();
        String textoMenu="---ASISTENTE DIGITAL DE NOTICIAS---\n"
                +"1. Actualizar noticias desde el feed\n"
                +"2. Generar informe HTML del dia\n"
                +"3. Ver/Modificar URL del feed\n"
                +"0. Salir\n"
                +"Elije una opción: ";

        logger.log(">>> Aplicación iniciada.");
        logger.log("Confiuracion cargada URL actual: "+config.getUrlFeed());
        do{
            System.out.println(textoMenu);
            int menu=entrada.nextInt();
            switch (menu){
                case 1:
                    System.out.println("Actualizando noticias...");
                    logger.log(">>> Actualizando noticias...");
                    List<Noticia> noticias=lector.leerFeed(config.getUrlFeed());
                    if (noticias.isEmpty()){
                        logger.log("No hay noticias nuevas.");
                    }else{
                        logger.log("Se han actualizado "+noticias.size()+" noticias.");
                        //para mañana ArchivoNoticias
                        for(Noticia n : noticias) {
                            System.out.println("  - " + n);
                        }
                    }

                    break;
                case 2:
                    System.out.println("Generando informe HTML...");
                    logger.log(">>> Generando informe HTML...");
                    break;
                case 3:
                    System.out.println("Modificando URL del feed...");
                    logger.log(">>> Modificando URL del feed...");
                    System.out.println("Añade una nueva url");
                    String url=entrada.next();
                    if (url.length()>0) {
                        config.setUrlFeed(url);
                    }else{
                        System.out.println("No se ha introducido una nueva url se a puesto xataka");
                    }
                    config.guardarConfig();
                    break;
                case 0:
                    System.out.println("Saliendo...");
                    logger.log("Saliendo...");
                    System.exit(0);
                    break;
            }
        }while(true);




    }
}
