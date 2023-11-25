package modelos;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import vistas.*;

public class ColaAutos {

    private Auto primero;
    private Auto ultimo;
    private int totalProductos;
    private VistaRegistrarProducto vistaRegistrarProducto;

    public ColaAutos(VistaRegistrarProducto vistaRegistrarProducto) {
        this.primero = null;
        this.ultimo = null;
        this.totalProductos = 0;
        this.vistaRegistrarProducto = vistaRegistrarProducto;
    }

    public Auto getPrimero() {
        return primero;
    }

    public void setPrimero(Auto primero) {
        this.primero = primero;
    }

    public Auto getUltimo() {
        return ultimo;
    }

    public void setUltimo(Auto ultimo) {
        this.ultimo = ultimo;
    }

    public int getTotalProductos() {
        return totalProductos;
    }

    public VistaRegistrarProducto getVistaRegistrarProducto() {
        return vistaRegistrarProducto;
    }

    public void setVistaRegistrarProducto(VistaRegistrarProducto vistaRegistrarProducto) {
        this.vistaRegistrarProducto = vistaRegistrarProducto;
    }

    public boolean colaVacia() {
        return getTotalProductos() == 0;
    }

    public void agregar(Auto producto) {
        if (colaVacia()) {
            setPrimero(producto);
            setUltimo(producto);
            getVistaRegistrarProducto().getTxtCodigo().setText("");
            getVistaRegistrarProducto().getTxtMarca().setText("");
            getVistaRegistrarProducto().getTxtModelo().setText("");
            getVistaRegistrarProducto().getTxtPrecio().setText("");
            getVistaRegistrarProducto().getTxtCantidad().setText("");
        } else {
            if (buscar(producto.getCodigo())) {
                getVistaRegistrarProducto().getTxtCodigo().setText("");
                getVistaRegistrarProducto().getTxtMarca().setText("");
                getVistaRegistrarProducto().getTxtModelo().setText("");
                getVistaRegistrarProducto().getTxtPrecio().setText("");
                getVistaRegistrarProducto().getTxtCantidad().setText("");
            } else {
                getUltimo().setSiguiente(producto);
                setUltimo(producto);
                getVistaRegistrarProducto().getTxtCodigo().setText("");
                getVistaRegistrarProducto().getTxtMarca().setText("");
                getVistaRegistrarProducto().getTxtModelo().setText("");
                getVistaRegistrarProducto().getTxtPrecio().setText("");
                getVistaRegistrarProducto().getTxtCantidad().setText("");
            }

        }
        totalProductos++;
    }

    public void agregarProductoTabla(JTable tabla) {
        Auto aux = getPrimero();
        DefaultTableModel modelo = new DefaultTableModel();
        int fila = 0;

        modelo.addColumn("Codigo");
        modelo.addColumn("Marca");
        modelo.addColumn("Modelo");
        modelo.addColumn("Cantidad");
        modelo.addColumn("Precio");
        if (!colaVacia()) {
            while (aux != null) {
                modelo.addRow(new Object[]{});
                modelo.setValueAt(aux.getCodigo(), fila, 0);
                modelo.setValueAt(aux.getMarca(), fila, 1);
                modelo.setValueAt(aux.getModelo(), fila, 2);
                modelo.setValueAt(aux.getCantidad(), fila, 3);
                modelo.setValueAt(aux.getPrecio(), fila, 4);
                fila++;
                aux = aux.getSiguiente();
            }
            tabla.setModel(modelo);
        }
    }

    public boolean buscar(int codigo) {
        if (!colaVacia()) {
            Auto aux = getPrimero();
            while (aux != null) {
                if (aux.getCodigo() == codigo) {
                    return true;
                }
                aux = aux.getSiguiente();
            }
        }
        return false;
    }

    public Auto buscar(String marca) {
        if (!colaVacia()) {
            Auto aux = getPrimero();
            while (aux != null) {
                if (aux.getMarca().equals(marca)) {
                    return aux;
                }
                aux = aux.getSiguiente();
            }
        }
        return null;
    }

    /**
     * Método que permite actualizar la cantidad de un producto.
     *
     * @param codigo del producto.
     * @param cantidad que se le va a asignar al producto.
     */
    public void actualizarCantidad(int codigo, int cantidad) {
        if (!colaVacia()) {
            Auto aux = getPrimero();
            while (aux != null) {
                if (aux.getCodigo() == codigo) {
                    aux.setCantidad(cantidad);
                    return;
                }
                aux = aux.getSiguiente();
            }
        }
    }

    public void eliminarPrimero() {
        if (!colaVacia()) {
            setPrimero(getPrimero().getSiguiente());
            totalProductos--;
        }
    }

    /**
     * Método que permite eliminar un producto de la cola.
     *
     * @param producto que se va a eliminar.
     */
    public void eliminar(Auto producto) {
        if (!colaVacia()) {
            Auto aux = getPrimero();
            Auto anterior = null;
            while (aux != null) {
                if (aux.getCodigo() == producto.getCodigo()) {
                    if (anterior == null) {
                        eliminarPrimero();
                    } else {
                        anterior.setSiguiente(aux.getSiguiente());
                        totalProductos--;
                    }
                    JOptionPane.showMessageDialog(null, "Producto eliminado: " + aux);
                    return;
                }
                anterior = aux;
                aux = aux.getSiguiente();
            }
            JOptionPane.showMessageDialog(null, "Producto no encontrado");
        }
    }

    public void buscarQuitar(int codigo) {
        if (!colaVacia()) {
            Auto aux = getPrimero();
            Auto anterior = null;
            while (aux != null) {
                if (aux.getCodigo() == codigo) {
                    if (anterior == null) {
                        eliminarPrimero();
                    } else {
                        anterior.setSiguiente(aux.getSiguiente());
                        totalProductos--;
                    }
                    System.out.println("Producto eliminado: " + aux);
                    return;
                }
                anterior = aux;
                aux = aux.getSiguiente();
            }
            System.out.println("Producto no encontrado");
        }
    }

    public void limpiar() {
        setPrimero(null);
        setUltimo(null);
        totalProductos = 0;
    }

    public Auto obtenerPrimero() {
        return getPrimero();
    }

    public Auto obtenerUltimo() {
        return getUltimo();
    }

}
