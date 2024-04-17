package fr.beglarian.amocrm.model.contact.info

import fr.beglarian.amocrm.model.common.AmoCrmCustomField

case class AmoCrmContactsResponse(
    id: Int,
    firstName: String,
    lastName: String,
    customFieldsValues: Seq[AmoCrmCustomField]
) {
  private val phoneFieldCode = "PHONE"
  private val emailFieldCode = "EMAIL"

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

  private def find(fieldCode: String): Option[String] = {
    customFieldsValues.find(_.fieldCode.contains(fieldCode)).flatMap(_.values.headOption.flatMap(_.value)).flatMap {
      case value: String => Some(value.trim())
      case _     => None
    }
  }

  private def findAll(fieldCode: String): Set[String] = {
    customFieldsValues.collect {
      case cfv if cfv.fieldCode.contains(fieldCode) =>
        cfv.values
    }.flatten.flatMap { value =>
      value.value match {
        case Some(v: String) => Some(v.trim)
        case _  => None
      }
    }.toSet
  }

  private def findAllRaw(fieldCode: String): Seq[AmoCrmCustomField] = {
    customFieldsValues.filter(_.fieldCode.contains(fieldCode))
  }
}
