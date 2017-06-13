package operation

/**
  * Created by kirinuki on 2016/12/22.
  */
sealed abstract class SelenideOperation(args: String*) {
  def toCode: String
}

final case class SOpen(url: String) extends SelenideOperation {
  override def toCode: String = s"""open("$url");"""
}

final case class SClick(locator: Locator) extends SelenideOperation {
  override def toCode: String = s"""${locator.toCode}.click();"""
}

final case class SClickAndWait(locator: Locator) extends SelenideOperation {
  override def toCode: String = s"""${locator.toCode}.click();"""
}

final case class SType(locator: Locator, value: String) extends SelenideOperation {
  override def toCode: String = s"""${locator.toCode}.val("$value");"""
}

final case class SSelectOption(selectLocator: Locator, optionLocator: Locator) extends SelenideOperation {
  override def toCode: String = s"""${selectLocator.toCode}.selectOption("${optionLocator.toCode}");"""
}

final case class SPause(waitTime: Int) extends SelenideOperation {
  override def toCode: String = s"""sleep($waitTime);"""
}

final case class SAssertText(locator: Locator, pattern: String) extends SelenideOperation {
  override def toCode: String = s"""${locator.toCode}.shouldHave(text("$pattern"));"""
}

final case class SAssertTextPresent(pattern: String) extends SelenideOperation {
  override def toCode: String = s"""$$(\"body\").shouldHave(text("$pattern"));"""
}

final case class SAssertTextNotPresent(pattern: String) extends SelenideOperation {
  override def toCode: String = s"""$$(\"body\").shouldNotHave(text("$pattern"));"""
}

final case class SAssertConfirmation(pattern: String) extends SelenideOperation {
  override def toCode: String = s"""confirm("$pattern");"""
}