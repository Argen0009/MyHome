package mbk.io.myhome.ui.door

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import mbk.io.myhome.data.RMRepository
import mbk.io.myhome.data.model.Door
import mbk.io.myhome.utils.Resource

class DoorViewModel(private val repository:RMRepository):ViewModel() {

    fun getDoors(): LiveData<Resource<List<Door>>> = repository.getDoors()
}