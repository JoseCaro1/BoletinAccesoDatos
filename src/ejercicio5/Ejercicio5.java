package ejercicio5;

import java.io.*;

public class Ejercicio5 {

    /*Realiza un programa que dadas dos rutas, origen y destino, copie el archivo origen(fichero
de texto) en el destino de la siguiente manera:
• Si el destino es un directorio, se creará un archivo con el mismo nombre donde se copiará el
archivo origen línea a línea(una línea se considera hasta que se encuentre un salto de línea).
Utilizar Stream de la clase BufferedReader.
• Si el destino es un archivo, habrá varias opciones según un booleano:
➢ Si el booleano es verdadero y el destino es un archivo existente, se reemplazará su
contenido por el del archivo origen copiando carácter a carácter.
➢ Si el booleano es verdadero y el destino es un archivo inexistente, se lanzará una
excepción.
➢ Si el booleano es falso y el destino es un archivo existente, se reemplazará su
contenido por el del archivo origen copiándolo usando un buffer(array) sin
desplazamiento de 20 caracteres. En el caso de la última escritura, si no se llena el
buffer, utilizar el desplazamiento para no dejar basura.
➢ Si el booleano es falso y el destino es un archivo inexistente, no se hará nada con el
archivo y se le dará un mensaje al usuario de que la copia no se puede realizar.*/

    private final String ORIGIN_PATH_DIRECTORY = "src\\ejercicio5\\fichero";
    private final String ORIGIN_PATH_FILE = "src\\ejercicio5\\fichero\\origen.txt";
    private final String SECOND_PATH_DIRECTORY = "src\\ejercicio5\\destino\\destino.txt";

    File origenDirectorio = new File(ORIGIN_PATH_DIRECTORY);
    File origenFichero = new File(ORIGIN_PATH_FILE);
    File destino = new File(SECOND_PATH_DIRECTORY);

    public Ejercicio5() throws IOException {
        createFileIfNotExist(origenDirectorio, "dir");
        createFileIfNotExist(origenFichero, "file");
        createFileIfNotExist(destino, "dir");
        boolean condition = false;

        if (destino.isDirectory()) {
            File destinoCreate = new File(destino.getPath() + "\\" + destino.getName() + ".txt");
            BufferedReader lectura = new BufferedReader(new FileReader(origenFichero));
            String buffer;
            FileWriter escritor = new FileWriter(destinoCreate);
            destinoCreate.createNewFile();

            while ((buffer = lectura.readLine()) != null) {
                escritor.write(buffer);
            }
            escritor.close();
            lectura.close();
        } else if (destino.isFile()) {

            FileReader lectura = new FileReader(origenFichero);
            FileWriter escritor = new FileWriter(destino);
            int c;
            if (destino.exists() && condition) {
                while ((c = lectura.read()) != -1) {
                    escritor.write(c);
                }
            } else if (!destino.exists() && condition) {

                throw new FileNotFoundException("NOT EXIST THIS FILE");
            } else if (destino.exists() && !condition) {
                char buffer[] = new char[20];

                while ((c = lectura.read(buffer)) != -1) {
                    if (c == 20) {
                        escritor.write(buffer);
                    } else {
                        escritor.write(buffer, 0, c);
                    }
                }
            }
            lectura.close();
            escritor.close();


        }
    }

    private void createFileIfNotExist(File file, String dirOrFile) throws IOException {
        if (!file.exists() && dirOrFile.equalsIgnoreCase("file")) {
            file.createNewFile();
        } else if (!file.exists() && dirOrFile.equalsIgnoreCase("dir")) {
            file.mkdir();
        }
    }

    public static void main(String[] args) throws IOException {
        new Ejercicio5();
    }


}
