package br.com.fiap.rpp.githubapp

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import br.com.fiap.rpp.githubapp.model.GithubUser
import br.com.fiap.rpp.githubapp.service.GithubService
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        buttonSearch.setOnClickListener { this.search() }

    }

    private fun search() {

        progreeBar.visibility = View.VISIBLE

        val retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("https://api.github.com/").build()

        val service = retrofit.create(GithubService::class.java)

        service.searchUser(userLogin.text.toString())
            .enqueue(object : Callback<GithubUser>{
                override fun onFailure(call: Call<GithubUser>, t: Throwable) {
                    Toast.makeText(this@MainActivity, t.message, Toast.LENGTH_SHORT).show()
                }

                override fun onResponse(call: Call<GithubUser>, response: Response<GithubUser>) {

                    if (response.isSuccessful) {
                        val user = response.body()
                        if (user != null) {
                            setupValueScreen(user)
                        }
                    }
                }

            })
    }

    private fun setupValueScreen(githubUser: GithubUser) {

        Picasso.get().load(githubUser.avatar).into(githubImage)
        userName.text = githubUser.userName

        progreeBar.visibility = View.GONE
    }
}
