package com.hamy.cloudstorage.Fragments

import android.R.attr
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.hamy.cloudstorage.R
import com.hamy.cloudstorage.ViewModels.MainViewModel
import com.hamy.cloudstorage.databinding.LoginviewBinding
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import com.google.android.material.snackbar.Snackbar
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.hamy.cloudstorage.Model.Data
import com.hamy.cloudstorage.Utils.ApiState
import com.hamy.cloudstorage.Utils.Notifier
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import retrofit2.Call
import retrofit2.Response
import retrofit2.awaitResponse
import java.util.ArrayList
import java.util.regex.Matcher
import java.util.regex.Pattern

@AndroidEntryPoint
class LoginView : Fragment() {
    private lateinit var binding: LoginviewBinding
    private lateinit var navController: NavController
    private val maiViewModel: MainViewModel by viewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = LoginviewBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)

        binding.txtSignup.setOnClickListener {
            navController.navigate(R.id.login_to_signup)
        }

        binding.btnLogin.setOnClickListener {
            binding.txtEmailInput.setBoxBackgroundColorResource(R.color.white)
            binding.txtInputPassword.setBoxBackgroundColorResource(R.color.white)

            if (binding.editEmailInput.text.toString().isEmpty() ||
                !checkEmail(binding.editEmailInput.text.toString())
            ) {
                binding.txtEmailInput.error = getString(R.string.mail_hint)
                binding.txtEmailInput.setBoxBackgroundColorResource(R.color.red)
                binding.txtEmailInput.setErrorIconDrawable(R.drawable.ic_baseline_error)
            } else {
                binding.txtEmailInput.error = ""
                binding.txtEmailInput.setBoxBackgroundColorResource(R.color.white)
                binding.txtEmailInput.errorIconDrawable = null

                val any = if (binding.editPasswordInput.text.toString().isEmpty() ||
                    !checkPassword(binding.editPasswordInput.text.toString())
                ) {
                    binding.txtInputPassword.setBoxBackgroundColorResource(R.color.red)
                    binding.txtInputPassword.error = getString(R.string.passoword_hint)
                    binding.txtInputPassword.setErrorIconDrawable(R.drawable.ic_baseline_error)
                } else {
                    binding.txtInputPassword.setBoxBackgroundColorResource(R.color.white)
                    binding.txtInputPassword.error = ""
                    binding.txtInputPassword.errorIconDrawable = null

                    binding.layout.loading.visibility = View.VISIBLE
                    binding.btnLogin.isEnabled = false
                    binding.btnLogin.isClickable = false
                    maiViewModel.getLogin(
                        binding.editEmailInput.text.toString(),
                        binding.editPasswordInput.text.toString()
                    )
                    lifecycleScope.launchWhenStarted {
                        maiViewModel.myPostStateFlow.collect {
                            when (it) {
                                is ApiState.Loading -> {
                                    binding.layout.loading.visibility = View.VISIBLE
                                }
                                is ApiState.Failure -> {
                                    binding.btnLogin.isEnabled = true
                                    binding.btnLogin.isClickable = true
                                    binding.layout.loading.visibility = View.GONE
                                    Notifier.showSnackBar(
                                        binding.btnLogin,
                                        "Network Problem, Try again!"
                                    )
                                }
                                is ApiState.Success -> {
                                    if (it.data[0].code == "login_success") {
                                        Notifier.showSnackBar(
                                            binding.btnLogin,
                                            "User Login with  ${it.data[0].email}"
                                        )
                                        navController.navigate(R.id.login_to_main)

                                    } else {
                                        binding.btnLogin.isEnabled = true
                                        binding.btnLogin.isClickable = true
                                        binding.layout.loading.visibility = View.GONE
                                        Notifier.showSnackBar(
                                            binding.btnLogin,
                                            "Login error for  ${it.data[0].email}"
                                        )
                                    }

                                }
                                is ApiState.Empty -> {

                                }
                            }
                        }
                    }

                }
            }
        }
    }


    private fun checkPassword(password: String): Boolean {
        val pattern: Pattern
        val PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[A-Z])(?=.*[@#$%^&+=!])(?=\\S+$).{4,}$"
        pattern = Pattern.compile(PASSWORD_PATTERN)
        val matcher: Matcher = pattern.matcher(password)

        return matcher.matches()
    }

    private fun checkEmail(email: String): Boolean {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }
}
