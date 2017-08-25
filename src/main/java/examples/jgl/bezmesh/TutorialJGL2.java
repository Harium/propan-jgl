package examples.jgl.bezmesh;

import com.harium.etyl.Etyl;
import com.harium.etyl.commons.context.Application;

public class TutorialJGL2 extends Etyl {

    private static final long serialVersionUID = 1L;

    public TutorialJGL2() {
        super(640, 480);
    }

    public static void main(String[] args) {
        TutorialJGL2 example = new TutorialJGL2();
        example.init();
    }

    @Override
    public Application startApplication() {
        return new BezMesh(w, h);
    }

}
