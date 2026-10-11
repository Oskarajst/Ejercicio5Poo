/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package qtz2;

import java.util.List;
import java.util.Scanner;

/**
 *
 * @author oskar
 */
public class Consola {
    private final Scanner teclado = new Scanner(System.in);

    public void mostrarMenu() {
        System.out.println("1 Listar modulos | 2 Buscar por ID o nombre");
        System.out.println("3 Catalogo por costo | 4 Avanzar un ciclo");
        System.out.println("5 Resumen de comunicaciones | 0 Salir");
    }

    public String pedir(String mensaje) {
        System.out.print(mensaje + ": ");
        return teclado.nextLine().trim();
    }

    public void mostrar(String mensaje) { System.out.println(mensaje); }

    public void mostrarModulos(List<Modulo> modulos) {
        if (modulos.isEmpty()) { mostrar("No se encontraron modulos."); return; }
        for (Modulo modulo : modulos) mostrar(modulo.toString());
    }

    public void mostrarCiclo(int numero, List<String> resultados, Recursos recursos) {
        mostrar("CICLO " + numero);
        for (String resultado : resultados) mostrar(resultado);
        mostrar("Estado final: " + recursos);
    }

    public void mostrarResumen(ResumenComunicaciones r) {
        mostrar("Modulos de tierra: " + r.getCantidadTierra() + " | Activos: " + r.getCantidadActivos());
        mostrar("Capacidad activa: " + r.getCapacidadActiva() + " MB por ciclo");
        mostrar("Total historico descargado: " + r.getTotalDescargado() + " MB");
        mostrar("Mayor descarga historica por modulo:");
        for (ModuloTierra lider : r.getLideres()) {
            mostrar("  " + lider.getNombre() + " | " + lider.getId() + " | "
                    + lider.getEstacion() + " | " + lider.getTotalDescargado() + " MB");
        }
    }
}
