/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelos;

/**
 *
 * @author usuario
 */
public class Cliente extends Usuario {

    private Cliente sig;
    private ColaPedido colaPedidos;

    public Cliente(long identificacion, String nombre, String apellidos, String direccion, String telefono, String usuario, String contrasenia) {
        super(identificacion, nombre, apellidos, direccion, telefono, usuario, contrasenia);
        this.sig = null;
        this.colaPedidos = null;
    }

    public Cliente() {
    }

    public Cliente getSig() {
        return sig;
    }

    public void setSig(Cliente sig) {
        this.sig = sig;
    }

    public ColaPedido getColaPedidos() {
        return colaPedidos;
    }

    public void setColaPedidos(ColaPedido colaPedidos) {
        this.colaPedidos = colaPedidos;
    }

}
