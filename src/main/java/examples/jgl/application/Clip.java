package examples.jgl.application;

import com.harium.propan.Application3D;
import com.harium.etyl.core.graphics.Graphics;

import static org.jgl.GL.*;

public class Clip extends Application3D {

    public Clip(int w, int h) {
        super(w, h);
    }

    public void load() {
        myinit();
        loading = 100;
    }

    public void draw(Graphics g) {
        double eqn[] = {0.0, 1.0, 0.0, 0.0};	/* y < 0 */
        double eqn2[] = {1.0, 0.0, 0.0, 0.0};	/* x < 0 */

        gl.glClear(GL_COLOR_BUFFER_BIT);

        gl.glColor3f(1.0f, 1.0f, 1.0f);
        gl.glPushMatrix();
        gl.glTranslatef(0.0f, 0.0f, -5.0f);

        gl.glClipPlane(GL_CLIP_PLANE0, eqn);
        gl.glEnable(GL_CLIP_PLANE0);
        gl.glClipPlane(GL_CLIP_PLANE1, eqn2);
        gl.glEnable(GL_CLIP_PLANE1);

        gl.glRotatef(90.0f, 1.0f, 0.0f, 0.0f);
        gl.auxWireSphere(1.0);
        gl.glPopMatrix();
        gl.glFlush(g);
    }

    private void myinit() {
        gl.glShadeModel(GL_FLAT);
        myReshape(w, h);
    }


    private void myReshape(float w, float h) {
        gl.glViewport(0, 0, w, h);
        gl.glMatrixMode(GL_PROJECTION);
        gl.glLoadIdentity();
        gl.gluPerspective(60.0, (float) w / (float) h, 1.0, 20.0);
        gl.glMatrixMode(GL_MODELVIEW);
    }

}
