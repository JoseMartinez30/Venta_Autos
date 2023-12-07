/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controladores;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import modelos.ArchivoAdmin;
import modelos.ListaCircularAdmins;
import modelos.interfaces.CajasTxts;
import vistas.*;
import modelos.interfaces.MetodosVistasRegistrar;

/**
 *
 * @author usuario
 */
public class ControllerAdmins implements ActionListener, MetodosVistasRegistrar, CajasTxts {

    private ListaCircularAdmins listaCircularAdmins;
    private ArchivoAdmin archivoAdmin;
    private VistaRegistrarAdministrador vistaRegistrarAdministrador;
    private VistaAdministrador vistaAdministrador;
    private VistaRegistrarProducto vistaRegistrarProducto;
    private VistaVerClientes vistaVerClientes;
    private VistaVerRepartidor vistaVerRepartidor;
    private VistaPedidosEntregar vistaPedidosEntregar;
    private VistaPedidosEntregados vistaPedidosEntregados;
    private VistaVerAdmins vistaVerAdmins;

    public ControllerAdmins(ListaCircularAdmins listaCircularAdmins, ArchivoAdmin archivoAdmin,
            VistaRegistrarAdministrador vistaRegistrarAdministrador, VistaAdministrador vistaAdministrador,
            VistaRegistrarProducto vistaRegistrarProducto, VistaVerClientes vistaVerClientes,
            VistaVerRepartidor vistaVerRepartidor, VistaPedidosEntregar vistaPedidosEntregar,
            VistaPedidosEntregados vistaPedidosEntregados, VistaVerAdmins vistaVerAdmins) {
        this.listaCircularAdmins = listaCircularAdmins;
        this.archivoAdmin = archivoAdmin;
        this.vistaRegistrarAdministrador = vistaRegistrarAdministrador;
        this.vistaAdministrador = vistaAdministrador;
        this.vistaRegistrarProducto = vistaRegistrarProducto;
        this.vistaVerClientes = vistaVerClientes;
        this.vistaVerRepartidor = vistaVerRepartidor;
        this.vistaPedidosEntregar = vistaPedidosEntregar;
        this.vistaPedidosEntregados = vistaPedidosEntregados;
        this.vistaVerAdmins = vistaVerAdmins;

        archivoAdmin.leer(listaCircularAdmins);
        vistaRegistrarAdministrador.getBtnIngresar().addActionListener(this);
        vistaRegistrarAdministrador.getBtnCancelar().addActionListener(this);
        vistaAdministrador.getBtnRevisar().addActionListener(this);
        vistaAdministrador.getComboOpciones().addActionListener(this);
    }

    public ListaCircularAdmins getListaCircularAdmins() {
        return listaCircularAdmins;
    }

    public void setListaCircularAdmins(ListaCircularAdmins listaCircularAdmins) {
        this.listaCircularAdmins = listaCircularAdmins;
    }

    public VistaAdministrador getVistaAdministrador() {
        return vistaAdministrador;
    }

    public void setVistaAdministrador(VistaAdministrador vistaAdministrador) {
        this.vistaAdministrador = vistaAdministrador;
    }

    public VistaRegistrarAdministrador getVistaRegistrarAdministrador() {
        return vistaRegistrarAdministrador;
    }

    public void setVistaRegistrarAdministrador(VistaRegistrarAdministrador vistaRegistrarAdministrador) {
        this.vistaRegistrarAdministrador = vistaRegistrarAdministrador;
    }

    public ArchivoAdmin getArchivoAdmin() {
        return archivoAdmin;
    }

    public void setArchivoAdmin(ArchivoAdmin archivoAdmin) {
        this.archivoAdmin = archivoAdmin;
    }

    public VistaRegistrarProducto getVistaRegistrarProducto() {
        return vistaRegistrarProducto;
    }

    public void setVistaRegistrarProducto(VistaRegistrarProducto vistaRegistrarProducto) {
        this.vistaRegistrarProducto = vistaRegistrarProducto;
    }

    public VistaVerClientes getVistaVerClientes() {
        return vistaVerClientes;
    }

    public void setVistaVerClientes(VistaVerClientes vistaVerClientes) {
        this.vistaVerClientes = vistaVerClientes;
    }

    public VistaVerRepartidor getVistaVerRepartidor() {
        return vistaVerRepartidor;
    }

    public void setVistaVerRepartidor(VistaVerRepartidor vistaVerRepartidor) {
        this.vistaVerRepartidor = vistaVerRepartidor;
    }

    public VistaPedidosEntregar getVistaPedidosEntregar() {
        return vistaPedidosEntregar;
    }

    public void setVistaPedidosEntregar(VistaPedidosEntregar vistaPedidosEntregar) {
        this.vistaPedidosEntregar = vistaPedidosEntregar;
    }

    public VistaPedidosEntregados getVistaPedidosEntregados() {
        return vistaPedidosEntregados;
    }

    public void setVistaPedidosEntregados(VistaPedidosEntregados vistaPedidosEntregados) {
        this.vistaPedidosEntregados = vistaPedidosEntregados;
    }

    public VistaVerAdmins getVistaVerAdmins() {
        return vistaVerAdmins;
    }

    public void setVistaVerAdmins(VistaVerAdmins vistaVerAdmins) {
        this.vistaVerAdmins = vistaVerAdmins;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        registrar(e);
        Cancelar(e);
        cargarVistas(e);
        eliminarAdministrador(e);
    }

    @Override
    public void registrar(ActionEvent e) {

        if (isCajasTxtLlenas()) {
            getListaCircularAdmins().setAddFin(
                    getVistaRegistrarAdministrador().getTxtIdentificacion(),
                    getVistaRegistrarAdministrador().getTxtNombre(),
                    getVistaRegistrarAdministrador().getTxtApellidos(),
                    getVistaRegistrarAdministrador().getTxtDireccion(),
                    getVistaRegistrarAdministrador().getTxtTelefono(),
                    getVistaRegistrarAdministrador().getTxtUsuario(),
                    getVistaRegistrarAdministrador().getTxtContrasenia());

        } else if (getVistaRegistrarAdministrador().isVisible()
                && e.getSource() == getVistaRegistrarAdministrador().getBtnIngresar()) {
            JOptionPane.showMessageDialog(null, "Debe llenar todos los campos");
        }
    }

    @Override
    public void Cancelar(ActionEvent e) {
        if (e.getSource() == getVistaRegistrarAdministrador().getBtnCancelar()) {
            getVistaRegistrarAdministrador().dispose();
        }
    }

    @Override
    public boolean isCajasTxtLlenas() {
        if (getVistaRegistrarAdministrador().getTxtNombre().getText().isEmpty()
                || getVistaRegistrarAdministrador().getTxtApellidos().getText().isEmpty()
                || getVistaRegistrarAdministrador().getTxtIdentificacion().getText().isEmpty()
                || getVistaRegistrarAdministrador().getTxtDireccion().getText().isEmpty()
                || getVistaRegistrarAdministrador().getTxtTelefono().getText().isEmpty()
                || getVistaRegistrarAdministrador().getTxtUsuario().getText().isEmpty()
                || getVistaRegistrarAdministrador().getTxtContrasenia().getPassword().length == 0) {
            getVistaRegistrarAdministrador().getTxtNombre().setText("");
            getVistaRegistrarAdministrador().getTxtApellidos().setText("");
            getVistaRegistrarAdministrador().getTxtIdentificacion().setText("");
            getVistaRegistrarAdministrador().getTxtDireccion().setText("");
            getVistaRegistrarAdministrador().getTxtTelefono().setText("");
            getVistaRegistrarAdministrador().getTxtUsuario().setText("");
            getVistaRegistrarAdministrador().getTxtContrasenia().setText("");
            return false;
        } else {
            return true;
        }
    }

    public void cargarVistas(ActionEvent e) {
        if (e.getSource() == getVistaAdministrador().getBtnRevisar()) {
            int valCombo = getVistaAdministrador().getComboOpciones().getSelectedIndex();
            switch (valCombo) {
                case 0 ->
                    JOptionPane.showMessageDialog(null, "Debe seleccionar una opcion");
                case 1 -> {
                    getVistaRegistrarProducto().setLocationRelativeTo(null);
                    getVistaRegistrarProducto().setVisible(true);
                }
                case 2 -> {
                    getVistaVerClientes().setLocationRelativeTo(null);
                    getVistaVerClientes().setVisible(true);
                }
                case 3 -> {
                    getVistaVerRepartidor().setLocationRelativeTo(null);
                    getVistaVerRepartidor().setVisible(true);
                }
                case 4 -> {
                    getVistaPedidosEntregar().setLocationRelativeTo(null);
                    getVistaPedidosEntregar().setVisible(true);
                }
                case 5 -> {
                    getVistaPedidosEntregados().setLocationRelativeTo(null);
                    getVistaPedidosEntregados().setVisible(true);
                }
                case 6 -> {
                    getVistaVerAdmins().setLocationRelativeTo(null);
                    getVistaVerAdmins().setVisible(true);
                }
                default ->
                    throw new AssertionError();
            }
        }
    }

    public void eliminarAdministrador(ActionEvent e) {
        if (e.getSource() == getVistaVerClientes().getPopMenuEliminar()) {
            int row = getVistaVerClientes().getTbClientes().getSelectedRow();
            long id = (long) getVistaVerClientes().getTbClientes().getValueAt(row, 0);
            getListaCircularAdmins().setEliminarAdministrador(id);
            getArchivoAdmin().Actualizar(getListaCircularAdmins());
            getListaCircularAdmins().cargarInfo(getVistaVerAdmins().getTbAdmins());
        }
    }
}
