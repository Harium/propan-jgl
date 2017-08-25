package examples.jgl.bezcurve;

import com.harium.propan.Application3D;
import com.harium.etyl.core.graphics.Graphics;

import static org.jgl.GL.*;

public class BezCurve extends Application3D {

    public BezCurve(int w, int h) {
        super(w, h);
    }

    public void load() {
        myinit();

        loading = 100;
    }

    private static final float ctrlpoints[][] = {
            {-4.0f, -4.0f, 0.0f},
            {-2.0f, 4.0f, 0.0f},
            {2.0f, -4.0f, 0.0f},
            {4.0f, 4.0f, 0.0f}};

    private void myinit() {

        gl.glClearColor(0.0f, 0.0f, 0.0f, 1.0f);
        gl.glMap1f(GL_MAP1_VERTEX_3, 0.0f, 1.0f, 3, 4, ctrlpoints);
        gl.glEnable(GL_MAP1_VERTEX_3);
        gl.glShadeModel(GL_FLAT);

        myReshape(w, h);
    }

    public void draw(Graphics g) {
        int i;

        gl.glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);

        gl.glColor3f(1.0f, 1.0f, 1.0f);

        gl.glBegin(GL_LINE_STRIP);

        for (i = 0; i <= 30; i++) {

            gl.glEvalCoord1f((float) i / 30.0f);

        }
        gl.glEnd();
        /* The following code displays the control points as dots. */
        gl.glPointSize(5.0f);
        gl.glColor3f(1.0f, 1.0f, 0.0f);
        gl.glBegin(GL_POINTS);

        for (i = 0; i < 4; i++) {
            gl.glVertex3fv(ctrlpoints[i]);
        }

        gl.glEnd();

        gl.glFlush(g);
    }

    private void myReshape(float w, float h) {

        gl.glViewport(0, 0, w, h);
        gl.glMatrixMode(GL_PROJECTION);
        gl.glLoadIdentity();
        if (w <= h) {
            gl.glOrtho(-5.0f, 5.0f,
                    -5.0f * (float) h / (float) w,
                    5.0f * (float) h / (float) w,
                    -5.0f, 5.0f);
        } else {
            gl.glOrtho(-5.0f * (float) w / (float) h,
                    5.0f * (float) w / (float) h,
                    -5.0f, 5.0f,
                    -5.0f, 5.0f);
        }

        gl.glMatrixMode(GL_MODELVIEW);
        gl.glLoadIdentity();
    }
}
