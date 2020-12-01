package ejercicio8;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Ejercicio8 {

    /*Queremos hacer una agenda telefónica con los siguientes datos:
• Nombre del contacto
• Teléfono
• Dirección
• Código postal (número entero)
• Fecha de nacimiento
• Si le debo dinero (booleano)
• Cuánto le debo(número decimal)
Realiza un programa que almacene los datos en un fichero binario. A continuación, lee el fichero y
muestra el contenido por consola. Hacer dos versiones:
• Sin serialización de objetos
• Con serialización de objetos. Almacena varios contactos y cierra el programa. Luego ábrelo
de nuevo y almacena unos cuantos contactos más.*/


    List<Contactos> contactosList = new ArrayList<>(List.of(new Contactos("Jose", 633221122, "Calle ter", 11300, true, 10),
            new Contactos("Pepe", 612345646, "Calle Arenal", 11300, false, 0),
            new Contactos("Mateo", 123456789, "Calle Ar", 11300, false, 0)));


    File directory = new File("src\\ejercicio8\\fichero");
    File sinSerielizacion = new File("src\\ejercicio8\\fichero\\datosSin.dat");
    File conSerielizacion = new File("src\\ejercicio8\\fichero\\datosCon.dat");

    public Ejercicio8() throws IOException {
        createFileIfNotExist(directory, "dir");
        createFileIfNotExist(sinSerielizacion, "file");
        createFileIfNotExist(conSerielizacion, "file");
        // writeWithoutSerializable(sinSerielizacion);
        writeWithSerializable(conSerielizacion);
    }

    private void writeWithSerializable(File destino) throws IOException {
        ObjectOutputStream escritorObjecto = new ObjectOutputStream(new FileOutputStream(destino));
        MyObjectOutputStream myEscritorObjecto = new MyObjectOutputStream(new FileOutputStream(destino, true));
        ObjectInputStream lectorObjecto = new ObjectInputStream(new FileInputStream(destino));
        System.out.println(destino.length());

        for (int i = 0; i < contactosList.size(); i++) {
            escritorObjecto.writeObject(contactosList.get(i));

        }

        myEscritorObjecto.writeObject(contactosList.get(0));
        myEscritorObjecto.close();
        escritorObjecto.close();


        try {
            while (true) {
                System.out.println(lectorObjecto.readObject());
            }
        } catch (EOFException EO) {
            System.out.println("Fin lectura");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            lectorObjecto.close();
        }


    }

    //File FileReader FileWriter BufferReader BufferWriter // File FileOutStream FileInputStream DataOutputStream ObjectOutputStream
    private void writeWithoutSerializable(File destino) throws IOException {

        DataOutputStream escritorBinario = new DataOutputStream(new FileOutputStream(destino));
        DataInputStream lectorBinario = new DataInputStream(new FileInputStream(destino));


        for (int i = 0; i < contactosList.size(); i++) {
            escritorBinario.writeUTF(contactosList.get(i).getName());
            escritorBinario.writeInt(contactosList.get(i).getTel());
            escritorBinario.writeUTF(contactosList.get(i).getAddress());
            escritorBinario.writeInt(contactosList.get(i).getCodigo_postal());
            escritorBinario.writeBoolean(contactosList.get(i).isDeberDinero());
            escritorBinario.writeDouble(contactosList.get(i).getDinero());
        }
        escritorBinario.close();
        try {
            while (true) {

                System.out.println(lectorBinario.readUTF());
                System.out.println(lectorBinario.readInt());
                System.out.println(lectorBinario.readUTF());
                System.out.println(lectorBinario.readInt());
                System.out.println(lectorBinario.readBoolean());
                System.out.println(lectorBinario.readDouble());
                System.out.println();
            }

        } catch (EOFException eo) {
            System.out.println("Fin lectura");
        } finally {
            lectorBinario.close();
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
        new Ejercicio8();
    }

}
