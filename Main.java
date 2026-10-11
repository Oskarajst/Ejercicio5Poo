/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package qtz2;

/**
 *
 * @author oskar
 */
public class Main {
     public static void main(String[] args) {
        Mision mision = Mision.crearMisionInicial();
        Consola vista = new Consola();
        new Controlador(mision, vista).ejecutar();
    }
}
