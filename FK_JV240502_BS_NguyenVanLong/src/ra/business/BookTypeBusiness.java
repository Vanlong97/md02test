package ra.business;

import ra.entity.Book;
import ra.entity.BookType;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class BookTypeBusiness {
    static  ArrayList<BookType> listBookType = new ArrayList<>();
    static int autoId = 1;
    public static ArrayList<BookType> getListBookType() {
        return listBookType;
    }
    // Hien thi danh sach cac loai sach chua xoa
    public static void displayBookType() {
        for (BookType bookType: listBookType) {
            if (!bookType.isDelete()) {
                bookType.displayData();
            }
        }
    }
    // Nhap vao ten loai sach va mo ta
    public static void addBookType() {
        Scanner sc = new Scanner(System.in);
        // Nhap ten khong duoc trung lap
        System.out.println("Nhap vao ten loai sach:");
        String bookName = sc.nextLine();
        // Khong duoc de trong
        if (Objects.equals(bookName, "")) {
            System.out.println("Khong duoc de trong");
            return;
        }
        // Kiem tra xem co trung lap khong
        for (BookType bookType: listBookType) {
            if (bookType.getTypeName() == bookName) {
                System.out.println("ten da ton tai!");
                break;
            }
        }
        // Nhap vao mo ta khong duoc de trong
        System.out.println("Nhap vao mo ta cho sach:");
        String description = sc.nextLine();
        // Khong duoc de trong
        if (description == "") {
            System.out.println("Khong duoc de trong");
            return;
        }
        //tao doi tuong laptop type
        BookType bookType = new BookType(autoId++,bookName,description,false);
        listBookType.add(bookType);
        System.out.println("Them loai sach thanh cong");
    }

    public static void updateBookType () {
        Scanner sc = new Scanner(System.in);
        displayBookType();
        System.out.println("Nhap vao ma sach can sua: ");
        int updateId = Integer.parseInt(sc.nextLine());
        if (!checkBookTypeIdExit(updateId)) {
            System.out.println("Khong tim thay loai sach nao co ma: " + updateId);
        } else {
            for (BookType bookType: listBookType) {
                if (bookType.getTypeId() == updateId) {
                    System.out.println("Thong tin cu cua sach");
                    bookType.displayData();
                    do {
                        System.out.println("Chon thong tin minh muon sua: ");
                        System.out.println("1. Sua ten");
                        System.out.println("2. Sua mo ta");
                        System.out.println("3. Thoat");
                        System.out.println("Lua chon cua ban");
                        int choise = Integer.parseInt(sc.nextLine());
                        switch (choise) {
                            case 1:
                                System.out.println("Nhap vao ten moi");
                                String newName = sc.nextLine();
                                if (checkBookTypeNameExit(newName)) {
                                    System.out.println("Ten da ton tai");
                                } else {
                                    bookType.setTypeName(newName);
                                    System.out.println("Sua ten thanh cong");
                                }
                                break;
                            case 2:
                                System.out.println("Nhap vap mo ta moi cho loai sach");
                                String newDescription = sc.nextLine();
                                bookType.setDescription(newDescription);
                                System.out.println("Sua mo ta thanh cong");
                                break;
                            case 3:
                                return;
                            default:
                                System.out.println("Ban phai nhap so tu 1 den 3");
                        }
                    } while (true);
                }
            }
        }
    }
    // Chuc nang xoa
    public static void deleteBookType() {
        Scanner sc = new Scanner(System.in);
        displayBookType();
        System.out.println("Nhap vao ma sach muon xoa");
        int deleteId = Integer.parseInt(sc.nextLine());
        if (checkBookTypeIdExit(deleteId)) {
            for (BookType bookType: listBookType ) {
                if (bookType.getTypeId() == deleteId) {
                    bookType.setDelete(true);
                    System.out.println("Da xoa thanh cong");
                }
            }
        } else {
            System.out.println("Khong co sach nao co ma: " + deleteId);
        }
    }

    // Thong ke so luong sach trong loai
    public static void countBookInBookType() {
        int count = 0;
        for (BookType bookType: listBookType) {
            for (Book book: BookBusiness.listBook ) {
                if (book.getBookId() == bookType.getTypeId()) {
                    count ++;
                }
            }
            System.out.println("Loai sach: " + bookType.getTypeName());
            System.out.println("So luong sach: " + count);
        }
    }

    public static boolean checkBookTypeIdExit (int typeId) {
        for (BookType bookType: listBookType ) {
            if (bookType.getTypeId() == typeId) {
                return true;
            }
        } return false;
    }
    private static boolean checkBookTypeNameExit (String typeName) {
        for (BookType bookType: listBookType ) {
            if (Objects.equals(bookType.getTypeName(), typeName)) {
                return true;
            }
        } return false;
    }
}
