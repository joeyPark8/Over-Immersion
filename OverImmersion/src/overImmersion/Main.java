package overImmersion;

import org.bukkit.ChatColor;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin implements Listener {
    boolean started = false, first = true;
    Block lastBlock;

    @Override
    public void onEnable() {
        System.out.println("OverImmersion is activated");
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
        }
        return true;
    }

    public void onBreak(BlockBreakEvent e) {
        if (started == true) {
            if (first == false) {
                if (e.getBlock() == lastBlock) {

                }
            }
            lastBlock = e.getBlock();
        }
    }

    public void onPlace(BlockPlaceEvent e) {
        if (started == true) {
            if (first == false) {
                if (e.getBlock() == lastBlock) {

                }
            }
            lastBlock = e.getBlock();
        }
    }

}
