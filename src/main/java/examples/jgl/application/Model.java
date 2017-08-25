package examples.jgl.application;


import com.harium.propan.Application3D;
import com.harium.etyl.core.graphics.Graphics;

import static org.jgl.GL.*;

public class Model extends Application3D {

    public Model(int w, int h) {
        super(w, h);
    }

    @Override
    public void load() {
        myinit();
        loading = 100;
    }

    private void draw_triangle() {
        gl.glBegin(GL_LINE_LOOP);
        gl.glVertex2f(0.0f, 25.0f);
        gl.glVertex2f(25.0f, -25.0f);
        gl.glVertex2f(-25.0f, -25.0f);
        gl.glEnd();
    }

    public void draw(Graphics g) {
        gl.glClearColor(0.0f, 0.0f, 0.0f, 1.0f);
        gl.glClear(GL_COLOR_BUFFER_BIT);

        gl.glLoadIdentity();
        gl.glColor3f(1.0f, 1.0f, 1.0f);
        draw_triangle();			/* solid lines */

        gl.glEnable(GL_LINE_STIPPLE);	/* dashed lines */
        gl.glLineStipple(1, (short) 0xF0F0);
        gl.glLoadIdentity();
        gl.glTranslatef(-20.0f, 0.0f, 0.0f);
        draw_triangle();

        gl.glLineStipple(1, (short) 0xF00F);	/* long dashed lines */
        gl.glLoadIdentity();
        gl.glScalef(1.5f, 0.5f, 1.0f);
        draw_triangle();

        gl.glLineStipple(1, (short) 0x8888);	/* dotted lines */
        gl.glLoadIdentity();
        gl.glRotatef(90.0f, 0.0f, 0.0f, 1.0f);
        draw_triangle();
        gl.glDisable(GL_LINE_STIPPLE);

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
        if (w <= h) {
            gl.glOrtho(-50.0f, 50.0f,
                    -50.0f * (float) h / (float) w,
                    50.0f * (float) h / (float) w,
                    -1.0f, 1.0f);
        } else {
            gl.glOrtho(-50.0f * (float) w / (float) h,
                    50.0f * (float) w / (float) h,
                    -50.0f, 50.0f,
                    -1.0f, 1.0f);
        }
        gl.glMatrixMode(GL_MODELVIEW);
    }

}
