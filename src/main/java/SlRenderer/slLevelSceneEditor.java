package SlRenderer;

import org.joml.Vector3f;
import org.lwjgl.BufferUtils;

import java.nio.FloatBuffer;
import java.nio.IntBuffer;

import static org.lwjgl.opengl.ARBVertexArrayObject.glBindVertexArray;
import static org.lwjgl.opengl.ARBVertexArrayObject.glGenVertexArrays;
import static org.lwjgl.opengl.GL20.*;
import static csc133.spot.*;

public class slLevelSceneEditor {

    private final Vector3f my_camera_location = new Vector3f(0f, 0f, 0f);
    private slShaderManager testShader;
    private slTextureManager testTexture;

    private float xmin = POLY_OFFSET, ymin = POLY_OFFSET, zmin = 0.0f, xmax = xmin+ SQUARE_LENGTH,
                            ymax = ymin+ SQUARE_LENGTH, zmax = 0.0f;

    private final float uvmin = 0.0f, uvmax = 1.0f;

    private final float [] vertexArray = {
            // vertices -        colors                   UV coordinates
            xmin, ymin, zmin, 1.0f, 1.0f, 0.0f, 1.0f, uvmin, uvmin, // 0,0
            xmax, ymin, zmin, 1.0f, 1.0f, 0.0f, 1.0f, uvmax, uvmin, // 1,0
            xmax, ymax, zmax, 1.0f, 1.0f, 0.0f, 1.0f, uvmax, uvmax, // 1,1
            xmin, ymax, zmax, 1.0f, 1.0f, 0.0f, 1.0f, uvmin, uvmax  // 0,1
    };

    private final int[] rgElements = { 0, 1, 2, 0, 2, 3 };
    int positionStride = 3;
    int colorStride = 4;
    int textureStride = 2;
    int vertexStride = (positionStride + colorStride + textureStride) * Float.BYTES;

    private int vaoID, vboID, eboID;
    final private int  vpoIndex = 0, vcoIndex = 1, vtoIndex = 2;

    slCamera my_camera;

    public slLevelSceneEditor() {

    }

    public void init() {
        my_camera = new slCamera(new Vector3f(my_camera_location));
        my_camera.setOrthoProjection();

        testShader = new slShaderManager("vs_texture_1.glsl", "fs_texture_1.glsl");

        testShader.compile_shader();

        testTexture = new slTextureManager(System.getProperty("user.dir") + "/assets/textures/MarioWithGun.png");

        vaoID = glGenVertexArrays();
        glBindVertexArray(vaoID);

        FloatBuffer vertexBuffer = BufferUtils.createFloatBuffer(vertexArray.length);
        vertexBuffer.put(vertexArray).flip();

        vboID = glGenBuffers();
        glBindBuffer(GL_ARRAY_BUFFER, vboID);
        // GL_STATIC_DRAW good for now; we can later change to dynamic vertices:
        glBufferData(GL_ARRAY_BUFFER, vertexBuffer, GL_STATIC_DRAW);

        IntBuffer elementBuffer = BufferUtils.createIntBuffer(rgElements.length);
        elementBuffer.put(rgElements).flip();

        eboID = glGenBuffers();
        glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, eboID);
        glBufferData(GL_ELEMENT_ARRAY_BUFFER, elementBuffer, GL_STATIC_DRAW);

        glVertexAttribPointer(vpoIndex, positionStride, GL_FLOAT, false, vertexStride, 0);
        glEnableVertexAttribArray(vpoIndex);

        glVertexAttribPointer(vcoIndex, colorStride, GL_FLOAT, false, vertexStride, positionStride * Float.BYTES);
        glEnableVertexAttribArray(vcoIndex);

        glVertexAttribPointer(vtoIndex, textureStride, GL_FLOAT, false, vertexStride, (colorStride + positionStride) * Float.BYTES);
        glEnableVertexAttribArray(vtoIndex);

    }

    public void update(float dt) {

        my_camera.relativeMoveCamera(dt*VFactor, dt*VFactor);

        if (my_camera.getCurLookFrom().x < -FRUSTUM_RIGHT) {
            my_camera.setCurLookFrom(my_camera_location);
            my_camera.setOrthoProjection();
        }

        testShader.set_shader_program();
        testTexture.bind_texture();

        testShader.loadMatrix4f("uProjMatrix", my_camera.getProjectionMatrix());
        testShader.loadMatrix4f("uViewMatrix", my_camera.getViewMatrix());

        glBindVertexArray(vaoID);

        glEnableVertexAttribArray(vpoIndex);
        glEnableVertexAttribArray(vcoIndex);
        glEnableVertexAttribArray(vtoIndex);
        glDrawElements(GL_TRIANGLES, rgElements.length, GL_UNSIGNED_INT, 0);

        glDisableVertexAttribArray(vpoIndex);
        glDisableVertexAttribArray(vcoIndex);
        glDisableVertexAttribArray(vtoIndex);

        glBindVertexArray(0);
        testShader.detach_shader();
        testTexture.unbind_texture();
    }

}
