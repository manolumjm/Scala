import org.scalatest.FunSuite
import org.junit.Assert._
/**
  * Created by manol on 19/06/2017.
  */

class HuffmanTest extends FunSuite {
  import Huffman._

  val codigoHuffmanFrances: NodoArbolHuffman = NodoInternoArbolHuffman(
    NodoInternoArbolHuffman(
      NodoInternoArbolHuffman(
        NodoHojaArbolHuffman('s', 121895),
        NodoInternoArbolHuffman(
          NodoHojaArbolHuffman('d', 56269),
          NodoInternoArbolHuffman(
            NodoInternoArbolHuffman(
              NodoInternoArbolHuffman(
                NodoHojaArbolHuffman('x', 5928),
                NodoHojaArbolHuffman('j', 8351),
                List('x', 'j'), 14279),
              NodoHojaArbolHuffman('f', 16351),
              List('x', 'j', 'f'), 30630),
            NodoInternoArbolHuffman(
              NodoInternoArbolHuffman(
                NodoInternoArbolHuffman(
                  NodoInternoArbolHuffman(
                    NodoHojaArbolHuffman('z', 2093),
                    NodoInternoArbolHuffman(
                      NodoHojaArbolHuffman('k', 745),
                      NodoHojaArbolHuffman('w', 1747),
                      List('k', 'w'), 2492),
                    List('z', 'k', 'w'), 4585),
                  NodoHojaArbolHuffman('y', 4725),
                  List('z', 'k', 'w', 'y'), 9310),
                NodoHojaArbolHuffman('h', 11298),
                List('z', 'k', 'w', 'y', 'h'), 20608),
              NodoHojaArbolHuffman('q', 20889),
              List('z', 'k', 'w', 'y', 'h', 'q'), 41497),
            List('x', 'j', 'f', 'z', 'k', 'w', 'y', 'h', 'q'), 72127),
          List('d', 'x', 'j', 'f', 'z', 'k', 'w', 'y', 'h', 'q'), 128396),
        List('s', 'd', 'x', 'j', 'f', 'z', 'k', 'w', 'y', 'h', 'q'), 250291),
      NodoInternoArbolHuffman(
        NodoInternoArbolHuffman(
          NodoHojaArbolHuffman('o', 82762),
          NodoHojaArbolHuffman('l', 83668),
          List('o', 'l'), 166430),
        NodoInternoArbolHuffman(
          NodoInternoArbolHuffman(
            NodoHojaArbolHuffman('m', 45521),
            NodoHojaArbolHuffman('p', 46335),
            List('m', 'p'), 91856),
          NodoHojaArbolHuffman('u', 96785),
          List('m', 'p', 'u'),
          188641),
        List('o', 'l', 'm', 'p', 'u'), 355071),
      List('s', 'd', 'x', 'j', 'f', 'z', 'k', 'w', 'y', 'h', 'q', 'o', 'l', 'm', 'p', 'u'), 605362),
    NodoInternoArbolHuffman(
      NodoInternoArbolHuffman(
        NodoInternoArbolHuffman(
          NodoHojaArbolHuffman('r', 100500),
          NodoInternoArbolHuffman(
            NodoHojaArbolHuffman('c', 50003),
            NodoInternoArbolHuffman(
              NodoHojaArbolHuffman('v', 24975),
              NodoInternoArbolHuffman(
                NodoHojaArbolHuffman('g', 13288),
                NodoHojaArbolHuffman('b', 13822),
                List('g', 'b'), 27110),
              List('v', 'g', 'b'), 52085),
            List('c', 'v', 'g', 'b'), 102088),
          List('r', 'c', 'v', 'g', 'b'), 202588),
        NodoInternoArbolHuffman(
          NodoHojaArbolHuffman('n', 108812),
          NodoHojaArbolHuffman('t', 111103),
          List('n', 't'), 219915),
        List('r', 'c', 'v', 'g', 'b', 'n', 't'), 422503),
      NodoInternoArbolHuffman(
        NodoHojaArbolHuffman('e', 225947),
        NodoInternoArbolHuffman(
          NodoHojaArbolHuffman('i', 115465),
          NodoHojaArbolHuffman('a', 117110),
          List('i', 'a'), 232575),
        List('e', 'i', 'a'), 458522),
      List('r', 'c', 'v', 'g', 'b', 'n', 't', 'e', 'i', 'a'), 881025),
    List('s', 'd', 'x', 'j', 'f', 'z', 'k', 'w', 'y', 'h', 'q', 'o', 'l', 'm', 'p', 'u', 'r', 'c', 'v', 'g', 'b', 'n', 't', 'e', 'i', 'a'), 1486387)


  //Mensaje que se desea decodificar con el arbol anterior, y produce la cadena "huffman est cool"
  val mensajeSecretoFrances: List[Int] = List(0, 0, 1, 1, 1, 0, 1, 0, 1, 1, 1, 0, 0, 1, 1, 0, 1, 0,
    0, 1, 1, 0, 1, 0, 1, 1, 0, 0, 1, 1, 1, 1, 1, 0, 1, 0, 1, 1, 0, 0, 0, 0, 1, 0, 1, 1, 1,
    0, 0, 1, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 1, 0, 1)

  // Se decodifica este mensaje secreto
  val mensajeSecretoEsp = List(0, 1, 1, 0, 1, 1, 1, 1, 0, 0, 0, 1, 0, 1, 1, 0, 0, 0, 1, 1, 0, 0, 0, 1,
    0, 1, 1, 0, 1, 1, 0, 0, 0, 0, 0, 1, 0, 1, 0, 0, 1, 0, 0, 1, 0, 1, 1, 0,
    1, 1, 1, 0, 0, 0, 0, 0, 1, 1, 0, 0, 1, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0,
    0, 0, 0, 1, 0, 1, 1, 1, 0, 1, 1, 0, 0, 1, 0, 1, 0, 1, 0, 1, 1, 0, 0, 1,
    1, 0, 1, 1, 1, 0, 1, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 1, 1, 0, 0, 1, 0,
    0, 1, 1, 0, 1, 0, 1, 1, 0, 1, 0, 1, 1, 1, 0, 0, 0, 1, 0, 1, 1, 1, 1, 1,
    1, 1, 1, 0, 0, 1, 0, 1, 0, 0, 1, 1, 1)

  val mensajeACodificarEsp = "La regenta de Benito Perez Galdos";

  /**
    * Árbol seccion 1.1.
    */
  val arbolS1 = NodoInternoArbolHuffman(
    NodoHojaArbolHuffman('A', 8),
    NodoInternoArbolHuffman(
      NodoInternoArbolHuffman(
        NodoInternoArbolHuffman(
          NodoHojaArbolHuffman('H', 1),
          NodoHojaArbolHuffman('D', 1),
          List('H','D'), 2),
        NodoInternoArbolHuffman(
          NodoHojaArbolHuffman('G', 1),
          NodoHojaArbolHuffman('C', 1),
          List('G','C'), 2),
        List('H','D','G','C'), 4),
      NodoInternoArbolHuffman(
        NodoInternoArbolHuffman(
          NodoHojaArbolHuffman('E', 1),
          NodoHojaArbolHuffman('F', 1),
          List('E', 'F'), 2),
        NodoHojaArbolHuffman('B', 3),
        List('E','F','B'), 5),
      List('H','D','G','C','E','F','B'), 9),
    List('A','H','D','G','C','E','F','B'), 17)

  //Prueba 1
  test("Comprobar la construccion del arbol de codificación con el arbol que aparece en la sección 1.1"){
    val codigoSecillo = construirArbolCodificacion("AAAAAAAABBBCDEFGH".toList)
    println(codigoSecillo)
    val mensajeSecreto = rapidoCodificacion(codigoSecillo, "H".toList)
    println(mensajeSecreto.mkString)
    assertEquals(codigoSecillo, arbolS1)
  }

  // Prueba 2
  test("Comprobar si la decodificacion se realiza correctamente. Esto quiere decir que debe ser igual a huffmanestcool") {
    val mensajeDecodificado = decodificacion(codigoHuffmanFrances, mensajeSecretoFrances)
    assertEquals(mensajeDecodificado.mkString, "huffmanestcool")
  }

  //Prueba 3
  test("Comprobar si la codificacion se realiza bien. Esto quiere decir que al codificar mensajeDecodificado, el resultado es mensajeSecretoFrances"){
    val mensajeDecodificado = decodificacion(codigoHuffmanFrances, mensajeSecretoFrances)
    val mensajeSecreto = rapidoCodificacion(codigoHuffmanFrances, mensajeDecodificado)
    assertEquals(mensajeSecreto, mensajeSecretoFrances)
  }

  //Prueba 4
  /*test("Comprobar si la codificacion se realiza correctamente, es decir, si al codificar mensajeDecodificado, el resultado es mensajeSecretoEsp"){
    val codigoHuffmanEsp = construirArbolCodificacion(leerArchivo(getClass().getResource("/archivo/regenta.txt").getPath).toList)
    val mensajeSecreto = rapidoCodificacion(codigoHuffmanEsp, mensajeACodificarEsp.toList)
    assertEquals(mensajeSecreto, mensajeSecretoEsp)
  }*/




}
