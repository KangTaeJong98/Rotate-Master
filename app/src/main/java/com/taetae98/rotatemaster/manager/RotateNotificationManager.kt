package com.taetae98.rotatemaster.manager

import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.widget.RemoteViews
import androidx.core.app.NotificationChannelCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.taetae98.rotatemaster.R
import com.taetae98.rotatemaster.application.MainActivity
import com.taetae98.rotatemaster.receiver.RotateActionReceiver
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RotateNotificationManager @Inject constructor(
    @ApplicationContext
    private val context: Context
) {
    private val manager = NotificationManagerCompat.from(context)

    init {
        createNotificationChannel()
    }

    fun sendMessage() {
        manager.notify(
            context.resources.getInteger(R.integer.rotate_notification_id),
            NotificationCompat.Builder(
                context,
                context.getString(R.string.rotate_notification_channel_id)
            )
                .setSmallIcon(R.drawable.ic_round_screen_rotation_24)
                .setCustomContentView(createRemoteViews())
                .setAutoCancel(false)
                .setShowWhen(false)
                .setOngoing(true)
                .setContentIntent(createContentIntent())
                .build()
        )
    }

    fun cancelMessage() {
        manager.cancel(context.resources.getInteger(R.integer.rotate_notification_id))
    }

    private fun createNotificationChannel() {
        manager.createNotificationChannel(
            NotificationChannelCompat.Builder(
                context.getString(R.string.rotate_notification_channel_id),
                NotificationManagerCompat.IMPORTANCE_MAX
            ).apply {
                setName(context.getString(R.string.rotate_notification_channel_id))
            }.build()
        )
    }

    private fun createContentIntent(): PendingIntent {
        return PendingIntent.getActivity(
            context,
            CONTENT_INTENT_CODE,
            Intent(context, MainActivity::class.java).apply {
                addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            },
            PendingIntent.FLAG_IMMUTABLE
        )
    }

    private fun createRemoteViews(): RemoteViews {
        return RemoteViews(context.packageName, R.layout.rotate_notification_view).apply {
            setRotateButton()
            setRotateLockButton()
            setPortraitButton()
            setLandscapeButton()
            setReversePortraitButton()
            setReverseLandscapeButton()
            setRotateLeftButton()
            setRotateRightButton()
        }
    }

    private fun RemoteViews.setRotateButton() {
        setOnClickPendingIntent(
            R.id.rotate_button,
            PendingIntent.getBroadcast(
                context,
                ROTATE_INTENT_CODE,
                Intent(context, RotateActionReceiver::class.java).apply {
                    action = RotateActionReceiver.ROTATE_ACTION
                },
                PendingIntent.FLAG_IMMUTABLE
            )
        )
    }

    private fun RemoteViews.setRotateLockButton() {
        setOnClickPendingIntent(
            R.id.rotate_lock_button,
            PendingIntent.getBroadcast(
                context,
                ROTATE_LOCK_INTENT_CODE,
                Intent(context, RotateActionReceiver::class.java).apply {
                    action = RotateActionReceiver.ROTATE_LOCK_ACTION
                },
                PendingIntent.FLAG_IMMUTABLE
            )
        )
    }

    private fun RemoteViews.setPortraitButton() {
        setOnClickPendingIntent(
            R.id.portrait_button,
            PendingIntent.getBroadcast(
                context,
                PORTRAIT_INTENT_CODE,
                Intent(context, RotateActionReceiver::class.java).apply {
                    action = RotateActionReceiver.PORTRAIT_ACTION
                },
                PendingIntent.FLAG_IMMUTABLE
            )
        )
    }

    private fun RemoteViews.setLandscapeButton() {
        setOnClickPendingIntent(
            R.id.landscape_button,
            PendingIntent.getBroadcast(
                context,
                LANDSCAPE_INTENT_CODE,
                Intent(context, RotateActionReceiver::class.java).apply {
                    action = RotateActionReceiver.LANDSCAPE_ACTION
                },
                PendingIntent.FLAG_IMMUTABLE
            )
        )
    }

    private fun RemoteViews.setReversePortraitButton() {
        setOnClickPendingIntent(
            R.id.reverse_portrait_button,
            PendingIntent.getBroadcast(
                context,
                REVERSE_PORTRAIT_INTENT_CODE,
                Intent(context, RotateActionReceiver::class.java).apply {
                    action = RotateActionReceiver.REVERSE_PORTRAIT_ACTION
                },
                PendingIntent.FLAG_IMMUTABLE
            )
        )
    }

    private fun RemoteViews.setReverseLandscapeButton() {
        setOnClickPendingIntent(
            R.id.reverse_landscape_button,
            PendingIntent.getBroadcast(
                context,
                REVERSE_LANDSCAPE_INTENT_CODE,
                Intent(context, RotateActionReceiver::class.java).apply {
                    action = RotateActionReceiver.REVERSE_LANDSCAPE_ACTION
                },
                PendingIntent.FLAG_IMMUTABLE
            )
        )
    }

    private fun RemoteViews.setRotateLeftButton() {
        setOnClickPendingIntent(
            R.id.rotate_left_button,
            PendingIntent.getBroadcast(
                context,
                ROTATE_LEFT_INTENT_CODE,
                Intent(context, RotateActionReceiver::class.java).apply {
                    action = RotateActionReceiver.ROTATE_LEFT_ACTION
                },
                PendingIntent.FLAG_IMMUTABLE
            )
        )
    }

    private fun RemoteViews.setRotateRightButton() {
        setOnClickPendingIntent(
            R.id.rotate_right_button,
            PendingIntent.getBroadcast(
                context,
                ROTATE_RIGHT_INTENT_CODE,
                Intent(context, RotateActionReceiver::class.java).apply {
                    action = RotateActionReceiver.ROTATE_RIGHT_ACTION
                },
                PendingIntent.FLAG_IMMUTABLE
            )
        )
    }

    companion object {
        private const val CONTENT_INTENT_CODE = 1000
        private const val ROTATE_INTENT_CODE = 1001
        private const val ROTATE_LOCK_INTENT_CODE = 1002
        private const val PORTRAIT_INTENT_CODE = 1003
        private const val LANDSCAPE_INTENT_CODE = 1004
        private const val REVERSE_PORTRAIT_INTENT_CODE = 1005
        private const val REVERSE_LANDSCAPE_INTENT_CODE = 1006
        private const val ROTATE_LEFT_INTENT_CODE = 1007
        private const val ROTATE_RIGHT_INTENT_CODE = 1008
    }
}