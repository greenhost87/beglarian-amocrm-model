package fr.beglarian.amocrm.model.auth

case class AmoCrmRefreshTokenRequest(
    clientId: String,
    clientSecret: String,
    grantType: String,
    refreshToken: String,
    redirectUri: String
)
