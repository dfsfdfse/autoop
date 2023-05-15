package realcool.autoop.view.state

data class EnterState(
    var floatBtnText: String = "悬浮窗",
    var originBtnText: String = "原图",
    var binaryBtnText: String = "二值化",
    var inputText: String = ""
) : State
