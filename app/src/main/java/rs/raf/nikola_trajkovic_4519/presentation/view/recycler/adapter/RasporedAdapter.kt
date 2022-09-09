package rs.raf.nikola_trajkovic_4519.presentation.view.recycler.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import rs.raf.nikola_trajkovic_4519.data.models.Raspored
import rs.raf.nikola_trajkovic_4519.databinding.LayoutItemMovieBinding
import rs.raf.nikola_trajkovic_4519.presentation.view.recycler.diff.RasporedDiffCallback
import rs.raf.nikola_trajkovic_4519.presentation.view.recycler.viewholder.RasporedViewHolder

class RasporedAdapter : ListAdapter<Raspored, RasporedViewHolder>(RasporedDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RasporedViewHolder {
        val itemBinding = LayoutItemMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return RasporedViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: RasporedViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

}