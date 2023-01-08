package fr.beglarian.amocrm.model.contact.links

case class AmoCrmContactLinksResponse(embedded: AmoCrmContactLinksResponseEmbedded) {
  def entityIds(tpe: String = "leads"): Seq[Int] = {
    embedded.links.collect {
      case link if link.toEntityType == tpe =>
        link.toEntityId
    }
  }
}
