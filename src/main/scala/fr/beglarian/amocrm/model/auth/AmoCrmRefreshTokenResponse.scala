package fr.beglarian.amocrm.model.auth

case class AmoCrmRefreshTokenResponse(tokenType: String, expiresIn: Int, accessToken: String, refreshToken: String)
