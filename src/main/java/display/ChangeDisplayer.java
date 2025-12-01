package display;

import config.AppConfig;
import domain.ChangeInfo;

import java.util.List;

public class ChangeDisplayer {

    public static void display(List<ChangeInfo> changes) {
        AppConfig config = AppConfig.getInstance();
        DisplayStrategy strategy = config.getDisplayStrategy();
        strategy.display(changes);
    }
}
