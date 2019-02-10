package ftc8564TestOpMode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

import static java.lang.Math.abs;

@TeleOp(name = "Outreach TeleOp", group = "Demo")
public class OutreachDemo extends OpMode {
    private ElapsedTime runtime = new ElapsedTime();

    private DcMotor left;
    private DcMotor right;

    private String mode = "Tank";
    private boolean drive = true;
    private double slow = 1;

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
    public void loop(){

        if (drive){
            if (mode == "Tank") {
                if (abs(gamepad1.left_stick_y) > .2) {
                    right.setPower(-gamepad1.left_stick_y * slow);
                } else {
                    right.setPower(0);
                }

                if (abs(gamepad1.right_stick_y) > .2) {
                    left.setPower(gamepad1.right_stick_y * slow);
                } else {
                    left.setPower(0);
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
        }

        if (!drive){
            if (mode == "Tank") {
                if (abs(gamepad2.left_stick_y) > .2) {
                    right.setPower(-gamepad2.left_stick_y * slow);
                } else {
                    right.setPower(0);
                }

                if (abs(gamepad2.right_stick_y) > .2) {
                    left.setPower(gamepad2.right_stick_y * slow);
                } else {
                    left.setPower(0);
                }
            }
            else {
                if ((abs(gamepad2.left_stick_y) > .2) ||  (abs(gamepad2.right_stick_x) > .2)){
                    left.setPower(scalePower((gamepad2.right_stick_x + gamepad2.left_stick_y) * slow));
                    right.setPower(scalePower((gamepad2.right_stick_x - gamepad2.left_stick_y)* slow));
                }
                else {
                    left.setPower(0);
                    right.setPower(0);
                }
            }
        }

        if (gamepad2.x){
            if (slow == 1) {
                slow = .5;
            }
        }
        if (gamepad2.y){
            if (slow == 1) {
                slow = .5;
            }
        }

        if (gamepad2.a){
            mode = "Arcade";
        }
        if (gamepad2.b){
            mode = "Tank";
        }

        if (gamepad2.left_trigger > .2){
            drive = true;
        }
        if (gamepad2.right_trigger > .2){
            drive = false;
        }

        telemetry.addData("Status", "Run Time: " + runtime.toString());
        telemetry.addData("Mode: ", mode);
        telemetry.addData("Drive: ", drive);

        telemetry.update();


    }

}
