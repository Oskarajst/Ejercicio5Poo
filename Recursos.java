/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package qtz2;

/**
 *
 * @author oskar
 */
public class Recursos {
    private long energia;
    private long datosOrbita;
    private long datosDescargados;

    public Recursos(long energia, long datosOrbita, long datosDescargados) {
        if (energia < 0 || datosOrbita < 0 || datosDescargados < 0)
            throw new IllegalArgumentException("Los recursos no pueden ser negativos");
        this.energia = energia;
        this.datosOrbita = datosOrbita;
        this.datosDescargados = datosDescargados;
    }

    public long getEnergia() { return energia; }
    public long getDatosOrbita() { return datosOrbita; }
    public long getDatosDescargados() { return datosDescargados; }

    public void generar(int cantidad) {
        if (cantidad <= 0) throw new IllegalArgumentException("Generacion invalida");
        energia = Math.addExact(energia, cantidad);
    }

    public boolean recolectar(int costoEnergia, int cantidad) {
        if (costoEnergia <= 0 || cantidad <= 0) throw new IllegalArgumentException("Datos de recoleccion invalidos");
        if (energia < costoEnergia) return false;
        long nuevoTotal = Math.addExact(datosOrbita, cantidad);
        energia -= costoEnergia;
        datosOrbita = nuevoTotal;
        return true;
    }

    public long descargar(int costoEnergia, int capacidad) {
        if (costoEnergia <= 0 || capacidad <= 0) throw new IllegalArgumentException("Datos de descarga invalidos");
        if (energia < costoEnergia || datosOrbita == 0) return 0;
        long cantidad = Math.min(datosOrbita, capacidad);
        long nuevoTotal = Math.addExact(datosDescargados, cantidad);
        energia -= costoEnergia;
        datosOrbita -= cantidad;
        datosDescargados = nuevoTotal;
        return cantidad;
    }

    @Override
    public String toString() {
        return "Energia: " + energia + " | En orbita: " + datosOrbita
                + " MB | Descargados: " + datosDescargados + " MB";
    }
}
