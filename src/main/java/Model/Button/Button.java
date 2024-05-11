package Model.Button;

import lombok.Data;

public abstract class Button {
    private boolean status;

    public abstract boolean isPressed();
}
