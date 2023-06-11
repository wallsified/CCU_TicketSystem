package System;

import java.io.IOException;

/**
 * Clase Main. En esta clase es donde se crea
 * la simulación del resto de las clases.
 * 
 * @version 1.0
 * @author wallsified
 * @author gentle_earthquake
 */

public class Simulador {

 /**
  * Main de prueba. Creamos visitantes aleatorios
  * y los añadimos a un objeto {@link}CCU. Repetimos
  * unas cuantas veces para poder simular dias distintos.
  * 
  * @param args N/A
  */
 public static void main(String[] args) {
  CCU nuevo = new CCU();

  // Inicio Dia 1
  for (int i = 0; i < 5; i++) {
   nuevo.venta(new Visitante());
  }

  System.out.println(nuevo);

  try {
   nuevo.terminaDia();
  } catch (IOException e) {
   System.out.println("Algo salio mal en la ejecución");
  }

  // Inicio Dia 2

  for (int i = 0; i < 7; i++)
   nuevo.venta(new Visitante());

  System.out.println(nuevo);

  try {
   nuevo.terminaDia();
  } catch (IOException e) {
   System.out.println("Algo salio mal en la ejecución");
  }

  // Inicio Dia 3
  for (int i = 0; i < 9; i++)
   nuevo.venta(new Visitante());

  System.out.println(nuevo);

  try {
   nuevo.terminaDia();
  } catch (IOException e) {
   System.out.println("Algo salio mal en la ejecución");
  }

  // Inicio Dia 4
  for (int i = 0; i < 10; i++)
   nuevo.venta(new Visitante());

  System.out.println(nuevo);

  try {
   nuevo.terminaDia();
  } catch (IOException e) {
   System.out.println("Algo salio mal en la ejecución");
  }

 }

}
