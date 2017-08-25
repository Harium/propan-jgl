package examples.jgl.application;

import com.harium.propan.Application3D;
import com.harium.etyl.core.graphics.Graphics;

import static org.jgl.GL.*;

public class BezSurf extends Application3D {

    public BezSurf(int w, int h) {
        super(w, h);
    }

    public void load() {

        loading = 99;

        myinit();

        loading = 100;
    }

    private static final float ctrlpoints[][][] = {
            {{-1.5f, -1.5f, 4.0f},
                    {-0.5f, -1.5f, 2.0f},
                    {0.5f, -1.5f, -1.0f},
                    {1.5f, -1.5f, 2.0f}},
            {{-1.5f, -0.5f, 1.0f},
                    {-0.5f, -0.5f, 3.0f},
                    {0.5f, -0.5f, 0.0f},
                    {1.5f, -0.5f, -1.0f}},
            {{-1.5f, 0.5f, 4.0f},
                    {-0.5f, 0.5f, 0.0f},
                    {0.5f, 0.5f, 3.0f},
                    {1.5f, 0.5f, 4.0f}},
            {{-1.5f, 1.5f, -2.0f},
                    {-0.5f, 1.5f, -2.0f},
                    {0.5f, 1.5f, 0.0f},
                    {1.5f, 1.5f, -1.0f}}};

    private void myinit() {
        gl.glClearColor(0.0f, 0.0f, 0.0f, 1.0f);
        gl.glMap2f(GL_MAP2_VERTEX_3, 0.0f, 1.0f, 3, 4,
                0.0f, 1.0f, 12, 4, ctrlpoints);
        gl.glEnable(GL_MAP2_VERTEX_3);
        gl.glEnable(GL_DEPTH_TEST);
        gl.glShadeModel(GL_FLAT);

        myReshape(w, h);
    }

    public void draw(Graphics g) {
        int i, j;

        gl.glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
        gl.glColor3f(1.0f, 1.0f, 1.0f);
        gl.glPushMatrix();
        gl.glRotatef(85.0f, 1.0f, 1.0f, 1.0f);
        for (j = 0; j <= 8; j++) {
            gl.glBegin(GL_LINE_STRIP);
            for (i = 0; i <= 30; i++) {
                gl.glEvalCoord2f((float) i / 30.0f, (float) j / 8.0f);
            }
            gl.glEnd();
            gl.glBegin(GL_LINE_STRIP);
            for (i = 0; i <= 30; i++) {
                gl.glEvalCoord2f((float) j / 8.0f, (float) i / 30.0f);
            }
            gl.glEnd();
        }

        gl.glPopMatrix();

        gl.glFlush(g);
    }


    private void myReshape(float w, float h) {
        gl.glViewport(0, 0, w, h);
        gl.glMatrixMode(GL_PROJECTION);
        gl.glLoadIdentity();
        if (w <= h) {
            gl.glOrtho(-4.0f, 4.0f,
                    -4.0f * (float) h / (float) w,
                    4.0f * (float) h / (float) w,
                    -4.0f, 4.0f);
        } else {
            gl.glOrtho(-4.0f * (float) w / (float) h,
                    4.0f * (float) w / (float) h,
                    -4.0f, 4.0f,
                    -4.0f, 4.0f);
        }
        gl.glMatrixMode(GL_MODELVIEW);
        gl.glLoadIdentity();
    }

}
