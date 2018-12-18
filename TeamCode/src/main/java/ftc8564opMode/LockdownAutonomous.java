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
    private Starting_Position hanging = Starting_Position.GROUND;

    @Override
    public void runOpMode() throws InterruptedException{
        robot = new Robot(this,true);

        //servo,setposition lock x 2

        waitForStart();

        if (hanging == Starting_Position.HANGING){
            /*
            lift down
            servo move
            wait 1 second
            lift up
            wait 3 second
            servo move
            lift down
             */
        }

        //get minerals

        if (alliance == Alliance_Position.BLUE_CRATER){
            robot.hang.climb(1);
            HalUtil.sleep(5000);
            robot.hang.stop();
            robot.driveBase.spinPID(180);
            HalUtil.sleep(1000);
            robot.driveBase.spinPID(30);
            robot.driveBase.drivePID(-36, false);
            robot.goldAlign.enable();
            if (robot.goldAlign.getAligned()) {
                robot.driveBase.drivePID(-15, false);
                robot.driveBase.drivePID(15, false);
            }
            else {
                robot.driveBase.spinPID(90);
                if (robot.goldAlign.getAligned()) {
                    robot.driveBase.drivePID(-15, false);
                    robot.driveBase.drivePID(15, false);
                    robot.driveBase.spinPID(-45);
                }
                robot.driveBase.spinPID(-90);
                if (robot.goldAlign.getAligned()) {
                    robot.driveBase.drivePID(-15, false);
                    robot.driveBase.drivePID(15, false);
                    robot.driveBase.spinPID(45);
                }
            }
            robot.goldAlign.stop();
            /*
            robot.driveBase.spinPID(90);
            robot.driveBase.drivePID(40,false);
            robot.driveBase.spinPID(-45);
            robot.driveBase.drivePID(50,false);
            //drop the thing
            robot.driveBase.spinPID(180);
            robot.driveBase.drivePID(100,false);

            */
        }

        else if (alliance == Alliance_Position.BLUE_DEPOT){

        }
        else if (alliance == Alliance_Position.RED_CRATER){
            robot.driveBase.drivePID(-36, false);
            robot.goldAlign.enable();
            if (robot.goldAlign.getAligned()) {
                robot.driveBase.drivePID(-15, false);
                robot.driveBase.drivePID(15, false);
            }
            else {
                robot.driveBase.spinPID(90);
                if (robot.goldAlign.getAligned()) {
                    robot.driveBase.drivePID(-15, false);
                    robot.driveBase.drivePID(15, false);
                    robot.driveBase.spinPID(-45);
                }
                robot.driveBase.spinPID(-90);
                if (robot.goldAlign.getAligned()) {
                    robot.driveBase.drivePID(-15, false);
                    robot.driveBase.drivePID(15, false);
                    robot.driveBase.spinPID(45);
                }
            }
            robot.goldAlign.stop();
            robot.driveBase.spinPID(90);
            robot.driveBase.drivePID(40,false);
            robot.driveBase.spinPID(-45);
            robot.driveBase.drivePID(50,false);
            //drop the thing
            robot.driveBase.spinPID(180);
            robot.driveBase.drivePID(100,false);



        }
        else if (alliance == Alliance_Position.RED_DEPOT) {

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
