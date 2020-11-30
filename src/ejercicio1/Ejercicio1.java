package ejercicio1;

import java.io.File;

public class Ejercicio1 {

    /*Realiza un programa que dado un fichero que se le solicite al usuario, muestre su nombre, si
       es un ejecutable, si está oculto, la ruta relativa, la ruta absoluta y el tamaño.*/

    public Ejercicio1(File fichero) {
        String result = String.format(
                "Nombre:%s \nEjecutable: %s\nOculto: %s\nRuta relativa: %s\nRuta absoluta: %s\nTamaño: %d",
                fichero.getName(), fichero.canExecute() ? "Si" : "No", fichero.isHidden() ? "Si" : "No",
                fichero.getPath(), fichero.getAbsolutePath(), fichero.length());
        System.out.println(result);

    }


}
