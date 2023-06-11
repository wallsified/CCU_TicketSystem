## Tarea Final: Sistema de Tickets

<br>

| Alumnos                     | No. de Cuenta |
| --------------------------- | ------------- |
| Paredes Zamudio Luis Daniel | 318159926     |
| Luis Norberto López García  | 423092075     |

<br>

## Dependencias

- Java 17

<br>

## Comandos de Ejecución

En la carpeta de la tarea se abre la terminal y se ejecutan los siguientes comandos:

```
javac -cp ".:lib/PilasYColas.jar" -d bin src/*/*.java

java -cp ".:lib/PilasYColas.jar:bin/" System/Simulador
```

<br>

## Resultado previsto en terminal.

Consideremos que al repetirse la simulación un par de veces, el resultado varia en cuanto a que
actividad vende más, ganancias del día, membresías usadas, etc. Por lo anterior, pondremos un ejemplo
de como se ve en terminal el resultado de la ejecución de un día de simulación.

```
Bienvenido al Sistema de Ventas del CCU.

Dia Laborado: 2023-06-10
Esto ocurrió hoy:

Fila de Venta:

Membresia: Estudiante
Prioridad: 3
Hora de entrada: 23:25:279:975629155
Actividad: Teatro

Membresia: Estudiante
Prioridad: 3
Hora de entrada: 23:25:279:975710108
Actividad: Museo

Membresia: Visitante Normal
Prioridad: 4
Hora de entrada: 23:25:279:971781855
Actividad: Exposición

Membresia: Visitante Normal
Prioridad: 4
Hora de entrada: 23:25:279:975536520
Actividad: Teatro

Membresia: Visitante Normal
Prioridad: 4
Hora de entrada: 23:25:279:975786242
Actividad: Teatro


Total de Boletos Vendidos: 5
Actividad más vendida: Teatro
Actividad menos vendida: Cine
Ganancias Totales del día: 310.0

CCU Cerrado por hoy. Resumen del día guardado exitosamente.
```

La información anterior también se puede consultar en el archivo _Dia #X.txt_ generado
tras la ejecución.

<br>

## Documentación

Para generar la documentación se ocupa el siguiente comando en la carpeta de la tarea:

```
javadoc -cp ".:lib/PilasYColas.jar" -d docs src/*/*.java
```

Y esta se podrá abrir en el navegador desde el archivo _allclasses-index.html_

<br>

## Notas

- El archivo _PilasyColas.jar_ es el .jar de las clases construidas en las prácticas del
  semestre, por lo que los _import datos.PilasAndColas_ los realizamos a este archivo.

- La construcción del archivo está hecha en dos partes.

  - En el paquete _Priority_ se encuentran las clases referentes a la Cola de Prioridad. Aqui ocupamos tanto las clases proporcionadas por el profesor (vease la práctica de reposición) asi como nuestrás modificaciones a las mismas para que funcionen con nuestro caso.

  - En el paquete _System_ se encuentran las clases de los Visitantes, el sistema de ventas (_CCU_) y la clase principal de la simulación.

- A simples razgos, el sistema funciona de la siguiente manera:

  - Creamos un objeto de tipo _CCU_, el cual es el sistema de ventas. Este objeto ya tiene una cola de prioridad, una fecha de ejecución, un indicador para el total de boletos vendidos y para las ganancias del día.

  - Añadimos _Visitantes_ de forma aleatoria, cada uno con una actividad y membresia igualmente aleatorios (pero cada membresía y cada actividad con una prioridad y precio respectivamente). Estos son añadidos a la cola de prioridad de _CCU_ en donde según su membresía
    es la forma en la que serán atendidos. (Como en los cines donde manejan sistemas de lealtad).

  - Se hace la venta según la cola de prioridad. Por cada venta se suma al total de boletos vendidos y las ganancias obtenidas.

  - Hacemos _"corte de caja"_. Calculamos la actividad más y menos vendida del dia. Se genera un archivo de texto con la información
    de venta del día (ganancias, total de boletos vendidos...) y reiniciamos la cola de ejecución para poder ser repetir nuevamente el proceso con valores de inicio.

- Los archivos generados tras la ejecución se encuentran en la carpeta raiz del proyecto. Cada archivo está diferenciado por el número de dia simulado.

<br>

> _I fight for the USER!..._
