package ra.presentation;

import ra.business.BookBusiness;
import ra.business.BookTypeBusiness;

import java.sql.SQLException;
import java.util.Scanner;
import ra.entity.Book;
import ra.entity.BookType;

public class BookManagement {
    public static void main(String[] args) throws SQLException {
        Scanner sc = new Scanner(System.in);
        do {
            int choise = 0;
            try {
                System.out.println("***************MENU****************");
                System.out.println("1. Quan ly loai sach");
                System.out.println("2. Quan ly sach");
                System.out.println("3. Thoat chuong trinh");
                System.out.println("Lua chon cua ban\t");
                choise = Integer.parseInt(sc.nextLine());
            } catch ( NumberFormatException err ) {
                System.out.println("Vui long nhap so!");
            }
            switch (choise) {
                case 1:
                    printBookTypeMenu();
                    break;
                case 2:
                    printBookMenu();
                    break;
                case 3:
                    System.exit(0);
                default:
                    System.out.println("Vui long nhap so tu 1 den 3");
            }
        } while (true);
    }
    public static void printBookTypeMenu() {
        Scanner sc = new Scanner(System.in);
        do {
            int choise = 0;
            try {
                System.out.println("***************BOOKTYPE-MENU****************");
                System.out.println("1. Danh sach loai sach");
                System.out.println("2. Tao moi loai sach");
                System.out.println("3. Cap nhap thong tin loai sach");
                System.out.println("4. Xoa loai sach");
                System.out.println("5.Thong ke so luong theo loai sach");
                System.out.println("6. Thoat");
                System.out.println("Lua chon cua ban\t");
                choise = Integer.parseInt(sc.nextLine());
            } catch ( NumberFormatException err ) {
                System.out.println("Vui long nhap so!");
            }
            switch (choise) {
                case 0:
                    return;
                case 1:
                    BookTypeBusiness.displayBookType();
                    break;
                case 2:
                    BookTypeBusiness.addBookType();
                    break;
                case 3:
                    BookTypeBusiness.updateBookType();
                    break;
                case 4:
                    BookTypeBusiness.deleteBookType();
                    break;
                case 5:
                    BookTypeBusiness.countBookInBookType();
                    break;
                case 6:
                    return;
                default:
                    System.out.println("Vui long nhap so tu 1 den 6");
            }
        } while (true);
    }
    public static void printBookMenu() throws SQLException {
        Scanner sc = new Scanner(System.in);
        do {
            int choise = 0;
            try {
                System.out.println("***************BOOK-MENU****************");
                System.out.println("1. Danh sach sach");
                System.out.println("2. Tao moi sach");
                System.out.println("3. Cap nhap thong tin sach");
                System.out.println("4. Xoa sach ");
                System.out.println("5. Hien thi danh sach cuon sach theo gia giam dan");
                System.out.println("6. Tim kiem sach theo ten hoac noi dung ");
                System.out.println("7. Thong ke so luong sach theo nhom ");
                System.out.println("8. Thoat");
                System.out.println("Lua chon cua ban\t");
                choise = Integer.parseInt(sc.nextLine());
            } catch ( NumberFormatException err ) {
                System.out.println("Vui long nhap so!");
            }
            switch (choise) {
                case 0:
                    return;
                case 1:
                    BookBusiness.displayBook();
                    break;
                case 2:
                    BookBusiness.addBook();
                    break;
                case 3:
                    // Cập nhật thông tin sách
                    BookBusiness.displayBook();
                    System.out.print("Nhap ma ID sach can cap nhat: ");
                    int updateBookId = Integer.parseInt(sc.nextLine());
                    System.out.print("Nhap ten sach moi: ");
                    String newBookName = sc.nextLine();
                    System.out.print("Nhap tieu de moi: ");
                    String newTitle = sc.nextLine();
                    System.out.print("Nhap tac gia moi: ");
                    String newAuthor = sc.nextLine();
                    System.out.print("Nhap so trang moi: ");
                    int newTotalPages = Integer.parseInt(sc.nextLine());
                    System.out.print("Nhap noi dung moi: ");
                    String newContent = sc.nextLine();
                    System.out.print("Nhap nha xuat ban moi: ");
                    String newPublisher = sc.nextLine();
                    System.out.print("Nhap gia moi: ");
                    double newPrice = Double.parseDouble(sc.nextLine());
                    System.out.print("Nhap ma ID loai sach ơ danh sách duoi: \n ");
                    BookTypeBusiness.displayBookType();
                    int newTypeId = Integer.parseInt(sc.nextLine());
                    Book updateBook = new Book();
                    updateBook.setBookId(updateBookId);
                    updateBook.setBookName(newBookName);
                    updateBook.setTitle(newTitle);
                    updateBook.setAuthor(newAuthor);
                    updateBook.setTotalPages(newTotalPages);
                    updateBook.setContent(newContent);
                    updateBook.setPublisher(newPublisher);
                    updateBook.setPrice(newPrice);
                    updateBook.setTypeId(newTypeId);
                    updateBook.updateBook();
                    break;
                case 4:
                    BookBusiness.displayBook();
                    System.out.print("Nhap ma ID sach can xoa: ");
                    int deleteBookId = sc.nextInt();
                    BookBusiness.deleteBook(deleteBookId);
                    break;
                case 5:
                    break;
                case 6:
                    break;
                case 7:
                    break;
                case 8:
                    return;
                default:
                    System.out.println("Vui long nhap so tu 1 den 8");
            }
        } while (true);
    }
}
