package display;

import domain.ChangeInfo;

import java.util.List;

public interface DisplayStrategy {
    void display(List<ChangeInfo> changes);
}
