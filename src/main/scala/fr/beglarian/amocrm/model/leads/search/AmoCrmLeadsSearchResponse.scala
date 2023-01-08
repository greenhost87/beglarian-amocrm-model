package fr.beglarian.amocrm.model.leads.search

case class AmoCrmLeadsSearchResponse(embedded: AmoCrmLeadsSearchEmbeddedResponse) {
  val leads: Seq[AmoCrmLeadsSearchEmbeddedLeadResponse] = embedded.leads
}
