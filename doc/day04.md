### PROBLEMA - PARTE 1
En la primera parte del problema tenemos como entrada un conjunto de '@' y '.', un "grid", en el que se nos pide que devolvamos el número de '@' que tienen menos de 4 '@' adyacentes.

### PROBLEMA - PARTE 2
En la segunda parte, ya no nos piden el número de '@' que cumplan la condición, nos piden seleccionar estos '@', eliminarlos de nuestro "grid", y repetir este proceso hasta que no queden más '@' con menos de 4 '@' adyacentes. Devolveremos la suma total de '@' eliminados en el proceso.

### RESOLUCIÓN
Ambas clases PrintingDepartment se apoyan en la clase Grid(), cuya función será representar el String[] que nos pasan como parámetro, además de incluir la lógica necesaria para conocer los adyacentes a cada posición del tablero, y en caso de la segunda parte permitirnos editar el "grid". Métodos principales:
* checkSurroundings(int i, int j): Se apoya en el resto de métodos, y su función es devolver el número de '@' adyacentes.
* posExists(int k, int l): Comprueba que una posición exista (para cuando se trabaje con los bordes).
* notCenter(int i, int j, int k, int l): Comprueba que la posición no sea justo la que se está evaluando en ese momento, para saltárnosla.
* setDot(int i, int j): sustituye un '@' por un '.'.

#### PrintingDepartment (PARTE 1):
Devuelve el número de '@' que cumplen con nuestra condición.
#### PrintingDepartment (PARTE 2):
Itera hasta que en todo el "grid" no haya ni 1 '@' que cumpla la condición. En cada iteración contará el número de '@' y almacenará sus posiciones, para al final de esta sustituir todos estos '@' por '.', y pasar a la siguiente iteración. 
### CONCEPTOS APLICADOS
Se pueden destacar la aplicación de los siguientes conceptos, entre otros:
* DRY (Don't Repeat Yourself): Gracias a la clase Grid() evitamos tener la lógica del "grid" en ambas clases PrintingDepartment().
* SRP (Single Responsibility Principle): Cada clase tiene su función, ambas PrintingDepartment() se encargan de calcular la solución, y Grid() se encarga de manejar la matriz de Strings.