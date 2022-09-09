package rs.raf.nikola_trajkovic_4519.modules

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import rs.raf.nikola_trajkovic_4519.data.datasources.local.RasporedDataBase
import rs.raf.nikola_trajkovic_4519.data.repositories.BeleskeRepository
import rs.raf.nikola_trajkovic_4519.data.repositories.BeleskeRepositoryImpl

import rs.raf.nikola_trajkovic_4519.presentation.viewmodel.BeleskeViewModel


val beleskeModule = module {

    viewModel { BeleskeViewModel(beleskeRepository = get()) }

    single<BeleskeRepository> { BeleskeRepositoryImpl(localDataSource = get())}

    single { get<RasporedDataBase>().getBeleskeDao() }

}