package org.glow.commands.RPGCommands;

import discord4j.core.object.entity.Message;
import org.glow.Main;
import org.glow.commands.Command;
import org.glow.magic.Spell;
import org.glow.magic.SpellManager;
import org.glow.message.MessageSender;
import org.glow.message.TextManager;
import org.glow.person.PersonManager;
import org.glow.person.Player;

public class CastCommand extends Command {

    private static CastCommand castCommand;

    private CastCommand() {
        setName("cast");
        String info = """
                комманда для использования извесных вам заклинаний
                %s%s - показывает извесные вам заклинания
                %s%s [название заклинания] [цель заклинания]
                """;
        setInfo(String.format(info,
                Main.getSystems().getCommandPrefix(), getName(),
                Main.getSystems().getCommandPrefix(), getName()));
    }

    @Override
    public void start(Message message) {

        try {

            Player player = userToPlayer(message);

            if (message.getContent().split(" ").length == 1) {
                showSpellBook(message, player);
            } else {
                castSpell(message, player);
            }

        } catch (IllegalArgumentException exception) {
            MessageSender.getInstance().sendMessageInChannel(message, exception.getMessage());
        }

    }

    private void showSpellBook(Message message, Player player) {

        String title = "Книга заклинаний " + PersonManager.getInstance().getPersonName(player);
        MessageSender.getInstance().sendMessageInChannel(message, title, TextManager.getInstance().getPlayerSkillBook(player));

    }

    private void castSpell(Message message, Player player) {

        try {

            Spell spell = findSpell(message);
            spell.cast(message, player);

        } catch (IllegalArgumentException exception) {

            MessageSender.getInstance().sendMessageInChannel(message, exception.getMessage());

        }

    }

    private Spell findSpell(Message message) {

        String croppedMessage = message.getContent()
                .replaceFirst(Main.getSystems().getCommandPrefix() + castCommand.getName(), "");

        for (Spell spell : SpellManager.getInstance().getMagicList()) {
            if (croppedMessage.contains(spell.getSpellName())) {
                return spell;
            }
        }

        throw new IllegalArgumentException("Заклинание " + croppedMessage + " не найдено");

    }

    public static CastCommand getCastCommand() {
        if (castCommand == null) {
            castCommand = new CastCommand();
        }
        return castCommand;
    }
}
