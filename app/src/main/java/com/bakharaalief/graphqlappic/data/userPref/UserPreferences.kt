package com.bakharaalief.graphqlappic.data.userPref

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class UserPreferences private constructor(private val dataStore: DataStore<Preferences>) {

    fun getUser(): Flow<UserModel> {
        return dataStore.data.map { preferences ->
            UserModel(
                preferences[ACCESS_TOKEN] ?: "",
                preferences[ACCESS_TOKEN_EXPIRED] ?: "",
                preferences[REFRESH_TOKEN] ?: "",
                preferences[REFRESH_TOKEN_EXPIRED] ?: "",
                preferences[IS_USER_LOGIN] ?: false
            )
        }
    }

    suspend fun saveUser(user: UserModel) {
        dataStore.edit { preferences ->
            preferences[ACCESS_TOKEN] = user.accessToken
            preferences[ACCESS_TOKEN_EXPIRED] = user.accessTokenExpired
            preferences[REFRESH_TOKEN] = user.refreshToken
            preferences[REFRESH_TOKEN_EXPIRED] = user.refreshTokenExpired
            preferences[IS_USER_LOGIN] = user.isUserLogin
        }
    }

    suspend fun logOut() {
        dataStore.edit { preferences ->
            preferences[IS_USER_LOGIN] = false
        }
    }

    companion object {
        @Volatile
        private var INSTANCE: UserPreferences? = null

        private val ACCESS_TOKEN = stringPreferencesKey("access_token")
        private val ACCESS_TOKEN_EXPIRED = stringPreferencesKey("access_token_expired")
        private val REFRESH_TOKEN = stringPreferencesKey("refresh_token")
        private val REFRESH_TOKEN_EXPIRED = stringPreferencesKey("refresh_token_expired")
        private val IS_USER_LOGIN = booleanPreferencesKey("is_user_login")

        fun getInstance(dataStore: DataStore<Preferences>): UserPreferences {
            return INSTANCE ?: synchronized(this) {
                val instance = UserPreferences(dataStore)
                INSTANCE = instance
                instance
            }
        }
    }
}