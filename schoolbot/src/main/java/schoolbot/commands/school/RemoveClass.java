package schoolbot.commands.school;

import java.io.File;

import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import schoolbot.Ryan;
import schoolbot.natives.util.Command;
import schoolbot.natives.Classroom;
import schoolbot.natives.Professor;
import schoolbot.natives.School;
import schoolbot.natives.util.operations.FileOperations;
import schoolbot.natives.util.operations.MessageOperations;

public class RemoveClass extends Command {

    public RemoveClass() {
        super(new String[] { "removeclass", "classremove" });
    }

    @Override
    public void run(MessageReceivedEvent event) {
        MessageOperations.invalidUsageShortner("https://google.com", "This command takes in atleast 2 arguments!", event.getMessage(), this);

    }

    @Override
    public void run(MessageReceivedEvent event, String[] args) {
        Message msg = event.getMessage();
        /**
         * Args[0] = school Args[1] = Class ID
         * 
         */

        MessageChannel channel = event.getChannel();
        Member userTyping = event.getMember();

        if (!userTyping.getPermissions().contains(Permission.ADMINISTRATOR)) {
            MessageOperations.invalidUsageShortner("https://google.com", "You don't have the wrong permissions!", msg,
                    this);
            return;
        }

        if (args.length < 1) {
            MessageOperations.invalidUsageShortner("https://google.com", "This command takes in atleast one arg!", msg, this);
        } else {
            if (Ryan.schools.containsKey(args[0])) {
                String schoolReefrence = args[0];
                String classNumber = args[1];

                School schoolToRemoveClassFrom = Ryan.schools.get(schoolReefrence);
                if (schoolToRemoveClassFrom.getListOfClasses().containsKey(classNumber)) {
                    Classroom classToRemove = schoolToRemoveClassFrom.getListOfClasses().get(classNumber);
                   // int classSize = classToRemove.getClassList().size();
                    Professor classProfessor = classToRemove.getProfessor();
                    //if (classSize <= 0) {
                        Ryan.classes.remove(classNumber);
                        classProfessor.removeClass(classToRemove);
                        classProfessor.getProfessorsClasses().remove(classNumber);
                        Ryan.schools.get(schoolReefrence).removeClazz(classToRemove);


                        FileOperations.writeToFile(FileOperations.professor, Ryan.professors);
                        FileOperations.writeToFile(FileOperations.schools, Ryan.schools);
                        FileOperations.writeToFile(FileOperations.classes, Ryan.classes);

                        channel.sendMessage(":white_check_mark: Class deleted sucessfully! :white_check_mark:").queue();


                  //  } else {
                   //     MessageOperations.invalidUsageShortner("https://google.com",
                    //            "This class still has students in it!", event.getMessage(), this);
                   // }
                } else {
                    MessageOperations.invalidUsageShortner("https://google.com", "That class does not exist", msg, this);
                }
            } else {
                MessageOperations.invalidUsageShortner("https://google.com", "That school doesnt exist!",
                        event.getMessage(), this);
            }
        }
    }

}
