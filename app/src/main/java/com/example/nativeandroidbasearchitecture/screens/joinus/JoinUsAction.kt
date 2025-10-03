package com.example.nativeandroidbasearchitecture.screens.joinus

import com.example.nativeandroidbasearchitecture.screens.base.ViewSingleAction

sealed class JoinUsAction : ViewSingleAction {
    object SubmitSuccess : JoinUsAction()
}