package ejercicio10;

import com.thoughtworks.xstream.XStream;
import ejercicio8.Contactos;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Ejercicio10 {
    /*Haz un programa que lea los contactos del fichero binario de la agenda telefónica con
    serialización de objetos del ejercicio 8 y los escriba en un fichero XML usando la librería XStream.*/

    XStream stream = new XStream();
    File lectura = new File("src\\ejercicio8\\fichero\\datosCon.dat");
    File directory = new File("src\\ejercicio10\\fichero");
    File escritura = new File("src\\ejercicio10\\fichero\\contactos.xml");
    List<Contactos> list = new ArrayList<>();
    ListaContactos listaContactos = new ListaContactos(list);

    public Ejercicio10() throws IOException {
        createFileIfNotExist(directory, "dir");
        createFileIfNotExist(escritura, "file");
        readObjectFile();

        //stream.addImplicitCollection(ListaContactos.class, "contactosList");
        //stream.alias("contactos", Contactos.class);
        //stream.alias("ListaContactos", ListaContactos.class);
        // stream.aliasField("nombre", Contactos.class, "name");
        stream.toXML(listaContactos, new FileOutputStream(escritura));
    }

    private void createFileIfNotExist(File file, String dirOrFile) throws IOException {
        if (!file.exists() && dirOrFile.equalsIgnoreCase("file")) {
            file.createNewFile();
        } else if (!file.exists() && dirOrFile.equalsIgnoreCase("dir")) {
            file.mkdir();
        }
    }

    private void readObjectFile() throws IOException {
        ObjectInputStream lectorObjecto = new ObjectInputStream(new FileInputStream(lectura));
        try {
            while (true) {
                list.add((Contactos) lectorObjecto.readObject());
            }
        } catch (EOFException EO) {
            System.out.println("Fin lectura");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            lectorObjecto.close();
        }

    }

    public static void main(String[] args) throws IOException {
        new Ejercicio10();
    }


}
