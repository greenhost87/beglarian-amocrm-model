package fr.beglarian.amocrm.model.leads.create

case class AmoCrmCreateLeadResponse(embedded: AmoCrmCreateLeadResponseEmbedded) {
  def createdLeadId(): Option[Int] = embedded.leads.headOption.map(_.id)
}
