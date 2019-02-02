package ftc8564TestOpMode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

import static java.lang.Math.abs;

@TeleOp(name = "ServoTest", group = "ServoTest")
public class ServoTest extends OpMode {
    private ElapsedTime runtime = new ElapsedTime();


    //private CRServo intake;
    private Servo hatch;
    //private Servo lock;

    private double scalePower(double dVal) {
        return (Math.signum(dVal) * ((Math.pow(dVal, 2) * (.9)) + .1));
    }


    @Override
    public void init() {
        hatch = hardwareMap.servo.get("hatch");
        //lock = hardwareMap.servo.get("lock");
        //intake = hardwareMap.crservo.get("intake");
        hatch.setPosition(1);
       //lock lock.setPosition(.4);
        //lock.setPosition(.4);

    }

    @Override
    public void start() {
        runtime.reset();
        hatch.setPosition(.75);
        //lock lock.setPosition(.4);
        //open lock.setPosition(.82);
        //easier lock.setPosition(.55);

    }

    @Override
    public void loop() {

        //telemetry.addData("lock" , lock.getPosition());

        telemetry.update();
    }

}
