import java.math.BigDecimal;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
       //este es el scaner que agarra el numero
        Scanner tomarNumero = new Scanner(System.in);
        //Declaracion de variables
        String numeroUsuario = ""; //variable que obtiene el numero del usuario
        double banderaTemporalVerificar = 0; //con esta usaremos para validar los decimales
        int cifrasSignificativas = 0; //variable que obtiene el numero de difras significativas
        String numeroTuncrado = ""; //variable que almacenara el numero truncado
        double numeroRedondeado = 0;
        int cantidadDecimales = 0; // obtendra cuantas numeros hay despues del punto
        int posicionPunto = 0; //saber la posicion del punto
        //Obtener el numero del usuario
       do {
           System.out.print("Digite su numero: ");
           numeroUsuario = tomarNumero.nextLine();

           banderaTemporalVerificar = Double.parseDouble(numeroUsuario);

           // Encontrar la posición del punto decimal
           posicionPunto = numeroUsuario.indexOf(".");

           // Contar los dígitos después del punto decimal
           if (posicionPunto != -1){
               cantidadDecimales = numeroUsuario.length() - posicionPunto - 1;
           }
           if (banderaTemporalVerificar <= 0){ //verificamos si el numero es mayor a 0 para mandar un mensaje
               System.out.println("El numero debe ser mayor a 0"); //verificamos si el numero tiene 4 decimales para mostrar mensaje
           }else if (cantidadDecimales < 4){
               System.out.println("El numero debe contener al menos 4 decimales");
           }

       }while (banderaTemporalVerificar <= 0 || cantidadDecimales < 4 ); //aqui menciona que siempre sera falso por q no toma el for de arriba q se realiza un aumento

       //CONTAR CRIFRAS SIGNIFICATIVAS
        for (int i= 0; i < numeroUsuario.length(); i ++){
            char cadena = numeroUsuario.charAt(i); // con esta variable extraemos el numero de la posicion
            int numeroCadena = cadena - '0'; // ch - '0'; convierte un carácter que representa un dígito en su correspondiente valor entero. Dado que cuando convertis esto te da el char en un numero Unicode (ASCII)

            if (cadena == '.'){ // si llegamos a la parte del punto solo es para obviar y no hacer nada
                //no contar nada
            }else if (numeroCadena > 0){ // contamos los digitos del num
                cifrasSignificativas += 1;
            }else if ((numeroUsuario.charAt(0)) - '0' > 0 && numeroCadena == 0){ // aqui contamos todos los 0 q estan adelante del punto
                cifrasSignificativas += 1;
            } else if (i > 0 && (numeroUsuario.charAt(i-1)) - '0' > 0 && numeroCadena == 0) { // agregamos los 0 q esten adelante de un digito mayor a 0 y pones i > 0 en caso de que el numero tenga un valor de 0. por ej por q dara error a la 1era vez de evaluar dado caso
                cifrasSignificativas += 1;
            }
        }

        //PROCESO DE REDONDEO
        BigDecimal numRed = BigDecimal.valueOf(banderaTemporalVerificar);
        numRed = numRed.setScale((cifrasSignificativas/2), BigDecimal.ROUND_HALF_UP);
        numeroRedondeado = numRed.doubleValue();
        //PROCESO DE TRUNCAMIENTO
        int iT = 0;
        int digitosSeleccion = numeroUsuario.length() - (cifrasSignificativas/2) + 1; // el mas 1 se agrega para introducir el "." dado q este es contado como una posicion pero no es ningun numero signifivo y aumenta tambien
        while (iT < digitosSeleccion){
            numeroTuncrado += numeroUsuario.charAt(iT);
            iT ++;
        }
        //PROCESO DE IMPRIMIR DATOS
        System.out.println("----");
        System.out.println(" ");
        System.out.println("Numero Redondeado: " + numeroRedondeado);
        System.out.println("Numero Truncado: " + numeroTuncrado);
        System.out.println("El numero de cifras significativas es: " + cifrasSignificativas);
        System.out.println("----");
    }
}