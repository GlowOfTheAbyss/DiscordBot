package org.glow;

import discord4j.common.util.Snowflake;
import discord4j.core.object.entity.Message;

import java.util.List;

public class MessageReader {

    private static final String prefix = "!";
    private static final Snowflake testChat = Snowflake.of(945704740866625547L);
    private static final List<Snowflake> readableChats = List.of(testChat);

    private static final MessageReader messageReader = new MessageReader();

    private MessageReader(){}

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

        if (message.getContent().contains(prefix)) {

            CommandReader.getCommandReader().readCommand(message);

        }

    }

    public static MessageReader getMessageReader() {
        return messageReader;
    }
}
