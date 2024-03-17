package mbk.io.myhome.presentora.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import mbk.io.myhome.data.model.camera.CameraEntity
import mbk.io.myhome.databinding.ItemCardCameraBinding


class CameraAdapter(private var dataList: Boolean) :
    RecyclerView.Adapter<CameraAdapter.ViewHolder>() {

    private var cameraList: List<CameraEntity> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemCardCameraBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(cameraList[position])
    }

    override fun getItemCount(): Int = cameraList.size

    fun submitList(list: List<CameraEntity>) {
        cameraList = list
        notifyDataSetChanged()
    }


    inner class ViewHolder(private val binding: ItemCardCameraBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(camera: CameraEntity) {
            with(binding) {
                videoIv.load(camera.snapshot)
                cameraTv.text = camera.name
                cameraTv.setOnClickListener {
                    if (binding.videoIv.visibility == View.GONE) {
                        slideOutViews(binding.videoIv)
                    } else {
                        slideInViews(binding.videoIv)
                    }
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