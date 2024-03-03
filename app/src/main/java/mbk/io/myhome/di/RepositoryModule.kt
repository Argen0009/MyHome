package mbk.io.myhome.di

import mbk.io.myhome.data.RMRepository
import org.koin.dsl.module

val RepositoryModule = module {
    single { RMRepository(get()) }
}
