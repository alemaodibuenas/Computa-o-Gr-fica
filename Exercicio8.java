/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package teste;

import com.jogamp.opengl.GLEventListener;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLProfile;
import com.jogamp.opengl.GLCapabilities;
import com.jogamp.opengl.awt.GLCanvas;
import com.jogamp.opengl.awt.GLJPanel;
import com.jogamp.opengl.util.gl2.GLUT;

import java.awt.BorderLayout;
import java.awt.Frame;
import java.awt.Label;
import java.awt.TextArea;
import java.awt.TextField;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.media.opengl.glu.GLU;

public class Exercicio8 implements GLEventListener, KeyListener {

	double p = 6;// aqui é a variavel global , p 5 a 20
    public float rotation = 0.69f;

	@Override
	public void init(GLAutoDrawable drawable) {
		System.err.println("Init: " + drawable);
		GL2 gl = drawable.getGL().getGL2();
		// Set the current clear color to black and the current drawing color to
		// white.
		gl.glClearColor(1f, 1f, 1f, 1f);
	}

	private void desenhaTriangulo(GL2 gl, float a, float b, float c, float d, float e, float f, float g, float h,
			float i) {

		gl.glColor3f(0.0f, 0.5f, 0.5f);
		gl.glBegin(GL2.GL_TRIANGLE_FAN);
		gl.glVertex3f(a, b, c);
		gl.glVertex3f(d, e, f);
		gl.glVertex3f(g, h, i);
		gl.glEnd();
	}

	private void desenhaEstela(GL2 gl, double pontas) {
		for (int i = 0; i <= 20; i++) {
			gl.glRotated(-360 / pontas, 0, 0, 1);
			desenhaTriangulo(gl, 0f, 0.5f, 0f, -0.25f, 0f, 0f, 0.25f, 0f, 0f);
		}
	}	

	private void desenhaTriangulo2(GL2 gl, double pontas) {		
		desenhaTriangulo(gl, 0f, -0.5f/2, 0f, -0.25f/2, 0f, 0f, 0.25f/2, 0f, 0f);
	}	
	
	
	
	@Override
	public void display(GLAutoDrawable drawable) {
		System.err.println("display: " + p);
		GL2 gl = drawable.getGL().getGL2();
		// Set every pixel in the frame buffer to the current clear color.

		gl.glClear(GL2.GL_COLOR_BUFFER_BIT);
		
		gl.glPushMatrix();
		gl.glTranslatef(0f,0f,0f);
		desenhaEstela(gl, 5);// p numero de pontas que a estrela vai ter
		gl.glPopMatrix();
		

		for(int i =0;i<=360;i=i+72) {
			gl.glPushMatrix();
			gl.glRotated(i, 0, 0, 1);
			gl.glTranslatef(0f,0.4f,0f);
			desenhaTriangulo2(gl, 1);
			gl.glPopMatrix();	
		}

		gl.glFlush();
	}

	@Override
	public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height) {
		System.err.println("Reshape " + x + "/" + y + " " + width + "x" + height);
		GL2 gl = drawable.getGL().getGL2();
		// Just the glViewport for this sample
		gl.glViewport(x, y, width, height);
	}

	public static void main(String[] args) {

		JFrame app = new JFrame("Estrela");
		app.setSize(800, 800);
		// this.setResizable(false);
		app.setLocationRelativeTo(null);
		app.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		Exercicio8 e = new Exercicio8();

		GLProfile profile = GLProfile.get(GLProfile.GL2);
		GLJPanel canvas = new GLJPanel(new GLCapabilities(profile));
		// cada vez que dá um new, tá criando um objeto novo! não vai funcionar!
		canvas.addGLEventListener(e);
		canvas.addKeyListener(e);

		app.getContentPane().add(canvas);
		canvas.requestFocusInWindow();
		app.setVisible(true);
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
			System.out.println("The key has been pressed");
			System.exit(0);
		}
		
		if (e.getKeyCode() == KeyEvent.VK_ENTER) {
			p += 1;
			
			if(p>20) {
				System.exit(0);
			}
			((GLJPanel) e.getSource()).display();// pega que gerou o evento
			System.out.println("pressionando enter" + p);
		}
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void dispose(GLAutoDrawable arg0) {
		// TODO Auto-generated method stub

	}

}