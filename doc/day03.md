### PROBLEMA - PARTE 1
En la primera parte del problema tenemos como entrada una serie de números de tamaño considerable, donde se nos pide extraer el número de dos cifras más grande posible, respetando el orden en el que aparecen. Finalmente, una vez extraídos los números de dos cifras para cada elemento de la entrada, retornamos la suma de estos.

Por ejemplo:

    987654321111111 -> Sería 98
    818181911112111 -> Sería 92, puesto que una vez seleccionamos el 9, ya no podemos volver y coger el 8

### PROBLEMA - PARTE 2
En esta segunda parte el cambio es que, en vez de dos, seleccionamos 12 cifras, el resto del problema es idéntico a la primera parte.

Por ejemplo:

    987654321111111 -> Sería 987654321111
    818181911112111 -> Sería 888911112111

### RESOLUCIÓN
* Clase Joltage(): Para este problema utilizaremos una única clase, ya que habiendo una sola diferencia entre ambos problemas simplemente cambiaremos la n que le pasamos como parámetro a la clase. Esta clase cuenta con 4 métodos, además del execute():
  * findLargestComb(String num, int n): Es el método al que llama execute(), y su función será iterar hasta que logre generar un número de longitud n. Para ir poco a poco generando el número, va llamando a nextString() en cada iteración.
  * nextString(String prev, String next): Devuelve un String [] con los 2 últimos números generados por findLargestComb(). 
  * findLargest(String n): Devuelve el número (Un único dígito) más grnde de lo que queda de String (El n que pasamos como parámetro). 
  * getString(String[] s, int n): Calcula dinámicamente el rango de búsqueda para asegurar que siempre queden suficientes dígitos para completar la longitud n.
### CONCEPTOS APLICADOS
Se pueden destacar la aplicación de los siguientes conceptos, entre otros:
* KISS (Keep It Simple, Stupid): Utilizamos una estrucutra muy sencilla basada en Strings, puesto que el problema no requiere más.
* SRP (Single Responsibility Principle): Cada método tiene una única función:
  * findLargest() busca el mayor dígito.
  * nextString() retorna los dos más recientes.
  * getString() delimita el rango.
  * findLargestComb() genera la solución apoyándose en los demás.