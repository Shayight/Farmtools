package plugin.riftiro.farmtools.commands;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import net.md_5.bungee.api.ChatColor;

public class CommandFarm implements CommandExecutor {

	//String[] messages = {"§6-=-=-=-§b§lRiftiro's Farm Plugin§6-=-=-=-", 
	//					 "§6/farm settings <arg>: "
	//		};
	
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(sender instanceof Player) {
			Player player = (Player) sender;
			
			Inventory gui = Bukkit.createInventory(player, 27, ChatColor.AQUA + "Riftiro's Farm Plugin");
			
			ItemStack hoe = new ItemStack(Material.IRON_HOE);
			AddMeta(hoe, ChatColor.AQUA + "Hoe Settings", "Change how hoes interact with farming materials.");
			
			ItemStack ct = new ItemStack(Material.WHEAT);
			AddMeta(ct, ChatColor.AQUA + "Enable/Disable Crop Trampling", "Enable or disable the player's abiliy to trample crops.");
						
			ItemStack air = new ItemStack(Material.AIR);
			
			ItemStack glass = new ItemStack(Material.BLUE_STAINED_GLASS_PANE);
			AddMeta(glass, " ", " ");
			
			
			ItemStack[] menu_items = {glass, air, air, air, air, air, air, air, glass,
									  glass, air, air, hoe, air,  ct, air, air, glass,
									  glass, air, air, air, air, air, air, air, glass};
			gui.setContents(menu_items);
			player.openInventory(gui);
			
			//player.sendMessage(messages);
		}else {
			System.out.println("You cannot view the GUI from the console.");
		}
		return true;
	}
	
	void AddMeta(ItemStack item, String displayName, String displayLore) {
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName(displayName);
		ArrayList<String> lore = new ArrayList<>();
		lore.add(displayLore);
		meta.setLore(lore);
		item.setItemMeta(meta);
	}
	

	
	
}
