package com.example.paybout.Logged
import android.content.Intent
import android.os.Bundle
import android.widget.Toast

import androidx.appcompat.app.AppCompatActivity

import com.example.paybout.databinding.SignupPageBinding
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import com.google.firebase.database.FirebaseDatabase
class SignupPage : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    private lateinit var binding: SignupPageBinding
    private lateinit var database: FirebaseDatabase


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = SignupPageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = Firebase.auth
        database = FirebaseDatabase.getInstance("https://paybout-777eee-default-rtdb.asia-southeast1.firebasedatabase.app/")
        
        binding.signEditsignin.setOnClickListener {
            val intent = Intent(this, LoginPage::class.java)
            startActivity(intent)
            finish()
        }

        binding.signinbtn.setOnClickListener {
            val email = binding.gmailsigninedit.text.toString()
            val pass = binding.passwordsigninedit.text.toString()
            val confirmPass = binding.repasswordsigninedit.text.toString()


            if(email.isNotEmpty() && pass.isNotEmpty() && confirmPass.isNotEmpty()) {
                if( pass == confirmPass) {
                    auth.createUserWithEmailAndPassword(email, pass).addOnCompleteListener { task ->
                        if(task.isSuccessful) {
                            val user = auth.currentUser
                            val databaseReference = database.getReference("users")


                            val userHelperClass = UserHelperClass(email,pass)

                            user?.uid?.let { uid ->
                                databaseReference.child(uid).setValue(userHelperClass).addOnSuccessListener {
                                    Toast.makeText(this, "User Registered Successfully!", Toast.LENGTH_SHORT).show()
                                    startActivity(Intent(this, LoginPage::class.java))
                                    finish()
                                }.addOnFailureListener {
                                    Toast.makeText(this, "Failed to Store user data", Toast.LENGTH_SHORT).show()
                                }
                            }
                        }
                        else {
                            Toast.makeText(this, task.exception?.message?: "Sign-up failed", Toast.LENGTH_SHORT).show()

                        }
                    }
                }
                else {
                    Toast.makeText(this, "Password do not match", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this, "Fill all the Details", Toast.LENGTH_SHORT).show()
            }
        }

    }
}


data class userHelperClass(
    val email: String,
    val password: String
)