package ejercicio6;

import java.io.*;

public class Ejercicio6 {

    /*
     * Realiza un programa que dado un fichero de texto, se copie en tres
     * ficherosdiferentes de tal manera que copie en el primer fichero los primeros
     * 5 caracteres, en el segundo, los 5 siguientes y en el tercero los 5
     * siguientes, y así sucesivamente hastacopiar todo el fichero. Utilizar lectura
     * sin desplazamiento y escrituras condesplazamiento//Después, hacer justamente
     * lo contrario. Dados los 3 ficheros, construiruno como el fichero original.
     * Utilizar ahora lecturas con desplazamiento y escritura sindesplazamiento.
     * Comprobar por código que ambos son iguales. En el caso de la
     * últimalectura/escritura, si no se llena el buffer, utilizar el desplazamiento
     * para no dejar basura
     */
    File directory = new File("src\\ejercicio6\\fichero");
    File lectura = new File("src\\ejercicio6\\fichero\\lectura.txt");
    File escritura1 = new File("src\\ejercicio6\\fichero\\escritura.txt");
    File escritura2 = new File("src\\ejercicio6\\fichero\\escritura2.txt");
    File escritura3 = new File("src\\ejercicio6\\fichero\\escritura3.txt");


    public Ejercicio6() throws IOException {
        createFileIfNotExist(directory, "dir");
        createFileIfNotExist(lectura, "file");
        createFileIfNotExist(escritura1, "file");
        createFileIfNotExist(escritura2, "file");
        createFileIfNotExist(escritura3, "file");
        //deconstruirFichero();
        reconstruirFichero();

    }

    private void reconstruirFichero() throws IOException {
        FileReader reader[] = {new FileReader(escritura1), new FileReader(escritura2), new FileReader(escritura3)};
        FileWriter escritor = new FileWriter(lectura);
        char buffer[] = new char[5];
        int count = -1, c = 5;


        while ((c = reader[++count].read(buffer, 0, c)) != -1) {
            if (buffer.length != c) {
                escritor.write(String.valueOf(buffer,0,c));

            } else {
                escritor.write(buffer);
            }

            if (count >= 2) {
                count = -1;
            }
        }



        reader[0].close();
        reader[1].close();
        reader[2].close();
        escritor.close();

    }

    private void deconstruirFichero() throws IOException {
        FileReader reader = new FileReader(lectura);
        FileWriter escritor1 = new FileWriter(escritura1);
        FileWriter escritor2 = new FileWriter(escritura2);
        FileWriter escritor3 = new FileWriter(escritura3);
        char buffer[] = new char[5];
        int count = 1, c;

        while ((c = reader.read(buffer)) != -1) {
            if (count > 3) {
                count = 1;
            }

            if (count == 1) {
                escritor1.write(buffer, 0, c);
            } else if (count == 2) {
                escritor2.write(buffer, 0, c);
            } else if (count == 3) {
                escritor3.write(buffer, 0, c);
            }
            count++;

        }
        reader.close();
        escritor1.close();
        escritor2.close();
        escritor3.close();


    }


    private void createFileIfNotExist(File file, String dirOrFile) throws IOException {
        if (!file.exists() && dirOrFile.equalsIgnoreCase("file")) {
            file.createNewFile();
        } else if (!file.exists() && dirOrFile.equalsIgnoreCase("dir")) {
            file.mkdir();
        }
    }


    public static void main(String[] args) throws IOException {
        new Ejercicio6();
    }
}
