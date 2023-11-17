package com.example.a096_ucp2.ui.theme

import androidx.lifecycle.VIEW_MODEL_STORE_OWNER_KEY
import androidx.lifecycle.ViewModel
import com.example.a096_ucp2.data.FormUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class FormViewModel: ViewModel() {
    private val _stateUI = MutableStateFlow(FormUiState())
    val stateUI: StateFlow<FormUiState> = _stateUI.asStateFlow()


    fun setDosen1(dosenPilihan1: String){
        _stateUI.update { stateSaatIni ->
            stateSaatIni.copy(dp1 = dosenPilihan1)
        }
    }
    fun resetForm(){
        _stateUI.value = FormUiState()
    }
    fun setContact(listData: MutableList<String>) {
        _stateUI.update { stateSaatIni ->
            stateSaatIni.copy(
                nama = listData[0],
                nim = listData[1],
                konsentrasi = listData[2],
                jk = listData[3]
            )
        }
    }
}