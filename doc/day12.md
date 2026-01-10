### PROBLEMA
Este último día cuenta con una única parte, en donde la entrada está compuesta por dos partes:

    Forma de los regalos                     Regiones del árbol
            0:
            ###                                 
            ##.                               4x4: 0 0 0 0 2 0
            ##.

            1:
            ###
            ##.
            .##
            
            2:
            .##
            ###
            ##.
            
            3:
            ##.
            ###
            ##.
            
            4:
            ###
            #..
            ###
            
            5:
            ###
            .#.
            ###

Como se ve, tendremos una serie de regalos con diferentes formas, donde los '#' indican parte del regalo y los '.' que es espacio libre, y una serie de regiones donde nos proporcionan el área total (4x4 en este caso), y cuántos regalos y de qué tipo tenemos (en este caso, únicamente dos regalos con la forma cuatro).

Para resolverlo, lógicamente deberíamos tener en cuenta todas las posibles rotaciones del regalo. Sin embargo, aquí es donde está la gracia de este último día, ya que en la entrada que tenemos no hay un solo caso en el que haga falta rotar el regalo, ya que en todos los casos donde los regalos caben, caben dando igual su colocación.

### RESOLUCIÓN
Por tanto, a la hora de resolverlo tenemos las siguientes clases:
* Present(): record que gestiona el regalo y su forma. Destaca su método area() encargado de contar el número de '#'.
* Region(): representa el espacio debajo del árbol donde tienen que caber los regalos. Tiene un único método getArea() que devuelve el área de la región.
* Charge(): simplemente funciona como un contenedor, almacena los regalos y las regiones en listas.
* TreeFarm(): es donde realmente se resuelve el problema, apoyándose en las clases anteriores. Destacamos el método execute(), encargado de resolver nuestro problema, para lo cual necesita los siguientes métodos:
  * toCharge(): como su nombre indica, devuelve un objeto de la clase Charge(), la cual habíamos aclarado que funciona como un contenedor: new Charge(forms(raw(lines)), regions(raw(lines))). Vemos que llama a dos métodos: forms(), encargado de extraer las formas de los regalos de la entrada, y regions(), que hace lo mismo pero con las regiones. 
  * solve(): una vez tenemos nuestra instancia TreeFarm() creada y el charge relleno (tras llamar a toCharge()), pasamos a la resolución del problema. Para ello, recorremos las regiones que hemos almacenado en nuestro charge y, por cada una, sumaremos uno al contador siempre y cuando quepan todos los regalos deseados con el método fits(). De este fits() destacamos la línea "map(i -> r.requestPresent().get(i) * charge.forms().get(i).area())", que por cada forma de regalo que queramos guardar obtiene el resultado de multiplicar la cantidad de ese regalo por su área.

### CONCEPTOS APLICADOS
Algunos de los conceptos aplicados, entre otros:
* YAGNI (You Ain't Gonna Need It): no implementamos lógica innecesaria. Como no nos hace falta todo lo relacionado con la rotación de los regalos, no lo implementamos.
* SRP (Single Responsibility Principle): se ve claramente cómo cada método tiene una única función.