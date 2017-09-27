/**
  * Created by manol on 08/05/2017.
  */

/**
  * Objeto companion que ofrece metodos para trabajar con
  * conjuntos
  */
object Conjunto {

  /**
    * Limite para la iteracion necesaria de algunas operaciones,
    * entre -1000 y 1000
    */
  private final val LIMITE = 10

  /**
    * Metodo que permite crear objetos de la clase Conjunto
    * de forma sencilla
    * @param f
    * @return
    */
  def apply(f: Int => Boolean): Conjunto = {
    new Conjunto(f)
  }

  /**
    * Creación de conjunto con único elemento
    * @param elemto Elemento que contendrá el conjunto
    * @return Nuevo conjunto del elemento pasado por argumento
    */
  def conjuntoUnElemento(elemto:Int) : Conjunto = {
    new Conjunto((x:Int) => x == elemto)
  }

  /**
    * Union de dos conjuntos
    * @param c1 Primer conjunto a unir
    * @param c2 Segundo conjunto a unir
    * @return Nuevo conjunto unido, resultado de los 2 anteriores
    */
  def union(c1:Conjunto, c2:Conjunto) : Conjunto = {
    new Conjunto((x:Int) => {c1.apply(x) || c2.apply(x)})
  }

  /**
    * Intersección de dos conjuntos
    * @param c1 Primer conjunto a intersectar
    * @param c2 Segundo conjunto a intersectar
    * @return Nuevo conjunto intersectado a partir de los dos anteriores
    */
  def interseccion(c1: Conjunto, c2: Conjunto) : Conjunto = {
    new Conjunto((x:Int) => c1.apply(x) && c2.apply(x))
  }

  /**
    * Diferencia de dos conjuntos. Son los elementos que en el
    * primer conjunto, pero no en el segundo
    * @param c1 Primer conjunto para hacer la diferencia
    * @param c2 Segundo conjunto para hacer la diferencia
    * @return Nuevo conjunto que contiene la diferencia de los dos anteriores
    */
  def diferencia(c1: Conjunto, c2: Conjunto) : Conjunto = {
    new Conjunto((x:Int) => c1(x) && !c2(x))
  }


  /**
    * Filtrado de un conjunto segun un predicado
    * @param c Conjunto para filtrar
    * @param predicado Condición de filtrado
    * @return Nuevo conjunto filtrado
    */
  def filtrar(c: Conjunto, predicado: Int => Boolean) : Conjunto = {
    val c1 = new Conjunto(predicado)
    //new Conjunto((x:Int) => c(x) && c1(x))
    interseccion(c,c1)
  }


  /**
    * Comprueba si un determinado predicado se cumple para todos
    * los elementos del conjunto
    * @param c Conjunto sobre el que trabajar
    * @param predicado condicion para el conjunto
    * @return Verdadero si se cumple la condicion para todo el conjunto
    */
  def paraTodo(c: Conjunto, predicado: Int => Boolean) : Boolean = {
    def iterar(elemento: Int) : Boolean = {
      // Condición de parada: Si llega al límite, es el fin del algoritmo recursivo
      if(elemento > LIMITE) true
        // Condicion 1 para iterar: Si el elemento siguiente no pertenece al conjunto, seguir con el siguiente elemento
      else if(!c(elemento)) iterar(elemento+1)
        // Condicion 2 para iterar: Si el elemento pertenece al conjunto, entonces
        // comprueba si cumple el predicado e itera para seguir comprobando el siguiente elemento
      else predicado(elemento) && iterar(elemento+1)
    }
    // La llamada recursiva empieza en -LIMITE y acabaremos el LIMITE.
    // p.e: LIMITE = 3 => {-3,-2,-1,0,1,2,3}, pero al hacer un conjunto con, puede que sea con
    // la condición x>0, por lo que tendriamos realmente un conjunto = {1,2,3}
    iterar(-LIMITE)
  }


  /**
    * Función que determina si un conjunto contiene al menos un elemento
    * para el que se cumple un cierto predicado
    * @param c Conjunto sobre el que trabajar
    * @param predicado Condición o predicado para el conjunto
    * @return Verdadero si algún elemento cumple el predicado. Caso contrario falso
    */
  def existe(c: Conjunto, predicado: Int => Boolean) : Boolean = {
    // Existe(x, p(x))  <=> ¬ParaTodo( x, ¬p(x)) .
    // Es el la relación lógica entre el cuatificador universal y el cuantificador existencial
    !paraTodo(c, x => !predicado(x))
  }

  /**
    * Función que transforma un conjunto en otro aplicando función
    * @param c Conjunto a transformar
    * @param funcion Función a usar para transformar el conjunto
    * @return Nuevo conjunto con la función aplicada
    */
  def map(c: Conjunto, funcion: Int => Int): Conjunto = {
    // El nuevo conjunto seran aquellos valores que cumplan la funcion y existan en el conjunto
    new Conjunto((x:Int) => existe(c, y => funcion(y) == x))
  }
}





/**
 * Clase para representar conjuntos definidos mediante una funcion
 * caracteristica (un predicado). De esta forma, se declara el tipo
 * conjunto como un predicado que recibe un entero (elemento) como
 * argumento y dvuelve un valor booleano que indica si pertenece o no
 * al conjunto
 *
 * @param funcionCaracteristica Condición con la que crear el conjunto (actúa como un filtrado)
 */
class Conjunto(val funcionCaracteristica: Int => Boolean) {

  /**
    * Crea una cadena con el contenido completo del conjunto
    * @return
    */
  override def toString(): String = {
      // Esto sería el intervalo {-Limite, Limite}
      val elementos = for (i <- -Conjunto.LIMITE to Conjunto.LIMITE if funcionCaracteristica(i)) yield i
        elementos.mkString("{", ",", "}")
    }

  /**
    * Metodo para determinar la pertenencia de un elemento al
    * conjunto
    * @param elemento
    * @return valor booleano indicando si elemento cumple
    *         la funcion caracteristica o no
    */
  def apply(elemento: Int): Boolean = {
    funcionCaracteristica(elemento)
  }
}

object Ejemplo extends App
{
  // Creamos un conjunto para valores mayores que 3
  val conjunto = new Conjunto((x:Int) => x > 3)
  println(conjunto)
  // Se comprueba si 5 pertenece al conjunto. Es lo mismo que apply(5)
  val pertenece = conjunto(5)
  println(pertenece)

  // Creamos conjunto para valores mayores que 3
  // usando la facilidad del metodo apply del objeto compañero
  val conjunto1 = Conjunto((x:Int) => x > 3)

  // Creando un conjunto de un solo elemento
  val unElemento = 5
  val conjunto2 = Conjunto.conjuntoUnElemento(unElemento)
  println("Conjunto de un solo elemento " + unElemento + " = " + conjunto2)

  val union1 = new Conjunto((x:Int) => x < -3)
  val union2 = new Conjunto((x:Int) => x > 3)
  val conjunto3 = Conjunto.union(union1,union2)
  println("Union de conjuntos " + union1 + " ∪ " + union2 + " = " + conjunto3)

  val interseccion1 = new Conjunto((x:Int) => x > 5)
  val interseccion2 = new Conjunto((x:Int) => x > 8)
  val conjunto4 = Conjunto.interseccion(interseccion1,interseccion2)
  println("Intersección de conjuntos " + interseccion1 + " ∩ " + interseccion2 + " = " + conjunto4)

  val diferencia1 = new Conjunto((x:Int) => x > 5)
  val diferencia2 = new Conjunto((x:Int) => x > 8)
  val conjunto5 = Conjunto.diferencia(diferencia1,diferencia2)
  // Para imprimir \ hay que ponerlo 2 veces, ya que una significara otra cosa
  println("Diferencia de conjuntos " + diferencia1 + " \\ " + diferencia2 + " = " + conjunto5)

  val filtrado1 = new Conjunto((x:Int) => x > 5)
  println(filtrado1)
  println("Filtrado de conjunto " + Conjunto.filtrar(filtrado1, (x:Int) => x > 7))

  println("ParaTodos de conjuntos")
  val paraTodo1 = new Conjunto((x:Int) => x > -3)
  println(paraTodo1)
  val predicadoParaTodo1 = (x: Int) => x > 3
  val predicadoParaTodo2 = (x: Int) => x > -3

  if(Conjunto.paraTodo(paraTodo1,predicadoParaTodo1)) println("Para todo el conjunto " + paraTodo1 + " se cumple x > 3")
  else println("Para todo el conjunto " + paraTodo1 + " NO se cumple x > 3")

  if(Conjunto.paraTodo(paraTodo1,predicadoParaTodo2)) println("Para todo el conjunto " + paraTodo1 + " se cumple x > -3")
  else println("Para todo el conjunto " + paraTodo1 + " NO se cumple x > -3")

  val existe1 = new Conjunto((x:Int) => x > -2)

  val predicadoExiste1 = (x: Int) => x > 3
  val predicadoExiste2 = (x: Int) => x > 10
  if(Conjunto.existe(existe1,predicadoExiste1)) println("Existe x > 3 que pertenece al conjunto " + existe1)
  else println("NO existe x > 3 que pertenece al conjunto " + existe1)

  if(Conjunto.existe(existe1,predicadoExiste2)) println("Existe x > 10 que pertenezca al conjunto " + existe1)
  else println("NO existe x > 10 que pertenezca al conjunto " + existe1)

  val map1 = new Conjunto((x:Int) => x % 2 == 0)
  println(map1)
  val resultado = Conjunto.map(map1,(x => x + 1))
  println(resultado)
}

