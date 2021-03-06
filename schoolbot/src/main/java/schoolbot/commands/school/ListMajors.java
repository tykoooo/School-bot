package schoolbot.commands.school;

import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import schoolbot.natives.util.Command;
import schoolbot.natives.util.Majors;
import schoolbot.natives.util.operations.StringOperations;

public class ListMajors extends Command {

    public ListMajors() {
        super(new String[] { "listmajors", "majors" });
    }

    @Override
    public void run(MessageReceivedEvent event) {
        MessageChannel channel = event.getChannel();
        StringBuilder kindaAnArray = new StringBuilder("```[");

        int len = Majors.values().length;
        int index = 0;
        for (Majors major : Majors.values()) {
            String maj = major.toString();
            maj = maj.replaceAll("_", " ");
            maj = StringOperations.normalizeCapitals(maj);
            if (index == len - 1) {
                kindaAnArray.append(maj);
            } else {
                kindaAnArray.append(maj + ", ");
            }
            index++;
        }
        kindaAnArray.append("]```");
        channel.sendMessage(kindaAnArray + "\n").queue();

    }

    @Override
    public void run(MessageReceivedEvent event, String[] args) {

    }

}