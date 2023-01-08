package fr.beglarian.amocrm.model.contact.search

import fr.beglarian.amocrm.model.contact.info.AmoCrmContactsResponse

case class AmoCrmContactSearchResponseEmbedded(contacts: Seq[AmoCrmContactsResponse])
