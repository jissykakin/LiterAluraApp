//package com.challenger.literAlura.main;
//
//import com.challenger.literAlura.models.*;
//import com.challenger.literAlura.repositories.AuthorRepository;
//import com.challenger.literAlura.repositories.BookRepository;
//import com.challenger.literAlura.services.AuthorService;
//import com.challenger.literAlura.services.BookService;
//import com.challenger.literAlura.services.ConsumeApi;
//import com.challenger.literAlura.services.ConvertDataToClass;
//import jakarta.transaction.Transactional;
//import org.aspectj.apache.bcel.Repository;
//
//import java.util.*;
//import java.util.stream.Collectors;
//
//@Transactional
//public class Principal {
//    private Scanner teclado = new Scanner(System.in);
//    private ConsumeApi consumeApi = new ConsumeApi();
//    private static final String URL_BASE = "https://gutendex.com/books/";
//    private ConvertDataToClass conversor = new ConvertDataToClass();
//    private BookService bookService;
//    private AuthorService authorService;
//
//    public Principal(BookService bookService, AuthorService authorService ) {
//        this.bookService = bookService;
//        this.authorService = authorService;
//    }
//
//    public void muestraElMenu() {
//        var opcion = -1;
//        while (opcion != 0) {
//            var menu = """
//                   \n ***** Menú Principal *****\n
//                    1 - Buscar Libro por Titulo
//                    2 - Listar Libros Registrados
//                    3 - Listar Autores Registrados
//                    4 - Listar Autores Vivos en un determinado año
//                    5 - Listar Libros por Idioma
//                    6 - Listar Libros por Nombre de Autor
//                    7 - Listar Top 10 de los Libros más descargados
//                    8 - Listar Autor por Nombre
//                    9 - Listar Estadisticas de Libros
//
//                    0 - Salir
//                    \n***********************************\n
//                    """;
//            System.out.println(menu);
//            opcion = teclado.nextInt();
//            teclado.nextLine();
//
//            switch (opcion) {
//                case 1:
//                    getBookByTittle();
//                    break;
//                case 2:
//                    showBooksOfDb();
//                    break;
//                case 3:
//                    showAuthorsOfDb();
//                    break;
//                case 4:
//                    showAuthorsByYear();
//                    break;
//                case 5:
//                    showBooksByLanguage();
//                    break;
//                case 6:
//                    getBooksByNameOfAuthor();
//                    break;
//                case 7:
//                    getTop10BooksByDownload();
//                    break;
//                case 8:
//                    getAuthorByName();
//                    break;
//                case 9:
//                    getSummaryBooks();
//                    break;
//                case 0:
//                    System.out.println("Cerrando la aplicación...");
//                    break;
//                default:
//                    System.out.println("Opción inválida");
//            }
//        }
//
//
//    }
//
//    private void getSummaryBooks() {
//        List<Book> books = bookService.findAll();
//
//        DoubleSummaryStatistics summaryStatistics = books.stream()
//                    .filter(b -> b.getDownloadCount() > 0 )
//                    .collect(Collectors.summarizingDouble(Book::getDownloadCount));
//
//            System.out.println("\n******Estadisticas de Registros de Libros******\n");
//            System.out.println("Cantidad Media de Descargas: " + summaryStatistics.getAverage() );
//            System.out.println( "Cantidad Máxima de descargas: " + summaryStatistics.getMax());
//            System.out.println( "Cantidad Minima de descargas: " + summaryStatistics.getMin() );
//            System.out.println( "Cantidad Total de descargas: " + summaryStatistics.getSum());
//            System.out.println( "Cantidad de registros de evaluados: " + summaryStatistics.getCount());
//    }
//
//
//    private DataBook searchBookByTittle() {
//        System.out.println("Escribe el nombre del libro que desea buscar");
//        var wordBook = teclado.nextLine();
//        var json = consumeApi.getDataApi(URL_BASE + "?search=" + wordBook.replace(" ", "+") );
//        System.out.println(json);
//        var dataTemp = conversor.getData(json, DataTemp.class);
//        System.out.println(dataTemp);
//
//        Optional<DataBook> bookSearch = dataTemp.books().stream()
//                .filter(b -> b.title().toUpperCase().contains(wordBook.toUpperCase()))
//                .findFirst();
//
//            if (bookSearch.isPresent()){
//                System.out.println(bookSearch.get());
//                return bookSearch.get();
//
//            }else {
//
//                return null;
//            }
//    }
//
//    private void getBookByTittle() {
//
//        try {
//            DataBook datos = searchBookByTittle();
//            if (datos!=null ) {
//                Book book = new Book(datos);
//
//                Set<Author> authors = datos.authors().stream()
//                        .map(a -> {
//                            Author author = new Author(a);
//                            // Map authorDto fields to author object
//                            return authorService.insertOrUpdateAuthor(author);
//                        })
//                        .collect(Collectors.toSet());
//
//                book.setAuthors(authors);
//
//                bookService.insertOrUpdateBook(book);
//
//            }else {
//                System.out.println("Libro No Encontrado");
//            }
//        } catch (Exception e) {
//            System.out.println("Error:" + e);
//        }
//    }
//
//    private void showBooksOfDb() {
//        List<Book> books = bookService.findAll();
//
//        printBook(books);
//    }
//
//    private void getBooksByNameOfAuthor() {
//        System.out.println("Ingrese el nombre del autor");
//        String nameSearch = teclado.nextLine();
//        List<Book> books = bookService.getBooksByNameOfAuthor( nameSearch);
//
//        printBook(books);
//    }
//
//    private void getTop10BooksByDownload() {
//        List<Book> books = bookService.getTop10BooksByDownload();
//
//        printBook(books);
//    }
//
//    private void showAuthorsByYear() {
//        int yearSearch;
//        do {
//            System.out.println("Ingrese el año de nacimiento del autor que desea buscar (solo números):");
//            while (!teclado.hasNextInt()) {
//                System.out.println("Entrada inválida. Por favor, ingrese un número entero:");
//                teclado.next();
//            }
//            yearSearch = teclado.nextInt();
//        } while (yearSearch <= 0); // Aseguramos que el año sea positivo
//
//        List<Author> authors = authorService.findAuthorByRangeOfYear( yearSearch);
//        printAuthor(authors);
//    }
//
//    private void showAuthorsOfDb() {
//        List<Author> authors = authorService.findAll();
//
//        printAuthor(authors);
//    }
//
//    private void getAuthorByName() {
//        System.out.println("Ingrese el nombre del autor");
//        String nameSearch = teclado.nextLine();
//        List<Author> authors = authorService.getAuthorByName(nameSearch);
//        printAuthor(authors);
//    }
//
//
//    private void printAuthor(List<Author> authors) {
//        System.out.println(authors);
//        if (authors == null || authors.size()==0) {
//            System.out.println("Registros no encontrados");
//        }else {
//            authors.stream()
//                    .sorted(Comparator.comparing(Author::getName))
//                    .forEach(author -> {
//                        System.out.println("****** Autores ******");
//                        System.out.println("Autor: " + author.getName());
//                        System.out.println("Fecha de Nacimiento: " + author.getBirthYear());
//                        System.out.println("Fecha de Fallecido: " + author.getDeathYear());
//                        System.out.println("Libros: ");
//                        author.getBooks().stream()
//                                .map(b -> b.getTitle().toUpperCase())
//                                .forEach(System.out::println);
//                        System.out.println("********************\n");
//                    });
//        }
//    }
//
//    private void showBooksByLanguage() {
//        System.out.println("Ingrese el idioma que desea buscar");
//        System.out.println("es - Español");
//        System.out.println("en - Ingles");
//        System.out.println("de - Alemán");
//        System.out.println("fr - Frances");
//        System.out.println("pt - Portugues");
//        String languageToSearch = teclado.nextLine();
//        List<Book> books = bookService.findBooksByLanguage(Language.fromString(languageToSearch.trim()));
//
//        printBook(books);
//    }
//
//    private void printBook( List<Book> books){
//        if (books == null || books.size()==0) {
//            System.out.println("Registros no encontrados");
//        }else {
//            books.forEach(book -> {
//                System.out.println("\n****** Libros ******");
//                System.out.println("Titulo: " + book.getTitle());
//                System.out.println("Categoria: " + book.getSubjects());
//                System.out.println("Idioma: " + book.getLanguage());
//                System.out.println("Numero de Descargas: " + book.getDownloadCount());
//                System.out.println("Link de Vista: " + book.getViewBook());
//                System.out.println("Portada de Libro: " + book.getImageBook());
//                System.out.println("Autores: ");
//                book.getAuthors().stream() // Access authors within transaction
//                        .map(Author::getName)
//                        .map(String::toUpperCase)
//                        .forEach(System.out::println);
//
//                System.out.println("********************\n");
//            });
//        }
//    }
//
//}
//