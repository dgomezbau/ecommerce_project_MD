/*
 THIS CLASS AND PACKAGE WILL BE REMOVE IN FUTURE RELEASES
 */
package business;

/**
 *
 * @author Daniel Gomez
 */
public class Articulo {

    public Articulo() {
    }

    public Articulo(int codigo, String descripcion, int cantidad, double precio) {
        this.codigo = codigo;
        this.descripcion = descripcion;
        this.cantidad = cantidad;
        this.precio = precio;
    }

    public Articulo(int codigo, String descripcion) {
        this(codigo, descripcion, 1, 0);
    }

    public Articulo(int id) {
        this(id, "no name", 0, 0);
    }

    private int codigo;

    /**
     * Get the value of codigo
     *
     * @return the value of codigo
     */
    public int getCodigo() {
        return codigo;
    }

    /**
     * Set the value of codigo
     *
     * @param codigo new value of codigo
     */
    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    private String descripcion;

    /**
     * Get the value of descripcion
     *
     * @return the value of descripcion
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * Set the value of descripcion
     *
     * @param descripcion new value of descripcion
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    private int cantidad;

    /**
     * Get the value of cantidad
     *
     * @return the value of cantidad
     */
    public int getCantidad() {
        return cantidad;
    }

    /**
     * Set the value of cantidad
     *
     * @param cantidad new value of cantidad
     */
    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    private double precio;

    /**
     * Get the value of precio
     *
     * @return the value of precio
     */
    public double getPrecio() {
        return precio;
    }

    /**
     * Set the value of precio
     *
     * @param precio new value of precio
     */
    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public boolean equals(Object obj) {
        Articulo a = (Articulo) obj;
        return (codigo == a.getCodigo());
    }

}
