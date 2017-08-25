package examples.jgl.application;

import com.harium.propan.Application3D;
import com.harium.etyl.core.graphics.Graphics;

import static org.jgl.GL.*;

public class TextGen extends Application3D {

    public TextGen(int w, int h) {
        super(w, h);
    }

    private final int stripeImageWidth = 32;
    private byte stripeImage[][] = new byte[stripeImageWidth][3];

    private void makeStripeImage() {
        int j;

        for (j = 0; j < stripeImageWidth; j++) {
            if (j <= 4) stripeImage[j][0] = (byte) 255;
            else stripeImage[j][0] = (byte) 0;
            if (j > 4) stripeImage[j][1] = (byte) 255;
            else stripeImage[j][1] = (byte) 0;
            stripeImage[j][2] = 0;
        }
    }

    /* gl.glTexGen stuff: */
    float sgenparams[] = {1.0f, 1.0f, 1.0f, 0.0f};

    @Override
    public void load() {

        gl.glClearColor(0.0f, 0.0f, 0.0f, 0.0f);

        makeStripeImage();

        gl.glPixelStorei(GL_UNPACK_ALIGNMENT, 1);
        gl.glTexEnvf(GL_TEXTURE_ENV, GL_TEXTURE_ENV_MODE, GL_MODULATE);
        gl.glTexParameterf(GL_TEXTURE_1D, GL_TEXTURE_WRAP_S, GL_REPEAT);
        gl.glTexParameterf(GL_TEXTURE_1D, GL_TEXTURE_MAG_FILTER, GL_LINEAR);
        gl.glTexParameterf(GL_TEXTURE_1D, GL_TEXTURE_MIN_FILTER, GL_LINEAR);
        gl.glTexImage1D(GL_TEXTURE_1D, 0, 3, stripeImageWidth, 0,
                GL_RGB, GL_UNSIGNED_BYTE, stripeImage);

        gl.glTexGeni(GL_S, GL_TEXTURE_GEN_MODE, GL_OBJECT_LINEAR);
        gl.glTexGenfv(GL_S, GL_OBJECT_PLANE, sgenparams);

        gl.glEnable(GL_DEPTH_TEST);
        gl.glDepthFunc(GL_LESS);
        gl.glEnable(GL_TEXTURE_GEN_S);
        gl.glEnable(GL_TEXTURE_1D);
        gl.glEnable(GL_CULL_FACE);
        gl.glEnable(GL_LIGHTING);
        gl.glEnable(GL_LIGHT0);
        gl.glEnable(GL_AUTO_NORMAL);
        gl.glEnable(GL_NORMALIZE);
        gl.glFrontFace(GL_CW);
        gl.glCullFace(GL_BACK);
        gl.glMaterialf(GL_FRONT, GL_SHININESS, 64.0f);

        myReshape(w, h);

        loading = 100;
    }

    @Override
    public void draw(Graphics g) {
        gl.glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);

        gl.glPushMatrix();
        gl.glRotatef(45.0f, 0.0f, 0.0f, 1.0f);
        gl.auxSolidTeapot(2.0);
        gl.glPopMatrix();
        gl.glFlush(g);
    }

    private void myReshape(float w, float h) {
        gl.glViewport(0, 0, w, h);
        gl.glMatrixMode(GL_PROJECTION);
        gl.glLoadIdentity();
        if (w <= h) {
            gl.glOrtho(-3.5f, 3.5f,
                    -3.5f * (float) h / (float) w,
                    3.5f * (float) h / (float) w,
                    -3.5f, 3.5f);
        } else {
            gl.glOrtho(-3.5f * (float) w / (float) h,
                    3.5f * (float) w / (float) h,
                    -3.5f, 3.5f,
                    -3.5f, 3.5f);
        }
        gl.glMatrixMode(GL_MODELVIEW);
        gl.glLoadIdentity();
    }
}
