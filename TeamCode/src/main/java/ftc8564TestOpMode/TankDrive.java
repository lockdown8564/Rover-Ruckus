package ftc8564TestOpMode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.ElapsedTime;

@TeleOp(name = "Tank Drive", group = "TeleOp")
public class TankDrive extends OpMode {
    private ElapsedTime runtime = new ElapsedTime();

    @Override
    public void init() {

    }

    @Override
    public void start() {
        runtime.reset();
    }

    @Override
    public void loop() {

        telemetry.addData("Status", "Run Time: " + runtime.toString());
        telemetry.update();
    }




}
