package fr.beglarian.amocrm.model.leads.create

import fr.beglarian.amocrm.model.common.AmoCrmCustomField

case class AmoCrmCreateLeadRequest(
    name: String,
    pipelineId: Int,
    statusId: Int,
    responsibleUserId: Int,
    customFieldsValues: Seq[AmoCrmCustomField],
    embedded: AmoCrmCreateLeadRequestEmbedded
)
