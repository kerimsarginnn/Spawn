package net.kerim.Spawn;

import dev.perryplaysmc.dynamicjson.data.CColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.awt.*;

public class Messages {
    public static void noPerm(CommandSender sender) {
        sender.sendMessage(CColor.translateGradient("Bunun için gerekli izine sahip değiilsin",CColor.RED,CColor.ORANGE));
    }
    public static void noPlayer(CommandSender sender) {
        sender.sendMessage(CColor.translateGradient("Bu komut yanlızca bir oyuncu tarafından kullanılabilir.",CColor.RED,CColor.ORANGE));
    }
    public static void noDefined(CommandSender sender) {
        sender.sendMessage(CColor.translateGradient("Daha önce bir spawn belirlenmemiş.",CColor.RED,CColor.ORANGE));
    }
    public static void TpOther(CommandSender sender, Player target) {
        sender.sendMessage(CColor.translateGradient("%s, başarıyla spawna ışınlandı!".replace("%s",target.getDisplayName()),CColor.RED,CColor.ORANGE));
        target.sendMessage(CColor.translateGradient("%s, tarafından spawna ışınlandın!".replace("%s",sender.getName()),CColor.RED,CColor.ORANGE));
    }
    public static void TpCancled(Player player, int i) {
        if (i == 0) {
            player.sendMessage(CColor.translateGradient("Hareket ettiğin için ışınlanma iptal edildi!",CColor.RED,CColor.ORANGE));
        } else if (i == 1) {
            player.sendMessage(CColor.translateGradient("Hasar aldığın için ışınlanma iptal edildi!",CColor.RED,CColor.ORANGE));
        }
        player.sendTitle(CColor.translateGradient("İptal Edildi!",CColor.RED,CColor.ORANGE)," ");
    }
    public static void SpawnSet(Player player, String loc) {
        player.sendMessage(CColor.translateGradient("Yeni spawn, %s kordinatlarına belirlendi.".replace("%s",loc),CColor.RED,CColor.ORANGE));
        player.sendTitle(CColor.translateGradient("Spawn Belirlendi!",CColor.RED,CColor.ORANGE),CColor.translateGradient(loc,CColor.RED,CColor.ORANGE));
    }
    public static void Teleporting(Player player, int i) {
        if (i==0) {
            player.sendTitle(CColor.translateGradient("Işınlanma Başarılı!",CColor.RED,CColor.ORANGE)," ");
            player.sendMessage(CColor.translateGradient("Spawna başarıyla ışınlandın!",CColor.RED,CColor.ORANGE));
        } else {
            player.sendTitle(CColor.translateGradient("Işınlanılıyor... %i".replace("%i",String.valueOf(i)),CColor.RED,CColor.ORANGE),"");
        }
    }
}
