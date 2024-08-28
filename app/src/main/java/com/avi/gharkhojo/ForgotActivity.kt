package com.avi.gharkhojo

import android.os.Bundle
import android.view.WindowManager
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.avi.gharkhojo.databinding.ActivityForgotBinding
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


class ForgotActivity : AppCompatActivity() {

    private lateinit var forgotBinding: ActivityForgotBinding

      var  firebaseAuth:FirebaseAuth = FirebaseAuth.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
        enableEdgeToEdge()
        forgotBinding = ActivityForgotBinding.inflate(layoutInflater)
        setContentView(forgotBinding.root)
        setupWindowInsets()

        forgotBinding.buttonResetPassword.setOnClickListener {
            val userEmail = forgotBinding.editTextViewEmailResetPassword.text.toString().trim()
            if (userEmail.isNotEmpty()) {
                resetPassword(userEmail)
            } else {
                showToast("Empty fields are not allowed 😓.")
            }
        }
    }

    private fun setupWindowInsets() {
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    private fun resetPassword(email: String) {
        firebaseAuth.sendPasswordResetEmail(email).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                showToast("We sent a password reset mail to your email address ✅✅😌")
                finish()
            } else {
                showToast(task.exception?.localizedMessage ?: "Password reset failed.")
            }
        }
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}
