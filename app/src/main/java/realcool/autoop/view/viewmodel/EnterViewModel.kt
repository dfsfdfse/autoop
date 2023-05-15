package realcool.autoop.view.viewmodel

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import realcool.autoop.utils.loge
import realcool.autoop.view.state.EnterState
import kotlin.random.Random

class EnterViewModel() : BaseViewModel<EnterState>() {
    override val muteState: MutableStateFlow<EnterState> = MutableStateFlow(EnterState())
    fun inputText(text: String) {
        setState {
            inputText = text
        }
    }

    fun changeName() {
        setState {
            floatBtnText = "测试"
        }
    }

    fun changeOrigin() {
        setState {
            originBtnText = randomText((0..5).random())
        }
    }

    private fun randomText(random: Int): String {
        return listOf(
            "随机1",
            "随机2",
            "随机3",
            "随机4",
            "随机5",
            "随机6",
        )[random]
    }
}



