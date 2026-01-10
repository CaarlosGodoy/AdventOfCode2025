### PROBLEMA - PARTE 1
En la primera parte tenemos una entrada con un conjunto de órdenes:

    L68
    L30
    R48
    L5
    R60
    L55
    L1
    L99
    R14
    L82

Entendemos la "L" como izquierda y la "R" como derecha, y el número es la distancia que se mueve la aguja en ese sentido. El espacio de la aguja va de 0 a 99, por lo que si estamos en 90, por ejemplo, y nos movemos 10 a la derecha, estaríamos en 0 no en 100. En este primer apartado nos piden que tras una serie de órdenes, calculemos cuántas veces la aguja cae exactamente en 0.

### PROBLEMA - PARTE 2
Mismo sistema que antes, una aguja que se mueve entre 0 y 99, y un conjunto de órdenes, pero en este caso ya no nos piden cuántas veces cae en 0, sino cuántas veces pasa por encima de 0. Por ejemplo, antes si estábamos en 90 y nos llegaba R20, no contábamos nada porque la aguja caía en 10, sin embargo ahora contaríamos 1 ya que ha pasado por 0.

### RESOLUCIÓN
* Record Order(): Almacena la orden de forma numérica (si es a la izquierda multiplica por -1, si es a la derecha por 1).
* Record Pointer(): Solo para la parte 2, almacena la posición actual y el número de veces que la aguja ha pasado por 0 hasta el momento.
* Clase Dial(): Es la clase principal donde están implementadas ambas soluciones.
  * Como métodos comunes a ambas partes tenemos:
    * create(): Factory Method para crear un Dial. 
    * add(): Permite añadir una o varias órdenes. 
    * parse(String order): Transforma las string a instancias de Order(). Como el único parámetro de Order, step, es un entero, multiplicamos por -1 el número si es un desplazamiento a la izquierda, y por 1 si es a la derecha. 
    * position(): Calcula la posición de la aguja tras una serie de órdenes, apoyándose en los dos siguientes métodos. 
    * sum(): Suma los steps de cada Order almacenado, sumando 50 ya que empezamos en esa posición. 
    * normalize(): En cada step que se intente sumar en sum(), se llama a este método, que se asegura que el número sumado esté entre 0 y 99. 
  * Parte 1: Como solo nos interesa si la aguja cae exactamente en 0, iremos viendo orden por orden dónde cae la aguja, si cae en 0, sumamos 1. Métodos para esta parte:
    * countA(): Lleva a cabo el recuento explicado. 
    * sumPartial(int size): Calcula la posición final de la aguja pero limitando el número de órdenes a tener en cuenta.
  * Parte 2: Ahora ya nos interesa saber si pasa por encima del 0, es decir, cuántas veces pasa la aguja por un múltiplo de 100. Métodos para esta parte:
    * countB(): Lleva a cabo el recuento explicado. 
    * getTotal(Pointer p, Order o): Vemos que actualiza un objeto Pointer, sumándole al total que ya tenía almacenado el total de esta orden. Para ello, tiene un Stream entre la posición actual y la futura (sin normalizar) y pasa por todos los valores entre medio, sumando 1 por cada múltiplo de 100.

### CONCEPTOS APLICADOS
Se pueden destacar la aplicación de los siguientes conceptos, entre otros:
* Patrón Creacional Factory Method: Empleamos este patrón haciendo el constructor de Dial() privado, y obligando a utilizar el método create() para crear objetos.
* Fluent API: La implementamos permitiendo que métodos como add() devuelvan la propia instancia de la clase (return this), permitiéndonos hacer los tests de esta manera:


    Dial.create().add("ORDEN").countA()