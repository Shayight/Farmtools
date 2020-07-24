package plugin.riftiro.farmtools.events;

import java.util.concurrent.ThreadLocalRandom;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.data.Ageable;
import org.bukkit.block.data.BlockData;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.Damageable;
import org.bukkit.inventory.meta.ItemMeta;

public class OnBreak implements Listener{

	@EventHandler(priority=EventPriority.HIGH)
	public void onBreak(BlockBreakEvent be) {
		
		ItemStack handStack = be.getPlayer().getInventory().getItemInMainHand(); //Get the item in the player's hand
		Material handItem = handStack.getType(); //Get the material of the item in the player's hand.
		Damageable handItemMeta = (Damageable) handStack.getItemMeta(); //get the item damage of the item in the player's hand.
		BlockData clickedBlock = be.getBlock().getBlockData(); //Get the data of the block being clicked.
		Material clickedMaterial = clickedBlock.getMaterial(); //get the type of item being clicked.
		
		if(clickedBlock instanceof Ageable) { //Check if the object being broken is a crop.

			be.setDropItems(false); //Don't drop the normal items that the object normally would.

			Ageable crop = (Ageable) clickedBlock;
			if(crop.getAge() == crop.getMaximumAge()) { //Check if the crop is at it's maximum age.
				if(handItem == Material.STONE_HOE || handItem == Material.WOODEN_HOE) { //if the item in the player's hand is a wooden/stone hoe, drop 1 crop and at least 1 seed.
					
					switchMaterial(clickedMaterial, 1, be);
					handItemMeta.setDamage(handItemMeta.getDamage()+1);
					handStack.setItemMeta((ItemMeta) handItemMeta);
					
				}else if(handItem == Material.IRON_HOE || handItem == Material.GOLDEN_HOE) { //if the item in the player's hand is an iron/golden hoe, drop 2 crops and at least 1 seed.
					
					switchMaterial(clickedMaterial, 2, be);
					handItemMeta.setDamage(handItemMeta.getDamage()+1);
					handStack.setItemMeta((ItemMeta) handItemMeta);
				
				}else if(handItem == Material.DIAMOND_HOE) { //if the item in the player's hand is a diamond hoe, drop 3 crops and at least 1 seed.
					
					switchMaterial(clickedMaterial, 3, be);
					handItemMeta.setDamage(handItemMeta.getDamage()+1);
					handStack.setItemMeta((ItemMeta) handItemMeta);
					
				}else { //If the item in the player's hand is not a hoe, do nothing.
					return;
					
				}
			}else { //If the crop is not at it's maximum age, do nothing.
				return;
			}
			
		}else { //If the block clicked is not a crop, do nothing.
			return;
		}
		
		
	}
	
	public void switchMaterial(Material clickedMaterial, int dropAmount, BlockBreakEvent e) {
		Block b = e.getBlock();
		int seedCount = ThreadLocalRandom.current().nextInt(1, 5 + 1); //A random number between 1-5.

		org.bukkit.Location loc = b.getLocation();
		switch (clickedMaterial) { //check what the material is, if it's one of the materials listed below, then drop seeds. If not, do nothing.
		case WHEAT:
			//drop wheat based on hoe used, and at least 1 seed. 
			loc.getWorld().dropItemNaturally(loc, new ItemStack(Material.WHEAT, dropAmount));
			loc.getWorld().dropItemNaturally(loc, new ItemStack(Material.WHEAT_SEEDS, seedCount));
			break;
		case BEETROOTS:
			//drop beetroots based on hoe used, and at least 1 seed. 
			loc.getWorld().dropItemNaturally(loc, new ItemStack(Material.BEETROOT, dropAmount));
			loc.getWorld().dropItemNaturally(loc, new ItemStack(Material.BEETROOT_SEEDS, seedCount));
			break;
		case POTATOES:
			//drop potatoes based on hoe used. 
			loc.getWorld().dropItemNaturally(loc, new ItemStack(Material.POTATO, dropAmount));
			break;
		case CARROTS:
			//drop carrots based on hoe used.
			loc.getWorld().dropItemNaturally(loc, new ItemStack(Material.CARROT, dropAmount));
			break;
		case NETHER_WART:
			//drop nether wart based on hoe used. 
			loc.getWorld().dropItemNaturally(loc, new ItemStack(Material.NETHER_WART, dropAmount));
			break;
		default:
			return;
		}
	}
	
	
}
