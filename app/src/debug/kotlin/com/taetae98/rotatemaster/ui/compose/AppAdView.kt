package com.taetae98.rotatemaster.ui.compose

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdSize
import com.google.android.gms.ads.AdView

@Composable
fun AppAdView(
    modifier: Modifier = Modifier
) {
    AndroidView(
        modifier = modifier,
        factory = { context ->
            val request = AdRequest.Builder()
                .build()

            AdView(context).apply {
                adSize = AdSize.BANNER
                adUnitId = "ca-app-pub-3940256099942544/6300978111"
                loadAd(request)
            }
        }
    )
}