package com.example.techsrcstudioc.TrackComp

import android.media.AudioManager
import android.media.MediaPlayer
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun customToastUI() {
    // on below line we are creating a variable for context
    val ctx = LocalContext.current
    val mediaPlayer = MediaPlayer()

    // on below line we are creating a column for our ui.
    Column(
        // in this column we are adding a modifier
        // for our column and specifying max width, height and size.
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .fillMaxSize()

            // on below line we are adding padding
            // from all sides to our column.
            .padding(6.dp),

        // on below line we are adding vertical arrangement
        // for our column as bottom
        verticalArrangement = Arrangement.Center,

        // on below line we are adding horizontal alignment
        // for our column.
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // on below line we are
        // displaying a simple text
        Text(

            // on below line we are specifying
            // modifier as padding for our text.
            modifier = Modifier.padding(6.dp),

            // on below line we are specifying
            // text as normal image.
            text = "Play Audio from URL",

            // on below line we are specifying
            // font weight as bold.
            fontWeight = FontWeight.Bold,

            // on below line we are
            // setting color for our text
            color = Color.Red,

            // on below line we are
            // setting font size.
            fontSize = 20.sp
        )

        // on below line we are creating a simple spacer
        // with height 20
        Spacer(modifier = Modifier.height(20.dp))

        // on below line we are creating a
        // button for displaying error toast
        Button(
            // on below line we are adding
            // width for our button and padding to it.
            modifier = Modifier
                .width(300.dp)
                .padding(7.dp),

            // in this button we are
            // adding on click for it on below line.
            onClick = {

                // on below line we are creating a variable for our audio url
                var audioUrl = "https://api.spotify.com/v1/tracks/5DufKwKSKB5Si5HcKYUaYb"

                // on below line we are setting audio stream type as
                // stream music on below line.
                mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC)

                // on below line we are running a try and catch block
                // for our media player.
                try {
                    // on below line we are setting audio source
                    // as audio url on below line.
                    mediaPlayer.setDataSource(audioUrl)

                    // on below line we are preparing
                    // our media player.
                    mediaPlayer.prepare()

                    // on below line we are starting
                    // our media player.
                    mediaPlayer.start()

                } catch (e: Exception) {

                    // on below line we are
                    // handling our exception.
                    e.printStackTrace()
                }

                // on below line we are displaying a toast message as audio player.
                Toast.makeText(ctx, "Audio started playing..", Toast.LENGTH_SHORT).show()

            }) {
            // on below line we are specifying
            // text for button.
            Text(text = "Play Audio")
        }

        // on below line we are creating a spacer of height 20
        Spacer(modifier = Modifier.height(20.dp))

        // on below line we are
        // creating a button for displaying a toast
        Button(
            // on below line we are
            // adding width for our button and padding to it.
            modifier = Modifier
                .width(300.dp)
                .padding(7.dp),

            // in this button we are adding
            // on click for it on below line.
            onClick = {
                // on below line we are checking
                // if media player is playing.
                if (mediaPlayer.isPlaying) {
                    // if media player is playing
                    // we are stopping it on below line.
                    mediaPlayer.stop()

                    // on below line we are resetting
                    // our media player.
                    mediaPlayer.reset()

                    // on below line we are calling release
                    // to release our media player.
                    mediaPlayer.release()

                    // on below line we are displaying a toast message to pause audio
                    Toast.makeText(ctx, "Audio has been  paused..", Toast.LENGTH_SHORT).show()
                } else {
                    // if audio player is not displaying we are displaying
                    // below toast message
                    Toast.makeText(ctx, "Audio not played..", Toast.LENGTH_SHORT).show()
                }


            }) {
            // on below line we are specifying text for button.
            Text(text = "Pause Audio")
        }


    }
}