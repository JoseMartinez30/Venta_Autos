package modelos;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class ColaPedido {

    private Pedido primero;
    private Pedido ultimo;
    private int totalPedidos;

    public ColaPedido() {
        this.primero = null;
        this.ultimo = null;
        this.totalPedidos = 0;
    }

    public Pedido getPrimero() {
        return primero;
    }

    public void setPrimero(Pedido primero) {
        this.primero = primero;
    }

    public Pedido getUltimo() {
        return ultimo;
    }

    public void setUltimo(Pedido ultimo) {
        this.ultimo = ultimo;
    }

    public int getTotalPedidos() {
        return totalPedidos;
    }

    public boolean colaVacia() {
        return getTotalPedidos() == 0;
    }

    public void agregar(Pedido pedido) {
        if (colaVacia()) {
            setPrimero(pedido);
            setUltimo(pedido);
        } else {
            getUltimo().setSiguiente(pedido);
            setUltimo(pedido);
        }
        totalPedidos++;
    }

    /**
     * Agrega los pedidos a la tabla
     *
     * @param tabla que se va a llenar con los pedidos de la cola
     */
    public void agregarPedidoTabla(JTable tabla) {
        Pedido aux = getPrimero();
        DefaultTableModel modelo = new DefaultTableModel();
        int fila = 0;

        modelo.addColumn("Número de Pedido");
        modelo.addColumn("Fecha");
        modelo.addColumn("Nombre Cliente");
        modelo.addColumn("Nombre Usuario");
        modelo.addColumn("Dirección");
         modelo.addColumn("Cod Producto");
        modelo.addColumn("Producto");
        modelo.addColumn("Cantidad");
        modelo.addColumn("Precio Unitario");
        modelo.addColumn("Total");
        if (!colaVacia()) {
            while (aux != null) {
                modelo.addRow(new Object[]{});
                modelo.setValueAt(aux.getNumeroPedido(), fila, 0);
                modelo.setValueAt(aux.getFecha(), fila, 1);
                modelo.setValueAt(aux.getCliente().getNombre(), fila, 2);
                modelo.setValueAt(aux.getCliente().getUsuario(), fila, 3);
                modelo.setValueAt(aux.getCliente().getDireccion(), fila, 4);
                modelo.setValueAt(aux.getProducto().getCodigo(), fila, 5);
                modelo.setValueAt(aux.getProducto().getMarca(), fila, 6);
                modelo.setValueAt(aux.getCantidad(), fila, 7);
                modelo.setValueAt(aux.getPrecioUnitario(), fila, 8);
                modelo.setValueAt(aux.getTotal(), fila, 9);
                fila++;
                aux = aux.getSiguiente();
            }
            tabla.setModel(modelo);
        }
    }

    public boolean buscar(long numeroPedido) {
        if (!colaVacia()) {
            Pedido aux = getPrimero();
            while (aux != null) {
                if (aux.getNumeroPedido() == numeroPedido) {
                    return true;
                }
                aux = aux.getSiguiente();
            }
        }
        return false;
    }

    public Pedido buscarPedido(long numeroPedido) {
        if (!colaVacia()) {
            Pedido aux = getPrimero();
            while (aux != null) {
                if (aux.getNumeroPedido() == numeroPedido) {
                    return aux;
                }
                aux = aux.getSiguiente();
            }
        }
        return null;
    }

    public void eliminar() {
        if (!colaVacia()) {
            setPrimero(getPrimero().getSiguiente());
            totalPedidos--;
        }
    }

    public void buscarQuitar(long numeroPedido) {
        if (!colaVacia()) {
            Pedido aux = getPrimero();
            Pedido anterior = null;
            while (aux != null) {
                if (aux.getNumeroPedido() == numeroPedido) {
                    if (anterior == null) {
                        eliminar();
                    } else {
                        anterior.setSiguiente(aux.getSiguiente());
                        totalPedidos--;
                    }
                    System.out.println("Pedido eliminado: " + aux);
                    return;
                }
                anterior = aux;
                aux = aux.getSiguiente();
            }
            System.out.println("Pedido no encontrado");
        }
    }

    public void limpiar() {
        setPrimero(null);
        setUltimo(null);
        totalPedidos = 0;
    }

    public Pedido obtenerPrimero() {
        return getPrimero();
    }

    public Pedido obtenerUltimo() {
        return getUltimo();
    }
}
