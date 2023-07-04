package org.glow.commands.RPGCommands;

import discord4j.core.object.entity.Message;
import discord4j.core.spec.EmbedCreateSpec;
import org.glow.Main;
import org.glow.commands.Command;
import org.glow.magic.Spell;
import org.glow.magic.SpellManager;
import org.glow.person.PersonManager;
import org.glow.person.Player;

public class CastCommand extends Command {

    private static CastCommand castCommand;

    private CastCommand() {
        setName("cast");
        setInfo("""
                комманда для использования извесных вам заклинаний
                !cast - показывает извесные вам заклинания
                !cast [название заклинания] [цель заклинания]
                """);
    }

    @Override
    public void start(Message message) {

        Player player = userToPlayer(message);
        if (player == null) {
            return;
        }

        if (message.getContent().split(" ").length == 1) {

            showSpellBook(message, player);

        } else {

            castSpell(message, player);

        }

    }

    private void showSpellBook(Message message, Player player) {

        EmbedCreateSpec.Builder builder = EmbedCreateSpec.builder();

        builder.title("Книга заклинаний " + PersonManager.getInstance().getPersonName(player));

        StringBuilder stringBuilder = new StringBuilder();

        if (player.getSpellBook().getListSpell().isEmpty()) {

            stringBuilder.append("Пусто");

        } else {

            for (Spell spell : player.getSpellBook().getListSpell()) {

                stringBuilder.append(spell.getSpellName())
                        .append(" | ")
                        .append(spell.getCoastInMana())
                        .append(" мана")
                        .append("\n");

            }

        }

        builder.description(stringBuilder.toString());

        message.getChannel().block().createMessage(builder.build()).block();
        message.delete().block();

    }

    private void castSpell(Message message, Player player) {

        try {

            Spell spell = findSpell(message);
            spell.cast(message, player);

        } catch (RuntimeException exception) {

            sendMessageInChanel(message, exception.getMessage());

        }

    }

    private Spell findSpell(Message message) {

        String croppedMessage = message.getContent()
                .replaceFirst(Main.systems.commandPrefix + castCommand.getName(), "");

        for (Spell spell : SpellManager.getInstance().getMagicList()) {

            if (croppedMessage.contains(spell.getSpellName())) {

                return spell;

            }

        }

        throw new RuntimeException("Spell " + croppedMessage + " not found");

    }

    public static CastCommand getCastCommand() {
        if (castCommand == null) {
            castCommand = new CastCommand();
        }
        return castCommand;
    }
}
