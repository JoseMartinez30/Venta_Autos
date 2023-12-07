/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controladores;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import modelos.interfaces.CajasTxts;
import modelos.*;
import vistas.*;
import modelos.interfaces.MetodosVistasRegistrar;

/**
 *
 * @author usuario
 */
public class ControllerIniciarSesion implements ActionListener, CajasTxts {

    private ListaCircularAdmins listaCircularAdmins;
    private ListaCircularClientes listaCircularClientes;
    private ListaCircularTrasportadora listaCircularTrasportadora;
    private ListaCircularTrasportadora transportadoras;
    private Cliente cliente;
    private vistas.VistaIniciarSesion vistaIniciarSesion;
    private VistaCompras vistaCompras;
    private VistaAdministrador vistaAdministrador;
    private VistaRepartidor vistaRepartidor;

    public ControllerIniciarSesion(ListaCircularAdmins listaCircularAdmins,
            ListaCircularClientes listaCircularClientes, ListaCircularTrasportadora listaCircularTrasportadora,
            ListaCircularTrasportadora repartidores, Cliente cliente, VistaIniciarSesion vistaIniciarSesion,
            VistaCompras vistaCompras, VistaAdministrador vistaAdministrador, VistaRepartidor vistaRepartidor) {
        this.listaCircularAdmins = listaCircularAdmins;
        this.listaCircularClientes = listaCircularClientes;
        this.listaCircularTrasportadora = listaCircularTrasportadora;
        this.transportadoras = repartidores;
        this.cliente = cliente;
        this.vistaIniciarSesion = vistaIniciarSesion;
        this.vistaCompras = vistaCompras;
        this.vistaAdministrador = vistaAdministrador;
        this.vistaRepartidor = vistaRepartidor;

        vistaIniciarSesion.getBtnIngresar().addActionListener(this);
    }

    public ListaCircularAdmins getListaCircularAdmins() {
        return listaCircularAdmins;
    }

    public void setListaCircularAdmins(ListaCircularAdmins listaCircularAdmins) {
        this.listaCircularAdmins = listaCircularAdmins;
    }

    public ListaCircularClientes getListaCircularClientes() {
        return listaCircularClientes;
    }

    public void setListaCircularClientes(ListaCircularClientes listaCircularClientes) {
        this.listaCircularClientes = listaCircularClientes;
    }

    public ListaCircularTrasportadora getListaCircularTrasportadora() {
        return listaCircularTrasportadora;
    }

    public void setListaCircularTrasportadora(ListaCircularTrasportadora listaCircularTrasportadora) {
        this.listaCircularTrasportadora = listaCircularTrasportadora;
    }

    public ListaCircularTrasportadora getTransportadoras() {
        return transportadoras;
    }

    public void setTransportadoras(ListaCircularTrasportadora transportadoras) {
        this.transportadoras = transportadoras;
    }

    public VistaAdministrador getVistaAdministrador() {
        return vistaAdministrador;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public VistaIniciarSesion getVistaIniciarSesion() {
        return vistaIniciarSesion;
    }

    public void setVistaIniciarSesion(VistaIniciarSesion vistaIniciarSesion) {
        this.vistaIniciarSesion = vistaIniciarSesion;
    }

    public VistaCompras getVistaCompras() {
        return vistaCompras;
    }

    public void setVistaCompras(VistaCompras vistaCompras) {
        this.vistaCompras = vistaCompras;
    }

    public void setVistaAdministrador(VistaAdministrador vistaAdministrador) {
        this.vistaAdministrador = vistaAdministrador;
    }

    public VistaRepartidor getVistaRepartidor() {
        return vistaRepartidor;
    }

    public void setVistaRepartidor(VistaRepartidor vistaRepartidor) {
        this.vistaRepartidor = vistaRepartidor;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        iniciarSeision(e);
    }

    @Override
    public boolean isCajasTxtLlenas() {
        if (getVistaIniciarSesion().getTxtUsuario().getText().isEmpty()
                || getVistaIniciarSesion().getTxtContrasenia().getPassword().length == 0) {
            getVistaIniciarSesion().getTxtUsuario().setText("");
            getVistaIniciarSesion().getTxtContrasenia().setText("");
            return false;
        } else {
            return true;
        }
    }

    public void iniciarSeision(ActionEvent e) {
        if (e.getSource() == getVistaIniciarSesion().getBtnIngresar()) {
            if (isCajasTxtLlenas()) {
                String usuario = getVistaIniciarSesion().getTxtUsuario().getText();
                char[] passwordChars = getVistaIniciarSesion().getTxtContrasenia().getPassword();
                String password = new String(passwordChars);
                String contrasenia = new String(password);
                System.out.println(""+contrasenia);
                cargarVistas(usuario, contrasenia);
            } else {
                JOptionPane.showMessageDialog(null, "Debe llenar todos los campos");
            }
        }
    }

    public void cargarVistas(String usuario, String contrasenia) {
        if (isCajasTxtLlenas()) {
            if (getListaCircularAdmins().getBuscarAdministrador(usuario, contrasenia)) {
                getVistaIniciarSesion().getTxtUsuario().setText("");
                getVistaIniciarSesion().getTxtContrasenia().setText("");
                getVistaAdministrador().setLocationRelativeTo(null);
                getVistaAdministrador().setVisible(true);
            } else if (getListaCircularClientes().getBuscarCliente(usuario, contrasenia)) {
                setCliente(getListaCircularClientes().getObCliente(usuario, contrasenia));
                getVistaIniciarSesion().getTxtUsuario().setText("");
                getVistaIniciarSesion().getTxtContrasenia().setText("");
                getVistaCompras().setLocationRelativeTo(null);
                getVistaCompras().setVisible(true);
            } else if (getListaCircularTrasportadora().getBuscarCliente(usuario, contrasenia)) {
                getVistaIniciarSesion().getTxtUsuario().setText("");
                getVistaIniciarSesion().getTxtContrasenia().setText("");
                getVistaRepartidor().setLocationRelativeTo(null);
                getVistaRepartidor().setVisible(true);
            } else {
                JOptionPane.showMessageDialog(null, "Contraseña o usuario incorrectos");
                JOptionPane.showMessageDialog(null, "Intenta nuevamente");
                getVistaIniciarSesion().getTxtUsuario().setText("");
                getVistaIniciarSesion().getTxtContrasenia().setText("");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Debe llenar todos los campos");
            getVistaIniciarSesion().getTxtUsuario().setText("");
            getVistaIniciarSesion().getTxtContrasenia().setText("");
        }

    }
}
