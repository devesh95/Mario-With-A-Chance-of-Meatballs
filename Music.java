import java.io.*;
import javax.sound.midi.*;

public class Music {
    boolean ingame=true;
   public static Sequencer midiPlayer;

   // testing main method
   public static void main(String[] args) {
      startMidi("src/sounds/menu.mid");     // start the midi player
      try {
         Thread.sleep(10000);   // delay
      } catch (InterruptedException e) { }
      // Do this on every move step, you could change to another song

      if (!midiPlayer.isRunning()) {  // previous song finished
         // reset midi player and start a new song
         midiPlayer.stop();
         midiPlayer.close();
         //startMidi("/Users/deveshdayal/NetBeansProjects/collision1/src/menu.mid");
         }
   }

   public static void startMidi(String midFilename) {
      try {
         File midiFile = new File(midFilename);
         Sequence song = MidiSystem.getSequence(midiFile);
         midiPlayer = MidiSystem.getSequencer();
         midiPlayer.open();
         midiPlayer.setSequence(song);
         midiPlayer.setLoopCount(Sequencer.LOOP_CONTINUOUSLY); //loop forever
         midiPlayer.start();
      } catch (MidiUnavailableException e) {
         e.printStackTrace();
      } catch (InvalidMidiDataException e) {
         e.printStackTrace();
      } catch (IOException e) {
         e.printStackTrace();
      }
   }
   public static void stopMidi(String midFilename) {
       try {
         File midiFile = new File(midFilename);
         Sequence song = MidiSystem.getSequence(midiFile);
         midiPlayer = MidiSystem.getSequencer();
         midiPlayer.stop();
         //midiPlayer.close();
      } catch (MidiUnavailableException e) {
         e.printStackTrace();
      } catch (InvalidMidiDataException e) {
         e.printStackTrace();
      } catch (IOException e) {
         e.printStackTrace();
      }
   }
}