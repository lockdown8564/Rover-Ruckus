package ftc8564TestOpMode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

import static java.lang.Math.abs;

@TeleOp(name = "ArcadeTankDrive", group = "ArcadeTankDrive")
public class ArcadeTankDrive extends OpMode {
    private ElapsedTime runtime = new ElapsedTime();

    private DcMotor left;
    private DcMotor right;

    private DcMotor liftleft;
    private DcMotor liftright;

    private DcMotor pivot;
    private DcMotor intake;

    private DcMotor extension;

    private Servo one;
    private Servo two;

    private double slow = 1;
    private boolean intakeon = false;

   // private DcMotor lift1;
    //private DcMotor lift2;

   // private DcMotor intake1;
    //private DcMotor intake2;

    private String mode = "Tank";

    private double scalePower(double dVal) {
        return (Math.signum(dVal) * ((Math.pow(dVal, 2) * (.9)) + .1));
    }


    @Override
    public void init() {
        left = hardwareMap.dcMotor.get("left");
        right = hardwareMap.dcMotor.get("right");
        liftleft = hardwareMap.dcMotor.get("liftleft");
        liftright = hardwareMap.dcMotor.get("liftright");
        pivot = hardwareMap.dcMotor.get("pivot");
        intake = hardwareMap.dcMotor.get("intake");
        extension = hardwareMap.dcMotor.get("extension");
        one = hardwareMap.servo.get("one");
        two = hardwareMap.servo.get("two");
    }

    @Override
    public void start() {
        runtime.reset();
    }

    @Override
    public void loop() {

        //driver one

        if (mode == "Tank") {
            if (abs(gamepad1.left_stick_y) > .2) {
                left.setPower(-gamepad1.left_stick_y);
            } else {
                left.setPower(0);
            }

            if (abs(gamepad1.right_stick_y) > .2) {
                right.setPower(gamepad1.right_stick_y);
            } else {
                right.setPower(0);
            }
        }
        else {
            if ((abs(gamepad1.left_stick_y) > .2) ||  (abs(gamepad1.right_stick_x) > .2)){
                left.setPower(scalePower((gamepad1.right_stick_x + gamepad1.left_stick_y) * slow));
                right.setPower(scalePower((gamepad1.right_stick_x - gamepad1.left_stick_y)* slow));
            }
            else {
                left.setPower(0);
                right.setPower(0);
            }
        }

        if (gamepad1.x){
            if (slow == 1){
                slow = .5;
            }
            if (slow == .5){
                slow = 1;
            }
        }

        if (gamepad1.a){
            mode = "Arcade";
        }
        if (gamepad1.b){
            mode = "Tank";
        }

        if (slow == 1){
            telemetry.addData("Status", "SlowMode: " + false);
        }
        if (slow == .5){
            telemetry.addData("Status", "SlowMode: " + true);
        }

        //driver 2

        if (abs(gamepad2.right_stick_y) > .2){
            liftleft.setPower(scalePower(gamepad2.right_stick_y));
            liftright.setPower(scalePower(gamepad2.right_stick_y));
        }
        else{
            liftleft.setPower(0);
            liftright.setPower(0);
        }

        if (abs(gamepad2.left_stick_y) > .2){
            extension.setPower(scalePower((gamepad2.left_stick_y)));
        }
        else{
            extension.setPower(0);
        }

        if (gamepad2.left_bumper){
            pivot.setPower(1);
        }
        else if (gamepad2.left_trigger > .2){
            pivot.setPower(-1);
        }
        else{
            pivot.setPower(0);
        }

        if (gamepad2.dpad_left){
            one.setPosition(1);
            two.setPosition(1);
        }

        if (gamepad2.dpad_right){
            one.setPosition(-1);
            two.setPosition(-1);
        }


        if (gamepad2.right_bumper){
            intakeon = true;
            intake.setPower(-1);
        }
        if (gamepad2.right_trigger > .2){
            intakeon = false;
            intake.setPower(1);
        }
        else{
            if (!intakeon){
                intake.setPower(0);
            }
        }


      /*
      alright so we're gonna have two servos with a set pos at first

      POWER AT INIT

      pull lift down

      move servos out


       */





        telemetry.addData("Status", "Run Time: " + runtime.toString());
        telemetry.addData("Mode: ", mode);
        telemetry.update();
    }

}
