package ejercicio11;

import com.thoughtworks.xstream.XStream;
import ejercicio10.ListaContactos;

import java.io.*;

public class Ejercicio11 {

    /*Dado el XML del ejercicio anterior, utilizando XStream, pásalo a un fichero binario sin
serialización de objetos. Compara que sea igual al fichero binario sin serialización de objetos
obtenido en el ejercicio 8. Haz la comparación en binario.*/

    XStream xStream = new XStream();
    ListaContactos listaContactos;
    File lectura = new File("src\\ejercicio10\\fichero\\contactos.xml");
    File lecturaSin = new File("src\\ejercicio8\\fichero\\datosSin.dat");
    File directory = new File("src\\ejercicio11\\fichero");
    File comparador = new File("src\\ejercicio11\\fichero\\comparador.dat");


    public Ejercicio11() throws IOException {
        createFileIfNotExist(directory, "dir");
        createFileIfNotExist(comparador, "file");
        convertBinaryFromXML();
        System.out.println(binaryComparator());

    }

    private void createFileIfNotExist(File file, String dirOrFile) throws IOException {
        if (!file.exists() && dirOrFile.equalsIgnoreCase("file")) {
            file.createNewFile();
        } else if (!file.exists() && dirOrFile.equalsIgnoreCase("dir")) {
            file.mkdir();
        }
    }

    private boolean binaryComparator() throws IOException {
        DataInputStream lectorBinario = new DataInputStream(new FileInputStream(lecturaSin));
        DataInputStream lectorBinario2 = new DataInputStream(new FileInputStream(comparador));
        int valor1 = 0, valor2 = 0;

        while (valor1 != -1 && valor2 != -1) {
            valor1 = lectorBinario.read();
            valor2 = lectorBinario2.read();
            if (valor1 != valor2) {
                return false;
            }

        }
        return true;
    }

    private void convertBinaryFromXML() throws IOException {
        DataOutputStream escritorBinario = new DataOutputStream(new FileOutputStream(comparador));
        listaContactos = (ListaContactos) xStream.fromXML(lectura);

        for (int i = 0; i < listaContactos.getContactosList().size(); i++) {
            escritorBinario.writeUTF(listaContactos.getContactosList().get(i).getName());
            escritorBinario.writeInt(listaContactos.getContactosList().get(i).getTel());
            escritorBinario.writeUTF(listaContactos.getContactosList().get(i).getAddress());
            escritorBinario.writeInt(listaContactos.getContactosList().get(i).getCodigo_postal());
            escritorBinario.writeBoolean(listaContactos.getContactosList().get(i).isDeberDinero());
            escritorBinario.writeDouble(listaContactos.getContactosList().get(i).getDinero());
        }

    }

    public static void main(String[] args) throws IOException {
        new Ejercicio11();
    }


}
