package com.taetae98.rotatemaster.protocol

import com.taetae98.rotatemaster.R

sealed class RotateItem(
    open val icon: Int,
    open val description: Int,
    open val contentDescription: Int,
    open val action: () -> Unit
) {
    open class Portrait(
        override val action: () -> Unit
    ) : RotateItem(
        R.drawable.ic_round_screen_lock_portrait_24, R.string.portrait, R.string.portrait, action
    )

    open class Landscape(
        override val action: () -> Unit
    ) : RotateItem(
        R.drawable.ic_round_screen_lock_landscape_24, R.string.landscape, R.string.landscape, action
    )

    open class ReversePortrait(
        override val action: () -> Unit
    ) : RotateItem(
        R.drawable.ic_round_screen_lock_portrait_24, R.string.reverse_portrait, R.string.reverse_portrait, action
    )

    open class ReverseLandscape(
        override val action: () -> Unit
    ) : RotateItem(
        R.drawable.ic_round_screen_lock_landscape_24, R.string.reverse_landscape, R.string.reverse_landscape, action
    )

    open class RotateLeft(
        override val action: () -> Unit
    ) : RotateItem(
        R.drawable.ic_round_rotate_left_24, R.string.rotate_left, R.string.rotate_left, action
    )

    open class RotateRight(
        override val action: () -> Unit
    ) : RotateItem(
        R.drawable.ic_round_rotate_right_24, R.string.rotate_right, R.string.rotate_right, action
    )
}