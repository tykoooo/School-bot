package schoolbot.commands.school;

import java.io.File;

import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import schoolbot.Ryan;
import schoolbot.natives.util.Command;
import schoolbot.natives.School;
import schoolbot.natives.util.operations.FileOperations;
import schoolbot.natives.util.operations.MessageOperations;

public class RemoveSchool extends Command {

    public RemoveSchool() {
        super(new String[] { "removeschool", "schoolremove", "rschool" });
    }

    @Override
    public void run(MessageReceivedEvent event, String[] args) {
        MessageChannel channel = event.getChannel();
        Message msg = event.getMessage();
        Member userTyping = event.getMember();

        if (!userTyping.getPermissions().contains(Permission.ADMINISTRATOR)) {
            MessageOperations.invalidUsageShortner("https://google.com", "You don't have the wrong permissions!", msg, this);
            return;
        }


        if (args.length != 1) {

        } else if (!Ryan.schools.containsKey(args[0])) {
            MessageOperations.invalidUsageShortner("https://google.com", "That school doesnt exist!", msg, this);
            return;
        } else {
            School school = Ryan.schools.get(args[0]);
            if (school.getListOfProfessors().size() > 0) {
                MessageOperations.invalidUsageShortner("https://google.com",
                        "There are still **professors** at this school", msg, this);
                return;
            } else if (school.getListOfClasses().size() > 0) {
                MessageOperations.invalidUsageShortner("https://google.com",
                        "There are still **classes** at this school", msg, this);
                return;
            } else if (school.getListOfStudents().size() > 0) {
                MessageOperations.invalidUsageShortner("https://google.com",
                        "There are still **professors** at this school", msg, this);
                return;
            }

   

            String schoolreference = args[0];

            Ryan.schools.remove(schoolreference);
            Ryan.schoolCalls.remove(schoolreference);
            FileOperations.writeToFile(FileOperations.schoolsCalls, Ryan.schoolCalls);
            FileOperations.writeToFile(FileOperations.schools, Ryan.schools);
            FileOperations.writeToFile(FileOperations.classes, Ryan.classes);
            channel.sendMessage(":white_check_mark: School removed :white_check_mark:").queue();

        }

    }

    @Override
    public void run(MessageReceivedEvent event) {
        MessageOperations.invalidUsageShortner("https://google.com", "This command takes in atleast 2 arguments!", event.getMessage(), this);

    }

}
