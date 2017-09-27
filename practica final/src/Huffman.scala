import scala.io.Source

/**
  * Created by manol on 17/06/2017.
  */
object Huffman {

  type TablaCodigo = List[(Char, List[Int])]

  /**
    * Devuelve el peso asociado al nodo
    * @param nodo Nodo específico sobre el que calcular el peso
    * @return Peso del nodo especificado
    */
  def calcularPeso(nodo: NodoArbolHuffman): Int = nodo match {
    case NodoHojaArbolHuffman (_,peso) => peso
    case NodoInternoArbolHuffman(nodoIzq, nodoDcha, _, _) =>
      calcularPeso(nodoIzq) + calcularPeso(nodoDcha)
  }

  /**
    * Devuelve la lista de caracteres que representa un nodo, considerando
    * los nodos inferiores
    * @param nodo Nodo especifico a partir del que empezar la búsqueda de caracteres
    * @return Lista de caracteres del nodo especificado
    */
  def obtenerCaracteres(nodo: NodoArbolHuffman): List[Char] = nodo match {
    case NodoHojaArbolHuffman(caracter,_) => List(caracter)
      // Uso de la concadenación para
    case NodoInternoArbolHuffman(nodoIzq, nodoDcha, _, _) => obtenerCaracteres(nodoIzq) ::: obtenerCaracteres(nodoDcha)
  }

  /**
    * Dados dos nodos, genera un arbol nuevo
    * @param nodoIzq Nodo izquierdo
    * @param nodoDcha Nodo derecho
    * @return Nuevo árbol con los nodos anteriores
    */
  def generarArbol(nodoIzq: NodoArbolHuffman, nodoDcha: NodoArbolHuffman): NodoArbolHuffman = {
    NodoInternoArbolHuffman(nodoIzq, nodoDcha,
      obtenerCaracteres(nodoIzq) ::: obtenerCaracteres(nodoDcha), calcularPeso(nodoIzq) + calcularPeso(nodoDcha))
  }

  /**
    * Dado un texto, es posible calcular y construir un arbol de codificacion
    * analizando sus caracteres y contadores de ocurrencia
    * @param caracteres para construir el arbol
    * @return arbol
    */
  def construirArbolCodificacion(caracteres: List[Char]): NodoArbolHuffman = {
    repetir(singleton,combinar)(generarListaNodosHojaOrd(frecuenciasCaracteres(caracteres))).head //Nodo codificado
  }

  /**
    * Calcula las frecuencias de aparición de cada carácter dado un texto a partir
    * del que generar el código
    * @param texto Lista de caracteres
    * @return Pares (carácter-contador de ocurrencias)
    */
  def frecuenciasCaracteres(texto: List[Char]): List[(Char, Int)] = {
    texto.groupBy(caracter => caracter) // agrupamos un caracter y la lista de todos los elementos de ese mismo caracter
      .map(caracter => (caracter._1,caracter._2.length)).toList // obtenemos el numero de veces que se repite cada caracter
  }

  /**
    * Ordena una lista de frecuencia de caracteres de menor a mayor (según peso) y construye con
    * ellas una lista de nodos hoja (uno por carácter)
    * @param frecCarac Lista de frecuencia de carácteres a usar
    * @return Lista de nodos hoja (uno por carácter)
    */
  def generarListaNodosHojaOrd(frecCarac: List[(Char, Int)]): List[NodoHojaArbolHuffman] = {
    frecCarac.sortWith((frec1, frec2) => frec1._2 < frec2._2) // ordena de menor a mayor frecuencia de aparicion de caracteres
      .map(frec => NodoHojaArbolHuffman(frec._1, frec._2)) // genera una lista de nodos hoja
  }

  /**
    * Comprueba si una lista de nodos contiene a un único elemento
    * @param nodos Lista de nodos a valorar
    * @return Verdadero si solo hay uno nodo, en caso contrario es falso
    */
  def singleton(nodos: List[NodoArbolHuffman]): Boolean = nodos.size.equals(1)

  /**
    * Combina todos los nodos contenidos en una lista de nodos
    * @param nodos Lista de nodos a usar
    * @return Lista con los nodos combinados
    */
  def combinar(nodos: List[NodoArbolHuffman]): List[NodoArbolHuffman]= {

    (generarArbol(nodos.head, nodos.tail.head) :: nodos.tail.tail) // une el nodo creado por los dos primeros con el resto de nodos
        .sortBy(nodo => calcularPeso(nodo)) // Ordenar de menor a mayor peso
  }


  /**
    * Función que realiza llamadas a funciones definidas anteriormente hasta que
    * la lista de nodos contenga un único elemento
    * @param unico Lista para comprobar si solo queda un elemento
    * @param funcion Función que combina dos primeros nodos en un nodo interno
    * @param nodos Lista con todos los nodos
    * @return Lista de nodos con un único elemento
    */
  def repetir(unico: List[NodoArbolHuffman] => Boolean, funcion: List[NodoArbolHuffman] => List[NodoArbolHuffman])
             (nodos: List[NodoArbolHuffman]): List[NodoArbolHuffman] = {

    unico(nodos) match {
      case true =>  nodos // Queda un solo elemento, devolvemos true
      case false => repetir(unico, funcion)(funcion(nodos)) // Aún queda más de un elemento, seguimos repitiendo
    }
  }

  /**
    * Obtiene la codificacion de un caracter siendo la tablaCodigo
    * @param tabla tabla (TablaCodigo) que contiene la codificacion de los caracteres
    * @param caracter El carácter a buscar su codificación
    * @return Lista con los enteros de la codificación del caracter
    */
  def codificarConTabla(tabla : TablaCodigo)(caracter : Char) : List[Int] = {
    val lista = tabla.filter(elementoTabla => (elementoTabla._1).equals(caracter))
    if(lista.isEmpty) //Se filtra por caracter, cuando se encuentra se devuelve su codificacion, sino se devuelve una lista vacía
      List()
    else lista.head._2
  }

  /**
    * Crear una tabla visitando el arbol de codificacion. Convierte el arbol
    * de codificacion en una tabla.
    * @param arbolCodificacion Arbol sobre el que trabajar
    * @return Una tabla codificada
    */
  def convertirArbolTabla(arbolCodificacion: NodoArbolHuffman) : TablaCodigo = {
    def recursivo (nodo: NodoArbolHuffman, listaCod: List[Int]): TablaCodigo = {
      nodo match {
        case NodoHojaArbolHuffman(caracter, _) => List((caracter, listaCod))
        case NodoInternoArbolHuffman(izq, dcha, _, _) =>
          recursivo(izq, listaCod :+ 0) ::: recursivo(dcha, listaCod :+1)
      }
    }
    recursivo(arbolCodificacion, List())
  }

  /**
    * Método para codificar un texto usando una tabla siguiendo la tabla
    * @param arbolCodificacion arbol de codificacion
    * @param texto texto a codificar
    * @return lista de enteros codificados
    */
  def rapidoCodificacion(arbolCodificacion: NodoArbolHuffman, texto: List[Char]): List[Int] = {

    var lista: List[Int] = List()

    texto.foreach(caracter => lista = lista ::: codificarConTabla(convertirArbolTabla(arbolCodificacion))(caracter))

    return lista

  }

  /**
    * Metodo para obtener el mensaje decodificado
    * @param arbolDecodificacion nodo de donde se obtiene el mensaje decodificado
    * @param lista lista con la codificacion
    * @return muestra el mensaje decodificado
    */
  def decodificacion(arbolDecodificacion: NodoArbolHuffman, lista : List[Int]): List[Char] = {
    def auxiliar(arbol: NodoArbolHuffman, lista : List[Int]): List[Char] = {
      arbol match {
        //caso 0: se llega a un nodo hoja
        case NodoHojaArbolHuffman(caracter, _) =>
          //Si la lista esta vacía no se para de extrar caracteres y se devuelve la lista obtenida
          if (lista.isEmpty) List(caracter)
          //Sino, se añade el caracter y se sigue en proceso
          else caracter :: auxiliar(arbolDecodificacion, lista)
        //caso 1: si es un nodo interno y vamos recorriendo el arbol hacia la derecha o izquierda dependiendo de si 0 o 1
        case NodoInternoArbolHuffman(hijoIzquierda, hijoDerecha, _, _) =>
          if (!lista.isEmpty) {
            if (lista.head.equals(0)) auxiliar(hijoIzquierda, lista.tail)
            else auxiliar(hijoDerecha, lista.tail)
          }
          else {
            println("error: No se puede decodificar ese mensaje, puede que el arbol utilizado para la decodificacion sea incorrecto")
            List()
          }
      }
    }
    auxiliar(arbolDecodificacion,lista)
  }

  /**
    * Permite leer un archivo
    * @param nombreArchivo
    * @return
    */
  def leerArchivo(nombreArchivo : String) : String = {
    Source.fromFile(nombreArchivo).getLines().mkString
  }

}
