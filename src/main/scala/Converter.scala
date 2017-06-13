import operation.{IDEOperation, SelenideOperation}

import scala.io.Source

/**
  * Created by kirinuki on 2016/12/22.
  */
object Converter {
  def main(args: Array[String]): Unit = {
    val fileName = args(0)
    val source = Source.fromFile(fileName)
    val html = source.getLines.mkString("\n")
    val parser = new TSParser
    val root = parser.getRootNode(html)
    val ops = parser.getOperations(root)
    printCode(ops)
  }

  private def printCode(ops: Seq[SelenideOperation]) = {
    print(
      """|@Test
         |public void test(){
         |""".stripMargin)
    ops.foreach(op => println("\t" + op.toCode))
    print("}")
  }

}
