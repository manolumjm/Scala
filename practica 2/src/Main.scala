/**
  * Created by manol on 06/04/2017.
  */
/**
  * Objeto singleton para probar la funcionalidad del triangulo
  * de Pascal
  */
object Main extends App {

  /**
    * Metodo main: en realidad no es necesario porque el desarrollo
    * deberia guiarse por los tests de prueba
    *
    * @param args
    */
  override def main(args: Array[String]) {
    println("................... Triangulo de Pascal ...................")

    // Se muestran 10 filas del trinagulo de Pascal
    for (row <- 0 to 10) {
      // Se muestran 10 10 columnas
      for (col <- 0 to row) {
        //print("columna= " + col + " ,fila= " +row +" ");
        print(calcularValorTrianguloPascal(row, col) + " ")
      }


      // Salto de linea final para mejorar la presentacion
      println()
    }

    // Se muestra el valor que debe ocupar la columna 5 en la fila 10
    println("\n...............Mostrar valores del Triangulo de Pascal.................\n")
    println("Fila 10, Columna 5 (10,5) = " + calcularValorTrianguloPascal(10, 5))
    println("Fila 0, Columna 0 (0,0) = " + calcularValorTrianguloPascal(0, 0))

    println("\n................Chequeando balanceo....................\n")
    val chequear = List( '(','(',')',')')
    val chequear1 = "(if (a ¿ b) (b/a) else (a/(b*b)))".toList
    val chequear2 = "(())".toList
    val resultado = chequearBalance(chequear2);
    println("Resultado de chequear " + chequear2 + "es " + resultado);


    println("\n................... Contar cambios posibles ...................\n")
    val monedas1 = List(500,5,50,100,20,200,10)
    val cantidad1 = 300
    val monedas2 = List(1,2,5)
    val cantidad2 = 10
    val monedas3 = List(1,2)
    val cantidad3 = 4
    val monedas4 = List(1,2,2)
    val cantidad4 = 4

    val cambio1 = contarCambiosPosibles(cantidad1,monedas1)
    val cambio2 = contarCambiosPosibles(cantidad2,monedas2)
    val cambio3 = contarCambiosPosibles(cantidad3,monedas3)
    val cambio4 = contarCambiosPosibles(cantidad4,monedas4)

    System.out.println("Para los tipos de monedas "+ monedas1 + " existen " + cambio1 + " cambios")
    System.out.println("Para los tipos de monedas "+ monedas2 + " existen " + cambio2 + " cambios")
    System.out.println("Para los tipos de monedas "+ monedas3 + " existen " + cambio3 + " cambios")
    System.out.println("Para los tipos de monedas "+ monedas4 + " existen " + cambio4 + " cambios")

    println("\n................... Búsqueda binaria genérica ...................\n")

    val vector = List(1,3,5,7,10)
    val vector1 = List("jo", "xao", "hola", "adios")
    val valorBusqueda = "hola"
    val valorBusqueda2 = 7

    if(busquedaBinariaGenerica(vector1,valorBusqueda, (x: String,y:String) => (x.length > y.length)) == -1)
      System.out.println("No existe el valor "+ valorBusqueda + " en el vector" + vector)
    else
    {
      val result = busquedaBinariaGenerica(vector1,valorBusqueda, (x: String,y:String) => (x.length > y.length))
      System.out.println("El indice del valor "+ valorBusqueda + " en el vector "+ vector + " es " + result)
    }

    def funcion(elementoVector: Int, valorABuscar:Int) : Boolean = {
      if(elementoVector > valorABuscar) true
      else false
    }

    if(busquedaBinariaGenerica(vector,valorBusqueda2,(x:Int, y:Int) => (x > y)) == -1)
      System.out.println("No existe el valor "+ valorBusqueda2 + " en el vector " + vector)
    else
    {
      val result = busquedaBinariaGenerica(vector,valorBusqueda2,(x:Int, y:Int) => (x > y))
      System.out.println("El indice del valor "+ valorBusqueda2 + " en el vector "+ vector + " es " + result)

    }


  }


  /**
    * Ejercicio 1: funcion para generar el triangulo de Pascal
    *
    * @param columna Columnas a generar
    * @param fila Fila a generar
    * @return Devuelve el valor para una (fila,columna)
    */
  def calcularValorTrianguloPascal(fila: Int, columna: Int): Int = {
    // Debe devolver 1 si es: (0, n) o tambien si ( col, fil) con col = fil, por ejemplo (3,3)
    if (columna == fila || columna == 0)
      1
    else if(columna > fila)
      return -1
    else
      calcularValorTrianguloPascal(fila - 1, columna - 1) + calcularValorTrianguloPascal(fila - 1, columna)
  }



  /**
    * Ejercicio 2: funcion para chequear el balance de parentesis
    *
    * @param cadena cadena a analizar
    * @return valor booleano con el resultado de la operacion
    */
  def chequearBalance(cadena : List[Char]): Boolean = {
    /**
      * Función que cuenta los paréntesis de la cadena.
      * @param cadena Lista con los paréntesis a chequear
      * @param contador Variable para determinar el equilibro entre parentesis
      * @return Devuelve 0 si es verdadero u otro valor si es falso.
      */
    def contarParentesis(cadena : List[Char], contador : Int): Int = {
      if (cadena.isEmpty)
        contador
      else {
        val acumulador = cadena.head match {
          case ')' => contador - 1
          case '(' => contador + 1
          case _ => contador
        }

        if (acumulador < 0)
          acumulador
        else
          contarParentesis(cadena.tail, acumulador)
      }
    }
    //contarParentesis(cadena, 0) == 0
    if(contarParentesis(cadena, 0) == 0) true;
    else false;
  }


  /**
    * Ejercicio 3: funcion para determinar las posibles formas de devolver el
    * cambio de una determinada cantidad con un conjunto de monedas. Si se introducen
    * tipos de monedas repetidas, se encarga de no contarlas como diferentes
    *
    * @param cantidad Cantidad de dinero que se quiere devolver
    * @param monedas Tipos de monedas con las que devolver el dinero
    * @return contador de numero de devoluciones posibles
    */
  def contarCambiosPosibles(cantidad: Int, monedas: List[Int]): Int =
  {
    if(cantidad == 0)
      1
    else if(monedas.length == 0 || cantidad < 0)
      0
    else
    {
      /* Esta condición al reves no funcionaría
         if(monedas.head == monedas(1) && monedas.length >= 2)
         , ya que cuando el algoritmo recursivo
         llegase a la hoja, el valor monedas(1) sería null. Al poner primero la
         condición monedas.length, hace que no tenga que valorar monedas(1), ya que
         la primera condicion ya era falsa.
       */
      monedas.partition(x => x < monedas(monedas.length/2))
      if(monedas.length >= 2 && monedas.head == monedas(1))
        contarCambiosPosibles(cantidad,monedas.tail)
      else contarCambiosPosibles(cantidad-monedas.head,monedas) + contarCambiosPosibles(cantidad,monedas.tail)
    }
  }



  /**
    * Ejercicio 4: función para determinar el índice de un determinado elemento dentro
    * de un array de objetos. Puede tratarse de cual tipo de objetos, ya que es un metodo genérico
    * @param vector Vector sobre el que se buscará un valor concreto para hallar su índice
    * @param valor Valor a buscar dentro del vector
    * @param comparar Funcion para indicar como se van a comparar los objetos del vector
    * @tparam A
    * @return Índice donde se encuentra el valor buscado. En caso de que no existiese, devuelve -1
    */
  def busquedaBinariaGenerica[A](vector: List[A], valor: A, comparar:(A,A) => Boolean) : Int = {
    /**
      * Funcion encargada de la division del vector para una mejora O(log n) en la
      * búsqueda del índice del vector.
      * @param principio Indice inicial sobre el que empezar a buscar el valor
      * @param fin Indice final sobre el que empezar a buscar el valor
      * @return Devuelve el índice donde se encuentra el valor si existe, sino -1.
      */
    @annotation.tailrec
    def busqueda(principio: Int, fin: Int): Int = {
      // Caso base: Comprobar que el vector contiene elementos, sino devolver -1
      if(principio > fin)
      {
        // Preguntar: Si pongo solo -1 falla, parece ser que el -1 solo hace cosas raras
        return -1
      }

      // Hallamos la mitad del vector, para trabajar recursivamente con él
      val mitad = principio + (fin-principio+1)/2

      // Caso 1: Si el valor que buscamos corresponde con mitad, el índica que buscamos es mitad
      if(vector(mitad) == valor)
        mitad
      // Caso 2: Comparar los elementos para saber si el valor que buscamos está en la primera mitad del vector
      else if(comparar(vector(mitad),valor))
        busqueda(principio,mitad-1)
      // Caso 3: Si el valor que buscamos no está en la primera mitad del vector, estará en la segunda mitad del vector
      else
        busqueda(mitad+1,fin)
    }
    busqueda(0,vector.length-1)
  }

}
