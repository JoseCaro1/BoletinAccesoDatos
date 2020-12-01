package ejercicio10;

import ejercicio8.Contactos;

import java.util.List;

public class ListaContactos {

    private List<Contactos> contactosList;

    public ListaContactos(List<Contactos> contactosList){

        this.contactosList = contactosList;
    }

    public List<Contactos> getContactosList() {
        return contactosList;
    }
}
