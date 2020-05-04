/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Bogdan
 */
public class CustomRunnable implements Runnable {

    HighPriorityToDo toDo;
    LocalDateTime timecurrent = LocalDateTime.now();

    public CustomRunnable(HighPriorityToDo toDo) {
        this.toDo = toDo;
    }

    @Override
    public void run() {
        while (toDo.isIsDeadlineValid()) {
            try {
                TimeUnit.SECONDS.sleep(20);
            } catch (InterruptedException ex) {
                Logger.getLogger(CustomRunnable.class.getName()).log(Level.SEVERE, null, ex);
            }
            System.out.println("20 seconds have passed");
            if (toDo.getDeadline().isBefore(LocalDateTime.now())) {
                toDo.setIsDeadlineValid(false); System.out.println("Deadline for item '" + toDo.getContent() + "' has expired " );
                
            }

        }
    }

}
