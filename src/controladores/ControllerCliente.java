/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controladores;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javax.swing.Action;
import javax.swing.JOptionPane;
import javax.swing.event.AncestorListener;
import javax.swing.table.DefaultTableModel;

import modelos.ArchivoCliente;
import modelos.Cliente;
import modelos.*;
import modelos.interfaces.CajasTxts;
import vistas.*;
import modelos.interfaces.MetodosVistasRegistrar;

/**
 *
 * @author usuario
 */
public class ControllerCliente implements ActionListener, CajasTxts, MetodosVistasRegistrar {

    private Cliente cliente;
    private Producto producto;
    private Pedido pedido;
    private ListaCircularClientes listaCircularClientes;
    private ColaProducto colaProducto;
    private ColaPedido colaPedido;
    private ArchivoCliente archivoCliente;
    private ArchivoPedido archivoPedido;
    private ArchivoProducto archivoProducto;
    private VistaRegistrarCliente vistaRegistrarCliente;
    private VistaCarrito vistaCarrito;
    private VistaHistorial vistaHistorial;
    private VistaCompras vistaCompras;
    private VistaVerClientes vistaVerClientes;
    private ControllerIniciarSesion controllerIniciarSesion;

    public ControllerCliente(Cliente cliente, Producto producto, ArchivoPedido archivoPedido, Pedido pedido, ArchivoProducto archivoProducto, ListaCircularClientes listaCircularClientes,
            ColaProducto colaProducto, ColaPedido colaPedido, ArchivoCliente archivoCliente,
            VistaRegistrarCliente vistaRegistrarCliente, VistaCarrito vistaCarrito,
            VistaHistorial vistaHistorial, VistaCompras vistaCompras,
            VistaVerClientes vistaVerClientes, ControllerIniciarSesion controllerIniciarSesion) {
        this.cliente = cliente;
        this.producto = producto;
        this.pedido = pedido;
        this.listaCircularClientes = listaCircularClientes;
        this.archivoProducto = archivoProducto;
        this.colaProducto = colaProducto;
        this.colaPedido = colaPedido;
        this.archivoCliente = archivoCliente;
        this.vistaRegistrarCliente = vistaRegistrarCliente;
        this.vistaCarrito = vistaCarrito;
        this.vistaHistorial = vistaHistorial;
        this.vistaCompras = vistaCompras;
        this.vistaVerClientes = vistaVerClientes;
        this.controllerIniciarSesion = controllerIniciarSesion;
        this.archivoPedido = archivoPedido;

        archivoCliente.leer(listaCircularClientes); // Se leen los datos del archivo.
        vistaRegistrarCliente.getBtnIngresar().addActionListener(this);
        vistaRegistrarCliente.getBtnCancelar().addActionListener(this);
        vistaVerClientes.getPopMenuEliminar().addActionListener(this);
        vistaCompras.getBtnComprar().addActionListener(this);
        vistaHistorial.getBtnRegresar().addActionListener(this);
        vistaCarrito.getBtnTerminarCompra().addActionListener(this);
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public ArchivoProducto getArchivoProducto() {
        return archivoProducto;
    }

    public void setArchivoProducto(ArchivoProducto archivoProducto) {
        this.archivoProducto = archivoProducto;
    }

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    public VistaVerClientes getVistaVerClientes() {
        return vistaVerClientes;
    }

    public void setVistaVerClientes(VistaVerClientes vistaVerClientes) {
        this.vistaVerClientes = vistaVerClientes;
    }

    public ListaCircularClientes getListaCircularClientes() {
        return listaCircularClientes;
    }

    public void setListaCircularClientes(ListaCircularClientes listaCircularClientes) {
        this.listaCircularClientes = listaCircularClientes;
    }

    public VistaCarrito getVistaCarrito() {
        return vistaCarrito;
    }

    public void setVistaCarrito(VistaCarrito vistaCarrito) {
        this.vistaCarrito = vistaCarrito;
    }

    public VistaHistorial getVistaHistorial() {
        return vistaHistorial;
    }

    public void setVistaHistorial(VistaHistorial vistaHistorial) {
        this.vistaHistorial = vistaHistorial;
    }

    public VistaRegistrarCliente getVistaRegistrarCliente() {
        return vistaRegistrarCliente;
    }

    public void setVistaRegistrarCliente(VistaRegistrarCliente vistaRegistrarCliente) {
        this.vistaRegistrarCliente = vistaRegistrarCliente;
    }

    public VistaCompras getVistaCompras() {
        return vistaCompras;
    }

    public void setVistaCompras(VistaCompras vistaCompras) {
        this.vistaCompras = vistaCompras;
    }

    public ArchivoCliente getArchivoCliente() {
        return archivoCliente;
    }

    public void setArchivoCliente(ArchivoCliente archivoCliente) {
        this.archivoCliente = archivoCliente;
    }

    public ColaProducto getColaProducto() {
        return colaProducto;
    }

    public void setColaProducto(ColaProducto colaProducto) {
        this.colaProducto = colaProducto;
    }

    public ColaPedido getColaPedido() {
        return colaPedido;
    }

    public void setColaPedido(ColaPedido colaPedido) {
        this.colaPedido = colaPedido;
    }

    public ArchivoPedido getArchivoPedido() {
        return archivoPedido;
    }

    public void setArchivoPedido(ArchivoPedido archivoPedido) {
        this.archivoPedido = archivoPedido;
    }

    public ControllerIniciarSesion getControllerIniciarSesion() {
        return controllerIniciarSesion;
    }

    public void setControllerIniciarSesion(ControllerIniciarSesion controllerIniciarSesion) {
        this.controllerIniciarSesion = controllerIniciarSesion;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        registrar(e);
        Cancelar(e);
        eliminarCliente(e);
        comprar(e);
        regresarAcompras(e);

    }

    public void registrar(ActionEvent e) {
        if (e.getSource() == getVistaRegistrarCliente().getBtnCancelar()) {
            getVistaRegistrarCliente().dispose();
        } else if (isCajasTxtLlenas()) {
            getListaCircularClientes().setAddFin(
                    getVistaRegistrarCliente().getTxtIdentificacion(),
                    getVistaRegistrarCliente().getTxtNombre(),
                    getVistaRegistrarCliente().getTxtApellidos(),
                    getVistaRegistrarCliente().getTxtDireccion(),
                    getVistaRegistrarCliente().getTxtTelefono(),
                    getVistaRegistrarCliente().getTxtUsuario(),
                    getVistaRegistrarCliente().getTxtContrasenia());
        } else if (getVistaRegistrarCliente().isVisible()
                && e.getSource() == getVistaRegistrarCliente().getBtnIngresar()) {
            JOptionPane.showMessageDialog(null, "Debe llenar todos los campos");
        }
    }

    @Override
    public void Cancelar(ActionEvent e) {
        if (e.getSource() == getVistaRegistrarCliente().getBtnCancelar()) {
            getVistaRegistrarCliente().dispose();
        }
    }

    @Override
    public boolean isCajasTxtLlenas() {
        if (getVistaRegistrarCliente().getTxtNombre().getText().isEmpty()
                || getVistaRegistrarCliente().getTxtApellidos().getText().isEmpty()
                || getVistaRegistrarCliente().getTxtIdentificacion().getText().isEmpty()
                || getVistaRegistrarCliente().getTxtDireccion().getText().isEmpty()
                || getVistaRegistrarCliente().getTxtTelefono().getText().isEmpty()
                || getVistaRegistrarCliente().getTxtUsuario().getText().isEmpty()
                || getVistaRegistrarCliente().getTxtContrasenia().getPassword().length == 0) {
            getVistaRegistrarCliente().getTxtNombre().setText("");
            getVistaRegistrarCliente().getTxtApellidos().setText("");
            getVistaRegistrarCliente().getTxtIdentificacion().setText("");
            getVistaRegistrarCliente().getTxtDireccion().setText("");
            getVistaRegistrarCliente().getTxtTelefono().setText("");
            getVistaRegistrarCliente().getTxtUsuario().setText("");
            getVistaRegistrarCliente().getTxtContrasenia().setText("");
            return false;
        } else {
            return true;
        }
    }

    public void cargarTablaClientes(ActionEvent e) {
        if (getVistaVerClientes().isVisible()) {
            getListaCircularClientes().cargarInfo();
        }

    }

    public void mostarVista(ActionEvent e) {
        getVistaVerClientes().setLocationRelativeTo(null);
        getVistaVerClientes().setVisible(true);
    }

    public void comprar(ActionEvent e) {
        if (e.getSource() == getVistaCompras().getBtnComprar()) {
            if (getControllerIniciarSesion().getCliente() != null) {
                setCliente(getControllerIniciarSesion().getCliente()); // se obtiene el cliente que inicio sesión
                long numeroPedido = 1 + getColaPedido().getTotalPedidos();
                LocalDateTime fecha = LocalDateTime.now();
                DateTimeFormatter formato = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                String fechaPedido = fecha.format(formato);
                String direccion = getCliente().getDireccion();
                int fila = getVistaCompras().getTbCompras().getSelectedRow();
                setProducto(getProductoSeleccionado(fila));

                String marca = getVistaCompras().getTbCompras().getValueAt(fila, 1).toString();
                String modelo = getVistaCompras().getTbCompras().getValueAt(fila, 2).toString();
                int cantidad = Integer.parseInt(getVistaCompras().getTbCompras().getValueAt(fila, 3).toString());
                double precio = Double.parseDouble(getVistaCompras().getTbCompras().getValueAt(fila, 4).toString());
                int cantidadComprar = Integer.parseInt(JOptionPane.showInputDialog("Ingrese la cantidad de productos que desea comprar: "));

                if (cantidadComprar > cantidad) {
                    JOptionPane.showMessageDialog(null, "No hay suficientes productos en stock");
                    return;
                } else if (cantidadComprar == 0 || cantidadComprar < 0) {
                    JOptionPane.showMessageDialog(null, "Debe ingresar una cantidad mayor a 0");
                    return;
                } else if (cantidadComprar == cantidad) {
                    getColaProducto().eliminar(getProducto());
                    DefaultTableModel defaultTableModel = (DefaultTableModel) getVistaCompras().getTbCompras().getModel();
                    defaultTableModel.removeRow(fila);
                    getColaProducto().agregarProductoTabla(getVistaCompras().getTbCompras());

                } else {
                    getProducto().setCantidad(cantidad - cantidadComprar);
                    getColaProducto().agregarProductoTabla(getVistaCompras().getTbCompras());
                    getArchivoProducto().Actualizar(getColaProducto());

                }
                double precioUnitario = getProducto().getPrecio();
                System.out.println("" + precioUnitario);
                double total = precioUnitario * cantidadComprar;
                String estado = "En espera";
                Pedido pedido = new Pedido(numeroPedido, marca, getCliente(), direccion, getProducto(), cantidadComprar, precioUnitario, total, estado);
                setPedido(pedido);
                getColaPedido().agregar(getPedido());
                getColaPedido().agregarPedidoTabla(getVistaCarrito().getTbCarrito());
            } else {
                JOptionPane.showMessageDialog(null, "Debe iniciar sesión para poder comprar",
                        "Error", JOptionPane.ERROR_MESSAGE);
            }

        }

    }

    public void regresarAcompras(ActionEvent e) {
        if (e.getSource() == getVistaCarrito().getBtnTerminarCompra()) {
            int salida = JOptionPane.showConfirmDialog(null, "Desea continuar con la compra");
            switch (salida) {
                case 0:
                    getVistaCarrito().dispose();
                    getVistaCompras().setLocationRelativeTo(null);
                    getVistaCompras().setVisible(true);
                    JOptionPane.showMessageDialog(null, "Pedido realizado con éxito", "Pedido",
                            JOptionPane.INFORMATION_MESSAGE);
                    getArchivoPedido().guardar(getPedido());
                    break;
                case 1:

                    break;
                case 2:

                    break;
                default:
                    throw new AssertionError();
            }
        }
    }

    public void eliminarCliente(ActionEvent e) {
        if (e.getSource() == getVistaVerClientes().getPopMenuEliminar()) {
            int row = getVistaVerClientes().getTbClientes().getSelectedRow();
            long id = (long) getVistaVerClientes().getTbClientes().getValueAt(row, 0);
            getListaCircularClientes().setEliminarCliente(id);
            getArchivoCliente().Actualizar(getListaCircularClientes());
            getListaCircularClientes().cargarInfo(getVistaVerClientes().getTbClientes());
        }
    }

    public Producto getProductoSeleccionado(int fila) {
        Producto aux = getColaProducto().getPrimero();
        int i = 0;
        while (aux != null && i < fila) {
            aux = aux.getSiguiente();
            i++;
        }
        return aux;
    }
}
