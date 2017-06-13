package operation

/**
  * Created by kirinuki on 2016/12/22.
  */
sealed abstract class IDEOperation(args: String*) {
  def toSelenide: SelenideOperation

  def createLocator(locator: String): Locator = {
    if (locator.startsWith("//")) Locator(XPath, locator)
    else {
      val pattern = """(.+?)=(.+)""".r
      val pattern(using, value) = locator
      val escapedVal = value.replace(""""""","""\"""")
      using match {
        case "id"    => Locator(Id, escapedVal)
        case "css"   => Locator(Css, escapedVal)
        case "link"  => Locator(Link, escapedVal)
        case "label" => Locator(Label, escapedVal)
        case "name"  => Locator(Name, escapedVal)
      }
    }
  }
}

final case class Open(url: String) extends IDEOperation {
  override def toSelenide: SelenideOperation = SOpen(url)
}

final case class Click(locator: String) extends IDEOperation {
  override def toSelenide: SelenideOperation = SClick(createLocator(locator))
}

final case class ClickAndWait(locator: String) extends IDEOperation {
  override def toSelenide: SelenideOperation = SClick(createLocator(locator))
}

final case class Type(locator: String, value: String) extends IDEOperation {
  override def toSelenide: SelenideOperation = SType(createLocator(locator), value)
}

final case class SelectOption(selectLocator: String, optionLocator: String) extends IDEOperation {
  override def toSelenide: SelenideOperation =
    SSelectOption(createLocator(selectLocator), createLocator(optionLocator))
}

final case class Pause(waitTime: String) extends IDEOperation {
  override def toSelenide: SelenideOperation = SPause(waitTime.toInt)
}
final case class AssertText(locator: String, pattern: String) extends IDEOperation {
  override def toSelenide: SelenideOperation = SAssertText(createLocator(locator), pattern)
}

final case class AssertTextPresent(pattern: String) extends IDEOperation {
  override def toSelenide: SelenideOperation = SAssertTextPresent(pattern)
}

final case class AssertTextNotPresent(pattern: String) extends IDEOperation {
  override def toSelenide: SelenideOperation = SAssertTextNotPresent(pattern)
}

final case class AssertConfirmation(pattern: String) extends IDEOperation {
  override def toSelenide: SelenideOperation = SAssertConfirmation(pattern)
}

