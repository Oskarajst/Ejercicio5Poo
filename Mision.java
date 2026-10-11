/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package qtz2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 *
 * @author oskar
 */
public class Mision {
     private final List<Modulo> modulos = new ArrayList<>();
    private final Recursos recursos;
    private int numeroCiclo;

    public Mision(Recursos recursos) {
        if (recursos == null) throw new IllegalArgumentException("Faltan los recursos");
        this.recursos = recursos;
    }

    public Recursos getRecursos() { return recursos; }
    public int getNumeroCiclo() { return numeroCiclo; }
    public List<Modulo> getModulos() { return Collections.unmodifiableList(modulos); }

    public void agregarModulo(Modulo nuevo) {
        if (nuevo == null) throw new IllegalArgumentException("El modulo es obligatorio");
        for (Modulo existente : modulos) {
            if (existente.getId().equalsIgnoreCase(nuevo.getId()))
                throw new IllegalArgumentException("ID de modulo repetido");
        }
        modulos.add(nuevo);
    }

    public List<Modulo> buscar(String texto) {
        List<Modulo> encontrados = new ArrayList<>();
        if (texto == null || texto.trim().isEmpty()) return encontrados;
        String busqueda = texto.trim();
        // Si coincide un ID, se da prioridad a ese modulo.
        for (Modulo modulo : modulos) {
            if (modulo.getId().equalsIgnoreCase(busqueda)) {
                encontrados.add(modulo);
                return encontrados;
            }
        }
        for (Modulo modulo : modulos) {
            if (modulo.getNombre().toLowerCase().contains(busqueda.toLowerCase()))
                encontrados.add(modulo);
        }
        return encontrados;
    }

    public List<Modulo> catalogoPorCosto() {
        List<Modulo> copia = new ArrayList<>(modulos);
        copia.sort(Comparator.comparingInt(Modulo::getCostoConstruccion).thenComparing(Modulo::getId));
        return copia;
    }

    public List<String> avanzarCiclo() {
        List<String> resultados = new ArrayList<>();
        numeroCiclo++;
        // La lista se recorre tal como se cargo. Cada modulo modifica los recursos al momento.
        for (Modulo modulo : modulos) {
            resultados.add(modulo.procesarCiclo(recursos));
        }
        return resultados;
    }

    public ResumenComunicaciones resumenComunicaciones() {
        int tierra = 0;
        int activos = 0;
        long capacidad = 0;
        long descargado = 0;
        long maximo = -1;
        List<ModuloTierra> lideres = new ArrayList<>();
        for (Modulo modulo : modulos) {
            if (modulo instanceof ModuloTierra) {
                ModuloTierra antena = (ModuloTierra) modulo;
                tierra++;
                if (antena.isActivo()) {
                    activos++;
                    capacidad += antena.getCapacidadDescarga();
                }
                descargado += antena.getTotalDescargado();
                if (antena.getTotalDescargado() > maximo) {
                    maximo = antena.getTotalDescargado();
                    lideres.clear();
                    lideres.add(antena);
                } else if (antena.getTotalDescargado() == maximo) {
                    lideres.add(antena);
                }
            }
        }
        return new ResumenComunicaciones(tierra, activos, capacidad, descargado, lideres);
    }

    public static Mision crearMisionInicial() {
        Mision mision = new Mision(new Recursos(10, 0, 0));
        mision.agregarModulo(new ModuloEnergia("E01", "Panel A", 95, true, 900, 20));
        mision.agregarModulo(new ModuloEspacio("V01", "Camara orbital", 90, true, 2400, TipoInstrumento.CAMARA, 15, 8));
        mision.agregarModulo(new ModuloTierra("T01", "Antena Norte", 85, true, 1800, "Campus Norte", 10, 5));
        mision.agregarModulo(new ModuloEnergia("E02", "Panel B", 75, true, 850, 12));
        mision.agregarModulo(new ModuloEspacio("V02", "Sensor magnetico", 88, true, 2100, TipoInstrumento.SENSOR, 9, 6));
        mision.agregarModulo(new ModuloTierra("T02", "Antena Central", 92, true, 1750, "Campus Central", 8, 4));
        mision.agregarModulo(new ModuloEnergia("E03", "Panel auxiliar", 40, false, 700, 7));
        mision.agregarModulo(new ModuloEspacio("V03", "Camara nocturna", 60, false, 1950, TipoInstrumento.CAMARA, 13, 7));
        mision.agregarModulo(new ModuloTierra("T03", "Antena Sur", 100, false, 1450, "Campus Sur", 6, 3));
        mision.agregarModulo(new ModuloEspacio("V04", "Sensor termico", 80, true, 2050, TipoInstrumento.SENSOR, 11, 9));
        mision.agregarModulo(new ModuloEnergia("E04", "Panel C", 100, true, 950, 6));
        // Dos ciclos previos dejan acumulados reales: la mision ya esta en progreso.
        mision.avanzarCiclo();
        mision.avanzarCiclo();
        return mision;
    }
}
