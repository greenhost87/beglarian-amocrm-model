package fr.beglarian.amocrm.model.contact.update

import fr.beglarian.amocrm.model.common.AmoCrmCustomField

case class AmoCrmUpdateContactRequest(customFieldsValues: Seq[AmoCrmCustomField])
