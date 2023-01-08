package fr.beglarian.amocrm.model.contact.info

import fr.beglarian.amocrm.model.common.AmoCrmCustomField

case class AmoCrmContactsResponse(
    id: Int,
    firstName: String,
    lastName: String,
    customFieldsValues: Seq[AmoCrmCustomField]
) {
  private[this] val phoneFieldCode = "PHONE"
  private[this] val emailFieldCode = "EMAIL"

  def phone: Option[String] = {
    find(phoneFieldCode).map(_.filter(_.isDigit))
  }

  def phones: Set[String] = {
    findAll(phoneFieldCode).map(_.filter(_.isDigit))
  }

  def rawPhones: Seq[AmoCrmCustomField] = {
    findAllRaw(phoneFieldCode)
  }

  def email: Option[String] = {
    find(emailFieldCode)
  }

  def emails: Set[String] = {
    findAll(emailFieldCode)
  }

  def rawEmails: Seq[AmoCrmCustomField] = {
    findAllRaw(emailFieldCode)
  }

  private[this] def find(fieldCode: String): Option[String] = {
    customFieldsValues.find(_.fieldCode.contains(fieldCode)).flatMap(_.values.headOption.flatMap(_.value)).flatMap {
      case Left(_)      => None
      case Right(value) => Some(value.trim())
    }
  }

  private[this] def findAll(fieldCode: String): Set[String] = {
    customFieldsValues.collect {
      case cfv if cfv.fieldCode.contains(fieldCode) =>
        cfv.values
    }.flatten.flatMap { value =>
      value.value match {
        case Some(Right(v)) => Some(v.trim)
        case Some(Left(_))  => None
        case None           => None
      }
    }.toSet
  }

  private[this] def findAllRaw(fieldCode: String): Seq[AmoCrmCustomField] = {
    customFieldsValues.filter(_.fieldCode.contains(fieldCode))
  }
}
