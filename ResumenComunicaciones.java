/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package qtz2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author oskar
 */
public class ResumenComunicaciones {
     private final int cantidadTierra;
    private final int cantidadActivos;
    private final long capacidadActiva;
    private final long totalDescargado;
    private final List<ModuloTierra> lideres;

    public ResumenComunicaciones(int cantidadTierra, int cantidadActivos, long capacidadActiva,
                                 long totalDescargado, List<ModuloTierra> lideres) {
        this.cantidadTierra = cantidadTierra;
        this.cantidadActivos = cantidadActivos;
        this.capacidadActiva = capacidadActiva;
        this.totalDescargado = totalDescargado;
        this.lideres = Collections.unmodifiableList(new ArrayList<>(lideres));
    }

    public int getCantidadTierra() { return cantidadTierra; }
    public int getCantidadActivos() { return cantidadActivos; }
    public long getCapacidadActiva() { return capacidadActiva; }
    public long getTotalDescargado() { return totalDescargado; }
    public List<ModuloTierra> getLideres() { return lideres; }
}
