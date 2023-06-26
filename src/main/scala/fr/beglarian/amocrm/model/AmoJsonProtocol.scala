package fr.beglarian.amocrm.model

import fr.beglarian.amocrm.model.auth.{AmoCrmRefreshTokenRequest, AmoCrmRefreshTokenResponse}
import fr.beglarian.amocrm.model.common.{AmoCrmCustomField, AmoCrmCustomFieldValue, AmoCrmLink}
import fr.beglarian.amocrm.model.contact.create.{
  AmoCrmCreateContactRequest,
  AmoCrmCreateContactResponse,
  AmoCrmCreateContactResponseEmbedded,
  AmoCrmCreateContactResponseEmbeddedContact
}
import fr.beglarian.amocrm.model.contact.info.AmoCrmContactsResponse
import fr.beglarian.amocrm.model.contact.links.{AmoCrmContactLinksResponse, AmoCrmContactLinksResponseEmbedded}
import fr.beglarian.amocrm.model.contact.search.{AmoCrmContactSearchResponse, AmoCrmContactSearchResponseEmbedded}
import fr.beglarian.amocrm.model.contact.update.AmoCrmUpdateContactRequest
import fr.beglarian.amocrm.model.leads.contacts.{
  AmoCrmLeadsResponse,
  AmoCrmLeadsResponseEmbedded,
  AmoCrmLeadsResponseEmbeddedContact
}
import fr.beglarian.amocrm.model.leads.create.*
import fr.beglarian.amocrm.model.leads.link.AmoCrmAddLinkRequest
import fr.beglarian.amocrm.model.leads.note.{AmoCrmAddLeadNoteRequest, AmoCrmAddLeadNoteRequestText}
import fr.beglarian.amocrm.model.leads.search.{
  AmoCrmLeadsSearchEmbeddedLeadResponse,
  AmoCrmLeadsSearchEmbeddedResponse,
  AmoCrmLeadsSearchResponse
}
import fr.beglarian.amocrm.model.task.create.AmoCrmTaskCreateRequest
import spray.json.*

object AmoJsonProtocol extends DefaultJsonProtocol {
  implicit val amoCrmRefreshTokenRequestFormat: RootJsonFormat[AmoCrmRefreshTokenRequest] = jsonFormat(
    AmoCrmRefreshTokenRequest.apply(_, _, _, _, _),
    "client_id",
    "client_secret",
    "grant_type",
    "refresh_token",
    "redirect_uri"
  )
  implicit val amoCrmRefreshTokenResponseFormat: RootJsonFormat[AmoCrmRefreshTokenResponse] = jsonFormat(
    AmoCrmRefreshTokenResponse.apply(_, _, _, _),
    "token_type",
    "expires_in",
    "access_token",
    "refresh_token"
  )

  implicit val amoCrmLinkFormat: RootJsonFormat[AmoCrmLink] =
    jsonFormat(AmoCrmLink.apply(_, _), "to_entity_id", "to_entity_type")


  implicit val amoCrmCustomFieldValueValueFormat: RootJsonFormat[Option[Long | String | Boolean]] = new RootJsonFormat[Option[Long | String | Boolean]] {
    override def write(obj: Option[Long | String | Boolean]): JsValue = {
      obj match {
        case Some(value: Long) => JsNumber(value)
        case Some(value: String) => JsString(value)
        case Some(value: Boolean) => JsBoolean(value)
        case None => JsNull
      }
    }

    override def read(json: JsValue): Option[Long | String | Boolean] = {
      json match
        case JsString(value) => Some(value)
        case JsNumber(value) => Some(value.longValue)
        case boolean: JsBoolean => Some(boolean.value)
        case _ => None
    }
  }


  implicit val amoCrmCustomFieldValueFormat: RootJsonFormat[AmoCrmCustomFieldValue] =
    jsonFormat(AmoCrmCustomFieldValue.apply(_, _, _), "value", "enum_code", "enum_id")

  implicit val amoCrmCustomFieldFormat: RootJsonFormat[AmoCrmCustomField] =
    jsonFormat(
      AmoCrmCustomField.apply(_, _, _, _),
      "field_name",
      "values",
      "field_code",
      "field_id"
    )

  implicit val amoCrmLeadsResponseEmbeddedContactFormat: RootJsonFormat[AmoCrmLeadsResponseEmbeddedContact] =
    jsonFormat(AmoCrmLeadsResponseEmbeddedContact.apply(_), "id")
  implicit val amoCrmLeadsResponseEmbeddedFormat: RootJsonFormat[AmoCrmLeadsResponseEmbedded] =
    jsonFormat(AmoCrmLeadsResponseEmbedded.apply(_), "contacts")
  implicit val amoCrmLeadsResponseFormat: RootJsonFormat[AmoCrmLeadsResponse] =
    jsonFormat(
      AmoCrmLeadsResponse.apply(_, _, _, _),
      "id",
      "name",
      "_embedded",
      "custom_fields_values"
    )

  implicit val amoCrmContactsResponseFormat: RootJsonFormat[AmoCrmContactsResponse] =
    jsonFormat(
      AmoCrmContactsResponse.apply(_, _, _, _),
      "id",
      "first_name",
      "last_name",
      "custom_fields_values"
    )

  implicit val amoCrmContactSearchResponseEmbeddedFormat: RootJsonFormat[AmoCrmContactSearchResponseEmbedded] =
    jsonFormat(AmoCrmContactSearchResponseEmbedded.apply(_), "contacts")
  implicit val amoCrmContactSearchResponseFormat: RootJsonFormat[AmoCrmContactSearchResponse] =
    jsonFormat(AmoCrmContactSearchResponse.apply(_, _), "_page", "_embedded")

  implicit val amoCrmUpdateContactRequestFormat: RootJsonFormat[AmoCrmUpdateContactRequest] =
    jsonFormat(AmoCrmUpdateContactRequest.apply(_), "custom_fields_values")

  implicit val AmoCrmCreateContactRequestFormat: RootJsonFormat[AmoCrmCreateContactRequest] =
    jsonFormat(
      AmoCrmCreateContactRequest.apply(_, _, _),
      "name",
      "responsible_user_id",
      "custom_fields_values"
    )

  implicit val amoCrmContactLinksResponseEmbeddedFormat: RootJsonFormat[AmoCrmContactLinksResponseEmbedded] =
    jsonFormat(AmoCrmContactLinksResponseEmbedded.apply(_), "links")
  implicit val amoCrmContactLinksResponseFormat: RootJsonFormat[AmoCrmContactLinksResponse] =
    jsonFormat(AmoCrmContactLinksResponse.apply(_), "_embedded")

  implicit val amoCrmLeadsSearchEmbeddedLeadResponseFormat: RootJsonFormat[AmoCrmLeadsSearchEmbeddedLeadResponse] =
    jsonFormat(AmoCrmLeadsSearchEmbeddedLeadResponse.apply(_, _), "id", "status_id")
  implicit val amoCrmLeadsSearchEmbeddedResponseFormat: RootJsonFormat[AmoCrmLeadsSearchEmbeddedResponse] =
    jsonFormat(AmoCrmLeadsSearchEmbeddedResponse.apply(_), "leads")
  implicit val amoCrmLeadsSearchResponseFormat: RootJsonFormat[AmoCrmLeadsSearchResponse] =
    jsonFormat(AmoCrmLeadsSearchResponse.apply(_), "_embedded")

  implicit val amoCrmAddLeadNoteRequestTextFormat: RootJsonFormat[AmoCrmAddLeadNoteRequestText] =
    jsonFormat(AmoCrmAddLeadNoteRequestText.apply(_), "text")
  implicit val amoCrmAddLeadNoteRequestFormat: RootJsonFormat[AmoCrmAddLeadNoteRequest] =
    jsonFormat(AmoCrmAddLeadNoteRequest.apply(_, _), "params", "note_type")

  implicit val amoCrmTaskCreateRequestFormat: RootJsonFormat[AmoCrmTaskCreateRequest] =
    jsonFormat(
      AmoCrmTaskCreateRequest.apply(_, _, _, _, _, _),
      "entity_id",
      "responsible_user_id",
      "entity_type",
      "task_type_id",
      "text",
      "complete_till"
    )

  implicit val amoCrmCreateLeadRequestEmbeddedTagFormat: RootJsonFormat[AmoCrmCreateLeadRequestEmbeddedTag] =
    jsonFormat(AmoCrmCreateLeadRequestEmbeddedTag.apply(_), "name")
  implicit val amoCrmCreateLeadRequestEmbeddedFormat: RootJsonFormat[AmoCrmCreateLeadRequestEmbedded] =
    jsonFormat(AmoCrmCreateLeadRequestEmbedded.apply(_), "tags")
  implicit val amoCrmCreateLeadRequestFormat: RootJsonFormat[AmoCrmCreateLeadRequest] =
    jsonFormat(
      AmoCrmCreateLeadRequest.apply(_, _, _, _, _, _),
      "name",
      "pipeline_id",
      "status_id",
      "responsible_user_id",
      "custom_fields_values",
      "_embedded"
    )

  implicit val amoCrmCreateLeadResponseEmbeddedLeadFormat: RootJsonFormat[AmoCrmCreateLeadResponseEmbeddedLead] =
    jsonFormat(AmoCrmCreateLeadResponseEmbeddedLead.apply(_), "id")
  implicit val amoCrmCreateLeadResponseEmbeddedFormat: RootJsonFormat[AmoCrmCreateLeadResponseEmbedded] =
    jsonFormat(AmoCrmCreateLeadResponseEmbedded.apply(_), "leads")
  implicit val amoCrmCreateLeadResponseFormat: RootJsonFormat[AmoCrmCreateLeadResponse] =
    jsonFormat(AmoCrmCreateLeadResponse.apply(_), "_embedded")

  implicit val amoCrmAddLinkRequestFormat: RootJsonFormat[AmoCrmAddLinkRequest] =
    jsonFormat(AmoCrmAddLinkRequest.apply(_, _), "to_entity_id", "to_entity_type")

  implicit val amoCrmCreateContactResponseEmbeddedContactFormat
      : RootJsonFormat[AmoCrmCreateContactResponseEmbeddedContact] =
    jsonFormat(AmoCrmCreateContactResponseEmbeddedContact.apply(_), "id")
  implicit val AmoCrmCreateContactResponseEmbeddedFormat: RootJsonFormat[AmoCrmCreateContactResponseEmbedded] =
    jsonFormat(AmoCrmCreateContactResponseEmbedded.apply(_), "contacts")
  implicit val amoCrmCreateContactResponseFormat: RootJsonFormat[AmoCrmCreateContactResponse] =
    jsonFormat(AmoCrmCreateContactResponse.apply(_), "_embedded")
}
