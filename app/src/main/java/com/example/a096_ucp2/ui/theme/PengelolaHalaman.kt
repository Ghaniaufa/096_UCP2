package com.example.a096_ucp2.ui.theme

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.a096_ucp2.R
import com.example.a096_ucp2.data.FormUiState
import com.example.a096_ucp2.data.SumberDataDosen.dosen

enum class PengelolaHalaman{
    Home,
    Contact,
    Dosen,
    Summary
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ucpAppBar(
    bisaNavigasiBack: Boolean,
    navigasiUp: () -> Unit,
    modifier: Modifier = Modifier
) {
    TopAppBar(
        title = { Text(stringResource(id = R.string.app_name)) },
        colors = TopAppBarDefaults.mediumTopAppBarColors(containerColor = MaterialTheme.colorScheme.primaryContainer),
        modifier = modifier,
        navigationIcon = {
            if (bisaNavigasiBack) {
                IconButton(onClick = { navigasiUp }) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = stringResource(id = R.string.back_button)
                    )
                }
            }
        }
    )
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ucpApp(
    viewModel: FormViewModel = FormViewModel(),
    navController: NavHostController = rememberNavController()
) {
    Scaffold(
        topBar = {
            ucpAppBar(
                bisaNavigasiBack = false,
                navigasiUp = { /*TODO: implement back navigation*/ })
        }
    ) { innerPadding ->
        val formUiState by viewModel.stateUI.collectAsState()
        NavHost(
            navController = navController,
            startDestination = PengelolaHalaman.Home.name,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(route = PengelolaHalaman.Home.name) {
                HalamanHome(
                    onNextButtonClicked = {
                        navController.navigate(PengelolaHalaman.Dosen.name)
                    }
                )
            }
            composable(route = PengelolaHalaman.Dosen.name) {
                val context = LocalContext.current
                HalamanSatu(
                    pilihanDosen1 = dosen.map { id -> context.resources.getString(id) },
                    onNextButtonClicked = { navController.navigate(PengelolaHalaman.Summary.name) },
                    onSelectionChanged = {viewModel.setDosen1(it)},
                )
            }
            composable(route = PengelolaHalaman.Summary.name) {
                HalamanDua(
                    formUiState = FormUiState(),
                    onBackButtonClicked = { cancelOrderAndNavigateToRasa(navController) },
                    //onSendButtonClicked = { subject: String, summary: String ->}
                )
            }
        }
    }
}






private fun cancelOrderAndNavigateToHome(
    viewModel: FormViewModel,
    navController: NavHostController
) {
    viewModel.resetForm()
    navController.popBackStack(PengelolaHalaman.Contact.name, inclusive = false)
}

private fun cancelOrderAndNavigateToRasa(
    navController: NavHostController
) {
    navController.popBackStack(PengelolaHalaman.Dosen.name, inclusive = false)
}

private fun cancelContactAndNavigateToHome(
    viewModel: FormViewModel,
    navController: NavHostController
){
    navController.popBackStack(PengelolaHalaman.Home.name, inclusive = false)
}


