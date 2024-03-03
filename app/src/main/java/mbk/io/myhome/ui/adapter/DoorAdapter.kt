package mbk.io.myhome.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import mbk.io.myhome.databinding.ItemDoorBinding
import mbk.io.model.DoorModel

class DoorAdapter(private var list: ArrayList<DoorModel>) :
    RecyclerView.Adapter<DoorAdapter.RecyclerViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemDoorBinding.inflate(inflater, parent, false)
        return RecyclerViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecyclerViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount() = list.size

    class RecyclerViewHolder(private var binding: ItemDoorBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(itemModel: DoorModel) = with(binding) {
            doorIv.load(itemModel.image)
            doorNameTv.text = itemModel.doorName
            doorNameTv.setOnClickListener {
                if (binding.doorCard.visibility == View.GONE) {
                    slideOutViews(binding.doorCard)
                } else {
                    slideInViews(binding.doorCard)
                }
            }
        }

        private fun slideOutViews(view: View) {
            val duration = 500L

            view.apply {
                visibility = View.VISIBLE
                translationY = -height.toFloat()

                animate()
                    .translationY(0f)
                    .setDuration(duration)
                    .start()
            }
        }

        private fun slideInViews(view: View) {
            val duration = 500L

            view.apply {
                animate()
                    .translationY(-height.toFloat())
                    .setDuration(duration)
                    .withEndAction {
                        visibility = View.GONE
                        translationY = 0f
                    }
                    .start()
            }
        }
    }
}