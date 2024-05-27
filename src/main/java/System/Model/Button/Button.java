package System.Model.Button;

import lombok.Data;

public abstract class Button {
    private boolean status = false;

    protected void setStatus(boolean status){
        this.status = status;
    }

    protected boolean getStatus(){
        return status;
    }

    public abstract boolean isPressed();
}
