/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.utils;

import java.time.Duration;
import java.util.Vector;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.LockSupport;

/**
 * A new System of Schedule, called 2020-nSoS
 * 
 * <p>Involving {@link ScheduledTask}
 * 
 * <p>Inspired by Team 3476
 */
public class ScheduleService implements Runnable{
    Vector<ScheduledTask> tasks;
    ExecutorService taskExecutor;

    boolean isRunning;
    boolean paused;

    public ScheduleService(ExecutorService eService){
        tasks = new Vector<>();
        taskExecutor = eService;

        ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.execute(this);
        isRunning = true;
        paused = true;
    }

    @Override
    public void run(){
        long waitTime = Duration.ofMillis(5).toNanos();
        while(isRunning){
            if(!paused){
                for(ScheduledTask sTask : tasks){
                    if(sTask.isReady())
                        taskExecutor.submit(sTask);
                }
            }
            LockSupport.parkNanos(waitTime);
        }
    }

    public void add(ScheduledTask scheduledTask){
        tasks.add(scheduledTask);
    }

    public void pause(){
        paused = true;
    }

    public void play(){
        paused = false;
    }

    public void shutdown(){
        isRunning = false;
    }
}