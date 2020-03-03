/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import business.Articulo;
import java.util.Vector;

/**
 *
 * @author Daniel Gomez
 */
public class Carrito {

    private Vector articulos = new Vector();

    public Articulo sacar(Articulo a) {
        int n = articulos.indexOf(a);
        return (n < 0) ? null : (Articulo) articulos.get(n);
    }

    public Articulo sacar(int c) {
        Articulo a = new Articulo(c);
        return (Articulo) articulos.get(c);

    }

    public void meter(Articulo a) {
        Articulo ar = null;
        int n = articulos.indexOf(a);
        if (n < 0) {
            articulos.add(a);
        } else {
            ar = (Articulo) articulos.get(n);
            ar.setCantidad(ar.getCantidad() + a.getCantidad());
        }
    }

    public void vaciar() {
        articulos.clear();
    }

    public int cuantos() {
        return articulos.size();
    }
}
