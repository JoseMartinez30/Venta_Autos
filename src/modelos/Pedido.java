package modelos;

public class Pedido {

    private long numeroPedido;
    private String fecha;
    private Cliente cliente;
    private ListaCircularClientes colaCliente;
    private String direccion;
    private Auto producto;
    private int cantidad;
    private double precioUnitario;
    private double total;
    private String estado;
    private Pedido siguiente;

    public Pedido(
            long numeroPedido,
            String fecha,
            Cliente cliente,
            String direccion,
            Auto producto,
            int cantidad,
            double precioUnitario,
            double total,
            String estado
    ) {
        this.numeroPedido = numeroPedido;
        this.fecha = fecha;
        this.cliente = cliente;
        this.direccion = direccion;
        this.producto = producto;
        this.cantidad = cantidad;
        this.precioUnitario = precioUnitario;
        this.total = total;
        this.estado = estado;
        this.siguiente = null;
    }

    public Pedido() {

    }

    public long getNumeroPedido() {
        return numeroPedido;
    }

    public void setNumeroPedido(long numeroPedido) {
        this.numeroPedido = numeroPedido;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public Auto getProducto() {
        return producto;
    }

    public void setProducto(Auto producto) {
        this.producto = producto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public double getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(double precioUnitario) {
        this.precioUnitario = precioUnitario;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public Pedido getSiguiente() {
        return siguiente;
    }

    public void setSiguiente(Pedido siguiente) {
        this.siguiente = siguiente;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
