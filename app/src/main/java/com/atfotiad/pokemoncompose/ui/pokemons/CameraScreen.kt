package com.atfotiad.pokemoncompose.ui.pokemons

import android.util.Log
import android.view.ViewGroup
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageCapture
import androidx.camera.core.ImageCaptureException
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.view.PreviewView
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.viewinterop.AndroidView
import java.io.File
import java.util.concurrent.Executors
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CameraScreen(

) {
    val cameraExecutor = Executors.newSingleThreadExecutor()
    val context = LocalContext.current
    val lifecycleOwner = LocalLifecycleOwner.current
    val state by remember { mutableStateOf(CameraState()) }
    val previewUseCase = remember { Preview.Builder().build() }

    LaunchedEffect(Unit) {
        val cameraProvider = suspendCoroutine { continuation ->
            ProcessCameraProvider.getInstance(context).apply {
                addListener({ continuation.resume(get()) }, cameraExecutor)
            }
        }
        try {
            cameraProvider.unbindAll()
            cameraProvider.bindToLifecycle(
                lifecycleOwner,
                CameraSelector.DEFAULT_BACK_CAMERA,
                previewUseCase,
                state.imageCapture
            )
        } catch (ex: Exception) {
            Log.e("CameraCapture", "Failed to bind camera use cases", ex)
        }
    }
    Scaffold { paddingValues ->
        AndroidView(modifier = Modifier
            .padding(paddingValues)
            .fillMaxSize(), factory = { context ->
            PreviewView(context).apply {
                layoutParams = ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT
                )
                previewUseCase.setSurfaceProvider(this.surfaceProvider)
            }

        })

    }
}



data class CameraState(
    val isTakingPicture: Boolean = false,
    val imageCapture: ImageCapture = ImageCapture.Builder()
        .setCaptureMode(ImageCapture.CAPTURE_MODE_MINIMIZE_LATENCY).build(),
    val imageFile: File? = null,
    val captureError: ImageCaptureException? = null
)