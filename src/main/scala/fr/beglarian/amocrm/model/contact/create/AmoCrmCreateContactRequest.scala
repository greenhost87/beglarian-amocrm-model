package fr.beglarian.amocrm.model.contact.create

import fr.beglarian.amocrm.model.common.{AmoCrmCustomField, AmoCrmCustomFieldValue}

case class AmoCrmCreateContactRequest(name: String, responsibleUserId: Int, customFieldsValues: Seq[AmoCrmCustomField])
