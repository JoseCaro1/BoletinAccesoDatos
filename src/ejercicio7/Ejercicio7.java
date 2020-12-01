package ejercicio7;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Ejercicio7 {

    /*Diseñar un programa para encriptar y desencriptar los datos de un fichero de texto. Se
introduce una cadena por teclado que será la clave a aplicar para la encriptación y desencriptación.
A cada carácter del fichero de texto original se le sumará una letra de la clave, cuando se hayan
acabado las letras de la palabra clave y aún no se hayan acabado los caracteres del fichero, se
volverá al principio de la cadena para seguir aplicando la encriptación. Los datos encriptados se
escribirán en un fichero destino, que será usado como origen para desencriptar. Para desencriptar se
aplicará la fórmula a la inversa. Por ejemplo, si el fichero origen contiene “abcdef” y la palabra
clave es “rosa”, en el fichero destino se escribirán los caracteres correspondientes a: “a+r b+o c+s
d+a e+r f+o”.*/


    final String encriptado = "abcdef";
    File directorio = new File("src\\ejercicio7\\fichero");
    File encriptadoFinal = new File("src\\ejercicio7\\fichero\\encriptado.txt");
    FileWriter escritor;

    String contraseña;

    int count = 0, i;
    Scanner keyboard = new Scanner(System.in);

    public Ejercicio7() throws IOException {
        directorio.mkdir();
        escritor = new FileWriter(encriptadoFinal);
        System.out.println("Introduce una contraseña:");
        contraseña = keyboard.nextLine();
        char[] buf = contraseña.toCharArray();
        if (encriptado.length() > buf.length) {
            for (int i = 0; i < encriptado.length(); i++) {
                escritor.write(encriptado.charAt(i) + buf[count++]);
                if (count == buf.length) {
                    count = 0;
                }

            }
        }else {
            for (int i = 0; i < buf.length; i++) {
                escritor.write(encriptado.charAt(count++) + buf[i]);
                if (count == encriptado.length()) {
                    count = 0;
                }

            }
        }

        escritor.close();

        FileReader lector = new FileReader("src\\ejercicio7\\fichero\\encriptado.txt");
        FileWriter escritorFinal = new FileWriter("src\\ejercicio7\\fichero\\encriptar.txt");
        count = 0;

        while ((i = lector.read()) != -1) {
            escritorFinal.write(i - buf[count++]);
            if (count == buf.length) {
                count = 0;
            }
        }

        lector.close();
        escritorFinal.close();

    }

    public static void main(String[] args) throws IOException {
        new Ejercicio7();
    }
}
