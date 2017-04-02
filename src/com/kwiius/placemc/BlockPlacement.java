package com.kwiius.placemc;

public class BlockPlacement {
    private static final byte[] colours = {
            0,
            8,
            7,
            15,
            6,
            14,
            1,
            12,
            4,
            5,
            13,
            3,
            9,
            11,
            2,
            10
    };

    int x;
    int y;
    byte blockType;
    boolean definitelyNew;

    public BlockPlacement(int x, int y, byte colour, boolean definitelyNew) {
        this.x = x;
        this.y = y;
        this.blockType = colours[colour];
        this.definitelyNew = definitelyNew;
    }
}
