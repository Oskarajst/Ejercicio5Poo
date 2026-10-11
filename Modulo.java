/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package qtz2;

/**
 *
 * @author oskar
 */
public abstract class Modulo {
    private final String id;
    private final String nombre;
    private final int costoConstruccion;
    private int salud;
    private boolean activo;

    protected Modulo(String id, String nombre, int salud, boolean activo, int costoConstruccion) {
        if (id == null || id.trim().isEmpty() || nombre == null || nombre.trim().isEmpty())
            throw new IllegalArgumentException("ID y nombre son obligatorios");
        if (salud < 0 || salud > 100 || costoConstruccion <= 0)
            throw new IllegalArgumentException("Salud o costo fuera de rango");
        this.id = id.trim();
        this.nombre = nombre.trim();
        this.salud = salud;
        this.activo = activo && salud > 0;
        this.costoConstruccion = costoConstruccion;
    }

    public String getId() { return id; }
    public String getNombre() { return nombre; }
    public int getSalud() { return salud; }
    public boolean isActivo() { return activo; }
    public int getCostoConstruccion() { return costoConstruccion; }

    public void setSalud(int nuevaSalud) {
        if (nuevaSalud < 0 || nuevaSalud > 100) throw new IllegalArgumentException("Salud fuera de rango");
        salud = nuevaSalud;
        if (salud == 0) activo = false;
    }

    public void setActivo(boolean nuevoEstado) {
        if (nuevoEstado && salud == 0) throw new IllegalStateException("Un modulo sin salud no puede activarse");
        activo = nuevoEstado;
    }

    public abstract String procesarCiclo(Recursos recursos);

    @Override
    public String toString() {
        return id + " | " + nombre + " | salud " + salud + "% | "
                + (activo ? "activo" : "inactivo") + " | construccion Q" + costoConstruccion;
    }
}

