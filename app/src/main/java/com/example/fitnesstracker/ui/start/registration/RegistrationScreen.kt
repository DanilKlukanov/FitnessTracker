package com.example.fitnesstracker.ui.start.registration

import android.content.Intent
import android.os.Bundle
import android.text.TextPaint
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.view.View
import android.widget.Button
import android.widget.RadioGroup
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.core.text.set
import androidx.core.text.toSpannable
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.fitnesstracker.ui.section.MainActivity
import com.example.fitnesstracker.R
import com.example.fitnesstracker.network.SharedPref
import com.example.fitnesstracker.ui.ViewModelFactory
import com.example.fitnesstracker.ui.profile.ProfileScreenViewModel
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

class RegistrationScreen : Fragment(R.layout.registration_screen) {

    private val viewModel by viewModels<RegistrationScreenViewModel> {
        ViewModelFactory { RegistrationScreenViewModel(SharedPref(requireContext())) }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val text_span =
            ("Нажимая на кнопку, вы соглашаетесь с политикой конфиденциальности и обработки персональных данных, а также принимаете пользовательское соглашение").toSpannable()

        text_span[27..66] = object : ClickableSpan() {
            override fun updateDrawState(text: TextPaint) {
                text.color = text.linkColor
                text.isUnderlineText = false
            }

            override fun onClick(view: View) {
                Toast.makeText(
                    requireActivity().application,
                    "Политика конфиденциальности",
                    Toast.LENGTH_LONG
                ).show()
            }
        }

        text_span[text_span.length - 27..text_span.length] = object : ClickableSpan() {
            override fun updateDrawState(text: TextPaint) {
                text.color = text.linkColor
                text.isUnderlineText = false
            }

            override fun onClick(view: View) {
                Toast.makeText(
                    requireActivity().application,
                    "Пользовательское соглашение",
                    Toast.LENGTH_LONG
                ).show()
            }
        }

        val text = view.findViewById<TextView>(R.id.hello)
        text.movementMethod = LinkMovementMethod()
        text.text = text_span

        view.findViewById<Toolbar>(R.id.toolbar).setNavigationOnClickListener {
            findNavController().navigateUp()
        }

        viewModel.showLoginError.observe(viewLifecycleOwner) {
            if (it) {
                view.findViewById<TextInputLayout>(R.id.LoginInput).error = "Введите логин"
            } else {
                view.findViewById<TextInputLayout>(R.id.LoginInput).error = null
            }
        }

        viewModel.showNameError.observe(viewLifecycleOwner) {
            if (it) {
                view.findViewById<TextInputLayout>(R.id.Name_or_nicknameInput).error =
                    "Введите имя или никнейм"
            } else {
                view.findViewById<TextInputLayout>(R.id.Name_or_nicknameInput).error = null
            }
        }

        viewModel.showPasswordError.observe(viewLifecycleOwner) {
            if (it) {
                view.findViewById<TextInputLayout>(R.id.PasswordInput).error = "Введите пароль"
            } else {
                view.findViewById<TextInputLayout>(R.id.PasswordInput).error = null
            }
        }

        viewModel.showRepeatPasswordError.observe(viewLifecycleOwner) {
            if (it) {
                view.findViewById<TextInputLayout>(R.id.Password_again_Input).error =
                    "Повторите пароль"
            } else {
                view.findViewById<TextInputLayout>(R.id.Password_again_Input).error = null
            }
        }

        viewModel.showWrongRepeatPasswordError.observe(viewLifecycleOwner) {
            if (it) {
                view.findViewById<TextInputLayout>(R.id.Password_again_Input).error =
                    "Неверный повтор пароля"
            } else {
                view.findViewById<TextInputLayout>(R.id.Password_again_Input).error = null
            }
        }

        var gender = 0
        view.findViewById<RadioGroup>(R.id.RadioGroup_sex)
            .setOnCheckedChangeListener { _, checkedId ->
                if (checkedId == R.id.radioVibrateOne) {
                    gender = 0
                } else if (checkedId == R.id.radioVibrateTwo) {
                    gender = 1
                }
            }

        view.findViewById<Button>(R.id.Btn_registration).setOnClickListener {
            viewModel.onRegistrationClicked(
                view.findViewById<TextInputEditText>(R.id.login).text.toString(),
                view.findViewById<TextInputEditText>(R.id.name_or_nickname).text.toString(),
                view.findViewById<TextInputEditText>(R.id.password).text.toString(),
                view.findViewById<TextInputEditText>(R.id.password_again).text.toString(),
                gender
            )
        }

        viewModel.error.observe(viewLifecycleOwner) {
            if (it == "Okay") {
                startActivity(Intent(activity, MainActivity::class.java))
                activity?.finish()
            } else {
                Toast.makeText(
                    requireActivity().application,
                    it,
                    Toast.LENGTH_LONG
                ).show()
            }
        }
    }
}