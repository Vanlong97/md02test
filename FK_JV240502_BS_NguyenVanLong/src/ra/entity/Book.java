package ra.entity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.Scanner;
import ra.business.BookBusiness;
import ra.util.ConnectionDB;

public class Book implements IBookManagement {
    private static Integer bookId;
    private static String bookName;
    private static String title;
    private static String author;
    private static Integer totalPages;
    private static String content;
    private static String publisher;
    private static Double price;
    private static Integer typeId;
    private Boolean isDelete = false;

    public Book(Integer bookId, String bookName, String title, String author, Integer totalPages, String content, String publisher, Double price, Integer typeId, Boolean isDelete) {
        this.bookId = bookId;
        this.bookName = bookName;
        this.title = title;
        this.author = author;
        this.totalPages = totalPages;
        this.content = content;
        this.publisher = publisher;
        this.price = price;
        this.typeId = typeId;
        this.isDelete = isDelete;
    }

    public Book() {}

    public boolean isDelete() {
        return isDelete;
    }

    public void setDelete(Boolean delete) {
        isDelete = delete;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public int getTypeId() {
        return typeId;
    }

    public void setTypeId(int typeId) {
        this.typeId = typeId;
    }

    @Override
    public void inputData(Scanner sc) {
        System.out.println("Nhap vao ten sach");
        bookName = sc.nextLine();
        System.out.println("Nhap vao tieu de sach");
        title = sc.nextLine();
        System.out.println("Nhap vao tac gia sach");
        author = sc.nextLine();
        System.out.println("Nhap vao so trang sach");
        totalPages = Integer.parseInt(sc.nextLine());
        System.out.println("Nhap vao noi dung sach");
        content = sc.nextLine();
        System.out.println("Nhap vao ten nha xuat ban");
        author = sc.nextLine();
        System.out.println("Nhap vao gia sach");
        price = Double.parseDouble(sc.nextLine());
        System.out.println("Nhap vao ma loai sach");
        typeId = Integer.parseInt(sc.nextLine());
    }

    @Override
    public void displayData() {
        System.out.println("------------------------");
        System.out.println("Ten sach: " + bookName);
        System.out.println("Tieu de sach " + title);
        System.out.println("Tac gia sach: " + author);
        System.out.println("Tong so trang sach " + totalPages);
        System.out.println("Noi dung sach " + content);
        System.out.println("Ten nha xuat ban " + author);
        System.out.println("Gia sach " +  price);
        System.out.println("Ma loai sach " + typeId);
        System.out.println("------------------------");
    }

    // Cập nhật sách
    public static void updateBook() throws SQLException {
        Connection conn = ConnectionDB.openConnection();
        String sql = "UPDATE Book SET BookName = ?, Title = ?, Author = ?, TotalPages = ?, Content = ?, Publisher = ?, Price = ?, TypeId = ? WHERE BookId = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, bookName);
            stmt.setString(2, title);
            stmt.setString(3, author);
            stmt.setInt(4, totalPages);
            stmt.setString(5, content);
            stmt.setString(6, publisher);
            stmt.setDouble(7, price);
            stmt.setInt(8, typeId);
            stmt.setInt(9, bookId);
            // Thực hiện câu lệnh SQL và kiểm tra kết quả
            int affectedRows = stmt.executeUpdate();
            if (affectedRows > 0) {
                System.out.println("Da them thanh cong");
                BookBusiness.displayBook();
            } else {
                System.out.println("Them that bai");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}
