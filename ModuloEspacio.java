/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package qtz2;

/**
 *
 * @author oskar
 */
public class ModuloEspacio extends Modulo {
     private final TipoInstrumento instrumento;
    private final int datosPorCiclo;
    private final int energiaPorCiclo;
    private long ciclosRealizados;
    private long totalRecolectado;

    public ModuloEspacio(String id, String nombre, int salud, boolean activo, int costo,
                       TipoInstrumento instrumento, int datosPorCiclo, int energiaPorCiclo) {
        super(id, nombre, salud, activo, costo);
        if (instrumento == null || datosPorCiclo <= 0 || energiaPorCiclo <= 0)
            throw new IllegalArgumentException("Instrumento o capacidades invalidas");
        this.instrumento = instrumento;
        this.datosPorCiclo = datosPorCiclo;
        this.energiaPorCiclo = energiaPorCiclo;
    }

    public TipoInstrumento getInstrumento() { return instrumento; }
    public int getDatosPorCiclo() { return datosPorCiclo; }
    public int getEnergiaPorCiclo() { return energiaPorCiclo; }
    public long getCiclosRealizados() { return ciclosRealizados; }
    public long getTotalRecolectado() { return totalRecolectado; }

    @Override
    public String procesarCiclo(Recursos recursos) {
        if (!isActivo()) return getNombre() + ": inactivo; no recolecto";
        if (!recursos.recolectar(energiaPorCiclo, datosPorCiclo))
            return getNombre() + ": energia insuficiente; no recolecto";
        totalRecolectado = Math.addExact(totalRecolectado, datosPorCiclo);
        ciclosRealizados++;
        return getNombre() + ": recolecto " + datosPorCiclo + " MB y consumio " + energiaPorCiclo + " de energia";
    }

    @Override
    public String toString() {
        return "VUELO | " + super.toString() + " | " + instrumento
                + " | " + datosPorCiclo + " MB/ciclo | consumo " + energiaPorCiclo
                + " | ciclos " + ciclosRealizados + " | recolectado " + totalRecolectado + " MB";
    }
}
