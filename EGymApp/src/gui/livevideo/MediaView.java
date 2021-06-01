package gui.livevideo;

import gui.mainpage.Main;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.application.Platform;
import javafx.beans.InvalidationListener;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.text.Text;
import javafx.util.Duration;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * This class is used to show the detailed video playing interface.
 * @author Qingyu Xian
 */
public class MediaView implements Initializable {

    @FXML private Slider slider;
    @FXML private Slider volumeSlider;
    @FXML private javafx.scene.media.MediaView mediaView;
    @FXML private Label playTime;
    @FXML private FontAwesomeIconView icon;
    @FXML private Label title;
    @FXML private Text text;
    private Duration duration;
    private Media media = null;
    private MediaPlayer mediaPlayer = null;
    private final boolean repeat = false;
    private boolean stopRequested = false;
    private boolean atEndOfMedia = false;
    public int position;
    public void setPosition(int position){
        this.position=position;
    }

    /**
     * This method initializes the Mediaview class
     * @param location
     * @param resources
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    /**
     * This method provides the actual UI when entering Mediaview section
     * @return Nothing.
     * @throws JSONException
     */
    public void show() throws JSONException {
        JSONObject w = LiveVideo.input.getJSONObject(position);
        title.setText(w.getString("title"));
        text.setText(w.getString("description"));
        media = new Media(new File("src/gui/mediasrc/"+w.getString("videoname")).toURI().toString());

        mediaPlayer = new MediaPlayer(media);

        mediaView.setMediaPlayer(mediaPlayer);

        mediaPlayer.currentTimeProperty().addListener(new ChangeListener<Duration>() {
            @Override
            public void changed(ObservableValue<? extends Duration> observable, Duration oldValue, Duration newValue) {
                updateValues();
            }
        });
        mediaPlayer.setOnPlaying(new Runnable() {
            public void run() {

                if (stopRequested) {
                    mediaPlayer.pause();
                    stopRequested = false;
                } else {
                    icon.setGlyphName("PAUSE");
                }
            }
        });
        mediaPlayer.setOnPaused(new Runnable() {
            public void run() {

                icon.setGlyphName("PLAY");
            }
        });
        mediaPlayer.setOnReady(new Runnable() {
            public void run() {
                duration = mediaPlayer.getMedia().getDuration();
                mediaPlayer.getMedia().getDuration();
                updateValues();
            }
        });

        mediaPlayer.setCycleCount(repeat ? MediaPlayer.INDEFINITE : 1);
        mediaPlayer.setOnEndOfMedia(new Runnable() {
            public void run() {
                if (!repeat) {
                    icon.setGlyphName("PAUSE");
//                    playButton.setGraphic(imageViewPlay);
                    //playButton.setText(">");
                    stopRequested = true;
                    atEndOfMedia = true;
                }
            }
        });


        slider.valueProperty().addListener(new InvalidationListener() {
            @Override
            public void invalidated(javafx.beans.Observable observable) {
                if (slider.isValueChanging()) {
                    // multiply duration by percentage calculated by slider position
                    if(duration != null) {
                        mediaPlayer.seek(duration.multiply(slider.getValue() / 100.0));
                    }
                    updateValues();

                }
            }
        });

        volumeSlider.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                if (volumeSlider.isValueChanging()) {
                    mediaPlayer.setVolume(volumeSlider.getValue() / 100.0);
                }
            }
        });
    }

    /**
     * This method returns the UI to the LiveVideo page.
     * @return Nothing
     * @throws IOException
     * @throws JSONException
     */
    @FXML
    private void handleClickm() throws IOException, JSONException {
        Main.ctrl.body.setContent(control.global.ViewManager.getInstance().get("LiveVideoPage0"));
    }

    /**
     * This method controls the playtime and volume slider.
     * @return Nothing.
     */
    private void updateValues() {
        if (playTime != null && slider != null && volumeSlider != null && duration != null) {
            Platform.runLater(new Runnable() {
                public void run() {
                    Duration currentTime = mediaPlayer.getCurrentTime();
                    playTime.setText(formatTime(currentTime, duration));
                    slider.setDisable(duration.isUnknown());
                    if (!slider.isDisabled() && duration.greaterThan(Duration.ZERO) && !slider.isValueChanging()) {
                        slider.setValue(currentTime.divide(duration).toMillis() * 100.0);

//                        double du = duration.toMillis() * 100.0;
//                        Duration divide =  currentTime.divide(du);
//                        slider.setValue(divide.toMillis());
                    }
                    if (!volumeSlider.isValueChanging()) {
                        volumeSlider.setValue((int) Math.round(mediaPlayer.getVolume() * 100));
                    }
                }
            });
        }
    }

    /**
     * This method illustrates the current video time and total time.
     * @param elapsed
     * @param duration
     * @return The current time of the video.
     */
    private String formatTime(Duration elapsed, Duration duration) {
        int intElapsed = (int)Math.floor(elapsed.toSeconds());
        int elapsedHours = intElapsed / (60 * 60);
        if (elapsedHours > 0) {
            intElapsed -= elapsedHours * 60 * 60;
        }
        int elapsedMinutes = intElapsed / 60;
        int elapsedSeconds = intElapsed - elapsedHours * 60 * 60 - elapsedMinutes * 60;

        if (duration.greaterThan(Duration.ZERO)) {
            int intDuration = (int)Math.floor(duration.toSeconds());
            int durationHours = intDuration / (60 * 60);
            if (durationHours > 0) {
                intDuration -= durationHours * 60 * 60;
            }
            int durationMinutes = intDuration / 60;
            int durationSeconds = intDuration - durationHours * 60 * 60 - durationMinutes * 60;

            if (durationHours > 0) {
                return String.format("%d:%02d:%02d/%d:%02d:%02d",
                        elapsedHours, elapsedMinutes, elapsedSeconds,
                        durationHours, durationMinutes, durationSeconds);
            } else {
                return String.format("%02d:%02d/%02d:%02d",
                        elapsedMinutes, elapsedSeconds,
                        durationMinutes, durationSeconds);
            }
        } else {
            if (elapsedHours > 0) {
                return String.format("%d:%02d:%02d",
                        elapsedHours, elapsedMinutes, elapsedSeconds);
            } else {
                return String.format("%02d:%02d",
                        elapsedMinutes, elapsedSeconds);
            }
        }
    }

    /**
     * This method makes the player play.
     * @return Nothing.
     */
    @FXML
    private void play(){
            updateValues();
            MediaPlayer.Status status = mediaPlayer.getStatus();
            if (status == MediaPlayer.Status.UNKNOWN
                    || status == MediaPlayer.Status.HALTED)
            {
                // don't do anything in these states
                return;
            }

            if (status == MediaPlayer.Status.PAUSED
                    || status == MediaPlayer.Status.READY
                    || status == MediaPlayer.Status.STOPPED)
            {
                // rewind the movie if we're sitting at the end
                if (atEndOfMedia) {
                    mediaPlayer.seek(mediaPlayer.getStartTime());
                    atEndOfMedia = false;
                    icon.setGlyphName("PLAY");
//                    playButton.setGraphic(imageViewPlay);
                    //playButton.setText(">");
                    updateValues();
                }
                mediaPlayer.play();
                icon.setGlyphName("PAUSE");
//                playButton.setGraphic(imageViewPause);
                //playButton.setText("||");
            }
            else {
                mediaPlayer.pause();
            }
    }

    /**
     * This method makes the player stop.
     * @return Nothing.
     */
    @FXML
    private void pause(){
        mediaPlayer.pause();
    }
}