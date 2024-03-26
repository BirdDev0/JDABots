import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.requests.GatewayIntent;
import org.jetbrains.annotations.NotNull;

// JDAMP stands for Java Discord API Multi-Purpose.
public class JDAMP extends ListenerAdapter {
    public static void main(String[] args) {
        //psvm saves lives
        final String TOKEN = "TOKEN";
        JDA jda = JDABuilder.createDefault(TOKEN, GatewayIntent.GUILD_MESSAGES, GatewayIntent.MESSAGE_CONTENT, GatewayIntent.GUILD_MEMBERS)
                .addEventListeners(new JDAMP())
                .build();

    }

    @Override
    public void onMessageReceived(@NotNull MessageReceivedEvent event) {

        if (event.getAuthor().isBot()) return;
        Message message = event.getMessage();
        String content = message.getContentRaw();
        String[] profanityList = {/*Add your bad words here(all lowercase).*/ "fuck", "shit", "bitch"};
        for (String profanity : profanityList) {
            if (content.toLowerCase().contains(profanity)) {
                event.getMessage().delete().queue();
                String mention = event.getAuthor().getAsMention();
                String callout = "Hey, " + mention + "! Please avoid using bad language.";
                event.getChannel().sendMessage(callout).queue();
                break;
            }
        }
    }
}