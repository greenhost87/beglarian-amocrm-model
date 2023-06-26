package fr.beglarian.amocrm.model.common

case class AmoCrmCustomFieldValue(
    value: Option[Long | String | Boolean],
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

  def value(v: Long | String | Boolean): AmoCrmCustomFieldValue = {
    new AmoCrmCustomFieldValue(Some(v), None)
  }
}
