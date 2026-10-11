/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package qtz2;

/**
 *
 * @author oskar
 */
public class ModuloEnergia extends Modulo {
    private final int generacionPorCiclo;
    private long ciclosRealizados;
    private long totalGenerado;

    public ModuloEnergia(String id, String nombre, int salud, boolean activo, int costo, int generacionPorCiclo) {
        super(id, nombre, salud, activo, costo);
        if (generacionPorCiclo <= 0) throw new IllegalArgumentException("Generacion invalida");
        this.generacionPorCiclo = generacionPorCiclo;
    }

    public int getGeneracionPorCiclo() { return generacionPorCiclo; }
    public long getCiclosRealizados() { return ciclosRealizados; }
    public long getTotalGenerado() { return totalGenerado; }

    @Override
    public String procesarCiclo(Recursos recursos) {
        if (!isActivo()) return getNombre() + ": inactivo; no genero energia";
        recursos.generar(generacionPorCiclo);
        totalGenerado = Math.addExact(totalGenerado, generacionPorCiclo);
        ciclosRealizados++;
        return getNombre() + ": genero " + generacionPorCiclo + " de energia";
    }

    @Override
    public String toString() {
        return "ENERGIA | " + super.toString() + " | genera " + generacionPorCiclo
                + "/ciclo | ciclos " + ciclosRealizados + " | generado " + totalGenerado;
    }
}
