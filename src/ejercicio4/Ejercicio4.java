package ejercicio4;

import java.io.File;
import java.io.FilenameFilter;
import java.util.List;
import java.util.Scanner;

public class Ejercicio4 {

    /*Un filtro sirve para que el método list devuelva solo aquellos archivos o carpetas que
cumplan una condición (que tengan una extensión determinada, contengan en su nombre una
cadena concreta, empiecen por un carácter, etc). Un filtro es un objeto de una clase que implementa
el interface FilenameFilter. Realiza un programa que muestre los archivos de un directorio que
posean una extensión concreta. Tanto la extensión como el directorio se solicita al usuario.*/

    private File getPathDirectory() {
        Scanner keyboard = new Scanner(System.in);
        String path, extension;
        File directory;
        System.out.println("Introduzca la direccion de un direcotorio");
        path = keyboard.nextLine();
        directory = new File(path);

        if (!directory.exists()) {
            throw new IllegalArgumentException(path + "IS NOT A DIRECTORY");
        }
        return directory;
    }

    private String getExtension() {
        Scanner keyboard = new Scanner(System.in);
        String extension;
        System.out.println("Introduce el tipo de extension de los archivos dentro del directorio");
        extension = keyboard.nextLine();

        return extension;
    }

    private void getTypeFileFromDirectory(File file, String extension) {
        for (String element :
                file.list((dir, name) -> name.endsWith(extension))) {
            System.out.println(element);
        }

    }

    public Ejercicio4() {

        getTypeFileFromDirectory(getPathDirectory(),
                getExtension());
    }

    public static void main(String[] args) {
        new Ejercicio4();
    }
}
