### PROBLEMA - PARTE 1
Para la primera parte del problema nos van a pasar una lista de rangos (a-b), donde para cada rango debemos devolver la suma de todos los ids inválidos de este.

Un id será inválido cuando al partirlo por la mitad, nos queda el mismo número en ambos extremos.
Por ejemplo:

    7272 ->  Es inválido, ya que si dividimos justo en la mitad nos quedamos con 72 | 72
    10401 -> Es válido, ya que si dividimos justo en la mitad nos queda 104 | 01

El resultado final será la suma de todos los ids inválidos de todos los rangos que nos pasen como entrada.

### PROBLEMA - PARTE 2
En esta segunda parte la condición se amplía, ya no solo nos valen aquellos ids donde ambas mitades sean igual, sino que también será inválido todo id que al ser dividido por 3, 4, 5... trozos iguales, de lugar a 3, 4, 5... números iguales.

Por ejemplo:
    
    7272 ->      Es inválido, ya que si dividimos justo en la mitad nos quedamos con 72 | 72
    104104104 -> Es inválido, ya que si lo dividimos en 3 partes iguales nos daría 104 | 104 | 104

### RESOLUCIÓN
* Clase Range()

Con la clase Range logramos generar un Stream de los números del rango a partir de una String de tipo "from-to".
* Interfaz InvalidPattern()

Cuenta con un único método, isInvalid(), que devolverá si el id introducio es válido o no. Cuenta con dos implementaciones, InvalidPatternOne() e InvalidPatternTwo(), las cuales simplemente añaden la condición necesaria para que un id sea inválido.
* Clase GiftShop()

Es la clase principal, y debemos pasarle como parametro un patrón, es decir un objeto de tipo InvalidPattern.

Cuenta con el método que resuelve el problema, solve(), el cuál recibe todos los rangos en forma de String[], los convierte a un LongStream utilizando los metodos toRange() y stream() de nuestra clase Range, y finalmente filtra aquellos ids inválidos para posteriormente sumarlos, utilizando el patrón pasado como parámetro.

### CONCEPTOS APLICADOS
Se pueden destacar la aplicación de los siguientes conceptos, en este caso son tres de los 5 principios que conforman SOLID:

* SRP (Single Responsability): En vez de tener una clase GitfShop que se encargue de todo (cosa que tenía previo a la refactorización), tenemos las 3 tareas bien diferenciadas, Range() se encarga de transformar la String que nos pasan como entrada en un Stream de los valores numéricos de los rangos, las implementaciones de InvalidPattern() se encargan de determinar cuando un id es válido y cuando no, y nuestra clase GiftShop() unicamente tiene procesar todos los rangos del input.
* OCP (Open / Closed): Si en un futuro se quisiera añadir o cambiar la forma en la que se determina si un rango es válido, simplemente crearíamos una nueva implemenación de InvalidPattern(), no habría que estar tocando el código ya hecho.
* DIP (Dependency Inversion): Nuestra clase GiftShop() no depende de las implementaciones individuales de InalidPattern(), sino que dependen directamente de la interfaz, por lo que ocurriría lo mismo que en la explicación anterior, si queremos cambiar algo no tenemos que cambiar nuestro código previo.