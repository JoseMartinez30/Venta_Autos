package modelos;

public class Pedido {

    private long numeroPedido;
    private String fecha;
    private Cliente cliente;
    private ListaCircularClientes colaCliente;
    private String direccion;
    private Producto producto;
    //private ColaProducto colaProducto;
    private int cantidad;
    private double precioUnitario;
    private double total;
    private String estado;
    private Pedido siguiente;

    public Pedido() {
        this.numeroPedido = 0;
        this.fecha = "";
        this.cliente = null;
        this.colaCliente = null;
        this.direccion = "";
        this.producto = null;
        //this.colaProducto = null;
        this.cantidad = 0;
        this.precioUnitario = 0;
        this.total = 0;
        this.estado = "";
        this.siguiente = null;
    }

    public Pedido(
            long numeroPedido,
            String fecha,
            Cliente cliente,
            String direccion,
            Producto producto,
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

  /*  public Pedido(
            long numeroPedido,
            String fecha,
            Cliente cliente,
            // ColaCliente colaCliente,
            String direccion,
            Producto producto,
            // ColaProducto colaProducto,
            int cantidad,
            double precioUnitario,
            double total,
            String estado
    ) {
        this.numeroPedido = numeroPedido;
        this.fecha = fecha;
        this.cliente = cliente;
        this.colaCliente = colaCliente;
        this.direccion = direccion;
        this.producto = producto;
       //  this.colaProducto = colaProducto;
        this.cantidad = cantidad;
        this.precioUnitario = precioUnitario;
        this.total = total;
        this.estado = estado;
        this.siguiente = null;
    }*/

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

   /* public ColaCliente getColaCliente() {
        return colaCliente;
    }

    public void setColaCliente(ColaCliente colaCliente) {
        this.colaCliente = colaCliente;
    }*/

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

  /*  public ColaProducto getColaProducto() {
        return colaProducto;
    }

    public void setColaProducto(ColaProducto colaProducto) {
        this.colaProducto = colaProducto;
    }*/

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