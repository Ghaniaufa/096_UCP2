package com.example.a096_ucp2.ui.theme

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.selection.selectable
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.a096_ucp2.R
import com.example.a096_ucp2.data.SumberDataDosen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HalamanSatu(
    onSubmitButtonClick: (MutableList<String>) -> Unit,
    pilihanDosen1: List<String>,
    onSelectionChanged: (String) -> Unit,
    modifier: Modifier
){
    var dosenYgDipilih1 by remember{ mutableStateOf("")}

    var namaTxt by rememberSaveable {
        mutableStateOf("")
    }
    var nimTxt by rememberSaveable {
        mutableStateOf("")
    }
    var konsenstrasiTxt by rememberSaveable {
        mutableStateOf("")
    }
    var jkTxt by remember{
       mutableStateOf("")
    }
    var listDataTxt: MutableList<String> = mutableListOf(namaTxt,nimTxt,konsenstrasiTxt,jkTxt)


    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .padding(16.dp)
            .fillMaxSize()
    ){
        OutlinedTextField(value = namaTxt, onValueChange = {
            namaTxt = it
        }, label = {
            Text(text = "Nama")
        })
        OutlinedTextField(value = nimTxt, onValueChange = {
            nimTxt = it
        }, label = {
            Text(text = "NIM")
        })
        OutlinedTextField(value = konsenstrasiTxt, onValueChange = {
            konsenstrasiTxt = it
        }, label = {
            Text(text = "Konsentrasi")
        })
        OutlinedTextField(value = jkTxt, onValueChange = {
            jkTxt = it
        }, label = {
            Text(text = "Judul Skripsi")
            Spacer(modifier = Modifier.padding(16.dp))
            Button(onClick = { onSubmitButtonClick(listDataTxt) }) {
                Text(text = stringResource(id = R.string.btn_submit))
            }
        })
        Column(
            modifier = Modifier.padding(dimensionResource(id = R.dimen.padding_medium))
        ) {
            pilihanDosen1.forEach { item ->
                Row(
                    modifier = Modifier.selectable(
                        selected = dosenYgDipilih1 == item,
                        onClick = {
                            dosenYgDipilih1 = item
                            onSelectionChanged(item)
                        }
                    ),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    RadioButton(
                        selected = dosenYgDipilih1 == item,
                        onClick = {
                            dosenYgDipilih1 = item
                            onSelectionChanged(item)
                        }
                    )
                    Text(item)
                }
            }
        }
    }
}
