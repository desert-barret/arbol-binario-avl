package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import model.ArbolAVL;
import model.AvlNode;

import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.RowSpec;

public final class ViewArbolAVL extends JFrame
{
	private static final long serialVersionUID = 1L;

	JPanel contentPanel = new JPanel();
	static JTextField txtnodo;
	ArbolAVL arbolAVL = new ArbolAVL();;
	ContenedroArbol contenedroArbol = new ContenedroArbol(arbolAVL);
	AvlNode raizArbolAvl = null;

	JTextPane textDatosIngresados;

	boolean bandera = true;
	boolean bandera2 = true;
	private JTextField textAltura;
	private JTextField textNumeroHojas;
	private JTextField textNodosInternos;
	JTextPane textPanePreorden;
	JTextPane textPaneInorden;
	JTextPane textPanePostorden;

	public ViewArbolAVL()
	{
		Image mi = null;
		try
		{
			mi = ImageIO.read(getClass().getResource("/resources/tree.png"));
		} catch (IOException ex)
		{
		}
		setTitle("Simulador árbol binario AVL");
		setIconImage(mi);
		setBounds(0, 0, 650, 700);
		setLocationRelativeTo(null);
		contenedroArbol.setVisible(true);
		contentPanel.add(contenedroArbol);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(new BorderLayout());
		{
			JPanel panel = new JPanel();
			getContentPane().add(panel, BorderLayout.EAST);
			panel.setLayout(new FormLayout(new ColumnSpec[]
			{ ColumnSpec.decode("234px"), }, new RowSpec[]
			{ FormFactory.RELATED_GAP_ROWSPEC, RowSpec.decode("17px"),
					FormFactory.NARROW_LINE_GAP_ROWSPEC,
					RowSpec.decode("54px"),
					FormFactory.NARROW_LINE_GAP_ROWSPEC,
					RowSpec.decode("23px"), RowSpec.decode("57px:grow"),
					RowSpec.decode("17px"), FormFactory.RELATED_GAP_ROWSPEC,
					RowSpec.decode("54px"),
					FormFactory.NARROW_LINE_GAP_ROWSPEC,
					RowSpec.decode("23px"), RowSpec.decode("53px:grow"),
					RowSpec.decode("17px"),
					FormFactory.NARROW_LINE_GAP_ROWSPEC,
					RowSpec.decode("54px"),
					FormFactory.NARROW_LINE_GAP_ROWSPEC,
					RowSpec.decode("23px"), FormFactory.UNRELATED_GAP_ROWSPEC,
					RowSpec.decode("23px"), }));
			{
				JLabel lblRecorridoPreorden = new JLabel("Recorrido Preorden");
				lblRecorridoPreorden.setFont(new Font("Georgia", Font.ITALIC,
						14));
				panel.add(lblRecorridoPreorden, "1, 2, center, center");
			}
			{
				JScrollPane scrollPane = new JScrollPane();
				panel.add(scrollPane, "1, 4, fill, fill");

				textPanePreorden = new JTextPane();
				textPanePreorden.setEditable(false);
				scrollPane.setViewportView(textPanePreorden);
			}

			JButton preorden_1 = new JButton("Preorden");
			panel.add(preorden_1, "1, 6, fill, center");
			preorden_1.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent arg0)
				{
					if (!arbolAVL.esVacio(raizArbolAvl))
					{
						arbolAVL.setRecorridoPreorden(new ArrayList<Integer>());
						arbolAVL.recorridoPreorden(raizArbolAvl);
						String preorden = "PREORDEN: ";
						for (Integer valor : arbolAVL.getRecorridoPreorden())
						{
							preorden = preorden + "   " + valor;
						}
						JOptionPane.showMessageDialog(null, preorden);
					}
				}
			});
			preorden_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
			{
				JLabel lblRecorridoInorden = new JLabel("Recorrido Inorden");
				lblRecorridoInorden.setFont(new Font("Georgia", Font.ITALIC,
						14));
				panel.add(lblRecorridoInorden, "1, 8, center, top");
			}
			{
				JScrollPane scrollPane = new JScrollPane();
				panel.add(scrollPane, "1, 10, fill, fill");
				{
					textPaneInorden = new JTextPane();
					textPaneInorden.setEditable(false);
					scrollPane.setViewportView(textPaneInorden);
				}
			}
			{
				JButton inorden_1 = new JButton("Inorden");
				panel.add(inorden_1, "1, 12, fill, center");
				inorden_1.addActionListener(new ActionListener()
				{
					public void actionPerformed(ActionEvent e)
					{
						if (!arbolAVL.esVacio(raizArbolAvl))
						{
							arbolAVL.setRecorridoInorden(new ArrayList<Integer>());
							arbolAVL.recorridoInorden(raizArbolAvl);
							String inorden = "INORDEN: ";
							for (Integer valor : arbolAVL
									.getRecorridoInorden())
							{
								inorden = inorden + "   " + valor;
							}
							JOptionPane.showMessageDialog(null, inorden);
						}
					}
				});
				inorden_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
				inorden_1.setActionCommand("OK");
			}
			{
				JLabel lblRecorridoPosorden = new JLabel("Recorrido Postorden");
				lblRecorridoPosorden.setFont(new Font("Georgia", Font.ITALIC,
						14));
				panel.add(lblRecorridoPosorden, "1, 14, center, top");
			}
			{
				JScrollPane scrollPane = new JScrollPane();
				panel.add(scrollPane, "1, 16, fill, fill");
				{
					textPanePostorden = new JTextPane();
					textPanePostorden.setEditable(false);
					scrollPane.setViewportView(textPanePostorden);
				}
			}
			{
				JButton posorden = new JButton("Postorden");
				panel.add(posorden, "1, 18, fill, center");
				posorden.addActionListener(new ActionListener()
				{
					public void actionPerformed(ActionEvent arg0)
					{
						if (!arbolAVL.esVacio(raizArbolAvl))
						{
							arbolAVL.setRecorridoPostOrden(new ArrayList<Integer>());
							arbolAVL.recorridoPostorden(raizArbolAvl);
							String postOrden = "POSTORDEN: ";
							for (Integer valor : arbolAVL
									.getRecorridoPostOrden())
							{
								postOrden = postOrden + "   " + valor;
							}
							JOptionPane.showMessageDialog(null, postOrden);
						}
					}
				});
				posorden.setFont(new Font("Tahoma", Font.PLAIN, 12));
				posorden.setActionCommand("Cancel");
			}
			{
				JButton datosArbol = new JButton("actualizar");
				panel.add(datosArbol, "1, 20, fill, center");
				datosArbol.addActionListener(new ActionListener()
				{
					public void actionPerformed(ActionEvent e)
					{
						// //Pares
						if (!arbolAVL.esVacio(raizArbolAvl))
						{
							arbolAVL.pares(raizArbolAvl);
							arbolAVL.nh = 0;
							arbolAVL.nI = 0;
							textAltura.setText(ArbolAVL
									.obtenerAltura(raizArbolAvl) + "");
							textNumeroHojas.setText(arbolAVL
									.contarHojas(raizArbolAvl) + "");
							textNodosInternos.setText(arbolAVL
									.contarNodosI(raizArbolAvl) + "");

							arbolAVL.setRecorridoPreorden(new ArrayList<Integer>());
							arbolAVL.recorridoPreorden(raizArbolAvl);
							textPanePreorden.setText("");
							for (Integer valor : arbolAVL
									.getRecorridoPreorden())
							{
								textPanePreorden.setText(textPanePreorden
										.getText() + valor + "   ");
							}

							arbolAVL.setRecorridoInorden(new ArrayList<Integer>());
							arbolAVL.recorridoInorden(raizArbolAvl);

							textPaneInorden.setText("");
							for (Integer valor : arbolAVL
									.getRecorridoInorden())
							{
								textPaneInorden.setText(textPaneInorden
										.getText() + valor + "   ");
							}

							arbolAVL.setRecorridoPostOrden(new ArrayList<Integer>());
							arbolAVL.recorridoPostorden(raizArbolAvl);

							textPanePostorden.setText("");
							for (Integer valor : arbolAVL
									.getRecorridoPostOrden())
							{
								textPanePostorden.setText(textPanePostorden
										.getText() + valor + "   ");
							}

						} else
						{
							limpiarCajas();
						}
					}
				});
				datosArbol.setFont(new Font("Tahoma", Font.PLAIN, 12));
			}
		}
		{
			JPanel panel2 = new JPanel();
			getContentPane().add(panel2, BorderLayout.NORTH);
			GridBagLayout gbl_panel2 = new GridBagLayout();
			gbl_panel2.columnWidths = new int[]
			{ 474, 0 };
			gbl_panel2.rowHeights = new int[]
			{ 40, 0 };
			gbl_panel2.columnWeights = new double[]
			{ 1.0, Double.MIN_VALUE };
			gbl_panel2.rowWeights = new double[]
			{ 0.0, Double.MIN_VALUE };
			panel2.setLayout(gbl_panel2);
			{
				JScrollPane scrollPane = new JScrollPane();
				GridBagConstraints gbc_scrollPane = new GridBagConstraints();
				gbc_scrollPane.fill = GridBagConstraints.BOTH;
				gbc_scrollPane.gridx = 0;
				gbc_scrollPane.gridy = 0;
				panel2.add(scrollPane, gbc_scrollPane);

				textDatosIngresados = new JTextPane();
				scrollPane.setViewportView(textDatosIngresados);
				textDatosIngresados.setEditable(false);
				textDatosIngresados
						.setFont(new Font("Tahoma", Font.PLAIN, 11));
			}
		}
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new GridLayout(1, 0, 0, 0));
		{
			JPanel buttonPane = new JPanel();
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton aceptar = new JButton("Ingresar");
				aceptar.setFont(new Font("Georgia", Font.BOLD | Font.ITALIC,
						12));
				aceptar.addActionListener(new ActionListener()
				{
					public void actionPerformed(ActionEvent arg0)
					{
						if (!txtnodo.getText().equals(""))
						{
							ingresarNodo();
							txtnodo.setText("");
							txtnodo.requestFocus();
						}
					}
				});
				buttonPane.setLayout(new FormLayout(new ColumnSpec[]
				{ FormFactory.GLUE_COLSPEC, ColumnSpec.decode("87px"),
						FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
						ColumnSpec.decode("76px"),
						FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
						ColumnSpec.decode("87px"), FormFactory.GLUE_COLSPEC,
						ColumnSpec.decode("75px"),
						FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
						ColumnSpec.decode("75px"),
						FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
						ColumnSpec.decode("75px"), }, new RowSpec[]
				{ RowSpec.decode("33px"), }));
				{
					JButton eliminar = new JButton("Eliminar");
					buttonPane.add(eliminar, "2, 1, fill, center");
					eliminar.addActionListener(new ActionListener()
					{
						public void actionPerformed(ActionEvent e)
						{
							if (!txtnodo.getText().equals(""))
							{
								eliminarNodo();
								txtnodo.setText("");
								txtnodo.requestFocus();
							}
						}
					});
					eliminar.setFont(new Font("Georgia", Font.BOLD
							| Font.ITALIC, 12));
				}
				{
					txtnodo = new JTextField();
					txtnodo.addKeyListener(new KeyListener()
					{
						public void keyTyped(KeyEvent e)
						{
							if (txtnodo.getText().length() == 3)
								e.consume();
						}

						public void keyPressed(KeyEvent arg0)
						{
						}

						public void keyReleased(KeyEvent arg0)
						{
						}
					});
					txtnodo.addKeyListener(new KeyAdapter()
					{
						public void keyTyped(KeyEvent e)
						{
							char caracter = e.getKeyChar();
							if (((caracter < '0') || (caracter > '9'))
									&& (caracter != '\b') && (caracter != '-'))
							{
								e.consume();
							}
						}
					});
					txtnodo.setHorizontalAlignment(SwingConstants.CENTER);
					txtnodo.setFont(new Font("Dialog",
							Font.BOLD | Font.ITALIC, 15));
					buttonPane.add(txtnodo, "4, 1, center, center");
					txtnodo.setColumns(5);
				}
				aceptar.setActionCommand("OK");
				buttonPane.add(aceptar, "6, 1, fill, center");
				getRootPane().setDefaultButton(aceptar);
			}
			{
				textAltura = new JTextField();
				textAltura.setEditable(false);
				textAltura.setToolTipText("Altura");
				buttonPane.add(textAltura, "8, 1, fill, center");
				textAltura.setColumns(10);
			}
			{
				textNumeroHojas = new JTextField();
				textNumeroHojas.setEditable(false);
				textNumeroHojas.setToolTipText("Hojas");
				buttonPane.add(textNumeroHojas, "10, 1, fill, center");
				textNumeroHojas.setColumns(10);
			}
			{
				textNodosInternos = new JTextField();
				textNodosInternos.setEditable(false);
				textNodosInternos.setToolTipText("Nodos Internos");
				buttonPane.add(textNodosInternos, "12, 1, fill, center");
				textNodosInternos.setColumns(10);
			}
		}

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		{
			JMenu mnArchivo = new JMenu("Archivo");
			menuBar.add(mnArchivo);
			{
				JMenuItem mntmNuevo = new JMenuItem("Nuevo");
				mntmNuevo.addActionListener(new ActionListener()
				{
					public void actionPerformed(ActionEvent e)
					{
						arbolAVL = new ArbolAVL();
						raizArbolAvl = arbolAVL.raiz;
						textDatosIngresados.setText(textDatosIngresados
								.getText() + "-" + txtnodo.getText() + "-");
						contenedroArbol.reiniciar();
						limpiarCajas();

					}
				});
				mnArchivo.add(mntmNuevo);
			}
			{
				JMenuItem mntmSalir = new JMenuItem("Salir");
				mntmSalir.addActionListener(new ActionListener()
				{
					public void actionPerformed(ActionEvent arg0)
					{
						System.exit(0);
					}
				});
				mnArchivo.add(mntmSalir);
			}
		}
		{
			JMenu mnAbout = new JMenu("About");
			menuBar.add(mnAbout);
			{
				JMenuItem mntmG = new JMenuItem("G+");
				mntmG.addActionListener(new ActionListener()
				{
					public void actionPerformed(ActionEvent e)
					{
						String direccion = "https://plus.google.com/u/0/107701390148460385655/posts";
						try
						{
							Desktop.getDesktop().browse(new URI(direccion));
						} catch (Exception err)
						{
							JOptionPane.showMessageDialog(null, "Error: "
									+ err);
						}
					}
				});
				mnAbout.add(mntmG);
			}
			{
				JMenuItem mntmTwitter = new JMenuItem("Twitter");
				mntmTwitter.addActionListener(new ActionListener()
				{
					public void actionPerformed(ActionEvent e)
					{
						String direccion = "https://twitter.com/desertbarret";
						try
						{
							Desktop.getDesktop().browse(new URI(direccion));
						} catch (Exception err)
						{
							JOptionPane.showMessageDialog(null, "Error: "
									+ err);
						}
					}
				});
				mnAbout.add(mntmTwitter);
			}
		}
	}

	public void limpiarCajas()
	{
		textAltura.setText("");
		textDatosIngresados.setText("");
		textNodosInternos.setText("");
		textNumeroHojas.setText("");
		textPaneInorden.setText("");
		textPanePostorden.setText("");
		textPanePreorden.setText("");
	}

	public void ingresarNodo()
	{
		raizArbolAvl = arbolAVL.insertar(Integer.parseInt(txtnodo.getText()),
				raizArbolAvl);

		textDatosIngresados.setText(textDatosIngresados.getText() + "-"
				+ txtnodo.getText() + "-");
		contenedroArbol.reiniciar();

	}

	public void eliminarNodo()
	{
		if (!arbolAVL.esVacio(raizArbolAvl))
		{
			arbolAVL.eliminar(Integer.parseInt(txtnodo.getText()),
					raizArbolAvl);

			// //Banlanceamos el Arbol
			raizArbolAvl = arbolAVL.balancear(raizArbolAvl);

			contenedroArbol.reiniciar();
		} else
		{
			JOptionPane.showMessageDialog(null, "El Arbol Esta Vacio");
		}
	}

	public final class ContenedroArbol extends JPanel
	{
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		private HashMap<AvlNode, Rectangle> posicionNodos = null;
		private HashMap<AvlNode, Dimension> subtreeSizes = null;
		private boolean dirty = true;
		private int parent2child = 47, child2child = 17;
		private Dimension empty = new Dimension(0, 0);
		private FontMetrics fm = null;

		/**
		 * Constructor de la clase ArbolExpresionGrafico. El constructor permite
		 * inicializar los atributos de la clase ArbolExpresionGrafico y llama
		 * al método repaint(), que es el encargado de pintar el Arbol.
		 * 
		 * @param miExpresion
		 *            : dato de tipo ArbolExpresion que contiene el Arbol a
		 *            dibujar.
		 */
		public ContenedroArbol(ArbolAVL miArbol)
		{
			this.setBackground(Color.WHITE);
			posicionNodos = new HashMap<AvlNode, Rectangle>();
			subtreeSizes = new HashMap<AvlNode, Dimension>();
			dirty = true;
			repaint();
		}

		public void reiniciar()
		{
			posicionNodos = new HashMap<AvlNode, Rectangle>();
			subtreeSizes = new HashMap<AvlNode, Dimension>();
			dirty = true;
			repaint();
		}

		/**
		 * Calcula las posiciones de los respectivos subárboles y de cada nodo
		 * que forma parte de ese subárbol, para conocer en que posición van a
		 * ir dibujados los rectángulos representativos del árbol de la
		 * expresión.
		 */
		private void calcularPosiciones()
		{
			posicionNodos.clear();
			subtreeSizes.clear();
			AvlNode root = raizArbolAvl;
			if (root != null)
			{
				calcularTamañoSubarbol(root);
				calcularPosicion(root, Integer.MAX_VALUE, Integer.MAX_VALUE, 0);
			}
		}

		/**
		 * Calcula el tamaño de cada subárbol y lo agrega al objeto subtreeSizes
		 * de la clase de tipo HashMap que va a contener la coleccion de todos
		 * los subárboles que contiene un arbol.
		 * 
		 * @param n
		 *            :Objeto de la clase NodoB <T> que se utiliza como
		 *            referencia calcular el tamaño de cada subárbol.
		 * @return Dimension con el tamaño de cada subárbol.
		 */
		private Dimension calcularTamañoSubarbol(AvlNode n)
		{
			if (n == null)
			{
				return new Dimension(0, 0);
			}

			Dimension ld = calcularTamañoSubarbol(n.getIzquierdo());
			Dimension rd = calcularTamañoSubarbol(n.getDerecho());

			int h = fm.getHeight() + parent2child
					+ Math.max(ld.height, rd.height);
			int w = ld.width + child2child + rd.width;

			Dimension d = new Dimension(w, h);
			subtreeSizes.put(n, d);

			return d;
		}

		/**
		 * Calcula la ubicación de cada nodo de cada subárbol y agrega cada nodo
		 * con un objeto de tipo Rectangule que tiene la ubicación y la
		 * información específica de dónde va a ser dibujado.
		 * 
		 * @param n
		 *            : Objeto de tipo NodoB <T> que se utiliza como referencia
		 *            para calcular la ubicación de cada nodo.
		 * @param left
		 *            : int con alineación y orientación a la izquierda.
		 * @param right
		 *            : int con alineación y orientación a la derecha.
		 * @param top
		 *            : int con el tope.
		 */
		private void calcularPosicion(AvlNode n, int left, int right, int top)
		{
			if (n == null)
				return;

			Dimension ld = (Dimension) subtreeSizes.get(n.getIzquierdo());
			if (ld == null)
				ld = empty;

			Dimension rd = (Dimension) subtreeSizes.get(n.getDerecho());
			if (rd == null)
				rd = empty;

			int center = 0;

			if (right != Integer.MAX_VALUE)
				center = right - rd.width - child2child / 2;
			else if (left != Integer.MAX_VALUE)
				center = left + ld.width + child2child / 2;
			int width = fm.stringWidth(n.getValor() + "");

			posicionNodos.put(n, new Rectangle(center - width / 2 - 3, top,
					width + 15, fm.getHeight() + 5));

			calcularPosicion(n.getIzquierdo(), Integer.MAX_VALUE, center
					- child2child / 2, top + fm.getHeight() + parent2child);
			calcularPosicion(n.getDerecho(), center + child2child / 2,
					Integer.MAX_VALUE, top + fm.getHeight() + parent2child);
		}

		/**
		 * Dibuja el árbol teniendo en cuenta las ubicaciones de los nodos y los
		 * subárboles calculadas anteriormente.
		 * 
		 * @param g
		 *            : Objeto de la clase Graphics2D que permite realizar el
		 *            dibujo de las líneas, rectangulos y del String de la
		 *            información que contiene el NodoB<T>.
		 * @param n
		 *            : Objeto de la clase NodoB <T> que se utiliza como
		 *            referencia para dibujar el árbol.
		 * @param puntox
		 *            : int con la posición en x desde donde se va a dibujar la
		 *            línea hasta el siguiente hijo.
		 * @param puntoy
		 *            : int con la posición en y desde donde se va a dibujar la
		 *            línea hasta el siguiente hijo.
		 * @param yoffs
		 *            : int con la altura del FontMetrics.
		 */
		private void dibujarArbol(Graphics2D g, AvlNode n, int puntox,
				int puntoy, int yoffs)
		{
			if (n == null)
			{
				return;

			}

			g.setColor(Color.BLUE);
			g.setFont(new Font("Arial", Font.TYPE1_FONT, 15));

			Rectangle r = (Rectangle) posicionNodos.get(n);
			g.draw(r);
			g.drawString(n.getValor() + "", r.x + 6, r.y + yoffs + 5);

			g.setColor(Color.RED);
			if (puntox != Integer.MAX_VALUE)
			{
				g.drawLine(puntox, puntoy, (int) (r.x + r.width / 2), r.y);
			}

			dibujarArbol(g, n.getIzquierdo(), (int) (r.x + r.width / 2), r.y
					+ r.height, yoffs);
			dibujarArbol(g, n.getDerecho(), (int) (r.x + r.width / 2), r.y
					+ r.height, yoffs);

		}

		/**
		 * Sobreescribe el metodo paint y se encarga de pintar todo el árbol.
		 * 
		 * @param g
		 *            : Objeto de la clase Graphics.
		 */
		public void paint(Graphics g)
		{
			super.paint(g);
			fm = g.getFontMetrics();

			if (dirty)
			{
				calcularPosiciones();
				dirty = false;
			}

			Graphics2D g2d = (Graphics2D) g;
			g2d.translate(getWidth() / 2, parent2child);

			dibujarArbol(g2d, raizArbolAvl, Integer.MAX_VALUE,
					Integer.MAX_VALUE, fm.getLeading() + fm.getAscent());
			fm = null;
		}

	}
}
