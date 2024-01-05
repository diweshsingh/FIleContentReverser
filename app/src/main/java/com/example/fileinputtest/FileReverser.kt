package com.example.fileinputtest

import android.content.Context
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.FileOutputStream
import java.io.IOException
import java.io.InputStream


/**
 * Created by Diwesh Singh on 05/01/24.
 * This class is responsible to read file contents and reverse it and write to a new file.
 */
class FileReverser {

    /**
     * This method will open a file from asset, will convert into streams, read it and reverse it contents
     * and then write it to a new file which will be in internal storage of android.
     * Coroutines is used to put the IO task in background thread and keep main thread light.
     */
    fun reverseStreams(
        context: Context,
        inputFileName: String,
        outputFileName: String
    ) {

        CoroutineScope(Dispatchers.IO).launch {
            try {
                val inputStream = context.assets.open(inputFileName)
                val outputStream = context.openFileOutput(outputFileName, Context.MODE_PRIVATE)
                reverseStreams(inputStream = inputStream, outputStream = outputStream)

            } catch (e: IOException) {
                // Handle file-related errors here
                e.printStackTrace()
                println("Error processing files: $e")
            }
        }


    }

    /**
     * This method will read the inputstream and write it to a new file.
     */
    fun reverseStreams(inputStream: InputStream, outputStream: FileOutputStream) {

        if (inputStream == null || outputStream == null)
            return

        inputStream.bufferedReader().useLines { lines ->
            val reversedContents = lines.joinToString("\n").reversed()
            outputStream.write(reversedContents.toByteArray())
            inputStream.close()
            outputStream.close()
        }


    }
}