package org.glow.item;

public class ItemManager {

    private static ItemManager itemManager;

    private ItemManager(){

    }

    public static ItemManager getInstance() {
        if (itemManager == null) {
            itemManager = new ItemManager();
        }
        return itemManager;
    }
}
