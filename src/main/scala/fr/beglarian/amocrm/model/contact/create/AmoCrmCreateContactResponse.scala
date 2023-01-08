package fr.beglarian.amocrm.model.contact.create

case class AmoCrmCreateContactResponse(embedded: AmoCrmCreateContactResponseEmbedded) {
  def contactId(): Option[Int] = embedded.contacts.headOption.map(_.id)
}
