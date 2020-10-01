package teste;

import javax.swing.JFrame;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLCapabilities;
import com.jogamp.opengl.GLEventListener;
import com.jogamp.opengl.GLProfile;
import com.jogamp.opengl.awt.GLJPanel;
import com.jogamp.opengl.util.gl2.GLUT;

public class Exercicio10 implements GLEventListener {
	@Override
	public void init(GLAutoDrawable drawable) {
		System.err.println("Init: " + drawable);
		GL2 gl = drawable.getGL().getGL2();
		// Set the current clear color to black and the current drawing color to
		// white.
		gl.glClearColor(0.3f, 0.3f, 0.3f, 0.3f);
	}

	
	private void desenhaRetas(GL2 gl, float a, float b, float c, float d) {
		
	    gl.glLineWidth(9.0f);//largura da linha
		gl.glColor4f(1.0f, 1.0f, 1.0f, 0.0f);//white
		gl.glBegin(GL2.GL_LINES);
		gl.glVertex2d(a,b);
		gl.glVertex2d(c,d);
		gl.glEnd();
	}
	
	private void desenhaCasa(GL2 gl, float a, float b, float c, float d) {
		
	    //gl.glLineWidth(9.0f);//largura da linha
		gl.glColor4f(1.0f, 0.0f, 0.0f, 0.0f);
		gl.glBegin(GL2.GL_LINES);
		gl.glVertex2d(a,b);
		gl.glVertex2d(c,d);
		gl.glEnd();
	}
	
	private void desenhaRetangulo(GL2 gl, float a, float b, float c, float d, float e, float f, float g, float h) {
		
	    //gl.glLineWidth(9.0f);//largura da linha
		gl.glColor4f(1.0f, 0.0f, 0.0f, 0.0f);//red
		gl.glBegin(GL2.GL_LINE_LOOP);                          // Draw A Quad
		gl.glVertex3f(a, b, 0f);                          // Top Left
		gl.glVertex3f(c, d, 0f);                          // Top Right
		gl.glVertex3f(e, f, 0f);                          // Bottom Right
		gl.glVertex3f(g, h, .0f);                          // Bottom Left
		gl.glEnd();
	}
	
	
	private void desenha(GL2 gl) {				
			
		//casa
		gl.glPushMatrix();
		gl.glScaled(0.1f,0.1f, 1f);	
		gl.glTranslatef(-0.4f,-0.3f,0);
		desenhaRetangulo(gl, -0.3f, 0.3f, 0.3f, 0.3f, 0.3f, -0.6f, -0.3f, -0.6f);//retangulo
		gl.glPopMatrix();
		
		gl.glPushMatrix();
		gl.glScaled(0.1f,0.1f, 1f);	
		gl.glTranslatef(-0.4f, 0f, 0f);
		desenhaCasa(gl, -0.3f, 0f, 0f, 0.6f);//reta vertical
		gl.glPopMatrix();
		
		gl.glPushMatrix();
		gl.glScaled(0.1f,0.1f, 1f);	
		gl.glTranslatef(-0.4f, 0f, 0f);
		desenhaCasa(gl, 0.3f, 0f, 0f, 0.6f);//reta vertical
		gl.glPopMatrix();
		
		gl.glPushMatrix();
		gl.glScaled(0.1f,0.1f, 1f);	
		gl.glTranslatef(0.3f, -0.9f, 0f);		
		desenhaCasa(gl, -0.4f, 0f, 0.4f, 0.2f);//reta 
		gl.glPopMatrix();
		
		gl.glPushMatrix();
		gl.glScaled(0.1f,0.1f, 1f);	
		gl.glTranslatef(0.3f, 0f, 0f);
		desenhaCasa(gl, -0.4f, 0f, 0.4f, 0.2f);//reta parede
		gl.glPopMatrix();
		
		gl.glPushMatrix();
		gl.glScaled(0.1f,0.1f, 1f);	
		gl.glTranslatef(0.7f, -0.3f, 0f);
		desenhaCasa(gl, 0f, -0.4f, 0f, 0.5f);//reta parede
		gl.glPopMatrix();
		
		gl.glPushMatrix();
		gl.glScaled(0.1f,0.1f, 1f);	
		gl.glTranslatef(0f, 0.6f, 0f);
		desenhaCasa(gl, -0.4f, 0f, 0.4f, 0.2f);//reta do teto 
		gl.glPopMatrix();
		
		gl.glPushMatrix();
		gl.glScaled(0.1f,0.1f, 1f);	
		gl.glTranslatef(0.7f, 0.6f, 0f);
		desenhaCasa(gl, 0f, -0.4f, -0.3f, 0.2f);//reta parede
		gl.glPopMatrix();
		
		
		//Mapa
		gl.glPushMatrix();
		gl.glTranslatef(-0.7f, 0f, 0f);
		desenhaRetas(gl, 0f, -1f, 0.3f, 1f);//reta 1
		gl.glPopMatrix();
		
		gl.glPushMatrix();
		gl.glTranslatef(-0.3f, 0f, 0f);
		desenhaRetas(gl, 0f, -1f, 0.3f, 1f);//reta 2
		gl.glPopMatrix();
		
		gl.glPushMatrix();
		gl.glTranslatef(0.1f, 0f, 0f);
		desenhaRetas(gl, 0f, -1f, 0.3f, 1f);//reta 3
		gl.glPopMatrix();
		
		gl.glPushMatrix();
		gl.glTranslatef(0.45f, 0f, 0f);
		desenhaRetas(gl, 0.2f, -1f, 0.3f, 1f);//reta 3
		gl.glPopMatrix();		
		
		gl.glPushMatrix();
		gl.glTranslatef(0f, -0.3f, 0f);
		desenhaRetas(gl, -1f, 0f, 0.7f, 0.2f);//reta 3
		gl.glPopMatrix();	
		
		gl.glPushMatrix();
		gl.glTranslatef(0f, 0f, 0f);
		desenhaRetas(gl, -1f, 0f, 0.7f, 0.2f);//reta 3
		gl.glPopMatrix();
		
		gl.glPushMatrix();
		gl.glTranslatef(0f, -0.5f, 0f);
		desenhaRetas(gl, -1f, 0f, 1f, 0f);//reta 3
		gl.glPopMatrix();	
		
		
		gl.glPushMatrix();
		gl.glTranslatef(0f, -0.5f, 0f);
		desenhaRetas(gl, -1f, 0f, 1f, 0f);//reta 3
		gl.glPopMatrix();	
		
		gl.glPushMatrix();
		gl.glTranslatef(0f, 0.8f, 0f);
		desenhaRetas(gl, -1f, 0f, 1f, 0f);//reta 3
		gl.glPopMatrix();	
		
		gl.glPushMatrix();
		gl.glTranslatef(0f, -0.8f, 0f);
		desenhaRetas(gl, -1f, 0f, 1f, 0f);//reta 3
		gl.glPopMatrix();	
		
		gl.glPushMatrix();
		gl.glTranslatef(0f, 0.5f, 0f);
		desenhaRetas(gl, -1f, 0f, 1f, 0f);//reta 3
		gl.glPopMatrix();
		
		gl.glPushMatrix();
		gl.glTranslatef(0f, 0f, 0f);
		desenhaRetas(gl, 0.7f, 0f, 1f, 0f);//reta 3
		gl.glPopMatrix();	
		
		gl.glPushMatrix();
		gl.glTranslatef(0f, 0.3f, 0f);
		desenhaRetas(gl, 0.72f, 0f, 1f, 0f);//reta 3
		gl.glPopMatrix();	
		
		gl.glPushMatrix();
		gl.glTranslatef(0f, -0.3f, 0f);
		desenhaRetas(gl, 0.2f, 0f, 1f, 0f);//reta 3
		gl.glPopMatrix();
		
		gl.glPushMatrix();
		gl.glTranslatef(0f, 0.3f, 0f);
		desenhaRetas(gl, -1f, 0f, 0.3f, 0f);//reta 3
		gl.glPopMatrix();			
		
		// Flush drawing command buffer to make drawing happen as soon as
		// possible.
		gl.glFlush();	
		
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
		
		GLUT glut = new GLUT();	
		
		gl.glColor4f(1.0f, 0.0f, 0.0f, 0.0f);//red
		gl.glRasterPos3f(0.1f, 0f, 0);		
		String text = "CASA";
		glut.glutBitmapString(GLUT.BITMAP_HELVETICA_18, text);	
		gl.glEnd();

		desenha(gl);			

	}

	public static void main(String[] args) {
		JFrame app = new JFrame("Teste");
		app.setSize(800, 800);
		// this.setResizable(false);
		app.setLocationRelativeTo(null);
		app.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		GLProfile profile = GLProfile.get(GLProfile.GL2);

		GLJPanel canvas = new GLJPanel(new GLCapabilities(profile));
		canvas.addGLEventListener(new Exercicio10());

		app.getContentPane().add(canvas);

		canvas.requestFocusInWindow();
		app.setVisible(true);
	}
}
