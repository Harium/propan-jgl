package examples.jgl.application;

import com.harium.propan.Application3D;
import com.harium.etyl.commons.event.KeyEvent;
import com.harium.etyl.core.graphics.Graphics;

import static org.jgl.GL.*;


public class Robot extends Application3D {

    public Robot(int w, int h) {
        super(w, h);
    }

    private static int shoulder = 0, elbow = 0;

    private void elbowAdd() {
        elbow = (elbow + 5) % 360;
    }

    private void elbowSubtract() {
        elbow = (elbow - 5) % 360;
    }

    private void shoulderAdd() {
        shoulder = (shoulder + 5) % 360;
    }

    private void shoulderSubtract() {
        shoulder = (shoulder - 5) % 360;
    }

    @Override
    public void updateKeyboard(KeyEvent event) {
        if (event.isKeyDown(KeyEvent.VK_CTRL_LEFT) || event.isKeyDown(KeyEvent.VK_CTRL_RIGHT)) {

            if (event.isKeyDown(KeyEvent.VK_RIGHT)) {
                shoulderAdd();
            } else if (event.isKeyDown(KeyEvent.VK_LEFT)) {
                shoulderSubtract();
            } else if (event.isKeyDown(KeyEvent.VK_UP)) {
                elbowAdd();
            } else if (event.isKeyDown(KeyEvent.VK_DOWN)) {
                elbowSubtract();
            }

        } else {

            if (event.isKeyDown(KeyEvent.VK_RIGHT)) {
                shoulderAdd();
            } else if (event.isKeyDown(KeyEvent.VK_LEFT)) {
                shoulderSubtract();
            } else if (event.isKeyDown(KeyEvent.VK_UP)) {
                elbowAdd();
            } else if (event.isKeyDown(KeyEvent.VK_DOWN)) {
                elbowSubtract();
            }

        }
    }

    @Override
    public void draw(Graphics g) {
        gl.glClear(GL_COLOR_BUFFER_BIT);
        gl.glColor3f(1.0f, 1.0f, 1.0f);
        gl.glPushMatrix();

        gl.glTranslatef(-1.0f, 0.0f, 0.0f);
        gl.glRotatef((float) shoulder, 0.0f, 0.0f, 1.0f);
        gl.glTranslatef(1.0f, 0.0f, 0.0f);
        gl.auxWireBox(2.0, 0.4, 1.0);	/* shoulder */

        gl.glTranslatef(1.0f, 0.0f, 0.0f);
        gl.glRotatef((float) elbow, 0.0f, 0.0f, 1.0f);
        gl.glTranslatef(1.0f, 0.0f, 0.0f);
        gl.auxWireBox(2.0, 0.4, 1.0);	/* elbow */

        gl.glPopMatrix();
        gl.glFlush(g);
    }

    private void myinit() {
        gl.glShadeModel(GL_FLAT);
        myReshape(w, h);
    }

    public void load() {
        myinit();
        loading = 100;
    }

    private void myReshape(float w, float h) {
        gl.glViewport(0, 0, w, h);
        gl.glMatrixMode(GL_PROJECTION);
        gl.glLoadIdentity();
        gl.gluPerspective(60.0, (double) w / (double) h, 1.0, 20.0);
        gl.glMatrixMode(GL_MODELVIEW);
        gl.glLoadIdentity();
        gl.glTranslatef(0.0f, 0.0f, -5.0f);
    }
}
