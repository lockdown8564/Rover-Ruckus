package ftc8564opMode;

import com.disnodeteam.dogecv.detectors.roverrukus.GoldAlignDetector;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

import ftclib.*;
import ftc8564lib.*;
import hallib.HalUtil;

@Autonomous(name="LockdownAutonomous", group="Autonomous")
//@Disabled
public class LockdownAutonomous extends LinearOpMode implements FtcMenu.MenuButtons {

    private Robot robot;
    private ElapsedTime mClock = new ElapsedTime();

    public enum Position {
        CRATER,
        DEPOT,
    }

    public enum Starting_Position {
        HANGING,
        GROUND
    }

    private Position alliance = Position.CRATER;
    private Starting_Position hanging = Starting_Position.HANGING;

    @Override
    public void runOpMode() throws InterruptedException{
        robot = new Robot(this,true);

        waitForStart();

        if (hanging == Starting_Position.HANGING){
          robot.hang.drop();
          robot.driveBase.spinPID(180);
        }


        //get minerals
        robot.goldAlign.enable();
        HalUtil.sleep(2000);
        if (robot.goldAlign.getAligned()) {
            robot.driveBase.drivePID(-36, false);
            robot.driveBase.drivePID(36, false);
        }
        else {
            robot.driveBase.spinPID(30);
            HalUtil.sleep(2000);
            if (robot.goldAlign.getAligned()) {
                robot.driveBase.drivePID(-36, false);
                robot.driveBase.drivePID(36, false);
                robot.driveBase.spinPID(30);
            }
            else {
                robot.driveBase.spinPID(-60);
                HalUtil.sleep(2000);
                if (robot.goldAlign.getAligned()) {
                    robot.driveBase.drivePID(-36, false);
                    robot.driveBase.drivePID(36, false);
                    robot.driveBase.spinPID(60);
                }
            }
        }
        robot.goldAlign.stop();


        if (alliance == Position.CRATER) {
            robot.driveBase.drivePID(-40, false);
        }
        else if (alliance == Position.DEPOT) {
            robot.driveBase.drivePID(40, false);
            robot.intake.dropMarker();
        }
    }

    @Override
    public boolean isMenuUpButton() {
        return gamepad1.dpad_up;
    }

    @Override
    public boolean isMenuDownButton() {
        return gamepad1.dpad_down;
    }

    @Override
    public boolean isMenuEnterButton() {
        return gamepad1.dpad_right;
    }

    @Override
    public boolean isMenuBackButton() {
        return gamepad1.dpad_left;
    }

    private void doMenus() throws InterruptedException {
        FtcChoiceMenu alliancePositionMenu = new FtcChoiceMenu("Alliance Position:", null, this);
        FtcChoiceMenu hangingMenu = new FtcChoiceMenu("Alliance Position:", alliancePositionMenu, this);

        alliancePositionMenu.addChoice("CRATER", LockdownAutonomous.Position.CRATER);
        alliancePositionMenu.addChoice("DEPOT", LockdownAutonomous.Position.DEPOT);

        hangingMenu.addChoice("HANGING", LockdownAutonomous.Starting_Position.HANGING);
        hangingMenu.addChoice("GROUND", LockdownAutonomous.Starting_Position.GROUND);



        FtcMenu.walkMenuTree(alliancePositionMenu);
        alliance = (LockdownAutonomous.Position) alliancePositionMenu.getCurrentChoiceObject();
        hanging = (LockdownAutonomous.Starting_Position) hangingMenu.getCurrentChoiceObject();
    }


}
