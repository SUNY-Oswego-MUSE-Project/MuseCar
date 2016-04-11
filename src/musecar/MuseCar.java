/*
 * Copyright (C) 2016 Christopher Wells <cwellsny@nycap.rr.com>
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package musecar;

import musegestures.MuseGestureServer;
import musegestures.MuseGestures;

/**
 * The <code>MuseCar</code> class is used to represent and control the rc-car.
 *
 * @author Christopher Wells <cwellsny@nycap.rr.com>
 */
public class MuseCar implements MuseGestures {

    private boolean moving;
    private int speed;
    private int idealSpeed;
    private boolean light;

    private int port;
    private MuseGestureServer server;

    /**
     * Initializes an object of the <code>MuseCar</code> class with the given
     * port.
     *
     * @param port The listening port of the MuseGestureServer.
     */
    public MuseCar(int port) {
        this.moving = false;
        this.speed = 0;
        this.idealSpeed = 0;
        this.light = true;

        this.port = port;
        this.server = new MuseGestureServer(this, port);
        this.server.start();
    }

    /**
     * Sets the speed of the car if it is moving.
     *
     * @param speed The new speed of the car.
     */
    public void setSpeed(int speed) {
        if (this.moving) {
            this.speed = speed;
            System.out.println("Speed: " + this.speed);

            // TODO: Implement car movement controls
            switch (speed) {
                case 0:
                    break;
                case 1:
                    break;
                case 2:
                    break;
                case 3:
                    break;
            }
        }
    }

    /**
     * Toggles the car between moving and not moving.
     */
    public void toggleMoving() {
        if (this.moving) {
            // Stop the car if it is moving
            this.setSpeed(0);
            this.moving = !this.moving;
        } else {
            // Start the car if it is not moving
            this.moving = !this.moving;
            this.setSpeed(this.idealSpeed);
        }

        System.out.println("Moving: " + this.moving);
    }

    /**
     * Toggles the light on the car on and off.
     */
    public void toggleLight() {
        this.light = !this.light;
        System.out.println("Light: " + this.light);

        // TODO: Implement car light controls
    }

    @Override
    public void onBlink() {
        this.toggleLight();
    }

    @Override
    public void onJawClench() {
        this.toggleMoving();
    }

    @Override
    public void onConcentrationChange(int state) {
        this.idealSpeed = state;

        if (this.moving) {
            this.setSpeed(state);
        }
    }
}
