package Tutorial1.configurationProperties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AppPropertiesDemo {

    @Autowired
    private AppProperties appProperties;

    public void display() {
        System.out.println("Name: " + appProperties.getName());
        System.out.println("Description: " + appProperties.getDescription());
        System.out.println("Upload Directory: " + appProperties.getUploadDir());
        System.out.println("Security: " + appProperties.getSecurity().getUsername());
        System.out.println("Security: " + appProperties.getSecurity().getPassword());
        System.out.println("Security: " + appProperties.getSecurity().getRoles());
        System.out.println("Security: " + appProperties.getSecurity().isEnabled());
        System.out.println(appProperties.getSecurity().getPermissions());

    }
}
