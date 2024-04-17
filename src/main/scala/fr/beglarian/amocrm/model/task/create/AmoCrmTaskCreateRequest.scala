package fr.beglarian.amocrm.model.task.create

import java.time.ZonedDateTime

case class AmoCrmTaskCreateRequest(
    entity_id: Int,
    responsible_user_id: Int = 8247223,
    entity_type: String = "leads",
    task_type_id: Int = 1,
    text: String,
    complete_till: Long = ZonedDateTime.now().plusMinutes(15).toInstant.getEpochSecond
)
