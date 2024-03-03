package mbk.io.myhome.di

import mbk.io.myhome.ui.camera.CameraViewModel
import mbk.io.myhome.ui.door.DoorViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val ViewModelModule = module{
    viewModel{
        DoorViewModel(get())
    }
    viewModel{
        CameraViewModel(get())
    }
}