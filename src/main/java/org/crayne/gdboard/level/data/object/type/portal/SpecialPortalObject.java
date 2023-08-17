package org.crayne.gdboard.level.data.object.type.general;

public class SpecialPortalObject extends PortalObject {

    private boolean specialPropertyChecked; // 13, special property of gamemode portals that have bounds (ball, ship, wave, spider, ufo)
                                            // to preview floor and ceiling, and speed portals to disable the music preview line from changing its speed

    public SpecialPortalObject(final boolean specialPropertyChecked) {
        this.specialPropertyChecked = specialPropertyChecked;
    }

    public SpecialPortalObject() {
        this.specialPropertyChecked = false;
    }

    public boolean specialPropertyChecked() {
        return specialPropertyChecked;
    }

    public void specialPropertyChecked(final boolean specialPropertyChecked) {
        this.specialPropertyChecked = specialPropertyChecked;
    }

}
