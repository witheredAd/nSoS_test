/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.utils;

import java.time.Duration;

/**
 * Designed for {@link ScheduleService}. Included in 2020-nSoS.
 */
public abstract class ScheduledTask implements Runnable{
    long period;
    boolean isReady;
    long lastTime;

    /**
     * @param1 periodMS: period time using ms
     */
    public ScheduledTask(long periodMS){
        period = Duration.ofMillis(periodMS).toNanos();
        isReady = true;
        lastTime = System.nanoTime();
    }

    @Override
    public void run(){
        if(!isReady()){
            System.out.println("Task " + this.getClass().getName() + " interrupted. IGNORED.");
            return ;
        }

        synchronized(this){
            lastTime = System.nanoTime();
            isReady = false;
        }
        update();
        synchronized(this){
            isReady = true;
        }
    }

    /**
     * This function will be called every period by .run()
     */
    public abstract void update();

    public boolean isReady(){
        return isReady && (System.nanoTime() - lastTime > period);
    }
}
