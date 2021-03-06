package schoolbot.commands.school;

import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import schoolbot.Ryan;
import schoolbot.natives.util.Command;
import schoolbot.natives.Classroom;
import schoolbot.natives.Professor;
import schoolbot.natives.Student;
import schoolbot.natives.util.operations.MessageOperations;

public class JoinClass extends Command {

    @Deprecated

    public JoinClass() {
        super(new String[] { "jClass", "joinclass", "classjoin" });
    }

    @Override
    public void run(MessageReceivedEvent event) {
        MessageOperations.invalidUsageShortner("https://google.com", "This command takes in atleast 1 arguments!", event.getMessage(), this);

    }

    @Override
    public void run(MessageReceivedEvent event, String[] args) {
        User userTyping = event.getAuthor();
        MessageChannel channel = event.getChannel();

        if (args.length != 1) {

        } else {
            if (Ryan.classes.containsKey(args[0])) {
                Classroom clazz = Ryan.classes.get(args[0]);
                if (Ryan.students.containsKey(userTyping)) {
                    Student student = Ryan.students.get(userTyping);
                    if (student.getSchool() == clazz.getSchool()) {
                        Professor prof = clazz.getProfessor();
                        prof.addStudent(student, clazz);
                        student.addClass(clazz);
                        channel.sendMessage(":white_check_mark: Joined class sucessfully :white_check_mark: ").queue();
                    } else {
                        // class doesnt belong to school
                    }
                } else {
                    // student doesnt exist
                }
            } else {
                // class doesnt exist;
            }
        }
    }

}
