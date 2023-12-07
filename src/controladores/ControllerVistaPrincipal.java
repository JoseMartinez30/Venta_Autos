/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controladores;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import vistas.*;

/**
 *
 * @author usuario
 */
public class ControllerVistaPrincipal implements ActionListener {

    private VistaPrincipal vistaPrincipal;
    private VistaIniciarSesion vistaIniciarSesion;
    private VistaRegistrarCliente vistaRegistrarCliente;
    private VistaRegistrarAdministrador vistaRegistrarAdministrador;
    private VistaRegistrarRepartidor vistaRegistrarRepartidor;

    public ControllerVistaPrincipal(
            VistaPrincipal vistaPrincipal, VistaIniciarSesion vistaIniciarSesion,
            VistaRegistrarCliente vistaRegistrarCliente,
            VistaRegistrarAdministrador vistaRegistrarAdministrador,
            VistaRegistrarRepartidor vistaRegistrarRepartidor) {

        this.vistaPrincipal = vistaPrincipal;
        this.vistaIniciarSesion = vistaIniciarSesion;
        this.vistaRegistrarCliente = vistaRegistrarCliente;
        this.vistaRegistrarAdministrador = vistaRegistrarAdministrador;
        this.vistaRegistrarRepartidor = vistaRegistrarRepartidor;

        vistaPrincipal.getItemIniciarSeision().addActionListener(this);
        vistaPrincipal.getItemAdministrador().addActionListener(this);
        vistaPrincipal.getItemCliente().addActionListener(this);
        vistaPrincipal.getItemRepartidor().addActionListener(this);
        vistaPrincipal.getItemSalir().addActionListener(this);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        MostrarIniciarSeision(e);
        MostarRegistarCliente(e);
        MostarRegistarAdministrador(e);
        MostarRegistarRepartidor(e);
        salir(e);
    }

    public void salir(ActionEvent e) {
        if (e.getSource() == vistaPrincipal.getItemSalir()) {
            JOptionPane.showMessageDialog(null,"Esta a punto de salir del sistema...");
            System.exit(0);
        }
    }

    public void MostrarIniciarSeision(ActionEvent e) {
        if (e.getSource() == vistaPrincipal.getItemIniciarSeision()) {
            vistaIniciarSesion.setLocationRelativeTo(null);
            vistaIniciarSesion.setVisible(true);
        }
    }

    public void MostarRegistarCliente(ActionEvent e) {
        if (e.getSource() == vistaPrincipal.getItemCliente()) {
            vistaRegistrarCliente.setLocationRelativeTo(null);
            vistaRegistrarCliente.setVisible(true);
        }
    }

    public void MostarRegistarAdministrador(ActionEvent e) {
        if (e.getSource() == vistaPrincipal.getItemAdministrador()) {
            vistaRegistrarAdministrador.setLocationRelativeTo(null);
            vistaRegistrarAdministrador.setVisible(true);
        }
    }

    public void MostarRegistarRepartidor(ActionEvent e) {
        if (e.getSource() == vistaPrincipal.getItemRepartidor()) {
            vistaRegistrarRepartidor.setLocationRelativeTo(null);
            vistaRegistrarRepartidor.setVisible(true);
        }
    }
}
