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
public class ArchivoAdmin {

    private String nombre;
    private String ruta;
    private VistaVerAdmins vistaVerAdmins;

    public ArchivoAdmin(String nombre, String ruta, VistaVerAdmins vistaVerAdmins) {
        this.nombre = nombre;
        this.ruta = ruta;
        this.vistaVerAdmins = vistaVerAdmins;
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

    public VistaVerAdmins getVistaVerAdmins() {
        return vistaVerAdmins;
    }

    public void setVistaVerAdmins(VistaVerAdmins vistaVerAdmins) {
        this.vistaVerAdmins = vistaVerAdmins;
    }

    /**
     * Método para guardar los datos en el archivo.
     *
     * @param admin que se va a guardar.
     */
    public void guardar(Administrador admin) {
        BufferedWriter escribir;
        try {
            File archivo = new File(getRuta());
            if (archivo.exists()) {
                escribir = new BufferedWriter(new FileWriter(archivo, true));
            } else {
                escribir = new BufferedWriter(new FileWriter(archivo));
            }
            if (admin != null) {
                escribir.write(admin.getIdentificacion() + " - " + admin.getNombre() + " - "
                        + admin.getApellidos() + " - " + admin.getDireccion() + " - "
                        + admin.getTelefono() + " - " + admin.getUsuario() + " - "
                        + admin.getContrasenia());
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
     * @param listaCircularAdmin donde se guardan los datos leídos.
     */
    public void leer(ListaCircularAdmins listaCircularAdmin) {
        String linea = ""; // Variable para guardar la línea leída.
        try {
            File directorio = new File(getRuta().replace("\\ListaAdmins.txt", ""));/*
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
                Administrador admin = new Administrador(
                        Long.parseLong(datos[0]), datos[1], datos[2], datos[3],
                        datos[4], datos[5], datos[6]);
                listaCircularAdmin.setAddFin(admin);
                listaCircularAdmin.cargarInfo(getVistaVerAdmins().getTbAdmins());
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
    
     public void Actualizar(ListaCircularAdmins listaCircularAdmins) {
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
        Administrador q = listaCircularAdmins.cab;
        do {
             if (q != null) {
                guardar(q);
                q = q.getSig();
            }
        } while (q != listaCircularAdmins.cab);
    }
}
