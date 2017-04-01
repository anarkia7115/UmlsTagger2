import umls._
import java.io.File

object Main {
  def main(args:Array[String]) {
    val ut = new UmlsTagger2("")
    
    val cuiFile = new File("./data/cuistr.csv")
    val outFile = new File("./data/cuistr.json")
    ut.buildIndexJson(cuiFile, outFile)

  }
}
