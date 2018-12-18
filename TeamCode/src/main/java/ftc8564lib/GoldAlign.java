package ftc8564lib;

import com.disnodeteam.dogecv.CameraViewDisplay;
import com.disnodeteam.dogecv.DogeCV;
import com.disnodeteam.dogecv.detectors.roverrukus.GoldAlignDetector;
import com.disnodeteam.dogecv.detectors.roverrukus.SamplingOrderDetector;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.opencv.core.Core;
import org.opencv.core.Mat;

public class GoldAlign {

    public GoldAlignDetector detector;
    private LinearOpMode opMode;


    public GoldAlign(LinearOpMode opMode) {
        this.opMode = opMode;
        // Set up detector
        detector = new GoldAlignDetector(); // Create detector
        detector.init(opMode.hardwareMap.appContext, CameraViewDisplay.getInstance()); // Initialize it with the app context and camera
        detector.useDefaults(); // Set detector to use default settings

        // Optional tuning
        detector.alignSize = 300; // How wide (in pixels) is the range in which the gold object will be aligned. (Represented by green bars in the preview)
        detector.alignPosOffset = 0; // How far from center frame to offset this alignment zone.
        detector.downscale = 0.4; // How much to downscale the input frames

        detector.areaScoringMethod = DogeCV.AreaScoringMethod.MAX_AREA; // Can also be PERFECT_AREA
        //detector.perfectAreaScorer.perfectArea = 10000; // if using PERFECT_AREA scoring
        detector.maxAreaScorer.weight = 0.005; //

        detector.ratioScorer.weight = 5; //
        detector.ratioScorer.perfectRatio = 1.0; // Ratio adjustment
    }

    public void enable(){
        detector.enable();// Start the detector!
    }

    public boolean getAligned(){
        return detector.getAligned();
    }


    public double getXPosition(){
        return detector.getXPosition(); // Gold X position.
    }
    /*
    telemetry.addData("IsAligned" , detector.getAligned()); // Is the bot aligned with the gold mineral?
    telemetry.addData("X Pos" , detector.getXPosition()); // Gold X position.
    */

    public void stop() {
        // Disable the detector
        detector.disable();
    }



}
