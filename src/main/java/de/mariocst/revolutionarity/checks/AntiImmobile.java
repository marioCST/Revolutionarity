package de.mariocst.revolutionarity.checks;

import cn.nukkit.Player;
import cn.nukkit.event.EventHandler;
import cn.nukkit.event.Listener;
import cn.nukkit.event.player.PlayerMoveEvent;
import de.mariocst.revolutionarity.Revolutionarity;
import de.mariocst.revolutionarity.utils.PlayerUtils;

public class AntiImmobile implements Listener {
    private final Revolutionarity plugin;

    public AntiImmobile(Revolutionarity plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onMove(PlayerMoveEvent event) {
        if (!this.plugin.getSettings().isAntiImmobile()) return;

        Player player = event.getPlayer();

        if (PlayerUtils.bypassesCheck(player, "antiimmobile")) return;

        if (!this.plugin.frozen.containsKey(player)) return;

        if (player.getLocation().getX() > this.plugin.frozen.get(player).getX() + 0.1 ||
                player.getLocation().getX() < this.plugin.frozen.get(player).getX() - 0.1 ||
                player.getLocation().getY() > this.plugin.frozen.get(player).getY() + 0.1 ||
                player.getLocation().getY() < this.plugin.frozen.get(player).getY() - 0.1 ||
                player.getLocation().getZ() > this.plugin.frozen.get(player).getZ() + 0.1 ||
                player.getLocation().getZ() < this.plugin.frozen.get(player).getZ() - 0.1) {
            event.setCancelled(true);
            this.plugin.flag("AntiImmobile", player);
        }
    }
}
