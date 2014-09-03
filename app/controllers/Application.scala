package controllers

import play.api.mvc._
import scala.util.Random
import play.api.libs.json._
import play.api.libs.functional.syntax._

case class Data(label: String, value: String)

object Application extends Controller {
  implicit val dataWrites: Writes[Data] = (
    (JsPath \ "label").write[String] and
      (JsPath \ "value").write[String]
    )(unlift(Data.unapply))

  val firstResponse = List(Data("stratio", "200"), Data("cloudera", "50"), Data("databricks", "470"), Data("hortonworks", "900"))
  val secondResponse = List(Data("stratio", "450"), Data("cloudera", "150"), Data("databricks", "47"), Data("hortonworks", "45"))
  val thirdResponse = List(Data("stratio", "345"), Data("cloudera", "78"), Data("databricks", "98"), Data("hortonworks", "70"))
  val fourthResponse = List(Data("stratio", "780"), Data("cloudera", "425"), Data("databricks", "24"), Data("hortonworks", "800"))
  val fifthResponse = List(Data("stratio", "240"), Data("cloudera", "700"), Data("databricks", "35"), Data("hortonworks", "456"))
  val responsesMap = Map(0 -> firstResponse, 1 -> secondResponse, 2 -> thirdResponse, 3 -> fourthResponse, 4 -> fifthResponse)

  def testData = Action {
    val rnd = new Random()
    val responseRandomIndex: Int = rnd.nextInt(5)
    Ok(Json.toJson(responsesMap.get(responseRandomIndex)))
  }
}