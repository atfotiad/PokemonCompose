package com.atfotiad.pokemoncompose.ui.pokemons

import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import android.os.Build.VERSION
import android.provider.MediaStore
import androidx.activity.compose.ManagedActivityResultLauncher
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts.PickVisualMedia
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import kotlinx.coroutines.launch

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun WhoisThatPokemon(
    onOpenCameraClick: () -> Unit = {}
) {

    var imageUri by remember {
        mutableStateOf(Uri.EMPTY)
    }
    val context = LocalContext.current
    val bitmapState = remember() {
        mutableStateOf<Bitmap?>(null)
    }

    val launcher = rememberLauncherForActivityResult(
        contract = PickVisualMedia()
    ) { uri ->
        imageUri = uri
        imageUri?.let {
            if (VERSION.SDK_INT < 28) {
                bitmapState.value = MediaStore.Images.Media.getBitmap(context.contentResolver, it)
            } else {
                val source = ImageDecoder.createSource(context.contentResolver, it)
                bitmapState.value = ImageDecoder.decodeBitmap(source)
            }

        }
    }

    Column(
        Modifier
            .padding(top = 16.dp)
            .fillMaxHeight(),
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        bitmapState.value?.let { bitmap ->
            if (VERSION.SDK_INT >= Build.VERSION_CODES.S) {
                GlideImage(
                    bitmap.asShared(),
                    contentDescription = null,
                    modifier = Modifier.size(400.dp)
                )
            } else {
                GlideImage(
                    bitmap.copy(Bitmap.Config.ARGB_8888, true),
                    contentDescription = null,
                    modifier = Modifier.size(400.dp)
                )
            }
        }
        Row(
            Modifier
                .fillMaxWidth()
                .padding(top = 16.dp),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            UploadButton(launcher)
            OpenCameraButton(onOpenCameraClick)
        }
    }

}


@Composable
fun UploadButton(launcher: ManagedActivityResultLauncher<PickVisualMediaRequest, Uri?>) {
    val coroutineScope = rememberCoroutineScope()
    ElevatedButton(onClick = {
        coroutineScope.launch {
            launcher.launch(PickVisualMediaRequest(PickVisualMedia.ImageOnly))
        }
    }, Modifier) {
        Text(text = "Upload")
    }
}

@Composable
fun OpenCameraButton(onOpenCameraClick: () -> Unit) {
    val coroutineScope = rememberCoroutineScope()
    ElevatedButton(onClick = {
        coroutineScope.launch {
            onOpenCameraClick()
        }
    }, Modifier) {
        Text(text = "Open Camera")
    }
}