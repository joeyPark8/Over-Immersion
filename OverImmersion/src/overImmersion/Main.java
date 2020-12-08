package overImmersion;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin implements Listener {
    boolean started = false, first = true;
    int count = 0;
    Block lastBlock;

    @Override
    public void onEnable() {
        System.out.println("OverImmersion is activated");
        Bukkit.getPluginManager().registerEvents(this, this);
    }

    @Override
    public void onDisable() {
        System.out.println("OverImmersion is deactivated");
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (command.getName().equalsIgnoreCase("oi")) {
            if (args.length == 0) {
                sender.sendMessage(ChatColor.LIGHT_PURPLE + "Don't immerse too much!");
            }
            else if (args[0].equalsIgnoreCase("stop")) {
                if (started == false) {
                    sender.sendMessage(ChatColor.GREEN + "stop over immersion");
                    started = true;
                }
            }
            else if (args[0].equalsIgnoreCase("start")) {
                if (started == true) {
                    sender.sendMessage(ChatColor.GREEN + "start over immersion");
                    started = false;
                }
            }
            else if (args[0].equalsIgnoreCase("clear")) {
                lastBlock = null;
            }
        }
        return true;
    }

    @EventHandler
    public void onBreak(BlockBreakEvent e) {
        Player player = e.getPlayer();
        if (started == true) {
            if (first == false) {
                if (e.getBlock().getType() == lastBlock.getType()) {
                    count+=1;
                }
                else {
                    count = 0;
                }
            }
            lastBlock = e.getBlock();
            first = false;
            if (count == 2) {
                player.getWorld().createExplosion(player.getLocation(), 20);
                player.sendMessage("Don't immerse");
            }
        }
    }

    @EventHandler
    public void onPlace(BlockPlaceEvent e) {
        Player player = e.getPlayer();
        if (started == true) {
            if (first == false) {
                if (e.getBlock().getType() == lastBlock.getType()) {
                    count+=1;
                }
                else {
                    count = 0;
                }
            }
            lastBlock = e.getBlock();
            first = false;
            if (count == 2) {
                player.getWorld().createExplosion(player.getLocation(), 20);
            }
        }
    }
}
