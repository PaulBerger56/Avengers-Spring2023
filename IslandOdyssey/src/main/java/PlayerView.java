
// Paul
public class PlayerView {

    public void printPlayerInventory(Player player) {
        for(Item i: player.getInventory()) {
            System.out.println(i.getName());
        }
    }

    public void printRoomInventory(Room room) {
        for(Item i: room.getItems()) {
            System.out.println(i.getName());
        }
    }

    public void printItemDescription(Player player, String itemName) {
        Item tempItem = player.doesPlayerHaveItem(itemName);
        if(tempItem == null) {
            System.out.println("Sorry, you do not have that item in your inventory");
        } else {
            System.out.println(tempItem.getDescription());
        }

    }

}
