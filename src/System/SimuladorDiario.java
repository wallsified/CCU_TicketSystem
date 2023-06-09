package System;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Clase Main. Mi idea es simular 3-5 dias aqui apartir de todo
 * lo demás y simplemente tenerlo más organizado.
 * 
 * @version 1.0
 * @author wallsified
 * @author gentle_earthquake
 */

public class SimuladorDiario {

 /**
  * Podemos hacer un constructor que reciba un {@link}CCU y que al final de eso
  * genere
  * un ticket. Ese ticket se genera acá de modo en el que Main simplemente
  * construimos n
  * simuladores.
  */

 public SimuladorDiario(CCU workday) {

 }

 /**
  * Contador de dias trabajados
  */
 private int contadorDias = 1;

 /**
  * Dia de ejecución del programa. Es simulada.
  */
 LocalDate fechaActual = LocalDate.now();

 /**
  * Método privado para imprimir la hora en que se hacen los movimientos
  * en el ticket.
  *
  * @return representación en cadena de la hora.
  */
 private String regresaHora() {
  DateTimeFormatter fechaFormato = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
  String formattedDate = "Fecha y Hora actuales: " + fechaActual.format(fechaFormato) + "\n";
  return formattedDate;
 }

 /**
  * Métódo que genera el archivo de salida de CCU por dia.
  *
  * @param veh     Vehiculo del cual se generará el ticket.
  * @param counter Número de Dia trabajado/simulado.
  * @return {@link}CCU en formato .txt generado como archivo externo.
  * @throws IOException En caso de que el archivo no se pueda generar.
  */
 public FileWriter generaResumen(CCU veh) throws IOException {
  FileWriter resumen = new FileWriter("Dia #" + contadorDias + ".txt");
  String startCCU = regresaHora();
  startCCU += veh.CCUToText();
  resumen.write(startCCU);
  resumen.close();
  System.out.println("CCU Cerrado por hoy. Resumen del día guardado exitosamente.");
  return resumen;
 }

 /**
  * Método para cerrar el día.
  */
 public void finDia(CCU veh) throws IOException {
  generaResumen(veh);
  contadorDias++;
  fechaActual.plusDays(1);
 }
 

}
