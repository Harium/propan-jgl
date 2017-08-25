package examples.jgl.bezcurve;

import com.harium.etyl.Etyl;
import com.harium.etyl.commons.context.Application;

public class TutorialJGL1 extends Etyl {

    private static final long serialVersionUID = 1L;

    public TutorialJGL1() {
        super(640, 480);
    }

    public static void main(String[] args) {
        TutorialJGL1 example = new TutorialJGL1();
        example.init();
    }

    @Override
    public Application startApplication() {
        return new BezCurve(w, h);
    }

}
