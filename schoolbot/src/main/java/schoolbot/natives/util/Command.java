package schoolbot.natives.util;

import java.io.File;
import java.util.Arrays;
import java.util.function.Consumer;

import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import schoolbot.natives.util.operations.FileOperations;
import schoolbot.natives.util.operations.StringOperations;

/**
 * Command interface for all commands to implement.
 * 
 * @author Elsklivet#8867
 */
public abstract class Command {

    /**
     * List of possible aliases for this command. Joshigakusei should check if the
     * command called is in this calls list.
     */
    protected String[] calls;
    /**
     * Enabled status. Commands which are not enabled should not run.
     */
    protected boolean enabled;
    /**
     * Documentation embed for asking {@code ++help "comand name"}
     * 
     * @see net.dv8tion.jda.api.entities.MessageEmbed;
     */
    protected MessageEmbed documentation;

    /**
     * This will make it easier for invalid usage
     */
    protected String name;

    /**
     * Path to the documenation
     */
    protected File documentationFile;

    /**
     * Empty constructor.
     * 
     * @apiNote This will set the command to {@code disabled}.
     */
    public Command() {
        this.enabled = false;
        this.initDocumentation();
    }

    /**
     * Command constructor with aliases for the command.
     * 
     * @param aliases command aliases
     */
    public Command(String[] aliases) {
        calls = new String[aliases.length];
        for (int i = 0; i < aliases.length; i++)
            calls[i] = aliases[i];
        this.enabled = true;
        this.initDocumentation();
        this.name = this.getClass().getSimpleName();
        this.documentationFile = new File("schoolbot\\docs\\" + name + ".txt");
        FileOperations.writeDocumentation(this);
    }

    /**
     * Speed testing constructor. Do not use.
     * 
     * @deprecated DO NOT USE THIS CONSTRUCTOR. For speed testing purposes.
     * @param aliases aliases for this command.
     * @param runCode code to run immediately on creation.
     * @param flags   flags for immediate run.
     */
    public Command(String[] aliases, String[] flags, Consumer<String[]> runCode) {
        calls = new String[aliases.length];
        for (int i = 0; i < aliases.length; i++)
            calls[i] = aliases[i];
        this.enabled = true;
        runCode.accept(flags);

    }

    /**
     * What the command will do on call.
     */
    public abstract void run(MessageReceivedEvent event);

    /**
     * What the command will do on call.
     * 
     * @param args Arguments sent to the command.
     */
    public abstract void run(MessageReceivedEvent event, String[] args);

    /**
     * Method to initialize the documentation for this command.
     * <p>
     * Implementation Note:<br>
     * </br>
     * This shouldn't really handle parsing itself. StringOperations will do that.
     * </p>
     * 
     * @see schoolbot.natives.util.StringOperations#parseDoc(String)
     */
    public void initDocumentation(String relativePath) {
        this.documentation = StringOperations.parseDoc(relativePath);
    }

    /**
     * Method to initialize the documentation for this command without providing a
     * path.
     * <p>
     * Implementation Note:<br>
     * </br>
     * This shouldn't really handle parsing itself. StringOperations will do that.
     * </p>
     * 
     * @see schoolbot.natives.util.StringOperations#parseDoc(String)
     */
    public void initDocumentation() {
        String className = this.getClass().getName();
        String name = className.substring(className.lastIndexOf(".") + 1);
        String relativePath = "schoolbot\\docs\\" + name + ".txt";
        this.documentation = StringOperations.parseDoc(relativePath);
    }

    /**
     * Check whether the current command is enabled or not.
     * 
     * @return enabled?
     */
    public boolean enabled() {
        return this.enabled;
    }

    /**
     * {@code calls} getter.
     * 
     * @return Array of calls
     */
    public String[] getCalls() {
        return this.calls;
    }

    /**
     * Check whether a test String is found in this command's calls.
     * 
     * @param test String to search {@code calls} for.
     * @return {@code true} if the test string is found in {@code calls},
     *         {@code false} otherwise.
     */
    public boolean isInCalls(String test) {
        for (String call : calls) {
            if (call.strip().equals(test)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Set this command to be enabled or disabled.
     * 
     * @param enabled {@code true} for enabled or {@code false} for disabled.
     */
    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    /**
     * Function to get the documentation JSON for this command as a MessageEmbed.
     * 
     * @return {@code MessageEmbed} of the command's documentation
     */
    public MessageEmbed getDocumentation() {
        return this.documentation;
    }

    /**
     * Function to get the name of the command
     * 
     * @return the name of the command
     */
    public String getName() {
        return name;
    }

    public File getDocumentationFile() {
        return documentationFile;
    }

    /**
     *
     * @param name
     * @return
     */
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Command [calls=" + Arrays.toString(calls) + ", enabled=" + enabled + "]";
    }
}
