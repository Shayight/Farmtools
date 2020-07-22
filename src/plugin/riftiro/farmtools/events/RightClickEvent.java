package plugin.riftiro.farmtools.events;

import org.bukkit.Material;
import org.bukkit.block.data.Ageable;
import org.bukkit.block.data.BlockData;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;

public class RightClickEvent implements Listener {

	
	@EventHandler(priority=EventPriority.HIGH)
	public void rightClickEvent(PlayerInteractEvent event) {
				
		Player p = event.getPlayer(); //Get the player
		ItemStack handItem = p.getInventory().getItemInMainHand(); //Get item in player's hand
	    BlockData clickedItem = event.getClickedBlock().getBlockData(); //Get the data of the clicked block
		
		if(event.getAction().equals(Action.RIGHT_CLICK_BLOCK) && event.getHand() == EquipmentSlot.HAND) { //Check if the block is right clicked
			if(clickedItem instanceof Ageable) { //check if the item being clicked on is a crop.
				
				Ageable crop = (Ageable) event.getClickedBlock().getBlockData(); //Get crop age data
				
				if(crop.getAge() == crop.getMaximumAge()) { //check if the crop is fully grown.
					
					if(handItem.getType() == Material.WOODEN_HOE || handItem.getType() == Material.STONE_HOE) {
						//Drop 1 crop, and minimum 1 seed.
						p.sendMessage("A wood/stone hoe was used.");
					
					} else if(handItem.getType() == Material.IRON_HOE || handItem.getType() == Material.GOLDEN_HOE) {
						//Drop 2 crops, and minimum 1 seed.
						p.sendMessage("A iron/gold hoe was used.");
					
					} else if(handItem.getType() == Material.DIAMOND_HOE) {
						//Drop 3 crops, and minimum 1 seed.
						p.sendMessage("A diamond hoe was used.");
					
					} else {
						//Do nothing if a hoe isn't used.	
						return;
					
					}
					
					crop.setAge(0);
					event.getClickedBlock().setBlockData(crop); //replant the crop after being used
					
				}else {
					//If the crop isn't at it's maximum age, then do nothing.
					return;
				}
			}else {
				//If the block is not ageable, then do nothing.
				return;
			}
			
		}else {
			//if the block is not right clicked, do nothing.
			return;
		}
	}
	
}
