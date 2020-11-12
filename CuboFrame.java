package OpenGL3;

import javax.swing.JFrame;
import com.jogamp.opengl.GLCapabilities;
import com.jogamp.opengl.GLProfile;
import com.jogamp.opengl.awt.GLJPanel;

public class CuboFrame {

  public static void wrap(final String title, final Cubo cubo) {
    new Thread() {
      @Override
      public void run() {
    	GLProfile profile = GLProfile.get(GLProfile.GL3);

  		GLJPanel canvas = new GLJPanel(new GLCapabilities(profile));
  		canvas.addGLEventListener(new Cubo());

  		JFrame app = new JFrame("Cubo colorido");
  		app.setSize(800, 600);
  		app.setLocationRelativeTo(null);
  		app.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  		app.getContentPane().add(canvas);
  		app.setVisible(true);
      }
    }.start();
  }
}