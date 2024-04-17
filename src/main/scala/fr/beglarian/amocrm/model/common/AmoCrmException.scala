package fr.beglarian.amocrm.model.common

case class AmoCrmException(message: String, statusCode: Int, response: Option[String] = None) extends RuntimeException {
  override def getMessage: String = s"$message; ${response.getOrElse("")}"
}
