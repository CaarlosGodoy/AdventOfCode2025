### PROBLEMA - PARTE 1
En la primera parte del día 9 nos dan como entrada una serie de puntos, que representan esquinas, y debemos devolver el área del rel rectángulo más grande posible que podamos hacer respetando una única condición, que dos esquinas opuestas sean '#'. Con un ejemplo queda más claro:

    ENTRADA              REPRESENTACIÓN               RESULTADO
    -----------          -------------------          ---------------------
    7,1                  ..............               ..............
    11,1                 .......#...#..               ..OOOOOOOOOO..
    11,7                 ..............               ..OOOOOOOOOO..
    9,7                  ..#....#......               ..OOOOOOOOOO..
    9,5                  ..............               ..OOOOOOOOOO..
    2,5                  ..#......#....               ..OOOOOOOOOO..
    2,3                  ..............               ..............
    7,3                  .........#.#..               .........#.#..
                         ..............               ..............

En este ejemplo devolvemos 50.
### PROBLEMA - PARTE 2
En la segunda parte se introduce un pequeño cambio, nos siguen pidiendo lo mismo, el área del mayor rectángulo, pero ahora también debemos respetar el polígono que forman los puntos. Ejemplo:

    ENTRADA         REPRESENTACIÓN (#)      POLÍGONO (X)      RESULTADO (O)
    -----------     ------------            ------------      --------------
    7,1             ..............          ..............    ..............
    11,1            .......#...#..          .......#XXX#..    .......#XXX#..
    11,7            ..............          .......XXXXX..    .......XXXXX..
    9,7             ..#....#......          ..#XXXX#XXXX..    ..OOOOOOOOXX..
    9,5             ..............          ..XXXXXXXXXX..    ..OOOOOOOOXX..
    2,5             ..#......#....          ..#XXXXXX#XX..    ..OOOOOOOOXX..
    2,3             ..............          .........XXX..    .........XXX..
    7,3             .........#.#..          .........#X#..    .........#X#..
                    ..............          ..............    ..............

### RESOLUCIÓN
* Cord(): Representación de los puntos que nos pasan como entrada.
* MovieTheater(): En esta clase tenemos la resolución de ambos problemas, e incluso utilizan el mismo método solve(), con una pequeña diferencia basada en lo que se explica a continuación:
  * solve(): Vemos que se anidan dos streams, lo que hace que se comprueben todos los puntos entre sí, y destacamos la siguiente línea:

        filter(j -> !checkValidity || isValid(cords, cords.get(i), cords.get(j)))

    Esta línea es clave en nuestro código, ya que toda la lógica del isValid() es solo necesaria en la parte 2. Por ello, utilizamos la variable checkValidity; esta será false si se llama a executeA(), lo que hará que el OR ni siquiera ejecute el isValid() (ya que la primera parte de la condición ya es verdadera), y si se ejecuta el executeB() se pasa como true, para que esa primera parte falle y pase a la validación.

    De esta manera evitamos estar creando más métodos, y ya el resto del stream es buscar el rectángulo de mayor área.

    A partir de aquí todos los métodos son únicamente de la parte 2, y es donde se complica la cosa. Inicialmente había pensado almacenar los puntos válidos e ir comprobando si los puntos de los posibles rectángulos estaban dentro de la lista, pero rápidamente te das cuenta de que en un problema tan grande como el nuestro esto es imposible. Por tanto, encontramos un algoritmo que soluciona esto.

    Ray Casting: Sirve si queremos saber si un punto está dentro del polígono, pero no sabemos cuántas paredes hay en una dirección. Por tanto, lanzamos un "rayo" desde el punto que estamos comprobando hacia una dirección, y contamos el número de veces que cruza una de estas paredes. Si la cruzas un número impar de veces significa que estás dentro, si es par estás fuera.

    (Esto se explica porque necesitamos un número par de cruces para volver a estar dentro. Por ejemplo, estamos dentro de una casa, y salimos, ahí llevaríamos 1 cruce (impar) y estamos fuera, si seguimos caminando y nos metemos en otra casa sería nuestro 2 cruce (par), y volveríamos a estar dentro)

    Métodos utilizados para ello:
    * isValid(): Decide si el rectángulo entre las dos coordenadas es válido, para ello llama a los dos siguientes métodos:
    * isInside(): Comprueba si el centro del rectángulo está dentro de lo permitido. Si no lo estuviese, retorna directamente falso.
        * crosses(): Antes de explicar el método, aclaramos que isValid() le llama repetidamente, como se ve, una vez por cada pared de la entrada, y recibe como parámetros ambas coordenadas, y los mínimos y máximos de X e Y de estas. Ahora, acerca del funcionamiento de crosses(), lo que hace es comprobar si la pared entra en los límites del rectángulo (por ejemplo, si la pared es vertical, comprueba si su X está dentro de los límites X del rectángulo).

### CONCEPTOS APLICADOS
Algunos de los conceptos aplicados, entre otros:
* SRP (Single Responsibility Principle): Cada clase y método tiene su función.
* Programación estructurada: He intentado mantener los métodos y sus nombres lo más legibles posible (Good Naming), además de mantenerlos en un tamaño de 1 o 2 líneas por método. Me habría gustado no tener que pasar tantos parámetros a ciertos métodos pero no ha sido posible.
