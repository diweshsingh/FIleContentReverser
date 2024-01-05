package com.example.fileinputtest

import android.content.Context
import io.mockk.mockk
import io.mockk.verify
import org.junit.Test
import java.io.InputStream


/**
 * Created by Diwesh Singh on 05/01/24.
 *
 */
class FileReverserTest {

    @Test
    fun reverseFileContents_readsAndReversesContent() {
        //Arrange
        val inputText = "ABC"
        val fakeStream: InputStream = inputText.byteInputStream()

        val context = mockk<Context>(relaxed = true)
        val outputStream = context.openFileOutput("Output.txt", Context.MODE_PRIVATE)

        //Act
        val reverser = FileReverser()
        reverser.reverseStreams(fakeStream, outputStream) // Pass outputStream

        //Assert
        verify { (outputStream).write("CBA".toByteArray()) }
        verify { (outputStream).close() }

    }
}
