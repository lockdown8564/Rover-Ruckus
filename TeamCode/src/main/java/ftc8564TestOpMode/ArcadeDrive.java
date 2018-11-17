package ftc8564TestOpMode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

import static java.lang.Math.abs;

@Disabled
@TeleOp(name = "Arcade Drive", group = "ArcadeTankDrive")
public class ArcadeDrive extends OpMode {
    private ElapsedTime runtime = new ElapsedTime();

    private DcMotor left;
    private DcMotor right;

    private String mode = "Tank";

    private double scalePower(double dVal) {
        return (Math.signum(dVal) * ((Math.pow(dVal, 2) * (.9)) + .1));
    }


    @Override
    public void init() {
        left = hardwareMap.dcMotor.get("left");
        right = hardwareMap.dcMotor.get("right");
    }

    @Override
    public void start() {
        runtime.reset();
    }

    @Override
    public void loop() {

        /*
        if ((abs(gamepad1.left_stick_y) > .2) && (abs(gamepad1.right_stick_x) > .2)){
            if (gamepad1.right_stick_x > .2){
                left.setPower(gamepad1.left_stick_y);
                right.setPower(gamepad1.right_stick_x/2);
            }
            if (gamepad1.right_stick_x < -.2){
                left.setPower(gamepad1.right_stick_x/2);
                right.setPower(gamepad1.left_stick_y);
            }
        }
        else if ((abs(gamepad1.left_stick_y) > .2) && (abs(gamepad1.right_stick_x) < .2)) {
            left.setPower(gamepad1.left_stick_y);
            right.setPower(-gamepad1.left_stick_y);
        }
        else if ((abs(gamepad1.left_stick_y) < .2) && (abs(gamepad1.right_stick_x) > .2)) {
            right.setPower(gamepad1.right_stick_x);
            left.setPower(gamepad1.right_stick_x);
        }
        else {
            left.setPower(0);
            right.setPower(0);
        }
        */

        if ((abs(gamepad1.left_stick_y) > .2) ||  (abs(gamepad1.right_stick_x) > .2)){
            //left.setPower(gamepad1.right_stick_x - gamepad1.left_stick_y);
            //right.setPower(gamepad1.right_stick_x + gamepad1.left_stick_y);
            left.setPower(gamepad1.right_stick_x +   gamepad1.left_stick_y);
            right.setPower(gamepad1.left_stick_y - gamepad1.right_stick_x);
        }
        else {
            left.setPower(0);
            right.setPower(0);
        }


        telemetry.addData("Status", "Run Time: " + runtime.toString());
        telemetry.update();
    }

}
