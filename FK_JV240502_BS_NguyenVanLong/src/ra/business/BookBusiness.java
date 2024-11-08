package ra.business;

import ra.entity.Book;
import ra.entity.BookType;
import ra.util.ConnectionDB;

import java.sql.*;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class BookBusiness {
    static ArrayList<Book> listBook = new ArrayList<>();
    static int autoId = 1;
    public static ArrayList<Book> getListBook() {
        return listBook;
    }
    // Hien thi tat ca cac sach co trang thai xoa la false
    // Hien thi danh sach cac loai sach chua xoa
    public static void displayBook() throws SQLException {
        Connection conn = ConnectionDB.openConnection();
        String sql = "SELECT * FROM Book WHERE IsDeleted = 0";
        try (PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                System.out.println("[ Ma sach: " + rs.getInt("BookId")
                        + " | Ten sach: " + rs.getString("BookName")
                        + " | Tieu de: " + rs.getString("Title")
                        + " | Tac gia: " + rs.getString("Author")
                        + " | So trang: " + rs.getInt("TotalPages")
                        + " | Noi dung: " + rs.getString("Content")
                        + " | Nha xuat ban: " + rs.getString("Publisher")
                        + " | Gia: " + rs.getString("Price")
                        + " | Ma loai sach: " + rs.getString("TypeId")
                        + " ]"
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Nhap thong tin vao roi luu tren csdl
    public static void addBook() {
        Scanner sc = new Scanner(System.in);
        try {
            Book book = new Book();
            System.out.println("Moi ban nhap vao ten sach");
            book.setBookName(sc.nextLine());
            System.out.println("Moi ban nhap vao tieu de sach:");
            book.setTitle(sc.nextLine());
            System.out.println("Moi ban nhap vao tac gia sach");
            book.setAuthor(sc.nextLine());
            System.out.println("Moi ban nhap vao so trang sach");
            int totalPages = Integer.parseInt(sc.nextLine());
            book.setTotalPages(totalPages);
            System.out.println("Moi ban nhap vao noi dung sach");
            book.setContent(sc.nextLine());
            System.out.println("Moi ban nhap vao nha xuat ban");
            book.setPublisher(sc.nextLine());
            System.out.println("Moi ban nhap vao gia sach");
            book.setPrice(Double.parseDouble(sc.nextLine()));
            // Kiem tra xem co nhap dung ma sach ton tai hay khong
            int bookIdCheck;
            while (true) {
                BookTypeBusiness.displayBookType();
                System.out.println("Moi ban nhap vao ma sach");
                bookIdCheck = Integer.parseInt(sc.nextLine());
                if (BookTypeBusiness.checkBookTypeIdExit(bookIdCheck)) {
                    break;
                } else {
                    System.out.println("Ma sach khong ton tai");
                }
            }
            book.setTypeId(bookIdCheck);

            String query = "INSERT INTO book (BookName,Title,Author,TotalPages,Content,Publisher,Price,TypeId) VALUES (?,?,?,?,?,?,?,?)";
            PreparedStatement preparedStatement = ConnectionDB.openConnection().prepareStatement(query);
            preparedStatement.setString(1, book.getBookName());
            preparedStatement.setString(2, book.getTitle());
            preparedStatement.setString(3, book.getAuthor());
            preparedStatement.setInt(4, book.getTotalPages());
            preparedStatement.setString(5, book.getContent());
            preparedStatement.setString(6, book.getPublisher());
            preparedStatement.setDouble(7, book.getPrice());
            preparedStatement.setInt(8, book.getTypeId());

            int affectedRows = preparedStatement.executeUpdate();
            if ( affectedRows > 0) {
                System.out.println("Cap nhap du lieu thanh cong");
            } else {
                System.out.println("Loi co so du lieu");
            }
        }catch (SQLException err) {
            throw new RuntimeException(err);
        }
    }
    // Xoa sach
    public static void deleteBook(int bookId) throws SQLException {
        displayBook();
        Connection conn = ConnectionDB.openConnection();
        String sql = "UPDATE Book SET IsDeleted = 1 WHERE BookId = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, bookId);
            int affectedRows = stmt.executeUpdate();

            if (affectedRows > 0) {
                System.out.println("Xoa thanh cong");
                displayBook();
            } else {
                System.out.println("Xoa that bai");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    // Kiem tra ten co ton tai hay khong
    public static boolean checkBookIdExit (int bookId) {
        for (Book book: listBook ) {
            if (book.getTypeId() == bookId) {
                return true;
            }
        } return false;
    }

    public static boolean checkBookNameExit (String bookName) {
        for (Book book: listBook ) {
            if (Objects.equals(book.getBookName(), bookName)) {
                return true;
            }
        } return false;
    }
}
