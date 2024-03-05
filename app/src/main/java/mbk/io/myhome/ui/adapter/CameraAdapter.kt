    package mbk.io.myhome.ui.adapter
    
    import android.view.LayoutInflater
    import android.view.ViewGroup
    import androidx.recyclerview.widget.RecyclerView
    import com.bumptech.glide.Glide
    import mbk.io.myhome.data.model.Camera
    import mbk.io.myhome.databinding.ItemCardCameraBinding
    
    
    class CameraAdapter(private var dataList: List<Camera>) :
        RecyclerView.Adapter<CameraAdapter.ViewHolder>() {
    
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val inflater = LayoutInflater.from(parent.context)
            val binding = ItemCardCameraBinding.inflate(inflater, parent, false)
            return ViewHolder(binding)
        }
    
        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            dataList?.let {
                data->
                val cameraModel = dataList[position]
                holder.bind(cameraModel)
            }
        }
    
        override fun getItemCount(): Int {
            return dataList.size
        }

        fun setData(cameraList: List<Camera>) {
            dataList = cameraList
            notifyDataSetChanged()
        }


        inner class ViewHolder(private val binding: ItemCardCameraBinding) :
            RecyclerView.ViewHolder(binding.root) {
    
            fun bind(cameraModel: Camera) {
                binding.cameraTv.text = cameraModel.name
                Glide.with(binding.root)
                    .load(cameraModel.snapshot)
                    .into(binding.videoIv)
            }
        }
    }