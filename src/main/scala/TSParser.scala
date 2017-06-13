import java.io.StringReader

import operation._
import nu.validator.htmlparser.sax.HtmlParser

import scala.collection.immutable.Seq
import scala.xml.{InputSource, Node}
import scala.xml.parsing.NoBindingFactoryAdapter

/**
  * Created by kirinuki on 2016/12/22.
  */
class TSParser {
  def getRootNode(html: String): Node = {
    val saxer = new NoBindingFactoryAdapter
    //パースできないので対処
    val convertedHtml = html.replace("xml:lang", "xml_lang")
    val reader = new StringReader(convertedHtml)
    val parser = new HtmlParser
    parser.setContentHandler(saxer)
    parser.parse(new InputSource(reader))
    saxer.rootElem
  }

  def getOperations(root: Node): Seq[SelenideOperation] = {
    val trs = root \\ "tbody" \ "tr"
    trs.map { tr =>
      val tds = tr \ "td"
      val args = tds.map(_.text)
      args.head match {
        case "open"         => Open(args(1))
        case "click"        => Click(args(1))
        case "clickAndWait" => ClickAndWait(args(1))
        case "type"         => Type(args(1), args(2))
        case "select"       => SelectOption(args(1), args(2))
        case "pause"        => Pause(args(1))

        case "assertText"           => AssertText(args(1), args(2))
        case "assertTextPresent"    => AssertTextPresent(args(1))
        case "assertTextNotPresent" => AssertTextNotPresent(args(1))
        case "assertConfirmation"   => AssertConfirmation(args(1))
      }
    }.map(_.toSelenide)
  }
}
