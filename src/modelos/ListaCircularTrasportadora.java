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
public class ListaCircularTrasportadora {

    Trasportadora cab;

    private ArchivoTrasportadora archivoRepartidor;
    private VistaVerRepartidor vistaVerRepartidor;

    public ListaCircularTrasportadora(ArchivoTrasportadora archivoRepartidor,
            VistaVerRepartidor vistaVerRepartidor) {
        this.archivoRepartidor = archivoRepartidor;
        this.vistaVerRepartidor = vistaVerRepartidor;
        cab = null;
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

    public Trasportadora getBuscarCliente(long id, String usuario) {
        Trasportadora q = null;
        if (cab == null) {
            return null;
        } else {
            q = cab;
            do {
                if ((q.getIdentificacion()) == id || q.getUsuario().equalsIgnoreCase(usuario)) {
                    return q;
                } else {
                    q = q.getSig();
                }
            } while (q != cab);
            return null;
        }
    }

    public boolean getBuscarCliente(String usuario, String contrasenia) {
        Trasportadora q = null;
        if (cab == null) {
            return false;
        } else {
            q = cab;
            do {
                if (q.getUsuario().equalsIgnoreCase(usuario)
                        && q.getContrasenia().equalsIgnoreCase(contrasenia)) {
                    return true;
                } else {
                    q = q.getSig();
                }
            } while (q != cab);
            return false;
        }
    }

    public Trasportadora getBuscarCliente(long id) {
        Trasportadora q = null;
        if (cab == null) {
            return null;
        } else {
            q = cab;
            do {
                if ((q.getIdentificacion()) == id) {
                    return q;
                } else {
                    q = q.getSig();
                }
            } while (q != cab);
            return null;
        }
    }

    public Trasportadora getCrearNodo(JTextField txtIdentificacion, JTextField txtNombre,
            JTextField txtApellidos, JTextField txtDireccion, JTextField txtTelefono,
            JTextField txtUsuario, JPasswordField txtContrasenia) {
        Trasportadora info = null, b = null;
        long id = Long.parseLong(txtIdentificacion.getText());
        String nombre = txtNombre.getText(),
                apellidos = txtApellidos.getText(),
                direccion = txtDireccion.getText(),
                telefono = txtTelefono.getText(),
                usuario = txtUsuario.getText(),
                contrasenia = new String(txtContrasenia.getPassword());
        b = getBuscarCliente(id, usuario);
        if (b != null) {
            JOptionPane.showMessageDialog(null,
                    "Error! Esta identificion o Usuario ya existe! "
                    + "Intente nuevamente.");
            txtNombre.setText("");
            txtApellidos.setText("");
            txtDireccion.setText("");
            txtTelefono.setText("");
            txtUsuario.setText("");
            txtContrasenia.setText("");

        } else {
            info = new Trasportadora(id, nombre, apellidos, direccion,
                    telefono, usuario, contrasenia);
            JOptionPane.showMessageDialog(null, "Administrador Registrado");
        }
        return info;
    }

    public Trasportadora getUltimo() {
        Trasportadora q;
        if (cab == null) {
            return null;
        } else {
            q = cab;
            while (q.getSig() != cab) {
                q = q.getSig();
            }
            return q;
        }
    }

    public void setAddFin(JTextField txtIdentificacion, JTextField txtNombre,
            JTextField txtApellidos, JTextField txtDireccion, JTextField txtTelefono,
            JTextField txtUsuario, JPasswordField txtContrasenia) {

        Trasportadora info = getCrearNodo(txtIdentificacion, txtNombre, txtApellidos,
                txtDireccion, txtTelefono, txtUsuario, txtContrasenia);
        Trasportadora q;
        if (info != null) {
            if (cab == null) {
                cab = info;
                cab.setSig(cab);
                JOptionPane.showMessageDialog(null,
                        "Repartidor registrado.  Un Repartidor en la lista!");
                getArchivoRepartidor().guardar(info);
                cargarInfo(getVistaVerRepartidor().getTbRepartidores());
            } else {
                q = getUltimo();
                q.setSig(info);
                info.setSig(cab);
                JOptionPane.showMessageDialog(null,
                        "Repartidor agregado al final de la lista!");
                getArchivoRepartidor().guardar(info);
                cargarInfo(getVistaVerRepartidor().getTbRepartidores());
            }
        } else {
            JOptionPane.showMessageDialog(null,
                    "Vuelve a llenar los campos!");

        }
    }

    public void setAddFin(Trasportadora repartidor) {
        Trasportadora info = repartidor;
        Trasportadora q;
        if (info != null) {
            if (cab == null) {
                cab = info;
                cab.setSig(cab);
                cargarInfo(getVistaVerRepartidor().getTbRepartidores());
            } else {
                q = getUltimo();
                q.setSig(info);
                info.setSig(cab);
                cargarInfo(getVistaVerRepartidor().getTbRepartidores());
            }
        } else {
            JOptionPane.showMessageDialog(null,
                    "Vuelve a llenar los campos!");

        }
    }

    public Trasportadora getAnterior(Trasportadora q) {
        if ((cab == null) || (q == null)) {
            return null;
        } else {
            Trasportadora a = cab;
            while (a.getSig() != q) {
                a = a.getSig();
            }
            return a;
        }
    }

    public void setEliminarRepartidor(long id) {
        if (cab == null) {
            JOptionPane.showMessageDialog(null,
                    "Lista vacía!!!");
        } else {
            Trasportadora q = getBuscarCliente(id);
            Trasportadora u, a;
            if (q == null) {
                JOptionPane.showMessageDialog(null,
                        "La identifica no existe!!!");
            } else {
                if ((cab.getSig() == cab) && (q == cab)) {
                    cab = null;
                    JOptionPane.showMessageDialog(null,
                            "Eliminado. La Lista esta vacía!!!");
                } else if ((cab.getSig() != cab) && (q == cab)) {
                    u = getUltimo();
                    cab = cab.getSig();
                    u.setSig(cab);
                    q.setSig(null);
                    q = null;
                    JOptionPane.showMessageDialog(null,
                            "Eliminado al inicio!!!");
                } else if (q.getSig() == cab) {
                    a = getAnterior(q);
                    a.setSig(cab);
                    q = null;
                    JOptionPane.showMessageDialog(null,
                            "Eliminado al final!!!");
                } else {
                    a = getAnterior(q);
                    a.setSig(q.getSig());
                    q.setSig(null);
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
            "Nit",
            "Nombre",
            "Apellidos",
            "Direccion",
            "Telefono",
            "Usuario",
            "contraseña"
        };
        if (cab == null) {
            JOptionPane.showMessageDialog(null,
                    "Lista vacía!!!");
            jTable.setModel(dtm);
        } else {

            dtm.setColumnIdentifiers(identificadores);
            Object[] row = new Object[7];

            Trasportadora aux = cab;
            do {
                row[0] = aux.getIdentificacion();
                row[1] = aux.getNombre();
                row[2] = aux.getApellidos();
                row[3] = aux.getDireccion();
                row[4] = aux.getTelefono();
                row[5] = aux.getUsuario();
                row[6] = aux.getContrasenia();

                dtm.addRow(row);
                aux = aux.getSig();
            } while (aux != cab);
            jTable.setModel(dtm);
        }
    }

}
