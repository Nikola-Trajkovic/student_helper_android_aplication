package rs.raf.nikola_trajkovic_4519.modules

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import rs.raf.nikola_trajkovic_4519.data.datasources.local.RasporedDataBase
import rs.raf.nikola_trajkovic_4519.data.datasources.remote.RasporedService
import rs.raf.nikola_trajkovic_4519.data.repositories.RasporedRepository
import rs.raf.nikola_trajkovic_4519.data.repositories.RasporedRepositoryImpl
import rs.raf.nikola_trajkovic_4519.presentation.viewmodel.RasporedViewModel

val movieModule = module {

    viewModel { RasporedViewModel(rasporedRepository = get()) }

    single<RasporedRepository> { RasporedRepositoryImpl(localDataSource = get(), remoteDataSource = get()) }

    single { get<RasporedDataBase>().getRasporedDao() }

    single<RasporedService> { create(retrofit = get()) }

}

