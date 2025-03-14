package com.example.paybout.Logged

import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.paybout.MainActivity
import com.example.paybout.databinding.LoginPageBinding
import com.google.firebase.auth.FirebaseAuth

class LoginPage : AppCompatActivity() {

    private lateinit var binding: LoginPageBinding
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        binding = LoginPageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = FirebaseAuth.getInstance()

        binding.loginbtn.setOnClickListener { loginUser() }



        binding.signEditlogin.setOnClickListener {
            startActivity(Intent(this, SignupPage::class.java))
        }
    }

    private fun loginUser() {
        val email = binding.gmailloginedit.text.toString().trim()
        val pass = binding.passwordloginedit.text.toString().trim()

        when {
            email.isEmpty() || pass.isEmpty() -> showToast("Please fill in all the details")
            !Patterns.EMAIL_ADDRESS.matcher(email).matches() -> showToast("Enter a valid email address")
            else -> {
                binding.progressBar.visibility = View.VISIBLE  // Show progress bar

                auth.signInWithEmailAndPassword(email, pass).addOnCompleteListener { task ->
                    binding.progressBar.visibility = View.GONE  // Hide progress bar

                    if (task.isSuccessful) {
                        showToast("Login Successful")
                        startActivity(Intent(this, MainActivity::class.java))  // Navigate to home
                        finish()
                    } else {
                        showToast(task.exception?.localizedMessage ?: "Authentication Failed")
                    }
                }
            }
        }
    }

    override fun onStart() {
        super.onStart()
        auth.currentUser?.let {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}
