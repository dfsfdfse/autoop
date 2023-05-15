package realcool.autoop.view.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import realcool.autoop.utils.loge
import realcool.autoop.view.state.State
import java.lang.reflect.Method
import kotlin.system.measureTimeMillis

abstract class BaseViewModel<STATE : State> : ViewModel() {
    /**
     * State to be exposed to the UI layer
     */
    protected abstract val muteState: MutableStateFlow<STATE>

    val state: StateFlow<STATE> get() = muteState.asStateFlow()

    fun setState(set: STATE.() -> Unit) {
        val newState = copyData()
        muteState.update {
            newState.set()
            newState
        }
    }

    private fun copyData(): STATE {
        val java = state.value::class.java
        val instance = java.newInstance()
        val setMap = HashMap<String, Method>()
        val getMap = HashMap<String, Method>()
        java.declaredMethods.forEach {
            val getI = it.name.indexOf("get")
            val setI = it.name.indexOf("set")
            if (getI != -1) {
                getMap[it.name.substring(3)] = it
            } else if (setI != -1) {
                setMap[it.name.substring(3)] = it
            }
        }
        getMap.forEach {
            setMap[it.key]?.invoke(instance, it.value.invoke(state.value))
        }
        return instance
    }
}





