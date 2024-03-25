package SlRenderer;

import org.joml.Matrix4f;
import org.lwjgl.BufferUtils;

import java.io.IOException;
import java.nio.FloatBuffer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


// UNCOMMENT THIS import static SlRenderer.slSingleBatchRenderer.OGL_MATRIX_SIZE;

import static csc133.spot.OGL_MATRIX_SIZE;
import static org.lwjgl.opengl.GL11.GL_FALSE;
import static org.lwjgl.opengl.GL20.*;
import static org.lwjgl.opengl.GL20.glGetShaderInfoLog;

public class slShaderManager {

    slShaderManager () {

    }  // slShaderManager(String vs_filename, String fs_filename)

    /* REMOVE LATER
    public  int compile_shader() {

    }  // slShaderManager(String vs_filename, String fs_filename)

    public  int compile_shader() {

        return csProgram;
    }  // public int compile_shaders()
    REMOVE LATER */

    public void set_shader_program() {

    }  // public void set_shader_program()

    public static void detach_shader() {

    }  // public static void detach_shader()

    public void loadMatrix4f(String strMatrixName, Matrix4f my_mat4) {
        // send the data in my_mat4 to strMatrixName in the shader
        // 1. Get the uniform location of strMatrix in the shader program compiled
        // 2. Create a FloatBuffer
        // 3. Load the my_mat4 data to the FloatBuffer
        // 4. Send the FloatBuffer data to uniform location

    }  //  public void loadMatrix4f(String strMatrixName, Matrix4f my_mat4)



}
