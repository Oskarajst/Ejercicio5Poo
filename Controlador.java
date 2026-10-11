/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package qtz2;

import java.util.List;
import java.util.NoSuchElementException;

/**
 *
 * @author oskar
 */
public class Controlador {
     private final Mision mision;
    private final Consola vista;

    public Controlador(Mision mision, Consola vista) {
        this.mision = mision;
        this.vista = vista;
    }

    public void ejecutar() {
        vista.mostrar("Mision cargada: " + mision.getModulos().size() + " modulos; "
                + mision.getNumeroCiclo() + " ciclos previos. " + mision.getRecursos());
        while (true) {
            vista.mostrarMenu();
            try {
                String opcion = vista.pedir("Opcion");
                switch (opcion) {
                    case "0": vista.mostrar("Hasta luego."); return;
                    case "1": vista.mostrarModulos(mision.getModulos()); break;
                    case "2":
                        List<Modulo> encontrados = mision.buscar(vista.pedir("ID o nombre"));
                        vista.mostrarModulos(encontrados);
                        break;
                    case "3": vista.mostrarModulos(mision.catalogoPorCosto()); break;
                    case "4":
                        List<String> eventos = mision.avanzarCiclo();
                        vista.mostrarCiclo(mision.getNumeroCiclo(), eventos, mision.getRecursos());
                        break;
                    case "5": vista.mostrarResumen(mision.resumenComunicaciones()); break;
                    default: vista.mostrar("Opcion invalida. Intente de nuevo.");
                }
            } catch (NoSuchElementException error) {
                return;
            } catch (IllegalArgumentException error) {
                vista.mostrar("Error: " + error.getMessage());
            }
        }
    }
}
