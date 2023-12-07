/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

import controladores.*;
import javax.swing.JOptionPane;
import vistas.*;
import modelos.*;

/**
 *
 * @author usuario
 */
public class Main {

    public static void main(String[] args) {
        //Iniicializamos las vistas que tendra nuestro programa
        VistaPrincipal vistaPrincipal = new VistaPrincipal();
        VistaIniciarSesion vistaIniciarSesion = new VistaIniciarSesion();
        VistaRegistrarCliente vistaRegistrarCliente = new VistaRegistrarCliente();
        VistaRegistrarAdministrador vistaRegistrarAdministrador = new VistaRegistrarAdministrador();
        VistaRegistrarRepartidor vistaRegistrarRepartidor = new VistaRegistrarRepartidor();
        VistaRegistrarProducto vistaRegistrarProducto = new VistaRegistrarProducto();
        VistaVerClientes vistaVerClientes = new VistaVerClientes();
        VistaAdministrador vistaAdministrador = new VistaAdministrador();
        VistaRepartidor vistaRepartidor = new VistaRepartidor();
        VistaHistorial vistaHistorial = new VistaHistorial();
        VistaVerAdmins vistaVerAdmins = new VistaVerAdmins();
        VistaCarrito vistaCarrito = new VistaCarrito();
        VistaCompras vistaCompras = new VistaCompras(vistaHistorial, vistaCarrito);
        VistaPedidosEntregar vistaPedidosEntregar = new VistaPedidosEntregar();
        VistaPedidosEntregados vistaPedidosEntregados = new VistaPedidosEntregados();
        VistaVerRepartidor vistaVerRepartidor = new VistaVerRepartidor();

        //Inicializamos los modelos que usara el sistema
        ArchivoCliente archivoCliente = new ArchivoCliente("ListaClientes.txt",
                "C:\\Base de datos\\ListaClientes.txt", vistaVerClientes);
        ArchivoAdmin archivoAdmin = new ArchivoAdmin("ListaAdmins.txt",
                "C:\\Base de datos\\ListaAdmins.txt", vistaVerAdmins);
        ArchivoTrasportadora archivoRepartidor = new ArchivoTrasportadora("ListaRepartidores.txt",
                "C:\\Base de datos\\ListaRepartidores.txt", vistaVerRepartidor);
        ArchivoProducto archivoProducto = new ArchivoProducto("ListaProductos.txt",
                "C:\\Base de datos\\ListaProductos.txt", vistaCompras);
        ArchivoPedido archivoPedido = new ArchivoPedido("ColaPedidosPendientes",
                "C:\\Base de datos\\ColaPedidosPendientes.txt", vistaPedidosEntregar);
        Cliente cliente = new Cliente();
        Pedido pedido = new Pedido();
        Producto producto = new Producto();
        ListaCircularClientes listaCircularClientes = new ListaCircularClientes(vistaVerClientes, archivoCliente);
        ListaCircularAdmins listaCircularAdmins = new ListaCircularAdmins(vistaVerAdmins, archivoAdmin);
        ListaCircularTrasportadora listaCircularRepartidores = new ListaCircularTrasportadora(archivoRepartidor,
                vistaVerRepartidor);
        ColaPedido colaPedido = new ColaPedido();
        ColaProducto colaProducto = new ColaProducto(vistaRegistrarProducto);

        //Inicializamos los controladores del sistema
        ControllerVistaPrincipal controllerVistaPrincipal = new ControllerVistaPrincipal(
                vistaPrincipal, vistaIniciarSesion, vistaRegistrarCliente,
                vistaRegistrarAdministrador, vistaRegistrarRepartidor);
        ControllerIniciarSesion controllerIniciarSesion = new ControllerIniciarSesion(listaCircularAdmins,
                listaCircularClientes, listaCircularRepartidores, listaCircularRepartidores,
                cliente, vistaIniciarSesion, vistaCompras, vistaAdministrador, vistaRepartidor);
        ControllerCliente controllerCliente = new ControllerCliente(cliente, producto, archivoPedido, 
                pedido, archivoProducto, listaCircularClientes, colaProducto, colaPedido, archivoCliente,
                vistaRegistrarCliente, vistaCarrito, vistaHistorial, vistaCompras, vistaVerClientes, controllerIniciarSesion);
        ControllerAdmins controllerAdmins = new ControllerAdmins(listaCircularAdmins,
                archivoAdmin, vistaRegistrarAdministrador, vistaAdministrador,
                vistaRegistrarProducto, vistaVerClientes, vistaVerRepartidor,
                vistaPedidosEntregar, vistaPedidosEntregados, vistaVerAdmins);
        ControllerTransportadora controllerRepartidor = new ControllerTransportadora(listaCircularRepartidores,
                archivoRepartidor, vistaRegistrarRepartidor, vistaVerRepartidor);
        ControllerProducto controllerProducto = new ControllerProducto(colaProducto,
                vistaRegistrarProducto, vistaCompras, archivoProducto);

        JOptionPane.showMessageDialog(null, "Todos los Archivos.txt seran guardados"
                + "en la ruta **  C:\\Base de datos  **, de no exitir el \n directorio **  Base de datos  **"
                + " sera creado en el disco local C", "RECUERDA", JOptionPane.INFORMATION_MESSAGE);
        vistaPrincipal.setLocationRelativeTo(null);//llamamos la metodo setLocationRelativeTo
        //y le pasamos como parametro null para que cuendo mostremos la vista salga en todo el centro 
        //de la pantalla.
        vistaPrincipal.setVisible(true);//Mostramos la vista

    }
}
