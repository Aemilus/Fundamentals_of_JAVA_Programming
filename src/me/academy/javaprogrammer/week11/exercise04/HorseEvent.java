package me.academy.javaprogrammer.week11.exercise04;

import java.util.EventObject;

final class HorseEvent extends EventObject {
    HorseEvent(HorsePanel source) {
        super(source);
    }

    @Override
    public HorsePanel getSource() {
        return (HorsePanel) super.getSource();
    }
}
