/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelos;

import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import vistas.*;

/**
 *
 * @author usuario
 */
public class ListaCircularHistorialPedido {

    Pedido cab;
    private VistaCarrito vistaCarrito;
    private ArchivoHistoriaPedido archivoHistorialPedido;

    public ListaCircularHistorialPedido(Pedido cab, VistaCarrito vistaCarrito, ArchivoHistoriaPedido archivoHistorialPedido) {
        this.cab = cab;
        this.vistaCarrito = vistaCarrito;
        this.archivoHistorialPedido = archivoHistorialPedido;
    }

    public Pedido getCab() {
        return cab;
    }

    public void setCab(Pedido cab) {
        this.cab = cab;
    }

    public VistaCarrito getVistaCarrito() {
        return vistaCarrito;
    }

    public void setVistaCarrito(VistaCarrito vistaCarrito) {
        this.vistaCarrito = vistaCarrito;
    }

    public ArchivoHistoriaPedido getArchivoHistorialPedido() {
        return archivoHistorialPedido;
    }

    public void setArchivoHistorialPedido(ArchivoHistoriaPedido archivoHistorialPedido) {
        this.archivoHistorialPedido = archivoHistorialPedido;
    }

    public Pedido getBuscarPedido(int cod) {
        Pedido q = null;
        if (cab == null) {
            return null;
        } else {
            q = cab;
            do {
                if (q.getNumeroPedido()== cod) {
                    return q;
                } else {
                    q = q.getSiguiente();
                }
            } while (q != cab);
            return null;
        }
    }

    public Pedido getUltimo() {
        Pedido q;
        if (cab == null) {
            return null;
        } else {
            q = cab;
            while (q.getSiguiente()!= cab) {
                q = q.getSiguiente();
            }
            return q;
        }
    }

    public void setAddFin(int cod, Cliente cliente, Producto producto) {

        Pedido info = new Pedido(cod, cliente, producto);
        Pedido q;
        if (info != null) {
            if (cab == null) {
                cab = info;
                cab.setSiguiente(cab);
                JOptionPane.showMessageDialog(null,
                        "Pedido registrado.  Un pedido en la lista!");
               getArchivoHistorialPedido().guardar(info);
            } else {
                q = getUltimo();
                q.setSiguiente(info);
                info.setSiguiente(cab);
               getArchivoHistorialPedido().guardar(info);
                JOptionPane.showMessageDialog(null,
                        "pedido agregado al final de la lista!");
            }
        }

    }

    public void setAddFin(Pedido pedido) {
        Pedido q;
        if (pedido != null) {
            if (cab == null) {
                cab = pedido;
                cab.setSiguiente(cab);
            } else {
                q = getUltimo();
                q.setSiguiente(pedido);
                pedido.setSiguiente(cab);
            }
        }
    }

    public Pedido getAnterior(Pedido q) {
        if ((cab == null) || (q == null)) {
            return null;
        } else {
            Pedido a = cab;
            while (a.getSiguiente()!= q) {
                a = a.getSiguiente();
            }
            return a;
        }
    }

    public void setEliminarCliente(int id) {
        if (cab == null) {
            JOptionPane.showMessageDialog(null,
                    "Lista vacía!!!");
        } else {
            Pedido q = getBuscarPedido(id);
            Pedido u, a;
            if (q == null) {
                JOptionPane.showMessageDialog(null,
                        "El codigo no existe!!!");
            } else {
                if ((cab.getSiguiente()== cab) && (q == cab)) {
                    cab = null;
                    JOptionPane.showMessageDialog(null,
                            "Eliminado. La Lista esta vacía!!!");
                } else if ((cab.getSiguiente()!= cab) && (q == cab)) {
                    u = getUltimo();
                    cab = cab.getSiguiente();
                    u.setSiguiente(cab);
                    q.setSiguiente(null);
                    q = null;
                    JOptionPane.showMessageDialog(null,
                            "Eliminado al inicio!!!");
                } else if (q.getSiguiente()== cab) {
                    a = getAnterior(q);
                    a.setSiguiente(cab);
                    q = null;
                    JOptionPane.showMessageDialog(null,
                            "Eliminado al final!!!");
                } else {
                    a = getAnterior(q);
                    a.setSiguiente(q.getSiguiente());
                    q.setSiguiente(null);
                    q = null;
                    JOptionPane.showMessageDialog(null,
                            "Eliminado entre nodos!!!");
                }
            }
        }
    }

    public void cargarInfo(JTable jTable) {

        DefaultTableModel dtm = new DefaultTableModel();
        String[] identificadores = {
            "CodPedido",
            "Marca",
            "Modelo",
            "Precio",
            "Cantidad",
            "Total"
        };
        if (cab == null) {
            JOptionPane.showMessageDialog(null,
                    "Lista vacía!!!");
            jTable.setModel(dtm);
        } else {

            dtm.setColumnIdentifiers(identificadores);
            Object[] row = new Object[6];

            Pedido aux = cab;
            do {
                row[0] = aux.getNumeroPedido();
                row[1] = aux.getProducto().getMarca();
                row[2] = aux.getProducto().getModelo();
                row[3] = aux.getDireccion();
                row[4] = aux.getProducto().getPrecio();
                row[5] = aux.getCliente().getUsuario();
                row[6] = aux.getCliente().getContrasenia();

                dtm.addRow(row);
                aux = aux.getSiguiente();
            } while (aux != cab);
            jTable.setModel(dtm);
        }
    }

}
