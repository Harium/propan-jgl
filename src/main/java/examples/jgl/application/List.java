package examples.jgl.application;


import com.harium.propan.Application3D;
import com.harium.etyl.core.graphics.Graphics;

import static org.jgl.GL.*;

public class List extends Application3D {

    public List(int w, int h) {
        super(w, h);
    }

    @Override
    public void load() {
        myinit();
        loading = 100;
    }

    private int listName = 1;

    private void drawLine() {
        gl.glBegin(GL_LINES);
        gl.glVertex2f(0.0f, 0.5f);
        gl.glVertex2f(15.0f, 0.5f);
        gl.glEnd();
    }

    public void draw(Graphics g) {
        int i;

        gl.glLoadIdentity();

        gl.glClear(GL_COLOR_BUFFER_BIT);
        gl.glColor3f(1.0f, 0.0f, 0.0f);
        for (i = 0; i < 10; i++) {
            gl.glCallList(listName);
        }
        drawLine();

        gl.glFlush(g);
    }

    private void myinit() {
        gl.glNewList(listName, GL_COMPILE);
        gl.glColor3f(1.0f, 0.0f, 0.0f);

        gl.glBegin(GL_TRIANGLES);
        gl.glVertex2f(0.0f, 0.0f);
        gl.glVertex2f(1.0f, 0.0f);
        gl.glVertex2f(0.0f, 1.0f);
        gl.glEnd();

        gl.glTranslatef(1.5f, 0.0f, 0.0f);
        gl.glEndList();

        gl.glShadeModel(GL_FLAT);

        myReshape(w, h);
    }

    private void myReshape(float w, float h) {
        gl.glViewport(0, 0, w, h);
        gl.glMatrixMode(GL_PROJECTION);
        gl.glLoadIdentity();
        if (w <= h) {
            gl.gluOrtho2D(0.0, 2.0, -0.5 * (float) h / (float) w, 1.5 * (float) h / (float) w);
        } else {
            gl.gluOrtho2D(0.0, 2.0 * (float) w / (float) h, -0.5, 1.5);
        }
        gl.glMatrixMode(GL_MODELVIEW);
        gl.glLoadIdentity();
    }

}
