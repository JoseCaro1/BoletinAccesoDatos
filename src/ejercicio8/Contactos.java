package ejercicio8;

import java.io.Serializable;

public class Contactos implements Serializable {

    private static final long serialVersionUID = 1L;
    private String name;
    private int tel;
    private String address;
    private int codigo_postal;
    private boolean deberDinero;
    private double dinero;

    public String getName() {
        return name;
    }

    public int getTel() {
        return tel;
    }

    public String getAddress() {
        return address;
    }

    public int getCodigo_postal() {
        return codigo_postal;
    }

    public boolean isDeberDinero() {
        return deberDinero;
    }

    public double getDinero() {
        return dinero;
    }

    public Contactos(String name, int tel, String address, int codigo_postal, boolean deberDinero, double dinero) {
        if (deberDinero == true && dinero <= 0) {
            throw new IllegalArgumentException("Datos invalidos");
        }
        this.name = name;
        this.tel = tel;
        this.address = address;
        this.codigo_postal = codigo_postal;
        this.deberDinero = deberDinero;
        this.dinero = dinero;
    }

    @Override
    public String toString() {
        // TODO Auto-generated method stub
        return String.format(
                "Nombre: %s\nNumero de telefono: %d\nDireccion: %s\nCodigo postal: %d\n%s\nDinero que debe: %.2f\n",
                name, tel, address, codigo_postal, deberDinero ? "Si debe dinero" : "No debe dinero", dinero);
    }
}
