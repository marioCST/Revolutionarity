package de.mariocst.revolutionarity.commands;

import cn.nukkit.Player;
import cn.nukkit.command.Command;
import cn.nukkit.command.CommandSender;
import cn.nukkit.command.data.CommandParameter;
import de.mariocst.revolutionarity.Revolutionarity;

public class RevolutionaryCommand extends Command {
    private final Revolutionarity plugin;

    public RevolutionaryCommand(Revolutionarity plugin) {
        super("revolutionarity");
        this.plugin = plugin;
        this.setDescription("Revolutionarity command");
        this.setUsage("revolutionarity <config|setprefix>");
        this.setAliases(new String[]{"rv"});
        this.setPermission("revolutionarity.revolutionartity");
        this.setPermissionMessage("§cNo permission");

        this.commandParameters.clear();
        this.commandParameters.put("default", new CommandParameter[]{
                CommandParameter.newEnum("action", false, new String[]{"config", "setprefix"})
        });
    }

    @Override
    public boolean execute(CommandSender sender, String s, String[] args) {
        if (!(sender instanceof Player)) {
            if (args.length >= 2) {
                switch (args[0].toLowerCase()) {
                    case "config": {
                        switch (args[1].toLowerCase()) {
                            case "save": {
                                this.plugin.saveConfigs();
                                sender.sendMessage(this.plugin.getPrefix() + "Configs saved!");
                                break;
                            }
                            case "reload": {
                                this.plugin.loadConfigs();
                                sender.sendMessage(this.plugin.getPrefix() + "Configs reloaded!");
                                break;
                            }
                            default: {
                                sender.sendMessage(this.plugin.getPrefix() + "/revolutionarity config <reload|save>");
                                break;
                            }
                        }
                    }
                    case "setprefix": {
                        StringBuilder strings = new StringBuilder();
                        for (int i = 1; i < args.length; i++) {
                            strings.append(args[i]).append(" ");
                        }

                        this.plugin.setPrefix(strings.toString().replaceAll("&", "§"));
                        this.plugin.getPluginSettings().setPrefix(strings.toString().replaceAll("&", "§"));
                        sender.sendMessage(this.plugin.getPrefix() + "Set prefix to " + strings.toString().replaceAll("&", "§"));
                        this.plugin.saveConfigs();
                        break;
                    }
                    default: {
                        sender.sendMessage(this.plugin.getPrefix() + "/revolutionarity <config|setprefix>");
                        break;
                    }
                }
            }
            else {
                sender.sendMessage(this.plugin.getPrefix() + "/revolutionarity <config|setprefix>");
            }
            return true;
        }

        Player player = (Player) sender;

        if (this.testPermission(player)) {
            if (args.length >= 2) {
                switch (args[0].toLowerCase()) {
                    case "config": {
                        switch (args[1].toLowerCase()) {
                            case "save": {
                                this.plugin.saveConfigs();
                                player.sendMessage(this.plugin.getPrefix() + "Configs saved!");
                                break;
                            }
                            case "reload": {
                                this.plugin.loadConfigs();
                                player.sendMessage(this.plugin.getPrefix() + "Configs reloaded!");
                                break;
                            }
                            default: {
                                player.sendMessage(this.plugin.getPrefix() + "/revolutionarity config <reload|save>");
                                break;
                            }
                        }
                    }
                    case "setprefix": {
                        StringBuilder strings = new StringBuilder();
                        for (int i = 1; i < args.length; i++) {
                            strings.append(args[i]).append(" ");
                        }

                        this.plugin.setPrefix(strings.toString().replaceAll("&", "§"));
                        this.plugin.getPluginSettings().setPrefix(strings.toString().replaceAll("&", "§"));
                        player.sendMessage(this.plugin.getPrefix() + "Set prefix to " + strings.toString().replaceAll("&", "§"));
                        this.plugin.saveConfigs();
                        break;
                    }
                    default: {
                        player.sendMessage(this.plugin.getPrefix() + "/revolutionarity <config|setprefix>");
                        break;
                    }
                }
            }
            else {
                player.sendMessage(this.plugin.getPrefix() + "/revolutionarity <config|setprefix>");
            }
        }
        return false;
    }
}
