package com.banger.mobile.service;

import java.io.File;

import it.sauronsoftware.jave.AudioAttributes;
import it.sauronsoftware.jave.Encoder;
import it.sauronsoftware.jave.EncoderException;
import it.sauronsoftware.jave.EncodingAttributes;
import it.sauronsoftware.jave.InputFormatException;
import it.sauronsoftware.jave.VideoAttributes;

public class AudioConvertTest {

    /**
     * @param args
     * @throws EncoderException 
     * @throws InputFormatException 
     * @throws IllegalArgumentException 
     */
    public static void main(String[] args) throws Exception {
        
        AudioAttributes audio = new AudioAttributes();
        audio.setCodec("libmp3lame");
        audio.setBitRate(new Integer(56000));
        audio.setChannels(new Integer(1));
        audio.setSamplingRate(new Integer(22050));
        VideoAttributes video = new VideoAttributes();
        video.setCodec(VideoAttributes.DIRECT_STREAM_COPY);
        EncodingAttributes attrs = new EncodingAttributes();
        attrs.setFormat("wav");
        attrs.setAudioAttributes(audio);
        attrs.setVideoAttributes(video);
        Encoder encoder = new Encoder();
        
        String[] codecs = encoder.getAudioEncoders();
        for (String codec : codecs) {
            System.out.println(codec);
        }
        
        File source = new File("");
        File target = new File("");
        encoder.encode(source, target, attrs);
    }

}
