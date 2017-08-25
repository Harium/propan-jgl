package examples.jgl.application;

import com.harium.propan.Application3D;
import com.harium.etyl.core.graphics.Graphics;

import static org.jgl.GL.*;

public class Tea extends Application3D {

    public Tea(int w, int h) {
        super(w, h);
    }

    @Override
    public void load() {
        myinit();

        loading = 100;
    }

    private void myinit() {
        float light_ambient[] = {0.0f, 0.0f, 0.0f, 1.0f};
        float light_diffuse[] = {1.0f, 1.0f, 1.0f, 1.0f};
        float light_specular[] = {1.0f, 1.0f, 1.0f, 1.0f};
        /*  light_position is NOT default value  */
        float light_position[] = {1.0f, 1.0f, 1.0f, 0.0f};

        gl.glLightfv(GL_LIGHT0, GL_AMBIENT, light_ambient);
        gl.glLightfv(GL_LIGHT0, GL_DIFFUSE, light_diffuse);
        gl.glLightfv(GL_LIGHT0, GL_SPECULAR, light_specular);
        gl.glLightfv(GL_LIGHT0, GL_POSITION, light_position);

        gl.glFrontFace(GL_CW);
        gl.glEnable(GL_LIGHTING);
        gl.glEnable(GL_LIGHT0);
        gl.glEnable(GL_AUTO_NORMAL);
        gl.glEnable(GL_NORMALIZE);
        gl.glDepthFunc(GL_LESS);
        gl.glEnable(GL_DEPTH_TEST);

        myReshape(w, h);
    }

    @Override
    public void draw(Graphics g) {

        double eqn[] = {1.0, 0.0, -1.0, 1.0};

        float mat_diffuse[] = {0.8f, 0.8f, 0.8f, 1.0f};
        float back_diffuse[] = {0.8f, 0.2f, 0.8f, 1.0f};

        gl.glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);

        gl.glPushMatrix();
        gl.glClipPlane(GL_CLIP_PLANE0, eqn);  /*  slice objects   */
        gl.glEnable(GL_CLIP_PLANE0);

        gl.glPushMatrix();
        gl.glTranslatef(0.0f, 2.0f, 0.0f);
        gl.auxSolidTeapot(1.0);		/*  one-sided lighting  */
        gl.glPopMatrix();

		/*  two-sided lighting, but same material  */
        gl.glLightModeli(GL_LIGHT_MODEL_TWO_SIDE, GL_TRUE);
        gl.glMaterialfv(GL_FRONT_AND_BACK, GL_DIFFUSE, mat_diffuse);
        gl.glPushMatrix();
        gl.glTranslatef(0.0f, 0.0f, 0.0f);
        gl.auxSolidTeapot(1.0);
        gl.glPopMatrix();

		/*  two-sided lighting, two different materials	*/
        gl.glMaterialfv(GL_FRONT, GL_DIFFUSE, mat_diffuse);
        gl.glMaterialfv(GL_BACK, GL_DIFFUSE, back_diffuse);
        gl.glPushMatrix();
        gl.glTranslatef(0.0f, -2.0f, 0.0f);
        gl.auxSolidTeapot(1.0);
        gl.glPopMatrix();

        gl.glLightModeli(GL_LIGHT_MODEL_TWO_SIDE, GL_FALSE);
        gl.glDisable(GL_CLIP_PLANE0);
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
                    -10.0f, 10.0f);
        } else {
            gl.glOrtho(-4.0f * (float) w / (float) h,
                    4.0f * (float) w / (float) h,
                    -4.0f, 4.0f,
                    -10.0f, 10.0f);
        }
        gl.glMatrixMode(GL_MODELVIEW);
    }

}
