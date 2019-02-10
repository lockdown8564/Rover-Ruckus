/*
 * Lockdown Framework Library
 * Copyright (c) 2018 Lockdown Team 8564 (lockdown8564.weebly.com)
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package ftc8564opMode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import static java.lang.Math.abs;
import ftc8564lib.*;

@TeleOp(name = "LockdownTeleOp", group = "ArcadeTankDrive")
//@Disabled

public class LockdownTeleOp extends LinearOpMode {

    private Robot robot;
    private boolean invertDrive = false;
    boolean intakeon = false;
    boolean ipress = false;
    boolean lbpress = false;
    boolean bpress = false;

    public void runOpMode() throws InterruptedException {
        robot = new Robot(this, false);
        robot.driveBase.noEncoders();
        robot.driveBase.slowSpeed(false);
        waitForStart();

        while (opModeIsActive()) {
            //driver 1
            if (gamepad1.x) {
                robot.driveBase.slowSpeed(true);
            }
            else if (gamepad1.y) {
                robot.driveBase.slowSpeed(false);
            }
            if (gamepad1.b && !ipress) {
                if (invertDrive) {
                    invertDrive = false;
                }
                else {
                    invertDrive = false;
                }
                ipress = true;
            }
            else if (!gamepad1.b) {
                ipress = false;
            }
            if (!invertDrive){
                robot.driveBase.arcadeDrive(gamepad1.left_stick_y, gamepad1.right_stick_y);
            }
            else if (invertDrive){
                robot.driveBase.arcadeDrive(-gamepad1.left_stick_y, -gamepad1.right_stick_y);
            }

            //driver 2
            robot.extension.power(gamepad2.left_stick_y);
            robot.hang.power(gamepad2.right_stick_y);

            //intake
            if (gamepad2.y && !intakeon) {
                if (robot.intake.getPower() == 0) {
                    robot.intake.in();
                }
                else {
                    robot.intake.power(0);
                }
                intakeon = true;
            }
            else if (!gamepad2.y) {
                intakeon = false;
            }
            if (gamepad2.x){
                robot.intake.out();
            }
            if (!gamepad2.x && !(robot.intake.getPower() == 1)) {
                robot.intake.power(0);
            }

            //servos
            if (gamepad2.b && !bpress) {
                if (robot.lock.hatch.getPosition() < .9) {
                    robot.lock.hatch.setPosition(.98);
                }
                else {
                    robot.lock.hatch.setPosition(.75);
                }
                bpress = true;
            }
            else if (!gamepad2.b) {
                bpress = false;
            }

            if (gamepad2.left_bumper && !lbpress) {
                if (robot.lock.lock.getPosition() < .5) {
                    robot.lock.open();
                }
                else {
                    robot.lock.close();
                }
                lbpress = true;
            }
            else if (!gamepad2.left_bumper) {
                lbpress = false;
            }





        }

    }
}
