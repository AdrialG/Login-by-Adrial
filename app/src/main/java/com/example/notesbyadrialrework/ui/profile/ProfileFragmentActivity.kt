package com.example.notesbyadrialrework.ui.profile
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.lifecycleScope
import com.crocodic.core.extension.openActivity
import com.example.notesbyadrialrework.R
import com.example.notesbyadrialrework.base.BaseActivity
import com.example.notesbyadrialrework.base.SharedPreference
import com.example.notesbyadrialrework.databinding.ActivityProfileBinding
import com.example.notesbyadrialrework.ui.login.LoginActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch


@AndroidEntryPoint
class ProfileFragmentActivity : BaseActivity<ActivityProfileBinding, ProfileFragmentViewModel>(R.layout.activity_profile) {

    private lateinit var sharedPref : SharedPreference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityProfileBinding = DataBindingUtil.setContentView(
            this, R.layout.activity_profile)
        sharedPref = SharedPreference(this)

        lifecycleScope.launch {
            viewModel.getUser.observe(this@ProfileFragmentActivity) {
                binding.exampleTextName.text = it.name
                binding.exampleTextEmail.text = it.email

                Toast.makeText(this@ProfileFragmentActivity, "ini user : ${it.name}", Toast.LENGTH_SHORT).show()
            }
        }
        binding.buttonLogout.setOnClickListener {
            viewModel.logout {
            }
        }
    }
}
