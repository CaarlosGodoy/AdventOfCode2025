### PROBLEMA - PARTE 1
En la primera parte nos pasan como entrada un grafo, de la siguiente manera:

    aaa: you hhh
    you: bbb ccc
    bbb: ddd eee
    ccc: ddd eee fff
    ddd: ggg
    eee: out
    fff: out
    ggg: out
    hhh: ccc fff iii
    iii: out

Nuestro objetivo es devolver la cantidad de caminos que se pueden llevar a cabo entre "you" y "out".
### PROBLEMA - PARTE 2
En la segunda parte cambia ligeramente, aunque nos siguen pasando un grafo, pero ahora de este estilo:

    svr: aaa bbb
    aaa: fft
    fft: ccc
    bbb: tty
    tty: ccc
    ccc: ddd eee
    ddd: hub
    hub: fff
    eee: dac
    dac: fff
    fff: ggg hhh
    ggg: out
    hhh: out

Nuestro objetivo va a ser el mismo que en la primera parte, devolver el número de caminos entre "svr" y "out", pero para esta parte deberemos tener en cuenta que un camino solo es válido si entre medias pasa por "fft" y "dac", dando igual el orden entre estos.
### RESOLUCIÓN
Debido a las similitudes entre ambas partes, utilizaremos el mismo código para ambas:
* Clase Reactor(): Incluye los execute() de ambas partes. Ambos tienen la misma forma, se crea una instancia de Graph() con un Factory Method, y se llama al método countPaths(), donde nuestro código diferencia ambas partes. Poniendo el último parámetro de este método, checkNodes, a falso, estamos resolviendo la parte uno, poniéndolo a true, la dos.
* Clase Graph(): Destacamos sus dos atributos y sus dos métodos.
  * Atributos:
    * Map<String, List<String>> g: Almacena nuestro grafo. 
    * Map<String, Long> mem: Funciona como un diccionario, necesario para el método countPaths() ya que lo resolvemos mediante memoization. 
  * Métodos:
    * graph(String[] input): Factory Method para crear un grafo a partir del input que se pase. 
    * countPaths(String current, boolean dac, boolean fft, boolean checkNodes): Método que resuelve ambos apartados del día 11. Destacamos la variable checkNodes, que como comentamos previamente determina qué parte se está ejecutando. Esto se ve a la hora de contar un camino, ya que como se ve en el primer if, si el checkNodes es falso, ni siquiera llega a evaluar dac y fft. El resto del método es una implementación estándar de memoization, donde comprueba si la clave está en el diccionario, si no está la mete y la devuelve, y si sí estaba solo la devuelve.

### CONCEPTOS APLICADOS
Algunos de los conceptos aplicados, entre otros:
* Patrón Creacional Factory Method: Empleamos este patrón haciendo el constructor de Graph() privado, y obligando a utilizar el método graph() para crear objetos.
* SRP (Single Responsibility Principle): cada método cumple una única función.
* Inmutabilidad: hacemos que los dos atributos de la clase Graph() sean inmutables (aunque mem cambie durante la ejecución, su referencia no lo hace, que es a lo que nos referimos con inmutable).