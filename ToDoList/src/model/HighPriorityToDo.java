/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 *
 * @author Bogdan
 */
public class HighPriorityToDo extends BaseToDo{
    private LocalDateTime deadline;
    private boolean isDeadlineValid=false;
    LocalDateTime timecurrent=LocalDateTime.now();

    public HighPriorityToDo(LocalDateTime deadline, String content) {
        super(content);
        this.deadline = deadline;
       if(deadline.isAfter(timecurrent)) this.isDeadlineValid=true;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 83 * hash + Objects.hashCode(this.deadline);
        hash = 83 * hash + (this.isDeadlineValid ? 1 : 0);
        return hash;
    }

    public LocalDateTime getDeadline() {
        return deadline;
    }

    public void setDeadline(LocalDateTime deadline) {
        this.deadline = deadline;
    }

    public boolean isIsDeadlineValid() {
        return isDeadlineValid;
    }

    public void setIsDeadlineValid(boolean isDeadlineValid) {
        this.isDeadlineValid = isDeadlineValid;
    }

    @Override
    public String toString() {
        return "HighPriorityToDo{" + "deadline=" + deadline + ", isDeadlineValid=" + isDeadlineValid + '}';
    }

   
    
    
    
}
