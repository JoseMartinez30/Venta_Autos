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
import vistas.VistaVerClientes;

/**
 *
 * @author usuario
 */
public class ListaCircularClientes {

    Cliente cab;
    private VistaVerClientes vistaVerClientes;

    public ListaCircularClientes(VistaVerClientes vistaVerClientes) {
        this.vistaVerClientes = vistaVerClientes;

        this.cab = null;
    }

    public Cliente getCab() {
        return cab;
    }

    public void setCab(Cliente cab) {
        this.cab = cab;
    }

    public VistaVerClientes getVistaVerClientes() {
        return vistaVerClientes;
    }

    public void setVistaVerClientes(VistaVerClientes vistaVerClientes) {
        this.vistaVerClientes = vistaVerClientes;
    }

    public Cliente getBuscarCliente(long id, String usuario) {
        Cliente q = null;
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

    public Cliente getObCliente(String usuario, String contrasenia) {
        Cliente q = null;
        if (cab == null) {
            return null;
        } else {
            q = cab;
            do {
                if (q.getUsuario().equalsIgnoreCase(usuario)
                        && q.getContrasenia().equalsIgnoreCase(contrasenia)) {
                    return q;
                } else {
                    q = q.getSig();
                }
            } while (q != cab);
            return null;
        }
    }

    public boolean getBuscarCliente(String usuario, String contrasenia) {
        Cliente q = null;
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

    public Cliente getBuscarCliente(long id) {
        Cliente q = null;
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

    public Cliente getCrearNodo(JTextField txtIdentificacion, JTextField txtNombre,
            JTextField txtApellidos, JTextField txtDireccion, JTextField txtTelefono,
            JTextField txtUsuario, JPasswordField txtContrasenia) {
        Cliente info = null, b = null;
        long id = Long.parseLong(txtIdentificacion.getText());
        String nombre = txtNombre.getText(),
                apellidos = txtApellidos.getText(),
                direccion = txtDireccion.getText(),
                telefono = txtTelefono.getText(),
                usuario = txtUsuario.getText(),
                contrasenia = new String(txtContrasenia.getPassword());
        b = ListaCircularClientes.this.getBuscarCliente(id, usuario);
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
            info = new Cliente(id, nombre, apellidos, direccion,
                    telefono, usuario, contrasenia);
            JOptionPane.showMessageDialog(null, "Cliente Registrado");
        }
        return info;
    }

    public Cliente getUltimo() {
        Cliente q;
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

        Cliente info = getCrearNodo(txtIdentificacion, txtNombre, txtApellidos,
                txtDireccion, txtTelefono, txtUsuario, txtContrasenia);
        Cliente q;
        if (info != null) {
            if (cab == null) {
                cab = info;
                cab.setSig(cab);
                JOptionPane.showMessageDialog(null,
                        "Cliente registrado.  Un cliente en la lista!");

                cargarInfo(getVistaVerClientes().getTbClientes());
                txtIdentificacion.setText("");
                txtNombre.setText("");
                txtApellidos.setText("");
                txtDireccion.setText("");
                txtTelefono.setText("");
                txtUsuario.setText("");
                txtContrasenia.setText("");
            } else {
                q = getUltimo();
                q.setSig(info);
                info.setSig(cab);
                JOptionPane.showMessageDialog(null,
                        "Cliente agregado al final de la lista!");
                cargarInfo(getVistaVerClientes().getTbClientes());
                txtIdentificacion.setText("");
                txtNombre.setText("");
                txtApellidos.setText("");
                txtDireccion.setText("");
                txtTelefono.setText("");
                txtUsuario.setText("");
                txtContrasenia.setText("");
            }
        } else {
            JOptionPane.showMessageDialog(null,
                    "Vuelve a llenar los campos!");

        }
    }

    public void setAddFin(Cliente cliente) {
        Cliente q;
        if (cliente != null) {
            if (cab == null) {
                cab = cliente;
                cab.setSig(cab);
                cargarInfo(getVistaVerClientes().getTbClientes());
            } else {
                q = getUltimo();
                q.setSig(cliente);
                cliente.setSig(cab);
                cargarInfo(getVistaVerClientes().getTbClientes());
            }
        }
    }

    public Cliente getAnterior(Cliente q) {
        if ((cab == null) || (q == null)) {
            return null;
        } else {
            Cliente a = cab;
            while (a.getSig() != q) {
                a = a.getSig();
            }
            return a;
        }
    }

    public void setEliminarCliente(long id) {
        if (cab == null) {
            JOptionPane.showMessageDialog(null,
                    "Lista vacía!!!");
        } else {
            Cliente q = ListaCircularClientes.this.getBuscarCliente(id);
            Cliente u, a;
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

    public void cargarInfo() {
        if (cab == null) {
            JOptionPane.showMessageDialog(null,
                    "Lista vacía!!!");
        } else {
            DefaultTableModel dtm = new DefaultTableModel();
            String[] identificadores = {
                "Idenficacion",
                "Nombre",
                "Apellidos",
                "Direccion",
                "Telefono",
                "Usuario",
                "contraseña"
            };
            dtm.setColumnIdentifiers(identificadores);
            Object[] row = new Object[7];

            Cliente aux = cab;
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
            getVistaVerClientes().getTbClientes().setModel(dtm);
        }
    }

    public void cargarInfo(JTable jTable) {
        if (cab == null) {
            JOptionPane.showMessageDialog(null,
                    "Lista vacía!!!");
        } else {
            DefaultTableModel dtm = new DefaultTableModel();
            String[] identificadores = {
                "Idenficacion",
                "Nombre",
                "Apellidos",
                "Direccion",
                "Telefono",
                "Usuario",
                "contraseña"
            };
            dtm.setColumnIdentifiers(identificadores);
            Object[] row = new Object[7];

            Cliente aux = cab;
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
