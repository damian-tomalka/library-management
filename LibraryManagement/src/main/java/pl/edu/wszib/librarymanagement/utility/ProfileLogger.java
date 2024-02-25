package pl.edu.wszib.librarymanagement.utility;

import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
public class ProfileLogger implements ApplicationListener<ApplicationReadyEvent> {

    private final Environment environment;

    public ProfileLogger(Environment environment) {
        this.environment = environment;
    }

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        String[] activeProfiles = environment.getActiveProfiles();
        String profiles = String.join(", ", activeProfiles);
        System.out.println("Uruchomiłeś aplikację z profilem: " + profiles);
    }
}
