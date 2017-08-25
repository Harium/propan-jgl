package examples.jgl.application;

import com.harium.propan.Application3D;
import com.harium.etyl.core.graphics.Graphics;
import org.jgl.glu.GLUnurbsObj;

import static org.jgl.GL.GL_AUTO_NORMAL;
import static org.jgl.GL.GL_COLOR_BUFFER_BIT;
import static org.jgl.GL.GL_DEPTH_BUFFER_BIT;
import static org.jgl.GL.GL_DEPTH_TEST;
import static org.jgl.GL.GL_DIFFUSE;
import static org.jgl.GL.GL_FRONT;
import static org.jgl.GL.GL_LEQUAL;
import static org.jgl.GL.GL_LIGHT0;
import static org.jgl.GL.GL_LIGHTING;
import static org.jgl.GL.GL_MAP2_VERTEX_3;
import static org.jgl.GL.GL_MODELVIEW;
import static org.jgl.GL.GL_NORMALIZE;
import static org.jgl.GL.GL_PROJECTION;
import static org.jgl.GL.GL_SHININESS;
import static org.jgl.GL.GL_SPECULAR;
import static org.jgl.GLU.*;

public class Surface extends Application3D {

    public Surface(int w, int h) {
        super(w, h);
    }

    private float ctlpoints[][][] = new float[4][4][3];
    private GLUnurbsObj theNurb;

    private void init_surface() {
        int u, v;
        for (u = 0; u < 4; u++) {
            for (v = 0; v < 4; v++) {
                ctlpoints[u][v][0] = 2.0f * ((float) u - 1.5f);
                ctlpoints[u][v][1] = 2.0f * ((float) v - 1.5f);

                if (((u == 1) || (u == 2)) && ((v == 1) || (v == 2))) {
                    ctlpoints[u][v][2] = 3.0f;
                } else {
                    ctlpoints[u][v][2] = -3.0f;
                }
            }
        }
    }

    public void load() {

        float mat_diffuse[] = {0.7f, 0.7f, 0.7f, 1.0f};
        float mat_specular[] = {1.0f, 1.0f, 1.0f, 1.0f};
        float mat_shininess[] = {100.0f};

        gl.glClearColor(0.0f, 0.0f, 0.0f, 1.0f);
        gl.glMaterialfv(GL_FRONT, GL_DIFFUSE, mat_diffuse);
        gl.glMaterialfv(GL_FRONT, GL_SPECULAR, mat_specular);
        gl.glMaterialfv(GL_FRONT, GL_SHININESS, mat_shininess);

        gl.glEnable(GL_LIGHTING);
        gl.glEnable(GL_LIGHT0);
        gl.glDepthFunc(GL_LEQUAL);
        gl.glEnable(GL_DEPTH_TEST);
        gl.glEnable(GL_AUTO_NORMAL);
        gl.glEnable(GL_NORMALIZE);

        init_surface();

        theNurb = gl.gluNewNurbsRenderer();
        gl.gluNurbsProperty(theNurb, GLU_SAMPLING_TOLERANCE, 25.0f);
        gl.gluNurbsProperty(theNurb, GLU_DISPLAY_MODE, GLU_FILL);

        myReshape(w, h);
        loading = 100;
    }

    public void draw(Graphics g) {
        float knots[] = {0.0f, 0.0f, 0.0f, 0.0f,
                1.0f, 1.0f, 1.0f, 1.0f};

        gl.glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);

        gl.glPushMatrix();
        gl.glRotatef(330.0f, 1.0f, 0.0f, 0.0f);
        gl.glScalef(0.5f, 0.5f, 0.5f);

        gl.gluBeginSurface(theNurb);
        gl.gluNurbsSurface(theNurb,
                8, knots,
                8, knots,
                4 * 3,
                3,
                ctlpoints,
                4, 4,
                GL_MAP2_VERTEX_3);
        gl.gluEndSurface(theNurb);

        gl.glPopMatrix();
        gl.glFlush(g);
    }


    private void myReshape(float w, float h) {
        gl.glViewport(0, 0, w, h);
        gl.glMatrixMode(GL_PROJECTION);
        gl.glLoadIdentity();
        gl.gluPerspective(45.0, (double) w / (double) h, 3.0, 8.0);
        gl.glMatrixMode(GL_MODELVIEW);
        gl.glLoadIdentity();
        gl.glTranslatef(0.0f, 0.0f, -5.0f);
    }

}
