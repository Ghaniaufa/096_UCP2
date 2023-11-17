package com.example.a096_ucp2.ui.theme

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import com.example.a096_ucp2.R
import com.example.a096_ucp2.data.FormUiState

@Composable
fun HalamanDua(
    orderUiState: FormUiState,
    onBackButtonClicked: () -> Unit,
    modifier: Modifier = Modifier
){
    val contact = listOf(
        Pair(stringResource(id = R.string.nama), orderUiState.nama),
        Pair(stringResource(id = R.string.nim), orderUiState.nim),
        Pair(stringResource(id = R.string.konsentrasi), orderUiState.konsentrasi),
        Pair(stringResource(id = R.string.jk), orderUiState.jk),
    )

    val dosen = listOf(
        Pair(stringResource(id = R.string.ndsn), orderUiState.dp1),
        Pair(stringResource(id = R.string.ndsn), orderUiState.dp2)


    )
    Column (
        modifier = modifier,
        verticalArrangement = Arrangement.SpaceBetween
    ){
        Column (
            modifier = Modifier.padding(dimensionResource(id = R.dimen.padding_medium)),
            verticalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.padding_small))
        ){
            contact.forEach { item ->
                Column{
                    Text(item.first, fontWeight = FontWeight.Bold)
                    Text(text = item.second)
                }
                Divider(
                    thickness = dimensionResource(id = R.dimen.thickness_divider)
                )
            }
            dosen.forEach{ item ->
                Column {
                    Text(item.first.uppercase())
                    Text(text = item.second.toString(), fontWeight = FontWeight.Bold)
                }
                Divider(
                    thickness = dimensionResource(id = R.dimen.thickness_divider)
                )
            }
        }
        Row (
            modifier = Modifier
                .weight(1f, false)
                .padding(dimensionResource(id = R.dimen.padding_medium))
        ){
            Column (
                verticalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.padding_small))
            ){
                OutlinedButton(
                    modifier = Modifier.fillMaxWidth(),
                    onClick = onBackButtonClicked
                ) {
                    Text(stringResource(id = R.string.back_button))
                }
            }
        }
    }
}
