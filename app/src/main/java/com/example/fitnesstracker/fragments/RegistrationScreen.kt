package com.example.fitnesstracker.fragments

import android.os.Bundle
import android.text.SpannableString
import android.text.TextPaint
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.core.text.set
import androidx.core.text.toSpannable
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.fitnesstracker.R

class RegistrationScreen : Fragment(R.layout.registration_screen) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val text_span = ("Нажимая на кнопку, вы соглашаетесь с политикой конфиденциальности и обработки персональных данных, а также принимаете пользовательское соглашение").toSpannable()

        text_span[27 .. 66] = object: ClickableSpan(){
            override fun updateDrawState(text: TextPaint) {
                text.color = text.linkColor
                text.isUnderlineText = false
            }
            override fun onClick(view: View) {
                Toast.makeText(requireActivity().application, "Политика конфиденциальности", Toast.LENGTH_LONG).show()
            }
        }

        text_span[text_span.length - 27 .. text_span.length] = object: ClickableSpan(){
            override fun updateDrawState(text: TextPaint) {
                text.color = text.linkColor
                text.isUnderlineText = false
            }
            override fun onClick(view: View) {
                Toast.makeText(requireActivity().application, "Пользовательское соглашение", Toast.LENGTH_LONG).show()
            }
        }

        val text = view.findViewById<TextView>(R.id.hello)
        text.movementMethod = LinkMovementMethod()
        text.text = text_span

        view.findViewById<Toolbar>(R.id.toolbar).setNavigationOnClickListener {
            findNavController().navigateUp()
        }
    }
}