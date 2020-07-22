package plugin.riftiro.farmtools.events;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

import net.md_5.bungee.api.ChatColor;

public class ClickEvent implements Listener {

	@EventHandler
	public void clickEvent(InventoryClickEvent e) {
		Player p = (Player) e.getWhoClicked();
		
		if(e.getView().getTitle().equalsIgnoreCase(ChatColor.AQUA + "Riftiro's Farm Plugin")) {
			
			if(e.getCurrentItem() == null){
				return;
			}
			if(e.getCurrentItem().getType().equals(Material.IRON_HOE)) {
				p.sendMessage("Hoe settings have been opened.");
				p.closeInventory();
			}
			if(e.getCurrentItem().getType().equals(Material.WHEAT)) {
				p.sendMessage("Crop trampling has been changed.");
				p.closeInventory();
			}


			e.setCancelled(true);
		}
		
		
	}
	

}
