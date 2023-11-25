package modelos;

public class Auto {

    private int codigo;
    private String marca;
    private String modelo;
    private String color;
    private double precio;
    private int cantidad;
    private Auto siguiente;
    
    
    public Auto(int codigo, String marca, String modelo, double precio, int cantidad) {
        this.codigo = codigo;
        this.marca = marca;
        this.modelo = modelo;
        this.precio = precio;
        this.cantidad = cantidad;
        this.siguiente = null;
    }

    public Auto() {
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public Auto getSiguiente() {
        return siguiente;
    }

    public void setSiguiente(Auto siguiente) {
        this.siguiente = siguiente;
    }

}
