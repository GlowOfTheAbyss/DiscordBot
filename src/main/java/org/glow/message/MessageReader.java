package org.glow.message;

import discord4j.common.util.Snowflake;
import discord4j.core.object.entity.Message;
import org.glow.Main;
import org.glow.commands.CommandReader;

import java.util.List;

public class MessageReader {

    private static final Snowflake testChat = Snowflake.of(945704740866625547L);
    private static final Snowflake mainChat = Snowflake.of(851600858100531230L);
    private static final List<Snowflake> readableChats = List.of(testChat, mainChat);

    private static final MessageReader messageReader = new MessageReader();
    private static CommandReader commandReader;

    private MessageReader(){
        commandReader = CommandReader.getCommandReader();
    }

    public void readMessage(Message message) {

        if (message.getAuthor().isEmpty()) {
            return;
        }
        if (message.getAuthor().get().isBot()) {
            return;
        }
        if (message.getChannel().block() == null) {
            return;
        }
        if (!readableChats.contains(message.getChannel().block().getId())) {
            return;
        }

        if (message.getContent().contains(Main.systems.commandPrefix)) {

            commandReader.readCommand(message);

        }

    }

    public static MessageReader getMessageReader() {
        return messageReader;
    }
}
