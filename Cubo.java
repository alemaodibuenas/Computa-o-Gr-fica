package OpenGL3;

import java.nio.FloatBuffer;

import org.joml.Matrix4f;

import com.jogamp.common.nio.Buffers;
import com.jogamp.opengl.GL;
import com.jogamp.opengl.GL3;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLEventListener;
import com.jogamp.opengl.GLException;
import com.jogamp.opengl.util.GLBuffers;
import com.jogamp.opengl.util.glsl.ShaderCode;
import com.jogamp.opengl.util.glsl.ShaderProgram;

public class Cubo implements GLEventListener{

	private ShaderProgram shaderProgram;
	private ShaderCode vertexShader;
	private ShaderCode fragmentShader;

	private int[] vao = new int[3];
	private int[] vbo = new int[3];		

	@Override
	public void init(GLAutoDrawable canvas) {
		GL3 gl = canvas.getGL().getGL3();
		gl.glClearColor(1f, 1f, 1f, 1f);
		// Create and compile our GLSL program from the shaders
		
		gl.glDepthFunc(GL3.GL_LESS);
		
		createShaders(gl);
		
		//cria o cubo
		Cube c = new Cube();
		float[] cubo = c.getCubeData();
		float[] color = c.getCubeColorData();
		
		FloatBuffer dataBuffer = GLBuffers.newDirectFloatBuffer(cubo);
		FloatBuffer colorBuffer = GLBuffers.newDirectFloatBuffer(color);		

		// Create and Bind the Vertex Array Object first, then bind and set vertex
		// buffer(s) and attribute pointer(s).
		gl.glGenVertexArrays(1, vao, 0);
		gl.glGenBuffers(2, vbo, 0);		
		
		gl.glBindVertexArray(vao[0]);
		
		gl.glBindBuffer(GL.GL_ARRAY_BUFFER, vbo[0]);
		gl.glBufferData(GL.GL_ARRAY_BUFFER, Buffers.SIZEOF_FLOAT * dataBuffer.limit(), dataBuffer,
								GL.GL_STATIC_DRAW);	
		
		gl.glBindBuffer(GL.GL_ARRAY_BUFFER, vbo[1]);
		gl.glBufferData(GL.GL_ARRAY_BUFFER, Buffers.SIZEOF_FLOAT * colorBuffer.limit(), colorBuffer,
								GL.GL_STATIC_DRAW);	
		
		Matrix4f m = new Matrix4f().rotate(45, 1f, 1f, 0f).scale(0.3f);
		
		m.translate(1f, -1f, 0);
		
		float identidade[] = MatrixUtil.newIdentidadeMatrix();
		
		int modelLoc = gl.glGetUniformLocation(shaderProgram.program(), "model");
		gl.glUniformMatrix4fv(modelLoc, 1, false, MatrixUtil.toArray(m),0);		

		float mat[] = MatrixUtil.transpose(m);
		
		int viewLoc = gl.glGetUniformLocation(shaderProgram.program(), "view");		
		gl.glUniformMatrix4fv(viewLoc, 1, false, identidade,0);
		
		int projectionLoc = gl.glGetUniformLocation(shaderProgram.program(), "projection");		
		gl.glUniformMatrix4fv(projectionLoc, 1, false, identidade,0);
		
		System.out.print("model com transformação:");
		MatrixUtil.print(mat);
		
		System.out.print("Matriz identidade:");
		MatrixUtil.print(identidade);
		
		System.out.println();
	}	
	
	private void createShaders(GL3 gl) {
		vertexShader = ShaderCode.create(gl, GL3.GL_VERTEX_SHADER, getClass(), "shader330", null, "cubo", false);
		fragmentShader = ShaderCode.create(gl, GL3.GL_FRAGMENT_SHADER, getClass(), "shader330", null, "cubo", false);

		if (!vertexShader.compile(gl, System.err))
			throw new GLException("Couldn't compile shader: " + vertexShader);
		if (!fragmentShader.compile(gl, System.err))
			throw new GLException("Couldn't compile shader: " + fragmentShader);

		shaderProgram = new ShaderProgram();
		shaderProgram.add(gl, vertexShader, System.err);
		shaderProgram.add(gl, fragmentShader, System.err);
		shaderProgram.link(gl, System.out);
		gl.glUseProgram(shaderProgram.program());

	}
	
	public void draw(GL3 gl) {		

		gl.glEnableVertexAttribArray(0);
		gl.glEnableVertexAttribArray(1);
		
		gl.glBindBuffer(GL.GL_ARRAY_BUFFER, vbo[0]);
		gl.glVertexAttribPointer(0, 3, GL.GL_FLOAT, false, Buffers.SIZEOF_FLOAT * 3, 0);// first 0 is the location in shader
			
		gl.glBindBuffer(GL.GL_ARRAY_BUFFER, vbo[1]);
		gl.glVertexAttribPointer(1, 3, GL.GL_FLOAT, false, Buffers.SIZEOF_FLOAT * 3, 0);
		
		gl.glDrawArrays(GL.GL_TRIANGLES, 0, 36);// or drawElements
		
		gl.glDisableVertexAttribArray(0);
		gl.glDisableVertexAttribArray(1);

	}

	@Override
	public void display(GLAutoDrawable canvas) {
		System.out.println("Display: " + canvas);
		GL3 gl = canvas.getGL().getGL3();
		gl.glClear(GL.GL_DEPTH_BUFFER_BIT | GL.GL_COLOR_BUFFER_BIT);	
		
		gl.glUseProgram(shaderProgram.program());
		
		draw(gl);		
	}

	@Override
	public void reshape(GLAutoDrawable canvas, int x, int y, int width, int height) {
		System.out.println("Reshape: " + canvas);
		GL3 gl3 = canvas.getGL().getGL3();
		gl3.glViewport(x, y, width, height);
	}

	@Override
	public void dispose(GLAutoDrawable canvas) {
		System.out.println("Dispose: " + canvas);

		GL3 gl3 = canvas.getGL().getGL3();
		gl3.glDeleteBuffers(2, vbo, 0);
		gl3.glDeleteVertexArrays(1, vao, 0);

		gl3.glDeleteProgram(shaderProgram.id());
		gl3.glDeleteShader(vertexShader.id());
		gl3.glDeleteShader(fragmentShader.id());
	}

	public static void main(String[] args) {	
		
		CuboFrame.wrap("Cubo 3d", new Cubo());
	}
}
