import java.io.IOException;
import java.util.HashMap;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineEvent;
import javax.sound.sampled.LineListener;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
//音ファイル読み込み https://aidiary.hatenablog.com/entry/20061105/1275137770
public class SoundManager implements LineListener{
    private int maxClips;
    private HashMap<String, Clip> clipMap;
    private int counter = 0;
    

    SoundManager(){
        this(256);
    }
    SoundManager(int maxClips){
        this.maxClips = maxClips;
        clipMap = new HashMap<String, Clip>(maxClips);
    }

    public void load(String name, String filename) {
        if (counter == maxClips) {
            System.out.println("hash is filled");
            return;
        }

        try {
            AudioInputStream stream = AudioSystem.getAudioInputStream(getClass().getResource(filename));
            AudioFormat format = stream.getFormat();
            if ((format.getEncoding() == AudioFormat.Encoding.ULAW)
                    || (format.getEncoding() == AudioFormat.Encoding.ALAW)) {
                AudioFormat newFormat = new AudioFormat(
                        AudioFormat.Encoding.PCM_SIGNED,
                        format.getSampleRate(),
                        format.getSampleSizeInBits() * 2, format.getChannels(),
                        format.getFrameSize() * 2, format.getFrameRate(), true);
                stream = AudioSystem.getAudioInputStream(newFormat, stream);
                format = newFormat;
            }

            DataLine.Info info = new DataLine.Info(Clip.class, format);
            if (!AudioSystem.isLineSupported(info)) {
                System.out.println("SoundLoadError: "+filename + "does not exit");
                System.exit(0);
            }

            Clip clip = (Clip) AudioSystem.getLine(info);
            clip.addLineListener(this);
            clip.open(stream);
            clipMap.put(name, clip);
            stream.close();
        } catch (UnsupportedAudioFileException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        }
    }


    public void play(String name) {
        Clip clip = clipMap.get(name);
        //同じ音のときは途中で止めて再生する
        if (clip != null) {
            clip.stop();
            clip.setFramePosition(0); 
            clip.start();
        }
    }

    public void loop(String name) {
        Clip clip = clipMap.get(name);
        //同じ音のときは途中で止めて再生する
        if (clip != null) {
            clip.stop();
            clip.setFramePosition(0); 
            clip.loop(20);
        }
    }


    public void stop(String name){
        Clip clip = clipMap.get(name);
        if (clip != null) {
            clip.stop();
            clip.setFramePosition(0); 
            //clip.start();
        }
    }

    

    public void update(LineEvent event) {
        if (event.getType() == LineEvent.Type.STOP) {
            Clip clip = (Clip) event.getSource();
            clip.stop();
            clip.setFramePosition(0); 

        }
    }
}
