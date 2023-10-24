package com.example.pokemongenerator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.codepath.asynchttpclient.AsyncHttpClient
import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler
import okhttp3.Headers

class MainActivity : AppCompatActivity() {
    var pokemonEndpoint = 1
    lateinit var recyclerView: RecyclerView
    private val pokemonList = ArrayList<Pokemon>()
    private lateinit var pokemonAdapter: PokemonAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val button = findViewById<Button>(R.id.Pokebutton)
        recyclerView = findViewById(R.id.pokemonRecyclerView)

        recyclerView.layoutManager = LinearLayoutManager(this)
        pokemonAdapter = PokemonAdapter(pokemonList)
        recyclerView.adapter = pokemonAdapter

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
                val number = json.jsonObject.getInt("id") // Use "id" to get the number
                val url = apiUrl

                
                pokemonList.add(Pokemon(name, url, number))
                pokemonAdapter.notifyDataSetChanged() 
            }

            override fun onFailure(
                statusCode: Int,
                headers: Headers?,
                response: String,
                throwable: Throwable?
            ) {
        
            }
        }]
    }

    private fun getNextPokemon() {
        pokemonEndpoint = (1..898).random()
        getPokemonAPI(pokemonEndpoint)
    }
}
