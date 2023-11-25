/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelos;



/**
 *
 * @author usuario
 */
public class Administrador extends Usuario {

    private Administrador sig;

    public Administrador(long identificacion, String nombre, String apellidos, String direccion, String telefono, String usuario, String contrasenia) {
        super(identificacion, nombre, apellidos, direccion, telefono, usuario, contrasenia);
        this.sig = null;
    }

    public Administrador getSig() {
        return sig;
    }

    public void setSig(Administrador sig) {
        this.sig = sig;
    }

}
