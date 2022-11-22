package GUI;

public class GUISupport {
    String dashedSegment = "-----------------------";

    public String longDashLine() {
        return dashedSegment + dashedSegment + dashedSegment;
    }

    public String shortDashLine() {
        return dashedSegment;
    }
}
