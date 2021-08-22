package com.hamy.cloudstorage.Fragments

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.hamy.cloudstorage.R
import com.hamy.cloudstorage.databinding.SignupnviewBinding
import java.util.regex.Matcher
import java.util.regex.Pattern


class SignUpView : Fragment() {
    lateinit var navController: NavController
    private lateinit var binding: SignupnviewBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = SignupnviewBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)


        // handling listners
        val callback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                navController.navigate(R.id.signup_to_login)
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(requireActivity(), callback)


        binding.txtAlreadyAcc.setOnClickListener {
            navController.navigate(R.id.signup_to_login)
        }

        binding.btnSignup.setOnClickListener {
            binding.inputTxtName.setBoxBackgroundColorResource(R.color.white)
            binding.inputTxtContact.setBoxBackgroundColorResource(R.color.white)
            binding.inputTxtMail.setBoxBackgroundColorResource(R.color.white)
            binding.inputTxtPassword.setBoxBackgroundColorResource(R.color.white)
            binding.inputTxtName.error = ""
            binding.inputTxtContact.error = ""
            binding.inputTxtMail.error = ""
            binding.inputTxtPassword.error = ""

            if (binding.editTxtName.text.toString().isNotEmpty()) {
                binding.inputTxtName.error = ""
                binding.inputTxtName.setBoxBackgroundColorResource(R.color.white)
                binding.inputTxtName.errorIconDrawable = null
                if (binding.editTxtContact.text.toString().isNotEmpty() &&
                    checkContact(binding.editTxtContact.text.toString())
                ) {
                    binding.inputTxtContact.error = ""
                    binding.inputTxtContact.setBoxBackgroundColorResource(R.color.white)
                    binding.inputTxtContact.errorIconDrawable = null
                    if (binding.editTxtEmail.text.toString().isNotEmpty() &&
                        checkEmail(binding.editTxtEmail.text.toString())
                    ) {
                        binding.inputTxtMail.error = ""
                        binding.inputTxtMail.setBoxBackgroundColorResource(R.color.white)
                        binding.inputTxtMail.errorIconDrawable = null
                          if(binding.editTxtPassword.text.toString().isNotEmpty() &&
                              checkPassword(binding.editTxtPassword.text.toString())){
                              binding.inputTxtPassword.error = ""
                              binding.inputTxtPassword.setBoxBackgroundColorResource(R.color.white)
                              binding.inputTxtPassword.errorIconDrawable = null

                        }else{
                              binding.inputTxtPassword.setBoxBackgroundColorResource(R.color.red)
                              binding.inputTxtPassword.error = getString(R.string.passoword_hint)
                              binding.inputTxtPassword.setErrorIconDrawable(R.drawable.ic_baseline_error)
                        }
                    } else {
                        binding.inputTxtMail.error = getString(R.string.mail_hint)
                        binding.inputTxtMail.setBoxBackgroundColorResource(R.color.red)
                        binding.inputTxtMail.setErrorIconDrawable(R.drawable.ic_baseline_error)
                    }
                } else {
                    binding.inputTxtContact.setBoxBackgroundColorResource(R.color.red)
                    binding.inputTxtContact.error = "Contact must be proper"
                    binding.inputTxtContact.setErrorIconDrawable(R.drawable.ic_baseline_error)

                }
            } else {
                binding.inputTxtName.error = "Name must be filled"
                binding.inputTxtName.setBoxBackgroundColorResource(R.color.red)
                binding.inputTxtName.setErrorIconDrawable(R.drawable.ic_baseline_error)
            }

        }

    }

    private fun checkContact(contact: String): Boolean {
        return contact.length > 9
    }

    private fun checkEmail(email: String): Boolean {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }
    private fun checkPassword(password: String): Boolean {
        val pattern: Pattern
        val PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[A-Z])(?=.*[@#$%^&+=!])(?=\\S+$).{4,}$"
        pattern = Pattern.compile(PASSWORD_PATTERN)
        val matcher: Matcher = pattern.matcher(password)

        return matcher.matches()
    }


}