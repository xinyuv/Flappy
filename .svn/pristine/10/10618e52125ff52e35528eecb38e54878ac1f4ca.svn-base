package com.xinyuli.flappy;

import android.media.MediaRecorder;

import java.io.IOException;

/**
 * Created by xinyuli on 11/04/2017.
 *
 * https://github.com/halibobo/SoundMeter.
 * https://developer.android.com/reference/android/media/MediaRecorder.html
 */

class Recorder {
    private MediaRecorder recorder;

    /**
     * Try to start the recorder and output the recorded file to null. (not saving files)
     * @return True if the process is successful, false otherwise.
     */
    boolean startRecording() {
        try {
            recorder = new MediaRecorder();

            recorder.setAudioSource(MediaRecorder.AudioSource.MIC);
            recorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
            recorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
            recorder.setOutputFile("/dev/null");        // no output file

            recorder.prepare();
            recorder.start();   // Recording is now started
            return true;
        } catch (IOException e) {
            recorder.reset();
            recorder.release();
            recorder = null;
            e.printStackTrace();
        } catch (IllegalStateException e) {
            stopRecording();
            e.printStackTrace();
        }
        return false;
    }

    /**
     * Dispose the recorder.
     */
    void stopRecording() {
        if (recorder != null) {
            recorder.stop();
            recorder.reset();   // You can reuse the object by going back to setAudioSource() step
            recorder.release(); // Now the object cannot be reused
        }
    }

    /**
     * @return Current amplitude.
     */
    int getMaxAmplitude() {
        if (recorder != null) {
            return recorder.getMaxAmplitude();
        } else {
            return 0;
        }
    }

}
