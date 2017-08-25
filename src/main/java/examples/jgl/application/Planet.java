package examples.jgl.application;

import com.harium.propan.Application3D;
import com.harium.etyl.commons.context.UpdateIntervalListener;
import com.harium.etyl.commons.event.KeyEvent;
import com.harium.etyl.core.graphics.Graphics;

import static org.jgl.GL.*;


public class Planet extends Application3D implements UpdateIntervalListener {

    public Planet(int w, int h) {
        super(w, h);
    }

    @Override
    public void load() {
        myReshape(w, h);

        updateAtFixedRate(50, this);

        loading = 100;
    }

    private static int month = 0, day = 0;

    private void dayAdd() {
        day = (day + 10) % 360;
    }

    private void daySubtract() {
        day = (day - 10) % 360;
    }

    private void monthAdd() {
        month = (month + 5) % 360;
    }

    private void monthSubtract() {
        month = (month - 5) % 360;
    }

    @Override
    public void timeUpdate(long now) {
        monthAdd();
        dayAdd();
    }

    @Override
    public void updateKeyboard(KeyEvent event) {

        if (event.isKeyDown(KeyEvent.VK_LEFT)) {
            monthSubtract();
        } else if (event.isKeyDown(KeyEvent.VK_RIGHT)) {
            monthAdd();
        } else if (event.isKeyDown(KeyEvent.VK_UP)) {
            dayAdd();
        } else if (event.isKeyDown(KeyEvent.VK_DOWN)) {
            daySubtract();
        }
    }

    @Override
    public void draw(Graphics g) {
        gl.glClear(GL_COLOR_BUFFER_BIT);

        gl.glColor3f(1.0f, 1.0f, 1.0f);
        gl.glPushMatrix();
        gl.auxWireSphere(1.0);		/* draw sun */
        gl.glRotatef((float) month, 0.0f, 1.0f, 0.0f);
        gl.glTranslatef(2.0f, 0.0f, 0.0f);
        gl.glRotatef((float) day, 0.0f, 1.0f, 0.0f);
        gl.auxWireSphere(0.2);		/* draw smaller planet */
        gl.glPopMatrix();
        gl.glFlush(g);
    }

    private void myReshape(float w, float h) {
        gl.glShadeModel(GL_FLAT);
        gl.glViewport(0, 0, w, h);
        gl.glMatrixMode(GL_PROJECTION);
        gl.glLoadIdentity();
        gl.gluPerspective(60.0, (double) w / (double) h, 1.0, 20.0);
        gl.glMatrixMode(GL_MODELVIEW);
        gl.glLoadIdentity();
        gl.glTranslatef(0.0f, 0.0f, -5.0f);
    }

}
