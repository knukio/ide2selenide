package operation

/**
  * Created by kirinuki on 2016/12/22.
  */
case class Locator(using: LocatorType, value: String) {

  def toCode: String = {
    using match {
      case Id    => s"""$$("#$value")"""
      case Css   => s"""$$(By.cssSelector("$value"))"""
      case Link  => s"""$$(By.linkText("$value"))"""
      case Name  => s"""$$(By.name("$value"))"""
      case XPath  => s"""$$(By.xpath("$value"))"""
      case Label => s"$value"
    }
  }
}

sealed trait LocatorType

case object Id extends LocatorType

case object Css extends LocatorType

case object Link extends LocatorType

case object Name extends LocatorType

case object XPath extends LocatorType

case object Label extends LocatorType
