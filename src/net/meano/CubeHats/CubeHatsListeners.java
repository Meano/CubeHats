package net.meano.CubeHats;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryAction;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType.SlotType;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

public class CubeHatsListeners implements Listener{
	public CubeHatsMain CHM;
	public CubeHatsListeners(CubeHatsMain MainPlugin){
		CHM = MainPlugin;
	}
	
	@EventHandler(priority = EventPriority.HIGH, ignoreCancelled = true)
	public void onChangeHat(final InventoryClickEvent event){
		if(event.getSlot()==39){
			if(event.getSlotType()==SlotType.ARMOR){
				ItemStack Air = new ItemStack(Material.AIR);
				ItemStack Cursor;
				if(event.getAction().equals(InventoryAction.PLACE_ALL)||event.getAction().equals(InventoryAction.PLACE_ONE)){
					Cursor = event.getCursor();
					if(Cursor.getType().equals(Material.SKULL_ITEM)&&(!event.getWhoClicked().hasPermission("CubeHats.More"))){
						SkullMeta HeadMeta = (SkullMeta)Cursor.getItemMeta();
						String HeadName = HeadMeta.getOwner();
						if(!HeadName.contains("!")){
							if(!HeadMeta.hasDisplayName())
								HeadMeta.setDisplayName(ChatColor.RESET+HeadName+"的头");
							HeadName+="!";
							HeadMeta.setOwner(HeadName);
							Cursor.setItemMeta(HeadMeta);
						}
						return;
					}
					if(Cursor.getType().isBlock()||Cursor.getType().equals(Material.BANNER)){
						if(event.getWhoClicked().hasPermission("CubeHats.More")){
							
						}else if(event.getWhoClicked().hasPermission("CubeHats.Normal")){
							if(!Cursor.getType().equals(Material.SNOW_BLOCK)){
								((Player)event.getWhoClicked()).sendMessage(ChatColor.RED+"抱歉,你没有换这种方块头的资格！");
								return;
							}
						}
						event.setCancelled(true);
						event.getWhoClicked().setItemOnCursor(new ItemStack(Material.AIR));
						event.getWhoClicked().getInventory().setHelmet(Cursor);
					}
				}else if(event.getAction().equals(InventoryAction.PICKUP_ALL)||event.getAction().equals(InventoryAction.PICKUP_ONE)){
					if(!event.getCursor().getType().equals(Material.AIR))
						return;
					Cursor = event.getWhoClicked().getInventory().getHelmet();
					if(Cursor.getType().equals(Material.SKULL_ITEM)&&(!event.getWhoClicked().hasPermission("CubeHats.More"))){
						SkullMeta HeadMeta = (SkullMeta)Cursor.getItemMeta();
						String HeadName = HeadMeta.getOwner();
						if(HeadName.contains("!")){
							HeadMeta.setOwner(HeadName.replaceAll("!",""));
							Cursor.setItemMeta(HeadMeta);
						}
						return;
					}
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
