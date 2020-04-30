/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.time.LocalDateTime;

/**
 *
 * @author Bogdan
 */
public class HighPriorityToDo extends BaseToDo{
    LocalDateTime deadline;
    boolean isDeadlineValid=false;
    public HighPriorityToDo(String content) {
        super(content);
    }
    
}
