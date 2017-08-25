package examples.jgl.application;

import com.harium.propan.Application3D;
import com.harium.etyl.commons.event.MouseEvent;
import com.harium.etyl.commons.event.PointerEvent;
import com.harium.etyl.core.graphics.Graphics;

import static org.jgl.GL.*;

public class ColorMaterial extends Application3D {

    public ColorMaterial(int w, int h) {
        super(w, h);
    }

    private float diffuseMaterial[] = {0.5f, 0.5f, 0.5f, 1.0f};

    private void myinit() {
        float mat_specular[] = {1.0f, 1.0f, 1.0f, 1.0f};
        float light_position[] = {1.0f, 1.0f, 1.0f, 0.0f};

        gl.glMaterialfv(GL_FRONT, GL_DIFFUSE, diffuseMaterial);
        gl.glMaterialfv(GL_FRONT, GL_SPECULAR, mat_specular);
        gl.glMaterialf(GL_FRONT, GL_SHININESS, 25.0f);
        gl.glLightfv(GL_LIGHT0, GL_POSITION, light_position);

        gl.glEnable(GL_LIGHTING);
        gl.glEnable(GL_LIGHT0);
        gl.glDepthFunc(GL_LEQUAL);
        gl.glEnable(GL_DEPTH_TEST);

        gl.glColorMaterial(GL_FRONT, GL_DIFFUSE);
        gl.glEnable(GL_COLOR_MATERIAL);

        myReshape(w, h);
    }

    private void changeRedDiffuse() {
        diffuseMaterial[0] += 0.1f;
        if (diffuseMaterial[0] > 1.0f) {
            diffuseMaterial[0] = 0.0f;
        }
        gl.glColor4fv(diffuseMaterial);
    }

    private void changeGreenDiffuse() {
        diffuseMaterial[1] += 0.1f;
        if (diffuseMaterial[1] > 1.0f) {
            diffuseMaterial[1] = 0.0f;
        }
        gl.glColor4fv(diffuseMaterial);
    }

    private void changeBlueDiffuse() {
        diffuseMaterial[2] += 0.1f;
        if (diffuseMaterial[2] > 1.0f) {
            diffuseMaterial[2] = 0.0f;
        }
        gl.glColor4fv(diffuseMaterial);
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

    @Override
    public void load() {
        myinit();
        loading = 100;
    }

    @Override
    public void updateMouse(PointerEvent event) {

        if (event.isButtonUp(MouseEvent.MOUSE_BUTTON_LEFT)) {
            changeRedDiffuse();
        }

        if (event.isButtonUp(MouseEvent.MOUSE_BUTTON_MIDDLE)) {
            changeBlueDiffuse();
        }

        if (event.isButtonUp(MouseEvent.MOUSE_BUTTON_RIGHT)) {
            changeGreenDiffuse();
        }
    }

}
