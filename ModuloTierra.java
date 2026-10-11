/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package qtz2;

/**
 *
 * @author oskar
 */
public class ModuloTierra extends Modulo{
      private final String estacion;
    private final int capacidadDescarga;
    private final int energiaDescarga;
    private long totalDescargado;

    public ModuloTierra(String id, String nombre, int salud, boolean activo, int costo,
                        String estacion, int capacidadDescarga, int energiaDescarga) {
        super(id, nombre, salud, activo, costo);
        if (estacion == null || estacion.trim().isEmpty() || capacidadDescarga <= 0
                || energiaDescarga <= 0)
            throw new IllegalArgumentException("Datos de estacion invalidos");
        this.estacion = estacion.trim();
        this.capacidadDescarga = capacidadDescarga;
        this.energiaDescarga = energiaDescarga;
    }

    public String getEstacion() { return estacion; }
    public int getCapacidadDescarga() { return capacidadDescarga; }
    public int getEnergiaDescarga() { return energiaDescarga; }
    public long getTotalDescargado() { return totalDescargado; }

    @Override
    public String procesarCiclo(Recursos recursos) {
        if (!isActivo()) return getNombre() + ": inactivo; no descargo";
        if (recursos.getEnergia() < energiaDescarga)
            return getNombre() + ": energia insuficiente; no descargo";
        if (recursos.getDatosOrbita() == 0)
            return getNombre() + ": no hay datos en orbita";
        long cantidad = recursos.descargar(energiaDescarga, capacidadDescarga);
        totalDescargado = Math.addExact(totalDescargado, cantidad);
        return getNombre() + ": descargo " + cantidad + " MB y consumio " + energiaDescarga + " de energia";
    }

    @Override
    public String toString() {
        return "TIERRA | " + super.toString() + " | estacion " + estacion
                + " | capacidad " + capacidadDescarga + " MB/ciclo | consumo "
                + energiaDescarga + " | descargado " + totalDescargado + " MB";
    }
}
