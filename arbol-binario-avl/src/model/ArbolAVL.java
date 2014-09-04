package model;

import java.util.ArrayList;

public final class ArbolAVL
{

	public static int obtenerAltura(AvlNode posision)
	{
		if (posision == null)
			return -1;
		else
			return posision.getAltura();
	}

	public static AvlNode rotasionSimpleIzquierda(AvlNode n2)
	{
		AvlNode k1;
		k1 = n2.getIzquierdo();
		n2.setIzquierdo(k1.getDerecho());
		k1.setDerecho(n2);
		n2.setAltura(Math.max(obtenerAltura(n2.getIzquierdo()),
				obtenerAltura(n2.getDerecho())) + 1);
		k1.setAltura(Math.max(obtenerAltura(k1.getIzquierdo()), n2.getAltura()) + 1);
		return k1;
	}

	public static AvlNode rotasionSimpleDerecha(AvlNode n1)
	{
		AvlNode k2;
		k2 = n1.getDerecho();
		n1.setDerecho(k2.getIzquierdo());
		k2.setIzquierdo(n1);
		n1.setAltura(Math.max(obtenerAltura(n1.getIzquierdo()),
				obtenerAltura(n1.getDerecho())) + 1);
		k2.setAltura(Math.max(obtenerAltura(k2.getDerecho()), n1.getAltura()) + 1);
		return k2;
	}

	public static AvlNode rotacionDobleIzquierda(AvlNode n3)
	{
		n3.setIzquierdo(rotasionSimpleDerecha(n3.getIzquierdo()));
		return rotasionSimpleIzquierda(n3);
	}

	public static AvlNode rotacionDobleDerecha(AvlNode n1)
	{
		n1.setDerecho(rotasionSimpleIzquierda(n1.getDerecho()));
		return rotasionSimpleDerecha(n1);
	}

	public AvlNode insertar(int value, AvlNode t)
	{
		if (t == null)
		{
			t = new AvlNode(value);
		} else if (value < t.getValor())
		{
			t.setIzquierdo(insertar(value, t.getIzquierdo()));
			if (obtenerAltura(t.getIzquierdo())
					- obtenerAltura(t.getDerecho()) == 2)
				if (value < t.getIzquierdo().getValor())
					t = rotasionSimpleIzquierda(t);
				else
					t = rotacionDobleIzquierda(t);
		} else if (value > t.getValor())
		{
			t.setDerecho(insertar(value, t.getDerecho()));
			if (obtenerAltura(t.getDerecho())
					- obtenerAltura(t.getIzquierdo()) == 2)
				if (value > t.getDerecho().getValor())
					t = rotasionSimpleDerecha(t);
				else
					t = rotacionDobleDerecha(t);
		}
		t.setAltura(Math.max(obtenerAltura(t.getIzquierdo()),
				obtenerAltura(t.getDerecho())) + 1);
		return t;
	}

	// /////////////////////////////////////////////////////////////////////////////////
	// //////////////////////PREORDEN///////////////////////////////////////////////////
	// !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
	private ArrayList<Integer> recorridoPreorden = new ArrayList<Integer>();

	public ArrayList<Integer> getRecorridoPreorden()
	{
		return recorridoPreorden;
	}

	public void setRecorridoPreorden(ArrayList<Integer> recorridoPreorden)
	{
		this.recorridoPreorden = recorridoPreorden;
	}

	public void recorridoPreorden(AvlNode t)
	{
		recorridoPreorden.add(t.getValor());
		if (t.getIzquierdo() != null)
		{
			recorridoPreorden(t.getIzquierdo());
		}
		if (t.getDerecho() != null)
		{
			recorridoPreorden(t.getDerecho());
		}
	}

	public void pares(AvlNode t)
	{
		if (t.getValor() % 2 == 0)
		{
			System.out.println(t.getValor());
		}
		if (t.getIzquierdo() != null)
		{
			pares(t.getIzquierdo());
		}
		if (t.getDerecho() != null)
		{
			pares(t.getDerecho());
		}
	}

	// ¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡
	// /////////////////////////////////////////////////////////////////////////////////

	// /////////////////////////////////////////////////////////////////////////////////
	// //////////////////////INORDEN////////////////////////////////////////////////////
	// !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
	private ArrayList<Integer> recorridoInorden = new ArrayList<Integer>();

	public ArrayList<Integer> getRecorridoInorden()
	{
		return recorridoInorden;
	}

	public void setRecorridoInorden(ArrayList<Integer> recorridoInorden)
	{
		this.recorridoInorden = recorridoInorden;
	}

	// empezar rrecorrido inorden
	public synchronized void recorridoInorden(AvlNode t)
	{
		ayudanteInorden(t);
	}

	// metodo recursivo para realizar rrecorrido inorden
	private void ayudanteInorden(AvlNode nodo)
	{
		if (nodo == null)
		{
			return;
		}
		ayudanteInorden(nodo.getIzquierdo());// rrecorrido subarbol izquierdo
		recorridoInorden.add(nodo.getValor());
		// System.out.print(nodo.getValor() + " "); // mostrar datos del nodo
		ayudanteInorden(nodo.getDerecho()); // rrecorrer subarbol derecho
	}

	// ¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡
	// /////////////////////////////////////////////////////////////////////////////////

	// /////////////////////////////////////////////////////////////////////////////////
	// //////////////////////POSTORDEN//////////////////////////////////////////////////
	// !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
	private ArrayList<Integer> recorridoPostOrden = new ArrayList<Integer>();

	public ArrayList<Integer> getRecorridoPostOrden()
	{
		return recorridoPostOrden;
	}

	public void setRecorridoPostOrden(ArrayList<Integer> recorridoPostOrden)
	{
		this.recorridoPostOrden = recorridoPostOrden;
	}

	// iniciar rrecorrido postorden
	public synchronized void recorridoPostorden(AvlNode t)
	{
		ayudantePostorden(t);
	}

	// metodo recursivo para realizar rrecorrido postorden
	private void ayudantePostorden(AvlNode nodo)
	{
		if (nodo == null)
		{
			return;
		}
		ayudantePostorden(nodo.getIzquierdo());// recorren subarbol izquierdo
		ayudantePostorden(nodo.getDerecho());// recorren subarbol Derecho
		// System.out.print(nodo.getValor() + " "); // mostrar datos del nodo
		recorridoPostOrden.add(nodo.getValor());
	}

	// ¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡
	// /////////////////////////////////////////////////////////////////////////////////
	public boolean esVacio(AvlNode t)
	{
		if (t == null)
		{
			return true;
		} else
		{
			return false;
		}
	}

	// //////////////////???????????????????????????//////////////////////////////////////
	// //////////////////???????????????????????????//////////////////////////////////////

	// //////////////////////////////////////////////////////////////////////////////////
	// //////////////////???????????????????????????//////////////////////////////////////
	public AvlNode balancear(AvlNode t)
	{
		if (obtenerAltura(t.getIzquierdo()) - obtenerAltura(t.getDerecho()) == 2)
		{

			/*
			 * desequilibrio hacia la izquierda !
			 */
			if (obtenerAltura(t.getIzquierdo().getIzquierdo()) >= obtenerAltura(t
					.getIzquierdo().getDerecho()))
			{
				/* desequilibrio simple hacia la izquierda */
				if (t.getIzquierdo().getIzquierdo() != null)
				{

				}

			} else
			{
				/* desequilibrio doble hacia la izquierda */
				if (t.getIzquierdo().getDerecho() != null)
				{

				}
			}
		} else if (obtenerAltura(t.getDerecho())
				- obtenerAltura(t.getIzquierdo()) == 2)
		{
			/*
			 * desequilibrio hacia la derecha!
			 */
			if (obtenerAltura(t.getDerecho().getDerecho()) >= obtenerAltura(t
					.getDerecho().getIzquierdo()))
			{
				/* desequilibrio simple hacia la izquierda */
				if (t.getDerecho().getDerecho() != null)
				{

				}
			} else
			{
				/* desequilibrio doble hacia la izquierda */
				if (t.getDerecho().getIzquierdo() != null)
				{

				}
			}
		}
		return t;

	}

	// //////////////////////////////////////////////////////////////////////////////////
	// //////////////////???????????????????????????//////////////////////////////////////

	// //////////////////////////////////////////////////////////////////////////////////
	// /////////////////////////////////////////ELIMINAR/////////////////////////////////
	// !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
	public AvlNode raiz = null;

	public synchronized void ayudanteliminar(int dato)
	{
		eliminar(dato, raiz);
	}

	AvlNode aux = this.raiz;
	AvlNode aux1 = this.raiz;

	public AvlNode eliminar(int dato, AvlNode r)
	{
		if (r == null)
		{
			return r;

		} else
		{
			if (dato < r.getValor())
			{
				if (r.getIzquierdo() != null)
				{
					if (r.getIzquierdo().getValor() == dato)
					{
						aux = r;
					}
				}
				eliminar(dato, r.getIzquierdo());
			} else if (dato > r.getValor())
			{
				if (r.getDerecho() != null)
				{
					if (r.getDerecho().getValor() == dato)
					{
						aux1 = r;
					}
				}
				eliminar(dato, r.getDerecho());
			} else
			{
				AvlNode q;
				q = r;
				if (q.getDerecho() == null && q.getIzquierdo() == null)
				{
					if (aux != null)
					{
						aux.setIzquierdo(null);
					} else if (aux1 != null)
					{
						aux1.setDerecho(null);
					}
				} else
				{
					if (q.getDerecho() != null)
					{
						reemplazarDerecha(q);
					} else
					{
						reemplazarIzquierda(q);
					}
				}
				aux = this.raiz;
				aux1 = this.raiz;
				return r;
			}

		}
		return r;
	}

	public void reemplazarIzquierda(AvlNode at)
	{
		AvlNode a, b;
		b = at;
		a = at.getIzquierdo();
		while (a.getDerecho() != null)
		{
			b = a;
			a = a.getDerecho();
		}
		at.setValor(a.getValor());
		if (b == at)
		{
			b.setIzquierdo(a.getIzquierdo());
		} else
			b.setDerecho(a.getIzquierdo());
		at = a;
	}

	public void reemplazarDerecha(AvlNode at)
	{
		AvlNode a, b;
		b = at;
		a = at.getDerecho();
		while (a.getIzquierdo() != null)
		{
			b = a;
			a = a.getIzquierdo();
		}
		at.setValor(a.getValor());
		if (b == at)
		{
			b.setDerecho(a.getDerecho());
		} else
			b.setIzquierdo(a.getDerecho());
		at = a;
	}

	// ////////////////////////////////////////////////////////////////////////////////////////////

	public int nh;

	public int contarHojas(AvlNode t)
	{
		return cuentaHojas(t);
	}

	public int cuentaHojas(AvlNode r)
	{
		if (r != null)
		{
			if (r.getIzquierdo() == null && r.getDerecho() == null)
			{
				nh++;
			} else
			{
				cuentaHojas(r.getIzquierdo());
				cuentaHojas(r.getDerecho());
			}
		}
		return nh;
	}

	// ////////////////////////////////////////////////////////////////////////////////////////////
	public int nI;

	public int contarNodosI(AvlNode t)
	{
		return cuentaNodosI(t);
	}

	public int cuentaNodosI(AvlNode r)
	{
		if (r != null)
		{
			if (r.getIzquierdo() != null || r.getDerecho() != null)
			{
				nI++;
			}
			cuentaNodosI(r.getIzquierdo());
			cuentaNodosI(r.getDerecho());

		}
		return nI - 1;
	}
}
