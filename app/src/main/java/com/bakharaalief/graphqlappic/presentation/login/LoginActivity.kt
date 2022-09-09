package com.bakharaalief.graphqlappic.presentation.login

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import androidx.lifecycle.ViewModelProvider
import com.bakharaalief.graphqlappic.R
import com.bakharaalief.graphqlappic.data.Resource
import com.bakharaalief.graphqlappic.data.userPref.UserModel
import com.bakharaalief.graphqlappic.databinding.ActivityLoginBinding
import com.bakharaalief.graphqlappic.presentation.ViewModelFactory
import com.bakharaalief.graphqlappic.presentation.main.MainActivity
import com.bakharaalief.graphqlappic.util.Helper

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")

class LoginActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityLoginBinding
    private lateinit var loginViewModel: LoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setUpActionBar()
        setUpViewModel()
        setUpOnClick()
        getUserPref()
    }

    private fun setUpActionBar() {
        supportActionBar?.title = "Login"
    }

    private fun setUpViewModel() {
        val factory = ViewModelFactory.getInstance(dataStore)
        loginViewModel = ViewModelProvider(this, factory)[LoginViewModel::class.java]
    }

    private fun setUpOnClick() {
        binding.loginBtn.setOnClickListener(this)
    }

    override fun onClick(view: View) {
        when (view.id) {
            R.id.login_btn -> loginAction()
        }
    }

    private fun loginAction() {
        val username = binding.usernameField.editText?.text.toString()
        val password = binding.passwordField.editText?.text.toString()

        loginViewModel.login(username, password).observe(this) { response ->
            when (response) {
                is Resource.Loading -> Toast.makeText(this, "Loading", Toast.LENGTH_SHORT).show()
                is Resource.Success -> {
                    Toast.makeText(this, "Berhasil Login", Toast.LENGTH_SHORT).show()
                    val userModel = UserModel(
                        response.data.accessToken,
                        response.data.accessTokenExpiresAt,
                        true
                    )
                    loginViewModel.saveUser(userModel)
                    toMain()
                }
                is Resource.Error -> {
                    Toast.makeText(this, response.error, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun toMain() {
        val intent = Intent(this, MainActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }
        startActivity(intent)
    }

    private fun getUserPref() {
        loginViewModel.getUserPref().observe(this) { userPref ->
            if (userPref.isUserLogin) {
                val isExpired = Helper.isAccessExpired(userPref.accessTokenExpired)
                if (isExpired) Toast.makeText(
                    this,
                    "Your session already end, Please Login Again :)",
                    Toast.LENGTH_SHORT
                ).show()
                else toMain()
            } else {
                Toast.makeText(
                    this,
                    "Please Login :)",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }
}