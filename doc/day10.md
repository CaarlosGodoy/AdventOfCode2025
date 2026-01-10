### PROBLEMA - PARTE 1
Input:

    [.##.] (3) (1,3) (2) (2,3) (0,2) (0,1) {3,5,4,7}
    [...#.] (0,2,3,4) (2,3) (0,4) (0,1,2) (1,2,3,4) {7,5,12,7,2}
    [.###.#] (0,1,2,3,4) (0,3,4) (0,1,2,4,5) (1,2) {10,11,11,5,10,5}

Para esta parte tenemos una entrada similar a la de arriba, dividida en tres partes:
* [.##.]: Una secuencia de '.' y '#', que nos indica cómo debe ser el resultado tras pulsar botones. En este caso: [0, 1, 1, 0]
* (3) (1,3) (2) (2,3) (0,2) (0,1): Una lista de botones. Los números indican a qué luces de la secuencia anterior afectan. Por ejemplo, (2, 3) cambiaría el valor en los índices 2 y 3, por lo que podría darse un cambio como este: [0, 1, 0, 1] --> [0, 1, 1, 0]
* {3,5,4,7}: No utilizamos esto en la primera parte.

Nuestro objetivo es devolver el número mínimo de botones que logran dejar una secuencia como nos piden. Ejemplo:

[.##.] (3) (1,3) (2) (2,3) (0,2) (0,1)
1. Pulsamos (0, 1): [0, 0, 0, 0] -> [1, 1, 0, 0]
2. Pulsamos (0, 2): [1, 1, 0, 0] -> [0, 1, 1, 0]

Con dos botones logramos nuestro objetivo.
### PROBLEMA - PARTE 2
Input:

    [.##.] (3) (1,3) (2) (2,3) (0,2) (0,1) {3,5,4,7}
    [...#.] (0,2,3,4) (2,3) (0,4) (0,1,2) (1,2,3,4) {7,5,12,7,2}
    [.###.#] (0,1,2,3,4) (0,3,4) (0,1,2,4,5) (1,2) {10,11,11,5,10,5}

Para esta parte tenemos una entrada similar a la de arriba, dividida en tres partes:
* [.##.]: No utilizamos esto en la segunda parte.
* (3) (1,3) (2) (2,3) (0,2) (0,1): Una lista de botones. Los números indican a qué luces de la secuencia anterior afectan. Por ejemplo, (2, 3) aumentarían el valor en los índices 2 y 3, por lo que podría darse un cambio como este: [0, 1, 0, 1] --> [0, 1, 1, 2]
* {3,5,4,7}: Este es el resultado final que buscamos.

Como se ve, cambia totalmente el problema, pues pasa de ser un problema donde las decisiones son binarias, a uno de acumulación. Por ello, para el día 10 tenemos dos clases Factory() separadas, cada una con una forma de resolución diferente.

### RESOLUCIÓN - PARTE 1
Entendido, aquí tienes tu texto exactamente igual a como lo escribiste, corrigiendo únicamente las faltas de ortografía (tildes y letras mal escritas) y respetando todos los parámetros de los métodos:

RESOLUCIÓN - PARTE 1
Esta primera parte la podemos resolver con programación dinámica, en este caso memoization. Para ello en cada botón tomamos una decisión, pulsarlo o no, de manera que se evalúan todas las posibles combinaciones de botones. Por cómo funciona memoization, cada camino que se quiera guardar primero se busca en el propio diccionario (mem), y si ya existiese, reutilizamos el valor guardado para evitar recalcular el subproblema. Este proceso lo llevan a cabo los siguientes métodos:
* execute(String[] s): Método al que llamamos para resolver el problema. Para cada línea de la entrada limpia el diccionario, y llama a solve. 
  * initial(int size): Necesario a la hora de llamar a solve(), dejando como current al primer botón. 
* solve(List<Long> goal, List<List<Long>> btns, int index, List<Long> current): Resuelve el problema mediante memoization. Es recursivo, y en cada una de sus ejecuciones comprueba:
  * Si hemos encontrado la solución. 
  * Si no nos quedan botones. 
  * Si el camino ya está en el diccionario.
  
  Si no se cumple ninguna de las condiciones, se queda con el mejor resultado entre haber pulsado o no el botón. 
* skipBtn(List<Long> goal, List<List<Long>> btns, int index, List<Long> current): Salta el botón. 
* takeBtn(List<Long> goal, List<List<Long>> btns, int index, List<Long> current): Pulsa el botón. 
  * next(List<Long> goal, List<List<Long>> btns, int index, List<Long> current): Genera nuestro siguiente current.

El resto de métodos (lightArray(), btnsList() y toBinaryArray()) sirven para transformar el resultado buscado y los botones de la entrada, en arrays binarios.

### RESOLUCIÓN - PARTE 2
Aquí cambia totalmente el problema, y en mi caso es el que más me ha costado de todo el calendario. Para resolverlo intenté aplicar el mismo modelo que en la parte 1, pero evidentemente no fue posible, ya que en la entrada del AoC tenemos objetivos como {101, 70, 74, 72, 97, 85, 95, 84, 122, 108}, lo cual hace imposible para memoization resolver el problema en un tiempo razonable. Para ello, vamos a utilizar una estrategia de poda por paridad, que funciona de la siguiente manera:
1. Generamos todas las combinaciones posibles de botones con generatePatterns(), y las agrupamos en un diccionario, de manera que tenemos como clave su patrón de paridad (obtenido con getParity()), es decir, un 1 si es impar y un 0 si es par, y como valor el número mínimo de pulsaciones para llegar ahí. 
2. En cada iteración extraemos la paridad de nuestro objetivo (goal), utilizando parity(). 
3. Podamos aquellas combinaciones en las que, pulsando el botón (verificado con isButtonOn()), superamos el voltaje objetivo de alguna luz. 
4. Aquí es donde vemos el verdadero uso de la paridad en newJoltageArray(), ya que entendemos que los botones para llegar, por ejemplo, a {4, 8, 16}, son los mismos que para {2, 4, 8}, y que para {1, 2, 4}, por lo que dividimos entre 2 nuestros voltajes objetivo y multiplicamos por 2 el número de botones necesarios en value(), obteniendo el mismo resultado, pero ahorrándonos muchas combinaciones e iteraciones. 
5. Dentro del solve() aplicamos nuevamente memoization con el diccionario mem, evitando recalcular subproblemas. 
6. Cuando todas las luces estén a 0, tenemos nuestra solución.

### CONCEPTOS APLICADOS
Algunos de los conceptos aplicados, entre otros:
* Programación estructurada: Previo a la refactorización tenía muchos menos métodos, pero generatePatterns() y solve(), a pesar de ser de una o dos líneas, tenían streams enormes imposibles de leer. Por ello, he optado por añadir varios métodos, mejorando considerablemente la legibilidad del código.
* Abstracción: Al ser un problema con alta complejidad matemática, buscamos ocultarla en diferentes métodos, de manera que fuera de ellos no se sepa cómo se logra obtener ese resultado, sino únicamente el resultado en sí.