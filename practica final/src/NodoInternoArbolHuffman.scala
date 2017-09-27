/**
  * Created by manol on 17/06/2017.
  */
case class NodoInternoArbolHuffman(val hijoIzq: NodoArbolHuffman, val hijoDcha: NodoArbolHuffman, val caracteres: List[Char], val peso: Int) extends NodoArbolHuffman {
  override def toString: String = "Interno: " + caracteres + " -> " + peso + "\n{\n1.- " + hijoIzq.toString + "2.- " + hijoDcha.toString + "}\n"
}
