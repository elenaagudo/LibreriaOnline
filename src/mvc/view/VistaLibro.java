package mvc.view;

import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.Vector;

import mvc.controller.Controlador;
import mvc.model.Autor;
import mvc.model.AutorLibro;
import mvc.model.Categoria;
import mvc.model.Editorial;
import mvc.model.Libro;

public class VistaLibro {

	private Controlador controlador;
	private VistaCategoria vistaCategoria;
	private VistaEditorial vistaEditorial;
	private VistaAutor vistaAutor;
	private Scanner sc;
	private int opcion;
	private int opcionEditar;

	private String feedback;

	// añadir menu principal para volver atras, vistaCategoria y vistaEditorial para
	// pedirle la lista
	public VistaLibro(Controlador controlador, VistaCategoria vistaCategoria, VistaEditorial vistaEditorial,
			VistaAutor vistaAutor) {
		this.controlador = controlador;
		this.vistaCategoria = vistaCategoria;
		this.vistaEditorial = vistaEditorial;
		this.vistaAutor = vistaAutor;
	}

	public void init() {
		getMenuLibro();
		getOption();
		do {
			switch (opcion) {
			case 1:
				insert();
				break;
			case 2:
				initUpdateBook();
				break;
			case 3:
				list();
				break;
			case 4:
				delete();
				break;
			case 0:

				break;
			default:
				System.out.println("\nLa opcion tecleada no es correcta.");
				break;
			}
			if (opcion != 0) {
				getMenuLibro();
				getOption();
			}
		} while (opcion != 0);
	}

	public void getMenuLibro() {
		System.out.println("**** LIBROS ****");
		System.out.println("Elige una opcion: ");
		System.out.println("1 - Nuevo");
		System.out.println("2 - Editar existente");
		System.out.println("3 - Listar");
		System.out.println("4 - Borrar");
		System.out.println("0 - Menu principal");
	}

	public void initUpdateBook() {
		getMenuUpdateBook();
		getOptionEditar();
		do {
			switch (opcionEditar) {
			case 1:
				updateIsbn();
				break;
			case 2:
				updateTitle();
				break;
			case 3:
				updatePrice();
				break;
			case 4:
				updateStock();
				break;
			case 5:
				updateCategory();
				break;
			case 6:
				updateEditorial();
				break;
			case 7:
				deleteAuthor();
				break;
			case 8:
				insertAuthor();
				break;
			case 0:
				break;
			default:
				System.out.println("\nLa opcion tecleada no es correcta.");
				break;
			}
			if (opcionEditar != 0) {
				getMenuUpdateBook();
				getOptionEditar();
			}
		} while (opcionEditar != 0);
	}

	public void getMenuUpdateBook() {
		System.out.println("**** EDITAR LIBROS ****");
		System.out.println("Elige una opcion: ");
		System.out.println("1 - Editar ISBN");
		System.out.println("2 - Editar titulo");
		System.out.println("3 - Editar precio");
		System.out.println("4 - Editar stock");
		System.out.println("5 - Editar categoria");
		System.out.println("6 - Editar editorial");
		System.out.println("7 - Borrar autor");
		System.out.println("8 - Añadir autor");
		System.out.println("0 - Menu principal");
	}

	public void getOption() {
		sc = new Scanner(System.in);
		System.out.println("Introduzca una opcion: ");
		try {
			opcion = sc.nextInt();
		} catch (InputMismatchException e) {
			opcion = -1;
		}
	}

	public void getOptionEditar() {
		sc = new Scanner(System.in);
		System.out.println("Introduzca una opcion: ");
		try {
			opcionEditar = sc.nextInt();
		} catch (InputMismatchException e) {
			opcionEditar = -1;
		}
	}

	public void insert() {
		boolean valorCorrecto = true;
		int isbn = 0;
		double precio = 0;
		int stock = 0;
		int codigoCategoria = 0;
		int codigoEditorial = 0;
		int codigoAutor = 0;

		// se pide el ISBN
		do {
			try {
				System.out.println("Introduce el ISBN");
				isbn = sc.nextInt();
				valorCorrecto = true;
			} catch (InputMismatchException e) {
				System.out.println("No es un valor correcto");
				sc.nextLine();
				valorCorrecto = false;
			}
		} while (!valorCorrecto);

		// comprobar si ya existe el isbn
		Vector<Libro> libros = controlador.searchBookByIsbn(isbn);
		if (libros.size() > 0) {
			System.out.println("Ya existe un libro con ese ISBN\n");
		} else {
			// se pide el titulo
			System.out.println("Introduce el titulo");
			sc.nextLine();
			String titulo = sc.nextLine();

			// se pide el precio
			do {
				try {
					System.out.println("Introduce el precio");
					precio = sc.nextDouble();
					valorCorrecto = true;
				} catch (InputMismatchException e) {
					System.out.println("No es un valor correcto");
					sc.nextLine();
					valorCorrecto = false;
				}
			} while (!valorCorrecto);

			// se pide el stock
			do {
				try {
					System.out.println("Introduce el stock");
					stock = sc.nextInt();
					valorCorrecto = true;
				} catch (InputMismatchException e) {
					System.out.println("No es un valor correcto");
					sc.nextLine();
					valorCorrecto = false;
				}
			} while (!valorCorrecto);

			boolean existeCategoria = true;
			do {
				// listar categoria
				vistaCategoria = new VistaCategoria(controlador);
				vistaCategoria.list();
				// se pide la categoria
				do {
					try {
						System.out.println("Codigo Categoria");
						codigoCategoria = sc.nextInt();
						valorCorrecto = true;
					} catch (InputMismatchException e) {
						System.out.println("No es un valor correcto");
						sc.nextLine();
						valorCorrecto = false;
					}
				} while (!valorCorrecto);

				// comprobar si existe la categoria
				Vector<Categoria> categorias = controlador.searchCategoryById(codigoCategoria);
				if (categorias.size() == 0) {
					System.out.println("No existe una categoria con ese ID");
					existeCategoria = false;
				} else {
					existeCategoria = true;
					boolean existeEditorial = true;
					do {
						// listar editorial
						vistaEditorial = new VistaEditorial(controlador);
						vistaEditorial.list();
						// se pide la editorial
						do {
							try {
								System.out.println("Codigo Editorial");
								codigoEditorial = sc.nextInt();
								valorCorrecto = true;
							} catch (InputMismatchException e) {
								System.out.println("No es un valor correcto");
								sc.nextLine();
								valorCorrecto = false;
							}
						} while (!valorCorrecto);

						// comprobar si existe la editorial
						Vector<Editorial> editoriales = controlador.searchEditorialById(codigoEditorial);
						if (editoriales.size() == 0) {
							System.out.println("No existe una editorial con ese ID");
							existeEditorial = false;
						} else {
							existeEditorial = true;

							// inserta el libro
							feedback = controlador.insertBook(isbn, titulo, precio, stock, codigoCategoria,
									codigoEditorial);
							System.out.println(feedback);

							boolean existeAutor = true;
							boolean introducirMas = true;
							do {
								// listar autores
								vistaAutor = new VistaAutor(controlador);
								vistaAutor.list();

								// se pide el autor
								do {
									try {
										System.out.println("Codigo del autor o 0 para terminar");
										codigoAutor = sc.nextInt();
										valorCorrecto = true;
									} catch (InputMismatchException e) {
										System.out.println("No es un valor correcto");
										sc.nextLine();
										valorCorrecto = false;
									}
								} while (!valorCorrecto);

								// si es 0 no pide mas autores
								if (codigoAutor != 0) {
									introducirMas = true;
									// comprobar si existe el autor
									Vector<Autor> autor = controlador.searchAuthorById(codigoAutor);
									if (autor.size() > 0) {
										existeAutor = true;
										// preguntar si se ha repetido el autor
										Vector<AutorLibro> autorLibro = controlador.searchBookByAuthorIsbn(codigoAutor,
												isbn);
										if (autorLibro.size() > 0) {
											System.out.println("Ya has introducido ese ID de autor para ese ISBN");
										} else {
											// inserta los codigos de autor con el isbn
											feedback = controlador.insertBookAuthor(codigoAutor, isbn);
											System.out.println(feedback);
										}
									} else {
										System.out.println("No existe un autor con ese ID\n");
										existeAutor = false;
									}
								} else {
									introducirMas = false;
								}
							} while (!existeAutor || introducirMas);
						}
					} while (!existeEditorial);

				}
			} while (!existeCategoria);
		}
	}

	public void updateIsbn() {
		list();
		try {
			System.out.println("Introduce el ISBN del libro que quieres editar");
			int isbn = sc.nextInt();
			// comprobar si existe el libro
			Vector<Libro> libroViejo = controlador.searchBookByIsbn(isbn);
			if (libroViejo.size() > 0) {
				System.out.println("Introduce el ISBN nuevo");
				int isbnNuevo = sc.nextInt();
				// comprobar si ya existe un libro con el isbn nuevo
				Vector<Libro> libroNuevo = controlador.searchBookByIsbn(isbnNuevo);
				if (libroNuevo.size() == 0) {
					feedback = controlador.updateBookIsbn(isbn, isbnNuevo);
					System.out.println(feedback);
				} else {
					System.out.println("Ya existe un libro con ese ISBN");
				}
			} else {
				System.out.println("No existe un libro con ese ISBN");
			}
		} catch (InputMismatchException e) {
			System.out.println("No es un valor correcto");
		}
	}

	public void updateTitle() {
		list();
		try {
			System.out.println("Introduce el ISBN del libro que quieres editar");
			int isbn = sc.nextInt();

			// comprobar si existe el libro
			Vector<Libro> libros = controlador.searchBookByIsbn(isbn);
			if (libros.size() > 0) {
				System.out.println("Introduce el titulo nuevo");
				sc.nextLine();
				String titulo = sc.nextLine();
				feedback = controlador.updateBookTitle(isbn, titulo);
				System.out.println(feedback);
			} else {
				System.out.println("No existe un libro con ese ISBN");
			}
		} catch (InputMismatchException e) {
			System.out.println("No es un valor correcto");
		}
	}

	public void updatePrice() {
		list();
		try {
			System.out.println("Introduce el ISBN del libro que quieres editar");
			int isbn = sc.nextInt();

			// comprobar si existe el libro
			Vector<Libro> libros = controlador.searchBookByIsbn(isbn);
			if (libros.size() > 0) {
				System.out.println("Introduce el precio nuevo");
				double precio = sc.nextDouble();
				feedback = controlador.updateBookPrice(isbn, precio);
				System.out.println(feedback);
			} else {
				System.out.println("No existe un libro con ese ISBN");
			}
		} catch (InputMismatchException e) {
			System.out.println("No es un valor correcto");
		}
	}

	public void updateStock() {
		list();
		try {
			System.out.println("Introduce el ISBN del libro que quieres editar");
			int isbn = sc.nextInt();

			// comprobar si existe el libro
			Vector<Libro> libros = controlador.searchBookByIsbn(isbn);
			if (libros.size() > 0) {
				System.out.println("Introduce el stock nuevo");
				int stock = sc.nextInt();
				feedback = controlador.updateBookStock(isbn, stock);
				System.out.println(feedback);
			} else {
				System.out.println("No existe un libro con ese ISBN");
			}
		} catch (InputMismatchException e) {
			System.out.println("No es un valor correcto");
		}
	}

	public void updateCategory() {
		list();
		try {
			System.out.println("Introduce el ISBN del libro que quieres editar");
			int isbn = sc.nextInt();

			// comprobar si existe el libro
			Vector<Libro> libros = controlador.searchBookByIsbn(isbn);
			if (libros.size() > 0) {
				// se listan las categorias
				vistaCategoria = new VistaCategoria(controlador);
				vistaCategoria.list();
				System.out.println("Introduce el codigo de categoria nuevo");
				int codigoCategoria = sc.nextInt();

				// comprobar si existe la categoria
				Vector<Categoria> categorias = controlador.searchCategoryById(codigoCategoria);
				if (categorias.size() > 0) {
					feedback = controlador.updateBookCategory(isbn, codigoCategoria);
					System.out.println(feedback);
				} else {
					System.out.println("No existe una categoria con ese ID");
				}
			} else {
				System.out.println("No existe un libro con ese ISBN");
			}
		} catch (InputMismatchException e) {
			System.out.println("No es un valor correcto");
		}
	}

	public void updateEditorial() {
		list();
		try {
			System.out.println("Introduce el ISBN del libro que quieres editar");
			int isbn = sc.nextInt();

			// comprobar si existe el libro
			Vector<Libro> libros = controlador.searchBookByIsbn(isbn);
			if (libros.size() > 0) {
				// se listan las editoriales
				vistaEditorial = new VistaEditorial(controlador);
				vistaEditorial.list();
				System.out.println("Introduce el codigo de editorial nuevo");
				int codigoEditorial = sc.nextInt();

				Vector<Editorial> editoriales = controlador.searchEditorialById(codigoEditorial);
				if (editoriales.size() > 0) {
					feedback = controlador.updateBookEditorial(isbn, codigoEditorial);
					System.out.println(feedback);
				} else {
					System.out.println("No existe una editorial con ese ID");
				}
			} else {
				System.out.println("No existe un libro con ese ISBN");
			}
		} catch (InputMismatchException e) {
			System.out.println("No es un valor correcto");
		}
	}

	public void list() {
		Vector<Libro> libros = controlador.listBooks();
		System.out.println("\n***** LISTADO DE LIBROS *****");
		System.out.println(
				"===========================================================================================================");
		System.out.println(
				"ISBN        Título                                        Precio   Stock    Categoría           Editorial");
		System.out.println(
				"----------- --------------------------------------------- -------- -------- ------------------- -------------------");
		for (Libro libro : libros) {
			Vector<Categoria> categoria = controlador.searchCategoryById(libro.getCodigoCategoria());
			Vector<Editorial> editorial = controlador.searchEditorialById(libro.getCodigoEditorial());
			Vector<AutorLibro> autorLibro = controlador.searchBookAuthorByIsbn(libro.getIsbn());
			// quitar ln al syso, para poner los autores en la misma linea
			System.out.println(libro.getIsbn() + " ".repeat(12 - String.valueOf(libro.getIsbn()).length())
					+ libro.getTitulo() + " ".repeat(46 - libro.getTitulo().length()) + libro.getPrecio()
					+ " ".repeat(9 - String.valueOf(libro.getPrecio()).length()) + libro.getStock()
					+ " ".repeat(9 - String.valueOf(libro.getStock()).length()) + categoria.get(0).getNombreCategoria()
					+ " ".repeat(20 - categoria.get(0).getNombreCategoria().length())
					+ editorial.get(0).getNombreEditorial()
					+ " ".repeat(20 - editorial.get(0).getNombreEditorial().length()));
			// foreach para recorrer todos los autores del libro
			for (AutorLibro autorL : autorLibro) {
				Vector<Autor> autor = controlador.searchAuthorById(autorL.getCodigoAutor());
				System.out.println(" ".repeat(12) + "-Autor: " + autor.get(0).getNombreAutor() + " ");
			}
			System.out.println();
		}
		System.out.println("***** FIN LISTADO LIBROS *****\n");
	}

	public void delete() {
		list();
		System.out.println("Introduce el ISBN del libro que quieres borrar");
		System.out.println("0 - Salir");
		int isbn = 0;
		try {
			isbn = sc.nextInt();
			if (isbn != 0) {
				feedback = controlador.deleteBook(isbn);
				System.out.println(feedback);
			}
		} catch (InputMismatchException e) {
			sc.nextLine();
			System.out.println("No es una opcion valida\n");
		}
	}

	/*
	 * AUTOR_LIBRO
	 * 
	 */
	public void insertAuthor() {
		list();
		System.out.println("Introduce el ISBN del libro al que le quieres añadir un autor");
		System.out.println("0 - Salir");

		int isbn = 0;
		try {
			isbn = sc.nextInt();
			if (isbn != 0) {
				// listar todos los autores
				vistaAutor = new VistaAutor(controlador);
				vistaAutor.list();

				System.out.println("Introduce el codigo del autor que quieres añadir al libro");
				int codigoAutor = sc.nextInt();

				// comprobar si existe ese autor
				Vector<Autor> autor = controlador.searchAuthorById(codigoAutor);
				if (autor.size() > 0) {
					// comprobar que ese autor no este ya asignado al libro
					Vector<AutorLibro> autorLibro = controlador.searchBookByAuthorIsbn(codigoAutor, isbn);
					if (autorLibro.size() == 0) {
						feedback = controlador.insertBookAuthor(codigoAutor, isbn);
						System.out.println(feedback);
					} else {
						System.out.println("Ya has introducido ese ID de autor para ese ISBN");
					}
				} else {
					System.out.println("No existe un autor con ese ID\n");
				}

			}
		} catch (InputMismatchException e) {
			e.printStackTrace();
		}
	}

	public void deleteAuthor() {
		list();
		System.out.println("Introduce el ISBN del libro del que quieres borrar el autor");
		System.out.println("0 - Salir");
		int isbn = 0;
		try {
			isbn = sc.nextInt();
			if (isbn != 0) {
				// listar los autores de ese libro
				Vector<AutorLibro> autorLibro = controlador.searchBookAuthorByIsbn(isbn);
				System.out.println("\n**** LISTADO DE AUTORES *****");
				for (AutorLibro autorL : autorLibro) {
					Vector<Autor> autor = controlador.searchAuthorById(autorL.getCodigoAutor());
					System.out.println(autor.get(0).getCodigoAutor() + " - " + autor.get(0).getNombreAutor());
				}
				System.out.println("**** FIN LISTADO DE AUTORES *****\n");

				System.out.println("Introduce el codigo del autor que quieres borrar del libro");
				int codigoAutor = sc.nextInt();

				// comprobar si existe ese autor con ese isbn
				Vector<AutorLibro> comprobarAutor = controlador.searchBookByAuthorIsbn(codigoAutor, isbn);
				if (comprobarAutor.size() > 0) {
					feedback = controlador.deleteBookAuthor(codigoAutor, isbn);
					System.out.println(feedback);
				} else {
					System.out.println("El libro no tiene el autor con ese codigo");
				}
			}
		} catch (InputMismatchException e) {
			sc.nextLine();
			System.out.println("No es una opcion valida\n");
		}
	}
}
