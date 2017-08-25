package examples.jgl.application;

import com.harium.propan.Application3D;
import com.harium.etyl.core.graphics.Graphics;

import static org.jgl.GL.*;


public class Light extends Application3D {

    public Light(int w, int h) {
        super(w, h);
    }

    public void load() {
        myinit();
        loading = 100;
    }

    private void myinit() {
        float mat_specular[] = {1.0f, 1.0f, 1.0f, 1.0f};
        float mat_shininess[] = {50.0f};
        float light_position[] = {1.0f, 1.0f, 1.0f, 0.0f};

        gl.glMaterialfv(GL_FRONT, GL_SPECULAR, mat_specular);
        gl.glMaterialfv(GL_FRONT, GL_SHININESS, mat_shininess);
        gl.glLightfv(GL_LIGHT0, GL_POSITION, light_position);

        gl.glEnable(GL_LIGHTING);
        gl.glEnable(GL_LIGHT0);
        gl.glDepthFunc(GL_LEQUAL);
        gl.glEnable(GL_DEPTH_TEST);

        myReshape(w, h);
    }

    public void draw(Graphics g) {
        gl.glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
        gl.auxSolidSphere(1.0);
        gl.glFlush(g);
    }

    private void myReshape(float w, float h) {
        gl.glViewport(0, 0, w, h);
        gl.glMatrixMode(GL_PROJECTION);
        gl.glLoadIdentity();
        if (w <= h) {
            gl.glOrtho(-1.5f, 1.5f,
                    -1.5f * (float) h / (float) w,
                    1.5f * (float) h / (float) w,
                    -10.0f, 10.0f);
        } else {
            gl.glOrtho(-1.5f * (float) w / (float) h,
                    1.5f * (float) w / (float) h,
                    -1.5f, 1.5f,
                    -10.0f, 10.0f);
        }
        gl.glMatrixMode(GL_MODELVIEW);
        gl.glLoadIdentity();
    }
}
