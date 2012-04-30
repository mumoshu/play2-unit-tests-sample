import sbt._
import Keys._
import PlayProject._

object ApplicationBuild extends Build {

    val appName         = "play2-unit-tests-sample"
    val appVersion      = "1.0-SNAPSHOT"

    val appDependencies = Seq(
      "org.mockito" % "mockito-all" % "1.9.0" % "test",
      "org.scalaquery" %% "scalaquery" % "0.10.0-M1",
      "mysql" % "mysql-connector-java" % "5.1.19"
    )

    val main = PlayProject(appName, appVersion, appDependencies, mainLang = SCALA).settings(
      // Add your own project settings here      
    )

}
