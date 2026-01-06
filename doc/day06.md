### PROBLEMA - PARTE 1
En la primera parte del día 6 nos van a pasar una serie de operaciones en el siguiente formato:
    
    123 328  51 64 
    45 64  387 23
    6 98  215 314
    *   +   *   +

Y con esta entrada lo que nos van a pedir es que sumemos los resultados de todas las columnas, siendo la salida de cada columna el resultado de aplicar el operando de esa columna a todos los números de esta. Ejemplo:

    123 * 45 * 6 = 33210
    328 + 64 + 98 = 490
    51 * 387 * 215 = 4243455
    64 + 23 + 314 = 401
    Resultado total: 33210 + 490 + 4243455 + 401 = 4277556

### PROBLEMA - PARTE 2
En la segunda parte cambia ligeramente el problema. El sistema es el mismo, realizar las operaciones por columna y sumar los resultados, pero ahora en vez de coger los números por columna, también dividiremos por columna dentro de cada columna. Para que se entienda mejor dejo un ejemplo, utilizando la misma entrada e iterando por las columnas de derecha a izquierda:

    123 328  51 64 
    45 64  387 23
    6 98  215 314
    *   +   *   +


    4 + 431 + 623 = 1058
    175 * 581 * 32 = 3253600
    8 + 248 + 369 = 625
    356 * 24 * 1 = 8544
    1058 + 3253600 + 625 + 8544 = 3263827

### RESOLUCIÓN
* Clase TrashCompactor(): Incluye el método execute(), al cuál se le pasa el número de la parte en la que estamos y, acorde a este, ejecuta el calculate() para la parte A o la B.
* Interfaz Command(): Utilizamos el Patrón de Diseño Command, por lo que tenemos una interfaz la cuál será implementada por cada comando que queramos crear, en este caso SumCommand() y MultCommand().
  * Interfaz Calculator(): Tenemos 2 métodos: un método calculate() que cambiará para cada una de las implementaciones, en este caso nuestras dos partes, y un método getCommand() que es default, es decir, es igual para todas las implementaciones de la interfaz.
      * CalculatorA() -> calculate(): Implementamos la parte 1; para ello tenemos un stream que itera por todas las columnas, repitiendo el siguiente proceso:
          1. A partir del signo de la columna obtenemos nuestro comando con getCommand().
          2. Ejecutamos ese comando pasando como parámetro el Stream de todos los valores de esa columna.
          3. Con limit() ignoramos el último valor de la columna, ya que es el signo.
          4. Por último, convertimos los valores de la columna de String a Long, ya que el método execute() de Command requiere un LongStream.

      * CalculatorB() -> calculate(): Implementamos la parte 2, y al igual que en la parte 1 tenemos un stream que itera las columnas, pero en este caso, debido a la dificultad del problema, tenemos algunos métodos más:
        * formatted(): Se encarga de, a partir de una columna de la entrada, devolver un String[] con los números ya unidos como nos pide el problema. Para ello se apoya en varios métodos:
          * getNumsFrom(): Busca extraer los números de cada una de las columnas dentro de nuestra columna, como indica el problema.
          * getCharAt(): Apoya a getNumsFrom() a la hora de extraer un número de una columna y posición concreta.
          * paddedRow(): Si la fila de la columna no llega al maxLen, rellena con espacios.
          * maxLen(): Mide la fila más larga dentro de esa columna.
        * extractRaw(): Su objetivo es recortar un "trozo" vertical de la entrada; para ello utiliza:
          * getSignPos(): Localiza el signo.
          * getStart(): Busca dónde cortar el trozo por la izquierda. Empieza en el índice del signo y va restando hasta que encuentra un espacio.
          * getEnd(): Busca dónde cortar el trozo por la derecha. Empieza en el índice del signo y salta hasta el índice del siguiente signo.
          * substring(): Para evitar fallos, si el índice fuese mayor que la longitud de nuestra línea, devuelve una String vacía.

### CONCEPTOS APLICADOS
Algunos de los conceptos aplicados, entre otros:
* Patrón de Diseño Command: Como se explica en la resolución, utilizamos este patrón de diseño para implementar nuestras 2 operaciones, lo cual nos permite añadir nuevas operaciones en el futuro, y si fuese necesario cambiar alguna de las existentes no habría que cambiar todo el código, solo el comando que se desee cambiar.
* SRP (Single Responsibility Principle): Cada clase y método tiene su función.