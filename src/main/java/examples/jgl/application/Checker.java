package examples.jgl.application;

import com.harium.propan.Application3D;
import com.harium.etyl.core.graphics.Graphics;

import static org.jgl.GL.*;

public class Checker extends Application3D {

    public Checker(int w, int h) {
        super(w, h);
    }

    @Override
    public void load() {
        myinit();

        loading = 100;
    }

    private static final int checkImageWidth = 64;

    private static final int checkImageHeight = 64;

    private byte checkImage[][][] =
            new byte[checkImageWidth][checkImageHeight][3];

    private void makeCheckImage() {
        int i, j;
        byte c;

        for (i = 0; i < checkImageWidth; i++) {
            for (j = 0; j < checkImageHeight; j++) {
                if ((((i & 0x8) == 0) ^ ((j & 0x8)) == 0)) {
                    c = (byte) 255;
                } else {
                    c = (byte) 0;
                }
                checkImage[i][j][0] = c;
                checkImage[i][j][1] = c;
                checkImage[i][j][2] = c;
            }
        }
    }

    private void myinit() {

        gl.glClearColor(0.0f, 0.0f, 0.0f, 0.0f);

        gl.glEnable(GL_DEPTH_TEST);
        gl.glDepthFunc(GL_LEQUAL);
        makeCheckImage();
        gl.glPixelStorei(GL_UNPACK_ALIGNMENT, 1);
        gl.glTexImage2D(GL_TEXTURE_2D, 0, 3, checkImageWidth,
                checkImageHeight, 0, GL_RGB, GL_UNSIGNED_BYTE, checkImage);
        gl.glTexParameterf(GL_TEXTURE_2D, GL_TEXTURE_WRAP_S, GL_CLAMP);
        gl.glTexParameterf(GL_TEXTURE_2D, GL_TEXTURE_WRAP_T, GL_CLAMP);
        gl.glTexParameterf(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);
        gl.glTexParameterf(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_NEAREST);
        gl.glTexEnvf(GL_TEXTURE_ENV, GL_TEXTURE_ENV_MODE, GL_DECAL);
        gl.glEnable(GL_TEXTURE_2D);
        gl.glShadeModel(GL_FLAT);

        myReshape(w, h);
    }

    public void draw(Graphics g) {
        gl.glClear(GL_COLOR_BUFFER_BIT);
        gl.glBegin(GL_QUADS);
        gl.glTexCoord2f(0.0f, 0.0f);
        gl.glVertex3f(-2.0f, -1.0f, 0.0f);
        gl.glTexCoord2f(0.0f, 1.0f);
        gl.glVertex3f(-2.0f, 1.0f, 0.0f);
        gl.glTexCoord2f(1.0f, 1.0f);
        gl.glVertex3f(0.0f, 1.0f, 0.0f);
        gl.glTexCoord2f(1.0f, 0.0f);
        gl.glVertex3f(0.0f, -1.0f, 0.0f);

        gl.glTexCoord2f(0.0f, 0.0f);
        gl.glVertex3f(1.0f, -1.0f, 0.0f);
        gl.glTexCoord2f(0.0f, 1.0f);
        gl.glVertex3f(1.0f, 1.0f, 0.0f);
        gl.glTexCoord2f(1.0f, 1.0f);
        gl.glVertex3f(2.41421f, 1.0f, -1.41421f);
        gl.glTexCoord2f(1.0f, 0.0f);
        gl.glVertex3f(2.41421f, -1.0f, -1.41421f);
        gl.glEnd();
        gl.glFlush(g);
    }

    private void myReshape(float w, float h) {
        gl.glViewport(0, 0, w, h);
        gl.glMatrixMode(GL_PROJECTION);
        gl.glLoadIdentity();
        gl.gluPerspective(60.0, 1.0 * (double) w / (double) h, 1.0, 30.0);
        gl.glMatrixMode(GL_MODELVIEW);
        gl.glLoadIdentity();
        gl.glTranslatef(0.0f, 0.0f, -3.6f);
    }

}
