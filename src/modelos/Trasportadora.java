/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelos;

import modelos.superClases.Usuario;

/**
 *
 * @author usuario
 */
public class Trasportadora extends Usuario {

    private Trasportadora sig;

    public Trasportadora(long identificacion, String nombre, String apellidos, String direccion, String telefono, String usuario, String contrasenia) {
        super(identificacion, nombre, apellidos, direccion, telefono, usuario, contrasenia);
        this.sig = null;
    }

    public Trasportadora getSig() {
        return sig;
    }

    public void setSig(Trasportadora sig) {
        this.sig = sig;
    }

}
