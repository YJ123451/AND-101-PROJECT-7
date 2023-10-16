package com.example.pokemongenerator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.codepath.asynchttpclient.AsyncHttpClient
import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler
import okhttp3.Headers

class MainActivity : AppCompatActivity() {
    var pokemonEndpoint = 1
    lateinit var nameTextView: TextView
    lateinit var urlTextView: TextView
    lateinit var numberTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val button = findViewById<Button>(R.id.Pokebutton)
        nameTextView = findViewById<TextView>(R.id.Name)
        urlTextView = findViewById<TextView>(R.id.poke_Url)
        numberTextView = findViewById<TextView>(R.id.poke_number)

        button.setOnClickListener {
            getNextPokemon()
        }
    }

    private fun getPokemonAPI(endpoint: Int) {
        val client = AsyncHttpClient()
        val apiUrl = "https://pokeapi.co/api/v2/pokemon/$endpoint/"

        client[apiUrl, object : JsonHttpResponseHandler() {
            override fun onSuccess(statusCode: Int, headers: Headers, json: JSON) {
                val name = json.jsonObject.getString("name")
                val url = apiUrl
                val number = endpoint

                nameTextView.text = "Name: $name"
                urlTextView.text = "URL: $url"
                numberTextView.text = "Number: $number"
            }

            override fun onFailure(
                statusCode: Int,
                headers: Headers?,
                response: String,
                throwable: Throwable?
            ) {
                // Handle the failure case
            }
        }]
    }

    private fun getNextPokemon() {
        pokemonEndpoint = (1..898).random()
        getPokemonAPI(pokemonEndpoint)
    }
}
