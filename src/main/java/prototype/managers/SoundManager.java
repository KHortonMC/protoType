package prototype.managers;

import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.util.Dictionary;
import java.util.Enumeration;
import java.util.Hashtable;

public class SoundManager {
    public enum AudioCues {
        TockTock
    }

    Dictionary<String, AudioClip> cues = new Hashtable<>();
    void preLoadAudio() {
        for (AudioCues cue : AudioCues.values()) {
            AudioClip clip = null;
            switch (cue) {
                case TockTock: clip = loadCue("/sounds/TockTock.wav"); break;
            }
            cues.put(cue.toString(), clip);
        }
    }

    static SoundManager instance;
    public static void initialize() { instance = new SoundManager(); }
    public static SoundManager getInstance() { return instance; }

    public SoundManager() {
        preLoadAudio();
    }

    private AudioClip loadCue(String filename) {
        return new AudioClip(getClass().getResource(filename).toExternalForm());
    }

    public void playSound(AudioCues cue) {
        AudioClip clip = cues.get(cue.toString());
        clip.play();
    }
}
