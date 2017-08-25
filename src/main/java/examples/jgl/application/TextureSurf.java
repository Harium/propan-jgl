package examples.jgl.application;

import com.harium.propan.Application3D;
import com.harium.etyl.core.graphics.Graphics;

import static org.jgl.GL.*;

public class TextureSurf extends Application3D {

    public TextureSurf(int w, int h) {
        super(w, h);
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

    private static final float texpts[][][] = {
            {{0.0f, 0.0f},
                    {0.0f, 1.0f}},
            {{1.0f, 0.0f},
                    {1.0f, 1.0f}}};

    private static final int imageWidth = 64;
    private static final int imageHeight = 64;

    private byte[][][] loadImage() {

        byte image[][][] = new byte[imageHeight][imageWidth][3];

        int i, j;
        float ti, tj;

        for (i = 0; i < imageWidth; i++) {
            ti = (float) (2.0 * Math.PI * i / imageWidth);
            for (j = 0; j < imageHeight; j++) {
                tj = (float) (2.0 * Math.PI * j / imageHeight);
                image[j][i][0] = (byte) (127 * (1.0 + Math.sin(ti)));
                image[j][i][1] = (byte) (127 * (1.0 + Math.cos(2 * tj)));
                image[j][i][2] = (byte) (127 * (1.0 + Math.cos(ti + tj)));
            }
        }

        return image;
    }

    @Override
    public void load() {

        gl.glMap2f(GL_MAP2_VERTEX_3, 0.0f, 1.0f, 3, 4,
                0.0f, 1.0f, 12, 4, ctrlpoints);

        gl.glMap2f(GL_MAP2_TEXTURE_COORD_2, 0.0f, 1.0f, 2, 2,
                0.0f, 1.0f, 4, 2, texpts);

        gl.glEnable(GL_MAP2_TEXTURE_COORD_2);
        gl.glEnable(GL_MAP2_VERTEX_3);
        gl.glMapGrid2f(20, 0.0f, 1.0f, 20, 0.0f, 1.0f);

        byte[][][] image = loadImage();

        gl.glTexEnvf(GL_TEXTURE_ENV, GL_TEXTURE_ENV_MODE, GL_DECAL);
        gl.glTexParameterf(GL_TEXTURE_2D, GL_TEXTURE_WRAP_S, GL_REPEAT);
        gl.glTexParameterf(GL_TEXTURE_2D, GL_TEXTURE_WRAP_T, GL_REPEAT);
        gl.glTexParameterf(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);
        gl.glTexParameterf(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_NEAREST);
        gl.glTexImage2D(GL_TEXTURE_2D, 0, 3, imageWidth, imageHeight,
                0, GL_RGB, GL_UNSIGNED_BYTE, image);

        gl.glEnable(GL_TEXTURE_2D);
        gl.glEnable(GL_DEPTH_TEST);
        gl.glEnable(GL_NORMALIZE);
        gl.glShadeModel(GL_FLAT);

        myReshape(w, h);

        loading = 100;
    }

    @Override
    public void draw(Graphics g) {
        gl.glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
        gl.glColor3f(1.0f, 1.0f, 1.0f);
        gl.glEvalMesh2(GL_FILL, 0, 20, 0, 20);
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
        gl.glRotatef(85.0f, 1.0f, 1.0f, 1.0f);
    }

}
