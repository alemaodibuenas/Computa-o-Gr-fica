package OpenGL3;

import org.joml.Matrix4f;

public class MatrixUtil {
	
	//print matriz
	public static void print(float[] m) {
		for(int i =0;i< m.length;i++) {
			if(i % 4 == 0)
				System.out.println();
			System.out.print(m[i] + " ");
		}
		System.out.println();
	}

//aqui faz transposta
	public static float[] transpose(Matrix4f m) {
		float[] p = new float[16];
		m.get(p);

		float m2[] = new float[16];
		
		for (int i = 0; i < p.length; i++) {
			m2[i] = p[i];
		}
		
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++)
				p[j * 4 + i] = m2[i * 4 + j];
		}		
		return p;
	}

	public static float[] toArray(Matrix4f m) {
		float[] p = new float[16];
		m.get(p);

		return p;
	}
	
	public static float[] newIdentidadeMatrix() {
		return toIdentidadeMatrix(new float[16]);
	}

	public static float[] toIdentidadeMatrix(float[] mat) {
		for (int i = 0; i < mat.length; i++)
			mat[i] = 0.0f;

		int size = (int) Math.sqrt(mat.length);

		for (int i = 0; i < size; i++)
			mat[i + i * size] = 1.0f;

		return mat;
	}

}
