name := "UAdmin"

version := "1.0-SNAPSHOT"

libraryDependencies ++= Seq(
  "mysql" % "mysql-connector-java" % "5.1.18",
  "be.objectify" %% "deadbolt-java" % "2.2.1-RC2",
  "commons-io" % "commons-io" % "2.1",
  "net.coobird" % "thumbnailator" % "0.4.3",
   "org.apache.poi" % "poi" % "3.10-FINAL",
   "org.apache.poi" % "poi-ooxml" % "3.10-FINAL",
   "org.apache.poi" % "poi-ooxml-schemas" % "3.10-FINAL",
    "javax.mail" % "mail" % "1.4.1",
    "com.itextpdf" % "itextpdf" % "5.0.6",
    "com.itextpdf.tool" % "xmlworker" % "1.0.0",
  javaJdbc,
  javaEbean,
  cache
)     


resolvers += Resolver.url("Objectify Play Repository", url("http://deadbolt.ws/releases/"))(Resolver.ivyStylePatterns)

play.Project.playJavaSettings
