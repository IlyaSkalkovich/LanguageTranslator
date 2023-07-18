package com.example.trans

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.trans.databinding.ActivityMainBinding
import com.google.mlkit.nl.translate.TranslateLanguage
import com.google.mlkit.nl.translate.Translation
import com.google.mlkit.nl.translate.TranslatorOptions

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val translationConfigs = TranslatorOptions.Builder()
            .setSourceLanguage(TranslateLanguage.ENGLISH)
            .setTargetLanguage(TranslateLanguage.RUSSIAN)
            .build()

        val translator = Translation.getClient(translationConfigs)

        binding.button.setOnClickListener {
            if (binding.editText.text.isNotEmpty()) {
                translator.downloadModelIfNeeded()
                    .addOnSuccessListener {
                        translator.translate(binding.editText.text.toString())
                            .addOnSuccessListener {
                                binding.textView.text = it
                            }
                            .addOnFailureListener {
                                it.printStackTrace()
                            }
                    }
            }
        }
    }
}