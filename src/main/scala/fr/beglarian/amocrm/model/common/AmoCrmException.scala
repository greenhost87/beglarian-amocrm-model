package fr.beglarian.amocrm.model.common

import scala.util.control.NoStackTrace

case class AmoCrmException(message: String, response: Option[String] = None) extends RuntimeException {
  override def getMessage: String = s"$message; ${response.getOrElse("")}"
}
