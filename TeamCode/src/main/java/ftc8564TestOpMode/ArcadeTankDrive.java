package ftc8564TestOpMode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

import static java.lang.Math.abs;

@TeleOp(name = "ArcadeTankDrive", group = "ArcadeTankDrive")
@Disabled
public class ArcadeTankDrive extends OpMode {
    private ElapsedTime runtime = new ElapsedTime();

    private DcMotor left;
    private DcMotor right;

    private DcMotor climb;
    private DcMotor extension;

    private CRServo intake;
    private Servo hatch;
    private Servo lock;

    private double slow = 1;

    boolean intakeon = false;
    boolean bpress = false;
    boolean lbpress = false;

    private double scalePower(double dVal) {
        return (Math.signum(dVal) * ((Math.pow(dVal, 2) * (.9)) + .1));
    }



    @Override
    public void init() {
        left = hardwareMap.dcMotor.get("left");
        right = hardwareMap.dcMotor.get("right");
        climb = hardwareMap.dcMotor.get("climb");
        climb.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        extension = hardwareMap.dcMotor.get("extension");

        hatch = hardwareMap.servo.get("hatch");
        lock = hardwareMap.servo.get("lock");

        intake = hardwareMap.crservo.get("intake");
    }

    @Override
    public void start() {
        runtime.reset();
    }

    @Override
    public void loop() {

        //driver one
        if ((abs(gamepad1.left_stick_y) > .2) ||  (abs(gamepad1.right_stick_x) > .2)){
            left.setPower(scalePower((gamepad1.right_stick_x + gamepad1.left_stick_y) * slow));
            right.setPower(scalePower((gamepad1.right_stick_x - gamepad1.left_stick_y)* slow));
        }
        else {
            left.setPower(0);
            right.setPower(0);
        }

        if (gamepad1.x){
            slow = 1;
        }

        if (gamepad1.y){
            slow = .5;
        }


        if (slow == 1){
            telemetry.addData("Status", "SlowMode: " + false);
        }
        if (slow == .5){
            telemetry.addData("Status", "SlowMode: " + true);
        }

        //driver 2
        if (abs(gamepad2.left_stick_y) > .2){
            extension.setPower(scalePower(-gamepad2.left_stick_y));
        }
        else{
            extension.setPower(0);
        }

        if (abs(gamepad2.right_stick_y) > .2){
            climb.setPower(scalePower(-gamepad2.right_stick_y));
        }
        else{
            climb.setPower(0);
        }

        if (gamepad2.y && !intakeon) {
            if (intake.getPower() == 0) {
                intake.setPower(1);
            }
            else {
                intake.setPower(0);
            }
            intakeon = true;
        }
        else if (!gamepad2.y) {
            intakeon = false;
        }

        if (gamepad2.x){
            intake.setPower(-1);
        }
        if (!gamepad2.x && !(intake.getPower() == 1)) {
            intake.setPower(0);
        }

        if (gamepad2.b && !bpress) {
            if (hatch.getPosition() < .9) {
                hatch.setPosition(.98);
            }
            else {
                hatch.setPosition(.75);
            }
            bpress = true;
        }
        else if (!gamepad2.b) {
            bpress = false;
        }

        if (gamepad2.left_bumper && !lbpress) {
            if (lock.getPosition() < .5) {
                lock.setPosition(.525);
            }
            else {
                lock.setPosition(.4);
            }
            lbpress = true;
        }
        else if (!gamepad2.left_bumper) {
            lbpress = false;
        }

        /*
        if (gamepad2.right_bumper){
            climb.setPower(1);
        }
        if (abs(gamepad2.right_trigger) > .2){
            climb.setPower(-1);
        }
        else{
            climb.setPower(0);
        }
        */






        telemetry.addData("Status", "Run Time: " + runtime.toString());
        telemetry.addData("Changes: ", slow);

        telemetry.update();
    }

}
