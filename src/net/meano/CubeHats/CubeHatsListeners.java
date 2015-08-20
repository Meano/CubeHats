package net.meano.CubeHats;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryAction;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType.SlotType;
import org.bukkit.inventory.ItemStack;

public class CubeHatsListeners implements Listener{
	public CubeHatsMain CHM;
	public CubeHatsListeners(CubeHatsMain MainPlugin){
		CHM = MainPlugin;
	}
	
	@EventHandler
	public void onChangeHat(final InventoryClickEvent event){
		if(event.getSlot()==39){
			if(event.getSlotType()==SlotType.ARMOR){
				ItemStack Air = new ItemStack(Material.AIR);
				ItemStack Cursor;
				if(event.getAction().equals(InventoryAction.PLACE_ALL)){
					Cursor = event.getCursor();
					if(Cursor.getType().equals(Material.SKULL_ITEM)&&(!event.getWhoClicked().hasPermission("CubeHats.More"))){
						((Player)event.getWhoClicked()).sendMessage(ChatColor.RED+"抱歉,你没有换这种头的资格");
						event.setCancelled(true);
						return;
					}
					if(Cursor.getType().isBlock()||Cursor.getType().equals(Material.BANNER)){
						if(event.getWhoClicked().hasPermission("CubeHats.More")){
							
						}else if(event.getWhoClicked().hasPermission("CubeHats.Normal")){
							if(!Cursor.getType().equals(Material.SNOW_BLOCK)){
								((Player)event.getWhoClicked()).sendMessage(ChatColor.RED+"抱歉,你没有换这种头的资格");
								return;
							}
						}
						event.setCancelled(true);
						event.getWhoClicked().setItemOnCursor(new ItemStack(Material.AIR));
						event.getWhoClicked().getInventory().setHelmet(Cursor);
					}
				}else if(event.getAction().equals(InventoryAction.PICKUP_ALL)){
					if(!event.getCursor().getType().equals(Material.AIR))
						return;
					Cursor = event.getWhoClicked().getInventory().getHelmet();
					if(Cursor.getType().isBlock()||Cursor.getType().equals(Material.BANNER)){
						event.setCancelled(true);
						event.getWhoClicked().getInventory().setHelmet(Air);
						event.getWhoClicked().setItemOnCursor(Cursor);
					}
				}
			}
		}
	}
}
