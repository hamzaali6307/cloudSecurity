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
import com.hamy.cloudstorage.databinding.ActivityMainBinding
import com.hamy.cloudstorage.databinding.LoginviewBinding
import android.R.attr.password
import com.hamy.cloudstorage.Utils.ApiState
import java.util.regex.Matcher
import java.util.regex.Pattern


class LoginView : Fragment() {
    private lateinit var binding: LoginviewBinding
    private lateinit var navController: NavController
    val maiViewModel: MainViewModel by viewModels()

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

        binding.btnLogin.setOnClickListener {
            binding.txtEmailInput.setBoxBackgroundColorResource(R.color.white)
            binding.txtInputPassword.setBoxBackgroundColorResource(R.color.white)

            if (binding.editEmailInput.text.toString().isEmpty() ||
                !checkEmail(binding.editEmailInput.text.toString())
            ) {
                binding.txtEmailInput.setBoxBackgroundColorResource(R.color.red)
                binding.txtEmailInput.setErrorIconDrawable(R.drawable.ic_baseline_error)
            } else {
                if (binding.editPasswordInput.text.toString().isEmpty() ||
                    !checkPassword(binding.editPasswordInput.text.toString())
                ) {
                    binding.txtInputPassword.setBoxBackgroundColorResource(R.color.red)
                    binding.txtInputPassword.setErrorIconDrawable(R.drawable.ic_baseline_error)
                } else {
                    maiViewModel.getLogin(
                        binding.editEmailInput.text.toString(),
                        binding.editPasswordInput.text.toString()
                    )
                    when(maiViewModel.myPostStateFlow.value){
                        ApiState.Loading->{

                        }
                    }


                    //navController.navigate(R.id.login_to_main)

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
