package examples.jgl.application;


import com.harium.propan.Application3D;
import com.harium.etyl.core.graphics.Graphics;

import static org.jgl.GL.*;

public class Smooth extends Application3D {

    public Smooth(int w, int h) {
        super(w, h);
    }

    @Override
    public void load() {
        myinit();

        myReshape(w, h);

        loading = 100;
    }

    private void myinit() {
        gl.glShadeModel(GL_SMOOTH); /* GL_SMOOTH is the default */
    }

    private void triangle() {
        gl.glBegin(GL_TRIANGLES);
        gl.glColor3f(1.0f, 0.0f, 0.0f);
        gl.glVertex2f(5.0f, 5.0f);
        gl.glColor3f(0.0f, 1.0f, 0.0f);
        gl.glVertex2f(25.0f, 5.0f);
        gl.glColor3f(0.0f, 0.0f, 1.0f);
        gl.glVertex2f(5.0f, 25.0f);
        gl.glEnd();
    }

    public void draw(Graphics g) {
        gl.glClear(GL_COLOR_BUFFER_BIT);

        triangle();
        gl.glFlush(g);
    }


    private void myReshape(float w, float h) {
        gl.glViewport(0, 0, w, h);
        gl.glMatrixMode(GL_PROJECTION);
        gl.glLoadIdentity();
        if (w <= h) {
            gl.gluOrtho2D(0.0, 30.0, 0.0, 30.0 * (float) h / (float) w);
        } else {
            gl.gluOrtho2D(0.0, 30.0 * (float) w / (float) h, 0.0, 30.0);
        }
        gl.glMatrixMode(GL_MODELVIEW);
    }

}
