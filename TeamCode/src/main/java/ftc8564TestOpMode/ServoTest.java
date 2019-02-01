package ftc8564TestOpMode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

import static java.lang.Math.abs;

@TeleOp(name = "ServoTest", group = "ServoTest")
public class ServoTest extends OpMode {
    private ElapsedTime runtime = new ElapsedTime();


    private Servo sort1;
    private Servo sort2;

    private double scalePower(double dVal) {
        return (Math.signum(dVal) * ((Math.pow(dVal, 2) * (.9)) + .1));
    }


    @Override
    public void init() {
        sort1 = hardwareMap.servo.get("sort1");
        sort2 = hardwareMap.servo.get("sort2");
    }

    @Override
    public void start() {
        runtime.reset();
        sort1.setPosition(.1);
        sort2.setPosition(.9);
    }

    @Override
    public void loop() {


        telemetry.addData("sort1" , sort1.getPosition());
        telemetry.addData("sort2" , sort2.getPosition());

        telemetry.update();
    }

}
