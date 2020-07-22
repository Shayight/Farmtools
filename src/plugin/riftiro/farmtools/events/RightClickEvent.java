package plugin.riftiro.farmtools.events;

import java.util.concurrent.ThreadLocalRandom;

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
import org.bukkit.inventory.meta.Damageable;
import org.bukkit.inventory.meta.ItemMeta;

public class RightClickEvent implements Listener {

	
	@EventHandler(priority=EventPriority.HIGH)
	public void rightClickEvent(PlayerInteractEvent event) {
				
		Player p = event.getPlayer(); //Get the player
		ItemStack handItem = p.getInventory().getItemInMainHand(); //Get item in player's hand
		ItemMeta handItemMeta = handItem.getItemMeta(); //get the info for the item in the player's hand.
		Damageable itemDamageable = (Damageable) handItemMeta; //get damage info for the item in the player's hand.
		int seedCount = ThreadLocalRandom.current().nextInt(1, 5 + 1); //A random number between 1-5.
	    
		if(event.getAction().equals(Action.RIGHT_CLICK_BLOCK) && event.getHand() == EquipmentSlot.HAND) { //Check if the block is right clicked
		    
			BlockData clickedItem = event.getClickedBlock().getBlockData(); //Get the data of the clicked block
		    
			if(clickedItem instanceof Ageable) { //check if the item being clicked on is a crop.
				
				Ageable crop = (Ageable) event.getClickedBlock().getBlockData(); //Get crop age data
				
				if(crop.getAge() == crop.getMaximumAge()) { //check if the crop is fully grown.
					
					if(handItem.getType() == Material.WOODEN_HOE || handItem.getType() == Material.STONE_HOE) {
						//Drop 1 crop, and minimum 1 seed.
						p.getInventory().addItem(new ItemStack(Material.WHEAT));
						p.sendMessage("A wood/stone hoe was used.");
					
					} else if(handItem.getType() == Material.IRON_HOE || handItem.getType() == Material.GOLDEN_HOE) {
						//Drop 2 crops, and minimum 1 seed.
						p.getInventory().addItem(new ItemStack(Material.WHEAT, 2));
						p.sendMessage("A iron/gold hoe was used.");
					
					} else if(handItem.getType() == Material.DIAMOND_HOE) {
						//Drop 3 crops, and minimum 1 seed.       
						p.getInventory().addItem(new ItemStack(Material.WHEAT, 3));
						p.sendMessage("A diamond hoe was used.");
					
					} else {
						//Do nothing if a hoe isn't used.	
						event.setCancelled(true);
						return;
					
					}
					p.getInventory().addItem(new ItemStack(Material.WHEAT_SEEDS, seedCount));
					itemDamageable.setDamage(itemDamageable.getDamage() + 1);
					handItem.setItemMeta(handItemMeta);
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