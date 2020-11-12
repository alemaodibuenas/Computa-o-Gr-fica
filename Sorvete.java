package OpenGL3;

import java.nio.FloatBuffer;
import javax.swing.JFrame;
import com.jogamp.common.nio.Buffers;
import com.jogamp.opengl.GL;
import com.jogamp.opengl.GL3;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLCapabilities;
import com.jogamp.opengl.GLEventListener;
import com.jogamp.opengl.GLException;
import com.jogamp.opengl.GLProfile;
import com.jogamp.opengl.awt.GLJPanel;
import com.jogamp.opengl.util.GLBuffers;
import com.jogamp.opengl.util.glsl.ShaderCode;
import com.jogamp.opengl.util.glsl.ShaderProgram;

public class Sorvete implements GLEventListener {

	private ShaderProgram shaderProgram;
	private ShaderCode vertexShader;
	private ShaderCode fragmentShader;

	private int[] vao = new int[4];
	private int[] vbo = new int[4];			

	static final int numTriangles = 100;

	@Override
	public void init(GLAutoDrawable canvas) {
		GL3 gl = canvas.getGL().getGL3();
		gl.glClearColor(0f, 0f, 0f, 1f);
		// Create and compile our GLSL program from the shaders
		
		createShaders(gl);
		
		// Set up vertex data (and buffer(s)) and attribute pointers		
		float coneData[] = {-1f, 1.5f, 0f, 0.65f, 0.17f, 0.17f,1f, 1.5f, 0f, 0.65f, 0.17f, 0.17f, 0f, -4f, 0f, 0.65f, 0.17f, 0.17f};		
		FloatBuffer coneDataBuffer = GLBuffers.newDirectFloatBuffer(coneData);
		
		float circleData[] = desenhaCirculo(1f, 1f, 0f, -1f, 1f, 0.1f);
		FloatBuffer circleDataBuffer = GLBuffers.newDirectFloatBuffer(circleData);
		
		float circleData2[] = desenhaCirculo(1f, 0f, 1f, 1f, 1f, 0.1f);
		FloatBuffer circleDataBuffer2 = GLBuffers.newDirectFloatBuffer(circleData2);
		
		float circleData3[] = desenhaCirculo(1f, 1f, 1f, 0f, 2.2f, 0.1f);
		FloatBuffer circleDataBuffer3 = GLBuffers.newDirectFloatBuffer(circleData3);

		// Create and Bind the Vertex Array Object first, then bind and set vertex
		// buffer(s) and attribute pointer(s).
		gl.glGenVertexArrays(4, vao, 0);
		gl.glGenBuffers(4, vbo, 0);				

		//setup triangulo
		gl.glBindVertexArray(vao[0]);
		gl.glBindBuffer(GL.GL_ARRAY_BUFFER, vbo[0]);
		gl.glBufferData(GL.GL_ARRAY_BUFFER, Buffers.SIZEOF_FLOAT * coneDataBuffer.limit(), coneDataBuffer,
				GL.GL_STATIC_DRAW);
		
		//setup circulo1
		gl.glBindVertexArray(vao[1]);
		gl.glBindBuffer(GL.GL_ARRAY_BUFFER, vbo[1]);
		gl.glBufferData(GL.GL_ARRAY_BUFFER, Buffers.SIZEOF_FLOAT * circleDataBuffer.limit(), circleDataBuffer,
				GL.GL_STATIC_DRAW);		
		
		//setup circulo2
		gl.glBindVertexArray(vao[2]);
		gl.glBindBuffer(GL.GL_ARRAY_BUFFER, vbo[2]);
		gl.glBufferData(GL.GL_ARRAY_BUFFER, Buffers.SIZEOF_FLOAT * circleDataBuffer2.limit(), circleDataBuffer2,
				GL.GL_STATIC_DRAW);	
		
		//setup circulo3
		gl.glBindVertexArray(vao[3]);
		gl.glBindBuffer(GL.GL_ARRAY_BUFFER, vbo[3]);
		gl.glBufferData(GL.GL_ARRAY_BUFFER, Buffers.SIZEOF_FLOAT * circleDataBuffer3.limit(), circleDataBuffer3,
				GL.GL_STATIC_DRAW);			
	}	
	
	public float[] desenhaCirculo(float r, float g,float b, float cx,float cy, float radius) {		
		float vertices[] = new float[(int)(numTriangles + 1) *6 ];
		
		float twicePi = 2.0f * (float) Math.PI;			
		
		for(int i=0;i<= numTriangles;i++) {
			float delta = twicePi/numTriangles;
			float x = (float)(1 * Math.cos(i * delta));
			float y = (float)(1 * Math.sin(i * delta));	
			int aux = i *6;
			vertices[aux] = cx + x;
			vertices[aux + 1] = cy + y;
			vertices[aux + 2] = 0;
			
			vertices[aux+3] = r;
			vertices[aux+4] = g;
			vertices[aux+5] = b;

			}	
		return vertices;
	}

	private void createShaders(GL3 gl) {
		vertexShader = ShaderCode.create(gl, GL3.GL_VERTEX_SHADER, getClass(), "shader330", null, "sorvete", false);
		fragmentShader = ShaderCode.create(gl, GL3.GL_FRAGMENT_SHADER, getClass(), "shader330", null, "sorvete", false);

		if (!vertexShader.compile(gl, System.err))
			throw new GLException("Couldn't compile shader: " + vertexShader);
		if (!fragmentShader.compile(gl, System.err))
			throw new GLException("Couldn't compile shader: " + fragmentShader);

		shaderProgram = new ShaderProgram();
		shaderProgram.add(gl, vertexShader, System.err);
		shaderProgram.add(gl, fragmentShader, System.err);
		shaderProgram.link(gl, System.out);
	}
	
	public void draw(GL3 gl) {	
	
		gl.glEnableVertexAttribArray(0);
		gl.glEnableVertexAttribArray(1);

		//triangulo
		gl.glBindBuffer(GL.GL_ARRAY_BUFFER, vbo[0]);
		gl.glVertexAttribPointer(0, 3, GL.GL_FLOAT, false,Buffers.SIZEOF_FLOAT * 6, 0);// first 0 is the location in shader
		gl.glVertexAttribPointer(1, 3, GL.GL_FLOAT, false,Buffers.SIZEOF_FLOAT * 6, Buffers.SIZEOF_FLOAT * 3);// first 0 is the location in shader
		gl.glDrawArrays(GL.GL_TRIANGLES, 0, 3);// or drawElements		

		//circulo1		
		gl.glBindBuffer(GL.GL_ARRAY_BUFFER, vbo[1]);
		gl.glVertexAttribPointer(0, 3, GL.GL_FLOAT, false,Buffers.SIZEOF_FLOAT * 6, 0);// first 0 is the location in shader
		gl.glVertexAttribPointer(1, 3, GL.GL_FLOAT, false,Buffers.SIZEOF_FLOAT * 6, Buffers.SIZEOF_FLOAT * 3);// first 0 is the location in shader
		gl.glDrawArrays(GL.GL_TRIANGLE_FAN, 0, 360);// or drawElements
		
		//circulo2		
		gl.glBindBuffer(GL.GL_ARRAY_BUFFER, vbo[2]);
		gl.glVertexAttribPointer(0, 3, GL.GL_FLOAT, false,Buffers.SIZEOF_FLOAT * 6, 0);// first 0 is the location in shader
		gl.glVertexAttribPointer(1, 3, GL.GL_FLOAT, false,Buffers.SIZEOF_FLOAT * 6, Buffers.SIZEOF_FLOAT * 3);// first 0 is the location in shader
		gl.glDrawArrays(GL.GL_TRIANGLE_FAN, 0, 360);// or drawElements	
		
		//circulo3		
		gl.glBindBuffer(GL.GL_ARRAY_BUFFER, vbo[3]);
		gl.glVertexAttribPointer(0, 3, GL.GL_FLOAT, false,Buffers.SIZEOF_FLOAT * 6, 0);// first 0 is the location in shader
		gl.glVertexAttribPointer(1, 3, GL.GL_FLOAT, false,Buffers.SIZEOF_FLOAT * 6, Buffers.SIZEOF_FLOAT * 3);// first 0 is the location in shader		gl.glBindAttribLocation(shaderProgram.program(), 0, "vertexPosition");// name of attribute in shader	
		gl.glDrawArrays(GL.GL_TRIANGLE_FAN, 0, 360);// or drawElements
		
		gl.glDisableVertexAttribArray(0);
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
		gl3.glDeleteBuffers(4, vbo, 0);
		gl3.glDeleteVertexArrays(4, vao, 0);

		gl3.glDeleteProgram(shaderProgram.id());
		gl3.glDeleteShader(vertexShader.id());
		gl3.glDeleteShader(fragmentShader.id());
	}

	public static void main(String[] args) {
		GLProfile profile = GLProfile.get(GLProfile.GL3);

		GLJPanel canvas = new GLJPanel(new GLCapabilities(profile));
		canvas.addGLEventListener(new Sorvete());

		JFrame app = new JFrame("Sorvete");
		app.setSize(800, 800);
		app.setLocationRelativeTo(null);
		app.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		app.getContentPane().add(canvas);
		app.setVisible(true);
	}
}
