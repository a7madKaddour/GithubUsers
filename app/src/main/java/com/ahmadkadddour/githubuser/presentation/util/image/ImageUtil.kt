package com.ahmadkadddour.githubuser.presentation.util.image

import android.content.Context
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.util.Base64
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.io.ByteArrayOutputStream
import java.io.File

private const val PICTURES_FOLDER = "images"

interface OnLoadBitmap {
    fun onSuccess(bitmap: Bitmap?)
}

fun loadPictureAsBitmap(context: Context, url: String, onLoadBitmap: OnLoadBitmap) {
    Glide.with(context).asBitmap()
        .load(url)
        .addListener(object : RequestListener<Bitmap?> {
            override fun onLoadFailed(
                e: GlideException?,
                model: Any,
                target: Target<Bitmap?>,
                isFirstResource: Boolean
            ): Boolean {
                onLoadBitmap.onSuccess(null)
                return true
            }

            override fun onResourceReady(
                resource: Bitmap?,
                model: Any,
                target: Target<Bitmap?>,
                dataSource: DataSource,
                isFirstResource: Boolean
            ): Boolean {
                onLoadBitmap.onSuccess(resource)
                return true
            }
        }).submit()
}

private fun getRequestBuilder(
    imageView: ImageView,
    url: String,
    placeHolderDrawable: Drawable?,
    cache: Boolean
) = Glide.with(imageView.context)
    .load(url)
    .apply {
        placeholder(placeHolderDrawable)
        diskCacheStrategy(if (cache) DiskCacheStrategy.ALL else DiskCacheStrategy.NONE)
    }

/**
 * This function just will load picture from url
 */
fun loadPicture(imageView: ImageView, url: String?, placeHolder: Drawable?, cache: Boolean) {
    if (url.isNullOrEmpty()) {
        loadPlaceHolder(imageView, placeHolder)
        return
    }
    getRequestBuilder(imageView, url, placeHolder, cache).into(imageView)
}


fun loadPictureAndCache(imageView: ImageView, url: String?, placeHolder: Drawable?) {
    loadPicture(imageView, url, placeHolder, true)
}

fun loadPictureAndCache(imageView: ImageView, url: String?) {
    loadPictureAndCache(imageView, url, null)
}

private fun loadPlaceHolder(imageView: ImageView, placeHolder: Drawable?) {
    if (placeHolder == null) imageView.setImageDrawable(null)
    else imageView.setImageDrawable(placeHolder)
}


fun loadPictureFromFile(imageView: ImageView, file: File?) {
    Glide.with(imageView.context)
        .load(file)
        .into(imageView)
}

/**
 * @param url : is remote url
 *
 *
 * This function will load the picture from network :
 * if success : will save the picture in local storage
 * if failed : will try to load picture from local storage
 */
fun loadPictureAndStore(context: Context, imageView: ImageView, url: String) {
    if (url.isEmpty()) return
    Glide.with(context)
        .load(url)
        .listener(object : RequestListener<Drawable?> {
            override fun onLoadFailed(
                e: GlideException?,
                model: Any,
                target: Target<Drawable?>,
                isFirstResource: Boolean
            ): Boolean {
                val picturePath = getPicturesFolder(context) + "/" + getFileNameFromUrl(url)
                loadPictureFromFile(imageView, File(picturePath))
                return false
            }

            override fun onResourceReady(
                resource: Drawable?,
                model: Any,
                target: Target<Drawable?>,
                dataSource: DataSource,
                isFirstResource: Boolean
            ): Boolean {
                val savedPath = getPicturesFolder(context) + "/" + getFileNameFromUrl(url)
                imageView.toByteArray()?.let { savePictureOnStorage(it, savedPath) }
                return false
            }
        })
        .into(imageView)
}

fun savePictureOnStorage(imageBytes: ByteArray, fullPath: String) =
    GlobalScope.launch(Dispatchers.IO) {
        val file = File(fullPath)
        try {
            with(file) {
                if (exists()) delete()
                createNewFile()
                writeBytes(imageBytes)
            }
            println("file saved in $fullPath")
        } catch (e: Exception) {
            e.printStackTrace()
            System.err.println("error when save the file in = $fullPath")
        }
    }

fun loadPictureFromFile(imageView: ImageView, filePath: String) {
    loadPictureFromFile(imageView, File(filePath))
}

fun getPicturesFolder(context: Context): String {
    val file =
        File(context.filesDir.absolutePath + "/" + PICTURES_FOLDER)
    file.mkdirs()
    return file.absolutePath
}

fun getFileNameFromUrl(url: String): String {
    val array = url.split("/").toTypedArray()
    return array[array.size - 1]
}

fun ImageView.toByteArray(): ByteArray? {
    return (drawable as? BitmapDrawable)?.bitmap?.let {
        val stream = ByteArrayOutputStream()
        it.compress(Bitmap.CompressFormat.PNG, 100, stream)
        stream.toByteArray()
    }
}

fun Bitmap.toByteStream(): ByteArray {
    val stream = ByteArrayOutputStream()
    compress(Bitmap.CompressFormat.JPEG, 70, stream)
    return Base64.encode(stream.toByteArray(), Base64.NO_WRAP)
}