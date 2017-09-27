/**
  * Created by manol on 17/06/2017.
  */
case class NodoHojaArbolHuffman(val caracter: Char, val peso: Int) extends NodoArbolHuffman {
  override def toString: String = "Hoja: " + caracter + " -> " + peso + "\n"
}
