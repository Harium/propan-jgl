package examples.jgl.application;

import com.harium.propan.Application3D;
import com.harium.etyl.commons.event.MouseEvent;
import com.harium.etyl.commons.event.PointerEvent;
import com.harium.etyl.core.graphics.Graphics;

import static org.jgl.GL.*;

public class MoveLight extends Application3D {

    public MoveLight(int w, int h) {
        super(w, h);
    }

    @Override
    public void load() {
        myinit();
        loading = 100;
    }

    private int spin = 0;

    private void movelight() {
        spin = (spin + 30) % 360;
    }

    @Override
    public void updateMouse(PointerEvent event) {
        if (event.isButtonDown(MouseEvent.MOUSE_BUTTON_LEFT)) {
            movelight();
        }
    }

    private void myinit() {
        gl.glEnable(GL_LIGHTING);
        gl.glEnable(GL_LIGHT0);

        gl.glDepthFunc(GL_LESS);
        gl.glEnable(GL_DEPTH_TEST);

        myReshape(w, h);
    }

    public void draw(Graphics g) {
        float position[] = {0.0f, 0.0f, 1.5f, 1.0f};

        gl.glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
        gl.glPushMatrix();
        gl.glTranslatef(0.0f, 0.0f, -5.0f);

        gl.glPushMatrix();
        gl.glRotated((double) spin, 1.0, 0.0, 0.0);
        gl.glRotated(0.0, 1.0, 0.0, 0.0);
        gl.glLightfv(GL_LIGHT0, GL_POSITION, position);

        gl.glTranslated(0.0, 0.0, 1.5);
        gl.glDisable(GL_LIGHTING);
        gl.glColor3f(0.0f, 1.0f, 1.0f);
        gl.auxWireCube(0.1);
        gl.glEnable(GL_LIGHTING);
        gl.glPopMatrix();

        gl.auxSolidTorus(0.275, 0.85);
        gl.glPopMatrix();
        gl.glFlush(g);
    }

    private void myReshape(float w, float h) {
        gl.glViewport(0, 0, w, h);
        gl.glMatrixMode(GL_PROJECTION);
        gl.glLoadIdentity();
        gl.gluPerspective(40.0, (float) w / (float) h, 1.0, 20.0);
        gl.glMatrixMode(GL_MODELVIEW);
    }

}
