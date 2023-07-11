package org.glow.message;

import discord4j.core.object.entity.Message;
import discord4j.core.spec.EmbedCreateSpec;

public class MessageSender {

    private final EmbedCreateSpec.Builder builder;

    private MessageSender() {
        builder = EmbedCreateSpec.builder();
    }

    public void sendMessageInChannel(Message message, String title) {

        builder.title(title);
        message.getChannel().block().createMessage(builder.build()).block();
        message.delete().block();

    }

    public void sendMessageInChannel(Message message, String title, String description) {

        builder.title(title);
        builder.description(description);
        message.getChannel().block().createMessage(builder.build()).block();
        message.delete().block();

    }

    public void sendMessageInChannel(Message message, String title, String description, String image) {

        builder.title(title);
        builder.description(description);
        builder.image(image);
        message.getChannel().block().createMessage(builder.build()).block();
        message.delete().block();

    }

    public static MessageSender getInstance() {
        return new MessageSender();
    }
}
