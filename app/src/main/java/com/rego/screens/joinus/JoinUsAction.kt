package com.rego.screens.joinus

import com.rego.screens.base.ViewSingleAction

sealed class JoinUsAction : ViewSingleAction {
    object SubmitSuccess : JoinUsAction()
}