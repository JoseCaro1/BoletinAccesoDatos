package ejercicio8;

import java.io.File;
import java.io.IOException;
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
        createFileIfNotExist(directory,"dir");
        createFileIfNotExist(sinSerielizacion,"file");
        createFileIfNotExist(conSerielizacion,"file");
    }

    private void createFileIfNotExist(File file, String dirOrFile) throws IOException {
        if (!file.exists() && dirOrFile.equalsIgnoreCase("file")) {
            file.createNewFile();
        } else if (!file.exists() && dirOrFile.equalsIgnoreCase("dir")) {
            file.mkdir();
        }
    }

}
