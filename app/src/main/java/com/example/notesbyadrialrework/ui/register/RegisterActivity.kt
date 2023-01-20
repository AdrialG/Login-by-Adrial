package com.example.notesbyadrialrework.ui.register

import android.os.Bundle
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.crocodic.core.api.ApiStatus
import com.crocodic.core.extension.isEmptyRequired
import com.crocodic.core.extension.openActivity
import com.crocodic.core.extension.textOf
import com.example.notesbyadrialrework.R
import com.example.notesbyadrialrework.base.BaseActivity
import com.example.notesbyadrialrework.databinding.ActivityRegisterBinding
import com.example.notesbyadrialrework.ui.home.HomeActivity
import com.example.notesbyadrialrework.ui.login.LoginActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class RegisterActivity : BaseActivity<ActivityRegisterBinding, RegisterViewModel>(R.layout.activity_register) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.registerbackbutton.setOnClickListener {
            openActivity<LoginActivity>()
            finish()
        }

        binding.registersave.setOnClickListener {
            if (binding.etNameRegister.isEmptyRequired(R.string.fill_please) || binding.etEmailRegister.isEmptyRequired(R.string.fill_please) || binding.etPasswordRegister.isEmptyRequired(R.string.fill_please)){
                return@setOnClickListener
            }

            val name = binding.etNameRegister.textOf()
            val email = binding.etEmailRegister.textOf()
            val password = binding.etPasswordRegister.textOf()
            val confirmPassword = binding.ettPasswordConfirmRegister.textOf()

            viewModel.register( name ,email, password)

            if (confirmPassword != password) {

            }
            else {
                binding.etNameRegister.isEmptyRequired(R.string.pass_match)
            }

        }

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                launch {
                    viewModel.apiResponse.collect {
                        when (it.status) {
                            ApiStatus.LOADING -> loadingDialog.show("Hang Tight...")
                            ApiStatus.SUCCESS -> {
                                loadingDialog.dismiss()
                                openActivity<HomeActivity>()
                                finish()
                            }
                            else -> loadingDialog.setResponse(it.message ?: return@collect)
                        }
                    }
                }
            }
        }

    }
}
