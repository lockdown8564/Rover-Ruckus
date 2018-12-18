package ftc8564opMode;

import com.disnodeteam.dogecv.detectors.roverrukus.GoldAlignDetector;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

import ftclib.*;
import ftc8564lib.*;
import hallib.HalUtil;

@Autonomous(name="LockdownAutonomous", group="Autonomous")
public class LockdownAutonomous extends LinearOpMode implements FtcMenu.MenuButtons {

    private Robot robot;
    private ElapsedTime mClock = new ElapsedTime();

    public enum Alliance_Position {
        BLUE_CRATER,
        BLUE_DEPOT,
        RED_CRATER,
        RED_DEPOT
    }

    public enum Starting_Position {
        HANGING,
        GROUND
    }

    private Alliance_Position alliance = Alliance_Position.BLUE_CRATER;
    private Starting_Position hanging = Starting_Position.HANGING;

    @Override
    public void runOpMode() throws InterruptedException{
        robot = new Robot(this,true);

        waitForStart();

        if (hanging == Starting_Position.HANGING){
          robot.hang.drop();
          robot.driveBase.drivePID(3,false);
          robot.driveBase.spinPID(180);
        }

        //get minerals

        robot.goldAlign.enable();
        HalUtil.sleep(1000);
        if (robot.goldAlign.getAligned()) {
            robot.driveBase.drivePID(-36, false);
        }
        else {
            robot.driveBase.spinPID(30);
            HalUtil.sleep(1000);
            if (robot.goldAlign.getAligned()) {
                robot.driveBase.drivePID(-36, false);
                robot.driveBase.spinPID(-30);
            }
            else {
                robot.driveBase.spinPID(-65);
                HalUtil.sleep(1000);
                if (robot.goldAlign.getAligned()) {
                    robot.driveBase.drivePID(-36, false);
                    robot.driveBase.spinPID(35);
                }
            }
        }
        robot.goldAlign.stop();

        if (alliance == Alliance_Position.BLUE_CRATER) {
            robot.driveBase.drivePID(30, false);
        }
        else if (alliance == Alliance_Position.BLUE_DEPOT){
            robot.driveBase.drivePID(20, false);
            robot.intake.dropMarker();

        }
        else if (alliance == Alliance_Position.RED_CRATER){
            robot.driveBase.drivePID(30, false);
        }
        else if (alliance == Alliance_Position.RED_DEPOT) {
            robot.driveBase.drivePID(20, false);
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

        alliancePositionMenu.addChoice("BLUE CRATER", LockdownAutonomous.Alliance_Position.BLUE_CRATER);
        alliancePositionMenu.addChoice("BLUE DEPOT", LockdownAutonomous.Alliance_Position.BLUE_DEPOT);
        alliancePositionMenu.addChoice("RED CRATER", LockdownAutonomous.Alliance_Position.RED_CRATER);
        alliancePositionMenu.addChoice("RED DEPOT", LockdownAutonomous.Alliance_Position.RED_DEPOT);

        hangingMenu.addChoice("HANGING", LockdownAutonomous.Starting_Position.HANGING);
        hangingMenu.addChoice("GROUND", LockdownAutonomous.Starting_Position.GROUND);



        FtcMenu.walkMenuTree(alliancePositionMenu);
        alliance = (LockdownAutonomous.Alliance_Position) alliancePositionMenu.getCurrentChoiceObject();
    }


}
