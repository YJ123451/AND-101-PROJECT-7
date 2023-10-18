package com.example.pokemongenerator
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

data class Pokemon(val name: String, val url: String, val number: Int) {

}
class PokemonAdapter(private val data: List<Pokemon>) : RecyclerView.Adapter<PokemonAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nameView: TextView = itemView.findViewById(R.id.Name)
        val urlView: TextView = itemView.findViewById(R.id.poke_Url)
        val numberView: TextView = itemView.findViewById(R.id.poke_number)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.recyclerview, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentItem = data[position]
        holder.nameView.text = "Name: ${currentItem.name}"
        holder.urlView.text = "URL: ${currentItem.url}"
        holder.numberView.text = "Number: ${currentItem.number}"
    }

    override fun getItemCount() = data.size
}
