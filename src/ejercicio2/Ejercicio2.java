package ejercicio2;

import java.io.File;
import java.io.IOException;

public class Ejercicio2 {

    /*Realiza un programa que cree un directorio en el directorio actual, luego cree tres ficheros
      en dicho directorio donde uno se borre y otro se renombre. Crearle también un subdirectorio con un
      fichero dentro. Después mostrar la ruta absoluta de ambos directorios y sus contenidos.*/

    public Ejercicio2() throws IOException {

        String list = "";
        String list1 = "";
        File directory = new File("src\\ejercicio2\\fichero");
        File subdirectory = new File("src\\ejercicio2\\fichero\\subfichero");
        File file1 = new File("src\\ejercicio2\\fichero\\fichero.txt");
        File file2 = new File("src\\ejercicio2\\fichero\\fichero2.txt");
        File file3 = new File("src\\ejercicio2\\fichero\\fichero3.txt");
        File file4 = new File("src\\ejercicio2\\fichero\\subfichero\\fichero.txt");

        createFileIfNotExist(directory,"dir");
        createFileIfNotExist(subdirectory,"dir");
        createFileIfNotExist(file1,"file");
        createFileIfNotExist(file2,"file");
        createFileIfNotExist(file3,"file");
        createFileIfNotExist(file4,"file");

        System.out.println(file2.delete());
        System.out.println(file3.renameTo(new File("src\\ejercicio2\\fichero\\ficheroRenamed.txt")));



        for (int i = 0; i < directory.list().length; i++) {
            list += directory.list()[i] + " ";
        }
        for (int i = 0; i < subdirectory.list().length; i++) {
            list1 += subdirectory.list()[i] + " ";
        }

        String result = String.format(
                "La ruta absoluta de %s es: %s y su contenido es: %s\nLa ruta absoluta de %s es: %s y su contenido: %s",
                directory.getName(), directory.getAbsolutePath(), list, subdirectory.getName(),
                subdirectory.getAbsolutePath(), list1);

        System.out.println(result);
    }

    private void createFileIfNotExist(File file,String dirOrFile) throws IOException {
        if(!file.exists() &&dirOrFile.equalsIgnoreCase("file")){
            file.createNewFile();
        }else if(!file.exists() &&dirOrFile.equalsIgnoreCase("dir")) {
            file.mkdir();
        }
    }

    public static void main(String[] args) throws IOException {
        new Ejercicio2();
    }
}
