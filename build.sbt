lazy val root = (project in file(".")).
  settings(
    name := "IDE2Selenide",
    scalacOptions ++= Seq("-encoding", "UTF-8"),
    javacOptions ++= Seq("-encoding", "UTF-8"),
    libraryDependencies ++= Seq(
      "org.seleniumhq.selenium" % "selenium-java" % "2.53.1",
      "junit" % "junit" % "4.12",
      "nu.validator.htmlparser" % "htmlparser" % "1.4",
      "com.codeborne" % "selenide" % "3.8.1"
    )
  )