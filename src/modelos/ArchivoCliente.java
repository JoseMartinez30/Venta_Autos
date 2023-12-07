/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelos;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.JOptionPane;
import vistas.*;
import modelos.*;

/**
 *
 * @author usuario
 */
public class ArchivoCliente {

    private String nombre;
    private String ruta;
    private VistaVerClientes vistaVerClientes;

    public ArchivoCliente() {
        this.nombre = "";
        this.ruta = "";
    }

    public ArchivoCliente(String nombre, String ruta, VistaVerClientes vistaVerClientes) {
        this.nombre = nombre;
        this.ruta = ruta;
        this.vistaVerClientes = vistaVerClientes;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getRuta() {
        return ruta;
    }

    public void setRuta(String ruta) {
        this.ruta = ruta;
    }

    public VistaVerClientes getVistaVerClientes() {
        return vistaVerClientes;
    }

    public void setVistaVerClientes(VistaVerClientes vistaVerClientes) {
        this.vistaVerClientes = vistaVerClientes;
    }

    /**
     * Método para guardar los datos en el archivo.
     *
     * @param cliente que se va a guardar.
     */
    public void guardar(Cliente cliente) {
        BufferedWriter escribir;
        try {
            File archivo = new File(getRuta());
            if (archivo.exists()) {
                escribir = new BufferedWriter(new FileWriter(archivo, true));
            } else {
                escribir = new BufferedWriter(new FileWriter(archivo));
            }
            if (cliente != null) {
                escribir.write(cliente.getIdentificacion() + " - " + cliente.getNombre() + " - "
                        + cliente.getApellidos() + " - " + cliente.getDireccion() + " - "
                        + cliente.getTelefono() + " - " + cliente.getUsuario() + " - "
                        + cliente.getContrasenia());
                escribir.newLine();
            }
            escribir.close();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error al guardar el archivo.", "Error",
                    JOptionPane.ERROR_MESSAGE);
            System.out.println("" + e);
        }
    }

    /**
     * Método para leer el archivo línea por línea y guardar los datos en una
     * cola.
     *
     * @param listaCircularClientes donde se guardan los datos leídos.
     */
    public void leer(ListaCircularClientes listaCircularClientes) {
        String linea = ""; // Variable para guardar la línea leída.
        try {
            File directorio = new File(getRuta().replace("\\ListaClientes.txt", ""));/*
            se crear una variable guardar la ruta para  la carpeta que guardara los archivos 
            utilizados en el programa u tilizamos el metodo replace(cadena a cambiar,cadena nueva), para
            dejar la ruta hasta la carpeta que almacenara los archivos.txt
             */
            if (!directorio.exists()) {/*Verificamos si no existe el directorio que se creara
                en la ruta especificada, si no exite entra en la ruta especificada*/
                if (directorio.mkdirs()) {/*Creamos el directorio o carpeta con el metodo mkdirs()
                    y validamos si se creo correctamente metiendo el comando
                    en un if, si devuelve true mandara un mensaje de confirmacion*/
                    JOptionPane.showMessageDialog(null, "Directorio creado");
                } else {
                    //Si no mandara un mensaje de error
                    JOptionPane.showMessageDialog(null, "Error al crear directorio, "
                            + "la carpeta donde se\n guardaran los archivos.txt no existe");
                }
            }
            FileReader archivo = new FileReader(getRuta());
            BufferedReader leer = new BufferedReader(archivo);
            while ((linea = leer.readLine()) != null) {
                String[] datos = linea.split(" - "); // Separa los datos leídos por guiones y los guarda en un arreglo.
                Cliente cliente = new Cliente(Long.parseLong(datos[0]), datos[1], datos[2], datos[3],
                        datos[4], datos[5], datos[6]);
                listaCircularClientes.setAddFin(cliente);
                listaCircularClientes.cargarInfo(getVistaVerClientes().getTbClientes());
            }
            leer.close();
        } catch (FileNotFoundException e) {
            try {
                File archivo = new File(getRuta());
                archivo.createNewFile();
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(null, "Error al crear el archivo.", "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error al leer el archivo.", "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    public void Actualizar(ListaCircularClientes listaCircularClientes) {
        // Especifica la ruta del archivo que deseas eliminar
        String rutaArchivo = getRuta();
        // Crea una instancia de la clase File con la ruta del archivo
        File archivo = new File(rutaArchivo);
        // Verifica si el archivo existe
        if (archivo.exists()) {
            // Intenta eliminar el archivo
            boolean eliminado = archivo.delete();
            if (eliminado) {
                System.out.println("El archivo ha sido eliminado exitosamente.");
            } else {
                System.out.println("No se pudo eliminar el archivo.");
            }
        } else {
            System.out.println("El archivo no existe.");
        }
        //Guardando la lista actualizada
        Cliente q = listaCircularClientes.cab;
        do {
            if (q != null) {
                guardar(q);
                q = q.getSig();
            }
        } while (q != listaCircularClientes.cab);
    }
}
