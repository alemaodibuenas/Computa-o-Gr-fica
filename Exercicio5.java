package teste;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

import javax.swing.JFrame;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLCapabilities;
import com.jogamp.opengl.GLEventListener;
import com.jogamp.opengl.GLProfile;
import com.jogamp.opengl.awt.GLJPanel;
import com.sun.xml.internal.ws.api.Component;


/*
 * 5.Fazer um programa que desenhe um retângulo, um triângulo e um círculo não
sobrepostos. Todos coloridos com cores diferentes. Além disso, desenhe as linhas
correspondentes aos eixos x e y. A execução deve possibilitar dar zoom com o mouse
(aumentando e diminuindo os objetos).

 */
public class Exercicio5 implements GLEventListener, KeyListener, MouseListener, MouseWheelListener {
	
	double zoom=1;   

	@Override
	public void init(GLAutoDrawable drawable) {
		System.err.println("Init: " + drawable);
		GL2 gl = drawable.getGL().getGL2();
		// Set the current clear color to black and the current drawing color to
		// white.
		gl.glClearColor(0f, 0f, 0f, 1f);		

	}
	
	private void desenhaCirculo(GL2 gl, float cx, float cy, int numTriangles) {		
		
		gl.glColor4f(1.0f, 1.0f, 0.0f, 0.0f);//yellow
		float twicePi = 2.0f * (float)Math.PI;					
		gl.glBegin(GL2.GL_TRIANGLE_FAN);
		float delta = twicePi/numTriangles;
		for(int i=0;i<= numTriangles;i++) {
			float x = (float)(0.27 * Math.cos(i * delta));
			float y = (float)(0.27 * Math.sin(i * delta));
			gl.glVertex3f(cx + x, cy + y, 0f);
		}
		gl.glEnd();
	}	
	
	private void desenhaTriangulo(GL2 gl, float a, float b, float c, float d, float e, float f, float g, float h,
			float i) {		
		
		gl.glColor3f(0.0f, 0.5f, 0.5f);
		gl.glBegin(GL2.GL_TRIANGLE_FAN);
		gl.glVertex3f(a,b,c);
		gl.glVertex3f(d,e,f);
		gl.glVertex3f(g,h,i);		
		gl.glEnd();
	}		
	
	private void desenhaRetangulo(GL2 gl, float a, float b, float c, float d, float e, float f, float g, float h) {
		
		gl.glColor3f(2.0f, 0.5f, 1.0f);//Lilac
		gl.glBegin(GL2.GL_QUADS);                          // Draw A Quad
		gl.glVertex3f(a, b, 0f);                          // Top Left
		gl.glVertex3f(c, d, 0f);                          // Top Right
		gl.glVertex3f(e, f, 0f);                          // Bottom Right
		gl.glVertex3f(g, h, .0f);                          // Bottom Left
		gl.glEnd();
	}
	
	private void desenha(GL2 gl) {				
		
		//triangulo
		gl.glPushMatrix();
		gl.glScaled(zoom,zoom, 1f);	
		gl.glTranslatef(-0.7f,0f,0f);
		desenhaTriangulo(gl, 0f, 0.25f, 0f, -0.25f, -0.25f, 0f, 0.25f, -0.25f, 0f);
		gl.glPopMatrix();
		
		//Circulo
		gl.glPushMatrix();
		gl.glScaled(zoom,zoom, 1f);	
		gl.glTranslatef(0.7f,0f,0f);
		desenhaCirculo(gl,0f,0f,1000);
		gl.glPopMatrix();

		//retangulo
		gl.glPushMatrix();
		gl.glScaled(zoom,zoom, 1f);	
		gl.glTranslatef(0,0,0);
		desenhaRetangulo(gl, -0.25f, 0.25f, 0.25f, 0.25f, 0.25f, -0.25f, -0.25f, -0.25f);
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
		
		desenha(gl);	
	}

	public static void main(String[] args) {
		JFrame app = new JFrame("Teste");
		app.setSize(1000, 1000);
		// this.setResizable(false);
		app.setLocationRelativeTo(null);
		app.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		Exercicio5 e = new Exercicio5();
		GLProfile profile = GLProfile.get(GLProfile.GL2);

		GLJPanel canvas = new GLJPanel(new GLCapabilities(profile));

		app.getContentPane().add(canvas);
		
		canvas.addGLEventListener(e);
		canvas.addKeyListener(e);
		canvas.addMouseListener(e);	
		canvas.addMouseWheelListener(e);
		canvas.requestFocusInWindow();
		app.setVisible(true);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub	   
				/*if (e.getButton()== MouseEvent.BUTTON1) { 
					 System.out.println("testado!");
					zoom += 0.5;
					((GLJPanel) e.getSource()).display();// pega que gerou o evento
				}		
				if (e.getButton()== MouseEvent.BUTTON3) { 
					 System.out.println("testado!");
					zoom -= 0.5;
					((GLJPanel) e.getSource()).display();// pega que gerou o evento
				}	*/	
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		/*if(e.getKeyCode() == KeyEvent.VK_UP) {
			zoom += 0.5;
			((GLJPanel) e.getSource()).display();// pega que gerou o evento
			}
		if(e.getKeyCode() == KeyEvent.VK_DOWN) {
			zoom -= 0.5;
			((GLJPanel) e.getSource()).display();// pega que gerou o evento
		}*/
		
	}
	
	 @Override
	    public void mouseWheelMoved(MouseWheelEvent e) {
	        int notches = e.getWheelRotation();
	        if (notches < 0) {// up
	            zoom += 0.1;
				 System.out.println("testado scroll!");
				((GLJPanel) e.getSource()).display();// pega que gerou o evento
	        } else {// down
	            zoom -= 0.1;
				((GLJPanel) e.getSource()).display();// pega que gerou o evento
	        }
	    }

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
}



