package fr.beglarian.amocrm.model.common

case class AmoCrmCustomField(
    fieldName: Option[String],
    values: Seq[AmoCrmCustomFieldValue],
    fieldCode: Option[String],
    fieldId: Option[Int] = None
)

object AmoCrmCustomField {
  def phone(value: String): AmoCrmCustomField = {
    AmoCrmCustomField(
      fieldName = None,
      values = Seq(
        AmoCrmCustomFieldValue(
          value = Some(Right(value)),
          enumCode = Some(Right("WORK"))
        )
      ),
      fieldCode = Some("PHONE")
    )
  }

  def email(value: String): AmoCrmCustomField = {
    AmoCrmCustomField(
      fieldName = None,
      values = Seq(
        AmoCrmCustomFieldValue(
          Some(Right(value)),
          Some(Right("WORK"))
        )
      ),
      fieldCode = Some("EMAIL")
    )
  }

  def apply(fieldId: Int, fieldValue: AmoCrmCustomFieldValue): AmoCrmCustomField = {
    AmoCrmCustomField(None, Seq(fieldValue), None, Some(fieldId))
  }
}
