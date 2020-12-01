package ejercicio9;

import ejercicio8.Contactos;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Ejercicio9 {

    /*Haz un programa que lea los contactos del fichero del ejercicio 8 (la versión con
serialización de objetos) y los guarde en un fichero aleatorio. En el fichero aleatorio tienes que
añadir un identificador a cada contacto antes de los datos. El identificador empieza en 1. Añádele
también en el fichero a cada contacto un boolean para indicar si el contacto ha sido borrado. A
continuación, hazle el siguiente menú al fichero aleatorio:
• Consultar todos los contactos.
• Consultar un contacto (pedirle al usuario el identificador).
• Añadir un contacto
➢ Por el final
➢ En la primera posición libre
• Eliminar un contacto poniendo el boolean a false (pedirle al usuario el identificador).
• Modificar si le debo dinero y la cantidad (pedirle al usuario el identificador).
• Compactación del fichero.
Realizar dos versiones del ejercicio:
➢ Utilizando writeChars
➢ Utilizando writeUTF*/

    private List<String> nombres = new ArrayList<>();
    private List<Integer> tel = new ArrayList<>();
    private List<String> direccion = new ArrayList<>();
    private List<Integer> codigo_postal = new ArrayList<>();
    private List<Boolean> deberDinero = new ArrayList<>();
    private List<Double> dinero = new ArrayList<>();
    private List<Long> length = new ArrayList<>();
    Contactos contacto;

    File directory = new File("src\\ejercicio9\\fichero");
    File fichero = new File("src\\ejercicio9\\fichero\\fichero.dat");
    File lectura = new File("src\\ejercicio8\\fichero\\datosCon.dat");

    public Ejercicio9() throws IOException {
        createFileIfNotExist(directory, "dir");
        createFileIfNotExist(fichero, "file");
        readFileSerializable(lectura);
        writeBinaryFile();
        showContact(3);

    }

    private void readFileSerializable(File destino) throws IOException {
        ObjectInputStream lector = new ObjectInputStream(new FileInputStream(destino));
        try {
            while (true) {
                contacto = ((Contactos) lector.readObject());
                nombres.add(contacto.getName());
                tel.add(contacto.getTel());
                direccion.add(contacto.getAddress());
                codigo_postal.add(contacto.getCodigo_postal());
                deberDinero.add(contacto.isDeberDinero());
                dinero.add(contacto.getDinero());
            }
        } catch (EOFException eo) {
            System.out.println("FIN DE LECTURA");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            lector.close();
        }
    }

    private void writeBinaryFile() throws IOException {
        RandomAccessFile escritor = new RandomAccessFile(fichero, "rw");



        for (int i = 0; i < nombres.size(); i++) {
            StringBuffer bufferName= new StringBuffer(nombres.get(i));
            bufferName.setLength(15);
            StringBuffer bufferAddress= new StringBuffer(direccion.get(i));
            bufferName.setLength(15);
            escritor.writeInt(i + 1);
            escritor.writeUTF(bufferName.toString());
            escritor.writeInt(tel.get(i));
            escritor.writeUTF(bufferAddress.toString());
            escritor.writeInt(codigo_postal.get(i));
            escritor.writeBoolean(deberDinero.get(i));
            escritor.writeDouble(dinero.get(i));
            escritor.writeBoolean(true);
            //39 bytes
        }
        System.out.println(fichero.length()/4);
        escritor.close();
    }

    private void showContact(int id) throws IOException {
        RandomAccessFile lectorContacto = new RandomAccessFile(fichero, "r");
        long position = 0, id2;

        while (true) {
            lectorContacto.seek(position);
            id2 = lectorContacto.readInt();
            if (id2 == id) {
                System.out.printf("Id: %d\nNombre: %s\nTelefono %d\nDireccion: %s\nCodigo postal %d\n%s\nCantidad %.2f\n%s\n\n",
                        id, lectorContacto.readUTF(), lectorContacto.readInt(), lectorContacto.readUTF(), lectorContacto.readInt(), lectorContacto.readBoolean() ? "Debe dinero" : "No debe dinero", lectorContacto.readDouble(),
                        lectorContacto.readBoolean() ? "Conctacto activo" : "Contacto borrado");
                break;
            }

            position+=61;

            if (fichero.length() <= position) {
                System.out.println("Id no encontrado");
                break;
            }
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
        new Ejercicio9();
    }
}



