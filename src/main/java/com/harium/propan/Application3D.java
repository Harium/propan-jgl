package com.harium.propan;

import com.badlogic.gdx.math.Vector3;
import com.harium.etyl.commons.context.Application;
import com.harium.propan.awt.material.Texture;
import com.harium.propan.core.light.Lamp;
import com.harium.propan.linear.Box3D;
import com.harium.propan.linear.Camera;
import com.harium.propan.linear.Polygon3D;
import org.jgl.GL;
import org.jgl.GLAUX;

import java.awt.*;

import static org.jgl.GL.*;

public abstract class Application3D extends Application {

    Vector3 color = new Vector3();

    protected GLAUX gl;

    public Application3D(int w, int h) {
        super(w, h);
        gl = new GLAUX(w, h);
    }

    protected void setColor(Color color) {
        this.color.set(color.getRed() / 255f, color.getGreen() / 255f, color.getBlue() / 255f);
    }

    protected void drawPolygon(Polygon3D polygon) {
        gl.glPushMatrix();
        gl.glLoadIdentity();
        gl.glColor3f(color.x, color.y, color.z);
        gl.glTranslated(polygon.x, polygon.y, polygon.z);

        // TODO Fix
        gl.glRotated(polygon.getAngleX(), 1, 0, 0);
        gl.glRotated(polygon.getAngleY(), 0, 1, 0);
        gl.glRotated(polygon.getAngleZ(), 0, 0, 1);

        gl.glBegin(GL_QUADS);

        float x, y, z;

        for (int i = 0; i < polygon.getVertices().size(); i++) {
            x = polygon.getVertices().get(i).x;
            y = polygon.getVertices().get(i).y;
            z = polygon.getVertices().get(i).z;
            gl.glVertex3f(x, y, z);
        }
        gl.glEnd();

        gl.glPopMatrix();
    }

    protected void desenhaCaixa(Box3D box) {
        gl.glPushMatrix();
        //gl.glLoadIdentity();
        gl.glColor3f(color.x, color.y, color.z);
        gl.glTranslated(box.x, box.y, box.z);
        gl.glRotated(box.getAngleZ(), 0.0, 0.0, 1.0);
        gl.glRotated(box.getAngleY(), 0.0, 1.0, 0.0);
        gl.auxSolidBox(box.getAltura(), box.getLargura(), box.getProfundidade());
        gl.glPopMatrix();
    }

    protected void setLamp(Lamp lamp) {
        float light_position[] = {lamp.x, lamp.y, lamp.z, 0};

        gl.glLightfv(GL_LIGHT0, GL_POSITION, light_position);

        gl.glEnable(GL_LIGHTING);
        gl.glEnable(GL_LIGHT0);

        gl.glColorMaterial(GL_FRONT, GL_DIFFUSE);
        gl.glEnable(GL_COLOR_MATERIAL);
    }

    protected void lookatCamera(Camera cam) {
        Vector3 target = cam.getTarget();

        gl.gluLookAt(cam.getX(), cam.getY(), cam.getZ(), target.x, target.y, target.z, 0, 0, 1);
    }

    //Texture routines
    protected void enableTextureDefault() {

        gl.glShadeModel(GL_FLAT);

        gl.glTexEnvf(GL.GL_TEXTURE_ENV, GL.GL_TEXTURE_ENV_MODE, GL.GL_MODULATE);
        gl.glTexParameteri(GL.GL_TEXTURE_2D, GL.GL_TEXTURE_MAG_FILTER, GL.GL_LINEAR);
        gl.glTexParameteri(GL.GL_TEXTURE_2D, GL.GL_TEXTURE_MIN_FILTER, GL.GL_LINEAR);

        gl.glTexParameteri(GL.GL_TEXTURE_2D, GL.GL_TEXTURE_WRAP_S, GL.GL_REPEAT);
        gl.glTexParameteri(GL.GL_TEXTURE_2D, GL.GL_TEXTURE_WRAP_T, GL.GL_REPEAT);


        gl.glEnable(GL.GL_DEPTH_TEST);
        gl.glDepthFunc(GL.GL_LESS);

        gl.glEnable(GL.GL_TEXTURE_2D);

        gl.glEnable(GL.GL_CULL_FACE);
        gl.glCullFace(GL.GL_BACK);
    }

    protected void enableTextureNoRepeat() {

    }

    protected void setTexture(Texture texture) {

        gl.glTexImage2D(GL_TEXTURE_2D, 0, GL_RGB, (int) texture.getW(), (int) texture.getH(), 0,
                GL_RGB, GL_UNSIGNED_BYTE, texture.getBytes());

        //glTexGeni(GL_S, GL_TEXTURE_GEN_MODE, GL_OBJECT_LINEAR);

        //int sgenIparams[] = {1, 1, 1, 0};

        //glTexGeniv(GL_S, GL_OBJECT_PLANE, sgenIparams);

    }

    protected void setAlphaTexture(Texture texture) {
        //glEnable(GL_ALPHA_TEST);

        gl.glTexImage2D(GL_TEXTURE_2D, 0, GL_RGBA, (int) texture.getW(), (int) texture.getH(), 0,
                GL_RGBA, GL_UNSIGNED_BYTE, texture.getAlphaBytes());

        gl.glEnable(GL_BLEND);
        gl.glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);

    }

    protected int[] getViewPort() {
        int viewport[] = new int[4];
        gl.glGetIntegerv(GL.GL_VIEWPORT, viewport);

        return viewport;
    }

    protected double[] getModelView() {

        double modelView[] = new double[16];
        gl.glGetDoublev(GL.GL_MODELVIEW_MATRIX, modelView);

        return modelView;
    }

    protected double[] getProjection() {

        double projection[] = new double[16];

        gl.glGetDoublev(GL.GL_PROJECTION_MATRIX, projection);

        return projection;
    }

}
