package com.harium.propan.animation.skeletal;

import com.harium.propan.GLDrawable;
import com.harium.propan.linear.ColoredPoint3D;
import org.jgl.GLAUX;

import java.util.ArrayList;
import java.util.List;

public class Joint extends ColoredPoint3D implements GLDrawable {

    private List<Bone> bones = new ArrayList<Bone>();

    public Joint(double x, double y, double z) {
        super(x, y, z);
    }

    public void addBone(Bone bone) {
        bones.add(bone);
        bone.setOrign(this);
    }

    public void translate(double tx, double ty, double tz) {

        this.x += tx;
        this.y += ty;
        this.z += tz;

        for (Bone bone : bones) {
            bone.translate(tx, ty, tz);
        }
    }

    public void rotateXOver(double angle, double px, double py, double pz) {

        for (Bone bone : bones) {
            bone.rotateX(angle, px, py, pz);
        }
    }

    public void rotateYOver(double angle, double px, double py, double pz) {

        for (Bone bone : bones) {
            bone.rotateY(angle, px, py, pz);
        }
    }

    public void rotateZOver(double angle, double px, double py, double pz) {

        for (Bone bone : bones) {
            bone.rotateZ(angle, px, py, pz);
        }
    }

    public void draw(GLAUX gl) {
        gl.glColor3i(color.getRed(), color.getGreen(), color.getBlue());

        //gl.glPushMatrix();
        gl.glTranslated(x, y, z);

        gl.auxWireSphere(0.02);
        gl.glTranslated(-x, -y, -z);
        //gl.glPopMatrix();

        for (Bone bone : bones) {
            bone.draw(gl);
        }

    }

}
