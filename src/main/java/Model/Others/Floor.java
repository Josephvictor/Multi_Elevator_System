package Model.Others;

import Model.Panel.HallPanel;

import java.util.List;

public class Floor {
    private int floorNumber;
    private List<HallPanel> hallPanelList;
    private List<Display>   displayList;

    public boolean isTop(){
        return false;
    }

    public boolean isBottom(){
        return false;
    }
}
