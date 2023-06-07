import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;
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

 /**
  * Método privado para imprimir la hora en que se hacen los movimientos
  * en el ticket.
  *
  * @return representación en cadena de la hora.
  */
 private String regresaHora() {
  LocalDateTime fechaActual = LocalDateTime.now();
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
 public FileWriter generaResumen(CCU veh, int counter) throws IOException {
  FileWriter resumen = new FileWriter("Dia de Operacion #" + counter + ".txt");
  String startCCU = "";
  startCCU += veh.CCUToText();
  // yep, me estoy reciclando esto de los tickets del estacionamiento. Hay que
  // hacer modificaciones.
  /*
   * startCCU += "Tiempo estacionado: " + veh.timeElapsed() + " minutos. \n";
   * startCCU += "Tarifa a Pagar: $ " + cobro(veh) + " M.N \n";
   * startCCU += "Se pago con: $ " + pagadoCon + " M.N\n";
   * startCCU += "Cambio al cliente: $ " + darCambio(cobro) + "\n";
   */
  resumen.write(startCCU);
  resumen.close();
  System.out.println("CCU Cerrado por hoy. Resumen del día guardado exitosamente.");
  return resumen;
 }

}
