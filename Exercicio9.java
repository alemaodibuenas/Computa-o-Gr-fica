package teste;
import javax.swing.JFrame;

import com.jogamp.opengl.GL;
import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLCapabilities;
import com.jogamp.opengl.GLEventListener;
import com.jogamp.opengl.GLProfile;
import com.jogamp.opengl.awt.GLJPanel;
import com.jogamp.opengl.glu.GLU;

public class Exercicio9 implements GLEventListener {
	@Override
	public void init(GLAutoDrawable drawable) {
		System.err.println("Init: " + drawable);
		GL2 gl = drawable.getGL().getGL2();
		// Set the current clear color to black and the current drawing color to
		// white.
		gl.glClearColor(0f, 0f, 0f, 1f);

		GLU glu = GLU.createGLU(gl);

		//gl.glEnable(GL2.GL_DEPTH_TEST);

		gl.glMatrixMode(GL2.GL_PROJECTION);
		gl.glLoadIdentity();
		gl.glFrustum(-1f, 1f, -1f, 1f, 0.5f, 3f);

		gl.glMatrixMode(GL2.GL_MODELVIEW);
		gl.glLoadIdentity();
		glu.gluLookAt(1f, 1f, 1f, 0f, 0f, 0f, 0f, 1f, 0f);

	}
	

	@Override
	public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height) {
		System.err.println("Reshape " + x + "/" + y + " " + width + "x" + height);
		GL2 gl = drawable.getGL().getGL2();
		// Just the glViewport for this sample
		gl.glViewport(x, y, width, height);
	}

	@Override
	public void dispose(GLAutoDrawable drawable) {
		System.err.println("Dispose");
	}

	@Override
	public void display(GLAutoDrawable drawable) {
		System.err.println("Init: " + drawable);
		GL2 gl = drawable.getGL().getGL2();
		// Set every pixel in the frame buffer to the current clear color.
		gl.glClear(GL2.GL_COLOR_BUFFER_BIT);
		gl.glColor3f(1.0f, 0.0f, 0.0f);// red

		// Draw a red x-axis, a green y-axis, and a blue z-axis.
		// Each of the axes are ten units long.
		gl.glBegin(GL.GL_LINES);
		gl.glColor3f(1f, 0f, 0f);
		gl.glVertex3f(0f, 0f, 0f);
		gl.glVertex3f(10f, 0f, 0f);

		gl.glColor3f(0f, 1f, 0f);
		gl.glVertex3f(0f, 0f, 0f);
		gl.glVertex3f(0f, 10f, 0f);

		gl.glColor3f(0f, 0f, 1f);
		gl.glVertex3f(0f, 0f, 0f);
		gl.glVertex3f(0f, 0f, 10f);

		gl.glEnd();

		// Flush drawing command buffer to make drawing happen as soon as
		// possible.
		gl.glFlush();

	}

	public static void main(String[] args) {
		JFrame app = new JFrame("Hello");
		app.setSize(800, 800);
		// this.setResizable(false);
		app.setLocationRelativeTo(null);
		app.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		GLProfile profile = GLProfile.get(GLProfile.GL2);

		GLJPanel canvas = new GLJPanel(new GLCapabilities(profile));
		canvas.addGLEventListener(new Exercicio9());
		app.getContentPane().add(canvas);

		canvas.requestFocusInWindow();
		app.setVisible(true);
	}
}
