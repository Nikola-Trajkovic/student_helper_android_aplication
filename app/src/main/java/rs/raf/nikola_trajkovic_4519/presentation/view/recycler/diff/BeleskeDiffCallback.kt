package rs.raf.nikola_trajkovic_4519.presentation.view.recycler.diff

import androidx.recyclerview.widget.DiffUtil
import rs.raf.nikola_trajkovic_4519.data.models.Beleske
import rs.raf.nikola_trajkovic_4519.data.models.Raspored

class BeleskeDiffCallback : DiffUtil.ItemCallback<Beleske>() {
    override fun areItemsTheSame(oldItem: Beleske, newItem: Beleske): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Beleske, newItem: Beleske): Boolean {
        return oldItem.naslov == newItem.naslov
    }


}