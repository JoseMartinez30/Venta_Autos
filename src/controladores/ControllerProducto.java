/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controladores;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import modelos.ArchivoAdmin;
import modelos.ArchivoProducto;
import modelos.ColaProducto;
import modelos.ListaCircularAdmins;
import modelos.Producto;
import modelos.interfaces.CajasTxts;
import vistas.*;
import modelos.interfaces.MetodosVistasRegistrar;

/**
 *
 * @author usuario
 */
public class ControllerProducto implements ActionListener, MetodosVistasRegistrar, CajasTxts {
    
    private ColaProducto colaProducto;
    private VistaRegistrarProducto vistaRegistrarProducto;
    private VistaCompras vistaCompras;
    private ArchivoProducto archivoProducto;
    
    public ControllerProducto(
            ColaProducto colaProducto,
            VistaRegistrarProducto vistaRegistrarProducto,
            VistaCompras vistaCompras,
            ArchivoProducto archivoProducto) {
        this.colaProducto = colaProducto;
        this.vistaRegistrarProducto = vistaRegistrarProducto;
        this.vistaCompras = vistaCompras;
        this.archivoProducto = archivoProducto;
        
        this.archivoProducto.leer(colaProducto);
        vistaRegistrarProducto.getBtnCancelar().addActionListener(this);
        vistaRegistrarProducto.getBtnIngresar().addActionListener(this);
        vistaCompras.getBtnComprar().addActionListener(this);
        
    }
    
    public ColaProducto getColaProducto() {
        return colaProducto;
    }
    
    public void setColaProducto(ColaProducto colaProducto) {
        this.colaProducto = colaProducto;
    }
    
    public ArchivoProducto getArchivoProducto() {
        return archivoProducto;
    }
    
    public void setArchivoProducto(ArchivoProducto archivoProducto) {
        this.archivoProducto = archivoProducto;
    }
    
    public VistaRegistrarProducto getVistaRegistrarProducto() {
        return vistaRegistrarProducto;
    }
    
    public void setVistaRegistrarProducto(VistaRegistrarProducto vistaRegistrarProducto) {
        this.vistaRegistrarProducto = vistaRegistrarProducto;
    }
    
    public VistaCompras getVistaCompras() {
        return vistaCompras;
    }
    
    public void setVistaCompras(VistaCompras vistaCompras) {
        this.vistaCompras = vistaCompras;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        registrar(e);
        Cancelar(e);
    }
    
    @Override
    public void registrar(ActionEvent e) {
        
        if (isCajasTxtLlenas()) {
            int codigo = Integer.parseInt(getVistaRegistrarProducto().getTxtCodigo().getText());
            String marca = getVistaRegistrarProducto().getTxtMarca().getText();
            String modelo = getVistaRegistrarProducto().getTxtModelo().getText();
            float precio = Float.parseFloat(getVistaRegistrarProducto().getTxtPrecio().getText());
            int cantidad = Integer.parseInt(getVistaRegistrarProducto().getTxtCantidad().getText());
            Producto producto = new Producto(codigo, marca, modelo, precio, cantidad);
            getColaProducto().agregar(producto);
            getColaProducto().agregarProductoTabla(getVistaCompras().getTbCompras());
            getArchivoProducto().guardar(producto);
        } else if (getVistaRegistrarProducto().isVisible()
                && e.getSource() == getVistaRegistrarProducto().getBtnIngresar()) {
            JOptionPane.showMessageDialog(null, "Debe llenar todos los campos");
        }
    }
    
    @Override
    public void Cancelar(ActionEvent e) {
        if (e.getSource() == getVistaRegistrarProducto().getBtnCancelar()) {
            getVistaRegistrarProducto().dispose();
        }
    }
    
    @Override
    public boolean isCajasTxtLlenas() {
        if (getVistaRegistrarProducto().getTxtCantidad().getText().isEmpty()
                || getVistaRegistrarProducto().getTxtCodigo().getText().isEmpty()
                || getVistaRegistrarProducto().getTxtMarca().getText().isEmpty()
                || getVistaRegistrarProducto().getTxtModelo().getText().isEmpty()
                || getVistaRegistrarProducto().getTxtPrecio().getText().isEmpty()) {
            getVistaRegistrarProducto().getTxtCantidad().setText("");
            getVistaRegistrarProducto().getTxtCodigo().setText("");
            getVistaRegistrarProducto().getTxtMarca().setText("");
            getVistaRegistrarProducto().getTxtModelo().setText("");
            getVistaRegistrarProducto().getTxtPrecio().setText("");
            return false;
        } else {
            return true;
        }
    }
    
}
