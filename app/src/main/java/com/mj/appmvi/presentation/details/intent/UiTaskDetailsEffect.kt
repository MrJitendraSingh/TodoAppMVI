package com.mj.appmvi.presentation.details.intent

sealed class UiTaskDetailsEffect {
    object NavigateBack : UiTaskDetailsEffect()
    object ShowError : UiTaskDetailsEffect()
    object SaveSuccess : UiTaskDetailsEffect()
}