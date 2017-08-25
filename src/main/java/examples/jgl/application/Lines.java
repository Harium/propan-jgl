package examples.jgl.application;

import com.harium.propan.Application3D;
import com.harium.etyl.core.graphics.Graphics;
import org.jgl.GL;

import static org.jgl.GL.*;


public class Lines extends Application3D {

    public Lines(int w, int h) {
        super(w, h);
    }

    @Override
    public void load() {
        myinit();
        loading = 100;
    }

    private void drawOneLine(float x1, float y1, float x2, float y2) {
        gl.glBegin(GL_LINES);
        gl.glVertex2f(x1, y1);
        gl.glVertex2f(x2, y2);
        gl.glEnd();
    }

    public void draw(Graphics g) {
        int i;

        gl.glClear(GL_COLOR_BUFFER_BIT);

		/* draw all lines in white */
        gl.glColor3f(1.0f, 1.0f, 1.0f);

		/* in 1st row, 3 lines, each with a different stipple */
        gl.glEnable(GL_LINE_STIPPLE);
        gl.glLineStipple(1, (short) 0x0101);	/* dotted */
        drawOneLine(50.0f, 125.0f, 150.0f, 125.0f);
        gl.glLineStipple(1, (short) 0x00FF);	/* dashed */
        drawOneLine(150.0f, 125.0f, 250.0f, 125.0f);
        gl.glLineStipple(1, (short) 0x1C47);	/* dash/dot/dash */
        drawOneLine(250.0f, 125.0f, 350.0f, 125.0f);

		/* in 2nd row, 3 width lines, each with different stipple */
        gl.glLineWidth(5.0f);
        gl.glLineStipple(1, (short) 0x0101);
        drawOneLine(50.0f, 100.0f, 150.0f, 100.0f);
        gl.glLineStipple(1, (short) 0x00FF);
        drawOneLine(150.0f, 100.0f, 250.0f, 100.0f);
        gl.glLineStipple(1, (short) 0x1C47);
        drawOneLine(250.0f, 100.0f, 350.0f, 100.0f);
        gl.glLineWidth(1.0f);

		/* in 3rd row, 6 lines, with dash/dot/dash stipple, */
        /* as part of a single connected line strip */
        gl.glLineStipple(1, (short) 0x1C47);
        gl.glBegin(GL_LINE_STRIP);
        for (i = 0; i < 7; i++) {
            gl.glVertex2f(50.0f + ((float) i * 50.0f), 75.0f);
        }
        gl.glEnd();

		/* in 4th row, 6 independent lines, */
        /* with dash/dot/dash stipple */
        for (i = 0; i < 6; i++) {
            drawOneLine(50.0f + ((float) i * 50.0f), 50.0f,
                    50.0f + ((float) (i + 1) * 50.0f), 50.0f);
        }

		/* in 5th row, 1 line, with dash/dot/dash stipple */
		/* and repeat factor of 5 */
        gl.glLineStipple(1, (short) 0x1C47);
        drawOneLine(50.0f, 25.0f, 350.0f, 25.0f);

        gl.glFlush(g);
    }

    private void myinit() {
		/* background to be cleared to black */
        gl.glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
        gl.glShadeModel(GL_FLAT);

        myReshape(w, h);
    }

    public void myReshape(float w, float h) {
        gl.glViewport(0, 0, w, h);
        gl.glMatrixMode(GL.GL_PROJECTION);
        gl.glLoadIdentity();
        gl.gluOrtho2D(0.0, (double) w, 0.0, (double) h);
    }
}
