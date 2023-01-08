package fr.beglarian.amocrm.model.leads.contacts

import fr.beglarian.amocrm.model.common.AmoCrmCustomField

case class AmoCrmLeadsResponse(
    id: Int,
    name: String,
    embedded: AmoCrmLeadsResponseEmbedded,
    customFieldsValues: Seq[AmoCrmCustomField]
) {
  def contactId(): Option[Int] = {
    embedded.contacts.headOption.map(_.id)
  }
}
