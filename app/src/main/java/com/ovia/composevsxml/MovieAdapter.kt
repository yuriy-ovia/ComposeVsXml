package com.ovia.composevsxml

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ovia.composevsxml.databinding.MovieCardBinding
import com.ovia.composevsxml.models.Result
import com.squareup.picasso.Picasso
import java.util.Locale

class MovieAdapter(
    private val movies: List<Result>
) : RecyclerView.Adapter<MovieAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = MovieCardBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(movies[position])
    }

    override fun getItemCount(): Int = movies.size

    inner class ViewHolder(private val binding: MovieCardBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(result: Result) {
            binding.item = result
            Picasso.get().load(result.poster)
                .error(R.drawable.ic_launcher_background)
                .into(binding.poster)

            binding.executePendingBindings()
            binding.type.text = binding.type.text.toString()
                .replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString() }
        }
    }
}
