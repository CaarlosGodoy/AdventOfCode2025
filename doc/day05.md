### PROBLEMA - PARTE 1
En la primera parte nos pasan una serie de rangos (Formato: a-b), y una serie de ids. El objetivo es devolver el número de ids que pertenecen a alguno de los rangos.

### PROBLEMA - PARTE 2
En la segunda parte ignoramos los ids que nos pasan y nos centramos únicamente en los rangos. Devolveremos el número de ids diferentes que podamos sacar de los rangos.

Por ejemplo:

    3-5 7-8 4-5 --> Sería 5, porque a pesar de que 4 y 5 aparecen dos veces, los contamos una vez

### RESOLUCIÓN
Utiizamos únicamente 2 clases, Range() y Cafeteria():
* Range(): Es la clase más sencilla de las dos, simplemente es un record al que se le pasan el inicio y el final del rango. Cuenta con varios métodos muy básicos, que nos permiten crear un nuevo Range (of()), ver si un número se encuentra dentro del rango (contains()), y obtener el tamaño del rango (size()).
* Cafeteria(): Es la clase principal en la que destacan dos métodos, los execute de cada una de las partes:
  * executeA(): Se apoya en los métodos ids() y ranges(), que recogen la información que indican sus nombres del input, y simplemente itera los ids y suma aquellos que estén contenidos en algún rango.
  * executeB(): Como se ve en el propio método, estamos haciendo una suma (sum()) de la unión (merge()) de los rangos ordenados (sortedRanges()):
    * sum(List<Range> merged): Suma todos los rangos.
    * merge(List<Range> sorted): Genera una lista con todos los rangos "unidos", es decir, si por ejemplo teníamos 3-8 y 5-10, los junta y mete en la lista 3-10.
    * sortedRanges(String s): Recoge todos los rangos que nos pasan como entrada, los ordena, y los devuelve en una lista.
### CONCEPTOS APLICADOS
Para no repetir los mismos principios que en los días anteriores ,nos vamos a centrar en los fundamentos:
* Abstracción: Hemos ocultado toda el trabajo para extraer los datos de la entrada en la clase Range().
* Modularidad: La solución se divide en dos módulos: Range() para la gestión de datos y Cafeteria() para la lógica de resolución.
* Acoplamiento: Al utilizar Range() como un objeto independiente, separamos el formato en el que nos pasen los rangos de la resolución del problema.
* Cohesión: Los métodos de cada clase ayudan a lograr la tarea de esta, y no se entretienen en con otras tareas.

