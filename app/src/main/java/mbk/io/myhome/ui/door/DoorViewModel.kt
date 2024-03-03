package mbk.io.myhome.ui.door

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import mbk.io.myhome.data.RMRepository
import mbk.io.myhome.utils.Resource

class DoorViewModel(private val repository:RMRepository):ViewModel() {

    fun getDors(): LiveData<Resource<List<Character>>> = repository.getDoors()
}