import java.sql.SQLOutput;
import java.util.Scanner;

public class Main {
    public static void main(String[] args){
        Scanner entrada=new Scanner(System.in);
        String textoMenu="---ASISTENTE DIGITAL DE NOTICIAS---\n"
                +"1. Actualizar noticias desde el feed\n"
                +"2. Generar informe HTML del dia\n"
                +"3. Ver/Modificar URL del feed\n"
                +"0. Salir\n"
                +"Elije una opción: ";
        int menu=entrada.nextInt();

        System.out.println(textoMenu);
        do{
            switch (menu){
                case 1:
                    System.out.println("Actualizando noticias...");
                    break;
                case 2:
                    System.out.println("Generando informe HTML...");
                    break;
                case 3:
                    System.out.println("Modificando URL del feed...");
                    break;
                case 0:
                    System.out.println("Saliendo...");
                    System.exit(0);
                    break;
            }
        }while(true);




    }
}
