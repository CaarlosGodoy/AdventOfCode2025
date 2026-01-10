### PROBLEMA - PARTE 1
En la primera parte del día 8 tenemos como entrada una serie de coordenadas, y nos piden que tras realizar las 10 uniones más cortas, es decir, de menor distancia euclídea entre sí, devolvamos la multiplicación de los tamaños de los 3 circuitos más grandes. Ejemplo:

    162,817,812
    57,618,57
    906,360,560
    592,479,940
    352,342,300
    466,668,158
    542,29,236
    431,825,988
    739,650,466
    52,470,668
    216,146,977
    819,987,18
    117,168,530
    805,96,715
    346,949,466
    970,615,88
    941,993,340
    862,61,35
    984,92,344
    425,690,689

De este ejemplo los tres circuitos de mayor tamaño son de 5, 4 y 2, por lo que la solución es 5 * 4 * 2 = 40.

### PROBLEMA - PARTE 2
En la segunda parte el problema cambia considerablemente. Ahora, nos piden que realicemos uniones hasta que estén todas las coordenadas en un mismo circuito, y nuestra salida será la multiplicación de las "X" de las dos coordenadas que completen el circuito. Ejemplo:

    Misma entrada que Parte 1 -> Últimas dos coordenadas: 216,146,977 --- 117,168,530
    Resultado: 216 x 117 = 25272

### RESOLUCIÓN
Aparte de la clase principal, utilizamos dos clases más:
* Cord(): Recibe los 3 valores de la coordenada.
* Cable(): Representa la unión entre dos instancias de Cord().

Y la clase Playground(), la cual tiene implementada la lógica necesaria para resolver ambas partes: Playground(): Cuenta con tres métodos públicos, un factory method (create()), y dos execute(), además de otros métodos que se usan en ambas partes:

* executeA():
  1. Inicializamos "groups" como una List<List<Cord>>, en donde en cada lista interna habrá una única coordenada. 
  2. sortedCables(coords(s)).stream().limit(n).toList().forEach(cable -> union(groups, cable));

     Pasamos a la línea más importante de la parte 1, donde primero almacenamos en una lista las n uniones de menor distancia, como nos pide el problema, y más tarde en el forEach() comprobamos si entre esas conexiones existen algunas que se puedan convertir en circuitos de mayor tamaño, combinándose entre sí.
  3. Con la línea de arriba actualizamos "groups", por tanto simplemente multiplicamos los tamaños de los tres mayores circuitos, como pide el problema.

* executeB():
  1. Inicializamos "groups" como una List<List<Cord>>, en donde en cada lista interna habrá una única coordenada. 
  2. Primero almacenamos en una lista las uniones de menor distancia, como nos pide el problema, y ahora es donde cambia, ya que realizaremos uniones hasta que se cumpla nuestra condición (filter()), siendo esta que se dé el caso en el que al unir dos circuitos, el tamaño de la lista pase a ser 1, lo que significa que hemos unido todas las coordenadas en un único circuito. 
  3. Devolvemos la multiplicación de las coordenadas X de la última unión, como nos pedía el problema.

Dentro de Playground() destacan varios métodos más, como:
* sortedCables(List<Cord> cords): Compara todas las coordenadas y devuelve una lista con todas las uniones (instancias de Cable()) ordenadas de menor a mayor distancia entre sí. 
* union(List<List<Cord>> groups, Cable cable): Se apoya en compareGroups() y findGroupsHaving(), siendo su función devolver si se han unido instancias de Cable() en la lista groups. 
* findGroupsHaving(List<List<Cord>> groups, Cord c): Busca el primer grupo que contiene la coordenada que se está tratando en ese momento. 
* compareGroups(List<Cord> g1, List<Cord> g2, List<List<Cord>> groups): Compara dos grupos, si son iguales no hace nada y devuelve false, pero si son diferentes los une en un solo grupo y devuelve true.

### CONCEPTOS APLICADOS
Algunos de los conceptos aplicados, entre otros:

* Patrón Creacional Factory Method: Empleamos este patrón haciendo el constructor de Playground() privado, y obligando a utilizar el método create() para crear objetos.
* DRY (Don't Repeat Yourself): Este es un caso muy claro, ya que unificamos los dos problemas en una única clase, evitando repetir todos los métodos comunes entre ambos.
* Good Naming: Los nombres de los métodos y clases dejan bastante claro cuál es su función.