package model;

public final class AvlNode
{

	private int dato;
	private AvlNode izquierda;
	private AvlNode derecha;
	private int altura;

	public AvlNode(int valor)
	{
		this.dato = valor;
		izquierda = null;
		derecha = null;
		altura = 0;
	}

	public int getValor()
	{
		return dato;
	}

	public void setValor(int valor)
	{
		this.dato = valor;
	}

	public AvlNode getIzquierdo()
	{
		return izquierda;
	}

	public void setIzquierdo(AvlNode izquierdo)
	{
		this.izquierda = izquierdo;
	}

	public AvlNode getDerecho()
	{
		return derecha;
	}

	public void setDerecho(AvlNode derecho)
	{
		this.derecha = derecho;
	}

	public int getAltura()
	{
		return altura;
	}

	public void setAltura(int altura)
	{
		this.altura = altura;
	}
}