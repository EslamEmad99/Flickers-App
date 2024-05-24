package com.eslam.data.util

import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.MultipartBody.Part.Companion.createFormData
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import java.io.File

object MultiPartUtil {

    fun Any.toMultiPart() = this.toString().toRequestBody("text/plain".toMediaTypeOrNull())

    fun prepareImagePart(
        partName: String,
        path: String
    ): MultipartBody.Part {
        val file = File(path)
        val parse = "image/${file.extension}".toMediaTypeOrNull()
        val requestBody: RequestBody = file.asRequestBody(parse)
        return createFormData(partName, file.name, requestBody)
    }

    fun prepareVideoPart(
        partName: String,
        path: String
    ): MultipartBody.Part {
        val file = File(path)
        val parse = "video/${file.extension}".toMediaTypeOrNull()
        val requestBody: RequestBody = file.asRequestBody(parse)
        return createFormData(partName, file.name, requestBody)
    }

    fun prepareAudioPart(
        partName: String,
        path: String
    ): MultipartBody.Part {
        val file = File(path)
        val requestFile = file.asRequestBody("audio/${file.extension}".toMediaTypeOrNull())
        return createFormData(partName, file.name, requestFile)
    }

    fun prepareFilePart(
        partName: String,
        path: String
    ): MultipartBody.Part {
        val file = File(path)
        val parse = "file/${file.extension}".toMediaTypeOrNull()
        val requestBody: RequestBody = file.asRequestBody(parse)
        return createFormData(partName, file.name, requestBody)
    }
}