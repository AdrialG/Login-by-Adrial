package com.example.notesbyadrialrework.ui.login

import android.os.Bundle
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.crocodic.core.api.ApiStatus
import com.crocodic.core.data.CoreSession
import com.crocodic.core.extension.base64encrypt
import com.crocodic.core.extension.isEmptyRequired
import com.crocodic.core.extension.openActivity
import com.crocodic.core.extension.textOf
import com.crocodic.core.helper.DateTimeHelper
import com.example.notesbyadrialrework.R
import com.example.notesbyadrialrework.base.BaseActivity
import com.example.notesbyadrialrework.data.Const
import com.example.notesbyadrialrework.databinding.ActivityLoginBinding
import com.example.notesbyadrialrework.ui.home.HomeActivity
import com.example.notesbyadrialrework.ui.register.RegisterActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject


@AndroidEntryPoint
class LoginActivity : BaseActivity<ActivityLoginBinding, LoginViewModel>(R.layout.activity_login) {

   @Inject
   lateinit var session : CoreSession

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        tokenAPI()


        binding.buttontohome.setOnClickListener {

            if (binding.etEmail.isEmptyRequired(R.string.fill_please) || binding.etPassword.isEmptyRequired(R.string.fill_please)){
                return@setOnClickListener
            }

            val email = binding.etEmail.textOf()
            val password = binding.etPassword.textOf()

            viewModel.login(email, password)
        }

        binding.buttontoregister.setOnClickListener {
            openActivity<RegisterActivity>() {
                finish()
            }
        }

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                launch {
                    viewModel.apiResponse.collect {
                        when (it.status) {
                            ApiStatus.LOADING -> loadingDialog.show("Logging in...")
                            ApiStatus.SUCCESS -> {
                                loadingDialog.dismiss()
                                openActivity<HomeActivity>()
                                finish()
                            }
                            ApiStatus.ERROR -> {
                                disconnect(it)
                            }
                            ApiStatus.EXPIRED -> {

                            }
                            else -> loadingDialog.setResponse(it.message ?: return@collect)
                        }
                    }
                }
            }
        }
    }

    private fun tokenAPI() {

        val dateNow = DateTimeHelper().dateNow()
        val tokenInit = "$dateNow|rahasia"
        val tokenEncrypt = tokenInit.base64encrypt()

        session.setValue(Const.TOKEN.API_TOKEN,tokenEncrypt)

        Timber.d("Check Token : $tokenInit")

        lifecycleScope.launch {
            viewModel.getToken()
        }
    }

}
