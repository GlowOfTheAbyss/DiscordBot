package org.glow.storage;

import org.glow.item.Item;

import java.util.List;

public class InventoryManager {

    private static InventoryManager inventoryManager;

    private InventoryManager() {

    }

    public List<Item> getEquippedItems(Inventory inventory) {
        return List.of(inventory.getHead(), inventory.getBody(), inventory.getLegs(),
                inventory.getRightHand(), inventory.getLeftHand(), inventory.getNeck(),
                inventory.getFinger());
    }

    public int getArmor(Inventory inventory) {

        return inventory.getHead().getArmor() + inventory.getBody().getArmor()
                + inventory.getLegs().getArmor() + inventory.getLeftHand().getArmor();

    }

    public int getAttack(Inventory inventory) {

        return inventory.getRightHand().getAttack();

    }

    public Item findUnequippedItem(Inventory inventory, String itemName) {

        for (Item item : inventory.getBag()) {
            if (item.getName().equalsIgnoreCase(itemName)) {
                return item;
            }
        }

        throw new IllegalArgumentException("Предмет " + itemName + " не найден");

    }

    public static InventoryManager getInstance() {
        if (inventoryManager == null) {
            inventoryManager = new InventoryManager();
        }
        return inventoryManager;
    }
}
