/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controladores;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import modelos.*;
import modelos.interfaces.CajasTxts;
import vistas.*;
import modelos.interfaces.MetodosVistasRegistrar;

/**
 *
 * @author usuario
 */
public class ControllerTransportadora implements ActionListener, MetodosVistasRegistrar, CajasTxts {

    private ListaCircularTrasportadora listaCircularRepartidores;
    private ArchivoTrasportadora archivoRepartidor;
    private VistaRegistrarRepartidor vistaRegistrarRepartidor;
    private VistaVerRepartidor vistaVerRepartidor;

    public ControllerTransportadora(
            ListaCircularTrasportadora listaCircularRepartidores,
            ArchivoTrasportadora archivoRepartidor,
            VistaRegistrarRepartidor vistaRegistrarRepartidor, VistaVerRepartidor vistaVerRepartidor) {

        this.listaCircularRepartidores = listaCircularRepartidores;
        this.vistaRegistrarRepartidor = vistaRegistrarRepartidor;
        this.vistaVerRepartidor = vistaVerRepartidor;
        this.archivoRepartidor = archivoRepartidor;

        archivoRepartidor.leer(listaCircularRepartidores);
        this.vistaRegistrarRepartidor.getBtnIngresar().addActionListener(this);
        this.vistaRegistrarRepartidor.getBtnCancelar().addActionListener(this);
        this.vistaVerRepartidor.getMenuItemEliminar().addActionListener(this);

    }

    public ListaCircularTrasportadora getListaCircularRepartidores() {
        return listaCircularRepartidores;
    }

    public void setListaCircularRepartidores(ListaCircularTrasportadora listaCircularRepartidores) {
        this.listaCircularRepartidores = listaCircularRepartidores;
    }

    public VistaRegistrarRepartidor getVistaRegistrarRepartidor() {
        return vistaRegistrarRepartidor;
    }

    public void setVistaRegistrarRepartidor(VistaRegistrarRepartidor vistaRegistrarRepartidor) {
        this.vistaRegistrarRepartidor = vistaRegistrarRepartidor;
    }

    public ArchivoTrasportadora getArchivoRepartidor() {
        return archivoRepartidor;
    }

    public void setArchivoRepartidor(ArchivoTrasportadora archivoRepartidor) {
        this.archivoRepartidor = archivoRepartidor;
    }

    public VistaVerRepartidor getVistaVerRepartidor() {
        return vistaVerRepartidor;
    }

    public void setVistaVerRepartidor(VistaVerRepartidor vistaVerRepartidor) {
        this.vistaVerRepartidor = vistaVerRepartidor;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Cancelar(e);
        registrar(e);
        eliminarRepartidor(e);
    }

    @Override
    public void registrar(ActionEvent e) {
        if (isCajasTxtLlenas()) {
            getListaCircularRepartidores().setAddFin(
                    getVistaRegistrarRepartidor().getTxtIdentificacion(),
                    getVistaRegistrarRepartidor().getTxtNombre(),
                    getVistaRegistrarRepartidor().getTxtApellidos(),
                    getVistaRegistrarRepartidor().getTxtDireccion(),
                    getVistaRegistrarRepartidor().getTxtTelefono(),
                    getVistaRegistrarRepartidor().getTxtUsuario(),
                    getVistaRegistrarRepartidor().getTxtContrasenia());

        } else if (getVistaRegistrarRepartidor().isVisible()
                && e.getSource() == getVistaRegistrarRepartidor().getBtnIngresar()) {
            JOptionPane.showMessageDialog(null, "Debe llenar todos los campos");
        }
    }

    @Override
    public void Cancelar(ActionEvent e) {
        if (e.getSource() == getVistaRegistrarRepartidor().getBtnCancelar()) {
            getVistaRegistrarRepartidor().dispose();
        }
    }

    @Override
    public boolean isCajasTxtLlenas() {
        if (getVistaRegistrarRepartidor().getTxtNombre().getText().isEmpty()
                || getVistaRegistrarRepartidor().getTxtApellidos().getText().isEmpty()
                || getVistaRegistrarRepartidor().getTxtIdentificacion().getText().isEmpty()
                || getVistaRegistrarRepartidor().getTxtDireccion().getText().isEmpty()
                || getVistaRegistrarRepartidor().getTxtTelefono().getText().isEmpty()
                || getVistaRegistrarRepartidor().getTxtUsuario().getText().isEmpty()
                || getVistaRegistrarRepartidor().getTxtContrasenia().getPassword().length == 0) {
            getVistaRegistrarRepartidor().getTxtNombre().setText("");
            getVistaRegistrarRepartidor().getTxtApellidos().setText("");
            getVistaRegistrarRepartidor().getTxtIdentificacion().setText("");
            getVistaRegistrarRepartidor().getTxtDireccion().setText("");
            getVistaRegistrarRepartidor().getTxtTelefono().setText("");
            getVistaRegistrarRepartidor().getTxtUsuario().setText("");
            getVistaRegistrarRepartidor().getTxtContrasenia().setText("");
            return false;
        } else {
            return true;
        }
    }

    public void eliminarRepartidor(ActionEvent e) {
        if (e.getSource() == getVistaVerRepartidor().getMenuItemEliminar()) {
            int row = getVistaVerRepartidor().getTbRepartidores().getSelectedRow();
            long id = (long) getVistaVerRepartidor().getTbRepartidores().getValueAt(row, 0);
            getListaCircularRepartidores().setEliminarRepartidor(id);
            getArchivoRepartidor().Actualizar(getListaCircularRepartidores());
            getListaCircularRepartidores().cargarInfo(getVistaVerRepartidor().getTbRepartidores());
        }
    }
}
