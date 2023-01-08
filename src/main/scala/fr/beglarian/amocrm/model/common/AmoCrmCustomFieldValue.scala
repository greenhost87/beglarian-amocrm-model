package fr.beglarian.amocrm.model.common

case class AmoCrmCustomFieldValue(
    value: Option[Either[Long, String]],
    enumCode: Option[Either[Int, String]],
    enumId: Option[Int] = None
)

object AmoCrmCustomFieldValue {
  def enumCode(v: Int): AmoCrmCustomFieldValue = {
    new AmoCrmCustomFieldValue(None, Some(Left(v)))
  }

  def enumCode(v: String): AmoCrmCustomFieldValue = {
    new AmoCrmCustomFieldValue(None, Some(Right(v)))
  }
  
  def enumId(v: Int): AmoCrmCustomFieldValue = {
    new AmoCrmCustomFieldValue(None, None, Some(v))
  }

  def value(v: String): AmoCrmCustomFieldValue = {
    new AmoCrmCustomFieldValue(Some(Right(v)), None)
  }

  def value(v: Long): AmoCrmCustomFieldValue = {
    new AmoCrmCustomFieldValue(Some(Left(v)), None)
  }
}
