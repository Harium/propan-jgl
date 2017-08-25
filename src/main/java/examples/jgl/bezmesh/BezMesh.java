package examples.jgl.bezmesh;

import com.harium.propan.Application3D;
import com.harium.etyl.core.graphics.Graphics;

import static org.jgl.GL.*;

public class BezMesh extends Application3D {

    public BezMesh(int w, int h) {
        super(w, h);
    }

    public void load() {
        loading = 0;

        myinit();

        loading = 100;
    }

    private static final float ctrlpoints[][][] = {
        /*
		{{-1.5f, -1.5f,  4.0f},
			{-0.5f, -1.5f,  2.0f},
			{ 0.5f, -1.5f, -1.0f},
			{ 1.5f, -1.5f,  2.0f}},
			{{-1.5f, -0.5f,  1.0f},
				{-0.5f, -0.5f,  3.0f},
				{ 0.5f, -0.5f,  0.0f},
				{ 1.5f, -0.5f, -1.0f}},
				{{-1.5f,  0.5f,  4.0f},
					{-0.5f,  0.5f,  0.0f},
					{ 0.5f,  0.5f,  3.0f},
					{ 1.5f,  0.5f,  4.0f}},
					{{-1.5f,  1.5f, -2.0f},
						{-0.5f,  1.5f, -2.0f},
						{ 0.5f,  1.5f,  0.0f},
						{ 1.5f,  1.5f, -1.0f}}};
		 */
            // Points
            {{0.0f, 0.0f, 0.0f},
                    {0.0f, 0.0f, 1.0f},
                    {0.0f, -1f, 0f},
                    {1f, 0f, 0f}},
            {{0f, 0f, 1.0f},
                    {1f, 0f, 1f},
                    {0f, 1f, 1f},
                    {1f, 1f, 1f}},
            {{-1.5f, 0.5f, 4.0f},
                    {-0.5f, 0.5f, 0.0f},
                    {0.5f, 0.5f, 3.0f},
                    {1.5f, 0.5f, 4.0f}},
            {{-1.5f, 1.5f, -2.0f},
                    {-0.5f, 1.5f, -2.0f},
                    {0.5f, 1.5f, 0.0f},
                    {1.5f, 1.5f, -1.0f}}};


    private void initlights() {
        float ambient[] = {0.2f, 0.2f, 0.2f, 1.0f};
        float position[] = {0.0f, 0.0f, 2.0f, 1.0f};
        float mat_diffuse[] = {0.6f, 0.6f, 0.6f, 1.0f};
        float mat_specular[] = {1.0f, 1.0f, 1.0f, 1.0f};
        float mat_shininess[] = {50.0f};

        gl.glEnable(GL_LIGHTING);
        gl.glEnable(GL_LIGHT0);
        gl.glLightfv(GL_LIGHT0, GL_AMBIENT, ambient);
        gl.glLightfv(GL_LIGHT0, GL_POSITION, position);

        gl.glMaterialfv(GL_FRONT, GL_DIFFUSE, mat_diffuse);
        gl.glMaterialfv(GL_FRONT, GL_SPECULAR, mat_specular);
        gl.glMaterialfv(GL_FRONT, GL_SHININESS, mat_shininess);
    }

    private void myinit() {
        gl.glClearColor(0.0f, 0.0f, 0.0f, 1.0f);
        gl.glMap2f(GL_MAP2_VERTEX_3, 0.0f, 1.0f, 3, 4,
                0.0f, 1.0f, 12, 4, ctrlpoints);
        gl.glEnable(GL_MAP2_VERTEX_3);
        gl.glEnable(GL_AUTO_NORMAL);
        gl.glEnable(GL_NORMALIZE);
        gl.glMapGrid2f(20, 0.0f, 1.0f, 20, 0.0f, 1.0f);
        initlights();		/* for lighted version only */

        myReshape(w, h);
    }

    public void draw(Graphics g) {
        gl.glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
        gl.glPushMatrix();
        gl.glRotatef(85.0f, 1.0f, 1.0f, 1.0f);
        gl.glEvalMesh2(GL_FILL, 0, 20, 0, 20);
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
