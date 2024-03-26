package SlRenderer;

import org.joml.Matrix4f;
import org.lwjgl.BufferUtils;

import java.io.File;
import java.io.IOException;
import java.nio.FloatBuffer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


// UNCOMMENT THIS import static SlRenderer.slSingleBatchRenderer.OGL_MATRIX_SIZE;

import static csc133.spot.OGL_MATRIX_SIZE;
import static org.lwjgl.opengl.GL11.GL_FALSE;
import static org.lwjgl.opengl.GL20.*;
import static org.lwjgl.opengl.GL20.glGetShaderInfoLog;

public class slShaderManager {
    private String strVertexShader;
    private String strFragmentShader;
    slShaderManager (String vs_filename, String fs_filename) {
        this.strVertexShader = readShader(vs_filename);
        this.strFragmentShader = readShader(fs_filename);

    }  // slShaderManager(String vs_filename, String fs_filename)

    // Read from the shader file and save the contents to a string
    private String readShader(String s_filename) {
        String strShader = "";
        try {
            File sLocation = new File(System.getProperty("user.dir") + "/assets/shaders/" + s_filename);
            Scanner myReader = new Scanner(sLocation);
            while (myReader.hasNextLine()) {
                strShader += myReader.nextLine();
            }
            myReader.close();

        } catch (Exception e) {
            System.out.println("Error finding file: " + e.getMessage());
        }
        return strShader;
    }

    // Overloading function, if we want to change the files and then compile
    public int compile_shader(String vs_filename, String fs_filename) {
        this.strVertexShader = readShader(vs_filename);
        this.strFragmentShader = readShader(fs_filename);
        return compile_shader();
    }  // slShaderManager(String vs_filename, String fs_filename)

    // Compile shaders based on filenames given when slShaderManager object is made
    public int compile_shader() {
        int csProgram = glCreateProgram();
        int VSID = glCreateShader(GL_VERTEX_SHADER);
        glShaderSource(VSID, this.strVertexShader);
        glCompileShader(VSID);
        glAttachShader(csProgram, VSID);
        int FSID = glCreateShader(GL_FRAGMENT_SHADER);
        glShaderSource(FSID, this.strFragmentShader);
        glCompileShader(FSID);
        glAttachShader(csProgram, FSID);
        glLinkProgram(csProgram);
        glUseProgram(csProgram);

        return csProgram;
    }  // public int compile_shaders()

    public void set_shader_program() {

    }  // public void set_shader_program()

    public static void detach_shader() {

    }  // public static void detach_shader()

    // TODO: SEE PREVIOUS ASSIGNMENT, ALL OF THIS IS PRE-EXISTING CODE!
    public void loadMatrix4f(String strMatrixName, Matrix4f my_mat4) {
        // send the data in my_mat4 to strMatrixName in the shader
        // 1. Get the uniform location of strMatrix in the shader program compiled
        // 2. Create a FloatBuffer
        // 3. Load the my_mat4 data to the FloatBuffer
        // 4. Send the FloatBuffer data to uniform location

    }  //  public void loadMatrix4f(String strMatrixName, Matrix4f my_mat4)



}