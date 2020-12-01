package ejercicio3;

import java.io.File;
import java.util.Scanner;

public class Ejercicio3 {

    /*Realiza un programa que muestre el nombre y tipo (fichero o directorio) de los ficheros y
subdirectorios contenidos en un directorio solicitado al usuario. Mostrar también el contenido de
todos los subdirectorios y si éstos contienen subdirectorios también...y así sucesivamente hasta
mostrar todo el contenido de dicho directorio.*/

    private File getDirectory() {
        Scanner keyboard = new Scanner(System.in);
        String path;
        File directory;

        System.out.println("Introduce direccion del directorio");
        path = keyboard.nextLine();

        directory = new File(path);

        if (!directory.exists()) {
            throw new IllegalArgumentException(path + "IS NOT A DIRECTORY");
        }
        System.out.println("Direccion raiz "+path);
        return directory;

    }

    private void getPathDirectoryRecursive(File file) {
        if (file.isDirectory()) {
            for (int i = 0; i < file.listFiles().length; i++) {
                System.out.println("Mi direccion es: "+file.listFiles()[i].getPath());
                if (file.listFiles()[i].isDirectory()) {
                    getPathDirectoryRecursive(file.listFiles()[i]);
                }
            }
        }
    }


    public Ejercicio3(){
        getPathDirectoryRecursive(getDirectory());
    }

    public static void main(String[] args) {
        new Ejercicio3();
    }
}
