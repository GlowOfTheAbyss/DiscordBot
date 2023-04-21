package org.glow.commands.RPGCommands;

import discord4j.core.object.entity.Message;
import discord4j.core.spec.EmbedCreateSpec;
import org.glow.commands.Command;
import org.glow.magic.Magic;
import org.glow.magic.Spells;
import org.glow.person.PersonManager;
import org.glow.person.Player;

public class CastCommand extends Command {

    private static CastCommand castCommand;

    private CastCommand() {
        setName("cast");
        setInfo("""
                комманда для использования извесных вам заклинаний
                !cast - показывает извесные вам заклинания
                !cast [название заклинания] [цель заклинания]""");
    }

    @Override
    public void start(Message message) {

        Player player = userToPlayer(message);
        if (player == null) {
            return;
        }

        if (message.getContent().split(" ").length == 1) {

            EmbedCreateSpec.Builder builder = EmbedCreateSpec.builder();
            builder.title("Книга заклинаний " + PersonManager.getInstance().getPersonName(player));
            StringBuilder stringBuilder = new StringBuilder();

            if (player.getSkillBook().getListSpell().isEmpty()) {
                stringBuilder.append("Пусто");
            } else {
                for (Magic magic : player.getSkillBook().getListSpell()) {
                    stringBuilder.append(magic.getSpellName()).append(" | ").append(magic.getCoastInMana()).append(" мана\n");
                }
            }

            builder.description(stringBuilder.toString());
            message.getChannel().block().createMessage(builder.build()).block();
            message.delete().block();


        } else {
            String spellName = message.getContent().split(" ")[1];
            for (Magic magic : Spells.getSpells().getMagicList()) {

                if (magic.getSpellName().equalsIgnoreCase(spellName)) {

                    magic.cast(message, player);
                    return;

                }

            }

            EmbedCreateSpec.Builder builder = EmbedCreateSpec.builder();
            builder.title("Заклинание " + spellName + " не найдено");
            message.getChannel().block().createMessage(builder.build()).block();
            message.delete().block();

        }

    }

    public static CastCommand getCastCommand() {
        if (castCommand == null) {
            castCommand = new CastCommand();
        }
        return castCommand;
    }
}
