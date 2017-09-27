import org.scalatest.FunSuite
//import org.junit.Assert._

/**
  * Created by manol on 21/04/2017.
  */
class MainTest extends FunSuite {

  /*
  **********************************************************************************************************************************
  *****************************     TEST     calcularValorTrianguloPascal(..)        ***********************************************
  **********************************************************************************************************************************
   */

  // Se importa la clase donde estan definidas las funciones a probar
  import Main.calcularValorTrianguloPascal


  //Inicio de los test para la funcion calcularValorTrianguloPascal(..)
  println("\n.....INICIO Test función de cálculo de valores del Triangulo de Pascal || Funcion calcularValorTrianguloPascal(...) ...\n")

  // Prueba 1: calcularValorTrianguloPascal(..).
  // Se calcula el valor para fila 10 y columna 5
  test("calcularValorTrianguloPascal 1: Fila 10, Columna 5 => (10,5)"){
    assert(calcularValorTrianguloPascal(10, 5) == 252)
  }


  // Prueba 2: calcularValorTrianguloPascal(..).
  // Se calcula el valor para fila 0 y columna 0
  test("calcularValorTrianguloPascal 2: Fila 0, Columna 0 => (0,0)") {
    assert(calcularValorTrianguloPascal(0, 0) == 1)
  }

  // Prueba 3: calcularValorTrianguloPascal(..).
  // Se calcula el valor para fila 6 y columna 4
  test("calcularValorTrianguloPascal 3: Fila 6, Columna 4 => (6,4)") {
    assert(calcularValorTrianguloPascal(6, 4) == 15)
  }

  // Prueba 4: calcularValorTrianguloPascal(..).
  // Se calcula el valor para fila 13 y columna 8
  test("calcularValorTrianguloPascal 4: Fila 13, Columna 8 => (13,8)") {
    assert(calcularValorTrianguloPascal(13, 8) == 1287)
  }


  // Prueba 5: calcularValorTrianguloPascal(..)
  // Calculo del valor de columna 10 y fila 15
  test("calcularValorTrianguloPascal 5: Fila=15, Columna=10 => (15,10)") {
    assert(calcularValorTrianguloPascal(15,10) === 3003)
  }

  // Prueba 6 para calcularValorTrianguloPascal(..)
  // En caso de que la columna sea mayor que la fila, deberá devolver un número negativo
  test("calcularValorTrianguloPascal 6: Fila 5, Columna 6 => (5,6)") {
    assert(calcularValorTrianguloPascal(5, 6) == -1)
  }


  //Fin de los test para la funcion calcularValorTrianguloPascal(..)
  println("\n.....FIN Test de cálculo de valores del Triangulo de Pascal || Funcion calcularValorTrianguloPascal(...) ...\n")



  /*
  **********************************************************************************************************************************
  *****************************************     TEST       chequearBalance(..)     *************************************************
  **********************************************************************************************************************************
   */




  //Inicio de los test para la funcion chequearBalance(..)
  println("\n.....INICIO Test de funcion para chequear el balance de parentesis || Funcion chequearBalance(..)....\n")

  // Se importa la clase donde estan definidas las funciones a probar
  import Main.chequearBalance


  // Prueba 1: chequearBalance(..)
  test("testChequearBalance 1: '(,(,),)' esta balanceada") {
    assert(chequearBalance("(,(,),)".toList))
  }

  // Prueba 2: chequearBalance(..)
  test("testChequearBalance 2: '(if (a ¿ b) (b/a) else (a/(b*b)))' esta balanceada") {
    assert(chequearBalance("(if (a ¿ b) (b/a) else (a/(b*b)))".toList))
  }

  // Prueba 3: chequearBalance(..)
  test("testChequearBalance 3: '(ccc(ccc)cc((ccc(c))))' esta balanceada") {
    assert(chequearBalance("(ccc(ccc)cc((ccc(c))))".toList))
  }

  // Prueba 4: chequearBalance(..)
  test("testChequearBalance 4: 'esto no tiene ni parentesis' esta balanceada") {
    assert(chequearBalance("esto no tiene ni parentesis".toList))
  }

  // Prueba 5: chequearBalance(..)
  test("testChequearBalance 5: '(if (a ¿ b) b/a) else (a/(b*b)))' no esta balanceada") {
    assert(!chequearBalance("(if (a ¿ b) b/a) else (a/(b*b)))".toList))
  }

  // Prueba 6: chequearBalance(..)
  test("testChequearBalance 6: '(ccc(ccccc((ccc(c))))' no esta balanceada") {
    assert(!chequearBalance("(ccc(ccccc((ccc(c))))".toList))
  }

  // Prueba 7: chequearBalance(..)
  test("testChequearBalance 7: '())()())' no esta balanceada") {
    assert(!chequearBalance("())()())".toList))
  }

  // Prueba 8: chequearBalance(..)
  test("testChequearBalance 8: '())(' no esta balanceada") {
    assert(!chequearBalance("())(".toList))
  }

  // Prueba 9: chequearBalance(..)
  test("testChequearBalance 9: ':-)' no esta balanceada") {
    assert(!chequearBalance(":-)".toList))
  }


  //Fin de los test para la funcion chequearBalance(..)
  println("\n.....FIN Test de funcion para chequear el balance de parentesis || Funcion chequearBalance(..)....\n")


  /*
    **********************************************************************************************************************************
    *****************************************    TEST   contarCambiosPosibles(..)      ***********************************************
    **********************************************************************************************************************************
   */




  //Inicio de los test para la funcion contarCambiosPosibles(..)
  println("\n.....INICIO Test de funcion para contar posibles cambios con determinadas monedas || Funcion contarCambiosPosibles(..)\n")

  // Se importa la clase donde estan definidas las funciones a probar
  import Main.contarCambiosPosibles

  // Prueba 1: contarCambiosPosibles(..)
  test("testContarCambiosPosibles 1: cambio de 300 con monedas de 500,5,50,100,20,200 y 10") {
    assert(contarCambiosPosibles(300,List(500,5,50,100,20,200,10)) == 1022)
  }

  // Prueba 2: contarCambiosPosibles(..)
  test("testContarCambiosPosibles 2: cambio de 10 con monedas de 1,2 y 5") {
    assert(contarCambiosPosibles(10,List(1,2,5)) == 10)
  }

  // Prueba 3: contarCambiosPosibles(..)
  test("testContarCambiosPosibles 3: cambio de 4 con monedas de 1 y 2") {
    assert(contarCambiosPosibles(4,List(1,2)) == 3)
  }

  // Prueba 4: contarCambiosPosibles(..)
  test("testContarCambiosPosibles 4: cambio de 4 con monedas de 1,2 y 2. Debe obviar tipos de monedas repetidas") {
    assert(contarCambiosPosibles(4,List(1,2,2)) == 3)
  }

  // Prueba 5: contarCambiosPosibles(..)
  test("testContarCambiosPosibles 5: cambio de 100 con monedas de 1,2,2,3,3,5,6,10. Debe obviar tipos de monedas repetidas") {
    assert(contarCambiosPosibles(100,List(1,2,2,3,3,5,6,10)) == 86223)
  }

  // Prueba 6: contarCambiosPosibles(..)
  test("testContarCambiosPosibles 6: cambio de 100 con monedas de 1,2,3,5,6,10") {
    assert(contarCambiosPosibles(100,List(1,2,3,5,6,10)) == 86223)
  }

  // Prueba 7: contarCambiosPosibles(..)
  test("testContarCambiosPosibles 7: cambio de 301 con monedas de 5,10,20,50,100,200,500") {
    assert(contarCambiosPosibles(301,List(5,10,20,50,100,200,500)) === 0)
  }


  //Fin de los test para la funcion contarCambiosPosibles(..)
  println("\n.....FIN Test de funcion para contar posibles cambios con determinadas monedas || Funcion contarCambiosPosibles(..)\n")

  /*
  **********************************************************************************************************************************
 ******************************************  TEST   BusquedaBinariaGenerica(..)   **************************************************
  **********************************************************************************************************************************
   */


  //Inicio de los test para la funcion BusquedaBinariaGenerica(..)
  println("\n.....INICIO Test de funcion para busqueda binaria genérica|| Funcion BusquedaBinariaGenerica(..)\n")

  // Se importa la clase donde estan definidas las funciones a probar
  import Main.busquedaBinariaGenerica



  // Prueba 1: busquedaBinaria
  // La comparación será sobre tipo String según su tamaño
  test("testBusquedaBinariaGenerica 1: busca cadena 'hola' sobre lista de String que se comparan por su longitud") {
    val vector1 = List("jo", "xao", "hola", "adios")
    val valorBusqueda1 = "hola"
    assert(busquedaBinariaGenerica(vector1,valorBusqueda1, (x: String,y:String) => (x.length > y.length)) == 2)
  }


  // Prueba 2: busquedaBinaria
  // La comparación será sobre números, de menor a mayor
  test("testBusquedaBinariaGenerica 2: busca valor 7 sobre lista de enteros comparados de menor a mayor") {
    val vector2 = List(1,3,5,7,10)
    val valorBusqueda2 = 7
    assert(busquedaBinariaGenerica(vector2,valorBusqueda2,(x:Int, y:Int) => (x > y)) == 3)
  }

  // Prueba 3: busquedaBinaria
  // La comparación será sobre números, de menor a mayor
  test("testBusquedaBinariaGenerica 3: busco el valor 6 sobre una lista de enteros. No está contenido en la lista") {
    val vector3 = List(1,3,5,7,10)
    val valorBusqueda3 = 6
    assert(busquedaBinariaGenerica(vector3,valorBusqueda3,(x:Int, y:Int) => (x > y)) == -1)
  }



  //Inicio de los test para la funcion BusquedaBinariaGenerica(..)
  println("\n.....FIN Test de funcion para busqueda binaria genérica|| Funcion BusquedaBinariaGenerica(..)\n")

}
