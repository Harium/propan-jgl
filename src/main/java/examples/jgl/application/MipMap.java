package examples.jgl.application;

import com.harium.propan.Application3D;
import com.harium.etyl.core.graphics.Graphics;

import static org.jgl.GL.*;

public class MipMap extends Application3D {

    public MipMap(int w, int h) {
        super(w, h);
    }

    @Override
    public void load() {
        myinit();
        loading = 100;
    }

    private byte mipmapImage32[][][] = new byte[32][32][3];
    private byte mipmapImage16[][][] = new byte[16][16][3];
    private byte mipmapImage8[][][] = new byte[8][8][3];
    private byte mipmapImage4[][][] = new byte[4][4][3];
    private byte mipmapImage2[][][] = new byte[2][2][3];
    private byte mipmapImage1[][][] = new byte[1][1][3];

    private void loadImage() {
        int i, j;

        for (i = 0; i < 32; i++) {
            for (j = 0; j < 32; j++) {
                mipmapImage32[i][j][0] = (byte) 255;
                mipmapImage32[i][j][1] = (byte) 255;
                mipmapImage32[i][j][2] = (byte) 0;
            }
        }
        for (i = 0; i < 16; i++) {
            for (j = 0; j < 16; j++) {
                mipmapImage16[i][j][0] = (byte) 255;
                mipmapImage16[i][j][1] = (byte) 0;
                mipmapImage16[i][j][2] = (byte) 255;
            }
        }
        for (i = 0; i < 8; i++) {
            for (j = 0; j < 8; j++) {
                mipmapImage8[i][j][0] = (byte) 255;
                mipmapImage8[i][j][1] = (byte) 0;
                mipmapImage8[i][j][2] = (byte) 0;
            }
        }
        for (i = 0; i < 4; i++) {
            for (j = 0; j < 4; j++) {
                mipmapImage4[i][j][0] = (byte) 0;
                mipmapImage4[i][j][1] = (byte) 255;
                mipmapImage4[i][j][2] = (byte) 0;
            }
        }
        for (i = 0; i < 2; i++) {
            for (j = 0; j < 2; j++) {
                mipmapImage2[i][j][0] = (byte) 0;
                mipmapImage2[i][j][1] = (byte) 0;
                mipmapImage2[i][j][2] = (byte) 255;
            }
        }
        mipmapImage1[0][0][0] = (byte) 255;
        mipmapImage1[0][0][1] = (byte) 255;
        mipmapImage1[0][0][2] = (byte) 255;
    }

    private void myinit() {
        gl.glEnable(GL_DEPTH_TEST);
        gl.glDepthFunc(GL_LEQUAL);
        gl.glShadeModel(GL_FLAT);
        gl.glTranslatef(0.0f, 0.0f, -3.6f);
        loadImage();
        gl.glPixelStorei(GL_UNPACK_ALIGNMENT, 1);
        gl.glTexImage2D(GL_TEXTURE_2D, 0, 3, 32, 32, 0,
                GL_RGB, GL_UNSIGNED_BYTE, mipmapImage32);
        gl.glTexImage2D(GL_TEXTURE_2D, 1, 3, 16, 16, 0,
                GL_RGB, GL_UNSIGNED_BYTE, mipmapImage16);
        gl.glTexImage2D(GL_TEXTURE_2D, 2, 3, 8, 8, 0,
                GL_RGB, GL_UNSIGNED_BYTE, mipmapImage8);
        gl.glTexImage2D(GL_TEXTURE_2D, 3, 3, 4, 4, 0,
                GL_RGB, GL_UNSIGNED_BYTE, mipmapImage4);
        gl.glTexImage2D(GL_TEXTURE_2D, 4, 3, 2, 2, 0,
                GL_RGB, GL_UNSIGNED_BYTE, mipmapImage2);
        gl.glTexImage2D(GL_TEXTURE_2D, 5, 3, 1, 1, 0,
                GL_RGB, GL_UNSIGNED_BYTE, mipmapImage1);
        gl.glTexParameterf(GL_TEXTURE_2D, GL_TEXTURE_WRAP_S,
                GL_REPEAT);
        gl.glTexParameterf(GL_TEXTURE_2D, GL_TEXTURE_WRAP_T,
                GL_REPEAT);
        gl.glTexParameterf(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER,
                GL_NEAREST);
        gl.glTexParameterf(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER,
                GL_NEAREST_MIPMAP_NEAREST);
        gl.glTexEnvf(GL_TEXTURE_ENV, GL_TEXTURE_ENV_MODE, GL_DECAL);
        gl.glEnable(GL_TEXTURE_2D);

        myReshape(w, h);
    }

    public void draw(Graphics g) {
        gl.glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
        gl.glBegin(GL_QUADS);
        gl.glTexCoord2f(0.0f, 0.0f);
        gl.glVertex3f(-2.0f, -1.0f, 0.0f);
        gl.glTexCoord2f(0.0f, 8.0f);
        gl.glVertex3f(-2.0f, 1.0f, 0.0f);
        gl.glTexCoord2f(8.0f, 8.0f);
        gl.glVertex3f(2000.0f, 1.0f, -6000.0f);
        gl.glTexCoord2f(8.0f, 0.0f);
        gl.glVertex3f(2000.0f, -1.0f, -6000.0f);
        gl.glEnd();
        gl.glFlush(g);
    }

    private void myReshape(float w, float h) {
        gl.glViewport(0, 0, w, h);
        gl.glMatrixMode(GL_PROJECTION);
        gl.glLoadIdentity();
        gl.gluPerspective(60.0, 1.0 * (double) w / (double) h, 1.0, 30000.0);
        gl.glMatrixMode(GL_MODELVIEW);
        gl.glLoadIdentity();
    }
}
